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

public class action extends Activity implements B4AActivity{
	public static action mostCurrent;
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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.action");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (action).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.action");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.action", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (action) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (action) Resume **");
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
		return action.class;
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
            BA.LogInfo("** Activity (action) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (action) Pause event (activity is not paused). **");
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
            action mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (action) Resume **");
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
public anywheresoftware.b4a.objects.LabelWrapper _notfound = null;
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.drama _drama = null;
public b4a.example.scifi _scifi = null;
public b4a.example.panelview _panelview = null;
public static String  _actionpage_click() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "actionpage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "actionpage_click", null));}
RDebugUtils.currentLine=2621440;
 //BA.debugLineNum = 2621440;BA.debugLine="Private Sub ActionPage_Click";
RDebugUtils.currentLine=2621442;
 //BA.debugLineNum = 2621442;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=2293760;
 //BA.debugLineNum = 2293760;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=2293761;
 //BA.debugLineNum = 2293761;BA.debugLine="Activity.LoadLayout(\"action\") ' Layout contains S";
mostCurrent._activity.LoadLayout("action",mostCurrent.activityBA);
RDebugUtils.currentLine=2293763;
 //BA.debugLineNum = 2293763;BA.debugLine="p.Initialize(\"\")";
mostCurrent._p.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=2293764;
 //BA.debugLineNum = 2293764;BA.debugLine="p.LoadLayout(\"panelview\")";
mostCurrent._p.LoadLayout("panelview",mostCurrent.activityBA);
RDebugUtils.currentLine=2293771;
 //BA.debugLineNum = 2293771;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=2293772;
 //BA.debugLineNum = 2293772;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sma";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=2293773;
 //BA.debugLineNum = 2293773;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=2293774;
 //BA.debugLineNum = 2293774;BA.debugLine="OverView1.Text = \"Professional assassin Chev Chel";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=2293775;
 //BA.debugLineNum = 2293775;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2293776;
 //BA.debugLineNum = 2293776;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=2293778;
 //BA.debugLineNum = 2293778;BA.debugLine="Drama2.Text = \"Sherlock Holmes \"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Sherlock Holmes "));
RDebugUtils.currentLine=2293779;
 //BA.debugLineNum = 2293779;BA.debugLine="Starter2.Text = \"Starring: Robert Downey Jr., Jud";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=2293780;
 //BA.debugLineNum = 2293780;BA.debugLine="Year2.Text = \"(2008)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=2293781;
 //BA.debugLineNum = 2293781;BA.debugLine="OverView2.Text = \"Detective Sherlock Holmes and h";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=2293782;
 //BA.debugLineNum = 2293782;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2293783;
 //BA.debugLineNum = 2293783;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=2293785;
 //BA.debugLineNum = 2293785;BA.debugLine="Drama3.Text = \"The Transporter\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=2293786;
 //BA.debugLineNum = 2293786;BA.debugLine="Starter3.Text = \"Starring: Jason Statham, Shu Qi,";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=2293787;
 //BA.debugLineNum = 2293787;BA.debugLine="Year3.Text = \"(2002)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=2293788;
 //BA.debugLineNum = 2293788;BA.debugLine="OverView3.Text = \"Frank Martin, who transports pa";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=2293789;
 //BA.debugLineNum = 2293789;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2293790;
 //BA.debugLineNum = 2293790;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=2293792;
 //BA.debugLineNum = 2293792;BA.debugLine="Drama4.Text = \"Avengers: Endgame\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=2293793;
 //BA.debugLineNum = 2293793;BA.debugLine="Starter4.Text = \"Starring: Robert Downey Jr., Chr";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=2293794;
 //BA.debugLineNum = 2293794;BA.debugLine="Year4.Text = \"(2019)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=2293795;
 //BA.debugLineNum = 2293795;BA.debugLine="OverView4.Text = \"After the devastating events of";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=2293796;
 //BA.debugLineNum = 2293796;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2293797;
 //BA.debugLineNum = 2293797;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=2293799;
 //BA.debugLineNum = 2293799;BA.debugLine="Drama5.Text = \"Logan\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=2293800;
 //BA.debugLineNum = 2293800;BA.debugLine="Starter5.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=2293801;
 //BA.debugLineNum = 2293801;BA.debugLine="Year5.Text = \"(2017)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=2293802;
 //BA.debugLineNum = 2293802;BA.debugLine="OverView5.Text = \"In a future where mutants are n";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=2293803;
 //BA.debugLineNum = 2293803;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
mostCurrent._dramaimage5.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2293804;
 //BA.debugLineNum = 2293804;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage5.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=2293807;
 //BA.debugLineNum = 2293807;BA.debugLine="Drama6.Text = \"Iron Man\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=2293808;
 //BA.debugLineNum = 2293808;BA.debugLine="Starter6.Text = \"Starring: Robert Downey Jr., Gwy";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=2293809;
 //BA.debugLineNum = 2293809;BA.debugLine="Year6.Text = \"(2008)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=2293810;
 //BA.debugLineNum = 2293810;BA.debugLine="OverView6.Text = \"After being held captive in an";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=2293811;
 //BA.debugLineNum = 2293811;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
mostCurrent._dramaimage6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2293812;
 //BA.debugLineNum = 2293812;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage6.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=2293814;
 //BA.debugLineNum = 2293814;BA.debugLine="Drama7.Text = \"X-Men\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=2293815;
 //BA.debugLineNum = 2293815;BA.debugLine="Starter7.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=2293816;
 //BA.debugLineNum = 2293816;BA.debugLine="Year7.Text = \"(2000)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=2293817;
 //BA.debugLineNum = 2293817;BA.debugLine="OverView7.Text = \"In a world where mutants (evolv";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=2293818;
 //BA.debugLineNum = 2293818;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2293819;
 //BA.debugLineNum = 2293819;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=2293821;
 //BA.debugLineNum = 2293821;BA.debugLine="Drama8.Text = \"Mr. & Mrs. Smith \"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("Mr. & Mrs. Smith "));
RDebugUtils.currentLine=2293822;
 //BA.debugLineNum = 2293822;BA.debugLine="Starter8.Text = \"Starring: Brad Pitt, Angelina Jo";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
RDebugUtils.currentLine=2293823;
 //BA.debugLineNum = 2293823;BA.debugLine="Year8.Text = \"(2005)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=2293824;
 //BA.debugLineNum = 2293824;BA.debugLine="OverView8.Text = \"A husband and wife struggle to";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
RDebugUtils.currentLine=2293825;
 //BA.debugLineNum = 2293825;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
mostCurrent._dramaimage8.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2293826;
 //BA.debugLineNum = 2293826;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage8.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=2293828;
 //BA.debugLineNum = 2293828;BA.debugLine="Drama9.Text = \"The Wolverine\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=2293829;
 //BA.debugLineNum = 2293829;BA.debugLine="Starter9.Text = \"Starring: Hugh Jackman, Will Yun";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=2293830;
 //BA.debugLineNum = 2293830;BA.debugLine="Year9.Text = \"(2015)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=2293831;
 //BA.debugLineNum = 2293831;BA.debugLine="OverView9.Text = \"A chance encounter between a yo";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=2293832;
 //BA.debugLineNum = 2293832;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
