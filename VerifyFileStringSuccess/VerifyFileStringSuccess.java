package FileStringTestProject.VerifyFileStringSuccess;

import FileStringTestProject.ClassUtils.ClassUtils;
import FileStringTestProject.ClassUtils.DataFill;
import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * VerifyFileStringSuccess
 * 
 * NOTE:1.This class can contain only one test case. 2.Do not change the class
 * name.
 */
public class VerifyFileStringSuccess extends UiAutomatorTestCase {

	/**
	 * @throws RemoteException
	 * @throws InterruptedException
	 * @generated All your test code will go here NOTE: Do not change the method
	 *            signature
	 */
	public void test() throws UiObjectNotFoundException, RemoteException,
			InterruptedException {
		System.out.println("testcase execution started");
		// takeScreenShot();
//		// CODE:START
//		ClassUtils.launchFileStringApp(DataFill.FILESTRING_APP);
//		ClassUtils.signIn(DataFill.EMAIL_RECIPIENT, DataFill.PASSWORD_PUBLIC);
//		// ClassUtils.loggerd(VerifyFileStringSuccess.class.getName(),
//		// "Sleeping 60s ...");
//		// Thread.sleep(60000);
//		ClassUtils.invokeListViewItem(ClassUtils.LISTVIEW_CLASSNAME,
//				DataFill.FILESTRING_RECEIVED_FILES, ClassUtils.ACTION_CLICK);
//		ClassUtils.invokeListViewItem(ClassUtils.LISTVIEW_CLASSNAME,
//				DataFill.FOLDER_OWNER, ClassUtils.ACTION_CLICK);
		ClassUtils.invokeListViewItem(ClassUtils.LISTVIEW_CLASSNAME,
				DataFill.FILE_FCS, ClassUtils.ACTION_TAKE_SCREENSHOT);
		ClassUtils.signOut();

		// CODE:END
		System.out.println("testcase execution completed");
	}

}
