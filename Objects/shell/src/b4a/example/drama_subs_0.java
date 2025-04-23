package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class drama_subs_0 {


public static RemoteObject  _actionpage_click() throws Exception{
try {
		Debug.PushSubsStack("ActionPage_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,300);
if (RapidSub.canDelegate("actionpage_click")) { return b4a.example.drama.remoteMe.runUserSub(false, "drama","actionpage_click");}
 BA.debugLineNum = 300;BA.debugLine="Private Sub ActionPage_Click";
Debug.ShouldStop(2048);
 BA.debugLineNum = 301;BA.debugLine="StartActivity(Action)";
Debug.ShouldStop(4096);
drama.mostCurrent.__c.runVoidMethod ("StartActivity",drama.processBA,(Object)((drama.mostCurrent._action.getObject())));
 BA.debugLineNum = 302;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
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
		Debug.PushSubsStack("Activity_Create (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,112);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.drama.remoteMe.runUserSub(false, "drama","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 112;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(32768);
 BA.debugLineNum = 113;BA.debugLine="Activity.LoadLayout(\"Drama\") ' Layout contains Sc";
Debug.ShouldStop(65536);
drama.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Drama")),drama.mostCurrent.activityBA);
 BA.debugLineNum = 115;BA.debugLine="p.Initialize(\"\")";
Debug.ShouldStop(262144);
drama.mostCurrent._p.runVoidMethod ("Initialize",drama.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 116;BA.debugLine="p.LoadLayout(\"panelview\")";
Debug.ShouldStop(524288);
drama.mostCurrent._p.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("panelview")),drama.mostCurrent.activityBA);
 BA.debugLineNum = 119;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(4194304);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("avengers.jpeg"))).getObject()));
 BA.debugLineNum = 121;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
Debug.ShouldStop(16777216);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Kramer vs. Kramer"));
 BA.debugLineNum = 122;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
Debug.ShouldStop(33554432);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
 BA.debugLineNum = 123;BA.debugLine="Year1.Text = \"(1979)\"";
Debug.ShouldStop(67108864);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(1979)"));
 BA.debugLineNum = 124;BA.debugLine="OverView1.Text = \"In this emotionally charged cou";
Debug.ShouldStop(134217728);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
 BA.debugLineNum = 126;BA.debugLine="Drama2.Text = \"Manchester by the Sea\"";
Debug.ShouldStop(536870912);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Manchester by the Sea"));
 BA.debugLineNum = 127;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michell";
Debug.ShouldStop(1073741824);
drama.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
 BA.debugLineNum = 128;BA.debugLine="Year2.Text = \"(2016)\"";
Debug.ShouldStop(-2147483648);
drama.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2016)"));
 BA.debugLineNum = 129;BA.debugLine="OverView2.Text = \"After the death of his brother,";
Debug.ShouldStop(1);
drama.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
 BA.debugLineNum = 131;BA.debugLine="Drama6.Text = \"Gone Baby Gone\"";
Debug.ShouldStop(4);
drama.mostCurrent._drama6.runMethod(true,"setText",BA.ObjectToCharSequence("Gone Baby Gone"));
 BA.debugLineNum = 132;BA.debugLine="Starter6.Text = \"Starring: Casey Affleck, Michell";
Debug.ShouldStop(8);
drama.mostCurrent._starter6.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
 BA.debugLineNum = 133;BA.debugLine="Year6.Text = \"(2007)\"";
Debug.ShouldStop(16);
drama.mostCurrent._year6.runMethod(true,"setText",BA.ObjectToCharSequence("(2007)"));
 BA.debugLineNum = 134;BA.debugLine="OverView6.Text = \"In a tough Boston neighborhood,";
