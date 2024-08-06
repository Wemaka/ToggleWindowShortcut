package com.wemaka.hotkey;

import com.wemaka.window.WindowManager;

public class WindowMinimizeCommand extends HotkeyCommand {

	public WindowMinimizeCommand(int modifier, int keycode) {
		super(modifier, keycode);
	}

	@Override
	public void execute() {
		WindowManager.winMinimize();
	}
}
