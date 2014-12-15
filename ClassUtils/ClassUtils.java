package FileStringTestProject.ClassUtils;

import java.io.File;

import android.graphics.Rect;
import android.os.RemoteException;
import android.util.Log;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;

/**
 * ClassUtils
 * 
 * NOTE:1.This class can contain only one test case. 2.Do not change the class
 * name.
 */
public class ClassUtils {

	private final static String APPTRAY_MOTOROLA = "Apps";
	private final static String APPTRAY_SAMSUNG_TAB = "Applications";
	private final static String APPTAB_MOTOROLA = "Apps";
	private final static String APPTAB_SAMSUNG_TAB = "Apps";
	public final static int ACTION_CLICK = 0;
	public final static int ACTION_TAKE_SCREENSHOT = 1;
	public final static String LISTVIEW_CLASSNAME = "android.widget.ListView";
	public final static String SCROLLVIEW_CLASSNAME = "android.widget.ScrollView";
	public final static String FILE_MANAGER = "File Manager";
	public final static String DATA_FOLDER = "data";

	public static void selectItem(String fileName)
			throws UiObjectNotFoundException {
		// select FileManager
		invokeListViewItem(LISTVIEW_CLASSNAME, FILE_MANAGER, ACTION_CLICK);
		// select folder "data"
		invokeListViewItem(LISTVIEW_CLASSNAME, DATA_FOLDER, ACTION_CLICK);
		// select file "fileName"
		invokeListViewItem(LISTVIEW_CLASSNAME, fileName, ACTION_CLICK);
	}

	// Input Email Recipient
	public static void inputEmailShare(String emailShareFile)
			throws UiObjectNotFoundException {
		UiObject emailField = new UiObject(
				new UiSelector()
						.resourceId("com.filestring.lattedouble:id/auto_text_add_recipient"));
		emailField.clickAndWaitForNewWindow();
		emailField.setText(emailShareFile);
	}

	// Send String File
	public static void sendStringFile() throws UiObjectNotFoundException {

		UiObject send = new UiObject(
				new UiSelector()
						.resourceId("com.filestring.lattedouble:id/menu_recipients_send"));
		if (send.exists()) {
			send.clickAndWaitForNewWindow(5000);
		}
	}

	// Sign In
	public static void signIn(final String email, final String password)
			throws UiObjectNotFoundException {
		UiObject emailSignIn = new UiObject(
				new UiSelector()
						.resourceId("com.filestring.lattedouble:id/edtSignInEmail"));

		UiObject passwordSignIn = new UiObject(
				new UiSelector()
						.resourceId("com.filestring.lattedouble:id/edtSignInPassword"));

		UiObject signIn = new UiObject(
				new UiSelector()
						.resourceId("com.filestring.lattedouble:id/btnSignIn"));
		if (emailSignIn.exists()) {
			emailSignIn.click();
			emailSignIn.setText(email);
		}
		if (passwordSignIn.exists()) {
			passwordSignIn.click();
			passwordSignIn.setText(password);
		}
		if (signIn.exists()) {
			signIn.clickAndWaitForNewWindow(10000);
		}
	}

	// Sign Out
	public static void signOut() throws UiObjectNotFoundException {
		UiObject hamberger = new UiObject(new UiSelector().resourceId(
				"android:id/up").className("android.widget.ImageView"));
		hamberger.click();

		UiObject info = new UiObject(new UiSelector().resourceId(
				"com.filestring.lattedouble:id/drawer_email").className(
				"android.widget.TextView"));
		info.clickAndWaitForNewWindow();

		UiObject signout = new UiObject(
				new UiSelector().text("Sign Out from FileString"));
		signout.clickAndWaitForNewWindow();
	}

