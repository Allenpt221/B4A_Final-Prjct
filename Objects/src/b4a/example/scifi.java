package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class scifi extends Activity implements B4AActivity{
	public static scifi mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.scifi");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (scifi).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.scifi");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.scifi", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (scifi) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (scifi) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return scifi.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (scifi) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (scifi) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            scifi mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (scifi) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }



public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scrollview1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _drama1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _drama2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _drama3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _drama4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _drama5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _drama6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _drama7 = null;
public anywheresoftware.b4a.objects.LabelWrapper _drama8 = null;
public anywheresoftware.b4a.objects.LabelWrapper _drama9 = null;
public anywheresoftware.b4a.objects.LabelWrapper _drama10 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _dramaimage1 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _dramaimage2 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _dramaimage3 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _dramaimage4 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _dramaimage5 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _dramaimage6 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _dramaimage7 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _dramaimage8 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _dramaimage9 = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _dramaimage10 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _searchbtn = null;
public anywheresoftware.b4a.objects.EditTextWrapper _searchengine = null;
public anywheresoftware.b4a.objects.PanelWrapper _p = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panelmovie1 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panelmovie2 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panelmovie3 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panelmovie4 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panelmovie5 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panelmovie6 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panelmovie7 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panelmovie8 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panelmovie9 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panelmovie10 = null;
public anywheresoftware.b4a.objects.LabelWrapper _dramapage = null;
public anywheresoftware.b4a.objects.LabelWrapper _homepage = null;
public anywheresoftware.b4a.objects.LabelWrapper _scifipage = null;
public anywheresoftware.b4a.objects.LabelWrapper _actionpage = null;
public anywheresoftware.b4a.objects.LabelWrapper _starter1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _starter2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _starter3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _starter4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _starter5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _starter6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _starter7 = null;
public anywheresoftware.b4a.objects.LabelWrapper _starter8 = null;
public anywheresoftware.b4a.objects.LabelWrapper _starter9 = null;
public anywheresoftware.b4a.objects.LabelWrapper _starter10 = null;
public anywheresoftware.b4a.objects.LabelWrapper _overview1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _overview2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _overview3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _overview4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _overview5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _overview6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _overview7 = null;
public anywheresoftware.b4a.objects.LabelWrapper _overview8 = null;
public anywheresoftware.b4a.objects.LabelWrapper _overview9 = null;
public anywheresoftware.b4a.objects.LabelWrapper _overview10 = null;
public anywheresoftware.b4a.objects.LabelWrapper _year1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _year2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _year3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _year4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _year5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _year6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _year7 = null;
public anywheresoftware.b4a.objects.LabelWrapper _year8 = null;
public anywheresoftware.b4a.objects.LabelWrapper _year9 = null;
public anywheresoftware.b4a.objects.LabelWrapper _year10 = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.drama _drama = null;
public b4a.example.action _action = null;
public b4a.example.panelview _panelview = null;
public static String  _actionpage_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "actionpage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "actionpage_click", null));}
RDebugUtils.currentLine=4456448;
 //BA.debugLineNum = 4456448;BA.debugLine="Private Sub ActionPage_Click";
RDebugUtils.currentLine=4456449;
 //BA.debugLineNum = 4456449;BA.debugLine="StartActivity(Action)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._action.getObject()));
RDebugUtils.currentLine=4456450;
 //BA.debugLineNum = 4456450;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=4456451;
 //BA.debugLineNum = 4456451;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=3866624;
 //BA.debugLineNum = 3866624;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=3866625;
 //BA.debugLineNum = 3866625;BA.debugLine="Activity.LoadLayout(\"scifi\") ' Layout contains Sc";
mostCurrent._activity.LoadLayout("scifi",mostCurrent.activityBA);
RDebugUtils.currentLine=3866627;
 //BA.debugLineNum = 3866627;BA.debugLine="p.Initialize(\"\")";
mostCurrent._p.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=3866628;
 //BA.debugLineNum = 3866628;BA.debugLine="p.LoadLayout(\"panelview\")";
mostCurrent._p.LoadLayout("panelview",mostCurrent.activityBA);
RDebugUtils.currentLine=3866635;
 //BA.debugLineNum = 3866635;BA.debugLine="Drama1.Text = \"The Fellowship of the Ring\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Fellowship of the Ring"));
RDebugUtils.currentLine=3866636;
 //BA.debugLineNum = 3866636;BA.debugLine="Starter1.Text = \"Starring: Elijah Wood, Ian McKel";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Elijah Wood, Ian McKellen, Orlando Bloom"));
RDebugUtils.currentLine=3866637;
 //BA.debugLineNum = 3866637;BA.debugLine="Year1.Text = \"(2001)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2001)"));
RDebugUtils.currentLine=3866638;
 //BA.debugLineNum = 3866638;BA.debugLine="OverView1.Text = \"The future of civilization rest";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"));
RDebugUtils.currentLine=3866639;
 //BA.debugLineNum = 3866639;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3866640;
 //BA.debugLineNum = 3866640;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lordRings.jpg").getObject()));
RDebugUtils.currentLine=3866642;
 //BA.debugLineNum = 3866642;BA.debugLine="Drama2.Text = \"Charlie and the Chocolate Factory\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Charlie and the Chocolate Factory"));
RDebugUtils.currentLine=3866643;
 //BA.debugLineNum = 3866643;BA.debugLine="Starter2.Text = \"Starring: Johnny Depp, Freddie H";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"));
RDebugUtils.currentLine=3866644;
 //BA.debugLineNum = 3866644;BA.debugLine="Year2.Text = \"(2005)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3866645;
 //BA.debugLineNum = 3866645;BA.debugLine="OverView2.Text = \"Charlie Bucket, a humble boy fr";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."));
RDebugUtils.currentLine=3866646;
 //BA.debugLineNum = 3866646;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3866647;
 //BA.debugLineNum = 3866647;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"charlieAndTheChocolate.jpg").getObject()));
RDebugUtils.currentLine=3866649;
 //BA.debugLineNum = 3866649;BA.debugLine="Drama3.Text = \"Alice in Wonderland\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("Alice in Wonderland"));
RDebugUtils.currentLine=3866650;
 //BA.debugLineNum = 3866650;BA.debugLine="Starter3.Text = \"Starring: Mia Wasikowska, Johnny";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"));
RDebugUtils.currentLine=3866651;
 //BA.debugLineNum = 3866651;BA.debugLine="Year3.Text = \"(2010)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2010)"));
RDebugUtils.currentLine=3866652;
 //BA.debugLineNum = 3866652;BA.debugLine="OverView3.Text = \"Alice, now a teenager, returns";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."));
RDebugUtils.currentLine=3866653;
 //BA.debugLineNum = 3866653;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3866654;
 //BA.debugLineNum = 3866654;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"aliceAndWonderland.jpg").getObject()));
RDebugUtils.currentLine=3866656;
 //BA.debugLineNum = 3866656;BA.debugLine="Drama4.Text = \"Harry Potter and the Philosopher's";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Harry Potter and the Philosopher's Stone"));
RDebugUtils.currentLine=3866657;
 //BA.debugLineNum = 3866657;BA.debugLine="Starter4.Text = \"Starring: Daniel Radcliffe, Rupe";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Daniel Radcliffe, Rupert Grint, Emma Watson"));
RDebugUtils.currentLine=3866658;
 //BA.debugLineNum = 3866658;BA.debugLine="Year4.Text = \"(2001)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2001)"));
RDebugUtils.currentLine=3866659;
 //BA.debugLineNum = 3866659;BA.debugLine="OverView4.Text = \"Captain Jack Sparrow must rescu";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("Captain Jack Sparrow must rescue Elizabeth Swann, who has been kidnapped by cursed pirates led by Captain Barbossa. Alongside Will Turner, Jack battles ghostly pirates, seeking both freedom and revenge."));
RDebugUtils.currentLine=3866660;
 //BA.debugLineNum = 3866660;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3866661;
 //BA.debugLineNum = 3866661;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"harryPotter.jpg").getObject()));
RDebugUtils.currentLine=3866663;
 //BA.debugLineNum = 3866663;BA.debugLine="Drama5.Text = \"The Curse of the Black Pearl\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("The Curse of the Black Pearl"));
RDebugUtils.currentLine=3866664;
 //BA.debugLineNum = 3866664;BA.debugLine="Starter5.Text = \"Starring: Johnny Depp, Orlando B";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Orlando Bloom, Keira Knightley"));
RDebugUtils.currentLine=3866665;
 //BA.debugLineNum = 3866665;BA.debugLine="Year5.Text = \"(2003)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(2003)"));
RDebugUtils.currentLine=3866666;
 //BA.debugLineNum = 3866666;BA.debugLine="OverView5.Text = \"Four siblings, evacuated from w";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=3866667;
 //BA.debugLineNum = 3866667;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
mostCurrent._dramaimage5.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3866668;
 //BA.debugLineNum = 3866668;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage5.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"piratesCurse.jpg").getObject()));
RDebugUtils.currentLine=3866671;
 //BA.debugLineNum = 3866671;BA.debugLine="Drama6.Text = \"The Chronicles of Narnia\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("The Chronicles of Narnia"));
RDebugUtils.currentLine=3866672;
 //BA.debugLineNum = 3866672;BA.debugLine="Starter6.Text = \"Starring: Georgie Henley, Skanda";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Georgie Henley, Skandar Keynes, William Moseley,"));
RDebugUtils.currentLine=3866673;
 //BA.debugLineNum = 3866673;BA.debugLine="Year6.Text = \"(2003)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2003)"));
RDebugUtils.currentLine=3866674;
 //BA.debugLineNum = 3866674;BA.debugLine="OverView6.Text = \"Four siblings, evacuated from w";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=3866675;
 //BA.debugLineNum = 3866675;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
mostCurrent._dramaimage6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3866676;
 //BA.debugLineNum = 3866676;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage6.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"narnia.jpg").getObject()));
RDebugUtils.currentLine=3866678;
 //BA.debugLineNum = 3866678;BA.debugLine="Drama7.Text = \"Doctor Strange\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("Doctor Strange"));
RDebugUtils.currentLine=3866679;
 //BA.debugLineNum = 3866679;BA.debugLine="Starter7.Text = \"Starring: Benedict Cumberbatch,";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Benedict Cumberbatch, Chiwetel Ejiofor"));
RDebugUtils.currentLine=3866680;
 //BA.debugLineNum = 3866680;BA.debugLine="Year7.Text = \"(2016)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=3866681;
 //BA.debugLineNum = 3866681;BA.debugLine="OverView7.Text = \"After a life-changing accident";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("After a life-changing accident ends his career, arrogant surgeon Stephen Strange seeks healing in Nepal, where he learns the mystic arts and must protect the world from dark magical forces beyond reality."));
RDebugUtils.currentLine=3866682;
 //BA.debugLineNum = 3866682;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3866683;
 //BA.debugLineNum = 3866683;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"doctorStrange.png").getObject()));
RDebugUtils.currentLine=3866685;
 //BA.debugLineNum = 3866685;BA.debugLine="Drama8.Text = \"V for Vendetta\"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("V for Vendetta"));
