package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class drama_subs_0 {


public static RemoteObject  _actionpage_click() throws Exception{
try {
		Debug.PushSubsStack("ActionPage_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,798);
if (RapidSub.canDelegate("actionpage_click")) { return b4a.example.drama.remoteMe.runUserSub(false, "drama","actionpage_click");}
 BA.debugLineNum = 798;BA.debugLine="Private Sub ActionPage_Click";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 799;BA.debugLine="StartActivity(Action)";
Debug.ShouldStop(1073741824);
drama.mostCurrent.__c.runVoidMethod ("StartActivity",drama.processBA,(Object)((drama.mostCurrent._action.getObject())));
 BA.debugLineNum = 800;BA.debugLine="Activity.Finish";
Debug.ShouldStop(-2147483648);
drama.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 801;BA.debugLine="End Sub";
Debug.ShouldStop(1);
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
		Debug.PushSubsStack("Activity_Create (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,106);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.drama.remoteMe.runUserSub(false, "drama","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 106;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(512);
 BA.debugLineNum = 107;BA.debugLine="Activity.LoadLayout(\"Drama\") ' Layout contains Sc";
Debug.ShouldStop(1024);
drama.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("Drama")),drama.mostCurrent.activityBA);
 BA.debugLineNum = 109;BA.debugLine="p.Initialize(\"\")";
Debug.ShouldStop(4096);
drama.mostCurrent._p.runVoidMethod ("Initialize",drama.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 110;BA.debugLine="p.LoadLayout(\"panelview\")";
Debug.ShouldStop(8192);
drama.mostCurrent._p.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("panelview")),drama.mostCurrent.activityBA);
 BA.debugLineNum = 117;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
Debug.ShouldStop(1048576);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Kramer vs. Kramer"));
 BA.debugLineNum = 118;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
Debug.ShouldStop(2097152);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
 BA.debugLineNum = 119;BA.debugLine="Year1.Text = \"(1979)\"";
Debug.ShouldStop(4194304);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(1979)"));
 BA.debugLineNum = 120;BA.debugLine="OverView1.Text = \"In this emotionally charged cou";
Debug.ShouldStop(8388608);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
 BA.debugLineNum = 121;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(16777216);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 122;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(33554432);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("kramer.jpg"))).getObject()));
 BA.debugLineNum = 124;BA.debugLine="Drama2.Text = \"Manchester by the Sea\"";
Debug.ShouldStop(134217728);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Manchester by the Sea"));
 BA.debugLineNum = 125;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michell";
Debug.ShouldStop(268435456);
drama.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
 BA.debugLineNum = 126;BA.debugLine="Year2.Text = \"(2016)\"";
Debug.ShouldStop(536870912);
drama.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2016)"));
 BA.debugLineNum = 127;BA.debugLine="OverView2.Text = \"After the death of his brother,";
Debug.ShouldStop(1073741824);
drama.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
 BA.debugLineNum = 128;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(-2147483648);
drama.mostCurrent._dramaimage2.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 129;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(1);
drama.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("manchester.jpg"))).getObject()));
 BA.debugLineNum = 131;BA.debugLine="Drama3.Text = \"The Master\"";
Debug.ShouldStop(4);
drama.mostCurrent._drama3.runMethod(true,"setText",BA.ObjectToCharSequence("The Master"));
 BA.debugLineNum = 132;BA.debugLine="Starter3.Text = \"Starring: Philip Seymour Hoffman";
Debug.ShouldStop(8);
drama.mostCurrent._starter3.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
 BA.debugLineNum = 133;BA.debugLine="Year3.Text = \"(2012)\"";
Debug.ShouldStop(16);
drama.mostCurrent._year3.runMethod(true,"setText",BA.ObjectToCharSequence("(2012)"));
 BA.debugLineNum = 134;BA.debugLine="OverView3.Text = \"A mentally unstable WWII vetera";
Debug.ShouldStop(32);
drama.mostCurrent._overview3.runMethod(true,"setText",BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
 BA.debugLineNum = 135;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
Debug.ShouldStop(64);
drama.mostCurrent._dramaimage3.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 136;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(128);
drama.mostCurrent._dramaimage3.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("master.jpg"))).getObject()));
 BA.debugLineNum = 138;BA.debugLine="Drama4.Text = \"Million Dollar Baby\"";
Debug.ShouldStop(512);
drama.mostCurrent._drama4.runMethod(true,"setText",BA.ObjectToCharSequence("Million Dollar Baby"));
 BA.debugLineNum = 139;BA.debugLine="Starter4.Text = \"Starring: Morgan Freeman, Hilary";
Debug.ShouldStop(1024);
drama.mostCurrent._starter4.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
 BA.debugLineNum = 140;BA.debugLine="Year4.Text = \"(2004)\"";
Debug.ShouldStop(2048);
drama.mostCurrent._year4.runMethod(true,"setText",BA.ObjectToCharSequence("(2004)"));
 BA.debugLineNum = 141;BA.debugLine="OverView4.Text = \"A waitress with dreams of becom";
Debug.ShouldStop(4096);
drama.mostCurrent._overview4.runMethod(true,"setText",BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
 BA.debugLineNum = 142;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
Debug.ShouldStop(8192);
drama.mostCurrent._dramaimage4.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 143;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(16384);
drama.mostCurrent._dramaimage4.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("millondolar.jpg"))).getObject()));
 BA.debugLineNum = 145;BA.debugLine="Drama5.Text = \"The Bridges of Madison County\"";
Debug.ShouldStop(65536);
drama.mostCurrent._drama5.runMethod(true,"setText",BA.ObjectToCharSequence("The Bridges of Madison County"));
 BA.debugLineNum = 146;BA.debugLine="Starter5.Text = \"Starring: Clint Eastwood, Meryl";
Debug.ShouldStop(131072);
drama.mostCurrent._starter5.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
 BA.debugLineNum = 147;BA.debugLine="Year5.Text = \"(1995)\"";
Debug.ShouldStop(262144);
drama.mostCurrent._year5.runMethod(true,"setText",BA.ObjectToCharSequence("(1995)"));
 BA.debugLineNum = 148;BA.debugLine="OverView5.Text = \"A brief, passionate romance bet";
Debug.ShouldStop(524288);
drama.mostCurrent._overview5.runMethod(true,"setText",BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
 BA.debugLineNum = 149;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
Debug.ShouldStop(1048576);
drama.mostCurrent._dramaimage5.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 150;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(2097152);
drama.mostCurrent._dramaimage5.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("bridges.jpg"))).getObject()));
 BA.debugLineNum = 153;BA.debugLine="Drama6.Text = \"Gone Baby Gone\"";
Debug.ShouldStop(16777216);
drama.mostCurrent._drama6.runMethod(true,"setText",BA.ObjectToCharSequence("Gone Baby Gone"));
 BA.debugLineNum = 154;BA.debugLine="Starter6.Text = \"Starring: Casey Affleck, Michell";
Debug.ShouldStop(33554432);
drama.mostCurrent._starter6.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
 BA.debugLineNum = 155;BA.debugLine="Year6.Text = \"(2007)\"";
Debug.ShouldStop(67108864);
drama.mostCurrent._year6.runMethod(true,"setText",BA.ObjectToCharSequence("(2007)"));
 BA.debugLineNum = 156;BA.debugLine="OverView6.Text = \"In a tough Boston neighborhood,";
Debug.ShouldStop(134217728);
drama.mostCurrent._overview6.runMethod(true,"setText",BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
 BA.debugLineNum = 157;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
Debug.ShouldStop(268435456);
drama.mostCurrent._dramaimage6.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 158;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(536870912);
drama.mostCurrent._dramaimage6.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("gonebaby.jpg"))).getObject()));
 BA.debugLineNum = 160;BA.debugLine="Drama7.Text = \"Blue Jasmine\"";
Debug.ShouldStop(-2147483648);
drama.mostCurrent._drama7.runMethod(true,"setText",BA.ObjectToCharSequence("Blue Jasmine"));
 BA.debugLineNum = 161;BA.debugLine="Starter7.Text = \"Starring: Cate Blanchett, Sally";
Debug.ShouldStop(1);
drama.mostCurrent._starter7.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
 BA.debugLineNum = 162;BA.debugLine="Year7.Text = \"(2013)\"";
Debug.ShouldStop(2);
drama.mostCurrent._year7.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 163;BA.debugLine="OverView7.Text = \"After losing her fortune and st";
Debug.ShouldStop(4);
drama.mostCurrent._overview7.runMethod(true,"setText",BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
 BA.debugLineNum = 164;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
Debug.ShouldStop(8);
drama.mostCurrent._dramaimage7.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 165;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(16);
drama.mostCurrent._dramaimage7.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("bluejasmine.jpg"))).getObject()));
 BA.debugLineNum = 167;BA.debugLine="Drama8.Text = \"Her\"";
Debug.ShouldStop(64);
drama.mostCurrent._drama8.runMethod(true,"setText",BA.ObjectToCharSequence("Her"));
 BA.debugLineNum = 168;BA.debugLine="Starter8.Text = \"Starring: Joaquin Phoenix, Roone";
Debug.ShouldStop(128);
drama.mostCurrent._starter8.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
 BA.debugLineNum = 169;BA.debugLine="Year8.Text = \"(2013)\"";
Debug.ShouldStop(256);
drama.mostCurrent._year8.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 170;BA.debugLine="OverView8.Text = \"In a near-future Los Angeles, a";
Debug.ShouldStop(512);
drama.mostCurrent._overview8.runMethod(true,"setText",BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
 BA.debugLineNum = 171;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
Debug.ShouldStop(1024);
drama.mostCurrent._dramaimage8.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 172;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(2048);
drama.mostCurrent._dramaimage8.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("her.jpg"))).getObject()));
 BA.debugLineNum = 174;BA.debugLine="Drama9.Text = \"Carol\"";
Debug.ShouldStop(8192);
drama.mostCurrent._drama9.runMethod(true,"setText",BA.ObjectToCharSequence("Carol"));
 BA.debugLineNum = 175;BA.debugLine="Starter9.Text = \"Starring: Rooney Mara, Cate Blan";
Debug.ShouldStop(16384);
drama.mostCurrent._starter9.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
 BA.debugLineNum = 176;BA.debugLine="Year9.Text = \"(2015)\"";
Debug.ShouldStop(32768);
drama.mostCurrent._year9.runMethod(true,"setText",BA.ObjectToCharSequence("(2015)"));
 BA.debugLineNum = 177;BA.debugLine="OverView9.Text = \"A chance encounter between a yo";
Debug.ShouldStop(65536);
drama.mostCurrent._overview9.runMethod(true,"setText",BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
 BA.debugLineNum = 178;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
Debug.ShouldStop(131072);
drama.mostCurrent._dramaimage9.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 179;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(262144);
drama.mostCurrent._dramaimage9.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("carol.jpg"))).getObject()));
 BA.debugLineNum = 181;BA.debugLine="Drama10.Text = \"The Lost Daughter\"";
