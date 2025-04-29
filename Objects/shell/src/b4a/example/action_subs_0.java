package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class action_subs_0 {


public static RemoteObject  _actionpage_click() throws Exception{
try {
		Debug.PushSubsStack("ActionPage_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,768);
if (RapidSub.canDelegate("actionpage_click")) { return b4a.example.action.remoteMe.runUserSub(false, "action","actionpage_click");}
 BA.debugLineNum = 768;BA.debugLine="Private Sub ActionPage_Click";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 770;BA.debugLine="End Sub";
Debug.ShouldStop(2);
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
		Debug.PushSubsStack("Activity_Create (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,108);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.action.remoteMe.runUserSub(false, "action","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 108;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(2048);
 BA.debugLineNum = 109;BA.debugLine="Activity.LoadLayout(\"action\") ' Layout contains S";
Debug.ShouldStop(4096);
action.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("action")),action.mostCurrent.activityBA);
 BA.debugLineNum = 111;BA.debugLine="p.Initialize(\"\")";
Debug.ShouldStop(16384);
action.mostCurrent._p.runVoidMethod ("Initialize",action.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 112;BA.debugLine="p.LoadLayout(\"panelview\")";
Debug.ShouldStop(32768);
action.mostCurrent._p.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("panelview")),action.mostCurrent.activityBA);
 BA.debugLineNum = 119;BA.debugLine="Drama1.Text = \"Crank\"";
Debug.ShouldStop(4194304);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Crank"));
 BA.debugLineNum = 120;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sma";
Debug.ShouldStop(8388608);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
 BA.debugLineNum = 121;BA.debugLine="Year1.Text = \"(2006)\"";
Debug.ShouldStop(16777216);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2006)"));
 BA.debugLineNum = 122;BA.debugLine="OverView1.Text = \"Professional assassin Chev Chel";
Debug.ShouldStop(33554432);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
 BA.debugLineNum = 123;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(67108864);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 124;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(134217728);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("crank.jpg"))).getObject()));
 BA.debugLineNum = 126;BA.debugLine="Drama2.Text = \"Sherlock Holmes \"";
Debug.ShouldStop(536870912);
action.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Sherlock Holmes "));
 BA.debugLineNum = 127;BA.debugLine="Starter2.Text = \"Starring: Robert Downey Jr., Jud";
Debug.ShouldStop(1073741824);
action.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
 BA.debugLineNum = 128;BA.debugLine="Year2.Text = \"(2008)\"";
Debug.ShouldStop(-2147483648);
action.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2008)"));
 BA.debugLineNum = 129;BA.debugLine="OverView2.Text = \"Detective Sherlock Holmes and h";
Debug.ShouldStop(1);
action.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
 BA.debugLineNum = 130;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(2);
action.mostCurrent._dramaimage2.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 131;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(4);
action.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("sherlockHolmes.jpg"))).getObject()));
 BA.debugLineNum = 133;BA.debugLine="Drama3.Text = \"The Transporter\"";
Debug.ShouldStop(16);
action.mostCurrent._drama3.runMethod(true,"setText",BA.ObjectToCharSequence("The Transporter"));
 BA.debugLineNum = 134;BA.debugLine="Starter3.Text = \"Starring: Jason Statham, Shu Qi,";
Debug.ShouldStop(32);
action.mostCurrent._starter3.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
 BA.debugLineNum = 135;BA.debugLine="Year3.Text = \"(2002)\"";
Debug.ShouldStop(64);
action.mostCurrent._year3.runMethod(true,"setText",BA.ObjectToCharSequence("(2002)"));
 BA.debugLineNum = 136;BA.debugLine="OverView3.Text = \"Frank Martin, who transports pa";
Debug.ShouldStop(128);
action.mostCurrent._overview3.runMethod(true,"setText",BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
 BA.debugLineNum = 137;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
Debug.ShouldStop(256);
action.mostCurrent._dramaimage3.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 138;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(512);
action.mostCurrent._dramaimage3.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("transporter.jpg"))).getObject()));
 BA.debugLineNum = 140;BA.debugLine="Drama4.Text = \"Avengers: Endgame\"";
Debug.ShouldStop(2048);
action.mostCurrent._drama4.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers: Endgame"));
 BA.debugLineNum = 141;BA.debugLine="Starter4.Text = \"Starring: Robert Downey Jr., Chr";
Debug.ShouldStop(4096);
action.mostCurrent._starter4.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
 BA.debugLineNum = 142;BA.debugLine="Year4.Text = \"(2019)\"";
Debug.ShouldStop(8192);
action.mostCurrent._year4.runMethod(true,"setText",BA.ObjectToCharSequence("(2019)"));
 BA.debugLineNum = 143;BA.debugLine="OverView4.Text = \"After the devastating events of";
Debug.ShouldStop(16384);
action.mostCurrent._overview4.runMethod(true,"setText",BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
 BA.debugLineNum = 144;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
Debug.ShouldStop(32768);
action.mostCurrent._dramaimage4.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 145;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(65536);
action.mostCurrent._dramaimage4.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("avengersEndgame.png"))).getObject()));
 BA.debugLineNum = 147;BA.debugLine="Drama5.Text = \"Logan\"";
Debug.ShouldStop(262144);
action.mostCurrent._drama5.runMethod(true,"setText",BA.ObjectToCharSequence("Logan"));
 BA.debugLineNum = 148;BA.debugLine="Starter5.Text = \"Starring: Hugh Jackman, Patrick";
Debug.ShouldStop(524288);
action.mostCurrent._starter5.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
 BA.debugLineNum = 149;BA.debugLine="Year5.Text = \"(2017)\"";
Debug.ShouldStop(1048576);
action.mostCurrent._year5.runMethod(true,"setText",BA.ObjectToCharSequence("(2017)"));
 BA.debugLineNum = 150;BA.debugLine="OverView5.Text = \"In a future where mutants are n";
Debug.ShouldStop(2097152);
action.mostCurrent._overview5.runMethod(true,"setText",BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
 BA.debugLineNum = 151;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
Debug.ShouldStop(4194304);
action.mostCurrent._dramaimage5.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 152;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(8388608);
action.mostCurrent._dramaimage5.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("logan.jpg"))).getObject()));
 BA.debugLineNum = 155;BA.debugLine="Drama6.Text = \"Iron Man\"";
Debug.ShouldStop(67108864);
action.mostCurrent._drama6.runMethod(true,"setText",BA.ObjectToCharSequence("Iron Man"));
 BA.debugLineNum = 156;BA.debugLine="Starter6.Text = \"Starring: Robert Downey Jr., Gwy";
Debug.ShouldStop(134217728);
action.mostCurrent._starter6.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
 BA.debugLineNum = 157;BA.debugLine="Year6.Text = \"(2008)\"";
Debug.ShouldStop(268435456);
action.mostCurrent._year6.runMethod(true,"setText",BA.ObjectToCharSequence("(2008)"));
 BA.debugLineNum = 158;BA.debugLine="OverView6.Text = \"After being held captive in an";
Debug.ShouldStop(536870912);
action.mostCurrent._overview6.runMethod(true,"setText",BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
 BA.debugLineNum = 159;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
Debug.ShouldStop(1073741824);
action.mostCurrent._dramaimage6.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 160;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(-2147483648);
action.mostCurrent._dramaimage6.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("ironman.jpg"))).getObject()));
 BA.debugLineNum = 162;BA.debugLine="Drama7.Text = \"X-Men\"";
Debug.ShouldStop(2);
action.mostCurrent._drama7.runMethod(true,"setText",BA.ObjectToCharSequence("X-Men"));
 BA.debugLineNum = 163;BA.debugLine="Starter7.Text = \"Starring: Patrick Stewart, Hugh";
Debug.ShouldStop(4);
action.mostCurrent._starter7.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
 BA.debugLineNum = 164;BA.debugLine="Year7.Text = \"(2000)\"";
Debug.ShouldStop(8);
action.mostCurrent._year7.runMethod(true,"setText",BA.ObjectToCharSequence("(2000)"));
 BA.debugLineNum = 165;BA.debugLine="OverView7.Text = \"In a world where mutants (evolv";
Debug.ShouldStop(16);
action.mostCurrent._overview7.runMethod(true,"setText",BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
 BA.debugLineNum = 166;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
Debug.ShouldStop(32);
action.mostCurrent._dramaimage7.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 167;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(64);
action.mostCurrent._dramaimage7.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("xmen.jpg"))).getObject()));
 BA.debugLineNum = 169;BA.debugLine="Drama8.Text = \"Mr. & Mrs. Smith \"";
Debug.ShouldStop(256);
action.mostCurrent._drama8.runMethod(true,"setText",BA.ObjectToCharSequence("Mr. & Mrs. Smith "));
 BA.debugLineNum = 170;BA.debugLine="Starter8.Text = \"Starring: Brad Pitt, Angelina Jo";
Debug.ShouldStop(512);
action.mostCurrent._starter8.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
 BA.debugLineNum = 171;BA.debugLine="Year8.Text = \"(2005)\"";
Debug.ShouldStop(1024);
action.mostCurrent._year8.runMethod(true,"setText",BA.ObjectToCharSequence("(2005)"));
 BA.debugLineNum = 172;BA.debugLine="OverView8.Text = \"A husband and wife struggle to";
Debug.ShouldStop(2048);
action.mostCurrent._overview8.runMethod(true,"setText",BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
 BA.debugLineNum = 173;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
Debug.ShouldStop(4096);
action.mostCurrent._dramaimage8.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 174;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(8192);
action.mostCurrent._dramaimage8.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("MrAndMrs.png"))).getObject()));
 BA.debugLineNum = 176;BA.debugLine="Drama9.Text = \"The Wolverine\"";
Debug.ShouldStop(32768);
action.mostCurrent._drama9.runMethod(true,"setText",BA.ObjectToCharSequence("The Wolverine"));
 BA.debugLineNum = 177;BA.debugLine="Starter9.Text = \"Starring: Hugh Jackman, Will Yun";
