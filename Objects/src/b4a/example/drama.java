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
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.action _action = null;
public b4a.example.scifi _scifi = null;
public b4a.example.panelview _panelview = null;
public static String  _actionpage_click() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "actionpage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "actionpage_click", null));}
RDebugUtils.currentLine=1441792;
 //BA.debugLineNum = 1441792;BA.debugLine="Private Sub ActionPage_Click";
RDebugUtils.currentLine=1441793;
 //BA.debugLineNum = 1441793;BA.debugLine="StartActivity(Action)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._action.getObject()));
RDebugUtils.currentLine=1441794;
 //BA.debugLineNum = 1441794;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=1048584;
 //BA.debugLineNum = 1048584;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"avengers.jpeg").getObject()));
RDebugUtils.currentLine=1048586;
 //BA.debugLineNum = 1048586;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Kramer vs. Kramer"));
RDebugUtils.currentLine=1048587;
 //BA.debugLineNum = 1048587;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
RDebugUtils.currentLine=1048588;
 //BA.debugLineNum = 1048588;BA.debugLine="Year1.Text = \"(1979)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1979)"));
RDebugUtils.currentLine=1048589;
 //BA.debugLineNum = 1048589;BA.debugLine="OverView1.Text = \"In this emotionally charged cou";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
RDebugUtils.currentLine=1048591;
 //BA.debugLineNum = 1048591;BA.debugLine="Drama2.Text = \"Manchester by the Sea\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Manchester by the Sea"));
RDebugUtils.currentLine=1048592;
 //BA.debugLineNum = 1048592;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michell";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
RDebugUtils.currentLine=1048593;
 //BA.debugLineNum = 1048593;BA.debugLine="Year2.Text = \"(2016)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=1048594;
 //BA.debugLineNum = 1048594;BA.debugLine="OverView2.Text = \"After the death of his brother,";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
RDebugUtils.currentLine=1048596;
 //BA.debugLineNum = 1048596;BA.debugLine="Drama6.Text = \"Gone Baby Gone\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("Gone Baby Gone"));
RDebugUtils.currentLine=1048597;
 //BA.debugLineNum = 1048597;BA.debugLine="Starter6.Text = \"Starring: Casey Affleck, Michell";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=1048598;
 //BA.debugLineNum = 1048598;BA.debugLine="Year6.Text = \"(2007)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2007)"));
RDebugUtils.currentLine=1048599;
 //BA.debugLineNum = 1048599;BA.debugLine="OverView6.Text = \"In a tough Boston neighborhood,";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
RDebugUtils.currentLine=1048601;
 //BA.debugLineNum = 1048601;BA.debugLine="Drama4.Text = \"Million Dollar Baby\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Million Dollar Baby"));
RDebugUtils.currentLine=1048602;
 //BA.debugLineNum = 1048602;BA.debugLine="Starter4.Text = \"Starring: Morgan Freeman, Hilary";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
RDebugUtils.currentLine=1048603;
 //BA.debugLineNum = 1048603;BA.debugLine="Year4.Text = \"(2004)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2004)"));
RDebugUtils.currentLine=1048604;
 //BA.debugLineNum = 1048604;BA.debugLine="OverView4.Text = \"A waitress with dreams of becom";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
RDebugUtils.currentLine=1048606;
 //BA.debugLineNum = 1048606;BA.debugLine="Drama5.Text = \"The Bridges of Madison County\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("The Bridges of Madison County"));
RDebugUtils.currentLine=1048607;
 //BA.debugLineNum = 1048607;BA.debugLine="Starter5.Text = \"Starring: Clint Eastwood, Meryl";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
RDebugUtils.currentLine=1048608;
 //BA.debugLineNum = 1048608;BA.debugLine="Year5.Text = \"(1995)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(1995)"));
RDebugUtils.currentLine=1048609;
 //BA.debugLineNum = 1048609;BA.debugLine="OverView5.Text = \"A brief, passionate romance bet";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
