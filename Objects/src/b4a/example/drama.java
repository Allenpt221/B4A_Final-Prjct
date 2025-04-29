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

public class drama extends Activity implements B4AActivity{
	public static drama mostCurrent;
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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.drama");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (drama).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.drama");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.drama", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (drama) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (drama) Resume **");
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
		return drama.class;
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
            BA.LogInfo("** Activity (drama) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (drama) Pause event (activity is not paused). **");
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
            drama mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (drama) Resume **");
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
public b4a.example.action _action = null;
public b4a.example.scifi _scifi = null;
public b4a.example.panelview _panelview = null;
public static String  _actionpage_click() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "actionpage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "actionpage_click", null));}
RDebugUtils.currentLine=1572864;
 //BA.debugLineNum = 1572864;BA.debugLine="Private Sub ActionPage_Click";
RDebugUtils.currentLine=1572865;
 //BA.debugLineNum = 1572865;BA.debugLine="StartActivity(Action)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._action.getObject()));
RDebugUtils.currentLine=1572866;
 //BA.debugLineNum = 1572866;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=1572867;
 //BA.debugLineNum = 1572867;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=1048576;
 //BA.debugLineNum = 1048576;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=1048577;
 //BA.debugLineNum = 1048577;BA.debugLine="Activity.LoadLayout(\"Drama\") ' Layout contains Sc";
mostCurrent._activity.LoadLayout("Drama",mostCurrent.activityBA);
RDebugUtils.currentLine=1048579;
 //BA.debugLineNum = 1048579;BA.debugLine="p.Initialize(\"\")";
mostCurrent._p.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=1048580;
 //BA.debugLineNum = 1048580;BA.debugLine="p.LoadLayout(\"panelview\")";
mostCurrent._p.LoadLayout("panelview",mostCurrent.activityBA);
RDebugUtils.currentLine=1048587;
 //BA.debugLineNum = 1048587;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Kramer vs. Kramer"));
RDebugUtils.currentLine=1048588;
 //BA.debugLineNum = 1048588;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
RDebugUtils.currentLine=1048589;
 //BA.debugLineNum = 1048589;BA.debugLine="Year1.Text = \"(1979)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1979)"));
RDebugUtils.currentLine=1048590;
 //BA.debugLineNum = 1048590;BA.debugLine="OverView1.Text = \"In this emotionally charged cou";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
RDebugUtils.currentLine=1048591;
 //BA.debugLineNum = 1048591;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1048592;
 //BA.debugLineNum = 1048592;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=1048594;
 //BA.debugLineNum = 1048594;BA.debugLine="Drama2.Text = \"Manchester by the Sea\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Manchester by the Sea"));
RDebugUtils.currentLine=1048595;
 //BA.debugLineNum = 1048595;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michell";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
RDebugUtils.currentLine=1048596;
 //BA.debugLineNum = 1048596;BA.debugLine="Year2.Text = \"(2016)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=1048597;
 //BA.debugLineNum = 1048597;BA.debugLine="OverView2.Text = \"After the death of his brother,";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
RDebugUtils.currentLine=1048598;
 //BA.debugLineNum = 1048598;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1048599;
 //BA.debugLineNum = 1048599;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=1048601;
 //BA.debugLineNum = 1048601;BA.debugLine="Drama3.Text = \"The Master\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=1048602;
 //BA.debugLineNum = 1048602;BA.debugLine="Starter3.Text = \"Starring: Philip Seymour Hoffman";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=1048603;
 //BA.debugLineNum = 1048603;BA.debugLine="Year3.Text = \"(2012)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=1048604;
 //BA.debugLineNum = 1048604;BA.debugLine="OverView3.Text = \"A mentally unstable WWII vetera";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=1048605;
 //BA.debugLineNum = 1048605;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1048606;
 //BA.debugLineNum = 1048606;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=1048608;
 //BA.debugLineNum = 1048608;BA.debugLine="Drama4.Text = \"Million Dollar Baby\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Million Dollar Baby"));
RDebugUtils.currentLine=1048609;
 //BA.debugLineNum = 1048609;BA.debugLine="Starter4.Text = \"Starring: Morgan Freeman, Hilary";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
RDebugUtils.currentLine=1048610;
 //BA.debugLineNum = 1048610;BA.debugLine="Year4.Text = \"(2004)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2004)"));
RDebugUtils.currentLine=1048611;
 //BA.debugLineNum = 1048611;BA.debugLine="OverView4.Text = \"A waitress with dreams of becom";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
RDebugUtils.currentLine=1048612;
 //BA.debugLineNum = 1048612;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1048613;
 //BA.debugLineNum = 1048613;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=1048615;
 //BA.debugLineNum = 1048615;BA.debugLine="Drama5.Text = \"The Bridges of Madison County\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("The Bridges of Madison County"));
RDebugUtils.currentLine=1048616;
 //BA.debugLineNum = 1048616;BA.debugLine="Starter5.Text = \"Starring: Clint Eastwood, Meryl";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
RDebugUtils.currentLine=1048617;
 //BA.debugLineNum = 1048617;BA.debugLine="Year5.Text = \"(1995)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(1995)"));
RDebugUtils.currentLine=1048618;
 //BA.debugLineNum = 1048618;BA.debugLine="OverView5.Text = \"A brief, passionate romance bet";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
RDebugUtils.currentLine=1048619;
 //BA.debugLineNum = 1048619;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
mostCurrent._dramaimage5.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1048620;
 //BA.debugLineNum = 1048620;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage5.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=1048623;
 //BA.debugLineNum = 1048623;BA.debugLine="Drama6.Text = \"Gone Baby Gone\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("Gone Baby Gone"));
RDebugUtils.currentLine=1048624;
 //BA.debugLineNum = 1048624;BA.debugLine="Starter6.Text = \"Starring: Casey Affleck, Michell";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=1048625;
 //BA.debugLineNum = 1048625;BA.debugLine="Year6.Text = \"(2007)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2007)"));
RDebugUtils.currentLine=1048626;
 //BA.debugLineNum = 1048626;BA.debugLine="OverView6.Text = \"In a tough Boston neighborhood,";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
RDebugUtils.currentLine=1048627;
 //BA.debugLineNum = 1048627;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
mostCurrent._dramaimage6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1048628;
 //BA.debugLineNum = 1048628;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage6.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=1048630;
 //BA.debugLineNum = 1048630;BA.debugLine="Drama7.Text = \"Blue Jasmine\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=1048631;
 //BA.debugLineNum = 1048631;BA.debugLine="Starter7.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=1048632;
 //BA.debugLineNum = 1048632;BA.debugLine="Year7.Text = \"(2013)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1048633;
 //BA.debugLineNum = 1048633;BA.debugLine="OverView7.Text = \"After losing her fortune and st";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=1048634;
 //BA.debugLineNum = 1048634;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1048635;
 //BA.debugLineNum = 1048635;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=1048637;
 //BA.debugLineNum = 1048637;BA.debugLine="Drama8.Text = \"Her\"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("Her"));
RDebugUtils.currentLine=1048638;
 //BA.debugLineNum = 1048638;BA.debugLine="Starter8.Text = \"Starring: Joaquin Phoenix, Roone";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
RDebugUtils.currentLine=1048639;
 //BA.debugLineNum = 1048639;BA.debugLine="Year8.Text = \"(2013)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1048640;
 //BA.debugLineNum = 1048640;BA.debugLine="OverView8.Text = \"In a near-future Los Angeles, a";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
RDebugUtils.currentLine=1048641;
 //BA.debugLineNum = 1048641;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
mostCurrent._dramaimage8.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1048642;
 //BA.debugLineNum = 1048642;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage8.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=1048644;
 //BA.debugLineNum = 1048644;BA.debugLine="Drama9.Text = \"Carol\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=1048645;
 //BA.debugLineNum = 1048645;BA.debugLine="Starter9.Text = \"Starring: Rooney Mara, Cate Blan";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=1048646;
 //BA.debugLineNum = 1048646;BA.debugLine="Year9.Text = \"(2015)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=1048647;
 //BA.debugLineNum = 1048647;BA.debugLine="OverView9.Text = \"A chance encounter between a yo";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=1048648;
 //BA.debugLineNum = 1048648;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
mostCurrent._dramaimage9.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1048649;
 //BA.debugLineNum = 1048649;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage9.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=1048651;
 //BA.debugLineNum = 1048651;BA.debugLine="Drama10.Text = \"The Lost Daughter\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("The Lost Daughter"));
RDebugUtils.currentLine=1048652;
 //BA.debugLineNum = 1048652;BA.debugLine="Starter10.Text = \"Starring: Olivia Colman, Dakota";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Olivia Colman, Dakota Johnson, Jessie Buckley"));
RDebugUtils.currentLine=1048653;
 //BA.debugLineNum = 1048653;BA.debugLine="Year10.Text = \"(2021)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2021)"));
RDebugUtils.currentLine=1048654;
 //BA.debugLineNum = 1048654;BA.debugLine="OverView10.Text = \"A solitary woman on vacation b";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity"));