Debug.ShouldStop(65536);
action.mostCurrent._starter9.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
 BA.debugLineNum = 178;BA.debugLine="Year9.Text = \"(2015)\"";
Debug.ShouldStop(131072);
action.mostCurrent._year9.runMethod(true,"setText",BA.ObjectToCharSequence("(2015)"));
 BA.debugLineNum = 179;BA.debugLine="OverView9.Text = \"A chance encounter between a yo";
Debug.ShouldStop(262144);
action.mostCurrent._overview9.runMethod(true,"setText",BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
 BA.debugLineNum = 180;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
Debug.ShouldStop(524288);
action.mostCurrent._dramaimage9.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 181;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(1048576);
action.mostCurrent._dramaimage9.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("wolverine.png"))).getObject()));
 BA.debugLineNum = 183;BA.debugLine="Drama10.Text = \"Prisoners\"";
Debug.ShouldStop(4194304);
action.mostCurrent._drama10.runMethod(true,"setText",BA.ObjectToCharSequence("Prisoners"));
 BA.debugLineNum = 184;BA.debugLine="Starter10.Text = \"Starring: Hugh Jackman, Jake Gy";
Debug.ShouldStop(8388608);
action.mostCurrent._starter10.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
 BA.debugLineNum = 185;BA.debugLine="Year10.Text = \"(2013)\"";
Debug.ShouldStop(16777216);
action.mostCurrent._year10.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 186;BA.debugLine="OverView10.Text = \"A desperate father takes the l";
Debug.ShouldStop(33554432);
action.mostCurrent._overview10.runMethod(true,"setText",BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
 BA.debugLineNum = 187;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
Debug.ShouldStop(67108864);
action.mostCurrent._dramaimage10.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 188;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(134217728);
action.mostCurrent._dramaimage10.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("prisoners.jpg"))).getObject()));
 BA.debugLineNum = 190;BA.debugLine="ScrollView1.Panel.AddView(p, 0, 0, 200%x, 210%y)";
Debug.ShouldStop(536870912);
action.mostCurrent._scrollview1.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((action.mostCurrent._p.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(action.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 200)),action.mostCurrent.activityBA)),(Object)(action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 210)),action.mostCurrent.activityBA)));
 BA.debugLineNum = 191;BA.debugLine="ScrollView1.Panel.Height = p.Height";
Debug.ShouldStop(1073741824);
action.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",action.mostCurrent._p.runMethod(true,"getHeight"));
 BA.debugLineNum = 192;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
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
		Debug.PushSubsStack("Activity_Pause (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,757);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.action.remoteMe.runUserSub(false, "action","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 757;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 759;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
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
		Debug.PushSubsStack("Activity_Resume (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,753);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.action.remoteMe.runUserSub(false, "action","activity_resume");}
 BA.debugLineNum = 753;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(65536);
 BA.debugLineNum = 755;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
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
		Debug.PushSubsStack("DramaPage_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,772);
if (RapidSub.canDelegate("dramapage_click")) { return b4a.example.action.remoteMe.runUserSub(false, "action","dramapage_click");}
 BA.debugLineNum = 772;BA.debugLine="Private Sub DramaPage_Click";
Debug.ShouldStop(8);
 BA.debugLineNum = 773;BA.debugLine="StartActivity(Drama)";
Debug.ShouldStop(16);
action.mostCurrent.__c.runVoidMethod ("StartActivity",action.processBA,(Object)((action.mostCurrent._drama.getObject())));
 BA.debugLineNum = 774;BA.debugLine="Activity.Finish";
Debug.ShouldStop(32);
action.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 775;BA.debugLine="End Sub";
Debug.ShouldStop(64);
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
 //BA.debugLineNum = 13;BA.debugLine="Private ScrollView1 As ScrollView";
action.mostCurrent._scrollview1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");
 //BA.debugLineNum = 16;BA.debugLine="Private Drama1 As Label";
action.mostCurrent._drama1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private Drama2 As Label";
action.mostCurrent._drama2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private Drama3 As Label";
action.mostCurrent._drama3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private Drama4 As Label";
action.mostCurrent._drama4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private Drama5 As Label";
action.mostCurrent._drama5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Private Drama6 As Label";
action.mostCurrent._drama6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 22;BA.debugLine="Private Drama7 As Label";
action.mostCurrent._drama7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 23;BA.debugLine="Private Drama8 As Label";
action.mostCurrent._drama8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 24;BA.debugLine="Private Drama9 As Label";
action.mostCurrent._drama9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Private Drama10 As Label";
action.mostCurrent._drama10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 30;BA.debugLine="Private DramaImage1 As ImageView";
action.mostCurrent._dramaimage1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 31;BA.debugLine="Private DramaImage2 As ImageView";
action.mostCurrent._dramaimage2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 32;BA.debugLine="Private DramaImage3 As ImageView";
action.mostCurrent._dramaimage3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 33;BA.debugLine="Private DramaImage4 As ImageView";
action.mostCurrent._dramaimage4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 34;BA.debugLine="Private DramaImage5 As ImageView";
action.mostCurrent._dramaimage5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 35;BA.debugLine="Private DramaImage6 As ImageView";
action.mostCurrent._dramaimage6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 36;BA.debugLine="Private DramaImage7 As ImageView";
action.mostCurrent._dramaimage7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 37;BA.debugLine="Private DramaImage8 As ImageView";
action.mostCurrent._dramaimage8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 38;BA.debugLine="Private DramaImage9 As ImageView";
action.mostCurrent._dramaimage9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 39;BA.debugLine="Private DramaImage10 As ImageView";
action.mostCurrent._dramaimage10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 44;BA.debugLine="Private SearchBtn As Button";
action.mostCurrent._searchbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 45;BA.debugLine="Private SearchEngine As EditText";
action.mostCurrent._searchengine = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 48;BA.debugLine="Dim p As Panel";
action.mostCurrent._p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 49;BA.debugLine="Private Panel1 As Panel";
action.mostCurrent._panel1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 50;BA.debugLine="Private PanelMovie1 As Panel";
action.mostCurrent._panelmovie1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 51;BA.debugLine="Private PanelMovie2 As Panel";
action.mostCurrent._panelmovie2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 52;BA.debugLine="Private PanelMovie3 As Panel";
action.mostCurrent._panelmovie3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 53;BA.debugLine="Private PanelMovie4 As Panel";
action.mostCurrent._panelmovie4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 54;BA.debugLine="Private PanelMovie5 As Panel";
action.mostCurrent._panelmovie5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 55;BA.debugLine="Private PanelMovie6 As Panel";
action.mostCurrent._panelmovie6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 56;BA.debugLine="Private PanelMovie7 As Panel";
action.mostCurrent._panelmovie7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 57;BA.debugLine="Private PanelMovie8 As Panel";
action.mostCurrent._panelmovie8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 58;BA.debugLine="Private PanelMovie9 As Panel";
action.mostCurrent._panelmovie9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 59;BA.debugLine="Private PanelMovie10 As Panel";
action.mostCurrent._panelmovie10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 64;BA.debugLine="Private DramaPage As Label";
action.mostCurrent._dramapage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 65;BA.debugLine="Private HomePage As Label";
action.mostCurrent._homepage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 66;BA.debugLine="Private SciFiPage As Label";
action.mostCurrent._scifipage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 67;BA.debugLine="Private ActionPage As Label";
action.mostCurrent._actionpage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 70;BA.debugLine="Private Starter1 As Label";
action.mostCurrent._starter1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 71;BA.debugLine="Private Starter2 As Label";
action.mostCurrent._starter2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 72;BA.debugLine="Private Starter3 As Label";
action.mostCurrent._starter3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 73;BA.debugLine="Private Starter4 As Label";
action.mostCurrent._starter4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 74;BA.debugLine="Private Starter5 As Label";
action.mostCurrent._starter5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 75;BA.debugLine="Private Starter6 As Label";
action.mostCurrent._starter6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 76;BA.debugLine="Private Starter7 As Label";
action.mostCurrent._starter7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 77;BA.debugLine="Private Starter8 As Label";
action.mostCurrent._starter8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 78;BA.debugLine="Private Starter9 As Label";
action.mostCurrent._starter9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 79;BA.debugLine="Private Starter10 As Label";
action.mostCurrent._starter10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 82;BA.debugLine="Private OverView1 As Label";
action.mostCurrent._overview1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 83;BA.debugLine="Private OverView2 As Label";
action.mostCurrent._overview2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 84;BA.debugLine="Private OverView3 As Label";
action.mostCurrent._overview3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 85;BA.debugLine="Private OverView4 As Label";
action.mostCurrent._overview4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 86;BA.debugLine="Private OverView5 As Label";
action.mostCurrent._overview5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 87;BA.debugLine="Private OverView6 As Label";
action.mostCurrent._overview6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 88;BA.debugLine="Private OverView7 As Label";
action.mostCurrent._overview7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 89;BA.debugLine="Private OverView8 As Label";
action.mostCurrent._overview8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 90;BA.debugLine="Private OverView9 As Label";
action.mostCurrent._overview9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 91;BA.debugLine="Private OverView10 As Label";
action.mostCurrent._overview10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 93;BA.debugLine="Private Year1 As Label";
action.mostCurrent._year1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 94;BA.debugLine="Private Year2 As Label";
action.mostCurrent._year2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 95;BA.debugLine="Private Year3 As Label";
action.mostCurrent._year3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 96;BA.debugLine="Private Year4 As Label";
action.mostCurrent._year4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 97;BA.debugLine="Private Year5 As Label";
action.mostCurrent._year5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 98;BA.debugLine="Private Year6 As Label";
action.mostCurrent._year6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 99;BA.debugLine="Private Year7 As Label";
action.mostCurrent._year7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 100;BA.debugLine="Private Year8 As Label";
action.mostCurrent._year8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 101;BA.debugLine="Private Year9 As Label";
action.mostCurrent._year9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 102;BA.debugLine="Private Year10 As Label";
action.mostCurrent._year10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 106;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _homepage_click() throws Exception{
try {
		Debug.PushSubsStack("HomePage_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,777);
if (RapidSub.canDelegate("homepage_click")) { return b4a.example.action.remoteMe.runUserSub(false, "action","homepage_click");}
 BA.debugLineNum = 777;BA.debugLine="Private Sub HomePage_Click";
Debug.ShouldStop(256);
 BA.debugLineNum = 778;BA.debugLine="StartActivity(Main)";
Debug.ShouldStop(512);
action.mostCurrent.__c.runVoidMethod ("StartActivity",action.processBA,(Object)((action.mostCurrent._main.getObject())));
 BA.debugLineNum = 779;BA.debugLine="Activity.Finish";
Debug.ShouldStop(1024);
action.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 780;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
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
		Debug.PushSubsStack("PanelMovie1_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,782);
if (RapidSub.canDelegate("panelmovie1_click")) { b4a.example.action.remoteMe.runUserSub(false, "action","panelmovie1_click"); return;}
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
public ResumableSub_PanelMovie1_Click(b4a.example.action parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.action parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie1_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,782);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 783;BA.debugLine="Try";
Debug.ShouldStop(16384);
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
 BA.debugLineNum = 784;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(32768);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),action.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 785;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(65536);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", action.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "action", "panelmovie1_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 786;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
Debug.ShouldStop(131072);
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
 BA.debugLineNum = 787;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(262144);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 788;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
Debug.ShouldStop(524288);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt0479884/")));
 BA.debugLineNum = 789;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(1048576);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 790;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(2097152);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",action.processBA,(Object)((_i.getObject())));
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
 BA.debugLineNum = 794;BA.debugLine="Log(LastException)";
Debug.ShouldStop(33554432);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","53080204",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",action.mostCurrent.activityBA)),0);
 BA.debugLineNum = 795;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(67108864);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),action.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 798;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",action.processBA, e0.toString());}
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
		Debug.PushSubsStack("PanelMovie10_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,937);
if (RapidSub.canDelegate("panelmovie10_click")) { b4a.example.action.remoteMe.runUserSub(false, "action","panelmovie10_click"); return;}
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
public ResumableSub_PanelMovie10_Click(b4a.example.action parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.action parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie10_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,937);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 938;BA.debugLine="Try";
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
 BA.debugLineNum = 939;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(1024);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),action.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 940;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(2048);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", action.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "action", "panelmovie10_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 941;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
 BA.debugLineNum = 942;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(8192);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 943;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
Debug.ShouldStop(16384);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt1392214/")));
 BA.debugLineNum = 944;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(32768);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 945;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(65536);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",action.processBA,(Object)((_i.getObject())));
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
 BA.debugLineNum = 948;BA.debugLine="Log(LastException)";
Debug.ShouldStop(524288);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","53670027",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",action.mostCurrent.activityBA)),0);
 BA.debugLineNum = 949;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(1048576);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),action.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 952;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",action.processBA, e0.toString());}
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
		Debug.PushSubsStack("PanelMovie2_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,801);
if (RapidSub.canDelegate("panelmovie2_click")) { b4a.example.action.remoteMe.runUserSub(false, "action","panelmovie2_click"); return;}
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
public ResumableSub_PanelMovie2_Click(b4a.example.action parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.action parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie2_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,801);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 802;BA.debugLine="Try";
Debug.ShouldStop(2);
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
 BA.debugLineNum = 803;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(4);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),action.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 804;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(8);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", action.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "action", "panelmovie2_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 805;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
