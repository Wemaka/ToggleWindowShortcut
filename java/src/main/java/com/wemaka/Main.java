package com.wemaka;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

public class Main {
	public static final String TAG = "ToggleWindow";

	public static void main(String[] args) {
		new SystemTrayManager(
				TAG,
				"java\\src\\main\\resources\\image\\icon.png"
		).createSystemTray();

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