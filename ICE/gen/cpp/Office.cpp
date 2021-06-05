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

#include <Office.h>
#include <IceUtil/PushDisableWarnings.h>
#include <Ice/LocalException.h>
#include <Ice/ValueFactory.h>
#include <Ice/OutgoingAsync.h>
#include <Ice/InputStream.h>
#include <Ice/OutputStream.h>
#include <IceUtil/PopDisableWarnings.h>

#if defined(_MSC_VER)
#   pragma warning(disable:4458) // declaration of ... hides class member
#elif defined(__clang__)
#   pragma clang diagnostic ignored "-Wshadow"
#elif defined(__GNUC__)
#   pragma GCC diagnostic ignored "-Wshadow"
#endif

#ifndef ICE_IGNORE_VERSION
#   if ICE_INT_VERSION / 100 != 307
#       error Ice version mismatch!
#   endif
#   if ICE_INT_VERSION % 100 >= 50
#       error Beta header file detected
#   endif
#   if ICE_INT_VERSION % 100 < 5
#       error Ice patch level mismatch!
#   endif
#endif

#ifdef ICE_CPP11_MAPPING // C++11 mapping

namespace
{

const ::std::string iceC_Office_Client_ids[2] =
{
    "::Ice::Object",
    "::Office::Client"
};
const ::std::string iceC_Office_Client_ops[] =
{
    "ice_id",
    "ice_ids",
    "ice_isA",
    "ice_ping",
    "sendCachedCaseStatuses",
    "sendCaseStatus"
};
const ::std::string iceC_Office_Client_sendCaseStatus_name = "sendCaseStatus";
const ::std::string iceC_Office_Client_sendCachedCaseStatuses_name = "sendCachedCaseStatuses";

const ::std::string iceC_Office_OfficeRegistration_ids[2] =
{
    "::Ice::Object",
    "::Office::OfficeRegistration"
};
const ::std::string iceC_Office_OfficeRegistration_ops[] =
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
const ::std::string iceC_Office_OfficeRegistration_startPassportCase_name = "startPassportCase";
const ::std::string iceC_Office_OfficeRegistration_startVisaCase_name = "startVisaCase";
const ::std::string iceC_Office_OfficeRegistration_startDriverLicenseCase_name = "startDriverLicenseCase";
const ::std::string iceC_Office_OfficeRegistration_saveClient_name = "saveClient";

}

bool
Office::Client::ice_isA(::std::string s, const ::Ice::Current&) const
{
    return ::std::binary_search(iceC_Office_Client_ids, iceC_Office_Client_ids + 2, s);
}

::std::vector<::std::string>
Office::Client::ice_ids(const ::Ice::Current&) const
{
    return ::std::vector<::std::string>(&iceC_Office_Client_ids[0], &iceC_Office_Client_ids[2]);
}

::std::string
Office::Client::ice_id(const ::Ice::Current&) const
{
    return ice_staticId();
}

const ::std::string&
Office::Client::ice_staticId()
{
    static const ::std::string typeId = "::Office::Client";
    return typeId;
}

/// \cond INTERNAL
bool
Office::Client::_iceD_sendCaseStatus(::IceInternal::Incoming& inS, const ::Ice::Current& current)
{
    _iceCheckMode(::Ice::OperationMode::Normal, current.mode);
    auto istr = inS.startReadParams();
    CaseStatus iceP_caseStatus;
    istr->readAll(iceP_caseStatus);
    inS.endReadParams();
    this->sendCaseStatus(::std::move(iceP_caseStatus), current);
    inS.writeEmptyParams();
    return true;
}
/// \endcond

/// \cond INTERNAL
bool
Office::Client::_iceD_sendCachedCaseStatuses(::IceInternal::Incoming& inS, const ::Ice::Current& current)
{
    _iceCheckMode(::Ice::OperationMode::Normal, current.mode);
    auto istr = inS.startReadParams();
    CaseStatusCache iceP_caseStatusCache;
    istr->readAll(iceP_caseStatusCache);
    inS.endReadParams();
    this->sendCachedCaseStatuses(::std::move(iceP_caseStatusCache), current);
    inS.writeEmptyParams();
    return true;
}
/// \endcond

/// \cond INTERNAL
bool
Office::Client::_iceDispatch(::IceInternal::Incoming& in, const ::Ice::Current& current)
{
    ::std::pair<const ::std::string*, const ::std::string*> r = ::std::equal_range(iceC_Office_Client_ops, iceC_Office_Client_ops + 6, current.operation);
    if(r.first == r.second)
    {
        throw ::Ice::OperationNotExistException(__FILE__, __LINE__, current.id, current.facet, current.operation);
    }

    switch(r.first - iceC_Office_Client_ops)
    {
        case 0:
        {
            return _iceD_ice_id(in, current);
        }
        case 1:
        {
            return _iceD_ice_ids(in, current);
        }
        case 2:
        {
            return _iceD_ice_isA(in, current);
        }
        case 3:
        {
            return _iceD_ice_ping(in, current);
        }
        case 4:
        {
            return _iceD_sendCachedCaseStatuses(in, current);
        }
        case 5:
        {
            return _iceD_sendCaseStatus(in, current);
        }
        default:
        {
            assert(false);
            throw ::Ice::OperationNotExistException(__FILE__, __LINE__, current.id, current.facet, current.operation);
        }
    }
}
/// \endcond

