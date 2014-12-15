package FileStringTestProject.StringFileSuite;

import FileStringTestProject.ClassUtils.ClassUtils;
import FileStringTestProject.ClassUtils.DataFill;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * StringFile
 * 
 * NOTE:1.This class can contain only one test case. 2.Do not change the class
 * name.
 */
public class StringFile extends UiAutomatorTestCase {

	/**
	 * @generated All your test code will go here NOTE: Do not change the method
	 *            signature
	 */

	public static final String STRING_FROM_STRINGAFILE = "com.filestring.lattedouble:id/string_file";
	public static final String STRING_FROM_DROPBOX = "";
	public static final String STRING_FROM_GOOGLEDRIVE = "";
	
	private final static String FILE_UPLOADED_SUCCESSFUL = "File uploaded";

	public void test() throws UiObjectNotFoundException, RemoteException {
		System.out.println("testcase execution started");
		// takeScreenShot();
		// CODE:START

		ClassUtils.launchFileStringApp(DataFill.FILESTRING_APP);
		ClassUtils.signIn(DataFill.EMAIL_DOWNSTREAM, DataFill.PASSWORD_PUBLIC);
		clickMenuStringFile(STRING_FROM_STRINGAFILE);
		ClassUtils.selectItem(DataFill.FILE_NAME_STRING);
		ClassUtils.inputEmailShare(DataFill.EMAIL_RECIPIENT);
		ClassUtils.sendStringFile();
		ClassUtils.openNotificationPanel();
		ClassUtils.notificationCheckFileUploaded(FILE_UPLOADED_SUCCESSFUL);
		ClassUtils.invokeListViewItem(ClassUtils.LISTVIEW_CLASSNAME,
				DataFill.FILE_NAME_STRING, ClassUtils.ACTION_TAKE_SCREENSHOT);
		ClassUtils.signOut();
		// CODE:END
		System.out.println("testcase execution completed");
	}

	// Open Menu String File
	public void clickMenuStringFile(String actionIdStringFrom)
			throws UiObjectNotFoundException {
		UiObject menuStringFile = new UiObject(new UiSelector().resourceId(
				"com.filestring.lattedouble:id/menu_files_list_action_add")
				.description("Add"));
		menuStringFile.clickAndWaitForNewWindow();

		UiObject menu = new UiObject(
				new UiSelector().resourceId(actionIdStringFrom));
		menu.clickAndWaitForNewWindow();
	}

}