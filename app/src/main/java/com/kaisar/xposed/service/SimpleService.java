package com.kaisar.xposed.service;

import android.util.Log;

import com.kaisar.xposed.service.ISimpleService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

class SimpleService extends ISimpleService.Stub
{
    @Override
    public String readPackageXml()
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            try(BufferedReader r = new BufferedReader(new FileReader("/data/system/packages.list")))
            {
                for(String line; (line = r.readLine()) != null; )
                {
                    sb.append(line).append('\n');
                }
            }
        }
        catch(Throwable t)
        {
            Log.e("Simple", "readPackageXml error", t);
        }
        return sb.toString();
    }

    @Override
    public boolean saveConfig(String path, String config)
    {
        try
        {
            Log.d("Simple", "write config to " + path);
            try(FileWriter w = new FileWriter(path))
            {
                w.write(config);
                w.flush();
            }
            return true;
        }
        catch(Throwable t)
        {
            Log.e("Simple", "saveConfig error", t);
        }
        return false;
    }
}
