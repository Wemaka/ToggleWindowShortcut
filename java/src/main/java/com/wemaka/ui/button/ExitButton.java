package com.wemaka.ui.button;

import com.melloware.jintellitype.JIntellitype;

import java.awt.event.ActionEvent;

public class ExitButton implements Button {

	@Override
	public String getLabel() {
		return "Exit";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JIntellitype.getInstance().cleanUp();
		System.runFinalization();
		System.exit(0);
	}
}
