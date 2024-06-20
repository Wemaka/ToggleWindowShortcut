package com.wemaka;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.util.*;

public class HotkeyListener implements NativeKeyListener {
	private static Map<Set<Integer>, HotkeyCommand> keyShortcut =
			new HashMap<>();
	private static Set<Integer> keys = new HashSet<>();
	private static final int VC_RSHIFT = 0xe36;

	public void nativeKeyPressed(NativeKeyEvent e) {
		keys.add(e.getKeyCode() == VC_RSHIFT ? NativeKeyEvent.VC_SHIFT : e.getKeyCode());

//		if (keys.isEmpty() || e.getKeyCode() != keys.get(keys.size() - 1)) {
//			if (e.getKeyCode() == VC_RSHIFT) {
//				keys.add(NativeKeyEvent.VC_SHIFT);
//			} else {
//				keys.add(e.getKeyCode());
//			}
//		}

		if (keyShortcut.containsKey(keys)) {
			keyShortcut.get(keys).execute();
		}

//		System.out.println(keys);
//		System.out.println(keys.stream().map(NativeKeyEvent::getKeyText).toList());
//		System.out.println("-------------------------------------------------");

		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
			try {
				GlobalScreen.unregisterNativeHook();
			} catch (NativeHookException nativeHookException) {
				nativeHookException.printStackTrace();
			}
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
//		for (int i = 0; i < keys.size(); i++) {
//			if ((
//					e.getKeyCode() == VC_RSHIFT ? NativeKeyEvent.VC_SHIFT : e.getKeyCode()
//			) == keys.get(i)) {
//				keys.remove(i);
//				break;
//			}
//		}

		keys.remove(e.getKeyCode() == VC_RSHIFT ? NativeKeyEvent.VC_SHIFT : e.getKeyCode());
	}

	public static void registerHotKey(Set<Integer> keys,
	                                  HotkeyCommand command) {
		keyShortcut.put(keys, command);
	}
}
