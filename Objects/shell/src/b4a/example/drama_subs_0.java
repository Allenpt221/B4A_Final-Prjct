package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class drama_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,59);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.drama.remoteMe.runUserSub(false, "drama","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 59;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 60;BA.debugLine="Activity.LoadLayout(\"Drama\") ' This layout contai";
Debug.ShouldStop(134217728);
drama.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Drama")),drama.mostCurrent.activityBA);
 BA.debugLineNum = 63;BA.debugLine="p.Initialize(\"\")";
Debug.ShouldStop(1073741824);
drama.mostCurrent._p.runVoidMethod ("Initialize",drama.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 64;BA.debugLine="p.LoadLayout(\"panelview\")";
Debug.ShouldStop(-2147483648);
drama.mostCurrent._p.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("panelview")),drama.mostCurrent.activityBA);
 BA.debugLineNum = 66;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(2);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("avengers.jpeg"))).getObject()));
 BA.debugLineNum = 70;BA.debugLine="Drama1.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(32);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 71;BA.debugLine="Star1.Text = \"☆☆☆\"";
Debug.ShouldStop(64);
drama.mostCurrent._star1.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 73;BA.debugLine="Drama2.Text = \"Spiderman\"";
Debug.ShouldStop(256);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Spiderman"));
 BA.debugLineNum = 74;BA.debugLine="Star2.Text = \"☆☆☆☆☆\"";
Debug.ShouldStop(512);
drama.mostCurrent._star2.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆☆☆"));
 BA.debugLineNum = 76;BA.debugLine="Drama3.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(2048);
drama.mostCurrent._drama3.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 77;BA.debugLine="Star3.Text = \"☆☆☆\"";
Debug.ShouldStop(4096);
drama.mostCurrent._star3.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 79;BA.debugLine="Drama3.Text = \"Spiderman\"";
Debug.ShouldStop(16384);
drama.mostCurrent._drama3.runMethod(true,"setText",BA.ObjectToCharSequence("Spiderman"));
 BA.debugLineNum = 80;BA.debugLine="Star2.Text = \"☆☆☆☆☆\"";
Debug.ShouldStop(32768);
drama.mostCurrent._star2.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆☆☆"));
 BA.debugLineNum = 82;BA.debugLine="Drama4.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(131072);
drama.mostCurrent._drama4.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 83;BA.debugLine="Star5.Text = \"☆☆☆\"";
Debug.ShouldStop(262144);
drama.mostCurrent._star5.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 85;BA.debugLine="Drama5.Text = \"Spiderman\"";
Debug.ShouldStop(1048576);
drama.mostCurrent._drama5.runMethod(true,"setText",BA.ObjectToCharSequence("Spiderman"));
 BA.debugLineNum = 86;BA.debugLine="Star5.Text = \"☆☆☆☆☆\"";
Debug.ShouldStop(2097152);
drama.mostCurrent._star5.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆☆☆"));
 BA.debugLineNum = 88;BA.debugLine="Drama6.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(8388608);
drama.mostCurrent._drama6.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 89;BA.debugLine="Star5.Text = \"☆☆☆\"";
Debug.ShouldStop(16777216);
drama.mostCurrent._star5.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 91;BA.debugLine="Drama6.Text = \"Spiderman\"";
Debug.ShouldStop(67108864);
drama.mostCurrent._drama6.runMethod(true,"setText",BA.ObjectToCharSequence("Spiderman"));
 BA.debugLineNum = 92;BA.debugLine="Star6.Text = \"☆☆☆☆☆\"";
Debug.ShouldStop(134217728);
drama.mostCurrent._star6.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆☆☆"));
 BA.debugLineNum = 94;BA.debugLine="Drama7.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(536870912);
drama.mostCurrent._drama7.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 95;BA.debugLine="Star7.Text = \"☆☆☆\"";
Debug.ShouldStop(1073741824);
drama.mostCurrent._star7.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 97;BA.debugLine="Drama8.Text = \"Spiderman\"";
Debug.ShouldStop(1);
drama.mostCurrent._drama8.runMethod(true,"setText",BA.ObjectToCharSequence("Spiderman"));
 BA.debugLineNum = 98;BA.debugLine="Star8.Text = \"☆☆☆☆☆\"";