bool
Office::OfficeRegistration::ice_isA(::std::string s, const ::Ice::Current&) const
{
    return ::std::binary_search(iceC_Office_OfficeRegistration_ids, iceC_Office_OfficeRegistration_ids + 2, s);
}

::std::vector<::std::string>
Office::OfficeRegistration::ice_ids(const ::Ice::Current&) const
{
    return ::std::vector<::std::string>(&iceC_Office_OfficeRegistration_ids[0], &iceC_Office_OfficeRegistration_ids[2]);
}

::std::string
Office::OfficeRegistration::ice_id(const ::Ice::Current&) const
{
    return ice_staticId();
}

const ::std::string&
Office::OfficeRegistration::ice_staticId()
{
    static const ::std::string typeId = "::Office::OfficeRegistration";
    return typeId;
}

/// \cond INTERNAL
bool
Office::OfficeRegistration::_iceD_startPassportCase(::IceInternal::Incoming& inS, const ::Ice::Current& current)
{
    _iceCheckMode(::Ice::OperationMode::Normal, current.mode);
    auto istr = inS.startReadParams();
    PassportCaseData iceP_passportCaseData;
    istr->readAll(iceP_passportCaseData);
    inS.endReadParams();
    CaseStartedInfo ret = this->startPassportCase(::std::move(iceP_passportCaseData), current);
    auto ostr = inS.startWriteParams();
    ostr->writeAll(ret);
    inS.endWriteParams();
    return true;
}
/// \endcond

/// \cond INTERNAL
bool
Office::OfficeRegistration::_iceD_startVisaCase(::IceInternal::Incoming& inS, const ::Ice::Current& current)
{
    _iceCheckMode(::Ice::OperationMode::Normal, current.mode);
    auto istr = inS.startReadParams();
    VisaCaseData iceP_visaCaseData;
    istr->readAll(iceP_visaCaseData);
    inS.endReadParams();
    CaseStartedInfo ret = this->startVisaCase(::std::move(iceP_visaCaseData), current);
    auto ostr = inS.startWriteParams();
    ostr->writeAll(ret);
    inS.endWriteParams();
    return true;
}
/// \endcond

/// \cond INTERNAL
bool
Office::OfficeRegistration::_iceD_startDriverLicenseCase(::IceInternal::Incoming& inS, const ::Ice::Current& current)
{
    _iceCheckMode(::Ice::OperationMode::Normal, current.mode);
    auto istr = inS.startReadParams();
    DriverLicenseCaseData iceP_driverLicenseCaseData;
    istr->readAll(iceP_driverLicenseCaseData);
    inS.endReadParams();
    CaseStartedInfo ret = this->startDriverLicenseCase(::std::move(iceP_driverLicenseCaseData), current);
    auto ostr = inS.startWriteParams();
    ostr->writeAll(ret);
    inS.endWriteParams();
    return true;
}
/// \endcond

/// \cond INTERNAL
bool
Office::OfficeRegistration::_iceD_saveClient(::IceInternal::Incoming& inS, const ::Ice::Current& current)
{
    _iceCheckMode(::Ice::OperationMode::Normal, current.mode);
    auto istr = inS.startReadParams();
    int iceP_clientId;
    ::std::shared_ptr<ClientPrx> iceP_client;
    istr->readAll(iceP_clientId, iceP_client);
    inS.endReadParams();
    this->saveClient(iceP_clientId, ::std::move(iceP_client), current);
    inS.writeEmptyParams();
    return true;
}
/// \endcond

