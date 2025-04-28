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
RDebugUtils.currentLine=4194304;
 //BA.debugLineNum = 4194304;BA.debugLine="Private Sub ActionPage_Click";
RDebugUtils.currentLine=4194305;
 //BA.debugLineNum = 4194305;BA.debugLine="StartActivity(Action)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._action.getObject()));
RDebugUtils.currentLine=4194306;
 //BA.debugLineNum = 4194306;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=3604480;
 //BA.debugLineNum = 3604480;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=3604481;
 //BA.debugLineNum = 3604481;BA.debugLine="Activity.LoadLayout(\"scifi\") ' Layout contains Sc";
mostCurrent._activity.LoadLayout("scifi",mostCurrent.activityBA);
RDebugUtils.currentLine=3604483;
 //BA.debugLineNum = 3604483;BA.debugLine="p.Initialize(\"\")";
mostCurrent._p.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=3604484;
 //BA.debugLineNum = 3604484;BA.debugLine="p.LoadLayout(\"panelview\")";
mostCurrent._p.LoadLayout("panelview",mostCurrent.activityBA);
RDebugUtils.currentLine=3604491;
 //BA.debugLineNum = 3604491;BA.debugLine="Drama1.Text = \"The Fellowship of the Ring\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Fellowship of the Ring"));
RDebugUtils.currentLine=3604492;
 //BA.debugLineNum = 3604492;BA.debugLine="Starter1.Text = \"Starring: Elijah Wood, Ian McKel";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Elijah Wood, Ian McKellen, Orlando Bloom"));
RDebugUtils.currentLine=3604493;
 //BA.debugLineNum = 3604493;BA.debugLine="Year1.Text = \"(2001)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2001)"));
RDebugUtils.currentLine=3604494;
 //BA.debugLineNum = 3604494;BA.debugLine="OverView1.Text = \"The future of civilization rest";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"));
RDebugUtils.currentLine=3604495;
 //BA.debugLineNum = 3604495;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3604496;
 //BA.debugLineNum = 3604496;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=3604498;
 //BA.debugLineNum = 3604498;BA.debugLine="Drama2.Text = \"Charlie and the Chocolate Factory\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Charlie and the Chocolate Factory"));
RDebugUtils.currentLine=3604499;
 //BA.debugLineNum = 3604499;BA.debugLine="Starter2.Text = \"Starring: Johnny Depp, Freddie H";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"));
RDebugUtils.currentLine=3604500;
 //BA.debugLineNum = 3604500;BA.debugLine="Year2.Text = \"(2005)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3604501;
 //BA.debugLineNum = 3604501;BA.debugLine="OverView2.Text = \"Charlie Bucket, a humble boy fr";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."));
RDebugUtils.currentLine=3604502;
 //BA.debugLineNum = 3604502;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3604503;
 //BA.debugLineNum = 3604503;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=3604505;
 //BA.debugLineNum = 3604505;BA.debugLine="Drama3.Text = \"Alice in Wonderland\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("Alice in Wonderland"));
RDebugUtils.currentLine=3604506;
 //BA.debugLineNum = 3604506;BA.debugLine="Starter3.Text = \"Starring: Mia Wasikowska, Johnny";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"));
RDebugUtils.currentLine=3604507;
 //BA.debugLineNum = 3604507;BA.debugLine="Year3.Text = \"(2010)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2010)"));
RDebugUtils.currentLine=3604508;
 //BA.debugLineNum = 3604508;BA.debugLine="OverView3.Text = \"Alice, now a teenager, returns";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."));
RDebugUtils.currentLine=3604509;
 //BA.debugLineNum = 3604509;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3604510;
 //BA.debugLineNum = 3604510;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=3604512;
 //BA.debugLineNum = 3604512;BA.debugLine="Drama4.Text = \"Harry Potter and the Philosopher's";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Harry Potter and the Philosopher's Stone"));
RDebugUtils.currentLine=3604513;
 //BA.debugLineNum = 3604513;BA.debugLine="Starter4.Text = \"Starring: Daniel Radcliffe, Rupe";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Daniel Radcliffe, Rupert Grint, Emma Watson"));
RDebugUtils.currentLine=3604514;
 //BA.debugLineNum = 3604514;BA.debugLine="Year4.Text = \"(2003)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2003)"));
RDebugUtils.currentLine=3604515;
 //BA.debugLineNum = 3604515;BA.debugLine="OverView4.Text = \"Captain Jack Sparrow must rescu";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("Captain Jack Sparrow must rescue Elizabeth Swann, who has been kidnapped by cursed pirates led by Captain Barbossa. Alongside Will Turner, Jack battles ghostly pirates, seeking both freedom and revenge."));
RDebugUtils.currentLine=3604516;
 //BA.debugLineNum = 3604516;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3604517;
 //BA.debugLineNum = 3604517;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=3604519;
 //BA.debugLineNum = 3604519;BA.debugLine="Drama5.Text = \"The Curse of the Black Pearl\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("The Curse of the Black Pearl"));
RDebugUtils.currentLine=3604520;
 //BA.debugLineNum = 3604520;BA.debugLine="Starter5.Text = \"Starring: Johnny Depp, Orlando B";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Orlando Bloom, Keira Knightley"));
RDebugUtils.currentLine=3604521;
 //BA.debugLineNum = 3604521;BA.debugLine="Year5.Text = \"(2003)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(2003)"));
RDebugUtils.currentLine=3604522;
 //BA.debugLineNum = 3604522;BA.debugLine="OverView5.Text = \"Four siblings, evacuated from w";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=3604523;
 //BA.debugLineNum = 3604523;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
mostCurrent._dramaimage5.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3604524;
 //BA.debugLineNum = 3604524;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage5.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=3604527;
 //BA.debugLineNum = 3604527;BA.debugLine="Drama6.Text = \"The Chronicles of Narnia\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("The Chronicles of Narnia"));
RDebugUtils.currentLine=3604528;
 //BA.debugLineNum = 3604528;BA.debugLine="Starter6.Text = \"Starring: Georgie Henley, Skanda";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Georgie Henley, Skandar Keynes, William Moseley,"));
RDebugUtils.currentLine=3604529;
 //BA.debugLineNum = 3604529;BA.debugLine="Year6.Text = \"(2003)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2003)"));
RDebugUtils.currentLine=3604530;
 //BA.debugLineNum = 3604530;BA.debugLine="OverView6.Text = \"Four siblings, evacuated from w";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=3604531;
 //BA.debugLineNum = 3604531;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
mostCurrent._dramaimage6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3604532;
 //BA.debugLineNum = 3604532;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage6.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=3604534;
 //BA.debugLineNum = 3604534;BA.debugLine="Drama7.Text = \"Doctor Strange\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("Doctor Strange"));
RDebugUtils.currentLine=3604535;
 //BA.debugLineNum = 3604535;BA.debugLine="Starter7.Text = \"Starring: Benedict Cumberbatch,";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Benedict Cumberbatch, Chiwetel Ejiofor"));
RDebugUtils.currentLine=3604536;
 //BA.debugLineNum = 3604536;BA.debugLine="Year7.Text = \"(2016)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=3604537;
 //BA.debugLineNum = 3604537;BA.debugLine="OverView7.Text = \"After a life-changing accident";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("After a life-changing accident ends his career, arrogant surgeon Stephen Strange seeks healing in Nepal, where he learns the mystic arts and must protect the world from dark magical forces beyond reality."));
RDebugUtils.currentLine=3604538;
 //BA.debugLineNum = 3604538;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3604539;
 //BA.debugLineNum = 3604539;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=3604541;
 //BA.debugLineNum = 3604541;BA.debugLine="Drama8.Text = \"V for Vendetta\"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("V for Vendetta"));
RDebugUtils.currentLine=3604542;
 //BA.debugLineNum = 3604542;BA.debugLine="Starter8.Text = \"Starring: Hugo Weaving, Natalie";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Hugo Weaving, Natalie Portman, Stephen Rea, John Hurt"));
RDebugUtils.currentLine=3604543;
 //BA.debugLineNum = 3604543;BA.debugLine="Year8.Text = \"(2005)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3604544;
 //BA.debugLineNum = 3604544;BA.debugLine="OverView8.Text = \"In a totalitarian future Britai";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("In a totalitarian future Britain, a masked revolutionary known as \"V\" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."));
RDebugUtils.currentLine=3604545;
 //BA.debugLineNum = 3604545;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
mostCurrent._dramaimage8.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3604546;
 //BA.debugLineNum = 3604546;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage8.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=3604548;
 //BA.debugLineNum = 3604548;BA.debugLine="Drama9.Text = \"Aladdin\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("Aladdin"));
RDebugUtils.currentLine=3604549;
 //BA.debugLineNum = 3604549;BA.debugLine="Starter9.Text = \"Starring: Mena Massoud, Naomi Sc";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Mena Massoud, Naomi Scott, Will Smith"));
RDebugUtils.currentLine=3604550;
 //BA.debugLineNum = 3604550;BA.debugLine="Year9.Text = \"(2019)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=3604551;
 //BA.debugLineNum = 3604551;BA.debugLine="OverView9.Text = \"Aladdin, a kind-hearted street";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."));
RDebugUtils.currentLine=3604552;
 //BA.debugLineNum = 3604552;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
mostCurrent._dramaimage9.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3604553;
 //BA.debugLineNum = 3604553;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage9.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=3604555;
 //BA.debugLineNum = 3604555;BA.debugLine="Drama10.Text = \"After Earth\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("After Earth"));
