package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class drama_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,58);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.drama.remoteMe.runUserSub(false, "drama","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 58;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 59;BA.debugLine="Activity.LoadLayout(\"Drama\") ' Layout contains Sc";
Debug.ShouldStop(67108864);
drama.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Drama")),drama.mostCurrent.activityBA);
 BA.debugLineNum = 61;BA.debugLine="p.Initialize(\"\")";
Debug.ShouldStop(268435456);
drama.mostCurrent._p.runVoidMethod ("Initialize",drama.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 62;BA.debugLine="p.LoadLayout(\"panelview\")";
Debug.ShouldStop(536870912);
drama.mostCurrent._p.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("panelview")),drama.mostCurrent.activityBA);
 BA.debugLineNum = 65;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(1);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("avengers.jpeg"))).getObject()));
 BA.debugLineNum = 67;BA.debugLine="Drama1.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(4);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 68;BA.debugLine="Star1.Text = \"☆☆☆\"";
Debug.ShouldStop(8);
drama.mostCurrent._star1.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 70;BA.debugLine="Drama2.Text = \"Spiderman\"";
Debug.ShouldStop(32);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Spiderman"));
 BA.debugLineNum = 71;BA.debugLine="Star2.Text = \"☆☆☆☆☆\"";
Debug.ShouldStop(64);
drama.mostCurrent._star2.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆☆☆"));
 BA.debugLineNum = 73;BA.debugLine="Drama3.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(256);
drama.mostCurrent._drama3.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 74;BA.debugLine="Star3.Text = \"☆☆☆\"";
Debug.ShouldStop(512);
drama.mostCurrent._star3.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 76;BA.debugLine="Drama4.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(2048);
drama.mostCurrent._drama4.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 77;BA.debugLine="Star4.Text = \"☆☆☆\"";
Debug.ShouldStop(4096);
drama.mostCurrent._star4.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 79;BA.debugLine="Drama5.Text = \"Spiderman\"";
Debug.ShouldStop(16384);
drama.mostCurrent._drama5.runMethod(true,"setText",BA.ObjectToCharSequence("Spiderman"));
 BA.debugLineNum = 80;BA.debugLine="Star5.Text = \"☆☆☆☆☆\"";
Debug.ShouldStop(32768);
drama.mostCurrent._star5.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆☆☆"));
 BA.debugLineNum = 82;BA.debugLine="Drama6.Text = \"Spiderman\"";
Debug.ShouldStop(131072);
drama.mostCurrent._drama6.runMethod(true,"setText",BA.ObjectToCharSequence("Spiderman"));
 BA.debugLineNum = 83;BA.debugLine="Star6.Text = \"☆☆☆☆☆\"";
Debug.ShouldStop(262144);
drama.mostCurrent._star6.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆☆☆"));
 BA.debugLineNum = 85;BA.debugLine="Drama7.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(1048576);
drama.mostCurrent._drama7.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 86;BA.debugLine="Star7.Text = \"☆☆☆\"";
Debug.ShouldStop(2097152);
drama.mostCurrent._star7.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 88;BA.debugLine="Drama8.Text = \"Spiderman\"";
Debug.ShouldStop(8388608);
drama.mostCurrent._drama8.runMethod(true,"setText",BA.ObjectToCharSequence("Spiderman"));
 BA.debugLineNum = 89;BA.debugLine="Star8.Text = \"☆☆☆☆☆\"";
Debug.ShouldStop(16777216);
drama.mostCurrent._star8.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆☆☆"));
 BA.debugLineNum = 91;BA.debugLine="Drama9.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(67108864);
drama.mostCurrent._drama9.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 92;BA.debugLine="Star9.Text = \"☆☆☆\"";
Debug.ShouldStop(134217728);
drama.mostCurrent._star9.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 94;BA.debugLine="Drama10.Text = \"Spiderman\"";
Debug.ShouldStop(536870912);
drama.mostCurrent._drama10.runMethod(true,"setText",BA.ObjectToCharSequence("Spiderman"));
 BA.debugLineNum = 95;BA.debugLine="Star10.Text = \"☆☆☆☆☆\"";
Debug.ShouldStop(1073741824);
drama.mostCurrent._star10.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆☆☆"));
 BA.debugLineNum = 97;BA.debugLine="ScrollView1.Panel.AddView(p, 0, 0, 200%x, 170%y)";
