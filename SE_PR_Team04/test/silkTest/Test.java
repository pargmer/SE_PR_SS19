package silkTest;
import com.borland.silktest.jtf.BaseState;
import org.junit.Before;
import com.microfocus.silktest.jtf.uia.UIAButton;
import com.microfocus.silktest.jtf.uia.UIATextBox;
import com.microfocus.silktest.jtf.uia.UIAWindow;
import com.borland.silktest.jtf.common.types.MouseButton;
import com.borland.silktest.jtf.common.types.Point;
import com.microfocus.silktest.jtf.uia.UIACheckBox;
import com.borland.silktest.jtf.Desktop;
import com.microfocus.silktest.jtf.uia.UIAImage;
import com.microfocus.silktest.jtf.uia.UIAComboBox;
import com.microfocus.silktest.jtf.uia.UIAListItem;
import com.microfocus.silktest.jtf.uia.UIAPane;
import com.microfocus.silktest.jtf.uia.UIATextBlock;
import com.microfocus.silktest.jtf.uia.UIAObject;
import com.microfocus.silktest.jtf.uia.UIATreeView;

public class Test {

	private Desktop desktop = new Desktop();

	@Before
	public void baseState() {
		BaseState baseState = new BaseState();
		baseState.execute(desktop);
	}

	@org.junit.Test
	public void create_delete_exercise() {
		desktop.<UIAButton>find("Window.New Workout").click();
		desktop.<UIAButton>find("Window.New Exercise").click();
		desktop.<UIATextBox>find("Window.TextBox").typeKeys("test<Tab>");
		desktop.<UIATextBox>find("Window.TextBox2").typeKeys("test<Tab>");
		desktop.<UIATextBox>find("Window.TextBox3").typeKeys("<#9><#9>");
		desktop.<UIAComboBox>find("Window.ComboBox").open();
		desktop.<UIAListItem>find("Window.Window.Wh").click(MouseButton.LEFT, new Point(76, 14));
		desktop.<UIAButton>find("Window.Save Exercise").click();
		desktop.<UIACheckBox>find("Window.CheckBox13").check();
		desktop.<UIAButton>find("Window.Delete Exercise").click();
		desktop.<UIAButton>find("Information Dialog.OK").click();
		desktop.<UIAButton>find("Window.Back").click();
	}

	@org.junit.Test
	public void create_delete_workout() {
		desktop.<UIAButton>find("Window.New Workout").click();
		desktop.<UIATextBox>find("Window.TextBox").click(MouseButton.LEFT, new Point(104, 14));
		desktop.<UIATextBox>find("Window.TextBox").typeKeys("test<Tab>");
		desktop.<UIAPane>find("Window.Pane").click(MouseButton.LEFT, new Point(153, 13));
		desktop.<UIAWindow>find("Window.Window").click(MouseButton.LEFT, new Point(126, 161));
		desktop.<UIACheckBox>find("Window.CheckBox").check();
		desktop.<UIAButton>find("Window.Save Workout").click();
		desktop.<UIAComboBox>find("Window.ComboBox").open();
		desktop.<UIAListItem>find("Window.Window.test").click(MouseButton.LEFT, new Point(100, 6));
		desktop.<UIAButton>find("Window.Delete Workout").click();
	}

	@org.junit.Test
	public void workout_start() {
		desktop.<UIAComboBox>find("Window.ComboBox").open();
		desktop.<UIAListItem>find("Window.Window.Wochenende").click(MouseButton.LEFT, new Point(59, 11));
		desktop.<UIAButton>find("Window.Start Workout").click();
		desktop.<UIACheckBox>find("Window.CheckBox2").click(MouseButton.LEFT, new Point(8, 13));
		desktop.<UIACheckBox>find("Window.CheckBox").click(MouseButton.LEFT, new Point(6, 8));
		desktop.<UIACheckBox>find("Window.CheckBox3").click(MouseButton.LEFT, new Point(8, 13));
		desktop.<UIACheckBox>find("Create Workout!.CheckBox").check();
		desktop.<UIAButton>find("Create Workout!.Done").click();
		desktop.<UIAButton>find("Information Dialog.OK").click();
	}

	@org.junit.Test
	public void statistic() {

		//start recording 
		desktop.<UIAButton>find("Window.Statistics").click();
		desktop.<UIAPane>find("Window.Pane").click(MouseButton.LEFT, new Point(174, 14));

		desktop.<UIAPane>find("Create Workout!.Pane").click(MouseButton.LEFT, new Point(166, 12));
		desktop.<UIAPane>find("Create Workout!.Pane").click(MouseButton.LEFT, new Point(196, 12));
		desktop.<UIAButton>find("Create Workout!.Search").click();

		desktop.<UIAButton>find("Create Workout!.Back to Main").click();
		//end recording
	}

	@org.junit.Test
	public void import_export() {
		desktop.<UIAButton>find("Window.Export Workouts").click();
		desktop.<UIAButton>find("Window.Export Workouts").click();
		desktop.<UIAButton>find("Information Dialog.OK").click();
		desktop.<UIAButton>find("Information Dialog.OK").click();
		desktop.<UIAButton>find("Window.Import Workouts").click();
		desktop.<UIAButton>find("Window.Import Workouts").click();
		desktop.<UIATreeView>find("Window2.Strukturansicht").select("/Schnellzugriff/Dokumente (angeheftet)");
		desktop.<UIATreeView>find("Window2.Strukturansicht").select("/Schnellzugriff/Dokumente (angeheftet)");
		desktop.<UIATextBox>find("Window2.System ItemNameDisplay").click(MouseButton.LEFT, new Point(24, 9));
		desktop.<UIATextBox>find("Window2.System ItemNameDisplay").click(MouseButton.LEFT, new Point(24, 9));
	}

	
	

	

}