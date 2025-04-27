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
RDebugUtils.currentLine=3342336;
 //BA.debugLineNum = 3342336;BA.debugLine="Private Sub ActionPage_Click";
RDebugUtils.currentLine=3342337;
 //BA.debugLineNum = 3342337;BA.debugLine="StartActivity(Action)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._action.getObject()));
RDebugUtils.currentLine=3342338;
 //BA.debugLineNum = 3342338;BA.debugLine="End Sub";
return "";
}
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=2949120;
 //BA.debugLineNum = 2949120;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=2949121;
 //BA.debugLineNum = 2949121;BA.debugLine="Activity.LoadLayout(\"scifi\") ' Layout contains Sc";
mostCurrent._activity.LoadLayout("scifi",mostCurrent.activityBA);
RDebugUtils.currentLine=2949123;
 //BA.debugLineNum = 2949123;BA.debugLine="p.Initialize(\"\")";
mostCurrent._p.Initialize(mostCurrent.activityBA,"");
RDebugUtils.currentLine=2949124;
 //BA.debugLineNum = 2949124;BA.debugLine="p.LoadLayout(\"panelview\")";
mostCurrent._p.LoadLayout("panelview",mostCurrent.activityBA);
RDebugUtils.currentLine=2949131;
 //BA.debugLineNum = 2949131;BA.debugLine="Drama1.Text = \"The Fellowship of the Ring\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Fellowship of the Ring"));
RDebugUtils.currentLine=2949132;
 //BA.debugLineNum = 2949132;BA.debugLine="Starter1.Text = \"Starring: Elijah Wood, Ian McKel";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Elijah Wood, Ian McKellen, Orlando Bloom"));
RDebugUtils.currentLine=2949133;
 //BA.debugLineNum = 2949133;BA.debugLine="Year1.Text = \"(2001)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2001)"));
RDebugUtils.currentLine=2949134;
 //BA.debugLineNum = 2949134;BA.debugLine="OverView1.Text = \"The future of civilization rest";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"));
RDebugUtils.currentLine=2949135;
 //BA.debugLineNum = 2949135;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2949136;
 //BA.debugLineNum = 2949136;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=2949138;
 //BA.debugLineNum = 2949138;BA.debugLine="Drama2.Text = \"Charlie and the Chocolate Factory\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Charlie and the Chocolate Factory"));
RDebugUtils.currentLine=2949139;
 //BA.debugLineNum = 2949139;BA.debugLine="Starter2.Text = \"Starring: Johnny Depp, Freddie H";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"));
RDebugUtils.currentLine=2949140;
 //BA.debugLineNum = 2949140;BA.debugLine="Year2.Text = \"(2005)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=2949141;
 //BA.debugLineNum = 2949141;BA.debugLine="OverView2.Text = \"Charlie Bucket, a humble boy fr";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."));
RDebugUtils.currentLine=2949142;
 //BA.debugLineNum = 2949142;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2949143;
 //BA.debugLineNum = 2949143;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=2949145;
 //BA.debugLineNum = 2949145;BA.debugLine="Drama3.Text = \"Alice in Wonderland\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("Alice in Wonderland"));
RDebugUtils.currentLine=2949146;
 //BA.debugLineNum = 2949146;BA.debugLine="Starter3.Text = \"Starring: Mia Wasikowska, Johnny";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"));
RDebugUtils.currentLine=2949147;
 //BA.debugLineNum = 2949147;BA.debugLine="Year3.Text = \"(2010)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2010)"));
RDebugUtils.currentLine=2949148;
 //BA.debugLineNum = 2949148;BA.debugLine="OverView3.Text = \"Alice, now a teenager, returns";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."));
RDebugUtils.currentLine=2949149;
 //BA.debugLineNum = 2949149;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2949150;
 //BA.debugLineNum = 2949150;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=2949152;
 //BA.debugLineNum = 2949152;BA.debugLine="Drama4.Text = \"Harry Potter and the Philosopher's";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Harry Potter and the Philosopher's Stone"));
RDebugUtils.currentLine=2949153;
 //BA.debugLineNum = 2949153;BA.debugLine="Starter4.Text = \"Starring: Daniel Radcliffe, Rupe";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Daniel Radcliffe, Rupert Grint, Emma Watson, Richard Harris"));
RDebugUtils.currentLine=2949154;
 //BA.debugLineNum = 2949154;BA.debugLine="Year4.Text = \"(2003)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2003)"));
RDebugUtils.currentLine=2949155;
 //BA.debugLineNum = 2949155;BA.debugLine="OverView4.Text = \"Captain Jack Sparrow must rescu";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("Captain Jack Sparrow must rescue Elizabeth Swann, who has been kidnapped by cursed pirates led by Captain Barbossa. Alongside Will Turner, Jack battles ghostly pirates, seeking both freedom and revenge."));
RDebugUtils.currentLine=2949156;
 //BA.debugLineNum = 2949156;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2949157;
 //BA.debugLineNum = 2949157;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=2949159;
 //BA.debugLineNum = 2949159;BA.debugLine="Drama5.Text = \"The Curse of the Black Pearl\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("The Curse of the Black Pearl"));
RDebugUtils.currentLine=2949160;
 //BA.debugLineNum = 2949160;BA.debugLine="Starter5.Text = \"Starring: Johnny Depp, Orlando B";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Johnny Depp, Orlando Bloom, Keira Knightley, Geoffrey Rush"));
RDebugUtils.currentLine=2949161;
 //BA.debugLineNum = 2949161;BA.debugLine="Year5.Text = \"(2005)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=2949162;
 //BA.debugLineNum = 2949162;BA.debugLine="OverView5.Text = \"Four siblings, evacuated from w";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=2949163;
 //BA.debugLineNum = 2949163;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
mostCurrent._dramaimage5.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2949164;
 //BA.debugLineNum = 2949164;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage5.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=2949167;
 //BA.debugLineNum = 2949167;BA.debugLine="Drama6.Text = \"The Chronicles of Narnia\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("The Chronicles of Narnia"));
RDebugUtils.currentLine=2949168;
 //BA.debugLineNum = 2949168;BA.debugLine="Starter6.Text = \"Starring: Georgie Henley, Skanda";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Georgie Henley, Skandar Keynes, William Moseley,"));
RDebugUtils.currentLine=2949169;
 //BA.debugLineNum = 2949169;BA.debugLine="Year6.Text = \"(2005)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=2949170;
 //BA.debugLineNum = 2949170;BA.debugLine="OverView6.Text = \"Four siblings, evacuated from w";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."));
RDebugUtils.currentLine=2949171;
 //BA.debugLineNum = 2949171;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
mostCurrent._dramaimage6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2949172;
 //BA.debugLineNum = 2949172;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage6.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=2949174;
 //BA.debugLineNum = 2949174;BA.debugLine="Drama7.Text = \"Doctor Strange\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("Doctor Strange"));
RDebugUtils.currentLine=2949175;
 //BA.debugLineNum = 2949175;BA.debugLine="Starter7.Text = \"Starring: Benedict Cumberbatch,";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Benedict Cumberbatch, Chiwetel Ejiofor, Rachel McAdams, Tilda Swinton, Mads Mikkelsen"));
RDebugUtils.currentLine=2949176;
 //BA.debugLineNum = 2949176;BA.debugLine="Year7.Text = \"(2016)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2016)"));
RDebugUtils.currentLine=2949177;
 //BA.debugLineNum = 2949177;BA.debugLine="OverView7.Text = \"After a life-changing accident";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("After a life-changing accident ends his career, arrogant surgeon Stephen Strange seeks healing in Nepal, where he learns the mystic arts and must protect the world from dark magical forces beyond reality."));
RDebugUtils.currentLine=2949178;
 //BA.debugLineNum = 2949178;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2949179;
 //BA.debugLineNum = 2949179;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=2949181;
 //BA.debugLineNum = 2949181;BA.debugLine="Drama8.Text = \"V for Vendetta\"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("V for Vendetta"));
