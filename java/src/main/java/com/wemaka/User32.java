package com.wemaka;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.win32.StdCallLibrary;

public interface User32 extends StdCallLibrary {
	User32 INSTANCE = Native.load("user32", User32.class);

	WinDef.HWND GetForegroundWindow();

	boolean ShowWindow(WinDef.HWND hwnd, int nCmdShow);

	boolean IsIconic(WinDef.HWND hwnd);

	boolean RegisterHotKey(WinDef.HWND hwnd, int id, WinDef.UINT fsMofifiers,
	                       WinDef.UINT vk);

	boolean UnregisterHotKey(WinDef.HWND hwnd, int id);

	boolean PeekMessage(WinUser.MSG lpMsg, WinDef.HWND hwnd,
	                    WinDef.UINT wMsgFilterMin, WinDef.UINT wMsgFilterMax,
	                    WinDef.UINT wRemoveMsg);
}