Debug.ShouldStop(2);
drama.mostCurrent._star8.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆☆☆"));
 BA.debugLineNum = 100;BA.debugLine="Drama9.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(8);
drama.mostCurrent._drama9.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 101;BA.debugLine="Star9.Text = \"☆☆☆\"";
Debug.ShouldStop(16);
drama.mostCurrent._star9.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 103;BA.debugLine="Drama10.Text = \"Spiderman\"";
Debug.ShouldStop(64);
drama.mostCurrent._drama10.runMethod(true,"setText",BA.ObjectToCharSequence("Spiderman"));
 BA.debugLineNum = 104;BA.debugLine="Star10.Text = \"☆☆☆☆☆\"";
Debug.ShouldStop(128);
drama.mostCurrent._star10.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆☆☆"));
 BA.debugLineNum = 107;BA.debugLine="ScrollView1.Panel.AddView(p, 0, 0, 200%x, 170%y)";
Debug.ShouldStop(1024);
drama.mostCurrent._scrollview1.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((drama.mostCurrent._p.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(drama.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 200)),drama.mostCurrent.activityBA)),(Object)(drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 170)),drama.mostCurrent.activityBA)));
 BA.debugLineNum = 110;BA.debugLine="ScrollView1.Panel.Height = p.Height";
Debug.ShouldStop(8192);
drama.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",drama.mostCurrent._p.runMethod(true,"getHeight"));
 BA.debugLineNum = 113;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _button1_click() throws Exception{
try {
		Debug.PushSubsStack("Button1_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,115);
if (RapidSub.canDelegate("button1_click")) { return b4a.example.drama.remoteMe.runUserSub(false, "drama","button1_click");}
RemoteObject _userinput = RemoteObject.createImmutable("");
 BA.debugLineNum = 115;BA.debugLine="Private Sub Button1_Click";
Debug.ShouldStop(262144);
 BA.debugLineNum = 117;BA.debugLine="Dim userInput As String";
Debug.ShouldStop(1048576);
_userinput = RemoteObject.createImmutable("");Debug.locals.put("userInput", _userinput);
 BA.debugLineNum = 118;BA.debugLine="userInput = EditText1.Text";
Debug.ShouldStop(2097152);
_userinput = drama.mostCurrent._edittext1.runMethod(true,"getText");Debug.locals.put("userInput", _userinput);
 BA.debugLineNum = 119;BA.debugLine="If EditText1.Text = \"Avengers Dooms Day\" Or EditT";
Debug.ShouldStop(4194304);
if (RemoteObject.solveBoolean("=",drama.mostCurrent._edittext1.runMethod(true,"getText"),BA.ObjectToString("Avengers Dooms Day")) || RemoteObject.solveBoolean("=",drama.mostCurrent._edittext1.runMethod(true,"getText"),BA.ObjectToString("Avengers"))) { 
 BA.debugLineNum = 120;BA.debugLine="Drama1.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(8388608);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 121;BA.debugLine="Star1.Text = \"☆☆☆\"";
Debug.ShouldStop(16777216);
drama.mostCurrent._star1.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 122;BA.debugLine="Drama2.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(33554432);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 123;BA.debugLine="Star2.Text = \"☆☆☆\"";
Debug.ShouldStop(67108864);
drama.mostCurrent._star2.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 124;BA.debugLine="Drama3.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(134217728);
drama.mostCurrent._drama3.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 125;BA.debugLine="Star3.Text = \"☆☆☆\"";
Debug.ShouldStop(268435456);
drama.mostCurrent._star3.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 126;BA.debugLine="Panel6.Visible = False";
Debug.ShouldStop(536870912);
drama.mostCurrent._panel6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 127;BA.debugLine="Panel7.Visible = False";
Debug.ShouldStop(1073741824);
drama.mostCurrent._panel7.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 128;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(-2147483648);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 BA.debugLineNum = 129;BA.debugLine="p.Width = 100%x";
Debug.ShouldStop(1);
drama.mostCurrent._p.runMethod(true,"setWidth",drama.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),drama.mostCurrent.activityBA));
 BA.debugLineNum = 131;BA.debugLine="ScrollView1.Panel.Height = p.Height";
Debug.ShouldStop(4);
drama.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",drama.mostCurrent._p.runMethod(true,"getHeight"));
 }else {
 BA.debugLineNum = 133;BA.debugLine="MsgboxAsync(userInput, \"Not Found!\")";
Debug.ShouldStop(16);
drama.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence(_userinput)),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Not Found!"))),drama.processBA);
 };
 BA.debugLineNum = 138;BA.debugLine="End Sub";
