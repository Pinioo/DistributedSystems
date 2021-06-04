#include "Office.h"
#include <Ice/Ice.h>
#include <iostream>

class ClientImpl : public Office::Client {
public:
    void sendCaseStatus(Office::CaseStatus status, const Ice::Current& current) override {
        std::cout << "Case number " << status.caseId << ": " << (status.status == Office::CaseStatusEnum::APPROVED ? "Approved" : "Rejected") << std::endl;
    }

    void sendCachedCaseStatuses(Office::CaseStatusCache statusVector, const Ice::Current& current) override {
        for(auto status: statusVector)
            std::cout << "Case number " << status.caseId << ": " << (status.status == Office::CaseStatusEnum::APPROVED ? "Approved" : "Rejected") << std::endl;
    }
};

Office::VisaCaseData testVisaData() {
    Office::VisaCaseData data;
    data.caseInfo.clientId = 1;
    data.caseInfo.paymentDone = true;
    data.country = Office::Country::USA;
    data.passportId = 10;
    return data;
}

Office::PassportCaseData testPassportData() {
    Office::PassportCaseData data;
    data.caseInfo.clientId = 1;
    data.caseInfo.paymentDone = true;
    data.firstname = "Jakub";
    data.lastname = "Jakubowski";
    return data;
}

Office::DriverLicenseCaseData testDriverLicenseData(bool passed) {
    Office::DriverLicenseCaseData data;
    data.caseInfo.clientId = 1;
    data.caseInfo.paymentDone = true;
    data.firstname = "Jakub";
    data.lastname = "Jakubowski";

    data.examResults.push_back(false);
    data.examResults.push_back(false);
    if(passed) 
        data.examResults.push_back(true);
    data.examResults.push_back(false);
    data.examResults.push_back(false);
    return data;
}

int main(int argc, char* argv[])
{
    try {
        Ice::CommunicatorHolder ich(argc, argv);
        auto base = ich->stringToProxy("OfficeRegistration:tcp -h 127.0.0.2 -p 10101");
        auto registration = Ice::checkedCast<Office::OfficeRegistrationPrx>(base);
        if(!registration)
        {
            throw std::runtime_error("Invalid proxy");
        }

        auto adapter = ich->createObjectAdapter("");
        auto object = std::make_shared<ClientImpl>();
        auto clientPrx = Ice::uncheckedCast<Office::ClientPrx>(adapter->addWithUUID(object));
        registration->ice_getCachedConnection()->setAdapter(adapter);
        registration->ice_getCachedConnection()->setACM(30, Ice::ACMClose::CloseOff, Ice::ACMHeartbeat::HeartbeatAlways);
        registration->saveClient(1, clientPrx);

        std::string c = "";
        while(c != "0") {
            if(c == "1")
                std::cout << "Visa case, case number: " << registration->startVisaCase(testVisaData()) << std::endl;
            if(c == "2")
                std::cout << "Passport case, case number: " << registration->startPassportCase(testPassportData()) << std::endl;
            if(c == "3")
                std::cout << "Driver license case, case number: " << registration->startDriverLicenseCase(testDriverLicenseData(true)) << std::endl;
            std::cin >> c;
        }
    } catch(const std::exception& e) {
        std::cerr << e.what() << std::endl;
        return 1;
    }
    return 0;
}