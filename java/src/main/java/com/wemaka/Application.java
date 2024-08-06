package com.wemaka;

import com.melloware.jintellitype.JIntellitype;
import com.wemaka.hotkey.WindowMinimizeCommand;
import com.wemaka.hotkey.WindowRestoreCommand;
import com.wemaka.ui.button.ExitButton;
import com.wemaka.hotkey.HotkeyHandler;
import com.wemaka.ui.SystemTrayManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {
	private static final Logger log = LogManager.getLogger(Application.class.getName());
	private static Application application;
	public static final HotkeyHandler HOTKEY_HANDLER = new HotkeyHandler();
	private static final SystemTrayManager systemTrayManager = new SystemTrayManager(
			"ToggleWindow",
			"java\\src\\main\\resources\\image\\icon.png"
	);

	public static void main(String[] args) {
		systemTrayManager.create();
		systemTrayManager.addButton(new ExitButton());

		if (!JIntellitype.isJIntellitypeSupported()) {
			log.fatal("There was a problem registering the hot key.");
			System.exit(1);
		}

		application = new Application();
		application.initJIntellitype();

//		HotkeyHandler.registerHotkeys(
//				new WindowMinimizeCommand("CTRL+SPACE")
//		);

		HotkeyHandler.registerHotkeys(
				new WindowMinimizeCommand(JIntellitype.MOD_CONTROL, ' '),
				new WindowRestoreCommand(JIntellitype.MOD_CONTROL + JIntellitype.MOD_SHIFT, ' ')
		);
	}

	public void initJIntellitype() {
		try {
			// initialize JIntellitype with the frame so all windows commands can
			// be attached to this window
			JIntellitype.getInstance().addHotKeyListener(HOTKEY_HANDLER);
//			JIntellitype.getInstance().addIntellitypeListener(this);
			log.info("JIntellitype initialized");
		} catch (RuntimeException ex) {
			log.fatal("Either you are not on Windows, or there is a problem with the JIntellitype library!", ex);
		}
	}
}