RDebugUtils.currentLine=3866686;
 //BA.debugLineNum = 3866686;BA.debugLine="Starter8.Text = \"Starring: Hugo Weaving, Natalie";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Hugo Weaving, Natalie Portman, Stephen Rea, John Hurt"));
RDebugUtils.currentLine=3866687;
 //BA.debugLineNum = 3866687;BA.debugLine="Year8.Text = \"(2005)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3866688;
 //BA.debugLineNum = 3866688;BA.debugLine="OverView8.Text = \"In a totalitarian future Britai";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("In a totalitarian future Britain, a masked revolutionary known as \"V\" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."));
RDebugUtils.currentLine=3866689;
 //BA.debugLineNum = 3866689;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
mostCurrent._dramaimage8.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3866690;
 //BA.debugLineNum = 3866690;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage8.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"vendetta.jpg").getObject()));
RDebugUtils.currentLine=3866692;
 //BA.debugLineNum = 3866692;BA.debugLine="Drama9.Text = \"Aladdin\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("Aladdin"));
RDebugUtils.currentLine=3866693;
 //BA.debugLineNum = 3866693;BA.debugLine="Starter9.Text = \"Starring: Mena Massoud, Naomi Sc";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Mena Massoud, Naomi Scott, Will Smith"));
RDebugUtils.currentLine=3866694;
 //BA.debugLineNum = 3866694;BA.debugLine="Year9.Text = \"(2019)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=3866695;
 //BA.debugLineNum = 3866695;BA.debugLine="OverView9.Text = \"Aladdin, a kind-hearted street";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."));
RDebugUtils.currentLine=3866696;
 //BA.debugLineNum = 3866696;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
mostCurrent._dramaimage9.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3866697;
 //BA.debugLineNum = 3866697;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage9.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"aladdin.jpg").getObject()));
RDebugUtils.currentLine=3866699;
 //BA.debugLineNum = 3866699;BA.debugLine="Drama10.Text = \"After Earth\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("After Earth"));
RDebugUtils.currentLine=3866700;
 //BA.debugLineNum = 3866700;BA.debugLine="Starter10.Text = \"Starring: Will Smith, Jaden Smi";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Will Smith, Jaden Smith, Sigourney Weaver"));
RDebugUtils.currentLine=3866701;
 //BA.debugLineNum = 3866701;BA.debugLine="Year10.Text = \"(2013)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=3866702;
 //BA.debugLineNum = 3866702;BA.debugLine="OverView10.Text = \"Set in the future, After Earth";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."));
RDebugUtils.currentLine=3866703;
 //BA.debugLineNum = 3866703;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3866704;
 //BA.debugLineNum = 3866704;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"afterEarch.jpg").getObject()));
RDebugUtils.currentLine=3866706;
 //BA.debugLineNum = 3866706;BA.debugLine="ScrollView1.Panel.AddView(p, 0, 0, 200%x, 210%y)";
mostCurrent._scrollview1.getPanel().AddView((android.view.View)(mostCurrent._p.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=3866707;
 //BA.debugLineNum = 3866707;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=3866708;
 //BA.debugLineNum = 3866708;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="scifi";
RDebugUtils.currentLine=4194304;
 //BA.debugLineNum = 4194304;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=4194306;
 //BA.debugLineNum = 4194306;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=4128768;
 //BA.debugLineNum = 4128768;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=4128770;
 //BA.debugLineNum = 4128770;BA.debugLine="End Sub";
return "";
}
public static String  _dramapage_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "dramapage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "dramapage_click", null));}
RDebugUtils.currentLine=4390912;
 //BA.debugLineNum = 4390912;BA.debugLine="Private Sub DramaPage_Click";
RDebugUtils.currentLine=4390913;
 //BA.debugLineNum = 4390913;BA.debugLine="StartActivity(Drama)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._drama.getObject()));
RDebugUtils.currentLine=4390914;
 //BA.debugLineNum = 4390914;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=4390915;
 //BA.debugLineNum = 4390915;BA.debugLine="End Sub";
return "";
}
public static String  _homepage_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "homepage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "homepage_click", null));}
RDebugUtils.currentLine=4325376;
 //BA.debugLineNum = 4325376;BA.debugLine="Private Sub HomePage_Click";
RDebugUtils.currentLine=4325377;
 //BA.debugLineNum = 4325377;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
RDebugUtils.currentLine=4325378;
 //BA.debugLineNum = 4325378;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=4325379;
 //BA.debugLineNum = 4325379;BA.debugLine="End Sub";
return "";
}
public static void  _panelmovie1_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie1_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie1_click", null); return;}
ResumableSub_PanelMovie1_Click rsub = new ResumableSub_PanelMovie1_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie1_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie1_Click(b4a.example.scifi parent) {
this.parent = parent;
}
b4a.example.scifi parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="scifi";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=4521985;
 //BA.debugLineNum = 4521985;BA.debugLine="Try";
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
RDebugUtils.currentLine=4521986;
 //BA.debugLineNum = 4521986;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=4521987;
 //BA.debugLineNum = 4521987;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "scifi", "panelmovie1_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=4521988;
 //BA.debugLineNum = 4521988;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
if (true) break;

case 4:
//if
this.state = 7;
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
RDebugUtils.currentLine=4521989;
 //BA.debugLineNum = 4521989;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=4521990;
 //BA.debugLineNum = 4521990;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0120737/");
RDebugUtils.currentLine=4521991;
 //BA.debugLineNum = 4521991;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=4521992;
 //BA.debugLineNum = 4521992;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_i.getObject()));
 if (true) break;

case 7:
//C
this.state = 10;
;
 if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
RDebugUtils.currentLine=4521996;
 //BA.debugLineNum = 4521996;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("54521996",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=4521997;
 //BA.debugLineNum = 4521997;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=4522000;
 //BA.debugLineNum = 4522000;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static void  _panelmovie10_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie10_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie10_click", null); return;}
ResumableSub_PanelMovie10_Click rsub = new ResumableSub_PanelMovie10_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie10_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie10_Click(b4a.example.scifi parent) {
this.parent = parent;
}
b4a.example.scifi parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="scifi";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=5111809;
 //BA.debugLineNum = 5111809;BA.debugLine="Try";
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
RDebugUtils.currentLine=5111810;
 //BA.debugLineNum = 5111810;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=5111811;
 //BA.debugLineNum = 5111811;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "scifi", "panelmovie10_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=5111812;
 //BA.debugLineNum = 5111812;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
if (true) break;

case 4:
//if
this.state = 7;
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
RDebugUtils.currentLine=5111813;
 //BA.debugLineNum = 5111813;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=5111814;
 //BA.debugLineNum = 5111814;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt1815862/");
RDebugUtils.currentLine=5111815;
 //BA.debugLineNum = 5111815;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=5111816;
 //BA.debugLineNum = 5111816;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_i.getObject()));
 if (true) break;

case 7:
//C
this.state = 10;
;
 if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
RDebugUtils.currentLine=5111819;
 //BA.debugLineNum = 5111819;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("55111819",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=5111820;
 //BA.debugLineNum = 5111820;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=5111823;
 //BA.debugLineNum = 5111823;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static void  _panelmovie2_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie2_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie2_click", null); return;}
ResumableSub_PanelMovie2_Click rsub = new ResumableSub_PanelMovie2_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie2_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie2_Click(b4a.example.scifi parent) {
this.parent = parent;
}
b4a.example.scifi parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="scifi";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=4587521;
 //BA.debugLineNum = 4587521;BA.debugLine="Try";
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
RDebugUtils.currentLine=4587522;
 //BA.debugLineNum = 4587522;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=4587523;
 //BA.debugLineNum = 4587523;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "scifi", "panelmovie2_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=4587524;
 //BA.debugLineNum = 4587524;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
if (true) break;

case 4:
//if
this.state = 7;
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
RDebugUtils.currentLine=4587525;
 //BA.debugLineNum = 4587525;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=4587526;
 //BA.debugLineNum = 4587526;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0367594/");
RDebugUtils.currentLine=4587527;
 //BA.debugLineNum = 4587527;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=4587528;
 //BA.debugLineNum = 4587528;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_i.getObject()));
 if (true) break;

case 7:
//C
this.state = 10;
;
 if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
RDebugUtils.currentLine=4587531;
 //BA.debugLineNum = 4587531;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("54587531",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=4587532;
 //BA.debugLineNum = 4587532;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=4587535;
 //BA.debugLineNum = 4587535;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static void  _panelmovie3_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie3_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie3_click", null); return;}
ResumableSub_PanelMovie3_Click rsub = new ResumableSub_PanelMovie3_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie3_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie3_Click(b4a.example.scifi parent) {
this.parent = parent;
}
b4a.example.scifi parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="scifi";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=4653057;
 //BA.debugLineNum = 4653057;BA.debugLine="Try";
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
RDebugUtils.currentLine=4653058;
 //BA.debugLineNum = 4653058;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=4653059;
 //BA.debugLineNum = 4653059;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "scifi", "panelmovie3_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=4653060;
 //BA.debugLineNum = 4653060;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
if (true) break;

case 4:
//if
this.state = 7;
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
RDebugUtils.currentLine=4653061;
 //BA.debugLineNum = 4653061;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=4653062;
 //BA.debugLineNum = 4653062;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt1014759/");
RDebugUtils.currentLine=4653063;
 //BA.debugLineNum = 4653063;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=4653064;
 //BA.debugLineNum = 4653064;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_i.getObject()));
 if (true) break;

case 7:
//C
this.state = 10;
;
 if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
RDebugUtils.currentLine=4653067;
 //BA.debugLineNum = 4653067;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("54653067",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=4653068;
 //BA.debugLineNum = 4653068;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=4653071;
 //BA.debugLineNum = 4653071;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static void  _panelmovie4_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie4_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie4_click", null); return;}
ResumableSub_PanelMovie4_Click rsub = new ResumableSub_PanelMovie4_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie4_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie4_Click(b4a.example.scifi parent) {
this.parent = parent;
}
b4a.example.scifi parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="scifi";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=4718593;
 //BA.debugLineNum = 4718593;BA.debugLine="Try";
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
RDebugUtils.currentLine=4718594;
 //BA.debugLineNum = 4718594;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=4718595;
 //BA.debugLineNum = 4718595;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "scifi", "panelmovie4_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=4718596;
 //BA.debugLineNum = 4718596;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
if (true) break;

case 4:
//if
this.state = 7;
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
RDebugUtils.currentLine=4718597;
 //BA.debugLineNum = 4718597;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=4718598;
 //BA.debugLineNum = 4718598;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0241527/");
RDebugUtils.currentLine=4718599;
 //BA.debugLineNum = 4718599;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=4718600;
 //BA.debugLineNum = 4718600;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_i.getObject()));
 if (true) break;

case 7:
//C
this.state = 10;
;
 if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
RDebugUtils.currentLine=4718603;
 //BA.debugLineNum = 4718603;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("54718603",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=4718604;
 //BA.debugLineNum = 4718604;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=4718607;
 //BA.debugLineNum = 4718607;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static void  _panelmovie5_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie5_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie5_click", null); return;}
