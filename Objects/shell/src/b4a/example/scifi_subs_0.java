package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class scifi_subs_0 {


public static RemoteObject  _actionpage_click() throws Exception{
try {
		Debug.PushSubsStack("ActionPage_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,49);
if (RapidSub.canDelegate("actionpage_click")) { return b4a.example.scifi.remoteMe.runUserSub(false, "scifi","actionpage_click");}
 BA.debugLineNum = 49;BA.debugLine="Private Sub ActionPage_Click";
Debug.ShouldStop(65536);
 BA.debugLineNum = 50;BA.debugLine="StartActivity(Action)";
Debug.ShouldStop(131072);
scifi.mostCurrent.__c.runVoidMethod ("StartActivity",scifi.processBA,(Object)((scifi.mostCurrent._action.getObject())));
 BA.debugLineNum = 51;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
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
		Debug.PushSubsStack("Activity_Create (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,22);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.scifi.remoteMe.runUserSub(false, "scifi","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 22;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 24;BA.debugLine="Activity.LoadLayout(\"scifi\")";
Debug.ShouldStop(8388608);
scifi.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("scifi")),scifi.mostCurrent.activityBA);
 BA.debugLineNum = 26;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
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
		Debug.PushSubsStack("Activity_Pause (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,32);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.scifi.remoteMe.runUserSub(false, "scifi","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 32;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 34;BA.debugLine="End Sub";
Debug.ShouldStop(2);
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
		Debug.PushSubsStack("Activity_Resume (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,28);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.scifi.remoteMe.runUserSub(false, "scifi","activity_resume");}
 BA.debugLineNum = 28;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 30;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
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
		Debug.PushSubsStack("DramaPage_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,45);
if (RapidSub.canDelegate("dramapage_click")) { return b4a.example.scifi.remoteMe.runUserSub(false, "scifi","dramapage_click");}
 BA.debugLineNum = 45;BA.debugLine="Private Sub DramaPage_Click";
Debug.ShouldStop(4096);
 BA.debugLineNum = 46;BA.debugLine="StartActivity(Drama)";
Debug.ShouldStop(8192);
scifi.mostCurrent.__c.runVoidMethod ("StartActivity",scifi.processBA,(Object)((scifi.mostCurrent._drama.getObject())));
 BA.debugLineNum = 47;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Private ActionPage As Label";
scifi.mostCurrent._actionpage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private DramaPage As Label";
scifi.mostCurrent._dramapage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private HomePage As Label";
scifi.mostCurrent._homepage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private SciFiPage As Label";
scifi.mostCurrent._scifipage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _homepage_click() throws Exception{
try {
		Debug.PushSubsStack("HomePage_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,41);
if (RapidSub.canDelegate("homepage_click")) { return b4a.example.scifi.remoteMe.runUserSub(false, "scifi","homepage_click");}
 BA.debugLineNum = 41;BA.debugLine="Private Sub HomePage_Click";
Debug.ShouldStop(256);
 BA.debugLineNum = 42;BA.debugLine="StartActivity(Main)";
Debug.ShouldStop(512);
scifi.mostCurrent.__c.runVoidMethod ("StartActivity",scifi.processBA,(Object)((scifi.mostCurrent._main.getObject())));
 BA.debugLineNum = 43;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _scifipage_click() throws Exception{
try {
		Debug.PushSubsStack("SciFiPage_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,37);
if (RapidSub.canDelegate("scifipage_click")) { return b4a.example.scifi.remoteMe.runUserSub(false, "scifi","scifipage_click");}
 BA.debugLineNum = 37;BA.debugLine="Private Sub SciFiPage_Click";
Debug.ShouldStop(16);
 BA.debugLineNum = 39;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}