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

public class VisaCaseData implements java.lang.Cloneable,
                                     java.io.Serializable
{
    public CaseInfo caseInfo;

    public String passportId;

    public Country country;

    public VisaCaseData()
    {
        this.caseInfo = new CaseInfo();
        this.passportId = "";
        this.country = Country.USA;
    }

    public VisaCaseData(CaseInfo caseInfo, String passportId, Country country)
    {
        this.caseInfo = caseInfo;
        this.passportId = passportId;
        this.country = country;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        VisaCaseData r = null;
        if(rhs instanceof VisaCaseData)
        {
            r = (VisaCaseData)rhs;
        }

        if(r != null)
        {
            if(this.caseInfo != r.caseInfo)
            {
                if(this.caseInfo == null || r.caseInfo == null || !this.caseInfo.equals(r.caseInfo))
                {
                    return false;
                }
            }
            if(this.passportId != r.passportId)
            {
                if(this.passportId == null || r.passportId == null || !this.passportId.equals(r.passportId))
                {
                    return false;
                }
            }
            if(this.country != r.country)
            {
                if(this.country == null || r.country == null || !this.country.equals(r.country))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::Office::VisaCaseData");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, caseInfo);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, passportId);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, country);
        return h_;
    }

    public VisaCaseData clone()
    {
        VisaCaseData c = null;
        try
        {
            c = (VisaCaseData)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        CaseInfo.ice_write(ostr, this.caseInfo);
        ostr.writeString(this.passportId);
        Country.ice_write(ostr, this.country);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.caseInfo = CaseInfo.ice_read(istr);
        this.passportId = istr.readString();
        this.country = Country.ice_read(istr);
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, VisaCaseData v)
    {
        if(v == null)
        {
            _nullMarshalValue.ice_writeMembers(ostr);
        }
        else
        {
            v.ice_writeMembers(ostr);
        }
    }

    static public VisaCaseData ice_read(com.zeroc.Ice.InputStream istr)
    {
        VisaCaseData v = new VisaCaseData();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<VisaCaseData> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, VisaCaseData v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<VisaCaseData> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(VisaCaseData.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final VisaCaseData _nullMarshalValue = new VisaCaseData();

    /** @hidden */
    public static final long serialVersionUID = 3003792035176768818L;
}