ResumableSub_PanelMovie5_Click rsub = new ResumableSub_PanelMovie5_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie5_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie5_Click(b4a.example.scifi parent) {
this.parent = parent;
}
b4a.example.scifi parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="scifi";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=4784129;
 //BA.debugLineNum = 4784129;BA.debugLine="Try";
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
RDebugUtils.currentLine=4784130;
 //BA.debugLineNum = 4784130;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=4784131;
 //BA.debugLineNum = 4784131;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "scifi", "panelmovie5_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=4784132;
 //BA.debugLineNum = 4784132;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
if (true) break;

case 4:
//if
this.state = 7;
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
RDebugUtils.currentLine=4784133;
 //BA.debugLineNum = 4784133;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=4784134;
 //BA.debugLineNum = 4784134;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0325980/");
RDebugUtils.currentLine=4784135;
 //BA.debugLineNum = 4784135;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=4784136;
 //BA.debugLineNum = 4784136;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_i.getObject()));
 if (true) break;

case 7:
//C
this.state = 10;
;
 if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
RDebugUtils.currentLine=4784139;
 //BA.debugLineNum = 4784139;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("54784139",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=4784140;
 //BA.debugLineNum = 4784140;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=4784143;
 //BA.debugLineNum = 4784143;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static void  _panelmovie6_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie6_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie6_click", null); return;}
ResumableSub_PanelMovie6_Click rsub = new ResumableSub_PanelMovie6_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie6_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie6_Click(b4a.example.scifi parent) {
this.parent = parent;
}
b4a.example.scifi parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="scifi";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=4849665;
 //BA.debugLineNum = 4849665;BA.debugLine="Try";
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
RDebugUtils.currentLine=4849666;
 //BA.debugLineNum = 4849666;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=4849667;
 //BA.debugLineNum = 4849667;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "scifi", "panelmovie6_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=4849668;
 //BA.debugLineNum = 4849668;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
if (true) break;

case 4:
//if
this.state = 7;
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
RDebugUtils.currentLine=4849669;
 //BA.debugLineNum = 4849669;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=4849670;
 //BA.debugLineNum = 4849670;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0363771/");
RDebugUtils.currentLine=4849671;
 //BA.debugLineNum = 4849671;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=4849672;
 //BA.debugLineNum = 4849672;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_i.getObject()));
 if (true) break;

case 7:
//C
this.state = 10;
;
 if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
RDebugUtils.currentLine=4849675;
 //BA.debugLineNum = 4849675;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("54849675",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=4849676;
 //BA.debugLineNum = 4849676;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=4849679;
 //BA.debugLineNum = 4849679;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static void  _panelmovie7_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie7_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie7_click", null); return;}
ResumableSub_PanelMovie7_Click rsub = new ResumableSub_PanelMovie7_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie7_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie7_Click(b4a.example.scifi parent) {
this.parent = parent;
}
b4a.example.scifi parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="scifi";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=4915201;
 //BA.debugLineNum = 4915201;BA.debugLine="Try";
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
RDebugUtils.currentLine=4915202;
 //BA.debugLineNum = 4915202;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=4915203;
 //BA.debugLineNum = 4915203;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "scifi", "panelmovie7_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=4915204;
 //BA.debugLineNum = 4915204;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
if (true) break;

case 4:
//if
this.state = 7;
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
RDebugUtils.currentLine=4915205;
 //BA.debugLineNum = 4915205;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=4915206;
 //BA.debugLineNum = 4915206;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt1211837/");
RDebugUtils.currentLine=4915207;
 //BA.debugLineNum = 4915207;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=4915208;
 //BA.debugLineNum = 4915208;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_i.getObject()));
 if (true) break;

case 7:
//C
this.state = 10;
;
 if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
RDebugUtils.currentLine=4915211;
 //BA.debugLineNum = 4915211;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("54915211",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=4915212;
 //BA.debugLineNum = 4915212;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=4915215;
 //BA.debugLineNum = 4915215;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static void  _panelmovie8_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie8_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie8_click", null); return;}
ResumableSub_PanelMovie8_Click rsub = new ResumableSub_PanelMovie8_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie8_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie8_Click(b4a.example.scifi parent) {
this.parent = parent;
}
b4a.example.scifi parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="scifi";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=4980737;
 //BA.debugLineNum = 4980737;BA.debugLine="Try";
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
RDebugUtils.currentLine=4980738;
 //BA.debugLineNum = 4980738;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=4980739;
 //BA.debugLineNum = 4980739;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "scifi", "panelmovie8_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=4980740;
 //BA.debugLineNum = 4980740;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
if (true) break;

case 4:
//if
this.state = 7;
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
RDebugUtils.currentLine=4980741;
 //BA.debugLineNum = 4980741;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=4980742;
 //BA.debugLineNum = 4980742;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0434409/");
RDebugUtils.currentLine=4980743;
 //BA.debugLineNum = 4980743;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=4980744;
 //BA.debugLineNum = 4980744;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_i.getObject()));
 if (true) break;

case 7:
//C
this.state = 10;
;
 if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
RDebugUtils.currentLine=4980747;
 //BA.debugLineNum = 4980747;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("54980747",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=4980748;
 //BA.debugLineNum = 4980748;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=4980751;
 //BA.debugLineNum = 4980751;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static void  _panelmovie9_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie9_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie9_click", null); return;}
ResumableSub_PanelMovie9_Click rsub = new ResumableSub_PanelMovie9_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie9_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie9_Click(b4a.example.scifi parent) {
this.parent = parent;
}
b4a.example.scifi parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="scifi";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=5046273;
 //BA.debugLineNum = 5046273;BA.debugLine="Try";
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
RDebugUtils.currentLine=5046274;
 //BA.debugLineNum = 5046274;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=5046275;
 //BA.debugLineNum = 5046275;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "scifi", "panelmovie9_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=5046276;
 //BA.debugLineNum = 5046276;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
if (true) break;

case 4:
//if
this.state = 7;
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
RDebugUtils.currentLine=5046277;
 //BA.debugLineNum = 5046277;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=5046278;
 //BA.debugLineNum = 5046278;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt6139732/");
RDebugUtils.currentLine=5046279;
 //BA.debugLineNum = 5046279;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=5046280;
 //BA.debugLineNum = 5046280;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_i.getObject()));
 if (true) break;

case 7:
//C
this.state = 10;
;
 if (true) break;

case 9:
//C
this.state = 10;
this.catchState = 0;
RDebugUtils.currentLine=5046283;
 //BA.debugLineNum = 5046283;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("55046283",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=5046284;
 //BA.debugLineNum = 5046284;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=5046287;
 //BA.debugLineNum = 5046287;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static String  _scifipage_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "scifipage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "scifipage_click", null));}
RDebugUtils.currentLine=4259840;
 //BA.debugLineNum = 4259840;BA.debugLine="Private Sub SciFiPage_Click";
RDebugUtils.currentLine=4259842;
 //BA.debugLineNum = 4259842;BA.debugLine="End Sub";
return "";
}
public static String  _searchbtn_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchbtn_click", null));}
RDebugUtils.currentLine=3932160;
 //BA.debugLineNum = 3932160;BA.debugLine="Private Sub SearchBtn_Click";
RDebugUtils.currentLine=3932161;
 //BA.debugLineNum = 3932161;BA.debugLine="SearchNow";
_searchnow();
RDebugUtils.currentLine=3932162;
 //BA.debugLineNum = 3932162;BA.debugLine="End Sub";
return "";
}
public static String  _searchnow() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchnow", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchnow", null));}
String _query = "";
String _userinput = "";
RDebugUtils.currentLine=3997696;
 //BA.debugLineNum = 3997696;BA.debugLine="Sub SearchNow";
RDebugUtils.currentLine=3997697;
 //BA.debugLineNum = 3997697;BA.debugLine="Dim query As String = SearchEngine.Text.ToLowerCa";
_query = mostCurrent._searchengine.getText().toLowerCase().trim();
RDebugUtils.currentLine=3997700;
 //BA.debugLineNum = 3997700;BA.debugLine="Dim UserInput As String = SearchEngine.Text";
_userinput = mostCurrent._searchengine.getText();
RDebugUtils.currentLine=3997703;
 //BA.debugLineNum = 3997703;BA.debugLine="If query.Contains(\"the fellowship of the ring\") O";