RDebugUtils.currentLine=3604556;
 //BA.debugLineNum = 3604556;BA.debugLine="Starter10.Text = \"Starring: Will Smith, Jaden Smi";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Will Smith, Jaden Smith, Sigourney Weaver"));
RDebugUtils.currentLine=3604557;
 //BA.debugLineNum = 3604557;BA.debugLine="Year10.Text = \"(2013)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=3604558;
 //BA.debugLineNum = 3604558;BA.debugLine="OverView10.Text = \"Set in the future, After Earth";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."));
RDebugUtils.currentLine=3604559;
 //BA.debugLineNum = 3604559;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3604560;
 //BA.debugLineNum = 3604560;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=3604562;
 //BA.debugLineNum = 3604562;BA.debugLine="ScrollView1.Panel.AddView(p, 0, 0, 200%x, 210%y)";
mostCurrent._scrollview1.getPanel().AddView((android.view.View)(mostCurrent._p.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=3604563;
 //BA.debugLineNum = 3604563;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=3604564;
 //BA.debugLineNum = 3604564;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="scifi";
RDebugUtils.currentLine=3932160;
 //BA.debugLineNum = 3932160;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=3932162;
 //BA.debugLineNum = 3932162;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=3866624;
 //BA.debugLineNum = 3866624;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=3866626;
 //BA.debugLineNum = 3866626;BA.debugLine="End Sub";
return "";
}
public static String  _dramapage_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "dramapage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "dramapage_click", null));}
RDebugUtils.currentLine=4128768;
 //BA.debugLineNum = 4128768;BA.debugLine="Private Sub DramaPage_Click";
RDebugUtils.currentLine=4128769;
 //BA.debugLineNum = 4128769;BA.debugLine="StartActivity(Drama)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._drama.getObject()));
RDebugUtils.currentLine=4128770;
 //BA.debugLineNum = 4128770;BA.debugLine="End Sub";
return "";
}
public static String  _homepage_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "homepage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "homepage_click", null));}
RDebugUtils.currentLine=4063232;
 //BA.debugLineNum = 4063232;BA.debugLine="Private Sub HomePage_Click";
RDebugUtils.currentLine=4063233;
 //BA.debugLineNum = 4063233;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
RDebugUtils.currentLine=4063234;
 //BA.debugLineNum = 4063234;BA.debugLine="End Sub";
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
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "scifi", "panelmovie1_click"), null);
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
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0479884/");
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
RDebugUtils.currentLine=4915212;
 //BA.debugLineNum = 4915212;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("84915212",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=4915213;
 //BA.debugLineNum = 4915213;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=4915216;
 //BA.debugLineNum = 4915216;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=5505025;
 //BA.debugLineNum = 5505025;BA.debugLine="Try";
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
RDebugUtils.currentLine=5505026;
 //BA.debugLineNum = 5505026;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=5505027;
 //BA.debugLineNum = 5505027;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "scifi", "panelmovie10_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=5505028;
 //BA.debugLineNum = 5505028;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=5505029;
 //BA.debugLineNum = 5505029;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=5505030;
 //BA.debugLineNum = 5505030;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt1392214/");
RDebugUtils.currentLine=5505031;
 //BA.debugLineNum = 5505031;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=5505032;
 //BA.debugLineNum = 5505032;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=5505035;
 //BA.debugLineNum = 5505035;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("85505035",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=5505036;
 //BA.debugLineNum = 5505036;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=5505039;
 //BA.debugLineNum = 5505039;BA.debugLine="End Sub";
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
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=4980739;
 //BA.debugLineNum = 4980739;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "scifi", "panelmovie2_click"), null);
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
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0988045/");
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
anywheresoftware.b4a.keywords.Common.LogImpl("84980747",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
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
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "scifi", "panelmovie3_click"), null);
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
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt4154796/");
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
anywheresoftware.b4a.keywords.Common.LogImpl("85046283",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
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
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "scifi", "panelmovie4_click"), null);
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
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt3315342/");
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
anywheresoftware.b4a.keywords.Common.LogImpl("85111819",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
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
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "scifi", "panelmovie5_click"), null);
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
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0371746/");
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
anywheresoftware.b4a.keywords.Common.LogImpl("85177355",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
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
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "scifi", "panelmovie6_click"), null);
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
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0120903/");
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
anywheresoftware.b4a.keywords.Common.LogImpl("85242891",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
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
RDebugUtils.currentLine=5308417;
 //BA.debugLineNum = 5308417;BA.debugLine="Try";
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
RDebugUtils.currentLine=5308418;
 //BA.debugLineNum = 5308418;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=5308419;
 //BA.debugLineNum = 5308419;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "scifi", "panelmovie7_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=5308420;
 //BA.debugLineNum = 5308420;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=5308421;
 //BA.debugLineNum = 5308421;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=5308422;
 //BA.debugLineNum = 5308422;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt2334873/");
RDebugUtils.currentLine=5308423;
 //BA.debugLineNum = 5308423;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=5308424;
 //BA.debugLineNum = 5308424;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=5308427;
 //BA.debugLineNum = 5308427;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("85308427",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=5308428;
 //BA.debugLineNum = 5308428;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=5308431;
 //BA.debugLineNum = 5308431;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=5373953;
 //BA.debugLineNum = 5373953;BA.debugLine="Try";
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
RDebugUtils.currentLine=5373954;
 //BA.debugLineNum = 5373954;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=5373955;
 //BA.debugLineNum = 5373955;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "scifi", "panelmovie8_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=5373956;
 //BA.debugLineNum = 5373956;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=5373957;
 //BA.debugLineNum = 5373957;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=5373958;
 //BA.debugLineNum = 5373958;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt0356910/");
RDebugUtils.currentLine=5373959;
 //BA.debugLineNum = 5373959;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=5373960;
 //BA.debugLineNum = 5373960;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=5373963;
 //BA.debugLineNum = 5373963;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("85373963",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=5373964;
 //BA.debugLineNum = 5373964;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=5373967;
 //BA.debugLineNum = 5373967;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=5439489;
 //BA.debugLineNum = 5439489;BA.debugLine="Try";
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
RDebugUtils.currentLine=5439490;
 //BA.debugLineNum = 5439490;BA.debugLine="Msgbox2Async(\"Want to watch the trailer of the m";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Want to watch the trailer of the movie?"),BA.ObjectToCharSequence("Go to Trailer"),"Yes","","No",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=5439491;
 //BA.debugLineNum = 5439491;BA.debugLine="Wait For Msgbox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "scifi", "panelmovie9_click"), null);
this.state = 11;
return;
case 11:
//C
this.state = 4;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=5439492;
 //BA.debugLineNum = 5439492;BA.debugLine="If Result = DialogResponse.POSITIVE Then";
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
RDebugUtils.currentLine=5439493;
 //BA.debugLineNum = 5439493;BA.debugLine="Dim i As Intent";
_i = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=5439494;
 //BA.debugLineNum = 5439494;BA.debugLine="i.Initialize(i.ACTION_VIEW, \"https://www.imdb.c";
_i.Initialize(_i.ACTION_VIEW,"https://www.imdb.com/title/tt1430132/");
RDebugUtils.currentLine=5439495;
 //BA.debugLineNum = 5439495;BA.debugLine="i.SetComponent(\"com.android.chrome/com.google.a";
_i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main");
RDebugUtils.currentLine=5439496;
 //BA.debugLineNum = 5439496;BA.debugLine="StartActivity(i)";
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
RDebugUtils.currentLine=5439499;
 //BA.debugLineNum = 5439499;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("85439499",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
RDebugUtils.currentLine=5439500;
 //BA.debugLineNum = 5439500;BA.debugLine="MsgboxAsync(\"can't find Chome app\", \"Error\")";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("can't find Chome app"),BA.ObjectToCharSequence("Error"),processBA);
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
this.catchState = 0;
;
RDebugUtils.currentLine=5439503;
 //BA.debugLineNum = 5439503;BA.debugLine="End Sub";
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
RDebugUtils.currentLine=3997696;
 //BA.debugLineNum = 3997696;BA.debugLine="Private Sub SciFiPage_Click";
RDebugUtils.currentLine=3997698;
 //BA.debugLineNum = 3997698;BA.debugLine="End Sub";
return "";
}
public static String  _searchbtn_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchbtn_click", null));}
RDebugUtils.currentLine=3670016;
 //BA.debugLineNum = 3670016;BA.debugLine="Private Sub SearchBtn_Click";
RDebugUtils.currentLine=3670017;
 //BA.debugLineNum = 3670017;BA.debugLine="SearchNow";
_searchnow();
RDebugUtils.currentLine=3670018;
 //BA.debugLineNum = 3670018;BA.debugLine="End Sub";
return "";
}
public static String  _searchnow() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchnow", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchnow", null));}
String _query = "";
String _userinput = "";
RDebugUtils.currentLine=3735552;
 //BA.debugLineNum = 3735552;BA.debugLine="Sub SearchNow";
RDebugUtils.currentLine=3735553;
 //BA.debugLineNum = 3735553;BA.debugLine="Dim query As String = SearchEngine.Text.ToLowerCa";
_query = mostCurrent._searchengine.getText().toLowerCase().trim();
RDebugUtils.currentLine=3735556;
 //BA.debugLineNum = 3735556;BA.debugLine="Dim UserInput As String = SearchEngine.Text";
