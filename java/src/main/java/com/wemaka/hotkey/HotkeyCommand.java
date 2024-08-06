package com.wemaka.hotkey;

public abstract class HotkeyCommand {
	private static int id = 0;
	private int identifier;
	private int modifier;
	private int keycode;

	public HotkeyCommand(int modifier, int keycode) {
		this.modifier = modifier;
		this.keycode = keycode;
		this.identifier = id++;
	}

	public int getIdentifier() {
		return identifier;
	}

	public int getModifier() {
		return modifier;
	}

	public int getKeycode() {
		return keycode;
	}

	abstract void execute();
}
