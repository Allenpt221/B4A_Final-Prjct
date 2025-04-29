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
public b4a.example.main _main = null;
public b4a.example.starter _starter = null;
public b4a.example.drama _drama = null;
public b4a.example.scifi _scifi = null;
public b4a.example.panelview _panelview = null;
public static String  _actionpage_click() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "actionpage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "actionpage_click", null));}
RDebugUtils.currentLine=2883584;
 //BA.debugLineNum = 2883584;BA.debugLine="Private Sub ActionPage_Click";
RDebugUtils.currentLine=2883586;
 //BA.debugLineNum = 2883586;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=2424832;
 //BA.debugLineNum = 2424832;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=2424833;
 //BA.debugLineNum = 2424833;BA.debugLine="Activity.LoadLayout(\"action\") ' Layout contains S";
mostCurrent._activity.LoadLayout("action",mostCurrent.activityBA);
RDebugUtils.currentLine=2424835;
 //BA.debugLineNum = 2424835;BA.debugLine="p.Initialize(\"\")";
mostCurrent._p.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=2424836;
 //BA.debugLineNum = 2424836;BA.debugLine="p.LoadLayout(\"panelview\")";
mostCurrent._p.LoadLayout("panelview",mostCurrent.activityBA);
RDebugUtils.currentLine=2424843;
 //BA.debugLineNum = 2424843;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=2424844;
 //BA.debugLineNum = 2424844;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sma";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=2424845;
 //BA.debugLineNum = 2424845;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=2424846;
 //BA.debugLineNum = 2424846;BA.debugLine="OverView1.Text = \"Professional assassin Chev Chel";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=2424847;
 //BA.debugLineNum = 2424847;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2424848;
 //BA.debugLineNum = 2424848;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"crank.jpg").getObject()));
RDebugUtils.currentLine=2424850;
 //BA.debugLineNum = 2424850;BA.debugLine="Drama2.Text = \"Sherlock Holmes \"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Sherlock Holmes "));
RDebugUtils.currentLine=2424851;
 //BA.debugLineNum = 2424851;BA.debugLine="Starter2.Text = \"Starring: Robert Downey Jr., Jud";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=2424852;
 //BA.debugLineNum = 2424852;BA.debugLine="Year2.Text = \"(2008)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=2424853;
 //BA.debugLineNum = 2424853;BA.debugLine="OverView2.Text = \"Detective Sherlock Holmes and h";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=2424854;
 //BA.debugLineNum = 2424854;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2424855;
 //BA.debugLineNum = 2424855;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"sherlockHolmes.jpg").getObject()));
RDebugUtils.currentLine=2424857;
 //BA.debugLineNum = 2424857;BA.debugLine="Drama3.Text = \"The Transporter\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=2424858;
 //BA.debugLineNum = 2424858;BA.debugLine="Starter3.Text = \"Starring: Jason Statham, Shu Qi,";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=2424859;
 //BA.debugLineNum = 2424859;BA.debugLine="Year3.Text = \"(2002)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=2424860;
 //BA.debugLineNum = 2424860;BA.debugLine="OverView3.Text = \"Frank Martin, who transports pa";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=2424861;
 //BA.debugLineNum = 2424861;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2424862;
 //BA.debugLineNum = 2424862;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"transporter.jpg").getObject()));
RDebugUtils.currentLine=2424864;
 //BA.debugLineNum = 2424864;BA.debugLine="Drama4.Text = \"Avengers: Endgame\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=2424865;
 //BA.debugLineNum = 2424865;BA.debugLine="Starter4.Text = \"Starring: Robert Downey Jr., Chr";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=2424866;
 //BA.debugLineNum = 2424866;BA.debugLine="Year4.Text = \"(2019)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=2424867;
 //BA.debugLineNum = 2424867;BA.debugLine="OverView4.Text = \"After the devastating events of";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=2424868;
 //BA.debugLineNum = 2424868;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2424869;
 //BA.debugLineNum = 2424869;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"avengersEndgame.png").getObject()));
RDebugUtils.currentLine=2424871;
 //BA.debugLineNum = 2424871;BA.debugLine="Drama5.Text = \"Logan\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=2424872;
 //BA.debugLineNum = 2424872;BA.debugLine="Starter5.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=2424873;
 //BA.debugLineNum = 2424873;BA.debugLine="Year5.Text = \"(2017)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=2424874;
 //BA.debugLineNum = 2424874;BA.debugLine="OverView5.Text = \"In a future where mutants are n";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=2424875;
 //BA.debugLineNum = 2424875;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
mostCurrent._dramaimage5.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2424876;
 //BA.debugLineNum = 2424876;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage5.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"logan.jpg").getObject()));
RDebugUtils.currentLine=2424879;
 //BA.debugLineNum = 2424879;BA.debugLine="Drama6.Text = \"Iron Man\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=2424880;
 //BA.debugLineNum = 2424880;BA.debugLine="Starter6.Text = \"Starring: Robert Downey Jr., Gwy";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=2424881;
 //BA.debugLineNum = 2424881;BA.debugLine="Year6.Text = \"(2008)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=2424882;
 //BA.debugLineNum = 2424882;BA.debugLine="OverView6.Text = \"After being held captive in an";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=2424883;
 //BA.debugLineNum = 2424883;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
mostCurrent._dramaimage6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2424884;
 //BA.debugLineNum = 2424884;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage6.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ironman.jpg").getObject()));
RDebugUtils.currentLine=2424886;
 //BA.debugLineNum = 2424886;BA.debugLine="Drama7.Text = \"X-Men\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=2424887;
 //BA.debugLineNum = 2424887;BA.debugLine="Starter7.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=2424888;
 //BA.debugLineNum = 2424888;BA.debugLine="Year7.Text = \"(2000)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=2424889;
 //BA.debugLineNum = 2424889;BA.debugLine="OverView7.Text = \"In a world where mutants (evolv";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=2424890;
 //BA.debugLineNum = 2424890;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2424891;
 //BA.debugLineNum = 2424891;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"xmen.jpg").getObject()));
RDebugUtils.currentLine=2424893;
 //BA.debugLineNum = 2424893;BA.debugLine="Drama8.Text = \"Mr. & Mrs. Smith \"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("Mr. & Mrs. Smith "));
RDebugUtils.currentLine=2424894;
 //BA.debugLineNum = 2424894;BA.debugLine="Starter8.Text = \"Starring: Brad Pitt, Angelina Jo";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
RDebugUtils.currentLine=2424895;
 //BA.debugLineNum = 2424895;BA.debugLine="Year8.Text = \"(2005)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=2424896;
 //BA.debugLineNum = 2424896;BA.debugLine="OverView8.Text = \"A husband and wife struggle to";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
RDebugUtils.currentLine=2424897;
 //BA.debugLineNum = 2424897;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
mostCurrent._dramaimage8.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2424898;
 //BA.debugLineNum = 2424898;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage8.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"MrAndMrs.png").getObject()));
RDebugUtils.currentLine=2424900;
 //BA.debugLineNum = 2424900;BA.debugLine="Drama9.Text = \"The Wolverine\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=2424901;
 //BA.debugLineNum = 2424901;BA.debugLine="Starter9.Text = \"Starring: Hugh Jackman, Will Yun";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=2424902;
 //BA.debugLineNum = 2424902;BA.debugLine="Year9.Text = \"(2015)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=2424903;
 //BA.debugLineNum = 2424903;BA.debugLine="OverView9.Text = \"A chance encounter between a yo";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=2424904;
 //BA.debugLineNum = 2424904;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
mostCurrent._dramaimage9.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2424905;
 //BA.debugLineNum = 2424905;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage9.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"wolverine.png").getObject()));