Debug.ShouldStop(32);
drama.mostCurrent._overview6.runMethod(true,"setText",BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
 BA.debugLineNum = 136;BA.debugLine="Drama4.Text = \"Million Dollar Baby\"";
Debug.ShouldStop(128);
drama.mostCurrent._drama4.runMethod(true,"setText",BA.ObjectToCharSequence("Million Dollar Baby"));
 BA.debugLineNum = 137;BA.debugLine="Starter4.Text = \"Starring: Morgan Freeman, Hilary";
Debug.ShouldStop(256);
drama.mostCurrent._starter4.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
 BA.debugLineNum = 138;BA.debugLine="Year4.Text = \"(2004)\"";
Debug.ShouldStop(512);
drama.mostCurrent._year4.runMethod(true,"setText",BA.ObjectToCharSequence("(2004)"));
 BA.debugLineNum = 139;BA.debugLine="OverView4.Text = \"A waitress with dreams of becom";
Debug.ShouldStop(1024);
drama.mostCurrent._overview4.runMethod(true,"setText",BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
 BA.debugLineNum = 141;BA.debugLine="Drama5.Text = \"The Bridges of Madison County\"";
Debug.ShouldStop(4096);
drama.mostCurrent._drama5.runMethod(true,"setText",BA.ObjectToCharSequence("The Bridges of Madison County"));
 BA.debugLineNum = 142;BA.debugLine="Starter5.Text = \"Starring: Clint Eastwood, Meryl";
Debug.ShouldStop(8192);
drama.mostCurrent._starter5.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
 BA.debugLineNum = 143;BA.debugLine="Year5.Text = \"(1995)\"";
Debug.ShouldStop(16384);
drama.mostCurrent._year5.runMethod(true,"setText",BA.ObjectToCharSequence("(1995)"));
 BA.debugLineNum = 144;BA.debugLine="OverView5.Text = \"A brief, passionate romance bet";
Debug.ShouldStop(32768);
drama.mostCurrent._overview5.runMethod(true,"setText",BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
 BA.debugLineNum = 146;BA.debugLine="Drama3.Text = \"The Master\"";
Debug.ShouldStop(131072);
drama.mostCurrent._drama3.runMethod(true,"setText",BA.ObjectToCharSequence("The Master"));
 BA.debugLineNum = 147;BA.debugLine="Starter3.Text = \"Starring: Philip Seymour Hoffman";
Debug.ShouldStop(262144);
drama.mostCurrent._starter3.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
 BA.debugLineNum = 148;BA.debugLine="Year3.Text = \"(2012)\"";
Debug.ShouldStop(524288);
drama.mostCurrent._year3.runMethod(true,"setText",BA.ObjectToCharSequence("(2012)"));
 BA.debugLineNum = 149;BA.debugLine="OverView3.Text = \"A mentally unstable WWII vetera";
Debug.ShouldStop(1048576);
drama.mostCurrent._overview3.runMethod(true,"setText",BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
 BA.debugLineNum = 151;BA.debugLine="Drama7.Text = \"Blue Jasmine\"";
Debug.ShouldStop(4194304);
drama.mostCurrent._drama7.runMethod(true,"setText",BA.ObjectToCharSequence("Blue Jasmine"));
 BA.debugLineNum = 152;BA.debugLine="Starter7.Text = \"Starring: Cate Blanchett, Sally";
Debug.ShouldStop(8388608);
drama.mostCurrent._starter7.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
 BA.debugLineNum = 153;BA.debugLine="Year7.Text = \"(2013)\"";
Debug.ShouldStop(16777216);
drama.mostCurrent._year7.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 154;BA.debugLine="OverView7.Text = \"After losing her fortune and st";
Debug.ShouldStop(33554432);
drama.mostCurrent._overview7.runMethod(true,"setText",BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
 BA.debugLineNum = 156;BA.debugLine="Drama8.Text = \"Her\"";
Debug.ShouldStop(134217728);
drama.mostCurrent._drama8.runMethod(true,"setText",BA.ObjectToCharSequence("Her"));
 BA.debugLineNum = 157;BA.debugLine="Starter8.Text = \"Starring: Joaquin Phoenix, Roone";
Debug.ShouldStop(268435456);
drama.mostCurrent._starter8.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
 BA.debugLineNum = 158;BA.debugLine="Year8.Text = \"(2013)\"";
Debug.ShouldStop(536870912);
drama.mostCurrent._year8.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 159;BA.debugLine="OverView8.Text = \"In a near-future Los Angeles, a";
Debug.ShouldStop(1073741824);
drama.mostCurrent._overview8.runMethod(true,"setText",BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
 BA.debugLineNum = 161;BA.debugLine="Drama9.Text = \"Carol\"";
Debug.ShouldStop(1);
drama.mostCurrent._drama9.runMethod(true,"setText",BA.ObjectToCharSequence("Carol"));
 BA.debugLineNum = 162;BA.debugLine="Starter9.Text = \"Starring: Rooney Mara, Cate Blan";
Debug.ShouldStop(2);
drama.mostCurrent._starter9.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
 BA.debugLineNum = 163;BA.debugLine="Year9.Text = \"(2015)\"";
Debug.ShouldStop(4);
drama.mostCurrent._year9.runMethod(true,"setText",BA.ObjectToCharSequence("(2015)"));
 BA.debugLineNum = 164;BA.debugLine="OverView9.Text = \"A chance encounter between a yo";
Debug.ShouldStop(8);
drama.mostCurrent._overview9.runMethod(true,"setText",BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
 BA.debugLineNum = 166;BA.debugLine="Drama10.Text = \"The Lost Daughter\"";
Debug.ShouldStop(32);
drama.mostCurrent._drama10.runMethod(true,"setText",BA.ObjectToCharSequence("The Lost Daughter"));
 BA.debugLineNum = 167;BA.debugLine="Starter10.Text = \"Starring: Olivia Colman, Dakota";
Debug.ShouldStop(64);
drama.mostCurrent._starter10.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Olivia Colman, Dakota Johnson, Jessie Buckley"));
 BA.debugLineNum = 168;BA.debugLine="Year10.Text = \"(2021)\"";
Debug.ShouldStop(128);
drama.mostCurrent._year10.runMethod(true,"setText",BA.ObjectToCharSequence("(2021)"));
 BA.debugLineNum = 169;BA.debugLine="OverView10.Text = \"A solitary woman on vacation b";
Debug.ShouldStop(256);
drama.mostCurrent._overview10.runMethod(true,"setText",BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity"));
 BA.debugLineNum = 171;BA.debugLine="ScrollView1.Panel.AddView(p, 0, 0, 200%x, 210%y)";
Debug.ShouldStop(1024);
drama.mostCurrent._scrollview1.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((drama.mostCurrent._p.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(drama.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 200)),drama.mostCurrent.activityBA)),(Object)(drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 210)),drama.mostCurrent.activityBA)));
 BA.debugLineNum = 172;BA.debugLine="ScrollView1.Panel.Height = p.Height";
Debug.ShouldStop(2048);
drama.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",drama.mostCurrent._p.runMethod(true,"getHeight"));
 BA.debugLineNum = 173;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
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
		Debug.PushSubsStack("DramaPage_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,296);
if (RapidSub.canDelegate("dramapage_click")) { return b4a.example.drama.remoteMe.runUserSub(false, "drama","dramapage_click");}
 BA.debugLineNum = 296;BA.debugLine="Private Sub DramaPage_Click";
Debug.ShouldStop(128);
 BA.debugLineNum = 298;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 11;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 12;BA.debugLine="Private ScrollView1 As ScrollView";
drama.mostCurrent._scrollview1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");
 //BA.debugLineNum = 15;BA.debugLine="Private Drama1 As Label";
drama.mostCurrent._drama1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
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
 //BA.debugLineNum = 24;BA.debugLine="Private Drama10 As Label";
drama.mostCurrent._drama10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 29;BA.debugLine="Private DramaImage1 As ImageView";
drama.mostCurrent._dramaimage1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 30;BA.debugLine="Private DramaImage2 As ImageView";
drama.mostCurrent._dramaimage2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 31;BA.debugLine="Private DramaImage3 As ImageView";
drama.mostCurrent._dramaimage3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 32;BA.debugLine="Private DramaImage4 As ImageView";
drama.mostCurrent._dramaimage4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 33;BA.debugLine="Private DramaImage5 As ImageView";
drama.mostCurrent._dramaimage5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 34;BA.debugLine="Private DramaImage6 As ImageView";
drama.mostCurrent._dramaimage6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 35;BA.debugLine="Private DramaImage7 As ImageView";
drama.mostCurrent._dramaimage7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 36;BA.debugLine="Private DramaImage8 As ImageView";
drama.mostCurrent._dramaimage8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 37;BA.debugLine="Private DramaImage9 As ImageView";
drama.mostCurrent._dramaimage9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 38;BA.debugLine="Private DramaImage10 As ImageView";
drama.mostCurrent._dramaimage10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 43;BA.debugLine="Private SearchBtn As Button";
drama.mostCurrent._searchbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 44;BA.debugLine="Private SearchEngine As EditText";
drama.mostCurrent._searchengine = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 47;BA.debugLine="Dim p As Panel";
drama.mostCurrent._p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 48;BA.debugLine="Private Panel2 As Panel";
drama.mostCurrent._panel2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 49;BA.debugLine="Private Panel3 As Panel";
drama.mostCurrent._panel3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 50;BA.debugLine="Private Panel4 As Panel";
drama.mostCurrent._panel4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 51;BA.debugLine="Private Panel5 As Panel";
drama.mostCurrent._panel5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 52;BA.debugLine="Private Panel6 As Panel";
drama.mostCurrent._panel6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 53;BA.debugLine="Private Panel7 As Panel";
drama.mostCurrent._panel7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 54;BA.debugLine="Private Panel8 As Panel";
drama.mostCurrent._panel8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 55;BA.debugLine="Private Panel9 As Panel";
drama.mostCurrent._panel9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 56;BA.debugLine="Private Panel10 As Panel";
drama.mostCurrent._panel10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 57;BA.debugLine="Private Panel11 As Panel";
drama.mostCurrent._panel11 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 62;BA.debugLine="Private DramaPage As Label";
drama.mostCurrent._dramapage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 63;BA.debugLine="Private HomePage As Label";
drama.mostCurrent._homepage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 64;BA.debugLine="Private SciFiPage As Label";
drama.mostCurrent._scifipage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 65;BA.debugLine="Private ActionPage As Label";
drama.mostCurrent._actionpage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 68;BA.debugLine="Private Starter1 As Label";
drama.mostCurrent._starter1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 69;BA.debugLine="Private Starter2 As Label";
drama.mostCurrent._starter2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 70;BA.debugLine="Private Starter3 As Label";
drama.mostCurrent._starter3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 71;BA.debugLine="Private Starter4 As Label";
drama.mostCurrent._starter4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 72;BA.debugLine="Private Starter5 As Label";
drama.mostCurrent._starter5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 73;BA.debugLine="Private Starter6 As Label";
drama.mostCurrent._starter6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 74;BA.debugLine="Private Starter7 As Label";
drama.mostCurrent._starter7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 75;BA.debugLine="Private Starter8 As Label";
drama.mostCurrent._starter8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 76;BA.debugLine="Private Starter9 As Label";
drama.mostCurrent._starter9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 77;BA.debugLine="Private Starter10 As Label";
drama.mostCurrent._starter10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 80;BA.debugLine="Private OverView1 As Label";
drama.mostCurrent._overview1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 81;BA.debugLine="Private OverView2 As Label";
drama.mostCurrent._overview2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 82;BA.debugLine="Private OverView3 As Label";
drama.mostCurrent._overview3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 83;BA.debugLine="Private OverView4 As Label";
drama.mostCurrent._overview4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 84;BA.debugLine="Private OverView5 As Label";
drama.mostCurrent._overview5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 85;BA.debugLine="Private OverView6 As Label";
drama.mostCurrent._overview6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 86;BA.debugLine="Private OverView7 As Label";
drama.mostCurrent._overview7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 87;BA.debugLine="Private OverView8 As Label";
drama.mostCurrent._overview8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 88;BA.debugLine="Private OverView9 As Label";
drama.mostCurrent._overview9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 89;BA.debugLine="Private OverView10 As Label";
drama.mostCurrent._overview10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 91;BA.debugLine="Private Year1 As Label";
drama.mostCurrent._year1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 92;BA.debugLine="Private Year2 As Label";
drama.mostCurrent._year2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 93;BA.debugLine="Private Year3 As Label";
drama.mostCurrent._year3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 94;BA.debugLine="Private Year4 As Label";
drama.mostCurrent._year4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 95;BA.debugLine="Private Year5 As Label";
drama.mostCurrent._year5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 96;BA.debugLine="Private Year6 As Label";
drama.mostCurrent._year6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 97;BA.debugLine="Private Year7 As Label";
drama.mostCurrent._year7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 98;BA.debugLine="Private Year8 As Label";
drama.mostCurrent._year8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 99;BA.debugLine="Private Year9 As Label";
drama.mostCurrent._year9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 100;BA.debugLine="Private Year10 As Label";
drama.mostCurrent._year10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _homepage_click() throws Exception{
try {
		Debug.PushSubsStack("HomePage_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,292);
if (RapidSub.canDelegate("homepage_click")) { return b4a.example.drama.remoteMe.runUserSub(false, "drama","homepage_click");}
 BA.debugLineNum = 292;BA.debugLine="Private Sub HomePage_Click";
Debug.ShouldStop(8);
 BA.debugLineNum = 293;BA.debugLine="StartActivity(Main)";
Debug.ShouldStop(16);
drama.mostCurrent.__c.runVoidMethod ("StartActivity",drama.processBA,(Object)((drama.mostCurrent._main.getObject())));
 BA.debugLineNum = 294;BA.debugLine="End Sub";
Debug.ShouldStop(32);
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
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _scifipage_click() throws Exception{
try {
		Debug.PushSubsStack("SciFiPage_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,288);
if (RapidSub.canDelegate("scifipage_click")) { return b4a.example.drama.remoteMe.runUserSub(false, "drama","scifipage_click");}
 BA.debugLineNum = 288;BA.debugLine="Private Sub SciFiPage_Click";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 289;BA.debugLine="StartActivity(SciFi)";
Debug.ShouldStop(1);
drama.mostCurrent.__c.runVoidMethod ("StartActivity",drama.processBA,(Object)((drama.mostCurrent._scifi.getObject())));
 BA.debugLineNum = 290;BA.debugLine="End Sub";
Debug.ShouldStop(2);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _searchbtn_click() throws Exception{
try {
		Debug.PushSubsStack("SearchBtn_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,175);
if (RapidSub.canDelegate("searchbtn_click")) { return b4a.example.drama.remoteMe.runUserSub(false, "drama","searchbtn_click");}
RemoteObject _query = RemoteObject.createImmutable("");
RemoteObject _userinput = RemoteObject.createImmutable("");
 BA.debugLineNum = 175;BA.debugLine="Private Sub SearchBtn_Click";
Debug.ShouldStop(16384);
 BA.debugLineNum = 176;BA.debugLine="Dim query As String = SearchEngine.Text.ToLowerCa";
Debug.ShouldStop(32768);
_query = drama.mostCurrent._searchengine.runMethod(true,"getText").runMethod(true,"toLowerCase").runMethod(true,"trim");Debug.locals.put("query", _query);Debug.locals.put("query", _query);
 BA.debugLineNum = 177;BA.debugLine="Dim userInput As String = SearchEngine.Text";
Debug.ShouldStop(65536);
_userinput = drama.mostCurrent._searchengine.runMethod(true,"getText");Debug.locals.put("userInput", _userinput);Debug.locals.put("userInput", _userinput);
 BA.debugLineNum = 179;BA.debugLine="If query.Contains(\"meryl streep\") Or query.Contai";
Debug.ShouldStop(262144);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("meryl streep")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("meryl"))))) { 
 BA.debugLineNum = 181;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
Debug.ShouldStop(1048576);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Kramer vs. Kramer"));
 BA.debugLineNum = 182;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
Debug.ShouldStop(2097152);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
 BA.debugLineNum = 183;BA.debugLine="Year1.Text = \"(1979)\"";
Debug.ShouldStop(4194304);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(1979)"));
 BA.debugLineNum = 184;BA.debugLine="OverView1.Text = \"In this emotionally charged co";
Debug.ShouldStop(8388608);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
 BA.debugLineNum = 186;BA.debugLine="Drama2.Text = \"The Bridges of Madison County\"";
Debug.ShouldStop(33554432);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("The Bridges of Madison County"));
 BA.debugLineNum = 187;BA.debugLine="Starter2.Text = \"Starring: Clint Eastwood, Meryl";
Debug.ShouldStop(67108864);
drama.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
 BA.debugLineNum = 188;BA.debugLine="Year2.Text = \"(1995)\"";
Debug.ShouldStop(134217728);
drama.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(1995)"));
 BA.debugLineNum = 189;BA.debugLine="OverView2.Text = \"A brief, passionate romance be";
Debug.ShouldStop(268435456);
drama.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
 BA.debugLineNum = 191;BA.debugLine="Panel4.Visible = False";
Debug.ShouldStop(1073741824);
drama.mostCurrent._panel4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 192;BA.debugLine="Panel5.Visible = False";
Debug.ShouldStop(-2147483648);
drama.mostCurrent._panel5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 193;BA.debugLine="p.Height = 85%y";
Debug.ShouldStop(1);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 85)),drama.mostCurrent.activityBA));
 BA.debugLineNum = 194;BA.debugLine="p.Width = 100%x";
Debug.ShouldStop(2);
drama.mostCurrent._p.runMethod(true,"setWidth",drama.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),drama.mostCurrent.activityBA));
 BA.debugLineNum = 195;BA.debugLine="ScrollView1.Panel.Height = p.Height";
Debug.ShouldStop(4);
drama.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",drama.mostCurrent._p.runMethod(true,"getHeight"));
 }else 
{ BA.debugLineNum = 197;BA.debugLine="Else If query.Contains(\"casey\") Or query.Contain";
Debug.ShouldStop(16);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("casey")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("Casey affleck"))))) { 
 BA.debugLineNum = 198;BA.debugLine="Drama1.Text = \"Gone Baby Gone\"";
Debug.ShouldStop(32);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Gone Baby Gone"));
 BA.debugLineNum = 199;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Michel";
Debug.ShouldStop(64);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
 BA.debugLineNum = 200;BA.debugLine="Year1.Text = \"(2007)\"";
Debug.ShouldStop(128);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2007)"));
 BA.debugLineNum = 201;BA.debugLine="OverView1.Text = \"In a tough Boston neighborhood";