RDebugUtils.currentLine=1048655;
 //BA.debugLineNum = 1048655;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1048656;
 //BA.debugLineNum = 1048656;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=1048658;
 //BA.debugLineNum = 1048658;BA.debugLine="ScrollView1.Panel.AddView(p, 0, 0, 200%x, 210%y)";
mostCurrent._scrollview1.getPanel().AddView((android.view.View)(mostCurrent._p.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=1048659;
 //BA.debugLineNum = 1048659;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=1048660;
 //BA.debugLineNum = 1048660;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="drama";
RDebugUtils.currentLine=1310720;
 //BA.debugLineNum = 1310720;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=1310722;
 //BA.debugLineNum = 1310722;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=1245184;
 //BA.debugLineNum = 1245184;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=1245186;
 //BA.debugLineNum = 1245186;BA.debugLine="End Sub";
return "";
}
public static String  _dramapage_click() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "dramapage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "dramapage_click", null));}
RDebugUtils.currentLine=1507328;
 //BA.debugLineNum = 1507328;BA.debugLine="Private Sub DramaPage_Click";
RDebugUtils.currentLine=1507330;
 //BA.debugLineNum = 1507330;BA.debugLine="End Sub";
return "";
}
public static String  _homepage_click() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "homepage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "homepage_click", null));}
RDebugUtils.currentLine=1441792;
 //BA.debugLineNum = 1441792;BA.debugLine="Private Sub HomePage_Click";
RDebugUtils.currentLine=1441793;
 //BA.debugLineNum = 1441793;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
RDebugUtils.currentLine=1441794;
 //BA.debugLineNum = 1441794;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=1441795;
 //BA.debugLineNum = 1441795;BA.debugLine="End Sub";
return "";
}
public static void  _panelmovie1_click() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie1_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie1_click", null); return;}
ResumableSub_PanelMovie1_Click rsub = new ResumableSub_PanelMovie1_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie1_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie1_Click(b4a.example.drama parent) {
this.parent = parent;
}
b4a.example.drama parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="drama";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=1638401;
 //BA.debugLineNum = 1638401;BA.debugLine="Try";
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
RDebugUtils.currentLine=1638402;
 //BA.debugLineNum = 1638402;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1638403;
 //BA.debugLineNum = 1638403;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "drama", "panelmovie1_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=1638404;
 //BA.debugLineNum = 1638404;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=1638405;
 //BA.debugLineNum = 1638405;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=1638406;
 //BA.debugLineNum = 1638406;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0079417/");
RDebugUtils.currentLine=1638407;
 //BA.debugLineNum = 1638407;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=1638408;
 //BA.debugLineNum = 1638408;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=1638412;
 //BA.debugLineNum = 1638412;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("51638412",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=1638413;
 //BA.debugLineNum = 1638413;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=1638416;
 //BA.debugLineNum = 1638416;BA.debugLine="End Sub";
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
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie10_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie10_click", null); return;}
ResumableSub_PanelMovie10_Click rsub = new ResumableSub_PanelMovie10_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie10_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie10_Click(b4a.example.drama parent) {
this.parent = parent;
}
b4a.example.drama parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="drama";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=2228225;
 //BA.debugLineNum = 2228225;BA.debugLine="Try";
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
RDebugUtils.currentLine=2228226;
 //BA.debugLineNum = 2228226;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2228227;
 //BA.debugLineNum = 2228227;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "drama", "panelmovie10_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=2228228;
 //BA.debugLineNum = 2228228;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=2228229;
 //BA.debugLineNum = 2228229;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=2228230;
 //BA.debugLineNum = 2228230;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.co";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt9100054/");
RDebugUtils.currentLine=2228231;
 //BA.debugLineNum = 2228231;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.an";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=2228232;
 //BA.debugLineNum = 2228232;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=2228235;
 //BA.debugLineNum = 2228235;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("52228235",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=2228236;
 //BA.debugLineNum = 2228236;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=2228239;
 //BA.debugLineNum = 2228239;BA.debugLine="End Sub";
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
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie2_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie2_click", null); return;}
ResumableSub_PanelMovie2_Click rsub = new ResumableSub_PanelMovie2_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie2_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie2_Click(b4a.example.drama parent) {
this.parent = parent;
}
b4a.example.drama parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="drama";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=1703937;
 //BA.debugLineNum = 1703937;BA.debugLine="Try";
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
RDebugUtils.currentLine=1703938;
 //BA.debugLineNum = 1703938;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1703939;
 //BA.debugLineNum = 1703939;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "drama", "panelmovie2_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=1703940;
 //BA.debugLineNum = 1703940;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=1703941;
 //BA.debugLineNum = 1703941;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=1703942;
 //BA.debugLineNum = 1703942;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.co";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt4034228/");
RDebugUtils.currentLine=1703943;
 //BA.debugLineNum = 1703943;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.an";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=1703944;
 //BA.debugLineNum = 1703944;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=1703947;
 //BA.debugLineNum = 1703947;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("51703947",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=1703948;
 //BA.debugLineNum = 1703948;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=1703951;
 //BA.debugLineNum = 1703951;BA.debugLine="End Sub";
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
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie3_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie3_click", null); return;}
ResumableSub_PanelMovie3_Click rsub = new ResumableSub_PanelMovie3_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie3_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie3_Click(b4a.example.drama parent) {
this.parent = parent;
}
b4a.example.drama parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="drama";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=1769473;
 //BA.debugLineNum = 1769473;BA.debugLine="Try";
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
RDebugUtils.currentLine=1769474;
 //BA.debugLineNum = 1769474;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1769475;
 //BA.debugLineNum = 1769475;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "drama", "panelmovie3_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=1769476;
 //BA.debugLineNum = 1769476;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=1769477;
 //BA.debugLineNum = 1769477;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=1769478;
 //BA.debugLineNum = 1769478;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.co";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt1560747/");
RDebugUtils.currentLine=1769479;
 //BA.debugLineNum = 1769479;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.an";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=1769480;
 //BA.debugLineNum = 1769480;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=1769483;
 //BA.debugLineNum = 1769483;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("51769483",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=1769484;
 //BA.debugLineNum = 1769484;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=1769487;
 //BA.debugLineNum = 1769487;BA.debugLine="End Sub";
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
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie4_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie4_click", null); return;}
ResumableSub_PanelMovie4_Click rsub = new ResumableSub_PanelMovie4_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie4_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie4_Click(b4a.example.drama parent) {
this.parent = parent;
}
b4a.example.drama parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="drama";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=1835009;
 //BA.debugLineNum = 1835009;BA.debugLine="Try";
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
RDebugUtils.currentLine=1835010;
 //BA.debugLineNum = 1835010;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1835011;
 //BA.debugLineNum = 1835011;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "drama", "panelmovie4_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=1835012;
 //BA.debugLineNum = 1835012;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=1835013;
 //BA.debugLineNum = 1835013;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=1835014;
 //BA.debugLineNum = 1835014;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.co";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0405159/");
RDebugUtils.currentLine=1835015;
 //BA.debugLineNum = 1835015;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.an";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=1835016;
 //BA.debugLineNum = 1835016;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=1835019;
 //BA.debugLineNum = 1835019;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("51835019",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=1835020;
 //BA.debugLineNum = 1835020;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=1835023;
 //BA.debugLineNum = 1835023;BA.debugLine="End Sub";
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
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie5_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie5_click", null); return;}
ResumableSub_PanelMovie5_Click rsub = new ResumableSub_PanelMovie5_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie5_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie5_Click(b4a.example.drama parent) {
this.parent = parent;
}
b4a.example.drama parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="drama";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=1900545;
 //BA.debugLineNum = 1900545;BA.debugLine="Try";
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
RDebugUtils.currentLine=1900546;
 //BA.debugLineNum = 1900546;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1900547;
 //BA.debugLineNum = 1900547;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "drama", "panelmovie5_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=1900548;
 //BA.debugLineNum = 1900548;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=1900549;
 //BA.debugLineNum = 1900549;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=1900550;
 //BA.debugLineNum = 1900550;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.co";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0112579/");
RDebugUtils.currentLine=1900551;
 //BA.debugLineNum = 1900551;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.an";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=1900552;
 //BA.debugLineNum = 1900552;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=1900555;
 //BA.debugLineNum = 1900555;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("51900555",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=1900556;
 //BA.debugLineNum = 1900556;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=1900559;
 //BA.debugLineNum = 1900559;BA.debugLine="End Sub";
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
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie6_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie6_click", null); return;}
ResumableSub_PanelMovie6_Click rsub = new ResumableSub_PanelMovie6_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie6_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie6_Click(b4a.example.drama parent) {
this.parent = parent;
}
b4a.example.drama parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="drama";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=1966081;
 //BA.debugLineNum = 1966081;BA.debugLine="Try";
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
RDebugUtils.currentLine=1966082;
 //BA.debugLineNum = 1966082;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1966083;
 //BA.debugLineNum = 1966083;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "drama", "panelmovie6_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=1966084;
 //BA.debugLineNum = 1966084;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=1966085;
 //BA.debugLineNum = 1966085;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=1966086;
 //BA.debugLineNum = 1966086;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.co";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0452623/");
