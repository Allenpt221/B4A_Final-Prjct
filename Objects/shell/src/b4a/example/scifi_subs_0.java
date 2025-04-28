package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class scifi_subs_0 {


public static RemoteObject  _actionpage_click() throws Exception{
try {
		Debug.PushSubsStack("ActionPage_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,811);
if (RapidSub.canDelegate("actionpage_click")) { return b4a.example.scifi.remoteMe.runUserSub(false, "scifi","actionpage_click");}
 BA.debugLineNum = 811;BA.debugLine="Private Sub ActionPage_Click";
Debug.ShouldStop(1024);
 BA.debugLineNum = 812;BA.debugLine="StartActivity(Action)";
Debug.ShouldStop(2048);
scifi.mostCurrent.__c.runVoidMethod ("StartActivity",scifi.processBA,(Object)((scifi.mostCurrent._action.getObject())));
 BA.debugLineNum = 813;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
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
		Debug.PushSubsStack("Activity_Create (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,109);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.scifi.remoteMe.runUserSub(false, "scifi","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 109;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(4096);
 BA.debugLineNum = 110;BA.debugLine="Activity.LoadLayout(\"scifi\") ' Layout contains Sc";
Debug.ShouldStop(8192);
scifi.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("scifi")),scifi.mostCurrent.activityBA);
 BA.debugLineNum = 112;BA.debugLine="p.Initialize(\"\")";
Debug.ShouldStop(32768);
scifi.mostCurrent._p.runVoidMethod ("Initialize",scifi.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 113;BA.debugLine="p.LoadLayout(\"panelview\")";
Debug.ShouldStop(65536);
scifi.mostCurrent._p.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("panelview")),scifi.mostCurrent.activityBA);
 BA.debugLineNum = 120;BA.debugLine="Drama1.Text = \"The Fellowship of the Ring\"";
Debug.ShouldStop(8388608);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("The Fellowship of the Ring"));
 BA.debugLineNum = 121;BA.debugLine="Starter1.Text = \"Starring: Elijah Wood, Ian McKel";
Debug.ShouldStop(16777216);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Elijah Wood, Ian McKellen, Orlando Bloom"));
 BA.debugLineNum = 122;BA.debugLine="Year1.Text = \"(2001)\"";
Debug.ShouldStop(33554432);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2001)"));
 BA.debugLineNum = 123;BA.debugLine="OverView1.Text = \"The future of civilization rest";
Debug.ShouldStop(67108864);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"));
 BA.debugLineNum = 124;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(134217728);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 125;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(268435456);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("kramer.jpg"))).getObject()));
 BA.debugLineNum = 127;BA.debugLine="Drama2.Text = \"Charlie and the Chocolate Factory\"";
Debug.ShouldStop(1073741824);
scifi.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Charlie and the Chocolate Factory"));
 BA.debugLineNum = 128;BA.debugLine="Starter2.Text = \"Starring: Johnny Depp, Freddie H";
Debug.ShouldStop(-2147483648);
scifi.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"));
 BA.debugLineNum = 129;BA.debugLine="Year2.Text = \"(2005)\"";
Debug.ShouldStop(1);
scifi.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2005)"));
 BA.debugLineNum = 130;BA.debugLine="OverView2.Text = \"Charlie Bucket, a humble boy fr";
Debug.ShouldStop(2);
scifi.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."));
 BA.debugLineNum = 131;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(4);
scifi.mostCurrent._dramaimage2.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 132;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(8);
scifi.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("manchester.jpg"))).getObject()));
 BA.debugLineNum = 134;BA.debugLine="Drama3.Text = \"Alice in Wonderland\"";
Debug.ShouldStop(32);
scifi.mostCurrent._drama3.runMethod(true,"setText",BA.ObjectToCharSequence("Alice in Wonderland"));
 BA.debugLineNum = 135;BA.debugLine="Starter3.Text = \"Starring: Mia Wasikowska, Johnny";
Debug.ShouldStop(64);
scifi.mostCurrent._starter3.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"));
 BA.debugLineNum = 136;BA.debugLine="Year3.Text = \"(2010)\"";
Debug.ShouldStop(128);
scifi.mostCurrent._year3.runMethod(true,"setText",BA.ObjectToCharSequence("(2010)"));
 BA.debugLineNum = 137;BA.debugLine="OverView3.Text = \"Alice, now a teenager, returns";
Debug.ShouldStop(256);
scifi.mostCurrent._overview3.runMethod(true,"setText",BA.ObjectToCharSequence("Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."));
 BA.debugLineNum = 138;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
Debug.ShouldStop(512);
scifi.mostCurrent._dramaimage3.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 139;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(1024);
scifi.mostCurrent._dramaimage3.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("master.jpg"))).getObject()));
 BA.debugLineNum = 141;BA.debugLine="Drama4.Text = \"Harry Potter and the Philosopher's";
Debug.ShouldStop(4096);
scifi.mostCurrent._drama4.runMethod(true,"setText",BA.ObjectToCharSequence("Harry Potter and the Philosopher's Stone"));
 BA.debugLineNum = 142;BA.debugLine="Starter4.Text = \"Starring: Daniel Radcliffe, Rupe";
Debug.ShouldStop(8192);
scifi.mostCurrent._starter4.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Daniel Radcliffe, Rupert Grint, Emma Watson"));
 BA.debugLineNum = 143;BA.debugLine="Year4.Text = \"(2003)\"";
Debug.ShouldStop(16384);
scifi.mostCurrent._year4.runMethod(true,"setText",BA.ObjectToCharSequence("(2003)"));
 BA.debugLineNum = 144;BA.debugLine="OverView4.Text = \"Captain Jack Sparrow must rescu";
Debug.ShouldStop(32768);
scifi.mostCurrent._overview4.runMethod(true,"setText",BA.ObjectToCharSequence("Captain Jack Sparrow must rescue Elizabeth Swann, who has been kidnapped by cursed pirates led by Captain Barbossa. Alongside Will Turner, Jack battles ghostly pirates, seeking both freedom and revenge."));
 BA.debugLineNum = 145;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
Debug.ShouldStop(65536);
scifi.mostCurrent._dramaimage4.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 146;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(131072);
scifi.mostCurrent._dramaimage4.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("millondolar.jpg"))).getObject()));
 BA.debugLineNum = 148;BA.debugLine="Drama5.Text = \"The Curse of the Black Pearl\"";
Debug.ShouldStop(524288);
scifi.mostCurrent._drama5.runMethod(true,"setText",BA.ObjectToCharSequence("The Curse of the Black Pearl"));
 BA.debugLineNum = 149;BA.debugLine="Starter5.Text = \"Starring: Johnny Depp, Orlando B";
Debug.ShouldStop(1048576);
scifi.mostCurrent._starter5.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Johnny Depp, Orlando Bloom, Keira Knightley"));
 BA.debugLineNum = 150;BA.debugLine="Year5.Text = \"(2003)\"";
Debug.ShouldStop(2097152);
scifi.mostCurrent._year5.runMethod(true,"setText",BA.ObjectToCharSequence("(2003)"));
 BA.debugLineNum = 151;BA.debugLine="OverView5.Text = \"Four siblings, evacuated from w";
Debug.ShouldStop(4194304);
scifi.mostCurrent._overview5.runMethod(true,"setText",BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
 BA.debugLineNum = 152;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
Debug.ShouldStop(8388608);
scifi.mostCurrent._dramaimage5.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 153;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(16777216);
scifi.mostCurrent._dramaimage5.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("bridges.jpg"))).getObject()));
 BA.debugLineNum = 156;BA.debugLine="Drama6.Text = \"The Chronicles of Narnia\"";
Debug.ShouldStop(134217728);
scifi.mostCurrent._drama6.runMethod(true,"setText",BA.ObjectToCharSequence("The Chronicles of Narnia"));
 BA.debugLineNum = 157;BA.debugLine="Starter6.Text = \"Starring: Georgie Henley, Skanda";
Debug.ShouldStop(268435456);
scifi.mostCurrent._starter6.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Georgie Henley, Skandar Keynes, William Moseley,"));
 BA.debugLineNum = 158;BA.debugLine="Year6.Text = \"(2003)\"";
Debug.ShouldStop(536870912);
scifi.mostCurrent._year6.runMethod(true,"setText",BA.ObjectToCharSequence("(2003)"));
 BA.debugLineNum = 159;BA.debugLine="OverView6.Text = \"Four siblings, evacuated from w";
Debug.ShouldStop(1073741824);
scifi.mostCurrent._overview6.runMethod(true,"setText",BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
 BA.debugLineNum = 160;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
Debug.ShouldStop(-2147483648);
scifi.mostCurrent._dramaimage6.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 161;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(1);
scifi.mostCurrent._dramaimage6.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("gonebaby.jpg"))).getObject()));
 BA.debugLineNum = 163;BA.debugLine="Drama7.Text = \"Doctor Strange\"";
Debug.ShouldStop(4);
scifi.mostCurrent._drama7.runMethod(true,"setText",BA.ObjectToCharSequence("Doctor Strange"));
 BA.debugLineNum = 164;BA.debugLine="Starter7.Text = \"Starring: Benedict Cumberbatch,";
Debug.ShouldStop(8);
scifi.mostCurrent._starter7.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Benedict Cumberbatch, Chiwetel Ejiofor"));
 BA.debugLineNum = 165;BA.debugLine="Year7.Text = \"(2016)\"";
Debug.ShouldStop(16);
scifi.mostCurrent._year7.runMethod(true,"setText",BA.ObjectToCharSequence("(2016)"));
 BA.debugLineNum = 166;BA.debugLine="OverView7.Text = \"After a life-changing accident";
Debug.ShouldStop(32);
scifi.mostCurrent._overview7.runMethod(true,"setText",BA.ObjectToCharSequence("After a life-changing accident ends his career, arrogant surgeon Stephen Strange seeks healing in Nepal, where he learns the mystic arts and must protect the world from dark magical forces beyond reality."));
 BA.debugLineNum = 167;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
Debug.ShouldStop(64);
scifi.mostCurrent._dramaimage7.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 168;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(128);
scifi.mostCurrent._dramaimage7.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("bluejasmine.jpg"))).getObject()));
 BA.debugLineNum = 170;BA.debugLine="Drama8.Text = \"V for Vendetta\"";
Debug.ShouldStop(512);
scifi.mostCurrent._drama8.runMethod(true,"setText",BA.ObjectToCharSequence("V for Vendetta"));
 BA.debugLineNum = 171;BA.debugLine="Starter8.Text = \"Starring: Hugo Weaving, Natalie";
Debug.ShouldStop(1024);
scifi.mostCurrent._starter8.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugo Weaving, Natalie Portman, Stephen Rea, John Hurt"));
 BA.debugLineNum = 172;BA.debugLine="Year8.Text = \"(2005)\"";
Debug.ShouldStop(2048);
scifi.mostCurrent._year8.runMethod(true,"setText",BA.ObjectToCharSequence("(2005)"));
 BA.debugLineNum = 173;BA.debugLine="OverView8.Text = \"In a totalitarian future Britai";
Debug.ShouldStop(4096);
scifi.mostCurrent._overview8.runMethod(true,"setText",BA.ObjectToCharSequence("In a totalitarian future Britain, a masked revolutionary known as \"V\" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."));
 BA.debugLineNum = 174;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
Debug.ShouldStop(8192);
scifi.mostCurrent._dramaimage8.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 175;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(16384);
scifi.mostCurrent._dramaimage8.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("her.jpg"))).getObject()));
 BA.debugLineNum = 177;BA.debugLine="Drama9.Text = \"Aladdin\"";
Debug.ShouldStop(65536);
scifi.mostCurrent._drama9.runMethod(true,"setText",BA.ObjectToCharSequence("Aladdin"));
 BA.debugLineNum = 178;BA.debugLine="Starter9.Text = \"Starring: Mena Massoud, Naomi Sc";
