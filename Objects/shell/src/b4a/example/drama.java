
package b4a.example;

import java.io.IOException;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.PCBA;
import anywheresoftware.b4a.pc.RDebug;
import anywheresoftware.b4a.pc.RemoteObject;
import anywheresoftware.b4a.pc.RDebug.IRemote;
import anywheresoftware.b4a.pc.Debug;
import anywheresoftware.b4a.pc.B4XTypes.B4XClass;
import anywheresoftware.b4a.pc.B4XTypes.DeviceClass;

public class drama implements IRemote{
	public static drama mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public drama() {
		mostCurrent = this;
	}
    public RemoteObject getRemoteMe() {
        return remoteMe;    
    }
    
	public static void main (String[] args) throws Exception {
		new RDebug(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
		RDebug.INSTANCE.waitForTask();

	}
    static {
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("drama"), "b4a.example.drama");
	}

public boolean isSingleton() {
		return true;
	}
     public static RemoteObject getObject() {
		return myClass;
	 }

	public RemoteObject activityBA;
	public RemoteObject _activity;
    private PCBA pcBA;

	public PCBA create(Object[] args) throws ClassNotFoundException{
		processBA = (RemoteObject) args[1];
		activityBA = (RemoteObject) args[2];
		_activity = (RemoteObject) args[3];
        anywheresoftware.b4a.keywords.Common.Density = (Float)args[4];
        remoteMe = (RemoteObject) args[5];
		pcBA = new PCBA(this, drama.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _scrollview1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ScrollViewWrapper");
public static RemoteObject _drama1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _drama10 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _drama2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _drama3 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _drama4 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _drama5 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _drama6 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _drama7 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _drama8 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _drama9 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _dramaimage1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _dramaimage10 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _dramaimage2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _dramaimage3 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _dramaimage4 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _dramaimage5 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _dramaimage6 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _dramaimage7 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _dramaimage8 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _dramaimage9 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _star1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _star2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _star10 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _star3 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _star4 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _star5 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _star6 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _star7 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _star8 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _star9 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _button1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _edittext1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _p = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _panel2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _panel6 = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _panel7 = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _panel8 = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _panel9 = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static b4a.example.main _main = null;
public static b4a.example.starter _starter = null;
public static b4a.example.action _action = null;
public static b4a.example.scifi _scifi = null;
public static b4a.example.panelview _panelview = null;
  public Object[] GetGlobals() {
		return new Object[] {"Action",Debug.moduleToString(b4a.example.action.class),"Activity",drama.mostCurrent._activity,"Button1",drama.mostCurrent._button1,"Drama1",drama.mostCurrent._drama1,"Drama10",drama.mostCurrent._drama10,"Drama2",drama.mostCurrent._drama2,"Drama3",drama.mostCurrent._drama3,"Drama4",drama.mostCurrent._drama4,"Drama5",drama.mostCurrent._drama5,"Drama6",drama.mostCurrent._drama6,"Drama7",drama.mostCurrent._drama7,"Drama8",drama.mostCurrent._drama8,"Drama9",drama.mostCurrent._drama9,"DramaImage1",drama.mostCurrent._dramaimage1,"DramaImage10",drama.mostCurrent._dramaimage10,"DramaImage2",drama.mostCurrent._dramaimage2,"DramaImage3",drama.mostCurrent._dramaimage3,"DramaImage4",drama.mostCurrent._dramaimage4,"DramaImage5",drama.mostCurrent._dramaimage5,"DramaImage6",drama.mostCurrent._dramaimage6,"DramaImage7",drama.mostCurrent._dramaimage7,"DramaImage8",drama.mostCurrent._dramaimage8,"DramaImage9",drama.mostCurrent._dramaimage9,"EditText1",drama.mostCurrent._edittext1,"Main",Debug.moduleToString(b4a.example.main.class),"p",drama.mostCurrent._p,"Panel2",drama.mostCurrent._panel2,"Panel6",drama.mostCurrent._panel6,"Panel7",drama.mostCurrent._panel7,"Panel8",drama.mostCurrent._panel8,"Panel9",drama.mostCurrent._panel9,"panelView",Debug.moduleToString(b4a.example.panelview.class),"SciFi",Debug.moduleToString(b4a.example.scifi.class),"ScrollView1",drama.mostCurrent._scrollview1,"Star1",drama.mostCurrent._star1,"Star10",drama.mostCurrent._star10,"Star2",drama.mostCurrent._star2,"Star3",drama.mostCurrent._star3,"Star4",drama.mostCurrent._star4,"Star5",drama.mostCurrent._star5,"Star6",drama.mostCurrent._star6,"Star7",drama.mostCurrent._star7,"Star8",drama.mostCurrent._star8,"Star9",drama.mostCurrent._star9,"Starter",Debug.moduleToString(b4a.example.starter.class)};
}
}