Debug.ShouldStop(1048576);
drama.mostCurrent._drama10.runMethod(true,"setText",BA.ObjectToCharSequence("The Lost Daughter"));
 BA.debugLineNum = 182;BA.debugLine="Starter10.Text = \"Starring: Olivia Colman, Dakota";
Debug.ShouldStop(2097152);
drama.mostCurrent._starter10.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Olivia Colman, Dakota Johnson, Jessie Buckley"));
 BA.debugLineNum = 183;BA.debugLine="Year10.Text = \"(2021)\"";
Debug.ShouldStop(4194304);
drama.mostCurrent._year10.runMethod(true,"setText",BA.ObjectToCharSequence("(2021)"));
 BA.debugLineNum = 184;BA.debugLine="OverView10.Text = \"A solitary woman on vacation b";
Debug.ShouldStop(8388608);
drama.mostCurrent._overview10.runMethod(true,"setText",BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity"));
 BA.debugLineNum = 185;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
Debug.ShouldStop(16777216);
drama.mostCurrent._dramaimage10.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 186;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(33554432);
drama.mostCurrent._dramaimage10.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("lostdaughter.jpg"))).getObject()));
 BA.debugLineNum = 188;BA.debugLine="ScrollView1.Panel.AddView(p, 0, 0, 200%x, 210%y)";
Debug.ShouldStop(134217728);
drama.mostCurrent._scrollview1.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((drama.mostCurrent._p.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(drama.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 200)),drama.mostCurrent.activityBA)),(Object)(drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 210)),drama.mostCurrent.activityBA)));
 BA.debugLineNum = 189;BA.debugLine="ScrollView1.Panel.Height = p.Height";