RDebugUtils.currentLine=2949182;
 //BA.debugLineNum = 2949182;BA.debugLine="Starter8.Text = \"Starring: Hugo Weaving, Natalie";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Hugo Weaving, Natalie Portman, Stephen Rea, John Hurt"));
RDebugUtils.currentLine=2949183;
 //BA.debugLineNum = 2949183;BA.debugLine="Year8.Text = \"(2005)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=2949184;
 //BA.debugLineNum = 2949184;BA.debugLine="OverView8.Text = \"In a totalitarian future Britai";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("In a totalitarian future Britain, a masked revolutionary known as \"V\" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."));
RDebugUtils.currentLine=2949185;
 //BA.debugLineNum = 2949185;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
mostCurrent._dramaimage8.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2949186;
 //BA.debugLineNum = 2949186;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage8.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=2949188;
 //BA.debugLineNum = 2949188;BA.debugLine="Drama9.Text = \"Aladdin\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("Aladdin"));
RDebugUtils.currentLine=2949189;
 //BA.debugLineNum = 2949189;BA.debugLine="Starter9.Text = \"Starring: Mena Massoud, Naomi Sc";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Mena Massoud, Naomi Scott, Will Smith"));
RDebugUtils.currentLine=2949190;
 //BA.debugLineNum = 2949190;BA.debugLine="Year9.Text = \"(2019)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=2949191;
 //BA.debugLineNum = 2949191;BA.debugLine="OverView9.Text = \"Aladdin, a kind-hearted street";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."));
RDebugUtils.currentLine=2949192;
 //BA.debugLineNum = 2949192;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
mostCurrent._dramaimage9.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2949193;
 //BA.debugLineNum = 2949193;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets, \"";
mostCurrent._dramaimage9.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=2949195;
 //BA.debugLineNum = 2949195;BA.debugLine="Drama10.Text = \"After Earth\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("After Earth"));
RDebugUtils.currentLine=2949196;
 //BA.debugLineNum = 2949196;BA.debugLine="Starter10.Text = \"Starring: Will Smith, Jaden Smi";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Will Smith, Jaden Smith, Sigourney Weaver"));
RDebugUtils.currentLine=2949197;
 //BA.debugLineNum = 2949197;BA.debugLine="Year10.Text = \"(2013)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=2949198;
 //BA.debugLineNum = 2949198;BA.debugLine="OverView10.Text = \"Set in the future, After Earth";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."));
RDebugUtils.currentLine=2949199;
 //BA.debugLineNum = 2949199;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=2949200;
 //BA.debugLineNum = 2949200;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=2949202;
 //BA.debugLineNum = 2949202;BA.debugLine="ScrollView1.Panel.AddView(p, 0, 0, 200%x, 210%y)";
mostCurrent._scrollview1.getPanel().AddView((android.view.View)(mostCurrent._p.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=2949203;
 //BA.debugLineNum = 2949203;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=2949204;
 //BA.debugLineNum = 2949204;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="scifi";
RDebugUtils.currentLine=3080192;
 //BA.debugLineNum = 3080192;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=3080194;
 //BA.debugLineNum = 3080194;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=3014656;
 //BA.debugLineNum = 3014656;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=3014658;
 //BA.debugLineNum = 3014658;BA.debugLine="End Sub";
return "";
}
public static String  _dramapage_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "dramapage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "dramapage_click", null));}
RDebugUtils.currentLine=3276800;
 //BA.debugLineNum = 3276800;BA.debugLine="Private Sub DramaPage_Click";
RDebugUtils.currentLine=3276801;
 //BA.debugLineNum = 3276801;BA.debugLine="StartActivity(Drama)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._drama.getObject()));
RDebugUtils.currentLine=3276802;
 //BA.debugLineNum = 3276802;BA.debugLine="End Sub";
return "";
}
public static String  _homepage_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "homepage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "homepage_click", null));}
RDebugUtils.currentLine=3211264;
 //BA.debugLineNum = 3211264;BA.debugLine="Private Sub HomePage_Click";
RDebugUtils.currentLine=3211265;
 //BA.debugLineNum = 3211265;BA.debugLine="StartActivity(Main)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._main.getObject()));
RDebugUtils.currentLine=3211266;
 //BA.debugLineNum = 3211266;BA.debugLine="End Sub";
return "";
}
public static String  _scifipage_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "scifipage_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "scifipage_click", null));}
RDebugUtils.currentLine=3145728;
 //BA.debugLineNum = 3145728;BA.debugLine="Private Sub SciFiPage_Click";
RDebugUtils.currentLine=3145730;
 //BA.debugLineNum = 3145730;BA.debugLine="End Sub";
return "";
}
public static String  _searchbtn_click() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchbtn_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchbtn_click", null));}
RDebugUtils.currentLine=6225920;
 //BA.debugLineNum = 6225920;BA.debugLine="Private Sub SearchBtn_Click";
RDebugUtils.currentLine=6225921;
 //BA.debugLineNum = 6225921;BA.debugLine="SearchNow";
_searchnow();
RDebugUtils.currentLine=6225922;
 //BA.debugLineNum = 6225922;BA.debugLine="End Sub";
return "";
}
public static String  _searchnow() throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchnow", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchnow", null));}
String _query = "";
String _userinput = "";
RDebugUtils.currentLine=6291456;
 //BA.debugLineNum = 6291456;BA.debugLine="Sub SearchNow";
RDebugUtils.currentLine=6291457;
 //BA.debugLineNum = 6291457;BA.debugLine="Dim query As String = SearchEngine.Text.ToLowerCa";
_query = mostCurrent._searchengine.getText().toLowerCase().trim();
RDebugUtils.currentLine=6291460;
 //BA.debugLineNum = 6291460;BA.debugLine="Dim UserInput As String = SearchEngine.Text";
_userinput = mostCurrent._searchengine.getText();
RDebugUtils.currentLine=6291463;
 //BA.debugLineNum = 6291463;BA.debugLine="If query.Contains(\"crank\") Then";