RDebugUtils.currentLine=1966087;
 //BA.debugLineNum = 1966087;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.an";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=1966088;
 //BA.debugLineNum = 1966088;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=1966091;
 //BA.debugLineNum = 1966091;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("51966091",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=1966092;
 //BA.debugLineNum = 1966092;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=1966095;
 //BA.debugLineNum = 1966095;BA.debugLine="End Sub";
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
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie7_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie7_click", null); return;}
ResumableSub_PanelMovie7_Click rsub = new ResumableSub_PanelMovie7_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie7_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie7_Click(b4a.example.drama parent) {
this.parent = parent;
}
b4a.example.drama parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="drama";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=2031617;
 //BA.debugLineNum = 2031617;BA.debugLine="Try";
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
RDebugUtils.currentLine=2031618;
 //BA.debugLineNum = 2031618;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2031619;
 //BA.debugLineNum = 2031619;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "drama", "panelmovie7_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=2031620;
 //BA.debugLineNum = 2031620;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=2031621;
 //BA.debugLineNum = 2031621;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=2031622;
 //BA.debugLineNum = 2031622;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.co";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt2334873/");
RDebugUtils.currentLine=2031623;
 //BA.debugLineNum = 2031623;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.an";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=2031624;
 //BA.debugLineNum = 2031624;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=2031627;
 //BA.debugLineNum = 2031627;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("52031627",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=2031628;
 //BA.debugLineNum = 2031628;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=2031631;
 //BA.debugLineNum = 2031631;BA.debugLine="End Sub";
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
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie8_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie8_click", null); return;}
ResumableSub_PanelMovie8_Click rsub = new ResumableSub_PanelMovie8_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie8_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie8_Click(b4a.example.drama parent) {
this.parent = parent;
}
b4a.example.drama parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="drama";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=2097153;
 //BA.debugLineNum = 2097153;BA.debugLine="Try";
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
RDebugUtils.currentLine=2097154;
 //BA.debugLineNum = 2097154;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2097155;
 //BA.debugLineNum = 2097155;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "drama", "panelmovie8_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=2097156;
 //BA.debugLineNum = 2097156;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=2097157;
 //BA.debugLineNum = 2097157;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=2097158;
 //BA.debugLineNum = 2097158;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.co";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt1798709/");
RDebugUtils.currentLine=2097159;
 //BA.debugLineNum = 2097159;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.an";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=2097160;
 //BA.debugLineNum = 2097160;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=2097163;
 //BA.debugLineNum = 2097163;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("52097163",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=2097164;
 //BA.debugLineNum = 2097164;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=2097167;
 //BA.debugLineNum = 2097167;BA.debugLine="End Sub";
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
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie9_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie9_click", null); return;}
ResumableSub_PanelMovie9_Click rsub = new ResumableSub_PanelMovie9_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie9_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie9_Click(b4a.example.drama parent) {
this.parent = parent;
}
b4a.example.drama parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="drama";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=2162689;
 //BA.debugLineNum = 2162689;BA.debugLine="Try";
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
RDebugUtils.currentLine=2162690;
 //BA.debugLineNum = 2162690;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2162691;
 //BA.debugLineNum = 2162691;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "drama", "panelmovie9_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=2162692;
 //BA.debugLineNum = 2162692;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=2162693;
 //BA.debugLineNum = 2162693;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=2162694;
 //BA.debugLineNum = 2162694;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.co";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt2402927/");
RDebugUtils.currentLine=2162695;
 //BA.debugLineNum = 2162695;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.an";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=2162696;
 //BA.debugLineNum = 2162696;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=2162699;
 //BA.debugLineNum = 2162699;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("52162699",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=2162700;
 //BA.debugLineNum = 2162700;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=2162703;
 //BA.debugLineNum = 2162703;BA.debugLine="End Sub";
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
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "scifipage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "scifipage_click", null));}
RDebugUtils.currentLine=1376256;
 //BA.debugLineNum = 1376256;BA.debugLine="Private Sub SciFiPage_Click";
RDebugUtils.currentLine=1376257;
 //BA.debugLineNum = 1376257;BA.debugLine="StartActivity(SciFi)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._scifi.getObject()));
RDebugUtils.currentLine=1376258;
 //BA.debugLineNum = 1376258;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=1376259;
 //BA.debugLineNum = 1376259;BA.debugLine="End Sub";
return "";
}
public static String  _searchbtn_click() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchbtn_click", null));}
String _query = "";
String _userinput = "";
RDebugUtils.currentLine=1114112;
 //BA.debugLineNum = 1114112;BA.debugLine="Private Sub SearchBtn_Click";
RDebugUtils.currentLine=1114113;
 //BA.debugLineNum = 1114113;BA.debugLine="Dim query As String = SearchEngine.Text.ToLowerCa";
_query = mostCurrent._searchengine.getText().toLowerCase().trim();
RDebugUtils.currentLine=1114115;
 //BA.debugLineNum = 1114115;BA.debugLine="Dim UserInput As String = SearchEngine.Text";
_userinput = mostCurrent._searchengine.getText();
RDebugUtils.currentLine=1114120;
 //BA.debugLineNum = 1114120;BA.debugLine="If query.Contains(\"kramer\") Or query.Contains(\"kr";
