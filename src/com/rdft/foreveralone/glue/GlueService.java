package com.rdft.foreveralone.glue;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class GlueService extends Service {
	private final IBinder mBinder = new LocalBinder();

	public class LocalBinder extends Binder {
		public GlueService getService() {
			return GlueService.this;
		}
	}

	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	public int getRandomNumber() {
		return 4;
	}
}
