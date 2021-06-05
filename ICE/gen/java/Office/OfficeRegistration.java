//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.5
//
// <auto-generated>
//
// Generated from file `Office.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package Office;

public interface OfficeRegistration extends com.zeroc.Ice.Object
{
    CaseStartedInfo startPassportCase(PassportCaseData passportCaseData, com.zeroc.Ice.Current current);

    CaseStartedInfo startVisaCase(VisaCaseData visaCaseData, com.zeroc.Ice.Current current);

    CaseStartedInfo startDriverLicenseCase(DriverLicenseCaseData driverLicenseCaseData, com.zeroc.Ice.Current current);

    void saveClient(int clientId, ClientPrx client, com.zeroc.Ice.Current current);

    /** @hidden */
    static final String[] _iceIds =
    {
        "::Ice::Object",
        "::Office::OfficeRegistration"
    };

    @Override
    default String[] ice_ids(com.zeroc.Ice.Current current)
    {
        return _iceIds;
    }

    @Override
    default String ice_id(com.zeroc.Ice.Current current)
    {
        return ice_staticId();
    }

    static String ice_staticId()
    {
        return "::Office::OfficeRegistration";
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_startPassportCase(OfficeRegistration obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        PassportCaseData iceP_passportCaseData;
        iceP_passportCaseData = PassportCaseData.ice_read(istr);
        inS.endReadParams();
        CaseStartedInfo ret = obj.startPassportCase(iceP_passportCaseData, current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        CaseStartedInfo.ice_write(ostr, ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_startVisaCase(OfficeRegistration obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        VisaCaseData iceP_visaCaseData;
        iceP_visaCaseData = VisaCaseData.ice_read(istr);
        inS.endReadParams();
        CaseStartedInfo ret = obj.startVisaCase(iceP_visaCaseData, current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        CaseStartedInfo.ice_write(ostr, ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_startDriverLicenseCase(OfficeRegistration obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        DriverLicenseCaseData iceP_driverLicenseCaseData;
        iceP_driverLicenseCaseData = DriverLicenseCaseData.ice_read(istr);
        inS.endReadParams();
        CaseStartedInfo ret = obj.startDriverLicenseCase(iceP_driverLicenseCaseData, current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        CaseStartedInfo.ice_write(ostr, ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_saveClient(OfficeRegistration obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
    {
        com.zeroc.Ice.Object._iceCheckMode(null, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        int iceP_clientId;
        ClientPrx iceP_client;
        iceP_clientId = istr.readInt();
        iceP_client = ClientPrx.uncheckedCast(istr.readProxy());
        inS.endReadParams();
        obj.saveClient(iceP_clientId, iceP_client, current);
        return inS.setResult(inS.writeEmptyParams());
    }

    /** @hidden */
    final static String[] _iceOps =
    {
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "saveClient",
        "startDriverLicenseCase",
        "startPassportCase",
        "startVisaCase"
    };

    /** @hidden */
    @Override
    default java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceDispatch(com.zeroc.IceInternal.Incoming in, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        int pos = java.util.Arrays.binarySearch(_iceOps, current.operation);
        if(pos < 0)
        {
            throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return com.zeroc.Ice.Object._iceD_ice_id(this, in, current);
            }
            case 1:
            {
                return com.zeroc.Ice.Object._iceD_ice_ids(this, in, current);
            }
            case 2:
            {
                return com.zeroc.Ice.Object._iceD_ice_isA(this, in, current);
            }
            case 3:
            {
                return com.zeroc.Ice.Object._iceD_ice_ping(this, in, current);
            }
            case 4:
            {
                return _iceD_saveClient(this, in, current);
            }
            case 5:
            {
                return _iceD_startDriverLicenseCase(this, in, current);
            }
            case 6:
            {
                return _iceD_startPassportCase(this, in, current);
            }
            case 7:
            {
                return _iceD_startVisaCase(this, in, current);
            }
        }

        assert(false);
        throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
    }
}
