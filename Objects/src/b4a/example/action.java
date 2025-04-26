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
public b4a.example.drama _drama = null;
public b4a.example.scifi _scifi = null;
public b4a.example.panelview _panelview = null;
public static String  _actionpage_click() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "actionpage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "actionpage_click", null));}
RDebugUtils.currentLine=1966080;
 //BA.debugLineNum = 1966080;BA.debugLine="Private Sub ActionPage_Click";
RDebugUtils.currentLine=1966082;
 //BA.debugLineNum = 1966082;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=1703936;
 //BA.debugLineNum = 1703936;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=1703937;
 //BA.debugLineNum = 1703937;BA.debugLine="Activity.LoadLayout(\"action\") ' Layout contains S";
mostCurrent._activity.LoadLayout("action",mostCurrent.activityBA);
RDebugUtils.currentLine=1703939;
 //BA.debugLineNum = 1703939;BA.debugLine="p.Initialize(\"\")";
mostCurrent._p.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=1703940;
 //BA.debugLineNum = 1703940;BA.debugLine="p.LoadLayout(\"panelview\")";
mostCurrent._p.LoadLayout("panelview",mostCurrent.activityBA);
RDebugUtils.currentLine=1703947;
 //BA.debugLineNum = 1703947;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=1703948;
 //BA.debugLineNum = 1703948;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sma";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=1703949;
 //BA.debugLineNum = 1703949;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=1703950;
 //BA.debugLineNum = 1703950;BA.debugLine="OverView1.Text = \"Professional assassin Chev Chel";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=1703951;
 //BA.debugLineNum = 1703951;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1703952;
 //BA.debugLineNum = 1703952;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=1703954;
 //BA.debugLineNum = 1703954;BA.debugLine="Drama2.Text = \"Sherlock Holmes \"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Sherlock Holmes "));
RDebugUtils.currentLine=1703955;
 //BA.debugLineNum = 1703955;BA.debugLine="Starter2.Text = \"Starring: Robert Downey Jr., Jud";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=1703956;
 //BA.debugLineNum = 1703956;BA.debugLine="Year2.Text = \"(2008)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=1703957;
 //BA.debugLineNum = 1703957;BA.debugLine="OverView2.Text = \"Detective Sherlock Holmes and h";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=1703958;
 //BA.debugLineNum = 1703958;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1703959;
 //BA.debugLineNum = 1703959;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=1703961;
 //BA.debugLineNum = 1703961;BA.debugLine="Drama3.Text = \"The Transporter\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=1703962;
 //BA.debugLineNum = 1703962;BA.debugLine="Starter3.Text = \"Starring: Jason Statham, Shu Qi,";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=1703963;
 //BA.debugLineNum = 1703963;BA.debugLine="Year3.Text = \"(2002)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=1703964;
 //BA.debugLineNum = 1703964;BA.debugLine="OverView3.Text = \"Frank Martin, who transports pa";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=1703965;
 //BA.debugLineNum = 1703965;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1703966;
 //BA.debugLineNum = 1703966;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=1703968;
 //BA.debugLineNum = 1703968;BA.debugLine="Drama4.Text = \"Avengers: Endgame\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=1703969;
 //BA.debugLineNum = 1703969;BA.debugLine="Starter4.Text = \"Starring: Robert Downey Jr., Chr";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=1703970;
 //BA.debugLineNum = 1703970;BA.debugLine="Year4.Text = \"(2019)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=1703971;
 //BA.debugLineNum = 1703971;BA.debugLine="OverView4.Text = \"After the devastating events of";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=1703972;
 //BA.debugLineNum = 1703972;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1703973;
 //BA.debugLineNum = 1703973;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=1703975;
 //BA.debugLineNum = 1703975;BA.debugLine="Drama5.Text = \"Logan\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=1703976;
 //BA.debugLineNum = 1703976;BA.debugLine="Starter5.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=1703977;
 //BA.debugLineNum = 1703977;BA.debugLine="Year5.Text = \"(2017)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=1703978;
 //BA.debugLineNum = 1703978;BA.debugLine="OverView5.Text = \"In a future where mutants are n";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=1703979;
 //BA.debugLineNum = 1703979;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
mostCurrent._dramaimage5.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1703980;
 //BA.debugLineNum = 1703980;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage5.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=1703983;
 //BA.debugLineNum = 1703983;BA.debugLine="Drama6.Text = \"Iron Man\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=1703984;
 //BA.debugLineNum = 1703984;BA.debugLine="Starter6.Text = \"Starring: Robert Downey Jr., Gwy";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=1703985;
 //BA.debugLineNum = 1703985;BA.debugLine="Year6.Text = \"(2008)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=1703986;
 //BA.debugLineNum = 1703986;BA.debugLine="OverView6.Text = \"After being held captive in an";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=1703987;
 //BA.debugLineNum = 1703987;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
mostCurrent._dramaimage6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1703988;
 //BA.debugLineNum = 1703988;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage6.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=1703990;
 //BA.debugLineNum = 1703990;BA.debugLine="Drama7.Text = \"X-Men\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=1703991;
 //BA.debugLineNum = 1703991;BA.debugLine="Starter7.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=1703992;
 //BA.debugLineNum = 1703992;BA.debugLine="Year7.Text = \"(2000)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=1703993;
 //BA.debugLineNum = 1703993;BA.debugLine="OverView7.Text = \"In a world where mutants (evolv";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=1703994;
 //BA.debugLineNum = 1703994;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1703995;
 //BA.debugLineNum = 1703995;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=1703997;
 //BA.debugLineNum = 1703997;BA.debugLine="Drama8.Text = \"Mr. & Mrs. Smith \"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("Mr. & Mrs. Smith "));
RDebugUtils.currentLine=1703998;
 //BA.debugLineNum = 1703998;BA.debugLine="Starter8.Text = \"Starring: Brad Pitt, Angelina Jo";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
RDebugUtils.currentLine=1703999;
 //BA.debugLineNum = 1703999;BA.debugLine="Year8.Text = \"(2005)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=1704000;
 //BA.debugLineNum = 1704000;BA.debugLine="OverView8.Text = \"A husband and wife struggle to";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
RDebugUtils.currentLine=1704001;
 //BA.debugLineNum = 1704001;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