_userinput = mostCurrent._searchengine.getText();
RDebugUtils.currentLine=3735559;
 //BA.debugLineNum = 3735559;BA.debugLine="If query.Contains(\"the fellowship of the ring\") O";
if (_query.contains("the fellowship of the ring") || _query.contains("fellowship") || _query.contains("the ring") || _query.contains("ring")) { 
RDebugUtils.currentLine=3735561;
 //BA.debugLineNum = 3735561;BA.debugLine="Drama1.Text = \"The Fellowship of the Ring\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Fellowship of the Ring"));
RDebugUtils.currentLine=3735562;
 //BA.debugLineNum = 3735562;BA.debugLine="Starter1.Text = \"Starring: Elijah Wood, Ian McKe";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Elijah Wood, Ian McKellen, Orlando Bloom"));
RDebugUtils.currentLine=3735563;
 //BA.debugLineNum = 3735563;BA.debugLine="Year1.Text = \"(2001)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2001)"));
RDebugUtils.currentLine=3735564;
 //BA.debugLineNum = 3735564;BA.debugLine="OverView1.Text = \"The future of civilization res";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"));
RDebugUtils.currentLine=3735565;
 //BA.debugLineNum = 3735565;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735566;
 //BA.debugLineNum = 3735566;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=3735568;
 //BA.debugLineNum = 3735568;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735569;
 //BA.debugLineNum = 3735569;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735570;
 //BA.debugLineNum = 3735570;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735571;
 //BA.debugLineNum = 3735571;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735572;
 //BA.debugLineNum = 3735572;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735574;
 //BA.debugLineNum = 3735574;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735576;
 //BA.debugLineNum = 3735576;BA.debugLine="Else If query.Contains(\"charlie and the chocolate";
if (_query.contains("charlie and the chocolate factory") || _query.contains("charlie") || _query.contains("factory")) { 
RDebugUtils.currentLine=3735578;
 //BA.debugLineNum = 3735578;BA.debugLine="Drama1.Text = \"Charlie and the Chocolate Factory";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Charlie and the Chocolate Factory"));
RDebugUtils.currentLine=3735579;
 //BA.debugLineNum = 3735579;BA.debugLine="Starter1.Text = \"Starring: Johnny Depp, Freddie";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"));
RDebugUtils.currentLine=3735580;
 //BA.debugLineNum = 3735580;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3735581;
 //BA.debugLineNum = 3735581;BA.debugLine="OverView1.Text = \"Charlie Bucket, a humble boy f";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."));
RDebugUtils.currentLine=3735582;
 //BA.debugLineNum = 3735582;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735583;
 //BA.debugLineNum = 3735583;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=3735585;
 //BA.debugLineNum = 3735585;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735586;
 //BA.debugLineNum = 3735586;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735587;
 //BA.debugLineNum = 3735587;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735588;
 //BA.debugLineNum = 3735588;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735589;
 //BA.debugLineNum = 3735589;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735591;
 //BA.debugLineNum = 3735591;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735593;
 //BA.debugLineNum = 3735593;BA.debugLine="Else If query.Contains(\"alice in wonderland\") Or";
if (_query.contains("alice in wonderland") || _query.contains("alice") || _query.contains("wonderland")) { 
RDebugUtils.currentLine=3735595;
 //BA.debugLineNum = 3735595;BA.debugLine="Drama1.Text = \"Alice in Wonderland\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Alice in Wonderland"));
RDebugUtils.currentLine=3735596;
 //BA.debugLineNum = 3735596;BA.debugLine="Starter1.Text = \"Starring: Mia Wasikowska, Johnn";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"));
RDebugUtils.currentLine=3735597;
 //BA.debugLineNum = 3735597;BA.debugLine="Year1.Text = \"(2010)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2010)"));
RDebugUtils.currentLine=3735598;
 //BA.debugLineNum = 3735598;BA.debugLine="OverView1.Text = \"Alice, now a teenager, returns";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."));
RDebugUtils.currentLine=3735599;
 //BA.debugLineNum = 3735599;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735600;
 //BA.debugLineNum = 3735600;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=3735602;
 //BA.debugLineNum = 3735602;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735603;
 //BA.debugLineNum = 3735603;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735604;
 //BA.debugLineNum = 3735604;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735605;
 //BA.debugLineNum = 3735605;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735606;
 //BA.debugLineNum = 3735606;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735608;
 //BA.debugLineNum = 3735608;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735610;
 //BA.debugLineNum = 3735610;BA.debugLine="Else If query.Contains(\"harry potter and the phil";
if (_query.contains("harry potter and the philosophers stone") || _query.contains("harry") || _query.contains("harry potter")) { 
RDebugUtils.currentLine=3735612;
 //BA.debugLineNum = 3735612;BA.debugLine="Drama1.Text = \"Harry Potter and the Philosopher'";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Harry Potter and the Philosopher's Stone"));
RDebugUtils.currentLine=3735613;
 //BA.debugLineNum = 3735613;BA.debugLine="Starter1.Text = \"Starring: Daniel Radcliffe, Rup";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Daniel Radcliffe, Rupert Grint, Emma Watson"));
RDebugUtils.currentLine=3735614;
 //BA.debugLineNum = 3735614;BA.debugLine="Year1.Text = \"(2003)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2003)"));
RDebugUtils.currentLine=3735615;
 //BA.debugLineNum = 3735615;BA.debugLine="OverView1.Text = \"Captain Jack Sparrow must resc";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Captain Jack Sparrow must rescue Elizabeth Swann, who has been kidnapped by cursed pirates led by Captain Barbossa. Alongside Will Turner, Jack battles ghostly pirates, seeking both freedom and revenge."));
RDebugUtils.currentLine=3735616;
 //BA.debugLineNum = 3735616;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735617;
 //BA.debugLineNum = 3735617;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=3735620;
 //BA.debugLineNum = 3735620;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735621;
 //BA.debugLineNum = 3735621;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735622;
 //BA.debugLineNum = 3735622;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735623;
 //BA.debugLineNum = 3735623;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735625;
 //BA.debugLineNum = 3735625;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735627;
 //BA.debugLineNum = 3735627;BA.debugLine="Else If query.Contains(\"the curse of the black pe";
if (_query.contains("the curse of the black pearl") || _query.contains("curse") || _query.contains("black pearl")) { 
RDebugUtils.currentLine=3735629;
 //BA.debugLineNum = 3735629;BA.debugLine="Drama1.Text = \"The Curse of the Black Pearl\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Curse of the Black Pearl"));
RDebugUtils.currentLine=3735630;
 //BA.debugLineNum = 3735630;BA.debugLine="Starter1.Text = \"Starring: Johnny Depp, Orlando";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Orlando Bloom, Keira Knightley"));
RDebugUtils.currentLine=3735631;
 //BA.debugLineNum = 3735631;BA.debugLine="Year1.Text = \"(2003)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2003)"));
RDebugUtils.currentLine=3735632;
 //BA.debugLineNum = 3735632;BA.debugLine="OverView1.Text = \"Four siblings, evacuated from";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=3735633;
 //BA.debugLineNum = 3735633;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735634;
 //BA.debugLineNum = 3735634;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=3735637;
 //BA.debugLineNum = 3735637;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735638;
 //BA.debugLineNum = 3735638;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735639;
 //BA.debugLineNum = 3735639;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735640;
 //BA.debugLineNum = 3735640;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735641;
 //BA.debugLineNum = 3735641;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735643;
 //BA.debugLineNum = 3735643;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735645;
 //BA.debugLineNum = 3735645;BA.debugLine="Else If query.Contains(\"the chronicles of narnia\"";
if (_query.contains("the chronicles of narnia") || _query.contains("chronicles") || _query.contains("narnia")) { 
RDebugUtils.currentLine=3735647;
 //BA.debugLineNum = 3735647;BA.debugLine="Drama1.Text = \"The Chronicles of Narnia\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Chronicles of Narnia"));
RDebugUtils.currentLine=3735648;
 //BA.debugLineNum = 3735648;BA.debugLine="Starter1.Text = \"Starring: Georgie Henley, Skand";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Georgie Henley, Skandar Keynes, William Moseley,"));
RDebugUtils.currentLine=3735649;
 //BA.debugLineNum = 3735649;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3735650;
 //BA.debugLineNum = 3735650;BA.debugLine="OverView1.Text = \"Four siblings, evacuated from";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=3735651;
 //BA.debugLineNum = 3735651;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735652;
 //BA.debugLineNum = 3735652;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=3735654;
 //BA.debugLineNum = 3735654;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735655;
 //BA.debugLineNum = 3735655;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735656;
 //BA.debugLineNum = 3735656;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735657;
 //BA.debugLineNum = 3735657;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735658;
 //BA.debugLineNum = 3735658;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735660;
 //BA.debugLineNum = 3735660;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735662;
 //BA.debugLineNum = 3735662;BA.debugLine="Else If query.Contains(\"doctor strange\") Or query";
if (_query.contains("doctor strange") || _query.contains("doctor") || _query.contains("strange")) { 
RDebugUtils.currentLine=3735664;
 //BA.debugLineNum = 3735664;BA.debugLine="Drama1.Text = \"Doctor Strange\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Doctor Strange"));
RDebugUtils.currentLine=3735665;
 //BA.debugLineNum = 3735665;BA.debugLine="Starter1.Text = \"Starring: Benedict Cumberbatch,";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Benedict Cumberbatch, Chiwetel Ejiofor"));
RDebugUtils.currentLine=3735666;
 //BA.debugLineNum = 3735666;BA.debugLine="Year1.Text = \"(2016)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=3735667;
 //BA.debugLineNum = 3735667;BA.debugLine="OverView1.Text = \"After a life-changing accident";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After a life-changing accident ends his career, arrogant surgeon Stephen Strange seeks healing in Nepal, where he learns the mystic arts and must protect the world from dark magical forces beyond reality."));
RDebugUtils.currentLine=3735668;
 //BA.debugLineNum = 3735668;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735669;
 //BA.debugLineNum = 3735669;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=3735671;
 //BA.debugLineNum = 3735671;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735672;
 //BA.debugLineNum = 3735672;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735673;
 //BA.debugLineNum = 3735673;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735674;
 //BA.debugLineNum = 3735674;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735675;
 //BA.debugLineNum = 3735675;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735677;
 //BA.debugLineNum = 3735677;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735679;
 //BA.debugLineNum = 3735679;BA.debugLine="Else If query.Contains(\"v for vendetta\") Or query";
if (_query.contains("v for vendetta") || _query.contains("vendetta") || _query.contains("v")) { 
RDebugUtils.currentLine=3735681;
 //BA.debugLineNum = 3735681;BA.debugLine="Drama1.Text = \"V for Vendetta\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("V for Vendetta"));
RDebugUtils.currentLine=3735682;
 //BA.debugLineNum = 3735682;BA.debugLine="Starter1.Text = \"Starring: Hugo Weaving, Natalie";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugo Weaving, Natalie Portman, Stephen Rea, John Hurt"));
RDebugUtils.currentLine=3735683;
 //BA.debugLineNum = 3735683;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3735684;
 //BA.debugLineNum = 3735684;BA.debugLine="OverView1.Text = \"In a totalitarian future Brita";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a totalitarian future Britain, a masked revolutionary known as \"V\" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."));
RDebugUtils.currentLine=3735685;
 //BA.debugLineNum = 3735685;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735686;
 //BA.debugLineNum = 3735686;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=3735688;
 //BA.debugLineNum = 3735688;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735689;
 //BA.debugLineNum = 3735689;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735690;
 //BA.debugLineNum = 3735690;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735691;
 //BA.debugLineNum = 3735691;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735692;
 //BA.debugLineNum = 3735692;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735694;
 //BA.debugLineNum = 3735694;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735696;
 //BA.debugLineNum = 3735696;BA.debugLine="Else If query.Contains(\"aladdin\") Then";
if (_query.contains("aladdin")) { 
RDebugUtils.currentLine=3735698;
 //BA.debugLineNum = 3735698;BA.debugLine="Drama1.Text = \"Aladdin\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Aladdin"));
RDebugUtils.currentLine=3735699;
 //BA.debugLineNum = 3735699;BA.debugLine="Starter1.Text = \"Starring: Mena Massoud, Naomi S";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Mena Massoud, Naomi Scott, Will Smith"));
RDebugUtils.currentLine=3735700;
 //BA.debugLineNum = 3735700;BA.debugLine="Year1.Text = \"(2019)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=3735701;
 //BA.debugLineNum = 3735701;BA.debugLine="OverView1.Text = \"Aladdin, a kind-hearted street";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."));
RDebugUtils.currentLine=3735702;
 //BA.debugLineNum = 3735702;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735703;
 //BA.debugLineNum = 3735703;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=3735705;
 //BA.debugLineNum = 3735705;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735706;
 //BA.debugLineNum = 3735706;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735707;
 //BA.debugLineNum = 3735707;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735708;
 //BA.debugLineNum = 3735708;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735709;
 //BA.debugLineNum = 3735709;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735711;
 //BA.debugLineNum = 3735711;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735713;
 //BA.debugLineNum = 3735713;BA.debugLine="Else If query.Contains(\"after earth\") Or query.Co";
if (_query.contains("after earth") || _query.contains("after") || _query.contains("earth")) { 
RDebugUtils.currentLine=3735715;
 //BA.debugLineNum = 3735715;BA.debugLine="Drama1.Text = \"After Earth\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("After Earth"));
RDebugUtils.currentLine=3735716;
 //BA.debugLineNum = 3735716;BA.debugLine="Starter1.Text = \"Starring: Will Smith, Jaden Smi";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Will Smith, Jaden Smith, Sigourney Weaver"));
RDebugUtils.currentLine=3735717;
 //BA.debugLineNum = 3735717;BA.debugLine="Year1.Text = \"(2013)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=3735718;
 //BA.debugLineNum = 3735718;BA.debugLine="OverView1.Text = \"Set in the future, After Earth";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."));
RDebugUtils.currentLine=3735719;
 //BA.debugLineNum = 3735719;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735720;
 //BA.debugLineNum = 3735720;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=3735722;
 //BA.debugLineNum = 3735722;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735723;
 //BA.debugLineNum = 3735723;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735724;
 //BA.debugLineNum = 3735724;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735725;
 //BA.debugLineNum = 3735725;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735726;
 //BA.debugLineNum = 3735726;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735728;
 //BA.debugLineNum = 3735728;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735730;
 //BA.debugLineNum = 3735730;BA.debugLine="Else If query.Contains(\"orlando bloom\") Or query.";
if (_query.contains("orlando bloom") || _query.contains("orlando") || _query.contains("bloom")) { 
RDebugUtils.currentLine=3735731;
 //BA.debugLineNum = 3735731;BA.debugLine="Drama1.Text = \"The Fellowship of the Ring\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Fellowship of the Ring"));
RDebugUtils.currentLine=3735732;
 //BA.debugLineNum = 3735732;BA.debugLine="Starter1.Text = \"Starring: Elijah Wood, Ian McKe";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Elijah Wood, Ian McKellen, Orlando Bloom"));
RDebugUtils.currentLine=3735733;
 //BA.debugLineNum = 3735733;BA.debugLine="Year1.Text = \"(2001)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2001)"));
RDebugUtils.currentLine=3735734;
 //BA.debugLineNum = 3735734;BA.debugLine="OverView1.Text = \"The future of civilization res";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"));
RDebugUtils.currentLine=3735735;
 //BA.debugLineNum = 3735735;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735736;
 //BA.debugLineNum = 3735736;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=3735738;
 //BA.debugLineNum = 3735738;BA.debugLine="Drama2.Text = \"The Curse of the Black Pearl\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("The Curse of the Black Pearl"));
RDebugUtils.currentLine=3735739;
 //BA.debugLineNum = 3735739;BA.debugLine="Starter2.Text = \"Starring: Johnny Depp, Orlando";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Orlando Bloom, Keira Knightley, Geoffrey Rush"));
RDebugUtils.currentLine=3735740;
 //BA.debugLineNum = 3735740;BA.debugLine="Year2.Text = \"(2003)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2003)"));
RDebugUtils.currentLine=3735741;
 //BA.debugLineNum = 3735741;BA.debugLine="OverView2.Text = \"Four siblings, evacuated from";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=3735742;
 //BA.debugLineNum = 3735742;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735743;
 //BA.debugLineNum = 3735743;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=3735745;
 //BA.debugLineNum = 3735745;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735746;
 //BA.debugLineNum = 3735746;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735747;
 //BA.debugLineNum = 3735747;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735749;
 //BA.debugLineNum = 3735749;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735751;
 //BA.debugLineNum = 3735751;BA.debugLine="Else If query.Contains(\"johnny depp\") Or query.Co";
if (_query.contains("johnny depp") || _query.contains("johnny") || _query.contains("depp")) { 
RDebugUtils.currentLine=3735752;
 //BA.debugLineNum = 3735752;BA.debugLine="Drama1.Text = \"Alice in Wonderland\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Alice in Wonderland"));
RDebugUtils.currentLine=3735753;
 //BA.debugLineNum = 3735753;BA.debugLine="Starter1.Text = \"Starring: Mia Wasikowska, Johnn";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"));
RDebugUtils.currentLine=3735754;
 //BA.debugLineNum = 3735754;BA.debugLine="Year1.Text = \"(2010)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2010)"));
RDebugUtils.currentLine=3735755;
 //BA.debugLineNum = 3735755;BA.debugLine="OverView1.Text = \"Alice, now a teenager, returns";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."));
RDebugUtils.currentLine=3735756;
 //BA.debugLineNum = 3735756;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735757;
 //BA.debugLineNum = 3735757;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=3735759;
 //BA.debugLineNum = 3735759;BA.debugLine="Drama2.Text = \"Charlie and the Chocolate Factory";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Charlie and the Chocolate Factory"));
RDebugUtils.currentLine=3735760;
 //BA.debugLineNum = 3735760;BA.debugLine="Starter2.Text = \"Starring: Johnny Depp, Freddie";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"));
RDebugUtils.currentLine=3735761;
 //BA.debugLineNum = 3735761;BA.debugLine="Year2.Text = \"(2005)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3735762;
 //BA.debugLineNum = 3735762;BA.debugLine="OverView2.Text = \"Charlie Bucket, a humble boy f";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."));
RDebugUtils.currentLine=3735763;
 //BA.debugLineNum = 3735763;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735764;
 //BA.debugLineNum = 3735764;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=3735766;
 //BA.debugLineNum = 3735766;BA.debugLine="Drama3.Text = \"The Curse of the Black Pearl\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Curse of the Black Pearl"));
RDebugUtils.currentLine=3735767;
 //BA.debugLineNum = 3735767;BA.debugLine="Starter3.Text = \"Starring: Johnny Depp, Orlando";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Orlando Bloom, Keira Knightley, Geoffrey Rush"));
RDebugUtils.currentLine=3735768;
 //BA.debugLineNum = 3735768;BA.debugLine="Year3.Text = \"(2003)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2003)"));
RDebugUtils.currentLine=3735769;
 //BA.debugLineNum = 3735769;BA.debugLine="OverView3.Text = \"Four siblings, evacuated from";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=3735770;
 //BA.debugLineNum = 3735770;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735771;
 //BA.debugLineNum = 3735771;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=3735773;
 //BA.debugLineNum = 3735773;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735774;
 //BA.debugLineNum = 3735774;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735775;
 //BA.debugLineNum = 3735775;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735777;
 //BA.debugLineNum = 3735777;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735779;
 //BA.debugLineNum = 3735779;BA.debugLine="Else If query.Contains(\"helena bonham carter\") Or";
if (_query.contains("helena bonham carter") || _query.contains("helena") || _query.contains("bonham") || _query.contains("carter") || _query.contains("helena bonham")) { 
RDebugUtils.currentLine=3735780;
 //BA.debugLineNum = 3735780;BA.debugLine="Drama1.Text = \"Charlie and the Chocolate Factory";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Charlie and the Chocolate Factory"));
RDebugUtils.currentLine=3735781;
 //BA.debugLineNum = 3735781;BA.debugLine="Starter1.Text = \"Starring: Johnny Depp, Freddie";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"));
RDebugUtils.currentLine=3735782;
 //BA.debugLineNum = 3735782;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3735783;
 //BA.debugLineNum = 3735783;BA.debugLine="OverView1.Text = \"Charlie Bucket, a humble boy f";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."));
RDebugUtils.currentLine=3735784;
 //BA.debugLineNum = 3735784;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735785;
 //BA.debugLineNum = 3735785;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=3735787;
 //BA.debugLineNum = 3735787;BA.debugLine="Drama2.Text = \"Alice in Wonderland\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Alice in Wonderland"));
RDebugUtils.currentLine=3735788;
 //BA.debugLineNum = 3735788;BA.debugLine="Starter2.Text = \"Starring: Mia Wasikowska, Johnn";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"));
RDebugUtils.currentLine=3735789;
 //BA.debugLineNum = 3735789;BA.debugLine="Year2.Text = \"(2010)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2010)"));
RDebugUtils.currentLine=3735790;
 //BA.debugLineNum = 3735790;BA.debugLine="OverView2.Text = \"Alice, now a teenager, returns";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."));
RDebugUtils.currentLine=3735791;
 //BA.debugLineNum = 3735791;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735792;
 //BA.debugLineNum = 3735792;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=3735794;
 //BA.debugLineNum = 3735794;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735795;
 //BA.debugLineNum = 3735795;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735796;
 //BA.debugLineNum = 3735796;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735797;
 //BA.debugLineNum = 3735797;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735799;
 //BA.debugLineNum = 3735799;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735801;
 //BA.debugLineNum = 3735801;BA.debugLine="Else If query.Contains(\"will smith\") Or query.Con";
if (_query.contains("will smith") || _query.contains("will") || _query.contains("smith")) { 
RDebugUtils.currentLine=3735802;
 //BA.debugLineNum = 3735802;BA.debugLine="Drama1.Text = \"Aladdin\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Aladdin"));
RDebugUtils.currentLine=3735803;
 //BA.debugLineNum = 3735803;BA.debugLine="Starter1.Text = \"Starring: Mena Massoud, Naomi S";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Mena Massoud, Naomi Scott, Will Smith"));
RDebugUtils.currentLine=3735804;
 //BA.debugLineNum = 3735804;BA.debugLine="Year1.Text = \"(2019)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=3735805;
 //BA.debugLineNum = 3735805;BA.debugLine="OverView1.Text = \"Aladdin, a kind-hearted street";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."));
RDebugUtils.currentLine=3735806;
 //BA.debugLineNum = 3735806;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735807;
 //BA.debugLineNum = 3735807;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=3735809;
 //BA.debugLineNum = 3735809;BA.debugLine="Drama1.Text = \"After Earth\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("After Earth"));
RDebugUtils.currentLine=3735810;
 //BA.debugLineNum = 3735810;BA.debugLine="Starter1.Text = \"Starring: Will Smith, Jaden Smi";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Will Smith, Jaden Smith, Sigourney Weaver"));
RDebugUtils.currentLine=3735811;
 //BA.debugLineNum = 3735811;BA.debugLine="Year1.Text = \"(2013)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=3735812;
 //BA.debugLineNum = 3735812;BA.debugLine="OverView1.Text = \"Set in the future, After Earth";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."));
RDebugUtils.currentLine=3735813;
 //BA.debugLineNum = 3735813;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735814;
 //BA.debugLineNum = 3735814;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=3735816;
 //BA.debugLineNum = 3735816;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735817;
 //BA.debugLineNum = 3735817;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735819;
 //BA.debugLineNum = 3735819;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735821;
 //BA.debugLineNum = 3735821;BA.debugLine="Else If query.Contains(\"william moseley\") Or quer";
if (_query.contains("william moseley") || _query.contains("william") || _query.contains("moseley")) { 
RDebugUtils.currentLine=3735823;
 //BA.debugLineNum = 3735823;BA.debugLine="Drama1.Text = \"The Chronicles of Narnia\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Chronicles of Narnia"));
RDebugUtils.currentLine=3735824;
 //BA.debugLineNum = 3735824;BA.debugLine="Starter1.Text = \"Starring: Georgie Henley, Skand";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Georgie Henley, Skandar Keynes, William Moseley"));
RDebugUtils.currentLine=3735825;
 //BA.debugLineNum = 3735825;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3735826;
 //BA.debugLineNum = 3735826;BA.debugLine="OverView1.Text = \"Four siblings, evacuated from";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=3735827;
 //BA.debugLineNum = 3735827;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735828;
 //BA.debugLineNum = 3735828;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=3735830;
 //BA.debugLineNum = 3735830;BA.debugLine="Drama2.Text = \"The Little Mermaid\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("The Little Mermaid"));
RDebugUtils.currentLine=3735831;
 //BA.debugLineNum = 3735831;BA.debugLine="Starter2.Text = \"Starring: Poppy Drayton, Willia";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Poppy Drayton, William Moseley, Shirley MacLaine"));
RDebugUtils.currentLine=3735832;
 //BA.debugLineNum = 3735832;BA.debugLine="Year2.Text = \"(2018)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2018)"));
RDebugUtils.currentLine=3735833;
 //BA.debugLineNum = 3735833;BA.debugLine="OverView2.Text = \"A young reporter and his niece";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("A young reporter and his niece discover a real-life mermaid being held captive by a shady circus owner. As they befriend the mermaid, they embark on a magical adventure to save her and help her return to the sea."));
RDebugUtils.currentLine=3735834;
 //BA.debugLineNum = 3735834;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735835;
 //BA.debugLineNum = 3735835;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=3735837;
 //BA.debugLineNum = 3735837;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735838;
 //BA.debugLineNum = 3735838;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735839;
 //BA.debugLineNum = 3735839;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735840;
 //BA.debugLineNum = 3735840;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735842;
 //BA.debugLineNum = 3735842;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735844;
 //BA.debugLineNum = 3735844;BA.debugLine="Else If query.Contains(\"natalie portman\") Or quer";
if (_query.contains("natalie portman") || _query.contains("natalie") || _query.contains("portman")) { 
RDebugUtils.currentLine=3735846;
 //BA.debugLineNum = 3735846;BA.debugLine="Drama1.Text = \"V for Vendetta\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("V for Vendetta"));
RDebugUtils.currentLine=3735847;
 //BA.debugLineNum = 3735847;BA.debugLine="Starter1.Text = \"Starring: Hugo Weaving, Natalie";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugo Weaving, Natalie Portman, Stephen Rea"));
RDebugUtils.currentLine=3735848;
 //BA.debugLineNum = 3735848;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3735849;
 //BA.debugLineNum = 3735849;BA.debugLine="OverView1.Text = \"In a totalitarian future Brita";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a totalitarian future Britain, a masked revolutionary known as \"V\" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."));
RDebugUtils.currentLine=3735850;
 //BA.debugLineNum = 3735850;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735851;
 //BA.debugLineNum = 3735851;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=3735853;
 //BA.debugLineNum = 3735853;BA.debugLine="Drama2.Text = \"Thor: The Dark World\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Thor: The Dark World"));
RDebugUtils.currentLine=3735854;
 //BA.debugLineNum = 3735854;BA.debugLine="Starter2.Text = \"Starring: Chris Hemsworth, Nata";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Chris Hemsworth, Natalie Portman, Tom Hiddleston"));
RDebugUtils.currentLine=3735855;
 //BA.debugLineNum = 3735855;BA.debugLine="Year2.Text = \"(2013)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=3735856;
 //BA.debugLineNum = 3735856;BA.debugLine="OverView2.Text = \"Thor must team up with his tre";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Thor must team up with his treacherous brother Loki to stop the Dark Elves, led by the vengeful Malekith, who seeks to plunge the universe into darkness using a powerful ancient force known as the Aether."));
RDebugUtils.currentLine=3735857;
 //BA.debugLineNum = 3735857;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735858;
 //BA.debugLineNum = 3735858;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=3735860;
 //BA.debugLineNum = 3735860;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735861;
 //BA.debugLineNum = 3735861;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735862;
 //BA.debugLineNum = 3735862;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735863;
 //BA.debugLineNum = 3735863;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735865;
 //BA.debugLineNum = 3735865;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735868;
 //BA.debugLineNum = 3735868;BA.debugLine="Else If query.Contains(\"elijah wood\") Or query.Co";
if (_query.contains("elijah wood") || _query.contains("elijah") || _query.contains("wood") || _query.contains("ian mckellen") || _query.contains("ian") || _query.contains("mckellen")) { 
RDebugUtils.currentLine=3735869;
 //BA.debugLineNum = 3735869;BA.debugLine="Drama1.Text = \"The Fellowship of the Ring\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Fellowship of the Ring"));
RDebugUtils.currentLine=3735870;
 //BA.debugLineNum = 3735870;BA.debugLine="Starter1.Text = \"Starring: Elijah Wood, Ian McKe";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Elijah Wood, Ian McKellen, Orlando Bloom"));
RDebugUtils.currentLine=3735871;
 //BA.debugLineNum = 3735871;BA.debugLine="Year1.Text = \"(2001)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2001)"));
RDebugUtils.currentLine=3735872;
 //BA.debugLineNum = 3735872;BA.debugLine="OverView1.Text = \"The future of civilization res";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"));
RDebugUtils.currentLine=3735873;
 //BA.debugLineNum = 3735873;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735874;
 //BA.debugLineNum = 3735874;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=3735876;
 //BA.debugLineNum = 3735876;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735877;
 //BA.debugLineNum = 3735877;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735878;
 //BA.debugLineNum = 3735878;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735879;
 //BA.debugLineNum = 3735879;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735880;
 //BA.debugLineNum = 3735880;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735882;
 //BA.debugLineNum = 3735882;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735883;
 //BA.debugLineNum = 3735883;BA.debugLine="Else If query.Contains(\"freddie highmore\") Or que";
if (_query.contains("freddie highmore") || _query.contains("freddie") || _query.contains("highmore") || _query.contains("david kelly") || _query.contains("david") || _query.contains("kelly")) { 
RDebugUtils.currentLine=3735884;
 //BA.debugLineNum = 3735884;BA.debugLine="Drama1.Text = \"Charlie and the Chocolate Factory";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Charlie and the Chocolate Factory"));
RDebugUtils.currentLine=3735885;
 //BA.debugLineNum = 3735885;BA.debugLine="Starter1.Text = \"Starring: Johnny Depp, Freddie";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"));
RDebugUtils.currentLine=3735886;
 //BA.debugLineNum = 3735886;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3735887;
 //BA.debugLineNum = 3735887;BA.debugLine="OverView1.Text = \"Charlie Bucket, a humble boy f";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."));
RDebugUtils.currentLine=3735888;
 //BA.debugLineNum = 3735888;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735889;
 //BA.debugLineNum = 3735889;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=3735891;
 //BA.debugLineNum = 3735891;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735892;
 //BA.debugLineNum = 3735892;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735893;
 //BA.debugLineNum = 3735893;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735894;
 //BA.debugLineNum = 3735894;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735895;
 //BA.debugLineNum = 3735895;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735897;
 //BA.debugLineNum = 3735897;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735898;
 //BA.debugLineNum = 3735898;BA.debugLine="Else if query.Contains(\"mia wasikowska\") Or query";
if (_query.contains("mia wasikowska") || _query.contains("mia") || _query.contains("wasikowska") || _query.contains("anne hathaway") || _query.contains("anne") || _query.contains("hathaway")) { 
RDebugUtils.currentLine=3735899;
 //BA.debugLineNum = 3735899;BA.debugLine="Drama1.Text = \"Alice in Wonderland\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Alice in Wonderland"));
RDebugUtils.currentLine=3735900;
 //BA.debugLineNum = 3735900;BA.debugLine="Starter1.Text = \"Starring: Mia Wasikowska, Johnn";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"));
RDebugUtils.currentLine=3735901;
 //BA.debugLineNum = 3735901;BA.debugLine="Year1.Text = \"(2010)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2010)"));
RDebugUtils.currentLine=3735902;
 //BA.debugLineNum = 3735902;BA.debugLine="OverView1.Text = \"Alice, now a teenager, returns";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."));
RDebugUtils.currentLine=3735903;
 //BA.debugLineNum = 3735903;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735904;
 //BA.debugLineNum = 3735904;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=3735906;
 //BA.debugLineNum = 3735906;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735907;
 //BA.debugLineNum = 3735907;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735908;
 //BA.debugLineNum = 3735908;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735909;
 //BA.debugLineNum = 3735909;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735910;
 //BA.debugLineNum = 3735910;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735912;
 //BA.debugLineNum = 3735912;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735913;
 //BA.debugLineNum = 3735913;BA.debugLine="Else if query.Contains(\"daniel radcliffe\") Or que";
if (_query.contains("daniel radcliffe") || _query.contains("daniel") || _query.contains("radcliffe") || _query.contains("rupert grint") || _query.contains("rupert") || _query.contains("grint") || _query.contains("emma watson") || _query.contains("emma") || _query.contains("watson")) { 
RDebugUtils.currentLine=3735914;
 //BA.debugLineNum = 3735914;BA.debugLine="Drama1.Text = \"Harry Potter and the Philosopher'";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Harry Potter and the Philosopher's Stone"));
RDebugUtils.currentLine=3735915;
 //BA.debugLineNum = 3735915;BA.debugLine="Starter1.Text = \"Starring: Daniel Radcliffe, Rup";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Daniel Radcliffe, Rupert Grint, Emma Watson"));
RDebugUtils.currentLine=3735916;
 //BA.debugLineNum = 3735916;BA.debugLine="Year1.Text = \"(2003)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2003)"));
RDebugUtils.currentLine=3735917;
 //BA.debugLineNum = 3735917;BA.debugLine="OverView1.Text = \"Captain Jack Sparrow must resc";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Captain Jack Sparrow must rescue Elizabeth Swann, who has been kidnapped by cursed pirates led by Captain Barbossa. Alongside Will Turner, Jack battles ghostly pirates, seeking both freedom and revenge."));
RDebugUtils.currentLine=3735918;
 //BA.debugLineNum = 3735918;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735919;
 //BA.debugLineNum = 3735919;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=3735921;
 //BA.debugLineNum = 3735921;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735922;
 //BA.debugLineNum = 3735922;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735923;
 //BA.debugLineNum = 3735923;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735924;
 //BA.debugLineNum = 3735924;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735925;
 //BA.debugLineNum = 3735925;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735927;
 //BA.debugLineNum = 3735927;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735928;
 //BA.debugLineNum = 3735928;BA.debugLine="Else if query.Contains(\"keira knightley\") Or quer";
if (_query.contains("keira knightley") || _query.contains("keira") || _query.contains("knightley")) { 
RDebugUtils.currentLine=3735929;
 //BA.debugLineNum = 3735929;BA.debugLine="Drama1.Text = \"The Curse of the Black Pearl\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Curse of the Black Pearl"));
RDebugUtils.currentLine=3735930;
 //BA.debugLineNum = 3735930;BA.debugLine="Starter1.Text = \"Starring: Johnny Depp, Orlando";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Orlando Bloom, Keira Knightley"));
RDebugUtils.currentLine=3735931;
 //BA.debugLineNum = 3735931;BA.debugLine="Year1.Text = \"(2003)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2003)"));
RDebugUtils.currentLine=3735932;
 //BA.debugLineNum = 3735932;BA.debugLine="OverView1.Text = \"Four siblings, evacuated from";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=3735933;
 //BA.debugLineNum = 3735933;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735934;
 //BA.debugLineNum = 3735934;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=3735936;
 //BA.debugLineNum = 3735936;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735937;
 //BA.debugLineNum = 3735937;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735938;
 //BA.debugLineNum = 3735938;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735939;
 //BA.debugLineNum = 3735939;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735940;
 //BA.debugLineNum = 3735940;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735942;
 //BA.debugLineNum = 3735942;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735944;
 //BA.debugLineNum = 3735944;BA.debugLine="Else if query.Contains(\"skandar keynes\") Or query";
if (_query.contains("skandar keynes") || _query.contains("skandar") || _query.contains("keynes") || _query.contains("william moseley") || _query.contains("william") || _query.contains("moseley")) { 
RDebugUtils.currentLine=3735945;
 //BA.debugLineNum = 3735945;BA.debugLine="Drama6.Text = \"The Chronicles of Narnia\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("The Chronicles of Narnia"));
RDebugUtils.currentLine=3735946;
 //BA.debugLineNum = 3735946;BA.debugLine="Starter6.Text = \"Starring: Georgie Henley, Skand";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Georgie Henley, Skandar Keynes, William Moseley"));
RDebugUtils.currentLine=3735947;
 //BA.debugLineNum = 3735947;BA.debugLine="Year6.Text = \"(2005)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3735948;
 //BA.debugLineNum = 3735948;BA.debugLine="OverView6.Text = \"Four siblings, evacuated from";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=3735949;
 //BA.debugLineNum = 3735949;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
mostCurrent._dramaimage6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735950;
 //BA.debugLineNum = 3735950;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage6.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=3735952;
 //BA.debugLineNum = 3735952;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735953;
 //BA.debugLineNum = 3735953;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735954;
 //BA.debugLineNum = 3735954;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735955;
 //BA.debugLineNum = 3735955;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735956;
 //BA.debugLineNum = 3735956;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735958;
 //BA.debugLineNum = 3735958;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735960;
 //BA.debugLineNum = 3735960;BA.debugLine="Else if query.Contains(\"benedict cumberbatch\") Or";
if (_query.contains("benedict cumberbatch") || _query.contains("benedict") || _query.contains("cumberbatch") || _query.contains("chiwetel ejiofor") || _query.contains("chiwetel") || _query.contains("ejiofor")) { 
RDebugUtils.currentLine=3735961;
 //BA.debugLineNum = 3735961;BA.debugLine="Drama1.Text = \"Doctor Strange\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Doctor Strange"));
RDebugUtils.currentLine=3735962;
 //BA.debugLineNum = 3735962;BA.debugLine="Starter1.Text = \"Starring: Benedict Cumberbatch,";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Benedict Cumberbatch, Chiwetel Ejiofor"));
RDebugUtils.currentLine=3735963;
 //BA.debugLineNum = 3735963;BA.debugLine="Year1.Text = \"(2016)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=3735964;
 //BA.debugLineNum = 3735964;BA.debugLine="OverView1.Text = \"After a life-changing accident";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After a life-changing accident ends his career, arrogant surgeon Stephen Strange seeks healing in Nepal, where he learns the mystic arts and must protect the world from dark magical forces beyond reality."));
RDebugUtils.currentLine=3735965;
 //BA.debugLineNum = 3735965;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735966;
 //BA.debugLineNum = 3735966;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=3735968;
 //BA.debugLineNum = 3735968;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735969;
 //BA.debugLineNum = 3735969;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735970;
 //BA.debugLineNum = 3735970;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735971;
 //BA.debugLineNum = 3735971;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735972;
 //BA.debugLineNum = 3735972;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735974;
 //BA.debugLineNum = 3735974;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735976;
 //BA.debugLineNum = 3735976;BA.debugLine="Else if query.Contains(\"hugo weaving\") Or query.C";
if (_query.contains("hugo weaving") || _query.contains("hugo") || _query.contains("weaving") || _query.contains("stephen rea") || _query.contains("stephen") || _query.contains("rea")) { 
RDebugUtils.currentLine=3735977;
 //BA.debugLineNum = 3735977;BA.debugLine="Drama1.Text = \"V for Vendetta\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("V for Vendetta"));
RDebugUtils.currentLine=3735978;
 //BA.debugLineNum = 3735978;BA.debugLine="Starter1.Text = \"Starring: Hugo Weaving, Natalie";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugo Weaving, Natalie Portman, Stephen Rea"));
RDebugUtils.currentLine=3735979;
 //BA.debugLineNum = 3735979;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3735980;
 //BA.debugLineNum = 3735980;BA.debugLine="OverView1.Text = \"In a totalitarian future Brita";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a totalitarian future Britain, a masked revolutionary known as \"V\" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."));
RDebugUtils.currentLine=3735981;
 //BA.debugLineNum = 3735981;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735982;
 //BA.debugLineNum = 3735982;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=3735984;
 //BA.debugLineNum = 3735984;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735985;
 //BA.debugLineNum = 3735985;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735986;
 //BA.debugLineNum = 3735986;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735987;
 //BA.debugLineNum = 3735987;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735988;
 //BA.debugLineNum = 3735988;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3735990;
 //BA.debugLineNum = 3735990;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3735993;
 //BA.debugLineNum = 3735993;BA.debugLine="Else if query.Contains(\"mena massoud\") Or query.C";
if (_query.contains("mena massoud") || _query.contains("mena") || _query.contains("massoud") || _query.contains("naomi scott") || _query.contains("naomi") || _query.contains("scott")) { 
RDebugUtils.currentLine=3735994;
 //BA.debugLineNum = 3735994;BA.debugLine="Drama1.Text = \"Aladdin\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Aladdin"));
RDebugUtils.currentLine=3735995;
 //BA.debugLineNum = 3735995;BA.debugLine="Starter1.Text = \"Starring: Mena Massoud, Naomi S";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Mena Massoud, Naomi Scott, Will Smith"));
RDebugUtils.currentLine=3735996;
 //BA.debugLineNum = 3735996;BA.debugLine="Year1.Text = \"(2019)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=3735997;
 //BA.debugLineNum = 3735997;BA.debugLine="OverView1.Text = \"Aladdin, a kind-hearted street";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."));
RDebugUtils.currentLine=3735998;
 //BA.debugLineNum = 3735998;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3735999;
 //BA.debugLineNum = 3735999;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=3736001;
 //BA.debugLineNum = 3736001;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3736002;
 //BA.debugLineNum = 3736002;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3736003;
 //BA.debugLineNum = 3736003;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3736004;
 //BA.debugLineNum = 3736004;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3736005;
 //BA.debugLineNum = 3736005;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3736007;
 //BA.debugLineNum = 3736007;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=3736009;
 //BA.debugLineNum = 3736009;BA.debugLine="Else if query.Contains(\"jaden smith\") Or query.Co";
if (_query.contains("jaden smith") || _query.contains("jaden") || _query.contains("smith") || _query.contains("sigourney weave") || _query.contains("sigourney") || _query.contains("weave")) { 
RDebugUtils.currentLine=3736010;
 //BA.debugLineNum = 3736010;BA.debugLine="Drama1.Text = \"After Earth\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("After Earth"));
RDebugUtils.currentLine=3736011;
 //BA.debugLineNum = 3736011;BA.debugLine="Starter1.Text = \"Starring: Will Smith, Jaden Smi";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Will Smith, Jaden Smith, Sigourney Weaver"));
RDebugUtils.currentLine=3736012;
 //BA.debugLineNum = 3736012;BA.debugLine="Year1.Text = \"(2013)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=3736013;
 //BA.debugLineNum = 3736013;BA.debugLine="OverView1.Text = \"Set in the future, After Earth";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."));
RDebugUtils.currentLine=3736014;
 //BA.debugLineNum = 3736014;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3736015;
 //BA.debugLineNum = 3736015;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=3736017;
 //BA.debugLineNum = 3736017;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3736018;
 //BA.debugLineNum = 3736018;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3736019;
 //BA.debugLineNum = 3736019;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3736020;
 //BA.debugLineNum = 3736020;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3736021;
 //BA.debugLineNum = 3736021;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3736023;
 //BA.debugLineNum = 3736023;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else {
RDebugUtils.currentLine=3736027;
 //BA.debugLineNum = 3736027;BA.debugLine="MsgboxAsync(\"No results found for\" & \" \"\"\" & Use";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("No results found for"+" \""+_userinput+"\""),BA.ObjectToCharSequence(""),processBA);
 }}}}}}}}}}}}}}}}}}}}}}}}}}
