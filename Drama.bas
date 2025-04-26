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
	Private Panel2 As Panel
	Private Panel3 As Panel
	Private Panel4 As Panel
	Private Panel5 As Panel
	Private Panel6 As Panel
	Private Panel7 As Panel
	Private Panel8 As Panel
	Private Panel9 As Panel
	Private Panel10 As Panel
	Private Panel11 As Panel
	
	
	
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
	Private NotFound As Label
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("Drama") ' Layout contains ScrollView1
	
	p.Initialize("")
	p.LoadLayout("panelview")
	
	
	

	' Set initial images and texts

	Drama1.Text = "Kramer vs. Kramer"
	Starter1.Text = "Starring: Dustin Hoffman, Meryl Streep, Justin Henry"
	Year1.Text = "(1979)"
	OverView1.Text = "In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"
	DramaImage1.Gravity = Gravity.FILL
	DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "kramer.jpg")
	
	Drama2.Text = "Manchester by the Sea"
	Starter2.Text = "Starring: Casey Affleck, Michelle Williams, Lucas Hedges"
	Year2.Text = "(2016)"
	OverView2.Text = "After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."
	DramaImage2.Gravity = Gravity.FILL
	DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "manchester.jpg")
	
	Drama3.Text = "The Master"
	Starter3.Text = "Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"
	Year3.Text = "(2012)"
	OverView3.Text = "A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."
	DramaImage3.Gravity = Gravity.FILL
	DramaImage3.Bitmap = LoadBitmap(File.DirAssets, "master.jpg")
	
	Drama4.Text = "Million Dollar Baby"
	Starter4.Text = "Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"
	Year4.Text = "(2004)"
	OverView4.Text = "A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."
	DramaImage4.Gravity = Gravity.FILL
	DramaImage4.Bitmap = LoadBitmap(File.DirAssets, "millondolar.jpg")
	
	Drama5.Text = "The Bridges of Madison County"
	Starter5.Text = "Starring: Clint Eastwood, Meryl Streep"
	Year5.Text = "(1995)"
	OverView5.Text = "A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."
	DramaImage5.Gravity = Gravity.FILL
	DramaImage5.Bitmap = LoadBitmap(File.DirAssets, "bridges.jpg")
	
	
	Drama6.Text = "Gone Baby Gone"
	Starter6.Text = "Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"
	Year6.Text = "(2007)"
	OverView6.Text = "In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."
	DramaImage6.Gravity = Gravity.FILL
	DramaImage6.Bitmap = LoadBitmap(File.DirAssets, "gonebaby.jpg")
	
	Drama7.Text = "Blue Jasmine"
	Starter7.Text = "Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"
	Year7.Text = "(2013)"
	OverView7.Text = "After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."
	DramaImage7.Gravity = Gravity.FILL
	DramaImage7.Bitmap = LoadBitmap(File.DirAssets, "bluejasmine.jpg")
	
	Drama8.Text = "Her"
	Starter8.Text = "Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"
	Year8.Text = "(2013)"
	OverView8.Text = "In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."
	DramaImage8.Gravity = Gravity.FILL
	DramaImage8.Bitmap = LoadBitmap(File.DirAssets, "her.jpg")
	
	Drama9.Text = "Carol"
	Starter9.Text = "Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"
	Year9.Text = "(2015)"
	OverView9.Text = "A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."
	DramaImage9.Gravity = Gravity.FILL
	DramaImage9.Bitmap = LoadBitmap(File.DirAssets, "carol.jpg")
	
	Drama10.Text = "The Lost Daughter"
	Starter10.Text = "Starring: Olivia Colman, Dakota Johnson, Jessie Buckley"
	Year10.Text = "(2021)"
	OverView10.Text = "A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity"
	DramaImage10.Gravity = Gravity.FILL
	DramaImage10.Bitmap = LoadBitmap(File.DirAssets, "lostdaughter.jpg")
	
	ScrollView1.Panel.AddView(p, 0, 0, 200%x, 210%y)
	ScrollView1.Panel.Height = p.Height
End Sub