RDebugUtils.currentLine=2424907;
 //BA.debugLineNum = 2424907;BA.debugLine="Drama10.Text = \"Prisoners\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=2424908;
 //BA.debugLineNum = 2424908;BA.debugLine="Starter10.Text = \"Starring: Hugh Jackman, Jake Gy";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=2424909;
 //BA.debugLineNum = 2424909;BA.debugLine="Year10.Text = \"(2013)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=2424910;
 //BA.debugLineNum = 2424910;BA.debugLine="OverView10.Text = \"A desperate father takes the l";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=2424911;
 //BA.debugLineNum = 2424911;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2424912;
 //BA.debugLineNum = 2424912;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"prisoners.jpg").getObject()));
RDebugUtils.currentLine=2424914;
 //BA.debugLineNum = 2424914;BA.debugLine="ScrollView1.Panel.AddView(p, 0, 0, 200%x, 210%y)";
mostCurrent._scrollview1.getPanel().AddView((android.view.View)(mostCurrent._p.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=2424915;
 //BA.debugLineNum = 2424915;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=2424916;
 //BA.debugLineNum = 2424916;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="action";
RDebugUtils.currentLine=2752512;
 //BA.debugLineNum = 2752512;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=2752514;
 //BA.debugLineNum = 2752514;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=2686976;
 //BA.debugLineNum = 2686976;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=2686978;
 //BA.debugLineNum = 2686978;BA.debugLine="End Sub";
return "";
}
public static String  _dramapage_click() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "dramapage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "dramapage_click", null));}
RDebugUtils.currentLine=2949120;
 //BA.debugLineNum = 2949120;BA.debugLine="Private Sub DramaPage_Click";
RDebugUtils.currentLine=2949121;
 //BA.debugLineNum = 2949121;BA.debugLine="StartActivity(Drama)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._drama.getObject()));
RDebugUtils.currentLine=2949122;
 //BA.debugLineNum = 2949122;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=2949123;
 //BA.debugLineNum = 2949123;BA.debugLine="End Sub";
return "";
}
public static String  _homepage_click() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "homepage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "homepage_click", null));}
RDebugUtils.currentLine=3014656;
 //BA.debugLineNum = 3014656;BA.debugLine="Private Sub HomePage_Click";
RDebugUtils.currentLine=3014657;
 //BA.debugLineNum = 3014657;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
RDebugUtils.currentLine=3014658;
 //BA.debugLineNum = 3014658;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=3014659;
 //BA.debugLineNum = 3014659;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=3080193;
 //BA.debugLineNum = 3080193;BA.debugLine="Try";
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
RDebugUtils.currentLine=3080194;
 //BA.debugLineNum = 3080194;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3080195;
 //BA.debugLineNum = 3080195;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "action", "panelmovie1_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=3080196;
 //BA.debugLineNum = 3080196;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=3080197;
 //BA.debugLineNum = 3080197;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=3080198;
 //BA.debugLineNum = 3080198;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0479884/");
RDebugUtils.currentLine=3080199;
 //BA.debugLineNum = 3080199;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=3080200;
 //BA.debugLineNum = 3080200;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=3080204;
 //BA.debugLineNum = 3080204;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("53080204",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=3080205;
 //BA.debugLineNum = 3080205;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=3080208;
 //BA.debugLineNum = 3080208;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=3670017;
 //BA.debugLineNum = 3670017;BA.debugLine="Try";
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
RDebugUtils.currentLine=3670018;
 //BA.debugLineNum = 3670018;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3670019;
 //BA.debugLineNum = 3670019;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "action", "panelmovie10_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=3670020;
 //BA.debugLineNum = 3670020;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=3670021;
 //BA.debugLineNum = 3670021;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=3670022;
 //BA.debugLineNum = 3670022;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt1392214/");
RDebugUtils.currentLine=3670023;
 //BA.debugLineNum = 3670023;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=3670024;
 //BA.debugLineNum = 3670024;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=3670027;
 //BA.debugLineNum = 3670027;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("53670027",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=3670028;
 //BA.debugLineNum = 3670028;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=3670031;
 //BA.debugLineNum = 3670031;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=3145729;
 //BA.debugLineNum = 3145729;BA.debugLine="Try";
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
RDebugUtils.currentLine=3145730;
 //BA.debugLineNum = 3145730;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3145731;
 //BA.debugLineNum = 3145731;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "action", "panelmovie2_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=3145732;
 //BA.debugLineNum = 3145732;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=3145733;
 //BA.debugLineNum = 3145733;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=3145734;
 //BA.debugLineNum = 3145734;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0988045/");
RDebugUtils.currentLine=3145735;
 //BA.debugLineNum = 3145735;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=3145736;
 //BA.debugLineNum = 3145736;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=3145739;
 //BA.debugLineNum = 3145739;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("53145739",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=3145740;
 //BA.debugLineNum = 3145740;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=3145743;
 //BA.debugLineNum = 3145743;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=3211265;
 //BA.debugLineNum = 3211265;BA.debugLine="Try";
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
RDebugUtils.currentLine=3211266;
 //BA.debugLineNum = 3211266;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211267;
 //BA.debugLineNum = 3211267;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "action", "panelmovie3_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=3211268;
 //BA.debugLineNum = 3211268;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=3211269;
 //BA.debugLineNum = 3211269;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=3211270;
 //BA.debugLineNum = 3211270;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt4154796/");
RDebugUtils.currentLine=3211271;
 //BA.debugLineNum = 3211271;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=3211272;
 //BA.debugLineNum = 3211272;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=3211275;
 //BA.debugLineNum = 3211275;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("53211275",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=3211276;
 //BA.debugLineNum = 3211276;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=3211279;
 //BA.debugLineNum = 3211279;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=3276801;
 //BA.debugLineNum = 3276801;BA.debugLine="Try";
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
RDebugUtils.currentLine=3276802;
 //BA.debugLineNum = 3276802;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3276803;
 //BA.debugLineNum = 3276803;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "action", "panelmovie4_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=3276804;
 //BA.debugLineNum = 3276804;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=3276805;
 //BA.debugLineNum = 3276805;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=3276806;
 //BA.debugLineNum = 3276806;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt3315342/");
RDebugUtils.currentLine=3276807;
 //BA.debugLineNum = 3276807;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=3276808;
 //BA.debugLineNum = 3276808;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=3276811;
 //BA.debugLineNum = 3276811;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("53276811",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=3276812;
 //BA.debugLineNum = 3276812;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=3276815;
 //BA.debugLineNum = 3276815;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=3342337;
 //BA.debugLineNum = 3342337;BA.debugLine="Try";
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
RDebugUtils.currentLine=3342338;
 //BA.debugLineNum = 3342338;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3342339;
 //BA.debugLineNum = 3342339;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "action", "panelmovie5_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=3342340;
 //BA.debugLineNum = 3342340;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=3342341;
 //BA.debugLineNum = 3342341;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=3342342;
 //BA.debugLineNum = 3342342;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0371746/");
RDebugUtils.currentLine=3342343;
 //BA.debugLineNum = 3342343;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=3342344;
 //BA.debugLineNum = 3342344;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=3342347;
 //BA.debugLineNum = 3342347;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("53342347",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=3342348;
 //BA.debugLineNum = 3342348;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=3342351;
 //BA.debugLineNum = 3342351;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=3407873;
 //BA.debugLineNum = 3407873;BA.debugLine="Try";
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
RDebugUtils.currentLine=3407874;
 //BA.debugLineNum = 3407874;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3407875;
 //BA.debugLineNum = 3407875;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "action", "panelmovie6_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=3407876;
 //BA.debugLineNum = 3407876;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=3407877;
 //BA.debugLineNum = 3407877;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=3407878;
 //BA.debugLineNum = 3407878;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0120903/");
RDebugUtils.currentLine=3407879;
 //BA.debugLineNum = 3407879;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=3407880;
 //BA.debugLineNum = 3407880;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=3407883;
 //BA.debugLineNum = 3407883;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("53407883",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=3407884;
 //BA.debugLineNum = 3407884;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=3407887;
 //BA.debugLineNum = 3407887;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=3473409;
 //BA.debugLineNum = 3473409;BA.debugLine="Try";
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
RDebugUtils.currentLine=3473410;
 //BA.debugLineNum = 3473410;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3473411;
 //BA.debugLineNum = 3473411;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "action", "panelmovie7_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=3473412;
 //BA.debugLineNum = 3473412;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=3473413;
 //BA.debugLineNum = 3473413;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=3473414;
 //BA.debugLineNum = 3473414;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt2334873/");
