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

public class CaseInfo implements java.lang.Cloneable,
                                 java.io.Serializable
{
    public int clientId;

    public boolean paymentDone;

    public CaseInfo()
    {
    }

    public CaseInfo(int clientId, boolean paymentDone)
    {
        this.clientId = clientId;
        this.paymentDone = paymentDone;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        CaseInfo r = null;
        if(rhs instanceof CaseInfo)
        {
            r = (CaseInfo)rhs;
        }

        if(r != null)
        {
            if(this.clientId != r.clientId)
            {
                return false;
            }
            if(this.paymentDone != r.paymentDone)
            {
                return false;
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::Office::CaseInfo");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, clientId);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, paymentDone);
        return h_;
    }

    public CaseInfo clone()
    {
        CaseInfo c = null;
        try
        {
            c = (CaseInfo)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeInt(this.clientId);
        ostr.writeBool(this.paymentDone);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.clientId = istr.readInt();
        this.paymentDone = istr.readBool();
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, CaseInfo v)
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

    static public CaseInfo ice_read(com.zeroc.Ice.InputStream istr)
    {
        CaseInfo v = new CaseInfo();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<CaseInfo> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, CaseInfo v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.VSize))
        {
            ostr.writeSize(5);
            ice_write(ostr, v);
        }
    }

    static public java.util.Optional<CaseInfo> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.VSize))
        {
            istr.skipSize();
            return java.util.Optional.of(CaseInfo.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final CaseInfo _nullMarshalValue = new CaseInfo();

    /** @hidden */
    public static final long serialVersionUID = -7945722467445120481L;
}
