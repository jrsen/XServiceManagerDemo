package com.kaisar.xposed.service;

import android.content.Context;
import android.os.Binder;

import com.kaisar.xservicemanager.XServiceManager;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


public class MainXposedLoader implements IXposedHookLoadPackage
{
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam)
    {
        if("android".equals(lpparam.packageName))
        {
            XServiceManager.initForSystemServer();
            XServiceManager.addService("simple", new SimpleService());
            XServiceManager.registerService("simple2", new XServiceManager.ServiceFetcher<Binder>()
            {
                @Override
                public Binder createService(Context ctx)
                {
                    return new SimpleService2(ctx);
                }
            });
        }
    }
}
