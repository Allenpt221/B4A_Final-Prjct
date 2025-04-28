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
	Private ScrollView1 As ScrollView
	
	'drama title label generate
	Private Drama1 As Label
	Private Drama2 As Label
	Private Drama3 As Label
	Private Drama4 As Label
	Private Drama5 As Label
	Private Drama6 As Label
	Private Drama7 As Label
	Private Drama8 As Label
	Private Drama9 As Label
	Private Drama10 As Label

	
	
	'image generate
	Private DramaImage1 As ImageView
	Private DramaImage2 As ImageView
	Private DramaImage3 As ImageView
	Private DramaImage4 As ImageView
	Private DramaImage5 As ImageView
	Private DramaImage6 As ImageView
	Private DramaImage7 As ImageView
	Private DramaImage8 As ImageView
	Private DramaImage9 As ImageView
	Private DramaImage10 As ImageView
	
	'label rate text generate


	Private SearchBtn As Button
	Private SearchEngine As EditText

	'panel generate
	Dim p As Panel
	Private Panel1 As Panel
	Private PanelMovie1 As Panel
	Private PanelMovie2 As Panel
	Private PanelMovie3 As Panel
	Private PanelMovie4 As Panel
	Private PanelMovie5 As Panel
	Private PanelMovie6 As Panel
	Private PanelMovie7 As Panel
	Private PanelMovie8 As Panel
	Private PanelMovie9 As Panel
	Private PanelMovie10 As Panel
	
	
	
	'label click to nagivate generate
	Private DramaPage As Label
	Private HomePage As Label
	Private SciFiPage As Label
	Private ActionPage As Label
	
	'Cast label generate
	Private Starter1 As Label
	Private Starter2 As Label
	Private Starter3 As Label
	Private Starter4 As Label
	Private Starter5 As Label
	Private Starter6 As Label
	Private Starter7 As Label
	Private Starter8 As Label
	Private Starter9 As Label
	Private Starter10 As Label
	
	'Overview label generate
	Private OverView1 As Label
	Private OverView2 As Label
	Private OverView3 As Label
	Private OverView4 As Label
	Private OverView5 As Label
	Private OverView6 As Label
	Private OverView7 As Label
	Private OverView8 As Label
	Private OverView9 As Label
	Private OverView10 As Label
	'Year laben generate
	Private Year1 As Label
	Private Year2 As Label
	Private Year3 As Label
	Private Year4 As Label
	Private Year5 As Label
	Private Year6 As Label
	Private Year7 As Label
	Private Year8 As Label
	Private Year9 As Label
	Private Year10 As Label
	
	'not found label generate
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("action") ' Layout contains ScrollView1
	
	p.Initialize("")
	p.LoadLayout("panelview")
	
	
	

	' Set initial images and texts

	Drama1.Text = "Crank"
	Starter1.Text = "Starring: Jason Statham, Amy Smart, Carlos Sanz"
	Year1.Text = "(2006)"
	OverView1.Text = "Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."
	DramaImage1.Gravity = Gravity.FILL
	DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "kramer.jpg")
	
	Drama2.Text = "Sherlock Holmes "
	Starter2.Text = "Starring: Robert Downey Jr., Jude Law, Rachel McAdams"
	Year2.Text = "(2008)"
	OverView2.Text = "Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."
	DramaImage2.Gravity = Gravity.FILL
	DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "manchester.jpg")
	
	Drama3.Text = "The Transporter"
	Starter3.Text = "Starring: Jason Statham, Shu Qi, Matt Schulze"
	Year3.Text = "(2002)"
	OverView3.Text = "Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."
	DramaImage3.Gravity = Gravity.FILL
	DramaImage3.Bitmap = LoadBitmap(File.DirAssets, "master.jpg")
	
	Drama4.Text = "Avengers: Endgame"
	Starter4.Text = "Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"
	Year4.Text = "(2019)"
	OverView4.Text = "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."
	DramaImage4.Gravity = Gravity.FILL
	DramaImage4.Bitmap = LoadBitmap(File.DirAssets, "millondolar.jpg")
	
	Drama5.Text = "Logan"
	Starter5.Text = "Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"
	Year5.Text = "(2017)"
	OverView5.Text = "In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."
	DramaImage5.Gravity = Gravity.FILL
	DramaImage5.Bitmap = LoadBitmap(File.DirAssets, "bridges.jpg")
	
	
	Drama6.Text = "Iron Man"
	Starter6.Text = "Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"
	Year6.Text = "(2008)"
	OverView6.Text = "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."
	DramaImage6.Gravity = Gravity.FILL
	DramaImage6.Bitmap = LoadBitmap(File.DirAssets, "gonebaby.jpg")
	
	Drama7.Text = "X-Men"
	Starter7.Text = "Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"
	Year7.Text = "(2000)"
	OverView7.Text = "In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."
	DramaImage7.Gravity = Gravity.FILL
	DramaImage7.Bitmap = LoadBitmap(File.DirAssets, "bluejasmine.jpg")
	
	Drama8.Text = "Mr. & Mrs. Smith "
	Starter8.Text = "Starring: Brad Pitt, Angelina Jolie, Adam Brody"
	Year8.Text = "(2005)"
	OverView8.Text = "A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."
	DramaImage8.Gravity = Gravity.FILL
	DramaImage8.Bitmap = LoadBitmap(File.DirAssets, "her.jpg")
	
	Drama9.Text = "The Wolverine"
	Starter9.Text = "Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"
	Year9.Text = "(2015)"
	OverView9.Text = "A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."
	DramaImage9.Gravity = Gravity.FILL
	DramaImage9.Bitmap = LoadBitmap(File.DirAssets, "carol.jpg")
	
	Drama10.Text = "Prisoners"
	Starter10.Text = "Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"
	Year10.Text = "(2013)"
	OverView10.Text = "A desperate father takes the law into his own hands after police fail to find two kidnapped girls."
	DramaImage10.Gravity = Gravity.FILL
	DramaImage10.Bitmap = LoadBitmap(File.DirAssets, "lostdaughter.jpg")
	
	ScrollView1.Panel.AddView(p, 0, 0, 200%x, 210%y)
	ScrollView1.Panel.Height = p.Height