Debug.ShouldStop(256);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
 BA.debugLineNum = 203;BA.debugLine="Drama2.Text = \"Manchester by the Sea\"";
Debug.ShouldStop(1024);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Manchester by the Sea"));
 BA.debugLineNum = 204;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michel";
Debug.ShouldStop(2048);
drama.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
 BA.debugLineNum = 205;BA.debugLine="Year2.Text = \"(2016)\"";
Debug.ShouldStop(4096);
drama.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2016)"));
 BA.debugLineNum = 206;BA.debugLine="OverView2.Text = \"After the death of his brother";
Debug.ShouldStop(8192);
drama.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
 BA.debugLineNum = 208;BA.debugLine="Panel4.Visible = False";
Debug.ShouldStop(32768);
drama.mostCurrent._panel4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 209;BA.debugLine="Panel5.Visible = False";
Debug.ShouldStop(65536);
drama.mostCurrent._panel5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 210;BA.debugLine="p.Height = 85%y";
Debug.ShouldStop(131072);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 85)),drama.mostCurrent.activityBA));
 BA.debugLineNum = 211;BA.debugLine="p.Width = 100%x";
Debug.ShouldStop(262144);
drama.mostCurrent._p.runMethod(true,"setWidth",drama.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),drama.mostCurrent.activityBA));
 BA.debugLineNum = 212;BA.debugLine="ScrollView1.Panel.Height = p.Height";
