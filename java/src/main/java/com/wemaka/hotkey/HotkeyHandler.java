package com.wemaka.hotkey;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class HotkeyHandler implements NativeKeyListener {
	private static final Logger log = LogManager.getLogger(HotkeyHandler.class);
	private static final int VC_RSHIFT = 0xe36;
	private static Map<Set<Integer>, HotkeyCommand> keyShortcut =
			new HashMap<>();
	private static Set<Integer> keys = new LinkedHashSet<>();

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		keys.add(e.getKeyCode() == VC_RSHIFT ? NativeKeyEvent.VC_SHIFT : e.getKeyCode());

		if (keyShortcut.containsKey(keys)) {
			keyShortcut.get(keys).execute();
		}

//		log.info(keys.stream().map(NativeKeyEvent::getKeyText).toList());
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		keys.remove(e.getKeyCode() == VC_RSHIFT ? NativeKeyEvent.VC_SHIFT : e.getKeyCode());
	}

	public static void registerHotkeys(HotkeyCommand... commands) {
		for (HotkeyCommand command : commands) {
			keyShortcut.put(command.getHotkeys(), command);
		}
	}

	public static void clearKeys() {
		keys.clear();
	}
}