mostCurrent._dramaimage8.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1704002;
 //BA.debugLineNum = 1704002;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage8.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=1704004;
 //BA.debugLineNum = 1704004;BA.debugLine="Drama9.Text = \"The Wolverine\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=1704005;
 //BA.debugLineNum = 1704005;BA.debugLine="Starter9.Text = \"Starring: Hugh Jackman, Will Yun";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=1704006;
 //BA.debugLineNum = 1704006;BA.debugLine="Year9.Text = \"(2015)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=1704007;
 //BA.debugLineNum = 1704007;BA.debugLine="OverView9.Text = \"A chance encounter between a yo";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=1704008;
 //BA.debugLineNum = 1704008;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
mostCurrent._dramaimage9.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1704009;
 //BA.debugLineNum = 1704009;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage9.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=1704011;
 //BA.debugLineNum = 1704011;BA.debugLine="Drama10.Text = \"Prisoners\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=1704012;
 //BA.debugLineNum = 1704012;BA.debugLine="Starter10.Text = \"Starring: Hugh Jackman, Jake Gy";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=1704013;
 //BA.debugLineNum = 1704013;BA.debugLine="Year10.Text = \"(2013)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1704014;
 //BA.debugLineNum = 1704014;BA.debugLine="OverView10.Text = \"A desperate father takes the l";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=1704015;
 //BA.debugLineNum = 1704015;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1704016;
 //BA.debugLineNum = 1704016;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=1704018;
 //BA.debugLineNum = 1704018;BA.debugLine="ScrollView1.Panel.AddView(p, 0, 0, 200%x, 210%y)";
mostCurrent._scrollview1.getPanel().AddView((android.view.View)(mostCurrent._p.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=1704019;
 //BA.debugLineNum = 1704019;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=1704020;
 //BA.debugLineNum = 1704020;BA.debugLine="End Sub";
return "";
}
public static String  _dramapage_click() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "dramapage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "dramapage_click", null));}
RDebugUtils.currentLine=2031616;
 //BA.debugLineNum = 2031616;BA.debugLine="Private Sub DramaPage_Click";
RDebugUtils.currentLine=2031617;
 //BA.debugLineNum = 2031617;BA.debugLine="StartActivity(Drama)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._drama.getObject()));
RDebugUtils.currentLine=2031618;
 //BA.debugLineNum = 2031618;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=2031619;
 //BA.debugLineNum = 2031619;BA.debugLine="End Sub";
return "";
}
public static String  _homepage_click() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "homepage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "homepage_click", null));}
RDebugUtils.currentLine=2097152;
 //BA.debugLineNum = 2097152;BA.debugLine="Private Sub HomePage_Click";
RDebugUtils.currentLine=2097153;
 //BA.debugLineNum = 2097153;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
RDebugUtils.currentLine=2097154;
 //BA.debugLineNum = 2097154;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=2097155;
 //BA.debugLineNum = 2097155;BA.debugLine="End Sub";
return "";
}
public static String  _scifipage_click() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "scifipage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "scifipage_click", null));}
RDebugUtils.currentLine=1900544;
 //BA.debugLineNum = 1900544;BA.debugLine="Private Sub SciFiPage_Click";
RDebugUtils.currentLine=1900545;
 //BA.debugLineNum = 1900545;BA.debugLine="StartActivity(SciFi)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._scifi.getObject()));
RDebugUtils.currentLine=1900546;
 //BA.debugLineNum = 1900546;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=1900547;
 //BA.debugLineNum = 1900547;BA.debugLine="End Sub";
return "";
}
public static String  _searchbtn_click() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchbtn_click", null));}
RDebugUtils.currentLine=3211264;
 //BA.debugLineNum = 3211264;BA.debugLine="Private Sub SearchBtn_Click";
RDebugUtils.currentLine=3211265;
 //BA.debugLineNum = 3211265;BA.debugLine="SearchNow";
_searchnow();
RDebugUtils.currentLine=3211266;
 //BA.debugLineNum = 3211266;BA.debugLine="End Sub";