RDebugUtils.currentLine=3473415;
 //BA.debugLineNum = 3473415;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=3473416;
 //BA.debugLineNum = 3473416;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=3473419;
 //BA.debugLineNum = 3473419;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("53473419",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=3473420;
 //BA.debugLineNum = 3473420;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=3473423;
 //BA.debugLineNum = 3473423;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=3538945;
 //BA.debugLineNum = 3538945;BA.debugLine="Try";
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
RDebugUtils.currentLine=3538946;
 //BA.debugLineNum = 3538946;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3538947;
 //BA.debugLineNum = 3538947;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "action", "panelmovie8_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=3538948;
 //BA.debugLineNum = 3538948;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=3538949;
 //BA.debugLineNum = 3538949;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=3538950;
 //BA.debugLineNum = 3538950;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0356910/");
RDebugUtils.currentLine=3538951;
 //BA.debugLineNum = 3538951;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=3538952;
 //BA.debugLineNum = 3538952;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=3538955;
 //BA.debugLineNum = 3538955;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("53538955",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=3538956;
 //BA.debugLineNum = 3538956;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=3538959;
 //BA.debugLineNum = 3538959;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=3604481;
 //BA.debugLineNum = 3604481;BA.debugLine="Try";
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
RDebugUtils.currentLine=3604482;
 //BA.debugLineNum = 3604482;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3604483;
 //BA.debugLineNum = 3604483;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "action", "panelmovie9_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=3604484;
 //BA.debugLineNum = 3604484;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=3604485;
 //BA.debugLineNum = 3604485;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=3604486;
 //BA.debugLineNum = 3604486;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt1430132/");
RDebugUtils.currentLine=3604487;
 //BA.debugLineNum = 3604487;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=3604488;
 //BA.debugLineNum = 3604488;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=3604491;
 //BA.debugLineNum = 3604491;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("53604491",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=3604492;
 //BA.debugLineNum = 3604492;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=3604495;
 //BA.debugLineNum = 3604495;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=2818048;
 //BA.debugLineNum = 2818048;BA.debugLine="Private Sub SciFiPage_Click";
RDebugUtils.currentLine=2818049;
 //BA.debugLineNum = 2818049;BA.debugLine="StartActivity(SciFi)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._scifi.getObject()));
RDebugUtils.currentLine=2818050;
 //BA.debugLineNum = 2818050;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=2818051;
 //BA.debugLineNum = 2818051;BA.debugLine="End Sub";
return "";
}
public static String  _searchbtn_click() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchbtn_click", null));}
RDebugUtils.currentLine=2490368;
 //BA.debugLineNum = 2490368;BA.debugLine="Private Sub SearchBtn_Click";
RDebugUtils.currentLine=2490369;
 //BA.debugLineNum = 2490369;BA.debugLine="SearchNow";
_searchnow();
RDebugUtils.currentLine=2490370;
 //BA.debugLineNum = 2490370;BA.debugLine="End Sub";
return "";
}
public static String  _searchnow() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchnow", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchnow", null));}
String _query = "";
String _userinput = "";
RDebugUtils.currentLine=2555904;
 //BA.debugLineNum = 2555904;BA.debugLine="Sub SearchNow";
RDebugUtils.currentLine=2555905;
 //BA.debugLineNum = 2555905;BA.debugLine="Dim query As String = SearchEngine.Text.ToLowerCa";
_query = mostCurrent._searchengine.getText().toLowerCase().trim();
RDebugUtils.currentLine=2555908;
 //BA.debugLineNum = 2555908;BA.debugLine="Dim UserInput As String = SearchEngine.Text";
_userinput = mostCurrent._searchengine.getText();
RDebugUtils.currentLine=2555911;
 //BA.debugLineNum = 2555911;BA.debugLine="If query.Contains(\"crank\") Then";
