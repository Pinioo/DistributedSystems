#include "Office.h"
#include <Ice/Ice.h>
#include <iostream>
#include <string>

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

Office::VisaCaseData testVisaData(int clientId, Office::Country country) {
    Office::VisaCaseData data;
    data.caseInfo.clientId = clientId;
    data.caseInfo.paymentDone = true;
    data.country = country;
    data.passportId = 10;
    return data;
}

Office::PassportCaseData testPassportData(int clientId, bool payment) {
    Office::PassportCaseData data;
    data.caseInfo.clientId = clientId;
    data.caseInfo.paymentDone = payment;
    data.firstname = "Jakub";
    data.lastname = "Jakubowski";
    return data;
}

Office::DriverLicenseCaseData testDriverLicenseData(int clientId, bool passed) {
    Office::DriverLicenseCaseData data;
    data.caseInfo.clientId = clientId;
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
    int clientId = 1;
    if(argc > 1){
        try{
            clientId = std::stoi(argv[1]);
        } catch(const std::exception& ex){}
    }
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

        registration->ice_getConnection()->setAdapter(adapter);
        registration->ice_getConnection()->setACM(30, Ice::ACMClose::CloseOff, Ice::ACMHeartbeat::HeartbeatAlways);
        registration->saveClient(clientId, clientPrx);

        std::string c = "";
        std::cout << "0. Quit" << std::endl;
        std::cout << "1. USA visa" << std::endl;
        std::cout << "2. North Korea visa" << std::endl;
        std::cout << "3. Passport" << std::endl;
        std::cout << "4. Passport without payment" << std::endl;
        std::cout << "5. Driver license passed" << std::endl;
        std::cout << "6. Driver license not passed" << std::endl;
        while(c != "0") {
            Office::CaseStartedInfo startedCaseInfo;
            if(c == "1") {
                std::cout << "Visa case, case number: ";
                startedCaseInfo = registration->startVisaCase(testVisaData(clientId, Office::Country::USA));
            }
            else if(c == "2") {
                std::cout << "Visa case, case number: ";
                startedCaseInfo = registration->startVisaCase(testVisaData(clientId, Office::Country::NorthKorea));
            }
            else if(c == "3") {
                std::cout << "Passport case, case number: ";
                startedCaseInfo = registration->startPassportCase(testPassportData(clientId, true));
            }
            else if(c == "4") {
                std::cout << "Passport case, case number: ";
                startedCaseInfo = registration->startPassportCase(testPassportData(clientId, false));
            }
            else if(c == "5") {
                std::cout << "Driver license case, case number: ";
                startedCaseInfo = registration->startDriverLicenseCase(testDriverLicenseData(clientId,true));
            }
            else if(c == "6") {
                std::cout << "Driver license case, case number: ";
                startedCaseInfo = registration->startDriverLicenseCase(testDriverLicenseData(clientId,false));
            }
            else {
                std::cin >> c;
                continue;
            }

            std::cout << startedCaseInfo.caseId << std::endl;
            if(!startedCaseInfo.serverHasProxy){
                registration->ice_getConnection()->setAdapter(adapter);
                registration->ice_getConnection()->setACM(30, Ice::ACMClose::CloseOff, Ice::ACMHeartbeat::HeartbeatAlways);
                registration->saveClient(clientId, clientPrx);
            }
            std::cin >> c;
        }
    } catch(const std::exception& e) {
        std::cerr << e.what() << std::endl;
        return 1;
    }
    return 0;
}