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
public anywheresoftware.b4a.objects.ImageViewWrapper _notfoundimg = null;
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
RDebugUtils.currentLine=1703942;
 //BA.debugLineNum = 1703942;BA.debugLine="NotFoundImg.Visible = False";
mostCurrent._notfoundimg.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1703948;
 //BA.debugLineNum = 1703948;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=1703949;
 //BA.debugLineNum = 1703949;BA.debugLine="Starter1.Text = \"1.	Starring: Jason Statham, Amy";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("1.	Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=1703950;
 //BA.debugLineNum = 1703950;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=1703951;
 //BA.debugLineNum = 1703951;BA.debugLine="OverView1.Text = \"Professional assassin Chev Chel";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=1703952;
 //BA.debugLineNum = 1703952;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1703953;
 //BA.debugLineNum = 1703953;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=1703955;
 //BA.debugLineNum = 1703955;BA.debugLine="Drama2.Text = \"Sherlock Holmes \"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Sherlock Holmes "));
RDebugUtils.currentLine=1703956;
 //BA.debugLineNum = 1703956;BA.debugLine="Starter2.Text = \"Starring: Robert Downey Jr., Jud";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=1703957;
 //BA.debugLineNum = 1703957;BA.debugLine="Year2.Text = \"(2008)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=1703958;
 //BA.debugLineNum = 1703958;BA.debugLine="OverView2.Text = \"Detective Sherlock Holmes and h";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=1703959;
 //BA.debugLineNum = 1703959;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1703960;
 //BA.debugLineNum = 1703960;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=1703962;
 //BA.debugLineNum = 1703962;BA.debugLine="Drama3.Text = \"The Transporter\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=1703963;
 //BA.debugLineNum = 1703963;BA.debugLine="Starter3.Text = \"Starring: Jason Statham, Shu Qi,";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=1703964;
 //BA.debugLineNum = 1703964;BA.debugLine="Year3.Text = \"(2002)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=1703965;
 //BA.debugLineNum = 1703965;BA.debugLine="OverView3.Text = \"Frank Martin, who transports pa";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=1703966;
 //BA.debugLineNum = 1703966;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1703967;
 //BA.debugLineNum = 1703967;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=1703969;
 //BA.debugLineNum = 1703969;BA.debugLine="Drama4.Text = \"Avengers: Endgame\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=1703970;
 //BA.debugLineNum = 1703970;BA.debugLine="Starter4.Text = \"Starring: Robert Downey Jr., Chr";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=1703971;
 //BA.debugLineNum = 1703971;BA.debugLine="Year4.Text = \"(2019)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=1703972;
 //BA.debugLineNum = 1703972;BA.debugLine="OverView4.Text = \"After the devastating events of";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=1703973;
 //BA.debugLineNum = 1703973;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1703974;
 //BA.debugLineNum = 1703974;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=1703976;
 //BA.debugLineNum = 1703976;BA.debugLine="Drama5.Text = \"Logan\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=1703977;
 //BA.debugLineNum = 1703977;BA.debugLine="Starter5.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=1703978;
 //BA.debugLineNum = 1703978;BA.debugLine="Year5.Text = \"(2017)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=1703979;
 //BA.debugLineNum = 1703979;BA.debugLine="OverView5.Text = \"In a future where mutants are n";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=1703980;
 //BA.debugLineNum = 1703980;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
mostCurrent._dramaimage5.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1703981;
 //BA.debugLineNum = 1703981;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage5.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=1703984;
 //BA.debugLineNum = 1703984;BA.debugLine="Drama6.Text = \"Iron Man\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=1703985;
 //BA.debugLineNum = 1703985;BA.debugLine="Starter6.Text = \"Starring: Robert Downey Jr., Gwy";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=1703986;
 //BA.debugLineNum = 1703986;BA.debugLine="Year6.Text = \"(2008)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=1703987;
 //BA.debugLineNum = 1703987;BA.debugLine="OverView6.Text = \"After being held captive in an";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=1703988;
 //BA.debugLineNum = 1703988;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
mostCurrent._dramaimage6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1703989;
 //BA.debugLineNum = 1703989;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage6.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=1703991;
 //BA.debugLineNum = 1703991;BA.debugLine="Drama7.Text = \"X-Men\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=1703992;
 //BA.debugLineNum = 1703992;BA.debugLine="Starter7.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=1703993;
 //BA.debugLineNum = 1703993;BA.debugLine="Year7.Text = \"(2000)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=1703994;
 //BA.debugLineNum = 1703994;BA.debugLine="OverView7.Text = \"In a world where mutants (evolv";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=1703995;
 //BA.debugLineNum = 1703995;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1703996;
 //BA.debugLineNum = 1703996;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=1703998;
 //BA.debugLineNum = 1703998;BA.debugLine="Drama8.Text = \"Mr. & Mrs. Smith \"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("Mr. & Mrs. Smith "));
RDebugUtils.currentLine=1703999;
 //BA.debugLineNum = 1703999;BA.debugLine="Starter8.Text = \"Starring: Brad Pitt, Angelina Jo";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
RDebugUtils.currentLine=1704000;
 //BA.debugLineNum = 1704000;BA.debugLine="Year8.Text = \"(2005)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=1704001;
 //BA.debugLineNum = 1704001;BA.debugLine="OverView8.Text = \"A husband and wife struggle to";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
RDebugUtils.currentLine=1704002;
 //BA.debugLineNum = 1704002;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
mostCurrent._dramaimage8.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1704003;
 //BA.debugLineNum = 1704003;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage8.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=1704005;
 //BA.debugLineNum = 1704005;BA.debugLine="Drama9.Text = \"The Wolverine\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=1704006;
 //BA.debugLineNum = 1704006;BA.debugLine="Starter9.Text = \"Starring: Hugh Jackman, Will Yun";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=1704007;
 //BA.debugLineNum = 1704007;BA.debugLine="Year9.Text = \"(2015)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=1704008;
 //BA.debugLineNum = 1704008;BA.debugLine="OverView9.Text = \"A chance encounter between a yo";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=1704009;
 //BA.debugLineNum = 1704009;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