if (_query.contains("crank")) { 
RDebugUtils.currentLine=2555913;
 //BA.debugLineNum = 2555913;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=2555914;
 //BA.debugLineNum = 2555914;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=2555915;
 //BA.debugLineNum = 2555915;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=2555916;
 //BA.debugLineNum = 2555916;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=2555917;
 //BA.debugLineNum = 2555917;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2555918;
 //BA.debugLineNum = 2555918;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"crank.jpg").getObject()));
RDebugUtils.currentLine=2555920;
 //BA.debugLineNum = 2555920;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555921;
 //BA.debugLineNum = 2555921;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555922;
 //BA.debugLineNum = 2555922;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555923;
 //BA.debugLineNum = 2555923;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555924;
 //BA.debugLineNum = 2555924;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555926;
 //BA.debugLineNum = 2555926;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2555928;
 //BA.debugLineNum = 2555928;BA.debugLine="Else If query.Contains(\"sherlock\") Or query.Conta";
if (_query.contains("sherlock") || _query.contains("sherlock holmes")) { 
RDebugUtils.currentLine=2555930;
 //BA.debugLineNum = 2555930;BA.debugLine="Drama1.Text = \"Sherlock Holmes \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Sherlock Holmes "));
RDebugUtils.currentLine=2555931;
 //BA.debugLineNum = 2555931;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ju";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=2555932;
 //BA.debugLineNum = 2555932;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=2555933;
 //BA.debugLineNum = 2555933;BA.debugLine="OverView1.Text = \"Detective Sherlock Holmes and";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=2555934;
 //BA.debugLineNum = 2555934;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2555935;
 //BA.debugLineNum = 2555935;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"sherlockHolmes.jpg").getObject()));
RDebugUtils.currentLine=2555937;
 //BA.debugLineNum = 2555937;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555938;
 //BA.debugLineNum = 2555938;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555939;
 //BA.debugLineNum = 2555939;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555940;
 //BA.debugLineNum = 2555940;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555941;
 //BA.debugLineNum = 2555941;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555943;
 //BA.debugLineNum = 2555943;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2555945;
 //BA.debugLineNum = 2555945;BA.debugLine="Else If query.Contains(\"the transporter\") Or quer";
if (_query.contains("the transporter") || _query.contains("transporter")) { 
RDebugUtils.currentLine=2555947;
 //BA.debugLineNum = 2555947;BA.debugLine="Drama1.Text = \"The Transporter\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=2555948;
 //BA.debugLineNum = 2555948;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Shu Qi";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=2555949;
 //BA.debugLineNum = 2555949;BA.debugLine="Year1.Text = \"(2002)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=2555950;
 //BA.debugLineNum = 2555950;BA.debugLine="OverView1.Text = \"Frank Martin, who transports p";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=2555951;
 //BA.debugLineNum = 2555951;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2555952;
 //BA.debugLineNum = 2555952;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"transporter.jpg").getObject()));
RDebugUtils.currentLine=2555954;
 //BA.debugLineNum = 2555954;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555955;
 //BA.debugLineNum = 2555955;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555956;
 //BA.debugLineNum = 2555956;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555957;
 //BA.debugLineNum = 2555957;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555958;
 //BA.debugLineNum = 2555958;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555960;
 //BA.debugLineNum = 2555960;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2555962;
 //BA.debugLineNum = 2555962;BA.debugLine="Else If query.Contains(\"avengers endgame\") Or que";
if (_query.contains("avengers endgame") || _query.contains("avengers") || _query.contains("endgame")) { 
RDebugUtils.currentLine=2555964;
 //BA.debugLineNum = 2555964;BA.debugLine="Drama1.Text = \"Avengers: Endgame\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=2555965;
 //BA.debugLineNum = 2555965;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ch";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=2555966;
 //BA.debugLineNum = 2555966;BA.debugLine="Year1.Text = \"(2019)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=2555967;
 //BA.debugLineNum = 2555967;BA.debugLine="OverView1.Text = \"After the devastating events o";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=2555968;
 //BA.debugLineNum = 2555968;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2555969;
 //BA.debugLineNum = 2555969;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"avengersEndgame.png").getObject()));
RDebugUtils.currentLine=2555971;
 //BA.debugLineNum = 2555971;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555972;
 //BA.debugLineNum = 2555972;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555973;
 //BA.debugLineNum = 2555973;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555974;
 //BA.debugLineNum = 2555974;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555975;
 //BA.debugLineNum = 2555975;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555977;
 //BA.debugLineNum = 2555977;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2555979;
 //BA.debugLineNum = 2555979;BA.debugLine="Else If query.Contains(\"logan\") Then";
if (_query.contains("logan")) { 
RDebugUtils.currentLine=2555981;
 //BA.debugLineNum = 2555981;BA.debugLine="Drama1.Text = \"Logan\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=2555982;
 //BA.debugLineNum = 2555982;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=2555983;
 //BA.debugLineNum = 2555983;BA.debugLine="Year1.Text = \"(2017)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=2555984;
 //BA.debugLineNum = 2555984;BA.debugLine="OverView1.Text = \"In a future where mutants are";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=2555985;
 //BA.debugLineNum = 2555985;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2555986;
 //BA.debugLineNum = 2555986;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"logan.jpg").getObject()));
RDebugUtils.currentLine=2555988;
 //BA.debugLineNum = 2555988;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555989;
 //BA.debugLineNum = 2555989;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555990;
 //BA.debugLineNum = 2555990;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555991;
 //BA.debugLineNum = 2555991;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555992;
 //BA.debugLineNum = 2555992;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555994;
 //BA.debugLineNum = 2555994;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2555996;
 //BA.debugLineNum = 2555996;BA.debugLine="Else If query.Contains(\"iron man\") Or query.Conta";
if (_query.contains("iron man") || _query.contains("man") || _query.contains("iron")) { 
RDebugUtils.currentLine=2555998;
 //BA.debugLineNum = 2555998;BA.debugLine="Drama1.Text = \"Iron Man\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=2555999;
 //BA.debugLineNum = 2555999;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Gw";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=2556000;
 //BA.debugLineNum = 2556000;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=2556001;
 //BA.debugLineNum = 2556001;BA.debugLine="OverView1.Text = \"After being held captive in an";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=2556002;
 //BA.debugLineNum = 2556002;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556003;
 //BA.debugLineNum = 2556003;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ironman.jpg").getObject()));
RDebugUtils.currentLine=2556005;
 //BA.debugLineNum = 2556005;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556006;
 //BA.debugLineNum = 2556006;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556007;
 //BA.debugLineNum = 2556007;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556008;
 //BA.debugLineNum = 2556008;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556009;
 //BA.debugLineNum = 2556009;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556011;
 //BA.debugLineNum = 2556011;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2556013;
 //BA.debugLineNum = 2556013;BA.debugLine="Else If query.Contains(\"x-men\") Or query.Contains";
if (_query.contains("x-men") || _query.contains("men") || _query.contains("xmen") || _query.contains("x")) { 
RDebugUtils.currentLine=2556015;
 //BA.debugLineNum = 2556015;BA.debugLine="Drama7.Text = \"X-Men\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=2556016;
 //BA.debugLineNum = 2556016;BA.debugLine="Starter7.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=2556017;
 //BA.debugLineNum = 2556017;BA.debugLine="Year7.Text = \"(2000)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=2556018;
 //BA.debugLineNum = 2556018;BA.debugLine="OverView7.Text = \"In a world where mutants (evol";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=2556019;
 //BA.debugLineNum = 2556019;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556020;
 //BA.debugLineNum = 2556020;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"xmen.jpg").getObject()));
RDebugUtils.currentLine=2556022;
 //BA.debugLineNum = 2556022;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556023;
 //BA.debugLineNum = 2556023;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556024;
 //BA.debugLineNum = 2556024;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556025;
 //BA.debugLineNum = 2556025;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556026;
 //BA.debugLineNum = 2556026;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556028;
 //BA.debugLineNum = 2556028;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2556030;
 //BA.debugLineNum = 2556030;BA.debugLine="Else If query.Contains(\"mr & mrs smith\") Or query";
if (_query.contains("mr & mrs smith") || _query.contains("mr and mrs") || _query.contains("smith") || _query.contains("mrs") || _query.contains("mr")) { 
RDebugUtils.currentLine=2556032;
 //BA.debugLineNum = 2556032;BA.debugLine="Drama1.Text = \"Mr. & Mrs. Smith \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Mr. & Mrs. Smith "));
RDebugUtils.currentLine=2556033;
 //BA.debugLineNum = 2556033;BA.debugLine="Starter1.Text = \"Starring: Brad Pitt, Angelina J";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
RDebugUtils.currentLine=2556034;
 //BA.debugLineNum = 2556034;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=2556035;
 //BA.debugLineNum = 2556035;BA.debugLine="OverView1.Text = \"A husband and wife struggle to";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
RDebugUtils.currentLine=2556036;
 //BA.debugLineNum = 2556036;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556037;
 //BA.debugLineNum = 2556037;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"MrAndMrs.png").getObject()));
RDebugUtils.currentLine=2556039;
 //BA.debugLineNum = 2556039;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556040;
 //BA.debugLineNum = 2556040;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556041;
 //BA.debugLineNum = 2556041;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556042;
 //BA.debugLineNum = 2556042;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556043;
 //BA.debugLineNum = 2556043;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556045;
 //BA.debugLineNum = 2556045;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2556047;
 //BA.debugLineNum = 2556047;BA.debugLine="Else If query.Contains(\"the wolverine\") Or query.";
if (_query.contains("the wolverine") || _query.contains("wolverine")) { 
RDebugUtils.currentLine=2556049;
 //BA.debugLineNum = 2556049;BA.debugLine="Drama1.Text = \"The Wolverine\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=2556050;
 //BA.debugLineNum = 2556050;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Will Yu";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=2556051;
 //BA.debugLineNum = 2556051;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=2556052;
 //BA.debugLineNum = 2556052;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=2556053;
 //BA.debugLineNum = 2556053;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556054;
 //BA.debugLineNum = 2556054;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"wolverine.png").getObject()));
RDebugUtils.currentLine=2556056;
 //BA.debugLineNum = 2556056;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556057;
 //BA.debugLineNum = 2556057;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556058;
 //BA.debugLineNum = 2556058;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556059;
 //BA.debugLineNum = 2556059;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556060;
 //BA.debugLineNum = 2556060;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556062;
 //BA.debugLineNum = 2556062;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2556064;
 //BA.debugLineNum = 2556064;BA.debugLine="Else If query.Contains(\"prisoners\") Or query.Cont";
if (_query.contains("prisoners") || _query.contains("prisoner") || _query.contains("pri")) { 
RDebugUtils.currentLine=2556066;
 //BA.debugLineNum = 2556066;BA.debugLine="Drama10.Text = \"Prisoners\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=2556067;
 //BA.debugLineNum = 2556067;BA.debugLine="Starter10.Text = \"Starring: Hugh Jackman, Jake G";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=2556068;
 //BA.debugLineNum = 2556068;BA.debugLine="Year10.Text = \"(2013)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=2556069;
 //BA.debugLineNum = 2556069;BA.debugLine="OverView10.Text = \"A desperate father takes the";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=2556070;
 //BA.debugLineNum = 2556070;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556071;
 //BA.debugLineNum = 2556071;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"prisoners.jpg").getObject()));
RDebugUtils.currentLine=2556073;
 //BA.debugLineNum = 2556073;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556074;
 //BA.debugLineNum = 2556074;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556075;
 //BA.debugLineNum = 2556075;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556076;
 //BA.debugLineNum = 2556076;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556077;
 //BA.debugLineNum = 2556077;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556079;
 //BA.debugLineNum = 2556079;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2556081;
 //BA.debugLineNum = 2556081;BA.debugLine="Else If query.Contains(\"jason statham\") Or query.";
if (_query.contains("jason statham") || _query.contains("jason") || _query.contains("statham")) { 
RDebugUtils.currentLine=2556082;
 //BA.debugLineNum = 2556082;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=2556083;
 //BA.debugLineNum = 2556083;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=2556084;
 //BA.debugLineNum = 2556084;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=2556085;
 //BA.debugLineNum = 2556085;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=2556086;
 //BA.debugLineNum = 2556086;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556087;
 //BA.debugLineNum = 2556087;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"crank.jpg").getObject()));
RDebugUtils.currentLine=2556089;
 //BA.debugLineNum = 2556089;BA.debugLine="Drama2.Text = \"The Transporter\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=2556090;
 //BA.debugLineNum = 2556090;BA.debugLine="Starter2.Text = \"Starring: Jason Statham, Shu Qi";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=2556091;
 //BA.debugLineNum = 2556091;BA.debugLine="Year2.Text = \"(2002)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=2556092;
 //BA.debugLineNum = 2556092;BA.debugLine="OverView2.Text = \"Frank Martin, who transports p";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=2556093;
 //BA.debugLineNum = 2556093;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556094;
 //BA.debugLineNum = 2556094;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"transporter.jpg").getObject()));
RDebugUtils.currentLine=2556096;
 //BA.debugLineNum = 2556096;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556097;
 //BA.debugLineNum = 2556097;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556098;
 //BA.debugLineNum = 2556098;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556100;
 //BA.debugLineNum = 2556100;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2556102;
 //BA.debugLineNum = 2556102;BA.debugLine="Else If query.Contains(\"Robert downey jr\") Or que";
if (_query.contains("Robert downey jr") || _query.contains("robert") || _query.contains("downey") || _query.contains("downey jr")) { 
RDebugUtils.currentLine=2556103;
 //BA.debugLineNum = 2556103;BA.debugLine="Drama1.Text = \"Sherlock Holmes \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Sherlock Holmes "));
RDebugUtils.currentLine=2556104;
 //BA.debugLineNum = 2556104;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ju";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=2556105;
 //BA.debugLineNum = 2556105;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=2556106;
 //BA.debugLineNum = 2556106;BA.debugLine="OverView1.Text = \"Detective Sherlock Holmes and";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=2556107;
 //BA.debugLineNum = 2556107;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556108;
 //BA.debugLineNum = 2556108;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"sherlockHolmes.jpg").getObject()));
RDebugUtils.currentLine=2556110;
 //BA.debugLineNum = 2556110;BA.debugLine="Drama2.Text = \"Avengers: Endgame\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=2556111;
 //BA.debugLineNum = 2556111;BA.debugLine="Starter2.Text = \"Starring: Robert Downey Jr., Ch";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=2556112;
 //BA.debugLineNum = 2556112;BA.debugLine="Year2.Text = \"(2019)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=2556113;
 //BA.debugLineNum = 2556113;BA.debugLine="OverView2.Text = \"After the devastating events o";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=2556114;
 //BA.debugLineNum = 2556114;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556115;
 //BA.debugLineNum = 2556115;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"avengersEndgame.png").getObject()));
RDebugUtils.currentLine=2556117;
 //BA.debugLineNum = 2556117;BA.debugLine="Drama3.Text = \"Iron Man\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=2556118;
 //BA.debugLineNum = 2556118;BA.debugLine="Starter3.Text = \"Starring: Robert Downey Jr., Gw";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=2556119;
 //BA.debugLineNum = 2556119;BA.debugLine="Year3.Text = \"(2008)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=2556120;
 //BA.debugLineNum = 2556120;BA.debugLine="OverView3.Text = \"After being held captive in an";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=2556121;
 //BA.debugLineNum = 2556121;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556122;
 //BA.debugLineNum = 2556122;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ironman.jpg").getObject()));
RDebugUtils.currentLine=2556124;
 //BA.debugLineNum = 2556124;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556125;
 //BA.debugLineNum = 2556125;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556126;
 //BA.debugLineNum = 2556126;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556128;
 //BA.debugLineNum = 2556128;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2556130;
 //BA.debugLineNum = 2556130;BA.debugLine="Else If query.Contains(\"patrick stewart\") Or quer";
if (_query.contains("patrick stewart") || _query.contains("patrick") || _query.contains("stewart")) { 
RDebugUtils.currentLine=2556131;
 //BA.debugLineNum = 2556131;BA.debugLine="Drama1.Text = \"Logan\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=2556132;
 //BA.debugLineNum = 2556132;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=2556133;
 //BA.debugLineNum = 2556133;BA.debugLine="Year1.Text = \"(2017)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=2556134;
 //BA.debugLineNum = 2556134;BA.debugLine="OverView1.Text = \"In a future where mutants are";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=2556135;
 //BA.debugLineNum = 2556135;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556136;
 //BA.debugLineNum = 2556136;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"logan.jpg").getObject()));
RDebugUtils.currentLine=2556138;
 //BA.debugLineNum = 2556138;BA.debugLine="Drama2.Text = \"X-Men\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=2556139;
 //BA.debugLineNum = 2556139;BA.debugLine="Starter2.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=2556140;
 //BA.debugLineNum = 2556140;BA.debugLine="Year2.Text = \"(2000)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=2556141;
 //BA.debugLineNum = 2556141;BA.debugLine="OverView2.Text = \"In a world where mutants (evol";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=2556142;
 //BA.debugLineNum = 2556142;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556143;
 //BA.debugLineNum = 2556143;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"xmen.jpg").getObject()));
RDebugUtils.currentLine=2556145;
 //BA.debugLineNum = 2556145;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556146;
 //BA.debugLineNum = 2556146;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556147;
 //BA.debugLineNum = 2556147;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556148;
 //BA.debugLineNum = 2556148;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556150;
 //BA.debugLineNum = 2556150;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2556152;
 //BA.debugLineNum = 2556152;BA.debugLine="Else If query.Contains(\"hugh jackman\") Or query.C";
if (_query.contains("hugh jackman") || _query.contains("hugh") || _query.contains("jackman")) { 
RDebugUtils.currentLine=2556153;
 //BA.debugLineNum = 2556153;BA.debugLine="Drama1.Text = \"Logan\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=2556154;
 //BA.debugLineNum = 2556154;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=2556155;
 //BA.debugLineNum = 2556155;BA.debugLine="Year1.Text = \"(2017)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=2556156;
 //BA.debugLineNum = 2556156;BA.debugLine="OverView1.Text = \"In a future where mutants are";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=2556157;
 //BA.debugLineNum = 2556157;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556158;
 //BA.debugLineNum = 2556158;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"logan.jpg").getObject()));
RDebugUtils.currentLine=2556160;
 //BA.debugLineNum = 2556160;BA.debugLine="Drama2.Text = \"X-Men\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=2556161;
 //BA.debugLineNum = 2556161;BA.debugLine="Starter2.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=2556162;
 //BA.debugLineNum = 2556162;BA.debugLine="Year2.Text = \"(2000)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=2556163;
 //BA.debugLineNum = 2556163;BA.debugLine="OverView2.Text = \"In a world where mutants (evol";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=2556164;
 //BA.debugLineNum = 2556164;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556165;
 //BA.debugLineNum = 2556165;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"xmen.jpg").getObject()));
RDebugUtils.currentLine=2556167;
 //BA.debugLineNum = 2556167;BA.debugLine="Drama3.Text = \"The Wolverine\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=2556168;
 //BA.debugLineNum = 2556168;BA.debugLine="Starter3.Text = \"Starring: Hugh Jackman, Will Yu";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=2556169;
 //BA.debugLineNum = 2556169;BA.debugLine="Year3.Text = \"(2015)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=2556170;
 //BA.debugLineNum = 2556170;BA.debugLine="OverView3.Text = \"A chance encounter between a y";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=2556171;
 //BA.debugLineNum = 2556171;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556172;
 //BA.debugLineNum = 2556172;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"wolverine.png").getObject()));
RDebugUtils.currentLine=2556174;
 //BA.debugLineNum = 2556174;BA.debugLine="Drama4.Text = \"Prisoners\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=2556175;
 //BA.debugLineNum = 2556175;BA.debugLine="Starter4.Text = \"Starring: Hugh Jackman, Jake Gy";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=2556176;
 //BA.debugLineNum = 2556176;BA.debugLine="Year4.Text = \"(2013)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=2556177;
 //BA.debugLineNum = 2556177;BA.debugLine="OverView4.Text = \"A desperate father takes the l";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=2556178;
 //BA.debugLineNum = 2556178;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556179;
 //BA.debugLineNum = 2556179;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"prisoners.jpg").getObject()));
RDebugUtils.currentLine=2556181;
 //BA.debugLineNum = 2556181;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556182;
 //BA.debugLineNum = 2556182;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556184;
 //BA.debugLineNum = 2556184;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2556186;
 //BA.debugLineNum = 2556186;BA.debugLine="Else If query.Contains(\"amy smart\") Or query.Cont";
if (_query.contains("amy smart") || _query.contains("amy") || _query.contains("smart") || _query.contains("carlos sanz") || _query.contains("carlos") || _query.contains("sanz")) { 
RDebugUtils.currentLine=2556187;
 //BA.debugLineNum = 2556187;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=2556188;
 //BA.debugLineNum = 2556188;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=2556189;
 //BA.debugLineNum = 2556189;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=2556190;
 //BA.debugLineNum = 2556190;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=2556191;
 //BA.debugLineNum = 2556191;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556192;
 //BA.debugLineNum = 2556192;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"crank.jpg").getObject()));
RDebugUtils.currentLine=2556194;
 //BA.debugLineNum = 2556194;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556195;
 //BA.debugLineNum = 2556195;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556196;
 //BA.debugLineNum = 2556196;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556197;
 //BA.debugLineNum = 2556197;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556198;
 //BA.debugLineNum = 2556198;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556200;
 //BA.debugLineNum = 2556200;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2556201;
 //BA.debugLineNum = 2556201;BA.debugLine="Else If query.Contains(\"jude law\") Or query.Conta";
if (_query.contains("jude law") || _query.contains("jude") || _query.contains("law") || _query.contains("rachel mcadams") || _query.contains("rachel") || _query.contains("mcadams")) { 
RDebugUtils.currentLine=2556202;
 //BA.debugLineNum = 2556202;BA.debugLine="Drama1.Text = \"Sherlock Holmes \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Sherlock Holmes "));
RDebugUtils.currentLine=2556203;
 //BA.debugLineNum = 2556203;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ju";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=2556204;
 //BA.debugLineNum = 2556204;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=2556205;
 //BA.debugLineNum = 2556205;BA.debugLine="OverView1.Text = \"Detective Sherlock Holmes and";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=2556206;
 //BA.debugLineNum = 2556206;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556207;
 //BA.debugLineNum = 2556207;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"sherlockHolmes.jpg").getObject()));
RDebugUtils.currentLine=2556209;
 //BA.debugLineNum = 2556209;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556210;
 //BA.debugLineNum = 2556210;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556211;
 //BA.debugLineNum = 2556211;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556212;
 //BA.debugLineNum = 2556212;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556213;
 //BA.debugLineNum = 2556213;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556215;
 //BA.debugLineNum = 2556215;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2556216;
 //BA.debugLineNum = 2556216;BA.debugLine="Else if query.Contains(\"shu qi\") Or query.Contain";
if (_query.contains("shu qi") || _query.contains("shu") || _query.contains("qi") || _query.contains("matt") || _query.contains("schulze") || _query.contains("matt schulze")) { 
RDebugUtils.currentLine=2556217;
 //BA.debugLineNum = 2556217;BA.debugLine="Drama1.Text = \"The Transporter\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=2556218;
 //BA.debugLineNum = 2556218;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Shu Qi";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=2556219;
 //BA.debugLineNum = 2556219;BA.debugLine="Year1.Text = \"(2002)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=2556220;
 //BA.debugLineNum = 2556220;BA.debugLine="OverView1.Text = \"Frank Martin, who transports p";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=2556221;
 //BA.debugLineNum = 2556221;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556222;
 //BA.debugLineNum = 2556222;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"transporter.jpg").getObject()));
RDebugUtils.currentLine=2556224;
 //BA.debugLineNum = 2556224;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556225;
 //BA.debugLineNum = 2556225;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556226;
 //BA.debugLineNum = 2556226;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556227;
 //BA.debugLineNum = 2556227;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556228;
 //BA.debugLineNum = 2556228;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556230;
 //BA.debugLineNum = 2556230;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2556231;
 //BA.debugLineNum = 2556231;BA.debugLine="Else if query.Contains(\"chris evans\") Or query.Co";
if (_query.contains("chris evans") || _query.contains("chris") || _query.contains("evans") || _query.contains("mark ruffalo") || _query.contains("mark") || _query.contains("ruffalo")) { 
RDebugUtils.currentLine=2556232;
 //BA.debugLineNum = 2556232;BA.debugLine="Drama1.Text = \"Avengers: Endgame\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=2556233;
 //BA.debugLineNum = 2556233;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ch";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=2556234;
 //BA.debugLineNum = 2556234;BA.debugLine="Year1.Text = \"(2019)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=2556235;
 //BA.debugLineNum = 2556235;BA.debugLine="OverView1.Text = \"After the devastating events o";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=2556236;
 //BA.debugLineNum = 2556236;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556237;
 //BA.debugLineNum = 2556237;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"avengersEndgame.png").getObject()));
RDebugUtils.currentLine=2556239;
 //BA.debugLineNum = 2556239;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556240;
 //BA.debugLineNum = 2556240;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556241;
 //BA.debugLineNum = 2556241;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556242;
 //BA.debugLineNum = 2556242;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556243;
 //BA.debugLineNum = 2556243;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556245;
 //BA.debugLineNum = 2556245;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2556246;
 //BA.debugLineNum = 2556246;BA.debugLine="Else if query.Contains(\"dafne keen\") Or query.Con";
if (_query.contains("dafne keen") || _query.contains("dafne") || _query.contains("keen")) { 
RDebugUtils.currentLine=2556247;
 //BA.debugLineNum = 2556247;BA.debugLine="Drama1.Text = \"Logan\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=2556248;
 //BA.debugLineNum = 2556248;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=2556249;
 //BA.debugLineNum = 2556249;BA.debugLine="Year1.Text = \"(2017)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=2556250;
 //BA.debugLineNum = 2556250;BA.debugLine="OverView1.Text = \"In a future where mutants are";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=2556251;
 //BA.debugLineNum = 2556251;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556252;
 //BA.debugLineNum = 2556252;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"logan.jpg").getObject()));
RDebugUtils.currentLine=2556254;
 //BA.debugLineNum = 2556254;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556255;
 //BA.debugLineNum = 2556255;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556256;
 //BA.debugLineNum = 2556256;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556257;
 //BA.debugLineNum = 2556257;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556258;
 //BA.debugLineNum = 2556258;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556260;
 //BA.debugLineNum = 2556260;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2556262;
 //BA.debugLineNum = 2556262;BA.debugLine="Else if query.Contains(\"gwyneth paltrow\") Or quer";
if (_query.contains("gwyneth paltrow") || _query.contains("gwyneth") || _query.contains("paltrow") || _query.contains("terrence howard") || _query.contains("terrence") || _query.contains("howard")) { 
RDebugUtils.currentLine=2556263;
 //BA.debugLineNum = 2556263;BA.debugLine="Drama1.Text = \"Iron Man\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=2556264;
 //BA.debugLineNum = 2556264;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Gw";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=2556265;
 //BA.debugLineNum = 2556265;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=2556266;
 //BA.debugLineNum = 2556266;BA.debugLine="OverView1.Text = \"After being held captive in an";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=2556267;
 //BA.debugLineNum = 2556267;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556268;
 //BA.debugLineNum = 2556268;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ironman.jpg").getObject()));
RDebugUtils.currentLine=2556270;
 //BA.debugLineNum = 2556270;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556271;
 //BA.debugLineNum = 2556271;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556272;
 //BA.debugLineNum = 2556272;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556273;
 //BA.debugLineNum = 2556273;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556274;
 //BA.debugLineNum = 2556274;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556276;
 //BA.debugLineNum = 2556276;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2556278;
 //BA.debugLineNum = 2556278;BA.debugLine="Else if query.Contains(\"ian mckellen\") Or query.C";
if (_query.contains("ian mckellen") || _query.contains("ian") || _query.contains("mckellen")) { 
RDebugUtils.currentLine=2556279;
 //BA.debugLineNum = 2556279;BA.debugLine="Drama1.Text = \"X-Men\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=2556280;
 //BA.debugLineNum = 2556280;BA.debugLine="Starter1.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=2556281;
 //BA.debugLineNum = 2556281;BA.debugLine="Year1.Text = \"(2000)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=2556282;
 //BA.debugLineNum = 2556282;BA.debugLine="OverView1.Text = \"In a world where mutants (evol";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=2556283;
 //BA.debugLineNum = 2556283;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556284;
 //BA.debugLineNum = 2556284;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"xmen.jpg").getObject()));
RDebugUtils.currentLine=2556286;
 //BA.debugLineNum = 2556286;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556287;
 //BA.debugLineNum = 2556287;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556288;
 //BA.debugLineNum = 2556288;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556289;
 //BA.debugLineNum = 2556289;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556290;
 //BA.debugLineNum = 2556290;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556292;
 //BA.debugLineNum = 2556292;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2556294;
 //BA.debugLineNum = 2556294;BA.debugLine="Else if query.Contains(\"brad pitt\") Or query.Cont";
if (_query.contains("brad pitt") || _query.contains("brad") || _query.contains("pitt") || _query.contains("angelina jolie") || _query.contains("angelina") || _query.contains("jolie") || _query.contains("adam brody") || _query.contains("adam") || _query.contains("brody")) { 
RDebugUtils.currentLine=2556295;
 //BA.debugLineNum = 2556295;BA.debugLine="Drama1.Text = \"Mr. & Mrs. Smith \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Mr. & Mrs. Smith "));
RDebugUtils.currentLine=2556296;
 //BA.debugLineNum = 2556296;BA.debugLine="Starter1.Text = \"Starring: Brad Pitt, Angelina J";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
RDebugUtils.currentLine=2556297;
 //BA.debugLineNum = 2556297;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=2556298;
 //BA.debugLineNum = 2556298;BA.debugLine="OverView1.Text = \"A husband and wife struggle to";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
RDebugUtils.currentLine=2556299;
 //BA.debugLineNum = 2556299;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556300;
 //BA.debugLineNum = 2556300;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"MrAndMrs.png").getObject()));
RDebugUtils.currentLine=2556302;
 //BA.debugLineNum = 2556302;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556303;
 //BA.debugLineNum = 2556303;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556304;
 //BA.debugLineNum = 2556304;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556305;
 //BA.debugLineNum = 2556305;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556306;
 //BA.debugLineNum = 2556306;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556308;
 //BA.debugLineNum = 2556308;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2556312;
 //BA.debugLineNum = 2556312;BA.debugLine="Else if query.Contains(\"will yun lee\") Or query.C";
if (_query.contains("will yun lee") || _query.contains("will") || _query.contains("yun") || _query.contains("lee") || _query.contains("tao okamoto") || _query.contains("tao") || _query.contains("okamoto")) { 
RDebugUtils.currentLine=2556313;
 //BA.debugLineNum = 2556313;BA.debugLine="Drama1.Text = \"The Wolverine\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=2556314;
 //BA.debugLineNum = 2556314;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Will Yu";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=2556315;
 //BA.debugLineNum = 2556315;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=2556316;
 //BA.debugLineNum = 2556316;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=2556317;
 //BA.debugLineNum = 2556317;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556318;
 //BA.debugLineNum = 2556318;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"wolverine.png").getObject()));
RDebugUtils.currentLine=2556320;
 //BA.debugLineNum = 2556320;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556321;
 //BA.debugLineNum = 2556321;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556322;
 //BA.debugLineNum = 2556322;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556323;
 //BA.debugLineNum = 2556323;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556324;
 //BA.debugLineNum = 2556324;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556326;
 //BA.debugLineNum = 2556326;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=2556328;
 //BA.debugLineNum = 2556328;BA.debugLine="Else if query.Contains(\"jake gyllenhaal\") Or quer";
if (_query.contains("jake gyllenhaal") || _query.contains("jake") || _query.contains("gyllenhaal") || _query.contains("viola davis") || _query.contains("viola") || _query.contains("davis")) { 
RDebugUtils.currentLine=2556329;
 //BA.debugLineNum = 2556329;BA.debugLine="Drama1.Text = \"Prisoners\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=2556330;
 //BA.debugLineNum = 2556330;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Jake Gy";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=2556331;
 //BA.debugLineNum = 2556331;BA.debugLine="Year1.Text = \"(2013)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=2556332;
 //BA.debugLineNum = 2556332;BA.debugLine="OverView1.Text = \"A desperate father takes the l";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=2556333;
 //BA.debugLineNum = 2556333;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2556334;
 //BA.debugLineNum = 2556334;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"prisoners.jpg").getObject()));
RDebugUtils.currentLine=2556336;
 //BA.debugLineNum = 2556336;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556337;
 //BA.debugLineNum = 2556337;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556338;
 //BA.debugLineNum = 2556338;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556339;
 //BA.debugLineNum = 2556339;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556340;
 //BA.debugLineNum = 2556340;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2556342;
 //BA.debugLineNum = 2556342;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else {
RDebugUtils.currentLine=2556346;
 //BA.debugLineNum = 2556346;BA.debugLine="MsgboxAsync(\"No results found for\" & \" \"\"\" & Use";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("No results found for"+" \""+_userinput+"\""),BA.ObjectToCharSequence(""),processBA);
 }}}}}}}}}}}}}}}}}}}}}}}}
