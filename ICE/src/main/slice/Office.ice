module Office {
    struct CaseInfo {
        int clientId;
        bool paymentDone;
    };

    struct PassportCaseData {
        CaseInfo caseInfo;
        string firstname;
        string lastname;
    };

    enum Country {
        USA, Germany, NorthKorea
    };

    struct VisaCaseData {
        CaseInfo caseInfo;
        string passportId;
        Country country;
    };

    sequence<bool> ExamResults;

    struct DriverLicenseCaseData {
        CaseInfo caseInfo;
        string firstname;
        string lastname;
        ExamResults examResults;
    };

    enum CaseStatusEnum {
        REJECTED, APPROVED
    };

    struct CaseStatus {
        int caseId;
        CaseStatusEnum status;
    };

    struct CaseStartedInfo {
        int caseId;
        bool serverHasProxy;
    }

    sequence<CaseStatus> CaseStatusCache;

    interface Client {
        void sendCaseStatus(CaseStatus caseStatus);
        void sendCachedCaseStatuses(CaseStatusCache caseStatusCache);
    };

    interface OfficeRegistration {
        CaseStartedInfo startPassportCase(PassportCaseData passportCaseData);
        CaseStartedInfo startVisaCase(VisaCaseData visaCaseData);
        CaseStartedInfo startDriverLicenseCase(DriverLicenseCaseData driverLicenseCaseData);

        void saveClient(int clientId, Client* client);
    };
};