Private Sub SearchBtn_Click
	Dim query As String = SearchEngine.Text.ToLowerCase.Trim
	
	Dim UserInput As String = SearchEngine.Text
    
	' Hide all panels by default
	
    
	If query.Contains("kramer") Or query.Contains("kramer vs kramer") Then
		' movie 1
		Drama1.Text = "Kramer vs. Kramer"
		Starter1.Text = "Starring: Dustin Hoffman, Meryl Streep, Justin Henry"
		Year1.Text = "(1979)"
		OverView1.Text = "In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "kramer.jpg")
		
		Panel3.Visible = False
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("manchester by the Sea") Or query.Contains("manchester") Then
		' movie 2
		Drama1.Text = "Manchester by the Sea"
		Starter1.Text = "Starring: Casey Affleck, Michelle Williams, Lucas Hedges"
		Year1.Text = "(2016)"
		OverView1.Text = "After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "manchester.jpg")
		
		
		Panel3.Visible = False
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("the master") Or query.Contains("master") Then
		' movie 3
		Drama1.Text = "The Master"
		Starter1.Text = "Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"
		Year1.Text = "(2012)"
		OverView1.Text = "A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "master.jpg")
		
		Panel3.Visible = False
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("million dollar") Or query.Contains("million") Or query.Contains("million dollar baby") Then
		' movie 4
		Drama1.Text = "Million Dollar Baby"
		Starter1.Text = "Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"
		Year1.Text = "(2004)"
		OverView1.Text = "A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "millondolar.jpg")
		
		Panel3.Visible = False
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("the Bridges of madison country") Or query.Contains("Bridge") Or query.Contains("bridges of madison") Then
		' movie 5
		Drama1.Text = "The Bridges of Madison County"
		Starter1.Text = "Starring: Clint Eastwood, Meryl Streep"
		Year1.Text = "(1995)"
		OverView1.Text = "A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "bridges.jpg")
		
		Panel3.Visible = False
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("gone") Or query.Contains("gone baby gone") Or query.Contains("gone baby") Then
		' movie 6
		Drama1.Text = "Gone Baby Gone"
		Starter1.Text = "Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"
		Year1.Text = "(2007)"
		OverView1.Text = "In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."

		Panel3.Visible = False
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("blue") Or query.Contains("blue jasmine") Then
		' movie 7
		
		Drama1.Text = "Blue Jasmine"
		Starter1.Text = "Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"
		Year1.Text = "(2013)"
		OverView1.Text = "After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "gonebaby.jpg")
		
		Panel3.Visible = False
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("her") Then
		' movie 8
		Drama1.Text = "Her"
		Starter1.Text = "Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"
		Year1.Text = "(2013)"
		OverView1.Text = "In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "her.jpg")
		
		Panel3.Visible = False
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("carol") Or query.Contains("sarah paulson") Or query.Contains("sarah") Or query.Contains("paulson") Then
		' movie 9
		Drama1.Text = "Carol"
		Starter1.Text = "Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"
		Year1.Text = "(2015)"
		OverView1.Text = "A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "carol.jpg")
		
		Panel3.Visible = False
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("the lost daugther") Or query.Contains("lost daughter") Or query.Contains("lost") Then
		' movie 10
		Drama1.Text = "The Lost Daughter"
		Starter1.Text = "Starring: Olivia Colman, Dakota Johnson, Jessie Buckley"
		Year1.Text = "(2021)"
		OverView1.Text = "A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity"
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "lostdaughter.jpg")
		
		Panel3.Visible = False
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 70%y		
	Else If query.Contains("doubt") Then
		Drama1.Text = "Doubt"
		Starter1.Text = "Starring: Meryl Streep, Philip Seymour Hoffman, Amy Adams"
		Year1.Text = "(2008)"
		OverView1.Text = "In a Catholic school in the Bronx, a strict nun becomes suspicious of a priest’s relationship with a student. As she digs deeper, the lines between truth and suspicion blur, leaving everyone in moral and emotional limbo."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "doubt.jpg")
		
		Panel3.Visible = False
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		p.Height = 70%y
		
	'cast
	Else If query.Contains("meryl streep") Or query.Contains("meryl") Or query.Contains("streep") Then
		Drama1.Text = "The Bridges of Madison County"
		Starter1.Text = "Starring: Clint Eastwood, Meryl Streep"
		Year1.Text = "(1995)"
		OverView1.Text = "A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "bridges.jpg")
		
		Drama2.Text = "Doubt"
		Starter2.Text = "Starring: Meryl Streep, Philip Seymour Hoffman, Amy Adams"
		Year2.Text = "(2008)"
		OverView2.Text = "In a Catholic school in the Bronx, a strict nun becomes suspicious of a priest’s relationship with a student. As she digs deeper, the lines between truth and suspicion blur, leaving everyone in moral and emotional limbo."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "doubt.jpg")
		
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		p.Height = 70%y
	Else If query.Contains("casey") Or query.Contains("casey affleck") Then
		Drama1.Text = "Gone Baby Gone"
		Starter1.Text = "Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"
		Year1.Text = "(2007)"
		OverView1.Text = "In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "gonebaby.jpg")
		
		Drama2.Text = "Manchester by the Sea"
		Starter2.Text = "Starring: Casey Affleck, Michelle Williams, Lucas Hedges"
		Year2.Text = "(2016)"
		OverView2.Text = "After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "manchester.jpg")
		
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		p.Height = 70%y
	
	Else If query.Contains("joaquin") Or query.Contains("joaquin phoenix") Or query.Contains("amy adams") Or query.Contains("amy") Then
		Drama1.Text = "The Master"
		Starter1.Text = "Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"
		Year1.Text = "(2012)"
		OverView1.Text = "A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "master.jpg")
		
		Drama2.Text = "Her"
		Starter2.Text = "Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"
		Year2.Text = "(2013)"
		OverView2.Text = "In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "her.jpg")
		
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 70%y
	
	Else If query.Contains("cate blanchett") Or query.Contains("cate") Then
		Drama1.Text = "Carol"
		Starter1.Text = "Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"
		Year1.Text = "(2015)"
		OverView1.Text = "A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "carol.jpg")
		
		Drama2.Text = "Blue Jasmine"
		Starter2.Text = "Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"
		Year2.Text = "(2013)"
		OverView2.Text = "After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "bluejasmine.jpg")
		
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("morgan freeman") Or query.Contains("morgan") Then
		Drama1.Text = "Million Dollar Baby"
		Starter1.Text = "Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"
		Year1.Text = "(2004)"
		OverView1.Text = "A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "millondolar.jpg")
		
		Drama2.Text = "Gone Baby Gone"
		Starter2.Text = "Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"
		Year2.Text = "(2007)"
		OverView2.Text = "In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "gonebaby.jpg")
		
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("clint eastwood") Or query.Contains("clint") Or query.Contains("eastwood") Then
		Drama1.Text = "Million Dollar Baby"
		Starter1.Text = "Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"
		Year1.Text = "(2004)"
		OverView1.Text = "A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "millondolar.jpg")
		
		Drama2.Text = "The Bridges of Madison Country"
		Starter2.Text = "Starring: Clint Eastwood, Meryl Streep"
		Year2.Text = "(1995)"
		OverView2.Text = "A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "bridges.jpg")
		
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("amy Adams") Or query.Contains("amy") Or query.Contains("adams") Then
		
		Drama2.Text = "The Master"
		Starter2.Text = "Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"
		Year2.Text = "(2012)"
		OverView2.Text = "A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "master.jpg")
		
		Drama3.Text = "Her"
		Starter3.Text = "Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"
		Year3.Text = "(2013)"
		OverView3.Text = "In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."
		DramaImage3.Gravity = Gravity.FILL
		DramaImage3.Bitmap = LoadBitmap(File.DirAssets, "her.jpg")
		
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 85%y
	Else If query.Contains("rooney mara") Or query.Contains("rooney") Or query.Contains("mara") Or query.Contains("sarah") Or query.Contains("sarah paulson") Or query.Contains("paulson") Then
		Drama1.Text = "Carol"
		Starter1.Text = "Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"
		Year1.Text = "(2015)"
		OverView1.Text = "A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "carol.jpg")
		
		Drama2.Text = "Blue Jasmine"
		Starter2.Text = "Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"
		Year2.Text = "(2013)"
		OverView2.Text = "After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "bluejasmine.jpg")
		
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 70%y
		
	'single cast or one movie only
	Else If query.Contains("olvia coloman") Or query.Contains("dakota johnson") Or query.Contains("jessie buckley") Or query.Contains("olvia") Or query.Contains("jessie ") Or query.Contains("dakota") Or query.Contains("buckley") Or query.Contains("coloman") Or query.Contains("johnson") Then
		Drama1.Text = "Carol"
		Starter1.Text = "Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"
		Year1.Text = "(2015)"
		OverView1.Text = "A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "carol.jpg")
		
		Panel3.Visible = False
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("sally hawkins") Or query.Contains("alec baldwin") Or query.Contains("sally") Or query.Contains("haswkins") Or query.Contains("alec") Or query.Contains("baldwin")Then
		Drama1.Text = "Blue Jasmine"
		Starter1.Text = "Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"
		Year1.Text = "(2013)"
		OverView1.Text = "After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "bluejasmine.jpg")
		
		Panel3.Visible = False
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 70%y
	Else if query.Contains("michelle williams") Or query.Contains("lucas hedges") Or query.Contains("michelle") Or query.Contains("williams") Or query.Contains("lucas") Or query.Contains("hedges") Then
		Drama1.Text = "Manchester by the Sea"
		Starter1.Text = "Starring: Casey Affleck, Michelle Williams, Lucas Hedges"
		Year1.Text = "(2016)"
		OverView1.Text = "After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "manchester.jpg")
		
		Panel3.Visible = False
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 70%y
	Else if query.Contains("dustin hoffman") Or query.Contains("dustin")  Or query.Contains("justin henry") Or query.Contains("justin") Or query.Contains("henry") Then
		Drama1.Text = "Kramer vs. Kramer"
		Starter1.Text = "Starring: Dustin Hoffman, Meryl Streep, Justin Henry"
		Year1.Text = "(1979)"
		OverView1.Text = "In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "kramer.jpg")
		
		Panel3.Visible = False
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 70%y
	Else If query.Contains("philip seymour hoffman") Or query.Contains("philip") Or query.Contains("seymour") Then
		Drama1.Text = "Doubt"
		Starter1.Text = "Starring: Meryl Streep, Philip Seymour Hoffman, Amy Adams"
		Year1.Text = "(2008)"
		OverView1.Text = "In a Catholic school in the Bronx, a strict nun becomes suspicious of a priest’s relationship with a student. As she digs deeper, the lines between truth and suspicion blur, leaving everyone in moral and emotional limbo."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "doubt.jpg")
		
		
		Drama2.Text = "The Master"
		Starter2.Text = "Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"
		Year2.Text = "(2012)"
		OverView2.Text = "A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "master.jpg")
		
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("hoffman") Then
		Drama1.Text = "Kramer vs. Kramer"
		Starter1.Text = "Starring: Dustin Hoffman, Meryl Streep, Justin Henry"
		Year1.Text = "(1979)"
		OverView1.Text = "In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "kramer.jpg")
		
		Drama2.Text = "Doubt"
		Starter2.Text = "Starring: Meryl Streep, Philip Seymour Hoffman, Amy Adams"
		Year2.Text = "(2008)"
		OverView2.Text = "In a Catholic school in the Bronx, a strict nun becomes suspicious of a priest’s relationship with a student. As she digs deeper, the lines between truth and suspicion blur, leaving everyone in moral and emotional limbo."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "doubt.jpg")
		
		Panel4.Visible = False
		Panel5.Visible = False
		Panel6.Visible = False
		Panel7.Visible = False
		
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
		NotFound.Text = ""
		p.Height = 210%y
		p.Width = 200%x
		ScrollView1.Panel.Height = p.Height
		
		Panel2.Visible = True
		Panel3.Visible = True
		Panel4.Visible = True
		Panel5.Visible = True
		Panel6.Visible = True
		Panel7.Visible = True
		Panel8.Visible = True
		Panel9.Visible = True
		Panel10.Visible = True
		Panel11.Visible = True
		
		Drama1.Text = "Kramer vs. Kramer"
		Starter1.Text = "Starring: Dustin Hoffman, Meryl Streep, Justin Henry"
		Year1.Text = "(1979)"
		OverView1.Text = "In this emotionally charged courtroom and family drama, Kramer vs. Kramer refers to the heartbreaking custody battle between Ted Kramer, a man forced into single fatherhood, and Joanna Kramer, the wife who returns after abandoning their son. As love, responsibility, and personal growth collide, the film asks: what defines a good parent?"
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "kramer.jpg")
	
		Drama2.Text = "Manchester by the Sea"
		Starter2.Text = "Starring: Casey Affleck, Michelle Williams, Lucas Hedges"
		Year2.Text = "(2016)"
		OverView2.Text = "After the death of his brother, a withdrawn and guilt-ridden janitor is forced to return to his hometown and take care of his teenage nephew. As buried trauma resurfaces, he must face his tragic past and confront the limits of personal healing."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "manchester.jpg")
	
		Drama3.Text = "The Master"
		Starter3.Text = "Starring: Philip Seymour Hoffman, Joaquin Phoenix, Amy Adams"
		Year3.Text = "(2012)"
		OverView3.Text = "A mentally unstable WWII veteran is drawn to a charismatic cult leader and his belief system. As their relationship grows, the film explores themes of control, identity, and the search for meaning in a post-war world."
		DramaImage3.Gravity = Gravity.FILL
		DramaImage3.Bitmap = LoadBitmap(File.DirAssets, "master.jpg")
	
		Drama4.Text = "Million Dollar Baby"
		Starter4.Text = "Starring: Morgan Freeman, Hilary Swank, Clint Eastwood"
		Year4.Text = "(2004)"
		OverView4.Text = "A waitress with dreams of becoming a boxer convinces a reluctant trainer to take her on. As she rises through the ranks, a tragic turn of events forces them both to reckon with loss, dignity, and the nature of love."
		DramaImage4.Gravity = Gravity.FILL
		DramaImage4.Bitmap = LoadBitmap(File.DirAssets, "millondolar.jpg")
	
		Drama5.Text = "The Bridges of Madison County"
		Starter5.Text = "Starring: Clint Eastwood, Meryl Streep"
		Year5.Text = "(1995)"
		OverView5.Text = "A brief, passionate romance between a lonely housewife and a traveling photographer leads to a life-changing emotional journey. Set against the backdrop of 1960s Iowa, it’s a heartbreaking story about missed chances and enduring love."
		DramaImage5.Gravity = Gravity.FILL
		DramaImage5.Bitmap = LoadBitmap(File.DirAssets, "bridges.jpg")
	
	
		Drama6.Text = "Gone Baby Gone"
		Starter6.Text = "Starring: Casey Affleck, Michelle Monaghan, Morgan Freeman"
		Year6.Text = "(2007)"
		OverView6.Text = "In a tough Boston neighborhood, a pair of private detectives investigates the kidnapping of a young girl. As they get closer to the truth, they are forced to question what justice really means, and whether doing the right thing is always right."
		DramaImage6.Gravity = Gravity.FILL
		DramaImage6.Bitmap = LoadBitmap(File.DirAssets, "gonebaby.jpg")
	
		Drama7.Text = "Blue Jasmine"
		Starter7.Text = "Starring: Cate Blanchett, Sally Hawkins, Alec Baldwin"
		Year7.Text = "(2013)"
		OverView7.Text = "After losing her fortune and status, a New York socialite is forced to move in with her working-class sister. As her mental state deteriorates, her desperate attempts to reclaim her old life unravel into tragic self-destruction."
		DramaImage7.Gravity = Gravity.FILL
		DramaImage7.Bitmap = LoadBitmap(File.DirAssets, "bluejasmine.jpg")
	
		Drama8.Text = "Her"
		Starter8.Text = "Starring: Joaquin Phoenix, Rooney Mara, Amy Adams"
		Year8.Text = "(2013)"
		OverView8.Text = "In a near-future Los Angeles, a lonely man finds solace in a hyper-intelligent AI voice. As their bond deepens into love, he’s forced to question what it means to be human and whether artificial connection is real connection."
		DramaImage8.Gravity = Gravity.FILL
		DramaImage8.Bitmap = LoadBitmap(File.DirAssets, "her.jpg")
	
		Drama9.Text = "Carol"
		Starter9.Text = "Starring: Rooney Mara, Cate Blanchett, Sarah Paulson"
		Year9.Text = "(2015)"
		OverView9.Text = "A chance encounter between a young aspiring photographer and a glamorous older woman sparks an unexpected romance. Set in the 1950s, this tale of forbidden love explores identity, societal expectations, and emotional resilience."
		DramaImage9.Gravity = Gravity.FILL
		DramaImage9.Bitmap = LoadBitmap(File.DirAssets, "carol.jpg")
	
		Drama10.Text = "The Lost Daughter"
		Starter10.Text = "Starring: Olivia Colman, Dakota Johnson, Jessie Buckley"
		Year10.Text = "(2021)"
		OverView10.Text = "A solitary woman on vacation becomes fascinated by a young mother and her daughter, triggering memories of her own past choices as a parent. The film dives into the complexities of motherhood, regret, and identity"
		DramaImage10.Gravity = Gravity.FILL
		DramaImage10.Bitmap = LoadBitmap(File.DirAssets, "lostdaughter.jpg")


		' Restore original texts
		
	End If
End Sub




Private Sub SciFiPage_Click
	StartActivity(SciFi)
	Activity.Finish
End Sub

Private Sub HomePage_Click
	StartActivity(Main)
	Activity.Finish
End Sub

Private Sub DramaPage_Click
	
End Sub

Private Sub ActionPage_Click
	StartActivity(Action)
	Activity.Finish
End Sub


