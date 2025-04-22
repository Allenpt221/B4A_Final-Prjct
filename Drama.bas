B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=13.1
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
	'These global variables will be declared once when the Application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
	' Create a list to hold the

	Private ImageView1 As ImageView
	Private Label1 As Label
	Private Label2 As Label
	
	Private ScrollView1 As ScrollView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("Drama") ' Load the main layout with ScrollView1
	

	' Load an image from the assets folder
	ImageView1.Bitmap = LoadBitmap(File.DirAssets, "avengers.jpeg")

	
	Label1.Text = "Avengers Dooms Day"
	Label2.Text = "☆☆☆"
	

	
	
	
End Sub



