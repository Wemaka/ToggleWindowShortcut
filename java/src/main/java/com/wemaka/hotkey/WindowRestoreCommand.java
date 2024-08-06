package com.wemaka.hotkey;

import com.wemaka.window.WindowManager;


public class WindowRestoreCommand extends HotkeyCommand {
	public WindowRestoreCommand(int modifier, int keycode) {
		super(modifier, keycode);
	}

	@Override
	void execute() {
		WindowManager.winRestore();
	}
}