Debug.ShouldStop(16);
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
 BA.debugLineNum = 806;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(32);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 807;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
Debug.ShouldStop(64);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt0988045/")));
 BA.debugLineNum = 808;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(128);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 809;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(256);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",action.processBA,(Object)((_i.getObject())));
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
 BA.debugLineNum = 812;BA.debugLine="Log(LastException)";
Debug.ShouldStop(2048);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","53145739",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",action.mostCurrent.activityBA)),0);
 BA.debugLineNum = 813;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(4096);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),action.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 816;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",action.processBA, e0.toString());}
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
		Debug.PushSubsStack("PanelMovie3_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,818);
if (RapidSub.canDelegate("panelmovie3_click")) { b4a.example.action.remoteMe.runUserSub(false, "action","panelmovie3_click"); return;}
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
public ResumableSub_PanelMovie3_Click(b4a.example.action parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.action parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie3_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,818);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 819;BA.debugLine="Try";
Debug.ShouldStop(262144);
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
 BA.debugLineNum = 820;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(524288);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),action.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 821;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(1048576);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", action.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "action", "panelmovie3_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 822;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
Debug.ShouldStop(2097152);
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
 BA.debugLineNum = 823;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(4194304);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 824;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
Debug.ShouldStop(8388608);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt4154796/")));
 BA.debugLineNum = 825;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(16777216);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 826;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(33554432);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",action.processBA,(Object)((_i.getObject())));
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
 BA.debugLineNum = 829;BA.debugLine="Log(LastException)";
Debug.ShouldStop(268435456);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","53211275",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",action.mostCurrent.activityBA)),0);
 BA.debugLineNum = 830;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(536870912);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),action.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 833;BA.debugLine="End Sub";
Debug.ShouldStop(1);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",action.processBA, e0.toString());}
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
		Debug.PushSubsStack("PanelMovie4_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,835);
if (RapidSub.canDelegate("panelmovie4_click")) { b4a.example.action.remoteMe.runUserSub(false, "action","panelmovie4_click"); return;}
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
public ResumableSub_PanelMovie4_Click(b4a.example.action parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.action parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie4_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,835);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 836;BA.debugLine="Try";
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
 BA.debugLineNum = 837;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(16);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),action.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 838;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(32);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", action.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "action", "panelmovie4_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 839;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
 BA.debugLineNum = 840;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(128);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 841;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
Debug.ShouldStop(256);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt3315342/")));
 BA.debugLineNum = 842;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(512);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 843;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(1024);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",action.processBA,(Object)((_i.getObject())));
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
 BA.debugLineNum = 846;BA.debugLine="Log(LastException)";
Debug.ShouldStop(8192);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","53276811",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",action.mostCurrent.activityBA)),0);
 BA.debugLineNum = 847;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(16384);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),action.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 850;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",action.processBA, e0.toString());}
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
		Debug.PushSubsStack("PanelMovie5_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,852);
if (RapidSub.canDelegate("panelmovie5_click")) { b4a.example.action.remoteMe.runUserSub(false, "action","panelmovie5_click"); return;}
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
public ResumableSub_PanelMovie5_Click(b4a.example.action parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.action parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie5_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,852);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 853;BA.debugLine="Try";
Debug.ShouldStop(1048576);
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
 BA.debugLineNum = 854;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(2097152);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),action.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 855;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(4194304);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", action.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "action", "panelmovie5_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 856;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
Debug.ShouldStop(8388608);
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
 BA.debugLineNum = 857;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(16777216);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 858;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
Debug.ShouldStop(33554432);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt0371746/")));
 BA.debugLineNum = 859;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(67108864);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 860;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(134217728);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",action.processBA,(Object)((_i.getObject())));
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
 BA.debugLineNum = 863;BA.debugLine="Log(LastException)";
Debug.ShouldStop(1073741824);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","53342347",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",action.mostCurrent.activityBA)),0);
 BA.debugLineNum = 864;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(-2147483648);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),action.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 867;BA.debugLine="End Sub";
Debug.ShouldStop(4);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",action.processBA, e0.toString());}
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
		Debug.PushSubsStack("PanelMovie6_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,869);
if (RapidSub.canDelegate("panelmovie6_click")) { b4a.example.action.remoteMe.runUserSub(false, "action","panelmovie6_click"); return;}
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
public ResumableSub_PanelMovie6_Click(b4a.example.action parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.action parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie6_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,869);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 870;BA.debugLine="Try";
Debug.ShouldStop(32);
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
 BA.debugLineNum = 871;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(64);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),action.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 872;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(128);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", action.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "action", "panelmovie6_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 873;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
Debug.ShouldStop(256);
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
 BA.debugLineNum = 874;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(512);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 875;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
Debug.ShouldStop(1024);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt0120903/")));
 BA.debugLineNum = 876;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(2048);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 877;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(4096);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",action.processBA,(Object)((_i.getObject())));
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
 BA.debugLineNum = 880;BA.debugLine="Log(LastException)";
Debug.ShouldStop(32768);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","53407883",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",action.mostCurrent.activityBA)),0);
 BA.debugLineNum = 881;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(65536);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),action.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 884;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",action.processBA, e0.toString());}
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
		Debug.PushSubsStack("PanelMovie7_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,886);
if (RapidSub.canDelegate("panelmovie7_click")) { b4a.example.action.remoteMe.runUserSub(false, "action","panelmovie7_click"); return;}
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
public ResumableSub_PanelMovie7_Click(b4a.example.action parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.action parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie7_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,886);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 887;BA.debugLine="Try";
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
 BA.debugLineNum = 888;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(8388608);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),action.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 889;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(16777216);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", action.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "action", "panelmovie7_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 890;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
 BA.debugLineNum = 891;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(67108864);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 892;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
Debug.ShouldStop(134217728);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt2334873/")));
 BA.debugLineNum = 893;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(268435456);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 894;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(536870912);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",action.processBA,(Object)((_i.getObject())));
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
 BA.debugLineNum = 897;BA.debugLine="Log(LastException)";
Debug.ShouldStop(1);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","53473419",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",action.mostCurrent.activityBA)),0);
 BA.debugLineNum = 898;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(2);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),action.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 901;BA.debugLine="End Sub";
Debug.ShouldStop(16);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",action.processBA, e0.toString());}
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
		Debug.PushSubsStack("PanelMovie8_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,903);
if (RapidSub.canDelegate("panelmovie8_click")) { b4a.example.action.remoteMe.runUserSub(false, "action","panelmovie8_click"); return;}
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
public ResumableSub_PanelMovie8_Click(b4a.example.action parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.action parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie8_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,903);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 904;BA.debugLine="Try";
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
 BA.debugLineNum = 905;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(256);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),action.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 906;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(512);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", action.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "action", "panelmovie8_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 907;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
 BA.debugLineNum = 908;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(2048);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 909;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