Debug.ShouldStop(268435456);
drama.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",drama.mostCurrent._p.runMethod(true,"getHeight"));
 BA.debugLineNum = 190;BA.debugLine="End Sub";
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
		Debug.PushSubsStack("DramaPage_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,794);
if (RapidSub.canDelegate("dramapage_click")) { return b4a.example.drama.remoteMe.runUserSub(false, "drama","dramapage_click");}
 BA.debugLineNum = 794;BA.debugLine="Private Sub DramaPage_Click";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 796;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
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
 //BA.debugLineNum = 48;BA.debugLine="Private Panel1 As Panel";
drama.mostCurrent._panel1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 49;BA.debugLine="Private PanelMovie1 As Panel";
drama.mostCurrent._panelmovie1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 50;BA.debugLine="Private PanelMovie2 As Panel";
drama.mostCurrent._panelmovie2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 51;BA.debugLine="Private PanelMovie3 As Panel";
drama.mostCurrent._panelmovie3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 52;BA.debugLine="Private PanelMovie4 As Panel";
drama.mostCurrent._panelmovie4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 53;BA.debugLine="Private PanelMovie5 As Panel";
drama.mostCurrent._panelmovie5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 54;BA.debugLine="Private PanelMovie6 As Panel";
drama.mostCurrent._panelmovie6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 55;BA.debugLine="Private PanelMovie7 As Panel";
drama.mostCurrent._panelmovie7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 56;BA.debugLine="Private PanelMovie8 As Panel";
drama.mostCurrent._panelmovie8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 57;BA.debugLine="Private PanelMovie9 As Panel";
drama.mostCurrent._panelmovie9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 58;BA.debugLine="Private PanelMovie10 As Panel";
drama.mostCurrent._panelmovie10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 63;BA.debugLine="Private DramaPage As Label";
drama.mostCurrent._dramapage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 64;BA.debugLine="Private HomePage As Label";
drama.mostCurrent._homepage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 65;BA.debugLine="Private SciFiPage As Label";
drama.mostCurrent._scifipage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 66;BA.debugLine="Private ActionPage As Label";
drama.mostCurrent._actionpage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 69;BA.debugLine="Private Starter1 As Label";
drama.mostCurrent._starter1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 70;BA.debugLine="Private Starter2 As Label";
drama.mostCurrent._starter2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 71;BA.debugLine="Private Starter3 As Label";
drama.mostCurrent._starter3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 72;BA.debugLine="Private Starter4 As Label";
drama.mostCurrent._starter4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 73;BA.debugLine="Private Starter5 As Label";
drama.mostCurrent._starter5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 74;BA.debugLine="Private Starter6 As Label";
drama.mostCurrent._starter6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 75;BA.debugLine="Private Starter7 As Label";
drama.mostCurrent._starter7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 76;BA.debugLine="Private Starter8 As Label";
drama.mostCurrent._starter8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 77;BA.debugLine="Private Starter9 As Label";
drama.mostCurrent._starter9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 78;BA.debugLine="Private Starter10 As Label";
drama.mostCurrent._starter10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 81;BA.debugLine="Private OverView1 As Label";
drama.mostCurrent._overview1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 82;BA.debugLine="Private OverView2 As Label";
drama.mostCurrent._overview2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 83;BA.debugLine="Private OverView3 As Label";
drama.mostCurrent._overview3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 84;BA.debugLine="Private OverView4 As Label";
drama.mostCurrent._overview4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 85;BA.debugLine="Private OverView5 As Label";
drama.mostCurrent._overview5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 86;BA.debugLine="Private OverView6 As Label";
drama.mostCurrent._overview6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 87;BA.debugLine="Private OverView7 As Label";
drama.mostCurrent._overview7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 88;BA.debugLine="Private OverView8 As Label";
drama.mostCurrent._overview8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 89;BA.debugLine="Private OverView9 As Label";
drama.mostCurrent._overview9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 90;BA.debugLine="Private OverView10 As Label";
drama.mostCurrent._overview10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 92;BA.debugLine="Private Year1 As Label";
drama.mostCurrent._year1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 93;BA.debugLine="Private Year2 As Label";
drama.mostCurrent._year2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 94;BA.debugLine="Private Year3 As Label";
drama.mostCurrent._year3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 95;BA.debugLine="Private Year4 As Label";
drama.mostCurrent._year4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 96;BA.debugLine="Private Year5 As Label";
drama.mostCurrent._year5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 97;BA.debugLine="Private Year6 As Label";
drama.mostCurrent._year6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 98;BA.debugLine="Private Year7 As Label";
drama.mostCurrent._year7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 99;BA.debugLine="Private Year8 As Label";
drama.mostCurrent._year8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 100;BA.debugLine="Private Year9 As Label";
drama.mostCurrent._year9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 101;BA.debugLine="Private Year10 As Label";
drama.mostCurrent._year10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 104;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _homepage_click() throws Exception{
try {
		Debug.PushSubsStack("HomePage_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,789);
if (RapidSub.canDelegate("homepage_click")) { return b4a.example.drama.remoteMe.runUserSub(false, "drama","homepage_click");}
 BA.debugLineNum = 789;BA.debugLine="Private Sub HomePage_Click";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 790;BA.debugLine="StartActivity(Main)";
Debug.ShouldStop(2097152);
drama.mostCurrent.__c.runVoidMethod ("StartActivity",drama.processBA,(Object)((drama.mostCurrent._main.getObject())));
 BA.debugLineNum = 791;BA.debugLine="Activity.Finish";
Debug.ShouldStop(4194304);
drama.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 792;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static void  _panelmovie1_click() throws Exception{
try {
		Debug.PushSubsStack("PanelMovie1_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,803);
if (RapidSub.canDelegate("panelmovie1_click")) { b4a.example.drama.remoteMe.runUserSub(false, "drama","panelmovie1_click"); return;}
ResumableSub_PanelMovie1_Click rsub = new ResumableSub_PanelMovie1_Click(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_PanelMovie1_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie1_Click(b4a.example.drama parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.drama parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie1_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,803);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 804;BA.debugLine="Try";
Debug.ShouldStop(8);
if (true) break;

case 1:
//try
this.state = 10;
this.catchState = 9;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 9;
 BA.debugLineNum = 805;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(16);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),drama.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 806;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(32);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", drama.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "drama", "panelmovie1_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 807;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
Debug.ShouldStop(64);
if (true) break;

case 4:
//if
this.state = 7;
if (RemoteObject.solveBoolean("=",_result,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 BA.debugLineNum = 808;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(128);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 809;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
Debug.ShouldStop(256);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt0079417/")));
 BA.debugLineNum = 810;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(512);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 811;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(1024);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",drama.processBA,(Object)((_i.getObject())));
 if (true) break;

case 7:
//C
this.state = 10;
;
 Debug.CheckDeviceExceptions();
if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
 BA.debugLineNum = 815;BA.debugLine="Log(LastException)";
Debug.ShouldStop(16384);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","25439500",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",drama.mostCurrent.activityBA)),0);
 BA.debugLineNum = 816;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(32768);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),drama.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 819;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",drama.processBA, e0.toString());}
            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _msgbox_result(RemoteObject _result) throws Exception{
}
public static void  _panelmovie10_click() throws Exception{
try {
		Debug.PushSubsStack("PanelMovie10_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,958);
if (RapidSub.canDelegate("panelmovie10_click")) { b4a.example.drama.remoteMe.runUserSub(false, "drama","panelmovie10_click"); return;}
ResumableSub_PanelMovie10_Click rsub = new ResumableSub_PanelMovie10_Click(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_PanelMovie10_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie10_Click(b4a.example.drama parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.drama parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie10_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,958);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 959;BA.debugLine="Try";
Debug.ShouldStop(1073741824);
if (true) break;

case 1:
//try
this.state = 10;
this.catchState = 9;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 9;
 BA.debugLineNum = 960;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(-2147483648);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),drama.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 961;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(1);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", drama.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "drama", "panelmovie10_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 962;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
Debug.ShouldStop(2);
if (true) break;

case 4:
//if
this.state = 7;
if (RemoteObject.solveBoolean("=",_result,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 BA.debugLineNum = 963;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(4);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 964;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.co";
Debug.ShouldStop(8);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt9100054/")));
 BA.debugLineNum = 965;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.an";
Debug.ShouldStop(16);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 966;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(32);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",drama.processBA,(Object)((_i.getObject())));
 if (true) break;

case 7:
//C
this.state = 10;
;
 Debug.CheckDeviceExceptions();
if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
 BA.debugLineNum = 969;BA.debugLine="Log(LastException)";
Debug.ShouldStop(256);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","26160395",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",drama.mostCurrent.activityBA)),0);
 BA.debugLineNum = 970;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(512);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),drama.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 973;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",drama.processBA, e0.toString());}
            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _panelmovie2_click() throws Exception{
try {
		Debug.PushSubsStack("PanelMovie2_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,822);
if (RapidSub.canDelegate("panelmovie2_click")) { b4a.example.drama.remoteMe.runUserSub(false, "drama","panelmovie2_click"); return;}
ResumableSub_PanelMovie2_Click rsub = new ResumableSub_PanelMovie2_Click(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_PanelMovie2_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie2_Click(b4a.example.drama parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.drama parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie2_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,822);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 823;BA.debugLine="Try";
Debug.ShouldStop(4194304);
if (true) break;

case 1:
//try
this.state = 10;
this.catchState = 9;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 9;
 BA.debugLineNum = 824;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(8388608);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),drama.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 825;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(16777216);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", drama.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "drama", "panelmovie2_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 826;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
Debug.ShouldStop(33554432);
if (true) break;

case 4:
//if
this.state = 7;
if (RemoteObject.solveBoolean("=",_result,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 BA.debugLineNum = 827;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(67108864);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 828;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.co";
Debug.ShouldStop(134217728);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt4034228/")));
 BA.debugLineNum = 829;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.an";
Debug.ShouldStop(268435456);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 830;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(536870912);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",drama.processBA,(Object)((_i.getObject())));
 if (true) break;

case 7:
//C
this.state = 10;
;
 Debug.CheckDeviceExceptions();
if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
 BA.debugLineNum = 833;BA.debugLine="Log(LastException)";
Debug.ShouldStop(1);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","25570571",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",drama.mostCurrent.activityBA)),0);
 BA.debugLineNum = 834;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(2);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),drama.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 837;BA.debugLine="End Sub";
Debug.ShouldStop(16);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",drama.processBA, e0.toString());}
            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _panelmovie3_click() throws Exception{
try {
		Debug.PushSubsStack("PanelMovie3_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,839);
if (RapidSub.canDelegate("panelmovie3_click")) { b4a.example.drama.remoteMe.runUserSub(false, "drama","panelmovie3_click"); return;}
ResumableSub_PanelMovie3_Click rsub = new ResumableSub_PanelMovie3_Click(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_PanelMovie3_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie3_Click(b4a.example.drama parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.drama parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie3_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,839);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 840;BA.debugLine="Try";
Debug.ShouldStop(128);
if (true) break;

case 1:
//try
this.state = 10;
this.catchState = 9;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 9;
 BA.debugLineNum = 841;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(256);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),drama.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 842;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(512);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", drama.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "drama", "panelmovie3_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 843;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
Debug.ShouldStop(1024);
if (true) break;

case 4:
//if
this.state = 7;
if (RemoteObject.solveBoolean("=",_result,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 BA.debugLineNum = 844;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(2048);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 845;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.co";
Debug.ShouldStop(4096);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt1560747/")));
 BA.debugLineNum = 846;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.an";
Debug.ShouldStop(8192);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 847;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(16384);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",drama.processBA,(Object)((_i.getObject())));
 if (true) break;

case 7:
//C
this.state = 10;
;
 Debug.CheckDeviceExceptions();
if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
 BA.debugLineNum = 850;BA.debugLine="Log(LastException)";
Debug.ShouldStop(131072);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","25636107",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",drama.mostCurrent.activityBA)),0);
 BA.debugLineNum = 851;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(262144);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),drama.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 854;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",drama.processBA, e0.toString());}
            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _panelmovie4_click() throws Exception{
try {
		Debug.PushSubsStack("PanelMovie4_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,856);
if (RapidSub.canDelegate("panelmovie4_click")) { b4a.example.drama.remoteMe.runUserSub(false, "drama","panelmovie4_click"); return;}
ResumableSub_PanelMovie4_Click rsub = new ResumableSub_PanelMovie4_Click(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_PanelMovie4_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie4_Click(b4a.example.drama parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.drama parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie4_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,856);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 857;BA.debugLine="Try";
Debug.ShouldStop(16777216);
if (true) break;

case 1:
//try
this.state = 10;
this.catchState = 9;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 9;
 BA.debugLineNum = 858;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(33554432);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),drama.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 859;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(67108864);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", drama.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "drama", "panelmovie4_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 860;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
Debug.ShouldStop(134217728);
if (true) break;

case 4:
//if
this.state = 7;
if (RemoteObject.solveBoolean("=",_result,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 BA.debugLineNum = 861;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(268435456);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 862;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.co";
Debug.ShouldStop(536870912);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt0405159/")));
 BA.debugLineNum = 863;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.an";
Debug.ShouldStop(1073741824);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 864;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(-2147483648);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",drama.processBA,(Object)((_i.getObject())));
 if (true) break;

case 7:
//C
this.state = 10;
;
 Debug.CheckDeviceExceptions();
if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
 BA.debugLineNum = 867;BA.debugLine="Log(LastException)";
Debug.ShouldStop(4);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","25701643",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",drama.mostCurrent.activityBA)),0);
 BA.debugLineNum = 868;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(8);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),drama.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 871;BA.debugLine="End Sub";
Debug.ShouldStop(64);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",drama.processBA, e0.toString());}
            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _panelmovie5_click() throws Exception{
try {
		Debug.PushSubsStack("PanelMovie5_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,873);
if (RapidSub.canDelegate("panelmovie5_click")) { b4a.example.drama.remoteMe.runUserSub(false, "drama","panelmovie5_click"); return;}
ResumableSub_PanelMovie5_Click rsub = new ResumableSub_PanelMovie5_Click(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_PanelMovie5_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie5_Click(b4a.example.drama parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.drama parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie5_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,873);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 874;BA.debugLine="Try";
Debug.ShouldStop(512);
if (true) break;

case 1:
//try
this.state = 10;
this.catchState = 9;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 9;
 BA.debugLineNum = 875;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(1024);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),drama.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 876;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(2048);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", drama.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "drama", "panelmovie5_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 877;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
Debug.ShouldStop(4096);
if (true) break;

case 4:
//if
this.state = 7;
if (RemoteObject.solveBoolean("=",_result,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 BA.debugLineNum = 878;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(8192);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 879;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.co";
Debug.ShouldStop(16384);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt0112579/")));
 BA.debugLineNum = 880;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.an";
Debug.ShouldStop(32768);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 881;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(65536);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",drama.processBA,(Object)((_i.getObject())));
 if (true) break;

case 7:
//C
this.state = 10;
;
 Debug.CheckDeviceExceptions();
if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
 BA.debugLineNum = 884;BA.debugLine="Log(LastException)";
Debug.ShouldStop(524288);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","25767179",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",drama.mostCurrent.activityBA)),0);
 BA.debugLineNum = 885;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(1048576);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),drama.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 888;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",drama.processBA, e0.toString());}
            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _panelmovie6_click() throws Exception{
try {
		Debug.PushSubsStack("PanelMovie6_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,890);
if (RapidSub.canDelegate("panelmovie6_click")) { b4a.example.drama.remoteMe.runUserSub(false, "drama","panelmovie6_click"); return;}
ResumableSub_PanelMovie6_Click rsub = new ResumableSub_PanelMovie6_Click(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_PanelMovie6_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie6_Click(b4a.example.drama parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.drama parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie6_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,890);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 891;BA.debugLine="Try";
Debug.ShouldStop(67108864);
if (true) break;

case 1:
//try
this.state = 10;
this.catchState = 9;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 9;
 BA.debugLineNum = 892;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(134217728);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),drama.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 893;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(268435456);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", drama.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "drama", "panelmovie6_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 894;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
Debug.ShouldStop(536870912);
if (true) break;

case 4:
//if
this.state = 7;
if (RemoteObject.solveBoolean("=",_result,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 BA.debugLineNum = 895;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(1073741824);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 896;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.co";
Debug.ShouldStop(-2147483648);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt0452623/")));
 BA.debugLineNum = 897;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.an";
Debug.ShouldStop(1);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 898;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(2);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",drama.processBA,(Object)((_i.getObject())));
 if (true) break;

case 7:
//C
this.state = 10;
;
 Debug.CheckDeviceExceptions();
if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
 BA.debugLineNum = 901;BA.debugLine="Log(LastException)";
Debug.ShouldStop(16);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","25832715",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",drama.mostCurrent.activityBA)),0);
 BA.debugLineNum = 902;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(32);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),drama.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 905;BA.debugLine="End Sub";
Debug.ShouldStop(256);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",drama.processBA, e0.toString());}
            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _panelmovie7_click() throws Exception{
try {
		Debug.PushSubsStack("PanelMovie7_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,907);
if (RapidSub.canDelegate("panelmovie7_click")) { b4a.example.drama.remoteMe.runUserSub(false, "drama","panelmovie7_click"); return;}
ResumableSub_PanelMovie7_Click rsub = new ResumableSub_PanelMovie7_Click(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_PanelMovie7_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie7_Click(b4a.example.drama parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.drama parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie7_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,907);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 908;BA.debugLine="Try";
Debug.ShouldStop(2048);
if (true) break;

case 1:
//try
this.state = 10;
this.catchState = 9;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 9;
 BA.debugLineNum = 909;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(4096);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),drama.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 910;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(8192);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", drama.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "drama", "panelmovie7_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 911;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
Debug.ShouldStop(16384);
if (true) break;

case 4:
//if
this.state = 7;
if (RemoteObject.solveBoolean("=",_result,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 BA.debugLineNum = 912;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(32768);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 913;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.co";
Debug.ShouldStop(65536);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt2334873/")));
 BA.debugLineNum = 914;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.an";
Debug.ShouldStop(131072);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 915;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(262144);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",drama.processBA,(Object)((_i.getObject())));
 if (true) break;

case 7:
//C
this.state = 10;
;
 Debug.CheckDeviceExceptions();
if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
 BA.debugLineNum = 918;BA.debugLine="Log(LastException)";
Debug.ShouldStop(2097152);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","25898251",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",drama.mostCurrent.activityBA)),0);
 BA.debugLineNum = 919;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(4194304);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),drama.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 922;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",drama.processBA, e0.toString());}
            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _panelmovie8_click() throws Exception{
try {
		Debug.PushSubsStack("PanelMovie8_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,924);
if (RapidSub.canDelegate("panelmovie8_click")) { b4a.example.drama.remoteMe.runUserSub(false, "drama","panelmovie8_click"); return;}
ResumableSub_PanelMovie8_Click rsub = new ResumableSub_PanelMovie8_Click(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_PanelMovie8_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie8_Click(b4a.example.drama parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.drama parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie8_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,924);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 925;BA.debugLine="Try";
Debug.ShouldStop(268435456);
if (true) break;

case 1:
//try
this.state = 10;
this.catchState = 9;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 9;
 BA.debugLineNum = 926;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(536870912);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),drama.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 927;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(1073741824);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", drama.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "drama", "panelmovie8_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 928;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
Debug.ShouldStop(-2147483648);
if (true) break;

case 4:
//if
this.state = 7;
if (RemoteObject.solveBoolean("=",_result,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 BA.debugLineNum = 929;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(1);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 930;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.co";
Debug.ShouldStop(2);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt1798709/")));
 BA.debugLineNum = 931;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.an";
Debug.ShouldStop(4);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 932;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(8);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",drama.processBA,(Object)((_i.getObject())));
 if (true) break;

case 7:
//C
this.state = 10;
;
 Debug.CheckDeviceExceptions();
if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
 BA.debugLineNum = 935;BA.debugLine="Log(LastException)";
Debug.ShouldStop(64);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","25963787",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",drama.mostCurrent.activityBA)),0);
 BA.debugLineNum = 936;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(128);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),drama.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 939;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",drama.processBA, e0.toString());}
            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _panelmovie9_click() throws Exception{
try {
		Debug.PushSubsStack("PanelMovie9_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,941);
if (RapidSub.canDelegate("panelmovie9_click")) { b4a.example.drama.remoteMe.runUserSub(false, "drama","panelmovie9_click"); return;}
ResumableSub_PanelMovie9_Click rsub = new ResumableSub_PanelMovie9_Click(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_PanelMovie9_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie9_Click(b4a.example.drama parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.drama parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie9_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,941);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 942;BA.debugLine="Try";
Debug.ShouldStop(8192);
if (true) break;

case 1:
//try
this.state = 10;
this.catchState = 9;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 9;
 BA.debugLineNum = 943;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(16384);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),drama.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 944;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(32768);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", drama.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "drama", "panelmovie9_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 945;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
Debug.ShouldStop(65536);
if (true) break;

case 4:
//if
this.state = 7;
if (RemoteObject.solveBoolean("=",_result,BA.numberCast(double.class, parent.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 BA.debugLineNum = 946;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(131072);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 947;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.co";
Debug.ShouldStop(262144);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt2402927/")));
 BA.debugLineNum = 948;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.an";
Debug.ShouldStop(524288);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 949;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(1048576);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",drama.processBA,(Object)((_i.getObject())));
 if (true) break;

case 7:
//C
this.state = 10;
;
 Debug.CheckDeviceExceptions();
if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
 BA.debugLineNum = 952;BA.debugLine="Log(LastException)";
Debug.ShouldStop(8388608);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","26029323",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",drama.mostCurrent.activityBA)),0);
 BA.debugLineNum = 953;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(16777216);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),drama.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 956;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",drama.processBA, e0.toString());}
            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _scifipage_click() throws Exception{
try {
		Debug.PushSubsStack("SciFiPage_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,784);
if (RapidSub.canDelegate("scifipage_click")) { return b4a.example.drama.remoteMe.runUserSub(false, "drama","scifipage_click");}
 BA.debugLineNum = 784;BA.debugLine="Private Sub SciFiPage_Click";
Debug.ShouldStop(32768);
 BA.debugLineNum = 785;BA.debugLine="StartActivity(SciFi)";
Debug.ShouldStop(65536);
drama.mostCurrent.__c.runVoidMethod ("StartActivity",drama.processBA,(Object)((drama.mostCurrent._scifi.getObject())));
 BA.debugLineNum = 786;BA.debugLine="Activity.Finish";
Debug.ShouldStop(131072);
drama.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 787;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
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
		Debug.PushSubsStack("SearchBtn_Click (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,194);
if (RapidSub.canDelegate("searchbtn_click")) { return b4a.example.drama.remoteMe.runUserSub(false, "drama","searchbtn_click");}
RemoteObject _query = RemoteObject.createImmutable("");
RemoteObject _userinput = RemoteObject.createImmutable("");
 BA.debugLineNum = 194;BA.debugLine="Private Sub SearchBtn_Click";
Debug.ShouldStop(2);
 BA.debugLineNum = 195;BA.debugLine="Dim query As String = SearchEngine.Text.ToLowerCa";
Debug.ShouldStop(4);
_query = drama.mostCurrent._searchengine.runMethod(true,"getText").runMethod(true,"toLowerCase").runMethod(true,"trim");Debug.locals.put("query", _query);Debug.locals.put("query", _query);
 BA.debugLineNum = 197;BA.debugLine="Dim UserInput As String = SearchEngine.Text";
Debug.ShouldStop(16);
_userinput = drama.mostCurrent._searchengine.runMethod(true,"getText");Debug.locals.put("UserInput", _userinput);Debug.locals.put("UserInput", _userinput);
 BA.debugLineNum = 202;BA.debugLine="If query.Contains(\"kramer\") Or query.Contains(\"kr";
Debug.ShouldStop(512);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("kramer")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("kramer vs kramer"))))) { 
 BA.debugLineNum = 204;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
Debug.ShouldStop(2048);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Kramer vs. Kramer"));
 BA.debugLineNum = 205;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
Debug.ShouldStop(4096);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
 BA.debugLineNum = 206;BA.debugLine="Year1.Text = \"(1979)\"";
Debug.ShouldStop(8192);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(1979)"));
 BA.debugLineNum = 207;BA.debugLine="OverView1.Text = \"In this emotionally charged co";
Debug.ShouldStop(16384);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
 BA.debugLineNum = 208;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(32768);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 209;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(65536);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("kramer.jpg"))).getObject()));
 BA.debugLineNum = 211;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(262144);
drama.mostCurrent._panelmovie2.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 212;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(524288);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 213;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(1048576);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 214;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(2097152);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 215;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(4194304);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 217;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(16777216);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 219;BA.debugLine="Else If query.Contains(\"manchester by the Sea\") O";
Debug.ShouldStop(67108864);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("manchester by the Sea")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("manchester"))))) { 
 BA.debugLineNum = 221;BA.debugLine="Drama1.Text = \"Manchester by the Sea\"";
Debug.ShouldStop(268435456);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Manchester by the Sea"));
 BA.debugLineNum = 222;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Michel";
Debug.ShouldStop(536870912);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
 BA.debugLineNum = 223;BA.debugLine="Year1.Text = \"(2016)\"";
Debug.ShouldStop(1073741824);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2016)"));
 BA.debugLineNum = 224;BA.debugLine="OverView1.Text = \"After the death of his brother";
Debug.ShouldStop(-2147483648);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
 BA.debugLineNum = 225;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(1);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 226;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(2);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("manchester.jpg"))).getObject()));
 BA.debugLineNum = 229;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(16);
drama.mostCurrent._panelmovie2.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 230;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(32);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 231;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(64);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 232;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(128);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 233;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(256);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 235;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(1024);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 237;BA.debugLine="Else If query.Contains(\"the master\") Or query.Con";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("the master")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("master"))))) { 
 BA.debugLineNum = 239;BA.debugLine="Drama1.Text = \"The Master\"";
Debug.ShouldStop(16384);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("The Master"));
 BA.debugLineNum = 240;BA.debugLine="Starter1.Text = \"Starring: Philip Seymour Hoffma";