Debug.ShouldStop(131072);
scifi.mostCurrent._starter9.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Mena Massoud, Naomi Scott, Will Smith"));
 BA.debugLineNum = 179;BA.debugLine="Year9.Text = \"(2019)\"";
Debug.ShouldStop(262144);
scifi.mostCurrent._year9.runMethod(true,"setText",BA.ObjectToCharSequence("(2019)"));
 BA.debugLineNum = 180;BA.debugLine="OverView9.Text = \"Aladdin, a kind-hearted street";
Debug.ShouldStop(524288);
scifi.mostCurrent._overview9.runMethod(true,"setText",BA.ObjectToCharSequence("Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."));
 BA.debugLineNum = 181;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
Debug.ShouldStop(1048576);
scifi.mostCurrent._dramaimage9.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 182;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets, \"";
Debug.ShouldStop(2097152);
scifi.mostCurrent._dramaimage9.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("carol.jpg"))).getObject()));
 BA.debugLineNum = 184;BA.debugLine="Drama10.Text = \"After Earth\"";
Debug.ShouldStop(8388608);
scifi.mostCurrent._drama10.runMethod(true,"setText",BA.ObjectToCharSequence("After Earth"));
 BA.debugLineNum = 185;BA.debugLine="Starter10.Text = \"Starring: Will Smith, Jaden Smi";
Debug.ShouldStop(16777216);
scifi.mostCurrent._starter10.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Will Smith, Jaden Smith, Sigourney Weaver"));
 BA.debugLineNum = 186;BA.debugLine="Year10.Text = \"(2013)\"";
Debug.ShouldStop(33554432);
scifi.mostCurrent._year10.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 187;BA.debugLine="OverView10.Text = \"Set in the future, After Earth";
Debug.ShouldStop(67108864);
scifi.mostCurrent._overview10.runMethod(true,"setText",BA.ObjectToCharSequence("Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."));
 BA.debugLineNum = 188;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
Debug.ShouldStop(134217728);
scifi.mostCurrent._dramaimage10.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 189;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(268435456);
scifi.mostCurrent._dramaimage10.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("lostdaughter.jpg"))).getObject()));
 BA.debugLineNum = 191;BA.debugLine="ScrollView1.Panel.AddView(p, 0, 0, 200%x, 210%y)";
Debug.ShouldStop(1073741824);
scifi.mostCurrent._scrollview1.runMethod(false,"getPanel").runVoidMethod ("AddView",(Object)((scifi.mostCurrent._p.getObject())),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0)),(Object)(scifi.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 200)),scifi.mostCurrent.activityBA)),(Object)(scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 210)),scifi.mostCurrent.activityBA)));
 BA.debugLineNum = 192;BA.debugLine="ScrollView1.Panel.Height = p.Height";
Debug.ShouldStop(-2147483648);
scifi.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",scifi.mostCurrent._p.runMethod(true,"getHeight"));
 BA.debugLineNum = 193;BA.debugLine="End Sub";