/// \cond INTERNAL
bool
Office::OfficeRegistration::_iceDispatch(::IceInternal::Incoming& in, const ::Ice::Current& current)
{
    ::std::pair<const ::std::string*, const ::std::string*> r = ::std::equal_range(iceC_Office_OfficeRegistration_ops, iceC_Office_OfficeRegistration_ops + 8, current.operation);
    if(r.first == r.second)
    {
        throw ::Ice::OperationNotExistException(__FILE__, __LINE__, current.id, current.facet, current.operation);
    }

    switch(r.first - iceC_Office_OfficeRegistration_ops)
    {
        case 0:
        {
            return _iceD_ice_id(in, current);
        }
        case 1:
        {
            return _iceD_ice_ids(in, current);
        }
        case 2:
        {
            return _iceD_ice_isA(in, current);
        }
        case 3:
        {
            return _iceD_ice_ping(in, current);
        }
        case 4:
        {
            return _iceD_saveClient(in, current);
        }
        case 5:
        {
            return _iceD_startDriverLicenseCase(in, current);
        }
        case 6:
        {
            return _iceD_startPassportCase(in, current);
        }
        case 7:
        {
            return _iceD_startVisaCase(in, current);
        }
        default:
        {
            assert(false);
            throw ::Ice::OperationNotExistException(__FILE__, __LINE__, current.id, current.facet, current.operation);
        }
    }
}
/// \endcond

/// \cond INTERNAL
void
Office::ClientPrx::_iceI_sendCaseStatus(const ::std::shared_ptr<::IceInternal::OutgoingAsyncT<void>>& outAsync, const CaseStatus& iceP_caseStatus, const ::Ice::Context& context)
{
    outAsync->invoke(iceC_Office_Client_sendCaseStatus_name, ::Ice::OperationMode::Normal, ::Ice::FormatType::DefaultFormat, context,
        [&](::Ice::OutputStream* ostr)
        {
            ostr->writeAll(iceP_caseStatus);
        },
        nullptr);
}
/// \endcond

/// \cond INTERNAL
void
Office::ClientPrx::_iceI_sendCachedCaseStatuses(const ::std::shared_ptr<::IceInternal::OutgoingAsyncT<void>>& outAsync, const CaseStatusCache& iceP_caseStatusCache, const ::Ice::Context& context)
{
    outAsync->invoke(iceC_Office_Client_sendCachedCaseStatuses_name, ::Ice::OperationMode::Normal, ::Ice::FormatType::DefaultFormat, context,
        [&](::Ice::OutputStream* ostr)
        {
            ostr->writeAll(iceP_caseStatusCache);
        },
        nullptr);
}
/// \endcond

/// \cond INTERNAL
::std::shared_ptr<::Ice::ObjectPrx>
Office::ClientPrx::_newInstance() const
{
    return ::IceInternal::createProxy<ClientPrx>();
}
/// \endcond

const ::std::string&
Office::ClientPrx::ice_staticId()
{
    return Client::ice_staticId();
}

/// \cond INTERNAL
void
Office::OfficeRegistrationPrx::_iceI_startPassportCase(const ::std::shared_ptr<::IceInternal::OutgoingAsyncT<::Office::CaseStartedInfo>>& outAsync, const PassportCaseData& iceP_passportCaseData, const ::Ice::Context& context)
{
    _checkTwowayOnly(iceC_Office_OfficeRegistration_startPassportCase_name);
    outAsync->invoke(iceC_Office_OfficeRegistration_startPassportCase_name, ::Ice::OperationMode::Normal, ::Ice::FormatType::DefaultFormat, context,
        [&](::Ice::OutputStream* ostr)
        {
            ostr->writeAll(iceP_passportCaseData);
        },
        nullptr);
}
/// \endcond

/// \cond INTERNAL
void
Office::OfficeRegistrationPrx::_iceI_startVisaCase(const ::std::shared_ptr<::IceInternal::OutgoingAsyncT<::Office::CaseStartedInfo>>& outAsync, const VisaCaseData& iceP_visaCaseData, const ::Ice::Context& context)
{
    _checkTwowayOnly(iceC_Office_OfficeRegistration_startVisaCase_name);
    outAsync->invoke(iceC_Office_OfficeRegistration_startVisaCase_name, ::Ice::OperationMode::Normal, ::Ice::FormatType::DefaultFormat, context,
        [&](::Ice::OutputStream* ostr)
        {
            ostr->writeAll(iceP_visaCaseData);
        },
        nullptr);
}
/// \endcond

/// \cond INTERNAL
void
Office::OfficeRegistrationPrx::_iceI_startDriverLicenseCase(const ::std::shared_ptr<::IceInternal::OutgoingAsyncT<::Office::CaseStartedInfo>>& outAsync, const DriverLicenseCaseData& iceP_driverLicenseCaseData, const ::Ice::Context& context)
{
    _checkTwowayOnly(iceC_Office_OfficeRegistration_startDriverLicenseCase_name);
    outAsync->invoke(iceC_Office_OfficeRegistration_startDriverLicenseCase_name, ::Ice::OperationMode::Normal, ::Ice::FormatType::DefaultFormat, context,
        [&](::Ice::OutputStream* ostr)
        {
            ostr->writeAll(iceP_driverLicenseCaseData);
        },
        nullptr);
}
/// \endcond

