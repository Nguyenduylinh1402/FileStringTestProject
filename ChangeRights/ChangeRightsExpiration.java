package FileStringTestProject.ChangeRights;

import FileStringTestProject.ClassUtils.ClassUtils;

import android.graphics.Rect;
import android.view.KeyEvent;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * ChangeRightsExpiration
 * 
 * NOTE:1.This class can contain only one test case. 2.Do not change the class
 * name.
 */
public class ChangeRightsExpiration extends UiAutomatorTestCase {
	public static final String FILENAME_CHANGERIGHT = "01BasicListening_1-17 black.pdf";
	public static final String FILENAME_CHANGE = "01BasicListening_1-17 black.fcs";

	/**
	 * @generated All your test code will go here NOTE: Do not change the method
	 *            signature
	 */
	public void test() throws UiObjectNotFoundException {
		System.out.println("testcase execution started");
		// takeScreenShot();
		// CODE:START
		// ClassUtils.openFileDetails(FILENAME_CHANGERIGHT);
		changeRightsAccessLevel(FILENAME_CHANGERIGHT);
		// CODE:END
		System.out.println("testcase execution completed");
	}

	public void changeRightsAccessLevel(String fileName)
			throws UiObjectNotFoundException {
		ClassUtils.openFileDetails(fileName);
		ClassUtils.clickItemID("com.filestring.lattedouble:id/right");

		ClassUtils
				.clickItemID("com.filestring.lattedouble:id/expiration_value");
		UiSelector setTimeExpirationCheckedSelector = new UiSelector()
				.resourceId("com.filestring.lattedouble:id/swExpirationOnOff");
		UiObject setTimeExpirationChecked = new UiObject(
				setTimeExpirationCheckedSelector);
		if (setTimeExpirationCheckedSelector.checked(false) == null) {
			setTimeExpirationChecked.click();
		}

		UiSelector textSelector = new UiSelector().resourceId(
				"android:id/numberpicker_input").index(1);
		UiSelector numberPicker = new UiSelector()
				.className("android.widget.NumberPicker");
		UiSelector linearLayoutExpirationPopup = new UiSelector()
				.resourceId("android:id/pickers");

		for (int i = 0; i < 3; i++) {
			UiSelector finalSelector = linearLayoutExpirationPopup
					.childSelector(numberPicker.childSelector(textSelector)
							.index(i));
			UiObject abc = new UiObject(finalSelector);
			if (i == 0) {
				abc.setText("Feb");
			} else if (i == 1) {
				abc.setText("14");
			} else {
				abc.setText("2015");
			}
		}
		UiDevice.getInstance().pressBack();
		ClassUtils.clickItemText("Set");
		ClassUtils
				.clickItemID("com.filestring.lattedouble:id/menu_recipients_send");
		ClassUtils.clickItemText("Change");
	}

}