Debug.ShouldStop(32768);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
 BA.debugLineNum = 241;BA.debugLine="Year1.Text = \"(2012)\"";
Debug.ShouldStop(65536);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2012)"));
 BA.debugLineNum = 242;BA.debugLine="OverView1.Text = \"A mentally unstable WWII veter";
Debug.ShouldStop(131072);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
 BA.debugLineNum = 243;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(262144);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 244;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(524288);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("master.jpg"))).getObject()));
 BA.debugLineNum = 246;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(2097152);
drama.mostCurrent._panelmovie2.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 247;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(4194304);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 248;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(8388608);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 249;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(16777216);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 250;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(33554432);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 252;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(134217728);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 254;BA.debugLine="Else If query.Contains(\"million dollar\") Or query";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("million dollar")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("million")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("million dollar baby"))))) { 
 BA.debugLineNum = 256;BA.debugLine="Drama1.Text = \"Million Dollar Baby\"";
Debug.ShouldStop(-2147483648);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Million Dollar Baby"));
 BA.debugLineNum = 257;BA.debugLine="Starter1.Text = \"Starring: Morgan Freeman, Hilar";
Debug.ShouldStop(1);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
 BA.debugLineNum = 258;BA.debugLine="Year1.Text = \"(2004)\"";
Debug.ShouldStop(2);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2004)"));
 BA.debugLineNum = 259;BA.debugLine="OverView1.Text = \"A waitress with dreams of beco";
Debug.ShouldStop(4);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
 BA.debugLineNum = 260;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(8);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 261;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(16);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("millondolar.jpg"))).getObject()));
 BA.debugLineNum = 263;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(64);
drama.mostCurrent._panelmovie2.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 264;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(128);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 265;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(256);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 266;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(512);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 267;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(1024);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 269;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(4096);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 271;BA.debugLine="Else If query.Contains(\"the Bridges of madison co";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("the Bridges of madison country")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("Bridge")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("bridges of madison"))))) { 
 BA.debugLineNum = 273;BA.debugLine="Drama1.Text = \"The Bridges of Madison County\"";
Debug.ShouldStop(65536);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("The Bridges of Madison County"));
 BA.debugLineNum = 274;BA.debugLine="Starter1.Text = \"Starring: Clint Eastwood, Meryl";
Debug.ShouldStop(131072);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
 BA.debugLineNum = 275;BA.debugLine="Year1.Text = \"(1995)\"";
Debug.ShouldStop(262144);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(1995)"));
 BA.debugLineNum = 276;BA.debugLine="OverView1.Text = \"A brief, passionate romance be";
Debug.ShouldStop(524288);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
 BA.debugLineNum = 277;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(1048576);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 278;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(2097152);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("bridges.jpg"))).getObject()));
 BA.debugLineNum = 280;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(8388608);
drama.mostCurrent._panelmovie2.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 281;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(16777216);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 282;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(33554432);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 283;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(67108864);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 284;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(134217728);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 286;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(536870912);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 288;BA.debugLine="Else If query.Contains(\"gone\") Or query.Contains(";
Debug.ShouldStop(-2147483648);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("gone")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("gone baby gone")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("gone baby"))))) { 
 BA.debugLineNum = 290;BA.debugLine="Drama1.Text = \"Gone Baby Gone\"";
Debug.ShouldStop(2);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Gone Baby Gone"));
 BA.debugLineNum = 291;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Michel";
Debug.ShouldStop(4);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
 BA.debugLineNum = 292;BA.debugLine="Year1.Text = \"(2007)\"";
Debug.ShouldStop(8);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2007)"));
 BA.debugLineNum = 293;BA.debugLine="OverView1.Text = \"In a tough Boston neighborhood";
Debug.ShouldStop(16);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
 BA.debugLineNum = 295;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(64);
drama.mostCurrent._panelmovie2.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 296;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(128);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 297;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(256);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 298;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(512);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 299;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(1024);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 301;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(4096);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 303;BA.debugLine="Else If query.Contains(\"blue\") Or query.Contains(";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("blue")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("blue jasmine"))))) { 
 BA.debugLineNum = 306;BA.debugLine="Drama1.Text = \"Blue Jasmine\"";
Debug.ShouldStop(131072);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Blue Jasmine"));
 BA.debugLineNum = 307;BA.debugLine="Starter1.Text = \"Starring: Cate Blanchett, Sally";
Debug.ShouldStop(262144);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
 BA.debugLineNum = 308;BA.debugLine="Year1.Text = \"(2013)\"";
Debug.ShouldStop(524288);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 309;BA.debugLine="OverView1.Text = \"After losing her fortune and s";
Debug.ShouldStop(1048576);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
 BA.debugLineNum = 310;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(2097152);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 311;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(4194304);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("gonebaby.jpg"))).getObject()));
 BA.debugLineNum = 313;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(16777216);