/// \cond INTERNAL
void
Office::OfficeRegistrationPrx::_iceI_saveClient(const ::std::shared_ptr<::IceInternal::OutgoingAsyncT<void>>& outAsync, int iceP_clientId, const ::std::shared_ptr<ClientPrx>& iceP_client, const ::Ice::Context& context)
{
    outAsync->invoke(iceC_Office_OfficeRegistration_saveClient_name, ::Ice::OperationMode::Normal, ::Ice::FormatType::DefaultFormat, context,
        [&](::Ice::OutputStream* ostr)
        {
            ostr->writeAll(iceP_clientId, iceP_client);
        },
        nullptr);
}
/// \endcond

/// \cond INTERNAL
::std::shared_ptr<::Ice::ObjectPrx>
Office::OfficeRegistrationPrx::_newInstance() const
{
    return ::IceInternal::createProxy<OfficeRegistrationPrx>();
}
/// \endcond

const ::std::string&
Office::OfficeRegistrationPrx::ice_staticId()
{
    return OfficeRegistration::ice_staticId();
}

namespace Ice
{
}

#else // C++98 mapping

namespace
{

const ::std::string iceC_Office_Client_sendCaseStatus_name = "sendCaseStatus";

const ::std::string iceC_Office_Client_sendCachedCaseStatuses_name = "sendCachedCaseStatuses";

const ::std::string iceC_Office_OfficeRegistration_startPassportCase_name = "startPassportCase";

const ::std::string iceC_Office_OfficeRegistration_startVisaCase_name = "startVisaCase";

const ::std::string iceC_Office_OfficeRegistration_startDriverLicenseCase_name = "startDriverLicenseCase";

const ::std::string iceC_Office_OfficeRegistration_saveClient_name = "saveClient";

}

/// \cond INTERNAL
::IceProxy::Ice::Object* ::IceProxy::Office::upCast(Client* p) { return p; }

void
::IceProxy::Office::_readProxy(::Ice::InputStream* istr, ::IceInternal::ProxyHandle< Client>& v)
{
    ::Ice::ObjectPrx proxy;
    istr->read(proxy);
    if(!proxy)
    {
        v = 0;
    }
    else
    {
        v = new Client;
        v->_copyFrom(proxy);
    }
}
/// \endcond

::Ice::AsyncResultPtr
IceProxy::Office::Client::_iceI_begin_sendCaseStatus(const ::Office::CaseStatus& iceP_caseStatus, const ::Ice::Context& context, const ::IceInternal::CallbackBasePtr& del, const ::Ice::LocalObjectPtr& cookie, bool sync)
{
    ::IceInternal::OutgoingAsyncPtr result = new ::IceInternal::CallbackOutgoing(this, iceC_Office_Client_sendCaseStatus_name, del, cookie, sync);
    try
    {
        result->prepare(iceC_Office_Client_sendCaseStatus_name, ::Ice::Normal, context);
        ::Ice::OutputStream* ostr = result->startWriteParams(::Ice::DefaultFormat);
        ostr->write(iceP_caseStatus);
        result->endWriteParams();
        result->invoke(iceC_Office_Client_sendCaseStatus_name);
    }
    catch(const ::Ice::Exception& ex)
    {
        result->abort(ex);
    }
    return result;
}

void
IceProxy::Office::Client::end_sendCaseStatus(const ::Ice::AsyncResultPtr& result)
{
    _end(result, iceC_Office_Client_sendCaseStatus_name);
}

::Ice::AsyncResultPtr
IceProxy::Office::Client::_iceI_begin_sendCachedCaseStatuses(const ::Office::CaseStatusCache& iceP_caseStatusCache, const ::Ice::Context& context, const ::IceInternal::CallbackBasePtr& del, const ::Ice::LocalObjectPtr& cookie, bool sync)
{
    ::IceInternal::OutgoingAsyncPtr result = new ::IceInternal::CallbackOutgoing(this, iceC_Office_Client_sendCachedCaseStatuses_name, del, cookie, sync);
    try
    {
        result->prepare(iceC_Office_Client_sendCachedCaseStatuses_name, ::Ice::Normal, context);
        ::Ice::OutputStream* ostr = result->startWriteParams(::Ice::DefaultFormat);
        ostr->write(iceP_caseStatusCache);
        result->endWriteParams();
        result->invoke(iceC_Office_Client_sendCachedCaseStatuses_name);
    }
    catch(const ::Ice::Exception& ex)
    {
        result->abort(ex);
    }
    return result;
}

void
IceProxy::Office::Client::end_sendCachedCaseStatuses(const ::Ice::AsyncResultPtr& result)
{
    _end(result, iceC_Office_Client_sendCachedCaseStatuses_name);
}

/// \cond INTERNAL
::IceProxy::Ice::Object*
IceProxy::Office::Client::_newInstance() const
{
    return new Client;
}
/// \endcond