if (_query.contains("the fellowship of the ring") || _query.contains("fellowship") || _query.contains("the ring") || _query.contains("ring")) { 
RDebugUtils.currentLine=3997705;
 //BA.debugLineNum = 3997705;BA.debugLine="Drama1.Text = \"The Fellowship of the Ring\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Fellowship of the Ring"));
RDebugUtils.currentLine=3997706;
 //BA.debugLineNum = 3997706;BA.debugLine="Starter1.Text = \"Starring: Elijah Wood, Ian McKe";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Elijah Wood, Ian McKellen, Orlando Bloom"));
RDebugUtils.currentLine=3997707;
 //BA.debugLineNum = 3997707;BA.debugLine="Year1.Text = \"(2001)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2001)"));
RDebugUtils.currentLine=3997708;
 //BA.debugLineNum = 3997708;BA.debugLine="OverView1.Text = \"The future of civilization res";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"));
RDebugUtils.currentLine=3997709;
 //BA.debugLineNum = 3997709;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997710;
 //BA.debugLineNum = 3997710;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lordRings.jpg").getObject()));
RDebugUtils.currentLine=3997712;
 //BA.debugLineNum = 3997712;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997713;
 //BA.debugLineNum = 3997713;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997714;
 //BA.debugLineNum = 3997714;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997715;
 //BA.debugLineNum = 3997715;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997716;
 //BA.debugLineNum = 3997716;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997718;
 //BA.debugLineNum = 3997718;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997720;
 //BA.debugLineNum = 3997720;BA.debugLine="Else If query.Contains(\"charlie and the chocolate";
if (_query.contains("charlie and the chocolate factory") || _query.contains("charlie") || _query.contains("factory")) { 
RDebugUtils.currentLine=3997722;
 //BA.debugLineNum = 3997722;BA.debugLine="Drama1.Text = \"Charlie and the Chocolate Factory";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Charlie and the Chocolate Factory"));
RDebugUtils.currentLine=3997723;
 //BA.debugLineNum = 3997723;BA.debugLine="Starter1.Text = \"Starring: Johnny Depp, Freddie";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"));
RDebugUtils.currentLine=3997724;
 //BA.debugLineNum = 3997724;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3997725;
 //BA.debugLineNum = 3997725;BA.debugLine="OverView1.Text = \"Charlie Bucket, a humble boy f";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."));
RDebugUtils.currentLine=3997726;
 //BA.debugLineNum = 3997726;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997727;
 //BA.debugLineNum = 3997727;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"charlieAndTheChocolate.jpg").getObject()));
RDebugUtils.currentLine=3997729;
 //BA.debugLineNum = 3997729;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997730;
 //BA.debugLineNum = 3997730;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997731;
 //BA.debugLineNum = 3997731;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997732;
 //BA.debugLineNum = 3997732;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997733;
 //BA.debugLineNum = 3997733;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997735;
 //BA.debugLineNum = 3997735;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997737;
 //BA.debugLineNum = 3997737;BA.debugLine="Else If query.Contains(\"alice in wonderland\") Or";
if (_query.contains("alice in wonderland") || _query.contains("alice") || _query.contains("wonderland")) { 
RDebugUtils.currentLine=3997739;
 //BA.debugLineNum = 3997739;BA.debugLine="Drama1.Text = \"Alice in Wonderland\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Alice in Wonderland"));
RDebugUtils.currentLine=3997740;
 //BA.debugLineNum = 3997740;BA.debugLine="Starter1.Text = \"Starring: Mia Wasikowska, Johnn";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"));
RDebugUtils.currentLine=3997741;
 //BA.debugLineNum = 3997741;BA.debugLine="Year1.Text = \"(2010)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2010)"));
RDebugUtils.currentLine=3997742;
 //BA.debugLineNum = 3997742;BA.debugLine="OverView1.Text = \"Alice, now a teenager, returns";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."));
RDebugUtils.currentLine=3997743;
 //BA.debugLineNum = 3997743;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997744;
 //BA.debugLineNum = 3997744;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"aliceAndWonderland.jpg").getObject()));
RDebugUtils.currentLine=3997746;
 //BA.debugLineNum = 3997746;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997747;
 //BA.debugLineNum = 3997747;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997748;
 //BA.debugLineNum = 3997748;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997749;
 //BA.debugLineNum = 3997749;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997750;
 //BA.debugLineNum = 3997750;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997752;
 //BA.debugLineNum = 3997752;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997754;
 //BA.debugLineNum = 3997754;BA.debugLine="Else If query.Contains(\"harry potter and the phil";
if (_query.contains("harry potter and the philosophers stone") || _query.contains("harry") || _query.contains("harry potter")) { 
RDebugUtils.currentLine=3997756;
 //BA.debugLineNum = 3997756;BA.debugLine="Drama1.Text = \"Harry Potter and the Philosopher'";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Harry Potter and the Philosopher's Stone"));
RDebugUtils.currentLine=3997757;
 //BA.debugLineNum = 3997757;BA.debugLine="Starter1.Text = \"Starring: Daniel Radcliffe, Rup";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Daniel Radcliffe, Rupert Grint, Emma Watson"));
RDebugUtils.currentLine=3997758;
 //BA.debugLineNum = 3997758;BA.debugLine="Year1.Text = \"(2001)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2001)"));
RDebugUtils.currentLine=3997759;
 //BA.debugLineNum = 3997759;BA.debugLine="OverView1.Text = \"Captain Jack Sparrow must resc";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Captain Jack Sparrow must rescue Elizabeth Swann, who has been kidnapped by cursed pirates led by Captain Barbossa. Alongside Will Turner, Jack battles ghostly pirates, seeking both freedom and revenge."));
RDebugUtils.currentLine=3997760;
 //BA.debugLineNum = 3997760;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997761;
 //BA.debugLineNum = 3997761;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"harryPotter.jpg").getObject()));
RDebugUtils.currentLine=3997764;
 //BA.debugLineNum = 3997764;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997765;
 //BA.debugLineNum = 3997765;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997766;
 //BA.debugLineNum = 3997766;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997767;
 //BA.debugLineNum = 3997767;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997769;
 //BA.debugLineNum = 3997769;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997771;
 //BA.debugLineNum = 3997771;BA.debugLine="Else If query.Contains(\"the curse of the black pe";
if (_query.contains("the curse of the black pearl") || _query.contains("curse") || _query.contains("black pearl")) { 
RDebugUtils.currentLine=3997773;
 //BA.debugLineNum = 3997773;BA.debugLine="Drama1.Text = \"The Curse of the Black Pearl\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Curse of the Black Pearl"));
RDebugUtils.currentLine=3997774;
 //BA.debugLineNum = 3997774;BA.debugLine="Starter1.Text = \"Starring: Johnny Depp, Orlando";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Orlando Bloom, Keira Knightley"));
RDebugUtils.currentLine=3997775;
 //BA.debugLineNum = 3997775;BA.debugLine="Year1.Text = \"(2003)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2003)"));
RDebugUtils.currentLine=3997776;
 //BA.debugLineNum = 3997776;BA.debugLine="OverView1.Text = \"Four siblings, evacuated from";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=3997777;
 //BA.debugLineNum = 3997777;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997778;
 //BA.debugLineNum = 3997778;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"piratesCurse.jpg").getObject()));
RDebugUtils.currentLine=3997781;
 //BA.debugLineNum = 3997781;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997782;
 //BA.debugLineNum = 3997782;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997783;
 //BA.debugLineNum = 3997783;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997784;
 //BA.debugLineNum = 3997784;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997785;
 //BA.debugLineNum = 3997785;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997787;
 //BA.debugLineNum = 3997787;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997789;
 //BA.debugLineNum = 3997789;BA.debugLine="Else If query.Contains(\"the chronicles of narnia\"";
if (_query.contains("the chronicles of narnia") || _query.contains("chronicles") || _query.contains("narnia")) { 
RDebugUtils.currentLine=3997791;
 //BA.debugLineNum = 3997791;BA.debugLine="Drama1.Text = \"The Chronicles of Narnia\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Chronicles of Narnia"));
RDebugUtils.currentLine=3997792;
 //BA.debugLineNum = 3997792;BA.debugLine="Starter1.Text = \"Starring: Georgie Henley, Skand";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Georgie Henley, Skandar Keynes, William Moseley,"));
RDebugUtils.currentLine=3997793;
 //BA.debugLineNum = 3997793;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3997794;
 //BA.debugLineNum = 3997794;BA.debugLine="OverView1.Text = \"Four siblings, evacuated from";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=3997795;
 //BA.debugLineNum = 3997795;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997796;
 //BA.debugLineNum = 3997796;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"narnia.jpg").getObject()));
RDebugUtils.currentLine=3997798;
 //BA.debugLineNum = 3997798;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997799;
 //BA.debugLineNum = 3997799;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997800;
 //BA.debugLineNum = 3997800;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997801;
 //BA.debugLineNum = 3997801;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997802;
 //BA.debugLineNum = 3997802;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997804;
 //BA.debugLineNum = 3997804;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997806;
 //BA.debugLineNum = 3997806;BA.debugLine="Else If query.Contains(\"doctor strange\") Or query";
if (_query.contains("doctor strange") || _query.contains("doctor") || _query.contains("strange")) { 
RDebugUtils.currentLine=3997808;
 //BA.debugLineNum = 3997808;BA.debugLine="Drama1.Text = \"Doctor Strange\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Doctor Strange"));
RDebugUtils.currentLine=3997809;
 //BA.debugLineNum = 3997809;BA.debugLine="Starter1.Text = \"Starring: Benedict Cumberbatch,";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Benedict Cumberbatch, Chiwetel Ejiofor"));
RDebugUtils.currentLine=3997810;
 //BA.debugLineNum = 3997810;BA.debugLine="Year1.Text = \"(2016)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=3997811;
 //BA.debugLineNum = 3997811;BA.debugLine="OverView1.Text = \"After a life-changing accident";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After a life-changing accident ends his career, arrogant surgeon Stephen Strange seeks healing in Nepal, where he learns the mystic arts and must protect the world from dark magical forces beyond reality."));
RDebugUtils.currentLine=3997812;
 //BA.debugLineNum = 3997812;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997813;
 //BA.debugLineNum = 3997813;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"doctorStrange.png").getObject()));
RDebugUtils.currentLine=3997815;
 //BA.debugLineNum = 3997815;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997816;
 //BA.debugLineNum = 3997816;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997817;
 //BA.debugLineNum = 3997817;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997818;
 //BA.debugLineNum = 3997818;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997819;
 //BA.debugLineNum = 3997819;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997821;
 //BA.debugLineNum = 3997821;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997823;
 //BA.debugLineNum = 3997823;BA.debugLine="Else If query.Contains(\"v for vendetta\") Or query";
if (_query.contains("v for vendetta") || _query.contains("vendetta") || _query.contains("v")) { 
RDebugUtils.currentLine=3997825;
 //BA.debugLineNum = 3997825;BA.debugLine="Drama1.Text = \"V for Vendetta\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("V for Vendetta"));
RDebugUtils.currentLine=3997826;
 //BA.debugLineNum = 3997826;BA.debugLine="Starter1.Text = \"Starring: Hugo Weaving, Natalie";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugo Weaving, Natalie Portman, Stephen Rea, John Hurt"));
RDebugUtils.currentLine=3997827;
 //BA.debugLineNum = 3997827;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3997828;
 //BA.debugLineNum = 3997828;BA.debugLine="OverView1.Text = \"In a totalitarian future Brita";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a totalitarian future Britain, a masked revolutionary known as \"V\" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."));
RDebugUtils.currentLine=3997829;
 //BA.debugLineNum = 3997829;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997830;
 //BA.debugLineNum = 3997830;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"vendetta.jpg").getObject()));
RDebugUtils.currentLine=3997832;
 //BA.debugLineNum = 3997832;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997833;
 //BA.debugLineNum = 3997833;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997834;
 //BA.debugLineNum = 3997834;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997835;
 //BA.debugLineNum = 3997835;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997836;
 //BA.debugLineNum = 3997836;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997838;
 //BA.debugLineNum = 3997838;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997840;
 //BA.debugLineNum = 3997840;BA.debugLine="Else If query.Contains(\"aladdin\") Then";
if (_query.contains("aladdin")) { 
RDebugUtils.currentLine=3997842;
 //BA.debugLineNum = 3997842;BA.debugLine="Drama1.Text = \"Aladdin\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Aladdin"));
RDebugUtils.currentLine=3997843;
 //BA.debugLineNum = 3997843;BA.debugLine="Starter1.Text = \"Starring: Mena Massoud, Naomi S";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Mena Massoud, Naomi Scott, Will Smith"));
RDebugUtils.currentLine=3997844;
 //BA.debugLineNum = 3997844;BA.debugLine="Year1.Text = \"(2019)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=3997845;
 //BA.debugLineNum = 3997845;BA.debugLine="OverView1.Text = \"Aladdin, a kind-hearted street";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."));
RDebugUtils.currentLine=3997846;
 //BA.debugLineNum = 3997846;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997847;
 //BA.debugLineNum = 3997847;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"afterEarch.jpg").getObject()));
RDebugUtils.currentLine=3997849;
 //BA.debugLineNum = 3997849;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997850;
 //BA.debugLineNum = 3997850;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997851;
 //BA.debugLineNum = 3997851;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997852;
 //BA.debugLineNum = 3997852;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997853;
 //BA.debugLineNum = 3997853;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997855;
 //BA.debugLineNum = 3997855;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997857;
 //BA.debugLineNum = 3997857;BA.debugLine="Else If query.Contains(\"after earth\") Or query.Co";
if (_query.contains("after earth") || _query.contains("after") || _query.contains("earth")) { 
RDebugUtils.currentLine=3997859;
 //BA.debugLineNum = 3997859;BA.debugLine="Drama1.Text = \"After Earth\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("After Earth"));
RDebugUtils.currentLine=3997860;
 //BA.debugLineNum = 3997860;BA.debugLine="Starter1.Text = \"Starring: Will Smith, Jaden Smi";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Will Smith, Jaden Smith, Sigourney Weaver"));
RDebugUtils.currentLine=3997861;
 //BA.debugLineNum = 3997861;BA.debugLine="Year1.Text = \"(2013)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=3997862;
 //BA.debugLineNum = 3997862;BA.debugLine="OverView1.Text = \"Set in the future, After Earth";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."));
RDebugUtils.currentLine=3997863;
 //BA.debugLineNum = 3997863;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997864;
 //BA.debugLineNum = 3997864;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=3997866;
 //BA.debugLineNum = 3997866;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997867;
 //BA.debugLineNum = 3997867;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997868;
 //BA.debugLineNum = 3997868;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997869;
 //BA.debugLineNum = 3997869;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997870;
 //BA.debugLineNum = 3997870;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997872;
 //BA.debugLineNum = 3997872;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997874;
 //BA.debugLineNum = 3997874;BA.debugLine="Else If query.Contains(\"orlando bloom\") Or query.";
if (_query.contains("orlando bloom") || _query.contains("orlando") || _query.contains("bloom")) { 
RDebugUtils.currentLine=3997875;
 //BA.debugLineNum = 3997875;BA.debugLine="Drama1.Text = \"The Fellowship of the Ring\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Fellowship of the Ring"));
RDebugUtils.currentLine=3997876;
 //BA.debugLineNum = 3997876;BA.debugLine="Starter1.Text = \"Starring: Elijah Wood, Ian McKe";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Elijah Wood, Ian McKellen, Orlando Bloom"));
RDebugUtils.currentLine=3997877;
 //BA.debugLineNum = 3997877;BA.debugLine="Year1.Text = \"(2001)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2001)"));
RDebugUtils.currentLine=3997878;
 //BA.debugLineNum = 3997878;BA.debugLine="OverView1.Text = \"The future of civilization res";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"));
RDebugUtils.currentLine=3997879;
 //BA.debugLineNum = 3997879;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997880;
 //BA.debugLineNum = 3997880;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lordRings.jpg").getObject()));
RDebugUtils.currentLine=3997882;
 //BA.debugLineNum = 3997882;BA.debugLine="Drama2.Text = \"The Curse of the Black Pearl\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("The Curse of the Black Pearl"));
RDebugUtils.currentLine=3997883;
 //BA.debugLineNum = 3997883;BA.debugLine="Starter2.Text = \"Starring: Johnny Depp, Orlando";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Orlando Bloom, Keira Knightley, Geoffrey Rush"));
RDebugUtils.currentLine=3997884;
 //BA.debugLineNum = 3997884;BA.debugLine="Year2.Text = \"(2003)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2003)"));
RDebugUtils.currentLine=3997885;
 //BA.debugLineNum = 3997885;BA.debugLine="OverView2.Text = \"Four siblings, evacuated from";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=3997886;
 //BA.debugLineNum = 3997886;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997887;
 //BA.debugLineNum = 3997887;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"piratesCurse.jpg").getObject()));
RDebugUtils.currentLine=3997889;
 //BA.debugLineNum = 3997889;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997890;
 //BA.debugLineNum = 3997890;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997891;
 //BA.debugLineNum = 3997891;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997893;
 //BA.debugLineNum = 3997893;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997895;
 //BA.debugLineNum = 3997895;BA.debugLine="Else If query.Contains(\"johnny depp\") Or query.Co";
if (_query.contains("johnny depp") || _query.contains("johnny") || _query.contains("depp")) { 
RDebugUtils.currentLine=3997896;
 //BA.debugLineNum = 3997896;BA.debugLine="Drama1.Text = \"Alice in Wonderland\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Alice in Wonderland"));
RDebugUtils.currentLine=3997897;
 //BA.debugLineNum = 3997897;BA.debugLine="Starter1.Text = \"Starring: Mia Wasikowska, Johnn";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"));
RDebugUtils.currentLine=3997898;
 //BA.debugLineNum = 3997898;BA.debugLine="Year1.Text = \"(2010)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2010)"));
RDebugUtils.currentLine=3997899;
 //BA.debugLineNum = 3997899;BA.debugLine="OverView1.Text = \"Alice, now a teenager, returns";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."));
RDebugUtils.currentLine=3997900;
 //BA.debugLineNum = 3997900;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997901;
 //BA.debugLineNum = 3997901;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"aliceAndWonderland.jpg").getObject()));
RDebugUtils.currentLine=3997903;
 //BA.debugLineNum = 3997903;BA.debugLine="Drama2.Text = \"Charlie and the Chocolate Factory";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Charlie and the Chocolate Factory"));
RDebugUtils.currentLine=3997904;
 //BA.debugLineNum = 3997904;BA.debugLine="Starter2.Text = \"Starring: Johnny Depp, Freddie";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"));
RDebugUtils.currentLine=3997905;
 //BA.debugLineNum = 3997905;BA.debugLine="Year2.Text = \"(2005)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3997906;
 //BA.debugLineNum = 3997906;BA.debugLine="OverView2.Text = \"Charlie Bucket, a humble boy f";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."));
RDebugUtils.currentLine=3997907;
 //BA.debugLineNum = 3997907;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997908;
 //BA.debugLineNum = 3997908;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"charlieAndTheChocolate.jpg").getObject()));
RDebugUtils.currentLine=3997910;
 //BA.debugLineNum = 3997910;BA.debugLine="Drama3.Text = \"The Curse of the Black Pearl\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Curse of the Black Pearl"));
RDebugUtils.currentLine=3997911;
 //BA.debugLineNum = 3997911;BA.debugLine="Starter3.Text = \"Starring: Johnny Depp, Orlando";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Orlando Bloom, Keira Knightley, Geoffrey Rush"));
RDebugUtils.currentLine=3997912;
 //BA.debugLineNum = 3997912;BA.debugLine="Year3.Text = \"(2003)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2003)"));
RDebugUtils.currentLine=3997913;
 //BA.debugLineNum = 3997913;BA.debugLine="OverView3.Text = \"Four siblings, evacuated from";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=3997914;
 //BA.debugLineNum = 3997914;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997915;
 //BA.debugLineNum = 3997915;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"PiratesCurse.jpg").getObject()));
RDebugUtils.currentLine=3997917;
 //BA.debugLineNum = 3997917;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997918;
 //BA.debugLineNum = 3997918;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997919;
 //BA.debugLineNum = 3997919;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997921;
 //BA.debugLineNum = 3997921;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997923;
 //BA.debugLineNum = 3997923;BA.debugLine="Else If query.Contains(\"helena bonham carter\") Or";
if (_query.contains("helena bonham carter") || _query.contains("helena") || _query.contains("bonham") || _query.contains("carter") || _query.contains("helena bonham")) { 
RDebugUtils.currentLine=3997924;
 //BA.debugLineNum = 3997924;BA.debugLine="Drama1.Text = \"Charlie and the Chocolate Factory";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Charlie and the Chocolate Factory"));
RDebugUtils.currentLine=3997925;
 //BA.debugLineNum = 3997925;BA.debugLine="Starter1.Text = \"Starring: Johnny Depp, Freddie";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"));
RDebugUtils.currentLine=3997926;
 //BA.debugLineNum = 3997926;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3997927;
 //BA.debugLineNum = 3997927;BA.debugLine="OverView1.Text = \"Charlie Bucket, a humble boy f";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."));
RDebugUtils.currentLine=3997928;
 //BA.debugLineNum = 3997928;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997929;
 //BA.debugLineNum = 3997929;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"charlieAndTheChocolate.jpg").getObject()));
RDebugUtils.currentLine=3997931;
 //BA.debugLineNum = 3997931;BA.debugLine="Drama2.Text = \"Alice in Wonderland\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Alice in Wonderland"));
RDebugUtils.currentLine=3997932;
 //BA.debugLineNum = 3997932;BA.debugLine="Starter2.Text = \"Starring: Mia Wasikowska, Johnn";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"));
RDebugUtils.currentLine=3997933;
 //BA.debugLineNum = 3997933;BA.debugLine="Year2.Text = \"(2010)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2010)"));
RDebugUtils.currentLine=3997934;
 //BA.debugLineNum = 3997934;BA.debugLine="OverView2.Text = \"Alice, now a teenager, returns";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."));
RDebugUtils.currentLine=3997935;
 //BA.debugLineNum = 3997935;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997936;
 //BA.debugLineNum = 3997936;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"aliceAndWonderland.jpg").getObject()));
RDebugUtils.currentLine=3997938;
 //BA.debugLineNum = 3997938;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997939;
 //BA.debugLineNum = 3997939;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997940;
 //BA.debugLineNum = 3997940;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997941;
 //BA.debugLineNum = 3997941;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997943;
 //BA.debugLineNum = 3997943;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997945;
 //BA.debugLineNum = 3997945;BA.debugLine="Else If query.Contains(\"will smith\") Or query.Con";
if (_query.contains("will smith") || _query.contains("will") || _query.contains("smith")) { 
RDebugUtils.currentLine=3997946;
 //BA.debugLineNum = 3997946;BA.debugLine="Drama1.Text = \"Aladdin\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Aladdin"));
RDebugUtils.currentLine=3997947;
 //BA.debugLineNum = 3997947;BA.debugLine="Starter1.Text = \"Starring: Mena Massoud, Naomi S";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Mena Massoud, Naomi Scott, Will Smith"));
RDebugUtils.currentLine=3997948;
 //BA.debugLineNum = 3997948;BA.debugLine="Year1.Text = \"(2019)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=3997949;
 //BA.debugLineNum = 3997949;BA.debugLine="OverView1.Text = \"Aladdin, a kind-hearted street";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."));
RDebugUtils.currentLine=3997950;
 //BA.debugLineNum = 3997950;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997951;
 //BA.debugLineNum = 3997951;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"aladdin.jpg").getObject()));
RDebugUtils.currentLine=3997953;
 //BA.debugLineNum = 3997953;BA.debugLine="Drama1.Text = \"After Earth\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("After Earth"));
RDebugUtils.currentLine=3997954;
 //BA.debugLineNum = 3997954;BA.debugLine="Starter1.Text = \"Starring: Will Smith, Jaden Smi";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Will Smith, Jaden Smith, Sigourney Weaver"));
RDebugUtils.currentLine=3997955;
 //BA.debugLineNum = 3997955;BA.debugLine="Year1.Text = \"(2013)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=3997956;
 //BA.debugLineNum = 3997956;BA.debugLine="OverView1.Text = \"Set in the future, After Earth";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."));
RDebugUtils.currentLine=3997957;
 //BA.debugLineNum = 3997957;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997958;
 //BA.debugLineNum = 3997958;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"afterEarch.jpg").getObject()));
RDebugUtils.currentLine=3997960;
 //BA.debugLineNum = 3997960;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997961;
 //BA.debugLineNum = 3997961;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997963;
 //BA.debugLineNum = 3997963;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997965;
 //BA.debugLineNum = 3997965;BA.debugLine="Else If query.Contains(\"william moseley\") Or quer";
if (_query.contains("william moseley") || _query.contains("william") || _query.contains("moseley")) { 
RDebugUtils.currentLine=3997967;
 //BA.debugLineNum = 3997967;BA.debugLine="Drama1.Text = \"The Chronicles of Narnia\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Chronicles of Narnia"));
RDebugUtils.currentLine=3997968;
 //BA.debugLineNum = 3997968;BA.debugLine="Starter1.Text = \"Starring: Georgie Henley, Skand";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Georgie Henley, Skandar Keynes, William Moseley"));
RDebugUtils.currentLine=3997969;
 //BA.debugLineNum = 3997969;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3997970;
 //BA.debugLineNum = 3997970;BA.debugLine="OverView1.Text = \"Four siblings, evacuated from";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=3997971;
 //BA.debugLineNum = 3997971;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997972;
 //BA.debugLineNum = 3997972;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"narnia.jpg").getObject()));
RDebugUtils.currentLine=3997974;
 //BA.debugLineNum = 3997974;BA.debugLine="Drama2.Text = \"The Little Mermaid\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("The Little Mermaid"));
RDebugUtils.currentLine=3997975;
 //BA.debugLineNum = 3997975;BA.debugLine="Starter2.Text = \"Starring: Poppy Drayton, Willia";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Poppy Drayton, William Moseley, Shirley MacLaine"));
RDebugUtils.currentLine=3997976;
 //BA.debugLineNum = 3997976;BA.debugLine="Year2.Text = \"(2018)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2018)"));
RDebugUtils.currentLine=3997977;
 //BA.debugLineNum = 3997977;BA.debugLine="OverView2.Text = \"A young reporter and his niece";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("A young reporter and his niece discover a real-life mermaid being held captive by a shady circus owner. As they befriend the mermaid, they embark on a magical adventure to save her and help her return to the sea."));
RDebugUtils.currentLine=3997978;
 //BA.debugLineNum = 3997978;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997979;
 //BA.debugLineNum = 3997979;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"littleMermaid.jpg").getObject()));
RDebugUtils.currentLine=3997981;
 //BA.debugLineNum = 3997981;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997982;
 //BA.debugLineNum = 3997982;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997983;
 //BA.debugLineNum = 3997983;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997984;
 //BA.debugLineNum = 3997984;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997986;
 //BA.debugLineNum = 3997986;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997988;
 //BA.debugLineNum = 3997988;BA.debugLine="Else If query.Contains(\"natalie portman\") Or quer";
if (_query.contains("natalie portman") || _query.contains("natalie") || _query.contains("portman")) { 
RDebugUtils.currentLine=3997990;
 //BA.debugLineNum = 3997990;BA.debugLine="Drama1.Text = \"V for Vendetta\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("V for Vendetta"));
RDebugUtils.currentLine=3997991;
 //BA.debugLineNum = 3997991;BA.debugLine="Starter1.Text = \"Starring: Hugo Weaving, Natalie";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugo Weaving, Natalie Portman, Stephen Rea"));
RDebugUtils.currentLine=3997992;
 //BA.debugLineNum = 3997992;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3997993;
 //BA.debugLineNum = 3997993;BA.debugLine="OverView1.Text = \"In a totalitarian future Brita";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a totalitarian future Britain, a masked revolutionary known as \"V\" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."));
RDebugUtils.currentLine=3997994;
 //BA.debugLineNum = 3997994;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997995;
 //BA.debugLineNum = 3997995;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=3997997;
 //BA.debugLineNum = 3997997;BA.debugLine="Drama2.Text = \"Thor: The Dark World\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Thor: The Dark World"));
RDebugUtils.currentLine=3997998;
 //BA.debugLineNum = 3997998;BA.debugLine="Starter2.Text = \"Starring: Chris Hemsworth, Nata";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Chris Hemsworth, Natalie Portman, Tom Hiddleston"));
RDebugUtils.currentLine=3997999;
 //BA.debugLineNum = 3997999;BA.debugLine="Year2.Text = \"(2013)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=3998000;
 //BA.debugLineNum = 3998000;BA.debugLine="OverView2.Text = \"Thor must team up with his tre";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Thor must team up with his treacherous brother Loki to stop the Dark Elves, led by the vengeful Malekith, who seeks to plunge the universe into darkness using a powerful ancient force known as the Aether."));
RDebugUtils.currentLine=3998001;
 //BA.debugLineNum = 3998001;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3998002;
 //BA.debugLineNum = 3998002;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"thorDark.jpg").getObject()));
RDebugUtils.currentLine=3998004;
 //BA.debugLineNum = 3998004;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998005;
 //BA.debugLineNum = 3998005;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998006;
 //BA.debugLineNum = 3998006;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998007;
 //BA.debugLineNum = 3998007;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998009;
 //BA.debugLineNum = 3998009;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3998012;
 //BA.debugLineNum = 3998012;BA.debugLine="Else If query.Contains(\"elijah wood\") Or query.Co";
if (_query.contains("elijah wood") || _query.contains("elijah") || _query.contains("wood") || _query.contains("ian mckellen") || _query.contains("ian") || _query.contains("mckellen")) { 
RDebugUtils.currentLine=3998013;
 //BA.debugLineNum = 3998013;BA.debugLine="Drama1.Text = \"The Fellowship of the Ring\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Fellowship of the Ring"));
RDebugUtils.currentLine=3998014;
 //BA.debugLineNum = 3998014;BA.debugLine="Starter1.Text = \"Starring: Elijah Wood, Ian McKe";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Elijah Wood, Ian McKellen, Orlando Bloom"));
RDebugUtils.currentLine=3998015;
 //BA.debugLineNum = 3998015;BA.debugLine="Year1.Text = \"(2001)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2001)"));
RDebugUtils.currentLine=3998016;
 //BA.debugLineNum = 3998016;BA.debugLine="OverView1.Text = \"The future of civilization res";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"));
RDebugUtils.currentLine=3998017;
 //BA.debugLineNum = 3998017;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3998018;
 //BA.debugLineNum = 3998018;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lordRings.jpg").getObject()));
RDebugUtils.currentLine=3998020;
 //BA.debugLineNum = 3998020;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998021;
 //BA.debugLineNum = 3998021;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998022;
 //BA.debugLineNum = 3998022;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998023;
 //BA.debugLineNum = 3998023;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998024;
 //BA.debugLineNum = 3998024;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998026;
 //BA.debugLineNum = 3998026;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3998027;
 //BA.debugLineNum = 3998027;BA.debugLine="Else If query.Contains(\"freddie highmore\") Or que";
if (_query.contains("freddie highmore") || _query.contains("freddie") || _query.contains("highmore") || _query.contains("david kelly") || _query.contains("david") || _query.contains("kelly")) { 
RDebugUtils.currentLine=3998028;
 //BA.debugLineNum = 3998028;BA.debugLine="Drama1.Text = \"Charlie and the Chocolate Factory";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Charlie and the Chocolate Factory"));
RDebugUtils.currentLine=3998029;
 //BA.debugLineNum = 3998029;BA.debugLine="Starter1.Text = \"Starring: Johnny Depp, Freddie";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"));
RDebugUtils.currentLine=3998030;
 //BA.debugLineNum = 3998030;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3998031;
 //BA.debugLineNum = 3998031;BA.debugLine="OverView1.Text = \"Charlie Bucket, a humble boy f";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."));
RDebugUtils.currentLine=3998032;
 //BA.debugLineNum = 3998032;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3998033;
 //BA.debugLineNum = 3998033;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"charlieAndTheChocolate.jpg").getObject()));
RDebugUtils.currentLine=3998035;
 //BA.debugLineNum = 3998035;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998036;
 //BA.debugLineNum = 3998036;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998037;
 //BA.debugLineNum = 3998037;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998038;
 //BA.debugLineNum = 3998038;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998039;
 //BA.debugLineNum = 3998039;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998041;
 //BA.debugLineNum = 3998041;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3998042;
 //BA.debugLineNum = 3998042;BA.debugLine="Else if query.Contains(\"mia wasikowska\") Or query";
if (_query.contains("mia wasikowska") || _query.contains("mia") || _query.contains("wasikowska") || _query.contains("anne hathaway") || _query.contains("anne") || _query.contains("hathaway")) { 
RDebugUtils.currentLine=3998043;
 //BA.debugLineNum = 3998043;BA.debugLine="Drama1.Text = \"Alice in Wonderland\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Alice in Wonderland"));
RDebugUtils.currentLine=3998044;
 //BA.debugLineNum = 3998044;BA.debugLine="Starter1.Text = \"Starring: Mia Wasikowska, Johnn";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"));
RDebugUtils.currentLine=3998045;
 //BA.debugLineNum = 3998045;BA.debugLine="Year1.Text = \"(2010)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2010)"));
RDebugUtils.currentLine=3998046;
 //BA.debugLineNum = 3998046;BA.debugLine="OverView1.Text = \"Alice, now a teenager, returns";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."));
RDebugUtils.currentLine=3998047;
 //BA.debugLineNum = 3998047;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3998048;
 //BA.debugLineNum = 3998048;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"aliceAndWonderland.jpg").getObject()));
RDebugUtils.currentLine=3998050;
 //BA.debugLineNum = 3998050;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998051;
 //BA.debugLineNum = 3998051;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998052;
 //BA.debugLineNum = 3998052;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998053;
 //BA.debugLineNum = 3998053;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998054;
 //BA.debugLineNum = 3998054;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998056;
 //BA.debugLineNum = 3998056;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3998057;
 //BA.debugLineNum = 3998057;BA.debugLine="Else if query.Contains(\"daniel radcliffe\") Or que";
if (_query.contains("daniel radcliffe") || _query.contains("daniel") || _query.contains("radcliffe") || _query.contains("rupert grint") || _query.contains("rupert") || _query.contains("grint") || _query.contains("emma watson") || _query.contains("emma") || _query.contains("watson")) { 
RDebugUtils.currentLine=3998058;
 //BA.debugLineNum = 3998058;BA.debugLine="Drama1.Text = \"Harry Potter and the Philosopher'";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Harry Potter and the Philosopher's Stone"));
RDebugUtils.currentLine=3998059;
 //BA.debugLineNum = 3998059;BA.debugLine="Starter1.Text = \"Starring: Daniel Radcliffe, Rup";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Daniel Radcliffe, Rupert Grint, Emma Watson"));
RDebugUtils.currentLine=3998060;
 //BA.debugLineNum = 3998060;BA.debugLine="Year1.Text = \"(2003)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2003)"));
RDebugUtils.currentLine=3998061;
 //BA.debugLineNum = 3998061;BA.debugLine="OverView1.Text = \"Captain Jack Sparrow must resc";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Captain Jack Sparrow must rescue Elizabeth Swann, who has been kidnapped by cursed pirates led by Captain Barbossa. Alongside Will Turner, Jack battles ghostly pirates, seeking both freedom and revenge."));
RDebugUtils.currentLine=3998062;
 //BA.debugLineNum = 3998062;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3998063;
 //BA.debugLineNum = 3998063;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"harryPotter.jpg").getObject()));
RDebugUtils.currentLine=3998065;
 //BA.debugLineNum = 3998065;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998066;
 //BA.debugLineNum = 3998066;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998067;
 //BA.debugLineNum = 3998067;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998068;
 //BA.debugLineNum = 3998068;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998069;
 //BA.debugLineNum = 3998069;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998071;
 //BA.debugLineNum = 3998071;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3998072;
 //BA.debugLineNum = 3998072;BA.debugLine="Else if query.Contains(\"keira knightley\") Or quer";
if (_query.contains("keira knightley") || _query.contains("keira") || _query.contains("knightley")) { 
RDebugUtils.currentLine=3998073;
 //BA.debugLineNum = 3998073;BA.debugLine="Drama1.Text = \"The Curse of the Black Pearl\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Curse of the Black Pearl"));
RDebugUtils.currentLine=3998074;
 //BA.debugLineNum = 3998074;BA.debugLine="Starter1.Text = \"Starring: Johnny Depp, Orlando";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Orlando Bloom, Keira Knightley"));
RDebugUtils.currentLine=3998075;
 //BA.debugLineNum = 3998075;BA.debugLine="Year1.Text = \"(2003)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2003)"));
RDebugUtils.currentLine=3998076;
 //BA.debugLineNum = 3998076;BA.debugLine="OverView1.Text = \"Four siblings, evacuated from";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=3998077;
 //BA.debugLineNum = 3998077;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3998078;
 //BA.debugLineNum = 3998078;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"PiratesCurse.jpg").getObject()));
RDebugUtils.currentLine=3998080;
 //BA.debugLineNum = 3998080;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998081;
 //BA.debugLineNum = 3998081;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998082;
 //BA.debugLineNum = 3998082;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998083;
 //BA.debugLineNum = 3998083;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998084;
 //BA.debugLineNum = 3998084;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998086;
 //BA.debugLineNum = 3998086;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3998088;
 //BA.debugLineNum = 3998088;BA.debugLine="Else if query.Contains(\"skandar keynes\") Or query";
if (_query.contains("skandar keynes") || _query.contains("skandar") || _query.contains("keynes") || _query.contains("william moseley") || _query.contains("william") || _query.contains("moseley")) { 
RDebugUtils.currentLine=3998089;
 //BA.debugLineNum = 3998089;BA.debugLine="Drama6.Text = \"The Chronicles of Narnia\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("The Chronicles of Narnia"));
RDebugUtils.currentLine=3998090;
 //BA.debugLineNum = 3998090;BA.debugLine="Starter6.Text = \"Starring: Georgie Henley, Skand";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Georgie Henley, Skandar Keynes, William Moseley"));
RDebugUtils.currentLine=3998091;
 //BA.debugLineNum = 3998091;BA.debugLine="Year6.Text = \"(2005)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3998092;
 //BA.debugLineNum = 3998092;BA.debugLine="OverView6.Text = \"Four siblings, evacuated from";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=3998093;
 //BA.debugLineNum = 3998093;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
mostCurrent._dramaimage6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3998094;
 //BA.debugLineNum = 3998094;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage6.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"narnia.jpg").getObject()));
RDebugUtils.currentLine=3998096;
 //BA.debugLineNum = 3998096;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998097;
 //BA.debugLineNum = 3998097;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998098;
 //BA.debugLineNum = 3998098;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998099;
 //BA.debugLineNum = 3998099;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998100;
 //BA.debugLineNum = 3998100;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998102;
 //BA.debugLineNum = 3998102;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3998104;
 //BA.debugLineNum = 3998104;BA.debugLine="Else if query.Contains(\"benedict cumberbatch\") Or";
if (_query.contains("benedict cumberbatch") || _query.contains("benedict") || _query.contains("cumberbatch") || _query.contains("chiwetel ejiofor") || _query.contains("chiwetel") || _query.contains("ejiofor")) { 
RDebugUtils.currentLine=3998105;
 //BA.debugLineNum = 3998105;BA.debugLine="Drama1.Text = \"Doctor Strange\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Doctor Strange"));
RDebugUtils.currentLine=3998106;
 //BA.debugLineNum = 3998106;BA.debugLine="Starter1.Text = \"Starring: Benedict Cumberbatch,";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Benedict Cumberbatch, Chiwetel Ejiofor"));
RDebugUtils.currentLine=3998107;
 //BA.debugLineNum = 3998107;BA.debugLine="Year1.Text = \"(2016)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=3998108;
 //BA.debugLineNum = 3998108;BA.debugLine="OverView1.Text = \"After a life-changing accident";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After a life-changing accident ends his career, arrogant surgeon Stephen Strange seeks healing in Nepal, where he learns the mystic arts and must protect the world from dark magical forces beyond reality."));
RDebugUtils.currentLine=3998109;
 //BA.debugLineNum = 3998109;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3998110;
 //BA.debugLineNum = 3998110;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"doctorStrange.png").getObject()));
RDebugUtils.currentLine=3998112;
 //BA.debugLineNum = 3998112;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998113;
 //BA.debugLineNum = 3998113;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998114;
 //BA.debugLineNum = 3998114;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998115;
 //BA.debugLineNum = 3998115;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998116;
 //BA.debugLineNum = 3998116;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998118;
 //BA.debugLineNum = 3998118;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3998120;
 //BA.debugLineNum = 3998120;BA.debugLine="Else if query.Contains(\"hugo weaving\") Or query.C";
if (_query.contains("hugo weaving") || _query.contains("hugo") || _query.contains("weaving") || _query.contains("stephen rea") || _query.contains("stephen") || _query.contains("rea")) { 
RDebugUtils.currentLine=3998121;
 //BA.debugLineNum = 3998121;BA.debugLine="Drama1.Text = \"V for Vendetta\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("V for Vendetta"));
RDebugUtils.currentLine=3998122;
 //BA.debugLineNum = 3998122;BA.debugLine="Starter1.Text = \"Starring: Hugo Weaving, Natalie";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugo Weaving, Natalie Portman, Stephen Rea"));
RDebugUtils.currentLine=3998123;
 //BA.debugLineNum = 3998123;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3998124;
 //BA.debugLineNum = 3998124;BA.debugLine="OverView1.Text = \"In a totalitarian future Brita";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a totalitarian future Britain, a masked revolutionary known as \"V\" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."));
RDebugUtils.currentLine=3998125;
 //BA.debugLineNum = 3998125;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3998126;
 //BA.debugLineNum = 3998126;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"vendetta.jpg").getObject()));
RDebugUtils.currentLine=3998128;
 //BA.debugLineNum = 3998128;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998129;
 //BA.debugLineNum = 3998129;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998130;
 //BA.debugLineNum = 3998130;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998131;
 //BA.debugLineNum = 3998131;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998132;
 //BA.debugLineNum = 3998132;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998134;
 //BA.debugLineNum = 3998134;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3998137;
 //BA.debugLineNum = 3998137;BA.debugLine="Else if query.Contains(\"mena massoud\") Or query.C";
if (_query.contains("mena massoud") || _query.contains("mena") || _query.contains("massoud") || _query.contains("naomi scott") || _query.contains("naomi") || _query.contains("scott")) { 
RDebugUtils.currentLine=3998138;
 //BA.debugLineNum = 3998138;BA.debugLine="Drama1.Text = \"Aladdin\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Aladdin"));
RDebugUtils.currentLine=3998139;
 //BA.debugLineNum = 3998139;BA.debugLine="Starter1.Text = \"Starring: Mena Massoud, Naomi S";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Mena Massoud, Naomi Scott, Will Smith"));
RDebugUtils.currentLine=3998140;
 //BA.debugLineNum = 3998140;BA.debugLine="Year1.Text = \"(2019)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=3998141;
 //BA.debugLineNum = 3998141;BA.debugLine="OverView1.Text = \"Aladdin, a kind-hearted street";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."));
RDebugUtils.currentLine=3998142;
 //BA.debugLineNum = 3998142;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3998143;
 //BA.debugLineNum = 3998143;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"aladdin.jpg").getObject()));
RDebugUtils.currentLine=3998145;
 //BA.debugLineNum = 3998145;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998146;
 //BA.debugLineNum = 3998146;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998147;
 //BA.debugLineNum = 3998147;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998148;
 //BA.debugLineNum = 3998148;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998149;
 //BA.debugLineNum = 3998149;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998151;
 //BA.debugLineNum = 3998151;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3998153;
 //BA.debugLineNum = 3998153;BA.debugLine="Else if query.Contains(\"jaden smith\") Or query.Co";
if (_query.contains("jaden smith") || _query.contains("jaden") || _query.contains("smith") || _query.contains("sigourney weave") || _query.contains("sigourney") || _query.contains("weave")) { 
RDebugUtils.currentLine=3998154;
 //BA.debugLineNum = 3998154;BA.debugLine="Drama1.Text = \"After Earth\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("After Earth"));
RDebugUtils.currentLine=3998155;
 //BA.debugLineNum = 3998155;BA.debugLine="Starter1.Text = \"Starring: Will Smith, Jaden Smi";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Will Smith, Jaden Smith, Sigourney Weaver"));
RDebugUtils.currentLine=3998156;
 //BA.debugLineNum = 3998156;BA.debugLine="Year1.Text = \"(2013)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=3998157;
 //BA.debugLineNum = 3998157;BA.debugLine="OverView1.Text = \"Set in the future, After Earth";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."));
RDebugUtils.currentLine=3998158;
 //BA.debugLineNum = 3998158;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3998159;
 //BA.debugLineNum = 3998159;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"afterEarch.jpg").getObject()));
RDebugUtils.currentLine=3998161;
 //BA.debugLineNum = 3998161;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998162;
 //BA.debugLineNum = 3998162;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998163;
 //BA.debugLineNum = 3998163;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998164;
 //BA.debugLineNum = 3998164;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998165;
 //BA.debugLineNum = 3998165;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998167;
 //BA.debugLineNum = 3998167;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else {
RDebugUtils.currentLine=3998171;
 //BA.debugLineNum = 3998171;BA.debugLine="MsgboxAsync(\"No results found for\" & \" \"\"\" & Use";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("No results found for"+" \""+_userinput+"\""),BA.ObjectToCharSequence(""),processBA);
 }}}}}}}}}}}}}}}}}}}}}}}}}}