drama.mostCurrent._panelmovie2.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 314;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(33554432);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 315;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(67108864);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 316;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(134217728);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 317;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(268435456);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 319;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(1073741824);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 321;BA.debugLine="Else If query.Contains(\"her\") Then";
Debug.ShouldStop(1);
if (_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("her"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 323;BA.debugLine="Drama1.Text = \"Her\"";
Debug.ShouldStop(4);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Her"));
 BA.debugLineNum = 324;BA.debugLine="Starter1.Text = \"Starring: Joaquin Phoenix, Roon";
Debug.ShouldStop(8);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
 BA.debugLineNum = 325;BA.debugLine="Year1.Text = \"(2013)\"";
Debug.ShouldStop(16);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 326;BA.debugLine="OverView1.Text = \"In a near-future Los Angeles,";
Debug.ShouldStop(32);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
 BA.debugLineNum = 327;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(64);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 328;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(128);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("her.jpg"))).getObject()));
 BA.debugLineNum = 330;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(512);
drama.mostCurrent._panelmovie2.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 331;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(1024);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 332;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(2048);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 333;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(4096);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 334;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(8192);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 336;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(32768);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 338;BA.debugLine="Else If query.Contains(\"carol\") Or query.Contains";
Debug.ShouldStop(131072);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("carol")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("sarah paulson")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("sarah")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("paulson"))))) { 
 BA.debugLineNum = 340;BA.debugLine="Drama1.Text = \"Carol\"";
Debug.ShouldStop(524288);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Carol"));
 BA.debugLineNum = 341;BA.debugLine="Starter1.Text = \"Starring: Rooney Mara, Cate Bla";
Debug.ShouldStop(1048576);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
 BA.debugLineNum = 342;BA.debugLine="Year1.Text = \"(2015)\"";
Debug.ShouldStop(2097152);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2015)"));
 BA.debugLineNum = 343;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
Debug.ShouldStop(4194304);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
 BA.debugLineNum = 344;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(8388608);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 345;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(16777216);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("carol.jpg"))).getObject()));
 BA.debugLineNum = 347;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(67108864);
drama.mostCurrent._panelmovie2.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 348;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(134217728);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 349;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(268435456);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 350;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(536870912);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 351;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(1073741824);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 353;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(1);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 355;BA.debugLine="Else If query.Contains(\"the lost daugther\") Or qu";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("the lost daugther")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("lost daughter")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("lost"))))) { 
 BA.debugLineNum = 357;BA.debugLine="Drama1.Text = \"The Lost Daughter\"";
Debug.ShouldStop(16);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("The Lost Daughter"));
 BA.debugLineNum = 358;BA.debugLine="Starter1.Text = \"Starring: Olivia Colman, Dakota";
Debug.ShouldStop(32);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Olivia Colman, Dakota Johnson, Jessie Buckley"));
 BA.debugLineNum = 359;BA.debugLine="Year1.Text = \"(2021)\"";
Debug.ShouldStop(64);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2021)"));
 BA.debugLineNum = 360;BA.debugLine="OverView1.Text = \"A solitary woman on vacation b";
Debug.ShouldStop(128);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity"));
 BA.debugLineNum = 361;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(256);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 362;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(512);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("lostdaughter.jpg"))).getObject()));
 BA.debugLineNum = 364;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(2048);
drama.mostCurrent._panelmovie2.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 365;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(4096);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 366;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(8192);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 367;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(16384);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 368;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(32768);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 370;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(131072);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 371;BA.debugLine="Else If query.Contains(\"doubt\") Then";
Debug.ShouldStop(262144);
if (_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("doubt"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 372;BA.debugLine="Drama1.Text = \"Doubt\"";
Debug.ShouldStop(524288);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Doubt"));
 BA.debugLineNum = 373;BA.debugLine="Starter1.Text = \"Starring: Meryl Streep, Philip";
Debug.ShouldStop(1048576);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Meryl Streep, Philip Seymour Hoffman, Amy Adams"));
 BA.debugLineNum = 374;BA.debugLine="Year1.Text = \"(2008)\"";
Debug.ShouldStop(2097152);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2008)"));
 BA.debugLineNum = 375;BA.debugLine="OverView1.Text = \"In a Catholic school in the Br";
Debug.ShouldStop(4194304);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In a Catholic school in the Bronx, a strict nun becomes suspicious of a priest’s relationship with a student. As she digs deeper, the lines between truth and suspicion blur, leaving everyone in moral and emotional limbo."));
 BA.debugLineNum = 376;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(8388608);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 377;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(16777216);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("doubt.jpg"))).getObject()));
 BA.debugLineNum = 379;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(67108864);
drama.mostCurrent._panelmovie2.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 380;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(134217728);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 381;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(268435456);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 382;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(536870912);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 383;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(1073741824);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 385;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(1);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 388;BA.debugLine="Else If query.Contains(\"meryl streep\") Or query.C";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("meryl streep")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("meryl")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("streep"))))) { 
 BA.debugLineNum = 389;BA.debugLine="Drama1.Text = \"The Bridges of Madison County\"";
Debug.ShouldStop(16);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("The Bridges of Madison County"));
 BA.debugLineNum = 390;BA.debugLine="Starter1.Text = \"Starring: Clint Eastwood, Meryl";
Debug.ShouldStop(32);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
 BA.debugLineNum = 391;BA.debugLine="Year1.Text = \"(1995)\"";
Debug.ShouldStop(64);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(1995)"));
 BA.debugLineNum = 392;BA.debugLine="OverView1.Text = \"A brief, passionate romance be";
Debug.ShouldStop(128);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
 BA.debugLineNum = 393;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(256);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 394;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(512);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("bridges.jpg"))).getObject()));
 BA.debugLineNum = 396;BA.debugLine="Drama2.Text = \"Doubt\"";
Debug.ShouldStop(2048);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Doubt"));
 BA.debugLineNum = 397;BA.debugLine="Starter2.Text = \"Starring: Meryl Streep, Philip";
Debug.ShouldStop(4096);
drama.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Meryl Streep, Philip Seymour Hoffman, Amy Adams"));
 BA.debugLineNum = 398;BA.debugLine="Year2.Text = \"(2008)\"";
Debug.ShouldStop(8192);
drama.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2008)"));
 BA.debugLineNum = 399;BA.debugLine="OverView2.Text = \"In a Catholic school in the Br";
Debug.ShouldStop(16384);
drama.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("In a Catholic school in the Bronx, a strict nun becomes suspicious of a priest’s relationship with a student. As she digs deeper, the lines between truth and suspicion blur, leaving everyone in moral and emotional limbo."));
 BA.debugLineNum = 400;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(32768);
drama.mostCurrent._dramaimage2.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 401;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(65536);
drama.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("doubt.jpg"))).getObject()));
 BA.debugLineNum = 404;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(524288);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 405;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(1048576);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 406;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(2097152);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 407;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(4194304);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 409;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(16777216);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 410;BA.debugLine="Else If query.Contains(\"casey\") Or query.Contains";
Debug.ShouldStop(33554432);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("casey")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("casey affleck"))))) { 
 BA.debugLineNum = 411;BA.debugLine="Drama1.Text = \"Gone Baby Gone\"";
Debug.ShouldStop(67108864);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Gone Baby Gone"));
 BA.debugLineNum = 412;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Michel";
Debug.ShouldStop(134217728);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
 BA.debugLineNum = 413;BA.debugLine="Year1.Text = \"(2007)\"";
Debug.ShouldStop(268435456);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2007)"));
 BA.debugLineNum = 414;BA.debugLine="OverView1.Text = \"In a tough Boston neighborhood";
Debug.ShouldStop(536870912);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
 BA.debugLineNum = 415;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(1073741824);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 416;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(-2147483648);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("gonebaby.jpg"))).getObject()));
 BA.debugLineNum = 418;BA.debugLine="Drama2.Text = \"Manchester by the Sea\"";
Debug.ShouldStop(2);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Manchester by the Sea"));
 BA.debugLineNum = 419;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michel";
Debug.ShouldStop(4);
drama.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
 BA.debugLineNum = 420;BA.debugLine="Year2.Text = \"(2016)\"";
Debug.ShouldStop(8);
drama.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2016)"));
 BA.debugLineNum = 421;BA.debugLine="OverView2.Text = \"After the death of his brother";
Debug.ShouldStop(16);
drama.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
 BA.debugLineNum = 422;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(32);
drama.mostCurrent._dramaimage2.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 423;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(64);
drama.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("manchester.jpg"))).getObject()));
 BA.debugLineNum = 425;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(256);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 426;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(512);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 427;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(1024);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 428;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(2048);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 430;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(8192);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 432;BA.debugLine="Else If query.Contains(\"joaquin\") Or query.Contai";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("joaquin")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("joaquin phoenix")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("amy adams")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("amy"))))) { 
 BA.debugLineNum = 433;BA.debugLine="Drama1.Text = \"The Master\"";
Debug.ShouldStop(65536);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("The Master"));
 BA.debugLineNum = 434;BA.debugLine="Starter1.Text = \"Starring: Philip Seymour Hoffma";
Debug.ShouldStop(131072);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
 BA.debugLineNum = 435;BA.debugLine="Year1.Text = \"(2012)\"";
Debug.ShouldStop(262144);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2012)"));
 BA.debugLineNum = 436;BA.debugLine="OverView1.Text = \"A mentally unstable WWII veter";
Debug.ShouldStop(524288);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
 BA.debugLineNum = 437;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(1048576);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 438;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(2097152);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("master.jpg"))).getObject()));
 BA.debugLineNum = 440;BA.debugLine="Drama2.Text = \"Her\"";
Debug.ShouldStop(8388608);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Her"));
 BA.debugLineNum = 441;BA.debugLine="Starter2.Text = \"Starring: Joaquin Phoenix, Roon";
Debug.ShouldStop(16777216);
drama.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
 BA.debugLineNum = 442;BA.debugLine="Year2.Text = \"(2013)\"";
