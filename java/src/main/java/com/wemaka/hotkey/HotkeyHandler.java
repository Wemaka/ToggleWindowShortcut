package com.wemaka.hotkey;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class HotkeyHandler implements HotkeyListener {
	private static final Logger log = LogManager.getLogger(HotkeyHandler.class);
	private static Map<Integer, HotkeyCommand> keyShortcut = new HashMap<>();

	public static void registerHotkeys(HotkeyCommand... commands) {
		for (HotkeyCommand command : commands) {
			JIntellitype.getInstance().registerHotKey(command.getIdentifier(),
					command.getModifier(), command.getKeycode());

			keyShortcut.put(command.getIdentifier(), command);
		}
	}

	@Override
	public void onHotKey(int i) {
		keyShortcut.get(i).execute();
	}
}