if (_query.contains("crank")) { 
RDebugUtils.currentLine=6291465;
 //BA.debugLineNum = 6291465;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=6291466;
 //BA.debugLineNum = 6291466;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=6291467;
 //BA.debugLineNum = 6291467;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=6291468;
 //BA.debugLineNum = 6291468;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=6291469;
 //BA.debugLineNum = 6291469;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291470;
 //BA.debugLineNum = 6291470;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=6291472;
 //BA.debugLineNum = 6291472;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291473;
 //BA.debugLineNum = 6291473;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291474;
 //BA.debugLineNum = 6291474;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291475;
 //BA.debugLineNum = 6291475;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291476;
 //BA.debugLineNum = 6291476;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291478;
 //BA.debugLineNum = 6291478;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291480;
 //BA.debugLineNum = 6291480;BA.debugLine="Else If query.Contains(\"sherlock\") Or query.Conta";
if (_query.contains("sherlock") || _query.contains("sherlock holmes")) { 
RDebugUtils.currentLine=6291482;
 //BA.debugLineNum = 6291482;BA.debugLine="Drama1.Text = \"Sherlock Holmes \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Sherlock Holmes "));
RDebugUtils.currentLine=6291483;
 //BA.debugLineNum = 6291483;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ju";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=6291484;
 //BA.debugLineNum = 6291484;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=6291485;
 //BA.debugLineNum = 6291485;BA.debugLine="OverView1.Text = \"Detective Sherlock Holmes and";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=6291486;
 //BA.debugLineNum = 6291486;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291487;
 //BA.debugLineNum = 6291487;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=6291489;
 //BA.debugLineNum = 6291489;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291490;
 //BA.debugLineNum = 6291490;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291491;
 //BA.debugLineNum = 6291491;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291492;
 //BA.debugLineNum = 6291492;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291493;
 //BA.debugLineNum = 6291493;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291495;
 //BA.debugLineNum = 6291495;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291497;
 //BA.debugLineNum = 6291497;BA.debugLine="Else If query.Contains(\"the transporter\") Or quer";
if (_query.contains("the transporter") || _query.contains("transporter")) { 
RDebugUtils.currentLine=6291499;
 //BA.debugLineNum = 6291499;BA.debugLine="Drama1.Text = \"The Transporter\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=6291500;
 //BA.debugLineNum = 6291500;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Shu Qi";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=6291501;
 //BA.debugLineNum = 6291501;BA.debugLine="Year1.Text = \"(2002)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=6291502;
 //BA.debugLineNum = 6291502;BA.debugLine="OverView1.Text = \"Frank Martin, who transports p";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=6291503;
 //BA.debugLineNum = 6291503;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291504;
 //BA.debugLineNum = 6291504;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=6291506;
 //BA.debugLineNum = 6291506;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291507;
 //BA.debugLineNum = 6291507;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291508;
 //BA.debugLineNum = 6291508;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291509;
 //BA.debugLineNum = 6291509;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291510;
 //BA.debugLineNum = 6291510;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291512;
 //BA.debugLineNum = 6291512;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291514;
 //BA.debugLineNum = 6291514;BA.debugLine="Else If query.Contains(\"avengers endgame\") Or que";
if (_query.contains("avengers endgame") || _query.contains("avengers") || _query.contains("endgame")) { 
RDebugUtils.currentLine=6291516;
 //BA.debugLineNum = 6291516;BA.debugLine="Drama1.Text = \"Avengers: Endgame\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=6291517;
 //BA.debugLineNum = 6291517;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ch";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=6291518;
 //BA.debugLineNum = 6291518;BA.debugLine="Year1.Text = \"(2019)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=6291519;
 //BA.debugLineNum = 6291519;BA.debugLine="OverView1.Text = \"After the devastating events o";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=6291520;
 //BA.debugLineNum = 6291520;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291521;
 //BA.debugLineNum = 6291521;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=6291524;
 //BA.debugLineNum = 6291524;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291525;
 //BA.debugLineNum = 6291525;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291526;
 //BA.debugLineNum = 6291526;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291527;
 //BA.debugLineNum = 6291527;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291529;
 //BA.debugLineNum = 6291529;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291531;
 //BA.debugLineNum = 6291531;BA.debugLine="Else If query.Contains(\"logan\") Then";
if (_query.contains("logan")) { 
RDebugUtils.currentLine=6291533;
 //BA.debugLineNum = 6291533;BA.debugLine="Drama1.Text = \"Logan\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=6291534;
 //BA.debugLineNum = 6291534;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=6291535;
 //BA.debugLineNum = 6291535;BA.debugLine="Year1.Text = \"(2017)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=6291536;
 //BA.debugLineNum = 6291536;BA.debugLine="OverView1.Text = \"In a future where mutants are";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=6291537;
 //BA.debugLineNum = 6291537;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291538;
 //BA.debugLineNum = 6291538;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=6291540;
 //BA.debugLineNum = 6291540;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291541;
 //BA.debugLineNum = 6291541;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291542;
 //BA.debugLineNum = 6291542;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291543;
 //BA.debugLineNum = 6291543;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291544;
 //BA.debugLineNum = 6291544;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291546;
 //BA.debugLineNum = 6291546;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291548;
 //BA.debugLineNum = 6291548;BA.debugLine="Else If query.Contains(\"iron man\") Or query.Conta";
if (_query.contains("iron man") || _query.contains("man") || _query.contains("iron")) { 
RDebugUtils.currentLine=6291550;
 //BA.debugLineNum = 6291550;BA.debugLine="Drama1.Text = \"Iron Man\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=6291551;
 //BA.debugLineNum = 6291551;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Gw";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=6291552;
 //BA.debugLineNum = 6291552;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=6291553;
 //BA.debugLineNum = 6291553;BA.debugLine="OverView1.Text = \"After being held captive in an";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=6291554;
 //BA.debugLineNum = 6291554;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291555;
 //BA.debugLineNum = 6291555;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=6291557;
 //BA.debugLineNum = 6291557;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291558;
 //BA.debugLineNum = 6291558;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291559;
 //BA.debugLineNum = 6291559;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291560;
 //BA.debugLineNum = 6291560;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291561;
 //BA.debugLineNum = 6291561;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291563;
 //BA.debugLineNum = 6291563;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291565;
 //BA.debugLineNum = 6291565;BA.debugLine="Else If query.Contains(\"x-men\") Or query.Contains";
if (_query.contains("x-men") || _query.contains("men") || _query.contains("xmen") || _query.contains("x")) { 
RDebugUtils.currentLine=6291567;
 //BA.debugLineNum = 6291567;BA.debugLine="Drama7.Text = \"X-Men\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=6291568;
 //BA.debugLineNum = 6291568;BA.debugLine="Starter7.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=6291569;
 //BA.debugLineNum = 6291569;BA.debugLine="Year7.Text = \"(2000)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=6291570;
 //BA.debugLineNum = 6291570;BA.debugLine="OverView7.Text = \"In a world where mutants (evol";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=6291571;
 //BA.debugLineNum = 6291571;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291572;
 //BA.debugLineNum = 6291572;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=6291574;
 //BA.debugLineNum = 6291574;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291575;
 //BA.debugLineNum = 6291575;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291576;
 //BA.debugLineNum = 6291576;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291577;
 //BA.debugLineNum = 6291577;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291578;
 //BA.debugLineNum = 6291578;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291580;
 //BA.debugLineNum = 6291580;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291582;
 //BA.debugLineNum = 6291582;BA.debugLine="Else If query.Contains(\"mr & mrs smith\") Or query";
if (_query.contains("mr & mrs smith") || _query.contains("mr and mrs") || _query.contains("smith") || _query.contains("mrs") || _query.contains("mr")) { 
RDebugUtils.currentLine=6291584;
 //BA.debugLineNum = 6291584;BA.debugLine="Drama1.Text = \"Mr. & Mrs. Smith \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Mr. & Mrs. Smith "));
RDebugUtils.currentLine=6291585;
 //BA.debugLineNum = 6291585;BA.debugLine="Starter1.Text = \"Starring: Brad Pitt, Angelina J";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
RDebugUtils.currentLine=6291586;
 //BA.debugLineNum = 6291586;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=6291587;
 //BA.debugLineNum = 6291587;BA.debugLine="OverView1.Text = \"A husband and wife struggle to";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
RDebugUtils.currentLine=6291588;
 //BA.debugLineNum = 6291588;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291589;
 //BA.debugLineNum = 6291589;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=6291591;
 //BA.debugLineNum = 6291591;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291592;
 //BA.debugLineNum = 6291592;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291593;
 //BA.debugLineNum = 6291593;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291594;
 //BA.debugLineNum = 6291594;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291595;
 //BA.debugLineNum = 6291595;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291597;
 //BA.debugLineNum = 6291597;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291599;
 //BA.debugLineNum = 6291599;BA.debugLine="Else If query.Contains(\"the wolverine\") Or query.";
if (_query.contains("the wolverine") || _query.contains("wolverine")) { 
RDebugUtils.currentLine=6291601;
 //BA.debugLineNum = 6291601;BA.debugLine="Drama1.Text = \"The Wolverine\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=6291602;
 //BA.debugLineNum = 6291602;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Will Yu";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=6291603;
 //BA.debugLineNum = 6291603;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=6291604;
 //BA.debugLineNum = 6291604;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=6291605;
 //BA.debugLineNum = 6291605;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291606;
 //BA.debugLineNum = 6291606;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=6291608;
 //BA.debugLineNum = 6291608;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291609;
 //BA.debugLineNum = 6291609;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291610;
 //BA.debugLineNum = 6291610;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291611;
 //BA.debugLineNum = 6291611;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291612;
 //BA.debugLineNum = 6291612;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291614;
 //BA.debugLineNum = 6291614;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291616;
 //BA.debugLineNum = 6291616;BA.debugLine="Else If query.Contains(\"prisoners\") Or query.Cont";
if (_query.contains("prisoners") || _query.contains("prisoner") || _query.contains("pri")) { 
RDebugUtils.currentLine=6291618;
 //BA.debugLineNum = 6291618;BA.debugLine="Drama10.Text = \"Prisoners\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=6291619;
 //BA.debugLineNum = 6291619;BA.debugLine="Starter10.Text = \"Starring: Hugh Jackman, Jake G";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=6291620;
 //BA.debugLineNum = 6291620;BA.debugLine="Year10.Text = \"(2013)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=6291621;
 //BA.debugLineNum = 6291621;BA.debugLine="OverView10.Text = \"A desperate father takes the";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=6291622;
 //BA.debugLineNum = 6291622;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291623;
 //BA.debugLineNum = 6291623;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=6291625;
 //BA.debugLineNum = 6291625;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291626;
 //BA.debugLineNum = 6291626;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291627;
 //BA.debugLineNum = 6291627;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291628;
 //BA.debugLineNum = 6291628;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291629;
 //BA.debugLineNum = 6291629;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291631;
 //BA.debugLineNum = 6291631;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291633;
 //BA.debugLineNum = 6291633;BA.debugLine="Else If query.Contains(\"jason statham\") Or query.";
if (_query.contains("jason statham") || _query.contains("jason") || _query.contains("statham")) { 
RDebugUtils.currentLine=6291634;
 //BA.debugLineNum = 6291634;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=6291635;
 //BA.debugLineNum = 6291635;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=6291636;
 //BA.debugLineNum = 6291636;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=6291637;
 //BA.debugLineNum = 6291637;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=6291638;
 //BA.debugLineNum = 6291638;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291639;
 //BA.debugLineNum = 6291639;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=6291641;
 //BA.debugLineNum = 6291641;BA.debugLine="Drama2.Text = \"The Transporter\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=6291642;
 //BA.debugLineNum = 6291642;BA.debugLine="Starter2.Text = \"Starring: Jason Statham, Shu Qi";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=6291643;
 //BA.debugLineNum = 6291643;BA.debugLine="Year2.Text = \"(2002)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=6291644;
 //BA.debugLineNum = 6291644;BA.debugLine="OverView2.Text = \"Frank Martin, who transports p";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=6291645;
 //BA.debugLineNum = 6291645;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291646;
 //BA.debugLineNum = 6291646;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=6291648;
 //BA.debugLineNum = 6291648;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291649;
 //BA.debugLineNum = 6291649;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291650;
 //BA.debugLineNum = 6291650;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291652;
 //BA.debugLineNum = 6291652;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291654;
 //BA.debugLineNum = 6291654;BA.debugLine="Else If query.Contains(\"Robert downey jr\") Or que";
if (_query.contains("Robert downey jr") || _query.contains("robert") || _query.contains("downey") || _query.contains("downey jr")) { 
RDebugUtils.currentLine=6291655;
 //BA.debugLineNum = 6291655;BA.debugLine="Drama1.Text = \"Sherlock Holmes \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Sherlock Holmes "));
RDebugUtils.currentLine=6291656;
 //BA.debugLineNum = 6291656;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ju";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=6291657;
 //BA.debugLineNum = 6291657;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=6291658;
 //BA.debugLineNum = 6291658;BA.debugLine="OverView1.Text = \"Detective Sherlock Holmes and";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=6291659;
 //BA.debugLineNum = 6291659;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291660;
 //BA.debugLineNum = 6291660;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=6291662;
 //BA.debugLineNum = 6291662;BA.debugLine="Drama2.Text = \"Avengers: Endgame\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=6291663;
 //BA.debugLineNum = 6291663;BA.debugLine="Starter2.Text = \"Starring: Robert Downey Jr., Ch";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=6291664;
 //BA.debugLineNum = 6291664;BA.debugLine="Year2.Text = \"(2019)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=6291665;
 //BA.debugLineNum = 6291665;BA.debugLine="OverView2.Text = \"After the devastating events o";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=6291666;
 //BA.debugLineNum = 6291666;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291667;
 //BA.debugLineNum = 6291667;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=6291669;
 //BA.debugLineNum = 6291669;BA.debugLine="Drama3.Text = \"Iron Man\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=6291670;
 //BA.debugLineNum = 6291670;BA.debugLine="Starter3.Text = \"Starring: Robert Downey Jr., Gw";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=6291671;
 //BA.debugLineNum = 6291671;BA.debugLine="Year3.Text = \"(2008)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=6291672;
 //BA.debugLineNum = 6291672;BA.debugLine="OverView3.Text = \"After being held captive in an";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=6291673;
 //BA.debugLineNum = 6291673;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291674;
 //BA.debugLineNum = 6291674;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=6291676;
 //BA.debugLineNum = 6291676;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291677;
 //BA.debugLineNum = 6291677;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291678;
 //BA.debugLineNum = 6291678;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291680;
 //BA.debugLineNum = 6291680;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291682;
 //BA.debugLineNum = 6291682;BA.debugLine="Else If query.Contains(\"patrick stewart\") Or quer";
if (_query.contains("patrick stewart") || _query.contains("patrick") || _query.contains("stewart")) { 
RDebugUtils.currentLine=6291683;
 //BA.debugLineNum = 6291683;BA.debugLine="Drama1.Text = \"Logan\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=6291684;
 //BA.debugLineNum = 6291684;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=6291685;
 //BA.debugLineNum = 6291685;BA.debugLine="Year1.Text = \"(2017)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=6291686;
 //BA.debugLineNum = 6291686;BA.debugLine="OverView1.Text = \"In a future where mutants are";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=6291687;
 //BA.debugLineNum = 6291687;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291688;
 //BA.debugLineNum = 6291688;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=6291690;
 //BA.debugLineNum = 6291690;BA.debugLine="Drama2.Text = \"X-Men\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=6291691;
 //BA.debugLineNum = 6291691;BA.debugLine="Starter2.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=6291692;
 //BA.debugLineNum = 6291692;BA.debugLine="Year2.Text = \"(2000)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=6291693;
 //BA.debugLineNum = 6291693;BA.debugLine="OverView2.Text = \"In a world where mutants (evol";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=6291694;
 //BA.debugLineNum = 6291694;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291695;
 //BA.debugLineNum = 6291695;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=6291697;
 //BA.debugLineNum = 6291697;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291698;
 //BA.debugLineNum = 6291698;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291699;
 //BA.debugLineNum = 6291699;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291700;
 //BA.debugLineNum = 6291700;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291702;
 //BA.debugLineNum = 6291702;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291704;
 //BA.debugLineNum = 6291704;BA.debugLine="Else If query.Contains(\"hugh jackman\") Or query.C";
if (_query.contains("hugh jackman") || _query.contains("hugh") || _query.contains("jackman")) { 
RDebugUtils.currentLine=6291705;
 //BA.debugLineNum = 6291705;BA.debugLine="Drama1.Text = \"Logan\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=6291706;
 //BA.debugLineNum = 6291706;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=6291707;
 //BA.debugLineNum = 6291707;BA.debugLine="Year1.Text = \"(2017)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=6291708;
 //BA.debugLineNum = 6291708;BA.debugLine="OverView1.Text = \"In a future where mutants are";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=6291709;
 //BA.debugLineNum = 6291709;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291710;
 //BA.debugLineNum = 6291710;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=6291712;
 //BA.debugLineNum = 6291712;BA.debugLine="Drama2.Text = \"X-Men\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=6291713;
 //BA.debugLineNum = 6291713;BA.debugLine="Starter2.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=6291714;
 //BA.debugLineNum = 6291714;BA.debugLine="Year2.Text = \"(2000)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=6291715;
 //BA.debugLineNum = 6291715;BA.debugLine="OverView2.Text = \"In a world where mutants (evol";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=6291716;
 //BA.debugLineNum = 6291716;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291717;
 //BA.debugLineNum = 6291717;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=6291719;
 //BA.debugLineNum = 6291719;BA.debugLine="Drama3.Text = \"The Wolverine\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=6291720;
 //BA.debugLineNum = 6291720;BA.debugLine="Starter3.Text = \"Starring: Hugh Jackman, Will Yu";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=6291721;
 //BA.debugLineNum = 6291721;BA.debugLine="Year3.Text = \"(2015)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=6291722;
 //BA.debugLineNum = 6291722;BA.debugLine="OverView3.Text = \"A chance encounter between a y";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=6291723;
 //BA.debugLineNum = 6291723;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291724;
 //BA.debugLineNum = 6291724;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=6291726;
 //BA.debugLineNum = 6291726;BA.debugLine="Drama4.Text = \"Prisoners\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=6291727;
 //BA.debugLineNum = 6291727;BA.debugLine="Starter4.Text = \"Starring: Hugh Jackman, Jake Gy";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=6291728;
 //BA.debugLineNum = 6291728;BA.debugLine="Year4.Text = \"(2013)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=6291729;
 //BA.debugLineNum = 6291729;BA.debugLine="OverView4.Text = \"A desperate father takes the l";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=6291730;
 //BA.debugLineNum = 6291730;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291731;
 //BA.debugLineNum = 6291731;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=6291733;
 //BA.debugLineNum = 6291733;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291734;
 //BA.debugLineNum = 6291734;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291736;
 //BA.debugLineNum = 6291736;BA.debugLine="p.Height = 85%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (85),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291738;
 //BA.debugLineNum = 6291738;BA.debugLine="Else If query.Contains(\"amy smart\") Or query.Cont";
if (_query.contains("amy smart") || _query.contains("amy") || _query.contains("smart") || _query.contains("carlos sanz") || _query.contains("carlos") || _query.contains("sanz")) { 
RDebugUtils.currentLine=6291739;
 //BA.debugLineNum = 6291739;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=6291740;
 //BA.debugLineNum = 6291740;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=6291741;
 //BA.debugLineNum = 6291741;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=6291742;
 //BA.debugLineNum = 6291742;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=6291743;
 //BA.debugLineNum = 6291743;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291744;
 //BA.debugLineNum = 6291744;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=6291746;
 //BA.debugLineNum = 6291746;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291747;
 //BA.debugLineNum = 6291747;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291748;
 //BA.debugLineNum = 6291748;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291749;
 //BA.debugLineNum = 6291749;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291750;
 //BA.debugLineNum = 6291750;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291752;
 //BA.debugLineNum = 6291752;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291753;
 //BA.debugLineNum = 6291753;BA.debugLine="Else If query.Contains(\"jude law\") Or query.Conta";
if (_query.contains("jude law") || _query.contains("jude") || _query.contains("law") || _query.contains("rachel mcadams") || _query.contains("rachel") || _query.contains("mcadams")) { 
RDebugUtils.currentLine=6291754;
 //BA.debugLineNum = 6291754;BA.debugLine="Drama1.Text = \"Sherlock Holmes \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Sherlock Holmes "));
RDebugUtils.currentLine=6291755;
 //BA.debugLineNum = 6291755;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ju";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=6291756;
 //BA.debugLineNum = 6291756;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=6291757;
 //BA.debugLineNum = 6291757;BA.debugLine="OverView1.Text = \"Detective Sherlock Holmes and";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=6291758;
 //BA.debugLineNum = 6291758;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291759;
 //BA.debugLineNum = 6291759;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=6291761;
 //BA.debugLineNum = 6291761;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291762;
 //BA.debugLineNum = 6291762;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291763;
 //BA.debugLineNum = 6291763;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291764;
 //BA.debugLineNum = 6291764;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291765;
 //BA.debugLineNum = 6291765;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291767;
 //BA.debugLineNum = 6291767;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291768;
 //BA.debugLineNum = 6291768;BA.debugLine="Else if query.Contains(\"shu qi\") Or query.Contain";
if (_query.contains("shu qi") || _query.contains("shu") || _query.contains("qi") || _query.contains("matt") || _query.contains("schulze") || _query.contains("matt schulze")) { 
RDebugUtils.currentLine=6291769;
 //BA.debugLineNum = 6291769;BA.debugLine="Drama1.Text = \"The Transporter\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=6291770;
 //BA.debugLineNum = 6291770;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Shu Qi";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=6291771;
 //BA.debugLineNum = 6291771;BA.debugLine="Year1.Text = \"(2002)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=6291772;
 //BA.debugLineNum = 6291772;BA.debugLine="OverView1.Text = \"Frank Martin, who transports p";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=6291773;
 //BA.debugLineNum = 6291773;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291774;
 //BA.debugLineNum = 6291774;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=6291776;
 //BA.debugLineNum = 6291776;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291777;
 //BA.debugLineNum = 6291777;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291778;
 //BA.debugLineNum = 6291778;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291779;
 //BA.debugLineNum = 6291779;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291780;
 //BA.debugLineNum = 6291780;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291782;
 //BA.debugLineNum = 6291782;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291783;
 //BA.debugLineNum = 6291783;BA.debugLine="Else if query.Contains(\"chris evans\") Or query.Co";
if (_query.contains("chris evans") || _query.contains("chris") || _query.contains("evans") || _query.contains("mark ruffalo") || _query.contains("mark") || _query.contains("ruffalo")) { 
RDebugUtils.currentLine=6291784;
 //BA.debugLineNum = 6291784;BA.debugLine="Drama1.Text = \"Avengers: Endgame\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=6291785;
 //BA.debugLineNum = 6291785;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Ch";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=6291786;
 //BA.debugLineNum = 6291786;BA.debugLine="Year1.Text = \"(2019)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=6291787;
 //BA.debugLineNum = 6291787;BA.debugLine="OverView1.Text = \"After the devastating events o";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=6291788;
 //BA.debugLineNum = 6291788;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291789;
 //BA.debugLineNum = 6291789;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=6291791;
 //BA.debugLineNum = 6291791;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291792;
 //BA.debugLineNum = 6291792;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291793;
 //BA.debugLineNum = 6291793;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291794;
 //BA.debugLineNum = 6291794;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291795;
 //BA.debugLineNum = 6291795;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291797;
 //BA.debugLineNum = 6291797;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291798;
 //BA.debugLineNum = 6291798;BA.debugLine="Else if query.Contains(\"dafne keen\") Or query.Con";
if (_query.contains("dafne keen") || _query.contains("dafne") || _query.contains("keen")) { 
RDebugUtils.currentLine=6291799;
 //BA.debugLineNum = 6291799;BA.debugLine="Drama1.Text = \"Logan\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=6291800;
 //BA.debugLineNum = 6291800;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=6291801;
 //BA.debugLineNum = 6291801;BA.debugLine="Year1.Text = \"(2017)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=6291802;
 //BA.debugLineNum = 6291802;BA.debugLine="OverView1.Text = \"In a future where mutants are";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=6291803;
 //BA.debugLineNum = 6291803;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291804;
 //BA.debugLineNum = 6291804;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=6291806;
 //BA.debugLineNum = 6291806;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291807;
 //BA.debugLineNum = 6291807;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291808;
 //BA.debugLineNum = 6291808;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291809;
 //BA.debugLineNum = 6291809;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291810;
 //BA.debugLineNum = 6291810;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291812;
 //BA.debugLineNum = 6291812;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291814;
 //BA.debugLineNum = 6291814;BA.debugLine="Else if query.Contains(\"gwyneth paltrow\") Or quer";
if (_query.contains("gwyneth paltrow") || _query.contains("gwyneth") || _query.contains("paltrow") || _query.contains("terrence howard") || _query.contains("terrence") || _query.contains("howard")) { 
RDebugUtils.currentLine=6291815;
 //BA.debugLineNum = 6291815;BA.debugLine="Drama1.Text = \"Iron Man\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=6291816;
 //BA.debugLineNum = 6291816;BA.debugLine="Starter1.Text = \"Starring: Robert Downey Jr., Gw";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=6291817;
 //BA.debugLineNum = 6291817;BA.debugLine="Year1.Text = \"(2008)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=6291818;
 //BA.debugLineNum = 6291818;BA.debugLine="OverView1.Text = \"After being held captive in an";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=6291819;
 //BA.debugLineNum = 6291819;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291820;
 //BA.debugLineNum = 6291820;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=6291822;
 //BA.debugLineNum = 6291822;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291823;
 //BA.debugLineNum = 6291823;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291824;
 //BA.debugLineNum = 6291824;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291825;
 //BA.debugLineNum = 6291825;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291826;
 //BA.debugLineNum = 6291826;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291828;
 //BA.debugLineNum = 6291828;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291830;
 //BA.debugLineNum = 6291830;BA.debugLine="Else if query.Contains(\"ian mckellen\") Or query.C";
if (_query.contains("ian mckellen") || _query.contains("ian") || _query.contains("mckellen")) { 
RDebugUtils.currentLine=6291831;
 //BA.debugLineNum = 6291831;BA.debugLine="Drama1.Text = \"X-Men\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=6291832;
 //BA.debugLineNum = 6291832;BA.debugLine="Starter1.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=6291833;
 //BA.debugLineNum = 6291833;BA.debugLine="Year1.Text = \"(2000)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=6291834;
 //BA.debugLineNum = 6291834;BA.debugLine="OverView1.Text = \"In a world where mutants (evol";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=6291835;
 //BA.debugLineNum = 6291835;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291836;
 //BA.debugLineNum = 6291836;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=6291838;
 //BA.debugLineNum = 6291838;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291839;
 //BA.debugLineNum = 6291839;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291840;
 //BA.debugLineNum = 6291840;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291841;
 //BA.debugLineNum = 6291841;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291842;
 //BA.debugLineNum = 6291842;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291844;
 //BA.debugLineNum = 6291844;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291846;
 //BA.debugLineNum = 6291846;BA.debugLine="Else if query.Contains(\"brad pitt\") Or query.Cont";
if (_query.contains("brad pitt") || _query.contains("brad") || _query.contains("pitt") || _query.contains("angelina jolie") || _query.contains("angelina") || _query.contains("jolie") || _query.contains("adam brody") || _query.contains("adam") || _query.contains("brody")) { 
RDebugUtils.currentLine=6291847;
 //BA.debugLineNum = 6291847;BA.debugLine="Drama1.Text = \"Mr. & Mrs. Smith \"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Mr. & Mrs. Smith "));
RDebugUtils.currentLine=6291848;
 //BA.debugLineNum = 6291848;BA.debugLine="Starter1.Text = \"Starring: Brad Pitt, Angelina J";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
RDebugUtils.currentLine=6291849;
 //BA.debugLineNum = 6291849;BA.debugLine="Year1.Text = \"(2005)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=6291850;
 //BA.debugLineNum = 6291850;BA.debugLine="OverView1.Text = \"A husband and wife struggle to";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
RDebugUtils.currentLine=6291851;
 //BA.debugLineNum = 6291851;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291852;
 //BA.debugLineNum = 6291852;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=6291854;
 //BA.debugLineNum = 6291854;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291855;
 //BA.debugLineNum = 6291855;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291856;
 //BA.debugLineNum = 6291856;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291857;
 //BA.debugLineNum = 6291857;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291858;
 //BA.debugLineNum = 6291858;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291860;
 //BA.debugLineNum = 6291860;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291864;
 //BA.debugLineNum = 6291864;BA.debugLine="Else if query.Contains(\"will yun lee\") Or query.C";
if (_query.contains("will yun lee") || _query.contains("will") || _query.contains("yun") || _query.contains("lee") || _query.contains("tao okamoto") || _query.contains("tao") || _query.contains("okamoto")) { 
RDebugUtils.currentLine=6291865;
 //BA.debugLineNum = 6291865;BA.debugLine="Drama1.Text = \"The Wolverine\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=6291866;
 //BA.debugLineNum = 6291866;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Will Yu";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=6291867;
 //BA.debugLineNum = 6291867;BA.debugLine="Year1.Text = \"(2015)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=6291868;
 //BA.debugLineNum = 6291868;BA.debugLine="OverView1.Text = \"A chance encounter between a y";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=6291869;
 //BA.debugLineNum = 6291869;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291870;
 //BA.debugLineNum = 6291870;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=6291872;
 //BA.debugLineNum = 6291872;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291873;
 //BA.debugLineNum = 6291873;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291874;
 //BA.debugLineNum = 6291874;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291875;
 //BA.debugLineNum = 6291875;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291876;
 //BA.debugLineNum = 6291876;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291878;
 //BA.debugLineNum = 6291878;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else 
{RDebugUtils.currentLine=6291880;
 //BA.debugLineNum = 6291880;BA.debugLine="Else if query.Contains(\"jake gyllenhaal\") Or quer";
if (_query.contains("jake gyllenhaal") || _query.contains("jake") || _query.contains("gyllenhaal") || _query.contains("viola davis") || _query.contains("viola") || _query.contains("davis")) { 
RDebugUtils.currentLine=6291881;
 //BA.debugLineNum = 6291881;BA.debugLine="Drama1.Text = \"Prisoners\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=6291882;
 //BA.debugLineNum = 6291882;BA.debugLine="Starter1.Text = \"Starring: Hugh Jackman, Jake Gy";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=6291883;
 //BA.debugLineNum = 6291883;BA.debugLine="Year1.Text = \"(2013)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=6291884;
 //BA.debugLineNum = 6291884;BA.debugLine="OverView1.Text = \"A desperate father takes the l";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=6291885;
 //BA.debugLineNum = 6291885;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6291886;
 //BA.debugLineNum = 6291886;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
RDebugUtils.currentLine=6291888;
 //BA.debugLineNum = 6291888;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291889;
 //BA.debugLineNum = 6291889;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291890;
 //BA.debugLineNum = 6291890;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291891;
 //BA.debugLineNum = 6291891;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291892;
 //BA.debugLineNum = 6291892;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6291894;
 //BA.debugLineNum = 6291894;BA.debugLine="p.Height = 70%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (70),mostCurrent.activityBA));
 }else {
RDebugUtils.currentLine=6291898;
 //BA.debugLineNum = 6291898;BA.debugLine="MsgboxAsync(\"No results found for\" & \" \"\"\" & Use";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("No results found for"+" \""+_userinput+"\""),BA.ObjectToCharSequence(""),processBA);
 }}}}}}}}}}}}}}}}}}}}}}}}
;
RDebugUtils.currentLine=6291902;
 //BA.debugLineNum = 6291902;BA.debugLine="p.Width = 100%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA));
RDebugUtils.currentLine=6291903;
 //BA.debugLineNum = 6291903;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=6291905;
 //BA.debugLineNum = 6291905;BA.debugLine="End Sub";
return "";
}
public static String  _searchengine_textchanged(String _old,String _new) throws Exception{
RDebugUtils.currentModule="scifi";
if (Debug.shouldDelegate(mostCurrent.activityBA, "searchengine_textchanged", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "searchengine_textchanged", new Object[] {_old,_new}));}
String _query = "";
RDebugUtils.currentLine=6356992;
 //BA.debugLineNum = 6356992;BA.debugLine="Sub SearchEngine_TextChanged (Old As String, New A";