Debug.ShouldStop(1);
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
		Debug.PushSubsStack("Activity_Pause (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,792);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.scifi.remoteMe.runUserSub(false, "scifi","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 792;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 794;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
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
		Debug.PushSubsStack("Activity_Resume (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,788);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.scifi.remoteMe.runUserSub(false, "scifi","activity_resume");}
 BA.debugLineNum = 788;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(524288);
 BA.debugLineNum = 790;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
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
		Debug.PushSubsStack("DramaPage_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,807);
if (RapidSub.canDelegate("dramapage_click")) { return b4a.example.scifi.remoteMe.runUserSub(false, "scifi","dramapage_click");}
 BA.debugLineNum = 807;BA.debugLine="Private Sub DramaPage_Click";
Debug.ShouldStop(64);
 BA.debugLineNum = 808;BA.debugLine="StartActivity(Drama)";
Debug.ShouldStop(128);
scifi.mostCurrent.__c.runVoidMethod ("StartActivity",scifi.processBA,(Object)((scifi.mostCurrent._drama.getObject())));
 BA.debugLineNum = 809;BA.debugLine="End Sub";
Debug.ShouldStop(256);
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
 //BA.debugLineNum = 16;BA.debugLine="Private ScrollView1 As ScrollView";
scifi.mostCurrent._scrollview1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ScrollViewWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private Drama1 As Label";
scifi.mostCurrent._drama1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private Drama2 As Label";
scifi.mostCurrent._drama2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Private Drama3 As Label";
scifi.mostCurrent._drama3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 22;BA.debugLine="Private Drama4 As Label";
scifi.mostCurrent._drama4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 23;BA.debugLine="Private Drama5 As Label";
scifi.mostCurrent._drama5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 24;BA.debugLine="Private Drama6 As Label";
scifi.mostCurrent._drama6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Private Drama7 As Label";
scifi.mostCurrent._drama7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 26;BA.debugLine="Private Drama8 As Label";
scifi.mostCurrent._drama8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 27;BA.debugLine="Private Drama9 As Label";
scifi.mostCurrent._drama9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 28;BA.debugLine="Private Drama10 As Label";
scifi.mostCurrent._drama10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 33;BA.debugLine="Private DramaImage1 As ImageView";
scifi.mostCurrent._dramaimage1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 34;BA.debugLine="Private DramaImage2 As ImageView";
scifi.mostCurrent._dramaimage2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 35;BA.debugLine="Private DramaImage3 As ImageView";
scifi.mostCurrent._dramaimage3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 36;BA.debugLine="Private DramaImage4 As ImageView";
scifi.mostCurrent._dramaimage4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 37;BA.debugLine="Private DramaImage5 As ImageView";
scifi.mostCurrent._dramaimage5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 38;BA.debugLine="Private DramaImage6 As ImageView";
scifi.mostCurrent._dramaimage6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 39;BA.debugLine="Private DramaImage7 As ImageView";
scifi.mostCurrent._dramaimage7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 40;BA.debugLine="Private DramaImage8 As ImageView";
scifi.mostCurrent._dramaimage8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 41;BA.debugLine="Private DramaImage9 As ImageView";
scifi.mostCurrent._dramaimage9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 42;BA.debugLine="Private DramaImage10 As ImageView";
scifi.mostCurrent._dramaimage10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ImageViewWrapper");
 //BA.debugLineNum = 47;BA.debugLine="Private SearchBtn As Button";
scifi.mostCurrent._searchbtn = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 48;BA.debugLine="Private SearchEngine As EditText";
scifi.mostCurrent._searchengine = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 51;BA.debugLine="Dim p As Panel";
scifi.mostCurrent._p = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 52;BA.debugLine="Private Panel1 As Panel";
scifi.mostCurrent._panel1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 53;BA.debugLine="Private PanelMovie1 As Panel";
scifi.mostCurrent._panelmovie1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 54;BA.debugLine="Private PanelMovie2 As Panel";
scifi.mostCurrent._panelmovie2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 55;BA.debugLine="Private PanelMovie3 As Panel";
scifi.mostCurrent._panelmovie3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 56;BA.debugLine="Private PanelMovie4 As Panel";
scifi.mostCurrent._panelmovie4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 57;BA.debugLine="Private PanelMovie5 As Panel";
scifi.mostCurrent._panelmovie5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 58;BA.debugLine="Private PanelMovie6 As Panel";
scifi.mostCurrent._panelmovie6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 59;BA.debugLine="Private PanelMovie7 As Panel";
scifi.mostCurrent._panelmovie7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 60;BA.debugLine="Private PanelMovie8 As Panel";
scifi.mostCurrent._panelmovie8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 61;BA.debugLine="Private PanelMovie9 As Panel";
scifi.mostCurrent._panelmovie9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 62;BA.debugLine="Private PanelMovie10 As Panel";
scifi.mostCurrent._panelmovie10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 67;BA.debugLine="Private DramaPage As Label";
scifi.mostCurrent._dramapage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 68;BA.debugLine="Private HomePage As Label";
scifi.mostCurrent._homepage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 69;BA.debugLine="Private SciFiPage As Label";
scifi.mostCurrent._scifipage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 70;BA.debugLine="Private ActionPage As Label";
scifi.mostCurrent._actionpage = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 73;BA.debugLine="Private Starter1 As Label";
scifi.mostCurrent._starter1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 74;BA.debugLine="Private Starter2 As Label";
scifi.mostCurrent._starter2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 75;BA.debugLine="Private Starter3 As Label";
scifi.mostCurrent._starter3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 76;BA.debugLine="Private Starter4 As Label";
scifi.mostCurrent._starter4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 77;BA.debugLine="Private Starter5 As Label";
scifi.mostCurrent._starter5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 78;BA.debugLine="Private Starter6 As Label";
scifi.mostCurrent._starter6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 79;BA.debugLine="Private Starter7 As Label";
scifi.mostCurrent._starter7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 80;BA.debugLine="Private Starter8 As Label";
scifi.mostCurrent._starter8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 81;BA.debugLine="Private Starter9 As Label";
scifi.mostCurrent._starter9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 82;BA.debugLine="Private Starter10 As Label";
scifi.mostCurrent._starter10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 85;BA.debugLine="Private OverView1 As Label";
scifi.mostCurrent._overview1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 86;BA.debugLine="Private OverView2 As Label";
scifi.mostCurrent._overview2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 87;BA.debugLine="Private OverView3 As Label";
scifi.mostCurrent._overview3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 88;BA.debugLine="Private OverView4 As Label";
scifi.mostCurrent._overview4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 89;BA.debugLine="Private OverView5 As Label";
scifi.mostCurrent._overview5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 90;BA.debugLine="Private OverView6 As Label";
scifi.mostCurrent._overview6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 91;BA.debugLine="Private OverView7 As Label";
scifi.mostCurrent._overview7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 92;BA.debugLine="Private OverView8 As Label";
scifi.mostCurrent._overview8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 93;BA.debugLine="Private OverView9 As Label";
scifi.mostCurrent._overview9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 94;BA.debugLine="Private OverView10 As Label";
scifi.mostCurrent._overview10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 96;BA.debugLine="Private Year1 As Label";
scifi.mostCurrent._year1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 97;BA.debugLine="Private Year2 As Label";
scifi.mostCurrent._year2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 98;BA.debugLine="Private Year3 As Label";
scifi.mostCurrent._year3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 99;BA.debugLine="Private Year4 As Label";
scifi.mostCurrent._year4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 100;BA.debugLine="Private Year5 As Label";
scifi.mostCurrent._year5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 101;BA.debugLine="Private Year6 As Label";
scifi.mostCurrent._year6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 102;BA.debugLine="Private Year7 As Label";
scifi.mostCurrent._year7 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 103;BA.debugLine="Private Year8 As Label";
scifi.mostCurrent._year8 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 104;BA.debugLine="Private Year9 As Label";
scifi.mostCurrent._year9 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 105;BA.debugLine="Private Year10 As Label";
scifi.mostCurrent._year10 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 107;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _homepage_click() throws Exception{
try {
		Debug.PushSubsStack("HomePage_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,803);
if (RapidSub.canDelegate("homepage_click")) { return b4a.example.scifi.remoteMe.runUserSub(false, "scifi","homepage_click");}
 BA.debugLineNum = 803;BA.debugLine="Private Sub HomePage_Click";
Debug.ShouldStop(4);
 BA.debugLineNum = 804;BA.debugLine="StartActivity(Main)";
Debug.ShouldStop(8);
scifi.mostCurrent.__c.runVoidMethod ("StartActivity",scifi.processBA,(Object)((scifi.mostCurrent._main.getObject())));
 BA.debugLineNum = 805;BA.debugLine="End Sub";
Debug.ShouldStop(16);
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
		Debug.PushSubsStack("PanelMovie1_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,816);
if (RapidSub.canDelegate("panelmovie1_click")) { b4a.example.scifi.remoteMe.runUserSub(false, "scifi","panelmovie1_click"); return;}
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
public ResumableSub_PanelMovie1_Click(b4a.example.scifi parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.scifi parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie1_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,816);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 817;BA.debugLine="Try";
Debug.ShouldStop(65536);
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
 BA.debugLineNum = 818;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(131072);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),scifi.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 819;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(262144);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", scifi.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "scifi", "panelmovie1_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 820;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
Debug.ShouldStop(524288);
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
 BA.debugLineNum = 821;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(1048576);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 822;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
Debug.ShouldStop(2097152);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt0479884/")));
 BA.debugLineNum = 823;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(4194304);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 824;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(8388608);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",scifi.processBA,(Object)((_i.getObject())));
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
 BA.debugLineNum = 828;BA.debugLine="Log(LastException)";
Debug.ShouldStop(134217728);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","84915212",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",scifi.mostCurrent.activityBA)),0);
 BA.debugLineNum = 829;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(268435456);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),scifi.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 832;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",scifi.processBA, e0.toString());}
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
		Debug.PushSubsStack("PanelMovie10_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,971);
if (RapidSub.canDelegate("panelmovie10_click")) { b4a.example.scifi.remoteMe.runUserSub(false, "scifi","panelmovie10_click"); return;}
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
public ResumableSub_PanelMovie10_Click(b4a.example.scifi parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.scifi parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie10_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,971);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 972;BA.debugLine="Try";
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
 BA.debugLineNum = 973;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(4096);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),scifi.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 974;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(8192);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", scifi.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "scifi", "panelmovie10_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 975;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
 BA.debugLineNum = 976;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(32768);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 977;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
Debug.ShouldStop(65536);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt1392214/")));
 BA.debugLineNum = 978;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(131072);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 979;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(262144);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",scifi.processBA,(Object)((_i.getObject())));
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
 BA.debugLineNum = 982;BA.debugLine="Log(LastException)";
Debug.ShouldStop(2097152);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","85505035",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",scifi.mostCurrent.activityBA)),0);
 BA.debugLineNum = 983;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(4194304);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),scifi.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 986;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",scifi.processBA, e0.toString());}
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
		Debug.PushSubsStack("PanelMovie2_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,835);
if (RapidSub.canDelegate("panelmovie2_click")) { b4a.example.scifi.remoteMe.runUserSub(false, "scifi","panelmovie2_click"); return;}
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
public ResumableSub_PanelMovie2_Click(b4a.example.scifi parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.scifi parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie2_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,835);
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
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),scifi.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 838;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(32);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", scifi.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "scifi", "panelmovie2_click"), null);
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
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt0988045/")));
 BA.debugLineNum = 842;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(512);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 843;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(1024);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",scifi.processBA,(Object)((_i.getObject())));
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
parent.mostCurrent.__c.runVoidMethod ("LogImpl","84980747",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",scifi.mostCurrent.activityBA)),0);
 BA.debugLineNum = 847;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(16384);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),scifi.processBA);
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
BA.rdebugUtils.runVoidMethod("setLastException",scifi.processBA, e0.toString());}
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
		Debug.PushSubsStack("PanelMovie3_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,852);
if (RapidSub.canDelegate("panelmovie3_click")) { b4a.example.scifi.remoteMe.runUserSub(false, "scifi","panelmovie3_click"); return;}
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
public ResumableSub_PanelMovie3_Click(b4a.example.scifi parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.scifi parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie3_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,852);
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
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),scifi.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 855;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(4194304);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", scifi.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "scifi", "panelmovie3_click"), null);
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
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt4154796/")));
 BA.debugLineNum = 859;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(67108864);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 860;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(134217728);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",scifi.processBA,(Object)((_i.getObject())));
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
parent.mostCurrent.__c.runVoidMethod ("LogImpl","85046283",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",scifi.mostCurrent.activityBA)),0);
 BA.debugLineNum = 864;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(-2147483648);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),scifi.processBA);
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
BA.rdebugUtils.runVoidMethod("setLastException",scifi.processBA, e0.toString());}
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
		Debug.PushSubsStack("PanelMovie4_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,869);
if (RapidSub.canDelegate("panelmovie4_click")) { b4a.example.scifi.remoteMe.runUserSub(false, "scifi","panelmovie4_click"); return;}
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
public ResumableSub_PanelMovie4_Click(b4a.example.scifi parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.scifi parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie4_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,869);
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
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),scifi.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 872;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(128);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", scifi.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "scifi", "panelmovie4_click"), null);
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
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt3315342/")));
 BA.debugLineNum = 876;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(2048);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 877;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(4096);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",scifi.processBA,(Object)((_i.getObject())));
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
parent.mostCurrent.__c.runVoidMethod ("LogImpl","85111819",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",scifi.mostCurrent.activityBA)),0);
 BA.debugLineNum = 881;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(65536);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),scifi.processBA);
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
BA.rdebugUtils.runVoidMethod("setLastException",scifi.processBA, e0.toString());}
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
		Debug.PushSubsStack("PanelMovie5_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,886);
if (RapidSub.canDelegate("panelmovie5_click")) { b4a.example.scifi.remoteMe.runUserSub(false, "scifi","panelmovie5_click"); return;}
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
public ResumableSub_PanelMovie5_Click(b4a.example.scifi parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.scifi parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie5_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,886);
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
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),scifi.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 889;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(16777216);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", scifi.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "scifi", "panelmovie5_click"), null);
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
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt0371746/")));
 BA.debugLineNum = 893;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(268435456);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 894;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(536870912);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",scifi.processBA,(Object)((_i.getObject())));
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
parent.mostCurrent.__c.runVoidMethod ("LogImpl","85177355",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",scifi.mostCurrent.activityBA)),0);
 BA.debugLineNum = 898;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(2);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),scifi.processBA);
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
BA.rdebugUtils.runVoidMethod("setLastException",scifi.processBA, e0.toString());}
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
		Debug.PushSubsStack("PanelMovie6_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,903);
if (RapidSub.canDelegate("panelmovie6_click")) { b4a.example.scifi.remoteMe.runUserSub(false, "scifi","panelmovie6_click"); return;}
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
public ResumableSub_PanelMovie6_Click(b4a.example.scifi parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.scifi parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie6_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,903);
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
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),scifi.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 906;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(512);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", scifi.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "scifi", "panelmovie6_click"), null);
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
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt0120903/")));
 BA.debugLineNum = 910;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(8192);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 911;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(16384);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",scifi.processBA,(Object)((_i.getObject())));
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
parent.mostCurrent.__c.runVoidMethod ("LogImpl","85242891",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",scifi.mostCurrent.activityBA)),0);
 BA.debugLineNum = 915;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(262144);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),scifi.processBA);
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
BA.rdebugUtils.runVoidMethod("setLastException",scifi.processBA, e0.toString());}
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
		Debug.PushSubsStack("PanelMovie7_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,920);
if (RapidSub.canDelegate("panelmovie7_click")) { b4a.example.scifi.remoteMe.runUserSub(false, "scifi","panelmovie7_click"); return;}
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
public ResumableSub_PanelMovie7_Click(b4a.example.scifi parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.scifi parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie7_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,920);
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
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),scifi.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 923;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(67108864);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", scifi.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "scifi", "panelmovie7_click"), null);
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
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt2334873/")));
 BA.debugLineNum = 927;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(1073741824);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 928;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(-2147483648);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",scifi.processBA,(Object)((_i.getObject())));
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
parent.mostCurrent.__c.runVoidMethod ("LogImpl","85308427",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",scifi.mostCurrent.activityBA)),0);
 BA.debugLineNum = 932;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(8);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),scifi.processBA);
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
BA.rdebugUtils.runVoidMethod("setLastException",scifi.processBA, e0.toString());}
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
		Debug.PushSubsStack("PanelMovie8_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,937);
if (RapidSub.canDelegate("panelmovie8_click")) { b4a.example.scifi.remoteMe.runUserSub(false, "scifi","panelmovie8_click"); return;}
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
public ResumableSub_PanelMovie8_Click(b4a.example.scifi parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.scifi parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie8_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,937);
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
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),scifi.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 940;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(2048);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", scifi.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "scifi", "panelmovie8_click"), null);
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
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt0356910/")));
 BA.debugLineNum = 944;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(32768);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 945;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(65536);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",scifi.processBA,(Object)((_i.getObject())));
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
parent.mostCurrent.__c.runVoidMethod ("LogImpl","85373963",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",scifi.mostCurrent.activityBA)),0);
 BA.debugLineNum = 949;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(1048576);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),scifi.processBA);
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
BA.rdebugUtils.runVoidMethod("setLastException",scifi.processBA, e0.toString());}
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
		Debug.PushSubsStack("PanelMovie9_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,954);
if (RapidSub.canDelegate("panelmovie9_click")) { b4a.example.scifi.remoteMe.runUserSub(false, "scifi","panelmovie9_click"); return;}
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
public ResumableSub_PanelMovie9_Click(b4a.example.scifi parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
b4a.example.scifi parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _i = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("PanelMovie9_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,954);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 955;BA.debugLine="Try";
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
 BA.debugLineNum = 956;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
Debug.ShouldStop(134217728);
parent.mostCurrent.__c.runVoidMethod ("Msgbox2Async",(Object)(BA.ObjectToCharSequence("Want to watch the trailer of the movie?")),(Object)(BA.ObjectToCharSequence("Go to Trailer")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper"), parent.mostCurrent.__c.getField(false,"Null")),scifi.processBA,(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 957;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
Debug.ShouldStop(268435456);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", scifi.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "scifi", "panelmovie9_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 958;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
 BA.debugLineNum = 959;BA.debugLine="Dim i As Intent";
Debug.ShouldStop(1073741824);
_i = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("i", _i);
 BA.debugLineNum = 960;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
Debug.ShouldStop(-2147483648);
_i.runVoidMethod ("Initialize",(Object)(_i.getField(true,"ACTION_VIEW")),(Object)(RemoteObject.createImmutable("https://www.imdb.com/title/tt1430132/")));
 BA.debugLineNum = 961;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
Debug.ShouldStop(1);
_i.runVoidMethod ("SetComponent",(Object)(RemoteObject.createImmutable("com.android.chrome/com.google.android.apps.chrome.Main")));
 BA.debugLineNum = 962;BA.debugLine="StartActivity(i)";
Debug.ShouldStop(2);
parent.mostCurrent.__c.runVoidMethod ("StartActivity",scifi.processBA,(Object)((_i.getObject())));
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
 BA.debugLineNum = 965;BA.debugLine="Log(LastException)";
Debug.ShouldStop(16);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","85439499",BA.ObjectToString(parent.mostCurrent.__c.runMethod(false,"LastException",scifi.mostCurrent.activityBA)),0);
 BA.debugLineNum = 966;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
Debug.ShouldStop(32);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("can't find Chome app")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Error"))),scifi.processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
 BA.debugLineNum = 969;BA.debugLine="End Sub";
Debug.ShouldStop(256);
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
BA.rdebugUtils.runVoidMethod("setLastException",scifi.processBA, e0.toString());}
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
		Debug.PushSubsStack("SciFiPage_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,799);
if (RapidSub.canDelegate("scifipage_click")) { return b4a.example.scifi.remoteMe.runUserSub(false, "scifi","scifipage_click");}
 BA.debugLineNum = 799;BA.debugLine="Private Sub SciFiPage_Click";
Debug.ShouldStop(1073741824);
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
public static RemoteObject  _searchbtn_click() throws Exception{
try {
		Debug.PushSubsStack("SearchBtn_Click (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,196);
if (RapidSub.canDelegate("searchbtn_click")) { return b4a.example.scifi.remoteMe.runUserSub(false, "scifi","searchbtn_click");}
 BA.debugLineNum = 196;BA.debugLine="Private Sub SearchBtn_Click";
Debug.ShouldStop(8);
 BA.debugLineNum = 197;BA.debugLine="SearchNow";
Debug.ShouldStop(16);
_searchnow();
 BA.debugLineNum = 198;BA.debugLine="End Sub";
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
		Debug.PushSubsStack("SearchEngine_TextChanged (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,688);
if (RapidSub.canDelegate("searchengine_textchanged")) { return b4a.example.scifi.remoteMe.runUserSub(false, "scifi","searchengine_textchanged", _old, _new);}
RemoteObject _query = RemoteObject.createImmutable("");
Debug.locals.put("Old", _old);
Debug.locals.put("New", _new);
 BA.debugLineNum = 688;BA.debugLine="Sub SearchEngine_TextChanged (Old As String, New A";
Debug.ShouldStop(32768);
 BA.debugLineNum = 689;BA.debugLine="Dim query As String = New.ToLowerCase.Trim";
Debug.ShouldStop(65536);
_query = _new.runMethod(true,"toLowerCase").runMethod(true,"trim");Debug.locals.put("query", _query);Debug.locals.put("query", _query);
 BA.debugLineNum = 691;BA.debugLine="If query = \"\" Then";
Debug.ShouldStop(262144);
if (RemoteObject.solveBoolean("=",_query,BA.ObjectToString(""))) { 
 BA.debugLineNum = 694;BA.debugLine="p.Height = 210%y";
Debug.ShouldStop(2097152);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 210)),scifi.mostCurrent.activityBA));
 BA.debugLineNum = 695;BA.debugLine="p.Width = 200%x";
Debug.ShouldStop(4194304);
scifi.mostCurrent._p.runMethod(true,"setWidth",scifi.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 200)),scifi.mostCurrent.activityBA));
 BA.debugLineNum = 696;BA.debugLine="ScrollView1.Panel.Height = p.Height";
Debug.ShouldStop(8388608);
scifi.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",scifi.mostCurrent._p.runMethod(true,"getHeight"));
 BA.debugLineNum = 698;BA.debugLine="PanelMovie1.Visible = True";
Debug.ShouldStop(33554432);
scifi.mostCurrent._panelmovie1.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 699;BA.debugLine="PanelMovie2.Visible = True";
Debug.ShouldStop(67108864);
scifi.mostCurrent._panelmovie2.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 700;BA.debugLine="PanelMovie3.Visible = True";
Debug.ShouldStop(134217728);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 701;BA.debugLine="PanelMovie4.Visible = True";
Debug.ShouldStop(268435456);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 702;BA.debugLine="PanelMovie5.Visible = True";
Debug.ShouldStop(536870912);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 703;BA.debugLine="PanelMovie6.Visible = True";
Debug.ShouldStop(1073741824);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 704;BA.debugLine="PanelMovie7.Visible = True";
Debug.ShouldStop(-2147483648);
scifi.mostCurrent._panelmovie7.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 705;BA.debugLine="PanelMovie8.Visible = True";
Debug.ShouldStop(1);
scifi.mostCurrent._panelmovie8.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 706;BA.debugLine="PanelMovie9.Visible = True";
Debug.ShouldStop(2);
scifi.mostCurrent._panelmovie9.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 707;BA.debugLine="PanelMovie10.Visible = True";
Debug.ShouldStop(4);
scifi.mostCurrent._panelmovie10.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 711;BA.debugLine="Drama1.Text = \"The Fellowship of the Ring\"";
Debug.ShouldStop(64);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("The Fellowship of the Ring"));
 BA.debugLineNum = 712;BA.debugLine="Starter1.Text = \"Starring: Elijah Wood, Ian McKe";
Debug.ShouldStop(128);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Elijah Wood, Ian McKellen, Orlando Bloom"));
 BA.debugLineNum = 713;BA.debugLine="Year1.Text = \"(2001)\"";
Debug.ShouldStop(256);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2001)"));
 BA.debugLineNum = 714;BA.debugLine="OverView1.Text = \"The future of civilization res";
Debug.ShouldStop(512);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"));
 BA.debugLineNum = 715;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(1024);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 716;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(2048);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("kramer.jpg"))).getObject()));
 BA.debugLineNum = 718;BA.debugLine="Drama2.Text = \"Charlie and the Chocolate Factory";