const ::std::string&
IceProxy::Office::Client::ice_staticId()
{
    return ::Office::Client::ice_staticId();
}

/// \cond INTERNAL
::IceProxy::Ice::Object* ::IceProxy::Office::upCast(OfficeRegistration* p) { return p; }

void
::IceProxy::Office::_readProxy(::Ice::InputStream* istr, ::IceInternal::ProxyHandle< OfficeRegistration>& v)
{
    ::Ice::ObjectPrx proxy;
    istr->read(proxy);
    if(!proxy)
    {
        v = 0;
    }
    else
    {
        v = new OfficeRegistration;
        v->_copyFrom(proxy);
    }
}
/// \endcond

::Ice::AsyncResultPtr
IceProxy::Office::OfficeRegistration::_iceI_begin_startPassportCase(const ::Office::PassportCaseData& iceP_passportCaseData, const ::Ice::Context& context, const ::IceInternal::CallbackBasePtr& del, const ::Ice::LocalObjectPtr& cookie, bool sync)
{
    _checkTwowayOnly(iceC_Office_OfficeRegistration_startPassportCase_name, sync);
    ::IceInternal::OutgoingAsyncPtr result = new ::IceInternal::CallbackOutgoing(this, iceC_Office_OfficeRegistration_startPassportCase_name, del, cookie, sync);
    try
    {
        result->prepare(iceC_Office_OfficeRegistration_startPassportCase_name, ::Ice::Normal, context);
        ::Ice::OutputStream* ostr = result->startWriteParams(::Ice::DefaultFormat);
        ostr->write(iceP_passportCaseData);
        result->endWriteParams();
        result->invoke(iceC_Office_OfficeRegistration_startPassportCase_name);
    }
    catch(const ::Ice::Exception& ex)
    {
        result->abort(ex);
    }
    return result;
}

::Office::CaseStartedInfo
IceProxy::Office::OfficeRegistration::end_startPassportCase(const ::Ice::AsyncResultPtr& result)
{
    ::Ice::AsyncResult::_check(result, this, iceC_Office_OfficeRegistration_startPassportCase_name);
    ::Office::CaseStartedInfo ret;
    if(!result->_waitForResponse())
    {
        try
        {
            result->_throwUserException();
        }
        catch(const ::Ice::UserException& ex)
        {
            throw ::Ice::UnknownUserException(__FILE__, __LINE__, ex.ice_id());
        }
    }
    ::Ice::InputStream* istr = result->_startReadParams();
    istr->read(ret);
    result->_endReadParams();
    return ret;
}

::Ice::AsyncResultPtr
IceProxy::Office::OfficeRegistration::_iceI_begin_startVisaCase(const ::Office::VisaCaseData& iceP_visaCaseData, const ::Ice::Context& context, const ::IceInternal::CallbackBasePtr& del, const ::Ice::LocalObjectPtr& cookie, bool sync)
{
    _checkTwowayOnly(iceC_Office_OfficeRegistration_startVisaCase_name, sync);
    ::IceInternal::OutgoingAsyncPtr result = new ::IceInternal::CallbackOutgoing(this, iceC_Office_OfficeRegistration_startVisaCase_name, del, cookie, sync);
    try
    {
        result->prepare(iceC_Office_OfficeRegistration_startVisaCase_name, ::Ice::Normal, context);
        ::Ice::OutputStream* ostr = result->startWriteParams(::Ice::DefaultFormat);
        ostr->write(iceP_visaCaseData);
        result->endWriteParams();
        result->invoke(iceC_Office_OfficeRegistration_startVisaCase_name);
    }
    catch(const ::Ice::Exception& ex)
    {
        result->abort(ex);
    }
    return result;
}

::Office::CaseStartedInfo
IceProxy::Office::OfficeRegistration::end_startVisaCase(const ::Ice::AsyncResultPtr& result)
{
    ::Ice::AsyncResult::_check(result, this, iceC_Office_OfficeRegistration_startVisaCase_name);
    ::Office::CaseStartedInfo ret;
    if(!result->_waitForResponse())
    {
        try
        {
            result->_throwUserException();
        }
        catch(const ::Ice::UserException& ex)
        {
            throw ::Ice::UnknownUserException(__FILE__, __LINE__, ex.ice_id());
        }
    }
    ::Ice::InputStream* istr = result->_startReadParams();
    istr->read(ret);
    result->_endReadParams();
    return ret;
}

