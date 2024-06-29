package com.wemaka.hotkey;

import com.wemaka.window.WindowManager;

public class WindowMinimizeCommand extends HotkeyCommand {

	public WindowMinimizeCommand(int... keyCodes) {
		super(keyCodes);
	}

	@Override
	public void execute() {
		WindowManager.winMinimize();
	}
}
