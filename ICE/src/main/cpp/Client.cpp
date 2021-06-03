#include "Office.h"
#include <Ice/Ice.h>
#include <iostream>

class ClientImpl : public Office::Client {
public:
    void sendCaseStatus(int caseId, Office::CaseStatus status, const Ice::Current& current) override {
        std::cout << "Case number " << caseId << ": " << (status == Office::CaseStatus::APPROVED? "Approved" : "Rejected") << std::endl;
    }

    void sendCachedCaseStatuses(int caseId, Office::CaseStatusCache statusVector, const Ice::Current& current) override {
        for(auto status: statusVector)
            std::cout << "Case number " << caseId << ": " << (status == Office::CaseStatus::APPROVED ? "Approved" : "Rejected") << std::endl;
    }
};

int main(int argc, char* argv[])
{
    try
    {
        Ice::CommunicatorHolder ich(argc, argv);
        auto base = ich->stringToProxy("OfficeRegistration:default -p 10101");
        auto registration = Ice::checkedCast<Office::OfficeRegistrationPrx>(base);
        if(!registration)
        {
            throw std::runtime_error("Invalid proxy");
        }

        auto adapter = ich->createObjectAdapterWithEndpoints("ClientAdapter", "default -p 10102");
        auto object = std::make_shared<ClientImpl>();
        auto clientPrx = Ice::uncheckedCast<Office::ClientPrx>(adapter->add(object, Ice::stringToIdentity("Client")));
        adapter->activate();


        registration->saveClient(1, clientPrx);

        Office::VisaCaseData data;
        data.caseInfo.caseId = 1;
        data.caseInfo.clientId = 1;
        data.caseInfo.paymentDone = true;
        data.country = Office::Country::USA;
        data.passportId = 10;
        std::cout << registration->startVisaCase(data) << std::endl;
        sleep(10);
    }
    catch(const std::exception& e)
    {
        std::cerr << e.what() << std::endl;
        return 1;
    }
    return 0;
}