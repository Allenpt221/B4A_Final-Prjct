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
public anywheresoftware.b4a.objects.PanelWrapper _panel2 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel3 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel4 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel5 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel6 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel7 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel8 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel9 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel10 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel11 = null;
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
public anywheresoftware.b4a.objects.LabelWrapper _notfound = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.action _action = null;
public b4a.example.scifi _scifi = null;
public b4a.example.panelview _panelview = null;
public static String  _actionpage_click() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "actionpage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "actionpage_click", null));}
RDebugUtils.currentLine=1310720;
 //BA.debugLineNum = 1310720;BA.debugLine="Private Sub ActionPage_Click";
RDebugUtils.currentLine=1310721;
 //BA.debugLineNum = 1310721;BA.debugLine="StartActivity(Action)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._action.getObject()));
RDebugUtils.currentLine=1310722;
 //BA.debugLineNum = 1310722;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=1310723;
 //BA.debugLineNum = 1310723;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=851968;
 //BA.debugLineNum = 851968;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=851969;
 //BA.debugLineNum = 851969;BA.debugLine="Activity.LoadLayout(\"Drama\") ' Layout contains Sc";
mostCurrent._activity.LoadLayout("Drama",mostCurrent.activityBA);
RDebugUtils.currentLine=851971;
 //BA.debugLineNum = 851971;BA.debugLine="p.Initialize(\"\")";
mostCurrent._p.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=851972;
 //BA.debugLineNum = 851972;BA.debugLine="p.LoadLayout(\"panelview\")";
mostCurrent._p.LoadLayout("panelview",mostCurrent.activityBA);
RDebugUtils.currentLine=851979;
 //BA.debugLineNum = 851979;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Kramer vs. Kramer"));
RDebugUtils.currentLine=851980;
 //BA.debugLineNum = 851980;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
RDebugUtils.currentLine=851981;
 //BA.debugLineNum = 851981;BA.debugLine="Year1.Text = \"(1979)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1979)"));
RDebugUtils.currentLine=851982;
 //BA.debugLineNum = 851982;BA.debugLine="OverView1.Text = \"In this emotionally charged cou";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
RDebugUtils.currentLine=851983;
 //BA.debugLineNum = 851983;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=851984;
 //BA.debugLineNum = 851984;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=851986;
 //BA.debugLineNum = 851986;BA.debugLine="Drama2.Text = \"Manchester by the Sea\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Manchester by the Sea"));
RDebugUtils.currentLine=851987;
 //BA.debugLineNum = 851987;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michell";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
RDebugUtils.currentLine=851988;
 //BA.debugLineNum = 851988;BA.debugLine="Year2.Text = \"(2016)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=851989;
 //BA.debugLineNum = 851989;BA.debugLine="OverView2.Text = \"After the death of his brother,";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
RDebugUtils.currentLine=851990;
 //BA.debugLineNum = 851990;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=851991;
 //BA.debugLineNum = 851991;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=851993;
 //BA.debugLineNum = 851993;BA.debugLine="Drama3.Text = \"The Master\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=851994;
 //BA.debugLineNum = 851994;BA.debugLine="Starter3.Text = \"Starring: Philip Seymour Hoffman";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=851995;
 //BA.debugLineNum = 851995;BA.debugLine="Year3.Text = \"(2012)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=851996;
 //BA.debugLineNum = 851996;BA.debugLine="OverView3.Text = \"A mentally unstable WWII vetera";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=851997;
 //BA.debugLineNum = 851997;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=851998;
 //BA.debugLineNum = 851998;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=852000;
 //BA.debugLineNum = 852000;BA.debugLine="Drama4.Text = \"Million Dollar Baby\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Million Dollar Baby"));
RDebugUtils.currentLine=852001;
 //BA.debugLineNum = 852001;BA.debugLine="Starter4.Text = \"Starring: Morgan Freeman, Hilary";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
RDebugUtils.currentLine=852002;
 //BA.debugLineNum = 852002;BA.debugLine="Year4.Text = \"(2004)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2004)"));
RDebugUtils.currentLine=852003;
 //BA.debugLineNum = 852003;BA.debugLine="OverView4.Text = \"A waitress with dreams of becom";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
RDebugUtils.currentLine=852004;
 //BA.debugLineNum = 852004;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=852005;
 //BA.debugLineNum = 852005;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=852007;
 //BA.debugLineNum = 852007;BA.debugLine="Drama5.Text = \"The Bridges of Madison County\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("The Bridges of Madison County"));
RDebugUtils.currentLine=852008;
 //BA.debugLineNum = 852008;BA.debugLine="Starter5.Text = \"Starring: Clint Eastwood, Meryl";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
RDebugUtils.currentLine=852009;
 //BA.debugLineNum = 852009;BA.debugLine="Year5.Text = \"(1995)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(1995)"));
RDebugUtils.currentLine=852010;
 //BA.debugLineNum = 852010;BA.debugLine="OverView5.Text = \"A brief, passionate romance bet";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
RDebugUtils.currentLine=852011;
 //BA.debugLineNum = 852011;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
mostCurrent._dramaimage5.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=852012;
 //BA.debugLineNum = 852012;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage5.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=852015;
 //BA.debugLineNum = 852015;BA.debugLine="Drama6.Text = \"Gone Baby Gone\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("Gone Baby Gone"));
RDebugUtils.currentLine=852016;
 //BA.debugLineNum = 852016;BA.debugLine="Starter6.Text = \"Starring: Casey Affleck, Michell";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=852017;
 //BA.debugLineNum = 852017;BA.debugLine="Year6.Text = \"(2007)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2007)"));
RDebugUtils.currentLine=852018;
 //BA.debugLineNum = 852018;BA.debugLine="OverView6.Text = \"In a tough Boston neighborhood,";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
RDebugUtils.currentLine=852019;
 //BA.debugLineNum = 852019;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
mostCurrent._dramaimage6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=852020;
 //BA.debugLineNum = 852020;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage6.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=852022;
 //BA.debugLineNum = 852022;BA.debugLine="Drama7.Text = \"Blue Jasmine\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=852023;
 //BA.debugLineNum = 852023;BA.debugLine="Starter7.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=852024;
 //BA.debugLineNum = 852024;BA.debugLine="Year7.Text = \"(2013)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=852025;
 //BA.debugLineNum = 852025;BA.debugLine="OverView7.Text = \"After losing her fortune and st";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=852026;
 //BA.debugLineNum = 852026;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=852027;
 //BA.debugLineNum = 852027;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=852029;
 //BA.debugLineNum = 852029;BA.debugLine="Drama8.Text = \"Her\"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("Her"));
RDebugUtils.currentLine=852030;
 //BA.debugLineNum = 852030;BA.debugLine="Starter8.Text = \"Starring: Joaquin Phoenix, Roone";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
RDebugUtils.currentLine=852031;
 //BA.debugLineNum = 852031;BA.debugLine="Year8.Text = \"(2013)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=852032;
 //BA.debugLineNum = 852032;BA.debugLine="OverView8.Text = \"In a near-future Los Angeles, a";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
RDebugUtils.currentLine=852033;
 //BA.debugLineNum = 852033;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
mostCurrent._dramaimage8.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=852034;
 //BA.debugLineNum = 852034;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage8.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=852036;
 //BA.debugLineNum = 852036;BA.debugLine="Drama9.Text = \"Carol\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=852037;
 //BA.debugLineNum = 852037;BA.debugLine="Starter9.Text = \"Starring: Rooney Mara, Cate Blan";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=852038;
 //BA.debugLineNum = 852038;BA.debugLine="Year9.Text = \"(2015)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=852039;
 //BA.debugLineNum = 852039;BA.debugLine="OverView9.Text = \"A chance encounter between a yo";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=852040;
 //BA.debugLineNum = 852040;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