mostCurrent._dramaimage9.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1704010;
 //BA.debugLineNum = 1704010;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage9.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=1704012;
 //BA.debugLineNum = 1704012;BA.debugLine="Drama10.Text = \"Prisoners\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=1704013;
 //BA.debugLineNum = 1704013;BA.debugLine="Starter10.Text = \"Starring: Hugh Jackman, Jake Gy";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=1704014;
 //BA.debugLineNum = 1704014;BA.debugLine="Year10.Text = \"(2013)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=1704015;
 //BA.debugLineNum = 1704015;BA.debugLine="OverView10.Text = \"A desperate father takes the l";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=1704016;
 //BA.debugLineNum = 1704016;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=1704017;
 //BA.debugLineNum = 1704017;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=1704019;
 //BA.debugLineNum = 1704019;BA.debugLine="ScrollView1.Panel.AddView(p, 0, 0, 200%x, 210%y)";
mostCurrent._scrollview1.getPanel().AddView((android.view.View)(mostCurrent._p.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=1704020;
 //BA.debugLineNum = 1704020;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=1704021;
 //BA.debugLineNum = 1704021;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 2031618;BA.debugLine="End Sub";
return "";
}
public static String  _hideallpanels() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "hideallpanels", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "hideallpanels", null));}
RDebugUtils.currentLine=3276800;
 //BA.debugLineNum = 3276800;BA.debugLine="Private Sub HideAllPanels";
RDebugUtils.currentLine=3276801;
 //BA.debugLineNum = 3276801;BA.debugLine="Panel2.Visible = False";
mostCurrent._panel2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3276802;
 //BA.debugLineNum = 3276802;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3276803;
 //BA.debugLineNum = 3276803;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3276804;
 //BA.debugLineNum = 3276804;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3276805;
 //BA.debugLineNum = 3276805;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3276806;
 //BA.debugLineNum = 3276806;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3276807;
 //BA.debugLineNum = 3276807;BA.debugLine="Panel8.Visible = False";
mostCurrent._panel8.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3276808;
 //BA.debugLineNum = 3276808;BA.debugLine="Panel9.Visible = False";
mostCurrent._panel9.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3276809;
 //BA.debugLineNum = 3276809;BA.debugLine="Panel10.Visible = False";
mostCurrent._panel10.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3276810;
 //BA.debugLineNum = 3276810;BA.debugLine="Panel11.Visible = False";
mostCurrent._panel11.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3276811;
 //BA.debugLineNum = 3276811;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 2097154;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 1900546;BA.debugLine="End Sub";
