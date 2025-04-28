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
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("scifi") ' Layout contains ScrollView1
	
	p.Initialize("")
	p.LoadLayout("panelview")
	
	
	

	' Set initial images and texts

	Drama1.Text = "The Fellowship of the Ring"
	Starter1.Text = "Starring: Elijah Wood, Ian McKellen, Orlando Bloom"
	Year1.Text = "(2001)"
	OverView1.Text = "The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"
	DramaImage1.Gravity = Gravity.FILL
	DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "kramer.jpg")
	
	Drama2.Text = "Charlie and the Chocolate Factory"
	Starter2.Text = "Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"
	Year2.Text = "(2005)"
	OverView2.Text = "Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."
	DramaImage2.Gravity = Gravity.FILL
	DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "manchester.jpg")
	
	Drama3.Text = "Alice in Wonderland"
	Starter3.Text = "Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"
	Year3.Text = "(2010)"
	OverView3.Text = "Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."
	DramaImage3.Gravity = Gravity.FILL
	DramaImage3.Bitmap = LoadBitmap(File.DirAssets, "master.jpg")
	
	Drama4.Text = "Harry Potter and the Philosopher's Stone"
	Starter4.Text = "Starring: Daniel Radcliffe, Rupert Grint, Emma Watson"
	Year4.Text = "(2003)"
	OverView4.Text = "Captain Jack Sparrow must rescue Elizabeth Swann, who has been kidnapped by cursed pirates led by Captain Barbossa. Alongside Will Turner, Jack battles ghostly pirates, seeking both freedom and revenge."
	DramaImage4.Gravity = Gravity.FILL
	DramaImage4.Bitmap = LoadBitmap(File.DirAssets, "millondolar.jpg")
	
	Drama5.Text = "The Curse of the Black Pearl"
	Starter5.Text = "Starring: Johnny Depp, Orlando Bloom, Keira Knightley"
	Year5.Text = "(2003)"
	OverView5.Text = "Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."
	DramaImage5.Gravity = Gravity.FILL
	DramaImage5.Bitmap = LoadBitmap(File.DirAssets, "bridges.jpg")
	
	
	Drama6.Text = "The Chronicles of Narnia"
	Starter6.Text = "Starring: Georgie Henley, Skandar Keynes, William Moseley,"
	Year6.Text = "(2003)"
	OverView6.Text = "Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."
	DramaImage6.Gravity = Gravity.FILL
	DramaImage6.Bitmap = LoadBitmap(File.DirAssets, "gonebaby.jpg")
	
	Drama7.Text = "Doctor Strange"
	Starter7.Text = "Starring: Benedict Cumberbatch, Chiwetel Ejiofor"
	Year7.Text = "(2016)"
	OverView7.Text = "After a life-changing accident ends his career, arrogant surgeon Stephen Strange seeks healing in Nepal, where he learns the mystic arts and must protect the world from dark magical forces beyond reality."
	DramaImage7.Gravity = Gravity.FILL
	DramaImage7.Bitmap = LoadBitmap(File.DirAssets, "bluejasmine.jpg")
	
	Drama8.Text = "V for Vendetta"
	Starter8.Text = "Starring: Hugo Weaving, Natalie Portman, Stephen Rea, John Hurt"
	Year8.Text = "(2005)"
	OverView8.Text = "In a totalitarian future Britain, a masked revolutionary known as ""V"" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."
	DramaImage8.Gravity = Gravity.FILL
	DramaImage8.Bitmap = LoadBitmap(File.DirAssets, "her.jpg")
	
	Drama9.Text = "Aladdin"
	Starter9.Text = "Starring: Mena Massoud, Naomi Scott, Will Smith"
	Year9.Text = "(2019)"
	OverView9.Text = "Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."
	DramaImage9.Gravity = Gravity.FILL
	DramaImage9.Bitmap = LoadBitmap(File.DirAssets, "carol.jpg")
	
	Drama10.Text = "After Earth"
	Starter10.Text = "Starring: Will Smith, Jaden Smith, Sigourney Weaver"
	Year10.Text = "(2013)"
	OverView10.Text = "Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."
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
	
    
	If query.Contains("the fellowship of the ring") Or query.Contains("fellowship") Or query.Contains("the ring") Or query.Contains("ring") Then
		' movie 1
		Drama1.Text = "The Fellowship of the Ring"
		Starter1.Text = "Starring: Elijah Wood, Ian McKellen, Orlando Bloom"
		Year1.Text = "(2001)"
		OverView1.Text = "The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "kramer.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("charlie and the chocolate factory") Or query.Contains("charlie") Or query.Contains("factory") Then
		' movie 2
		Drama1.Text = "Charlie and the Chocolate Factory"
		Starter1.Text = "Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"
		Year1.Text = "(2005)"
		OverView1.Text = "Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "manchester.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("alice in wonderland") Or query.Contains("alice") Or query.Contains("wonderland") Then
		' movie 3
		Drama1.Text = "Alice in Wonderland"
		Starter1.Text = "Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"
		Year1.Text = "(2010)"
		OverView1.Text = "Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "master.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("harry potter and the philosophers stone") Or query.Contains("harry") Or query.Contains("harry potter") Then
		' movie 4
		Drama1.Text = "Harry Potter and the Philosopher's Stone"
		Starter1.Text = "Starring: Daniel Radcliffe, Rupert Grint, Emma Watson"
		Year1.Text = "(2003)"
		OverView1.Text = "Captain Jack Sparrow must rescue Elizabeth Swann, who has been kidnapped by cursed pirates led by Captain Barbossa. Alongside Will Turner, Jack battles ghostly pirates, seeking both freedom and revenge."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "millondolar.jpg")
		
	
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("the curse of the black pearl") Or query.Contains("curse") Or query.Contains("black pearl") Then
		' movie 5
		Drama1.Text = "The Curse of the Black Pearl"
		Starter1.Text = "Starring: Johnny Depp, Orlando Bloom, Keira Knightley"
		Year1.Text = "(2003)"
		OverView1.Text = "Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "bridges.jpg")

		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("the chronicles of narnia") Or query.Contains("chronicles") Or query.Contains("narnia") Then
		' movie 6
		Drama1.Text = "The Chronicles of Narnia"
		Starter1.Text = "Starring: Georgie Henley, Skandar Keynes, William Moseley,"
		Year1.Text = "(2005)"
		OverView1.Text = "Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "gonebaby.jpg")

		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("doctor strange") Or query.Contains("doctor") Or query.Contains("strange") Then
		' movie 7
		Drama1.Text = "Doctor Strange"
		Starter1.Text = "Starring: Benedict Cumberbatch, Chiwetel Ejiofor"
		Year1.Text = "(2016)"
		OverView1.Text = "After a life-changing accident ends his career, arrogant surgeon Stephen Strange seeks healing in Nepal, where he learns the mystic arts and must protect the world from dark magical forces beyond reality."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "bluejasmine.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("v for vendetta") Or query.Contains("vendetta") Or query.Contains("v") Then
		' movie 8
		Drama1.Text = "V for Vendetta"
		Starter1.Text = "Starring: Hugo Weaving, Natalie Portman, Stephen Rea, John Hurt"
		Year1.Text = "(2005)"
		OverView1.Text = "In a totalitarian future Britain, a masked revolutionary known as ""V"" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "her.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("aladdin") Then
		' movie 9
		Drama1.Text = "Aladdin"
		Starter1.Text = "Starring: Mena Massoud, Naomi Scott, Will Smith"
		Year1.Text = "(2019)"
		OverView1.Text = "Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "carol.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("after earth") Or query.Contains("after") Or query.Contains("earth") Then
		' movie 10
		Drama1.Text = "After Earth"
		Starter1.Text = "Starring: Will Smith, Jaden Smith, Sigourney Weaver"
		Year1.Text = "(2013)"
		OverView1.Text = "Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "lostdaughter.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		'cast
	Else If query.Contains("orlando bloom") Or query.Contains("orlando") Or query.Contains("bloom") Then
		Drama1.Text = "The Fellowship of the Ring"
		Starter1.Text = "Starring: Elijah Wood, Ian McKellen, Orlando Bloom"
		Year1.Text = "(2001)"
		OverView1.Text = "The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "kramer.jpg")
		
		Drama2.Text = "The Curse of the Black Pearl"
		Starter2.Text = "Starring: Johnny Depp, Orlando Bloom, Keira Knightley, Geoffrey Rush"
		Year2.Text = "(2003)"
		OverView2.Text = "Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "bridges.jpg")
		
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("johnny depp") Or query.Contains("johnny") Or query.Contains("depp") Then
		Drama1.Text = "Alice in Wonderland"
		Starter1.Text = "Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"
		Year1.Text = "(2010)"
		OverView1.Text = "Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "master.jpg")
		
		Drama2.Text = "Charlie and the Chocolate Factory"
		Starter2.Text = "Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"
		Year2.Text = "(2005)"
		OverView2.Text = "Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "manchester.jpg")
		
		Drama3.Text = "The Curse of the Black Pearl"
		Starter3.Text = "Starring: Johnny Depp, Orlando Bloom, Keira Knightley, Geoffrey Rush"
		Year3.Text = "(2003)"
		OverView3.Text = "Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."
		DramaImage3.Gravity = Gravity.FILL
		DramaImage3.Bitmap = LoadBitmap(File.DirAssets, "bridges.jpg")
		
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 85%y
	
	Else If query.Contains("helena bonham carter") Or query.Contains("helena") Or query.Contains("bonham") Or query.Contains("carter") Or query.Contains("helena bonham") Then
		Drama1.Text = "Charlie and the Chocolate Factory"
		Starter1.Text = "Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"
		Year1.Text = "(2005)"
		OverView1.Text = "Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "manchester.jpg")
		
		Drama2.Text = "Alice in Wonderland"
		Starter2.Text = "Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"
		Year2.Text = "(2010)"
		OverView2.Text = "Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "master.jpg")
		
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
	
	Else If query.Contains("will smith") Or query.Contains("will") Or query.Contains("smith") Then
		Drama1.Text = "Aladdin"
		Starter1.Text = "Starring: Mena Massoud, Naomi Scott, Will Smith"
		Year1.Text = "(2019)"
		OverView1.Text = "Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "carol.jpg")
		
		Drama1.Text = "After Earth"
		Starter1.Text = "Starring: Will Smith, Jaden Smith, Sigourney Weaver"
		Year1.Text = "(2013)"
		OverView1.Text = "Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "lostdaughter.jpg")
		
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 85%y
		
	Else If query.Contains("william moseley") Or query.Contains("william") Or query.Contains("moseley") Then
		
		Drama1.Text = "The Chronicles of Narnia"
		Starter1.Text = "Starring: Georgie Henley, Skandar Keynes, William Moseley"
		Year1.Text = "(2005)"
		OverView1.Text = "Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "gonebaby.jpg")
		
		Drama2.Text = "The Little Mermaid"
		Starter2.Text = "Starring: Poppy Drayton, William Moseley, Shirley MacLaine"
		Year2.Text = "(2018)"
		OverView2.Text = "A young reporter and his niece discover a real-life mermaid being held captive by a shady circus owner. As they befriend the mermaid, they embark on a magical adventure to save her and help her return to the sea."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "gonebaby.jpg")
		
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else If query.Contains("natalie portman") Or query.Contains("natalie") Or query.Contains("portman") Then
		
		Drama1.Text = "V for Vendetta"
		Starter1.Text = "Starring: Hugo Weaving, Natalie Portman, Stephen Rea"
		Year1.Text = "(2005)"
		OverView1.Text = "In a totalitarian future Britain, a masked revolutionary known as ""V"" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "her.jpg")
		
		Drama2.Text = "Thor: The Dark World"
		Starter2.Text = "Starring: Chris Hemsworth, Natalie Portman, Tom Hiddleston"
		Year2.Text = "(2013)"
		OverView2.Text = "Thor must team up with his treacherous brother Loki to stop the Dark Elves, led by the vengeful Malekith, who seeks to plunge the universe into darkness using a powerful ancient force known as the Aether."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "gonebaby.jpg")
		
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
		'single cast or one movie only
	Else If query.Contains("elijah wood") Or query.Contains("elijah") Or query.Contains("wood") Or query.Contains("ian mckellen") Or query.Contains("ian") Or query.Contains("mckellen") Then
		Drama1.Text = "The Fellowship of the Ring"
		Starter1.Text = "Starring: Elijah Wood, Ian McKellen, Orlando Bloom"
		Year1.Text = "(2001)"
		OverView1.Text = "The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "kramer.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
	Else If query.Contains("freddie highmore") Or query.Contains("freddie") Or query.Contains("highmore") Or query.Contains("david kelly") Or query.Contains("david") Or query.Contains("kelly")Then
		Drama1.Text = "Charlie and the Chocolate Factory"
		Starter1.Text = "Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"
		Year1.Text = "(2005)"
		OverView1.Text = "Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "manchester.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
	Else if query.Contains("mia wasikowska") Or query.Contains("mia") Or query.Contains("wasikowska") Or query.Contains("anne hathaway") Or query.Contains("anne") Or query.Contains("hathaway") Then
		Drama1.Text = "Alice in Wonderland"
		Starter1.Text = "Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"
		Year1.Text = "(2010)"
		OverView1.Text = "Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "master.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
	Else if query.Contains("daniel radcliffe") Or query.Contains("daniel") Or query.Contains("radcliffe") Or query.Contains("rupert grint") Or query.Contains("rupert") Or query.Contains("grint") Or query.Contains("emma watson") Or query.Contains("emma") Or query.Contains("watson") Then
		Drama1.Text = "Harry Potter and the Philosopher's Stone"
		Starter1.Text = "Starring: Daniel Radcliffe, Rupert Grint, Emma Watson"
		Year1.Text = "(2003)"
		OverView1.Text = "Captain Jack Sparrow must rescue Elizabeth Swann, who has been kidnapped by cursed pirates led by Captain Barbossa. Alongside Will Turner, Jack battles ghostly pirates, seeking both freedom and revenge."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "millondolar.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
	Else if query.Contains("keira knightley") Or query.Contains("keira") Or query.Contains("knightley") Then
		Drama1.Text = "The Curse of the Black Pearl"
		Starter1.Text = "Starring: Johnny Depp, Orlando Bloom, Keira Knightley"
		Year1.Text = "(2003)"
		OverView1.Text = "Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "bridges.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else if query.Contains("skandar keynes") Or query.Contains("skandar") Or query.Contains("keynes") Or query.Contains("william moseley") Or query.Contains("william") Or query.Contains("moseley") Then
		Drama6.Text = "The Chronicles of Narnia"
		Starter6.Text = "Starring: Georgie Henley, Skandar Keynes, William Moseley"
		Year6.Text = "(2005)"
		OverView6.Text = "Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."
		DramaImage6.Gravity = Gravity.FILL
		DramaImage6.Bitmap = LoadBitmap(File.DirAssets, "gonebaby.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
	
	Else if query.Contains("benedict cumberbatch") Or query.Contains("benedict") Or query.Contains("cumberbatch") Or query.Contains("chiwetel ejiofor") Or query.Contains("chiwetel") Or query.Contains("ejiofor") Then
		Drama1.Text = "Doctor Strange"
		Starter1.Text = "Starring: Benedict Cumberbatch, Chiwetel Ejiofor"
		Year1.Text = "(2016)"
		OverView1.Text = "After a life-changing accident ends his career, arrogant surgeon Stephen Strange seeks healing in Nepal, where he learns the mystic arts and must protect the world from dark magical forces beyond reality."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "bluejasmine.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
	
	Else if query.Contains("hugo weaving") Or query.Contains("hugo") Or query.Contains("weaving") Or query.Contains("stephen rea") Or query.Contains("stephen") Or query.Contains("rea") Then
		Drama1.Text = "V for Vendetta"
		Starter1.Text = "Starring: Hugo Weaving, Natalie Portman, Stephen Rea"
		Year1.Text = "(2005)"
		OverView1.Text = "In a totalitarian future Britain, a masked revolutionary known as ""V"" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "her.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
		
	Else if query.Contains("mena massoud") Or query.Contains("mena") Or query.Contains("massoud") Or query.Contains("naomi scott") Or query.Contains("naomi") Or query.Contains("scott") Then
		Drama1.Text = "Aladdin"
		Starter1.Text = "Starring: Mena Massoud, Naomi Scott, Will Smith"
		Year1.Text = "(2019)"
		OverView1.Text = "Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "carol.jpg")
		
		PanelMovie2.Visible = False
		PanelMovie3.Visible = False
		PanelMovie4.Visible = False
		PanelMovie5.Visible = False
		PanelMovie6.Visible = False
		
		p.Height = 70%y
		
	Else if query.Contains("jaden smith") Or query.Contains("jaden") Or query.Contains("smith") Or query.Contains("sigourney weave") Or query.Contains("sigourney") Or query.Contains("weave") Then
		Drama1.Text = "After Earth"
		Starter1.Text = "Starring: Will Smith, Jaden Smith, Sigourney Weaver"
		Year1.Text = "(2013)"
		OverView1.Text = "Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."
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
		
		Drama1.Text = "The Fellowship of the Ring"
		Starter1.Text = "Starring: Elijah Wood, Ian McKellen, Orlando Bloom"
		Year1.Text = "(2001)"
		OverView1.Text = "The future of civilization rests in the fate of the One Ring, which has been lost for centuries. Powerful forces are unrelenting in their search for it. But fate has placed it in the hands of a young Hobbit named Frodo Baggins (Elijah Wood), who inherits the Ring and steps into legend"
		DramaImage1.Gravity = Gravity.FILL
		DramaImage1.Bitmap = LoadBitmap(File.DirAssets, "kramer.jpg")
	
		Drama2.Text = "Charlie and the Chocolate Factory"
		Starter2.Text = "Starring: Johnny Depp, Freddie Highmore, Helena Bonham Carter, David Kelly"
		Year2.Text = "(2005)"
		OverView2.Text = "Charlie Bucket, a humble boy from a poor family, wins a golden ticket to tour Willy Wonka’s magical chocolate factory. Inside, he witnesses the strange fates of other spoiled children and proves his honesty and kindness to the eccentric candy-maker."
		DramaImage2.Gravity = Gravity.FILL
		DramaImage2.Bitmap = LoadBitmap(File.DirAssets, "manchester.jpg")
	
		Drama3.Text = "Alice in Wonderland"
		Starter3.Text = "Starring: Mia Wasikowska, Johnny Depp, Helena Bonham Carter, Anne Hathaway"
		Year3.Text = "(2010)"
		OverView3.Text = "Alice, now a teenager, returns to the fantastical world of Underland, where she must unite the divided inhabitants and defeat the cruel Red Queen by battling the Jabberwocky, fulfilling her true destiny."
		DramaImage3.Gravity = Gravity.FILL
		DramaImage3.Bitmap = LoadBitmap(File.DirAssets, "master.jpg")
	
		Drama4.Text = "Harry Potter and the Philosopher's Stone"
		Starter4.Text = "Starring: Daniel Radcliffe, Rupert Grint, Emma Watson"
		Year4.Text = "(2003)"
		OverView4.Text = "Captain Jack Sparrow must rescue Elizabeth Swann, who has been kidnapped by cursed pirates led by Captain Barbossa. Alongside Will Turner, Jack battles ghostly pirates, seeking both freedom and revenge."
		DramaImage4.Gravity = Gravity.FILL
		DramaImage4.Bitmap = LoadBitmap(File.DirAssets, "millondolar.jpg")
	
		Drama5.Text = "The Curse of the Black Pearl"
		Starter5.Text = "Starring: Johnny Depp, Orlando Bloom, Keira Knightley"
		Year5.Text = "(2003)"
		OverView5.Text = "Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."
		DramaImage5.Gravity = Gravity.FILL
		DramaImage5.Bitmap = LoadBitmap(File.DirAssets, "bridges.jpg")
	
	
		Drama6.Text = "The Chronicles of Narnia"
		Starter6.Text = "Starring: Georgie Henley, Skandar Keynes, William Moseley,"
		Year6.Text = "(2005)"
		OverView6.Text = "Four siblings, evacuated from wartime London, discover the magical land of Narnia through a wardrobe. There, they join forces with the lion Aslan to battle the evil White Witch and fulfill an ancient prophecy to save the realm."
		DramaImage6.Gravity = Gravity.FILL
		DramaImage6.Bitmap = LoadBitmap(File.DirAssets, "gonebaby.jpg")
	
		Drama7.Text = "Doctor Strange"
		Starter7.Text = "Starring: Benedict Cumberbatch, Chiwetel Ejiofor"
		Year7.Text = "(2016)"
		OverView7.Text = "After a life-changing accident ends his career, arrogant surgeon Stephen Strange seeks healing in Nepal, where he learns the mystic arts and must protect the world from dark magical forces beyond reality."
		DramaImage7.Gravity = Gravity.FILL
		DramaImage7.Bitmap = LoadBitmap(File.DirAssets, "bluejasmine.jpg")
	
		Drama8.Text = "V for Vendetta"
		Starter8.Text = "Starring: Hugo Weaving, Natalie Portman, Stephen Rea, John Hurt"
		Year8.Text = "(2005)"
		OverView8.Text = "In a totalitarian future Britain, a masked revolutionary known as ""V"" ignites a movement against the oppressive regime, forming a bond with a young woman named Evey and inspiring citizens to fight back."
		DramaImage8.Gravity = Gravity.FILL
		DramaImage8.Bitmap = LoadBitmap(File.DirAssets, "her.jpg")
	
		Drama9.Text = "Aladdin"
		Starter9.Text = "Starring: Mena Massoud, Naomi Scott, Will Smith"
		Year9.Text = "(2019)"
		OverView9.Text = "Aladdin, a kind-hearted street urchin in the city of Agrabah, discovers a magical lamp that releases a powerful Genie. With the Genie's help, Aladdin embarks on a journey to win the heart of Princess Jasmine and thwart the evil sorcerer Jafar, who seeks to overthrow the Sultan and rule the kingdom."
		DramaImage9.Gravity = Gravity.FILL
		DramaImage9.Bitmap = LoadBitmap(File.DirAssets, "carol.jpg")
	
		Drama10.Text = "After Earth"
		Starter10.Text = "Starring: Will Smith, Jaden Smith, Sigourney Weaver"
		Year10.Text = "(2013)"
		OverView10.Text = "Set in the future, After Earth follows a father and son (Will Smith and Jaden Smith) who crash-land on an abandoned Earth. As they fight to survive and find a way to escape, they must face various dangers, including dangerous wildlife and the elements, all while dealing with internal tensions and fears. The film mixes sci-fi with fantasy elements, particularly around survival and overcoming obstacles."
		DramaImage10.Gravity = Gravity.FILL
		DramaImage10.Bitmap = LoadBitmap(File.DirAssets, "lostdaughter.jpg")


		
		
	End If
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub




Private Sub SciFiPage_Click

End Sub

Private Sub HomePage_Click
	StartActivity(Main)	
End Sub

Private Sub DramaPage_Click
	StartActivity(Drama)
End Sub

Private Sub ActionPage_Click
	StartActivity(Action)
End Sub