	// Launch FileString App
	public static void launchFileStringApp(String appName)
			throws RemoteException, UiObjectNotFoundException {
		// Wake up Device
		UiDevice.getInstance().wakeUp();
		// Press button Home
		UiDevice.getInstance().pressHome();
		// Click appTray icon
		UiObject appTray = new UiObject(
				new UiSelector().description(APPTRAY_MOTOROLA));
		appTray.clickAndWaitForNewWindow();
		// Click appTab
		UiObject appsTab = new UiObject(new UiSelector().text(APPTAB_MOTOROLA));
		appsTab.clickAndWaitForNewWindow();

		UiScrollable appViews = new UiScrollable(
				new UiSelector().scrollable(true));
		appViews.setAsHorizontalList();
		appViews.scrollToBeginning(10);

		int maxSearchSwipes = appViews.getMaxSearchSwipes();
		UiSelector selector;
		selector = new UiSelector().className(android.widget.TextView.class
				.getName());
		UiObject appToLaunch;

		for (int i = 0; i < maxSearchSwipes; i++) {
			// if do not try/catch: search app not work well
			try {
				appToLaunch = appViews.getChildByText(selector, appName);
				if (appToLaunch != null) {
					// Create a UiSelector to find the Settings app and simulate
					// a user click to launch the app.
					appToLaunch.clickAndWaitForNewWindow();
					break;
				}
			} catch (UiObjectNotFoundException e) {
				System.out.println("Did not find match for "
						+ e.getLocalizedMessage());
			}

			for (int j = 0; j < i; j++) {
				appViews.scrollForward();
				System.out.println("scrolling forward 1 page of apps.");
			}
		}
	}

	public static void openFileDetails(String name) throws UiObjectNotFoundException {

		UiScrollable listView = new UiScrollable(
				new UiSelector().className("android.widget.ListView"));

		UiSelector listViewItemSelector;

		listViewItemSelector = new UiSelector()
				.className(android.widget.TextView.class.getName());
		UiObject listViewItem;

		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(5000);
				listViewItem = listView.getChildByText(listViewItemSelector,
						name);
				if (listViewItem != null) {
					Rect rect = listViewItem.getBounds();
					Rect rect1 = new Rect();
					rect1.set(rect.left + 479, rect.top - 20, rect.right + 130,
							rect.bottom + 49);
					UiDevice.getInstance().click(rect1.centerX(),
							rect1.centerY());
					System.out.println("\"" + name + "\" Detail was clicked.");
					break;
				}
			} catch (Exception e) {
				System.out.println("Did not find match for "
						+ e.getLocalizedMessage());
			}
		}
	}

	public static void invokeListViewItem(String className, String name,
			int action) throws UiObjectNotFoundException {

		UiScrollable listView = new UiScrollable(
				new UiSelector().className(className));

		// listView.scrollTextIntoView(name);
		// listView.waitForExists(5000);

		UiSelector listViewItemSelector;

		listViewItemSelector = new UiSelector()
				.className(android.widget.TextView.class.getName());
		UiObject listViewItem;

		File path = new File("/sdcard/AutomationScreenShot/" + name);
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(5000);
				listViewItem = listView.getChildByText(listViewItemSelector,
						name);
				// listViewItem = listView.getChild(listViewItemSelector);

				if (listViewItem != null) {
					if (action == ACTION_CLICK) {
						listViewItem.click();
						System.out
								.println("\"" + name + "\" item was clicked.");
					} else if (action == ACTION_TAKE_SCREENSHOT) {
						// TODO TAKE SCREENSHOT
						UiDevice.getInstance().takeScreenshot(path);

					}

					break;
				}
			} catch (Exception e) {
				System.out.println("Did not find match for "
						+ e.getLocalizedMessage());
			}
		}

	}

	// Open Notification Bar
	public static void openNotificationPanel() {
		UiDevice.getInstance().openNotification();
	}

	public static void notificationCheckFileUploaded(String keyCheckState) {

		// why scrollable(=true) this function not work
		UiScrollable scrollableScrollView = new UiScrollable(new UiSelector()
				.className("android.widget.ScrollView")
				.resourceId("com.android.systemui:id/scroll").scrollable(false));

		UiSelector notiSelector;
		notiSelector = new UiSelector().className(
				android.widget.TextView.class.getName()).resourceId(
				"android:id/title");

		UiObject notiFileUploaded;

		for (int i = 0; i < 20; i++) {
			try {
				Thread.sleep(5000);
				notiFileUploaded = scrollableScrollView.getChildByText(
						notiSelector, keyCheckState);

				if (notiFileUploaded != null) {
					// notiFileUploaded.click();
					UiDevice.getInstance().pressBack();
					break;
				}
			} catch (Exception e) {
				System.out.println("Did not find match for "
						+ e.getLocalizedMessage());
				UiDevice.getInstance().pressBack();
			}
		}
	}

	public static void loggerd(final String tag, final String message) {
		System.out.println(tag + ": " + message);
		Log.d(tag, message);
	}

}