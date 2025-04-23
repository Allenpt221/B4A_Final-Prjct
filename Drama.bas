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
	'These global variables will be declared once when the Application starts.
	'These variables can be accessed from all modules.
End Sub

Sub Globals
	Private ScrollView1 As ScrollView
	
	'drama title label generate
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
	
	
	'image generate
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
	
	'label rate text generate
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

	Private SearchBtn As Button
	Private SearchEngine As EditText

	'panel generate 
	Dim p As Panel
	Private Panel2 As Panel
	Private Panel6 As Panel
	Private Panel7 As Panel
	Private Panel8 As Panel
	Private Panel9 As Panel
	
	
	'label generate
	Private DramaPage As Label
	Private HomePage As Label
	Private SciFiPage As Label
	Private ActionPage As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("Drama") ' Layout contains ScrollView1
	
	p.Initialize("")
	p.LoadLayout("panelview")

	' Set initial images and texts
	DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "avengers.jpeg")

	Drama1.Text = "Avengers Dooms Day"
	Star1.Text = "☆☆☆"

	Drama2.Text = "Spiderman"
	Star2.Text = "☆☆☆☆☆"

	Drama3.Text = "Avengers Dooms Day"
	Star3.Text = "☆☆☆"

	Drama4.Text = "Avengers Dooms Day"
	Star4.Text = "☆☆☆"

	Drama5.Text = "Spiderman"
	Star5.Text = "☆☆☆☆☆"

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

	ScrollView1.Panel.AddView(p, 0, 0, 200%x, 170%y)
	ScrollView1.Panel.Height = p.Height
End Sub

Private Sub SearchBtn_Click
	Dim query As String = SearchEngine.Text.ToLowerCase.Trim
	Dim userInput As String = SearchEngine.Text

	If query.Contains("avengers dooms day") Or query.Contains("avengers") Then
		Drama1.Text = "Avengers Dooms Day"
		Star1.Text = "☆☆☆"
		Drama2.Text = "Avengers Dooms Day"
		Star2.Text = "☆☆☆"
		Drama3.Text = "Avengers Dooms Day"
		Star3.Text = "☆☆☆"

		Panel6.Visible = False
		Panel7.Visible = False

		p.Height = 80%y
		p.Width = 100%x
		ScrollView1.Panel.Height = p.Height
	Else
		MsgboxAsync(userInput, "Not Found!")
	End If
End Sub

Sub SearchEngine_TextChanged (Old As String, New As String)
	Dim query As String = New.ToLowerCase.Trim

	If query = "" Then
		' Reset layout
		Panel6.Visible = True
		Panel7.Visible = True

		p.Height = 170%y
		p.Width = 200%x
		ScrollView1.Panel.Height = p.Height

		' Restore original texts
		Drama1.Text = "Avengers Dooms Day"
		Star1.Text = "☆☆☆"
		Drama2.Text = "Spiderman"
		Star2.Text = "☆☆☆☆☆"
		Drama3.Text = "Avengers Dooms Day"
		Star3.Text = "☆☆☆"
		Drama4.Text = "Avengers Dooms Day"
		Star4.Text = "☆☆☆"
		Drama5.Text = "Spiderman"
		Star5.Text = "☆☆☆☆☆"
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
	End If
End Sub


Private Sub SciFiPage_Click
	StartActivity(SciFi)
End Sub

Private Sub HomePage_Click
	StartActivity(Main)
End Sub

Private Sub DramaPage_Click
	
End Sub

Private Sub ActionPage_Click
	StartActivity(Action)
End Sub