if (_query.contains("kramer") || _query.contains("kramer vs kramer")) { 
RDebugUtils.currentLine=1114122;
 //BA.debugLineNum = 1114122;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Kramer vs. Kramer"));
RDebugUtils.currentLine=1114123;
 //BA.debugLineNum = 1114123;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
RDebugUtils.currentLine=1114124;
 //BA.debugLineNum = 1114124;BA.debugLine="Year1.Text = \"(1979)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1979)"));
RDebugUtils.currentLine=1114125;
 //BA.debugLineNum = 1114125;BA.debugLine="OverView1.Text = \"In this emotionally charged co";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
RDebugUtils.currentLine=1114126;
 //BA.debugLineNum = 1114126;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114127;
 //BA.debugLineNum = 1114127;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=1114129;
 //BA.debugLineNum = 1114129;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114130;
 //BA.debugLineNum = 1114130;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114131;
 //BA.debugLineNum = 1114131;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114132;
 //BA.debugLineNum = 1114132;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114133;
 //BA.debugLineNum = 1114133;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114135;
 //BA.debugLineNum = 1114135;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114137;
 //BA.debugLineNum = 1114137;BA.debugLine="Else If query.Contains(\"manchester by the Sea\") O";
if (_query.contains("manchester by the Sea") || _query.contains("manchester")) { 
RDebugUtils.currentLine=1114139;
 //BA.debugLineNum = 1114139;BA.debugLine="Drama1.Text = \"Manchester by the Sea\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Manchester by the Sea"));
RDebugUtils.currentLine=1114140;
 //BA.debugLineNum = 1114140;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
RDebugUtils.currentLine=1114141;
 //BA.debugLineNum = 1114141;BA.debugLine="Year1.Text = \"(2016)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=1114142;
 //BA.debugLineNum = 1114142;BA.debugLine="OverView1.Text = \"After the death of his brother";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
RDebugUtils.currentLine=1114143;
 //BA.debugLineNum = 1114143;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114144;
 //BA.debugLineNum = 1114144;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=1114147;
 //BA.debugLineNum = 1114147;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114148;
 //BA.debugLineNum = 1114148;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114149;
 //BA.debugLineNum = 1114149;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114150;
 //BA.debugLineNum = 1114150;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114151;
 //BA.debugLineNum = 1114151;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114153;
 //BA.debugLineNum = 1114153;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114155;
 //BA.debugLineNum = 1114155;BA.debugLine="Else If query.Contains(\"the master\") Or query.Con";
if (_query.contains("the master") || _query.contains("master")) { 
RDebugUtils.currentLine=1114157;
 //BA.debugLineNum = 1114157;BA.debugLine="Drama1.Text = \"The Master\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=1114158;
 //BA.debugLineNum = 1114158;BA.debugLine="Starter1.Text = \"Starring: Philip Seymour Hoffma";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=1114159;
 //BA.debugLineNum = 1114159;BA.debugLine="Year1.Text = \"(2012)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=1114160;
 //BA.debugLineNum = 1114160;BA.debugLine="OverView1.Text = \"A mentally unstable WWII veter";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=1114161;
 //BA.debugLineNum = 1114161;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114162;
 //BA.debugLineNum = 1114162;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=1114164;
 //BA.debugLineNum = 1114164;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114165;
 //BA.debugLineNum = 1114165;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114166;
 //BA.debugLineNum = 1114166;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114167;
 //BA.debugLineNum = 1114167;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114168;
 //BA.debugLineNum = 1114168;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114170;
 //BA.debugLineNum = 1114170;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114172;
 //BA.debugLineNum = 1114172;BA.debugLine="Else If query.Contains(\"million dollar\") Or query";
if (_query.contains("million dollar") || _query.contains("million") || _query.contains("million dollar baby")) { 
RDebugUtils.currentLine=1114174;
 //BA.debugLineNum = 1114174;BA.debugLine="Drama1.Text = \"Million Dollar Baby\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Million Dollar Baby"));
RDebugUtils.currentLine=1114175;
 //BA.debugLineNum = 1114175;BA.debugLine="Starter1.Text = \"Starring: Morgan Freeman, Hilar";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
RDebugUtils.currentLine=1114176;
 //BA.debugLineNum = 1114176;BA.debugLine="Year1.Text = \"(2004)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2004)"));
RDebugUtils.currentLine=1114177;
 //BA.debugLineNum = 1114177;BA.debugLine="OverView1.Text = \"A waitress with dreams of beco";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
RDebugUtils.currentLine=1114178;
 //BA.debugLineNum = 1114178;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114179;
 //BA.debugLineNum = 1114179;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=1114181;
 //BA.debugLineNum = 1114181;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114182;
 //BA.debugLineNum = 1114182;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114183;
 //BA.debugLineNum = 1114183;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114184;
 //BA.debugLineNum = 1114184;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114185;
 //BA.debugLineNum = 1114185;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114187;
 //BA.debugLineNum = 1114187;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114189;
 //BA.debugLineNum = 1114189;BA.debugLine="Else If query.Contains(\"the Bridges of madison co";
if (_query.contains("the Bridges of madison country") || _query.contains("Bridge") || _query.contains("bridges of madison")) { 
RDebugUtils.currentLine=1114191;
 //BA.debugLineNum = 1114191;BA.debugLine="Drama1.Text = \"The Bridges of Madison County\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Bridges of Madison County"));
RDebugUtils.currentLine=1114192;
 //BA.debugLineNum = 1114192;BA.debugLine="Starter1.Text = \"Starring: Clint Eastwood, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
RDebugUtils.currentLine=1114193;
 //BA.debugLineNum = 1114193;BA.debugLine="Year1.Text = \"(1995)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1995)"));
RDebugUtils.currentLine=1114194;
 //BA.debugLineNum = 1114194;BA.debugLine="OverView1.Text = \"A brief, passionate romance be";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
RDebugUtils.currentLine=1114195;
 //BA.debugLineNum = 1114195;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114196;
 //BA.debugLineNum = 1114196;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=1114198;
 //BA.debugLineNum = 1114198;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114199;
 //BA.debugLineNum = 1114199;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114200;
 //BA.debugLineNum = 1114200;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114201;
 //BA.debugLineNum = 1114201;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114202;
 //BA.debugLineNum = 1114202;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114204;
 //BA.debugLineNum = 1114204;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114206;
 //BA.debugLineNum = 1114206;BA.debugLine="Else If query.Contains(\"gone\") Or query.Contains(";
if (_query.contains("gone") || _query.contains("gone baby gone") || _query.contains("gone baby")) { 
RDebugUtils.currentLine=1114208;
 //BA.debugLineNum = 1114208;BA.debugLine="Drama1.Text = \"Gone Baby Gone\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Gone Baby Gone"));
RDebugUtils.currentLine=1114209;
 //BA.debugLineNum = 1114209;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=1114210;
 //BA.debugLineNum = 1114210;BA.debugLine="Year1.Text = \"(2007)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2007)"));
RDebugUtils.currentLine=1114211;
 //BA.debugLineNum = 1114211;BA.debugLine="OverView1.Text = \"In a tough Boston neighborhood";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
RDebugUtils.currentLine=1114213;
 //BA.debugLineNum = 1114213;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114214;
 //BA.debugLineNum = 1114214;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114215;
 //BA.debugLineNum = 1114215;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114216;
 //BA.debugLineNum = 1114216;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114217;
 //BA.debugLineNum = 1114217;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114219;
 //BA.debugLineNum = 1114219;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114221;
 //BA.debugLineNum = 1114221;BA.debugLine="Else If query.Contains(\"blue\") Or query.Contains(";
if (_query.contains("blue") || _query.contains("blue jasmine")) { 
RDebugUtils.currentLine=1114224;
 //BA.debugLineNum = 1114224;BA.debugLine="Drama1.Text = \"Blue Jasmine\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=1114225;
 //BA.debugLineNum = 1114225;BA.debugLine="Starter1.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=1114226;
 //BA.debugLineNum = 1114226;BA.debugLine="Year1.Text = \"(2013)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1114227;
 //BA.debugLineNum = 1114227;BA.debugLine="OverView1.Text = \"After losing her fortune and s";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=1114228;
 //BA.debugLineNum = 1114228;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114229;
 //BA.debugLineNum = 1114229;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=1114231;
 //BA.debugLineNum = 1114231;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114232;
 //BA.debugLineNum = 1114232;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114233;
 //BA.debugLineNum = 1114233;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114234;
 //BA.debugLineNum = 1114234;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114235;
 //BA.debugLineNum = 1114235;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114237;
 //BA.debugLineNum = 1114237;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114239;
 //BA.debugLineNum = 1114239;BA.debugLine="Else If query.Contains(\"her\") Then";
if (_query.contains("her")) { 
RDebugUtils.currentLine=1114241;
 //BA.debugLineNum = 1114241;BA.debugLine="Drama1.Text = \"Her\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Her"));
RDebugUtils.currentLine=1114242;
 //BA.debugLineNum = 1114242;BA.debugLine="Starter1.Text = \"Starring: Joaquin Phoenix, Roon";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
RDebugUtils.currentLine=1114243;
 //BA.debugLineNum = 1114243;BA.debugLine="Year1.Text = \"(2013)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1114244;
 //BA.debugLineNum = 1114244;BA.debugLine="OverView1.Text = \"In a near-future Los Angeles,";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
RDebugUtils.currentLine=1114245;
 //BA.debugLineNum = 1114245;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114246;
 //BA.debugLineNum = 1114246;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=1114248;
 //BA.debugLineNum = 1114248;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114249;
 //BA.debugLineNum = 1114249;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114250;
 //BA.debugLineNum = 1114250;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114251;
 //BA.debugLineNum = 1114251;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114252;
 //BA.debugLineNum = 1114252;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114254;
 //BA.debugLineNum = 1114254;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114256;
 //BA.debugLineNum = 1114256;BA.debugLine="Else If query.Contains(\"carol\") Or query.Contains";
if (_query.contains("carol") || _query.contains("sarah paulson") || _query.contains("sarah") || _query.contains("paulson")) { 
RDebugUtils.currentLine=1114258;
 //BA.debugLineNum = 1114258;BA.debugLine="Drama1.Text = \"Carol\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=1114259;
 //BA.debugLineNum = 1114259;BA.debugLine="Starter1.Text = \"Starring: Rooney Mara, Cate Bla";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=1114260;
 //BA.debugLineNum = 1114260;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=1114261;
 //BA.debugLineNum = 1114261;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=1114262;
 //BA.debugLineNum = 1114262;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114263;
 //BA.debugLineNum = 1114263;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=1114265;
 //BA.debugLineNum = 1114265;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114266;
 //BA.debugLineNum = 1114266;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114267;
 //BA.debugLineNum = 1114267;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114268;
 //BA.debugLineNum = 1114268;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114269;
 //BA.debugLineNum = 1114269;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114271;
 //BA.debugLineNum = 1114271;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114273;
 //BA.debugLineNum = 1114273;BA.debugLine="Else If query.Contains(\"the lost daugther\") Or qu";
if (_query.contains("the lost daugther") || _query.contains("lost daughter") || _query.contains("lost")) { 
RDebugUtils.currentLine=1114275;
 //BA.debugLineNum = 1114275;BA.debugLine="Drama1.Text = \"The Lost Daughter\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Lost Daughter"));
RDebugUtils.currentLine=1114276;
 //BA.debugLineNum = 1114276;BA.debugLine="Starter1.Text = \"Starring: Olivia Colman, Dakota";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Olivia Colman, Dakota Johnson, Jessie Buckley"));
RDebugUtils.currentLine=1114277;
 //BA.debugLineNum = 1114277;BA.debugLine="Year1.Text = \"(2021)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2021)"));
RDebugUtils.currentLine=1114278;
 //BA.debugLineNum = 1114278;BA.debugLine="OverView1.Text = \"A solitary woman on vacation b";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity"));
RDebugUtils.currentLine=1114279;
 //BA.debugLineNum = 1114279;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114280;
 //BA.debugLineNum = 1114280;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=1114282;
 //BA.debugLineNum = 1114282;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114283;
 //BA.debugLineNum = 1114283;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114284;
 //BA.debugLineNum = 1114284;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114285;
 //BA.debugLineNum = 1114285;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114286;
 //BA.debugLineNum = 1114286;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114288;
 //BA.debugLineNum = 1114288;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114289;
 //BA.debugLineNum = 1114289;BA.debugLine="Else If query.Contains(\"doubt\") Then";
if (_query.contains("doubt")) { 
RDebugUtils.currentLine=1114290;
 //BA.debugLineNum = 1114290;BA.debugLine="Drama1.Text = \"Doubt\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Doubt"));
RDebugUtils.currentLine=1114291;
 //BA.debugLineNum = 1114291;BA.debugLine="Starter1.Text = \"Starring: Meryl Streep, Philip";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Meryl Streep, Philip Seymour Hoffman, Amy Adams"));
RDebugUtils.currentLine=1114292;
 //BA.debugLineNum = 1114292;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=1114293;
 //BA.debugLineNum = 1114293;BA.debugLine="OverView1.Text = \"In a Catholic school in the Br";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a Catholic school in the Bronx, a strict nun becomes suspicious of a priest’s relationship with a student. As she digs deeper, the lines between truth and suspicion blur, leaving everyone in moral and emotional limbo."));
RDebugUtils.currentLine=1114294;
 //BA.debugLineNum = 1114294;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114295;
 //BA.debugLineNum = 1114295;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"doubt.jpg").getObject()));
RDebugUtils.currentLine=1114297;
 //BA.debugLineNum = 1114297;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114298;
 //BA.debugLineNum = 1114298;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114299;
 //BA.debugLineNum = 1114299;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114300;
 //BA.debugLineNum = 1114300;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114301;
 //BA.debugLineNum = 1114301;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114303;
 //BA.debugLineNum = 1114303;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114306;
 //BA.debugLineNum = 1114306;BA.debugLine="Else If query.Contains(\"meryl streep\") Or query.C";
if (_query.contains("meryl streep") || _query.contains("meryl") || _query.contains("streep")) { 
RDebugUtils.currentLine=1114307;
 //BA.debugLineNum = 1114307;BA.debugLine="Drama1.Text = \"The Bridges of Madison County\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Bridges of Madison County"));
RDebugUtils.currentLine=1114308;
 //BA.debugLineNum = 1114308;BA.debugLine="Starter1.Text = \"Starring: Clint Eastwood, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
RDebugUtils.currentLine=1114309;
 //BA.debugLineNum = 1114309;BA.debugLine="Year1.Text = \"(1995)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1995)"));
RDebugUtils.currentLine=1114310;
 //BA.debugLineNum = 1114310;BA.debugLine="OverView1.Text = \"A brief, passionate romance be";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
RDebugUtils.currentLine=1114311;
 //BA.debugLineNum = 1114311;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114312;
 //BA.debugLineNum = 1114312;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=1114314;
 //BA.debugLineNum = 1114314;BA.debugLine="Drama2.Text = \"Doubt\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Doubt"));
RDebugUtils.currentLine=1114315;
 //BA.debugLineNum = 1114315;BA.debugLine="Starter2.Text = \"Starring: Meryl Streep, Philip";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Meryl Streep, Philip Seymour Hoffman, Amy Adams"));
RDebugUtils.currentLine=1114316;
 //BA.debugLineNum = 1114316;BA.debugLine="Year2.Text = \"(2008)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=1114317;
 //BA.debugLineNum = 1114317;BA.debugLine="OverView2.Text = \"In a Catholic school in the Br";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a Catholic school in the Bronx, a strict nun becomes suspicious of a priest’s relationship with a student. As she digs deeper, the lines between truth and suspicion blur, leaving everyone in moral and emotional limbo."));
RDebugUtils.currentLine=1114318;
 //BA.debugLineNum = 1114318;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114319;
 //BA.debugLineNum = 1114319;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"doubt.jpg").getObject()));
RDebugUtils.currentLine=1114322;
 //BA.debugLineNum = 1114322;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114323;
 //BA.debugLineNum = 1114323;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114324;
 //BA.debugLineNum = 1114324;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114325;
 //BA.debugLineNum = 1114325;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114327;
 //BA.debugLineNum = 1114327;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114328;
 //BA.debugLineNum = 1114328;BA.debugLine="Else If query.Contains(\"casey\") Or query.Contains";
if (_query.contains("casey") || _query.contains("casey affleck")) { 
RDebugUtils.currentLine=1114329;
 //BA.debugLineNum = 1114329;BA.debugLine="Drama1.Text = \"Gone Baby Gone\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Gone Baby Gone"));
RDebugUtils.currentLine=1114330;
 //BA.debugLineNum = 1114330;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=1114331;
 //BA.debugLineNum = 1114331;BA.debugLine="Year1.Text = \"(2007)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2007)"));
RDebugUtils.currentLine=1114332;
 //BA.debugLineNum = 1114332;BA.debugLine="OverView1.Text = \"In a tough Boston neighborhood";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
RDebugUtils.currentLine=1114333;
 //BA.debugLineNum = 1114333;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114334;
 //BA.debugLineNum = 1114334;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=1114336;
 //BA.debugLineNum = 1114336;BA.debugLine="Drama2.Text = \"Manchester by the Sea\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Manchester by the Sea"));
RDebugUtils.currentLine=1114337;
 //BA.debugLineNum = 1114337;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
RDebugUtils.currentLine=1114338;
 //BA.debugLineNum = 1114338;BA.debugLine="Year2.Text = \"(2016)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=1114339;
 //BA.debugLineNum = 1114339;BA.debugLine="OverView2.Text = \"After the death of his brother";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
RDebugUtils.currentLine=1114340;
 //BA.debugLineNum = 1114340;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114341;
 //BA.debugLineNum = 1114341;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=1114343;
 //BA.debugLineNum = 1114343;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114344;
 //BA.debugLineNum = 1114344;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114345;
 //BA.debugLineNum = 1114345;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114346;
 //BA.debugLineNum = 1114346;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114348;
 //BA.debugLineNum = 1114348;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114350;
 //BA.debugLineNum = 1114350;BA.debugLine="Else If query.Contains(\"joaquin\") Or query.Contai";
if (_query.contains("joaquin") || _query.contains("joaquin phoenix") || _query.contains("amy adams") || _query.contains("amy")) { 
RDebugUtils.currentLine=1114351;
 //BA.debugLineNum = 1114351;BA.debugLine="Drama1.Text = \"The Master\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=1114352;
 //BA.debugLineNum = 1114352;BA.debugLine="Starter1.Text = \"Starring: Philip Seymour Hoffma";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=1114353;
 //BA.debugLineNum = 1114353;BA.debugLine="Year1.Text = \"(2012)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=1114354;
 //BA.debugLineNum = 1114354;BA.debugLine="OverView1.Text = \"A mentally unstable WWII veter";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=1114355;
 //BA.debugLineNum = 1114355;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114356;
 //BA.debugLineNum = 1114356;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=1114358;
 //BA.debugLineNum = 1114358;BA.debugLine="Drama2.Text = \"Her\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Her"));
RDebugUtils.currentLine=1114359;
 //BA.debugLineNum = 1114359;BA.debugLine="Starter2.Text = \"Starring: Joaquin Phoenix, Roon";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
RDebugUtils.currentLine=1114360;
 //BA.debugLineNum = 1114360;BA.debugLine="Year2.Text = \"(2013)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1114361;
 //BA.debugLineNum = 1114361;BA.debugLine="OverView2.Text = \"In a near-future Los Angeles,";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
RDebugUtils.currentLine=1114362;
 //BA.debugLineNum = 1114362;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114363;
 //BA.debugLineNum = 1114363;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=1114365;
 //BA.debugLineNum = 1114365;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114366;
 //BA.debugLineNum = 1114366;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114367;
 //BA.debugLineNum = 1114367;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114368;
 //BA.debugLineNum = 1114368;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114370;
 //BA.debugLineNum = 1114370;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114372;
 //BA.debugLineNum = 1114372;BA.debugLine="Else If query.Contains(\"cate blanchett\") Or query";
if (_query.contains("cate blanchett") || _query.contains("cate")) { 
RDebugUtils.currentLine=1114373;
 //BA.debugLineNum = 1114373;BA.debugLine="Drama1.Text = \"Carol\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=1114374;
 //BA.debugLineNum = 1114374;BA.debugLine="Starter1.Text = \"Starring: Rooney Mara, Cate Bla";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=1114375;
 //BA.debugLineNum = 1114375;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=1114376;
 //BA.debugLineNum = 1114376;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=1114377;
 //BA.debugLineNum = 1114377;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114378;
 //BA.debugLineNum = 1114378;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=1114380;
 //BA.debugLineNum = 1114380;BA.debugLine="Drama2.Text = \"Blue Jasmine\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=1114381;
 //BA.debugLineNum = 1114381;BA.debugLine="Starter2.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=1114382;
 //BA.debugLineNum = 1114382;BA.debugLine="Year2.Text = \"(2013)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1114383;
 //BA.debugLineNum = 1114383;BA.debugLine="OverView2.Text = \"After losing her fortune and s";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=1114384;
 //BA.debugLineNum = 1114384;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114385;
 //BA.debugLineNum = 1114385;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=1114387;
 //BA.debugLineNum = 1114387;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114388;
 //BA.debugLineNum = 1114388;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114389;
 //BA.debugLineNum = 1114389;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114390;
 //BA.debugLineNum = 1114390;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114392;
 //BA.debugLineNum = 1114392;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114394;
 //BA.debugLineNum = 1114394;BA.debugLine="Else If query.Contains(\"morgan freeman\") Or query";
if (_query.contains("morgan freeman") || _query.contains("morgan")) { 
RDebugUtils.currentLine=1114395;
 //BA.debugLineNum = 1114395;BA.debugLine="Drama1.Text = \"Million Dollar Baby\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Million Dollar Baby"));
RDebugUtils.currentLine=1114396;
 //BA.debugLineNum = 1114396;BA.debugLine="Starter1.Text = \"Starring: Morgan Freeman, Hilar";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
RDebugUtils.currentLine=1114397;
 //BA.debugLineNum = 1114397;BA.debugLine="Year1.Text = \"(2004)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2004)"));
RDebugUtils.currentLine=1114398;
 //BA.debugLineNum = 1114398;BA.debugLine="OverView1.Text = \"A waitress with dreams of beco";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
RDebugUtils.currentLine=1114399;
 //BA.debugLineNum = 1114399;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114400;
 //BA.debugLineNum = 1114400;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=1114402;
 //BA.debugLineNum = 1114402;BA.debugLine="Drama2.Text = \"Gone Baby Gone\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Gone Baby Gone"));
RDebugUtils.currentLine=1114403;
 //BA.debugLineNum = 1114403;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=1114404;
 //BA.debugLineNum = 1114404;BA.debugLine="Year2.Text = \"(2007)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2007)"));
RDebugUtils.currentLine=1114405;
 //BA.debugLineNum = 1114405;BA.debugLine="OverView2.Text = \"In a tough Boston neighborhood";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
RDebugUtils.currentLine=1114406;
 //BA.debugLineNum = 1114406;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114407;
 //BA.debugLineNum = 1114407;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=1114409;
 //BA.debugLineNum = 1114409;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114410;
 //BA.debugLineNum = 1114410;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114411;
 //BA.debugLineNum = 1114411;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114412;
 //BA.debugLineNum = 1114412;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114414;
 //BA.debugLineNum = 1114414;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114416;
 //BA.debugLineNum = 1114416;BA.debugLine="Else If query.Contains(\"clint eastwood\") Or query";
if (_query.contains("clint eastwood") || _query.contains("clint") || _query.contains("eastwood")) { 
RDebugUtils.currentLine=1114417;
 //BA.debugLineNum = 1114417;BA.debugLine="Drama1.Text = \"Million Dollar Baby\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Million Dollar Baby"));
RDebugUtils.currentLine=1114418;
 //BA.debugLineNum = 1114418;BA.debugLine="Starter1.Text = \"Starring: Morgan Freeman, Hilar";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
RDebugUtils.currentLine=1114419;
 //BA.debugLineNum = 1114419;BA.debugLine="Year1.Text = \"(2004)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2004)"));
RDebugUtils.currentLine=1114420;
 //BA.debugLineNum = 1114420;BA.debugLine="OverView1.Text = \"A waitress with dreams of beco";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
RDebugUtils.currentLine=1114421;
 //BA.debugLineNum = 1114421;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114422;
 //BA.debugLineNum = 1114422;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=1114424;
 //BA.debugLineNum = 1114424;BA.debugLine="Drama2.Text = \"The Bridges of Madison Country\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("The Bridges of Madison Country"));
RDebugUtils.currentLine=1114425;
 //BA.debugLineNum = 1114425;BA.debugLine="Starter2.Text = \"Starring: Clint Eastwood, Meryl";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
RDebugUtils.currentLine=1114426;
 //BA.debugLineNum = 1114426;BA.debugLine="Year2.Text = \"(1995)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(1995)"));
RDebugUtils.currentLine=1114427;
 //BA.debugLineNum = 1114427;BA.debugLine="OverView2.Text = \"A brief, passionate romance be";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
RDebugUtils.currentLine=1114428;
 //BA.debugLineNum = 1114428;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114429;
 //BA.debugLineNum = 1114429;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=1114431;
 //BA.debugLineNum = 1114431;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114432;
 //BA.debugLineNum = 1114432;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114433;
 //BA.debugLineNum = 1114433;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114434;
 //BA.debugLineNum = 1114434;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114436;
 //BA.debugLineNum = 1114436;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114438;
 //BA.debugLineNum = 1114438;BA.debugLine="Else If query.Contains(\"amy Adams\") Or query.Cont";
if (_query.contains("amy Adams") || _query.contains("amy") || _query.contains("adams")) { 
RDebugUtils.currentLine=1114440;
 //BA.debugLineNum = 1114440;BA.debugLine="Drama2.Text = \"The Master\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=1114441;
 //BA.debugLineNum = 1114441;BA.debugLine="Starter2.Text = \"Starring: Philip Seymour Hoffma";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=1114442;
 //BA.debugLineNum = 1114442;BA.debugLine="Year2.Text = \"(2012)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=1114443;
 //BA.debugLineNum = 1114443;BA.debugLine="OverView2.Text = \"A mentally unstable WWII veter";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=1114444;
 //BA.debugLineNum = 1114444;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114445;
 //BA.debugLineNum = 1114445;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=1114447;
 //BA.debugLineNum = 1114447;BA.debugLine="Drama3.Text = \"Her\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("Her"));
RDebugUtils.currentLine=1114448;
 //BA.debugLineNum = 1114448;BA.debugLine="Starter3.Text = \"Starring: Joaquin Phoenix, Roon";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
RDebugUtils.currentLine=1114449;
 //BA.debugLineNum = 1114449;BA.debugLine="Year3.Text = \"(2013)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1114450;
 //BA.debugLineNum = 1114450;BA.debugLine="OverView3.Text = \"In a near-future Los Angeles,";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
RDebugUtils.currentLine=1114451;
 //BA.debugLineNum = 1114451;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114452;
 //BA.debugLineNum = 1114452;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=1114454;
 //BA.debugLineNum = 1114454;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114455;
 //BA.debugLineNum = 1114455;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114456;
 //BA.debugLineNum = 1114456;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114457;
 //BA.debugLineNum = 1114457;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114459;
 //BA.debugLineNum = 1114459;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114460;
 //BA.debugLineNum = 1114460;BA.debugLine="Else If query.Contains(\"rooney mara\") Or query.Co";
if (_query.contains("rooney mara") || _query.contains("rooney") || _query.contains("mara") || _query.contains("sarah") || _query.contains("sarah paulson") || _query.contains("paulson")) { 
RDebugUtils.currentLine=1114461;
 //BA.debugLineNum = 1114461;BA.debugLine="Drama1.Text = \"Carol\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=1114462;
 //BA.debugLineNum = 1114462;BA.debugLine="Starter1.Text = \"Starring: Rooney Mara, Cate Bla";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=1114463;
 //BA.debugLineNum = 1114463;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=1114464;
 //BA.debugLineNum = 1114464;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=1114465;
 //BA.debugLineNum = 1114465;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114466;
 //BA.debugLineNum = 1114466;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=1114468;
 //BA.debugLineNum = 1114468;BA.debugLine="Drama2.Text = \"Blue Jasmine\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=1114469;
 //BA.debugLineNum = 1114469;BA.debugLine="Starter2.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=1114470;
 //BA.debugLineNum = 1114470;BA.debugLine="Year2.Text = \"(2013)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1114471;
 //BA.debugLineNum = 1114471;BA.debugLine="OverView2.Text = \"After losing her fortune and s";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=1114472;
 //BA.debugLineNum = 1114472;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114473;
 //BA.debugLineNum = 1114473;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=1114475;
 //BA.debugLineNum = 1114475;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114476;
 //BA.debugLineNum = 1114476;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114477;
 //BA.debugLineNum = 1114477;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114478;
 //BA.debugLineNum = 1114478;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114480;
 //BA.debugLineNum = 1114480;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114483;
 //BA.debugLineNum = 1114483;BA.debugLine="Else If query.Contains(\"olvia coloman\") Or query.";
if (_query.contains("olvia coloman") || _query.contains("dakota johnson") || _query.contains("jessie buckley") || _query.contains("olvia") || _query.contains("jessie ") || _query.contains("dakota") || _query.contains("buckley") || _query.contains("coloman") || _query.contains("johnson")) { 
RDebugUtils.currentLine=1114484;
 //BA.debugLineNum = 1114484;BA.debugLine="Drama1.Text = \"Carol\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=1114485;
 //BA.debugLineNum = 1114485;BA.debugLine="Starter1.Text = \"Starring: Rooney Mara, Cate Bla";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=1114486;
 //BA.debugLineNum = 1114486;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=1114487;
 //BA.debugLineNum = 1114487;BA.debugLine="OverView1.Text = \"A solitary woman on vacation b";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity."));
RDebugUtils.currentLine=1114488;
 //BA.debugLineNum = 1114488;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114489;
 //BA.debugLineNum = 1114489;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=1114491;
 //BA.debugLineNum = 1114491;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114492;
 //BA.debugLineNum = 1114492;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114493;
 //BA.debugLineNum = 1114493;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114494;
 //BA.debugLineNum = 1114494;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114496;
 //BA.debugLineNum = 1114496;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114498;
 //BA.debugLineNum = 1114498;BA.debugLine="Else If query.Contains(\"sally hawkins\") Or query.";
if (_query.contains("sally hawkins") || _query.contains("alec baldwin") || _query.contains("sally") || _query.contains("haswkins") || _query.contains("alec") || _query.contains("baldwin")) { 
RDebugUtils.currentLine=1114499;
 //BA.debugLineNum = 1114499;BA.debugLine="Drama1.Text = \"Blue Jasmine\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=1114500;
 //BA.debugLineNum = 1114500;BA.debugLine="Starter1.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=1114501;
 //BA.debugLineNum = 1114501;BA.debugLine="Year1.Text = \"(2013)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1114502;
 //BA.debugLineNum = 1114502;BA.debugLine="OverView1.Text = \"After losing her fortune and s";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=1114503;
 //BA.debugLineNum = 1114503;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114504;
 //BA.debugLineNum = 1114504;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=1114506;
 //BA.debugLineNum = 1114506;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114507;
 //BA.debugLineNum = 1114507;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114508;
 //BA.debugLineNum = 1114508;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114509;
 //BA.debugLineNum = 1114509;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114510;
 //BA.debugLineNum = 1114510;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114512;
 //BA.debugLineNum = 1114512;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114513;
 //BA.debugLineNum = 1114513;BA.debugLine="Else if query.Contains(\"michelle williams\") Or qu";
if (_query.contains("michelle williams") || _query.contains("lucas hedges") || _query.contains("michelle") || _query.contains("williams") || _query.contains("lucas") || _query.contains("hedges")) { 
RDebugUtils.currentLine=1114514;
 //BA.debugLineNum = 1114514;BA.debugLine="Drama1.Text = \"Manchester by the Sea\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Manchester by the Sea"));
RDebugUtils.currentLine=1114515;
 //BA.debugLineNum = 1114515;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
RDebugUtils.currentLine=1114516;
 //BA.debugLineNum = 1114516;BA.debugLine="Year1.Text = \"(2016)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=1114517;
 //BA.debugLineNum = 1114517;BA.debugLine="OverView1.Text = \"After the death of his brother";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
RDebugUtils.currentLine=1114518;
 //BA.debugLineNum = 1114518;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114519;
 //BA.debugLineNum = 1114519;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=1114521;
 //BA.debugLineNum = 1114521;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114522;
 //BA.debugLineNum = 1114522;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114523;
 //BA.debugLineNum = 1114523;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114524;
 //BA.debugLineNum = 1114524;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114525;
 //BA.debugLineNum = 1114525;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114527;
 //BA.debugLineNum = 1114527;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114528;
 //BA.debugLineNum = 1114528;BA.debugLine="Else if query.Contains(\"dustin hoffman\") Or query";
if (_query.contains("dustin hoffman") || _query.contains("dustin") || _query.contains("justin henry") || _query.contains("justin") || _query.contains("henry")) { 
RDebugUtils.currentLine=1114529;
 //BA.debugLineNum = 1114529;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Kramer vs. Kramer"));
RDebugUtils.currentLine=1114530;
 //BA.debugLineNum = 1114530;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
RDebugUtils.currentLine=1114531;
 //BA.debugLineNum = 1114531;BA.debugLine="Year1.Text = \"(1979)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1979)"));
RDebugUtils.currentLine=1114532;
 //BA.debugLineNum = 1114532;BA.debugLine="OverView1.Text = \"In this emotionally charged co";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
RDebugUtils.currentLine=1114533;
 //BA.debugLineNum = 1114533;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114534;
 //BA.debugLineNum = 1114534;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=1114536;
 //BA.debugLineNum = 1114536;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114537;
 //BA.debugLineNum = 1114537;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114538;
 //BA.debugLineNum = 1114538;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114539;
 //BA.debugLineNum = 1114539;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114540;
 //BA.debugLineNum = 1114540;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114542;
 //BA.debugLineNum = 1114542;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114543;
 //BA.debugLineNum = 1114543;BA.debugLine="Else If query.Contains(\"philip seymour hoffman\")";
if (_query.contains("philip seymour hoffman") || _query.contains("philip") || _query.contains("seymour")) { 
RDebugUtils.currentLine=1114544;
 //BA.debugLineNum = 1114544;BA.debugLine="Drama1.Text = \"Doubt\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Doubt"));
RDebugUtils.currentLine=1114545;
 //BA.debugLineNum = 1114545;BA.debugLine="Starter1.Text = \"Starring: Meryl Streep, Philip";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Meryl Streep, Philip Seymour Hoffman, Amy Adams"));
RDebugUtils.currentLine=1114546;
 //BA.debugLineNum = 1114546;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=1114547;
 //BA.debugLineNum = 1114547;BA.debugLine="OverView1.Text = \"In a Catholic school in the Br";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a Catholic school in the Bronx, a strict nun becomes suspicious of a priest’s relationship with a student. As she digs deeper, the lines between truth and suspicion blur, leaving everyone in moral and emotional limbo."));
RDebugUtils.currentLine=1114548;
 //BA.debugLineNum = 1114548;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114549;
 //BA.debugLineNum = 1114549;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"doubt.jpg").getObject()));
RDebugUtils.currentLine=1114552;
 //BA.debugLineNum = 1114552;BA.debugLine="Drama2.Text = \"The Master\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=1114553;
 //BA.debugLineNum = 1114553;BA.debugLine="Starter2.Text = \"Starring: Philip Seymour Hoffma";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=1114554;
 //BA.debugLineNum = 1114554;BA.debugLine="Year2.Text = \"(2012)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=1114555;
 //BA.debugLineNum = 1114555;BA.debugLine="OverView2.Text = \"A mentally unstable WWII veter";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=1114556;
 //BA.debugLineNum = 1114556;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114557;
 //BA.debugLineNum = 1114557;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=1114559;
 //BA.debugLineNum = 1114559;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114560;
 //BA.debugLineNum = 1114560;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114561;
 //BA.debugLineNum = 1114561;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114562;
 //BA.debugLineNum = 1114562;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114564;
 //BA.debugLineNum = 1114564;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114566;
 //BA.debugLineNum = 1114566;BA.debugLine="Else If query.Contains(\"hoffman\") Then";
if (_query.contains("hoffman")) { 
RDebugUtils.currentLine=1114567;
 //BA.debugLineNum = 1114567;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Kramer vs. Kramer"));
RDebugUtils.currentLine=1114568;
 //BA.debugLineNum = 1114568;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
RDebugUtils.currentLine=1114569;
 //BA.debugLineNum = 1114569;BA.debugLine="Year1.Text = \"(1979)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1979)"));
RDebugUtils.currentLine=1114570;
 //BA.debugLineNum = 1114570;BA.debugLine="OverView1.Text = \"In this emotionally charged co";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
RDebugUtils.currentLine=1114571;
 //BA.debugLineNum = 1114571;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114572;
 //BA.debugLineNum = 1114572;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=1114574;
 //BA.debugLineNum = 1114574;BA.debugLine="Drama2.Text = \"Doubt\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Doubt"));
RDebugUtils.currentLine=1114575;
 //BA.debugLineNum = 1114575;BA.debugLine="Starter2.Text = \"Starring: Meryl Streep, Philip";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Meryl Streep, Philip Seymour Hoffman, Amy Adams"));
RDebugUtils.currentLine=1114576;
 //BA.debugLineNum = 1114576;BA.debugLine="Year2.Text = \"(2008)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=1114577;
 //BA.debugLineNum = 1114577;BA.debugLine="OverView2.Text = \"In a Catholic school in the Br";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a Catholic school in the Bronx, a strict nun becomes suspicious of a priest’s relationship with a student. As she digs deeper, the lines between truth and suspicion blur, leaving everyone in moral and emotional limbo."));
RDebugUtils.currentLine=1114578;
 //BA.debugLineNum = 1114578;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1114579;
 //BA.debugLineNum = 1114579;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"doubt.jpg").getObject()));
RDebugUtils.currentLine=1114581;
 //BA.debugLineNum = 1114581;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114582;
 //BA.debugLineNum = 1114582;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114583;
 //BA.debugLineNum = 1114583;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114584;
 //BA.debugLineNum = 1114584;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114586;
 //BA.debugLineNum = 1114586;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else {
RDebugUtils.currentLine=1114589;
 //BA.debugLineNum = 1114589;BA.debugLine="MsgboxAsync(\"No results found for\" & \" \"\"\" & Use";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("No results found for"+" \""+_userinput+"\""),BA.ObjectToCharSequence(""),processBA);
 }}}}}}}}}}}}}}}}}}}}}}}}}
