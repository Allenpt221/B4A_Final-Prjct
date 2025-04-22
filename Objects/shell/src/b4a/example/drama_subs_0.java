package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class drama_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,21);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.drama.remoteMe.runUserSub(false, "drama","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 21;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 22;BA.debugLine="Activity.LoadLayout(\"Drama\") ' Load the main layo";
Debug.ShouldStop(2097152);
drama.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Drama")),drama.mostCurrent.activityBA);
 BA.debugLineNum = 26;BA.debugLine="ImageView1.Bitmap = LoadBitmap(File.DirAssets, \"a";
Debug.ShouldStop(33554432);
drama.mostCurrent._imageview1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("avengers.jpeg"))).getObject()));
 BA.debugLineNum = 29;BA.debugLine="Label1.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(268435456);
drama.mostCurrent._label1.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 30;BA.debugLine="Label2.Text = \"☆☆☆\"";
Debug.ShouldStop(536870912);
drama.mostCurrent._label2.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 36;BA.debugLine="End Sub";
Debug.ShouldStop(8);
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
 //BA.debugLineNum = 15;BA.debugLine="Private ImageView1 As ImageView";
drama.mostCurrent._imageview1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 16;BA.debugLine="Private Label1 As Label";
drama.mostCurrent._label1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private Label2 As Label";
drama.mostCurrent._label2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}