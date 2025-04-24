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
RDebugUtils.currentLine=1507328;
 //BA.debugLineNum = 1507328;BA.debugLine="Private Sub ActionPage_Click";
RDebugUtils.currentLine=1507329;
 //BA.debugLineNum = 1507329;BA.debugLine="StartActivity(Action)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._action.getObject()));
RDebugUtils.currentLine=1507330;
 //BA.debugLineNum = 1507330;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=1507331;
 //BA.debugLineNum = 1507331;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=1048585;
 //BA.debugLineNum = 1048585;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"avengers.jpeg").getObject()));
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
RDebugUtils.currentLine=1048592;
 //BA.debugLineNum = 1048592;BA.debugLine="Drama2.Text = \"Manchester by the Sea\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Manchester by the Sea"));
RDebugUtils.currentLine=1048593;
 //BA.debugLineNum = 1048593;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michell";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
RDebugUtils.currentLine=1048594;
 //BA.debugLineNum = 1048594;BA.debugLine="Year2.Text = \"(2016)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=1048595;
 //BA.debugLineNum = 1048595;BA.debugLine="OverView2.Text = \"After the death of his brother,";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
RDebugUtils.currentLine=1048597;
 //BA.debugLineNum = 1048597;BA.debugLine="Drama6.Text = \"Gone Baby Gone\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("Gone Baby Gone"));
RDebugUtils.currentLine=1048598;
 //BA.debugLineNum = 1048598;BA.debugLine="Starter6.Text = \"Starring: Casey Affleck, Michell";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=1048599;
 //BA.debugLineNum = 1048599;BA.debugLine="Year6.Text = \"(2007)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2007)"));
RDebugUtils.currentLine=1048600;
 //BA.debugLineNum = 1048600;BA.debugLine="OverView6.Text = \"In a tough Boston neighborhood,";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
RDebugUtils.currentLine=1048602;
 //BA.debugLineNum = 1048602;BA.debugLine="Drama4.Text = \"Million Dollar Baby\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Million Dollar Baby"));
RDebugUtils.currentLine=1048603;
 //BA.debugLineNum = 1048603;BA.debugLine="Starter4.Text = \"Starring: Morgan Freeman, Hilary";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
RDebugUtils.currentLine=1048604;
 //BA.debugLineNum = 1048604;BA.debugLine="Year4.Text = \"(2004)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2004)"));
RDebugUtils.currentLine=1048605;
 //BA.debugLineNum = 1048605;BA.debugLine="OverView4.Text = \"A waitress with dreams of becom";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
RDebugUtils.currentLine=1048607;
 //BA.debugLineNum = 1048607;BA.debugLine="Drama5.Text = \"The Bridges of Madison County\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("The Bridges of Madison County"));
RDebugUtils.currentLine=1048608;
 //BA.debugLineNum = 1048608;BA.debugLine="Starter5.Text = \"Starring: Clint Eastwood, Meryl";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
RDebugUtils.currentLine=1048609;
 //BA.debugLineNum = 1048609;BA.debugLine="Year5.Text = \"(1995)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(1995)"));
RDebugUtils.currentLine=1048610;
 //BA.debugLineNum = 1048610;BA.debugLine="OverView5.Text = \"A brief, passionate romance bet";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
RDebugUtils.currentLine=1048612;
 //BA.debugLineNum = 1048612;BA.debugLine="Drama3.Text = \"The Master\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=1048613;
 //BA.debugLineNum = 1048613;BA.debugLine="Starter3.Text = \"Starring: Philip Seymour Hoffman";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=1048614;
 //BA.debugLineNum = 1048614;BA.debugLine="Year3.Text = \"(2012)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=1048615;
 //BA.debugLineNum = 1048615;BA.debugLine="OverView3.Text = \"A mentally unstable WWII vetera";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=1048617;
 //BA.debugLineNum = 1048617;BA.debugLine="Drama7.Text = \"Blue Jasmine\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=1048618;
 //BA.debugLineNum = 1048618;BA.debugLine="Starter7.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=1048619;
 //BA.debugLineNum = 1048619;BA.debugLine="Year7.Text = \"(2013)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1048620;
 //BA.debugLineNum = 1048620;BA.debugLine="OverView7.Text = \"After losing her fortune and st";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=1048622;
 //BA.debugLineNum = 1048622;BA.debugLine="Drama8.Text = \"Her\"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("Her"));
RDebugUtils.currentLine=1048623;
 //BA.debugLineNum = 1048623;BA.debugLine="Starter8.Text = \"Starring: Joaquin Phoenix, Roone";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
RDebugUtils.currentLine=1048624;
 //BA.debugLineNum = 1048624;BA.debugLine="Year8.Text = \"(2013)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1048625;
 //BA.debugLineNum = 1048625;BA.debugLine="OverView8.Text = \"In a near-future Los Angeles, a";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
RDebugUtils.currentLine=1048627;
 //BA.debugLineNum = 1048627;BA.debugLine="Drama9.Text = \"Carol\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=1048628;
 //BA.debugLineNum = 1048628;BA.debugLine="Starter9.Text = \"Starring: Rooney Mara, Cate Blan";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=1048629;
 //BA.debugLineNum = 1048629;BA.debugLine="Year9.Text = \"(2015)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=1048630;
 //BA.debugLineNum = 1048630;BA.debugLine="OverView9.Text = \"A chance encounter between a yo";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=1048632;
 //BA.debugLineNum = 1048632;BA.debugLine="Drama10.Text = \"The Lost Daughter\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("The Lost Daughter"));
RDebugUtils.currentLine=1048633;
 //BA.debugLineNum = 1048633;BA.debugLine="Starter10.Text = \"Starring: Olivia Colman, Dakota";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Olivia Colman, Dakota Johnson, Jessie Buckley"));
RDebugUtils.currentLine=1048634;
 //BA.debugLineNum = 1048634;BA.debugLine="Year10.Text = \"(2021)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2021)"));
RDebugUtils.currentLine=1048635;
 //BA.debugLineNum = 1048635;BA.debugLine="OverView10.Text = \"A solitary woman on vacation b";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity"));
