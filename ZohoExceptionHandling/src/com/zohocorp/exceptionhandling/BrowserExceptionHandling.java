package com.zohocorp.exceptionhandling;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Scanner;

class Reference
{
	public static int currentPosition=-1;
}

interface Shortcuts {
	void addShortcut(String shortcutUrl);
	void viewShortcuts();
}

class InvalidURLException extends Exception {
	public InvalidURLException(String s)
	{
		super(s); 
	}
}
class NoHistoryFoundException extends Exception {
	public NoHistoryFoundException(String s)
	{
		super(s); 
	}
}
class InvalidPositionException extends Exception {
	public InvalidPositionException(String s)
	{
		super(s); 
	}
}

class Browser {
	private ArrayList<String> url = new ArrayList<>();
	private ArrayList<String> urlBookmarks = new ArrayList<>();
	private ArrayList<String> urlShortcuts = new ArrayList<>();
	private int numberOfTabs;
	private Integer numberOfTabsObject;
	private HashMap<String, Integer> numberOfVisits= new HashMap<>();
	
	Browser(){
		//empty constructor
	}
	
	Browser(String urlHistory[])	
	{
		int l=urlHistory.length;
		for(int i=0;i<l;i++)
			addURL(urlHistory[i]);
	}

	public void addURL(String s)
	{
		numberOfVisits.merge(s, 1, Integer::sum);
		//url.add(s); 
		url.add(String.join(" ",s,"##",String.valueOf(numberOfVisits.get(s))));
	}
	
	public void setTabNumber(int numberOfTabs)
	{
		this.numberOfTabs=numberOfTabs;
		this.numberOfTabsObject=numberOfTabs;
	}
	public int getTabNumber()
	{
		return this.numberOfTabs;
	}
	
	public void display()  
	{  
		System.out.println("Browser History \n");
		for(int i=0;i<url.size();i++)
			System.out.println(url.get(i)+"\n");
	}
	
	public void browserHistoryTracking()
	{
		System.out.println("Browser History Tracking \n");
		numberOfVisits.forEach((K,V)
				-> System.out.println(K+" ## "+V));
	}
	public void whoAmI() {
		System.out.println("I am a browser");
	}
	
	public void autoboxing()
	{
		numberOfTabsObject=numberOfTabs;
		System.out.println(numberOfTabsObject);
	}
	public void unboxing()
	{
		numberOfTabs=numberOfTabsObject;
		System.out.println(numberOfTabs);
	}
	
	class Bookmarks
	{
		public void addBookmarks(String bookmarkUrl)
		{
			if (urlBookmarks.contains(bookmarkUrl))
				System.out.println("Bookmark already exists");
			else
				urlBookmarks.add(bookmarkUrl);
		}
		public void viewBookmarks()
		{
			System.out.println("Browser Bookmarks \n");
			for(int i=0;i<urlBookmarks.size();i++)
				System.out.println(urlBookmarks.get(i)+"\n");
		}
	}
	
	Shortcuts shortcutUrls = new Shortcuts()
	{
		@Override
		public void addShortcut(String shortcutUrlName)
		{
			if (urlShortcuts.contains(shortcutUrlName))
				System.out.println("Shortcut already exists");
			else
			urlShortcuts.add(shortcutUrlName);
		}
		@Override
		public void viewShortcuts()
		{
			System.out.println("Browser Shortcuts \n");
			for(int i=0;i<urlShortcuts.size();i++)
				System.out.println(urlShortcuts.get(i)+"\n");
		}
	};
	public void ShortcutFuntion(String s)
	{
		shortcutUrls.addShortcut(s);
		shortcutUrls.viewShortcuts();
	}		
	
	class BrowserHistory
	{  
		