Debug.ShouldStop(33554432);
drama.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 443;BA.debugLine="OverView2.Text = \"In a near-future Los Angeles,";
Debug.ShouldStop(67108864);
drama.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
 BA.debugLineNum = 444;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(134217728);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 445;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(268435456);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("her.jpg"))).getObject()));
 BA.debugLineNum = 447;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(1073741824);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 448;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(-2147483648);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 449;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(1);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 450;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(2);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 452;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(8);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 454;BA.debugLine="Else If query.Contains(\"cate blanchett\") Or query";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("cate blanchett")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("cate"))))) { 
 BA.debugLineNum = 455;BA.debugLine="Drama1.Text = \"Carol\"";
Debug.ShouldStop(64);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Carol"));
 BA.debugLineNum = 456;BA.debugLine="Starter1.Text = \"Starring: Rooney Mara, Cate Bla";
Debug.ShouldStop(128);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
 BA.debugLineNum = 457;BA.debugLine="Year1.Text = \"(2015)\"";
Debug.ShouldStop(256);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2015)"));
 BA.debugLineNum = 458;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
Debug.ShouldStop(512);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
 BA.debugLineNum = 459;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(1024);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 460;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(2048);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("carol.jpg"))).getObject()));
 BA.debugLineNum = 462;BA.debugLine="Drama2.Text = \"Blue Jasmine\"";
Debug.ShouldStop(8192);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Blue Jasmine"));
 BA.debugLineNum = 463;BA.debugLine="Starter2.Text = \"Starring: Cate Blanchett, Sally";
Debug.ShouldStop(16384);
drama.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
 BA.debugLineNum = 464;BA.debugLine="Year2.Text = \"(2013)\"";
Debug.ShouldStop(32768);
drama.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 465;BA.debugLine="OverView2.Text = \"After losing her fortune and s";
Debug.ShouldStop(65536);
drama.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
 BA.debugLineNum = 466;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(131072);
drama.mostCurrent._dramaimage2.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 467;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(262144);
drama.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("bluejasmine.jpg"))).getObject()));
 BA.debugLineNum = 469;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(1048576);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 470;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(2097152);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 471;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(4194304);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 472;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(8388608);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 474;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(33554432);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 476;BA.debugLine="Else If query.Contains(\"morgan freeman\") Or query";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("morgan freeman")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("morgan"))))) { 
 BA.debugLineNum = 477;BA.debugLine="Drama1.Text = \"Million Dollar Baby\"";
Debug.ShouldStop(268435456);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Million Dollar Baby"));
 BA.debugLineNum = 478;BA.debugLine="Starter1.Text = \"Starring: Morgan Freeman, Hilar";
Debug.ShouldStop(536870912);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
 BA.debugLineNum = 479;BA.debugLine="Year1.Text = \"(2004)\"";
Debug.ShouldStop(1073741824);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2004)"));
 BA.debugLineNum = 480;BA.debugLine="OverView1.Text = \"A waitress with dreams of beco";
Debug.ShouldStop(-2147483648);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
 BA.debugLineNum = 481;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(1);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 482;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(2);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("millondolar.jpg"))).getObject()));
 BA.debugLineNum = 484;BA.debugLine="Drama2.Text = \"Gone Baby Gone\"";
Debug.ShouldStop(8);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Gone Baby Gone"));
 BA.debugLineNum = 485;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michel";
Debug.ShouldStop(16);
drama.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
 BA.debugLineNum = 486;BA.debugLine="Year2.Text = \"(2007)\"";
Debug.ShouldStop(32);
drama.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2007)"));
 BA.debugLineNum = 487;BA.debugLine="OverView2.Text = \"In a tough Boston neighborhood";
Debug.ShouldStop(64);
drama.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
 BA.debugLineNum = 488;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(128);
drama.mostCurrent._dramaimage2.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 489;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(256);
drama.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("gonebaby.jpg"))).getObject()));
 BA.debugLineNum = 491;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(1024);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 492;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(2048);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 493;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(4096);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 494;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(8192);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 496;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(32768);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 498;BA.debugLine="Else If query.Contains(\"clint eastwood\") Or query";
Debug.ShouldStop(131072);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("clint eastwood")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("clint")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("eastwood"))))) { 
 BA.debugLineNum = 499;BA.debugLine="Drama1.Text = \"Million Dollar Baby\"";
Debug.ShouldStop(262144);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Million Dollar Baby"));
 BA.debugLineNum = 500;BA.debugLine="Starter1.Text = \"Starring: Morgan Freeman, Hilar";
Debug.ShouldStop(524288);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
 BA.debugLineNum = 501;BA.debugLine="Year1.Text = \"(2004)\"";
Debug.ShouldStop(1048576);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2004)"));
 BA.debugLineNum = 502;BA.debugLine="OverView1.Text = \"A waitress with dreams of beco";
Debug.ShouldStop(2097152);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
 BA.debugLineNum = 503;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(4194304);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 504;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(8388608);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("millondolar.jpg"))).getObject()));
 BA.debugLineNum = 506;BA.debugLine="Drama2.Text = \"The Bridges of Madison Country\"";
Debug.ShouldStop(33554432);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("The Bridges of Madison Country"));
 BA.debugLineNum = 507;BA.debugLine="Starter2.Text = \"Starring: Clint Eastwood, Meryl";
Debug.ShouldStop(67108864);
drama.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
 BA.debugLineNum = 508;BA.debugLine="Year2.Text = \"(1995)\"";
Debug.ShouldStop(134217728);
drama.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(1995)"));
 BA.debugLineNum = 509;BA.debugLine="OverView2.Text = \"A brief, passionate romance be";
Debug.ShouldStop(268435456);
drama.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
 BA.debugLineNum = 510;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(536870912);
drama.mostCurrent._dramaimage2.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 511;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(1073741824);
drama.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("bridges.jpg"))).getObject()));
 BA.debugLineNum = 513;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(1);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 514;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(2);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 515;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(4);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 516;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(8);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 518;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(32);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 520;BA.debugLine="Else If query.Contains(\"amy Adams\") Or query.Cont";
Debug.ShouldStop(128);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("amy Adams")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("amy")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("adams"))))) { 
 BA.debugLineNum = 522;BA.debugLine="Drama2.Text = \"The Master\"";
Debug.ShouldStop(512);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("The Master"));
 BA.debugLineNum = 523;BA.debugLine="Starter2.Text = \"Starring: Philip Seymour Hoffma";
Debug.ShouldStop(1024);
drama.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
 BA.debugLineNum = 524;BA.debugLine="Year2.Text = \"(2012)\"";
Debug.ShouldStop(2048);
drama.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2012)"));
 BA.debugLineNum = 525;BA.debugLine="OverView2.Text = \"A mentally unstable WWII veter";
Debug.ShouldStop(4096);
drama.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
 BA.debugLineNum = 526;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(8192);
drama.mostCurrent._dramaimage2.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 527;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(16384);
drama.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("master.jpg"))).getObject()));
 BA.debugLineNum = 529;BA.debugLine="Drama3.Text = \"Her\"";
Debug.ShouldStop(65536);
drama.mostCurrent._drama3.runMethod(true,"setText",BA.ObjectToCharSequence("Her"));
 BA.debugLineNum = 530;BA.debugLine="Starter3.Text = \"Starring: Joaquin Phoenix, Roon";
Debug.ShouldStop(131072);
drama.mostCurrent._starter3.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
 BA.debugLineNum = 531;BA.debugLine="Year3.Text = \"(2013)\"";
Debug.ShouldStop(262144);
drama.mostCurrent._year3.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 532;BA.debugLine="OverView3.Text = \"In a near-future Los Angeles,";
Debug.ShouldStop(524288);
drama.mostCurrent._overview3.runMethod(true,"setText",BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
 BA.debugLineNum = 533;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
Debug.ShouldStop(1048576);
drama.mostCurrent._dramaimage3.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 534;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(2097152);
drama.mostCurrent._dramaimage3.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("her.jpg"))).getObject()));
 BA.debugLineNum = 536;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(8388608);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 537;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(16777216);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 538;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(33554432);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 539;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(67108864);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 541;BA.debugLine="p.Height = 85%y";
Debug.ShouldStop(268435456);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 85)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 542;BA.debugLine="Else If query.Contains(\"rooney mara\") Or query.Co";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("rooney mara")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("rooney")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("mara")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("sarah")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("sarah paulson")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("paulson"))))) { 
 BA.debugLineNum = 543;BA.debugLine="Drama1.Text = \"Carol\"";
Debug.ShouldStop(1073741824);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Carol"));
 BA.debugLineNum = 544;BA.debugLine="Starter1.Text = \"Starring: Rooney Mara, Cate Bla";
Debug.ShouldStop(-2147483648);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
 BA.debugLineNum = 545;BA.debugLine="Year1.Text = \"(2015)\"";
Debug.ShouldStop(1);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2015)"));
 BA.debugLineNum = 546;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
Debug.ShouldStop(2);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
 BA.debugLineNum = 547;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(4);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 548;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(8);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("carol.jpg"))).getObject()));
 BA.debugLineNum = 550;BA.debugLine="Drama2.Text = \"Blue Jasmine\"";
Debug.ShouldStop(32);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Blue Jasmine"));
 BA.debugLineNum = 551;BA.debugLine="Starter2.Text = \"Starring: Cate Blanchett, Sally";
Debug.ShouldStop(64);
drama.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
 BA.debugLineNum = 552;BA.debugLine="Year2.Text = \"(2013)\"";
Debug.ShouldStop(128);
drama.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 553;BA.debugLine="OverView2.Text = \"After losing her fortune and s";
Debug.ShouldStop(256);
drama.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
 BA.debugLineNum = 554;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(512);
drama.mostCurrent._dramaimage2.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 555;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(1024);
drama.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("bluejasmine.jpg"))).getObject()));
 BA.debugLineNum = 557;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(4096);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 558;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(8192);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 559;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(16384);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 560;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(32768);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 562;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(131072);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 565;BA.debugLine="Else If query.Contains(\"olvia coloman\") Or query.";
Debug.ShouldStop(1048576);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("olvia coloman")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("dakota johnson")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("jessie buckley")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("olvia")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("jessie ")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("dakota")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("buckley")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("coloman")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("johnson"))))) { 
 BA.debugLineNum = 566;BA.debugLine="Drama1.Text = \"Carol\"";
Debug.ShouldStop(2097152);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Carol"));
 BA.debugLineNum = 567;BA.debugLine="Starter1.Text = \"Starring: Rooney Mara, Cate Bla";
Debug.ShouldStop(4194304);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
 BA.debugLineNum = 568;BA.debugLine="Year1.Text = \"(2015)\"";
Debug.ShouldStop(8388608);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2015)"));
 BA.debugLineNum = 569;BA.debugLine="OverView1.Text = \"A solitary woman on vacation b";