RDebugUtils.currentLine=1048637;
 //BA.debugLineNum = 1048637;BA.debugLine="ScrollView1.Panel.AddView(p, 0, 0, 200%x, 210%y)";
mostCurrent._scrollview1.getPanel().AddView((android.view.View)(mostCurrent._p.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=1048638;
 //BA.debugLineNum = 1048638;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=1048639;
 //BA.debugLineNum = 1048639;BA.debugLine="End Sub";
return "";
}
public static String  _dramapage_click() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "dramapage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "dramapage_click", null));}
RDebugUtils.currentLine=1441792;
 //BA.debugLineNum = 1441792;BA.debugLine="Private Sub DramaPage_Click";
RDebugUtils.currentLine=1441794;
 //BA.debugLineNum = 1441794;BA.debugLine="End Sub";
return "";
}
public static String  _hideallpanels() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "hideallpanels", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "hideallpanels", null));}
RDebugUtils.currentLine=1179648;
 //BA.debugLineNum = 1179648;BA.debugLine="Private Sub HideAllPanels";
RDebugUtils.currentLine=1179649;
 //BA.debugLineNum = 1179649;BA.debugLine="Panel2.Visible = False";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1179650;
 //BA.debugLineNum = 1179650;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1179651;
 //BA.debugLineNum = 1179651;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1179652;
 //BA.debugLineNum = 1179652;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1179653;
 //BA.debugLineNum = 1179653;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1179654;
 //BA.debugLineNum = 1179654;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1179655;
 //BA.debugLineNum = 1179655;BA.debugLine="Panel8.Visible = False";
mostCurrent._panel8.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1179656;
 //BA.debugLineNum = 1179656;BA.debugLine="Panel9.Visible = False";
mostCurrent._panel9.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1179657;
 //BA.debugLineNum = 1179657;BA.debugLine="Panel10.Visible = False";
mostCurrent._panel10.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1179658;
 //BA.debugLineNum = 1179658;BA.debugLine="Panel11.Visible = False";
mostCurrent._panel11.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1179659;
 //BA.debugLineNum = 1179659;BA.debugLine="End Sub";
return "";
}
public static String  _homepage_click() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "homepage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "homepage_click", null));}
RDebugUtils.currentLine=1376256;
 //BA.debugLineNum = 1376256;BA.debugLine="Private Sub HomePage_Click";
RDebugUtils.currentLine=1376257;
 //BA.debugLineNum = 1376257;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
RDebugUtils.currentLine=1376258;
 //BA.debugLineNum = 1376258;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=1376259;
 //BA.debugLineNum = 1376259;BA.debugLine="End Sub";
return "";
}
public static String  _scifipage_click() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "scifipage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "scifipage_click", null));}
RDebugUtils.currentLine=1310720;
 //BA.debugLineNum = 1310720;BA.debugLine="Private Sub SciFiPage_Click";
RDebugUtils.currentLine=1310721;
 //BA.debugLineNum = 1310721;BA.debugLine="StartActivity(SciFi)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._scifi.getObject()));
RDebugUtils.currentLine=1310722;
 //BA.debugLineNum = 1310722;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=1310723;
 //BA.debugLineNum = 1310723;BA.debugLine="End Sub";
return "";
}
public static String  _searchbtn_click() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchbtn_click", null));}
String _query = "";
RDebugUtils.currentLine=1114112;
 //BA.debugLineNum = 1114112;BA.debugLine="Private Sub SearchBtn_Click";
RDebugUtils.currentLine=1114113;
 //BA.debugLineNum = 1114113;BA.debugLine="Dim query As String = SearchEngine.Text.ToLowerCa";
_query = mostCurrent._searchengine.getText().toLowerCase().trim();
RDebugUtils.currentLine=1114118;
 //BA.debugLineNum = 1114118;BA.debugLine="If query.Contains(\"kramer\") Or query.Contains(\"kr";