Debug.ShouldStop(4096);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt0356910/")));
 BA.debugLineNum = 910;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(8192);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 911;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(16384);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",action.processBA,(Object)((_i.getObject())));
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
 BA.debugLineNum = 914;BA.debugLine="Log(LastException)";
Debug.ShouldStop(131072);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","53538955",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",action.mostCurrent.activityBA)),0);
 BA.debugLineNum = 915;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(262144);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),action.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 918;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",action.processBA, e0.toString());}
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
		Debug.PushSubsStack("PanelMovie9_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,920);
if (RapidSub.canDelegate("panelmovie9_click")) { b4a.example.action.remoteMe.runUserSub(false, "action","panelmovie9_click"); return;}
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
public ResumableSub_PanelMovie9_Click(b4a.example.action parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.action parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie9_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,920);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 921;BA.debugLine="Try";
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
 BA.debugLineNum = 922;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(33554432);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),action.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 923;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(67108864);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", action.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "action", "panelmovie9_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 924;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
 BA.debugLineNum = 925;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(268435456);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 926;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
Debug.ShouldStop(536870912);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt1430132/")));
 BA.debugLineNum = 927;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(1073741824);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 928;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(-2147483648);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",action.processBA,(Object)((_i.getObject())));
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
 BA.debugLineNum = 931;BA.debugLine="Log(LastException)";
Debug.ShouldStop(4);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","53604491",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",action.mostCurrent.activityBA)),0);
 BA.debugLineNum = 932;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(8);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),action.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 935;BA.debugLine="End Sub";
Debug.ShouldStop(64);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",action.processBA, e0.toString());}
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
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _scifipage_click() throws Exception{
try {
		Debug.PushSubsStack("SciFiPage_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,763);
if (RapidSub.canDelegate("scifipage_click")) { return b4a.example.action.remoteMe.runUserSub(false, "action","scifipage_click");}
 BA.debugLineNum = 763;BA.debugLine="Private Sub SciFiPage_Click";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 764;BA.debugLine="StartActivity(SciFi)";
Debug.ShouldStop(134217728);
action.mostCurrent.__c.runVoidMethod ("StartActivity",action.processBA,(Object)((action.mostCurrent._scifi.getObject())));
 BA.debugLineNum = 765;BA.debugLine="Activity.Finish";
Debug.ShouldStop(268435456);
action.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 766;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
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
		Debug.PushSubsStack("SearchBtn_Click (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,194);
if (RapidSub.canDelegate("searchbtn_click")) { return b4a.example.action.remoteMe.runUserSub(false, "action","searchbtn_click");}
 BA.debugLineNum = 194;BA.debugLine="Private Sub SearchBtn_Click";
Debug.ShouldStop(2);
 BA.debugLineNum = 195;BA.debugLine="SearchNow";
Debug.ShouldStop(4);
_searchnow();
 BA.debugLineNum = 196;BA.debugLine="End Sub";
Debug.ShouldStop(8);
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
		Debug.PushSubsStack("SearchEngine_TextChanged (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,653);
if (RapidSub.canDelegate("searchengine_textchanged")) { return b4a.example.action.remoteMe.runUserSub(false, "action","searchengine_textchanged", _old, _new);}
RemoteObject _query = RemoteObject.createImmutable("");
Debug.locals.put("Old", _old);
Debug.locals.put("New", _new);
 BA.debugLineNum = 653;BA.debugLine="Sub SearchEngine_TextChanged (Old As String, New A";
Debug.ShouldStop(4096);
 BA.debugLineNum = 654;BA.debugLine="Dim query As String = New.ToLowerCase.Trim";
Debug.ShouldStop(8192);
_query = _new.runMethod(true,"toLowerCase").runMethod(true,"trim");Debug.locals.put("query", _query);Debug.locals.put("query", _query);
 BA.debugLineNum = 656;BA.debugLine="If query = \"\" Then";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean("=",_query,BA.ObjectToString(""))) { 
 BA.debugLineNum = 659;BA.debugLine="p.Height = 210%y";
Debug.ShouldStop(262144);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 210)),action.mostCurrent.activityBA));
 BA.debugLineNum = 660;BA.debugLine="p.Width = 200%x";
Debug.ShouldStop(524288);
action.mostCurrent._p.runMethod(true,"setWidth",action.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 200)),action.mostCurrent.activityBA));
 BA.debugLineNum = 661;BA.debugLine="ScrollView1.Panel.Height = p.Height";
Debug.ShouldStop(1048576);
action.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",action.mostCurrent._p.runMethod(true,"getHeight"));
 BA.debugLineNum = 663;BA.debugLine="PanelMovie1.Visible = True";
Debug.ShouldStop(4194304);
action.mostCurrent._panelmovie1.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 664;BA.debugLine="PanelMovie2.Visible = True";
Debug.ShouldStop(8388608);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 665;BA.debugLine="PanelMovie3.Visible = True";
Debug.ShouldStop(16777216);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 666;BA.debugLine="PanelMovie4.Visible = True";
Debug.ShouldStop(33554432);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 667;BA.debugLine="PanelMovie5.Visible = True";
Debug.ShouldStop(67108864);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 668;BA.debugLine="PanelMovie6.Visible = True";
Debug.ShouldStop(134217728);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 669;BA.debugLine="PanelMovie7.Visible = True";
Debug.ShouldStop(268435456);
action.mostCurrent._panelmovie7.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 670;BA.debugLine="PanelMovie8.Visible = True";
Debug.ShouldStop(536870912);
action.mostCurrent._panelmovie8.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 671;BA.debugLine="PanelMovie9.Visible = True";
Debug.ShouldStop(1073741824);
action.mostCurrent._panelmovie9.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 672;BA.debugLine="PanelMovie10.Visible = True";
Debug.ShouldStop(-2147483648);
action.mostCurrent._panelmovie10.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 676;BA.debugLine="Drama1.Text = \"Crank\"";
Debug.ShouldStop(8);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Crank"));
 BA.debugLineNum = 677;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
Debug.ShouldStop(16);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
 BA.debugLineNum = 678;BA.debugLine="Year1.Text = \"(2006)\"";
Debug.ShouldStop(32);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2006)"));
 BA.debugLineNum = 679;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
Debug.ShouldStop(64);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
 BA.debugLineNum = 680;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(128);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 681;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(256);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("crank.jpg"))).getObject()));
 BA.debugLineNum = 683;BA.debugLine="Drama2.Text = \"Sherlock Holmes\"";
Debug.ShouldStop(1024);
action.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Sherlock Holmes"));
 BA.debugLineNum = 684;BA.debugLine="Starter2.Text = \"Starring: Robert Downey Jr., Ju";
Debug.ShouldStop(2048);
action.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
 BA.debugLineNum = 685;BA.debugLine="Year2.Text = \"(2008)\"";
Debug.ShouldStop(4096);
action.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2008)"));
 BA.debugLineNum = 686;BA.debugLine="OverView2.Text = \"Detective Sherlock Holmes and";
Debug.ShouldStop(8192);
action.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
 BA.debugLineNum = 687;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(16384);
action.mostCurrent._dramaimage2.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 688;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(32768);
action.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("sherlockHolmes.jpg"))).getObject()));
 BA.debugLineNum = 690;BA.debugLine="Drama3.Text = \"The Transporter\"";
Debug.ShouldStop(131072);
action.mostCurrent._drama3.runMethod(true,"setText",BA.ObjectToCharSequence("The Transporter"));
 BA.debugLineNum = 691;BA.debugLine="Starter3.Text = \"Starring: Jason Statham, Shu Qi";
Debug.ShouldStop(262144);
action.mostCurrent._starter3.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
 BA.debugLineNum = 692;BA.debugLine="Year3.Text = \"(2002)\"";
Debug.ShouldStop(524288);
action.mostCurrent._year3.runMethod(true,"setText",BA.ObjectToCharSequence("(2002)"));
 BA.debugLineNum = 693;BA.debugLine="OverView3.Text = \"Frank Martin, who transports p";
Debug.ShouldStop(1048576);
action.mostCurrent._overview3.runMethod(true,"setText",BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
 BA.debugLineNum = 694;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
Debug.ShouldStop(2097152);
action.mostCurrent._dramaimage3.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 695;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(4194304);
action.mostCurrent._dramaimage3.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("transporter.jpg"))).getObject()));
 BA.debugLineNum = 697;BA.debugLine="Drama4.Text = \"Avengers: Endgame\"";
Debug.ShouldStop(16777216);
action.mostCurrent._drama4.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers: Endgame"));
 BA.debugLineNum = 698;BA.debugLine="Starter4.Text = \"Starring: Robert Downey Jr., Ch";
Debug.ShouldStop(33554432);
action.mostCurrent._starter4.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
 BA.debugLineNum = 699;BA.debugLine="Year4.Text = \"(2019)\"";
Debug.ShouldStop(67108864);
action.mostCurrent._year4.runMethod(true,"setText",BA.ObjectToCharSequence("(2019)"));
 BA.debugLineNum = 700;BA.debugLine="OverView4.Text = \"After the devastating events o";
Debug.ShouldStop(134217728);
action.mostCurrent._overview4.runMethod(true,"setText",BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
 BA.debugLineNum = 701;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
Debug.ShouldStop(268435456);
action.mostCurrent._dramaimage4.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 702;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(536870912);
action.mostCurrent._dramaimage4.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("avengersEndgame.png"))).getObject()));
 BA.debugLineNum = 704;BA.debugLine="Drama5.Text = \"Logan\"";
Debug.ShouldStop(-2147483648);
action.mostCurrent._drama5.runMethod(true,"setText",BA.ObjectToCharSequence("Logan"));
 BA.debugLineNum = 705;BA.debugLine="Starter5.Text = \"Starring: Hugh Jackman, Patrick";
Debug.ShouldStop(1);
action.mostCurrent._starter5.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
 BA.debugLineNum = 706;BA.debugLine="Year5.Text = \"(2017)\"";