Debug.ShouldStop(8192);
scifi.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Charlie and the Chocolate Factory"));
 BA.debugLineNum = 719;BA.debugLine="Starter2.Text = \"Starring: Johnny Depp, Freddie";
Debug.ShouldStop(16384);
scifi.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"));
 BA.debugLineNum = 720;BA.debugLine="Year2.Text = \"(2005)\"";
Debug.ShouldStop(32768);
scifi.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2005)"));
 BA.debugLineNum = 721;BA.debugLine="OverView2.Text = \"Charlie Bucket, a humble boy f";
Debug.ShouldStop(65536);
scifi.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."));
 BA.debugLineNum = 722;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(131072);
scifi.mostCurrent._dramaimage2.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 723;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(262144);
scifi.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("manchester.jpg"))).getObject()));
 BA.debugLineNum = 725;BA.debugLine="Drama3.Text = \"Alice in Wonderland\"";
Debug.ShouldStop(1048576);
scifi.mostCurrent._drama3.runMethod(true,"setText",BA.ObjectToCharSequence("Alice in Wonderland"));
 BA.debugLineNum = 726;BA.debugLine="Starter3.Text = \"Starring: Mia Wasikowska, Johnn";
Debug.ShouldStop(2097152);
scifi.mostCurrent._starter3.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"));
 BA.debugLineNum = 727;BA.debugLine="Year3.Text = \"(2010)\"";
Debug.ShouldStop(4194304);
scifi.mostCurrent._year3.runMethod(true,"setText",BA.ObjectToCharSequence("(2010)"));
 BA.debugLineNum = 728;BA.debugLine="OverView3.Text = \"Alice, now a teenager, returns";
Debug.ShouldStop(8388608);
scifi.mostCurrent._overview3.runMethod(true,"setText",BA.ObjectToCharSequence("Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."));
 BA.debugLineNum = 729;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
Debug.ShouldStop(16777216);
scifi.mostCurrent._dramaimage3.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 730;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(33554432);
scifi.mostCurrent._dramaimage3.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("master.jpg"))).getObject()));
 BA.debugLineNum = 732;BA.debugLine="Drama4.Text = \"Harry Potter and the Philosopher'";
Debug.ShouldStop(134217728);
scifi.mostCurrent._drama4.runMethod(true,"setText",BA.ObjectToCharSequence("Harry Potter and the Philosopher's Stone"));
 BA.debugLineNum = 733;BA.debugLine="Starter4.Text = \"Starring: Daniel Radcliffe, Rup";
Debug.ShouldStop(268435456);
scifi.mostCurrent._starter4.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Daniel Radcliffe, Rupert Grint, Emma Watson"));
 BA.debugLineNum = 734;BA.debugLine="Year4.Text = \"(2003)\"";
Debug.ShouldStop(536870912);
scifi.mostCurrent._year4.runMethod(true,"setText",BA.ObjectToCharSequence("(2003)"));
 BA.debugLineNum = 735;BA.debugLine="OverView4.Text = \"Captain Jack Sparrow must resc";
Debug.ShouldStop(1073741824);
scifi.mostCurrent._overview4.runMethod(true,"setText",BA.ObjectToCharSequence("Captain Jack Sparrow must rescue Elizabeth Swann, who has been kidnapped by cursed pirates led by Captain Barbossa. Alongside Will Turner, Jack battles ghostly pirates, seeking both freedom and revenge."));
 BA.debugLineNum = 736;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
Debug.ShouldStop(-2147483648);
scifi.mostCurrent._dramaimage4.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 737;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(1);
scifi.mostCurrent._dramaimage4.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("millondolar.jpg"))).getObject()));
 BA.debugLineNum = 739;BA.debugLine="Drama5.Text = \"The Curse of the Black Pearl\"";
Debug.ShouldStop(4);
scifi.mostCurrent._drama5.runMethod(true,"setText",BA.ObjectToCharSequence("The Curse of the Black Pearl"));
 BA.debugLineNum = 740;BA.debugLine="Starter5.Text = \"Starring: Johnny Depp, Orlando";
Debug.ShouldStop(8);
scifi.mostCurrent._starter5.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Johnny Depp, Orlando Bloom, Keira Knightley"));
 BA.debugLineNum = 741;BA.debugLine="Year5.Text = \"(2003)\"";
Debug.ShouldStop(16);
scifi.mostCurrent._year5.runMethod(true,"setText",BA.ObjectToCharSequence("(2003)"));
 BA.debugLineNum = 742;BA.debugLine="OverView5.Text = \"Four siblings, evacuated from";
Debug.ShouldStop(32);
scifi.mostCurrent._overview5.runMethod(true,"setText",BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
 BA.debugLineNum = 743;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
Debug.ShouldStop(64);
scifi.mostCurrent._dramaimage5.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 744;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(128);
scifi.mostCurrent._dramaimage5.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("bridges.jpg"))).getObject()));
 BA.debugLineNum = 747;BA.debugLine="Drama6.Text = \"The Chronicles of Narnia\"";
Debug.ShouldStop(1024);
scifi.mostCurrent._drama6.runMethod(true,"setText",BA.ObjectToCharSequence("The Chronicles of Narnia"));
 BA.debugLineNum = 748;BA.debugLine="Starter6.Text = \"Starring: Georgie Henley, Skand";
Debug.ShouldStop(2048);
scifi.mostCurrent._starter6.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Georgie Henley, Skandar Keynes, William Moseley,"));
 BA.debugLineNum = 749;BA.debugLine="Year6.Text = \"(2005)\"";
Debug.ShouldStop(4096);
scifi.mostCurrent._year6.runMethod(true,"setText",BA.ObjectToCharSequence("(2005)"));
 BA.debugLineNum = 750;BA.debugLine="OverView6.Text = \"Four siblings, evacuated from";
Debug.ShouldStop(8192);
scifi.mostCurrent._overview6.runMethod(true,"setText",BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
 BA.debugLineNum = 751;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
Debug.ShouldStop(16384);
scifi.mostCurrent._dramaimage6.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 752;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(32768);
scifi.mostCurrent._dramaimage6.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("gonebaby.jpg"))).getObject()));
 BA.debugLineNum = 754;BA.debugLine="Drama7.Text = \"Doctor Strange\"";
Debug.ShouldStop(131072);
scifi.mostCurrent._drama7.runMethod(true,"setText",BA.ObjectToCharSequence("Doctor Strange"));
 BA.debugLineNum = 755;BA.debugLine="Starter7.Text = \"Starring: Benedict Cumberbatch,";
Debug.ShouldStop(262144);
scifi.mostCurrent._starter7.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Benedict Cumberbatch, Chiwetel Ejiofor"));
 BA.debugLineNum = 756;BA.debugLine="Year7.Text = \"(2016)\"";
Debug.ShouldStop(524288);
scifi.mostCurrent._year7.runMethod(true,"setText",BA.ObjectToCharSequence("(2016)"));
 BA.debugLineNum = 757;BA.debugLine="OverView7.Text = \"After a life-changing accident";
Debug.ShouldStop(1048576);
scifi.mostCurrent._overview7.runMethod(true,"setText",BA.ObjectToCharSequence("After a life-changing accident ends his career, arrogant surgeon Stephen Strange seeks healing in Nepal, where he learns the mystic arts and must protect the world from dark magical forces beyond reality."));
 BA.debugLineNum = 758;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
Debug.ShouldStop(2097152);
scifi.mostCurrent._dramaimage7.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 759;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(4194304);
scifi.mostCurrent._dramaimage7.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("bluejasmine.jpg"))).getObject()));
 BA.debugLineNum = 761;BA.debugLine="Drama8.Text = \"V for Vendetta\"";
Debug.ShouldStop(16777216);
scifi.mostCurrent._drama8.runMethod(true,"setText",BA.ObjectToCharSequence("V for Vendetta"));
 BA.debugLineNum = 762;BA.debugLine="Starter8.Text = \"Starring: Hugo Weaving, Natalie";
