package com.example.shao.smarthome;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ClientHelper {
    public static final String TAG="CLIENTHELPER";
    void log(String msg)
    {
        Log.d(TAG,msg);
    }
    DataInputStream in;
    DataOutputStream out;
    Socket socket=null;

    public ClientHelper()
    {
        try{
             socket =  new Socket("192.168.0.1", 3000);
             in=new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            }
            catch(IOException e)
            {
                e.printStackTrace();
        }
    }
    public void  send(String msg)
    {
        try{
            out.writeUTF(msg);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public String read(){
        String accept=null;
        try
        {
            accept=in.readUTF();
            log(accept);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return accept;
    }
}