RDebugUtils.currentLine=6356993;
 //BA.debugLineNum = 6356993;BA.debugLine="Dim query As String = New.ToLowerCase.Trim";
_query = _new.toLowerCase().trim();
RDebugUtils.currentLine=6356995;
 //BA.debugLineNum = 6356995;BA.debugLine="If query = \"\" Then";
if ((_query).equals("")) { 
RDebugUtils.currentLine=6356998;
 //BA.debugLineNum = 6356998;BA.debugLine="p.Height = 210%y";
mostCurrent._p.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (210),mostCurrent.activityBA));
RDebugUtils.currentLine=6356999;
 //BA.debugLineNum = 6356999;BA.debugLine="p.Width = 200%x";
mostCurrent._p.setWidth(anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (200),mostCurrent.activityBA));
RDebugUtils.currentLine=6357000;
 //BA.debugLineNum = 6357000;BA.debugLine="ScrollView1.Panel.Height = p.Height";
mostCurrent._scrollview1.getPanel().setHeight(mostCurrent._p.getHeight());
RDebugUtils.currentLine=6357002;
 //BA.debugLineNum = 6357002;BA.debugLine="PanelMovie1.Visible = False";
mostCurrent._panelmovie1.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6357003;
 //BA.debugLineNum = 6357003;BA.debugLine="PanelMovie2.Visible = False";