End Sub

Private Sub SearchBtn_Click 
	SearchNow
End Sub


Sub SearchNow
	Dim query As String = SearchEngine.Text.ToLowerCase.Trim    
	' Hide all panels by default
	
	Dim UserInput As String = SearchEngine.Text
	
    
	If query.Contains("crank") Then
		' movie 1
		Drama1.Text = "Crank"
		Starter1.Text = "Starring: Jason Statham, Amy Smart, Carlos Sanz"
		Year1.Text = "(2006)"
		OverView1.Text = "Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "kramer.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("sherlock") Or query.Contains("sherlock holmes") Then
		' movie 2
		Drama1.Text = "Sherlock Holmes "
		Starter1.Text = "Starring: Robert Downey Jr., Jude Law, Rachel McAdams"
		Year1.Text = "(2008)"
		OverView1.Text = "Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "manchester.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("the transporter") Or query.Contains("transporter") Then
		' movie 3
		Drama1.Text = "The Transporter"
		Starter1.Text = "Starring: Jason Statham, Shu Qi, Matt Schulze"
		Year1.Text = "(2002)"
		OverView1.Text = "Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "master.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("avengers endgame") Or query.Contains("avengers") Or query.Contains("endgame") Then
		' movie 4
		Drama1.Text = "Avengers: Endgame"
		Starter1.Text = "Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"
		Year1.Text = "(2019)"
		OverView1.Text = "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "millondolar.jpg")
		
	
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("logan") Then
		' movie 5
		Drama1.Text = "Logan"
		Starter1.Text = "Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"
		Year1.Text = "(2017)"
		OverView1.Text = "In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "bridges.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("iron man") Or query.Contains("man") Or query.Contains("iron") Then
		' movie 6
		Drama1.Text = "Iron Man"
		Starter1.Text = "Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"
		Year1.Text = "(2008)"
		OverView1.Text = "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "gonebaby.jpg")

		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("x-men") Or query.Contains("men") Or query.Contains("xmen") Or query.Contains("x") Then
		' movie 7
		Drama7.Text = "X-Men"
		Starter7.Text = "Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"
		Year7.Text = "(2000)"
		OverView7.Text = "In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."
		DramaImage7.Gravity = Gravity.FILL
		DramaImage7.Bitmap = LoadBitmap(File.DirAssets, "bluejasmine.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("mr & mrs smith") Or query.Contains("mr and mrs") Or query.Contains("smith") Or query.Contains("mrs") Or query.Contains("mr") Then
		' movie 8
		Drama1.Text = "Mr. & Mrs. Smith "
		Starter1.Text = "Starring: Brad Pitt, Angelina Jolie, Adam Brody"
		Year1.Text = "(2005)"
		OverView1.Text = "A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "her.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("the wolverine") Or query.Contains("wolverine")  Then
		' movie 9
		Drama1.Text = "The Wolverine"
		Starter1.Text = "Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"
		Year1.Text = "(2015)"
		OverView1.Text = "A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "carol.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("prisoners") Or query.Contains("prisoner") Or query.Contains("pri") Then
		' movie 10
		Drama10.Text = "Prisoners"
		Starter10.Text = "Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"
		Year10.Text = "(2013)"
		OverView10.Text = "A desperate father takes the law into his own hands after police fail to find two kidnapped girls."
		DramaImage10.Gravity = Gravity.FILL
		DramaImage10.Bitmap = LoadBitmap(File.DirAssets, "lostdaughter.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		'cast
	Else If query.Contains("jason statham") Or query.Contains("jason") Or query.Contains("statham") Then
		Drama1.Text = "Crank"
		Starter1.Text = "Starring: Jason Statham, Amy Smart, Carlos Sanz"
		Year1.Text = "(2006)"
		OverView1.Text = "Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "kramer.jpg")
		
		Drama2.Text = "The Transporter"
		Starter2.Text = "Starring: Jason Statham, Shu Qi, Matt Schulze"
		Year2.Text = "(2002)"
		OverView2.Text = "Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "master.jpg")
		
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("Robert downey jr") Or query.Contains("robert") Or query.Contains("downey") Or query.Contains("downey jr") Then
		Drama1.Text = "Sherlock Holmes "
		Starter1.Text = "Starring: Robert Downey Jr., Jude Law, Rachel McAdams"
		Year1.Text = "(2008)"
		OverView1.Text = "Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "manchester.jpg")
		
		Drama2.Text = "Avengers: Endgame"
		Starter2.Text = "Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"
		Year2.Text = "(2019)"
		OverView2.Text = "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "millondolar.jpg")
		
		Drama3.Text = "Iron Man"
		Starter3.Text = "Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"
		Year3.Text = "(2008)"
		OverView3.Text = "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."
		DramaImage3.Gravity = Gravity.FILL
		DramaImage3.Bitmap = LoadBitmap(File.DirAssets, "gonebaby.jpg")
		
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 85%y
	
	Else If query.Contains("patrick stewart") Or query.Contains("patrick") Or query.Contains("stewart") Then
		Drama1.Text = "Logan"
		Starter1.Text = "Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"
		Year1.Text = "(2017)"
		OverView1.Text = "In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "bridges.jpg")
		
		Drama2.Text = "X-Men"
		Starter2.Text = "Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"
		Year2.Text = "(2000)"
		OverView2.Text = "In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "bluejasmine.jpg")
		
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
	
	Else If query.Contains("hugh jackman") Or query.Contains("hugh") Or query.Contains("jackman") Then
		Drama1.Text = "Logan"
		Starter1.Text = "Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"
		Year1.Text = "(2017)"
		OverView1.Text = "In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "bridges.jpg")
		
		Drama2.Text = "X-Men"
		Starter2.Text = "Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"
		Year2.Text = "(2000)"
		OverView2.Text = "In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "bluejasmine.jpg")
		
		Drama3.Text = "The Wolverine"
		Starter3.Text = "Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"
		Year3.Text = "(2015)"
		OverView3.Text = "A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."
		DramaImage3.Gravity = Gravity.FILL
		DramaImage3.Bitmap = LoadBitmap(File.DirAssets, "carol.jpg")
		
		Drama4.Text = "Prisoners"
		Starter4.Text = "Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"
		Year4.Text = "(2013)"
		OverView4.Text = "A desperate father takes the law into his own hands after police fail to find two kidnapped girls."
		DramaImage4.Gravity = Gravity.FILL
		DramaImage4.Bitmap = LoadBitmap(File.DirAssets, "lostdaughter.jpg")
		
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 85%y
		'single cast or one movie only
	Else If query.Contains("amy smart") Or query.Contains("amy") Or query.Contains("smart") Or query.Contains("carlos sanz") Or query.Contains("carlos") Or query.Contains("sanz") Then
		Drama1.Text = "Crank"
		Starter1.Text = "Starring: Jason Statham, Amy Smart, Carlos Sanz"
		Year1.Text = "(2006)"
		OverView1.Text = "Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "kramer.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
	Else If query.Contains("jude law") Or query.Contains("jude") Or query.Contains("law") Or query.Contains("rachel mcadams") Or query.Contains("rachel") Or query.Contains("mcadams")Then
		Drama1.Text = "Sherlock Holmes "
		Starter1.Text = "Starring: Robert Downey Jr., Jude Law, Rachel McAdams"
		Year1.Text = "(2008)"
		OverView1.Text = "Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "manchester.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
	Else if query.Contains("shu qi") Or query.Contains("shu") Or query.Contains("qi") Or query.Contains("matt") Or query.Contains("schulze") Or query.Contains("matt schulze") Then
		Drama1.Text = "The Transporter"
		Starter1.Text = "Starring: Jason Statham, Shu Qi, Matt Schulze"
		Year1.Text = "(2002)"
		OverView1.Text = "Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "master.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
	Else if query.Contains("chris evans") Or query.Contains("chris") Or query.Contains("evans") Or query.Contains("mark ruffalo") Or query.Contains("mark") Or query.Contains("ruffalo") Then
		Drama1.Text = "Avengers: Endgame"
		Starter1.Text = "Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"
		Year1.Text = "(2019)"
		OverView1.Text = "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "millondolar.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
	Else if query.Contains("dafne keen") Or query.Contains("dafne") Or query.Contains("keen") Then
		Drama1.Text = "Logan"
		Starter1.Text = "Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"
		Year1.Text = "(2017)"
		OverView1.Text = "In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "bridges.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else if query.Contains("gwyneth paltrow") Or query.Contains("gwyneth") Or query.Contains("paltrow") Or query.Contains("terrence howard") Or query.Contains("terrence") Or query.Contains("howard") Then
		Drama1.Text = "Iron Man"
		Starter1.Text = "Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"
		Year1.Text = "(2008)"
		OverView1.Text = "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "gonebaby.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
	
	Else if query.Contains("ian mckellen") Or query.Contains("ian") Or query.Contains("mckellen") Then
		Drama1.Text = "X-Men"
		Starter1.Text = "Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"
		Year1.Text = "(2000)"
		OverView1.Text = "In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "bluejasmine.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
	
	Else if query.Contains("brad pitt") Or query.Contains("brad") Or query.Contains("pitt") Or query.Contains("angelina jolie") Or query.Contains("angelina") Or query.Contains("jolie") Or query.Contains("adam brody") Or query.Contains("adam") Or query.Contains("brody") Then
		Drama1.Text = "Mr. & Mrs. Smith "
		Starter1.Text = "Starring: Brad Pitt, Angelina Jolie, Adam Brody"
		Year1.Text = "(2005)"
		OverView1.Text = "A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "her.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
		
		
	Else if query.Contains("will yun lee") Or query.Contains("will") Or query.Contains("yun") Or query.Contains("lee") Or query.Contains("tao okamoto") Or query.Contains("tao") Or query.Contains("okamoto") Then
		Drama1.Text = "The Wolverine"
		Starter1.Text = "Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"
		Year1.Text = "(2015)"
		OverView1.Text = "A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "carol.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else if query.Contains("jake gyllenhaal") Or query.Contains("jake") Or query.Contains("gyllenhaal") Or query.Contains("viola davis") Or query.Contains("viola") Or query.Contains("davis") Then
		Drama1.Text = "Prisoners"
		Starter1.Text = "Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"
		Year1.Text = "(2013)"
		OverView1.Text = "A desperate father takes the law into his own hands after police fail to find two kidnapped girls."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "lostdaughter.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else
		
		MsgboxAsync("No results found for" & " """ & UserInput & """" , "")
	
	End If

	p.Width = 100%x
	ScrollView1.Panel.Height = p.Height
	
End Sub




Sub SearchEngine_TextChanged (Old As String, New As String)
	Dim query As String = New.ToLowerCase.Trim

	If query = "" Then
		' Reset layout
		
		p.Height = 210%y
		p.Width = 200%x
		ScrollView1.Panel.Height = p.Height
		
		PanelMovie1.Visible = True
		PanelMovie2.Visible = True
		PanelMovie3.Visible = True
		PanelMovie4.Visible = True
		PanelMovie5.Visible = True
		PanelMovie6.Visible = True
		PanelMovie7.Visible = True
		PanelMovie8.Visible = True
		PanelMovie9.Visible = True
		PanelMovie10.Visible = True
		
		' Restore original texts
		
		Drama1.Text = "Crank"
		Starter1.Text = "Starring: Jason Statham, Amy Smart, Carlos Sanz"
		Year1.Text = "(2006)"
		OverView1.Text = "Professional assassin Chev Chelios learns his rival has injected him with a poison that will kill him if his heart rate drops."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "kramer.jpg")
	
		Drama2.Text = "Sherlock Holmes"
		Starter2.Text = "Starring: Robert Downey Jr., Jude Law, Rachel McAdams"
		Year2.Text = "(2008)"
		OverView2.Text = "Detective Sherlock Holmes and his stalwart partner Watson engage in a battle of wits and brawn with a nemesis whose plot is a threat to all of England."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "manchester.jpg")
	
		Drama3.Text = "The Transporter"
		Starter3.Text = "Starring: Jason Statham, Shu Qi, Matt Schulze"
		Year3.Text = "(2002)"
		OverView3.Text = "Frank Martin, who transports packages for unknown clients, is asked to move a package that soon begins moving, and complications arise."
		DramaImage3.Gravity = Gravity.FILL
		DramaImage3.Bitmap = LoadBitmap(File.DirAssets, "master.jpg")
	
		Drama4.Text = "Avengers: Endgame"
		Starter4.Text = "Starring: Robert Downey Jr., Chris Evans, Mark Ruffalo"
		Year4.Text = "(2019)"
		OverView4.Text = "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe."
		DramaImage4.Gravity = Gravity.FILL
		DramaImage4.Bitmap = LoadBitmap(File.DirAssets, "millondolar.jpg")
	
		Drama5.Text = "Logan"
		Starter5.Text = "Starring: Hugh Jackman, Patrick Stewart, Dafne Keen"
		Year5.Text = "(2017)"
		OverView5.Text = "In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."
		DramaImage5.Gravity = Gravity.FILL
		DramaImage5.Bitmap = LoadBitmap(File.DirAssets, "bridges.jpg")
	
	
		Drama6.Text = "Iron Man"
		Starter6.Text = "Starring: Robert Downey Jr., Gwyneth Paltrow, Terrence Howard"
		Year6.Text = "(2008)"
		OverView6.Text = "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil."
		DramaImage6.Gravity = Gravity.FILL
		DramaImage6.Bitmap = LoadBitmap(File.DirAssets, "gonebaby.jpg")
	
		Drama7.Text = "X-Men"
		Starter7.Text = "Starring: Patrick Stewart, Hugh Jackman, Ian McKellen"
		Year7.Text = "(2000)"
		OverView7.Text = "In a world where mutants (evolved super-powered humans) exist and are discriminated against, two groups form for an inevitable clash: the supremacist Brotherhood, and the pacifist X-Men."
		DramaImage7.Gravity = Gravity.FILL
		DramaImage7.Bitmap = LoadBitmap(File.DirAssets, "bluejasmine.jpg")
	
		Drama8.Text = "Mr. & Mrs. Smith"
		Starter8.Text = "Starring: Brad Pitt, Angelina Jolie, Adam Brody"
		Year8.Text = "(2005)"
		OverView8.Text = "A husband and wife struggle to keep their marriage alive until they realize they are both secretly working as assassins. Now, their respective assignments require them to kill each other."
		DramaImage8.Gravity = Gravity.FILL
		DramaImage8.Bitmap = LoadBitmap(File.DirAssets, "her.jpg")
	
		Drama9.Text = "The Wolverine"
		Starter9.Text = "Starring: Hugh Jackman, Will Yun Lee, Tao Okamoto"
		Year9.Text = "(2015)"
		OverView9.Text = "A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."
		DramaImage9.Gravity = Gravity.FILL
		DramaImage9.Bitmap = LoadBitmap(File.DirAssets, "carol.jpg")
	
		Drama10.Text = "Prisoners"
		Starter10.Text = "Starring: Hugh Jackman, Jake Gyllenhaal, Viola Davis"
		Year10.Text = "(2013)"
		OverView10.Text = "A desperate father takes the law into his own hands after police fail to find two kidnapped girls."
		DramaImage10.Gravity = Gravity.FILL
		DramaImage10.Bitmap = LoadBitmap(File.DirAssets, "lostdaughter.jpg")


		
		
	End If
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub



Private Sub SciFiPage_Click
	StartActivity(SciFi)
	Activity.Finish
End Sub

Private Sub ActionPage_Click
	
End Sub

Private Sub DramaPage_Click
	StartActivity(Drama)
	Activity.Finish
End Sub

Private Sub HomePage_Click
	StartActivity(Main)
	Activity.Finish
End Sub

Private Sub PanelMovie1_Click
	Try
		Msgbox2Async("Want to watch the trailer of the movie?", "Go to Trailer", "Yes", "", "No", Null, False)
		Wait For Msgbox_Result (Result As Int)
		If Result = DialogResponse.POSITIVE Then
			Dim i As Intent
			i.Initialize(i.ACTION_VIEW, "https://www.imdb.com/title/tt0479884/")
			i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main")
			StartActivity(i)
		End If
		
	Catch
		Log(LastException)
		MsgboxAsync("can't find Chome app", "Error")
	End Try
	
End Sub


Private Sub PanelMovie2_Click
	Try
		Msgbox2Async("Want to watch the trailer of the movie?", "Go to", "Yes", "", "No", Null, False)
		Wait For Msgbox_Result (Result As Int)
		If Result = DialogResponse.POSITIVE Then
			Dim i As Intent
			i.Initialize(i.ACTION_VIEW, "https://www.imdb.com/title/tt0988045/")
			i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main")
			StartActivity(i)
		End If
	Catch
		Log(LastException)
		MsgboxAsync("can't find Chome app", "Error")
	End Try
	
End Sub

Private Sub PanelMovie3_Click
	Try
		Msgbox2Async("Want to watch the trailer of the movie?", "Go to Trailer", "Yes", "", "No", Null, False)
		Wait For Msgbox_Result (Result As Int)
		If Result = DialogResponse.POSITIVE Then
			Dim i As Intent
			i.Initialize(i.ACTION_VIEW, "https://www.imdb.com/title/tt4154796/")
			i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main")
			StartActivity(i)
		End If
	Catch
		Log(LastException)
		MsgboxAsync("can't find Chome app", "Error")
	End Try
	
End Sub

Private Sub PanelMovie4_Click
	Try
		Msgbox2Async("Want to watch the trailer of the movie?", "Go to Trailer", "Yes", "", "No", Null, False)
		Wait For Msgbox_Result (Result As Int)
		If Result = DialogResponse.POSITIVE Then
			Dim i As Intent
			i.Initialize(i.ACTION_VIEW, "https://www.imdb.com/title/tt3315342/")
			i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main")
			StartActivity(i)
		End If
	Catch
		Log(LastException)
		MsgboxAsync("can't find Chome app", "Error")
	End Try
	
End Sub

Private Sub PanelMovie5_Click
	Try
		Msgbox2Async("Want to watch the trailer of the movie?", "Go to Trailer", "Yes", "", "No", Null, False)
		Wait For Msgbox_Result (Result As Int)
		If Result = DialogResponse.POSITIVE Then
			Dim i As Intent
			i.Initialize(i.ACTION_VIEW, "https://www.imdb.com/title/tt0371746/")
			i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main")
			StartActivity(i)
		End If
	Catch
		Log(LastException)
		MsgboxAsync("can't find Chome app", "Error")
	End Try
	
End Sub

Private Sub PanelMovie6_Click
	Try
		Msgbox2Async("Want to watch the trailer of the movie?", "Go to Trailer", "Yes", "", "No", Null, False)
		Wait For Msgbox_Result (Result As Int)
		If Result = DialogResponse.POSITIVE Then
			Dim i As Intent
			i.Initialize(i.ACTION_VIEW, "https://www.imdb.com/title/tt0120903/")
			i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main")
			StartActivity(i)
		End If
	Catch
		Log(LastException)
		MsgboxAsync("can't find Chome app", "Error")
	End Try
	
End Sub

Private Sub PanelMovie7_Click
	Try
		Msgbox2Async("Want to watch the trailer of the movie?", "Go to Trailer", "Yes", "", "No", Null, False)
		Wait For Msgbox_Result (Result As Int)
		If Result = DialogResponse.POSITIVE Then
			Dim i As Intent
			i.Initialize(i.ACTION_VIEW, "https://www.imdb.com/title/tt2334873/")
			i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main")
			StartActivity(i)
		End If
	Catch
		Log(LastException)
		MsgboxAsync("can't find Chome app", "Error")
	End Try
	
End Sub

Private Sub PanelMovie8_Click
	Try
		Msgbox2Async("Want to watch the trailer of the movie?", "Go to Trailer", "Yes", "", "No", Null, False)
		Wait For Msgbox_Result (Result As Int)
		If Result = DialogResponse.POSITIVE Then
			Dim i As Intent
			i.Initialize(i.ACTION_VIEW, "https://www.imdb.com/title/tt0356910/")
			i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main")
			StartActivity(i)
		End If
	Catch
		Log(LastException)
		MsgboxAsync("can't find Chome app", "Error")
	End Try
	
End Sub

Private Sub PanelMovie9_Click
	Try
		Msgbox2Async("Want to watch the trailer of the movie?", "Go to Trailer", "Yes", "", "No", Null, False)
		Wait For Msgbox_Result (Result As Int)
		If Result = DialogResponse.POSITIVE Then
			Dim i As Intent
			i.Initialize(i.ACTION_VIEW, "https://www.imdb.com/title/tt1430132/")
			i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main")
			StartActivity(i)
		End If
	Catch
		Log(LastException)
		MsgboxAsync("can't find Chome app", "Error")
	End Try
	
End Sub

Private Sub PanelMovie10_Click
	Try
		Msgbox2Async("Want to watch the trailer of the movie?", "Go to Trailer", "Yes", "", "No", Null, False)
		Wait For Msgbox_Result (Result As Int)
		If Result = DialogResponse.POSITIVE Then
			Dim i As Intent
			i.Initialize(i.ACTION_VIEW, "https://www.imdb.com/title/tt1392214/")
			i.SetComponent("com.android.chrome/com.google.android.apps.chrome.Main")
			StartActivity(i)
		End If
	Catch
		Log(LastException)
		MsgboxAsync("can't find Chome app", "Error")
	End Try
	
End Sub