Debug.ShouldStop(2);
action.mostCurrent._year5.runMethod(true,"setText",BA.ObjectToCharSequence("(2017)"));
 BA.debugLineNum = 707;BA.debugLine="OverView5.Text = \"In a future where mutants are";
Debug.ShouldStop(4);
action.mostCurrent._overview5.runMethod(true,"setText",BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
 BA.debugLineNum = 708;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
Debug.ShouldStop(8);
action.mostCurrent._dramaimage5.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 709;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(16);
action.mostCurrent._dramaimage5.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("logan.jpg"))).getObject()));
 BA.debugLineNum = 712;BA.debugLine="Drama6.Text = \"Iron Man\"";
Debug.ShouldStop(128);
action.mostCurrent._drama6.runMethod(true,"setText",BA.ObjectToCharSequence("Iron Man"));
 BA.debugLineNum = 713;BA.debugLine="Starter6.Text = \"Starring: Robert Downey Jr., Gw";
Debug.ShouldStop(256);
action.mostCurrent._starter6.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
 BA.debugLineNum = 714;BA.debugLine="Year6.Text = \"(2008)\"";
Debug.ShouldStop(512);
action.mostCurrent._year6.runMethod(true,"setText",BA.ObjectToCharSequence("(2008)"));
 BA.debugLineNum = 715;BA.debugLine="OverView6.Text = \"After being held captive in an";
Debug.ShouldStop(1024);
action.mostCurrent._overview6.runMethod(true,"setText",BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
 BA.debugLineNum = 716;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
Debug.ShouldStop(2048);
action.mostCurrent._dramaimage6.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 717;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(4096);
action.mostCurrent._dramaimage6.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("ironman.jpg"))).getObject()));
 BA.debugLineNum = 719;BA.debugLine="Drama7.Text = \"X-Men\"";
Debug.ShouldStop(16384);
action.mostCurrent._drama7.runMethod(true,"setText",BA.ObjectToCharSequence("X-Men"));
 BA.debugLineNum = 720;BA.debugLine="Starter7.Text = \"Starring: Patrick Stewart, Hugh";
Debug.ShouldStop(32768);
action.mostCurrent._starter7.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
 BA.debugLineNum = 721;BA.debugLine="Year7.Text = \"(2000)\"";
Debug.ShouldStop(65536);
action.mostCurrent._year7.runMethod(true,"setText",BA.ObjectToCharSequence("(2000)"));
 BA.debugLineNum = 722;BA.debugLine="OverView7.Text = \"In a world where mutants (evol";
Debug.ShouldStop(131072);
action.mostCurrent._overview7.runMethod(true,"setText",BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
 BA.debugLineNum = 723;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
Debug.ShouldStop(262144);
action.mostCurrent._dramaimage7.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 724;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(524288);
action.mostCurrent._dramaimage7.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("xmen.jpg"))).getObject()));
 BA.debugLineNum = 726;BA.debugLine="Drama8.Text = \"Mr. & Mrs. Smith\"";
Debug.ShouldStop(2097152);
action.mostCurrent._drama8.runMethod(true,"setText",BA.ObjectToCharSequence("Mr. & Mrs. Smith"));
 BA.debugLineNum = 727;BA.debugLine="Starter8.Text = \"Starring: Brad Pitt, Angelina J";
Debug.ShouldStop(4194304);
action.mostCurrent._starter8.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
 BA.debugLineNum = 728;BA.debugLine="Year8.Text = \"(2005)\"";
Debug.ShouldStop(8388608);
action.mostCurrent._year8.runMethod(true,"setText",BA.ObjectToCharSequence("(2005)"));
 BA.debugLineNum = 729;BA.debugLine="OverView8.Text = \"A husband and wife struggle to";
Debug.ShouldStop(16777216);
action.mostCurrent._overview8.runMethod(true,"setText",BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
 BA.debugLineNum = 730;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
Debug.ShouldStop(33554432);
action.mostCurrent._dramaimage8.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 731;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(67108864);
action.mostCurrent._dramaimage8.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("MrAndMrs.png"))).getObject()));
 BA.debugLineNum = 733;BA.debugLine="Drama9.Text = \"The Wolverine\"";
Debug.ShouldStop(268435456);
action.mostCurrent._drama9.runMethod(true,"setText",BA.ObjectToCharSequence("The Wolverine"));
 BA.debugLineNum = 734;BA.debugLine="Starter9.Text = \"Starring: Hugh Jackman, Will Yu";
Debug.ShouldStop(536870912);
action.mostCurrent._starter9.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
 BA.debugLineNum = 735;BA.debugLine="Year9.Text = \"(2015)\"";
Debug.ShouldStop(1073741824);
action.mostCurrent._year9.runMethod(true,"setText",BA.ObjectToCharSequence("(2015)"));
 BA.debugLineNum = 736;BA.debugLine="OverView9.Text = \"A chance encounter between a y";
Debug.ShouldStop(-2147483648);
action.mostCurrent._overview9.runMethod(true,"setText",BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
 BA.debugLineNum = 737;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
Debug.ShouldStop(1);
action.mostCurrent._dramaimage9.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 738;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(2);
action.mostCurrent._dramaimage9.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("wolverine.png"))).getObject()));
 BA.debugLineNum = 740;BA.debugLine="Drama10.Text = \"Prisoners\"";
Debug.ShouldStop(8);
action.mostCurrent._drama10.runMethod(true,"setText",BA.ObjectToCharSequence("Prisoners"));
 BA.debugLineNum = 741;BA.debugLine="Starter10.Text = \"Starring: Hugh Jackman, Jake G";
Debug.ShouldStop(16);
action.mostCurrent._starter10.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
 BA.debugLineNum = 742;BA.debugLine="Year10.Text = \"(2013)\"";
Debug.ShouldStop(32);
action.mostCurrent._year10.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 743;BA.debugLine="OverView10.Text = \"A desperate father takes the";
Debug.ShouldStop(64);
action.mostCurrent._overview10.runMethod(true,"setText",BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
 BA.debugLineNum = 744;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
Debug.ShouldStop(128);
action.mostCurrent._dramaimage10.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 745;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(256);
action.mostCurrent._dramaimage10.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("prisoners.jpg"))).getObject()));
 };
 BA.debugLineNum = 751;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _searchnow() throws Exception{
try {
		Debug.PushSubsStack("SearchNow (action) ","action",3,action.mostCurrent.activityBA,action.mostCurrent,199);
if (RapidSub.canDelegate("searchnow")) { return b4a.example.action.remoteMe.runUserSub(false, "action","searchnow");}
RemoteObject _query = RemoteObject.createImmutable("");
RemoteObject _userinput = RemoteObject.createImmutable("");
 BA.debugLineNum = 199;BA.debugLine="Sub SearchNow";
Debug.ShouldStop(64);
 BA.debugLineNum = 200;BA.debugLine="Dim query As String = SearchEngine.Text.ToLowerCa";
Debug.ShouldStop(128);
_query = action.mostCurrent._searchengine.runMethod(true,"getText").runMethod(true,"toLowerCase").runMethod(true,"trim");Debug.locals.put("query", _query);Debug.locals.put("query", _query);
 BA.debugLineNum = 203;BA.debugLine="Dim UserInput As String = SearchEngine.Text";
Debug.ShouldStop(1024);
_userinput = action.mostCurrent._searchengine.runMethod(true,"getText");Debug.locals.put("UserInput", _userinput);Debug.locals.put("UserInput", _userinput);
 BA.debugLineNum = 206;BA.debugLine="If query.Contains(\"crank\") Then";
Debug.ShouldStop(8192);
if (_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("crank"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 208;BA.debugLine="Drama1.Text = \"Crank\"";
Debug.ShouldStop(32768);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Crank"));
 BA.debugLineNum = 209;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
Debug.ShouldStop(65536);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
 BA.debugLineNum = 210;BA.debugLine="Year1.Text = \"(2006)\"";
Debug.ShouldStop(131072);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2006)"));
 BA.debugLineNum = 211;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
Debug.ShouldStop(262144);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
 BA.debugLineNum = 212;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(524288);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 213;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(1048576);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("crank.jpg"))).getObject()));
 BA.debugLineNum = 215;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(4194304);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 216;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(8388608);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 217;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(16777216);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 218;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(33554432);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 219;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(67108864);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 221;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(268435456);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 223;BA.debugLine="Else If query.Contains(\"sherlock\") Or query.Conta";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("sherlock")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("sherlock holmes"))))) { 
 BA.debugLineNum = 225;BA.debugLine="Drama1.Text = \"Sherlock Holmes \"";
Debug.ShouldStop(1);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Sherlock Holmes "));
 BA.debugLineNum = 226;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ju";
Debug.ShouldStop(2);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
 BA.debugLineNum = 227;BA.debugLine="Year1.Text = \"(2008)\"";
Debug.ShouldStop(4);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2008)"));
 BA.debugLineNum = 228;BA.debugLine="OverView1.Text = \"Detective Sherlock Holmes and";
Debug.ShouldStop(8);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
 BA.debugLineNum = 229;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(16);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 230;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(32);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("sherlockHolmes.jpg"))).getObject()));
 BA.debugLineNum = 232;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(128);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 233;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(256);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 234;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(512);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 235;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(1024);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 236;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(2048);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 238;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(8192);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 240;BA.debugLine="Else If query.Contains(\"the transporter\") Or quer";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("the transporter")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("transporter"))))) { 
 BA.debugLineNum = 242;BA.debugLine="Drama1.Text = \"The Transporter\"";
Debug.ShouldStop(131072);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("The Transporter"));
 BA.debugLineNum = 243;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Shu Qi";
Debug.ShouldStop(262144);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
 BA.debugLineNum = 244;BA.debugLine="Year1.Text = \"(2002)\"";
Debug.ShouldStop(524288);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2002)"));
 BA.debugLineNum = 245;BA.debugLine="OverView1.Text = \"Frank Martin, who transports p";
Debug.ShouldStop(1048576);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
 BA.debugLineNum = 246;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(2097152);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 247;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(4194304);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("transporter.jpg"))).getObject()));
 BA.debugLineNum = 249;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(16777216);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 250;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(33554432);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 251;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(67108864);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 252;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(134217728);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 253;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(268435456);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 255;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(1073741824);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 257;BA.debugLine="Else If query.Contains(\"avengers endgame\") Or que";