RDebugUtils.currentLine=1048611;
 //BA.debugLineNum = 1048611;BA.debugLine="Drama3.Text = \"The Master\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=1048612;
 //BA.debugLineNum = 1048612;BA.debugLine="Starter3.Text = \"Starring: Philip Seymour Hoffman";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=1048613;
 //BA.debugLineNum = 1048613;BA.debugLine="Year3.Text = \"(2012)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=1048614;
 //BA.debugLineNum = 1048614;BA.debugLine="OverView3.Text = \"A mentally unstable WWII vetera";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=1048616;
 //BA.debugLineNum = 1048616;BA.debugLine="Drama7.Text = \"Blue Jasmine\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=1048617;
 //BA.debugLineNum = 1048617;BA.debugLine="Starter7.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=1048618;
 //BA.debugLineNum = 1048618;BA.debugLine="Year7.Text = \"(2013)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1048619;
 //BA.debugLineNum = 1048619;BA.debugLine="OverView7.Text = \"After losing her fortune and st";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=1048621;
 //BA.debugLineNum = 1048621;BA.debugLine="Drama8.Text = \"Her\"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("Her"));
RDebugUtils.currentLine=1048622;
 //BA.debugLineNum = 1048622;BA.debugLine="Starter8.Text = \"Starring: Joaquin Phoenix, Roone";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
RDebugUtils.currentLine=1048623;
 //BA.debugLineNum = 1048623;BA.debugLine="Year8.Text = \"(2013)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1048624;
 //BA.debugLineNum = 1048624;BA.debugLine="OverView8.Text = \"In a near-future Los Angeles, a";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
RDebugUtils.currentLine=1048626;
 //BA.debugLineNum = 1048626;BA.debugLine="Drama9.Text = \"Carol\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=1048627;
 //BA.debugLineNum = 1048627;BA.debugLine="Starter9.Text = \"Starring: Rooney Mara, Cate Blan";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=1048628;
 //BA.debugLineNum = 1048628;BA.debugLine="Year9.Text = \"(2015)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=1048629;
 //BA.debugLineNum = 1048629;BA.debugLine="OverView9.Text = \"A chance encounter between a yo";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=1048631;
 //BA.debugLineNum = 1048631;BA.debugLine="Drama10.Text = \"The Lost Daughter\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("The Lost Daughter"));
RDebugUtils.currentLine=1048632;
 //BA.debugLineNum = 1048632;BA.debugLine="Starter10.Text = \"Starring: Olivia Colman, Dakota";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Olivia Colman, Dakota Johnson, Jessie Buckley"));
RDebugUtils.currentLine=1048633;
 //BA.debugLineNum = 1048633;BA.debugLine="Year10.Text = \"(2021)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2021)"));
RDebugUtils.currentLine=1048634;
 //BA.debugLineNum = 1048634;BA.debugLine="OverView10.Text = \"A solitary woman on vacation b";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity"));
RDebugUtils.currentLine=1048636;
 //BA.debugLineNum = 1048636;BA.debugLine="ScrollView1.Panel.AddView(p, 0, 0, 200%x, 210%y)";
mostCurrent._scrollview1.getPanel().AddView((android.view.View)(mostCurrent._p.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=1048637;
 //BA.debugLineNum = 1048637;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=1048638;
 //BA.debugLineNum = 1048638;BA.debugLine="End Sub";
return "";
}
public static String  _dramapage_click() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "dramapage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "dramapage_click", null));}
RDebugUtils.currentLine=1376256;
 //BA.debugLineNum = 1376256;BA.debugLine="Private Sub DramaPage_Click";
RDebugUtils.currentLine=1376258;
 //BA.debugLineNum = 1376258;BA.debugLine="End Sub";
return "";
}
public static String  _hideallpanels() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "hideallpanels", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "hideallpanels", null));}
RDebugUtils.currentLine=3080192;
 //BA.debugLineNum = 3080192;BA.debugLine="Private Sub HideAllPanels";