mostCurrent._dramaimage9.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2293833;
 //BA.debugLineNum = 2293833;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage9.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=2293835;
 //BA.debugLineNum = 2293835;BA.debugLine="Drama10.Text = \"Prisoners\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=2293836;
 //BA.debugLineNum = 2293836;BA.debugLine="Starter10.Text = \"Starring: Hugh Jackman, Jake Gy";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=2293837;
 //BA.debugLineNum = 2293837;BA.debugLine="Year10.Text = \"(2013)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=2293838;
 //BA.debugLineNum = 2293838;BA.debugLine="OverView10.Text = \"A desperate father takes the l";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=2293839;
 //BA.debugLineNum = 2293839;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2293840;
 //BA.debugLineNum = 2293840;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=2293842;
 //BA.debugLineNum = 2293842;BA.debugLine="ScrollView1.Panel.AddView(p, 0, 0, 200%x, 210%y)";
mostCurrent._scrollview1.getPanel().AddView((android.view.View)(mostCurrent._p.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=2293843;
 //BA.debugLineNum = 2293843;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=2293844;
 //BA.debugLineNum = 2293844;BA.debugLine="End Sub";
return "";
}
public static String  _dramapage_click() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "dramapage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "dramapage_click", null));}
RDebugUtils.currentLine=2686976;
 //BA.debugLineNum = 2686976;BA.debugLine="Private Sub DramaPage_Click";
RDebugUtils.currentLine=2686977;
 //BA.debugLineNum = 2686977;BA.debugLine="StartActivity(Drama)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._drama.getObject()));
RDebugUtils.currentLine=2686978;
 //BA.debugLineNum = 2686978;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=2686979;
 //BA.debugLineNum = 2686979;BA.debugLine="End Sub";
return "";
}
public static String  _homepage_click() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "homepage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "homepage_click", null));}
RDebugUtils.currentLine=2752512;
 //BA.debugLineNum = 2752512;BA.debugLine="Private Sub HomePage_Click";
RDebugUtils.currentLine=2752513;
 //BA.debugLineNum = 2752513;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
RDebugUtils.currentLine=2752514;
 //BA.debugLineNum = 2752514;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=2752515;
 //BA.debugLineNum = 2752515;BA.debugLine="End Sub";
return "";
}
public static void  _panelmovie1_click() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie1_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie1_click", null); return;}
ResumableSub_PanelMovie1_Click rsub = new ResumableSub_PanelMovie1_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie1_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie1_Click(b4a.example.action parent) {
this.parent = parent;
}
b4a.example.action parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="action";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=4456449;
 //BA.debugLineNum = 4456449;BA.debugLine="Try";
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
RDebugUtils.currentLine=4456450;
 //BA.debugLineNum = 4456450;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=4456451;
 //BA.debugLineNum = 4456451;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "action", "panelmovie1_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=4456452;
 //BA.debugLineNum = 4456452;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=4456453;
 //BA.debugLineNum = 4456453;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=4456454;
 //BA.debugLineNum = 4456454;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0479884/");
RDebugUtils.currentLine=4456455;
 //BA.debugLineNum = 4456455;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=4456456;
 //BA.debugLineNum = 4456456;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=4456460;
 //BA.debugLineNum = 4456460;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("24456460",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=4456461;
 //BA.debugLineNum = 4456461;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=4456464;
 //BA.debugLineNum = 4456464;BA.debugLine="End Sub";
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
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie10_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie10_click", null); return;}
ResumableSub_PanelMovie10_Click rsub = new ResumableSub_PanelMovie10_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie10_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie10_Click(b4a.example.action parent) {
this.parent = parent;
}
b4a.example.action parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="action";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=5242881;
 //BA.debugLineNum = 5242881;BA.debugLine="Try";
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
RDebugUtils.currentLine=5242882;
 //BA.debugLineNum = 5242882;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=5242883;
 //BA.debugLineNum = 5242883;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "action", "panelmovie10_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=5242884;
 //BA.debugLineNum = 5242884;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=5242885;
 //BA.debugLineNum = 5242885;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=5242886;
 //BA.debugLineNum = 5242886;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt1392214/");
RDebugUtils.currentLine=5242887;
 //BA.debugLineNum = 5242887;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=5242888;
 //BA.debugLineNum = 5242888;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=5242891;
 //BA.debugLineNum = 5242891;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("25242891",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=5242892;
 //BA.debugLineNum = 5242892;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=5242895;
 //BA.debugLineNum = 5242895;BA.debugLine="End Sub";
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
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie2_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie2_click", null); return;}
ResumableSub_PanelMovie2_Click rsub = new ResumableSub_PanelMovie2_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie2_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie2_Click(b4a.example.action parent) {
this.parent = parent;
}
b4a.example.action parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="action";

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
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "action", "panelmovie2_click"), null);
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
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0988045/");
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
anywheresoftware.b4a.keywords.Common.LogImpl("24587531",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
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
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie3_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie3_click", null); return;}
ResumableSub_PanelMovie3_Click rsub = new ResumableSub_PanelMovie3_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie3_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie3_Click(b4a.example.action parent) {
this.parent = parent;
}
b4a.example.action parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="action";

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
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "action", "panelmovie3_click"), null);
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
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt4154796/");
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
anywheresoftware.b4a.keywords.Common.LogImpl("24653067",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
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
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie4_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie4_click", null); return;}
ResumableSub_PanelMovie4_Click rsub = new ResumableSub_PanelMovie4_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie4_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie4_Click(b4a.example.action parent) {
this.parent = parent;
}
b4a.example.action parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="action";

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
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "action", "panelmovie4_click"), null);
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
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt3315342/");
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
anywheresoftware.b4a.keywords.Common.LogImpl("24718603",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
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
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie5_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie5_click", null); return;}
ResumableSub_PanelMovie5_Click rsub = new ResumableSub_PanelMovie5_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie5_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie5_Click(b4a.example.action parent) {
this.parent = parent;
}
b4a.example.action parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="action";

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
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "action", "panelmovie5_click"), null);
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
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0371746/");
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
anywheresoftware.b4a.keywords.Common.LogImpl("24784139",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
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
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie6_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie6_click", null); return;}
ResumableSub_PanelMovie6_Click rsub = new ResumableSub_PanelMovie6_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie6_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie6_Click(b4a.example.action parent) {
this.parent = parent;
}
b4a.example.action parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="action";

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
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "action", "panelmovie6_click"), null);
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
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0120903/");
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
anywheresoftware.b4a.keywords.Common.LogImpl("24915211",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
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
public static void  _panelmovie7_click() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie7_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie7_click", null); return;}
ResumableSub_PanelMovie7_Click rsub = new ResumableSub_PanelMovie7_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie7_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie7_Click(b4a.example.action parent) {
this.parent = parent;
}
b4a.example.action parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="action";

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
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "action", "panelmovie7_click"), null);
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
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt2334873/");
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
anywheresoftware.b4a.keywords.Common.LogImpl("25111819",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
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
public static void  _panelmovie8_click() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie8_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie8_click", null); return;}
ResumableSub_PanelMovie8_Click rsub = new ResumableSub_PanelMovie8_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie8_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie8_Click(b4a.example.action parent) {
this.parent = parent;
}
b4a.example.action parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="action";

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
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "action", "panelmovie8_click"), null);
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
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0356910/");
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
anywheresoftware.b4a.keywords.Common.LogImpl("25046283",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
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
public static void  _panelmovie9_click() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "panelmovie9_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "panelmovie9_click", null); return;}
ResumableSub_PanelMovie9_Click rsub = new ResumableSub_PanelMovie9_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_PanelMovie9_Click extends BA.ResumableSub {
public ResumableSub_PanelMovie9_Click(b4a.example.action parent) {
this.parent = parent;
}
b4a.example.action parent;
int _result = 0;
anywheresoftware.b4a.objects.IntentWrapper _i = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="action";

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=5177345;
 //BA.debugLineNum = 5177345;BA.debugLine="Try";
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
RDebugUtils.currentLine=5177346;
 //BA.debugLineNum = 5177346;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=5177347;
 //BA.debugLineNum = 5177347;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "action", "panelmovie9_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=5177348;
 //BA.debugLineNum = 5177348;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=5177349;
 //BA.debugLineNum = 5177349;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=5177350;
 //BA.debugLineNum = 5177350;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt1430132/");
RDebugUtils.currentLine=5177351;
 //BA.debugLineNum = 5177351;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=5177352;
 //BA.debugLineNum = 5177352;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=5177355;
 //BA.debugLineNum = 5177355;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("25177355",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=5177356;
 //BA.debugLineNum = 5177356;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=5177359;
 //BA.debugLineNum = 5177359;BA.debugLine="End Sub";
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
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "scifipage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "scifipage_click", null));}
RDebugUtils.currentLine=2555904;
 //BA.debugLineNum = 2555904;BA.debugLine="Private Sub SciFiPage_Click";
RDebugUtils.currentLine=2555905;
 //BA.debugLineNum = 2555905;BA.debugLine="StartActivity(SciFi)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._scifi.getObject()));
RDebugUtils.currentLine=2555906;
 //BA.debugLineNum = 2555906;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=2555907;
 //BA.debugLineNum = 2555907;BA.debugLine="End Sub";
return "";
}
public static String  _searchbtn_click() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchbtn_click", null));}
RDebugUtils.currentLine=2359296;
 //BA.debugLineNum = 2359296;BA.debugLine="Private Sub SearchBtn_Click";
RDebugUtils.currentLine=2359297;
 //BA.debugLineNum = 2359297;BA.debugLine="SearchNow";
_searchnow();
RDebugUtils.currentLine=2359298;
 //BA.debugLineNum = 2359298;BA.debugLine="End Sub";
return "";
}
public static String  _searchnow() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchnow", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchnow", null));}
String _query = "";
String _userinput = "";
RDebugUtils.currentLine=2424832;
 //BA.debugLineNum = 2424832;BA.debugLine="Sub SearchNow";