;
RDebugUtils.currentLine=1114594;
 //BA.debugLineNum = 1114594;BA.debugLine="p.Width = 100%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=1114595;
 //BA.debugLineNum = 1114595;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=1114596;
 //BA.debugLineNum = 1114596;BA.debugLine="End Sub";
return "";
}
public static String  _searchengine_textchanged(String _old,String _new) throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchengine_textchanged", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchengine_textchanged", new Object[] {_old,_new}));}
String _query = "";
RDebugUtils.currentLine=1179648;
 //BA.debugLineNum = 1179648;BA.debugLine="Sub SearchEngine_TextChanged (Old As String, New A";
RDebugUtils.currentLine=1179649;
 //BA.debugLineNum = 1179649;BA.debugLine="Dim query As String = New.ToLowerCase.Trim";
_query = _new.toLowerCase().trim();
RDebugUtils.currentLine=1179651;
 //BA.debugLineNum = 1179651;BA.debugLine="If query = \"\" Then";
if ((_query).equals("")) { 
RDebugUtils.currentLine=1179653;
 //BA.debugLineNum = 1179653;BA.debugLine="p.Height = 210%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=1179654;
 //BA.debugLineNum = 1179654;BA.debugLine="p.Width = 200%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA));
RDebugUtils.currentLine=1179655;
 //BA.debugLineNum = 1179655;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=1179657;
 //BA.debugLineNum = 1179657;BA.debugLine="PanelMovie1.Visible = True";
mostCurrent._panelmovie1.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179658;
 //BA.debugLineNum = 1179658;BA.debugLine="PanelMovie2.Visible = True";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179659;
 //BA.debugLineNum = 1179659;BA.debugLine="PanelMovie3.Visible = True";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179660;
 //BA.debugLineNum = 1179660;BA.debugLine="PanelMovie4.Visible = True";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179661;
 //BA.debugLineNum = 1179661;BA.debugLine="PanelMovie5.Visible = True";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179662;
 //BA.debugLineNum = 1179662;BA.debugLine="PanelMovie6.Visible = True";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179663;
 //BA.debugLineNum = 1179663;BA.debugLine="PanelMovie7.Visible = True";
mostCurrent._panelmovie7.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179664;
 //BA.debugLineNum = 1179664;BA.debugLine="PanelMovie8.Visible = True";
mostCurrent._panelmovie8.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179665;
 //BA.debugLineNum = 1179665;BA.debugLine="PanelMovie9.Visible = True";
mostCurrent._panelmovie9.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179666;
 //BA.debugLineNum = 1179666;BA.debugLine="PanelMovie10.Visible = True";
mostCurrent._panelmovie10.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179668;
 //BA.debugLineNum = 1179668;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Kramer vs. Kramer"));
RDebugUtils.currentLine=1179669;
 //BA.debugLineNum = 1179669;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
RDebugUtils.currentLine=1179670;
 //BA.debugLineNum = 1179670;BA.debugLine="Year1.Text = \"(1979)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1979)"));
RDebugUtils.currentLine=1179671;
 //BA.debugLineNum = 1179671;BA.debugLine="OverView1.Text = \"In this emotionally charged co";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
RDebugUtils.currentLine=1179672;
 //BA.debugLineNum = 1179672;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1179673;
 //BA.debugLineNum = 1179673;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=1179675;
 //BA.debugLineNum = 1179675;BA.debugLine="Drama2.Text = \"Manchester by the Sea\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Manchester by the Sea"));
RDebugUtils.currentLine=1179676;
 //BA.debugLineNum = 1179676;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
RDebugUtils.currentLine=1179677;
 //BA.debugLineNum = 1179677;BA.debugLine="Year2.Text = \"(2016)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=1179678;
 //BA.debugLineNum = 1179678;BA.debugLine="OverView2.Text = \"After the death of his brother";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
RDebugUtils.currentLine=1179679;
 //BA.debugLineNum = 1179679;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1179680;
 //BA.debugLineNum = 1179680;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=1179682;
 //BA.debugLineNum = 1179682;BA.debugLine="Drama3.Text = \"The Master\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=1179683;
 //BA.debugLineNum = 1179683;BA.debugLine="Starter3.Text = \"Starring: Philip Seymour Hoffma";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=1179684;
 //BA.debugLineNum = 1179684;BA.debugLine="Year3.Text = \"(2012)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=1179685;
 //BA.debugLineNum = 1179685;BA.debugLine="OverView3.Text = \"A mentally unstable WWII veter";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=1179686;
 //BA.debugLineNum = 1179686;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1179687;
 //BA.debugLineNum = 1179687;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=1179689;
 //BA.debugLineNum = 1179689;BA.debugLine="Drama4.Text = \"Million Dollar Baby\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Million Dollar Baby"));
RDebugUtils.currentLine=1179690;
 //BA.debugLineNum = 1179690;BA.debugLine="Starter4.Text = \"Starring: Morgan Freeman, Hilar";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
RDebugUtils.currentLine=1179691;
 //BA.debugLineNum = 1179691;BA.debugLine="Year4.Text = \"(2004)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2004)"));
RDebugUtils.currentLine=1179692;
 //BA.debugLineNum = 1179692;BA.debugLine="OverView4.Text = \"A waitress with dreams of beco";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
RDebugUtils.currentLine=1179693;
 //BA.debugLineNum = 1179693;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1179694;
 //BA.debugLineNum = 1179694;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=1179696;
 //BA.debugLineNum = 1179696;BA.debugLine="Drama5.Text = \"The Bridges of Madison County\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("The Bridges of Madison County"));
RDebugUtils.currentLine=1179697;
 //BA.debugLineNum = 1179697;BA.debugLine="Starter5.Text = \"Starring: Clint Eastwood, Meryl";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
RDebugUtils.currentLine=1179698;
 //BA.debugLineNum = 1179698;BA.debugLine="Year5.Text = \"(1995)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(1995)"));
RDebugUtils.currentLine=1179699;
 //BA.debugLineNum = 1179699;BA.debugLine="OverView5.Text = \"A brief, passionate romance be";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
RDebugUtils.currentLine=1179700;
 //BA.debugLineNum = 1179700;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
mostCurrent._dramaimage5.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1179701;
 //BA.debugLineNum = 1179701;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage5.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=1179704;
 //BA.debugLineNum = 1179704;BA.debugLine="Drama6.Text = \"Gone Baby Gone\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("Gone Baby Gone"));
RDebugUtils.currentLine=1179705;
 //BA.debugLineNum = 1179705;BA.debugLine="Starter6.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=1179706;
 //BA.debugLineNum = 1179706;BA.debugLine="Year6.Text = \"(2007)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2007)"));
RDebugUtils.currentLine=1179707;
 //BA.debugLineNum = 1179707;BA.debugLine="OverView6.Text = \"In a tough Boston neighborhood";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
RDebugUtils.currentLine=1179708;
 //BA.debugLineNum = 1179708;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
mostCurrent._dramaimage6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1179709;
 //BA.debugLineNum = 1179709;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage6.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=1179711;
 //BA.debugLineNum = 1179711;BA.debugLine="Drama7.Text = \"Blue Jasmine\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=1179712;
 //BA.debugLineNum = 1179712;BA.debugLine="Starter7.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=1179713;
 //BA.debugLineNum = 1179713;BA.debugLine="Year7.Text = \"(2013)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1179714;
 //BA.debugLineNum = 1179714;BA.debugLine="OverView7.Text = \"After losing her fortune and s";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=1179715;
 //BA.debugLineNum = 1179715;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1179716;
 //BA.debugLineNum = 1179716;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=1179718;
 //BA.debugLineNum = 1179718;BA.debugLine="Drama8.Text = \"Her\"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("Her"));
RDebugUtils.currentLine=1179719;
 //BA.debugLineNum = 1179719;BA.debugLine="Starter8.Text = \"Starring: Joaquin Phoenix, Roon";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
RDebugUtils.currentLine=1179720;
 //BA.debugLineNum = 1179720;BA.debugLine="Year8.Text = \"(2013)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1179721;
 //BA.debugLineNum = 1179721;BA.debugLine="OverView8.Text = \"In a near-future Los Angeles,";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
RDebugUtils.currentLine=1179722;
 //BA.debugLineNum = 1179722;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
mostCurrent._dramaimage8.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1179723;
 //BA.debugLineNum = 1179723;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage8.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=1179725;
 //BA.debugLineNum = 1179725;BA.debugLine="Drama9.Text = \"Carol\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=1179726;
 //BA.debugLineNum = 1179726;BA.debugLine="Starter9.Text = \"Starring: Rooney Mara, Cate Bla";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=1179727;
 //BA.debugLineNum = 1179727;BA.debugLine="Year9.Text = \"(2015)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=1179728;
 //BA.debugLineNum = 1179728;BA.debugLine="OverView9.Text = \"A chance encounter between a y";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=1179729;
 //BA.debugLineNum = 1179729;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
mostCurrent._dramaimage9.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1179730;
 //BA.debugLineNum = 1179730;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage9.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=1179732;
 //BA.debugLineNum = 1179732;BA.debugLine="Drama10.Text = \"The Lost Daughter\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("The Lost Daughter"));
RDebugUtils.currentLine=1179733;
 //BA.debugLineNum = 1179733;BA.debugLine="Starter10.Text = \"Starring: Olivia Colman, Dakot";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Olivia Colman, Dakota Johnson, Jessie Buckley"));
RDebugUtils.currentLine=1179734;
 //BA.debugLineNum = 1179734;BA.debugLine="Year10.Text = \"(2021)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2021)"));
RDebugUtils.currentLine=1179735;
 //BA.debugLineNum = 1179735;BA.debugLine="OverView10.Text = \"A solitary woman on vacation";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity"));
RDebugUtils.currentLine=1179736;
 //BA.debugLineNum = 1179736;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1179737;
 //BA.debugLineNum = 1179737;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
 };
RDebugUtils.currentLine=1179743;
 //BA.debugLineNum = 1179743;BA.debugLine="End Sub";
return "";
}
}