;
RDebugUtils.currentLine=2556350;
 //BA.debugLineNum = 2556350;BA.debugLine="p.Width = 100%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=2556351;
 //BA.debugLineNum = 2556351;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=2556353;
 //BA.debugLineNum = 2556353;BA.debugLine="End Sub";
return "";
}
public static String  _searchengine_textchanged(String _old,String _new) throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchengine_textchanged", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchengine_textchanged", new Object[] {_old,_new}));}
String _query = "";
RDebugUtils.currentLine=2621440;
 //BA.debugLineNum = 2621440;BA.debugLine="Sub SearchEngine_TextChanged (Old As String, New A";
RDebugUtils.currentLine=2621441;
 //BA.debugLineNum = 2621441;BA.debugLine="Dim query As String = New.ToLowerCase.Trim";
_query = _new.toLowerCase().trim();
RDebugUtils.currentLine=2621443;
 //BA.debugLineNum = 2621443;BA.debugLine="If query = \"\" Then";
if ((_query).equals("")) { 
RDebugUtils.currentLine=2621446;
 //BA.debugLineNum = 2621446;BA.debugLine="p.Height = 210%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=2621447;
 //BA.debugLineNum = 2621447;BA.debugLine="p.Width = 200%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA));
RDebugUtils.currentLine=2621448;
 //BA.debugLineNum = 2621448;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=2621450;
 //BA.debugLineNum = 2621450;BA.debugLine="PanelMovie1.Visible = True";
mostCurrent._panelmovie1.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2621451;
 //BA.debugLineNum = 2621451;BA.debugLine="PanelMovie2.Visible = True";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2621452;
 //BA.debugLineNum = 2621452;BA.debugLine="PanelMovie3.Visible = True";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2621453;
 //BA.debugLineNum = 2621453;BA.debugLine="PanelMovie4.Visible = True";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2621454;
 //BA.debugLineNum = 2621454;BA.debugLine="PanelMovie5.Visible = True";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2621455;
 //BA.debugLineNum = 2621455;BA.debugLine="PanelMovie6.Visible = True";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2621456;
 //BA.debugLineNum = 2621456;BA.debugLine="PanelMovie7.Visible = True";
mostCurrent._panelmovie7.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2621457;
 //BA.debugLineNum = 2621457;BA.debugLine="PanelMovie8.Visible = True";
mostCurrent._panelmovie8.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2621458;
 //BA.debugLineNum = 2621458;BA.debugLine="PanelMovie9.Visible = True";
mostCurrent._panelmovie9.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2621459;
 //BA.debugLineNum = 2621459;BA.debugLine="PanelMovie10.Visible = True";
mostCurrent._panelmovie10.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2621463;
 //BA.debugLineNum = 2621463;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=2621464;
 //BA.debugLineNum = 2621464;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=2621465;
 //BA.debugLineNum = 2621465;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=2621466;
 //BA.debugLineNum = 2621466;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=2621467;
 //BA.debugLineNum = 2621467;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2621468;
 //BA.debugLineNum = 2621468;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"crank.jpg").getObject()));
RDebugUtils.currentLine=2621470;
 //BA.debugLineNum = 2621470;BA.debugLine="Drama2.Text = \"Sherlock Holmes\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Sherlock Holmes"));
RDebugUtils.currentLine=2621471;
 //BA.debugLineNum = 2621471;BA.debugLine="Starter2.Text = \"Starring: Robert Downey Jr., Ju";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=2621472;
 //BA.debugLineNum = 2621472;BA.debugLine="Year2.Text = \"(2008)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=2621473;
 //BA.debugLineNum = 2621473;BA.debugLine="OverView2.Text = \"Detective Sherlock Holmes and";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=2621474;
 //BA.debugLineNum = 2621474;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2621475;
 //BA.debugLineNum = 2621475;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"sherlockHolmes.jpg").getObject()));
RDebugUtils.currentLine=2621477;
 //BA.debugLineNum = 2621477;BA.debugLine="Drama3.Text = \"The Transporter\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=2621478;
 //BA.debugLineNum = 2621478;BA.debugLine="Starter3.Text = \"Starring: Jason Statham, Shu Qi";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=2621479;
 //BA.debugLineNum = 2621479;BA.debugLine="Year3.Text = \"(2002)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=2621480;
 //BA.debugLineNum = 2621480;BA.debugLine="OverView3.Text = \"Frank Martin, who transports p";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=2621481;
 //BA.debugLineNum = 2621481;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2621482;
 //BA.debugLineNum = 2621482;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"transporter.jpg").getObject()));
RDebugUtils.currentLine=2621484;
 //BA.debugLineNum = 2621484;BA.debugLine="Drama4.Text = \"Avengers: Endgame\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=2621485;
 //BA.debugLineNum = 2621485;BA.debugLine="Starter4.Text = \"Starring: Robert Downey Jr., Ch";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=2621486;
 //BA.debugLineNum = 2621486;BA.debugLine="Year4.Text = \"(2019)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=2621487;
 //BA.debugLineNum = 2621487;BA.debugLine="OverView4.Text = \"After the devastating events o";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=2621488;
 //BA.debugLineNum = 2621488;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2621489;
 //BA.debugLineNum = 2621489;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"avengersEndgame.png").getObject()));
RDebugUtils.currentLine=2621491;
 //BA.debugLineNum = 2621491;BA.debugLine="Drama5.Text = \"Logan\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=2621492;
 //BA.debugLineNum = 2621492;BA.debugLine="Starter5.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=2621493;
 //BA.debugLineNum = 2621493;BA.debugLine="Year5.Text = \"(2017)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=2621494;
 //BA.debugLineNum = 2621494;BA.debugLine="OverView5.Text = \"In a future where mutants are";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=2621495;
 //BA.debugLineNum = 2621495;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
mostCurrent._dramaimage5.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2621496;
 //BA.debugLineNum = 2621496;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage5.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"logan.jpg").getObject()));
RDebugUtils.currentLine=2621499;
 //BA.debugLineNum = 2621499;BA.debugLine="Drama6.Text = \"Iron Man\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=2621500;
 //BA.debugLineNum = 2621500;BA.debugLine="Starter6.Text = \"Starring: Robert Downey Jr., Gw";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=2621501;
 //BA.debugLineNum = 2621501;BA.debugLine="Year6.Text = \"(2008)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=2621502;
 //BA.debugLineNum = 2621502;BA.debugLine="OverView6.Text = \"After being held captive in an";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=2621503;
 //BA.debugLineNum = 2621503;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
mostCurrent._dramaimage6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2621504;
 //BA.debugLineNum = 2621504;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage6.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"ironman.jpg").getObject()));
RDebugUtils.currentLine=2621506;
 //BA.debugLineNum = 2621506;BA.debugLine="Drama7.Text = \"X-Men\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=2621507;
 //BA.debugLineNum = 2621507;BA.debugLine="Starter7.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=2621508;
 //BA.debugLineNum = 2621508;BA.debugLine="Year7.Text = \"(2000)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=2621509;
 //BA.debugLineNum = 2621509;BA.debugLine="OverView7.Text = \"In a world where mutants (evol";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=2621510;
 //BA.debugLineNum = 2621510;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2621511;
 //BA.debugLineNum = 2621511;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"xmen.jpg").getObject()));
RDebugUtils.currentLine=2621513;
 //BA.debugLineNum = 2621513;BA.debugLine="Drama8.Text = \"Mr. & Mrs. Smith\"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("Mr. & Mrs. Smith"));
RDebugUtils.currentLine=2621514;
 //BA.debugLineNum = 2621514;BA.debugLine="Starter8.Text = \"Starring: Brad Pitt, Angelina J";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
RDebugUtils.currentLine=2621515;
 //BA.debugLineNum = 2621515;BA.debugLine="Year8.Text = \"(2005)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=2621516;
 //BA.debugLineNum = 2621516;BA.debugLine="OverView8.Text = \"A husband and wife struggle to";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
RDebugUtils.currentLine=2621517;
 //BA.debugLineNum = 2621517;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
mostCurrent._dramaimage8.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2621518;
 //BA.debugLineNum = 2621518;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage8.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"MrAndMrs.png").getObject()));
RDebugUtils.currentLine=2621520;
 //BA.debugLineNum = 2621520;BA.debugLine="Drama9.Text = \"The Wolverine\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=2621521;
 //BA.debugLineNum = 2621521;BA.debugLine="Starter9.Text = \"Starring: Hugh Jackman, Will Yu";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=2621522;
 //BA.debugLineNum = 2621522;BA.debugLine="Year9.Text = \"(2015)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=2621523;
 //BA.debugLineNum = 2621523;BA.debugLine="OverView9.Text = \"A chance encounter between a y";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=2621524;
 //BA.debugLineNum = 2621524;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
mostCurrent._dramaimage9.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2621525;
 //BA.debugLineNum = 2621525;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage9.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"wolverine.png").getObject()));
RDebugUtils.currentLine=2621527;
 //BA.debugLineNum = 2621527;BA.debugLine="Drama10.Text = \"Prisoners\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=2621528;
 //BA.debugLineNum = 2621528;BA.debugLine="Starter10.Text = \"Starring: Hugh Jackman, Jake G";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=2621529;
 //BA.debugLineNum = 2621529;BA.debugLine="Year10.Text = \"(2013)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=2621530;
 //BA.debugLineNum = 2621530;BA.debugLine="OverView10.Text = \"A desperate father takes the";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=2621531;
 //BA.debugLineNum = 2621531;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2621532;
 //BA.debugLineNum = 2621532;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"prisoners.jpg").getObject()));
 };
RDebugUtils.currentLine=2621538;
 //BA.debugLineNum = 2621538;BA.debugLine="End Sub";
return "";
}
}