mostCurrent._panelmovie2.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6357004;
 //BA.debugLineNum = 6357004;BA.debugLine="PanelMovie3.Visible = False";
mostCurrent._panelmovie3.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6357005;
 //BA.debugLineNum = 6357005;BA.debugLine="PanelMovie4.Visible = False";
mostCurrent._panelmovie4.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6357006;
 //BA.debugLineNum = 6357006;BA.debugLine="PanelMovie5.Visible = False";
mostCurrent._panelmovie5.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6357007;
 //BA.debugLineNum = 6357007;BA.debugLine="PanelMovie6.Visible = False";
mostCurrent._panelmovie6.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6357008;
 //BA.debugLineNum = 6357008;BA.debugLine="PanelMovie7.Visible = False";
mostCurrent._panelmovie7.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6357009;
 //BA.debugLineNum = 6357009;BA.debugLine="PanelMovie8.Visible = False";
mostCurrent._panelmovie8.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6357010;
 //BA.debugLineNum = 6357010;BA.debugLine="PanelMovie9.Visible = False";
mostCurrent._panelmovie9.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6357011;
 //BA.debugLineNum = 6357011;BA.debugLine="PanelMovie10.Visible = False";
mostCurrent._panelmovie10.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=6357015;
 //BA.debugLineNum = 6357015;BA.debugLine="Drama1.Text = \"Crank\"";
