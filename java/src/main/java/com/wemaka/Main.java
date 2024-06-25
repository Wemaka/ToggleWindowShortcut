package com.wemaka;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

import java.util.List;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		try {
			GlobalScreen.registerNativeHook();

			HotkeyListener.registerHotKey(
					List.of(NativeKeyEvent.VC_CONTROL, NativeKeyEvent.VC_SPACE),
					WindowManager::winMinimize
			);

			HotkeyListener.registerHotKey(
					List.of(NativeKeyEvent.VC_CONTROL, NativeKeyEvent.VC_SHIFT, NativeKeyEvent.VC_SPACE),
					WindowManager::winRestore
			);

		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(new HotkeyListener());
	}
}