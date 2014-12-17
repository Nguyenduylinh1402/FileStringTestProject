package FileStringTestProject.ChangeRights;

import FileStringTestProject.ClassUtils.ClassUtils;
import FileStringTestProject.ClassUtils.DataFill;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * ChangeRights
 * 
 * NOTE:1.This class can contain only one test case. 2.Do not change the class
 * name.
 */
public class ChangeRights extends UiAutomatorTestCase {

	public static final String FILE_INFO = "com.filestring.lattedouble:id/txt_file_info";
	public static final String FILENAME_CHANGERIGHT = "01BasicListening_1-17 black.pdf";
	public static final String FILENAME_CHANGE = "01BasicListening_1-17 black.fcs";
	public static final String ACCESSLEVEL_ID_POPUP = "com.filestring.lattedouble:id/checkedTextView1";

	/**
	 * @throws InterruptedException
	 * @generated All your test code will go here NOTE: Do not change the method
	 *            signature
	 */
	public void test() throws UiObjectNotFoundException, InterruptedException {
		System.out.println("testcase execution started");
		// takeScreenShot();
		// CODE:START
		// Change rights file :01BasicListening_1-17 black.pdf
		ClassUtils.openFileDetails(FILENAME_CHANGERIGHT);
		verifyRightsAfterChange(FILENAME_CHANGE);
		// -----start change rights---------//
		changeRightsAccessLevel(FILENAME_CHANGERIGHT);
		// ----repeat------------//
		Thread.sleep(10000);
		verifyRightsAfterChange(FILENAME_CHANGE);

		// CODE:END
		System.out.println("testcase execution completed");
	}

	public void changeRightsAccessLevel(String fileName)
			throws UiObjectNotFoundException {
		ClassUtils.openFileDetails(fileName);
		ClassUtils.clickItemID("com.filestring.lattedouble:id/right");

		ClassUtils
				.clickItemID("com.filestring.lattedouble:id/access_level_value");

		ClassUtils.clickFalseChecked(ACCESSLEVEL_ID_POPUP);
		ClassUtils.clickItemText("OK");
		ClassUtils
				.clickItemID("com.filestring.lattedouble:id/menu_recipients_send");
		ClassUtils.clickItemText("Change");
	}

	public void verifyRightsAfterChange(String fileName)
			throws UiObjectNotFoundException {
		String rightSenderChange = getRightsSenderShared();
		ClassUtils.clickItemID(ClassUtils.ID_HOME);

		ClassUtils.switchAccount(DataFill.EMAIL_RECIPIENT);
		ClassUtils.invokeFolderSenderFromRecipient(DataFill.FOLDER_OWNER);
		ClassUtils.openFileDetails(fileName);
		ClassUtils.clickItemID(FILE_INFO);
		// Thread.sleep(5000);
		String myRights = getMyRights();

		assertEquals(myRights, rightSenderChange);
		ClassUtils.takeScreenShot(myRights);
		ClassUtils.clickItemID(ClassUtils.ID_HOME);
		ClassUtils.clickItemID(ClassUtils.ID_HOME);
		ClassUtils.switchAccount(DataFill.EMAIL_OWNER);
	}

	public String getMyRights() throws UiObjectNotFoundException {
		UiObject recipient = new UiObject(
				new UiSelector()
						.resourceId("com.filestring.lattedouble:id/my_right_value"));
		String myRights = recipient.getText().toString();
		ClassUtils.loggerd("getMyRight: ", myRights);
		return myRights;
	}

	public String getRightsSenderShared() throws UiObjectNotFoundException {

		UiObject recipient = new UiObject(
				new UiSelector()
						.resourceId("com.filestring.lattedouble:id/right"));
		String senderShareRights = recipient.getText().toString();
		ClassUtils.loggerd("getRightsSenderShared: ", senderShareRights);
		return senderShareRights;

	}

}
