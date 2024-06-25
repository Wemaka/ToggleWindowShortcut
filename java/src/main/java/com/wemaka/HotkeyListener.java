package com.wemaka;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.util.*;

public class HotkeyListener implements NativeKeyListener {
	private static final int VC_RSHIFT = 0xe36;
	private static Map<Set<Integer>, HotkeyCommand> keyShortcut =
			new HashMap<>();
	private static Set<Integer> keys = new LinkedHashSet<>();

	public void nativeKeyPressed(NativeKeyEvent e) {
		keys.add(e.getKeyCode() == VC_RSHIFT ? NativeKeyEvent.VC_SHIFT : e.getKeyCode());

		if (keyShortcut.containsKey(keys)) {
			keyShortcut.get(keys).execute();
		}

//		System.out.println(keys.stream().map(NativeKeyEvent::getKeyText).toList());

// 		if (e.getModifiers() == NativeKeyEvent.CTRL_L_MASK && e.getKeyCode() == NativeKeyEvent.VC_SPACE) {
//		}

		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
			try {
				GlobalScreen.unregisterNativeHook();
			} catch (NativeHookException nativeHookException) {
				nativeHookException.printStackTrace();
			}
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		keys.remove(e.getKeyCode() == VC_RSHIFT ? NativeKeyEvent.VC_SHIFT : e.getKeyCode());
	}

	public static void registerHotKey(List<Integer> keys, HotkeyCommand command) {
		keyShortcut.put(new LinkedHashSet<>(keys), command);
	}
}