if (_query.contains("kramer") || _query.contains("kramer vs kramer")) { 
RDebugUtils.currentLine=1114120;
 //BA.debugLineNum = 1114120;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Kramer vs. Kramer"));
RDebugUtils.currentLine=1114121;
 //BA.debugLineNum = 1114121;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
RDebugUtils.currentLine=1114122;
 //BA.debugLineNum = 1114122;BA.debugLine="Year1.Text = \"(1979)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1979)"));
RDebugUtils.currentLine=1114123;
 //BA.debugLineNum = 1114123;BA.debugLine="OverView1.Text = \"In this emotionally charged co";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
RDebugUtils.currentLine=1114125;
 //BA.debugLineNum = 1114125;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114126;
 //BA.debugLineNum = 1114126;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114127;
 //BA.debugLineNum = 1114127;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114128;
 //BA.debugLineNum = 1114128;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114129;
 //BA.debugLineNum = 1114129;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114131;
 //BA.debugLineNum = 1114131;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114133;
 //BA.debugLineNum = 1114133;BA.debugLine="Else If query.Contains(\"manchester by the Sea\") O";
if (_query.contains("manchester by the Sea") || _query.contains("manchester")) { 
RDebugUtils.currentLine=1114135;
 //BA.debugLineNum = 1114135;BA.debugLine="Drama1.Text = \"Manchester by the Sea\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Manchester by the Sea"));
RDebugUtils.currentLine=1114136;
 //BA.debugLineNum = 1114136;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
RDebugUtils.currentLine=1114137;
 //BA.debugLineNum = 1114137;BA.debugLine="Year1.Text = \"(2016)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=1114138;
 //BA.debugLineNum = 1114138;BA.debugLine="OverView1.Text = \"After the death of his brother";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
RDebugUtils.currentLine=1114140;
 //BA.debugLineNum = 1114140;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114141;
 //BA.debugLineNum = 1114141;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114142;
 //BA.debugLineNum = 1114142;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114143;
 //BA.debugLineNum = 1114143;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114144;
 //BA.debugLineNum = 1114144;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114146;
 //BA.debugLineNum = 1114146;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114148;
 //BA.debugLineNum = 1114148;BA.debugLine="Else If query.Contains(\"the master\") Or query.Con";
if (_query.contains("the master") || _query.contains("master")) { 
RDebugUtils.currentLine=1114150;
 //BA.debugLineNum = 1114150;BA.debugLine="Drama1.Text = \"The Master\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=1114151;
 //BA.debugLineNum = 1114151;BA.debugLine="Starter1.Text = \"Starring: Philip Seymour Hoffma";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=1114152;
 //BA.debugLineNum = 1114152;BA.debugLine="Year1.Text = \"(2012)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=1114153;
 //BA.debugLineNum = 1114153;BA.debugLine="OverView1.Text = \"A mentally unstable WWII veter";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=1114155;
 //BA.debugLineNum = 1114155;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114156;
 //BA.debugLineNum = 1114156;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114157;
 //BA.debugLineNum = 1114157;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114158;
 //BA.debugLineNum = 1114158;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114159;
 //BA.debugLineNum = 1114159;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114161;
 //BA.debugLineNum = 1114161;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114163;
 //BA.debugLineNum = 1114163;BA.debugLine="Else If query.Contains(\"million dollar\") Or query";
if (_query.contains("million dollar") || _query.contains("million") || _query.contains("million dollar baby")) { 
RDebugUtils.currentLine=1114165;
 //BA.debugLineNum = 1114165;BA.debugLine="Drama1.Text = \"Million Dollar Baby\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Million Dollar Baby"));
RDebugUtils.currentLine=1114166;
 //BA.debugLineNum = 1114166;BA.debugLine="Starter1.Text = \"Starring: Morgan Freeman, Hilar";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
RDebugUtils.currentLine=1114167;
 //BA.debugLineNum = 1114167;BA.debugLine="Year1.Text = \"(2004)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2004)"));
RDebugUtils.currentLine=1114168;
 //BA.debugLineNum = 1114168;BA.debugLine="OverView1.Text = \"A waitress with dreams of beco";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
RDebugUtils.currentLine=1114171;
 //BA.debugLineNum = 1114171;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114172;
 //BA.debugLineNum = 1114172;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114173;
 //BA.debugLineNum = 1114173;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114174;
 //BA.debugLineNum = 1114174;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114175;
 //BA.debugLineNum = 1114175;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114177;
 //BA.debugLineNum = 1114177;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114179;
 //BA.debugLineNum = 1114179;BA.debugLine="Else If query.Contains(\"the Bridges of madison co";
if (_query.contains("the Bridges of madison country") || _query.contains("Bridge") || _query.contains("bridges of madison")) { 
RDebugUtils.currentLine=1114181;
 //BA.debugLineNum = 1114181;BA.debugLine="Drama1.Text = \"The Bridges of Madison County\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Bridges of Madison County"));
RDebugUtils.currentLine=1114182;
 //BA.debugLineNum = 1114182;BA.debugLine="Starter1.Text = \"Starring: Clint Eastwood, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
RDebugUtils.currentLine=1114183;
 //BA.debugLineNum = 1114183;BA.debugLine="Year1.Text = \"(1995)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1995)"));
RDebugUtils.currentLine=1114184;
 //BA.debugLineNum = 1114184;BA.debugLine="OverView1.Text = \"A brief, passionate romance be";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
RDebugUtils.currentLine=1114186;
 //BA.debugLineNum = 1114186;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114187;
 //BA.debugLineNum = 1114187;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114188;
 //BA.debugLineNum = 1114188;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114189;
 //BA.debugLineNum = 1114189;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114190;
 //BA.debugLineNum = 1114190;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114192;
 //BA.debugLineNum = 1114192;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114194;
 //BA.debugLineNum = 1114194;BA.debugLine="Else If query.Contains(\"gone\") Or query.Contains(";
if (_query.contains("gone") || _query.contains("gone baby gone") || _query.contains("gone baby")) { 
RDebugUtils.currentLine=1114196;
 //BA.debugLineNum = 1114196;BA.debugLine="Drama1.Text = \"Gone Baby Gone\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Gone Baby Gone"));
RDebugUtils.currentLine=1114197;
 //BA.debugLineNum = 1114197;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=1114198;
 //BA.debugLineNum = 1114198;BA.debugLine="Year1.Text = \"(2007)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2007)"));
RDebugUtils.currentLine=1114199;
 //BA.debugLineNum = 1114199;BA.debugLine="OverView1.Text = \"In a tough Boston neighborhood";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
RDebugUtils.currentLine=1114201;
 //BA.debugLineNum = 1114201;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114202;
 //BA.debugLineNum = 1114202;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114203;
 //BA.debugLineNum = 1114203;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114204;
 //BA.debugLineNum = 1114204;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114205;
 //BA.debugLineNum = 1114205;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114207;
 //BA.debugLineNum = 1114207;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114209;
 //BA.debugLineNum = 1114209;BA.debugLine="Else If query.Contains(\"blue\") Or query.Contains(";
if (_query.contains("blue") || _query.contains("blue jasmine")) { 
RDebugUtils.currentLine=1114212;
 //BA.debugLineNum = 1114212;BA.debugLine="Drama1.Text = \"Blue Jasmine\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=1114213;
 //BA.debugLineNum = 1114213;BA.debugLine="Starter1.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=1114214;
 //BA.debugLineNum = 1114214;BA.debugLine="Year1.Text = \"(2013)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1114215;
 //BA.debugLineNum = 1114215;BA.debugLine="OverView1.Text = \"After losing her fortune and s";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=1114217;
 //BA.debugLineNum = 1114217;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114218;
 //BA.debugLineNum = 1114218;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114219;
 //BA.debugLineNum = 1114219;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114220;
 //BA.debugLineNum = 1114220;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114221;
 //BA.debugLineNum = 1114221;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114223;
 //BA.debugLineNum = 1114223;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114225;
 //BA.debugLineNum = 1114225;BA.debugLine="Else If query.Contains(\"her\") Then";
if (_query.contains("her")) { 
RDebugUtils.currentLine=1114227;
 //BA.debugLineNum = 1114227;BA.debugLine="Drama1.Text = \"Her\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Her"));
RDebugUtils.currentLine=1114228;
 //BA.debugLineNum = 1114228;BA.debugLine="Starter1.Text = \"Starring: Joaquin Phoenix, Roon";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
RDebugUtils.currentLine=1114229;
 //BA.debugLineNum = 1114229;BA.debugLine="Year1.Text = \"(2013)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1114230;
 //BA.debugLineNum = 1114230;BA.debugLine="OverView1.Text = \"In a near-future Los Angeles,";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
RDebugUtils.currentLine=1114232;
 //BA.debugLineNum = 1114232;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114233;
 //BA.debugLineNum = 1114233;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114234;
 //BA.debugLineNum = 1114234;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114235;
 //BA.debugLineNum = 1114235;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114236;
 //BA.debugLineNum = 1114236;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114238;
 //BA.debugLineNum = 1114238;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114240;
 //BA.debugLineNum = 1114240;BA.debugLine="Else If query.Contains(\"carol\") Or query.Contains";
if (_query.contains("carol") || _query.contains("sarah paulson") || _query.contains("sarah") || _query.contains("paulson")) { 
RDebugUtils.currentLine=1114242;
 //BA.debugLineNum = 1114242;BA.debugLine="Drama1.Text = \"Carol\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=1114243;
 //BA.debugLineNum = 1114243;BA.debugLine="Starter1.Text = \"Starring: Rooney Mara, Cate Bla";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=1114244;
 //BA.debugLineNum = 1114244;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=1114245;
 //BA.debugLineNum = 1114245;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=1114247;
 //BA.debugLineNum = 1114247;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114248;
 //BA.debugLineNum = 1114248;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114249;
 //BA.debugLineNum = 1114249;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114250;
 //BA.debugLineNum = 1114250;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114251;
 //BA.debugLineNum = 1114251;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114253;
 //BA.debugLineNum = 1114253;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114255;
 //BA.debugLineNum = 1114255;BA.debugLine="Else If query.Contains(\"the lost daugther\") Or qu";
if (_query.contains("the lost daugther") || _query.contains("lost daughter") || _query.contains("lost")) { 
RDebugUtils.currentLine=1114257;
 //BA.debugLineNum = 1114257;BA.debugLine="Drama1.Text = \"The Lost Daughter\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Lost Daughter"));
RDebugUtils.currentLine=1114258;
 //BA.debugLineNum = 1114258;BA.debugLine="Starter1.Text = \"Starring: Olivia Colman, Dakota";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Olivia Colman, Dakota Johnson, Jessie Buckley"));
RDebugUtils.currentLine=1114259;
 //BA.debugLineNum = 1114259;BA.debugLine="Year1.Text = \"(2021)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2021)"));
RDebugUtils.currentLine=1114260;
 //BA.debugLineNum = 1114260;BA.debugLine="OverView1.Text = \"A solitary woman on vacation b";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity"));
RDebugUtils.currentLine=1114262;
 //BA.debugLineNum = 1114262;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114263;
 //BA.debugLineNum = 1114263;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114264;
 //BA.debugLineNum = 1114264;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114265;
 //BA.debugLineNum = 1114265;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114266;
 //BA.debugLineNum = 1114266;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114268;
 //BA.debugLineNum = 1114268;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114270;
 //BA.debugLineNum = 1114270;BA.debugLine="Else If query.Contains(\"meryl streep\") Or query.C";
if (_query.contains("meryl streep") || _query.contains("meryl") || _query.contains("streep")) { 
RDebugUtils.currentLine=1114271;
 //BA.debugLineNum = 1114271;BA.debugLine="Drama1.Text = \"The Bridges of Madison County\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Bridges of Madison County"));
RDebugUtils.currentLine=1114272;
 //BA.debugLineNum = 1114272;BA.debugLine="Starter1.Text = \"Starring: Clint Eastwood, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
RDebugUtils.currentLine=1114273;
 //BA.debugLineNum = 1114273;BA.debugLine="Year1.Text = \"(1995)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1995)"));
RDebugUtils.currentLine=1114274;
 //BA.debugLineNum = 1114274;BA.debugLine="OverView1.Text = \"A brief, passionate romance be";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
RDebugUtils.currentLine=1114276;
 //BA.debugLineNum = 1114276;BA.debugLine="Drama2.Text = \"Doubt\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Doubt"));
RDebugUtils.currentLine=1114277;
 //BA.debugLineNum = 1114277;BA.debugLine="Starter2.Text = \"Starring: Meryl Streep, Philip";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Meryl Streep, Philip Seymour Hoffman, Amy Adams"));
RDebugUtils.currentLine=1114278;
 //BA.debugLineNum = 1114278;BA.debugLine="Year2.Text = \"(2008)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=1114279;
 //BA.debugLineNum = 1114279;BA.debugLine="OverView2.Text = \"In a Catholic school in the Br";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a Catholic school in the Bronx, a strict nun becomes suspicious of a priest’s relationship with a student. As she digs deeper, the lines between truth and suspicion blur, leaving everyone in moral and emotional limbo."));
RDebugUtils.currentLine=1114281;
 //BA.debugLineNum = 1114281;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114282;
 //BA.debugLineNum = 1114282;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114283;
 //BA.debugLineNum = 1114283;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114284;
 //BA.debugLineNum = 1114284;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114285;
 //BA.debugLineNum = 1114285;BA.debugLine="Else If query.Contains(\"casey\") Or query.Contains";
if (_query.contains("casey") || _query.contains("casey affleck")) { 
RDebugUtils.currentLine=1114286;
 //BA.debugLineNum = 1114286;BA.debugLine="Drama1.Text = \"Gone Baby Gone\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Gone Baby Gone"));
RDebugUtils.currentLine=1114287;
 //BA.debugLineNum = 1114287;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=1114288;
 //BA.debugLineNum = 1114288;BA.debugLine="Year1.Text = \"(2007)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2007)"));
RDebugUtils.currentLine=1114289;
 //BA.debugLineNum = 1114289;BA.debugLine="OverView1.Text = \"In a tough Boston neighborhood";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
RDebugUtils.currentLine=1114291;
 //BA.debugLineNum = 1114291;BA.debugLine="Drama2.Text = \"Manchester by the Sea\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Manchester by the Sea"));
RDebugUtils.currentLine=1114292;
 //BA.debugLineNum = 1114292;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
RDebugUtils.currentLine=1114293;
 //BA.debugLineNum = 1114293;BA.debugLine="Year2.Text = \"(2016)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=1114294;
 //BA.debugLineNum = 1114294;BA.debugLine="OverView2.Text = \"After the death of his brother";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
RDebugUtils.currentLine=1114296;
 //BA.debugLineNum = 1114296;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114297;
 //BA.debugLineNum = 1114297;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114298;
 //BA.debugLineNum = 1114298;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114299;
 //BA.debugLineNum = 1114299;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114301;
 //BA.debugLineNum = 1114301;BA.debugLine="Else If query.Contains(\"joaquin\") Or query.Contai";
if (_query.contains("joaquin") || _query.contains("joaquin phoenix") || _query.contains("amy adams") || _query.contains("amy")) { 
RDebugUtils.currentLine=1114302;
 //BA.debugLineNum = 1114302;BA.debugLine="Drama1.Text = \"The Master\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=1114303;
 //BA.debugLineNum = 1114303;BA.debugLine="Starter1.Text = \"Starring: Philip Seymour Hoffma";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=1114304;
 //BA.debugLineNum = 1114304;BA.debugLine="Year1.Text = \"(2012)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=1114305;
 //BA.debugLineNum = 1114305;BA.debugLine="OverView1.Text = \"A mentally unstable WWII veter";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=1114307;
 //BA.debugLineNum = 1114307;BA.debugLine="Drama2.Text = \"Her\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Her"));
RDebugUtils.currentLine=1114308;
 //BA.debugLineNum = 1114308;BA.debugLine="Starter2.Text = \"Starring: Joaquin Phoenix, Roon";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
RDebugUtils.currentLine=1114309;
 //BA.debugLineNum = 1114309;BA.debugLine="Year2.Text = \"(2013)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1114310;
 //BA.debugLineNum = 1114310;BA.debugLine="OverView2.Text = \"In a near-future Los Angeles,";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
RDebugUtils.currentLine=1114312;
 //BA.debugLineNum = 1114312;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114313;
 //BA.debugLineNum = 1114313;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114314;
 //BA.debugLineNum = 1114314;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114315;
 //BA.debugLineNum = 1114315;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114317;
 //BA.debugLineNum = 1114317;BA.debugLine="Else If query.Contains(\"cate blanchett\") Or query";
if (_query.contains("cate blanchett") || _query.contains("cate")) { 
RDebugUtils.currentLine=1114318;
 //BA.debugLineNum = 1114318;BA.debugLine="Drama1.Text = \"Carol\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=1114319;
 //BA.debugLineNum = 1114319;BA.debugLine="Starter1.Text = \"Starring: Rooney Mara, Cate Bla";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=1114320;
 //BA.debugLineNum = 1114320;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=1114321;
 //BA.debugLineNum = 1114321;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=1114323;
 //BA.debugLineNum = 1114323;BA.debugLine="Drama2.Text = \"Blue Jasmine\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=1114324;
 //BA.debugLineNum = 1114324;BA.debugLine="Starter2.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=1114325;
 //BA.debugLineNum = 1114325;BA.debugLine="Year2.Text = \"(2013)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1114326;
 //BA.debugLineNum = 1114326;BA.debugLine="OverView2.Text = \"After losing her fortune and s";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=1114328;
 //BA.debugLineNum = 1114328;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114329;
 //BA.debugLineNum = 1114329;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114330;
 //BA.debugLineNum = 1114330;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114331;
 //BA.debugLineNum = 1114331;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114332;
 //BA.debugLineNum = 1114332;BA.debugLine="Else If query.Contains(\"morgan freeman\") Or query";
if (_query.contains("morgan freeman") || _query.contains("morgan")) { 
RDebugUtils.currentLine=1114333;
 //BA.debugLineNum = 1114333;BA.debugLine="Drama1.Text = \"Million Dollar Baby\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Million Dollar Baby"));
RDebugUtils.currentLine=1114334;
 //BA.debugLineNum = 1114334;BA.debugLine="Starter1.Text = \"Starring: Morgan Freeman, Hilar";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
RDebugUtils.currentLine=1114335;
 //BA.debugLineNum = 1114335;BA.debugLine="Year1.Text = \"(2004)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2004)"));
RDebugUtils.currentLine=1114336;
 //BA.debugLineNum = 1114336;BA.debugLine="OverView1.Text = \"A waitress with dreams of beco";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
RDebugUtils.currentLine=1114338;
 //BA.debugLineNum = 1114338;BA.debugLine="Drama2.Text = \"Gone Baby Gone\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Gone Baby Gone"));
RDebugUtils.currentLine=1114339;
 //BA.debugLineNum = 1114339;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=1114340;
 //BA.debugLineNum = 1114340;BA.debugLine="Year2.Text = \"(2007)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2007)"));
RDebugUtils.currentLine=1114341;
 //BA.debugLineNum = 1114341;BA.debugLine="OverView2.Text = \"In a tough Boston neighborhood";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
RDebugUtils.currentLine=1114343;
 //BA.debugLineNum = 1114343;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114344;
 //BA.debugLineNum = 1114344;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114345;
 //BA.debugLineNum = 1114345;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114346;
 //BA.debugLineNum = 1114346;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114347;
 //BA.debugLineNum = 1114347;BA.debugLine="Else If query.Contains(\"clint eastwood\") Or query";
if (_query.contains("clint eastwood") || _query.contains("clint") || _query.contains("eastwood")) { 
RDebugUtils.currentLine=1114348;
 //BA.debugLineNum = 1114348;BA.debugLine="Drama1.Text = \"Million Dollar Baby\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Million Dollar Baby"));
RDebugUtils.currentLine=1114349;
 //BA.debugLineNum = 1114349;BA.debugLine="Starter1.Text = \"Starring: Morgan Freeman, Hilar";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
RDebugUtils.currentLine=1114350;
 //BA.debugLineNum = 1114350;BA.debugLine="Year1.Text = \"(2004)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2004)"));
RDebugUtils.currentLine=1114351;
 //BA.debugLineNum = 1114351;BA.debugLine="OverView1.Text = \"A waitress with dreams of beco";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
RDebugUtils.currentLine=1114353;
 //BA.debugLineNum = 1114353;BA.debugLine="Drama2.Text = \"The Bridges of Madison Country\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("The Bridges of Madison Country"));
RDebugUtils.currentLine=1114354;
 //BA.debugLineNum = 1114354;BA.debugLine="Starter2.Text = \"Starring: Clint Eastwood, Meryl";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
RDebugUtils.currentLine=1114355;
 //BA.debugLineNum = 1114355;BA.debugLine="Year2.Text = \"(1995)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(1995)"));
RDebugUtils.currentLine=1114356;
 //BA.debugLineNum = 1114356;BA.debugLine="OverView2.Text = \"A brief, passionate romance be";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
RDebugUtils.currentLine=1114358;
 //BA.debugLineNum = 1114358;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114359;
 //BA.debugLineNum = 1114359;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114360;
 //BA.debugLineNum = 1114360;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114361;
 //BA.debugLineNum = 1114361;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114362;
 //BA.debugLineNum = 1114362;BA.debugLine="Else If query.Contains(\"amy Adams\") Or query.Cont";
if (_query.contains("amy Adams") || _query.contains("amy") || _query.contains("adams")) { 
RDebugUtils.currentLine=1114363;
 //BA.debugLineNum = 1114363;BA.debugLine="Drama1.Text = \"Doubt\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Doubt"));
RDebugUtils.currentLine=1114364;
 //BA.debugLineNum = 1114364;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=1114365;
 //BA.debugLineNum = 1114365;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=1114366;
 //BA.debugLineNum = 1114366;BA.debugLine="OverView1.Text = \"In a Catholic school in the Br";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a Catholic school in the Bronx, a strict nun becomes suspicious of a priest’s relationship with a student. As she digs deeper, the lines between truth and suspicion blur, leaving everyone in moral and emotional limbo."));
RDebugUtils.currentLine=1114368;
 //BA.debugLineNum = 1114368;BA.debugLine="Drama2.Text = \"The Master\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=1114369;
 //BA.debugLineNum = 1114369;BA.debugLine="Starter2.Text = \"Starring: Philip Seymour Hoffma";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=1114370;
 //BA.debugLineNum = 1114370;BA.debugLine="Year2.Text = \"(2012)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=1114371;
 //BA.debugLineNum = 1114371;BA.debugLine="OverView2.Text = \"A mentally unstable WWII veter";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=1114373;
 //BA.debugLineNum = 1114373;BA.debugLine="Drama3.Text = \"Her\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("Her"));
RDebugUtils.currentLine=1114374;
 //BA.debugLineNum = 1114374;BA.debugLine="Starter3.Text = \"Starring: Joaquin Phoenix, Roon";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
RDebugUtils.currentLine=1114375;
 //BA.debugLineNum = 1114375;BA.debugLine="Year3.Text = \"(2013)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1114376;
 //BA.debugLineNum = 1114376;BA.debugLine="OverView3.Text = \"In a near-future Los Angeles,";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
RDebugUtils.currentLine=1114378;
 //BA.debugLineNum = 1114378;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114379;
 //BA.debugLineNum = 1114379;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114380;
 //BA.debugLineNum = 1114380;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114381;
 //BA.debugLineNum = 1114381;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114382;
 //BA.debugLineNum = 1114382;BA.debugLine="Else If query.Contains(\"rooney mara\") Or query.Co";
if (_query.contains("rooney mara") || _query.contains("rooney") || _query.contains("mara") || _query.contains("sarah") || _query.contains("sarah paulson") || _query.contains("paulson")) { 
RDebugUtils.currentLine=1114383;
 //BA.debugLineNum = 1114383;BA.debugLine="Drama1.Text = \"Carol\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=1114384;
 //BA.debugLineNum = 1114384;BA.debugLine="Starter1.Text = \"Starring: Rooney Mara, Cate Bla";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=1114385;
 //BA.debugLineNum = 1114385;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=1114386;
 //BA.debugLineNum = 1114386;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=1114388;
 //BA.debugLineNum = 1114388;BA.debugLine="Drama2.Text = \"Blue Jasmine\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=1114389;
 //BA.debugLineNum = 1114389;BA.debugLine="Starter2.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=1114390;
 //BA.debugLineNum = 1114390;BA.debugLine="Year2.Text = \"(2013)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1114391;
 //BA.debugLineNum = 1114391;BA.debugLine="OverView2.Text = \"After losing her fortune and s";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=1114393;
 //BA.debugLineNum = 1114393;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114394;
 //BA.debugLineNum = 1114394;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114395;
 //BA.debugLineNum = 1114395;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114396;
 //BA.debugLineNum = 1114396;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114398;
 //BA.debugLineNum = 1114398;BA.debugLine="Else If query.Contains(\"olvia coloman\") Or query.";
if (_query.contains("olvia coloman") || _query.contains("dakota johnson") || _query.contains("jessie buckley") || _query.contains("olvia") || _query.contains("jessie ") || _query.contains("dakota") || _query.contains("buckley") || _query.contains("coloman") || _query.contains("johnson")) { 
RDebugUtils.currentLine=1114399;
 //BA.debugLineNum = 1114399;BA.debugLine="Drama1.Text = \"Carol\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=1114400;
 //BA.debugLineNum = 1114400;BA.debugLine="Starter1.Text = \"Starring: Rooney Mara, Cate Bla";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=1114401;
 //BA.debugLineNum = 1114401;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=1114402;
 //BA.debugLineNum = 1114402;BA.debugLine="OverView1.Text = \"A solitary woman on vacation b";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity."));
RDebugUtils.currentLine=1114404;
 //BA.debugLineNum = 1114404;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114405;
 //BA.debugLineNum = 1114405;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114406;
 //BA.debugLineNum = 1114406;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114407;
 //BA.debugLineNum = 1114407;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114408;
 //BA.debugLineNum = 1114408;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114410;
 //BA.debugLineNum = 1114410;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114411;
 //BA.debugLineNum = 1114411;BA.debugLine="Else If query.Contains(\"sally hawkins\") Or query.";
if (_query.contains("sally hawkins") || _query.contains("alec baldwin") || _query.contains("sally") || _query.contains("haswkins") || _query.contains("alec") || _query.contains("baldwin")) { 
RDebugUtils.currentLine=1114412;
 //BA.debugLineNum = 1114412;BA.debugLine="Drama1.Text = \"Blue Jasmine\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=1114413;
 //BA.debugLineNum = 1114413;BA.debugLine="Starter1.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=1114414;
 //BA.debugLineNum = 1114414;BA.debugLine="Year1.Text = \"(2013)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1114415;
 //BA.debugLineNum = 1114415;BA.debugLine="OverView1.Text = \"After losing her fortune and s";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=1114417;
 //BA.debugLineNum = 1114417;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114418;
 //BA.debugLineNum = 1114418;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114419;
 //BA.debugLineNum = 1114419;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114420;
 //BA.debugLineNum = 1114420;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114421;
 //BA.debugLineNum = 1114421;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114423;
 //BA.debugLineNum = 1114423;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114424;
 //BA.debugLineNum = 1114424;BA.debugLine="Else if query.Contains(\"michelle williams\") Or qu";
if (_query.contains("michelle williams") || _query.contains("lucas hedges") || _query.contains("michelle") || _query.contains("williams") || _query.contains("lucas") || _query.contains("hedges")) { 
RDebugUtils.currentLine=1114425;
 //BA.debugLineNum = 1114425;BA.debugLine="Drama1.Text = \"Manchester by the Sea\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Manchester by the Sea"));
RDebugUtils.currentLine=1114426;
 //BA.debugLineNum = 1114426;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
RDebugUtils.currentLine=1114427;
 //BA.debugLineNum = 1114427;BA.debugLine="Year1.Text = \"(2016)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=1114428;
 //BA.debugLineNum = 1114428;BA.debugLine="OverView1.Text = \"After the death of his brother";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
RDebugUtils.currentLine=1114430;
 //BA.debugLineNum = 1114430;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114431;
 //BA.debugLineNum = 1114431;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114432;
 //BA.debugLineNum = 1114432;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114433;
 //BA.debugLineNum = 1114433;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114434;
 //BA.debugLineNum = 1114434;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114436;
 //BA.debugLineNum = 1114436;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=1114437;
 //BA.debugLineNum = 1114437;BA.debugLine="Else if query.Contains(\"dustin hoffman\") Or query";
if (_query.contains("dustin hoffman") || _query.contains("dustin") || _query.contains("hoffman") || _query.contains("justin henry") || _query.contains("justin") || _query.contains("henry")) { 
RDebugUtils.currentLine=1114438;
 //BA.debugLineNum = 1114438;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Kramer vs. Kramer"));
RDebugUtils.currentLine=1114439;
 //BA.debugLineNum = 1114439;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
RDebugUtils.currentLine=1114440;
 //BA.debugLineNum = 1114440;BA.debugLine="Year1.Text = \"(1979)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1979)"));
RDebugUtils.currentLine=1114441;
 //BA.debugLineNum = 1114441;BA.debugLine="OverView1.Text = \"In this emotionally charged co";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
RDebugUtils.currentLine=1114443;
 //BA.debugLineNum = 1114443;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114444;
 //BA.debugLineNum = 1114444;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114445;
 //BA.debugLineNum = 1114445;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114446;
 //BA.debugLineNum = 1114446;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114447;
 //BA.debugLineNum = 1114447;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1114449;
 //BA.debugLineNum = 1114449;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else {
RDebugUtils.currentLine=1114451;
 //BA.debugLineNum = 1114451;BA.debugLine="HideAllPanels";
_hideallpanels();
RDebugUtils.currentLine=1114453;
 //BA.debugLineNum = 1114453;BA.debugLine="NotFound.Text = \"Not Found!\"";
mostCurrent._notfound.setText(BA.ObjectToCharSequence("Not Found!"));
 }}}}}}}}}}}}}}}}}}}}}}