::Ice::AsyncResultPtr
IceProxy::Office::OfficeRegistration::_iceI_begin_startDriverLicenseCase(const ::Office::DriverLicenseCaseData& iceP_driverLicenseCaseData, const ::Ice::Context& context, const ::IceInternal::CallbackBasePtr& del, const ::Ice::LocalObjectPtr& cookie, bool sync)
{
    _checkTwowayOnly(iceC_Office_OfficeRegistration_startDriverLicenseCase_name, sync);
    ::IceInternal::OutgoingAsyncPtr result = new ::IceInternal::CallbackOutgoing(this, iceC_Office_OfficeRegistration_startDriverLicenseCase_name, del, cookie, sync);
    try
    {
        result->prepare(iceC_Office_OfficeRegistration_startDriverLicenseCase_name, ::Ice::Normal, context);
        ::Ice::OutputStream* ostr = result->startWriteParams(::Ice::DefaultFormat);
        ostr->write(iceP_driverLicenseCaseData);
        result->endWriteParams();
        result->invoke(iceC_Office_OfficeRegistration_startDriverLicenseCase_name);
    }
    catch(const ::Ice::Exception& ex)
    {
        result->abort(ex);
    }
    return result;
}

::Office::CaseStartedInfo
IceProxy::Office::OfficeRegistration::end_startDriverLicenseCase(const ::Ice::AsyncResultPtr& result)
{
    ::Ice::AsyncResult::_check(result, this, iceC_Office_OfficeRegistration_startDriverLicenseCase_name);
    ::Office::CaseStartedInfo ret;
    if(!result->_waitForResponse())
    {
        try
        {
            result->_throwUserException();
        }
        catch(const ::Ice::UserException& ex)
        {
            throw ::Ice::UnknownUserException(__FILE__, __LINE__, ex.ice_id());
        }
    }
    ::Ice::InputStream* istr = result->_startReadParams();
    istr->read(ret);
    result->_endReadParams();
    return ret;
}

::Ice::AsyncResultPtr
IceProxy::Office::OfficeRegistration::_iceI_begin_saveClient(::Ice::Int iceP_clientId, const ::Office::ClientPrx& iceP_client, const ::Ice::Context& context, const ::IceInternal::CallbackBasePtr& del, const ::Ice::LocalObjectPtr& cookie, bool sync)
{
    ::IceInternal::OutgoingAsyncPtr result = new ::IceInternal::CallbackOutgoing(this, iceC_Office_OfficeRegistration_saveClient_name, del, cookie, sync);
    try
    {
        result->prepare(iceC_Office_OfficeRegistration_saveClient_name, ::Ice::Normal, context);
        ::Ice::OutputStream* ostr = result->startWriteParams(::Ice::DefaultFormat);
        ostr->write(iceP_clientId);
        ostr->write(iceP_client);
        result->endWriteParams();
        result->invoke(iceC_Office_OfficeRegistration_saveClient_name);
    }
    catch(const ::Ice::Exception& ex)
    {
        result->abort(ex);
    }
    return result;
}

void
IceProxy::Office::OfficeRegistration::end_saveClient(const ::Ice::AsyncResultPtr& result)
{
    _end(result, iceC_Office_OfficeRegistration_saveClient_name);
}

/// \cond INTERNAL
::IceProxy::Ice::Object*
IceProxy::Office::OfficeRegistration::_newInstance() const
{
    return new OfficeRegistration;
}
/// \endcond

const ::std::string&
IceProxy::Office::OfficeRegistration::ice_staticId()
{
    return ::Office::OfficeRegistration::ice_staticId();
}

Office::Client::~Client()
{
}

/// \cond INTERNAL
::Ice::Object* Office::upCast(Client* p) { return p; }

/// \endcond

namespace
{
const ::std::string iceC_Office_Client_ids[2] =
{
    "::Ice::Object",
    "::Office::Client"
};

}

bool
Office::Client::ice_isA(const ::std::string& s, const ::Ice::Current&) const
{
    return ::std::binary_search(iceC_Office_Client_ids, iceC_Office_Client_ids + 2, s);
}

::std::vector< ::std::string>
Office::Client::ice_ids(const ::Ice::Current&) const
{
    return ::std::vector< ::std::string>(&iceC_Office_Client_ids[0], &iceC_Office_Client_ids[2]);
}

const ::std::string&
Office::Client::ice_id(const ::Ice::Current&) const
{
    return ice_staticId();
}

const ::std::string&
Office::Client::ice_staticId()
{
#ifdef ICE_HAS_THREAD_SAFE_LOCAL_STATIC
    static const ::std::string typeId = "::Office::Client";
    return typeId;
#else
    return iceC_Office_Client_ids[1];
#endif
}

/// \cond INTERNAL
bool
Office::Client::_iceD_sendCaseStatus(::IceInternal::Incoming& inS, const ::Ice::Current& current)
{
    _iceCheckMode(::Ice::Normal, current.mode);
    ::Ice::InputStream* istr = inS.startReadParams();
    CaseStatus iceP_caseStatus;
    istr->read(iceP_caseStatus);
    inS.endReadParams();
    this->sendCaseStatus(iceP_caseStatus, current);
    inS.writeEmptyParams();
    return true;
}
/// \endcond

