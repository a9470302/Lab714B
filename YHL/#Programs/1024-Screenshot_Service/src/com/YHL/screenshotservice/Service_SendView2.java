package com.YHL.screenshotservice;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Service_SendView2 extends Service {
	String IP;
	Boolean B = true;
	@Override
	public IBinder onBind(Intent intent) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(getApplicationContext(),"Service Start",Toast.LENGTH_SHORT).show();
		Bundle bundle = intent.getExtras();
		IP = bundle.getString("IP");
		new Thread(new SOCKET()).start();
		return START_STICKY;
	}
	@Override
	public void onDestroy() {
		B = false;
		Toast.makeText(getApplicationContext(),"Service Stop",Toast.LENGTH_SHORT).show();
		super.onDestroy();
	}
	private class SOCKET implements Runnable{
		@Override
		public void run() {
			Socket SC = null;
			DataOutputStream DOS = null;
			Process process;
			try {
				/*process = Runtime.getRuntime().exec("su");
				DataOutputStream D = new DataOutputStream(process.getOutputStream());
				D.writeBytes("chmod 777 /dev/graphics/fb0\n");
				D.writeBytes("exit\n");
				D.flush();*/
				SC = new Socket(IP,12345);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			while(B){
				try {
					View v = new View(getBaseContext()).getRootView();
					Bitmap b = v.getDrawingCache();
					ByteArrayOutputStream baos = null;
					if (b != null) {
	                    try {
	                        baos = new ByteArrayOutputStream();
	                        Bitmap rbitmap = Bitmap.createScaledBitmap(b, b.getWidth()/4, b.getHeight()/4, true);
	                        rbitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                }
					
					byte[] bytearray = baos.toByteArray();
					
					DOS.writeUTF("SEND");
					
					DataOutputStream DOS2 = new DataOutputStream(new Socket(IP,12346).getOutputStream());
					ByteArrayInputStream bb = new ByteArrayInputStream(bytearray);
					byte[] bu = new byte[2048];
					int num = bb.read(bu);
					while (num != -1) {
						DOS2.write(bu, 0, num);
						DOS2.flush();
						num = bb.read(bu);
					}
					bb.close();
					DOS2.flush();
					DOS2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				DOS.writeUTF("END");
				DOS.close();
				SC.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}

