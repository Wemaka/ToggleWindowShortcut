package com.wemaka.ui.button;

import com.wemaka.Application;

import java.awt.event.ActionEvent;

public class ExitButton implements Button {

	@Override
	public String getLabel() {
		return "Exit";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Application.removeListener(Application.HOTKEY_HANDLER);
		Application.unRegisterHook();

		System.runFinalization();
		System.exit(0);
	}
}
