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

	Private ScrollView1 As ScrollView
	
	Private Drama1 As Label
	Private Drama10 As Label
	Private Drama2 As Label
	Private Drama3 As Label
	Private Drama4 As Label
	Private Drama5 As Label
	Private Drama6 As Label
	Private Drama7 As Label
	Private Drama8 As Label
	Private Drama9 As Label
	Private DramaImage1 As ImageView
	Private DramaImage10 As ImageView
	Private DramaImage2 As ImageView
	Private DramaImage3 As ImageView
	Private DramaImage4 As ImageView
	Private DramaImage5 As ImageView
	Private DramaImage6 As ImageView
	Private DramaImage7 As ImageView
	Private DramaImage8 As ImageView
	Private DramaImage9 As ImageView
	Private Star1 As Label
	Private Star2 As Label
	Private Star10 As Label
	Private Star3 As Label
	Private Star4 As Label
	Private Star5 As Label
	Private Star6 As Label
	Private Star7 As Label
	Private Star8 As Label
	Private Star9 As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("Drama") ' This layout contains ScrollView1

	Dim p As Panel
	p.Initialize("")

	' Load the layout used in the other module/activity
	p.LoadLayout("panelview") ' Just reuse the layout from panelView activity
	
	DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "avengers.jpeg")

	
	Drama1.Text = "Avengers Dooms Day"
	Star1.Text = "☆☆☆"
	
	Drama2.Text = "Spiderman"
	Star2.Text = "☆☆☆☆☆"
	
	Drama3.Text = "Avengers Dooms Day"
	Star3.Text = "☆☆☆"
	
	Drama3.Text = "Spiderman"
	Star2.Text = "☆☆☆☆☆"
	
	Drama4.Text = "Avengers Dooms Day"
	Star5.Text = "☆☆☆"
	
	Drama5.Text = "Spiderman"
	Star5.Text = "☆☆☆☆☆"
	
	Drama6.Text = "Avengers Dooms Day"
	Star5.Text = "☆☆☆"
	
	Drama6.Text = "Spiderman"
	Star6.Text = "☆☆☆☆☆"
	
	Drama7.Text = "Avengers Dooms Day"
	Star7.Text = "☆☆☆"
	
	Drama8.Text = "Spiderman"
	Star8.Text = "☆☆☆☆☆"
	
	Drama9.Text = "Avengers Dooms Day"
	Star9.Text = "☆☆☆"
	
	Drama10.Text = "Spiderman"
	Star10.Text = "☆☆☆☆☆"

	' Add it to ScrollView
	ScrollView1.Panel.AddView(p, 0, 0, 120%x, 180%y)

	' Adjust scroll height
	ScrollView1.Panel.Height = p.Height
End Sub




