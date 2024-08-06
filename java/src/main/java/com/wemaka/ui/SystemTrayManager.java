package com.wemaka.ui;

import com.wemaka.ui.button.Button;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;


public class SystemTrayManager {
	private static final Logger log = LogManager.getLogger(SystemTrayManager.class);
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
		if (trayTitle == null || trayTitle.isBlank()) {
			log.error("Tray title cannot be blank.");
		}

		this.trayTitle = trayTitle;
	}

	public void setTrayImagePath(String trayImagePath) {
		if (trayImagePath == null || !Files.exists(Paths.get(trayImagePath))) {
			log.error("Image path does not exist.");
		}

		this.trayImagePath = trayImagePath;
	}

	public void create() {
		if (!SystemTray.isSupported()) {
			log.info("The system does not supports system tray.");
			System.exit(0);
		}

		createSystemTray();
	}

	private void createSystemTray() {
		systemTray = SystemTray.getSystemTray();
		popupMenu = new PopupMenu();

		createSystemTrayIcon();
	}

	private void createSystemTrayIcon() {
		Image image = Toolkit.getDefaultToolkit().getImage(trayImagePath);
		trayIcon = new TrayIcon(image, trayTitle, popupMenu);

		addSystemTray();
	}

	private void addMenuItem(PopupMenu popup, String label, ActionListener l) {
		MenuItem item = new MenuItem(label);
		item.addActionListener(l);
		popup.add(item);
	}

	public void addButton(Button button) {
		if (this.popupMenu != null) {
			addMenuItem(this.popupMenu, button.getLabel(), button);
			log.info("Added button: {}", button.getLabel());
		} else {
			log.warn("The {} button cannot be added. PopupMenu is missing.",
					button.getLabel());
		}
	}

	private void addSystemTray() {
		try {
			systemTray.add(trayIcon);
			trayIcon.displayMessage(trayTitle, "Running on system tray.",
					TrayIcon.MessageType.INFO);
		} catch (AWTException e) {
			log.error("TrayIcon could not be added.");
		}
	}
}