		int extensionIndex;
		String extension;
		String[] extensionsArray= {".com",".in",".org"};
		
		
		BrowserHistory(String homepage)	
		{
			url.add(homepage);
			Reference.currentPosition++;
		}
		void visit(String visitUrl)
		{
			try
			{
				extensionIndex=visitUrl.lastIndexOf(".");
				extension=visitUrl.substring(extensionIndex, visitUrl.length());
				if (Arrays.asList(extensionsArray).contains(extension))
				{
					Reference.currentPosition++;
					url.add(Reference.currentPosition,visitUrl);
					display();
				}
				else
				{
					throw new InvalidURLException("Invalid url exception");
				}
			}
			catch(InvalidURLException urlException)
			{
				System.out.println(urlException.getMessage());
			}
		}
		public String back(int steps)
		{
			int currentUrlPosition=Reference.currentPosition;
			try
			{
				ListIterator<String> urlIterator = url.listIterator(currentUrlPosition);
				for(int i=0;i<steps;i++) 
				{
					if (urlIterator.hasPrevious())
					{
						urlIterator.previous();
						Reference.currentPosition--;
					}
					else
						throw new NoHistoryFoundException("No History Found");
				}
				//Reference.currentPosition++;
				//System.out.println(url.get(Reference.currentPosition));
				return (urlIterator.next());
			}
			catch(NoHistoryFoundException noHistoryFound)
			{
				Reference.currentPosition=currentUrlPosition;
				System.out.println(noHistoryFound.getMessage());
			}
			//System.out.println(url.get(Reference.currentPosition));
			return url.get(currentUrlPosition);
		}
		public String forward(int steps)
		{
			int currentUrlPosition=Reference.currentPosition;
			try
			{
				ListIterator<String> urlIterator = url.listIterator(currentUrlPosition);
				for(int i=0;i<=steps;i++) 
				{
					if (urlIterator.hasNext())
					{
						urlIterator.next();
						Reference.currentPosition++;
					}
					else
						throw new NoHistoryFoundException("No History Found");
				}
				Reference.currentPosition--;
				//System.out.println(url.get(Reference.currentPosition));
				return(urlIterator.previous());
			}
			catch(NoHistoryFoundException noHistoryFound)
			{
				Reference.currentPosition=currentUrlPosition;//return to current url
				System.out.println(noHistoryFound.getMessage());
			}
			
			//System.out.println(url.get(Reference.currentPosition));
			return url.get(currentUrlPosition);
		}
		public String getUrl(int position)
		{
			String urlInPosition=null;
			try
			{
				if (position<0)
				{
					throw new InvalidPositionException("Provide only positive values");
				}
				else
					urlInPosition=url.get((position));
			}
			catch(InvalidPositionException invalidPosition)
			{
				System.out.println(invalidPosition.getMessage());
			}
			catch(Exception e)
			{
				System.out.println("Invalid position");
			}
			return urlInPosition;
		}
		
		
	  }
}

class GoogleChrome extends Browser{
	private boolean isLocationAccessible=false,isCameraAccessible=false, isMicrophoneAccessible=false;
	final String versionNumber="1.0";
	static int countGoogleTabs=0;
	
	GoogleChrome(){
		countGoogleTabs++; //To count the number of google tabs opened
	}

	GoogleChrome(String urlHistory[]){
		super(urlHistory);
	}
	
	
	public void setAccessibility() {
		
			this.isLocationAccessible=false;
		
			this.isCameraAccessible=false;
	
			this.isMicrophoneAccessible=false;
		permissionDisplay();
	}
	
	public void setAccessibility(boolean permissionArray[]) {
		this.isLocationAccessible=permissionArray[0];
		this.isCameraAccessible=permissionArray[1];
		this.isMicrophoneAccessible=permissionArray[2];
		permissionDisplay();
	}
	
	public void permissionDisplay() {
		System.out.println("Location Permission:" + this.isLocationAccessible);
		System.out.println("Camera Permission:" + this.isCameraAccessible);
		System.out.println("Microphone Permission:" + this.isMicrophoneAccessible);
	}
	
	@Override
	public void whoAmI() {
		System.out.println("I am Google Chrome");
	}

}

interface MultipleAccountContainers {
	void addContainer(String newContainer);
	void removeContainer(String deleteContainer);
}

class Firefox extends Browser implements MultipleAccountContainers {
	
	private static ArrayList<String> container = new ArrayList<>();
	
	Firefox(){
		//empty constructor
	}
	
	Firefox(String urlHistory[]){
		super(urlHistory);
	}
	@Override
	public void whoAmI() {
		System.out.println("I am Firefox");
	}

	@Override
	public void addContainer(String newContainer) {
		if (container.contains(newContainer)) {
			System.out.println("Container already present");
		}
		else
			container.add(newContainer);
		System.out.println("Container List:"+ container);
	}

	@Override
	public void removeContainer(String deleteContainer) {
		if (container.size()==0)
		{
			System.out.println("Container List is empty");
		}
		else {
			if (container.contains(deleteContainer)==false) {
				System.out.println("Container not present");
			}
			else {	
				container.removeIf(n -> (n.equals(deleteContainer)));
				System.out.println("Container List:"+container);
			}
		}
	}
}

public class BrowserExceptionHandling{
	