Debug.ShouldStop(524288);
drama.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",drama.mostCurrent._p.runMethod(true,"getHeight"));
 }else {
 BA.debugLineNum = 214;BA.debugLine="MsgboxAsync(userInput, \"Not Found!\")";
Debug.ShouldStop(2097152);
drama.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence(_userinput)),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Not Found!"))),drama.processBA);
 }}
;
 BA.debugLineNum = 216;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
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
		Debug.PushSubsStack("SearchEngine_TextChanged (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,218);
if (RapidSub.canDelegate("searchengine_textchanged")) { return b4a.example.drama.remoteMe.runUserSub(false, "drama","searchengine_textchanged", _old, _new);}
RemoteObject _query = RemoteObject.createImmutable("");
Debug.locals.put("Old", _old);
Debug.locals.put("New", _new);
 BA.debugLineNum = 218;BA.debugLine="Sub SearchEngine_TextChanged (Old As String, New A";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 219;BA.debugLine="Dim query As String = New.ToLowerCase.Trim";
Debug.ShouldStop(67108864);
_query = _new.runMethod(true,"toLowerCase").runMethod(true,"trim");Debug.locals.put("query", _query);Debug.locals.put("query", _query);
 BA.debugLineNum = 221;BA.debugLine="If query = \"\" Then";
Debug.ShouldStop(268435456);
if (RemoteObject.solveBoolean("=",_query,BA.ObjectToString(""))) { 
 BA.debugLineNum = 224;BA.debugLine="p.Height = 210%y";
Debug.ShouldStop(-2147483648);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 210)),drama.mostCurrent.activityBA));
 BA.debugLineNum = 225;BA.debugLine="p.Width = 200%x";