Debug.ShouldStop(1);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("avengers endgame")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("avengers")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("endgame"))))) { 
 BA.debugLineNum = 259;BA.debugLine="Drama1.Text = \"Avengers: Endgame\"";
Debug.ShouldStop(4);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers: Endgame"));
 BA.debugLineNum = 260;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ch";
Debug.ShouldStop(8);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
 BA.debugLineNum = 261;BA.debugLine="Year1.Text = \"(2019)\"";
Debug.ShouldStop(16);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2019)"));
 BA.debugLineNum = 262;BA.debugLine="OverView1.Text = \"After the devastating events o";
Debug.ShouldStop(32);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
 BA.debugLineNum = 263;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(64);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 264;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(128);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("avengersEndgame.png"))).getObject()));
 BA.debugLineNum = 266;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(512);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 267;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(1024);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 268;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(2048);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 269;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(4096);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 270;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(8192);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 272;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(32768);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 274;BA.debugLine="Else If query.Contains(\"logan\") Then";
Debug.ShouldStop(131072);
if (_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("logan"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 276;BA.debugLine="Drama1.Text = \"Logan\"";
Debug.ShouldStop(524288);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Logan"));
 BA.debugLineNum = 277;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
Debug.ShouldStop(1048576);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
 BA.debugLineNum = 278;BA.debugLine="Year1.Text = \"(2017)\"";
Debug.ShouldStop(2097152);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2017)"));
 BA.debugLineNum = 279;BA.debugLine="OverView1.Text = \"In a future where mutants are";
Debug.ShouldStop(4194304);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
 BA.debugLineNum = 280;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(8388608);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 281;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(16777216);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("logan.jpg"))).getObject()));
 BA.debugLineNum = 283;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(67108864);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 284;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(134217728);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 285;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(268435456);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 286;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(536870912);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 287;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(1073741824);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 289;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(1);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 291;BA.debugLine="Else If query.Contains(\"iron man\") Or query.Conta";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("iron man")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("man")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("iron"))))) { 
 BA.debugLineNum = 293;BA.debugLine="Drama1.Text = \"Iron Man\"";
Debug.ShouldStop(16);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Iron Man"));
 BA.debugLineNum = 294;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Gw";
Debug.ShouldStop(32);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
 BA.debugLineNum = 295;BA.debugLine="Year1.Text = \"(2008)\"";
Debug.ShouldStop(64);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2008)"));
 BA.debugLineNum = 296;BA.debugLine="OverView1.Text = \"After being held captive in an";
Debug.ShouldStop(128);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
 BA.debugLineNum = 297;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(256);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 298;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(512);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("ironman.jpg"))).getObject()));
 BA.debugLineNum = 300;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(2048);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 301;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(4096);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 302;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(8192);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 303;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(16384);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 304;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(32768);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 306;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(131072);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 308;BA.debugLine="Else If query.Contains(\"x-men\") Or query.Contains";
Debug.ShouldStop(524288);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("x-men")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("men")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("xmen")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("x"))))) { 
 BA.debugLineNum = 310;BA.debugLine="Drama7.Text = \"X-Men\"";
Debug.ShouldStop(2097152);
action.mostCurrent._drama7.runMethod(true,"setText",BA.ObjectToCharSequence("X-Men"));
 BA.debugLineNum = 311;BA.debugLine="Starter7.Text = \"Starring: Patrick Stewart, Hugh";
Debug.ShouldStop(4194304);
action.mostCurrent._starter7.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
 BA.debugLineNum = 312;BA.debugLine="Year7.Text = \"(2000)\"";
Debug.ShouldStop(8388608);
action.mostCurrent._year7.runMethod(true,"setText",BA.ObjectToCharSequence("(2000)"));
 BA.debugLineNum = 313;BA.debugLine="OverView7.Text = \"In a world where mutants (evol";
Debug.ShouldStop(16777216);
action.mostCurrent._overview7.runMethod(true,"setText",BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
 BA.debugLineNum = 314;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
Debug.ShouldStop(33554432);
action.mostCurrent._dramaimage7.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 315;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(67108864);
action.mostCurrent._dramaimage7.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("xmen.jpg"))).getObject()));
 BA.debugLineNum = 317;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(268435456);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 318;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(536870912);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 319;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(1073741824);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 320;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(-2147483648);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 321;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(1);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 323;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(4);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 325;BA.debugLine="Else If query.Contains(\"mr & mrs smith\") Or query";
Debug.ShouldStop(16);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("mr & mrs smith")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("mr and mrs")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("smith")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("mrs")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("mr"))))) { 
 BA.debugLineNum = 327;BA.debugLine="Drama1.Text = \"Mr. & Mrs. Smith \"";
Debug.ShouldStop(64);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Mr. & Mrs. Smith "));
 BA.debugLineNum = 328;BA.debugLine="Starter1.Text = \"Starring: Brad Pitt, Angelina J";
Debug.ShouldStop(128);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
 BA.debugLineNum = 329;BA.debugLine="Year1.Text = \"(2005)\"";
Debug.ShouldStop(256);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2005)"));
 BA.debugLineNum = 330;BA.debugLine="OverView1.Text = \"A husband and wife struggle to";
Debug.ShouldStop(512);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
 BA.debugLineNum = 331;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(1024);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 332;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(2048);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("MrAndMrs.png"))).getObject()));
 BA.debugLineNum = 334;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(8192);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 335;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(16384);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 336;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(32768);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 337;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(65536);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 338;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(131072);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 340;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(524288);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 342;BA.debugLine="Else If query.Contains(\"the wolverine\") Or query.";
Debug.ShouldStop(2097152);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("the wolverine")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("wolverine"))))) { 
 BA.debugLineNum = 344;BA.debugLine="Drama1.Text = \"The Wolverine\"";
Debug.ShouldStop(8388608);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("The Wolverine"));
 BA.debugLineNum = 345;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Will Yu";
Debug.ShouldStop(16777216);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
 BA.debugLineNum = 346;BA.debugLine="Year1.Text = \"(2015)\"";
Debug.ShouldStop(33554432);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2015)"));
 BA.debugLineNum = 347;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
Debug.ShouldStop(67108864);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
 BA.debugLineNum = 348;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(134217728);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 349;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(268435456);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("wolverine.png"))).getObject()));
 BA.debugLineNum = 351;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(1073741824);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 352;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(-2147483648);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 353;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(1);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 354;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(2);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 355;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(4);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 357;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(16);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 359;BA.debugLine="Else If query.Contains(\"prisoners\") Or query.Cont";
Debug.ShouldStop(64);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("prisoners")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("prisoner")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("pri"))))) { 
 BA.debugLineNum = 361;BA.debugLine="Drama10.Text = \"Prisoners\"";
Debug.ShouldStop(256);
action.mostCurrent._drama10.runMethod(true,"setText",BA.ObjectToCharSequence("Prisoners"));
 BA.debugLineNum = 362;BA.debugLine="Starter10.Text = \"Starring: Hugh Jackman, Jake G";
Debug.ShouldStop(512);
action.mostCurrent._starter10.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
 BA.debugLineNum = 363;BA.debugLine="Year10.Text = \"(2013)\"";
Debug.ShouldStop(1024);
action.mostCurrent._year10.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 364;BA.debugLine="OverView10.Text = \"A desperate father takes the";
Debug.ShouldStop(2048);
action.mostCurrent._overview10.runMethod(true,"setText",BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
 BA.debugLineNum = 365;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
Debug.ShouldStop(4096);
action.mostCurrent._dramaimage10.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 366;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(8192);
action.mostCurrent._dramaimage10.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("prisoners.jpg"))).getObject()));
 BA.debugLineNum = 368;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(32768);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 369;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(65536);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 370;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(131072);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 371;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(262144);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 372;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(524288);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 374;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(2097152);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 376;BA.debugLine="Else If query.Contains(\"jason statham\") Or query.";
Debug.ShouldStop(8388608);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("jason statham")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("jason")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("statham"))))) { 
 BA.debugLineNum = 377;BA.debugLine="Drama1.Text = \"Crank\"";
Debug.ShouldStop(16777216);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Crank"));
 BA.debugLineNum = 378;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
Debug.ShouldStop(33554432);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
 BA.debugLineNum = 379;BA.debugLine="Year1.Text = \"(2006)\"";
Debug.ShouldStop(67108864);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2006)"));
 BA.debugLineNum = 380;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
Debug.ShouldStop(134217728);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
 BA.debugLineNum = 381;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(268435456);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 382;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(536870912);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("crank.jpg"))).getObject()));
 BA.debugLineNum = 384;BA.debugLine="Drama2.Text = \"The Transporter\"";
Debug.ShouldStop(-2147483648);
action.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("The Transporter"));
 BA.debugLineNum = 385;BA.debugLine="Starter2.Text = \"Starring: Jason Statham, Shu Qi";
Debug.ShouldStop(1);
action.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
 BA.debugLineNum = 386;BA.debugLine="Year2.Text = \"(2002)\"";
Debug.ShouldStop(2);
action.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2002)"));
 BA.debugLineNum = 387;BA.debugLine="OverView2.Text = \"Frank Martin, who transports p";
Debug.ShouldStop(4);
action.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
 BA.debugLineNum = 388;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(8);
action.mostCurrent._dramaimage2.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 389;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(16);
action.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("transporter.jpg"))).getObject()));
 BA.debugLineNum = 391;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(64);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 392;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(128);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 393;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(256);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 395;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(1024);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 397;BA.debugLine="Else If query.Contains(\"Robert downey jr\") Or que";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("Robert downey jr")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("robert")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("downey")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("downey jr"))))) { 
 BA.debugLineNum = 398;BA.debugLine="Drama1.Text = \"Sherlock Holmes \"";
Debug.ShouldStop(8192);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Sherlock Holmes "));
 BA.debugLineNum = 399;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ju";
Debug.ShouldStop(16384);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
 BA.debugLineNum = 400;BA.debugLine="Year1.Text = \"(2008)\"";
Debug.ShouldStop(32768);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2008)"));
 BA.debugLineNum = 401;BA.debugLine="OverView1.Text = \"Detective Sherlock Holmes and";