RDebugUtils.currentLine=2424833;
 //BA.debugLineNum = 2424833;BA.debugLine="Dim query As String = SearchEngine.Text.ToLowerCa";
_query = mostCurrent._searchengine.getText().toLowerCase().trim();
RDebugUtils.currentLine=2424836;
 //BA.debugLineNum = 2424836;BA.debugLine="Dim UserInput As String = SearchEngine.Text";
_userinput = mostCurrent._searchengine.getText();
RDebugUtils.currentLine=2424839;
 //BA.debugLineNum = 2424839;BA.debugLine="If query.Contains(\"crank\") Then";
if (_query.contains("crank")) { 
RDebugUtils.currentLine=2424841;
 //BA.debugLineNum = 2424841;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=2424842;
 //BA.debugLineNum = 2424842;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=2424843;
 //BA.debugLineNum = 2424843;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=2424844;
 //BA.debugLineNum = 2424844;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=2424845;
 //BA.debugLineNum = 2424845;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2424846;
 //BA.debugLineNum = 2424846;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=2424848;
 //BA.debugLineNum = 2424848;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424849;
 //BA.debugLineNum = 2424849;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424850;
 //BA.debugLineNum = 2424850;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424851;
 //BA.debugLineNum = 2424851;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424852;
 //BA.debugLineNum = 2424852;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424854;
 //BA.debugLineNum = 2424854;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2424856;
 //BA.debugLineNum = 2424856;BA.debugLine="Else If query.Contains(\"sherlock\") Or query.Conta";
if (_query.contains("sherlock") || _query.contains("sherlock holmes")) { 
RDebugUtils.currentLine=2424858;
 //BA.debugLineNum = 2424858;BA.debugLine="Drama1.Text = \"Sherlock Holmes \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Sherlock Holmes "));
RDebugUtils.currentLine=2424859;
 //BA.debugLineNum = 2424859;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ju";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=2424860;
 //BA.debugLineNum = 2424860;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=2424861;
 //BA.debugLineNum = 2424861;BA.debugLine="OverView1.Text = \"Detective Sherlock Holmes and";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=2424862;
 //BA.debugLineNum = 2424862;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2424863;
 //BA.debugLineNum = 2424863;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=2424865;
 //BA.debugLineNum = 2424865;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424866;
 //BA.debugLineNum = 2424866;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424867;
 //BA.debugLineNum = 2424867;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424868;
 //BA.debugLineNum = 2424868;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424869;
 //BA.debugLineNum = 2424869;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424871;
 //BA.debugLineNum = 2424871;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2424873;
 //BA.debugLineNum = 2424873;BA.debugLine="Else If query.Contains(\"the transporter\") Or quer";
if (_query.contains("the transporter") || _query.contains("transporter")) { 
RDebugUtils.currentLine=2424875;
 //BA.debugLineNum = 2424875;BA.debugLine="Drama1.Text = \"The Transporter\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=2424876;
 //BA.debugLineNum = 2424876;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Shu Qi";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=2424877;
 //BA.debugLineNum = 2424877;BA.debugLine="Year1.Text = \"(2002)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=2424878;
 //BA.debugLineNum = 2424878;BA.debugLine="OverView1.Text = \"Frank Martin, who transports p";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=2424879;
 //BA.debugLineNum = 2424879;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2424880;
 //BA.debugLineNum = 2424880;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=2424882;
 //BA.debugLineNum = 2424882;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424883;
 //BA.debugLineNum = 2424883;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424884;
 //BA.debugLineNum = 2424884;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424885;
 //BA.debugLineNum = 2424885;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424886;
 //BA.debugLineNum = 2424886;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424888;
 //BA.debugLineNum = 2424888;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2424890;
 //BA.debugLineNum = 2424890;BA.debugLine="Else If query.Contains(\"avengers endgame\") Or que";
if (_query.contains("avengers endgame") || _query.contains("avengers") || _query.contains("endgame")) { 
RDebugUtils.currentLine=2424892;
 //BA.debugLineNum = 2424892;BA.debugLine="Drama1.Text = \"Avengers: Endgame\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=2424893;
 //BA.debugLineNum = 2424893;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ch";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=2424894;
 //BA.debugLineNum = 2424894;BA.debugLine="Year1.Text = \"(2019)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=2424895;
 //BA.debugLineNum = 2424895;BA.debugLine="OverView1.Text = \"After the devastating events o";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=2424896;
 //BA.debugLineNum = 2424896;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2424897;
 //BA.debugLineNum = 2424897;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=2424900;
 //BA.debugLineNum = 2424900;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424901;
 //BA.debugLineNum = 2424901;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424902;
 //BA.debugLineNum = 2424902;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424903;
 //BA.debugLineNum = 2424903;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424905;
 //BA.debugLineNum = 2424905;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2424907;
 //BA.debugLineNum = 2424907;BA.debugLine="Else If query.Contains(\"logan\") Then";
if (_query.contains("logan")) { 
RDebugUtils.currentLine=2424909;
 //BA.debugLineNum = 2424909;BA.debugLine="Drama1.Text = \"Logan\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=2424910;
 //BA.debugLineNum = 2424910;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=2424911;
 //BA.debugLineNum = 2424911;BA.debugLine="Year1.Text = \"(2017)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=2424912;
 //BA.debugLineNum = 2424912;BA.debugLine="OverView1.Text = \"In a future where mutants are";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=2424913;
 //BA.debugLineNum = 2424913;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2424914;
 //BA.debugLineNum = 2424914;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=2424916;
 //BA.debugLineNum = 2424916;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424917;
 //BA.debugLineNum = 2424917;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424918;
 //BA.debugLineNum = 2424918;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424919;
 //BA.debugLineNum = 2424919;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424920;
 //BA.debugLineNum = 2424920;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424922;
 //BA.debugLineNum = 2424922;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2424924;
 //BA.debugLineNum = 2424924;BA.debugLine="Else If query.Contains(\"iron man\") Or query.Conta";
if (_query.contains("iron man") || _query.contains("man") || _query.contains("iron")) { 
RDebugUtils.currentLine=2424926;
 //BA.debugLineNum = 2424926;BA.debugLine="Drama1.Text = \"Iron Man\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=2424927;
 //BA.debugLineNum = 2424927;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Gw";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=2424928;
 //BA.debugLineNum = 2424928;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=2424929;
 //BA.debugLineNum = 2424929;BA.debugLine="OverView1.Text = \"After being held captive in an";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=2424930;
 //BA.debugLineNum = 2424930;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2424931;
 //BA.debugLineNum = 2424931;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=2424933;
 //BA.debugLineNum = 2424933;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424934;
 //BA.debugLineNum = 2424934;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424935;
 //BA.debugLineNum = 2424935;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424936;
 //BA.debugLineNum = 2424936;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424937;
 //BA.debugLineNum = 2424937;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424939;
 //BA.debugLineNum = 2424939;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2424941;
 //BA.debugLineNum = 2424941;BA.debugLine="Else If query.Contains(\"x-men\") Or query.Contains";
if (_query.contains("x-men") || _query.contains("men") || _query.contains("xmen") || _query.contains("x")) { 
RDebugUtils.currentLine=2424943;
 //BA.debugLineNum = 2424943;BA.debugLine="Drama7.Text = \"X-Men\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=2424944;
 //BA.debugLineNum = 2424944;BA.debugLine="Starter7.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=2424945;
 //BA.debugLineNum = 2424945;BA.debugLine="Year7.Text = \"(2000)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=2424946;
 //BA.debugLineNum = 2424946;BA.debugLine="OverView7.Text = \"In a world where mutants (evol";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=2424947;
 //BA.debugLineNum = 2424947;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2424948;
 //BA.debugLineNum = 2424948;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=2424950;
 //BA.debugLineNum = 2424950;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424951;
 //BA.debugLineNum = 2424951;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424952;
 //BA.debugLineNum = 2424952;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424953;
 //BA.debugLineNum = 2424953;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424954;
 //BA.debugLineNum = 2424954;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424956;
 //BA.debugLineNum = 2424956;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2424958;
 //BA.debugLineNum = 2424958;BA.debugLine="Else If query.Contains(\"mr & mrs smith\") Or query";
if (_query.contains("mr & mrs smith") || _query.contains("mr and mrs") || _query.contains("smith") || _query.contains("mrs") || _query.contains("mr")) { 
RDebugUtils.currentLine=2424960;
 //BA.debugLineNum = 2424960;BA.debugLine="Drama1.Text = \"Mr. & Mrs. Smith \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Mr. & Mrs. Smith "));
RDebugUtils.currentLine=2424961;
 //BA.debugLineNum = 2424961;BA.debugLine="Starter1.Text = \"Starring: Brad Pitt, Angelina J";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
RDebugUtils.currentLine=2424962;
 //BA.debugLineNum = 2424962;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=2424963;
 //BA.debugLineNum = 2424963;BA.debugLine="OverView1.Text = \"A husband and wife struggle to";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
RDebugUtils.currentLine=2424964;
 //BA.debugLineNum = 2424964;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2424965;
 //BA.debugLineNum = 2424965;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=2424967;
 //BA.debugLineNum = 2424967;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424968;
 //BA.debugLineNum = 2424968;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424969;
 //BA.debugLineNum = 2424969;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424970;
 //BA.debugLineNum = 2424970;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424971;
 //BA.debugLineNum = 2424971;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424973;
 //BA.debugLineNum = 2424973;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2424975;
 //BA.debugLineNum = 2424975;BA.debugLine="Else If query.Contains(\"the wolverine\") Or query.";
if (_query.contains("the wolverine") || _query.contains("wolverine")) { 
RDebugUtils.currentLine=2424977;
 //BA.debugLineNum = 2424977;BA.debugLine="Drama1.Text = \"The Wolverine\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=2424978;
 //BA.debugLineNum = 2424978;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Will Yu";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=2424979;
 //BA.debugLineNum = 2424979;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=2424980;
 //BA.debugLineNum = 2424980;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=2424981;
 //BA.debugLineNum = 2424981;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2424982;
 //BA.debugLineNum = 2424982;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=2424984;
 //BA.debugLineNum = 2424984;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424985;
 //BA.debugLineNum = 2424985;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424986;
 //BA.debugLineNum = 2424986;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424987;
 //BA.debugLineNum = 2424987;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424988;
 //BA.debugLineNum = 2424988;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2424990;
 //BA.debugLineNum = 2424990;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2424992;
 //BA.debugLineNum = 2424992;BA.debugLine="Else If query.Contains(\"prisoners\") Or query.Cont";
if (_query.contains("prisoners") || _query.contains("prisoner") || _query.contains("pri")) { 
RDebugUtils.currentLine=2424994;
 //BA.debugLineNum = 2424994;BA.debugLine="Drama10.Text = \"Prisoners\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=2424995;
 //BA.debugLineNum = 2424995;BA.debugLine="Starter10.Text = \"Starring: Hugh Jackman, Jake G";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=2424996;
 //BA.debugLineNum = 2424996;BA.debugLine="Year10.Text = \"(2013)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=2424997;
 //BA.debugLineNum = 2424997;BA.debugLine="OverView10.Text = \"A desperate father takes the";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=2424998;
 //BA.debugLineNum = 2424998;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2424999;
 //BA.debugLineNum = 2424999;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=2425001;
 //BA.debugLineNum = 2425001;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425002;
 //BA.debugLineNum = 2425002;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425003;
 //BA.debugLineNum = 2425003;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425004;
 //BA.debugLineNum = 2425004;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425005;
 //BA.debugLineNum = 2425005;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425007;
 //BA.debugLineNum = 2425007;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2425009;
 //BA.debugLineNum = 2425009;BA.debugLine="Else If query.Contains(\"jason statham\") Or query.";
if (_query.contains("jason statham") || _query.contains("jason") || _query.contains("statham")) { 
RDebugUtils.currentLine=2425010;
 //BA.debugLineNum = 2425010;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=2425011;
 //BA.debugLineNum = 2425011;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=2425012;
 //BA.debugLineNum = 2425012;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=2425013;
 //BA.debugLineNum = 2425013;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=2425014;
 //BA.debugLineNum = 2425014;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425015;
 //BA.debugLineNum = 2425015;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=2425017;
 //BA.debugLineNum = 2425017;BA.debugLine="Drama2.Text = \"The Transporter\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=2425018;
 //BA.debugLineNum = 2425018;BA.debugLine="Starter2.Text = \"Starring: Jason Statham, Shu Qi";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=2425019;
 //BA.debugLineNum = 2425019;BA.debugLine="Year2.Text = \"(2002)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=2425020;
 //BA.debugLineNum = 2425020;BA.debugLine="OverView2.Text = \"Frank Martin, who transports p";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=2425021;
 //BA.debugLineNum = 2425021;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425022;
 //BA.debugLineNum = 2425022;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=2425024;
 //BA.debugLineNum = 2425024;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425025;
 //BA.debugLineNum = 2425025;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425026;
 //BA.debugLineNum = 2425026;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425028;
 //BA.debugLineNum = 2425028;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2425030;
 //BA.debugLineNum = 2425030;BA.debugLine="Else If query.Contains(\"Robert downey jr\") Or que";
if (_query.contains("Robert downey jr") || _query.contains("robert") || _query.contains("downey") || _query.contains("downey jr")) { 
RDebugUtils.currentLine=2425031;
 //BA.debugLineNum = 2425031;BA.debugLine="Drama1.Text = \"Sherlock Holmes \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Sherlock Holmes "));
RDebugUtils.currentLine=2425032;
 //BA.debugLineNum = 2425032;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ju";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=2425033;
 //BA.debugLineNum = 2425033;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=2425034;
 //BA.debugLineNum = 2425034;BA.debugLine="OverView1.Text = \"Detective Sherlock Holmes and";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=2425035;
 //BA.debugLineNum = 2425035;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425036;
 //BA.debugLineNum = 2425036;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=2425038;
 //BA.debugLineNum = 2425038;BA.debugLine="Drama2.Text = \"Avengers: Endgame\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=2425039;
 //BA.debugLineNum = 2425039;BA.debugLine="Starter2.Text = \"Starring: Robert Downey Jr., Ch";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=2425040;
 //BA.debugLineNum = 2425040;BA.debugLine="Year2.Text = \"(2019)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=2425041;
 //BA.debugLineNum = 2425041;BA.debugLine="OverView2.Text = \"After the devastating events o";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=2425042;
 //BA.debugLineNum = 2425042;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425043;
 //BA.debugLineNum = 2425043;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=2425045;
 //BA.debugLineNum = 2425045;BA.debugLine="Drama3.Text = \"Iron Man\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=2425046;
 //BA.debugLineNum = 2425046;BA.debugLine="Starter3.Text = \"Starring: Robert Downey Jr., Gw";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=2425047;
 //BA.debugLineNum = 2425047;BA.debugLine="Year3.Text = \"(2008)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=2425048;
 //BA.debugLineNum = 2425048;BA.debugLine="OverView3.Text = \"After being held captive in an";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=2425049;
 //BA.debugLineNum = 2425049;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425050;
 //BA.debugLineNum = 2425050;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=2425052;
 //BA.debugLineNum = 2425052;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425053;
 //BA.debugLineNum = 2425053;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425054;
 //BA.debugLineNum = 2425054;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425056;
 //BA.debugLineNum = 2425056;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2425058;
 //BA.debugLineNum = 2425058;BA.debugLine="Else If query.Contains(\"patrick stewart\") Or quer";
if (_query.contains("patrick stewart") || _query.contains("patrick") || _query.contains("stewart")) { 
RDebugUtils.currentLine=2425059;
 //BA.debugLineNum = 2425059;BA.debugLine="Drama1.Text = \"Logan\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=2425060;
 //BA.debugLineNum = 2425060;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=2425061;
 //BA.debugLineNum = 2425061;BA.debugLine="Year1.Text = \"(2017)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=2425062;
 //BA.debugLineNum = 2425062;BA.debugLine="OverView1.Text = \"In a future where mutants are";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=2425063;
 //BA.debugLineNum = 2425063;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425064;
 //BA.debugLineNum = 2425064;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=2425066;
 //BA.debugLineNum = 2425066;BA.debugLine="Drama2.Text = \"X-Men\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=2425067;
 //BA.debugLineNum = 2425067;BA.debugLine="Starter2.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=2425068;
 //BA.debugLineNum = 2425068;BA.debugLine="Year2.Text = \"(2000)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=2425069;
 //BA.debugLineNum = 2425069;BA.debugLine="OverView2.Text = \"In a world where mutants (evol";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=2425070;
 //BA.debugLineNum = 2425070;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425071;
 //BA.debugLineNum = 2425071;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=2425073;
 //BA.debugLineNum = 2425073;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425074;
 //BA.debugLineNum = 2425074;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425075;
 //BA.debugLineNum = 2425075;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425076;
 //BA.debugLineNum = 2425076;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425078;
 //BA.debugLineNum = 2425078;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2425080;
 //BA.debugLineNum = 2425080;BA.debugLine="Else If query.Contains(\"hugh jackman\") Or query.C";
if (_query.contains("hugh jackman") || _query.contains("hugh") || _query.contains("jackman")) { 
RDebugUtils.currentLine=2425081;
 //BA.debugLineNum = 2425081;BA.debugLine="Drama1.Text = \"Logan\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=2425082;
 //BA.debugLineNum = 2425082;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=2425083;
 //BA.debugLineNum = 2425083;BA.debugLine="Year1.Text = \"(2017)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=2425084;
 //BA.debugLineNum = 2425084;BA.debugLine="OverView1.Text = \"In a future where mutants are";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=2425085;
 //BA.debugLineNum = 2425085;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425086;
 //BA.debugLineNum = 2425086;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=2425088;
 //BA.debugLineNum = 2425088;BA.debugLine="Drama2.Text = \"X-Men\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=2425089;
 //BA.debugLineNum = 2425089;BA.debugLine="Starter2.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=2425090;
 //BA.debugLineNum = 2425090;BA.debugLine="Year2.Text = \"(2000)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=2425091;
 //BA.debugLineNum = 2425091;BA.debugLine="OverView2.Text = \"In a world where mutants (evol";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=2425092;
 //BA.debugLineNum = 2425092;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425093;
 //BA.debugLineNum = 2425093;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=2425095;
 //BA.debugLineNum = 2425095;BA.debugLine="Drama3.Text = \"The Wolverine\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=2425096;
 //BA.debugLineNum = 2425096;BA.debugLine="Starter3.Text = \"Starring: Hugh Jackman, Will Yu";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=2425097;
 //BA.debugLineNum = 2425097;BA.debugLine="Year3.Text = \"(2015)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=2425098;
 //BA.debugLineNum = 2425098;BA.debugLine="OverView3.Text = \"A chance encounter between a y";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=2425099;
 //BA.debugLineNum = 2425099;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425100;
 //BA.debugLineNum = 2425100;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=2425102;
 //BA.debugLineNum = 2425102;BA.debugLine="Drama4.Text = \"Prisoners\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=2425103;
 //BA.debugLineNum = 2425103;BA.debugLine="Starter4.Text = \"Starring: Hugh Jackman, Jake Gy";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=2425104;
 //BA.debugLineNum = 2425104;BA.debugLine="Year4.Text = \"(2013)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=2425105;
 //BA.debugLineNum = 2425105;BA.debugLine="OverView4.Text = \"A desperate father takes the l";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=2425106;
 //BA.debugLineNum = 2425106;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425107;
 //BA.debugLineNum = 2425107;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=2425109;
 //BA.debugLineNum = 2425109;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425110;
 //BA.debugLineNum = 2425110;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425112;
 //BA.debugLineNum = 2425112;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2425114;
 //BA.debugLineNum = 2425114;BA.debugLine="Else If query.Contains(\"amy smart\") Or query.Cont";
if (_query.contains("amy smart") || _query.contains("amy") || _query.contains("smart") || _query.contains("carlos sanz") || _query.contains("carlos") || _query.contains("sanz")) { 
RDebugUtils.currentLine=2425115;
 //BA.debugLineNum = 2425115;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=2425116;
 //BA.debugLineNum = 2425116;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=2425117;
 //BA.debugLineNum = 2425117;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=2425118;
 //BA.debugLineNum = 2425118;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=2425119;
 //BA.debugLineNum = 2425119;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425120;
 //BA.debugLineNum = 2425120;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=2425122;
 //BA.debugLineNum = 2425122;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425123;
 //BA.debugLineNum = 2425123;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425124;
 //BA.debugLineNum = 2425124;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425125;
 //BA.debugLineNum = 2425125;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425126;
 //BA.debugLineNum = 2425126;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425128;
 //BA.debugLineNum = 2425128;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2425129;
 //BA.debugLineNum = 2425129;BA.debugLine="Else If query.Contains(\"jude law\") Or query.Conta";
if (_query.contains("jude law") || _query.contains("jude") || _query.contains("law") || _query.contains("rachel mcadams") || _query.contains("rachel") || _query.contains("mcadams")) { 
RDebugUtils.currentLine=2425130;
 //BA.debugLineNum = 2425130;BA.debugLine="Drama1.Text = \"Sherlock Holmes \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Sherlock Holmes "));
RDebugUtils.currentLine=2425131;
 //BA.debugLineNum = 2425131;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ju";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=2425132;
 //BA.debugLineNum = 2425132;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=2425133;
 //BA.debugLineNum = 2425133;BA.debugLine="OverView1.Text = \"Detective Sherlock Holmes and";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=2425134;
 //BA.debugLineNum = 2425134;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425135;
 //BA.debugLineNum = 2425135;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=2425137;
 //BA.debugLineNum = 2425137;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425138;
 //BA.debugLineNum = 2425138;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425139;
 //BA.debugLineNum = 2425139;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425140;
 //BA.debugLineNum = 2425140;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425141;
 //BA.debugLineNum = 2425141;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425143;
 //BA.debugLineNum = 2425143;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2425144;
 //BA.debugLineNum = 2425144;BA.debugLine="Else if query.Contains(\"shu qi\") Or query.Contain";
if (_query.contains("shu qi") || _query.contains("shu") || _query.contains("qi") || _query.contains("matt") || _query.contains("schulze") || _query.contains("matt schulze")) { 
RDebugUtils.currentLine=2425145;
 //BA.debugLineNum = 2425145;BA.debugLine="Drama1.Text = \"The Transporter\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=2425146;
 //BA.debugLineNum = 2425146;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Shu Qi";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=2425147;
 //BA.debugLineNum = 2425147;BA.debugLine="Year1.Text = \"(2002)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=2425148;
 //BA.debugLineNum = 2425148;BA.debugLine="OverView1.Text = \"Frank Martin, who transports p";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=2425149;
 //BA.debugLineNum = 2425149;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425150;
 //BA.debugLineNum = 2425150;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=2425152;
 //BA.debugLineNum = 2425152;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425153;
 //BA.debugLineNum = 2425153;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425154;
 //BA.debugLineNum = 2425154;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425155;
 //BA.debugLineNum = 2425155;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425156;
 //BA.debugLineNum = 2425156;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425158;
 //BA.debugLineNum = 2425158;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2425159;
 //BA.debugLineNum = 2425159;BA.debugLine="Else if query.Contains(\"chris evans\") Or query.Co";
if (_query.contains("chris evans") || _query.contains("chris") || _query.contains("evans") || _query.contains("mark ruffalo") || _query.contains("mark") || _query.contains("ruffalo")) { 
RDebugUtils.currentLine=2425160;
 //BA.debugLineNum = 2425160;BA.debugLine="Drama1.Text = \"Avengers: Endgame\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=2425161;
 //BA.debugLineNum = 2425161;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ch";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=2425162;
 //BA.debugLineNum = 2425162;BA.debugLine="Year1.Text = \"(2019)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=2425163;
 //BA.debugLineNum = 2425163;BA.debugLine="OverView1.Text = \"After the devastating events o";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=2425164;
 //BA.debugLineNum = 2425164;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425165;
 //BA.debugLineNum = 2425165;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=2425167;
 //BA.debugLineNum = 2425167;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425168;
 //BA.debugLineNum = 2425168;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425169;
 //BA.debugLineNum = 2425169;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425170;
 //BA.debugLineNum = 2425170;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425171;
 //BA.debugLineNum = 2425171;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425173;
 //BA.debugLineNum = 2425173;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2425174;
 //BA.debugLineNum = 2425174;BA.debugLine="Else if query.Contains(\"dafne keen\") Or query.Con";
if (_query.contains("dafne keen") || _query.contains("dafne") || _query.contains("keen")) { 
RDebugUtils.currentLine=2425175;
 //BA.debugLineNum = 2425175;BA.debugLine="Drama1.Text = \"Logan\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=2425176;
 //BA.debugLineNum = 2425176;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=2425177;
 //BA.debugLineNum = 2425177;BA.debugLine="Year1.Text = \"(2017)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=2425178;
 //BA.debugLineNum = 2425178;BA.debugLine="OverView1.Text = \"In a future where mutants are";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=2425179;
 //BA.debugLineNum = 2425179;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425180;
 //BA.debugLineNum = 2425180;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=2425182;
 //BA.debugLineNum = 2425182;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425183;
 //BA.debugLineNum = 2425183;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425184;
 //BA.debugLineNum = 2425184;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425185;
 //BA.debugLineNum = 2425185;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425186;
 //BA.debugLineNum = 2425186;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425188;
 //BA.debugLineNum = 2425188;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2425190;
 //BA.debugLineNum = 2425190;BA.debugLine="Else if query.Contains(\"gwyneth paltrow\") Or quer";
if (_query.contains("gwyneth paltrow") || _query.contains("gwyneth") || _query.contains("paltrow") || _query.contains("terrence howard") || _query.contains("terrence") || _query.contains("howard")) { 
RDebugUtils.currentLine=2425191;
 //BA.debugLineNum = 2425191;BA.debugLine="Drama1.Text = \"Iron Man\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=2425192;
 //BA.debugLineNum = 2425192;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Gw";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=2425193;
 //BA.debugLineNum = 2425193;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=2425194;
 //BA.debugLineNum = 2425194;BA.debugLine="OverView1.Text = \"After being held captive in an";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=2425195;
 //BA.debugLineNum = 2425195;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425196;
 //BA.debugLineNum = 2425196;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=2425198;
 //BA.debugLineNum = 2425198;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425199;
 //BA.debugLineNum = 2425199;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425200;
 //BA.debugLineNum = 2425200;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425201;
 //BA.debugLineNum = 2425201;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425202;
 //BA.debugLineNum = 2425202;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425204;
 //BA.debugLineNum = 2425204;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2425206;
 //BA.debugLineNum = 2425206;BA.debugLine="Else if query.Contains(\"ian mckellen\") Or query.C";
if (_query.contains("ian mckellen") || _query.contains("ian") || _query.contains("mckellen")) { 
RDebugUtils.currentLine=2425207;
 //BA.debugLineNum = 2425207;BA.debugLine="Drama1.Text = \"X-Men\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=2425208;
 //BA.debugLineNum = 2425208;BA.debugLine="Starter1.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=2425209;
 //BA.debugLineNum = 2425209;BA.debugLine="Year1.Text = \"(2000)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=2425210;
 //BA.debugLineNum = 2425210;BA.debugLine="OverView1.Text = \"In a world where mutants (evol";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=2425211;
 //BA.debugLineNum = 2425211;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425212;
 //BA.debugLineNum = 2425212;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=2425214;
 //BA.debugLineNum = 2425214;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425215;
 //BA.debugLineNum = 2425215;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425216;
 //BA.debugLineNum = 2425216;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425217;
 //BA.debugLineNum = 2425217;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425218;
 //BA.debugLineNum = 2425218;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425220;
 //BA.debugLineNum = 2425220;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2425222;
 //BA.debugLineNum = 2425222;BA.debugLine="Else if query.Contains(\"brad pitt\") Or query.Cont";
if (_query.contains("brad pitt") || _query.contains("brad") || _query.contains("pitt") || _query.contains("angelina jolie") || _query.contains("angelina") || _query.contains("jolie") || _query.contains("adam brody") || _query.contains("adam") || _query.contains("brody")) { 
RDebugUtils.currentLine=2425223;
 //BA.debugLineNum = 2425223;BA.debugLine="Drama1.Text = \"Mr. & Mrs. Smith \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Mr. & Mrs. Smith "));
RDebugUtils.currentLine=2425224;
 //BA.debugLineNum = 2425224;BA.debugLine="Starter1.Text = \"Starring: Brad Pitt, Angelina J";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
RDebugUtils.currentLine=2425225;
 //BA.debugLineNum = 2425225;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=2425226;
 //BA.debugLineNum = 2425226;BA.debugLine="OverView1.Text = \"A husband and wife struggle to";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
RDebugUtils.currentLine=2425227;
 //BA.debugLineNum = 2425227;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425228;
 //BA.debugLineNum = 2425228;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=2425230;
 //BA.debugLineNum = 2425230;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425231;
 //BA.debugLineNum = 2425231;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425232;
 //BA.debugLineNum = 2425232;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425233;
 //BA.debugLineNum = 2425233;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425234;
 //BA.debugLineNum = 2425234;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425236;
 //BA.debugLineNum = 2425236;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2425240;
 //BA.debugLineNum = 2425240;BA.debugLine="Else if query.Contains(\"will yun lee\") Or query.C";
if (_query.contains("will yun lee") || _query.contains("will") || _query.contains("yun") || _query.contains("lee") || _query.contains("tao okamoto") || _query.contains("tao") || _query.contains("okamoto")) { 
RDebugUtils.currentLine=2425241;
 //BA.debugLineNum = 2425241;BA.debugLine="Drama1.Text = \"The Wolverine\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=2425242;
 //BA.debugLineNum = 2425242;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Will Yu";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=2425243;
 //BA.debugLineNum = 2425243;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=2425244;
 //BA.debugLineNum = 2425244;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=2425245;
 //BA.debugLineNum = 2425245;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425246;
 //BA.debugLineNum = 2425246;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=2425248;
 //BA.debugLineNum = 2425248;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425249;
 //BA.debugLineNum = 2425249;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425250;
 //BA.debugLineNum = 2425250;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425251;
 //BA.debugLineNum = 2425251;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425252;
 //BA.debugLineNum = 2425252;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425254;
 //BA.debugLineNum = 2425254;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2425256;
 //BA.debugLineNum = 2425256;BA.debugLine="Else if query.Contains(\"jake gyllenhaal\") Or quer";
if (_query.contains("jake gyllenhaal") || _query.contains("jake") || _query.contains("gyllenhaal") || _query.contains("viola davis") || _query.contains("viola") || _query.contains("davis")) { 
RDebugUtils.currentLine=2425257;
 //BA.debugLineNum = 2425257;BA.debugLine="Drama1.Text = \"Prisoners\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=2425258;
 //BA.debugLineNum = 2425258;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Jake Gy";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=2425259;
 //BA.debugLineNum = 2425259;BA.debugLine="Year1.Text = \"(2013)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=2425260;
 //BA.debugLineNum = 2425260;BA.debugLine="OverView1.Text = \"A desperate father takes the l";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=2425261;
 //BA.debugLineNum = 2425261;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2425262;
 //BA.debugLineNum = 2425262;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=2425264;
 //BA.debugLineNum = 2425264;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425265;
 //BA.debugLineNum = 2425265;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425266;
 //BA.debugLineNum = 2425266;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425267;
 //BA.debugLineNum = 2425267;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425268;
 //BA.debugLineNum = 2425268;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2425270;
 //BA.debugLineNum = 2425270;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else {
RDebugUtils.currentLine=2425274;
 //BA.debugLineNum = 2425274;BA.debugLine="MsgboxAsync(\"No results found for\" & \" \"\"\" & Use";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("No results found for"+" \""+_userinput+"\""),BA.ObjectToCharSequence(""),processBA);
 }}}}}}}}}}}}}}}}}}}}}}}}