Debug.ShouldStop(1);
drama.mostCurrent._p.runMethod(true,"setWidth",drama.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 200)),drama.mostCurrent.activityBA));
 BA.debugLineNum = 226;BA.debugLine="ScrollView1.Panel.Height = p.Height";
Debug.ShouldStop(2);
drama.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",drama.mostCurrent._p.runMethod(true,"getHeight"));
 BA.debugLineNum = 228;BA.debugLine="Panel4.Visible = True";
Debug.ShouldStop(8);
drama.mostCurrent._panel4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 229;BA.debugLine="Panel5.Visible = True";
Debug.ShouldStop(16);
drama.mostCurrent._panel5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 231;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
Debug.ShouldStop(64);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Kramer vs. Kramer"));
 BA.debugLineNum = 232;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
Debug.ShouldStop(128);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
 BA.debugLineNum = 233;BA.debugLine="Year1.Text = \"(1979)\"";
Debug.ShouldStop(256);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(1979)"));
 BA.debugLineNum = 234;BA.debugLine="OverView1.Text = \"In this emotionally charged co";
Debug.ShouldStop(512);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
 BA.debugLineNum = 236;BA.debugLine="Drama2.Text = \"Manchester by the Sea\"";
Debug.ShouldStop(2048);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Manchester by the Sea"));
 BA.debugLineNum = 237;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michel";