return "";
}
public static String  _searchnow() throws Exception{
RDebugUtils.currentModule="action";
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
 //BA.debugLineNum = 3997703;BA.debugLine="If query.Contains(\"crank\") Then";
if (_query.contains("crank")) { 
RDebugUtils.currentLine=3997705;
 //BA.debugLineNum = 3997705;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=3997706;
 //BA.debugLineNum = 3997706;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=3997707;
 //BA.debugLineNum = 3997707;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=3997708;
 //BA.debugLineNum = 3997708;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=3997709;
 //BA.debugLineNum = 3997709;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997710;
 //BA.debugLineNum = 3997710;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=3997712;
 //BA.debugLineNum = 3997712;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997713;
 //BA.debugLineNum = 3997713;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997714;
 //BA.debugLineNum = 3997714;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997715;
 //BA.debugLineNum = 3997715;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997716;
 //BA.debugLineNum = 3997716;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997718;
 //BA.debugLineNum = 3997718;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997720;
 //BA.debugLineNum = 3997720;BA.debugLine="Else If query.Contains(\"sherlock\") Or query.Conta";
if (_query.contains("sherlock") || _query.contains("sherlock holmes")) { 
RDebugUtils.currentLine=3997722;
 //BA.debugLineNum = 3997722;BA.debugLine="Drama1.Text = \"Sherlock Holmes \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Sherlock Holmes "));
RDebugUtils.currentLine=3997723;
 //BA.debugLineNum = 3997723;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ju";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=3997724;
 //BA.debugLineNum = 3997724;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=3997725;
 //BA.debugLineNum = 3997725;BA.debugLine="OverView1.Text = \"Detective Sherlock Holmes and";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=3997726;
 //BA.debugLineNum = 3997726;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997727;
 //BA.debugLineNum = 3997727;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=3997729;
 //BA.debugLineNum = 3997729;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997730;
 //BA.debugLineNum = 3997730;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997731;
 //BA.debugLineNum = 3997731;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997732;
 //BA.debugLineNum = 3997732;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997733;
 //BA.debugLineNum = 3997733;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997735;
 //BA.debugLineNum = 3997735;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997737;
 //BA.debugLineNum = 3997737;BA.debugLine="Else If query.Contains(\"the transporter\") Or quer";
if (_query.contains("the transporter") || _query.contains("transporter")) { 
RDebugUtils.currentLine=3997739;
 //BA.debugLineNum = 3997739;BA.debugLine="Drama1.Text = \"The Transporter\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=3997740;
 //BA.debugLineNum = 3997740;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Shu Qi";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=3997741;
 //BA.debugLineNum = 3997741;BA.debugLine="Year1.Text = \"(2002)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=3997742;
 //BA.debugLineNum = 3997742;BA.debugLine="OverView1.Text = \"Frank Martin, who transports p";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=3997743;
 //BA.debugLineNum = 3997743;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997744;
 //BA.debugLineNum = 3997744;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=3997746;
 //BA.debugLineNum = 3997746;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997747;
 //BA.debugLineNum = 3997747;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997748;
 //BA.debugLineNum = 3997748;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997749;
 //BA.debugLineNum = 3997749;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997750;
 //BA.debugLineNum = 3997750;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997752;
 //BA.debugLineNum = 3997752;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997754;
 //BA.debugLineNum = 3997754;BA.debugLine="Else If query.Contains(\"avengers endgame\") Or que";
if (_query.contains("avengers endgame") || _query.contains("avengers") || _query.contains("endgame")) { 
RDebugUtils.currentLine=3997756;
 //BA.debugLineNum = 3997756;BA.debugLine="Drama1.Text = \"Avengers: Endgame\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=3997757;
 //BA.debugLineNum = 3997757;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ch";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=3997758;
 //BA.debugLineNum = 3997758;BA.debugLine="Year1.Text = \"(2019)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=3997759;
 //BA.debugLineNum = 3997759;BA.debugLine="OverView1.Text = \"After the devastating events o";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=3997760;
 //BA.debugLineNum = 3997760;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997761;
 //BA.debugLineNum = 3997761;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=3997763;
 //BA.debugLineNum = 3997763;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997764;
 //BA.debugLineNum = 3997764;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997765;
 //BA.debugLineNum = 3997765;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997766;
 //BA.debugLineNum = 3997766;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997767;
 //BA.debugLineNum = 3997767;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997769;
 //BA.debugLineNum = 3997769;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997771;
 //BA.debugLineNum = 3997771;BA.debugLine="Else If query.Contains(\"logan\") Then";
if (_query.contains("logan")) { 
RDebugUtils.currentLine=3997773;
 //BA.debugLineNum = 3997773;BA.debugLine="Drama1.Text = \"Logan\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=3997774;
 //BA.debugLineNum = 3997774;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=3997775;
 //BA.debugLineNum = 3997775;BA.debugLine="Year1.Text = \"(2017)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=3997776;
 //BA.debugLineNum = 3997776;BA.debugLine="OverView1.Text = \"In a future where mutants are";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=3997777;
 //BA.debugLineNum = 3997777;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997778;
 //BA.debugLineNum = 3997778;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=3997780;
 //BA.debugLineNum = 3997780;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997781;
 //BA.debugLineNum = 3997781;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997782;
 //BA.debugLineNum = 3997782;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997783;
 //BA.debugLineNum = 3997783;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997784;
 //BA.debugLineNum = 3997784;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997786;
 //BA.debugLineNum = 3997786;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997788;
 //BA.debugLineNum = 3997788;BA.debugLine="Else If query.Contains(\"iron man\") Or query.Conta";
if (_query.contains("iron man") || _query.contains("man") || _query.contains("iron")) { 
RDebugUtils.currentLine=3997790;
 //BA.debugLineNum = 3997790;BA.debugLine="Drama1.Text = \"Iron Man\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=3997791;
 //BA.debugLineNum = 3997791;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Gw";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=3997792;
 //BA.debugLineNum = 3997792;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=3997793;
 //BA.debugLineNum = 3997793;BA.debugLine="OverView1.Text = \"After being held captive in an";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=3997794;
 //BA.debugLineNum = 3997794;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997795;
 //BA.debugLineNum = 3997795;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=3997797;
 //BA.debugLineNum = 3997797;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997798;
 //BA.debugLineNum = 3997798;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997799;
 //BA.debugLineNum = 3997799;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997800;
 //BA.debugLineNum = 3997800;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997801;
 //BA.debugLineNum = 3997801;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997803;
 //BA.debugLineNum = 3997803;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997805;
 //BA.debugLineNum = 3997805;BA.debugLine="Else If query.Contains(\"x-men\") Or query.Contains";
if (_query.contains("x-men") || _query.contains("men") || _query.contains("xmen") || _query.contains("x")) { 
RDebugUtils.currentLine=3997807;
 //BA.debugLineNum = 3997807;BA.debugLine="Drama7.Text = \"X-Men\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=3997808;
 //BA.debugLineNum = 3997808;BA.debugLine="Starter7.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=3997809;
 //BA.debugLineNum = 3997809;BA.debugLine="Year7.Text = \"(2000)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=3997810;
 //BA.debugLineNum = 3997810;BA.debugLine="OverView7.Text = \"In a world where mutants (evol";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=3997811;
 //BA.debugLineNum = 3997811;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997812;
 //BA.debugLineNum = 3997812;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=3997814;
 //BA.debugLineNum = 3997814;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997815;
 //BA.debugLineNum = 3997815;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997816;
 //BA.debugLineNum = 3997816;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997817;
 //BA.debugLineNum = 3997817;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997818;
 //BA.debugLineNum = 3997818;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997820;
 //BA.debugLineNum = 3997820;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997822;
 //BA.debugLineNum = 3997822;BA.debugLine="Else If query.Contains(\"mr & mrs smith\") Or query";
if (_query.contains("mr & mrs smith") || _query.contains("mr and mrs") || _query.contains("smith") || _query.contains("mrs") || _query.contains("mr")) { 
RDebugUtils.currentLine=3997824;
 //BA.debugLineNum = 3997824;BA.debugLine="Drama1.Text = \"Mr. & Mrs. Smith \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Mr. & Mrs. Smith "));
RDebugUtils.currentLine=3997825;
 //BA.debugLineNum = 3997825;BA.debugLine="Starter1.Text = \"Starring: Brad Pitt, Angelina J";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
RDebugUtils.currentLine=3997826;
 //BA.debugLineNum = 3997826;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3997827;
 //BA.debugLineNum = 3997827;BA.debugLine="OverView1.Text = \"A husband and wife struggle to";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
RDebugUtils.currentLine=3997828;
 //BA.debugLineNum = 3997828;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997829;
 //BA.debugLineNum = 3997829;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=3997831;
 //BA.debugLineNum = 3997831;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997832;
 //BA.debugLineNum = 3997832;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997833;
 //BA.debugLineNum = 3997833;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997834;
 //BA.debugLineNum = 3997834;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997835;
 //BA.debugLineNum = 3997835;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997837;
 //BA.debugLineNum = 3997837;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997839;
 //BA.debugLineNum = 3997839;BA.debugLine="Else If query.Contains(\"the wolverine\") Or query.";
if (_query.contains("the wolverine") || _query.contains("wolverine")) { 
RDebugUtils.currentLine=3997841;
 //BA.debugLineNum = 3997841;BA.debugLine="Drama1.Text = \"The Wolverine\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=3997842;
 //BA.debugLineNum = 3997842;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Will Yu";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=3997843;
 //BA.debugLineNum = 3997843;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=3997844;
 //BA.debugLineNum = 3997844;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=3997845;
 //BA.debugLineNum = 3997845;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997846;
 //BA.debugLineNum = 3997846;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=3997848;
 //BA.debugLineNum = 3997848;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997849;
 //BA.debugLineNum = 3997849;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997850;
 //BA.debugLineNum = 3997850;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997851;
 //BA.debugLineNum = 3997851;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997852;
 //BA.debugLineNum = 3997852;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997854;
 //BA.debugLineNum = 3997854;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997856;
 //BA.debugLineNum = 3997856;BA.debugLine="Else If query.Contains(\"prisoners\") Or query.Cont";
if (_query.contains("prisoners") || _query.contains("prisoner") || _query.contains("pri")) { 
RDebugUtils.currentLine=3997858;
 //BA.debugLineNum = 3997858;BA.debugLine="Drama10.Text = \"Prisoners\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=3997859;
 //BA.debugLineNum = 3997859;BA.debugLine="Starter10.Text = \"Starring: Hugh Jackman, Jake G";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=3997860;
 //BA.debugLineNum = 3997860;BA.debugLine="Year10.Text = \"(2013)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=3997861;
 //BA.debugLineNum = 3997861;BA.debugLine="OverView10.Text = \"A desperate father takes the";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=3997862;
 //BA.debugLineNum = 3997862;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997863;
 //BA.debugLineNum = 3997863;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=3997865;
 //BA.debugLineNum = 3997865;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997866;
 //BA.debugLineNum = 3997866;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997867;
 //BA.debugLineNum = 3997867;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997868;
 //BA.debugLineNum = 3997868;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997869;
 //BA.debugLineNum = 3997869;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997871;
 //BA.debugLineNum = 3997871;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997873;
 //BA.debugLineNum = 3997873;BA.debugLine="Else If query.Contains(\"jason statham\") Or query.";
if (_query.contains("jason statham") || _query.contains("jason") || _query.contains("statham")) { 
RDebugUtils.currentLine=3997874;
 //BA.debugLineNum = 3997874;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=3997875;
 //BA.debugLineNum = 3997875;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=3997876;
 //BA.debugLineNum = 3997876;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=3997877;
 //BA.debugLineNum = 3997877;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=3997878;
 //BA.debugLineNum = 3997878;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997879;
 //BA.debugLineNum = 3997879;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=3997881;
 //BA.debugLineNum = 3997881;BA.debugLine="Drama2.Text = \"The Transporter\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=3997882;
 //BA.debugLineNum = 3997882;BA.debugLine="Starter2.Text = \"Starring: Jason Statham, Shu Qi";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=3997883;
 //BA.debugLineNum = 3997883;BA.debugLine="Year2.Text = \"(2002)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=3997884;
 //BA.debugLineNum = 3997884;BA.debugLine="OverView2.Text = \"Frank Martin, who transports p";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=3997885;
 //BA.debugLineNum = 3997885;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997886;
 //BA.debugLineNum = 3997886;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=3997888;
 //BA.debugLineNum = 3997888;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997889;
 //BA.debugLineNum = 3997889;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997890;
 //BA.debugLineNum = 3997890;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997892;
 //BA.debugLineNum = 3997892;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997894;
 //BA.debugLineNum = 3997894;BA.debugLine="Else If query.Contains(\"Robert downey jr\") Or que";
if (_query.contains("Robert downey jr") || _query.contains("robert") || _query.contains("downey") || _query.contains("downey jr")) { 
RDebugUtils.currentLine=3997895;
 //BA.debugLineNum = 3997895;BA.debugLine="Drama1.Text = \"Sherlock Holmes \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Sherlock Holmes "));
RDebugUtils.currentLine=3997896;
 //BA.debugLineNum = 3997896;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ju";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=3997897;
 //BA.debugLineNum = 3997897;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=3997898;
 //BA.debugLineNum = 3997898;BA.debugLine="OverView1.Text = \"Detective Sherlock Holmes and";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=3997899;
 //BA.debugLineNum = 3997899;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997900;
 //BA.debugLineNum = 3997900;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=3997902;
 //BA.debugLineNum = 3997902;BA.debugLine="Drama2.Text = \"Avengers: Endgame\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=3997903;
 //BA.debugLineNum = 3997903;BA.debugLine="Starter2.Text = \"Starring: Robert Downey Jr., Ch";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=3997904;
 //BA.debugLineNum = 3997904;BA.debugLine="Year2.Text = \"(2019)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=3997905;
 //BA.debugLineNum = 3997905;BA.debugLine="OverView2.Text = \"After the devastating events o";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=3997906;
 //BA.debugLineNum = 3997906;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997907;
 //BA.debugLineNum = 3997907;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=3997909;
 //BA.debugLineNum = 3997909;BA.debugLine="Drama3.Text = \"Iron Man\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=3997910;
 //BA.debugLineNum = 3997910;BA.debugLine="Starter3.Text = \"Starring: Robert Downey Jr., Gw";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=3997911;
 //BA.debugLineNum = 3997911;BA.debugLine="Year3.Text = \"(2008)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=3997912;
 //BA.debugLineNum = 3997912;BA.debugLine="OverView3.Text = \"After being held captive in an";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=3997913;
 //BA.debugLineNum = 3997913;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997914;
 //BA.debugLineNum = 3997914;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=3997916;
 //BA.debugLineNum = 3997916;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997917;
 //BA.debugLineNum = 3997917;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997918;
 //BA.debugLineNum = 3997918;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997920;
 //BA.debugLineNum = 3997920;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997922;
 //BA.debugLineNum = 3997922;BA.debugLine="Else If query.Contains(\"patrick stewart\") Or quer";
if (_query.contains("patrick stewart") || _query.contains("patrick") || _query.contains("stewart")) { 
RDebugUtils.currentLine=3997923;
 //BA.debugLineNum = 3997923;BA.debugLine="Drama1.Text = \"Logan\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=3997924;
 //BA.debugLineNum = 3997924;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=3997925;
 //BA.debugLineNum = 3997925;BA.debugLine="Year1.Text = \"(2017)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=3997926;
 //BA.debugLineNum = 3997926;BA.debugLine="OverView1.Text = \"In a future where mutants are";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=3997927;
 //BA.debugLineNum = 3997927;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997928;
 //BA.debugLineNum = 3997928;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=3997930;
 //BA.debugLineNum = 3997930;BA.debugLine="Drama2.Text = \"X-Men\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=3997931;
 //BA.debugLineNum = 3997931;BA.debugLine="Starter2.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=3997932;
 //BA.debugLineNum = 3997932;BA.debugLine="Year2.Text = \"(2000)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=3997933;
 //BA.debugLineNum = 3997933;BA.debugLine="OverView2.Text = \"In a world where mutants (evol";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=3997934;
 //BA.debugLineNum = 3997934;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997935;
 //BA.debugLineNum = 3997935;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=3997937;
 //BA.debugLineNum = 3997937;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997938;
 //BA.debugLineNum = 3997938;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997939;
 //BA.debugLineNum = 3997939;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997940;
 //BA.debugLineNum = 3997940;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997942;
 //BA.debugLineNum = 3997942;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997944;
 //BA.debugLineNum = 3997944;BA.debugLine="Else If query.Contains(\"hugh jackman\") Or query.C";
if (_query.contains("hugh jackman") || _query.contains("hugh") || _query.contains("jackman")) { 
RDebugUtils.currentLine=3997945;
 //BA.debugLineNum = 3997945;BA.debugLine="Drama1.Text = \"Logan\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=3997946;
 //BA.debugLineNum = 3997946;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=3997947;
 //BA.debugLineNum = 3997947;BA.debugLine="Year1.Text = \"(2017)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=3997948;
 //BA.debugLineNum = 3997948;BA.debugLine="OverView1.Text = \"In a future where mutants are";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=3997949;
 //BA.debugLineNum = 3997949;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997950;
 //BA.debugLineNum = 3997950;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=3997952;
 //BA.debugLineNum = 3997952;BA.debugLine="Drama2.Text = \"X-Men\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=3997953;
 //BA.debugLineNum = 3997953;BA.debugLine="Starter2.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=3997954;
 //BA.debugLineNum = 3997954;BA.debugLine="Year2.Text = \"(2000)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=3997955;
 //BA.debugLineNum = 3997955;BA.debugLine="OverView2.Text = \"In a world where mutants (evol";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=3997956;
 //BA.debugLineNum = 3997956;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997957;
 //BA.debugLineNum = 3997957;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=3997959;
 //BA.debugLineNum = 3997959;BA.debugLine="Drama3.Text = \"The Wolverine\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=3997960;
 //BA.debugLineNum = 3997960;BA.debugLine="Starter3.Text = \"Starring: Hugh Jackman, Will Yu";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=3997961;
 //BA.debugLineNum = 3997961;BA.debugLine="Year3.Text = \"(2015)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=3997962;
 //BA.debugLineNum = 3997962;BA.debugLine="OverView3.Text = \"A chance encounter between a y";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=3997963;
 //BA.debugLineNum = 3997963;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997964;
 //BA.debugLineNum = 3997964;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=3997966;
 //BA.debugLineNum = 3997966;BA.debugLine="Drama4.Text = \"Prisoners\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=3997967;
 //BA.debugLineNum = 3997967;BA.debugLine="Starter4.Text = \"Starring: Hugh Jackman, Jake Gy";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=3997968;
 //BA.debugLineNum = 3997968;BA.debugLine="Year4.Text = \"(2013)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=3997969;
 //BA.debugLineNum = 3997969;BA.debugLine="OverView4.Text = \"A desperate father takes the l";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=3997970;
 //BA.debugLineNum = 3997970;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997971;
 //BA.debugLineNum = 3997971;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=3997973;
 //BA.debugLineNum = 3997973;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997974;
 //BA.debugLineNum = 3997974;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997975;
 //BA.debugLineNum = 3997975;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997977;
 //BA.debugLineNum = 3997977;BA.debugLine="Else If query.Contains(\"amy smart\") Or query.Cont";
if (_query.contains("amy smart") || _query.contains("amy") || _query.contains("smart") || _query.contains("carlos sanz") || _query.contains("carlos") || _query.contains("sanz")) { 
RDebugUtils.currentLine=3997978;
 //BA.debugLineNum = 3997978;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=3997979;
 //BA.debugLineNum = 3997979;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=3997980;
 //BA.debugLineNum = 3997980;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=3997981;
 //BA.debugLineNum = 3997981;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=3997982;
 //BA.debugLineNum = 3997982;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997983;
 //BA.debugLineNum = 3997983;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=3997985;
 //BA.debugLineNum = 3997985;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997986;
 //BA.debugLineNum = 3997986;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997987;
 //BA.debugLineNum = 3997987;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997988;
 //BA.debugLineNum = 3997988;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997989;
 //BA.debugLineNum = 3997989;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3997991;
 //BA.debugLineNum = 3997991;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3997992;
 //BA.debugLineNum = 3997992;BA.debugLine="Else If query.Contains(\"jude law\") Or query.Conta";
if (_query.contains("jude law") || _query.contains("jude") || _query.contains("law") || _query.contains("rachel mcadams") || _query.contains("rachel") || _query.contains("mcadams")) { 
RDebugUtils.currentLine=3997993;
 //BA.debugLineNum = 3997993;BA.debugLine="Drama1.Text = \"Sherlock Holmes \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Sherlock Holmes "));
RDebugUtils.currentLine=3997994;
 //BA.debugLineNum = 3997994;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ju";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=3997995;
 //BA.debugLineNum = 3997995;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=3997996;
 //BA.debugLineNum = 3997996;BA.debugLine="OverView1.Text = \"Detective Sherlock Holmes and";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=3997997;
 //BA.debugLineNum = 3997997;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3997998;
 //BA.debugLineNum = 3997998;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=3998000;
 //BA.debugLineNum = 3998000;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998001;
 //BA.debugLineNum = 3998001;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998002;
 //BA.debugLineNum = 3998002;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998003;
 //BA.debugLineNum = 3998003;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998004;
 //BA.debugLineNum = 3998004;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998006;
 //BA.debugLineNum = 3998006;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3998007;
 //BA.debugLineNum = 3998007;BA.debugLine="Else if query.Contains(\"shu qi\") Or query.Contain";
if (_query.contains("shu qi") || _query.contains("shu") || _query.contains("qi") || _query.contains("matt") || _query.contains("schulze") || _query.contains("matt schulze")) { 
RDebugUtils.currentLine=3998008;
 //BA.debugLineNum = 3998008;BA.debugLine="Drama1.Text = \"The Transporter\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=3998009;
 //BA.debugLineNum = 3998009;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Shu Qi";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=3998010;
 //BA.debugLineNum = 3998010;BA.debugLine="Year1.Text = \"(2002)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=3998011;
 //BA.debugLineNum = 3998011;BA.debugLine="OverView1.Text = \"Frank Martin, who transports p";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=3998012;
 //BA.debugLineNum = 3998012;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3998013;
 //BA.debugLineNum = 3998013;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=3998015;
 //BA.debugLineNum = 3998015;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998016;
 //BA.debugLineNum = 3998016;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998017;
 //BA.debugLineNum = 3998017;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998018;
 //BA.debugLineNum = 3998018;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998019;
 //BA.debugLineNum = 3998019;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998021;
 //BA.debugLineNum = 3998021;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3998022;
 //BA.debugLineNum = 3998022;BA.debugLine="Else if query.Contains(\"chris evans\") Or query.Co";
if (_query.contains("chris evans") || _query.contains("chris") || _query.contains("evans") || _query.contains("mark ruffalo") || _query.contains("mark") || _query.contains("ruffalo")) { 
RDebugUtils.currentLine=3998023;
 //BA.debugLineNum = 3998023;BA.debugLine="Drama1.Text = \"Avengers: Endgame\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=3998024;
 //BA.debugLineNum = 3998024;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ch";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=3998025;
 //BA.debugLineNum = 3998025;BA.debugLine="Year1.Text = \"(2019)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=3998026;
 //BA.debugLineNum = 3998026;BA.debugLine="OverView1.Text = \"After the devastating events o";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=3998027;
 //BA.debugLineNum = 3998027;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3998028;
 //BA.debugLineNum = 3998028;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=3998030;
 //BA.debugLineNum = 3998030;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998031;
 //BA.debugLineNum = 3998031;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998032;
 //BA.debugLineNum = 3998032;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998033;
 //BA.debugLineNum = 3998033;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998034;
 //BA.debugLineNum = 3998034;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998036;
 //BA.debugLineNum = 3998036;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3998037;
 //BA.debugLineNum = 3998037;BA.debugLine="Else if query.Contains(\"dafne keen\") Or query.Con";
if (_query.contains("dafne keen") || _query.contains("dafne") || _query.contains("keen")) { 
RDebugUtils.currentLine=3998038;
 //BA.debugLineNum = 3998038;BA.debugLine="Drama1.Text = \"Logan\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=3998039;
 //BA.debugLineNum = 3998039;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=3998040;
 //BA.debugLineNum = 3998040;BA.debugLine="Year1.Text = \"(2017)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=3998041;
 //BA.debugLineNum = 3998041;BA.debugLine="OverView1.Text = \"In a future where mutants are";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=3998042;
 //BA.debugLineNum = 3998042;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3998043;
 //BA.debugLineNum = 3998043;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=3998045;
 //BA.debugLineNum = 3998045;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998046;
 //BA.debugLineNum = 3998046;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998047;
 //BA.debugLineNum = 3998047;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998048;
 //BA.debugLineNum = 3998048;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998049;
 //BA.debugLineNum = 3998049;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998051;
 //BA.debugLineNum = 3998051;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3998053;
 //BA.debugLineNum = 3998053;BA.debugLine="Else if query.Contains(\"gwyneth paltrow\") Or quer";
if (_query.contains("gwyneth paltrow") || _query.contains("gwyneth") || _query.contains("paltrow") || _query.contains("terrence howard") || _query.contains("terrence") || _query.contains("howard")) { 
RDebugUtils.currentLine=3998054;
 //BA.debugLineNum = 3998054;BA.debugLine="Drama1.Text = \"Iron Man\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=3998055;
 //BA.debugLineNum = 3998055;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Gw";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=3998056;
 //BA.debugLineNum = 3998056;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=3998057;
 //BA.debugLineNum = 3998057;BA.debugLine="OverView1.Text = \"After being held captive in an";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=3998058;
 //BA.debugLineNum = 3998058;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3998059;
 //BA.debugLineNum = 3998059;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=3998061;
 //BA.debugLineNum = 3998061;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998062;
 //BA.debugLineNum = 3998062;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998063;
 //BA.debugLineNum = 3998063;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998064;
 //BA.debugLineNum = 3998064;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998065;
 //BA.debugLineNum = 3998065;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998067;
 //BA.debugLineNum = 3998067;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3998069;
 //BA.debugLineNum = 3998069;BA.debugLine="Else if query.Contains(\"ian mckellen\") Or query.C";
if (_query.contains("ian mckellen") || _query.contains("ian") || _query.contains("mckellen")) { 
RDebugUtils.currentLine=3998070;
 //BA.debugLineNum = 3998070;BA.debugLine="Drama1.Text = \"X-Men\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=3998071;
 //BA.debugLineNum = 3998071;BA.debugLine="Starter1.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=3998072;
 //BA.debugLineNum = 3998072;BA.debugLine="Year1.Text = \"(2000)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=3998073;
 //BA.debugLineNum = 3998073;BA.debugLine="OverView1.Text = \"In a world where mutants (evol";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=3998074;
 //BA.debugLineNum = 3998074;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3998075;
 //BA.debugLineNum = 3998075;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=3998077;
 //BA.debugLineNum = 3998077;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998078;
 //BA.debugLineNum = 3998078;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998079;
 //BA.debugLineNum = 3998079;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998080;
 //BA.debugLineNum = 3998080;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998081;
 //BA.debugLineNum = 3998081;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998083;
 //BA.debugLineNum = 3998083;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3998085;
 //BA.debugLineNum = 3998085;BA.debugLine="Else if query.Contains(\"brad pitt\") Or query.Cont";
if (_query.contains("brad pitt") || _query.contains("brad") || _query.contains("pitt") || _query.contains("angelina jolie") || _query.contains("angelina") || _query.contains("jolie") || _query.contains("adam brody") || _query.contains("adam") || _query.contains("brody")) { 
RDebugUtils.currentLine=3998086;
 //BA.debugLineNum = 3998086;BA.debugLine="Drama1.Text = \"Mr. & Mrs. Smith \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Mr. & Mrs. Smith "));
RDebugUtils.currentLine=3998087;
 //BA.debugLineNum = 3998087;BA.debugLine="Starter1.Text = \"Starring: Brad Pitt, Angelina J";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
RDebugUtils.currentLine=3998088;
 //BA.debugLineNum = 3998088;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3998089;
 //BA.debugLineNum = 3998089;BA.debugLine="OverView1.Text = \"A husband and wife struggle to";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
RDebugUtils.currentLine=3998090;
 //BA.debugLineNum = 3998090;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3998091;
 //BA.debugLineNum = 3998091;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=3998093;
 //BA.debugLineNum = 3998093;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998094;
 //BA.debugLineNum = 3998094;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998095;
 //BA.debugLineNum = 3998095;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998096;
 //BA.debugLineNum = 3998096;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998097;
 //BA.debugLineNum = 3998097;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998099;
 //BA.debugLineNum = 3998099;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3998103;
 //BA.debugLineNum = 3998103;BA.debugLine="Else if query.Contains(\"will yun lee\") Or query.C";
if (_query.contains("will yun lee") || _query.contains("will") || _query.contains("yun") || _query.contains("lee") || _query.contains("tao okamoto") || _query.contains("tao") || _query.contains("okamoto")) { 
RDebugUtils.currentLine=3998104;
 //BA.debugLineNum = 3998104;BA.debugLine="Drama1.Text = \"The Wolverine\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=3998105;
 //BA.debugLineNum = 3998105;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Will Yu";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=3998106;
 //BA.debugLineNum = 3998106;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=3998107;
 //BA.debugLineNum = 3998107;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=3998108;
 //BA.debugLineNum = 3998108;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3998109;
 //BA.debugLineNum = 3998109;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=3998111;
 //BA.debugLineNum = 3998111;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998112;
 //BA.debugLineNum = 3998112;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998113;
 //BA.debugLineNum = 3998113;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998114;
 //BA.debugLineNum = 3998114;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998115;
 //BA.debugLineNum = 3998115;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998117;
 //BA.debugLineNum = 3998117;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3998119;
 //BA.debugLineNum = 3998119;BA.debugLine="Else if query.Contains(\"jake gyllenhaal\") Or quer";
if (_query.contains("jake gyllenhaal") || _query.contains("jake") || _query.contains("gyllenhaal") || _query.contains("viola davis") || _query.contains("viola") || _query.contains("davis")) { 
RDebugUtils.currentLine=3998120;
 //BA.debugLineNum = 3998120;BA.debugLine="Drama1.Text = \"Prisoners\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=3998121;
 //BA.debugLineNum = 3998121;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Jake Gy";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=3998122;
 //BA.debugLineNum = 3998122;BA.debugLine="Year1.Text = \"(2013)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=3998123;
 //BA.debugLineNum = 3998123;BA.debugLine="OverView1.Text = \"A desperate father takes the l";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=3998124;
 //BA.debugLineNum = 3998124;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3998125;
 //BA.debugLineNum = 3998125;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=3998127;
 //BA.debugLineNum = 3998127;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998128;
 //BA.debugLineNum = 3998128;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998129;
 //BA.debugLineNum = 3998129;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998130;
 //BA.debugLineNum = 3998130;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998131;
 //BA.debugLineNum = 3998131;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3998133;
 //BA.debugLineNum = 3998133;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else {
RDebugUtils.currentLine=3998137;
 //BA.debugLineNum = 3998137;BA.debugLine="MsgboxAsync(\"No results found for\" & \" \"\"\" & Use";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("No results found for"+" \""+_userinput+"\""),BA.ObjectToCharSequence(""),processBA);
 }}}}}}}}}}}}}}}}}}}}}}}}
;
RDebugUtils.currentLine=3998141;
 //BA.debugLineNum = 3998141;BA.debugLine="p.Width = 100%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=3998142;
 //BA.debugLineNum = 3998142;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=3998144;
 //BA.debugLineNum = 3998144;BA.debugLine="End Sub";
return "";
}
public static String  _searchengine_textchanged(String _old,String _new) throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchengine_textchanged", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchengine_textchanged", new Object[] {_old,_new}));}
String _query = "";
RDebugUtils.currentLine=3342336;
 //BA.debugLineNum = 3342336;BA.debugLine="Sub SearchEngine_TextChanged (Old As String, New A";