Debug.ShouldStop(33554432);
scifi.mostCurrent._starter8.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugo Weaving, Natalie Portman, Stephen Rea, John Hurt"));
 BA.debugLineNum = 763;BA.debugLine="Year8.Text = \"(2005)\"";
Debug.ShouldStop(67108864);
scifi.mostCurrent._year8.runMethod(true,"setText",BA.ObjectToCharSequence("(2005)"));
 BA.debugLineNum = 764;BA.debugLine="OverView8.Text = \"In a totalitarian future Brita";
Debug.ShouldStop(134217728);
scifi.mostCurrent._overview8.runMethod(true,"setText",BA.ObjectToCharSequence("In a totalitarian future Britain, a masked revolutionary known as \"V\" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."));
 BA.debugLineNum = 765;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
Debug.ShouldStop(268435456);
scifi.mostCurrent._dramaimage8.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 766;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(536870912);
scifi.mostCurrent._dramaimage8.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("her.jpg"))).getObject()));
 BA.debugLineNum = 768;BA.debugLine="Drama9.Text = \"Aladdin\"";
Debug.ShouldStop(-2147483648);
scifi.mostCurrent._drama9.runMethod(true,"setText",BA.ObjectToCharSequence("Aladdin"));
 BA.debugLineNum = 769;BA.debugLine="Starter9.Text = \"Starring: Mena Massoud, Naomi S";
Debug.ShouldStop(1);
scifi.mostCurrent._starter9.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Mena Massoud, Naomi Scott, Will Smith"));
 BA.debugLineNum = 770;BA.debugLine="Year9.Text = \"(2019)\"";
Debug.ShouldStop(2);
scifi.mostCurrent._year9.runMethod(true,"setText",BA.ObjectToCharSequence("(2019)"));
 BA.debugLineNum = 771;BA.debugLine="OverView9.Text = \"Aladdin, a kind-hearted street";
Debug.ShouldStop(4);
scifi.mostCurrent._overview9.runMethod(true,"setText",BA.ObjectToCharSequence("Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."));
 BA.debugLineNum = 772;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
Debug.ShouldStop(8);
scifi.mostCurrent._dramaimage9.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 773;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(16);
scifi.mostCurrent._dramaimage9.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("carol.jpg"))).getObject()));
 BA.debugLineNum = 775;BA.debugLine="Drama10.Text = \"After Earth\"";
Debug.ShouldStop(64);
scifi.mostCurrent._drama10.runMethod(true,"setText",BA.ObjectToCharSequence("After Earth"));
 BA.debugLineNum = 776;BA.debugLine="Starter10.Text = \"Starring: Will Smith, Jaden Sm";
Debug.ShouldStop(128);
scifi.mostCurrent._starter10.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Will Smith, Jaden Smith, Sigourney Weaver"));
 BA.debugLineNum = 777;BA.debugLine="Year10.Text = \"(2013)\"";
Debug.ShouldStop(256);
scifi.mostCurrent._year10.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 778;BA.debugLine="OverView10.Text = \"Set in the future, After Eart";
Debug.ShouldStop(512);
scifi.mostCurrent._overview10.runMethod(true,"setText",BA.ObjectToCharSequence("Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."));
 BA.debugLineNum = 779;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
Debug.ShouldStop(1024);
scifi.mostCurrent._dramaimage10.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 780;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(2048);
scifi.mostCurrent._dramaimage10.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("lostdaughter.jpg"))).getObject()));
 };
 BA.debugLineNum = 786;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
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
		Debug.PushSubsStack("SearchNow (scifi) ","scifi",4,scifi.mostCurrent.activityBA,scifi.mostCurrent,201);
if (RapidSub.canDelegate("searchnow")) { return b4a.example.scifi.remoteMe.runUserSub(false, "scifi","searchnow");}
RemoteObject _query = RemoteObject.createImmutable("");
RemoteObject _userinput = RemoteObject.createImmutable("");
 BA.debugLineNum = 201;BA.debugLine="Sub SearchNow";
Debug.ShouldStop(256);
 BA.debugLineNum = 202;BA.debugLine="Dim query As String = SearchEngine.Text.ToLowerCa";
Debug.ShouldStop(512);
_query = scifi.mostCurrent._searchengine.runMethod(true,"getText").runMethod(true,"toLowerCase").runMethod(true,"trim");Debug.locals.put("query", _query);Debug.locals.put("query", _query);
 BA.debugLineNum = 205;BA.debugLine="Dim UserInput As String = SearchEngine.Text";
Debug.ShouldStop(4096);
_userinput = scifi.mostCurrent._searchengine.runMethod(true,"getText");Debug.locals.put("UserInput", _userinput);Debug.locals.put("UserInput", _userinput);
 BA.debugLineNum = 208;BA.debugLine="If query.Contains(\"the fellowship of the ring\") O";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("the fellowship of the ring")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("fellowship")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("the ring")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("ring"))))) { 
 BA.debugLineNum = 210;BA.debugLine="Drama1.Text = \"The Fellowship of the Ring\"";
Debug.ShouldStop(131072);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("The Fellowship of the Ring"));
 BA.debugLineNum = 211;BA.debugLine="Starter1.Text = \"Starring: Elijah Wood, Ian McKe";
Debug.ShouldStop(262144);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Elijah Wood, Ian McKellen, Orlando Bloom"));
 BA.debugLineNum = 212;BA.debugLine="Year1.Text = \"(2001)\"";
Debug.ShouldStop(524288);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2001)"));
 BA.debugLineNum = 213;BA.debugLine="OverView1.Text = \"The future of civilization res";
Debug.ShouldStop(1048576);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"));
 BA.debugLineNum = 214;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(2097152);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 215;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(4194304);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("kramer.jpg"))).getObject()));
 BA.debugLineNum = 217;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(16777216);
scifi.mostCurrent._panelmovie2.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 218;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(33554432);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 219;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(67108864);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 220;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(134217728);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 221;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(268435456);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 223;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(1073741824);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 225;BA.debugLine="Else If query.Contains(\"charlie and the chocolate";
Debug.ShouldStop(1);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("charlie and the chocolate factory")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("charlie")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("factory"))))) { 
 BA.debugLineNum = 227;BA.debugLine="Drama1.Text = \"Charlie and the Chocolate Factory";
Debug.ShouldStop(4);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Charlie and the Chocolate Factory"));
 BA.debugLineNum = 228;BA.debugLine="Starter1.Text = \"Starring: Johnny Depp, Freddie";
Debug.ShouldStop(8);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"));
 BA.debugLineNum = 229;BA.debugLine="Year1.Text = \"(2005)\"";
Debug.ShouldStop(16);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2005)"));
 BA.debugLineNum = 230;BA.debugLine="OverView1.Text = \"Charlie Bucket, a humble boy f";
Debug.ShouldStop(32);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."));
 BA.debugLineNum = 231;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(64);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 232;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(128);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("manchester.jpg"))).getObject()));
 BA.debugLineNum = 234;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(512);
scifi.mostCurrent._panelmovie2.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 235;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(1024);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 236;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(2048);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 237;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(4096);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 238;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(8192);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 240;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(32768);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 242;BA.debugLine="Else If query.Contains(\"alice in wonderland\") Or";
Debug.ShouldStop(131072);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("alice in wonderland")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("alice")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("wonderland"))))) { 
 BA.debugLineNum = 244;BA.debugLine="Drama1.Text = \"Alice in Wonderland\"";
Debug.ShouldStop(524288);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Alice in Wonderland"));
 BA.debugLineNum = 245;BA.debugLine="Starter1.Text = \"Starring: Mia Wasikowska, Johnn";
Debug.ShouldStop(1048576);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"));
 BA.debugLineNum = 246;BA.debugLine="Year1.Text = \"(2010)\"";
Debug.ShouldStop(2097152);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2010)"));
 BA.debugLineNum = 247;BA.debugLine="OverView1.Text = \"Alice, now a teenager, returns";
Debug.ShouldStop(4194304);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."));
 BA.debugLineNum = 248;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(8388608);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 249;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(16777216);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("master.jpg"))).getObject()));
 BA.debugLineNum = 251;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(67108864);
scifi.mostCurrent._panelmovie2.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 252;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(134217728);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 253;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(268435456);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 254;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(536870912);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 255;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(1073741824);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 257;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(1);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 259;BA.debugLine="Else If query.Contains(\"harry potter and the phil";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("harry potter and the philosophers stone")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("harry")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("harry potter"))))) { 
 BA.debugLineNum = 261;BA.debugLine="Drama1.Text = \"Harry Potter and the Philosopher'";
Debug.ShouldStop(16);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Harry Potter and the Philosopher's Stone"));
 BA.debugLineNum = 262;BA.debugLine="Starter1.Text = \"Starring: Daniel Radcliffe, Rup";
Debug.ShouldStop(32);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Daniel Radcliffe, Rupert Grint, Emma Watson"));
 BA.debugLineNum = 263;BA.debugLine="Year1.Text = \"(2003)\"";
Debug.ShouldStop(64);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2003)"));
 BA.debugLineNum = 264;BA.debugLine="OverView1.Text = \"Captain Jack Sparrow must resc";
Debug.ShouldStop(128);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Captain Jack Sparrow must rescue Elizabeth Swann, who has been kidnapped by cursed pirates led by Captain Barbossa. Alongside Will Turner, Jack battles ghostly pirates, seeking both freedom and revenge."));
 BA.debugLineNum = 265;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(256);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 266;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(512);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("millondolar.jpg"))).getObject()));
 BA.debugLineNum = 269;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(4096);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 270;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(8192);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 271;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(16384);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 272;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(32768);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 274;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(131072);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 276;BA.debugLine="Else If query.Contains(\"the curse of the black pe";
Debug.ShouldStop(524288);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("the curse of the black pearl")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("curse")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("black pearl"))))) { 
 BA.debugLineNum = 278;BA.debugLine="Drama1.Text = \"The Curse of the Black Pearl\"";
Debug.ShouldStop(2097152);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("The Curse of the Black Pearl"));
 BA.debugLineNum = 279;BA.debugLine="Starter1.Text = \"Starring: Johnny Depp, Orlando";
Debug.ShouldStop(4194304);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Johnny Depp, Orlando Bloom, Keira Knightley"));
 BA.debugLineNum = 280;BA.debugLine="Year1.Text = \"(2003)\"";
Debug.ShouldStop(8388608);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2003)"));
 BA.debugLineNum = 281;BA.debugLine="OverView1.Text = \"Four siblings, evacuated from";
Debug.ShouldStop(16777216);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
 BA.debugLineNum = 282;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(33554432);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 283;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(67108864);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("bridges.jpg"))).getObject()));
 BA.debugLineNum = 286;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(536870912);
scifi.mostCurrent._panelmovie2.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 287;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(1073741824);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 288;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(-2147483648);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 289;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(1);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 290;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(2);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 292;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(8);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 294;BA.debugLine="Else If query.Contains(\"the chronicles of narnia\"";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("the chronicles of narnia")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("chronicles")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("narnia"))))) { 
 BA.debugLineNum = 296;BA.debugLine="Drama1.Text = \"The Chronicles of Narnia\"";
Debug.ShouldStop(128);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("The Chronicles of Narnia"));
 BA.debugLineNum = 297;BA.debugLine="Starter1.Text = \"Starring: Georgie Henley, Skand";
Debug.ShouldStop(256);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Georgie Henley, Skandar Keynes, William Moseley,"));
 BA.debugLineNum = 298;BA.debugLine="Year1.Text = \"(2005)\"";
Debug.ShouldStop(512);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2005)"));
 BA.debugLineNum = 299;BA.debugLine="OverView1.Text = \"Four siblings, evacuated from";
Debug.ShouldStop(1024);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
 BA.debugLineNum = 300;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(2048);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 301;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(4096);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("gonebaby.jpg"))).getObject()));
 BA.debugLineNum = 303;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(16384);