Debug.ShouldStop(16777216);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity."));
 BA.debugLineNum = 570;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(33554432);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 571;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(67108864);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("carol.jpg"))).getObject()));
 BA.debugLineNum = 573;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(268435456);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 574;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(536870912);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 575;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(1073741824);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 576;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(-2147483648);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 578;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(2);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 580;BA.debugLine="Else If query.Contains(\"sally hawkins\") Or query.";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("sally hawkins")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("alec baldwin")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("sally")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("haswkins")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("alec")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("baldwin"))))) { 
 BA.debugLineNum = 581;BA.debugLine="Drama1.Text = \"Blue Jasmine\"";
Debug.ShouldStop(16);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Blue Jasmine"));
 BA.debugLineNum = 582;BA.debugLine="Starter1.Text = \"Starring: Cate Blanchett, Sally";
Debug.ShouldStop(32);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
 BA.debugLineNum = 583;BA.debugLine="Year1.Text = \"(2013)\"";
Debug.ShouldStop(64);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 584;BA.debugLine="OverView1.Text = \"After losing her fortune and s";
Debug.ShouldStop(128);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
 BA.debugLineNum = 585;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(256);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 586;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(512);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("bluejasmine.jpg"))).getObject()));
 BA.debugLineNum = 588;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(2048);
drama.mostCurrent._panelmovie2.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 589;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(4096);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 590;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(8192);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 591;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(16384);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 592;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(32768);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 594;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(131072);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 595;BA.debugLine="Else if query.Contains(\"michelle williams\") Or qu";
Debug.ShouldStop(262144);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("michelle williams")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("lucas hedges")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("michelle")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("williams")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("lucas")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("hedges"))))) { 
 BA.debugLineNum = 596;BA.debugLine="Drama1.Text = \"Manchester by the Sea\"";
Debug.ShouldStop(524288);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Manchester by the Sea"));
 BA.debugLineNum = 597;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Michel";
Debug.ShouldStop(1048576);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
 BA.debugLineNum = 598;BA.debugLine="Year1.Text = \"(2016)\"";
Debug.ShouldStop(2097152);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2016)"));
 BA.debugLineNum = 599;BA.debugLine="OverView1.Text = \"After the death of his brother";
Debug.ShouldStop(4194304);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
 BA.debugLineNum = 600;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(8388608);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 601;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(16777216);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("manchester.jpg"))).getObject()));
 BA.debugLineNum = 603;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(67108864);
drama.mostCurrent._panelmovie2.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 604;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(134217728);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 605;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(268435456);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 606;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(536870912);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 607;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(1073741824);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 609;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(1);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 610;BA.debugLine="Else if query.Contains(\"dustin hoffman\") Or query";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("dustin hoffman")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("dustin")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("justin henry")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("justin")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("henry"))))) { 
 BA.debugLineNum = 611;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
Debug.ShouldStop(4);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Kramer vs. Kramer"));
 BA.debugLineNum = 612;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
Debug.ShouldStop(8);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
 BA.debugLineNum = 613;BA.debugLine="Year1.Text = \"(1979)\"";
Debug.ShouldStop(16);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(1979)"));
 BA.debugLineNum = 614;BA.debugLine="OverView1.Text = \"In this emotionally charged co";
Debug.ShouldStop(32);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
 BA.debugLineNum = 615;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(64);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 616;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(128);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("kramer.jpg"))).getObject()));
 BA.debugLineNum = 618;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(512);
drama.mostCurrent._panelmovie2.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 619;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(1024);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 620;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(2048);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 621;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(4096);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 622;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(8192);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 624;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(32768);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 625;BA.debugLine="Else If query.Contains(\"philip seymour hoffman\")";
Debug.ShouldStop(65536);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("philip seymour hoffman")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("philip")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("seymour"))))) { 
 BA.debugLineNum = 626;BA.debugLine="Drama1.Text = \"Doubt\"";
Debug.ShouldStop(131072);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Doubt"));
 BA.debugLineNum = 627;BA.debugLine="Starter1.Text = \"Starring: Meryl Streep, Philip";
Debug.ShouldStop(262144);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Meryl Streep, Philip Seymour Hoffman, Amy Adams"));
 BA.debugLineNum = 628;BA.debugLine="Year1.Text = \"(2008)\"";
Debug.ShouldStop(524288);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2008)"));
 BA.debugLineNum = 629;BA.debugLine="OverView1.Text = \"In a Catholic school in the Br";
Debug.ShouldStop(1048576);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In a Catholic school in the Bronx, a strict nun becomes suspicious of a priest’s relationship with a student. As she digs deeper, the lines between truth and suspicion blur, leaving everyone in moral and emotional limbo."));
 BA.debugLineNum = 630;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(2097152);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 631;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(4194304);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("doubt.jpg"))).getObject()));
 BA.debugLineNum = 634;BA.debugLine="Drama2.Text = \"The Master\"";
Debug.ShouldStop(33554432);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("The Master"));
 BA.debugLineNum = 635;BA.debugLine="Starter2.Text = \"Starring: Philip Seymour Hoffma";
Debug.ShouldStop(67108864);
drama.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
 BA.debugLineNum = 636;BA.debugLine="Year2.Text = \"(2012)\"";
Debug.ShouldStop(134217728);
drama.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2012)"));
 BA.debugLineNum = 637;BA.debugLine="OverView2.Text = \"A mentally unstable WWII veter";
Debug.ShouldStop(268435456);
drama.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
 BA.debugLineNum = 638;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(536870912);
drama.mostCurrent._dramaimage2.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 639;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(1073741824);
drama.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("master.jpg"))).getObject()));
 BA.debugLineNum = 641;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(1);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 642;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(2);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 643;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(4);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 644;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(8);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 646;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(32);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 648;BA.debugLine="Else If query.Contains(\"hoffman\") Then";
Debug.ShouldStop(128);
if (_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("hoffman"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 649;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
Debug.ShouldStop(256);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Kramer vs. Kramer"));
 BA.debugLineNum = 650;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
Debug.ShouldStop(512);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
 BA.debugLineNum = 651;BA.debugLine="Year1.Text = \"(1979)\"";
Debug.ShouldStop(1024);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(1979)"));
 BA.debugLineNum = 652;BA.debugLine="OverView1.Text = \"In this emotionally charged co";
Debug.ShouldStop(2048);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
 BA.debugLineNum = 653;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(4096);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 654;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(8192);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("kramer.jpg"))).getObject()));
 BA.debugLineNum = 656;BA.debugLine="Drama2.Text = \"Doubt\"";
Debug.ShouldStop(32768);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Doubt"));
 BA.debugLineNum = 657;BA.debugLine="Starter2.Text = \"Starring: Meryl Streep, Philip";
Debug.ShouldStop(65536);
drama.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Meryl Streep, Philip Seymour Hoffman, Amy Adams"));
 BA.debugLineNum = 658;BA.debugLine="Year2.Text = \"(2008)\"";
Debug.ShouldStop(131072);
drama.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2008)"));
 BA.debugLineNum = 659;BA.debugLine="OverView2.Text = \"In a Catholic school in the Br";
Debug.ShouldStop(262144);
drama.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("In a Catholic school in the Bronx, a strict nun becomes suspicious of a priest’s relationship with a student. As she digs deeper, the lines between truth and suspicion blur, leaving everyone in moral and emotional limbo."));
 BA.debugLineNum = 660;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(524288);
drama.mostCurrent._dramaimage2.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 661;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(1048576);
drama.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("doubt.jpg"))).getObject()));
 BA.debugLineNum = 663;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(4194304);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 664;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(8388608);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 665;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(16777216);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 666;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(33554432);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 668;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(134217728);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),drama.mostCurrent.activityBA));
 }else {
 BA.debugLineNum = 671;BA.debugLine="MsgboxAsync(\"No results found for\" & \" \"\"\" & Use";
Debug.ShouldStop(1073741824);
drama.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("No results found for"),RemoteObject.createImmutable(" \""),_userinput,RemoteObject.createImmutable("\"")))),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable(""))),drama.processBA);
 }}}}}}}}}}}}}}}}}}}}}}}}}
;
 BA.debugLineNum = 676;BA.debugLine="p.Width = 100%x";
Debug.ShouldStop(8);
drama.mostCurrent._p.runMethod(true,"setWidth",drama.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),drama.mostCurrent.activityBA));
 BA.debugLineNum = 677;BA.debugLine="ScrollView1.Panel.Height = p.Height";
Debug.ShouldStop(16);
drama.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",drama.mostCurrent._p.runMethod(true,"getHeight"));
 BA.debugLineNum = 678;BA.debugLine="End Sub";
Debug.ShouldStop(32);
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
		Debug.PushSubsStack("SearchEngine_TextChanged (drama) ","drama",2,drama.mostCurrent.activityBA,drama.mostCurrent,684);
if (RapidSub.canDelegate("searchengine_textchanged")) { return b4a.example.drama.remoteMe.runUserSub(false, "drama","searchengine_textchanged", _old, _new);}
RemoteObject _query = RemoteObject.createImmutable("");
Debug.locals.put("Old", _old);
Debug.locals.put("New", _new);
 BA.debugLineNum = 684;BA.debugLine="Sub SearchEngine_TextChanged (Old As String, New A";
Debug.ShouldStop(2048);
 BA.debugLineNum = 685;BA.debugLine="Dim query As String = New.ToLowerCase.Trim";
Debug.ShouldStop(4096);
_query = _new.runMethod(true,"toLowerCase").runMethod(true,"trim");Debug.locals.put("query", _query);Debug.locals.put("query", _query);
 BA.debugLineNum = 687;BA.debugLine="If query = \"\" Then";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean("=",_query,BA.ObjectToString(""))) { 
 BA.debugLineNum = 689;BA.debugLine="p.Height = 210%y";
Debug.ShouldStop(65536);
drama.mostCurrent._p.runMethod(true,"setHeight",drama.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 210)),drama.mostCurrent.activityBA));
 BA.debugLineNum = 690;BA.debugLine="p.Width = 200%x";
Debug.ShouldStop(131072);
drama.mostCurrent._p.runMethod(true,"setWidth",drama.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 200)),drama.mostCurrent.activityBA));
 BA.debugLineNum = 691;BA.debugLine="ScrollView1.Panel.Height = p.Height";
Debug.ShouldStop(262144);
drama.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",drama.mostCurrent._p.runMethod(true,"getHeight"));
 BA.debugLineNum = 693;BA.debugLine="PanelMovie1.Visible = False";
