package com.wemaka.hotkey;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class HotkeyCommand {
	private LinkedHashSet<Integer> hotkeys = new LinkedHashSet<>();

	public HotkeyCommand(int... keyCodes) {
		setHotkeys(keyCodes);
	}

	public Set<Integer> getHotkeys() {
		return Collections.unmodifiableSet(hotkeys);
	}

	public void setHotkeys(int... keyCodes) {
		if (keyCodes.length == 0) {
			throw new IllegalArgumentException("keyCodes cannot be empty");
		}

		this.hotkeys.addAll(Arrays.stream(keyCodes).boxed().toList());
	}

	abstract void execute();
}
