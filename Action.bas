B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=13.1
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: True
	#IncludeTitle: False
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

	Private HomePage As Label
	Private DramaPage As Label
	Private ActionPage As Label
	Private SciFiPage As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	Activity.LoadLayout("action")

End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub


Private Sub SciFiPage_Click
	StartActivity(SciFi)
End Sub

Private Sub ActionPage_Click
	
End Sub

Private Sub DramaPage_Click
	StartActivity(Drama)
End Sub

Private Sub HomePage_Click
	StartActivity(Main)
End Sub