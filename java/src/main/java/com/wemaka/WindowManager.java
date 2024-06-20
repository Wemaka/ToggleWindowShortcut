package com.wemaka;

import com.sun.jna.platform.win32.WinDef;

import java.util.Scanner;

public class WindowManager {
	private static final int SW_MINIMIZE = 6;
	private static final int SW_RESTORE = 9;
	private static WinDef.HWND lastWindow = null;

	public static void winMinimize() {
		WinDef.HWND activeWin = User32.INSTANCE.GetForegroundWindow();

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