/// \cond INTERNAL
bool
Office::Client::_iceD_sendCachedCaseStatuses(::IceInternal::Incoming& inS, const ::Ice::Current& current)
{
    _iceCheckMode(::Ice::Normal, current.mode);
    ::Ice::InputStream* istr = inS.startReadParams();
    CaseStatusCache iceP_caseStatusCache;
    istr->read(iceP_caseStatusCache);
    inS.endReadParams();
    this->sendCachedCaseStatuses(iceP_caseStatusCache, current);
    inS.writeEmptyParams();
    return true;
}
/// \endcond

namespace
{
const ::std::string iceC_Office_Client_all[] =
{
    "ice_id",
    "ice_ids",
    "ice_isA",
    "ice_ping",
    "sendCachedCaseStatuses",
    "sendCaseStatus"
};

}

/// \cond INTERNAL
bool
Office::Client::_iceDispatch(::IceInternal::Incoming& in, const ::Ice::Current& current)
{
    ::std::pair<const ::std::string*, const ::std::string*> r = ::std::equal_range(iceC_Office_Client_all, iceC_Office_Client_all + 6, current.operation);
    if(r.first == r.second)
    {
        throw ::Ice::OperationNotExistException(__FILE__, __LINE__, current.id, current.facet, current.operation);
    }

    switch(r.first - iceC_Office_Client_all)
    {
        case 0:
        {
            return _iceD_ice_id(in, current);
        }
        case 1:
        {
            return _iceD_ice_ids(in, current);
        }
        case 2:
        {
            return _iceD_ice_isA(in, current);
        }
        case 3:
        {
            return _iceD_ice_ping(in, current);
        }
        case 4:
        {
            return _iceD_sendCachedCaseStatuses(in, current);
        }
        case 5:
        {
            return _iceD_sendCaseStatus(in, current);
        }
        default:
        {
            assert(false);
            throw ::Ice::OperationNotExistException(__FILE__, __LINE__, current.id, current.facet, current.operation);
        }
    }
}
/// \endcond

/// \cond STREAM
void
Office::Client::_iceWriteImpl(::Ice::OutputStream* ostr) const
{
    ostr->startSlice(ice_staticId(), -1, true);
    ::Ice::StreamWriter< Client, ::Ice::OutputStream>::write(ostr, *this);
    ostr->endSlice();
}

void
Office::Client::_iceReadImpl(::Ice::InputStream* istr)
{
    istr->startSlice();
    ::Ice::StreamReader< Client, ::Ice::InputStream>::read(istr, *this);
    istr->endSlice();
}
/// \endcond

/// \cond INTERNAL
void
Office::_icePatchObjectPtr(ClientPtr& handle, const ::Ice::ObjectPtr& v)
{
    handle = ClientPtr::dynamicCast(v);
    if(v && !handle)
    {
        IceInternal::Ex::throwUOE(Client::ice_staticId(), v);
    }
}
/// \endcond

Office::OfficeRegistration::~OfficeRegistration()
{
}

/// \cond INTERNAL
::Ice::Object* Office::upCast(OfficeRegistration* p) { return p; }

/// \endcond

namespace
{
const ::std::string iceC_Office_OfficeRegistration_ids[2] =
{
    "::Ice::Object",
    "::Office::OfficeRegistration"
};

}

bool
Office::OfficeRegistration::ice_isA(const ::std::string& s, const ::Ice::Current&) const
{
    return ::std::binary_search(iceC_Office_OfficeRegistration_ids, iceC_Office_OfficeRegistration_ids + 2, s);
}

::std::vector< ::std::string>
Office::OfficeRegistration::ice_ids(const ::Ice::Current&) const
{
    return ::std::vector< ::std::string>(&iceC_Office_OfficeRegistration_ids[0], &iceC_Office_OfficeRegistration_ids[2]);
}

const ::std::string&
Office::OfficeRegistration::ice_id(const ::Ice::Current&) const
{
    return ice_staticId();
}

const ::std::string&
Office::OfficeRegistration::ice_staticId()
{
#ifdef ICE_HAS_THREAD_SAFE_LOCAL_STATIC
    static const ::std::string typeId = "::Office::OfficeRegistration";
    return typeId;
#else
    return iceC_Office_OfficeRegistration_ids[1];
#endif
}

/// \cond INTERNAL
bool
Office::OfficeRegistration::_iceD_startPassportCase(::IceInternal::Incoming& inS, const ::Ice::Current& current)
{
    _iceCheckMode(::Ice::Normal, current.mode);
    ::Ice::InputStream* istr = inS.startReadParams();
    PassportCaseData iceP_passportCaseData;
    istr->read(iceP_passportCaseData);
    inS.endReadParams();
    CaseStartedInfo ret = this->startPassportCase(iceP_passportCaseData, current);
    ::Ice::OutputStream* ostr = inS.startWriteParams();
    ostr->write(ret);
    inS.endWriteParams();
    return true;
}
/// \endcond

