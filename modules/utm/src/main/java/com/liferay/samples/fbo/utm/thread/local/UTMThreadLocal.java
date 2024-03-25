package com.liferay.samples.fbo.utm.thread.local;

import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.samples.fbo.utm.model.UTM;

public class UTMThreadLocal {

	public static UTM getUTM() {
		return _utm.get();
	}
	
	public static void setUTM(UTM utm) {
		_utm.set(utm);
	}
	
	private static final ThreadLocal<UTM> _utm =
			new CentralizedThreadLocal<>(
					UTMThreadLocal.class + "._utm");
}