RDebugUtils.currentLine=3080193;
 //BA.debugLineNum = 3080193;BA.debugLine="Panel2.Visible = False";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3080194;
 //BA.debugLineNum = 3080194;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3080195;
 //BA.debugLineNum = 3080195;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3080196;
 //BA.debugLineNum = 3080196;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3080197;
 //BA.debugLineNum = 3080197;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3080198;
 //BA.debugLineNum = 3080198;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3080199;
 //BA.debugLineNum = 3080199;BA.debugLine="Panel8.Visible = False";
mostCurrent._panel8.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3080200;
 //BA.debugLineNum = 3080200;BA.debugLine="Panel9.Visible = False";
mostCurrent._panel9.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3080201;
 //BA.debugLineNum = 3080201;BA.debugLine="Panel10.Visible = False";
mostCurrent._panel10.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3080202;
 //BA.debugLineNum = 3080202;BA.debugLine="Panel11.Visible = False";
mostCurrent._panel11.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3080203;
 //BA.debugLineNum = 3080203;BA.debugLine="End Sub";
return "";
}
public static String  _homepage_click() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "homepage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "homepage_click", null));}
RDebugUtils.currentLine=1310720;
 //BA.debugLineNum = 1310720;BA.debugLine="Private Sub HomePage_Click";
RDebugUtils.currentLine=1310721;
 //BA.debugLineNum = 1310721;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
RDebugUtils.currentLine=1310722;
 //BA.debugLineNum = 1310722;BA.debugLine="End Sub";