	public static Browser[] growArraySize(Browser browserTabArray[], int indexAllBrowsers) {
		if(browserTabArray.length==indexAllBrowsers)
		{
			Browser newB[]=new Browser[indexAllBrowsers+1];
			for(int i=0;i<indexAllBrowsers;i++)
				newB[i]=browserTabArray[i];
			
			browserTabArray=newB;
		}  
		return browserTabArray;
	}
	
	
	public static Scanner in = new Scanner(System.in); //globally declared scanner is static since non-static variable i/p cannot be referenced from static content
	public static void main(String[] args) throws IOException
	{
		BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
		char c='Y' ;
		int choice;
		int indexAllBrowsers=5;
		String browserName;
		int steps;
		
		Browser newTab=new Browser();
		Browser newGoogleTab=new GoogleChrome();
		Browser newFirefoxTab=new Firefox();
		Browser.BrowserHistory browserHistoryObject=newTab.new BrowserHistory("www.google.com");
		
//		GoogleChrome.History googleTab=new GoogleChrome.History();
//		Firefox.History firefoxTab=new Firefox.History();
		
		Browser tabOne=new GoogleChrome();
		Browser tabTwo=new Firefox();
		Browser tabThree=new Firefox();
		Browser tabFour=new GoogleChrome();
		Browser tabFive=new GoogleChrome();
		Browser[] allBrowsers=new Browser[5];
		
		allBrowsers[0]=tabOne;
		allBrowsers[1]=tabTwo;
		allBrowsers[2]=tabThree;
		allBrowsers[3]=tabFour;
		allBrowsers[4]=tabFive;
		
		do
		{
			System.out.println("\n Main Menu \n 1.Add URLs \n 2.Find my Browser name \n 3.Set Permissions"
					+ "\n 4.Number of Tabs \n 5.Container \n 6.Add Bookmarks \n 7.Add Shortcuts  \n 8.Show Bookmarks "
					+ "\n 9.Visit URL \n 10.Go back in History \n 11.Go forward in History \n 12.Get url from position \n 13.Quit");
			System.out.println("Enter your choice: ");
			choice = in.nextInt();
			
			switch(choice)
			{
			case 1:
				System.out.println("Enter the browser whose history you want to enter: ");
				browserName = reader.readLine();
				if(browserName.equalsIgnoreCase("Google Chrome"))
					newTab=addHistory(newGoogleTab);
				else if(browserName.equalsIgnoreCase("Firefox"))
					newTab=addHistory(newFirefoxTab);
				allBrowsers=growArraySize(allBrowsers, indexAllBrowsers);
				allBrowsers[indexAllBrowsers++]=newTab;
				break;
			case 2:
				System.out.println("The open browser tabs are :");
				for(int j=0;j<allBrowsers.length;j++) {
					allBrowsers[j].whoAmI();
				}
				break;
				
			case 3:
				setPermissions(tabOne);
				break;
			case 4:
				numberOfTabs(allBrowsers);
				break;
			case 5:
				editContainer(tabTwo);
				break;
			case 6:
				String urlName;
				System.out.println("Enter the browser where you want to add bookmarks: ");
				browserName = reader.readLine();
				System.out.println("Enter the url of bookmark: ");
				urlName = reader.readLine();
				if(browserName.equalsIgnoreCase("Google Chrome"))
				{
					Browser.Bookmarks bookmarksObject=newGoogleTab.new Bookmarks();
					bookmarksObject.addBookmarks(urlName);
					bookmarksObject.viewBookmarks();
				}
				else if(browserName.equalsIgnoreCase("Firefox"))
				{
					Browser.Bookmarks bookmarksObject=newFirefoxTab.new Bookmarks();
					bookmarksObject.addBookmarks(urlName);
					bookmarksObject.viewBookmarks();
				}
				break;
			case 7:
				System.out.println("Enter the browser where you want to add shortcut: ");
				browserName = reader.readLine();
				System.out.println("Enter the url of shortcut: ");
				urlName = reader.readLine();
				if(browserName.equalsIgnoreCase("Google Chrome"))
					newGoogleTab.ShortcutFuntion(urlName);
				else if(browserName.equalsIgnoreCase("Firefox"))
					newFirefoxTab.ShortcutFuntion(urlName);
				break;
			case 8:
				System.out.println("Enter the browser where you want to view bookmarks: ");
				browserName = reader.readLine();
				if(browserName.equalsIgnoreCase("Google Chrome"))
				{
					Browser.Bookmarks bookmarksObject=newGoogleTab.new Bookmarks();
					bookmarksObject.viewBookmarks();
				}
				else if(browserName.equalsIgnoreCase("Firefox"))
				{
					Browser.Bookmarks bookmarksObject=newFirefoxTab.new Bookmarks();
					bookmarksObject.viewBookmarks();
				}
				break;
			case 9:
				String visitUrl;
				System.out.println("Enter the url you want to visit: ");
				visitUrl = reader.readLine();
				browserHistoryObject.visit(visitUrl);
				break;
			case 10:
				String backUrl;
				System.out.println("Enter the number of steps you want to move back: ");
				steps=in.nextInt();
				backUrl=browserHistoryObject.back(steps);
				System.out.println(backUrl);
				break;
			case 11:
				String forwardUrl;
				System.out.println("Enter the number of steps you want to move forward: ");
				steps=in.nextInt();
				forwardUrl=browserHistoryObject.forward(steps);
				System.out.println(forwardUrl);
				break;
			case 12:
				int position;
				String urlInPosition;
				System.out.println("Enter the position of url you want to get: ");
				position=in.nextInt();
				urlInPosition=browserHistoryObject.getUrl(position-1);
				System.out.println(urlInPosition);
				break;
			case 13:
				System.out.println("Do you want to continue(Y/N): ");
				c=in.next().charAt(0);
				break;
			default:
				System.out.println("Wrong choice");
			}
			
		}while(c=='Y');
		System.out.println("Goodbye");
		in.close();

	}
	
	
	private static Browser addHistory(Browser newTab) { //add urls to browser 
		
		int urlNumber;
		System.out.println("Enter the number of urls you want to add: ");
		urlNumber = in.nextInt();
		String[] urlName= new String[urlNumber];
		System.out.println("Enter the URLS you want to add: ");
		for(int i=0;i<urlNumber;i++)
		{
			urlName[i] = in.next();
			newTab.addURL(urlName[i]);
		}
		newTab.browserHistoryTracking();
		return (newTab);
	}
	
