package com.kaisar.xposed.xsmsimple;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.kaisar.xposed.service.ISimpleService;
import com.kaisar.xposed.service.ISimpleService2;
import com.kaisar.xservicemanager.XServiceManager;


public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.text);
        try
        {
            IBinder binder = XServiceManager.getService("simple");
            if(binder == null)
            {
                textView.append("simple service not injected");
            }
            else
            {
                ISimpleService service = ISimpleService.Stub.asInterface(binder);
                String content = service.readPackageXml();
                boolean success = service.saveConfig("/data/simple_config.xml", content);
                textView.append(String.format("write config %s\n", (success) ? "ok" : "fail"));
                textView.append(content);
            }

            ISimpleService2 simple2 = XServiceManager.getServiceInterface("simple2");
            if(simple2 == null)
            {
                textView.append("simple2 service not injected");
            }
            else
            {
                String packageName = simple2.getPackageName();
                textView.append(packageName);
            }
        }
        catch(RemoteException e)
        {
            textView.setText(Log.getStackTraceString(e));
        }
    }

}