Debug.ShouldStop(512);
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
 //BA.debugLineNum = 15;BA.debugLine="Private ScrollView1 As ScrollView";
drama.mostCurrent._scrollview1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private Drama1 As Label";
drama.mostCurrent._drama1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private Drama10 As Label";
drama.mostCurrent._drama10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private Drama2 As Label";
drama.mostCurrent._drama2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private Drama3 As Label";
drama.mostCurrent._drama3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Private Drama4 As Label";
drama.mostCurrent._drama4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 22;BA.debugLine="Private Drama5 As Label";
drama.mostCurrent._drama5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 23;BA.debugLine="Private Drama6 As Label";
drama.mostCurrent._drama6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 24;BA.debugLine="Private Drama7 As Label";
drama.mostCurrent._drama7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Private Drama8 As Label";
drama.mostCurrent._drama8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 26;BA.debugLine="Private Drama9 As Label";
drama.mostCurrent._drama9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 27;BA.debugLine="Private DramaImage1 As ImageView";
drama.mostCurrent._dramaimage1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 28;BA.debugLine="Private DramaImage10 As ImageView";
drama.mostCurrent._dramaimage10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 29;BA.debugLine="Private DramaImage2 As ImageView";
drama.mostCurrent._dramaimage2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 30;BA.debugLine="Private DramaImage3 As ImageView";
drama.mostCurrent._dramaimage3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 31;BA.debugLine="Private DramaImage4 As ImageView";
drama.mostCurrent._dramaimage4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 32;BA.debugLine="Private DramaImage5 As ImageView";
drama.mostCurrent._dramaimage5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 33;BA.debugLine="Private DramaImage6 As ImageView";
drama.mostCurrent._dramaimage6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 34;BA.debugLine="Private DramaImage7 As ImageView";
drama.mostCurrent._dramaimage7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 35;BA.debugLine="Private DramaImage8 As ImageView";
drama.mostCurrent._dramaimage8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 36;BA.debugLine="Private DramaImage9 As ImageView";
drama.mostCurrent._dramaimage9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 37;BA.debugLine="Private Star1 As Label";
drama.mostCurrent._star1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 38;BA.debugLine="Private Star2 As Label";
drama.mostCurrent._star2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 39;BA.debugLine="Private Star10 As Label";
drama.mostCurrent._star10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 40;BA.debugLine="Private Star3 As Label";
drama.mostCurrent._star3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 41;BA.debugLine="Private Star4 As Label";
drama.mostCurrent._star4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 42;BA.debugLine="Private Star5 As Label";
drama.mostCurrent._star5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 43;BA.debugLine="Private Star6 As Label";
drama.mostCurrent._star6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 44;BA.debugLine="Private Star7 As Label";
drama.mostCurrent._star7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 45;BA.debugLine="Private Star8 As Label";
drama.mostCurrent._star8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 46;BA.debugLine="Private Star9 As Label";
drama.mostCurrent._star9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 47;BA.debugLine="Private Button1 As Button";
drama.mostCurrent._button1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 48;BA.debugLine="Private EditText1 As EditText";
drama.mostCurrent._edittext1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 50;BA.debugLine="Dim p As Panel";
drama.mostCurrent._p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 52;BA.debugLine="Private Panel2 As Panel";
drama.mostCurrent._panel2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 53;BA.debugLine="Private Panel6 As Panel";
drama.mostCurrent._panel6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 54;BA.debugLine="Private Panel7 As Panel";
drama.mostCurrent._panel7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 55;BA.debugLine="Private Panel8 As Panel";
drama.mostCurrent._panel8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 56;BA.debugLine="Private Panel9 As Panel";
drama.mostCurrent._panel9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 57;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}