mostCurrent._drama1.setText(BA.ObjectToCharSequence("Crank"));
RDebugUtils.currentLine=6357016;
 //BA.debugLineNum = 6357016;BA.debugLine="Starter1.Text = \"Starring: Jason Statham, Amy Sm";
mostCurrent._starter1.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Amy Smart, Carlos Sanz"));
RDebugUtils.currentLine=6357017;
 //BA.debugLineNum = 6357017;BA.debugLine="Year1.Text = \"(2006)\"";
mostCurrent._year1.setText(BA.ObjectToCharSequence("(2006)"));
RDebugUtils.currentLine=6357018;
 //BA.debugLineNum = 6357018;BA.debugLine="OverView1.Text = \"Professional assassin Chev Che";
mostCurrent._overview1.setText(BA.ObjectToCharSequence("Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."));
RDebugUtils.currentLine=6357019;
 //BA.debugLineNum = 6357019;BA.debugLine="DramaImage1.Gravity = Gravity.FILL";
mostCurrent._dramaimage1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6357020;
 //BA.debugLineNum = 6357020;BA.debugLine="DramaImage1.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage1.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"kramer.jpg").getObject()));
RDebugUtils.currentLine=6357022;
 //BA.debugLineNum = 6357022;BA.debugLine="Drama2.Text = \"Sherlock Holmes\"";