RDebugUtils.currentLine=3342337;
 //BA.debugLineNum = 3342337;BA.debugLine="Dim query As String = New.ToLowerCase.Trim";
_query = _new.toLowerCase().trim();
RDebugUtils.currentLine=3342339;
 //BA.debugLineNum = 3342339;BA.debugLine="If query = \"\" Then";
if ((_query).equals("")) { 
RDebugUtils.currentLine=3342341;
 //BA.debugLineNum = 3342341;BA.debugLine="NotFound.Text = \"\"";
mostCurrent._notfound.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=3342342;
 //BA.debugLineNum = 3342342;BA.debugLine="p.Height = 210%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=3342343;
 //BA.debugLineNum = 3342343;BA.debugLine="p.Width = 200%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA));
RDebugUtils.currentLine=3342344;
 //BA.debugLineNum = 3342344;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=3342346;
 //BA.debugLineNum = 3342346;BA.debugLine="Panel2.Visible = True";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3342347;
 //BA.debugLineNum = 3342347;BA.debugLine="Panel3.Visible = True";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3342348;
 //BA.debugLineNum = 3342348;BA.debugLine="Panel4.Visible = True";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3342349;
 //BA.debugLineNum = 3342349;BA.debugLine="Panel5.Visible = True";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3342350;
 //BA.debugLineNum = 3342350;BA.debugLine="Panel6.Visible = True";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3342351;
 //BA.debugLineNum = 3342351;BA.debugLine="Panel7.Visible = True";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3342352;
 //BA.debugLineNum = 3342352;BA.debugLine="Panel8.Visible = True";
mostCurrent._panel8.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3342353;
 //BA.debugLineNum = 3342353;BA.debugLine="Panel9.Visible = True";
mostCurrent._panel9.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3342354;
 //BA.debugLineNum = 3342354;BA.debugLine="Panel10.Visible = True";
mostCurrent._panel10.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3342355;
 //BA.debugLineNum = 3342355;BA.debugLine="Panel11.Visible = True";
mostCurrent._panel11.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3342357;
 //BA.debugLineNum = 3342357;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=3342358;
 //BA.debugLineNum = 3342358;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=3342359;
 //BA.debugLineNum = 3342359;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=3342360;
 //BA.debugLineNum = 3342360;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=3342361;
 //BA.debugLineNum = 3342361;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3342362;
 //BA.debugLineNum = 3342362;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=3342364;
 //BA.debugLineNum = 3342364;BA.debugLine="Drama2.Text = \"Sherlock Holmes \"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Sherlock Holmes "));
RDebugUtils.currentLine=3342365;
 //BA.debugLineNum = 3342365;BA.debugLine="Starter2.Text = \"Starring: Robert Downey Jr., Ju";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=3342366;
 //BA.debugLineNum = 3342366;BA.debugLine="Year2.Text = \"(2008)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=3342367;
 //BA.debugLineNum = 3342367;BA.debugLine="OverView2.Text = \"Detective Sherlock Holmes and";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=3342368;
 //BA.debugLineNum = 3342368;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3342369;
 //BA.debugLineNum = 3342369;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=3342371;
 //BA.debugLineNum = 3342371;BA.debugLine="Drama3.Text = \"The Transporter\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=3342372;
 //BA.debugLineNum = 3342372;BA.debugLine="Starter3.Text = \"Starring: Jason Statham, Shu Qi";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=3342373;
 //BA.debugLineNum = 3342373;BA.debugLine="Year3.Text = \"(2002)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=3342374;
 //BA.debugLineNum = 3342374;BA.debugLine="OverView3.Text = \"Frank Martin, who transports p";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=3342375;
 //BA.debugLineNum = 3342375;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3342376;
 //BA.debugLineNum = 3342376;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=3342378;
 //BA.debugLineNum = 3342378;BA.debugLine="Drama4.Text = \"Avengers: Endgame\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=3342379;
 //BA.debugLineNum = 3342379;BA.debugLine="Starter4.Text = \"Starring: Robert Downey Jr., Ch";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=3342380;
 //BA.debugLineNum = 3342380;BA.debugLine="Year4.Text = \"(2019)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=3342381;
 //BA.debugLineNum = 3342381;BA.debugLine="OverView4.Text = \"After the devastating events o";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=3342382;
 //BA.debugLineNum = 3342382;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3342383;
 //BA.debugLineNum = 3342383;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=3342385;
 //BA.debugLineNum = 3342385;BA.debugLine="Drama5.Text = \"Logan\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=3342386;
 //BA.debugLineNum = 3342386;BA.debugLine="Starter5.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=3342387;
 //BA.debugLineNum = 3342387;BA.debugLine="Year5.Text = \"(2017)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=3342388;
 //BA.debugLineNum = 3342388;BA.debugLine="OverView5.Text = \"In a future where mutants are";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=3342389;
 //BA.debugLineNum = 3342389;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
mostCurrent._dramaimage5.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3342390;
 //BA.debugLineNum = 3342390;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage5.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=3342393;
 //BA.debugLineNum = 3342393;BA.debugLine="Drama6.Text = \"Iron Man\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=3342394;
 //BA.debugLineNum = 3342394;BA.debugLine="Starter6.Text = \"Starring: Robert Downey Jr., Gw";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=3342395;
 //BA.debugLineNum = 3342395;BA.debugLine="Year6.Text = \"(2008)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=3342396;
 //BA.debugLineNum = 3342396;BA.debugLine="OverView6.Text = \"After being held captive in an";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=3342397;
 //BA.debugLineNum = 3342397;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
mostCurrent._dramaimage6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3342398;
 //BA.debugLineNum = 3342398;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage6.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=3342400;
 //BA.debugLineNum = 3342400;BA.debugLine="Drama7.Text = \"X-Men\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=3342401;
 //BA.debugLineNum = 3342401;BA.debugLine="Starter7.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=3342402;
 //BA.debugLineNum = 3342402;BA.debugLine="Year7.Text = \"(2000)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=3342403;
 //BA.debugLineNum = 3342403;BA.debugLine="OverView7.Text = \"In a world where mutants (evol";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=3342404;
 //BA.debugLineNum = 3342404;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3342405;
 //BA.debugLineNum = 3342405;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=3342407;
 //BA.debugLineNum = 3342407;BA.debugLine="Drama8.Text = \"Mr. & Mrs. Smith \"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("Mr. & Mrs. Smith "));
RDebugUtils.currentLine=3342408;
 //BA.debugLineNum = 3342408;BA.debugLine="Starter8.Text = \"Starring: Brad Pitt, Angelina J";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
RDebugUtils.currentLine=3342409;
 //BA.debugLineNum = 3342409;BA.debugLine="Year8.Text = \"(2005)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3342410;
 //BA.debugLineNum = 3342410;BA.debugLine="OverView8.Text = \"A husband and wife struggle to";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
RDebugUtils.currentLine=3342411;
 //BA.debugLineNum = 3342411;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
mostCurrent._dramaimage8.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3342412;
 //BA.debugLineNum = 3342412;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage8.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=3342414;
 //BA.debugLineNum = 3342414;BA.debugLine="Drama9.Text = \"The Wolverine\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=3342415;
 //BA.debugLineNum = 3342415;BA.debugLine="Starter9.Text = \"Starring: Hugh Jackman, Will Yu";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=3342416;
 //BA.debugLineNum = 3342416;BA.debugLine="Year9.Text = \"(2015)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=3342417;
 //BA.debugLineNum = 3342417;BA.debugLine="OverView9.Text = \"A chance encounter between a y";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=3342418;
 //BA.debugLineNum = 3342418;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
mostCurrent._dramaimage9.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3342419;
 //BA.debugLineNum = 3342419;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage9.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=3342421;
 //BA.debugLineNum = 3342421;BA.debugLine="Drama10.Text = \"Prisoners\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=3342422;
 //BA.debugLineNum = 3342422;BA.debugLine="Starter10.Text = \"Starring: Hugh Jackman, Jake G";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=3342423;
 //BA.debugLineNum = 3342423;BA.debugLine="Year10.Text = \"(2013)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=3342424;
 //BA.debugLineNum = 3342424;BA.debugLine="OverView10.Text = \"A desperate father takes the";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=3342425;
 //BA.debugLineNum = 3342425;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3342426;
 //BA.debugLineNum = 3342426;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
 };
RDebugUtils.currentLine=3342432;
 //BA.debugLineNum = 3342432;BA.debugLine="End Sub";
return "";
}
}