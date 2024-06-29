package com.wemaka;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.wemaka.hotkey.WindowMinimizeCommand;
import com.wemaka.hotkey.WindowRestoreCommand;
import com.wemaka.ui.button.ExitButton;
import com.wemaka.ui.button.ReloadButton;
import com.wemaka.window.WindowManager;
import com.wemaka.hotkey.HotkeyHandler;
import com.wemaka.ui.SystemTrayManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class Application {
	private static final Logger log = LogManager.getLogger(Application.class.getName());
	public static final HotkeyHandler HOTKEY_HANDLER = new HotkeyHandler();
	private static final SystemTrayManager systemTrayManager = new SystemTrayManager(
			"ToggleWindow",
			"java\\src\\main\\resources\\image\\icon.png"
	);

	public static void main(String[] args) {
		systemTrayManager.create();
		systemTrayManager.addButton(new ExitButton());
		systemTrayManager.addButton(new ReloadButton());


		if (registerHook()) {
			HotkeyHandler.registerHotkeys(
					new WindowMinimizeCommand(NativeKeyEvent.VC_CONTROL,
							NativeKeyEvent.VC_SPACE),
					new WindowRestoreCommand(NativeKeyEvent.VC_CONTROL,
							NativeKeyEvent.VC_SHIFT, NativeKeyEvent.VC_SPACE)
			);

			addListener(HOTKEY_HANDLER);
		} else {
			System.exit(1);
		}

//		try {
//			GlobalScreen.registerNativeHook();
//
//			HotkeyListener.registerHotKey(
//					List.of(NativeKeyEvent.VC_CONTROL, NativeKeyEvent.VC_SPACE),
//					WindowManager::winMinimize
//			);
//
//			HotkeyListener.registerHotKey(
//					List.of(NativeKeyEvent.VC_CONTROL, NativeKeyEvent.VC_SHIFT, NativeKeyEvent.VC_SPACE),
//					WindowManager::winRestore
//			);
//
//		} catch (NativeHookException ex) {
//			log.error("There was a problem registering the native hook.");
//			log.error(ex);
//
//			System.exit(1);
//		}
	}

	public static boolean registerHook() {
		try {
			GlobalScreen.registerNativeHook();
			return true;
		} catch (NativeHookException e) {
			log.fatal("There was a problem registering the native hook.");
			log.fatal(e);

			return false;
		}
	}

	public static boolean unRegisterHook() {
		try {
			GlobalScreen.unregisterNativeHook();
			return true;
		} catch (NativeHookException e) {
			log.error("There was a problem unregistering the native hook.");
			log.error(e);

			return false;
		}
	}

	public static void reloadHandler() {
		HotkeyHandler.clearKeys();

		removeListener(HOTKEY_HANDLER);
		addListener(HOTKEY_HANDLER);
	}

	public static void addListener(HotkeyHandler hotkeyHandler) {
		GlobalScreen.addNativeKeyListener(hotkeyHandler);
	}

	public static void removeListener(HotkeyHandler hotkeyHandler) {
		GlobalScreen.removeNativeKeyListener(hotkeyHandler);
	}
}