package FileStringTestProject.StringFileTestSuite;

import java.util.concurrent.TimeUnit;

import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * This is StringFileTest
 * 
 * NOTE:1.This class can contain only one test case. 2.Do not change the class
 * name.
 */
public class StringFileTest extends UiAutomatorTestCase {

	private static String TAG = "StringFileTest";

	/**
	 * @generated All your test code will go here NOTE: Do not change the method
	 *            signature
	 */
	// ------Global variable-------//
	// All App Tray
	UiObject appTray = new UiObject(new UiSelector().description("Apps"));

	// Get AppTray container
	UiScrollable appView = new UiScrollable(new UiSelector().className(
			"android.view.View").scrollable(true));

	// Apps Tab
	UiObject appsTab = new UiObject(new UiSelector().className(
			"android.widget.TextView").description("Apps"));

	// Verify the launched application by it's package name//will clear
	UiObject appFileString = new UiObject(new UiSelector().packageName(
			"com.filestring.lattedouble").text("FileString"));

	// Menu String file
	UiObject menuStringFile = new UiObject(new UiSelector().resourceId(
			"com.filestring.lattedouble:id/menu_files_list_action_add")
			.description("Add"));

	// String a file
	UiObject stringFile = new UiObject(new UiSelector().resourceId(
			"com.filestring.lattedouble:id/string_file").text("String a File"));

	// File Manager
	UiObject fileManager = new UiObject(new UiSelector().resourceId(
			"com.filestring.lattedouble:id/source_title").text("File Manager"));

	// get Folder container//scroll add more
	UiScrollable appFolder = new UiScrollable(new UiSelector().className(
			"android.widget.ListView").scrollable(true));

	// Send String File
	UiObject sendStringFile = new UiObject(new UiSelector().resourceId(
			"com.filestring.lattedouble:id/menu_recipients_send").description(
			"Send"));

	// UiScrollable String file // Special thing when change to another screen//

	UiScrollable uiScrollableStringFile = new UiScrollable(new UiSelector()
			.resourceId("android:id/action_bar_overlay_layout")
			.className("android.view.View")
			.packageName("com.filestring.lattedouble").scrollable(false));

	// -------------Some variable-------------//
	UiObject file = null;
	UiObject data = null;
	UiObject emailEditText = null;