mostCurrent._drama2.setText(BA.ObjectToCharSequence("Sherlock Holmes"));
RDebugUtils.currentLine=6357023;
 //BA.debugLineNum = 6357023;BA.debugLine="Starter2.Text = \"Starring: Robert Downey Jr., Ju";
mostCurrent._starter2.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Jude Law, Rachel McAdams"));
RDebugUtils.currentLine=6357024;
 //BA.debugLineNum = 6357024;BA.debugLine="Year2.Text = \"(2008)\"";
mostCurrent._year2.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=6357025;
 //BA.debugLineNum = 6357025;BA.debugLine="OverView2.Text = \"Detective Sherlock Holmes and";
mostCurrent._overview2.setText(BA.ObjectToCharSequence("Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."));
RDebugUtils.currentLine=6357026;
 //BA.debugLineNum = 6357026;BA.debugLine="DramaImage2.Gravity = Gravity.FILL";
mostCurrent._dramaimage2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6357027;
 //BA.debugLineNum = 6357027;BA.debugLine="DramaImage2.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage2.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"manchester.jpg").getObject()));
RDebugUtils.currentLine=6357029;
 //BA.debugLineNum = 6357029;BA.debugLine="Drama3.Text = \"The Transporter\"";
mostCurrent._drama3.setText(BA.ObjectToCharSequence("The Transporter"));
RDebugUtils.currentLine=6357030;
 //BA.debugLineNum = 6357030;BA.debugLine="Starter3.Text = \"Starring: Jason Statham, Shu Qi";
mostCurrent._starter3.setText(BA.ObjectToCharSequence("Starring: Jason Statham, Shu Qi, Matt Schulze"));
RDebugUtils.currentLine=6357031;
 //BA.debugLineNum = 6357031;BA.debugLine="Year3.Text = \"(2002)\"";
mostCurrent._year3.setText(BA.ObjectToCharSequence("(2002)"));
RDebugUtils.currentLine=6357032;
 //BA.debugLineNum = 6357032;BA.debugLine="OverView3.Text = \"Frank Martin, who transports p";
mostCurrent._overview3.setText(BA.ObjectToCharSequence("Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."));
RDebugUtils.currentLine=6357033;
 //BA.debugLineNum = 6357033;BA.debugLine="DramaImage3.Gravity = Gravity.FILL";
mostCurrent._dramaimage3.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6357034;
 //BA.debugLineNum = 6357034;BA.debugLine="DramaImage3.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage3.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"master.jpg").getObject()));
RDebugUtils.currentLine=6357036;
 //BA.debugLineNum = 6357036;BA.debugLine="Drama4.Text = \"Avengers: Endgame\"";
mostCurrent._drama4.setText(BA.ObjectToCharSequence("Avengers: Endgame"));
RDebugUtils.currentLine=6357037;
 //BA.debugLineNum = 6357037;BA.debugLine="Starter4.Text = \"Starring: Robert Downey Jr., Ch";
mostCurrent._starter4.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"));
RDebugUtils.currentLine=6357038;
 //BA.debugLineNum = 6357038;BA.debugLine="Year4.Text = \"(2019)\"";
mostCurrent._year4.setText(BA.ObjectToCharSequence("(2019)"));
RDebugUtils.currentLine=6357039;
 //BA.debugLineNum = 6357039;BA.debugLine="OverView4.Text = \"After the devastating events o";
mostCurrent._overview4.setText(BA.ObjectToCharSequence("After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."));
RDebugUtils.currentLine=6357040;
 //BA.debugLineNum = 6357040;BA.debugLine="DramaImage4.Gravity = Gravity.FILL";
mostCurrent._dramaimage4.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6357041;
 //BA.debugLineNum = 6357041;BA.debugLine="DramaImage4.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage4.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"millondolar.jpg").getObject()));
RDebugUtils.currentLine=6357043;
 //BA.debugLineNum = 6357043;BA.debugLine="Drama5.Text = \"Logan\"";
mostCurrent._drama5.setText(BA.ObjectToCharSequence("Logan"));
RDebugUtils.currentLine=6357044;
 //BA.debugLineNum = 6357044;BA.debugLine="Starter5.Text = \"Starring: Hugh Jackman, Patrick";
