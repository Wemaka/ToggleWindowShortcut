package com.wemaka;

import jnr.ffi.types.u_int32_t;

public class WindowManager {
	private static final int SW_MINIMIZE = 6;
	private static final int SW_RESTORE = 9;
	private static long lastWindow = 0;


	public static void winMinimize() {
		long activeWin = User32.INSTANCE.GetForegroundWindow();

		if (activeWin != 0) {
			lastWindow = activeWin;
			User32.INSTANCE.ShowWindow(activeWin, SW_MINIMIZE);
		}
	}

	public static void winRestore() {
		if (lastWindow != 0 && User32.INSTANCE.IsIconic(lastWindow)) {
			User32.INSTANCE.ShowWindow(lastWindow, SW_RESTORE);
			lastWindow = 0;
		}
	}
}