mostCurrent._dramaimage9.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=852041;
 //BA.debugLineNum = 852041;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage9.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=852043;
 //BA.debugLineNum = 852043;BA.debugLine="Drama10.Text = \"The Lost Daughter\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("The Lost Daughter"));
RDebugUtils.currentLine=852044;
 //BA.debugLineNum = 852044;BA.debugLine="Starter10.Text = \"Starring: Olivia Colman, Dakota";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Olivia Colman, Dakota Johnson, Jessie Buckley"));
RDebugUtils.currentLine=852045;
 //BA.debugLineNum = 852045;BA.debugLine="Year10.Text = \"(2021)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2021)"));
RDebugUtils.currentLine=852046;
 //BA.debugLineNum = 852046;BA.debugLine="OverView10.Text = \"A solitary woman on vacation b";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity"));
RDebugUtils.currentLine=852047;
 //BA.debugLineNum = 852047;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=852048;
 //BA.debugLineNum = 852048;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=852050;
 //BA.debugLineNum = 852050;BA.debugLine="ScrollView1.Panel.AddView(p, 0, 0, 200%x, 210%y)";
mostCurrent._scrollview1.getPanel().AddView((android.view.View)(mostCurrent._p.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=852051;
 //BA.debugLineNum = 852051;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=852052;
 //BA.debugLineNum = 852052;BA.debugLine="End Sub";
return "";
}
public static String  _dramapage_click() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "dramapage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "dramapage_click", null));}
RDebugUtils.currentLine=1245184;
 //BA.debugLineNum = 1245184;BA.debugLine="Private Sub DramaPage_Click";
RDebugUtils.currentLine=1245186;
 //BA.debugLineNum = 1245186;BA.debugLine="End Sub";
return "";
}
public static String  _homepage_click() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "homepage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "homepage_click", null));}
RDebugUtils.currentLine=1179648;
 //BA.debugLineNum = 1179648;BA.debugLine="Private Sub HomePage_Click";
RDebugUtils.currentLine=1179649;
 //BA.debugLineNum = 1179649;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
RDebugUtils.currentLine=1179650;
 //BA.debugLineNum = 1179650;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=1179651;
 //BA.debugLineNum = 1179651;BA.debugLine="End Sub";
return "";
}
public static String  _panel2_click() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panel2_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "panel2_click", null));}
anywheresoftware.b4a.objects.IntentWrapper _i = null;
RDebugUtils.currentLine=5570560;
 //BA.debugLineNum = 5570560;BA.debugLine="Private Sub Panel2_Click";
RDebugUtils.currentLine=5570561;
 //BA.debugLineNum = 5570561;BA.debugLine="Try";
try {RDebugUtils.currentLine=5570562;
 //BA.debugLineNum = 5570562;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=5570563;
 //BA.debugLineNum = 5570563;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.co";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0079417/");
RDebugUtils.currentLine=5570564;
 //BA.debugLineNum = 5570564;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.an";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=5570565;
 //BA.debugLineNum = 5570565;BA.debugLine="StartActivity(i)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_i.getObject()));
 } 
       catch (Exception e7) {
			processBA.setLastException(e7);RDebugUtils.currentLine=5570567;
 //BA.debugLineNum = 5570567;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("05570567",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=5570568;
 //BA.debugLineNum = 5570568;BA.debugLine="Msgbox(\"Error\", \"\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Error"),BA.ObjectToCharSequence(""),mostCurrent.activityBA);
 };
RDebugUtils.currentLine=5570571;
 //BA.debugLineNum = 5570571;BA.debugLine="End Sub";
return "";
}
public static String  _scifipage_click() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "scifipage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "scifipage_click", null));}
RDebugUtils.currentLine=1114112;
 //BA.debugLineNum = 1114112;BA.debugLine="Private Sub SciFiPage_Click";
RDebugUtils.currentLine=1114113;
 //BA.debugLineNum = 1114113;BA.debugLine="StartActivity(SciFi)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._scifi.getObject()));
RDebugUtils.currentLine=1114114;
 //BA.debugLineNum = 1114114;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=1114115;
 //BA.debugLineNum = 1114115;BA.debugLine="End Sub";
return "";
}
public static String  _searchbtn_click() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchbtn_click", null));}
String _query = "";
String _userinput = "";
RDebugUtils.currentLine=917504;
 //BA.debugLineNum = 917504;BA.debugLine="Private Sub SearchBtn_Click";
RDebugUtils.currentLine=917505;
 //BA.debugLineNum = 917505;BA.debugLine="Dim query As String = SearchEngine.Text.ToLowerCa";
_query = mostCurrent._searchengine.getText().toLowerCase().trim();
RDebugUtils.currentLine=917507;
 //BA.debugLineNum = 917507;BA.debugLine="Dim UserInput As String = SearchEngine.Text";
_userinput = mostCurrent._searchengine.getText();
RDebugUtils.currentLine=917512;
 //BA.debugLineNum = 917512;BA.debugLine="If query.Contains(\"kramer\") Or query.Contains(\"kr";