scifi.mostCurrent._panelmovie2.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 304;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(32768);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 305;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(65536);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 306;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(131072);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 307;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(262144);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 309;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(1048576);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 311;BA.debugLine="Else If query.Contains(\"doctor strange\") Or query";
Debug.ShouldStop(4194304);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("doctor strange")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("doctor")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("strange"))))) { 
 BA.debugLineNum = 313;BA.debugLine="Drama1.Text = \"Doctor Strange\"";
Debug.ShouldStop(16777216);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Doctor Strange"));
 BA.debugLineNum = 314;BA.debugLine="Starter1.Text = \"Starring: Benedict Cumberbatch,";
Debug.ShouldStop(33554432);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Benedict Cumberbatch, Chiwetel Ejiofor"));
 BA.debugLineNum = 315;BA.debugLine="Year1.Text = \"(2016)\"";
Debug.ShouldStop(67108864);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2016)"));
 BA.debugLineNum = 316;BA.debugLine="OverView1.Text = \"After a life-changing accident";
Debug.ShouldStop(134217728);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("After a life-changing accident ends his career, arrogant surgeon Stephen Strange seeks healing in Nepal, where he learns the mystic arts and must protect the world from dark magical forces beyond reality."));
 BA.debugLineNum = 317;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(268435456);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 318;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(536870912);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("bluejasmine.jpg"))).getObject()));
 BA.debugLineNum = 320;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(-2147483648);
scifi.mostCurrent._panelmovie2.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 321;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(1);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 322;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(2);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 323;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(4);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 324;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(8);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 326;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(32);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 328;BA.debugLine="Else If query.Contains(\"v for vendetta\") Or query";
Debug.ShouldStop(128);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("v for vendetta")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("vendetta")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("v"))))) { 
 BA.debugLineNum = 330;BA.debugLine="Drama1.Text = \"V for Vendetta\"";
Debug.ShouldStop(512);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("V for Vendetta"));
 BA.debugLineNum = 331;BA.debugLine="Starter1.Text = \"Starring: Hugo Weaving, Natalie";
Debug.ShouldStop(1024);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugo Weaving, Natalie Portman, Stephen Rea, John Hurt"));
 BA.debugLineNum = 332;BA.debugLine="Year1.Text = \"(2005)\"";
Debug.ShouldStop(2048);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2005)"));
 BA.debugLineNum = 333;BA.debugLine="OverView1.Text = \"In a totalitarian future Brita";
Debug.ShouldStop(4096);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In a totalitarian future Britain, a masked revolutionary known as \"V\" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."));
 BA.debugLineNum = 334;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(8192);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 335;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(16384);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("her.jpg"))).getObject()));
 BA.debugLineNum = 337;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(65536);
scifi.mostCurrent._panelmovie2.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 338;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(131072);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 339;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(262144);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 340;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(524288);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 341;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(1048576);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 343;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(4194304);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 345;BA.debugLine="Else If query.Contains(\"aladdin\") Then";
Debug.ShouldStop(16777216);
if (_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("aladdin"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 347;BA.debugLine="Drama1.Text = \"Aladdin\"";
Debug.ShouldStop(67108864);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Aladdin"));
 BA.debugLineNum = 348;BA.debugLine="Starter1.Text = \"Starring: Mena Massoud, Naomi S";
Debug.ShouldStop(134217728);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Mena Massoud, Naomi Scott, Will Smith"));
 BA.debugLineNum = 349;BA.debugLine="Year1.Text = \"(2019)\"";
Debug.ShouldStop(268435456);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2019)"));
 BA.debugLineNum = 350;BA.debugLine="OverView1.Text = \"Aladdin, a kind-hearted street";
Debug.ShouldStop(536870912);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."));
 BA.debugLineNum = 351;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(1073741824);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 352;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(-2147483648);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("carol.jpg"))).getObject()));
 BA.debugLineNum = 354;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(2);
scifi.mostCurrent._panelmovie2.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 355;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(4);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 356;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(8);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 357;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(16);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 358;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(32);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 360;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(128);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 362;BA.debugLine="Else If query.Contains(\"after earth\") Or query.Co";
Debug.ShouldStop(512);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("after earth")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("after")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("earth"))))) { 
 BA.debugLineNum = 364;BA.debugLine="Drama1.Text = \"After Earth\"";
Debug.ShouldStop(2048);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("After Earth"));
 BA.debugLineNum = 365;BA.debugLine="Starter1.Text = \"Starring: Will Smith, Jaden Smi";
Debug.ShouldStop(4096);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Will Smith, Jaden Smith, Sigourney Weaver"));
 BA.debugLineNum = 366;BA.debugLine="Year1.Text = \"(2013)\"";
Debug.ShouldStop(8192);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 367;BA.debugLine="OverView1.Text = \"Set in the future, After Earth";
Debug.ShouldStop(16384);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."));
 BA.debugLineNum = 368;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(32768);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 369;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(65536);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("lostdaughter.jpg"))).getObject()));
 BA.debugLineNum = 371;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(262144);
scifi.mostCurrent._panelmovie2.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 372;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(524288);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 373;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(1048576);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 374;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(2097152);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 375;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(4194304);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 377;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(16777216);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 379;BA.debugLine="Else If query.Contains(\"orlando bloom\") Or query.";
Debug.ShouldStop(67108864);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("orlando bloom")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("orlando")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("bloom"))))) { 
 BA.debugLineNum = 380;BA.debugLine="Drama1.Text = \"The Fellowship of the Ring\"";
Debug.ShouldStop(134217728);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("The Fellowship of the Ring"));
 BA.debugLineNum = 381;BA.debugLine="Starter1.Text = \"Starring: Elijah Wood, Ian McKe";
Debug.ShouldStop(268435456);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Elijah Wood, Ian McKellen, Orlando Bloom"));
 BA.debugLineNum = 382;BA.debugLine="Year1.Text = \"(2001)\"";
Debug.ShouldStop(536870912);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2001)"));
 BA.debugLineNum = 383;BA.debugLine="OverView1.Text = \"The future of civilization res";
Debug.ShouldStop(1073741824);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"));
 BA.debugLineNum = 384;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(-2147483648);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 385;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(1);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("kramer.jpg"))).getObject()));
 BA.debugLineNum = 387;BA.debugLine="Drama2.Text = \"The Curse of the Black Pearl\"";
Debug.ShouldStop(4);
scifi.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("The Curse of the Black Pearl"));
 BA.debugLineNum = 388;BA.debugLine="Starter2.Text = \"Starring: Johnny Depp, Orlando";
Debug.ShouldStop(8);
scifi.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Johnny Depp, Orlando Bloom, Keira Knightley, Geoffrey Rush"));
 BA.debugLineNum = 389;BA.debugLine="Year2.Text = \"(2003)\"";
Debug.ShouldStop(16);
scifi.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2003)"));
 BA.debugLineNum = 390;BA.debugLine="OverView2.Text = \"Four siblings, evacuated from";
Debug.ShouldStop(32);
scifi.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
 BA.debugLineNum = 391;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(64);
scifi.mostCurrent._dramaimage2.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 392;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(128);
scifi.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("bridges.jpg"))).getObject()));
 BA.debugLineNum = 394;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(512);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 395;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(1024);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 396;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(2048);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 398;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(8192);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 400;BA.debugLine="Else If query.Contains(\"johnny depp\") Or query.Co";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("johnny depp")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("johnny")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("depp"))))) { 
 BA.debugLineNum = 401;BA.debugLine="Drama1.Text = \"Alice in Wonderland\"";
Debug.ShouldStop(65536);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Alice in Wonderland"));
 BA.debugLineNum = 402;BA.debugLine="Starter1.Text = \"Starring: Mia Wasikowska, Johnn";
Debug.ShouldStop(131072);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"));
 BA.debugLineNum = 403;BA.debugLine="Year1.Text = \"(2010)\"";
Debug.ShouldStop(262144);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2010)"));
 BA.debugLineNum = 404;BA.debugLine="OverView1.Text = \"Alice, now a teenager, returns";
Debug.ShouldStop(524288);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."));
 BA.debugLineNum = 405;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(1048576);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 406;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(2097152);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("master.jpg"))).getObject()));
 BA.debugLineNum = 408;BA.debugLine="Drama2.Text = \"Charlie and the Chocolate Factory";
Debug.ShouldStop(8388608);
scifi.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Charlie and the Chocolate Factory"));
 BA.debugLineNum = 409;BA.debugLine="Starter2.Text = \"Starring: Johnny Depp, Freddie";
Debug.ShouldStop(16777216);
scifi.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"));
 BA.debugLineNum = 410;BA.debugLine="Year2.Text = \"(2005)\"";
Debug.ShouldStop(33554432);
scifi.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2005)"));
 BA.debugLineNum = 411;BA.debugLine="OverView2.Text = \"Charlie Bucket, a humble boy f";
Debug.ShouldStop(67108864);
scifi.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."));
 BA.debugLineNum = 412;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(134217728);
scifi.mostCurrent._dramaimage2.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 413;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(268435456);
scifi.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("manchester.jpg"))).getObject()));
 BA.debugLineNum = 415;BA.debugLine="Drama3.Text = \"The Curse of the Black Pearl\"";
Debug.ShouldStop(1073741824);
scifi.mostCurrent._drama3.runMethod(true,"setText",BA.ObjectToCharSequence("The Curse of the Black Pearl"));
 BA.debugLineNum = 416;BA.debugLine="Starter3.Text = \"Starring: Johnny Depp, Orlando";
Debug.ShouldStop(-2147483648);
scifi.mostCurrent._starter3.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Johnny Depp, Orlando Bloom, Keira Knightley, Geoffrey Rush"));
 BA.debugLineNum = 417;BA.debugLine="Year3.Text = \"(2003)\"";
Debug.ShouldStop(1);
scifi.mostCurrent._year3.runMethod(true,"setText",BA.ObjectToCharSequence("(2003)"));
 BA.debugLineNum = 418;BA.debugLine="OverView3.Text = \"Four siblings, evacuated from";
Debug.ShouldStop(2);
scifi.mostCurrent._overview3.runMethod(true,"setText",BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
 BA.debugLineNum = 419;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
Debug.ShouldStop(4);
scifi.mostCurrent._dramaimage3.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 420;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(8);
scifi.mostCurrent._dramaimage3.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("bridges.jpg"))).getObject()));
 BA.debugLineNum = 422;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(32);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 423;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(64);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 424;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(128);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 426;BA.debugLine="p.Height = 85%y";
Debug.ShouldStop(512);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 85)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 428;BA.debugLine="Else If query.Contains(\"helena bonham carter\") Or";
Debug.ShouldStop(2048);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("helena bonham carter")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("helena")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("bonham")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("carter")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("helena bonham"))))) { 
 BA.debugLineNum = 429;BA.debugLine="Drama1.Text = \"Charlie and the Chocolate Factory";
Debug.ShouldStop(4096);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Charlie and the Chocolate Factory"));
 BA.debugLineNum = 430;BA.debugLine="Starter1.Text = \"Starring: Johnny Depp, Freddie";
Debug.ShouldStop(8192);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"));
 BA.debugLineNum = 431;BA.debugLine="Year1.Text = \"(2005)\"";
Debug.ShouldStop(16384);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2005)"));
 BA.debugLineNum = 432;BA.debugLine="OverView1.Text = \"Charlie Bucket, a humble boy f";
Debug.ShouldStop(32768);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."));
 BA.debugLineNum = 433;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(65536);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 434;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(131072);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("manchester.jpg"))).getObject()));
 BA.debugLineNum = 436;BA.debugLine="Drama2.Text = \"Alice in Wonderland\"";
Debug.ShouldStop(524288);
scifi.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Alice in Wonderland"));
 BA.debugLineNum = 437;BA.debugLine="Starter2.Text = \"Starring: Mia Wasikowska, Johnn";