Debug.ShouldStop(1048576);
drama.mostCurrent._panelmovie1.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 694;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(2097152);
drama.mostCurrent._panelmovie2.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 695;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(4194304);
drama.mostCurrent._panelmovie3.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 696;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(8388608);
drama.mostCurrent._panelmovie4.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 697;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(16777216);
drama.mostCurrent._panelmovie5.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 698;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(33554432);
drama.mostCurrent._panelmovie6.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 699;BA.debugLine="PanelMovie7.Visible = False";
Debug.ShouldStop(67108864);
drama.mostCurrent._panelmovie7.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 700;BA.debugLine="PanelMovie8.Visible = False";
Debug.ShouldStop(134217728);
drama.mostCurrent._panelmovie8.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 701;BA.debugLine="PanelMovie9.Visible = False";
Debug.ShouldStop(268435456);
drama.mostCurrent._panelmovie9.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 702;BA.debugLine="PanelMovie10.Visible = False";
Debug.ShouldStop(536870912);
drama.mostCurrent._panelmovie10.runMethod(true,"setVisible",drama.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 704;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
Debug.ShouldStop(-2147483648);
drama.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Kramer vs. Kramer"));
 BA.debugLineNum = 705;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
Debug.ShouldStop(1);
drama.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
 BA.debugLineNum = 706;BA.debugLine="Year1.Text = \"(1979)\"";
Debug.ShouldStop(2);
drama.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(1979)"));
 BA.debugLineNum = 707;BA.debugLine="OverView1.Text = \"In this emotionally charged co";
Debug.ShouldStop(4);
drama.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
 BA.debugLineNum = 708;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(8);
drama.mostCurrent._dramaimage1.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 709;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(16);
drama.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("kramer.jpg"))).getObject()));
 BA.debugLineNum = 711;BA.debugLine="Drama2.Text = \"Manchester by the Sea\"";
Debug.ShouldStop(64);
drama.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Manchester by the Sea"));
 BA.debugLineNum = 712;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michel";
Debug.ShouldStop(128);
drama.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
 BA.debugLineNum = 713;BA.debugLine="Year2.Text = \"(2016)\"";
Debug.ShouldStop(256);
drama.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2016)"));
 BA.debugLineNum = 714;BA.debugLine="OverView2.Text = \"After the death of his brother";
Debug.ShouldStop(512);
drama.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
 BA.debugLineNum = 715;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(1024);
drama.mostCurrent._dramaimage2.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 716;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(2048);
drama.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("manchester.jpg"))).getObject()));
 BA.debugLineNum = 718;BA.debugLine="Drama3.Text = \"The Master\"";
Debug.ShouldStop(8192);
drama.mostCurrent._drama3.runMethod(true,"setText",BA.ObjectToCharSequence("The Master"));
 BA.debugLineNum = 719;BA.debugLine="Starter3.Text = \"Starring: Philip Seymour Hoffma";
Debug.ShouldStop(16384);
drama.mostCurrent._starter3.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
 BA.debugLineNum = 720;BA.debugLine="Year3.Text = \"(2012)\"";
Debug.ShouldStop(32768);
drama.mostCurrent._year3.runMethod(true,"setText",BA.ObjectToCharSequence("(2012)"));
 BA.debugLineNum = 721;BA.debugLine="OverView3.Text = \"A mentally unstable WWII veter";
Debug.ShouldStop(65536);
drama.mostCurrent._overview3.runMethod(true,"setText",BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
 BA.debugLineNum = 722;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
Debug.ShouldStop(131072);
drama.mostCurrent._dramaimage3.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 723;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(262144);
drama.mostCurrent._dramaimage3.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("master.jpg"))).getObject()));
 BA.debugLineNum = 725;BA.debugLine="Drama4.Text = \"Million Dollar Baby\"";
Debug.ShouldStop(1048576);
drama.mostCurrent._drama4.runMethod(true,"setText",BA.ObjectToCharSequence("Million Dollar Baby"));
 BA.debugLineNum = 726;BA.debugLine="Starter4.Text = \"Starring: Morgan Freeman, Hilar";
Debug.ShouldStop(2097152);
drama.mostCurrent._starter4.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
 BA.debugLineNum = 727;BA.debugLine="Year4.Text = \"(2004)\"";
Debug.ShouldStop(4194304);
drama.mostCurrent._year4.runMethod(true,"setText",BA.ObjectToCharSequence("(2004)"));
 BA.debugLineNum = 728;BA.debugLine="OverView4.Text = \"A waitress with dreams of beco";
Debug.ShouldStop(8388608);
drama.mostCurrent._overview4.runMethod(true,"setText",BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
 BA.debugLineNum = 729;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
Debug.ShouldStop(16777216);
drama.mostCurrent._dramaimage4.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 730;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(33554432);
drama.mostCurrent._dramaimage4.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("millondolar.jpg"))).getObject()));
 BA.debugLineNum = 732;BA.debugLine="Drama5.Text = \"The Bridges of Madison County\"";
Debug.ShouldStop(134217728);
drama.mostCurrent._drama5.runMethod(true,"setText",BA.ObjectToCharSequence("The Bridges of Madison County"));
 BA.debugLineNum = 733;BA.debugLine="Starter5.Text = \"Starring: Clint Eastwood, Meryl";
Debug.ShouldStop(268435456);
drama.mostCurrent._starter5.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
 BA.debugLineNum = 734;BA.debugLine="Year5.Text = \"(1995)\"";
Debug.ShouldStop(536870912);
drama.mostCurrent._year5.runMethod(true,"setText",BA.ObjectToCharSequence("(1995)"));
 BA.debugLineNum = 735;BA.debugLine="OverView5.Text = \"A brief, passionate romance be";
Debug.ShouldStop(1073741824);
drama.mostCurrent._overview5.runMethod(true,"setText",BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
 BA.debugLineNum = 736;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
Debug.ShouldStop(-2147483648);
drama.mostCurrent._dramaimage5.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 737;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(1);
drama.mostCurrent._dramaimage5.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("bridges.jpg"))).getObject()));
 BA.debugLineNum = 740;BA.debugLine="Drama6.Text = \"Gone Baby Gone\"";
Debug.ShouldStop(8);
drama.mostCurrent._drama6.runMethod(true,"setText",BA.ObjectToCharSequence("Gone Baby Gone"));
 BA.debugLineNum = 741;BA.debugLine="Starter6.Text = \"Starring: Casey Affleck, Michel";
Debug.ShouldStop(16);
drama.mostCurrent._starter6.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
 BA.debugLineNum = 742;BA.debugLine="Year6.Text = \"(2007)\"";
Debug.ShouldStop(32);
drama.mostCurrent._year6.runMethod(true,"setText",BA.ObjectToCharSequence("(2007)"));
 BA.debugLineNum = 743;BA.debugLine="OverView6.Text = \"In a tough Boston neighborhood";
Debug.ShouldStop(64);
drama.mostCurrent._overview6.runMethod(true,"setText",BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
 BA.debugLineNum = 744;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
Debug.ShouldStop(128);
drama.mostCurrent._dramaimage6.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 745;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(256);
drama.mostCurrent._dramaimage6.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("gonebaby.jpg"))).getObject()));
 BA.debugLineNum = 747;BA.debugLine="Drama7.Text = \"Blue Jasmine\"";
Debug.ShouldStop(1024);
drama.mostCurrent._drama7.runMethod(true,"setText",BA.ObjectToCharSequence("Blue Jasmine"));
 BA.debugLineNum = 748;BA.debugLine="Starter7.Text = \"Starring: Cate Blanchett, Sally";
Debug.ShouldStop(2048);
drama.mostCurrent._starter7.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
 BA.debugLineNum = 749;BA.debugLine="Year7.Text = \"(2013)\"";
Debug.ShouldStop(4096);
drama.mostCurrent._year7.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 750;BA.debugLine="OverView7.Text = \"After losing her fortune and s";
Debug.ShouldStop(8192);
drama.mostCurrent._overview7.runMethod(true,"setText",BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
 BA.debugLineNum = 751;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
Debug.ShouldStop(16384);
drama.mostCurrent._dramaimage7.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 752;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(32768);
drama.mostCurrent._dramaimage7.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("bluejasmine.jpg"))).getObject()));
 BA.debugLineNum = 754;BA.debugLine="Drama8.Text = \"Her\"";
Debug.ShouldStop(131072);
drama.mostCurrent._drama8.runMethod(true,"setText",BA.ObjectToCharSequence("Her"));
 BA.debugLineNum = 755;BA.debugLine="Starter8.Text = \"Starring: Joaquin Phoenix, Roon";
Debug.ShouldStop(262144);
drama.mostCurrent._starter8.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
 BA.debugLineNum = 756;BA.debugLine="Year8.Text = \"(2013)\"";
Debug.ShouldStop(524288);
drama.mostCurrent._year8.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 757;BA.debugLine="OverView8.Text = \"In a near-future Los Angeles,";
Debug.ShouldStop(1048576);
drama.mostCurrent._overview8.runMethod(true,"setText",BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
 BA.debugLineNum = 758;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
Debug.ShouldStop(2097152);
drama.mostCurrent._dramaimage8.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 759;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(4194304);
drama.mostCurrent._dramaimage8.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("her.jpg"))).getObject()));
 BA.debugLineNum = 761;BA.debugLine="Drama9.Text = \"Carol\"";
Debug.ShouldStop(16777216);
drama.mostCurrent._drama9.runMethod(true,"setText",BA.ObjectToCharSequence("Carol"));
 BA.debugLineNum = 762;BA.debugLine="Starter9.Text = \"Starring: Rooney Mara, Cate Bla";
Debug.ShouldStop(33554432);
drama.mostCurrent._starter9.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
 BA.debugLineNum = 763;BA.debugLine="Year9.Text = \"(2015)\"";
Debug.ShouldStop(67108864);
drama.mostCurrent._year9.runMethod(true,"setText",BA.ObjectToCharSequence("(2015)"));
 BA.debugLineNum = 764;BA.debugLine="OverView9.Text = \"A chance encounter between a y";
Debug.ShouldStop(134217728);
drama.mostCurrent._overview9.runMethod(true,"setText",BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
 BA.debugLineNum = 765;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
Debug.ShouldStop(268435456);
drama.mostCurrent._dramaimage9.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 766;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(536870912);
drama.mostCurrent._dramaimage9.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("carol.jpg"))).getObject()));
 BA.debugLineNum = 768;BA.debugLine="Drama10.Text = \"The Lost Daughter\"";
Debug.ShouldStop(-2147483648);
drama.mostCurrent._drama10.runMethod(true,"setText",BA.ObjectToCharSequence("The Lost Daughter"));
 BA.debugLineNum = 769;BA.debugLine="Starter10.Text = \"Starring: Olivia Colman, Dakot";
Debug.ShouldStop(1);
drama.mostCurrent._starter10.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Olivia Colman, Dakota Johnson, Jessie Buckley"));
 BA.debugLineNum = 770;BA.debugLine="Year10.Text = \"(2021)\"";
Debug.ShouldStop(2);
drama.mostCurrent._year10.runMethod(true,"setText",BA.ObjectToCharSequence("(2021)"));
 BA.debugLineNum = 771;BA.debugLine="OverView10.Text = \"A solitary woman on vacation";
Debug.ShouldStop(4);
drama.mostCurrent._overview10.runMethod(true,"setText",BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity"));
 BA.debugLineNum = 772;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
Debug.ShouldStop(8);
drama.mostCurrent._dramaimage10.runMethod(true,"setGravity",drama.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 773;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(16);
drama.mostCurrent._dramaimage10.runMethod(false,"setBitmap",(drama.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(drama.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("lostdaughter.jpg"))).getObject()));
 };
 BA.debugLineNum = 779;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}