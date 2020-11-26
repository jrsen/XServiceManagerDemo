package com.kaisar.xposed.service;

import android.content.Context;
import android.os.RemoteException;

import com.kaisar.xposed.service.ISimpleService2;

public class SimpleService2 extends ISimpleService2.Stub
{

    private final Context context;

    public SimpleService2(Context context)
    {
        this.context = context;
    }

    @Override
    public String getPackageName() throws RemoteException
    {
        try
        {
            return context.getPackageName();
        }
        catch(Exception e)
        {
            throw new RemoteException(e.getMessage());
        }
    }
}