Debug.ShouldStop(1048576);
scifi.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"));
 BA.debugLineNum = 438;BA.debugLine="Year2.Text = \"(2010)\"";
Debug.ShouldStop(2097152);
scifi.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2010)"));
 BA.debugLineNum = 439;BA.debugLine="OverView2.Text = \"Alice, now a teenager, returns";
Debug.ShouldStop(4194304);
scifi.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."));
 BA.debugLineNum = 440;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(8388608);
scifi.mostCurrent._dramaimage2.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 441;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(16777216);
scifi.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("master.jpg"))).getObject()));
 BA.debugLineNum = 443;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(67108864);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 444;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(134217728);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 445;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(268435456);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 446;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(536870912);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 448;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(-2147483648);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 450;BA.debugLine="Else If query.Contains(\"will smith\") Or query.Con";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("will smith")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("will")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("smith"))))) { 
 BA.debugLineNum = 451;BA.debugLine="Drama1.Text = \"Aladdin\"";
Debug.ShouldStop(4);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Aladdin"));
 BA.debugLineNum = 452;BA.debugLine="Starter1.Text = \"Starring: Mena Massoud, Naomi S";
Debug.ShouldStop(8);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Mena Massoud, Naomi Scott, Will Smith"));
 BA.debugLineNum = 453;BA.debugLine="Year1.Text = \"(2019)\"";
Debug.ShouldStop(16);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2019)"));
 BA.debugLineNum = 454;BA.debugLine="OverView1.Text = \"Aladdin, a kind-hearted street";
Debug.ShouldStop(32);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."));
 BA.debugLineNum = 455;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(64);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 456;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(128);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("carol.jpg"))).getObject()));
 BA.debugLineNum = 458;BA.debugLine="Drama1.Text = \"After Earth\"";
Debug.ShouldStop(512);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("After Earth"));
 BA.debugLineNum = 459;BA.debugLine="Starter1.Text = \"Starring: Will Smith, Jaden Smi";
Debug.ShouldStop(1024);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Will Smith, Jaden Smith, Sigourney Weaver"));
 BA.debugLineNum = 460;BA.debugLine="Year1.Text = \"(2013)\"";
Debug.ShouldStop(2048);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 461;BA.debugLine="OverView1.Text = \"Set in the future, After Earth";
Debug.ShouldStop(4096);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."));
 BA.debugLineNum = 462;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(8192);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 463;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(16384);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("lostdaughter.jpg"))).getObject()));
 BA.debugLineNum = 465;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(65536);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 466;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(131072);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 468;BA.debugLine="p.Height = 85%y";
Debug.ShouldStop(524288);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 85)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 470;BA.debugLine="Else If query.Contains(\"william moseley\") Or quer";
Debug.ShouldStop(2097152);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("william moseley")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("william")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("moseley"))))) { 
 BA.debugLineNum = 472;BA.debugLine="Drama1.Text = \"The Chronicles of Narnia\"";
Debug.ShouldStop(8388608);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("The Chronicles of Narnia"));
 BA.debugLineNum = 473;BA.debugLine="Starter1.Text = \"Starring: Georgie Henley, Skand";
Debug.ShouldStop(16777216);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Georgie Henley, Skandar Keynes, William Moseley"));
 BA.debugLineNum = 474;BA.debugLine="Year1.Text = \"(2005)\"";
Debug.ShouldStop(33554432);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2005)"));
 BA.debugLineNum = 475;BA.debugLine="OverView1.Text = \"Four siblings, evacuated from";
Debug.ShouldStop(67108864);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
 BA.debugLineNum = 476;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(134217728);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 477;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(268435456);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("gonebaby.jpg"))).getObject()));
 BA.debugLineNum = 479;BA.debugLine="Drama2.Text = \"The Little Mermaid\"";
Debug.ShouldStop(1073741824);
scifi.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("The Little Mermaid"));
 BA.debugLineNum = 480;BA.debugLine="Starter2.Text = \"Starring: Poppy Drayton, Willia";
Debug.ShouldStop(-2147483648);
scifi.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Poppy Drayton, William Moseley, Shirley MacLaine"));
 BA.debugLineNum = 481;BA.debugLine="Year2.Text = \"(2018)\"";
Debug.ShouldStop(1);
scifi.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2018)"));
 BA.debugLineNum = 482;BA.debugLine="OverView2.Text = \"A young reporter and his niece";
Debug.ShouldStop(2);
scifi.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("A young reporter and his niece discover a real-life mermaid being held captive by a shady circus owner. As they befriend the mermaid, they embark on a magical adventure to save her and help her return to the sea."));
 BA.debugLineNum = 483;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(4);
scifi.mostCurrent._dramaimage2.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 484;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(8);
scifi.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("gonebaby.jpg"))).getObject()));
 BA.debugLineNum = 486;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(32);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 487;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(64);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 488;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(128);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 489;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(256);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 491;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(1024);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 493;BA.debugLine="Else If query.Contains(\"natalie portman\") Or quer";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("natalie portman")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("natalie")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("portman"))))) { 
 BA.debugLineNum = 495;BA.debugLine="Drama1.Text = \"V for Vendetta\"";
Debug.ShouldStop(16384);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("V for Vendetta"));
 BA.debugLineNum = 496;BA.debugLine="Starter1.Text = \"Starring: Hugo Weaving, Natalie";
Debug.ShouldStop(32768);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugo Weaving, Natalie Portman, Stephen Rea"));
 BA.debugLineNum = 497;BA.debugLine="Year1.Text = \"(2005)\"";
Debug.ShouldStop(65536);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2005)"));
 BA.debugLineNum = 498;BA.debugLine="OverView1.Text = \"In a totalitarian future Brita";
Debug.ShouldStop(131072);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In a totalitarian future Britain, a masked revolutionary known as \"V\" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."));
 BA.debugLineNum = 499;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(262144);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 500;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(524288);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("her.jpg"))).getObject()));
 BA.debugLineNum = 502;BA.debugLine="Drama2.Text = \"Thor: The Dark World\"";
Debug.ShouldStop(2097152);
scifi.mostCurrent._drama2.runMethod(true,"setText",BA.ObjectToCharSequence("Thor: The Dark World"));
 BA.debugLineNum = 503;BA.debugLine="Starter2.Text = \"Starring: Chris Hemsworth, Nata";
Debug.ShouldStop(4194304);
scifi.mostCurrent._starter2.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Chris Hemsworth, Natalie Portman, Tom Hiddleston"));
 BA.debugLineNum = 504;BA.debugLine="Year2.Text = \"(2013)\"";
Debug.ShouldStop(8388608);
scifi.mostCurrent._year2.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 505;BA.debugLine="OverView2.Text = \"Thor must team up with his tre";
Debug.ShouldStop(16777216);
scifi.mostCurrent._overview2.runMethod(true,"setText",BA.ObjectToCharSequence("Thor must team up with his treacherous brother Loki to stop the Dark Elves, led by the vengeful Malekith, who seeks to plunge the universe into darkness using a powerful ancient force known as the Aether."));
 BA.debugLineNum = 506;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
Debug.ShouldStop(33554432);
scifi.mostCurrent._dramaimage2.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 507;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(67108864);
scifi.mostCurrent._dramaimage2.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("gonebaby.jpg"))).getObject()));
 BA.debugLineNum = 509;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(268435456);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 510;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(536870912);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 511;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(1073741824);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 512;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(-2147483648);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 514;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(2);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 517;BA.debugLine="Else If query.Contains(\"elijah wood\") Or query.Co";
Debug.ShouldStop(16);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("elijah wood")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("elijah")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("wood")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("ian mckellen")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("ian")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("mckellen"))))) { 
 BA.debugLineNum = 518;BA.debugLine="Drama1.Text = \"The Fellowship of the Ring\"";
Debug.ShouldStop(32);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("The Fellowship of the Ring"));
 BA.debugLineNum = 519;BA.debugLine="Starter1.Text = \"Starring: Elijah Wood, Ian McKe";
Debug.ShouldStop(64);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Elijah Wood, Ian McKellen, Orlando Bloom"));
 BA.debugLineNum = 520;BA.debugLine="Year1.Text = \"(2001)\"";
Debug.ShouldStop(128);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2001)"));
 BA.debugLineNum = 521;BA.debugLine="OverView1.Text = \"The future of civilization res";
Debug.ShouldStop(256);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"));
 BA.debugLineNum = 522;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(512);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 523;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(1024);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("kramer.jpg"))).getObject()));
 BA.debugLineNum = 525;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(4096);
scifi.mostCurrent._panelmovie2.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 526;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(8192);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 527;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(16384);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 528;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(32768);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 529;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(65536);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 531;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(262144);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 532;BA.debugLine="Else If query.Contains(\"freddie highmore\") Or que";
Debug.ShouldStop(524288);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("freddie highmore")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("freddie")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("highmore")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("david kelly")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("david")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("kelly"))))) { 
 BA.debugLineNum = 533;BA.debugLine="Drama1.Text = \"Charlie and the Chocolate Factory";
Debug.ShouldStop(1048576);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Charlie and the Chocolate Factory"));
 BA.debugLineNum = 534;BA.debugLine="Starter1.Text = \"Starring: Johnny Depp, Freddie";
Debug.ShouldStop(2097152);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"));
 BA.debugLineNum = 535;BA.debugLine="Year1.Text = \"(2005)\"";
Debug.ShouldStop(4194304);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2005)"));
 BA.debugLineNum = 536;BA.debugLine="OverView1.Text = \"Charlie Bucket, a humble boy f";
Debug.ShouldStop(8388608);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."));
 BA.debugLineNum = 537;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(16777216);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 538;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(33554432);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("manchester.jpg"))).getObject()));
 BA.debugLineNum = 540;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(134217728);
scifi.mostCurrent._panelmovie2.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 541;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(268435456);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 542;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(536870912);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 543;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(1073741824);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 544;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(-2147483648);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 546;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(2);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 547;BA.debugLine="Else if query.Contains(\"mia wasikowska\") Or query";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("mia wasikowska")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("mia")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("wasikowska")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("anne hathaway")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("anne")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("hathaway"))))) { 
 BA.debugLineNum = 548;BA.debugLine="Drama1.Text = \"Alice in Wonderland\"";
Debug.ShouldStop(8);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Alice in Wonderland"));
 BA.debugLineNum = 549;BA.debugLine="Starter1.Text = \"Starring: Mia Wasikowska, Johnn";
Debug.ShouldStop(16);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"));
 BA.debugLineNum = 550;BA.debugLine="Year1.Text = \"(2010)\"";
Debug.ShouldStop(32);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2010)"));
 BA.debugLineNum = 551;BA.debugLine="OverView1.Text = \"Alice, now a teenager, returns";
Debug.ShouldStop(64);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."));
 BA.debugLineNum = 552;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(128);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 553;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(256);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("master.jpg"))).getObject()));
 BA.debugLineNum = 555;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(1024);
scifi.mostCurrent._panelmovie2.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 556;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(2048);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 557;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(4096);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 558;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(8192);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 559;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(16384);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 561;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(65536);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 562;BA.debugLine="Else if query.Contains(\"daniel radcliffe\") Or que";
Debug.ShouldStop(131072);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("daniel radcliffe")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("daniel")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("radcliffe")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("rupert grint")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("rupert")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("grint")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("emma watson")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("emma")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("watson"))))) { 
 BA.debugLineNum = 563;BA.debugLine="Drama1.Text = \"Harry Potter and the Philosopher'";
Debug.ShouldStop(262144);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Harry Potter and the Philosopher's Stone"));
 BA.debugLineNum = 564;BA.debugLine="Starter1.Text = \"Starring: Daniel Radcliffe, Rup";
Debug.ShouldStop(524288);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Daniel Radcliffe, Rupert Grint, Emma Watson"));
 BA.debugLineNum = 565;BA.debugLine="Year1.Text = \"(2003)\"";
Debug.ShouldStop(1048576);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2003)"));
 BA.debugLineNum = 566;BA.debugLine="OverView1.Text = \"Captain Jack Sparrow must resc";