return "";
}
public static String  _scifipage_click() throws Exception{
RDebugUtils.currentModule="drama";
if (Debug.shouldDelegate(mostCurrent.activityBA, "scifipage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "scifipage_click", null));}
RDebugUtils.currentLine=1245184;
 //BA.debugLineNum = 1245184;BA.debugLine="Private Sub SciFiPage_Click";
RDebugUtils.currentLine=1245185;
 //BA.debugLineNum = 1245185;BA.debugLine="StartActivity(SciFi)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._scifi.getObject()));
RDebugUtils.currentLine=1245186;
 //BA.debugLineNum = 1245186;BA.debugLine="End Sub";
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
if (_query.contains("meryl streep") || _query.contains("meryl")) { 
RDebugUtils.currentLine=1114271;
 //BA.debugLineNum = 1114271;BA.debugLine="Drama1.Text = \"The Bridges of Madison County\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Bridges of Madison County"));
RDebugUtils.currentLine=1114272;
 //BA.debugLineNum = 1114272;BA.debugLine="Starter1.Text = \"Starring: Clint Eastwood, Mery";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
RDebugUtils.currentLine=1114273;
 //BA.debugLineNum = 1114273;BA.debugLine="Year1.Text = \"(1995)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1995)"));
RDebugUtils.currentLine=1114274;
 //BA.debugLineNum = 1114274;BA.debugLine="OverView1.Text = \"A brief, passionate romance b";
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
 //BA.debugLineNum = 1114279;BA.debugLine="OverView2.Text = \"In a Catholic school in the B";
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
 //BA.debugLineNum = 1114285;BA.debugLine="Else If query.Contains(\"casey\") Or query.Contain";
if (_query.contains("casey") || _query.contains("casey affleck")) { 
RDebugUtils.currentLine=1114286;
 //BA.debugLineNum = 1114286;BA.debugLine="Drama1.Text = \"Gone Baby Gone\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Gone Baby Gone"));
RDebugUtils.currentLine=1114287;
 //BA.debugLineNum = 1114287;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Miche";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=1114288;
 //BA.debugLineNum = 1114288;BA.debugLine="Year1.Text = \"(2007)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2007)"));
RDebugUtils.currentLine=1114289;
 //BA.debugLineNum = 1114289;BA.debugLine="OverView1.Text = \"In a tough Boston neighborhoo";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
RDebugUtils.currentLine=1114291;
 //BA.debugLineNum = 1114291;BA.debugLine="Drama2.Text = \"Manchester by the Sea\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Manchester by the Sea"));
RDebugUtils.currentLine=1114292;
 //BA.debugLineNum = 1114292;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Miche";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
RDebugUtils.currentLine=1114293;
 //BA.debugLineNum = 1114293;BA.debugLine="Year2.Text = \"(2016)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=1114294;
 //BA.debugLineNum = 1114294;BA.debugLine="OverView2.Text = \"After the death of his brothe";
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
 //BA.debugLineNum = 1114301;BA.debugLine="Else If query.Contains(\"joaquin\") Or query.Conta";
if (_query.contains("joaquin") || _query.contains("joaquin phoenix") || _query.contains("amy adams") || _query.contains("amy")) { 
RDebugUtils.currentLine=1114302;
 //BA.debugLineNum = 1114302;BA.debugLine="Drama1.Text = \"The Master\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=1114303;
 //BA.debugLineNum = 1114303;BA.debugLine="Starter1.Text = \"Starring: Philip Seymour Hoffm";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=1114304;
 //BA.debugLineNum = 1114304;BA.debugLine="Year1.Text = \"(2012)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=1114305;
 //BA.debugLineNum = 1114305;BA.debugLine="OverView1.Text = \"A mentally unstable WWII vete";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=1114307;
 //BA.debugLineNum = 1114307;BA.debugLine="Drama2.Text = \"Her\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Her"));
RDebugUtils.currentLine=1114308;
 //BA.debugLineNum = 1114308;BA.debugLine="Starter2.Text = \"Starring: Joaquin Phoenix, Roo";
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
 //BA.debugLineNum = 1114317;BA.debugLine="Else If query.Contains(\"cate blanchett\") Or quer";
if (_query.contains("cate blanchett") || _query.contains("cate")) { 
RDebugUtils.currentLine=1114318;
 //BA.debugLineNum = 1114318;BA.debugLine="Drama1.Text = \"Carol\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=1114319;
 //BA.debugLineNum = 1114319;BA.debugLine="Starter1.Text = \"Starring: Rooney Mara, Cate Bl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=1114320;
 //BA.debugLineNum = 1114320;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=1114321;
 //BA.debugLineNum = 1114321;BA.debugLine="OverView1.Text = \"A chance encounter between a";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=1114323;
 //BA.debugLineNum = 1114323;BA.debugLine="Drama2.Text = \"Blue Jasmine\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=1114324;
 //BA.debugLineNum = 1114324;BA.debugLine="Starter2.Text = \"Starring: Cate Blanchett, Sall";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=1114325;
 //BA.debugLineNum = 1114325;BA.debugLine="Year2.Text = \"(2013)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1114326;
 //BA.debugLineNum = 1114326;BA.debugLine="OverView2.Text = \"After losing her fortune and";
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
 //BA.debugLineNum = 1114332;BA.debugLine="Else If query.Contains(\"morgan freeman\") Or quer";
if (_query.contains("morgan freeman") || _query.contains("morgan")) { 
RDebugUtils.currentLine=1114333;
 //BA.debugLineNum = 1114333;BA.debugLine="Drama1.Text = \"Million Dollar Baby\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Million Dollar Baby"));
RDebugUtils.currentLine=1114334;
 //BA.debugLineNum = 1114334;BA.debugLine="Starter1.Text = \"Starring: Morgan Freeman, Hila";
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
 //BA.debugLineNum = 1114339;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Miche";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=1114340;
 //BA.debugLineNum = 1114340;BA.debugLine="Year2.Text = \"(2007)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2007)"));
RDebugUtils.currentLine=1114341;
 //BA.debugLineNum = 1114341;BA.debugLine="OverView2.Text = \"In a tough Boston neighborhoo";
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
 }else {
RDebugUtils.currentLine=1114348;
 //BA.debugLineNum = 1114348;BA.debugLine="HideAllPanels";
_hideallpanels();
 }}}}}}}}}}}}}}}