mostCurrent._starter5.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"));
RDebugUtils.currentLine=6357045;
 //BA.debugLineNum = 6357045;BA.debugLine="Year5.Text = \"(2017)\"";
mostCurrent._year5.setText(BA.ObjectToCharSequence("(2017)"));
RDebugUtils.currentLine=6357046;
 //BA.debugLineNum = 6357046;BA.debugLine="OverView5.Text = \"In a future where mutants are";
mostCurrent._overview5.setText(BA.ObjectToCharSequence("In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."));
RDebugUtils.currentLine=6357047;
 //BA.debugLineNum = 6357047;BA.debugLine="DramaImage5.Gravity = Gravity.FILL";
mostCurrent._dramaimage5.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6357048;
 //BA.debugLineNum = 6357048;BA.debugLine="DramaImage5.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage5.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bridges.jpg").getObject()));
RDebugUtils.currentLine=6357051;
 //BA.debugLineNum = 6357051;BA.debugLine="Drama6.Text = \"Iron Man\"";
mostCurrent._drama6.setText(BA.ObjectToCharSequence("Iron Man"));
RDebugUtils.currentLine=6357052;
 //BA.debugLineNum = 6357052;BA.debugLine="Starter6.Text = \"Starring: Robert Downey Jr., Gw";
mostCurrent._starter6.setText(BA.ObjectToCharSequence("Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"));
RDebugUtils.currentLine=6357053;
 //BA.debugLineNum = 6357053;BA.debugLine="Year6.Text = \"(2008)\"";
mostCurrent._year6.setText(BA.ObjectToCharSequence("(2008)"));
RDebugUtils.currentLine=6357054;
 //BA.debugLineNum = 6357054;BA.debugLine="OverView6.Text = \"After being held captive in an";
mostCurrent._overview6.setText(BA.ObjectToCharSequence("After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."));
RDebugUtils.currentLine=6357055;
 //BA.debugLineNum = 6357055;BA.debugLine="DramaImage6.Gravity = Gravity.FILL";
mostCurrent._dramaimage6.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6357056;
 //BA.debugLineNum = 6357056;BA.debugLine="DramaImage6.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage6.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"gonebaby.jpg").getObject()));
RDebugUtils.currentLine=6357058;
 //BA.debugLineNum = 6357058;BA.debugLine="Drama7.Text = \"X-Men\"";
mostCurrent._drama7.setText(BA.ObjectToCharSequence("X-Men"));
RDebugUtils.currentLine=6357059;
 //BA.debugLineNum = 6357059;BA.debugLine="Starter7.Text = \"Starring: Patrick Stewart, Hugh";
mostCurrent._starter7.setText(BA.ObjectToCharSequence("Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"));
RDebugUtils.currentLine=6357060;
 //BA.debugLineNum = 6357060;BA.debugLine="Year7.Text = \"(2000)\"";
mostCurrent._year7.setText(BA.ObjectToCharSequence("(2000)"));
RDebugUtils.currentLine=6357061;
 //BA.debugLineNum = 6357061;BA.debugLine="OverView7.Text = \"In a world where mutants (evol";
mostCurrent._overview7.setText(BA.ObjectToCharSequence("In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."));
RDebugUtils.currentLine=6357062;
 //BA.debugLineNum = 6357062;BA.debugLine="DramaImage7.Gravity = Gravity.FILL";
mostCurrent._dramaimage7.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6357063;
 //BA.debugLineNum = 6357063;BA.debugLine="DramaImage7.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage7.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"bluejasmine.jpg").getObject()));
RDebugUtils.currentLine=6357065;
 //BA.debugLineNum = 6357065;BA.debugLine="Drama8.Text = \"Mr. & Mrs. Smith\"";
mostCurrent._drama8.setText(BA.ObjectToCharSequence("Mr. & Mrs. Smith"));
RDebugUtils.currentLine=6357066;
 //BA.debugLineNum = 6357066;BA.debugLine="Starter8.Text = \"Starring: Brad Pitt, Angelina J";
mostCurrent._starter8.setText(BA.ObjectToCharSequence("Starring: Brad Pitt, Angelina Jolie, Adam Brody"));
RDebugUtils.currentLine=6357067;
 //BA.debugLineNum = 6357067;BA.debugLine="Year8.Text = \"(2005)\"";
mostCurrent._year8.setText(BA.ObjectToCharSequence("(2005)"));
RDebugUtils.currentLine=6357068;
 //BA.debugLineNum = 6357068;BA.debugLine="OverView8.Text = \"A husband and wife struggle to";
mostCurrent._overview8.setText(BA.ObjectToCharSequence("A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."));
RDebugUtils.currentLine=6357069;
 //BA.debugLineNum = 6357069;BA.debugLine="DramaImage8.Gravity = Gravity.FILL";
mostCurrent._dramaimage8.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6357070;
 //BA.debugLineNum = 6357070;BA.debugLine="DramaImage8.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage8.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"her.jpg").getObject()));
RDebugUtils.currentLine=6357072;
 //BA.debugLineNum = 6357072;BA.debugLine="Drama9.Text = \"The Wolverine\"";
mostCurrent._drama9.setText(BA.ObjectToCharSequence("The Wolverine"));
RDebugUtils.currentLine=6357073;
 //BA.debugLineNum = 6357073;BA.debugLine="Starter9.Text = \"Starring: Hugh Jackman, Will Yu";
mostCurrent._starter9.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"));
RDebugUtils.currentLine=6357074;
 //BA.debugLineNum = 6357074;BA.debugLine="Year9.Text = \"(2015)\"";
mostCurrent._year9.setText(BA.ObjectToCharSequence("(2015)"));
RDebugUtils.currentLine=6357075;
 //BA.debugLineNum = 6357075;BA.debugLine="OverView9.Text = \"A chance encounter between a y";
mostCurrent._overview9.setText(BA.ObjectToCharSequence("A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."));
RDebugUtils.currentLine=6357076;
 //BA.debugLineNum = 6357076;BA.debugLine="DramaImage9.Gravity = Gravity.FILL";
mostCurrent._dramaimage9.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6357077;
 //BA.debugLineNum = 6357077;BA.debugLine="DramaImage9.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage9.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"carol.jpg").getObject()));
RDebugUtils.currentLine=6357079;
 //BA.debugLineNum = 6357079;BA.debugLine="Drama10.Text = \"Prisoners\"";
mostCurrent._drama10.setText(BA.ObjectToCharSequence("Prisoners"));
RDebugUtils.currentLine=6357080;
 //BA.debugLineNum = 6357080;BA.debugLine="Starter10.Text = \"Starring: Hugh Jackman, Jake G";
mostCurrent._starter10.setText(BA.ObjectToCharSequence("Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"));
RDebugUtils.currentLine=6357081;
 //BA.debugLineNum = 6357081;BA.debugLine="Year10.Text = \"(2013)\"";
mostCurrent._year10.setText(BA.ObjectToCharSequence("(2013)"));
RDebugUtils.currentLine=6357082;
 //BA.debugLineNum = 6357082;BA.debugLine="OverView10.Text = \"A desperate father takes the";
mostCurrent._overview10.setText(BA.ObjectToCharSequence("A desperate father takes the law into his own hands after police fail to find two kidnapped girls."));
RDebugUtils.currentLine=6357083;
 //BA.debugLineNum = 6357083;BA.debugLine="DramaImage10.Gravity = Gravity.FILL";
mostCurrent._dramaimage10.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
RDebugUtils.currentLine=6357084;
 //BA.debugLineNum = 6357084;BA.debugLine="DramaImage10.Bitmap = LoadBitmap(File.DirAssets,";
mostCurrent._dramaimage10.setBitmap((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lostdaughter.jpg").getObject()));
 };
RDebugUtils.currentLine=6357090;
 //BA.debugLineNum = 6357090;BA.debugLine="End Sub";
return "";
}
}