Debug.ShouldStop(1);
drama.mostCurrent._scrollview1.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((drama.mostCurrent._p.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(drama.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 200)),drama.mostCurrent.activityBA)),(Object)(drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 170)),drama.mostCurrent.activityBA)));
 BA.debugLineNum = 98;BA.debugLine="ScrollView1.Panel.Height = p.Height";
Debug.ShouldStop(2);
drama.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",drama.mostCurrent._p.runMethod(true,"getHeight"));
 BA.debugLineNum = 99;BA.debugLine="End Sub";
Debug.ShouldStop(4);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 12;BA.debugLine="Private ScrollView1 As ScrollView";
drama.mostCurrent._scrollview1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");
 //BA.debugLineNum = 14;BA.debugLine="Private Drama1 As Label";
drama.mostCurrent._drama1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 15;BA.debugLine="Private Drama10 As Label";
drama.mostCurrent._drama10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 16;BA.debugLine="Private Drama2 As Label";
drama.mostCurrent._drama2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private Drama3 As Label";
drama.mostCurrent._drama3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private Drama4 As Label";
drama.mostCurrent._drama4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private Drama5 As Label";
drama.mostCurrent._drama5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private Drama6 As Label";
drama.mostCurrent._drama6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Private Drama7 As Label";
drama.mostCurrent._drama7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 22;BA.debugLine="Private Drama8 As Label";
drama.mostCurrent._drama8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 23;BA.debugLine="Private Drama9 As Label";
drama.mostCurrent._drama9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Private DramaImage1 As ImageView";
drama.mostCurrent._dramaimage1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 26;BA.debugLine="Private DramaImage10 As ImageView";
drama.mostCurrent._dramaimage10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 27;BA.debugLine="Private DramaImage2 As ImageView";
drama.mostCurrent._dramaimage2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 28;BA.debugLine="Private DramaImage3 As ImageView";
drama.mostCurrent._dramaimage3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 29;BA.debugLine="Private DramaImage4 As ImageView";
drama.mostCurrent._dramaimage4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 30;BA.debugLine="Private DramaImage5 As ImageView";
drama.mostCurrent._dramaimage5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 31;BA.debugLine="Private DramaImage6 As ImageView";
drama.mostCurrent._dramaimage6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 32;BA.debugLine="Private DramaImage7 As ImageView";
drama.mostCurrent._dramaimage7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 33;BA.debugLine="Private DramaImage8 As ImageView";
drama.mostCurrent._dramaimage8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 34;BA.debugLine="Private DramaImage9 As ImageView";
drama.mostCurrent._dramaimage9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 36;BA.debugLine="Private Star1 As Label";
drama.mostCurrent._star1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 37;BA.debugLine="Private Star2 As Label";
drama.mostCurrent._star2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 38;BA.debugLine="Private Star10 As Label";
drama.mostCurrent._star10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 39;BA.debugLine="Private Star3 As Label";
drama.mostCurrent._star3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 40;BA.debugLine="Private Star4 As Label";
drama.mostCurrent._star4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 41;BA.debugLine="Private Star5 As Label";
drama.mostCurrent._star5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 42;BA.debugLine="Private Star6 As Label";
drama.mostCurrent._star6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 43;BA.debugLine="Private Star7 As Label";
drama.mostCurrent._star7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 44;BA.debugLine="Private Star8 As Label";
drama.mostCurrent._star8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 45;BA.debugLine="Private Star9 As Label";
drama.mostCurrent._star9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 47;BA.debugLine="Private SearchBtn As Button";
drama.mostCurrent._searchbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 48;BA.debugLine="Private SearchEngine As EditText";
drama.mostCurrent._searchengine = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 50;BA.debugLine="Dim p As Panel";
drama.mostCurrent._p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 51;BA.debugLine="Private Panel2 As Panel";
drama.mostCurrent._panel2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 52;BA.debugLine="Private Panel6 As Panel";
drama.mostCurrent._panel6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 53;BA.debugLine="Private Panel7 As Panel";
drama.mostCurrent._panel7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 54;BA.debugLine="Private Panel8 As Panel";
drama.mostCurrent._panel8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 55;BA.debugLine="Private Panel9 As Panel";
drama.mostCurrent._panel9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _searchbtn_click() throws Exception{
try {
		Debug.PushSubsStack("SearchBtn_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,101);
if (RapidSub.canDelegate("searchbtn_click")) { return b4a.example.drama.remoteMe.runUserSub(false, "drama","searchbtn_click");}
RemoteObject _query = RemoteObject.createImmutable("");
RemoteObject _userinput = RemoteObject.createImmutable("");
 BA.debugLineNum = 101;BA.debugLine="Private Sub SearchBtn_Click";
Debug.ShouldStop(16);
 BA.debugLineNum = 102;BA.debugLine="Dim query As String = SearchEngine.Text.ToLowerCa";
Debug.ShouldStop(32);
_query = drama.mostCurrent._searchengine.runMethod(true,"getText").runMethod(true,"toLowerCase").runMethod(true,"trim");Debug.locals.put("query", _query);Debug.locals.put("query", _query);
 BA.debugLineNum = 103;BA.debugLine="Dim userInput As String = SearchEngine.Text";
Debug.ShouldStop(64);
_userinput = drama.mostCurrent._searchengine.runMethod(true,"getText");Debug.locals.put("userInput", _userinput);Debug.locals.put("userInput", _userinput);
 BA.debugLineNum = 105;BA.debugLine="If query.Contains(\"avengers dooms day\") Or query.";
Debug.ShouldStop(256);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("avengers dooms day")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("avengers"))))) { 
 BA.debugLineNum = 106;BA.debugLine="Drama1.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(512);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 107;BA.debugLine="Star1.Text = \"☆☆☆\"";
Debug.ShouldStop(1024);
drama.mostCurrent._star1.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 108;BA.debugLine="Drama2.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(2048);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 109;BA.debugLine="Star2.Text = \"☆☆☆\"";
Debug.ShouldStop(4096);
drama.mostCurrent._star2.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 110;BA.debugLine="Drama3.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(8192);
drama.mostCurrent._drama3.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 111;BA.debugLine="Star3.Text = \"☆☆☆\"";
Debug.ShouldStop(16384);
drama.mostCurrent._star3.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 113;BA.debugLine="Panel6.Visible = False";
Debug.ShouldStop(65536);
drama.mostCurrent._panel6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 114;BA.debugLine="Panel7.Visible = False";
Debug.ShouldStop(131072);
drama.mostCurrent._panel7.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 116;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(524288);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 BA.debugLineNum = 117;BA.debugLine="p.Width = 100%x";
Debug.ShouldStop(1048576);
drama.mostCurrent._p.runMethod(true,"setWidth",drama.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),drama.mostCurrent.activityBA));
 BA.debugLineNum = 118;BA.debugLine="ScrollView1.Panel.Height = p.Height";
