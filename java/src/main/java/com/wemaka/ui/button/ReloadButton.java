package com.wemaka.ui.button;

import com.wemaka.Application;

import java.awt.event.ActionEvent;

public class ReloadButton implements Button {
	@Override
	public String getLabel() {
		return "Reload";
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Application.reloadHandler();
	}
}
