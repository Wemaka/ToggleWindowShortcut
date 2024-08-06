package com.wemaka.window;

import jnr.ffi.Pointer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WindowManager {
	private static final Logger log = LogManager.getLogger(WindowManager.class.getName());
	private static final int SW_MINIMIZE = 6;
	private static final int SW_RESTORE = 9;
	private static Pointer lastWindow = null;

	public static void winMinimize() {
		Pointer activeWin = User32.INSTANCE.GetForegroundWindow();
		if (activeWin != null) {
			lastWindow = activeWin;
			User32.INSTANCE.ShowWindow(activeWin, SW_MINIMIZE);
		}
	}

	public static void winRestore() {
		if (lastWindow != null && User32.INSTANCE.IsIconic(lastWindow)) {
			User32.INSTANCE.ShowWindow(lastWindow, SW_RESTORE);
			lastWindow = null;
		}
	}
}