Debug.ShouldStop(2097152);
drama.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",drama.mostCurrent._p.runMethod(true,"getHeight"));
 }else {
 BA.debugLineNum = 120;BA.debugLine="MsgboxAsync(userInput, \"Not Found!\")";
Debug.ShouldStop(8388608);
drama.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence(_userinput)),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Not Found!"))),drama.processBA);
 };
 BA.debugLineNum = 122;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _searchengine_textchanged(RemoteObject _old,RemoteObject _new) throws Exception{
try {
		Debug.PushSubsStack("SearchEngine_TextChanged (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,124);
if (RapidSub.canDelegate("searchengine_textchanged")) { return b4a.example.drama.remoteMe.runUserSub(false, "drama","searchengine_textchanged", _old, _new);}
RemoteObject _query = RemoteObject.createImmutable("");
Debug.locals.put("Old", _old);
Debug.locals.put("New", _new);
 BA.debugLineNum = 124;BA.debugLine="Sub SearchEngine_TextChanged (Old As String, New A";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 125;BA.debugLine="Dim query As String = New.ToLowerCase.Trim";
Debug.ShouldStop(268435456);
_query = _new.runMethod(true,"toLowerCase").runMethod(true,"trim");Debug.locals.put("query", _query);Debug.locals.put("query", _query);
 BA.debugLineNum = 127;BA.debugLine="If query = \"\" Then";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean("=",_query,BA.ObjectToString(""))) { 
 BA.debugLineNum = 129;BA.debugLine="Panel6.Visible = True";
Debug.ShouldStop(1);
drama.mostCurrent._panel6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 130;BA.debugLine="Panel7.Visible = True";
Debug.ShouldStop(2);
drama.mostCurrent._panel7.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 132;BA.debugLine="p.Height = 170%y";
Debug.ShouldStop(8);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 170)),drama.mostCurrent.activityBA));
 BA.debugLineNum = 133;BA.debugLine="p.Width = 200%x";