;
RDebugUtils.currentLine=1114458;
 //BA.debugLineNum = 1114458;BA.debugLine="p.Width = 100%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=1114459;
 //BA.debugLineNum = 1114459;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=1114460;
 //BA.debugLineNum = 1114460;BA.debugLine="End Sub";
return "";
}
public static String  _searchengine_textchanged(String _old,String _new) throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchengine_textchanged", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchengine_textchanged", new Object[] {_old,_new}));}
String _query = "";
RDebugUtils.currentLine=1245184;
 //BA.debugLineNum = 1245184;BA.debugLine="Sub SearchEngine_TextChanged (Old As String, New A";
RDebugUtils.currentLine=1245185;
 //BA.debugLineNum = 1245185;BA.debugLine="Dim query As String = New.ToLowerCase.Trim";
_query = _new.toLowerCase().trim();
RDebugUtils.currentLine=1245187;
 //BA.debugLineNum = 1245187;BA.debugLine="If query = \"\" Then";
if ((_query).equals("")) { 
RDebugUtils.currentLine=1245189;
 //BA.debugLineNum = 1245189;BA.debugLine="NotFound.Text = \"\"";
mostCurrent._notfound.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=1245190;
 //BA.debugLineNum = 1245190;BA.debugLine="p.Height = 210%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=1245191;
 //BA.debugLineNum = 1245191;BA.debugLine="p.Width = 200%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA));
RDebugUtils.currentLine=1245192;
 //BA.debugLineNum = 1245192;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=1245194;
 //BA.debugLineNum = 1245194;BA.debugLine="Panel2.Visible = True";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1245195;
 //BA.debugLineNum = 1245195;BA.debugLine="Panel3.Visible = True";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1245196;
 //BA.debugLineNum = 1245196;BA.debugLine="Panel4.Visible = True";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1245197;
 //BA.debugLineNum = 1245197;BA.debugLine="Panel5.Visible = True";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1245198;
 //BA.debugLineNum = 1245198;BA.debugLine="Panel6.Visible = True";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1245199;
 //BA.debugLineNum = 1245199;BA.debugLine="Panel7.Visible = True";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1245200;
 //BA.debugLineNum = 1245200;BA.debugLine="Panel8.Visible = True";
mostCurrent._panel8.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1245201;
 //BA.debugLineNum = 1245201;BA.debugLine="Panel9.Visible = True";
mostCurrent._panel9.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1245202;
 //BA.debugLineNum = 1245202;BA.debugLine="Panel10.Visible = True";
mostCurrent._panel10.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1245203;
 //BA.debugLineNum = 1245203;BA.debugLine="Panel11.Visible = True";
mostCurrent._panel11.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1245205;
 //BA.debugLineNum = 1245205;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Kramer vs. Kramer"));
RDebugUtils.currentLine=1245206;
 //BA.debugLineNum = 1245206;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
RDebugUtils.currentLine=1245207;
 //BA.debugLineNum = 1245207;BA.debugLine="Year1.Text = \"(1979)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1979)"));
RDebugUtils.currentLine=1245208;
 //BA.debugLineNum = 1245208;BA.debugLine="OverView1.Text = \"In this emotionally charged co";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
RDebugUtils.currentLine=1245210;
 //BA.debugLineNum = 1245210;BA.debugLine="Drama2.Text = \"Manchester by the Sea\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Manchester by the Sea"));
RDebugUtils.currentLine=1245211;
 //BA.debugLineNum = 1245211;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
RDebugUtils.currentLine=1245212;
 //BA.debugLineNum = 1245212;BA.debugLine="Year2.Text = \"(2016)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=1245213;
 //BA.debugLineNum = 1245213;BA.debugLine="OverView2.Text = \"After the death of his brother";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
RDebugUtils.currentLine=1245215;
 //BA.debugLineNum = 1245215;BA.debugLine="Drama6.Text = \"Gone Baby Gone\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("Gone Baby Gone"));
RDebugUtils.currentLine=1245216;
 //BA.debugLineNum = 1245216;BA.debugLine="Starter6.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=1245217;
 //BA.debugLineNum = 1245217;BA.debugLine="Year6.Text = \"(2007)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2007)"));
RDebugUtils.currentLine=1245218;
 //BA.debugLineNum = 1245218;BA.debugLine="OverView6.Text = \"In a tough Boston neighborhood";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
RDebugUtils.currentLine=1245220;
 //BA.debugLineNum = 1245220;BA.debugLine="Drama4.Text = \"Million Dollar Baby\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Million Dollar Baby"));
RDebugUtils.currentLine=1245221;
 //BA.debugLineNum = 1245221;BA.debugLine="Starter4.Text = \"Starring: Morgan Freeman, Hilar";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
RDebugUtils.currentLine=1245222;
 //BA.debugLineNum = 1245222;BA.debugLine="Year4.Text = \"(2004)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2004)"));
RDebugUtils.currentLine=1245223;
 //BA.debugLineNum = 1245223;BA.debugLine="OverView4.Text = \"A waitress with dreams of beco";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
RDebugUtils.currentLine=1245225;
 //BA.debugLineNum = 1245225;BA.debugLine="Drama5.Text = \"The Bridges of Madison County\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("The Bridges of Madison County"));
RDebugUtils.currentLine=1245226;
 //BA.debugLineNum = 1245226;BA.debugLine="Starter5.Text = \"Starring: Clint Eastwood, Meryl";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
RDebugUtils.currentLine=1245227;
 //BA.debugLineNum = 1245227;BA.debugLine="Year5.Text = \"(1995)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(1995)"));
RDebugUtils.currentLine=1245228;
 //BA.debugLineNum = 1245228;BA.debugLine="OverView5.Text = \"A brief, passionate romance be";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
RDebugUtils.currentLine=1245230;
 //BA.debugLineNum = 1245230;BA.debugLine="Drama3.Text = \"The Master\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=1245231;
 //BA.debugLineNum = 1245231;BA.debugLine="Starter3.Text = \"Starring: Philip Seymour Hoffma";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=1245232;
 //BA.debugLineNum = 1245232;BA.debugLine="Year3.Text = \"(2012)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=1245233;
 //BA.debugLineNum = 1245233;BA.debugLine="OverView3.Text = \"A mentally unstable WWII veter";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=1245235;
 //BA.debugLineNum = 1245235;BA.debugLine="Drama7.Text = \"Blue Jasmine\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=1245236;
 //BA.debugLineNum = 1245236;BA.debugLine="Starter7.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=1245237;
 //BA.debugLineNum = 1245237;BA.debugLine="Year7.Text = \"(2013)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1245238;
 //BA.debugLineNum = 1245238;BA.debugLine="OverView7.Text = \"After losing her fortune and s";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=1245240;
 //BA.debugLineNum = 1245240;BA.debugLine="Drama8.Text = \"Her\"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("Her"));
RDebugUtils.currentLine=1245241;
 //BA.debugLineNum = 1245241;BA.debugLine="Starter8.Text = \"Starring: Joaquin Phoenix, Roon";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
RDebugUtils.currentLine=1245242;
 //BA.debugLineNum = 1245242;BA.debugLine="Year8.Text = \"(2013)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1245243;
 //BA.debugLineNum = 1245243;BA.debugLine="OverView8.Text = \"In a near-future Los Angeles,";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
RDebugUtils.currentLine=1245245;
 //BA.debugLineNum = 1245245;BA.debugLine="Drama9.Text = \"Carol\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=1245246;
 //BA.debugLineNum = 1245246;BA.debugLine="Starter9.Text = \"Starring: Rooney Mara, Cate Bla";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=1245247;
 //BA.debugLineNum = 1245247;BA.debugLine="Year9.Text = \"(2015)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=1245248;
 //BA.debugLineNum = 1245248;BA.debugLine="OverView9.Text = \"A chance encounter between a y";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=1245250;
 //BA.debugLineNum = 1245250;BA.debugLine="Drama10.Text = \"The Lost Daughter\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("The Lost Daughter"));
RDebugUtils.currentLine=1245251;
 //BA.debugLineNum = 1245251;BA.debugLine="Starter10.Text = \"Starring: Olivia Colman, Dakot";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Olivia Colman, Dakota Johnson, Jessie Buckley"));
RDebugUtils.currentLine=1245252;
 //BA.debugLineNum = 1245252;BA.debugLine="Year10.Text = \"(2021)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2021)"));
RDebugUtils.currentLine=1245253;
 //BA.debugLineNum = 1245253;BA.debugLine="OverView10.Text = \"A solitary woman on vacation";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity"));
 };
RDebugUtils.currentLine=1245259;
 //BA.debugLineNum = 1245259;BA.debugLine="End Sub";
return "";
}
}