package com.wemaka.window;

import jnr.ffi.LibraryLoader;
import jnr.ffi.Pointer;
import jnr.ffi.types.u_int32_t;

public interface User32 {
	User32 INSTANCE = LibraryLoader.create(User32.class).load("user32");

	Pointer GetForegroundWindow();

	boolean ShowWindow(Pointer hWnd, @u_int32_t long nCmdShow);

	boolean IsIconic(Pointer hWnd);
}