Debug.ShouldStop(16);
drama.mostCurrent._p.runMethod(true,"setWidth",drama.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 200)),drama.mostCurrent.activityBA));
 BA.debugLineNum = 134;BA.debugLine="ScrollView1.Panel.Height = p.Height";
Debug.ShouldStop(32);
drama.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",drama.mostCurrent._p.runMethod(true,"getHeight"));
 BA.debugLineNum = 137;BA.debugLine="Drama1.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(256);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 138;BA.debugLine="Star1.Text = \"☆☆☆\"";
Debug.ShouldStop(512);
drama.mostCurrent._star1.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 139;BA.debugLine="Drama2.Text = \"Spiderman\"";
Debug.ShouldStop(1024);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Spiderman"));
 BA.debugLineNum = 140;BA.debugLine="Star2.Text = \"☆☆☆☆☆\"";
Debug.ShouldStop(2048);
drama.mostCurrent._star2.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆☆☆"));
 BA.debugLineNum = 141;BA.debugLine="Drama3.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(4096);
drama.mostCurrent._drama3.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 142;BA.debugLine="Star3.Text = \"☆☆☆\"";
Debug.ShouldStop(8192);
drama.mostCurrent._star3.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 143;BA.debugLine="Drama4.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(16384);
drama.mostCurrent._drama4.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 144;BA.debugLine="Star4.Text = \"☆☆☆\"";
Debug.ShouldStop(32768);
drama.mostCurrent._star4.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 145;BA.debugLine="Drama5.Text = \"Spiderman\"";
Debug.ShouldStop(65536);
drama.mostCurrent._drama5.runMethod(true,"setText",BA.ObjectToCharSequence("Spiderman"));
 BA.debugLineNum = 146;BA.debugLine="Star5.Text = \"☆☆☆☆☆\"";
Debug.ShouldStop(131072);
drama.mostCurrent._star5.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆☆☆"));
 BA.debugLineNum = 147;BA.debugLine="Drama6.Text = \"Spiderman\"";
Debug.ShouldStop(262144);
drama.mostCurrent._drama6.runMethod(true,"setText",BA.ObjectToCharSequence("Spiderman"));
 BA.debugLineNum = 148;BA.debugLine="Star6.Text = \"☆☆☆☆☆\"";
Debug.ShouldStop(524288);
drama.mostCurrent._star6.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆☆☆"));
 BA.debugLineNum = 149;BA.debugLine="Drama7.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(1048576);
drama.mostCurrent._drama7.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 150;BA.debugLine="Star7.Text = \"☆☆☆\"";
Debug.ShouldStop(2097152);
drama.mostCurrent._star7.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 151;BA.debugLine="Drama8.Text = \"Spiderman\"";
Debug.ShouldStop(4194304);
drama.mostCurrent._drama8.runMethod(true,"setText",BA.ObjectToCharSequence("Spiderman"));
 BA.debugLineNum = 152;BA.debugLine="Star8.Text = \"☆☆☆☆☆\"";
Debug.ShouldStop(8388608);
drama.mostCurrent._star8.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆☆☆"));
 BA.debugLineNum = 153;BA.debugLine="Drama9.Text = \"Avengers Dooms Day\"";
Debug.ShouldStop(16777216);
drama.mostCurrent._drama9.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers Dooms Day"));
 BA.debugLineNum = 154;BA.debugLine="Star9.Text = \"☆☆☆\"";
Debug.ShouldStop(33554432);
drama.mostCurrent._star9.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆"));
 BA.debugLineNum = 155;BA.debugLine="Drama10.Text = \"Spiderman\"";
Debug.ShouldStop(67108864);
drama.mostCurrent._drama10.runMethod(true,"setText",BA.ObjectToCharSequence("Spiderman"));
 BA.debugLineNum = 156;BA.debugLine="Star10.Text = \"☆☆☆☆☆\"";
Debug.ShouldStop(134217728);
drama.mostCurrent._star10.runMethod(true,"setText",BA.ObjectToCharSequence("☆☆☆☆☆"));
 };
 BA.debugLineNum = 158;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}