;
RDebugUtils.currentLine=2425278;
 //BA.debugLineNum = 2425278;BA.debugLine="p.Width = 100%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=2425279;
 //BA.debugLineNum = 2425279;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=2425281;
 //BA.debugLineNum = 2425281;BA.debugLine="End Sub";
return "";
}
public static String  _searchengine_textchanged(String _old,String _new) throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchengine_textchanged", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchengine_textchanged", new Object[] {_old,_new}));}
String _query = "";
RDebugUtils.currentLine=2490368;
 //BA.debugLineNum = 2490368;BA.debugLine="Sub SearchEngine_TextChanged (Old As String, New A";
RDebugUtils.currentLine=2490369;
 //BA.debugLineNum = 2490369;BA.debugLine="Dim query As String = New.ToLowerCase.Trim";
_query = _new.toLowerCase().trim();
RDebugUtils.currentLine=2490371;
 //BA.debugLineNum = 2490371;BA.debugLine="If query = \"\" Then";
if ((_query).equals("")) { 
RDebugUtils.currentLine=2490373;
 //BA.debugLineNum = 2490373;BA.debugLine="NotFound.Text = \"\"";
mostCurrent._notfound.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=2490374;
 //BA.debugLineNum = 2490374;BA.debugLine="p.Height = 210%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=2490375;
 //BA.debugLineNum = 2490375;BA.debugLine="p.Width = 200%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA));
RDebugUtils.currentLine=2490376;
 //BA.debugLineNum = 2490376;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=2490378;
 //BA.debugLineNum = 2490378;BA.debugLine="PanelMovie1.Visible = False";
mostCurrent._panelmovie1.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2490379;
 //BA.debugLineNum = 2490379;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2490380;
 //BA.debugLineNum = 2490380;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2490381;
 //BA.debugLineNum = 2490381;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2490382;
 //BA.debugLineNum = 2490382;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2490383;
 //BA.debugLineNum = 2490383;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2490384;
 //BA.debugLineNum = 2490384;BA.debugLine="PanelMovie7.Visible = False";
mostCurrent._panelmovie7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2490385;
 //BA.debugLineNum = 2490385;BA.debugLine="PanelMovie8.Visible = False";
mostCurrent._panelmovie8.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2490386;
 //BA.debugLineNum = 2490386;BA.debugLine="PanelMovie9.Visible = False";
mostCurrent._panelmovie9.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2490387;
 //BA.debugLineNum = 2490387;BA.debugLine="PanelMovie10.Visible = False";
mostCurrent._panelmovie10.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2490391;
 //BA.debugLineNum = 2490391;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=2490392;
 //BA.debugLineNum = 2490392;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=2490393;
 //BA.debugLineNum = 2490393;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=2490394;
 //BA.debugLineNum = 2490394;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=2490395;
 //BA.debugLineNum = 2490395;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2490396;
 //BA.debugLineNum = 2490396;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=2490398;
 //BA.debugLineNum = 2490398;BA.debugLine="Drama2.Text = \"Sherlock Holmes\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Sherlock Holmes"));
RDebugUtils.currentLine=2490399;
 //BA.debugLineNum = 2490399;BA.debugLine="Starter2.Text = \"Starring: Robert Downey Jr., Ju";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=2490400;
 //BA.debugLineNum = 2490400;BA.debugLine="Year2.Text = \"(2008)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=2490401;
 //BA.debugLineNum = 2490401;BA.debugLine="OverView2.Text = \"Detective Sherlock Holmes and";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=2490402;
 //BA.debugLineNum = 2490402;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2490403;
 //BA.debugLineNum = 2490403;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=2490405;
 //BA.debugLineNum = 2490405;BA.debugLine="Drama3.Text = \"The Transporter\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=2490406;
 //BA.debugLineNum = 2490406;BA.debugLine="Starter3.Text = \"Starring: Jason Statham, Shu Qi";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=2490407;
 //BA.debugLineNum = 2490407;BA.debugLine="Year3.Text = \"(2002)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=2490408;
 //BA.debugLineNum = 2490408;BA.debugLine="OverView3.Text = \"Frank Martin, who transports p";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=2490409;
 //BA.debugLineNum = 2490409;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2490410;
 //BA.debugLineNum = 2490410;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=2490412;
 //BA.debugLineNum = 2490412;BA.debugLine="Drama4.Text = \"Avengers: Endgame\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=2490413;
 //BA.debugLineNum = 2490413;BA.debugLine="Starter4.Text = \"Starring: Robert Downey Jr., Ch";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=2490414;
 //BA.debugLineNum = 2490414;BA.debugLine="Year4.Text = \"(2019)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=2490415;
 //BA.debugLineNum = 2490415;BA.debugLine="OverView4.Text = \"After the devastating events o";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=2490416;
 //BA.debugLineNum = 2490416;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2490417;
 //BA.debugLineNum = 2490417;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=2490419;
 //BA.debugLineNum = 2490419;BA.debugLine="Drama5.Text = \"Logan\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=2490420;
 //BA.debugLineNum = 2490420;BA.debugLine="Starter5.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=2490421;
 //BA.debugLineNum = 2490421;BA.debugLine="Year5.Text = \"(2017)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=2490422;
 //BA.debugLineNum = 2490422;BA.debugLine="OverView5.Text = \"In a future where mutants are";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=2490423;
 //BA.debugLineNum = 2490423;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
mostCurrent._dramaimage5.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2490424;
 //BA.debugLineNum = 2490424;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage5.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=2490427;
 //BA.debugLineNum = 2490427;BA.debugLine="Drama6.Text = \"Iron Man\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=2490428;
 //BA.debugLineNum = 2490428;BA.debugLine="Starter6.Text = \"Starring: Robert Downey Jr., Gw";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=2490429;
 //BA.debugLineNum = 2490429;BA.debugLine="Year6.Text = \"(2008)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=2490430;
 //BA.debugLineNum = 2490430;BA.debugLine="OverView6.Text = \"After being held captive in an";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=2490431;
 //BA.debugLineNum = 2490431;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
mostCurrent._dramaimage6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2490432;
 //BA.debugLineNum = 2490432;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage6.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=2490434;
 //BA.debugLineNum = 2490434;BA.debugLine="Drama7.Text = \"X-Men\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=2490435;
 //BA.debugLineNum = 2490435;BA.debugLine="Starter7.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=2490436;
 //BA.debugLineNum = 2490436;BA.debugLine="Year7.Text = \"(2000)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=2490437;
 //BA.debugLineNum = 2490437;BA.debugLine="OverView7.Text = \"In a world where mutants (evol";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=2490438;
 //BA.debugLineNum = 2490438;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2490439;
 //BA.debugLineNum = 2490439;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=2490441;
 //BA.debugLineNum = 2490441;BA.debugLine="Drama8.Text = \"Mr. & Mrs. Smith\"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("Mr. & Mrs. Smith"));
RDebugUtils.currentLine=2490442;
 //BA.debugLineNum = 2490442;BA.debugLine="Starter8.Text = \"Starring: Brad Pitt, Angelina J";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
RDebugUtils.currentLine=2490443;
 //BA.debugLineNum = 2490443;BA.debugLine="Year8.Text = \"(2005)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=2490444;
 //BA.debugLineNum = 2490444;BA.debugLine="OverView8.Text = \"A husband and wife struggle to";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
RDebugUtils.currentLine=2490445;
 //BA.debugLineNum = 2490445;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
mostCurrent._dramaimage8.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2490446;
 //BA.debugLineNum = 2490446;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage8.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=2490448;
 //BA.debugLineNum = 2490448;BA.debugLine="Drama9.Text = \"The Wolverine\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=2490449;
 //BA.debugLineNum = 2490449;BA.debugLine="Starter9.Text = \"Starring: Hugh Jackman, Will Yu";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=2490450;
 //BA.debugLineNum = 2490450;BA.debugLine="Year9.Text = \"(2015)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=2490451;
 //BA.debugLineNum = 2490451;BA.debugLine="OverView9.Text = \"A chance encounter between a y";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=2490452;
 //BA.debugLineNum = 2490452;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
mostCurrent._dramaimage9.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2490453;
 //BA.debugLineNum = 2490453;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage9.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=2490455;
 //BA.debugLineNum = 2490455;BA.debugLine="Drama10.Text = \"Prisoners\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=2490456;
 //BA.debugLineNum = 2490456;BA.debugLine="Starter10.Text = \"Starring: Hugh Jackman, Jake G";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=2490457;
 //BA.debugLineNum = 2490457;BA.debugLine="Year10.Text = \"(2013)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=2490458;
 //BA.debugLineNum = 2490458;BA.debugLine="OverView10.Text = \"A desperate father takes the";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=2490459;
 //BA.debugLineNum = 2490459;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2490460;
 //BA.debugLineNum = 2490460;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
 };
RDebugUtils.currentLine=2490466;
 //BA.debugLineNum = 2490466;BA.debugLine="End Sub";
return "";
}
}