/// \cond INTERNAL
bool
Office::OfficeRegistration::_iceD_startVisaCase(::IceInternal::Incoming& inS, const ::Ice::Current& current)
{
    _iceCheckMode(::Ice::Normal, current.mode);
    ::Ice::InputStream* istr = inS.startReadParams();
    VisaCaseData iceP_visaCaseData;
    istr->read(iceP_visaCaseData);
    inS.endReadParams();
    CaseStartedInfo ret = this->startVisaCase(iceP_visaCaseData, current);
    ::Ice::OutputStream* ostr = inS.startWriteParams();
    ostr->write(ret);
    inS.endWriteParams();
    return true;
}
/// \endcond

/// \cond INTERNAL
bool
Office::OfficeRegistration::_iceD_startDriverLicenseCase(::IceInternal::Incoming& inS, const ::Ice::Current& current)
{
    _iceCheckMode(::Ice::Normal, current.mode);
    ::Ice::InputStream* istr = inS.startReadParams();
    DriverLicenseCaseData iceP_driverLicenseCaseData;
    istr->read(iceP_driverLicenseCaseData);
    inS.endReadParams();
    CaseStartedInfo ret = this->startDriverLicenseCase(iceP_driverLicenseCaseData, current);
    ::Ice::OutputStream* ostr = inS.startWriteParams();
    ostr->write(ret);
    inS.endWriteParams();
    return true;
}
/// \endcond

/// \cond INTERNAL
bool
Office::OfficeRegistration::_iceD_saveClient(::IceInternal::Incoming& inS, const ::Ice::Current& current)
{
    _iceCheckMode(::Ice::Normal, current.mode);
    ::Ice::InputStream* istr = inS.startReadParams();
    ::Ice::Int iceP_clientId;
    ClientPrx iceP_client;
    istr->read(iceP_clientId);
    istr->read(iceP_client);
    inS.endReadParams();
    this->saveClient(iceP_clientId, iceP_client, current);
    inS.writeEmptyParams();
    return true;
}
/// \endcond

namespace
{
const ::std::string iceC_Office_OfficeRegistration_all[] =
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

}

/// \cond INTERNAL
bool
Office::OfficeRegistration::_iceDispatch(::IceInternal::Incoming& in, const ::Ice::Current& current)
{
    ::std::pair<const ::std::string*, const ::std::string*> r = ::std::equal_range(iceC_Office_OfficeRegistration_all, iceC_Office_OfficeRegistration_all + 8, current.operation);
    if(r.first == r.second)
    {
        throw ::Ice::OperationNotExistException(__FILE__, __LINE__, current.id, current.facet, current.operation);
    }

    switch(r.first - iceC_Office_OfficeRegistration_all)
    {
        case 0:
        {
            return _iceD_ice_id(in, current);
        }
        case 1:
        {
            return _iceD_ice_ids(in, current);
        }
        case 2:
        {
            return _iceD_ice_isA(in, current);
        }
        case 3:
        {
            return _iceD_ice_ping(in, current);
        }
        case 4:
        {
            return _iceD_saveClient(in, current);
        }
        case 5:
        {
            return _iceD_startDriverLicenseCase(in, current);
        }
        case 6:
        {
            return _iceD_startPassportCase(in, current);
        }
        case 7:
        {
            return _iceD_startVisaCase(in, current);
        }
        default:
        {
            assert(false);
            throw ::Ice::OperationNotExistException(__FILE__, __LINE__, current.id, current.facet, current.operation);
        }
    }
}
/// \endcond

/// \cond STREAM
void
Office::OfficeRegistration::_iceWriteImpl(::Ice::OutputStream* ostr) const
{
    ostr->startSlice(ice_staticId(), -1, true);
    ::Ice::StreamWriter< OfficeRegistration, ::Ice::OutputStream>::write(ostr, *this);
    ostr->endSlice();
}

void
Office::OfficeRegistration::_iceReadImpl(::Ice::InputStream* istr)
{
    istr->startSlice();
    ::Ice::StreamReader< OfficeRegistration, ::Ice::InputStream>::read(istr, *this);
    istr->endSlice();
}
/// \endcond

/// \cond INTERNAL
void
Office::_icePatchObjectPtr(OfficeRegistrationPtr& handle, const ::Ice::ObjectPtr& v)
{
    handle = OfficeRegistrationPtr::dynamicCast(v);
    if(v && !handle)
    {
        IceInternal::Ex::throwUOE(OfficeRegistration::ice_staticId(), v);
    }
}
/// \endcond

namespace Ice
{
}

#endif