;
RDebugUtils.currentLine=1114352;
 //BA.debugLineNum = 1114352;BA.debugLine="p.Width = 100%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=1114353;
 //BA.debugLineNum = 1114353;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=1114354;
 //BA.debugLineNum = 1114354;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=1179654;
 //BA.debugLineNum = 1179654;BA.debugLine="p.Height = 210%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=1179655;
 //BA.debugLineNum = 1179655;BA.debugLine="p.Width = 200%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA));
RDebugUtils.currentLine=1179656;
 //BA.debugLineNum = 1179656;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=1179658;
 //BA.debugLineNum = 1179658;BA.debugLine="Panel2.Visible = True";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179659;
 //BA.debugLineNum = 1179659;BA.debugLine="Panel3.Visible = True";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179660;
 //BA.debugLineNum = 1179660;BA.debugLine="Panel4.Visible = True";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179661;
 //BA.debugLineNum = 1179661;BA.debugLine="Panel5.Visible = True";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179662;
 //BA.debugLineNum = 1179662;BA.debugLine="Panel6.Visible = True";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179663;
 //BA.debugLineNum = 1179663;BA.debugLine="Panel7.Visible = True";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179664;
 //BA.debugLineNum = 1179664;BA.debugLine="Panel8.Visible = True";
mostCurrent._panel8.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179665;
 //BA.debugLineNum = 1179665;BA.debugLine="Panel9.Visible = True";
mostCurrent._panel9.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179666;
 //BA.debugLineNum = 1179666;BA.debugLine="Panel10.Visible = True";
mostCurrent._panel10.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179667;
 //BA.debugLineNum = 1179667;BA.debugLine="Panel11.Visible = True";
mostCurrent._panel11.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1179669;
 //BA.debugLineNum = 1179669;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Kramer vs. Kramer"));
RDebugUtils.currentLine=1179670;
 //BA.debugLineNum = 1179670;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
RDebugUtils.currentLine=1179671;
 //BA.debugLineNum = 1179671;BA.debugLine="Year1.Text = \"(1979)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1979)"));
RDebugUtils.currentLine=1179672;
 //BA.debugLineNum = 1179672;BA.debugLine="OverView1.Text = \"In this emotionally charged co";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
RDebugUtils.currentLine=1179674;
 //BA.debugLineNum = 1179674;BA.debugLine="Drama2.Text = \"Manchester by the Sea\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Manchester by the Sea"));
RDebugUtils.currentLine=1179675;
 //BA.debugLineNum = 1179675;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
RDebugUtils.currentLine=1179676;
 //BA.debugLineNum = 1179676;BA.debugLine="Year2.Text = \"(2016)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=1179677;
 //BA.debugLineNum = 1179677;BA.debugLine="OverView2.Text = \"After the death of his brother";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
RDebugUtils.currentLine=1179679;
 //BA.debugLineNum = 1179679;BA.debugLine="Drama6.Text = \"Gone Baby Gone\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("Gone Baby Gone"));
RDebugUtils.currentLine=1179680;
 //BA.debugLineNum = 1179680;BA.debugLine="Starter6.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=1179681;
 //BA.debugLineNum = 1179681;BA.debugLine="Year6.Text = \"(2007)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2007)"));
RDebugUtils.currentLine=1179682;
 //BA.debugLineNum = 1179682;BA.debugLine="OverView6.Text = \"In a tough Boston neighborhood";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
RDebugUtils.currentLine=1179684;
 //BA.debugLineNum = 1179684;BA.debugLine="Drama4.Text = \"Million Dollar Baby\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Million Dollar Baby"));
RDebugUtils.currentLine=1179685;
 //BA.debugLineNum = 1179685;BA.debugLine="Starter4.Text = \"Starring: Morgan Freeman, Hilar";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
RDebugUtils.currentLine=1179686;
 //BA.debugLineNum = 1179686;BA.debugLine="Year4.Text = \"(2004)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2004)"));
