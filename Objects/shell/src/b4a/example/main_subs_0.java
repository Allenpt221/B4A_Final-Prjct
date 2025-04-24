package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _actionpage_click() throws Exception{
try {
		Debug.PushSubsStack("ActionPage_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,61);
if (RapidSub.canDelegate("actionpage_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","actionpage_click");}
 BA.debugLineNum = 61;BA.debugLine="Private Sub ActionPage_Click";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 62;BA.debugLine="StartActivity(Action)";
Debug.ShouldStop(536870912);
main.mostCurrent.__c.runVoidMethod ("StartActivity",main.processBA,(Object)((main.mostCurrent._action.getObject())));
 BA.debugLineNum = 63;BA.debugLine="Activity.Finish";
Debug.ShouldStop(1073741824);
main.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 64;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,31);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 31;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 32;BA.debugLine="Activity.LoadLayout(\"layout\")";
Debug.ShouldStop(-2147483648);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("layout")),main.mostCurrent.activityBA);
 BA.debugLineNum = 37;BA.debugLine="End Sub";
Debug.ShouldStop(16);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,44);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 44;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(2048);
 BA.debugLineNum = 46;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,40);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 40;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(128);
 BA.debugLineNum = 42;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _dramapage_click() throws Exception{
try {
		Debug.PushSubsStack("DramaPage_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,56);
if (RapidSub.canDelegate("dramapage_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","dramapage_click");}
 BA.debugLineNum = 56;BA.debugLine="Private Sub DramaPage_Click";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 57;BA.debugLine="StartActivity(Drama)";
Debug.ShouldStop(16777216);
main.mostCurrent.__c.runVoidMethod ("StartActivity",main.processBA,(Object)((main.mostCurrent._drama.getObject())));
 BA.debugLineNum = 58;BA.debugLine="Activity.Finish";
Debug.ShouldStop(33554432);
main.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 59;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 25;BA.debugLine="Private ActionPage As Label";
main.mostCurrent._actionpage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 26;BA.debugLine="Private DramaPage As Label";
main.mostCurrent._dramapage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 27;BA.debugLine="Private HomePage As Label";
main.mostCurrent._homepage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 28;BA.debugLine="Private SciFiPage As Label";
main.mostCurrent._scifipage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
starter_subs_0._process_globals();
drama_subs_0._process_globals();
action_subs_0._process_globals();
scifi_subs_0._process_globals();
panelview_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("b4a.example.main");
starter.myClass = BA.getDeviceClass ("b4a.example.starter");
drama.myClass = BA.getDeviceClass ("b4a.example.drama");
action.myClass = BA.getDeviceClass ("b4a.example.action");
scifi.myClass = BA.getDeviceClass ("b4a.example.scifi");
panelview.myClass = BA.getDeviceClass ("b4a.example.panelview");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 18;BA.debugLine="Private xui As XUI";
main._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _scifipage_click() throws Exception{
try {
		Debug.PushSubsStack("SciFiPage_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,50);
if (RapidSub.canDelegate("scifipage_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","scifipage_click");}
 BA.debugLineNum = 50;BA.debugLine="Private Sub SciFiPage_Click";
Debug.ShouldStop(131072);
 BA.debugLineNum = 51;BA.debugLine="StartActivity(SciFi)";
Debug.ShouldStop(262144);
main.mostCurrent.__c.runVoidMethod ("StartActivity",main.processBA,(Object)((main.mostCurrent._scifi.getObject())));
 BA.debugLineNum = 52;BA.debugLine="Activity.Finish";
Debug.ShouldStop(524288);
main.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 53;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}