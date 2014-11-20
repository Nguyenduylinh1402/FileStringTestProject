package FileStringTestProject.SignInTestSuite;

import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * This is SignInTest
 * 
 * NOTE:1.This class can contain only one test case. 2.Do not change the class
 * name.
 */
public class SignInTest extends UiAutomatorTestCase {

	private static String TAG = "SignInTest";

	/**
	 * @generated All your test code will go here NOTE: Do not change the method
	 *            signature
	 */

	// Global variable
	UiObject appTray = new UiObject(new UiSelector().description("Apps"));

	UiObject appsTab = new UiObject(new UiSelector().className(
			"android.widget.TextView").description("Apps"));

	UiScrollable appView = new UiScrollable(new UiSelector().className(
			"android.view.View").scrollable(true));

	UiObject emailSignIn = new UiObject(
			new UiSelector()
					.resourceId("com.filestring.lattedouble:id/edtSignInEmail"));

	UiObject passwordSignIn = new UiObject(
			new UiSelector()
					.resourceId("com.filestring.lattedouble:id/edtSignInPassword"));

	UiObject signIn = new UiObject(
			new UiSelector()
					.resourceId("com.filestring.lattedouble:id/btnSignIn"));

	public void test() throws UiObjectNotFoundException, RemoteException {
		System.out.println("testcase execution started");
		// takeScreenShot();
		// CODE:START
		UiDevice myDevice = getUiDevice();
		if (!myDevice.isScreenOn()) {
			myDevice.wakeUp();
		}
		myDevice.pressHome();
		if (appTray.exists()) {
			appTray.click();
		}
		if (appsTab.exists()) {
			appsTab.clickAndWaitForNewWindow();
		}

		appView.setAsHorizontalList();
		UiObject applicationFileString = appView.getChildByText(
				new UiSelector().className("android.widget.TextView"),
				"FileString", true);
		assertTrue("FileString app not launched",
				applicationFileString.exists());
		applicationFileString.clickAndWaitForNewWindow();

		if (emailSignIn.exists()) {
			emailSignIn.click();
			emailSignIn.setText("sta23.1@yopmail.com");
		}
		if (passwordSignIn.exists()) {
			passwordSignIn.click();
			passwordSignIn.setText("1234");
		}
		if (signIn.exists()) {
			signIn.click();
		}
		
		// CODE:END
		System.out.println("testcase execution completed");
	}

}