RDebugUtils.currentLine=1179687;
 //BA.debugLineNum = 1179687;BA.debugLine="OverView4.Text = \"A waitress with dreams of beco";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
RDebugUtils.currentLine=1179689;
 //BA.debugLineNum = 1179689;BA.debugLine="Drama5.Text = \"The Bridges of Madison County\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("The Bridges of Madison County"));
RDebugUtils.currentLine=1179690;
 //BA.debugLineNum = 1179690;BA.debugLine="Starter5.Text = \"Starring: Clint Eastwood, Meryl";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
RDebugUtils.currentLine=1179691;
 //BA.debugLineNum = 1179691;BA.debugLine="Year5.Text = \"(1995)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(1995)"));
RDebugUtils.currentLine=1179692;
 //BA.debugLineNum = 1179692;BA.debugLine="OverView5.Text = \"A brief, passionate romance be";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
RDebugUtils.currentLine=1179694;
 //BA.debugLineNum = 1179694;BA.debugLine="Drama3.Text = \"The Master\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=1179695;
 //BA.debugLineNum = 1179695;BA.debugLine="Starter3.Text = \"Starring: Philip Seymour Hoffma";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=1179696;
 //BA.debugLineNum = 1179696;BA.debugLine="Year3.Text = \"(2012)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=1179697;
 //BA.debugLineNum = 1179697;BA.debugLine="OverView3.Text = \"A mentally unstable WWII veter";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=1179699;
 //BA.debugLineNum = 1179699;BA.debugLine="Drama7.Text = \"Blue Jasmine\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=1179700;
 //BA.debugLineNum = 1179700;BA.debugLine="Starter7.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=1179701;
 //BA.debugLineNum = 1179701;BA.debugLine="Year7.Text = \"(2013)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1179702;
 //BA.debugLineNum = 1179702;BA.debugLine="OverView7.Text = \"After losing her fortune and s";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=1179704;
 //BA.debugLineNum = 1179704;BA.debugLine="Drama8.Text = \"Her\"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("Her"));
RDebugUtils.currentLine=1179705;
 //BA.debugLineNum = 1179705;BA.debugLine="Starter8.Text = \"Starring: Joaquin Phoenix, Roon";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
RDebugUtils.currentLine=1179706;
 //BA.debugLineNum = 1179706;BA.debugLine="Year8.Text = \"(2013)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1179707;
 //BA.debugLineNum = 1179707;BA.debugLine="OverView8.Text = \"In a near-future Los Angeles,";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
RDebugUtils.currentLine=1179709;
 //BA.debugLineNum = 1179709;BA.debugLine="Drama9.Text = \"Carol\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=1179710;
 //BA.debugLineNum = 1179710;BA.debugLine="Starter9.Text = \"Starring: Rooney Mara, Cate Bla";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=1179711;
 //BA.debugLineNum = 1179711;BA.debugLine="Year9.Text = \"(2015)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=1179712;
 //BA.debugLineNum = 1179712;BA.debugLine="OverView9.Text = \"A chance encounter between a y";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=1179714;
 //BA.debugLineNum = 1179714;BA.debugLine="Drama10.Text = \"The Lost Daughter\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("The Lost Daughter"));
RDebugUtils.currentLine=1179715;
 //BA.debugLineNum = 1179715;BA.debugLine="Starter10.Text = \"Starring: Olivia Colman, Dakot";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Olivia Colman, Dakota Johnson, Jessie Buckley"));
RDebugUtils.currentLine=1179716;
 //BA.debugLineNum = 1179716;BA.debugLine="Year10.Text = \"(2021)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2021)"));
RDebugUtils.currentLine=1179717;
 //BA.debugLineNum = 1179717;BA.debugLine="OverView10.Text = \"A solitary woman on vacation";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity"));
 };
RDebugUtils.currentLine=1179723;
 //BA.debugLineNum = 1179723;BA.debugLine="End Sub";
return "";
}
}