Debug.ShouldStop(4096);
drama.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
 BA.debugLineNum = 238;BA.debugLine="Year2.Text = \"(2016)\"";
Debug.ShouldStop(8192);
drama.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2016)"));
 BA.debugLineNum = 239;BA.debugLine="OverView2.Text = \"After the death of his brother";
Debug.ShouldStop(16384);
drama.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
 BA.debugLineNum = 241;BA.debugLine="Drama6.Text = \"Gone Baby Gone\"";
Debug.ShouldStop(65536);
drama.mostCurrent._drama6.runMethod(true,"setText",BA.ObjectToCharSequence("Gone Baby Gone"));
 BA.debugLineNum = 242;BA.debugLine="Starter6.Text = \"Starring: Casey Affleck, Michel";
Debug.ShouldStop(131072);
drama.mostCurrent._starter6.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
 BA.debugLineNum = 243;BA.debugLine="Year6.Text = \"(2007)\"";
Debug.ShouldStop(262144);
drama.mostCurrent._year6.runMethod(true,"setText",BA.ObjectToCharSequence("(2007)"));
 BA.debugLineNum = 244;BA.debugLine="OverView6.Text = \"In a tough Boston neighborhood";
Debug.ShouldStop(524288);
drama.mostCurrent._overview6.runMethod(true,"setText",BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
 BA.debugLineNum = 246;BA.debugLine="Drama4.Text = \"Million Dollar Baby\"";
Debug.ShouldStop(2097152);
drama.mostCurrent._drama4.runMethod(true,"setText",BA.ObjectToCharSequence("Million Dollar Baby"));
 BA.debugLineNum = 247;BA.debugLine="Starter4.Text = \"Starring: Morgan Freeman, Hilar";
Debug.ShouldStop(4194304);
drama.mostCurrent._starter4.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
 BA.debugLineNum = 248;BA.debugLine="Year4.Text = \"(2004)\"";
Debug.ShouldStop(8388608);
drama.mostCurrent._year4.runMethod(true,"setText",BA.ObjectToCharSequence("(2004)"));
 BA.debugLineNum = 249;BA.debugLine="OverView4.Text = \"A waitress with dreams of beco";
Debug.ShouldStop(16777216);
drama.mostCurrent._overview4.runMethod(true,"setText",BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
 BA.debugLineNum = 251;BA.debugLine="Drama5.Text = \"The Bridges of Madison County\"";
Debug.ShouldStop(67108864);
drama.mostCurrent._drama5.runMethod(true,"setText",BA.ObjectToCharSequence("The Bridges of Madison County"));
 BA.debugLineNum = 252;BA.debugLine="Starter5.Text = \"Starring: Clint Eastwood, Meryl";
Debug.ShouldStop(134217728);
drama.mostCurrent._starter5.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
 BA.debugLineNum = 253;BA.debugLine="Year5.Text = \"(1995)\"";
Debug.ShouldStop(268435456);
drama.mostCurrent._year5.runMethod(true,"setText",BA.ObjectToCharSequence("(1995)"));
 BA.debugLineNum = 254;BA.debugLine="OverView5.Text = \"A brief, passionate romance be";
Debug.ShouldStop(536870912);
drama.mostCurrent._overview5.runMethod(true,"setText",BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
 BA.debugLineNum = 256;BA.debugLine="Drama3.Text = \"The Master\"";
Debug.ShouldStop(-2147483648);
drama.mostCurrent._drama3.runMethod(true,"setText",BA.ObjectToCharSequence("The Master"));
 BA.debugLineNum = 257;BA.debugLine="Starter3.Text = \"Starring: Philip Seymour Hoffma";
Debug.ShouldStop(1);
drama.mostCurrent._starter3.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
 BA.debugLineNum = 258;BA.debugLine="Year3.Text = \"(2012)\"";
Debug.ShouldStop(2);
drama.mostCurrent._year3.runMethod(true,"setText",BA.ObjectToCharSequence("(2012)"));
 BA.debugLineNum = 259;BA.debugLine="OverView3.Text = \"A mentally unstable WWII veter";
Debug.ShouldStop(4);
drama.mostCurrent._overview3.runMethod(true,"setText",BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
 BA.debugLineNum = 261;BA.debugLine="Drama7.Text = \"Blue Jasmine\"";
Debug.ShouldStop(16);
drama.mostCurrent._drama7.runMethod(true,"setText",BA.ObjectToCharSequence("Blue Jasmine"));
 BA.debugLineNum = 262;BA.debugLine="Starter7.Text = \"Starring: Cate Blanchett, Sally";
Debug.ShouldStop(32);
drama.mostCurrent._starter7.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
 BA.debugLineNum = 263;BA.debugLine="Year7.Text = \"(2013)\"";
Debug.ShouldStop(64);
drama.mostCurrent._year7.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 264;BA.debugLine="OverView7.Text = \"After losing her fortune and s";
Debug.ShouldStop(128);
drama.mostCurrent._overview7.runMethod(true,"setText",BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
 BA.debugLineNum = 266;BA.debugLine="Drama8.Text = \"Her\"";
Debug.ShouldStop(512);
drama.mostCurrent._drama8.runMethod(true,"setText",BA.ObjectToCharSequence("Her"));
 BA.debugLineNum = 267;BA.debugLine="Starter8.Text = \"Starring: Joaquin Phoenix, Roon";
Debug.ShouldStop(1024);
drama.mostCurrent._starter8.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
 BA.debugLineNum = 268;BA.debugLine="Year8.Text = \"(2013)\"";
Debug.ShouldStop(2048);
drama.mostCurrent._year8.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 269;BA.debugLine="OverView8.Text = \"In a near-future Los Angeles,";
Debug.ShouldStop(4096);
drama.mostCurrent._overview8.runMethod(true,"setText",BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
 BA.debugLineNum = 271;BA.debugLine="Drama9.Text = \"Carol\"";
Debug.ShouldStop(16384);
drama.mostCurrent._drama9.runMethod(true,"setText",BA.ObjectToCharSequence("Carol"));
 BA.debugLineNum = 272;BA.debugLine="Starter9.Text = \"Starring: Rooney Mara, Cate Bla";
Debug.ShouldStop(32768);
drama.mostCurrent._starter9.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
 BA.debugLineNum = 273;BA.debugLine="Year9.Text = \"(2015)\"";
Debug.ShouldStop(65536);
drama.mostCurrent._year9.runMethod(true,"setText",BA.ObjectToCharSequence("(2015)"));
 BA.debugLineNum = 274;BA.debugLine="OverView9.Text = \"A chance encounter between a y";
Debug.ShouldStop(131072);
drama.mostCurrent._overview9.runMethod(true,"setText",BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
 BA.debugLineNum = 276;BA.debugLine="Drama10.Text = \"The Lost Daughter\"";
Debug.ShouldStop(524288);
drama.mostCurrent._drama10.runMethod(true,"setText",BA.ObjectToCharSequence("The Lost Daughter"));
 BA.debugLineNum = 277;BA.debugLine="Starter10.Text = \"Starring: Olivia Colman, Dakot";
Debug.ShouldStop(1048576);
drama.mostCurrent._starter10.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Olivia Colman, Dakota Johnson, Jessie Buckley"));
 BA.debugLineNum = 278;BA.debugLine="Year10.Text = \"(2021)\"";
Debug.ShouldStop(2097152);
drama.mostCurrent._year10.runMethod(true,"setText",BA.ObjectToCharSequence("(2021)"));
 BA.debugLineNum = 279;BA.debugLine="OverView10.Text = \"A solitary woman on vacation";
Debug.ShouldStop(4194304);
drama.mostCurrent._overview10.runMethod(true,"setText",BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity"));
 };
 BA.debugLineNum = 285;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}