	public void test() throws UiObjectNotFoundException, RemoteException {
		System.out.println("testcase execution started");
		// takeScreenShot();
		// CODE:START
		UiDevice myDevice = getUiDevice();

		// Wakeup the device if the screen is Off
		if (!myDevice.isScreenOn()) {
			myDevice.wakeUp();
		}
		// Press Home button
		myDevice.pressHome();
		// Set the swiping mode to horizontal
		appView.setAsHorizontalList();

		// Look for the FileString application
		UiObject applicationFileString = appView.getChildByText(
				new UiSelector().className("android.widget.TextView"),
				"FileString", true);

		assertTrue("FileString App not launched",
				applicationFileString.exists());
		applicationFileString.clickAndWaitForNewWindow();
		for (int i = 0; i < 1; i++) {
			// Menu String File Click
			assertTrue("Click Menu Failed", menuStringFile.exists());
			menuStringFile.clickAndWaitForNewWindow();

			// String a File
			assertTrue("Click String a File Failed", stringFile.exists());
			stringFile.clickAndWaitForNewWindow();

			// File Manager
			assertTrue("Click File Manager Failed", fileManager.exists());
			fileManager.clickAndWaitForNewWindow();

			// Looking for data
			appFolder.setAsVerticalList();
			data = appFolder
					.getChildByText(
							new UiSelector().resourceId(
									"com.rhmsoft.fm:id/name").className(
									"android.widget.TextView"), "data", true);
			assertTrue("Click data Failed", data.exists());
			data.clickAndWaitForNewWindow();

			// //Looking for file
			appFolder.setAsVerticalList();

			// *[@id="user-links"]/li[1]/a/span/span

			if (i == 0) {
				file = appFolder.getChildByText(
						new UiSelector().resourceId("com.rhmsoft.fm:id/name")
								.className("android.widget.TextView"),
						"Chuyen Mua - Trung Quan Idol.mp3", true);
				// English Classes-Elementary.pdf English
				// Classes-Preintermediate.pdf HR-DEPENDENT REGISTRATION
				// FORM.doc
			} else if (i == 1) {
				file = appFolder.getChildByText(
						new UiSelector().resourceId("com.rhmsoft.fm:id/name")
								.className("android.widget.TextView"),
						"Can do list_softskills.docx", true);
			}
			assertTrue("Click data Failed", file.exists());
			file.clickAndWaitForNewWindow(5000);
			// Fill email
			emailEditText = uiScrollableStringFile
					.getChildByText(
							new UiSelector()
									.className("android.widget.EditText")
									.resourceId(
											"com.filestring.lattedouble:id/auto_text_add_recipient"),
							"Type Names or Email Addresses");

			if (emailEditText.exists()) {
				emailEditText.clickAndWaitForNewWindow();
				emailEditText.setText("sta005@yopmail.com");
			}

			// Send String File
			assertTrue("Click send String File Failed", sendStringFile.exists());
			sendStringFile.clickAndWaitForNewWindow();

			// -------Notification Test--------------//
			UiDevice deviceInstance = UiDevice.getInstance();
			UiScrollable notiBar = new UiScrollable(new UiSelector()
					.className("android.widget.ScrollView")
					.resourceId("com.android.systemui:id/scroll")
					.scrollable(false));
			deviceInstance.openNotification();

			// CODE:START
			UiScrollable appViews = new UiScrollable(
					new UiSelector().scrollable(true));
			// Set the swiping mode to horizontal (the default is vertical)
			// appViews.setAsHorizontalList();
			// appViews.scrollToBeginning(10); // Otherwise the Apps may be on a
			// later
			// page of apps.
			// int maxSearchSwipes = appViews.getMaxSearchSwipes();

			UiSelector selector;
			selector = new UiSelector().className(
					android.widget.TextView.class.getName()).resourceId(
					"android:id/title");

			UiObject appToLaunch;

			// The following loop is to workaround a bug in Android 4.2.2 which
			// fails to scroll more than once into view.
			for (int a = 0; a < 5; a++) {

				try {
					try {
						Thread.sleep(5000); // 1000 milliseconds is one second.
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					appToLaunch = notiBar.getChildByText(selector,
							"File uploaded");

					if (appToLaunch != null) {
						// Create a UiSelector to find the Settings app and
						// simulate
						// a user click to launch the app.
						// appToLaunch.clickAndWaitForNewWindow();
						deviceInstance.pressBack();
						break;
					}
				} catch (UiObjectNotFoundException e) {
					System.out.println("Did not find match for "
							+ e.getLocalizedMessage());
					deviceInstance.pressBack();
				}

				// for (int j = 0; j < i; j++) {
				// appViews.scrollForward();
				// System.out.println("scrolling forward 1 page of apps.");
				// }
				// notiBar.scrollBackward();
				// notiBar.scrollToBeginning(10);
				// deviceInstance.openNotification();
			}

			UiScrollable checkFileString = new UiScrollable(new UiSelector().className(
					"android.widget.ListView").scrollable(true));

			UiSelector fileSelector;
			fileSelector = new UiSelector().className(android.widget.TextView.class.getName());
			UiObject fileObject;
			
			for (int a = 0; a < 5; a++) {

				try {
					try {
						Thread.sleep(5000); // 1000 milliseconds is one second.
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					fileObject = checkFileString.getChildByText(fileSelector,
							"Chuyen Mua - Trung Quan Idol.mp3");

					if (fileObject != null) {
						// Create a UiSelector to find the Settings app and
						// simulate
						// a user click to launch the app.
						 fileObject.clickAndWaitForNewWindow();
						//deviceInstance.pressBack();
						break;
					}
				} catch (UiObjectNotFoundException e) {
					System.out.println("Did not find match for "
							+ e.getLocalizedMessage());
					deviceInstance.pressBack();
				}

			}
			
//			UiObject filename = checkFileString
//					.getChildByText(
//							new UiSelector()
//									.className("android.widget.TextView")
//									.resourceId(
//											"com.filestring.lattedouble:id/txtfile_item_name"),
//							"20140604_220.jpg", true);
//			filename.click();
			
			
			// //Check file in all file
			//
			// UiScrollable notiBar1 = new UiScrollable(new UiSelector()
			// .className("android.widget.ListView")
			// .resourceId("com.filestring.lattedouble:id/file_list_listview")
			// .scrollable(true));
			//
			// UiSelector selector1;
			// selector1 = new UiSelector().className(
			// android.widget.TextView.class.getName()).resourceId(
			// "com.filestring.lattedouble:id/txtfile_item_name");
			//
			// UiObject appToLaunch1;
			//
			// // The following loop is to workaround a bug in Android 4.2.2
			// which
			// // fails to scroll more than once into view.
			// for (int a = 0; a < 5; a++) {
			//
			// try {
			// try {
			// Thread.sleep(5000); // 1000 milliseconds is one second.
			// } catch (InterruptedException ex) {
			// Thread.currentThread().interrupt();
			// }
			// appToLaunch1 = notiBar1.getChildByText(selector1,
			// "PL_15_businessethics.ppt");
			//
			// if (appToLaunch1 != null) {
			// // Create a UiSelector to find the Settings app and
			// // simulate
			// // a user click to launch the app.
			// appToLaunch1.clickAndWaitForNewWindow();
			// //deviceInstance.pressBack();
			// break;
			// }
			// } catch (UiObjectNotFoundException e) {
			// System.out.println("Did not find match for "
			// + e.getLocalizedMessage());
			// deviceInstance.pressBack();
			// }
			//
			// // for (int j = 0; j < i; j++) {
			// // appViews.scrollForward();
			// // System.out.println("scrolling forward 1 page of apps.");
			// // }
			// // notiBar.scrollBackward();
			// // notiBar.scrollToBeginning(10);
			// // deviceInstance.openNotification();
			// }
			//
			// // Performs a swipe from one coordinate to another using the
			// number
			// // of steps to determine smoothness and speed. Each step
			// execution
			// // is throttled to 5ms per step. So for a 100 steps, the swipe
			// will
			// // take about 1/2 second to complete.
			// UiDevice deviceInstance = UiDevice.getInstance();
			// // or
			// UiScrollable notiBar = new UiScrollable(new UiSelector()
			// .className("android.widget.FrameLayout")
			// .resourceId("com.android.systemui:id/notification_panel")
			// .scrollable(false));
			// // .resourceId("com.android.systemui:id/notification_panel")
			// // .scrollable(false));
			// // for (int j = 0; j < 4; j++) {
			// deviceInstance.openNotification();
			// // try {
			// // TimeUnit.SECONDS.sleep(10);
			// // } catch (InterruptedException e) {
			// // // TODO Auto-generated catch block
			// // e.printStackTrace();
			// // }
			// //Khong co title thi ko clic duoc usb debugging mac du co quet
			// list noti
			// UiObject openNoti = notiBar.getChildByText(new UiSelector()
			// .resourceId(android.widget.TextView.class.getName())
			// .resourceId("android:id/title"), "USB debugging connected");
			//
			// if (openNoti.exists()) {
			// openNoti.click();
			// }
			//
			// // UiObject listFile = notiBar.getChildByText(new UiSelector(),
			// // "PL_15_businessethics.ppt", true);
			// // if(listFile.exists()){
			// // listFile.click();
			// // }
			// // }
			//
			// // int dHeight = deviceInstance.getDisplayHeight();
			// // int dWidth = deviceInstance.getDisplayWidth();
			// // System.out.println("height =" + dHeight);
			// // System.out.println("width =" + dWidth);
			// // int xScrollPosition = dWidth / 2;
			// // int yScrollStop = dHeight / 2;
			// // UiDevice.getInstance().swipe(xScrollPosition, 0,
			// xScrollPosition,
			// // yScrollStop, 100);
			// // --------------------//
			// // UiSelector selector = null;
			// // UiObject noti = new UiObject(selector);
			// // if (selector.equals("Upload file failed")) {
			// //
			// // }

		}
		// CODE:END
		System.out.println("testcase execution completed");
	}

	public void tearDown() throws Exception {

	}

	public static void checkFileUploaded(final UiAutomatorTestCase test,
			int times) {
		while (times > 0) {
			test.getUiDevice().pressBack();
			times--;
		}
	}

}
