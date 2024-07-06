package com.wemaka.window;

import jnr.ffi.LibraryLoader;
import jnr.ffi.types.u_int32_t;

public interface User32 {
	User32 INSTANCE = LibraryLoader.create(User32.class).load("user32");

	@u_int32_t
	long GetForegroundWindow();

	boolean ShowWindowAsync(@u_int32_t long hwnd, @u_int32_t long nCmdShow);

	boolean IsIconic(@u_int32_t long hwnd);
}