Debug.ShouldStop(65536);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
 BA.debugLineNum = 402;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(131072);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 403;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(262144);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("sherlockHolmes.jpg"))).getObject()));
 BA.debugLineNum = 405;BA.debugLine="Drama2.Text = \"Avengers: Endgame\"";
Debug.ShouldStop(1048576);
action.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers: Endgame"));
 BA.debugLineNum = 406;BA.debugLine="Starter2.Text = \"Starring: Robert Downey Jr., Ch";
Debug.ShouldStop(2097152);
action.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
 BA.debugLineNum = 407;BA.debugLine="Year2.Text = \"(2019)\"";
Debug.ShouldStop(4194304);
action.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2019)"));
 BA.debugLineNum = 408;BA.debugLine="OverView2.Text = \"After the devastating events o";
Debug.ShouldStop(8388608);
action.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
 BA.debugLineNum = 409;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(16777216);
action.mostCurrent._dramaimage2.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 410;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(33554432);
action.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("avengersEndgame.png"))).getObject()));
 BA.debugLineNum = 412;BA.debugLine="Drama3.Text = \"Iron Man\"";
Debug.ShouldStop(134217728);
action.mostCurrent._drama3.runMethod(true,"setText",BA.ObjectToCharSequence("Iron Man"));
 BA.debugLineNum = 413;BA.debugLine="Starter3.Text = \"Starring: Robert Downey Jr., Gw";
Debug.ShouldStop(268435456);
action.mostCurrent._starter3.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
 BA.debugLineNum = 414;BA.debugLine="Year3.Text = \"(2008)\"";
Debug.ShouldStop(536870912);
action.mostCurrent._year3.runMethod(true,"setText",BA.ObjectToCharSequence("(2008)"));
 BA.debugLineNum = 415;BA.debugLine="OverView3.Text = \"After being held captive in an";
Debug.ShouldStop(1073741824);
action.mostCurrent._overview3.runMethod(true,"setText",BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
 BA.debugLineNum = 416;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
Debug.ShouldStop(-2147483648);
action.mostCurrent._dramaimage3.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 417;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(1);
action.mostCurrent._dramaimage3.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("ironman.jpg"))).getObject()));
 BA.debugLineNum = 419;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(4);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 420;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(8);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 421;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(16);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 423;BA.debugLine="p.Height = 85%y";
Debug.ShouldStop(64);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 85)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 425;BA.debugLine="Else If query.Contains(\"patrick stewart\") Or quer";
Debug.ShouldStop(256);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("patrick stewart")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("patrick")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("stewart"))))) { 
 BA.debugLineNum = 426;BA.debugLine="Drama1.Text = \"Logan\"";
Debug.ShouldStop(512);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Logan"));
 BA.debugLineNum = 427;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
Debug.ShouldStop(1024);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
 BA.debugLineNum = 428;BA.debugLine="Year1.Text = \"(2017)\"";
Debug.ShouldStop(2048);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2017)"));
 BA.debugLineNum = 429;BA.debugLine="OverView1.Text = \"In a future where mutants are";
Debug.ShouldStop(4096);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
 BA.debugLineNum = 430;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(8192);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 431;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(16384);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("logan.jpg"))).getObject()));
 BA.debugLineNum = 433;BA.debugLine="Drama2.Text = \"X-Men\"";
Debug.ShouldStop(65536);
action.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("X-Men"));
 BA.debugLineNum = 434;BA.debugLine="Starter2.Text = \"Starring: Patrick Stewart, Hugh";
Debug.ShouldStop(131072);
action.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
 BA.debugLineNum = 435;BA.debugLine="Year2.Text = \"(2000)\"";
Debug.ShouldStop(262144);
action.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2000)"));
 BA.debugLineNum = 436;BA.debugLine="OverView2.Text = \"In a world where mutants (evol";
Debug.ShouldStop(524288);
action.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
 BA.debugLineNum = 437;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(1048576);
action.mostCurrent._dramaimage2.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 438;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(2097152);
action.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("xmen.jpg"))).getObject()));
 BA.debugLineNum = 440;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(8388608);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 441;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(16777216);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 442;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(33554432);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 443;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(67108864);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 445;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(268435456);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 447;BA.debugLine="Else If query.Contains(\"hugh jackman\") Or query.C";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("hugh jackman")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("hugh")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("jackman"))))) { 
 BA.debugLineNum = 448;BA.debugLine="Drama1.Text = \"Logan\"";
Debug.ShouldStop(-2147483648);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Logan"));
 BA.debugLineNum = 449;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
Debug.ShouldStop(1);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
 BA.debugLineNum = 450;BA.debugLine="Year1.Text = \"(2017)\"";
Debug.ShouldStop(2);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2017)"));
 BA.debugLineNum = 451;BA.debugLine="OverView1.Text = \"In a future where mutants are";
Debug.ShouldStop(4);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
 BA.debugLineNum = 452;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(8);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 453;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(16);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("logan.jpg"))).getObject()));
 BA.debugLineNum = 455;BA.debugLine="Drama2.Text = \"X-Men\"";
Debug.ShouldStop(64);
action.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("X-Men"));
 BA.debugLineNum = 456;BA.debugLine="Starter2.Text = \"Starring: Patrick Stewart, Hugh";
Debug.ShouldStop(128);
action.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
 BA.debugLineNum = 457;BA.debugLine="Year2.Text = \"(2000)\"";
Debug.ShouldStop(256);
action.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2000)"));
 BA.debugLineNum = 458;BA.debugLine="OverView2.Text = \"In a world where mutants (evol";
Debug.ShouldStop(512);
action.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
 BA.debugLineNum = 459;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(1024);
action.mostCurrent._dramaimage2.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 460;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(2048);
action.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("xmen.jpg"))).getObject()));
 BA.debugLineNum = 462;BA.debugLine="Drama3.Text = \"The Wolverine\"";
Debug.ShouldStop(8192);
action.mostCurrent._drama3.runMethod(true,"setText",BA.ObjectToCharSequence("The Wolverine"));
 BA.debugLineNum = 463;BA.debugLine="Starter3.Text = \"Starring: Hugh Jackman, Will Yu";
Debug.ShouldStop(16384);
action.mostCurrent._starter3.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
 BA.debugLineNum = 464;BA.debugLine="Year3.Text = \"(2015)\"";
Debug.ShouldStop(32768);
action.mostCurrent._year3.runMethod(true,"setText",BA.ObjectToCharSequence("(2015)"));
 BA.debugLineNum = 465;BA.debugLine="OverView3.Text = \"A chance encounter between a y";
Debug.ShouldStop(65536);
action.mostCurrent._overview3.runMethod(true,"setText",BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
 BA.debugLineNum = 466;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
Debug.ShouldStop(131072);
action.mostCurrent._dramaimage3.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 467;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(262144);
action.mostCurrent._dramaimage3.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("wolverine.png"))).getObject()));
 BA.debugLineNum = 469;BA.debugLine="Drama4.Text = \"Prisoners\"";
Debug.ShouldStop(1048576);
action.mostCurrent._drama4.runMethod(true,"setText",BA.ObjectToCharSequence("Prisoners"));
 BA.debugLineNum = 470;BA.debugLine="Starter4.Text = \"Starring: Hugh Jackman, Jake Gy";
Debug.ShouldStop(2097152);
action.mostCurrent._starter4.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
 BA.debugLineNum = 471;BA.debugLine="Year4.Text = \"(2013)\"";
Debug.ShouldStop(4194304);
action.mostCurrent._year4.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 472;BA.debugLine="OverView4.Text = \"A desperate father takes the l";
Debug.ShouldStop(8388608);
action.mostCurrent._overview4.runMethod(true,"setText",BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
 BA.debugLineNum = 473;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
Debug.ShouldStop(16777216);
action.mostCurrent._dramaimage4.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 474;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(33554432);
action.mostCurrent._dramaimage4.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("prisoners.jpg"))).getObject()));
 BA.debugLineNum = 476;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(134217728);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 477;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(268435456);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 479;BA.debugLine="p.Height = 85%y";
Debug.ShouldStop(1073741824);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 85)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 481;BA.debugLine="Else If query.Contains(\"amy smart\") Or query.Cont";
Debug.ShouldStop(1);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("amy smart")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("amy")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("smart")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("carlos sanz")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("carlos")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("sanz"))))) { 
 BA.debugLineNum = 482;BA.debugLine="Drama1.Text = \"Crank\"";
Debug.ShouldStop(2);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Crank"));
 BA.debugLineNum = 483;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
Debug.ShouldStop(4);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
 BA.debugLineNum = 484;BA.debugLine="Year1.Text = \"(2006)\"";
Debug.ShouldStop(8);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2006)"));
 BA.debugLineNum = 485;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
Debug.ShouldStop(16);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
 BA.debugLineNum = 486;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(32);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 487;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(64);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("crank.jpg"))).getObject()));
 BA.debugLineNum = 489;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(256);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 490;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(512);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 491;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(1024);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 492;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(2048);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 493;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(4096);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 495;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(16384);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 496;BA.debugLine="Else If query.Contains(\"jude law\") Or query.Conta";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("jude law")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("jude")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("law")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("rachel mcadams")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("rachel")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("mcadams"))))) { 
 BA.debugLineNum = 497;BA.debugLine="Drama1.Text = \"Sherlock Holmes \"";
Debug.ShouldStop(65536);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Sherlock Holmes "));
 BA.debugLineNum = 498;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ju";
Debug.ShouldStop(131072);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
 BA.debugLineNum = 499;BA.debugLine="Year1.Text = \"(2008)\"";
Debug.ShouldStop(262144);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2008)"));
 BA.debugLineNum = 500;BA.debugLine="OverView1.Text = \"Detective Sherlock Holmes and";
