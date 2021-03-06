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

public enum CaseStatusEnum implements java.io.Serializable
{
    REJECTED(0),
    APPROVED(1);

    public int value()
    {
        return _value;
    }

    public static CaseStatusEnum valueOf(int v)
    {
        switch(v)
        {
        case 0:
            return REJECTED;
        case 1:
            return APPROVED;
        }
        return null;
    }

    private CaseStatusEnum(int v)
    {
        _value = v;
    }

    public void ice_write(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeEnum(_value, 1);
    }

    public static void ice_write(com.zeroc.Ice.OutputStream ostr, CaseStatusEnum v)
    {
        if(v == null)
        {
            ostr.writeEnum(Office.CaseStatusEnum.REJECTED.value(), 1);
        }
        else
        {
            ostr.writeEnum(v.value(), 1);
        }
    }

    public static CaseStatusEnum ice_read(com.zeroc.Ice.InputStream istr)
    {
        int v = istr.readEnum(1);
        return validate(v);
    }

    public static void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<CaseStatusEnum> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    public static void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, CaseStatusEnum v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.Size))
        {
            ice_write(ostr, v);
        }
    }

    public static java.util.Optional<CaseStatusEnum> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.Size))
        {
            return java.util.Optional.of(ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static CaseStatusEnum validate(int v)
    {
        final CaseStatusEnum e = valueOf(v);
        if(e == null)
        {
            throw new com.zeroc.Ice.MarshalException("enumerator value " + v + " is out of range");
        }
        return e;
    }

    private final int _value;
}
