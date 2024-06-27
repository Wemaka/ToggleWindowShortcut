package com.wemaka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SystemTrayManager {
	private TrayIcon trayIcon;
	private SystemTray systemTray;
	private PopupMenu popupMenu;
	private String trayTitle;
	private String trayImagePath;

	public SystemTrayManager(String trayTitle, String trayImagePath) {
		setTrayTitle(trayTitle);
		setTrayImagePath(trayImagePath);
	}

	public void setTrayTitle(String trayTitle) {
		if (trayTitle.isBlank()) {
			throw new IllegalArgumentException("Tray title cannot be blank.");
		}

		this.trayTitle = trayTitle;
	}

	public void setTrayImagePath(String trayImagePath) {
		if (!Files.exists(Paths.get(trayImagePath))) {
			throw new IllegalArgumentException("Image path does not exist.");
		}

		this.trayImagePath = trayImagePath;
	}

	public void createSystemTray() {
		if (!SystemTray.isSupported()) {
			System.out.println("The system does not supports system tray.");
			System.exit(0);
		}

		create();
	}

	private void create() {
		systemTray = SystemTray.getSystemTray();
		popupMenu = new PopupMenu();

		addMenuItem(popupMenu, "Exit", e -> {
			System.out.println("Exiting...");
			System.exit(0);
		});

		createSystemTrayIcon();
	}

	private void createSystemTrayIcon() {
		Image image = Toolkit.getDefaultToolkit().getImage(trayImagePath);

		trayIcon = new TrayIcon(image, trayTitle, popupMenu);
		trayIcon.addActionListener(e -> {
			System.out.println("Click");
		});

		addSystemTray();
	}

	private void addMenuItem(PopupMenu popup, String label, ActionListener l) {
		MenuItem item = new MenuItem(label);
		item.addActionListener(l);
		popup.add(item);
	}

	private void addSystemTray() {
		try {
			systemTray.add(trayIcon);
			trayIcon.displayMessage(trayTitle, "Running on system tray.",
					TrayIcon.MessageType.INFO);
		} catch (AWTException e) {
			System.err.println("TrayIcon could not be added.");
		}
	}
}