Debug.ShouldStop(524288);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
 BA.debugLineNum = 501;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(1048576);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 502;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(2097152);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("sherlockHolmes.jpg"))).getObject()));
 BA.debugLineNum = 504;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(8388608);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 505;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(16777216);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 506;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(33554432);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 507;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(67108864);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 508;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(134217728);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 510;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(536870912);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 511;BA.debugLine="Else if query.Contains(\"shu qi\") Or query.Contain";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("shu qi")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("shu")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("qi")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("matt")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("schulze")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("matt schulze"))))) { 
 BA.debugLineNum = 512;BA.debugLine="Drama1.Text = \"The Transporter\"";
Debug.ShouldStop(-2147483648);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("The Transporter"));
 BA.debugLineNum = 513;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Shu Qi";
Debug.ShouldStop(1);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
 BA.debugLineNum = 514;BA.debugLine="Year1.Text = \"(2002)\"";
Debug.ShouldStop(2);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2002)"));
 BA.debugLineNum = 515;BA.debugLine="OverView1.Text = \"Frank Martin, who transports p";
Debug.ShouldStop(4);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
 BA.debugLineNum = 516;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(8);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 517;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(16);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("transporter.jpg"))).getObject()));
 BA.debugLineNum = 519;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(64);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 520;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(128);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 521;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(256);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 522;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(512);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 523;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(1024);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 525;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(4096);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 526;BA.debugLine="Else if query.Contains(\"chris evans\") Or query.Co";
Debug.ShouldStop(8192);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("chris evans")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("chris")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("evans")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("mark ruffalo")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("mark")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("ruffalo"))))) { 
 BA.debugLineNum = 527;BA.debugLine="Drama1.Text = \"Avengers: Endgame\"";
Debug.ShouldStop(16384);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Avengers: Endgame"));
 BA.debugLineNum = 528;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ch";
Debug.ShouldStop(32768);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
 BA.debugLineNum = 529;BA.debugLine="Year1.Text = \"(2019)\"";
Debug.ShouldStop(65536);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2019)"));
 BA.debugLineNum = 530;BA.debugLine="OverView1.Text = \"After the devastating events o";
Debug.ShouldStop(131072);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
 BA.debugLineNum = 531;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(262144);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 532;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(524288);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("avengersEndgame.png"))).getObject()));
 BA.debugLineNum = 534;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(2097152);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 535;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(4194304);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 536;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(8388608);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 537;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(16777216);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 538;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(33554432);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 540;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(134217728);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 541;BA.debugLine="Else if query.Contains(\"dafne keen\") Or query.Con";
Debug.ShouldStop(268435456);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("dafne keen")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("dafne")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("keen"))))) { 
 BA.debugLineNum = 542;BA.debugLine="Drama1.Text = \"Logan\"";
Debug.ShouldStop(536870912);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Logan"));
 BA.debugLineNum = 543;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
Debug.ShouldStop(1073741824);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
 BA.debugLineNum = 544;BA.debugLine="Year1.Text = \"(2017)\"";
Debug.ShouldStop(-2147483648);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2017)"));
 BA.debugLineNum = 545;BA.debugLine="OverView1.Text = \"In a future where mutants are";
Debug.ShouldStop(1);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
 BA.debugLineNum = 546;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(2);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 547;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(4);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("logan.jpg"))).getObject()));
 BA.debugLineNum = 549;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(16);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 550;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(32);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 551;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(64);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 552;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(128);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 553;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(256);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 555;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(1024);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 557;BA.debugLine="Else if query.Contains(\"gwyneth paltrow\") Or quer";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("gwyneth paltrow")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("gwyneth")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("paltrow")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("terrence howard")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("terrence")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("howard"))))) { 
 BA.debugLineNum = 558;BA.debugLine="Drama1.Text = \"Iron Man\"";
Debug.ShouldStop(8192);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Iron Man"));
 BA.debugLineNum = 559;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Gw";
Debug.ShouldStop(16384);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
 BA.debugLineNum = 560;BA.debugLine="Year1.Text = \"(2008)\"";
Debug.ShouldStop(32768);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2008)"));
 BA.debugLineNum = 561;BA.debugLine="OverView1.Text = \"After being held captive in an";
Debug.ShouldStop(65536);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
 BA.debugLineNum = 562;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(131072);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 563;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(262144);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("ironman.jpg"))).getObject()));
 BA.debugLineNum = 565;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(1048576);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 566;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(2097152);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 567;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(4194304);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 568;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(8388608);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 569;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(16777216);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 571;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(67108864);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 573;BA.debugLine="Else if query.Contains(\"ian mckellen\") Or query.C";
Debug.ShouldStop(268435456);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("ian mckellen")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("ian")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("mckellen"))))) { 
 BA.debugLineNum = 574;BA.debugLine="Drama1.Text = \"X-Men\"";
Debug.ShouldStop(536870912);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("X-Men"));
 BA.debugLineNum = 575;BA.debugLine="Starter1.Text = \"Starring: Patrick Stewart, Hugh";
Debug.ShouldStop(1073741824);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
 BA.debugLineNum = 576;BA.debugLine="Year1.Text = \"(2000)\"";
Debug.ShouldStop(-2147483648);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2000)"));
 BA.debugLineNum = 577;BA.debugLine="OverView1.Text = \"In a world where mutants (evol";
Debug.ShouldStop(1);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
 BA.debugLineNum = 578;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(2);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 579;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(4);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("xmen.jpg"))).getObject()));
 BA.debugLineNum = 581;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(16);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 582;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(32);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 583;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(64);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 584;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(128);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 585;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(256);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 587;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(1024);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 589;BA.debugLine="Else if query.Contains(\"brad pitt\") Or query.Cont";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("brad pitt")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("brad")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("pitt")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("angelina jolie")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("angelina")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("jolie")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("adam brody")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("adam")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("brody"))))) { 
 BA.debugLineNum = 590;BA.debugLine="Drama1.Text = \"Mr. & Mrs. Smith \"";
Debug.ShouldStop(8192);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Mr. & Mrs. Smith "));
 BA.debugLineNum = 591;BA.debugLine="Starter1.Text = \"Starring: Brad Pitt, Angelina J";
Debug.ShouldStop(16384);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
 BA.debugLineNum = 592;BA.debugLine="Year1.Text = \"(2005)\"";
Debug.ShouldStop(32768);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2005)"));
 BA.debugLineNum = 593;BA.debugLine="OverView1.Text = \"A husband and wife struggle to";
Debug.ShouldStop(65536);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
 BA.debugLineNum = 594;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(131072);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 595;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(262144);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("MrAndMrs.png"))).getObject()));
 BA.debugLineNum = 597;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(1048576);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 598;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(2097152);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 599;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(4194304);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 600;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(8388608);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 601;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(16777216);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 603;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(67108864);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 607;BA.debugLine="Else if query.Contains(\"will yun lee\") Or query.C";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("will yun lee")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("will")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("yun")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("lee")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("tao okamoto")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("tao")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("okamoto"))))) { 
 BA.debugLineNum = 608;BA.debugLine="Drama1.Text = \"The Wolverine\"";
Debug.ShouldStop(-2147483648);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("The Wolverine"));
 BA.debugLineNum = 609;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Will Yu";
Debug.ShouldStop(1);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
 BA.debugLineNum = 610;BA.debugLine="Year1.Text = \"(2015)\"";
Debug.ShouldStop(2);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2015)"));
 BA.debugLineNum = 611;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
Debug.ShouldStop(4);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
 BA.debugLineNum = 612;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(8);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 613;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(16);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("wolverine.png"))).getObject()));
 BA.debugLineNum = 615;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(64);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 616;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(128);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 617;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(256);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 618;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(512);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 619;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(1024);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 621;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(4096);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 623;BA.debugLine="Else if query.Contains(\"jake gyllenhaal\") Or quer";
Debug.ShouldStop(16384);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("jake gyllenhaal")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("jake")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("gyllenhaal")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("viola davis")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("viola")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("davis"))))) { 
 BA.debugLineNum = 624;BA.debugLine="Drama1.Text = \"Prisoners\"";
Debug.ShouldStop(32768);
action.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Prisoners"));
 BA.debugLineNum = 625;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Jake Gy";
Debug.ShouldStop(65536);
action.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
 BA.debugLineNum = 626;BA.debugLine="Year1.Text = \"(2013)\"";
Debug.ShouldStop(131072);
action.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 627;BA.debugLine="OverView1.Text = \"A desperate father takes the l";
Debug.ShouldStop(262144);
action.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
 BA.debugLineNum = 628;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(524288);
action.mostCurrent._dramaimage1.runMethod(true,"setGravity",action.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 629;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(1048576);
action.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(action.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(action.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("prisoners.jpg"))).getObject()));
 BA.debugLineNum = 631;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(4194304);
action.mostCurrent._panelmovie2.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 632;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(8388608);
action.mostCurrent._panelmovie3.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 633;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(16777216);
action.mostCurrent._panelmovie4.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 634;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(33554432);
action.mostCurrent._panelmovie5.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 635;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(67108864);
action.mostCurrent._panelmovie6.runMethod(true,"setVisible",action.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 637;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(268435456);
action.mostCurrent._p.runMethod(true,"setHeight",action.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),action.mostCurrent.activityBA));
 }else {
 BA.debugLineNum = 641;BA.debugLine="MsgboxAsync(\"No results found for\" & \" \"\"\" & Use";
Debug.ShouldStop(1);
action.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("No results found for"),RemoteObject.createImmutable(" \""),_userinput,RemoteObject.createImmutable("\"")))),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable(""))),action.processBA);
 }}}}}}}}}}}}}}}}}}}}}}}}
;
 BA.debugLineNum = 645;BA.debugLine="p.Width = 100%x";
Debug.ShouldStop(16);
action.mostCurrent._p.runMethod(true,"setWidth",action.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),action.mostCurrent.activityBA));
 BA.debugLineNum = 646;BA.debugLine="ScrollView1.Panel.Height = p.Height";
Debug.ShouldStop(32);
action.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",action.mostCurrent._p.runMethod(true,"getHeight"));
 BA.debugLineNum = 648;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}