Debug.ShouldStop(2097152);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Captain Jack Sparrow must rescue Elizabeth Swann, who has been kidnapped by cursed pirates led by Captain Barbossa. Alongside Will Turner, Jack battles ghostly pirates, seeking both freedom and revenge."));
 BA.debugLineNum = 567;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(4194304);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 568;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(8388608);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("millondolar.jpg"))).getObject()));
 BA.debugLineNum = 570;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(33554432);
scifi.mostCurrent._panelmovie2.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 571;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(67108864);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 572;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(134217728);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 573;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(268435456);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 574;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(536870912);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 576;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(-2147483648);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 577;BA.debugLine="Else if query.Contains(\"keira knightley\") Or quer";
Debug.ShouldStop(1);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("keira knightley")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("keira")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("knightley"))))) { 
 BA.debugLineNum = 578;BA.debugLine="Drama1.Text = \"The Curse of the Black Pearl\"";
Debug.ShouldStop(2);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("The Curse of the Black Pearl"));
 BA.debugLineNum = 579;BA.debugLine="Starter1.Text = \"Starring: Johnny Depp, Orlando";
Debug.ShouldStop(4);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Johnny Depp, Orlando Bloom, Keira Knightley"));
 BA.debugLineNum = 580;BA.debugLine="Year1.Text = \"(2003)\"";
Debug.ShouldStop(8);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2003)"));
 BA.debugLineNum = 581;BA.debugLine="OverView1.Text = \"Four siblings, evacuated from";
Debug.ShouldStop(16);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
 BA.debugLineNum = 582;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(32);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 583;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(64);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("bridges.jpg"))).getObject()));
 BA.debugLineNum = 585;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(256);
scifi.mostCurrent._panelmovie2.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 586;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(512);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 587;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(1024);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 588;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(2048);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 589;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(4096);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 591;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(16384);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 593;BA.debugLine="Else if query.Contains(\"skandar keynes\") Or query";
Debug.ShouldStop(65536);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("skandar keynes")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("skandar")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("keynes")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("william moseley")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("william")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("moseley"))))) { 
 BA.debugLineNum = 594;BA.debugLine="Drama6.Text = \"The Chronicles of Narnia\"";
Debug.ShouldStop(131072);
scifi.mostCurrent._drama6.runMethod(true,"setText",BA.ObjectToCharSequence("The Chronicles of Narnia"));
 BA.debugLineNum = 595;BA.debugLine="Starter6.Text = \"Starring: Georgie Henley, Skand";
Debug.ShouldStop(262144);
scifi.mostCurrent._starter6.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Georgie Henley, Skandar Keynes, William Moseley"));
 BA.debugLineNum = 596;BA.debugLine="Year6.Text = \"(2005)\"";
Debug.ShouldStop(524288);
scifi.mostCurrent._year6.runMethod(true,"setText",BA.ObjectToCharSequence("(2005)"));
 BA.debugLineNum = 597;BA.debugLine="OverView6.Text = \"Four siblings, evacuated from";
Debug.ShouldStop(1048576);
scifi.mostCurrent._overview6.runMethod(true,"setText",BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
 BA.debugLineNum = 598;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
Debug.ShouldStop(2097152);
scifi.mostCurrent._dramaimage6.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 599;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(4194304);
scifi.mostCurrent._dramaimage6.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("gonebaby.jpg"))).getObject()));
 BA.debugLineNum = 601;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(16777216);
scifi.mostCurrent._panelmovie2.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 602;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(33554432);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 603;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(67108864);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 604;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(134217728);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 605;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(268435456);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 607;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(1073741824);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 609;BA.debugLine="Else if query.Contains(\"benedict cumberbatch\") Or";
Debug.ShouldStop(1);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("benedict cumberbatch")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("benedict")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("cumberbatch")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("chiwetel ejiofor")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("chiwetel")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("ejiofor"))))) { 
 BA.debugLineNum = 610;BA.debugLine="Drama1.Text = \"Doctor Strange\"";
Debug.ShouldStop(2);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Doctor Strange"));
 BA.debugLineNum = 611;BA.debugLine="Starter1.Text = \"Starring: Benedict Cumberbatch,";
Debug.ShouldStop(4);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Benedict Cumberbatch, Chiwetel Ejiofor"));
 BA.debugLineNum = 612;BA.debugLine="Year1.Text = \"(2016)\"";
Debug.ShouldStop(8);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2016)"));
 BA.debugLineNum = 613;BA.debugLine="OverView1.Text = \"After a life-changing accident";
Debug.ShouldStop(16);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("After a life-changing accident ends his career, arrogant surgeon Stephen Strange seeks healing in Nepal, where he learns the mystic arts and must protect the world from dark magical forces beyond reality."));
 BA.debugLineNum = 614;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(32);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 615;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(64);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("bluejasmine.jpg"))).getObject()));
 BA.debugLineNum = 617;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(256);
scifi.mostCurrent._panelmovie2.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 618;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(512);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 619;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(1024);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 620;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(2048);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 621;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(4096);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 623;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(16384);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 625;BA.debugLine="Else if query.Contains(\"hugo weaving\") Or query.C";
Debug.ShouldStop(65536);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("hugo weaving")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("hugo")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("weaving")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("stephen rea")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("stephen")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("rea"))))) { 
 BA.debugLineNum = 626;BA.debugLine="Drama1.Text = \"V for Vendetta\"";
Debug.ShouldStop(131072);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("V for Vendetta"));
 BA.debugLineNum = 627;BA.debugLine="Starter1.Text = \"Starring: Hugo Weaving, Natalie";
Debug.ShouldStop(262144);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Hugo Weaving, Natalie Portman, Stephen Rea"));
 BA.debugLineNum = 628;BA.debugLine="Year1.Text = \"(2005)\"";
Debug.ShouldStop(524288);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2005)"));
 BA.debugLineNum = 629;BA.debugLine="OverView1.Text = \"In a totalitarian future Brita";
Debug.ShouldStop(1048576);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("In a totalitarian future Britain, a masked revolutionary known as \"V\" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."));
 BA.debugLineNum = 630;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(2097152);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 631;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(4194304);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("her.jpg"))).getObject()));
 BA.debugLineNum = 633;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(16777216);
scifi.mostCurrent._panelmovie2.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 634;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(33554432);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 635;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(67108864);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 636;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(134217728);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 637;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(268435456);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 639;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(1073741824);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 642;BA.debugLine="Else if query.Contains(\"mena massoud\") Or query.C";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("mena massoud")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("mena")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("massoud")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("naomi scott")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("naomi")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("scott"))))) { 
 BA.debugLineNum = 643;BA.debugLine="Drama1.Text = \"Aladdin\"";
Debug.ShouldStop(4);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("Aladdin"));
 BA.debugLineNum = 644;BA.debugLine="Starter1.Text = \"Starring: Mena Massoud, Naomi S";
Debug.ShouldStop(8);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Mena Massoud, Naomi Scott, Will Smith"));
 BA.debugLineNum = 645;BA.debugLine="Year1.Text = \"(2019)\"";
Debug.ShouldStop(16);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2019)"));
 BA.debugLineNum = 646;BA.debugLine="OverView1.Text = \"Aladdin, a kind-hearted street";
Debug.ShouldStop(32);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."));
 BA.debugLineNum = 647;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(64);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 648;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(128);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("carol.jpg"))).getObject()));
 BA.debugLineNum = 650;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(512);
scifi.mostCurrent._panelmovie2.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 651;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(1024);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 652;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(2048);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 653;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(4096);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 654;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(8192);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 656;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(32768);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else 
{ BA.debugLineNum = 658;BA.debugLine="Else if query.Contains(\"jaden smith\") Or query.Co";
Debug.ShouldStop(131072);
if (RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("jaden smith")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("jaden")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("smith")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("sigourney weave")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("sigourney")))) || RemoteObject.solveBoolean(".",_query.runMethod(true,"contains",(Object)(RemoteObject.createImmutable("weave"))))) { 
 BA.debugLineNum = 659;BA.debugLine="Drama1.Text = \"After Earth\"";
Debug.ShouldStop(262144);
scifi.mostCurrent._drama1.runMethod(true,"setText",BA.ObjectToCharSequence("After Earth"));
 BA.debugLineNum = 660;BA.debugLine="Starter1.Text = \"Starring: Will Smith, Jaden Smi";
Debug.ShouldStop(524288);
scifi.mostCurrent._starter1.runMethod(true,"setText",BA.ObjectToCharSequence("Starring: Will Smith, Jaden Smith, Sigourney Weaver"));
 BA.debugLineNum = 661;BA.debugLine="Year1.Text = \"(2013)\"";
Debug.ShouldStop(1048576);
scifi.mostCurrent._year1.runMethod(true,"setText",BA.ObjectToCharSequence("(2013)"));
 BA.debugLineNum = 662;BA.debugLine="OverView1.Text = \"Set in the future, After Earth";
Debug.ShouldStop(2097152);
scifi.mostCurrent._overview1.runMethod(true,"setText",BA.ObjectToCharSequence("Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."));
 BA.debugLineNum = 663;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
Debug.ShouldStop(4194304);
scifi.mostCurrent._dramaimage1.runMethod(true,"setGravity",scifi.mostCurrent.__c.getField(false,"Gravity").getField(true,"FILL"));
 BA.debugLineNum = 664;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
Debug.ShouldStop(8388608);
scifi.mostCurrent._dramaimage1.runMethod(false,"setBitmap",(scifi.mostCurrent.__c.runMethod(false,"LoadBitmap",(Object)(scifi.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(RemoteObject.createImmutable("lostdaughter.jpg"))).getObject()));
 BA.debugLineNum = 666;BA.debugLine="PanelMovie2.Visible = False";
Debug.ShouldStop(33554432);
scifi.mostCurrent._panelmovie2.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 667;BA.debugLine="PanelMovie3.Visible = False";
Debug.ShouldStop(67108864);
scifi.mostCurrent._panelmovie3.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 668;BA.debugLine="PanelMovie4.Visible = False";
Debug.ShouldStop(134217728);
scifi.mostCurrent._panelmovie4.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 669;BA.debugLine="PanelMovie5.Visible = False";
Debug.ShouldStop(268435456);
scifi.mostCurrent._panelmovie5.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 670;BA.debugLine="PanelMovie6.Visible = False";
Debug.ShouldStop(536870912);
scifi.mostCurrent._panelmovie6.runMethod(true,"setVisible",scifi.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 672;BA.debugLine="p.Height = 70%y";
Debug.ShouldStop(-2147483648);
scifi.mostCurrent._p.runMethod(true,"setHeight",scifi.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 70)),scifi.mostCurrent.activityBA));
 }else {
 BA.debugLineNum = 676;BA.debugLine="MsgboxAsync(\"No results found for\" & \" \"\"\" & Use";
Debug.ShouldStop(8);
scifi.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("No results found for"),RemoteObject.createImmutable(" \""),_userinput,RemoteObject.createImmutable("\"")))),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable(""))),scifi.processBA);
 }}}}}}}}}}}}}}}}}}}}}}}}}}
;
 BA.debugLineNum = 680;BA.debugLine="p.Width = 100%x";
Debug.ShouldStop(128);
scifi.mostCurrent._p.runMethod(true,"setWidth",scifi.mostCurrent.__c.runMethod(true,"PerXToCurrent",(Object)(BA.numberCast(float.class, 100)),scifi.mostCurrent.activityBA));
 BA.debugLineNum = 681;BA.debugLine="ScrollView1.Panel.Height = p.Height";
Debug.ShouldStop(256);
scifi.mostCurrent._scrollview1.runMethod(false,"getPanel").runMethod(true,"setHeight",scifi.mostCurrent._p.runMethod(true,"getHeight"));
 BA.debugLineNum = 683;BA.debugLine="End Sub";
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