if (_query.contains("kramer") || _query.contains("kramer vs kramer")) { 
RDebugUtils.currentLine=917514;
 //BA.debugLineNum = 917514;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Kramer vs. Kramer"));
RDebugUtils.currentLine=917515;
 //BA.debugLineNum = 917515;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
RDebugUtils.currentLine=917516;
 //BA.debugLineNum = 917516;BA.debugLine="Year1.Text = \"(1979)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1979)"));
RDebugUtils.currentLine=917517;
 //BA.debugLineNum = 917517;BA.debugLine="OverView1.Text = \"In this emotionally charged co";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
RDebugUtils.currentLine=917518;
 //BA.debugLineNum = 917518;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917519;
 //BA.debugLineNum = 917519;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=917521;
 //BA.debugLineNum = 917521;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917522;
 //BA.debugLineNum = 917522;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917523;
 //BA.debugLineNum = 917523;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917524;
 //BA.debugLineNum = 917524;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917525;
 //BA.debugLineNum = 917525;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917527;
 //BA.debugLineNum = 917527;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917529;
 //BA.debugLineNum = 917529;BA.debugLine="Else If query.Contains(\"manchester by the Sea\") O";
if (_query.contains("manchester by the Sea") || _query.contains("manchester")) { 
RDebugUtils.currentLine=917531;
 //BA.debugLineNum = 917531;BA.debugLine="Drama1.Text = \"Manchester by the Sea\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Manchester by the Sea"));
RDebugUtils.currentLine=917532;
 //BA.debugLineNum = 917532;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
RDebugUtils.currentLine=917533;
 //BA.debugLineNum = 917533;BA.debugLine="Year1.Text = \"(2016)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=917534;
 //BA.debugLineNum = 917534;BA.debugLine="OverView1.Text = \"After the death of his brother";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
RDebugUtils.currentLine=917535;
 //BA.debugLineNum = 917535;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917536;
 //BA.debugLineNum = 917536;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=917539;
 //BA.debugLineNum = 917539;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917540;
 //BA.debugLineNum = 917540;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917541;
 //BA.debugLineNum = 917541;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917542;
 //BA.debugLineNum = 917542;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917543;
 //BA.debugLineNum = 917543;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917545;
 //BA.debugLineNum = 917545;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917547;
 //BA.debugLineNum = 917547;BA.debugLine="Else If query.Contains(\"the master\") Or query.Con";
if (_query.contains("the master") || _query.contains("master")) { 
RDebugUtils.currentLine=917549;
 //BA.debugLineNum = 917549;BA.debugLine="Drama1.Text = \"The Master\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=917550;
 //BA.debugLineNum = 917550;BA.debugLine="Starter1.Text = \"Starring: Philip Seymour Hoffma";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=917551;
 //BA.debugLineNum = 917551;BA.debugLine="Year1.Text = \"(2012)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=917552;
 //BA.debugLineNum = 917552;BA.debugLine="OverView1.Text = \"A mentally unstable WWII veter";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=917553;
 //BA.debugLineNum = 917553;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917554;
 //BA.debugLineNum = 917554;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=917556;
 //BA.debugLineNum = 917556;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917557;
 //BA.debugLineNum = 917557;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917558;
 //BA.debugLineNum = 917558;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917559;
 //BA.debugLineNum = 917559;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917560;
 //BA.debugLineNum = 917560;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917562;
 //BA.debugLineNum = 917562;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917564;
 //BA.debugLineNum = 917564;BA.debugLine="Else If query.Contains(\"million dollar\") Or query";
if (_query.contains("million dollar") || _query.contains("million") || _query.contains("million dollar baby")) { 
RDebugUtils.currentLine=917566;
 //BA.debugLineNum = 917566;BA.debugLine="Drama1.Text = \"Million Dollar Baby\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Million Dollar Baby"));
RDebugUtils.currentLine=917567;
 //BA.debugLineNum = 917567;BA.debugLine="Starter1.Text = \"Starring: Morgan Freeman, Hilar";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
RDebugUtils.currentLine=917568;
 //BA.debugLineNum = 917568;BA.debugLine="Year1.Text = \"(2004)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2004)"));
RDebugUtils.currentLine=917569;
 //BA.debugLineNum = 917569;BA.debugLine="OverView1.Text = \"A waitress with dreams of beco";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
RDebugUtils.currentLine=917570;
 //BA.debugLineNum = 917570;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917571;
 //BA.debugLineNum = 917571;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=917573;
 //BA.debugLineNum = 917573;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917574;
 //BA.debugLineNum = 917574;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917575;
 //BA.debugLineNum = 917575;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917576;
 //BA.debugLineNum = 917576;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917577;
 //BA.debugLineNum = 917577;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917579;
 //BA.debugLineNum = 917579;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917581;
 //BA.debugLineNum = 917581;BA.debugLine="Else If query.Contains(\"the Bridges of madison co";
if (_query.contains("the Bridges of madison country") || _query.contains("Bridge") || _query.contains("bridges of madison")) { 
RDebugUtils.currentLine=917583;
 //BA.debugLineNum = 917583;BA.debugLine="Drama1.Text = \"The Bridges of Madison County\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Bridges of Madison County"));
RDebugUtils.currentLine=917584;
 //BA.debugLineNum = 917584;BA.debugLine="Starter1.Text = \"Starring: Clint Eastwood, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
RDebugUtils.currentLine=917585;
 //BA.debugLineNum = 917585;BA.debugLine="Year1.Text = \"(1995)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1995)"));
RDebugUtils.currentLine=917586;
 //BA.debugLineNum = 917586;BA.debugLine="OverView1.Text = \"A brief, passionate romance be";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
RDebugUtils.currentLine=917587;
 //BA.debugLineNum = 917587;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917588;
 //BA.debugLineNum = 917588;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=917590;
 //BA.debugLineNum = 917590;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917591;
 //BA.debugLineNum = 917591;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917592;
 //BA.debugLineNum = 917592;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917593;
 //BA.debugLineNum = 917593;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917594;
 //BA.debugLineNum = 917594;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917596;
 //BA.debugLineNum = 917596;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917598;
 //BA.debugLineNum = 917598;BA.debugLine="Else If query.Contains(\"gone\") Or query.Contains(";
if (_query.contains("gone") || _query.contains("gone baby gone") || _query.contains("gone baby")) { 
RDebugUtils.currentLine=917600;
 //BA.debugLineNum = 917600;BA.debugLine="Drama1.Text = \"Gone Baby Gone\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Gone Baby Gone"));
RDebugUtils.currentLine=917601;
 //BA.debugLineNum = 917601;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=917602;
 //BA.debugLineNum = 917602;BA.debugLine="Year1.Text = \"(2007)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2007)"));
RDebugUtils.currentLine=917603;
 //BA.debugLineNum = 917603;BA.debugLine="OverView1.Text = \"In a tough Boston neighborhood";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
RDebugUtils.currentLine=917605;
 //BA.debugLineNum = 917605;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917606;
 //BA.debugLineNum = 917606;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917607;
 //BA.debugLineNum = 917607;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917608;
 //BA.debugLineNum = 917608;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917609;
 //BA.debugLineNum = 917609;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917611;
 //BA.debugLineNum = 917611;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917613;
 //BA.debugLineNum = 917613;BA.debugLine="Else If query.Contains(\"blue\") Or query.Contains(";
if (_query.contains("blue") || _query.contains("blue jasmine")) { 
RDebugUtils.currentLine=917616;
 //BA.debugLineNum = 917616;BA.debugLine="Drama1.Text = \"Blue Jasmine\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=917617;
 //BA.debugLineNum = 917617;BA.debugLine="Starter1.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=917618;
 //BA.debugLineNum = 917618;BA.debugLine="Year1.Text = \"(2013)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=917619;
 //BA.debugLineNum = 917619;BA.debugLine="OverView1.Text = \"After losing her fortune and s";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=917620;
 //BA.debugLineNum = 917620;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917621;
 //BA.debugLineNum = 917621;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=917623;
 //BA.debugLineNum = 917623;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917624;
 //BA.debugLineNum = 917624;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917625;
 //BA.debugLineNum = 917625;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917626;
 //BA.debugLineNum = 917626;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917627;
 //BA.debugLineNum = 917627;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917629;
 //BA.debugLineNum = 917629;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917631;
 //BA.debugLineNum = 917631;BA.debugLine="Else If query.Contains(\"her\") Then";
if (_query.contains("her")) { 
RDebugUtils.currentLine=917633;
 //BA.debugLineNum = 917633;BA.debugLine="Drama1.Text = \"Her\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Her"));
RDebugUtils.currentLine=917634;
 //BA.debugLineNum = 917634;BA.debugLine="Starter1.Text = \"Starring: Joaquin Phoenix, Roon";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
RDebugUtils.currentLine=917635;
 //BA.debugLineNum = 917635;BA.debugLine="Year1.Text = \"(2013)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=917636;
 //BA.debugLineNum = 917636;BA.debugLine="OverView1.Text = \"In a near-future Los Angeles,";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
RDebugUtils.currentLine=917637;
 //BA.debugLineNum = 917637;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917638;
 //BA.debugLineNum = 917638;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=917640;
 //BA.debugLineNum = 917640;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917641;
 //BA.debugLineNum = 917641;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917642;
 //BA.debugLineNum = 917642;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917643;
 //BA.debugLineNum = 917643;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917644;
 //BA.debugLineNum = 917644;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917646;
 //BA.debugLineNum = 917646;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917648;
 //BA.debugLineNum = 917648;BA.debugLine="Else If query.Contains(\"carol\") Or query.Contains";
if (_query.contains("carol") || _query.contains("sarah paulson") || _query.contains("sarah") || _query.contains("paulson")) { 
RDebugUtils.currentLine=917650;
 //BA.debugLineNum = 917650;BA.debugLine="Drama1.Text = \"Carol\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=917651;
 //BA.debugLineNum = 917651;BA.debugLine="Starter1.Text = \"Starring: Rooney Mara, Cate Bla";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=917652;
 //BA.debugLineNum = 917652;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=917653;
 //BA.debugLineNum = 917653;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=917654;
 //BA.debugLineNum = 917654;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917655;
 //BA.debugLineNum = 917655;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=917657;
 //BA.debugLineNum = 917657;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917658;
 //BA.debugLineNum = 917658;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917659;
 //BA.debugLineNum = 917659;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917660;
 //BA.debugLineNum = 917660;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917661;
 //BA.debugLineNum = 917661;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917663;
 //BA.debugLineNum = 917663;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917665;
 //BA.debugLineNum = 917665;BA.debugLine="Else If query.Contains(\"the lost daugther\") Or qu";
if (_query.contains("the lost daugther") || _query.contains("lost daughter") || _query.contains("lost")) { 
RDebugUtils.currentLine=917667;
 //BA.debugLineNum = 917667;BA.debugLine="Drama1.Text = \"The Lost Daughter\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Lost Daughter"));
RDebugUtils.currentLine=917668;
 //BA.debugLineNum = 917668;BA.debugLine="Starter1.Text = \"Starring: Olivia Colman, Dakota";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Olivia Colman, Dakota Johnson, Jessie Buckley"));
RDebugUtils.currentLine=917669;
 //BA.debugLineNum = 917669;BA.debugLine="Year1.Text = \"(2021)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2021)"));
RDebugUtils.currentLine=917670;
 //BA.debugLineNum = 917670;BA.debugLine="OverView1.Text = \"A solitary woman on vacation b";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity"));
RDebugUtils.currentLine=917671;
 //BA.debugLineNum = 917671;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917672;
 //BA.debugLineNum = 917672;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=917674;
 //BA.debugLineNum = 917674;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917675;
 //BA.debugLineNum = 917675;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917676;
 //BA.debugLineNum = 917676;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917677;
 //BA.debugLineNum = 917677;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917678;
 //BA.debugLineNum = 917678;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917680;
 //BA.debugLineNum = 917680;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917681;
 //BA.debugLineNum = 917681;BA.debugLine="Else If query.Contains(\"doubt\") Then";
if (_query.contains("doubt")) { 
RDebugUtils.currentLine=917682;
 //BA.debugLineNum = 917682;BA.debugLine="Drama1.Text = \"Doubt\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Doubt"));
RDebugUtils.currentLine=917683;
 //BA.debugLineNum = 917683;BA.debugLine="Starter1.Text = \"Starring: Meryl Streep, Philip";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Meryl Streep, Philip Seymour Hoffman, Amy Adams"));
RDebugUtils.currentLine=917684;
 //BA.debugLineNum = 917684;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=917685;
 //BA.debugLineNum = 917685;BA.debugLine="OverView1.Text = \"In a Catholic school in the Br";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a Catholic school in the Bronx, a strict nun becomes suspicious of a priest’s relationship with a student. As she digs deeper, the lines between truth and suspicion blur, leaving everyone in moral and emotional limbo."));
RDebugUtils.currentLine=917686;
 //BA.debugLineNum = 917686;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917687;
 //BA.debugLineNum = 917687;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"doubt.jpg").getObject()));
RDebugUtils.currentLine=917689;
 //BA.debugLineNum = 917689;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917690;
 //BA.debugLineNum = 917690;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917691;
 //BA.debugLineNum = 917691;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917692;
 //BA.debugLineNum = 917692;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917693;
 //BA.debugLineNum = 917693;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917694;
 //BA.debugLineNum = 917694;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917697;
 //BA.debugLineNum = 917697;BA.debugLine="Else If query.Contains(\"meryl streep\") Or query.C";
if (_query.contains("meryl streep") || _query.contains("meryl") || _query.contains("streep")) { 
RDebugUtils.currentLine=917698;
 //BA.debugLineNum = 917698;BA.debugLine="Drama1.Text = \"The Bridges of Madison County\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Bridges of Madison County"));
RDebugUtils.currentLine=917699;
 //BA.debugLineNum = 917699;BA.debugLine="Starter1.Text = \"Starring: Clint Eastwood, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
RDebugUtils.currentLine=917700;
 //BA.debugLineNum = 917700;BA.debugLine="Year1.Text = \"(1995)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1995)"));
RDebugUtils.currentLine=917701;
 //BA.debugLineNum = 917701;BA.debugLine="OverView1.Text = \"A brief, passionate romance be";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
RDebugUtils.currentLine=917702;
 //BA.debugLineNum = 917702;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917703;
 //BA.debugLineNum = 917703;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=917705;
 //BA.debugLineNum = 917705;BA.debugLine="Drama2.Text = \"Doubt\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Doubt"));
RDebugUtils.currentLine=917706;
 //BA.debugLineNum = 917706;BA.debugLine="Starter2.Text = \"Starring: Meryl Streep, Philip";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Meryl Streep, Philip Seymour Hoffman, Amy Adams"));
RDebugUtils.currentLine=917707;
 //BA.debugLineNum = 917707;BA.debugLine="Year2.Text = \"(2008)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=917708;
 //BA.debugLineNum = 917708;BA.debugLine="OverView2.Text = \"In a Catholic school in the Br";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a Catholic school in the Bronx, a strict nun becomes suspicious of a priest’s relationship with a student. As she digs deeper, the lines between truth and suspicion blur, leaving everyone in moral and emotional limbo."));
RDebugUtils.currentLine=917709;
 //BA.debugLineNum = 917709;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917710;
 //BA.debugLineNum = 917710;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"doubt.jpg").getObject()));
RDebugUtils.currentLine=917712;
 //BA.debugLineNum = 917712;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917713;
 //BA.debugLineNum = 917713;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917714;
 //BA.debugLineNum = 917714;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917715;
 //BA.debugLineNum = 917715;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917716;
 //BA.debugLineNum = 917716;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917717;
 //BA.debugLineNum = 917717;BA.debugLine="Else If query.Contains(\"casey\") Or query.Contains";
if (_query.contains("casey") || _query.contains("casey affleck")) { 
RDebugUtils.currentLine=917718;
 //BA.debugLineNum = 917718;BA.debugLine="Drama1.Text = \"Gone Baby Gone\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Gone Baby Gone"));
RDebugUtils.currentLine=917719;
 //BA.debugLineNum = 917719;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=917720;
 //BA.debugLineNum = 917720;BA.debugLine="Year1.Text = \"(2007)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2007)"));
RDebugUtils.currentLine=917721;
 //BA.debugLineNum = 917721;BA.debugLine="OverView1.Text = \"In a tough Boston neighborhood";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
RDebugUtils.currentLine=917722;
 //BA.debugLineNum = 917722;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917723;
 //BA.debugLineNum = 917723;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=917725;
 //BA.debugLineNum = 917725;BA.debugLine="Drama2.Text = \"Manchester by the Sea\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Manchester by the Sea"));
RDebugUtils.currentLine=917726;
 //BA.debugLineNum = 917726;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
RDebugUtils.currentLine=917727;
 //BA.debugLineNum = 917727;BA.debugLine="Year2.Text = \"(2016)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=917728;
 //BA.debugLineNum = 917728;BA.debugLine="OverView2.Text = \"After the death of his brother";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
RDebugUtils.currentLine=917729;
 //BA.debugLineNum = 917729;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917730;
 //BA.debugLineNum = 917730;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=917732;
 //BA.debugLineNum = 917732;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917733;
 //BA.debugLineNum = 917733;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917734;
 //BA.debugLineNum = 917734;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917735;
 //BA.debugLineNum = 917735;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917736;
 //BA.debugLineNum = 917736;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917738;
 //BA.debugLineNum = 917738;BA.debugLine="Else If query.Contains(\"joaquin\") Or query.Contai";
if (_query.contains("joaquin") || _query.contains("joaquin phoenix") || _query.contains("amy adams") || _query.contains("amy")) { 
RDebugUtils.currentLine=917739;
 //BA.debugLineNum = 917739;BA.debugLine="Drama1.Text = \"The Master\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=917740;
 //BA.debugLineNum = 917740;BA.debugLine="Starter1.Text = \"Starring: Philip Seymour Hoffma";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=917741;
 //BA.debugLineNum = 917741;BA.debugLine="Year1.Text = \"(2012)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=917742;
 //BA.debugLineNum = 917742;BA.debugLine="OverView1.Text = \"A mentally unstable WWII veter";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=917743;
 //BA.debugLineNum = 917743;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917744;
 //BA.debugLineNum = 917744;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=917746;
 //BA.debugLineNum = 917746;BA.debugLine="Drama2.Text = \"Her\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Her"));
RDebugUtils.currentLine=917747;
 //BA.debugLineNum = 917747;BA.debugLine="Starter2.Text = \"Starring: Joaquin Phoenix, Roon";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
RDebugUtils.currentLine=917748;
 //BA.debugLineNum = 917748;BA.debugLine="Year2.Text = \"(2013)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=917749;
 //BA.debugLineNum = 917749;BA.debugLine="OverView2.Text = \"In a near-future Los Angeles,";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
RDebugUtils.currentLine=917750;
 //BA.debugLineNum = 917750;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917751;
 //BA.debugLineNum = 917751;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=917753;
 //BA.debugLineNum = 917753;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917754;
 //BA.debugLineNum = 917754;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917755;
 //BA.debugLineNum = 917755;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917756;
 //BA.debugLineNum = 917756;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917758;
 //BA.debugLineNum = 917758;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917760;
 //BA.debugLineNum = 917760;BA.debugLine="Else If query.Contains(\"cate blanchett\") Or query";
if (_query.contains("cate blanchett") || _query.contains("cate")) { 
RDebugUtils.currentLine=917761;
 //BA.debugLineNum = 917761;BA.debugLine="Drama1.Text = \"Carol\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=917762;
 //BA.debugLineNum = 917762;BA.debugLine="Starter1.Text = \"Starring: Rooney Mara, Cate Bla";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=917763;
 //BA.debugLineNum = 917763;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=917764;
 //BA.debugLineNum = 917764;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=917765;
 //BA.debugLineNum = 917765;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917766;
 //BA.debugLineNum = 917766;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=917768;
 //BA.debugLineNum = 917768;BA.debugLine="Drama2.Text = \"Blue Jasmine\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=917769;
 //BA.debugLineNum = 917769;BA.debugLine="Starter2.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=917770;
 //BA.debugLineNum = 917770;BA.debugLine="Year2.Text = \"(2013)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=917771;
 //BA.debugLineNum = 917771;BA.debugLine="OverView2.Text = \"After losing her fortune and s";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=917772;
 //BA.debugLineNum = 917772;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917773;
 //BA.debugLineNum = 917773;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=917775;
 //BA.debugLineNum = 917775;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917776;
 //BA.debugLineNum = 917776;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917777;
 //BA.debugLineNum = 917777;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917778;
 //BA.debugLineNum = 917778;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917780;
 //BA.debugLineNum = 917780;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917782;
 //BA.debugLineNum = 917782;BA.debugLine="Else If query.Contains(\"morgan freeman\") Or query";
if (_query.contains("morgan freeman") || _query.contains("morgan")) { 
RDebugUtils.currentLine=917783;
 //BA.debugLineNum = 917783;BA.debugLine="Drama1.Text = \"Million Dollar Baby\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Million Dollar Baby"));
RDebugUtils.currentLine=917784;
 //BA.debugLineNum = 917784;BA.debugLine="Starter1.Text = \"Starring: Morgan Freeman, Hilar";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
RDebugUtils.currentLine=917785;
 //BA.debugLineNum = 917785;BA.debugLine="Year1.Text = \"(2004)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2004)"));
RDebugUtils.currentLine=917786;
 //BA.debugLineNum = 917786;BA.debugLine="OverView1.Text = \"A waitress with dreams of beco";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
RDebugUtils.currentLine=917787;
 //BA.debugLineNum = 917787;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917788;
 //BA.debugLineNum = 917788;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=917790;
 //BA.debugLineNum = 917790;BA.debugLine="Drama2.Text = \"Gone Baby Gone\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Gone Baby Gone"));
RDebugUtils.currentLine=917791;
 //BA.debugLineNum = 917791;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=917792;
 //BA.debugLineNum = 917792;BA.debugLine="Year2.Text = \"(2007)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2007)"));
RDebugUtils.currentLine=917793;
 //BA.debugLineNum = 917793;BA.debugLine="OverView2.Text = \"In a tough Boston neighborhood";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
RDebugUtils.currentLine=917794;
 //BA.debugLineNum = 917794;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917795;
 //BA.debugLineNum = 917795;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=917797;
 //BA.debugLineNum = 917797;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917798;
 //BA.debugLineNum = 917798;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917799;
 //BA.debugLineNum = 917799;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917800;
 //BA.debugLineNum = 917800;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917802;
 //BA.debugLineNum = 917802;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917804;
 //BA.debugLineNum = 917804;BA.debugLine="Else If query.Contains(\"clint eastwood\") Or query";
if (_query.contains("clint eastwood") || _query.contains("clint") || _query.contains("eastwood")) { 
RDebugUtils.currentLine=917805;
 //BA.debugLineNum = 917805;BA.debugLine="Drama1.Text = \"Million Dollar Baby\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Million Dollar Baby"));
RDebugUtils.currentLine=917806;
 //BA.debugLineNum = 917806;BA.debugLine="Starter1.Text = \"Starring: Morgan Freeman, Hilar";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
RDebugUtils.currentLine=917807;
 //BA.debugLineNum = 917807;BA.debugLine="Year1.Text = \"(2004)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2004)"));
RDebugUtils.currentLine=917808;
 //BA.debugLineNum = 917808;BA.debugLine="OverView1.Text = \"A waitress with dreams of beco";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
RDebugUtils.currentLine=917809;
 //BA.debugLineNum = 917809;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917810;
 //BA.debugLineNum = 917810;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=917812;
 //BA.debugLineNum = 917812;BA.debugLine="Drama2.Text = \"The Bridges of Madison Country\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("The Bridges of Madison Country"));
RDebugUtils.currentLine=917813;
 //BA.debugLineNum = 917813;BA.debugLine="Starter2.Text = \"Starring: Clint Eastwood, Meryl";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
RDebugUtils.currentLine=917814;
 //BA.debugLineNum = 917814;BA.debugLine="Year2.Text = \"(1995)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(1995)"));
RDebugUtils.currentLine=917815;
 //BA.debugLineNum = 917815;BA.debugLine="OverView2.Text = \"A brief, passionate romance be";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
RDebugUtils.currentLine=917816;
 //BA.debugLineNum = 917816;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917817;
 //BA.debugLineNum = 917817;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=917819;
 //BA.debugLineNum = 917819;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917820;
 //BA.debugLineNum = 917820;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917821;
 //BA.debugLineNum = 917821;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917822;
 //BA.debugLineNum = 917822;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917824;
 //BA.debugLineNum = 917824;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917826;
 //BA.debugLineNum = 917826;BA.debugLine="Else If query.Contains(\"amy Adams\") Or query.Cont";
if (_query.contains("amy Adams") || _query.contains("amy") || _query.contains("adams")) { 
RDebugUtils.currentLine=917828;
 //BA.debugLineNum = 917828;BA.debugLine="Drama2.Text = \"The Master\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=917829;
 //BA.debugLineNum = 917829;BA.debugLine="Starter2.Text = \"Starring: Philip Seymour Hoffma";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=917830;
 //BA.debugLineNum = 917830;BA.debugLine="Year2.Text = \"(2012)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=917831;
 //BA.debugLineNum = 917831;BA.debugLine="OverView2.Text = \"A mentally unstable WWII veter";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=917832;
 //BA.debugLineNum = 917832;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917833;
 //BA.debugLineNum = 917833;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=917835;
 //BA.debugLineNum = 917835;BA.debugLine="Drama3.Text = \"Her\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("Her"));
RDebugUtils.currentLine=917836;
 //BA.debugLineNum = 917836;BA.debugLine="Starter3.Text = \"Starring: Joaquin Phoenix, Roon";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
RDebugUtils.currentLine=917837;
 //BA.debugLineNum = 917837;BA.debugLine="Year3.Text = \"(2013)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=917838;
 //BA.debugLineNum = 917838;BA.debugLine="OverView3.Text = \"In a near-future Los Angeles,";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
RDebugUtils.currentLine=917839;
 //BA.debugLineNum = 917839;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917840;
 //BA.debugLineNum = 917840;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=917842;
 //BA.debugLineNum = 917842;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917843;
 //BA.debugLineNum = 917843;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917844;
 //BA.debugLineNum = 917844;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917845;
 //BA.debugLineNum = 917845;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917847;
 //BA.debugLineNum = 917847;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917848;
 //BA.debugLineNum = 917848;BA.debugLine="Else If query.Contains(\"rooney mara\") Or query.Co";
if (_query.contains("rooney mara") || _query.contains("rooney") || _query.contains("mara") || _query.contains("sarah") || _query.contains("sarah paulson") || _query.contains("paulson")) { 
RDebugUtils.currentLine=917849;
 //BA.debugLineNum = 917849;BA.debugLine="Drama1.Text = \"Carol\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=917850;
 //BA.debugLineNum = 917850;BA.debugLine="Starter1.Text = \"Starring: Rooney Mara, Cate Bla";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=917851;
 //BA.debugLineNum = 917851;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=917852;
 //BA.debugLineNum = 917852;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=917853;
 //BA.debugLineNum = 917853;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917854;
 //BA.debugLineNum = 917854;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=917856;
 //BA.debugLineNum = 917856;BA.debugLine="Drama2.Text = \"Blue Jasmine\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=917857;
 //BA.debugLineNum = 917857;BA.debugLine="Starter2.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=917858;
 //BA.debugLineNum = 917858;BA.debugLine="Year2.Text = \"(2013)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=917859;
 //BA.debugLineNum = 917859;BA.debugLine="OverView2.Text = \"After losing her fortune and s";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=917860;
 //BA.debugLineNum = 917860;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917861;
 //BA.debugLineNum = 917861;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=917863;
 //BA.debugLineNum = 917863;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917864;
 //BA.debugLineNum = 917864;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917865;
 //BA.debugLineNum = 917865;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917866;
 //BA.debugLineNum = 917866;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917868;
 //BA.debugLineNum = 917868;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917871;
 //BA.debugLineNum = 917871;BA.debugLine="Else If query.Contains(\"olvia coloman\") Or query.";
if (_query.contains("olvia coloman") || _query.contains("dakota johnson") || _query.contains("jessie buckley") || _query.contains("olvia") || _query.contains("jessie ") || _query.contains("dakota") || _query.contains("buckley") || _query.contains("coloman") || _query.contains("johnson")) { 
RDebugUtils.currentLine=917872;
 //BA.debugLineNum = 917872;BA.debugLine="Drama1.Text = \"Carol\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=917873;
 //BA.debugLineNum = 917873;BA.debugLine="Starter1.Text = \"Starring: Rooney Mara, Cate Bla";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=917874;
 //BA.debugLineNum = 917874;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=917875;
 //BA.debugLineNum = 917875;BA.debugLine="OverView1.Text = \"A solitary woman on vacation b";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity."));
RDebugUtils.currentLine=917876;
 //BA.debugLineNum = 917876;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917877;
 //BA.debugLineNum = 917877;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=917879;
 //BA.debugLineNum = 917879;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917880;
 //BA.debugLineNum = 917880;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917881;
 //BA.debugLineNum = 917881;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917882;
 //BA.debugLineNum = 917882;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917883;
 //BA.debugLineNum = 917883;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917885;
 //BA.debugLineNum = 917885;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917887;
 //BA.debugLineNum = 917887;BA.debugLine="Else If query.Contains(\"sally hawkins\") Or query.";
if (_query.contains("sally hawkins") || _query.contains("alec baldwin") || _query.contains("sally") || _query.contains("haswkins") || _query.contains("alec") || _query.contains("baldwin")) { 
RDebugUtils.currentLine=917888;
 //BA.debugLineNum = 917888;BA.debugLine="Drama1.Text = \"Blue Jasmine\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=917889;
 //BA.debugLineNum = 917889;BA.debugLine="Starter1.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=917890;
 //BA.debugLineNum = 917890;BA.debugLine="Year1.Text = \"(2013)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=917891;
 //BA.debugLineNum = 917891;BA.debugLine="OverView1.Text = \"After losing her fortune and s";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=917892;
 //BA.debugLineNum = 917892;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917893;
 //BA.debugLineNum = 917893;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=917895;
 //BA.debugLineNum = 917895;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917896;
 //BA.debugLineNum = 917896;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917897;
 //BA.debugLineNum = 917897;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917898;
 //BA.debugLineNum = 917898;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917899;
 //BA.debugLineNum = 917899;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917901;
 //BA.debugLineNum = 917901;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917902;
 //BA.debugLineNum = 917902;BA.debugLine="Else if query.Contains(\"michelle williams\") Or qu";
if (_query.contains("michelle williams") || _query.contains("lucas hedges") || _query.contains("michelle") || _query.contains("williams") || _query.contains("lucas") || _query.contains("hedges")) { 
RDebugUtils.currentLine=917903;
 //BA.debugLineNum = 917903;BA.debugLine="Drama1.Text = \"Manchester by the Sea\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Manchester by the Sea"));
RDebugUtils.currentLine=917904;
 //BA.debugLineNum = 917904;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
RDebugUtils.currentLine=917905;
 //BA.debugLineNum = 917905;BA.debugLine="Year1.Text = \"(2016)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=917906;
 //BA.debugLineNum = 917906;BA.debugLine="OverView1.Text = \"After the death of his brother";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
RDebugUtils.currentLine=917907;
 //BA.debugLineNum = 917907;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917908;
 //BA.debugLineNum = 917908;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=917910;
 //BA.debugLineNum = 917910;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917911;
 //BA.debugLineNum = 917911;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917912;
 //BA.debugLineNum = 917912;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917913;
 //BA.debugLineNum = 917913;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917914;
 //BA.debugLineNum = 917914;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917916;
 //BA.debugLineNum = 917916;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917917;
 //BA.debugLineNum = 917917;BA.debugLine="Else if query.Contains(\"dustin hoffman\") Or query";
if (_query.contains("dustin hoffman") || _query.contains("dustin") || _query.contains("justin henry") || _query.contains("justin") || _query.contains("henry")) { 
RDebugUtils.currentLine=917918;
 //BA.debugLineNum = 917918;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Kramer vs. Kramer"));
RDebugUtils.currentLine=917919;
 //BA.debugLineNum = 917919;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
RDebugUtils.currentLine=917920;
 //BA.debugLineNum = 917920;BA.debugLine="Year1.Text = \"(1979)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1979)"));
RDebugUtils.currentLine=917921;
 //BA.debugLineNum = 917921;BA.debugLine="OverView1.Text = \"In this emotionally charged co";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
RDebugUtils.currentLine=917922;
 //BA.debugLineNum = 917922;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917923;
 //BA.debugLineNum = 917923;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=917925;
 //BA.debugLineNum = 917925;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917926;
 //BA.debugLineNum = 917926;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917927;
 //BA.debugLineNum = 917927;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917928;
 //BA.debugLineNum = 917928;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917929;
 //BA.debugLineNum = 917929;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917931;
 //BA.debugLineNum = 917931;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917932;
 //BA.debugLineNum = 917932;BA.debugLine="Else If query.Contains(\"philip seymour hoffman\")";
if (_query.contains("philip seymour hoffman") || _query.contains("philip") || _query.contains("seymour")) { 
RDebugUtils.currentLine=917933;
 //BA.debugLineNum = 917933;BA.debugLine="Drama1.Text = \"Doubt\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Doubt"));
RDebugUtils.currentLine=917934;
 //BA.debugLineNum = 917934;BA.debugLine="Starter1.Text = \"Starring: Meryl Streep, Philip";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Meryl Streep, Philip Seymour Hoffman, Amy Adams"));
RDebugUtils.currentLine=917935;
 //BA.debugLineNum = 917935;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=917936;
 //BA.debugLineNum = 917936;BA.debugLine="OverView1.Text = \"In a Catholic school in the Br";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a Catholic school in the Bronx, a strict nun becomes suspicious of a priest’s relationship with a student. As she digs deeper, the lines between truth and suspicion blur, leaving everyone in moral and emotional limbo."));
RDebugUtils.currentLine=917937;
 //BA.debugLineNum = 917937;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917938;
 //BA.debugLineNum = 917938;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"doubt.jpg").getObject()));
RDebugUtils.currentLine=917941;
 //BA.debugLineNum = 917941;BA.debugLine="Drama2.Text = \"The Master\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=917942;
 //BA.debugLineNum = 917942;BA.debugLine="Starter2.Text = \"Starring: Philip Seymour Hoffma";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=917943;
 //BA.debugLineNum = 917943;BA.debugLine="Year2.Text = \"(2012)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=917944;
 //BA.debugLineNum = 917944;BA.debugLine="OverView2.Text = \"A mentally unstable WWII veter";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=917945;
 //BA.debugLineNum = 917945;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917946;
 //BA.debugLineNum = 917946;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=917948;
 //BA.debugLineNum = 917948;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917949;
 //BA.debugLineNum = 917949;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917950;
 //BA.debugLineNum = 917950;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917951;
 //BA.debugLineNum = 917951;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917953;
 //BA.debugLineNum = 917953;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=917955;
 //BA.debugLineNum = 917955;BA.debugLine="Else If query.Contains(\"hoffman\") Then";
if (_query.contains("hoffman")) { 
RDebugUtils.currentLine=917956;
 //BA.debugLineNum = 917956;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Kramer vs. Kramer"));
RDebugUtils.currentLine=917957;
 //BA.debugLineNum = 917957;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
RDebugUtils.currentLine=917958;
 //BA.debugLineNum = 917958;BA.debugLine="Year1.Text = \"(1979)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1979)"));
RDebugUtils.currentLine=917959;
 //BA.debugLineNum = 917959;BA.debugLine="OverView1.Text = \"In this emotionally charged co";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
RDebugUtils.currentLine=917960;
 //BA.debugLineNum = 917960;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917961;
 //BA.debugLineNum = 917961;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=917963;
 //BA.debugLineNum = 917963;BA.debugLine="Drama2.Text = \"Doubt\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Doubt"));
RDebugUtils.currentLine=917964;
 //BA.debugLineNum = 917964;BA.debugLine="Starter2.Text = \"Starring: Meryl Streep, Philip";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Meryl Streep, Philip Seymour Hoffman, Amy Adams"));
RDebugUtils.currentLine=917965;
 //BA.debugLineNum = 917965;BA.debugLine="Year2.Text = \"(2008)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=917966;
 //BA.debugLineNum = 917966;BA.debugLine="OverView2.Text = \"In a Catholic school in the Br";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a Catholic school in the Bronx, a strict nun becomes suspicious of a priest’s relationship with a student. As she digs deeper, the lines between truth and suspicion blur, leaving everyone in moral and emotional limbo."));
RDebugUtils.currentLine=917967;
 //BA.debugLineNum = 917967;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=917968;
 //BA.debugLineNum = 917968;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"doubt.jpg").getObject()));
RDebugUtils.currentLine=917970;
 //BA.debugLineNum = 917970;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917971;
 //BA.debugLineNum = 917971;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917972;
 //BA.debugLineNum = 917972;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917973;
 //BA.debugLineNum = 917973;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917975;
 //BA.debugLineNum = 917975;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else {
RDebugUtils.currentLine=917978;
 //BA.debugLineNum = 917978;BA.debugLine="MsgboxAsync(\"No results found for\" & \" \"\"\" & Use";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("No results found for"+" \""+_userinput+"\""),BA.ObjectToCharSequence(""),processBA);
 }}}}}}}}}}}}}}}}}}}}}}}}}