	private static void setPermissions(Browser tabOne){
		int permissionChoice;
		System.out.println("\n Set Permissions \n 1.ALL \n 2.RESET PERMISSIONS");
		System.out.println("Enter your choice:");
		permissionChoice = in.nextInt(); 
		
		switch(permissionChoice)
		{
		case 1:
			boolean permissionOptionArray[]=new boolean[3];
			System.out.println("Enter permission for location, camera and microphone: ");
			for(int j=0;j<3;j++) {
				permissionOptionArray[j]=in.nextBoolean();
			}
			((GoogleChrome) tabOne).setAccessibility(permissionOptionArray);
			break;
		case 2:
			((GoogleChrome) tabOne).setAccessibility();
			break;
		default:
			System.out.println("Wrong choice");
		}
	}
	
	public static void numberOfTabs(Browser browserTabArray[]){
		int countGoogleChrome=0;
		int countFirefox=0;
		int googleTabIndex=0;
		int firefoxTabIndex=0;
		
		for(int j=0;j<browserTabArray.length;j++) {
			if(browserTabArray[j] instanceof GoogleChrome) {
				browserTabArray[j].setTabNumber(++countGoogleChrome);
				for(int i=0;i<j;i++)
				{
					if(browserTabArray[i] instanceof GoogleChrome)
						browserTabArray[i].setTabNumber(countGoogleChrome);
				}
				googleTabIndex=j;
			}
			else if(browserTabArray[j] instanceof Firefox) {
				browserTabArray[j].setTabNumber(++countFirefox);
				for(int i=0;i<j;i++)
				{
					if(browserTabArray[i] instanceof Firefox)
						browserTabArray[i].setTabNumber(countFirefox);
				}
				firefoxTabIndex=j;
			}
		}
//		System.out.println("Number of GoogleChrome tabs: "+ browserTabArray[googleTabIndex].getTabNumber());
//		System.out.println("Number of Firefox tabs: "+ browserTabArray[firefoxTabIndex].getTabNumber());
		System.out.print("Number of GoogleChrome tabs: ");
		browserTabArray[googleTabIndex].autoboxing();
		System.out.print("Number of Firefox tabs: ");
		browserTabArray[firefoxTabIndex].unboxing();
	}
	
	public static void editContainer(Browser tabTwo){
	
			String containerName;
			int containerChoice;
			System.out.println("\n Container \n 1.Add Container \n 2.Remove Container");
			System.out.println("Enter your choice:");
			containerChoice = in.nextInt(); 
			switch(containerChoice)
			{
			case 1:
				System.out.println("Enter the name of container you want to add: ");
				containerName = in.next();
				((Firefox) tabTwo).addContainer(containerName);
				break;
			case 2:
				System.out.println("Enter the name of container you want to remove: ");
				containerName = in.next();
				((Firefox) tabTwo).removeContainer(containerName);
				break;
			default:
				System.out.println("Wrong choice");
			}
	}

}