;
RDebugUtils.currentLine=3998175;
 //BA.debugLineNum = 3998175;BA.debugLine="p.Width = 100%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=3998176;
 //BA.debugLineNum = 3998176;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=3998178;
 //BA.debugLineNum = 3998178;BA.debugLine="End Sub";
return "";
}
public static String  _searchengine_textchanged(String _old,String _new) throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchengine_textchanged", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchengine_textchanged", new Object[] {_old,_new}));}
String _query = "";
RDebugUtils.currentLine=4063232;
 //BA.debugLineNum = 4063232;BA.debugLine="Sub SearchEngine_TextChanged (Old As String, New A";
RDebugUtils.currentLine=4063233;
 //BA.debugLineNum = 4063233;BA.debugLine="Dim query As String = New.ToLowerCase.Trim";
_query = _new.toLowerCase().trim();
RDebugUtils.currentLine=4063235;
 //BA.debugLineNum = 4063235;BA.debugLine="If query = \"\" Then";
if ((_query).equals("")) { 
RDebugUtils.currentLine=4063238;
 //BA.debugLineNum = 4063238;BA.debugLine="p.Height = 210%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=4063239;
 //BA.debugLineNum = 4063239;BA.debugLine="p.Width = 200%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA));
RDebugUtils.currentLine=4063240;
 //BA.debugLineNum = 4063240;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=4063242;
 //BA.debugLineNum = 4063242;BA.debugLine="PanelMovie1.Visible = True";
mostCurrent._panelmovie1.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=4063243;
 //BA.debugLineNum = 4063243;BA.debugLine="PanelMovie2.Visible = True";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=4063244;
 //BA.debugLineNum = 4063244;BA.debugLine="PanelMovie3.Visible = True";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=4063245;
 //BA.debugLineNum = 4063245;BA.debugLine="PanelMovie4.Visible = True";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=4063246;
 //BA.debugLineNum = 4063246;BA.debugLine="PanelMovie5.Visible = True";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=4063247;
 //BA.debugLineNum = 4063247;BA.debugLine="PanelMovie6.Visible = True";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=4063248;
 //BA.debugLineNum = 4063248;BA.debugLine="PanelMovie7.Visible = True";
mostCurrent._panelmovie7.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=4063249;
 //BA.debugLineNum = 4063249;BA.debugLine="PanelMovie8.Visible = True";
mostCurrent._panelmovie8.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=4063250;
 //BA.debugLineNum = 4063250;BA.debugLine="PanelMovie9.Visible = True";
mostCurrent._panelmovie9.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=4063251;
 //BA.debugLineNum = 4063251;BA.debugLine="PanelMovie10.Visible = True";
mostCurrent._panelmovie10.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=4063255;
 //BA.debugLineNum = 4063255;BA.debugLine="Drama1.Text = \"The Fellowship of the Ring\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Fellowship of the Ring"));
RDebugUtils.currentLine=4063256;
 //BA.debugLineNum = 4063256;BA.debugLine="Starter1.Text = \"Starring: Elijah Wood, Ian McKe";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Elijah Wood, Ian McKellen, Orlando Bloom"));
RDebugUtils.currentLine=4063257;
 //BA.debugLineNum = 4063257;BA.debugLine="Year1.Text = \"(2001)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2001)"));
RDebugUtils.currentLine=4063258;
 //BA.debugLineNum = 4063258;BA.debugLine="OverView1.Text = \"The future of civilization res";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"));
RDebugUtils.currentLine=4063259;
 //BA.debugLineNum = 4063259;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=4063260;
 //BA.debugLineNum = 4063260;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lordRings.jpg").getObject()));
RDebugUtils.currentLine=4063262;
 //BA.debugLineNum = 4063262;BA.debugLine="Drama2.Text = \"Charlie and the Chocolate Factory";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Charlie and the Chocolate Factory"));
RDebugUtils.currentLine=4063263;
 //BA.debugLineNum = 4063263;BA.debugLine="Starter2.Text = \"Starring: Johnny Depp, Freddie";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"));
RDebugUtils.currentLine=4063264;
 //BA.debugLineNum = 4063264;BA.debugLine="Year2.Text = \"(2005)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=4063265;
 //BA.debugLineNum = 4063265;BA.debugLine="OverView2.Text = \"Charlie Bucket, a humble boy f";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."));
RDebugUtils.currentLine=4063266;
 //BA.debugLineNum = 4063266;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=4063267;
 //BA.debugLineNum = 4063267;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"charlieAndTheChocolate.jpg").getObject()));
RDebugUtils.currentLine=4063269;
 //BA.debugLineNum = 4063269;BA.debugLine="Drama3.Text = \"Alice in Wonderland\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("Alice in Wonderland"));
RDebugUtils.currentLine=4063270;
 //BA.debugLineNum = 4063270;BA.debugLine="Starter3.Text = \"Starring: Mia Wasikowska, Johnn";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"));
RDebugUtils.currentLine=4063271;
 //BA.debugLineNum = 4063271;BA.debugLine="Year3.Text = \"(2010)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2010)"));
RDebugUtils.currentLine=4063272;
 //BA.debugLineNum = 4063272;BA.debugLine="OverView3.Text = \"Alice, now a teenager, returns";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."));
RDebugUtils.currentLine=4063273;
 //BA.debugLineNum = 4063273;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=4063274;
 //BA.debugLineNum = 4063274;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"aliceAndWonderland.jpg").getObject()));
RDebugUtils.currentLine=4063276;
 //BA.debugLineNum = 4063276;BA.debugLine="Drama4.Text = \"Harry Potter and the Philosopher'";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Harry Potter and the Philosopher's Stone"));
RDebugUtils.currentLine=4063277;
 //BA.debugLineNum = 4063277;BA.debugLine="Starter4.Text = \"Starring: Daniel Radcliffe, Rup";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Daniel Radcliffe, Rupert Grint, Emma Watson"));
RDebugUtils.currentLine=4063278;
 //BA.debugLineNum = 4063278;BA.debugLine="Year4.Text = \"(2001)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2001)"));
RDebugUtils.currentLine=4063279;
 //BA.debugLineNum = 4063279;BA.debugLine="OverView4.Text = \"Captain Jack Sparrow must resc";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("Captain Jack Sparrow must rescue Elizabeth Swann, who has been kidnapped by cursed pirates led by Captain Barbossa. Alongside Will Turner, Jack battles ghostly pirates, seeking both freedom and revenge."));
RDebugUtils.currentLine=4063280;
 //BA.debugLineNum = 4063280;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=4063281;
 //BA.debugLineNum = 4063281;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"harryPotter.jpg").getObject()));
RDebugUtils.currentLine=4063283;
 //BA.debugLineNum = 4063283;BA.debugLine="Drama5.Text = \"The Curse of the Black Pearl\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("The Curse of the Black Pearl"));
RDebugUtils.currentLine=4063284;
 //BA.debugLineNum = 4063284;BA.debugLine="Starter5.Text = \"Starring: Johnny Depp, Orlando";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Orlando Bloom, Keira Knightley"));
RDebugUtils.currentLine=4063285;
 //BA.debugLineNum = 4063285;BA.debugLine="Year5.Text = \"(2003)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(2003)"));
RDebugUtils.currentLine=4063286;
 //BA.debugLineNum = 4063286;BA.debugLine="OverView5.Text = \"Four siblings, evacuated from";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=4063287;
 //BA.debugLineNum = 4063287;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
mostCurrent._dramaimage5.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=4063288;
 //BA.debugLineNum = 4063288;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage5.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"PiratesCurse.jpg").getObject()));
RDebugUtils.currentLine=4063291;
 //BA.debugLineNum = 4063291;BA.debugLine="Drama6.Text = \"The Chronicles of Narnia\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("The Chronicles of Narnia"));
RDebugUtils.currentLine=4063292;
 //BA.debugLineNum = 4063292;BA.debugLine="Starter6.Text = \"Starring: Georgie Henley, Skand";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Georgie Henley, Skandar Keynes, William Moseley,"));
RDebugUtils.currentLine=4063293;
 //BA.debugLineNum = 4063293;BA.debugLine="Year6.Text = \"(2005)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=4063294;
 //BA.debugLineNum = 4063294;BA.debugLine="OverView6.Text = \"Four siblings, evacuated from";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=4063295;
 //BA.debugLineNum = 4063295;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
mostCurrent._dramaimage6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=4063296;
 //BA.debugLineNum = 4063296;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage6.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"narnia.jpg").getObject()));
RDebugUtils.currentLine=4063298;
 //BA.debugLineNum = 4063298;BA.debugLine="Drama7.Text = \"Doctor Strange\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("Doctor Strange"));
RDebugUtils.currentLine=4063299;
 //BA.debugLineNum = 4063299;BA.debugLine="Starter7.Text = \"Starring: Benedict Cumberbatch,";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Benedict Cumberbatch, Chiwetel Ejiofor"));
RDebugUtils.currentLine=4063300;
 //BA.debugLineNum = 4063300;BA.debugLine="Year7.Text = \"(2016)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=4063301;
 //BA.debugLineNum = 4063301;BA.debugLine="OverView7.Text = \"After a life-changing accident";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("After a life-changing accident ends his career, arrogant surgeon Stephen Strange seeks healing in Nepal, where he learns the mystic arts and must protect the world from dark magical forces beyond reality."));
RDebugUtils.currentLine=4063302;
 //BA.debugLineNum = 4063302;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=4063303;
 //BA.debugLineNum = 4063303;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"doctorStrange.png").getObject()));
RDebugUtils.currentLine=4063305;
 //BA.debugLineNum = 4063305;BA.debugLine="Drama8.Text = \"V for Vendetta\"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("V for Vendetta"));
RDebugUtils.currentLine=4063306;
 //BA.debugLineNum = 4063306;BA.debugLine="Starter8.Text = \"Starring: Hugo Weaving, Natalie";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Hugo Weaving, Natalie Portman, Stephen Rea, John Hurt"));
RDebugUtils.currentLine=4063307;
 //BA.debugLineNum = 4063307;BA.debugLine="Year8.Text = \"(2005)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=4063308;
 //BA.debugLineNum = 4063308;BA.debugLine="OverView8.Text = \"In a totalitarian future Brita";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("In a totalitarian future Britain, a masked revolutionary known as \"V\" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."));
RDebugUtils.currentLine=4063309;
 //BA.debugLineNum = 4063309;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
mostCurrent._dramaimage8.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=4063310;
 //BA.debugLineNum = 4063310;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage8.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"vendetta.jpg").getObject()));
RDebugUtils.currentLine=4063312;
 //BA.debugLineNum = 4063312;BA.debugLine="Drama9.Text = \"Aladdin\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("Aladdin"));
RDebugUtils.currentLine=4063313;
 //BA.debugLineNum = 4063313;BA.debugLine="Starter9.Text = \"Starring: Mena Massoud, Naomi S";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Mena Massoud, Naomi Scott, Will Smith"));
RDebugUtils.currentLine=4063314;
 //BA.debugLineNum = 4063314;BA.debugLine="Year9.Text = \"(2019)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=4063315;
 //BA.debugLineNum = 4063315;BA.debugLine="OverView9.Text = \"Aladdin, a kind-hearted street";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."));
RDebugUtils.currentLine=4063316;
 //BA.debugLineNum = 4063316;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
mostCurrent._dramaimage9.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=4063317;
 //BA.debugLineNum = 4063317;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage9.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"aladdin.jpg").getObject()));
RDebugUtils.currentLine=4063319;
 //BA.debugLineNum = 4063319;BA.debugLine="Drama10.Text = \"After Earth\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("After Earth"));
RDebugUtils.currentLine=4063320;
 //BA.debugLineNum = 4063320;BA.debugLine="Starter10.Text = \"Starring: Will Smith, Jaden Sm";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Will Smith, Jaden Smith, Sigourney Weaver"));
RDebugUtils.currentLine=4063321;
 //BA.debugLineNum = 4063321;BA.debugLine="Year10.Text = \"(2013)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=4063322;
 //BA.debugLineNum = 4063322;BA.debugLine="OverView10.Text = \"Set in the future, After Eart";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."));
RDebugUtils.currentLine=4063323;
 //BA.debugLineNum = 4063323;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=4063324;
 //BA.debugLineNum = 4063324;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"afterEarch.jpg").getObject()));
 };
RDebugUtils.currentLine=4063330;
 //BA.debugLineNum = 4063330;BA.debugLine="End Sub";
return "";
}
}