;
RDebugUtils.currentLine=3736031;
 //BA.debugLineNum = 3736031;BA.debugLine="p.Width = 100%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=3736032;
 //BA.debugLineNum = 3736032;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=3736034;
 //BA.debugLineNum = 3736034;BA.debugLine="End Sub";
return "";
}
public static String  _searchengine_textchanged(String _old,String _new) throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchengine_textchanged", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchengine_textchanged", new Object[] {_old,_new}));}
String _query = "";
RDebugUtils.currentLine=3801088;
 //BA.debugLineNum = 3801088;BA.debugLine="Sub SearchEngine_TextChanged (Old As String, New A";
RDebugUtils.currentLine=3801089;
 //BA.debugLineNum = 3801089;BA.debugLine="Dim query As String = New.ToLowerCase.Trim";
_query = _new.toLowerCase().trim();
RDebugUtils.currentLine=3801091;
 //BA.debugLineNum = 3801091;BA.debugLine="If query = \"\" Then";
if ((_query).equals("")) { 
RDebugUtils.currentLine=3801094;
 //BA.debugLineNum = 3801094;BA.debugLine="p.Height = 210%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=3801095;
 //BA.debugLineNum = 3801095;BA.debugLine="p.Width = 200%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA));
RDebugUtils.currentLine=3801096;
 //BA.debugLineNum = 3801096;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=3801098;
 //BA.debugLineNum = 3801098;BA.debugLine="PanelMovie1.Visible = True";
mostCurrent._panelmovie1.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3801099;
 //BA.debugLineNum = 3801099;BA.debugLine="PanelMovie2.Visible = True";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3801100;
 //BA.debugLineNum = 3801100;BA.debugLine="PanelMovie3.Visible = True";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3801101;
 //BA.debugLineNum = 3801101;BA.debugLine="PanelMovie4.Visible = True";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3801102;
 //BA.debugLineNum = 3801102;BA.debugLine="PanelMovie5.Visible = True";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3801103;
 //BA.debugLineNum = 3801103;BA.debugLine="PanelMovie6.Visible = True";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3801104;
 //BA.debugLineNum = 3801104;BA.debugLine="PanelMovie7.Visible = True";
mostCurrent._panelmovie7.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3801105;
 //BA.debugLineNum = 3801105;BA.debugLine="PanelMovie8.Visible = True";
mostCurrent._panelmovie8.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3801106;
 //BA.debugLineNum = 3801106;BA.debugLine="PanelMovie9.Visible = True";
mostCurrent._panelmovie9.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3801107;
 //BA.debugLineNum = 3801107;BA.debugLine="PanelMovie10.Visible = True";
mostCurrent._panelmovie10.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3801111;
 //BA.debugLineNum = 3801111;BA.debugLine="Drama1.Text = \"The Fellowship of the Ring\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Fellowship of the Ring"));
RDebugUtils.currentLine=3801112;
 //BA.debugLineNum = 3801112;BA.debugLine="Starter1.Text = \"Starring: Elijah Wood, Ian McKe";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Elijah Wood, Ian McKellen, Orlando Bloom"));
RDebugUtils.currentLine=3801113;
 //BA.debugLineNum = 3801113;BA.debugLine="Year1.Text = \"(2001)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2001)"));
RDebugUtils.currentLine=3801114;
 //BA.debugLineNum = 3801114;BA.debugLine="OverView1.Text = \"The future of civilization res";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"));
RDebugUtils.currentLine=3801115;
 //BA.debugLineNum = 3801115;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3801116;
 //BA.debugLineNum = 3801116;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=3801118;
 //BA.debugLineNum = 3801118;BA.debugLine="Drama2.Text = \"Charlie and the Chocolate Factory";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Charlie and the Chocolate Factory"));
RDebugUtils.currentLine=3801119;
 //BA.debugLineNum = 3801119;BA.debugLine="Starter2.Text = \"Starring: Johnny Depp, Freddie";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"));
RDebugUtils.currentLine=3801120;
 //BA.debugLineNum = 3801120;BA.debugLine="Year2.Text = \"(2005)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3801121;
 //BA.debugLineNum = 3801121;BA.debugLine="OverView2.Text = \"Charlie Bucket, a humble boy f";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."));
RDebugUtils.currentLine=3801122;
 //BA.debugLineNum = 3801122;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3801123;
 //BA.debugLineNum = 3801123;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=3801125;
 //BA.debugLineNum = 3801125;BA.debugLine="Drama3.Text = \"Alice in Wonderland\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("Alice in Wonderland"));
RDebugUtils.currentLine=3801126;
 //BA.debugLineNum = 3801126;BA.debugLine="Starter3.Text = \"Starring: Mia Wasikowska, Johnn";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"));
RDebugUtils.currentLine=3801127;
 //BA.debugLineNum = 3801127;BA.debugLine="Year3.Text = \"(2010)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2010)"));
RDebugUtils.currentLine=3801128;
 //BA.debugLineNum = 3801128;BA.debugLine="OverView3.Text = \"Alice, now a teenager, returns";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."));
RDebugUtils.currentLine=3801129;
 //BA.debugLineNum = 3801129;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3801130;
 //BA.debugLineNum = 3801130;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=3801132;
 //BA.debugLineNum = 3801132;BA.debugLine="Drama4.Text = \"Harry Potter and the Philosopher'";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Harry Potter and the Philosopher's Stone"));
RDebugUtils.currentLine=3801133;
 //BA.debugLineNum = 3801133;BA.debugLine="Starter4.Text = \"Starring: Daniel Radcliffe, Rup";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Daniel Radcliffe, Rupert Grint, Emma Watson"));
RDebugUtils.currentLine=3801134;
 //BA.debugLineNum = 3801134;BA.debugLine="Year4.Text = \"(2003)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2003)"));
RDebugUtils.currentLine=3801135;
 //BA.debugLineNum = 3801135;BA.debugLine="OverView4.Text = \"Captain Jack Sparrow must resc";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("Captain Jack Sparrow must rescue Elizabeth Swann, who has been kidnapped by cursed pirates led by Captain Barbossa. Alongside Will Turner, Jack battles ghostly pirates, seeking both freedom and revenge."));
RDebugUtils.currentLine=3801136;
 //BA.debugLineNum = 3801136;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3801137;
 //BA.debugLineNum = 3801137;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=3801139;
 //BA.debugLineNum = 3801139;BA.debugLine="Drama5.Text = \"The Curse of the Black Pearl\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("The Curse of the Black Pearl"));
RDebugUtils.currentLine=3801140;
 //BA.debugLineNum = 3801140;BA.debugLine="Starter5.Text = \"Starring: Johnny Depp, Orlando";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Orlando Bloom, Keira Knightley"));
RDebugUtils.currentLine=3801141;
 //BA.debugLineNum = 3801141;BA.debugLine="Year5.Text = \"(2003)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(2003)"));
RDebugUtils.currentLine=3801142;
 //BA.debugLineNum = 3801142;BA.debugLine="OverView5.Text = \"Four siblings, evacuated from";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=3801143;
 //BA.debugLineNum = 3801143;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
mostCurrent._dramaimage5.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3801144;
 //BA.debugLineNum = 3801144;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage5.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=3801147;
 //BA.debugLineNum = 3801147;BA.debugLine="Drama6.Text = \"The Chronicles of Narnia\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("The Chronicles of Narnia"));
RDebugUtils.currentLine=3801148;
 //BA.debugLineNum = 3801148;BA.debugLine="Starter6.Text = \"Starring: Georgie Henley, Skand";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Georgie Henley, Skandar Keynes, William Moseley,"));
RDebugUtils.currentLine=3801149;
 //BA.debugLineNum = 3801149;BA.debugLine="Year6.Text = \"(2005)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3801150;
 //BA.debugLineNum = 3801150;BA.debugLine="OverView6.Text = \"Four siblings, evacuated from";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=3801151;
 //BA.debugLineNum = 3801151;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
mostCurrent._dramaimage6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3801152;
 //BA.debugLineNum = 3801152;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage6.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=3801154;
 //BA.debugLineNum = 3801154;BA.debugLine="Drama7.Text = \"Doctor Strange\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("Doctor Strange"));
RDebugUtils.currentLine=3801155;
 //BA.debugLineNum = 3801155;BA.debugLine="Starter7.Text = \"Starring: Benedict Cumberbatch,";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Benedict Cumberbatch, Chiwetel Ejiofor"));
RDebugUtils.currentLine=3801156;
 //BA.debugLineNum = 3801156;BA.debugLine="Year7.Text = \"(2016)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=3801157;
 //BA.debugLineNum = 3801157;BA.debugLine="OverView7.Text = \"After a life-changing accident";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("After a life-changing accident ends his career, arrogant surgeon Stephen Strange seeks healing in Nepal, where he learns the mystic arts and must protect the world from dark magical forces beyond reality."));
RDebugUtils.currentLine=3801158;
 //BA.debugLineNum = 3801158;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3801159;
 //BA.debugLineNum = 3801159;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=3801161;
 //BA.debugLineNum = 3801161;BA.debugLine="Drama8.Text = \"V for Vendetta\"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("V for Vendetta"));
RDebugUtils.currentLine=3801162;
 //BA.debugLineNum = 3801162;BA.debugLine="Starter8.Text = \"Starring: Hugo Weaving, Natalie";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Hugo Weaving, Natalie Portman, Stephen Rea, John Hurt"));
RDebugUtils.currentLine=3801163;
 //BA.debugLineNum = 3801163;BA.debugLine="Year8.Text = \"(2005)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=3801164;
 //BA.debugLineNum = 3801164;BA.debugLine="OverView8.Text = \"In a totalitarian future Brita";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("In a totalitarian future Britain, a masked revolutionary known as \"V\" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."));
RDebugUtils.currentLine=3801165;
 //BA.debugLineNum = 3801165;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
mostCurrent._dramaimage8.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3801166;
 //BA.debugLineNum = 3801166;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage8.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=3801168;
 //BA.debugLineNum = 3801168;BA.debugLine="Drama9.Text = \"Aladdin\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("Aladdin"));
RDebugUtils.currentLine=3801169;
 //BA.debugLineNum = 3801169;BA.debugLine="Starter9.Text = \"Starring: Mena Massoud, Naomi S";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Mena Massoud, Naomi Scott, Will Smith"));
RDebugUtils.currentLine=3801170;
 //BA.debugLineNum = 3801170;BA.debugLine="Year9.Text = \"(2019)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=3801171;
 //BA.debugLineNum = 3801171;BA.debugLine="OverView9.Text = \"Aladdin, a kind-hearted street";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."));
RDebugUtils.currentLine=3801172;
 //BA.debugLineNum = 3801172;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
mostCurrent._dramaimage9.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3801173;
 //BA.debugLineNum = 3801173;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage9.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=3801175;
 //BA.debugLineNum = 3801175;BA.debugLine="Drama10.Text = \"After Earth\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("After Earth"));
RDebugUtils.currentLine=3801176;
 //BA.debugLineNum = 3801176;BA.debugLine="Starter10.Text = \"Starring: Will Smith, Jaden Sm";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Will Smith, Jaden Smith, Sigourney Weaver"));
RDebugUtils.currentLine=3801177;
 //BA.debugLineNum = 3801177;BA.debugLine="Year10.Text = \"(2013)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=3801178;
 //BA.debugLineNum = 3801178;BA.debugLine="OverView10.Text = \"Set in the future, After Eart";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."));
RDebugUtils.currentLine=3801179;
 //BA.debugLineNum = 3801179;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=3801180;
 //BA.debugLineNum = 3801180;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
 };
RDebugUtils.currentLine=3801186;
 //BA.debugLineNum = 3801186;BA.debugLine="End Sub";
return "";
}
}