return "";
}
public static String  _searchbtn_click() throws Exception{
RDebugUtils.currentModule="action";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchbtn_click", null));}
String _query = "";
RDebugUtils.currentLine=3211264;
 //BA.debugLineNum = 3211264;BA.debugLine="Private Sub SearchBtn_Click";
RDebugUtils.currentLine=3211265;
 //BA.debugLineNum = 3211265;BA.debugLine="Dim query As String = SearchEngine.Text.ToLowerCa";
_query = mostCurrent._searchengine.getText().toLowerCase().trim();
RDebugUtils.currentLine=3211270;
 //BA.debugLineNum = 3211270;BA.debugLine="If query.Contains(\"crank\") Then";
if (_query.contains("crank")) { 
RDebugUtils.currentLine=3211272;
 //BA.debugLineNum = 3211272;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=3211273;
 //BA.debugLineNum = 3211273;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=3211274;
 //BA.debugLineNum = 3211274;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=3211275;
 //BA.debugLineNum = 3211275;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=3211276;
 //BA.debugLineNum = 3211276;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211277;
 //BA.debugLineNum = 3211277;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=3211279;
 //BA.debugLineNum = 3211279;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211280;
 //BA.debugLineNum = 3211280;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211281;
 //BA.debugLineNum = 3211281;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211282;
 //BA.debugLineNum = 3211282;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211283;
 //BA.debugLineNum = 3211283;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211285;
 //BA.debugLineNum = 3211285;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211287;
 //BA.debugLineNum = 3211287;BA.debugLine="Else If query.Contains(\"sherlock\") Or query.Conta";
if (_query.contains("sherlock") || _query.contains("sherlock holmes")) { 
RDebugUtils.currentLine=3211289;
 //BA.debugLineNum = 3211289;BA.debugLine="Drama1.Text = \"Sherlock Holmes \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Sherlock Holmes "));
RDebugUtils.currentLine=3211290;
 //BA.debugLineNum = 3211290;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ju";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=3211291;
 //BA.debugLineNum = 3211291;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=3211292;
 //BA.debugLineNum = 3211292;BA.debugLine="OverView1.Text = \"Detective Sherlock Holmes and";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=3211293;
 //BA.debugLineNum = 3211293;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211294;
 //BA.debugLineNum = 3211294;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=3211296;
 //BA.debugLineNum = 3211296;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211297;
 //BA.debugLineNum = 3211297;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211298;
 //BA.debugLineNum = 3211298;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211299;
 //BA.debugLineNum = 3211299;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211300;
 //BA.debugLineNum = 3211300;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211302;
 //BA.debugLineNum = 3211302;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211304;
 //BA.debugLineNum = 3211304;BA.debugLine="Else If query.Contains(\"the transporter\") Or quer";
if (_query.contains("the transporter") || _query.contains("transporter")) { 
RDebugUtils.currentLine=3211306;
 //BA.debugLineNum = 3211306;BA.debugLine="Drama1.Text = \"The Transporter\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=3211307;
 //BA.debugLineNum = 3211307;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Shu Qi";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=3211308;
 //BA.debugLineNum = 3211308;BA.debugLine="Year1.Text = \"(2002)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=3211309;
 //BA.debugLineNum = 3211309;BA.debugLine="OverView1.Text = \"Frank Martin, who transports p";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=3211310;
 //BA.debugLineNum = 3211310;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211311;
 //BA.debugLineNum = 3211311;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=3211313;
 //BA.debugLineNum = 3211313;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211314;
 //BA.debugLineNum = 3211314;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211315;
 //BA.debugLineNum = 3211315;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211316;
 //BA.debugLineNum = 3211316;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211317;
 //BA.debugLineNum = 3211317;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211319;
 //BA.debugLineNum = 3211319;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211321;
 //BA.debugLineNum = 3211321;BA.debugLine="Else If query.Contains(\"avengers endgame\") Or que";
if (_query.contains("avengers endgame") || _query.contains("avengers") || _query.contains("endgame")) { 
RDebugUtils.currentLine=3211323;
 //BA.debugLineNum = 3211323;BA.debugLine="Drama1.Text = \"Avengers: Endgame\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=3211324;
 //BA.debugLineNum = 3211324;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ch";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=3211325;
 //BA.debugLineNum = 3211325;BA.debugLine="Year1.Text = \"(2019)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=3211326;
 //BA.debugLineNum = 3211326;BA.debugLine="OverView1.Text = \"After the devastating events o";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=3211327;
 //BA.debugLineNum = 3211327;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211328;
 //BA.debugLineNum = 3211328;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=3211330;
 //BA.debugLineNum = 3211330;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211331;
 //BA.debugLineNum = 3211331;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211332;
 //BA.debugLineNum = 3211332;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211333;
 //BA.debugLineNum = 3211333;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211334;
 //BA.debugLineNum = 3211334;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211336;
 //BA.debugLineNum = 3211336;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211338;
 //BA.debugLineNum = 3211338;BA.debugLine="Else If query.Contains(\"logan\") Then";
if (_query.contains("logan")) { 
RDebugUtils.currentLine=3211340;
 //BA.debugLineNum = 3211340;BA.debugLine="Drama1.Text = \"Logan\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=3211341;
 //BA.debugLineNum = 3211341;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=3211342;
 //BA.debugLineNum = 3211342;BA.debugLine="Year1.Text = \"(2017)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=3211343;
 //BA.debugLineNum = 3211343;BA.debugLine="OverView1.Text = \"In a future where mutants are";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=3211344;
 //BA.debugLineNum = 3211344;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211345;
 //BA.debugLineNum = 3211345;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=3211347;
 //BA.debugLineNum = 3211347;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211348;
 //BA.debugLineNum = 3211348;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211349;
 //BA.debugLineNum = 3211349;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211350;
 //BA.debugLineNum = 3211350;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211351;
 //BA.debugLineNum = 3211351;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211353;
 //BA.debugLineNum = 3211353;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211355;
 //BA.debugLineNum = 3211355;BA.debugLine="Else If query.Contains(\"iron man\") Or query.Conta";
if (_query.contains("iron man") || _query.contains("man") || _query.contains("iron")) { 
RDebugUtils.currentLine=3211357;
 //BA.debugLineNum = 3211357;BA.debugLine="Drama1.Text = \"Iron Man\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=3211358;
 //BA.debugLineNum = 3211358;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Gw";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=3211359;
 //BA.debugLineNum = 3211359;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=3211360;
 //BA.debugLineNum = 3211360;BA.debugLine="OverView1.Text = \"After being held captive in an";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=3211361;
 //BA.debugLineNum = 3211361;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211362;
 //BA.debugLineNum = 3211362;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=3211364;
 //BA.debugLineNum = 3211364;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211365;
 //BA.debugLineNum = 3211365;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211366;
 //BA.debugLineNum = 3211366;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211367;
 //BA.debugLineNum = 3211367;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211368;
 //BA.debugLineNum = 3211368;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211370;
 //BA.debugLineNum = 3211370;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211372;
 //BA.debugLineNum = 3211372;BA.debugLine="Else If query.Contains(\"x-men\") Or query.Contains";
if (_query.contains("x-men") || _query.contains("men") || _query.contains("xmen") || _query.contains("x")) { 
RDebugUtils.currentLine=3211374;
 //BA.debugLineNum = 3211374;BA.debugLine="Drama7.Text = \"X-Men\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=3211375;
 //BA.debugLineNum = 3211375;BA.debugLine="Starter7.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=3211376;
 //BA.debugLineNum = 3211376;BA.debugLine="Year7.Text = \"(2000)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=3211377;
 //BA.debugLineNum = 3211377;BA.debugLine="OverView7.Text = \"In a world where mutants (evol";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=3211378;
 //BA.debugLineNum = 3211378;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211379;
 //BA.debugLineNum = 3211379;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=3211381;
 //BA.debugLineNum = 3211381;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211382;
 //BA.debugLineNum = 3211382;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211383;
 //BA.debugLineNum = 3211383;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211384;
 //BA.debugLineNum = 3211384;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211385;
 //BA.debugLineNum = 3211385;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211387;
 //BA.debugLineNum = 3211387;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211389;
 //BA.debugLineNum = 3211389;BA.debugLine="Else If query.Contains(\"mr & mrs smith\") Or query";
if (_query.contains("mr & mrs smith") || _query.contains("mr and mrs") || _query.contains("smith") || _query.contains("mrs") || _query.contains("mr")) { 
RDebugUtils.currentLine=3211391;
 //BA.debugLineNum = 3211391;BA.debugLine="Drama1.Text = \"Mr. & Mrs. Smith \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Mr. & Mrs. Smith "));
RDebugUtils.currentLine=3211392;
 //BA.debugLineNum = 3211392;BA.debugLine="Starter1.Text = \"Starring: Brad Pitt, Angelina J";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
RDebugUtils.currentLine=3211393;
 //BA.debugLineNum = 3211393;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3211394;
 //BA.debugLineNum = 3211394;BA.debugLine="OverView1.Text = \"A husband and wife struggle to";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
RDebugUtils.currentLine=3211395;
 //BA.debugLineNum = 3211395;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211396;
 //BA.debugLineNum = 3211396;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=3211398;
 //BA.debugLineNum = 3211398;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211399;
 //BA.debugLineNum = 3211399;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211400;
 //BA.debugLineNum = 3211400;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211401;
 //BA.debugLineNum = 3211401;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211402;
 //BA.debugLineNum = 3211402;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211404;
 //BA.debugLineNum = 3211404;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211406;
 //BA.debugLineNum = 3211406;BA.debugLine="Else If query.Contains(\"the wolverine\") Or query.";
if (_query.contains("the wolverine") || _query.contains("wolverine")) { 
RDebugUtils.currentLine=3211408;
 //BA.debugLineNum = 3211408;BA.debugLine="Drama1.Text = \"The Wolverine\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=3211409;
 //BA.debugLineNum = 3211409;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Will Yu";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=3211410;
 //BA.debugLineNum = 3211410;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=3211411;
 //BA.debugLineNum = 3211411;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=3211412;
 //BA.debugLineNum = 3211412;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211413;
 //BA.debugLineNum = 3211413;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=3211415;
 //BA.debugLineNum = 3211415;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211416;
 //BA.debugLineNum = 3211416;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211417;
 //BA.debugLineNum = 3211417;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211418;
 //BA.debugLineNum = 3211418;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211419;
 //BA.debugLineNum = 3211419;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211421;
 //BA.debugLineNum = 3211421;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211423;
 //BA.debugLineNum = 3211423;BA.debugLine="Else If query.Contains(\"prisoners\") Or query.Cont";
if (_query.contains("prisoners") || _query.contains("prisoner") || _query.contains("pri")) { 
RDebugUtils.currentLine=3211425;
 //BA.debugLineNum = 3211425;BA.debugLine="Drama10.Text = \"Prisoners\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=3211426;
 //BA.debugLineNum = 3211426;BA.debugLine="Starter10.Text = \"Starring: Hugh Jackman, Jake G";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=3211427;
 //BA.debugLineNum = 3211427;BA.debugLine="Year10.Text = \"(2013)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=3211428;
 //BA.debugLineNum = 3211428;BA.debugLine="OverView10.Text = \"A desperate father takes the";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=3211429;
 //BA.debugLineNum = 3211429;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211430;
 //BA.debugLineNum = 3211430;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=3211432;
 //BA.debugLineNum = 3211432;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211433;
 //BA.debugLineNum = 3211433;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211434;
 //BA.debugLineNum = 3211434;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211435;
 //BA.debugLineNum = 3211435;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211436;
 //BA.debugLineNum = 3211436;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211438;
 //BA.debugLineNum = 3211438;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211440;
 //BA.debugLineNum = 3211440;BA.debugLine="Else If query.Contains(\"jason statham\") Or query.";
if (_query.contains("jason statham") || _query.contains("jason") || _query.contains("statham")) { 
RDebugUtils.currentLine=3211441;
 //BA.debugLineNum = 3211441;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=3211442;
 //BA.debugLineNum = 3211442;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=3211443;
 //BA.debugLineNum = 3211443;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=3211444;
 //BA.debugLineNum = 3211444;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=3211445;
 //BA.debugLineNum = 3211445;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211446;
 //BA.debugLineNum = 3211446;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=3211448;
 //BA.debugLineNum = 3211448;BA.debugLine="Drama2.Text = \"The Transporter\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=3211449;
 //BA.debugLineNum = 3211449;BA.debugLine="Starter2.Text = \"Starring: Jason Statham, Shu Qi";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=3211450;
 //BA.debugLineNum = 3211450;BA.debugLine="Year2.Text = \"(2002)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=3211451;
 //BA.debugLineNum = 3211451;BA.debugLine="OverView2.Text = \"Frank Martin, who transports p";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=3211452;
 //BA.debugLineNum = 3211452;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211453;
 //BA.debugLineNum = 3211453;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=3211455;
 //BA.debugLineNum = 3211455;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211456;
 //BA.debugLineNum = 3211456;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211457;
 //BA.debugLineNum = 3211457;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211459;
 //BA.debugLineNum = 3211459;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211461;
 //BA.debugLineNum = 3211461;BA.debugLine="Else If query.Contains(\"Robert downey jr\") Or que";
if (_query.contains("Robert downey jr") || _query.contains("robert") || _query.contains("downey") || _query.contains("downey jr")) { 
RDebugUtils.currentLine=3211462;
 //BA.debugLineNum = 3211462;BA.debugLine="Drama1.Text = \"Sherlock Holmes \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Sherlock Holmes "));
RDebugUtils.currentLine=3211463;
 //BA.debugLineNum = 3211463;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ju";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=3211464;
 //BA.debugLineNum = 3211464;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=3211465;
 //BA.debugLineNum = 3211465;BA.debugLine="OverView1.Text = \"Detective Sherlock Holmes and";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=3211466;
 //BA.debugLineNum = 3211466;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211467;
 //BA.debugLineNum = 3211467;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=3211469;
 //BA.debugLineNum = 3211469;BA.debugLine="Drama2.Text = \"Avengers: Endgame\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=3211470;
 //BA.debugLineNum = 3211470;BA.debugLine="Starter2.Text = \"Starring: Robert Downey Jr., Ch";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=3211471;
 //BA.debugLineNum = 3211471;BA.debugLine="Year2.Text = \"(2019)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=3211472;
 //BA.debugLineNum = 3211472;BA.debugLine="OverView2.Text = \"After the devastating events o";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=3211473;
 //BA.debugLineNum = 3211473;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211474;
 //BA.debugLineNum = 3211474;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=3211476;
 //BA.debugLineNum = 3211476;BA.debugLine="Drama3.Text = \"Iron Man\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=3211477;
 //BA.debugLineNum = 3211477;BA.debugLine="Starter3.Text = \"Starring: Robert Downey Jr., Gw";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=3211478;
 //BA.debugLineNum = 3211478;BA.debugLine="Year3.Text = \"(2008)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=3211479;
 //BA.debugLineNum = 3211479;BA.debugLine="OverView3.Text = \"After being held captive in an";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=3211480;
 //BA.debugLineNum = 3211480;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211481;
 //BA.debugLineNum = 3211481;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=3211483;
 //BA.debugLineNum = 3211483;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211484;
 //BA.debugLineNum = 3211484;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211485;
 //BA.debugLineNum = 3211485;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211487;
 //BA.debugLineNum = 3211487;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211489;
 //BA.debugLineNum = 3211489;BA.debugLine="Else If query.Contains(\"patrick stewart\") Or quer";
if (_query.contains("patrick stewart") || _query.contains("patrick") || _query.contains("stewart")) { 
RDebugUtils.currentLine=3211490;
 //BA.debugLineNum = 3211490;BA.debugLine="Drama1.Text = \"Logan\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=3211491;
 //BA.debugLineNum = 3211491;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=3211492;
 //BA.debugLineNum = 3211492;BA.debugLine="Year1.Text = \"(2017)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=3211493;
 //BA.debugLineNum = 3211493;BA.debugLine="OverView1.Text = \"In a future where mutants are";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=3211494;
 //BA.debugLineNum = 3211494;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211495;
 //BA.debugLineNum = 3211495;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=3211497;
 //BA.debugLineNum = 3211497;BA.debugLine="Drama2.Text = \"X-Men\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=3211498;
 //BA.debugLineNum = 3211498;BA.debugLine="Starter2.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=3211499;
 //BA.debugLineNum = 3211499;BA.debugLine="Year2.Text = \"(2000)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=3211500;
 //BA.debugLineNum = 3211500;BA.debugLine="OverView2.Text = \"In a world where mutants (evol";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=3211501;
 //BA.debugLineNum = 3211501;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211502;
 //BA.debugLineNum = 3211502;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=3211504;
 //BA.debugLineNum = 3211504;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211505;
 //BA.debugLineNum = 3211505;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211506;
 //BA.debugLineNum = 3211506;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211507;
 //BA.debugLineNum = 3211507;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211509;
 //BA.debugLineNum = 3211509;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211511;
 //BA.debugLineNum = 3211511;BA.debugLine="Else If query.Contains(\"hugh jackman\") Or query.C";
if (_query.contains("hugh jackman") || _query.contains("hugh") || _query.contains("jackman")) { 
RDebugUtils.currentLine=3211512;
 //BA.debugLineNum = 3211512;BA.debugLine="Drama1.Text = \"Logan\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=3211513;
 //BA.debugLineNum = 3211513;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=3211514;
 //BA.debugLineNum = 3211514;BA.debugLine="Year1.Text = \"(2017)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=3211515;
 //BA.debugLineNum = 3211515;BA.debugLine="OverView1.Text = \"In a future where mutants are";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=3211516;
 //BA.debugLineNum = 3211516;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211517;
 //BA.debugLineNum = 3211517;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=3211519;
 //BA.debugLineNum = 3211519;BA.debugLine="Drama2.Text = \"X-Men\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=3211520;
 //BA.debugLineNum = 3211520;BA.debugLine="Starter2.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=3211521;
 //BA.debugLineNum = 3211521;BA.debugLine="Year2.Text = \"(2000)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=3211522;
 //BA.debugLineNum = 3211522;BA.debugLine="OverView2.Text = \"In a world where mutants (evol";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=3211523;
 //BA.debugLineNum = 3211523;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211524;
 //BA.debugLineNum = 3211524;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=3211528;
 //BA.debugLineNum = 3211528;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211529;
 //BA.debugLineNum = 3211529;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211530;
 //BA.debugLineNum = 3211530;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211531;
 //BA.debugLineNum = 3211531;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211532;
 //BA.debugLineNum = 3211532;BA.debugLine="Else If query.Contains(\"morgan freeman\") Or query";
if (_query.contains("morgan freeman") || _query.contains("morgan")) { 
RDebugUtils.currentLine=3211533;
 //BA.debugLineNum = 3211533;BA.debugLine="Drama1.Text = \"Million Dollar Baby\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Million Dollar Baby"));
RDebugUtils.currentLine=3211534;
 //BA.debugLineNum = 3211534;BA.debugLine="Starter1.Text = \"Starring: Morgan Freeman, Hilar";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
RDebugUtils.currentLine=3211535;
 //BA.debugLineNum = 3211535;BA.debugLine="Year1.Text = \"(2004)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2004)"));
RDebugUtils.currentLine=3211536;
 //BA.debugLineNum = 3211536;BA.debugLine="OverView1.Text = \"A waitress with dreams of beco";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
RDebugUtils.currentLine=3211537;
 //BA.debugLineNum = 3211537;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211538;
 //BA.debugLineNum = 3211538;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=3211540;
 //BA.debugLineNum = 3211540;BA.debugLine="Drama2.Text = \"Gone Baby Gone\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Gone Baby Gone"));
RDebugUtils.currentLine=3211541;
 //BA.debugLineNum = 3211541;BA.debugLine="Starter2.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=3211542;
 //BA.debugLineNum = 3211542;BA.debugLine="Year2.Text = \"(2007)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2007)"));
RDebugUtils.currentLine=3211543;
 //BA.debugLineNum = 3211543;BA.debugLine="OverView2.Text = \"In a tough Boston neighborhood";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."));
RDebugUtils.currentLine=3211544;
 //BA.debugLineNum = 3211544;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211545;
 //BA.debugLineNum = 3211545;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=3211547;
 //BA.debugLineNum = 3211547;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211548;
 //BA.debugLineNum = 3211548;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211549;
 //BA.debugLineNum = 3211549;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211550;
 //BA.debugLineNum = 3211550;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211551;
 //BA.debugLineNum = 3211551;BA.debugLine="Else If query.Contains(\"clint eastwood\") Or query";
if (_query.contains("clint eastwood") || _query.contains("clint") || _query.contains("eastwood")) { 
RDebugUtils.currentLine=3211552;
 //BA.debugLineNum = 3211552;BA.debugLine="Drama1.Text = \"Million Dollar Baby\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Million Dollar Baby"));
RDebugUtils.currentLine=3211553;
 //BA.debugLineNum = 3211553;BA.debugLine="Starter1.Text = \"Starring: Morgan Freeman, Hilar";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"));
RDebugUtils.currentLine=3211554;
 //BA.debugLineNum = 3211554;BA.debugLine="Year1.Text = \"(2004)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2004)"));
RDebugUtils.currentLine=3211555;
 //BA.debugLineNum = 3211555;BA.debugLine="OverView1.Text = \"A waitress with dreams of beco";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."));
RDebugUtils.currentLine=3211556;
 //BA.debugLineNum = 3211556;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211557;
 //BA.debugLineNum = 3211557;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=3211559;
 //BA.debugLineNum = 3211559;BA.debugLine="Drama2.Text = \"The Bridges of Madison Country\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("The Bridges of Madison Country"));
RDebugUtils.currentLine=3211560;
 //BA.debugLineNum = 3211560;BA.debugLine="Starter2.Text = \"Starring: Clint Eastwood, Meryl";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Clint Eastwood, Meryl Streep"));
RDebugUtils.currentLine=3211561;
 //BA.debugLineNum = 3211561;BA.debugLine="Year2.Text = \"(1995)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(1995)"));
RDebugUtils.currentLine=3211562;
 //BA.debugLineNum = 3211562;BA.debugLine="OverView2.Text = \"A brief, passionate romance be";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."));
RDebugUtils.currentLine=3211563;
 //BA.debugLineNum = 3211563;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211564;
 //BA.debugLineNum = 3211564;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=3211566;
 //BA.debugLineNum = 3211566;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211567;
 //BA.debugLineNum = 3211567;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211568;
 //BA.debugLineNum = 3211568;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211569;
 //BA.debugLineNum = 3211569;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211570;
 //BA.debugLineNum = 3211570;BA.debugLine="Else If query.Contains(\"amy Adams\") Or query.Cont";
if (_query.contains("amy Adams") || _query.contains("amy") || _query.contains("adams")) { 
RDebugUtils.currentLine=3211571;
 //BA.debugLineNum = 3211571;BA.debugLine="Drama1.Text = \"Doubt\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Doubt"));
RDebugUtils.currentLine=3211572;
 //BA.debugLineNum = 3211572;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"));
RDebugUtils.currentLine=3211573;
 //BA.debugLineNum = 3211573;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=3211574;
 //BA.debugLineNum = 3211574;BA.debugLine="OverView1.Text = \"In a Catholic school in the Br";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a Catholic school in the Bronx, a strict nun becomes suspicious of a priest’s relationship with a student. As she digs deeper, the lines between truth and suspicion blur, leaving everyone in moral and emotional limbo."));
RDebugUtils.currentLine=3211575;
 //BA.debugLineNum = 3211575;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211576;
 //BA.debugLineNum = 3211576;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"doubt.jpg").getObject()));
RDebugUtils.currentLine=3211578;
 //BA.debugLineNum = 3211578;BA.debugLine="Drama2.Text = \"The Master\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("The Master"));
RDebugUtils.currentLine=3211579;
 //BA.debugLineNum = 3211579;BA.debugLine="Starter2.Text = \"Starring: Philip Seymour Hoffma";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"));
RDebugUtils.currentLine=3211580;
 //BA.debugLineNum = 3211580;BA.debugLine="Year2.Text = \"(2012)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2012)"));
RDebugUtils.currentLine=3211581;
 //BA.debugLineNum = 3211581;BA.debugLine="OverView2.Text = \"A mentally unstable WWII veter";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."));
RDebugUtils.currentLine=3211582;
 //BA.debugLineNum = 3211582;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211583;
 //BA.debugLineNum = 3211583;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=3211585;
 //BA.debugLineNum = 3211585;BA.debugLine="Drama3.Text = \"Her\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("Her"));
RDebugUtils.currentLine=3211586;
 //BA.debugLineNum = 3211586;BA.debugLine="Starter3.Text = \"Starring: Joaquin Phoenix, Roon";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"));
RDebugUtils.currentLine=3211587;
 //BA.debugLineNum = 3211587;BA.debugLine="Year3.Text = \"(2013)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=3211588;
 //BA.debugLineNum = 3211588;BA.debugLine="OverView3.Text = \"In a near-future Los Angeles,";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."));
RDebugUtils.currentLine=3211589;
 //BA.debugLineNum = 3211589;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211590;
 //BA.debugLineNum = 3211590;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=3211592;
 //BA.debugLineNum = 3211592;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211593;
 //BA.debugLineNum = 3211593;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211594;
 //BA.debugLineNum = 3211594;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211595;
 //BA.debugLineNum = 3211595;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211596;
 //BA.debugLineNum = 3211596;BA.debugLine="Else If query.Contains(\"rooney mara\") Or query.Co";
if (_query.contains("rooney mara") || _query.contains("rooney") || _query.contains("mara") || _query.contains("sarah") || _query.contains("sarah paulson") || _query.contains("paulson")) { 
RDebugUtils.currentLine=3211597;
 //BA.debugLineNum = 3211597;BA.debugLine="Drama1.Text = \"Carol\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=3211598;
 //BA.debugLineNum = 3211598;BA.debugLine="Starter1.Text = \"Starring: Rooney Mara, Cate Bla";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=3211599;
 //BA.debugLineNum = 3211599;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=3211600;
 //BA.debugLineNum = 3211600;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=3211601;
 //BA.debugLineNum = 3211601;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211602;
 //BA.debugLineNum = 3211602;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=3211604;
 //BA.debugLineNum = 3211604;BA.debugLine="Drama2.Text = \"Blue Jasmine\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=3211605;
 //BA.debugLineNum = 3211605;BA.debugLine="Starter2.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=3211606;
 //BA.debugLineNum = 3211606;BA.debugLine="Year2.Text = \"(2013)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=3211607;
 //BA.debugLineNum = 3211607;BA.debugLine="OverView2.Text = \"After losing her fortune and s";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=3211608;
 //BA.debugLineNum = 3211608;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211609;
 //BA.debugLineNum = 3211609;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=3211611;
 //BA.debugLineNum = 3211611;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211612;
 //BA.debugLineNum = 3211612;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211613;
 //BA.debugLineNum = 3211613;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211614;
 //BA.debugLineNum = 3211614;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211616;
 //BA.debugLineNum = 3211616;BA.debugLine="Else If query.Contains(\"olvia coloman\") Or query.";
if (_query.contains("olvia coloman") || _query.contains("dakota johnson") || _query.contains("jessie buckley") || _query.contains("olvia") || _query.contains("jessie ") || _query.contains("dakota") || _query.contains("buckley") || _query.contains("coloman") || _query.contains("johnson")) { 
RDebugUtils.currentLine=3211617;
 //BA.debugLineNum = 3211617;BA.debugLine="Drama1.Text = \"Carol\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Carol"));
RDebugUtils.currentLine=3211618;
 //BA.debugLineNum = 3211618;BA.debugLine="Starter1.Text = \"Starring: Rooney Mara, Cate Bla";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"));
RDebugUtils.currentLine=3211619;
 //BA.debugLineNum = 3211619;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=3211620;
 //BA.debugLineNum = 3211620;BA.debugLine="OverView1.Text = \"A solitary woman on vacation b";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity."));
RDebugUtils.currentLine=3211621;
 //BA.debugLineNum = 3211621;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211622;
 //BA.debugLineNum = 3211622;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=3211624;
 //BA.debugLineNum = 3211624;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211625;
 //BA.debugLineNum = 3211625;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211626;
 //BA.debugLineNum = 3211626;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211627;
 //BA.debugLineNum = 3211627;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211628;
 //BA.debugLineNum = 3211628;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211630;
 //BA.debugLineNum = 3211630;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211631;
 //BA.debugLineNum = 3211631;BA.debugLine="Else If query.Contains(\"sally hawkins\") Or query.";
if (_query.contains("sally hawkins") || _query.contains("alec baldwin") || _query.contains("sally") || _query.contains("haswkins") || _query.contains("alec") || _query.contains("baldwin")) { 
RDebugUtils.currentLine=3211632;
 //BA.debugLineNum = 3211632;BA.debugLine="Drama1.Text = \"Blue Jasmine\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Blue Jasmine"));
RDebugUtils.currentLine=3211633;
 //BA.debugLineNum = 3211633;BA.debugLine="Starter1.Text = \"Starring: Cate Blanchett, Sally";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"));
RDebugUtils.currentLine=3211634;
 //BA.debugLineNum = 3211634;BA.debugLine="Year1.Text = \"(2013)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=3211635;
 //BA.debugLineNum = 3211635;BA.debugLine="OverView1.Text = \"After losing her fortune and s";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."));
RDebugUtils.currentLine=3211636;
 //BA.debugLineNum = 3211636;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211637;
 //BA.debugLineNum = 3211637;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=3211639;
 //BA.debugLineNum = 3211639;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211640;
 //BA.debugLineNum = 3211640;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211641;
 //BA.debugLineNum = 3211641;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211642;
 //BA.debugLineNum = 3211642;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211643;
 //BA.debugLineNum = 3211643;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211645;
 //BA.debugLineNum = 3211645;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211646;
 //BA.debugLineNum = 3211646;BA.debugLine="Else if query.Contains(\"michelle williams\") Or qu";
if (_query.contains("michelle williams") || _query.contains("lucas hedges") || _query.contains("michelle") || _query.contains("williams") || _query.contains("lucas") || _query.contains("hedges")) { 
RDebugUtils.currentLine=3211647;
 //BA.debugLineNum = 3211647;BA.debugLine="Drama1.Text = \"Manchester by the Sea\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Manchester by the Sea"));
RDebugUtils.currentLine=3211648;
 //BA.debugLineNum = 3211648;BA.debugLine="Starter1.Text = \"Starring: Casey Affleck, Michel";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Casey Affleck, Michelle Williams, Lucas Hedges"));
RDebugUtils.currentLine=3211649;
 //BA.debugLineNum = 3211649;BA.debugLine="Year1.Text = \"(2016)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=3211650;
 //BA.debugLineNum = 3211650;BA.debugLine="OverView1.Text = \"After the death of his brother";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."));
RDebugUtils.currentLine=3211651;
 //BA.debugLineNum = 3211651;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211652;
 //BA.debugLineNum = 3211652;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=3211654;
 //BA.debugLineNum = 3211654;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211655;
 //BA.debugLineNum = 3211655;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211656;
 //BA.debugLineNum = 3211656;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211657;
 //BA.debugLineNum = 3211657;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211658;
 //BA.debugLineNum = 3211658;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211660;
 //BA.debugLineNum = 3211660;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3211661;
 //BA.debugLineNum = 3211661;BA.debugLine="Else if query.Contains(\"dustin hoffman\") Or query";
if (_query.contains("dustin hoffman") || _query.contains("dustin") || _query.contains("hoffman") || _query.contains("justin henry") || _query.contains("justin") || _query.contains("henry")) { 
RDebugUtils.currentLine=3211662;
 //BA.debugLineNum = 3211662;BA.debugLine="Drama1.Text = \"Kramer vs. Kramer\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Kramer vs. Kramer"));
RDebugUtils.currentLine=3211663;
 //BA.debugLineNum = 3211663;BA.debugLine="Starter1.Text = \"Starring: Dustin Hoffman, Meryl";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Dustin Hoffman, Meryl Streep, Justin Henry"));
RDebugUtils.currentLine=3211664;
 //BA.debugLineNum = 3211664;BA.debugLine="Year1.Text = \"(1979)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(1979)"));
RDebugUtils.currentLine=3211665;
 //BA.debugLineNum = 3211665;BA.debugLine="OverView1.Text = \"In this emotionally charged co";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"));
RDebugUtils.currentLine=3211666;
 //BA.debugLineNum = 3211666;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3211667;
 //BA.debugLineNum = 3211667;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=3211669;
 //BA.debugLineNum = 3211669;BA.debugLine="Panel3.Visible = False";
mostCurrent._panel3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211670;
 //BA.debugLineNum = 3211670;BA.debugLine="Panel4.Visible = False";
mostCurrent._panel4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211671;
 //BA.debugLineNum = 3211671;BA.debugLine="Panel5.Visible = False";
mostCurrent._panel5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211672;
 //BA.debugLineNum = 3211672;BA.debugLine="Panel6.Visible = False";
mostCurrent._panel6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211673;
 //BA.debugLineNum = 3211673;BA.debugLine="Panel7.Visible = False";
mostCurrent._panel7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211675;
 //BA.debugLineNum = 3211675;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else {
RDebugUtils.currentLine=3211677;
 //BA.debugLineNum = 3211677;BA.debugLine="HideAllPanels";
_hideallpanels();
RDebugUtils.currentLine=3211678;
 //BA.debugLineNum = 3211678;BA.debugLine="NotFoundImg.Visible = True";
mostCurrent._notfoundimg.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3211679;
 //BA.debugLineNum = 3211679;BA.debugLine="NotFoundImg.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._notfoundimg.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"404.png").getObject()));
 }}}}}}}}}}}}}}}}}}}}}}
;
RDebugUtils.currentLine=3211684;
 //BA.debugLineNum = 3211684;BA.debugLine="p.Width = 100%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=3211685;
 //BA.debugLineNum = 3211685;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=3211686;
 //BA.debugLineNum = 3211686;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=3342356;
 //BA.debugLineNum = 3342356;BA.debugLine="NotFoundImg.Visible = False";
mostCurrent._notfoundimg.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3342358;
 //BA.debugLineNum = 3342358;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=3342359;
 //BA.debugLineNum = 3342359;BA.debugLine="Starter1.Text = \"1.	Starring: Jason Statham, Amy";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("1.	Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=3342360;
 //BA.debugLineNum = 3342360;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=3342361;
 //BA.debugLineNum = 3342361;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=3342362;
 //BA.debugLineNum = 3342362;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3342363;
 //BA.debugLineNum = 3342363;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=3342365;
 //BA.debugLineNum = 3342365;BA.debugLine="Drama2.Text = \"Sherlock Holmes \"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Sherlock Holmes "));
RDebugUtils.currentLine=3342366;
 //BA.debugLineNum = 3342366;BA.debugLine="Starter2.Text = \"Starring: Robert Downey Jr., Ju";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=3342367;
 //BA.debugLineNum = 3342367;BA.debugLine="Year2.Text = \"(2008)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=3342368;
 //BA.debugLineNum = 3342368;BA.debugLine="OverView2.Text = \"Detective Sherlock Holmes and";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=3342369;
 //BA.debugLineNum = 3342369;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3342370;
 //BA.debugLineNum = 3342370;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=3342372;
 //BA.debugLineNum = 3342372;BA.debugLine="Drama3.Text = \"The Transporter\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=3342373;
 //BA.debugLineNum = 3342373;BA.debugLine="Starter3.Text = \"Starring: Jason Statham, Shu Qi";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=3342374;
 //BA.debugLineNum = 3342374;BA.debugLine="Year3.Text = \"(2002)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=3342375;
 //BA.debugLineNum = 3342375;BA.debugLine="OverView3.Text = \"Frank Martin, who transports p";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=3342376;
 //BA.debugLineNum = 3342376;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3342377;
 //BA.debugLineNum = 3342377;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=3342379;
 //BA.debugLineNum = 3342379;BA.debugLine="Drama4.Text = \"Avengers: Endgame\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=3342380;
 //BA.debugLineNum = 3342380;BA.debugLine="Starter4.Text = \"Starring: Robert Downey Jr., Ch";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=3342381;
 //BA.debugLineNum = 3342381;BA.debugLine="Year4.Text = \"(2019)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=3342382;
 //BA.debugLineNum = 3342382;BA.debugLine="OverView4.Text = \"After the devastating events o";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=3342383;
 //BA.debugLineNum = 3342383;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3342384;
 //BA.debugLineNum = 3342384;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=3342386;
 //BA.debugLineNum = 3342386;BA.debugLine="Drama5.Text = \"Logan\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=3342387;
 //BA.debugLineNum = 3342387;BA.debugLine="Starter5.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=3342388;
 //BA.debugLineNum = 3342388;BA.debugLine="Year5.Text = \"(2017)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=3342389;
 //BA.debugLineNum = 3342389;BA.debugLine="OverView5.Text = \"In a future where mutants are";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=3342390;
 //BA.debugLineNum = 3342390;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
mostCurrent._dramaimage5.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3342391;
 //BA.debugLineNum = 3342391;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage5.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=3342394;
 //BA.debugLineNum = 3342394;BA.debugLine="Drama6.Text = \"Iron Man\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=3342395;
 //BA.debugLineNum = 3342395;BA.debugLine="Starter6.Text = \"Starring: Robert Downey Jr., Gw";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=3342396;
 //BA.debugLineNum = 3342396;BA.debugLine="Year6.Text = \"(2008)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=3342397;
 //BA.debugLineNum = 3342397;BA.debugLine="OverView6.Text = \"After being held captive in an";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=3342398;
 //BA.debugLineNum = 3342398;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
mostCurrent._dramaimage6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3342399;
 //BA.debugLineNum = 3342399;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage6.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=3342401;
 //BA.debugLineNum = 3342401;BA.debugLine="Drama7.Text = \"X-Men\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=3342402;
 //BA.debugLineNum = 3342402;BA.debugLine="Starter7.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=3342403;
 //BA.debugLineNum = 3342403;BA.debugLine="Year7.Text = \"(2000)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=3342404;
 //BA.debugLineNum = 3342404;BA.debugLine="OverView7.Text = \"In a world where mutants (evol";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=3342405;
 //BA.debugLineNum = 3342405;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3342406;
 //BA.debugLineNum = 3342406;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=3342408;
 //BA.debugLineNum = 3342408;BA.debugLine="Drama8.Text = \"Mr. & Mrs. Smith \"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("Mr. & Mrs. Smith "));
RDebugUtils.currentLine=3342409;
 //BA.debugLineNum = 3342409;BA.debugLine="Starter8.Text = \"Starring: Brad Pitt, Angelina J";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
RDebugUtils.currentLine=3342410;
 //BA.debugLineNum = 3342410;BA.debugLine="Year8.Text = \"(2005)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3342411;
 //BA.debugLineNum = 3342411;BA.debugLine="OverView8.Text = \"A husband and wife struggle to";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
RDebugUtils.currentLine=3342412;
 //BA.debugLineNum = 3342412;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
mostCurrent._dramaimage8.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3342413;
 //BA.debugLineNum = 3342413;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage8.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=3342415;
 //BA.debugLineNum = 3342415;BA.debugLine="Drama9.Text = \"The Wolverine\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=3342416;
 //BA.debugLineNum = 3342416;BA.debugLine="Starter9.Text = \"Starring: Hugh Jackman, Will Yu";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=3342417;
 //BA.debugLineNum = 3342417;BA.debugLine="Year9.Text = \"(2015)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=3342418;
 //BA.debugLineNum = 3342418;BA.debugLine="OverView9.Text = \"A chance encounter between a y";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=3342419;
 //BA.debugLineNum = 3342419;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
mostCurrent._dramaimage9.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3342420;
 //BA.debugLineNum = 3342420;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage9.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=3342422;
 //BA.debugLineNum = 3342422;BA.debugLine="Drama10.Text = \"Prisoners\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=3342423;
 //BA.debugLineNum = 3342423;BA.debugLine="Starter10.Text = \"Starring: Hugh Jackman, Jake G";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=3342424;
 //BA.debugLineNum = 3342424;BA.debugLine="Year10.Text = \"(2013)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=3342425;
 //BA.debugLineNum = 3342425;BA.debugLine="OverView10.Text = \"A desperate father takes the";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=3342426;
 //BA.debugLineNum = 3342426;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3342427;
 //BA.debugLineNum = 3342427;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
 };
RDebugUtils.currentLine=3342433;
 //BA.debugLineNum = 3342433;BA.debugLine="End Sub";
return "";
}
}