package FileStringTestProject.SignUpTestSuite;

import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
  This is SignUpTest

NOTE:1.This class can contain only one test case.
	 2.Do not change the class name.
*/
public class SignUpTest extends UiAutomatorTestCase {

	private static String TAG = "SignUpTest";

	/**
	 * @generated All your test code will go here NOTE: Do not change the method
	 *            signature
	 */
	// Global variable
	UiObject appTray = new UiObject(new UiSelector().description("Apps"));

	// Apps Tab
	UiObject appsTab = new UiObject(new UiSelector().className(
			"android.widget.TextView").description("Apps"));

	// Get AppTray container
	UiScrollable appView = new UiScrollable(new UiSelector().className(
			"android.view.View").scrollable(true));

	// Verify the launched application by it's package name
	UiObject appFileString = new UiObject(new UiSelector().packageName(
			"com.filestring.lattedouble").text("FileString"));

	// Sign Up now
	UiObject signUpNow = new UiObject(new UiSelector()
			.resourceId("com.filestring.lattedouble:id/orSignUp")
			.className("android.widget.TextView")
			.text("Not a member? Sign up now"));

	// First Name
	UiObject firstName = new UiObject(
			new UiSelector()
					.resourceId("com.filestring.lattedouble:id/edtSignUp_FirstName"));

	// Last name
	UiObject lastName = new UiObject(
			new UiSelector()
					.resourceId("com.filestring.lattedouble:id/edtSignUp_LastName"));

	// Email
	UiObject email = new UiObject(
			new UiSelector()
					.resourceId("com.filestring.lattedouble:id/edtSignUp_Email"));

	// Password
	UiObject passWord = new UiObject(
			new UiSelector()
					.resourceId("com.filestring.lattedouble:id/edtSignUp_Password"));

	// Sign Up now
	UiObject createSignUp = new UiObject(
			new UiSelector()
					.resourceId("com.filestring.lattedouble:id/txtSignUp_Create"));

	// OK verify
	UiObject okButton = new UiObject(new UiSelector().className(
			"android.widget.TextView").resourceId(
			"com.filestring.lattedouble:id/dlg_btn_positive"));

	public void test() throws UiObjectNotFoundException, RemoteException {
		System.out.println("testcase execution started");
		// takeScreenShot();
		// CODE:START

		UiDevice myDevie = getUiDevice();
		if (!myDevie.isScreenOn()) {
			myDevie.wakeUp();
		}
		myDevie.pressHome();
		appView.setAsHorizontalList();
		// can not file FS if give the package name ???
		// open wrong app ( FileManager) when do not have classname
		UiObject applicationFileString = appView.getChildByText(
				new UiSelector().className("android.widget.TextView"),
				"FileString", true);
		assertTrue("FileString app not Launched",
				applicationFileString.exists());
		applicationFileString.clickAndWaitForNewWindow();

		// Click Sign Up now
		if (signUpNow.exists()) {
			signUpNow.clickAndWaitForNewWindow();
		}

		if (firstName.exists()) {
			firstName.click();
			firstName.setText("auto_sta");
		}

		if (lastName.exists()) {
			lastName.click();
			lastName.setText("23.1");
		}

		if (email.exists()) {
			email.exists();
			email.setText("auto_sta23.11@yopmail.com");
		}

		if (passWord.exists()) {
			passWord.click();
			passWord.setText("1234");
		}
		if (createSignUp.exists()) {
			createSignUp.clickAndWaitForNewWindow();
		}

		// OK Email Unavailable
		 UiScrollable uiScrollableDialogEmailUnavailable = new UiScrollable(
		 new UiSelector().resourceId(
		 "com.filestring.lattedouble:id/dlg_root").scrollable(
		 false));
		 UiObject okEmailUnavailable = uiScrollableDialogEmailUnavailable
		 .getChildByText(
		 new UiSelector()
		 .className("android.widget.TextView")
		 .resourceId(
		 "com.filestring.lattedouble:id/dlg_btn_positive"),
		 "OK");
		 if (okEmailUnavailable.exists()) {
		 okEmailUnavailable.click();
		 }

		// OK Verify Email
//		UiScrollable uiScrollableDialogVerifyEmail = new UiScrollable(
//				new UiSelector().resourceId("android:id/content").scrollable(
//						false));
//		UiObject okVerifyEmail = uiScrollableDialogVerifyEmail
//				.getChildByText(
//						new UiSelector()
//								.className("android.widget.TextView")
//								.resourceId(
//										"com.filestring.lattedouble:id/dlg_btn_positive"),
//						"OK");
//		if (okVerifyEmail.exists()) {
//			okVerifyEmail.click();
//		}

		// CODE:END
		System.out.println("testcase execution completed");
	}

}