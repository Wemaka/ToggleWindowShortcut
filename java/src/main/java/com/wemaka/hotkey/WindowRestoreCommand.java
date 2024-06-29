package com.wemaka.hotkey;

import com.wemaka.window.WindowManager;


public class WindowRestoreCommand extends HotkeyCommand {
	public WindowRestoreCommand(int... keyCodes) {
		super(keyCodes);
	}

	@Override
	void execute() {
		WindowManager.winRestore();
	}
}
