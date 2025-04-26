
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

public class action implements IRemote{
	public static action mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public action() {
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
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("action"), "b4a.example.action");
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
		pcBA = new PCBA(this, action.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _scrollview1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ScrollViewWrapper");
public static RemoteObject _drama1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _drama2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _drama3 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _drama4 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _drama5 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _drama6 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _drama7 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _drama8 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _drama9 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _drama10 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _dramaimage1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _dramaimage2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _dramaimage3 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _dramaimage4 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _dramaimage5 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _dramaimage6 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _dramaimage7 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _dramaimage8 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _dramaimage9 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _dramaimage10 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static RemoteObject _searchbtn = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _searchengine = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _p = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _panel1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _panel2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _panel3 = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _panel4 = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _panel5 = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _panel6 = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _panel7 = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _panel8 = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _panel9 = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _panel10 = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _panel11 = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _dramapage = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _homepage = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _scifipage = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _actionpage = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _starter1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _starter2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _starter3 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _starter4 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _starter5 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _starter6 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _starter7 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _starter8 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _starter9 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _starter10 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _overview1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _overview2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _overview3 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _overview4 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _overview5 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _overview6 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _overview7 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _overview8 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _overview9 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _overview10 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _year1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _year2 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _year3 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _year4 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _year5 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _year6 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _year7 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _year8 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _year9 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _year10 = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _notfound = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _notfoundimg = RemoteObject.declareNull("anywheresoftware.b4a.objects.ImageViewWrapper");
public static b4a.example.main _main = null;
public static b4a.example.starter _starter = null;
public static b4a.example.drama _drama = null;
public static b4a.example.scifi _scifi = null;
public static b4a.example.panelview _panelview = null;
  public Object[] GetGlobals() {
		return new Object[] {"ActionPage",action.mostCurrent._actionpage,"Activity",action.mostCurrent._activity,"Drama",Debug.moduleToString(b4a.example.drama.class),"Drama1",action.mostCurrent._drama1,"Drama10",action.mostCurrent._drama10,"Drama2",action.mostCurrent._drama2,"Drama3",action.mostCurrent._drama3,"Drama4",action.mostCurrent._drama4,"Drama5",action.mostCurrent._drama5,"Drama6",action.mostCurrent._drama6,"Drama7",action.mostCurrent._drama7,"Drama8",action.mostCurrent._drama8,"Drama9",action.mostCurrent._drama9,"DramaImage1",action.mostCurrent._dramaimage1,"DramaImage10",action.mostCurrent._dramaimage10,"DramaImage2",action.mostCurrent._dramaimage2,"DramaImage3",action.mostCurrent._dramaimage3,"DramaImage4",action.mostCurrent._dramaimage4,"DramaImage5",action.mostCurrent._dramaimage5,"DramaImage6",action.mostCurrent._dramaimage6,"DramaImage7",action.mostCurrent._dramaimage7,"DramaImage8",action.mostCurrent._dramaimage8,"DramaImage9",action.mostCurrent._dramaimage9,"DramaPage",action.mostCurrent._dramapage,"HomePage",action.mostCurrent._homepage,"Main",Debug.moduleToString(b4a.example.main.class),"NotFound",action.mostCurrent._notfound,"NotFoundImg",action.mostCurrent._notfoundimg,"OverView1",action.mostCurrent._overview1,"OverView10",action.mostCurrent._overview10,"OverView2",action.mostCurrent._overview2,"OverView3",action.mostCurrent._overview3,"OverView4",action.mostCurrent._overview4,"OverView5",action.mostCurrent._overview5,"OverView6",action.mostCurrent._overview6,"OverView7",action.mostCurrent._overview7,"OverView8",action.mostCurrent._overview8,"OverView9",action.mostCurrent._overview9,"p",action.mostCurrent._p,"Panel1",action.mostCurrent._panel1,"Panel10",action.mostCurrent._panel10,"Panel11",action.mostCurrent._panel11,"Panel2",action.mostCurrent._panel2,"Panel3",action.mostCurrent._panel3,"Panel4",action.mostCurrent._panel4,"Panel5",action.mostCurrent._panel5,"Panel6",action.mostCurrent._panel6,"Panel7",action.mostCurrent._panel7,"Panel8",action.mostCurrent._panel8,"Panel9",action.mostCurrent._panel9,"panelView",Debug.moduleToString(b4a.example.panelview.class),"SciFi",Debug.moduleToString(b4a.example.scifi.class),"SciFiPage",action.mostCurrent._scifipage,"ScrollView1",action.mostCurrent._scrollview1,"SearchBtn",action.mostCurrent._searchbtn,"SearchEngine",action.mostCurrent._searchengine,"Starter",Debug.moduleToString(b4a.example.starter.class),"Starter1",action.mostCurrent._starter1,"Starter10",action.mostCurrent._starter10,"Starter2",action.mostCurrent._starter2,"Starter3",action.mostCurrent._starter3,"Starter4",action.mostCurrent._starter4,"Starter5",action.mostCurrent._starter5,"Starter6",action.mostCurrent._starter6,"Starter7",action.mostCurrent._starter7,"Starter8",action.mostCurrent._starter8,"Starter9",action.mostCurrent._starter9,"Year1",action.mostCurrent._year1,"Year10",action.mostCurrent._year10,"Year2",action.mostCurrent._year2,"Year3",action.mostCurrent._year3,"Year4",action.mostCurrent._year4,"Year5",action.mostCurrent._year5,"Year6",action.mostCurrent._year6,"Year7",action.mostCurrent._year7,"Year8",action.mostCurrent._year8,"Year9",action.mostCurrent._year9};
}
}