;
RDebugUtils.currentLine=917983;
 //BA.debugLineNum = 917983;BA.debugLine="p.Width = 100%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=917984;
 //BA.debugLineNum = 917984;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=917985;
 //BA.debugLineNum = 917985;BA.debugLine="End Sub";
return "";
}
public static String  _searchengine_textchanged(String _old,String _new) throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchengine_textchanged", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchengine_textchanged", new Object[] {_old,_new}));}
String _query = "";
RDebugUtils.currentLine=1048576;
 //BA.debugLineNum = 1048576;BA.debugLine="Sub SearchEngine_TextChanged (Old As String, New A";
RDebugUtils.currentLine=1048577;
 //BA.debugLineNum = 1048577;BA.debugLine="Dim query As String = New.ToLowerCase.Trim";
_query = _new.toLowerCase().trim();
RDebugUtils.currentLine=1048579;
 //BA.debugLineNum = 1048579;BA.debugLine="If query = \"\" Then";
if ((_query).equals("")) { 
RDebugUtils.currentLine=1048581;
 //BA.debugLineNum = 1048581;BA.debugLine="NotFound.Text = \"\"";
mostCurrent._notfound.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=1048582;
 //BA.debugLineNum = 1048582;BA.debugLine="p.Height = 210%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=1048583;
 //BA.debugLineNum = 1048583;BA.debugLine="p.Width = 200%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA));
RDebugUtils.currentLine=1048584;
 //BA.debugLineNum = 1048584;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=1048586;
 //BA.debugLineNum = 1048586;BA.debugLine="Panel2.Visible = True";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1048587;
 //BA.debugLineNum = 1048587;BA.debugLine="Panel3.Visible = True";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1048588;
 //BA.debugLineNum = 1048588;BA.debugLine="Panel4.Visible = True";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1048589;
 //BA.debugLineNum = 1048589;BA.debugLine="Panel5.Visible = True";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1048590;
 //BA.debugLineNum = 1048590;BA.debugLine="Panel6.Visible = True";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1048591;
 //BA.debugLineNum = 1048591;BA.debugLine="Panel7.Visible = True";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1048592;
 //BA.debugLineNum = 1048592;BA.debugLine="Panel8.Visible = True";
mostCurrent._panel8.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1048593;
 //BA.debugLineNum = 1048593;BA.debugLine="Panel9.Visible = True";
mostCurrent._panel9.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1048594;
 //BA.debugLineNum = 1048594;BA.debugLine="Panel10.Visible = True";
mostCurrent._panel10.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1048595;
 //BA.debugLineNum = 1048595;BA.debugLine="Panel11.Visible = True";
mostCurrent._panel11.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1048597;
 //BA.debugLineNum = 1048597;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Kramer vs. Kramer"));
RDebugUtils.currentLine=1048598;
 //BA.debugLineNum = 1048598;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
RDebugUtils.currentLine=1048599;
 //BA.debugLineNum = 1048599;BA.debugLine="Year1.Text = \"(1979)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1979)"));
RDebugUtils.currentLine=1048600;
 //BA.debugLineNum = 1048600;BA.debugLine="OverView1.Text = \"In this emotionally charged co";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
RDebugUtils.currentLine=1048601;
 //BA.debugLineNum = 1048601;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1048602;
 //BA.debugLineNum = 1048602;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=1048604;
 //BA.debugLineNum = 1048604;BA.debugLine="Drama2.Text = \"Manchester by the Sea\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Manchester by the Sea"));
RDebugUtils.currentLine=1048605;
 //BA.debugLineNum = 1048605;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
RDebugUtils.currentLine=1048606;
 //BA.debugLineNum = 1048606;BA.debugLine="Year2.Text = \"(2016)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=1048607;
 //BA.debugLineNum = 1048607;BA.debugLine="OverView2.Text = \"After the death of his brother";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
RDebugUtils.currentLine=1048608;
 //BA.debugLineNum = 1048608;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1048609;
 //BA.debugLineNum = 1048609;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=1048611;
 //BA.debugLineNum = 1048611;BA.debugLine="Drama3.Text = \"The Master\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=1048612;
 //BA.debugLineNum = 1048612;BA.debugLine="Starter3.Text = \"Starring: Philip Seymour Hoffma";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=1048613;
 //BA.debugLineNum = 1048613;BA.debugLine="Year3.Text = \"(2012)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=1048614;
 //BA.debugLineNum = 1048614;BA.debugLine="OverView3.Text = \"A mentally unstable WWII veter";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=1048615;
 //BA.debugLineNum = 1048615;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1048616;
 //BA.debugLineNum = 1048616;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=1048618;
 //BA.debugLineNum = 1048618;BA.debugLine="Drama4.Text = \"Million Dollar Baby\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Million Dollar Baby"));
RDebugUtils.currentLine=1048619;
 //BA.debugLineNum = 1048619;BA.debugLine="Starter4.Text = \"Starring: Morgan Freeman, Hilar";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
RDebugUtils.currentLine=1048620;
 //BA.debugLineNum = 1048620;BA.debugLine="Year4.Text = \"(2004)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2004)"));
RDebugUtils.currentLine=1048621;
 //BA.debugLineNum = 1048621;BA.debugLine="OverView4.Text = \"A waitress with dreams of beco";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
RDebugUtils.currentLine=1048622;
 //BA.debugLineNum = 1048622;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1048623;
 //BA.debugLineNum = 1048623;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=1048625;
 //BA.debugLineNum = 1048625;BA.debugLine="Drama5.Text = \"The Bridges of Madison County\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("The Bridges of Madison County"));
RDebugUtils.currentLine=1048626;
 //BA.debugLineNum = 1048626;BA.debugLine="Starter5.Text = \"Starring: Clint Eastwood, Meryl";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
RDebugUtils.currentLine=1048627;
 //BA.debugLineNum = 1048627;BA.debugLine="Year5.Text = \"(1995)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(1995)"));
RDebugUtils.currentLine=1048628;
 //BA.debugLineNum = 1048628;BA.debugLine="OverView5.Text = \"A brief, passionate romance be";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
RDebugUtils.currentLine=1048629;
 //BA.debugLineNum = 1048629;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
mostCurrent._dramaimage5.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1048630;
 //BA.debugLineNum = 1048630;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage5.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=1048633;
 //BA.debugLineNum = 1048633;BA.debugLine="Drama6.Text = \"Gone Baby Gone\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("Gone Baby Gone"));
RDebugUtils.currentLine=1048634;
 //BA.debugLineNum = 1048634;BA.debugLine="Starter6.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=1048635;
 //BA.debugLineNum = 1048635;BA.debugLine="Year6.Text = \"(2007)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2007)"));
RDebugUtils.currentLine=1048636;
 //BA.debugLineNum = 1048636;BA.debugLine="OverView6.Text = \"In a tough Boston neighborhood";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
RDebugUtils.currentLine=1048637;
 //BA.debugLineNum = 1048637;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
mostCurrent._dramaimage6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1048638;
 //BA.debugLineNum = 1048638;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage6.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=1048640;
 //BA.debugLineNum = 1048640;BA.debugLine="Drama7.Text = \"Blue Jasmine\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=1048641;
 //BA.debugLineNum = 1048641;BA.debugLine="Starter7.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=1048642;
 //BA.debugLineNum = 1048642;BA.debugLine="Year7.Text = \"(2013)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1048643;
 //BA.debugLineNum = 1048643;BA.debugLine="OverView7.Text = \"After losing her fortune and s";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=1048644;
 //BA.debugLineNum = 1048644;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1048645;
 //BA.debugLineNum = 1048645;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=1048647;
 //BA.debugLineNum = 1048647;BA.debugLine="Drama8.Text = \"Her\"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("Her"));
RDebugUtils.currentLine=1048648;
 //BA.debugLineNum = 1048648;BA.debugLine="Starter8.Text = \"Starring: Joaquin Phoenix, Roon";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
RDebugUtils.currentLine=1048649;
 //BA.debugLineNum = 1048649;BA.debugLine="Year8.Text = \"(2013)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1048650;
 //BA.debugLineNum = 1048650;BA.debugLine="OverView8.Text = \"In a near-future Los Angeles,";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
RDebugUtils.currentLine=1048651;
 //BA.debugLineNum = 1048651;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
mostCurrent._dramaimage8.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1048652;
 //BA.debugLineNum = 1048652;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage8.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=1048654;
 //BA.debugLineNum = 1048654;BA.debugLine="Drama9.Text = \"Carol\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=1048655;
 //BA.debugLineNum = 1048655;BA.debugLine="Starter9.Text = \"Starring: Rooney Mara, Cate Bla";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=1048656;
 //BA.debugLineNum = 1048656;BA.debugLine="Year9.Text = \"(2015)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=1048657;
 //BA.debugLineNum = 1048657;BA.debugLine="OverView9.Text = \"A chance encounter between a y";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=1048658;
 //BA.debugLineNum = 1048658;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
mostCurrent._dramaimage9.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1048659;
 //BA.debugLineNum = 1048659;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage9.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=1048661;
 //BA.debugLineNum = 1048661;BA.debugLine="Drama10.Text = \"The Lost Daughter\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("The Lost Daughter"));
RDebugUtils.currentLine=1048662;
 //BA.debugLineNum = 1048662;BA.debugLine="Starter10.Text = \"Starring: Olivia Colman, Dakot";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Olivia Colman, Dakota Johnson, Jessie Buckley"));
RDebugUtils.currentLine=1048663;
 //BA.debugLineNum = 1048663;BA.debugLine="Year10.Text = \"(2021)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2021)"));
RDebugUtils.currentLine=1048664;
 //BA.debugLineNum = 1048664;BA.debugLine="OverView10.Text = \"A solitary woman on vacation";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity"));
RDebugUtils.currentLine=1048665;
 //BA.debugLineNum = 1048665;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1048666;
 //BA.debugLineNum = 1048666;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
 };
RDebugUtils.currentLine=1048672;
 //BA.debugLineNum = 1048672;BA.debugLine="End Sub";
return "";
}
}