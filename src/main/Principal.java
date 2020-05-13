package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import exceptions.Field_Error;
import exceptions.Url_Erronea;
import models.User;
import models.Video;

public class Principal {	


	public static void main(String[] args) {
	
		List <User> clients =new ArrayList<User>();			
		
		Scanner entry = new Scanner(System.in);		
		
		int option = -1;										
		
		do {	
			
			menuClient();					
			
			do {
								
					try {														//KEEP SELECTION USER
							  
						  option=  Integer.parseInt(entry.next());		
							
			            } catch (Exception e) {									//CONTROL ERROR WRONG ENTRY
			            	
				           System.out.println("ERROR --- You must enter a number.");		
				           option=-1;
					    }
					
			  
				 if(option != 1 && option !=2 && option!=3 && option!=0) {		//MSG ERROR
					 
					System.out.println("ERROR --- Please enter one next options. ( 1 / 2 / 3 / 0)");						
				 }		
					
			}while(option!=1 && option !=2 && option!=3 && option!=0  );		//WHILE ENTRY IS WRONG
				
			
			switch(option)
				{
				
				case 1:										//CREATE NEW USER 
					clients=newClient(clients);
					System.out.println(clients);					
				break;
				
				case 2:
					clients=deleteClient(clients);			//DELETE USER
					System.out.println(clients);
					
				break;
				
				case 3:
					try {
						viewClient(clients);				//SHOW DETAILS USER
						
						} catch (Field_Error e) {
								System.out.println(e);
							
						} catch (Url_Erronea e) {					
								System.out.println(e);	
					}									
				break;	
				case 0:
					System.out.println("GOOD BY");			//EXIT TO PROGRAM					
				break;
				default:
					}				
		
		}while(option!=0);
		
	}

	
	
	///---------------------------------------------------------------------------------------------------------------------
	///---------------------------------------------------------------------------------------------------------------------
	///--------------------------------------------	NEW USER ---------------------------------------------------------------
	///---------------------------------------------------------------------------------------------------------------------
	///---------------------------------------------------------------------------------------------------------------------

	public static List <User>  newClient (List <User> clients) {			
		
		String nameEnt;
		String snameEnt;
		String nickEnt;
		String pwdEnt;
		boolean exist=false;
		boolean existNick=false;
		int year, month, day;
		String date;				
		boolean isEmpty=false;
		
		Scanner entry = new Scanner(System.in);		
				
		System.out.println("--------------------- USSER ACCOUNT -------------------------\n");		
		
		try {
			
			System.out.println("Enter your name :. \n");			//ENTER NEW VALUES
			nameEnt=entry.next();	
			
			isEmpty=fieldEmpty (nameEnt);
			
			System.out.println("Enter your surname : \n");
			snameEnt=entry.next();		
			
			isEmpty=fieldEmpty (snameEnt);
			
			System.out.println("Enter your Nick \n");
			nickEnt=entry.next();	
			
			isEmpty=fieldEmpty (nickEnt);
			
			System.out.println("Enter your Password :. \n");
			pwdEnt=entry.next();	
			
			isEmpty=fieldEmpty (pwdEnt);
	
						
			Calendar registry=new GregorianCalendar();			//GET DATE FROM SYSTEM
			year=registry.get(Calendar.YEAR);
			month=registry.get(Calendar.MONTH);
			day=registry.get(Calendar.DAY_OF_MONTH);		
			date = day + "/" +  month + "/" + year;		
							
			User client = new User(nameEnt, snameEnt, nickEnt, pwdEnt,date);		//CREATE NEW OBJET USER
						
				
			for (User user : clients) {								//COMPARE NEW CLIENT INSIDE ARRAY LIST CLIENTS
				
				if(user.equals(client)) {
					
					System.out.println("User exist");				//FIND CLIENT INSIDE LIST
					exist=true;
							
				}else {												//USER ISN'T EXIST
								
					if(user.getNick().equals(nickEnt)) {			//COMPARE IS NICK IS USED TO AVOID REPEAT
						
						System.out.println("This Nick exist in another User, Please try another Nick");
						existNick=true;
						}							
				}
			}
				
				
			if (exist!= true && existNick!=true) {			//IF IS A NEW USER AND NEW NICK 
				clients.add(client);						//ADD NEW USER TO ARRAY LIST
				System.out.println("User has been created");
			}	
			
			
		
		}catch (Field_Error e){							//IF THERE ARE SOME FIELD EMPTY THROW A EXCEPTION
			
			System.out.println(e);		
		}
		
		return clients;									//RETURN OBJET ARRAY LIST CLIENTS
	}

	
	

		///------------------------------------------------------------------------------------------------------------------------
		///------------------------------------------------------------------------------------------------------------------------
		///--------------------------------------------	DELETE USER ---------------------------------------------------------------
		///------------------------------------------------------------------------------------------------------------------------
		///------------------------------------------------------------------------------------------------------------------------

	
	private static List<User> deleteClient(List<User> clients) {		//DELETE USER		
		
		String nickEnt;		
		String pwdEnt;							
		boolean exist=false;	
		boolean isEmpty=false;
		int position=0;								
		Scanner entry = new Scanner(System.in);
						
		System.out.println("--------------------- USSER ACCOUNT -------------------------\n");
		
		
		try {
			
			System.out.println("Enter your Nick \n");
			nickEnt=entry.next();
			isEmpty=fieldEmpty (nickEnt);					//CHECK IF FIELD IS EMPTY
			
			System.out.println("Enter your Password :. \n");
			pwdEnt=entry.next();
			isEmpty=fieldEmpty (pwdEnt);					//CHECK IF FIELD IS EMPTY
			
			for (User user : clients) {	
					
				if(user.getNick().equals(nickEnt) && user.getPwd().equals(pwdEnt) ) {			//COMPARE NICK AND PASWORD TO LOGIN
						
					position=clients.indexOf(user);			//GET POSITION USER IN ARRAY CLIENTS
					exist=true;				
					}
				}
							
			if(exist==true) {								//NICK AND PASWORD CORRECT
				
				clients.remove(position);					//DELETE USER IN ARRAY CLIENTS
				System.out.println("User has been deleted");	
				
			}else {											//NICK AND PASWORD INCORRECT
				System.out.println("User doesn't exist or your Pasword is wrong");
			}
			
		}catch (Field_Error e) {							//THROW EXCEPTION IF SOME FIELD IS EMPTY
			System.out.println(e);
		}
		
		return clients;										//RETURN ARRAY CLIENTS
	}	
	
	
	///---------------------------------------------------------------------------------------------------------------------------
	///---------------------------------------------------------------------------------------------------------------------------
	///--------------------------------------------	VIEW DATA USER ---------------------------------------------------------------
	///---------------------------------------------------------------------------------------------------------------------------
	///---------------------------------------------------------------------------------------------------------------------------


	
	private static void viewClient(List<User> clients) throws Url_Erronea, Field_Error {				// DETAILS USER
				
		Scanner entry = new Scanner(System.in);
		int option=-1;
		int index=0;
		User client;
				
		client=existClient (clients);					//VERIFY IF CLIENT EXIST SEND ARRAY CLIENTS AND GET A OBJECT USER
			
		if (client!=null) {								//IF OBJECT CLIENT IS DIFERENT NULL
			
			do {					//WHILE DON'T CHOOSE EXIT MENU
					
				menuVideo();							//SHOW MENU VIDEO OF THAT USER
					
				do {				//WHILE OPTION OF MENU WRONG
						
					  try {									  
							option=  Integer.parseInt(entry.nextLine());		
								
			            } catch (NumberFormatException excepcion) {			
				            	
			            	System.out.println("ERROR --- You must enter a number.");
			            	option=-1;
				       }
				  
						if(option != 1 && option !=2 && option!=3 && option!=4 && option!=5) {		
								
							System.out.println("ERROR --- You must choose option ( 1 / 2 / 3 / 4 / 5 )");
							option=-1;
						}
						
				}while(option!=1 && option !=2 && option!=3 && option!=4  && option!=5);			
					
					
					
				switch(option){
					
					case 1:												
						
						System.out.println("----------------------------CREATE VIDEO-----------------------------------------------\n");					
						Video video = createVideo(client.getNick());		//FUNCTION CREATE VIDEO SENDING OBJECT USER NAME
						client.addVideo(video);								//ADD NEW OBJECT VIDEO GET TO PREVIOUS FUNCTION 
										
					break;
					
					case 2:												
						System.out.println("----------------------------DELETE VIDEO-----------------------------------------------\n");					
						index = findVideo(client.getVideos());				//FUNCTION FIND VIDEO SENDING OBJECT ARRAY VIDEOS
							
						try {
							client.deleteVideo(index);						//DELETE VIDEO WITH INDEX GET TO PREVIOUS FUNCTION
							
						}catch (IndexOutOfBoundsException e){
							System.out.println("Has been generated one Error because user hasn´t videos");
						}
					break;
						
					case 3:												
						System.out.println("----------------------------VIEW VIDEO LIST--------------------------------------------\n");			
						System.out.println("---------------------------- "+ client.getNick() +" ------------------------------------\n");
						ViewVideos(client.getVideos());					//FUNCTION SHOW VIDEOS SENDIND ARRAY LIST VIDEOS USER			
					break;
						
					case 4:												
						System.out.println("----------------------------EDIT VIDEO --------------------------------------------\n");			
						System.out.println("---------------------------- "+ client.getNick() +" ------------------------------------\n");
						EditVideo(client.getVideos());					//GO TO OTHER MENU WITH ARRAY VIDEOS USER				
					break;
		
					default:
						System.out.println("BACK TO PREVIOUS MENU");					
				}			
										
			}while(option!=5);						
			
		}	else {
			
				System.out.println("The User doesn't exist ");
		}
	}


	///-------------------------------------------------------------------------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------
	///--------------------------------------------	CREATE VIDEO ---------------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------


	

	private static Video createVideo(String nick)  {
	
		String url = null;
		String title = null;
		String entry;
		boolean isEmpty=false;
		boolean ok=false;
		Video video = null;
		boolean isOk=false;
		
	
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("-------------------- NEW VIDEO ---------------------------------------------\n");			
		
		try {
				

			
			do {								//WHILE URL WRONG OR FIELD EMPTY
								
				System.out.println("Enter the url of the new video \n");			
				url = sc.next();	
				
				isEmpty=fieldEmpty (url);
					
				try {
						
					isOk= verifyURL(url);
						
				} catch (Url_Erronea e) {
					System.out.println(e);
				}
					
			}while(isOk==false || isEmpty==true);
			
		System.out.println("Enter the title of the new video \n");		
		title = sc.next();	
		
		isEmpty=fieldEmpty (title);
			
		ok=true;
			
		}catch (Field_Error e){
			System.out.println(e);				
		}
			
			
			
		if (ok==true) {											//IF VIDEO EXIST ADD NEW TAG
			    video = new Video(nick, url, title);
			}else {
				video=null;
			}
			
		if (video!=null) {			
				
			video= insertTags(video);		
			
		}
			
					
		return video;
	}


	
	
	///------------------------------------------------------------------------------------------------------------------------
	///------------------------------------------------------------------------------------------------------------------------
	///--------------------------------------------	VIEW VIDEOS ---------------------------------------------------------------
	///------------------------------------------------------------------------------------------------------------------------
	///------------------------------------------------------------------------------------------------------------------------


		
	private static void ViewVideos(List<Video> videos) {			//VIEW DETAILS VIDEO
		
		for (Video video : videos) {
				
			System.out.println(video + "\n");				
		}		
			
	}

	
		

		///-------------------------------------------------------------------------------------------------------------------------
		///-------------------------------------------------------------------------------------------------------------------------
		///--------------------------------------------	EDIT VIDEO -----------------------------------------------------------------
		///-------------------------------------------------------------------------------------------------------------------------
		///-------------------------------------------------------------------------------------------------------------------------

	
	private static void EditVideo(List<Video> videos) throws Field_Error {
		
		int index=-1;
		boolean isOk=false;
		int option=0;
		boolean isEmpty=false;
		int iCont=0;
		int iOption=0;
		String sTag="";
		
		
		System.out.println("Edita video");
		
		index=findVideo(videos);				//FIND VIDEO BELOWS USER
		
		if (index < 0 ) {						//IF INDEX <0 THERE ISN'T ELEMENTS INSIDE ARRAY VIDEOS
			
			System.out.println("Video doesn't exist");
			
		}else {
			
			
			Scanner entry = new Scanner(System.in);
			String url="";
				
			
			do {					//WHILE DON'T CHOOSE OPTION EXIT
					
				menuEdit();
					
				do {				//WHILE OPTION WRONG
						
					  try {									  
							option=  Integer.parseInt(entry.next());	
								
			            } catch (NumberFormatException excepcion) {						            	
			            	System.out.println("ERROR --- You must enter a number.");
				        }		
						  
						if(option != 1 && option !=2 && option!=3 && option!=4 && option!=5) {		
							
							System.out.println("ERROR --- You must choose option ( 1 / 2 / 3 / 4 / 5 )");						
						}
							
				}while(option!=1 && option !=2 && option!=3 && option!=4  && option!=5);			
					
					
					
				switch(option){
					
					case 1:	
							
						System.out.println("----------------------------MODIFY URL -----------------------------------------------\n");					
						System.out.println("Change the url : " + videos.get(index).getUrl() + " .");
						
						url=entry.next();
						
						do {			//WHILE FIELD IS EMPTY AND URL WRONG
			
							isEmpty=fieldEmpty(url);			//CHECK IS EMPTY
								
							try {			
									
									
																		//	} catch (Field_Error e2) {
																				
																		//		e2.printStackTrace();
																		//		System.out.println("Field is empty");
																		//	}
																		
																//			try {
																				
								isOk=verifyURL(url);							//CHECK IS OK URL
										
								} catch (Url_Erronea e1) {
										
									e1.printStackTrace();
									System.out.println("Url mistake");
								}
									
						}while(isEmpty==true && isOk ==false);
							
						if (isEmpty!=true && isOk !=false)	videos.get(index).setUrl(url);		//RE WRITE SET URL
							
					break;
						
					case 2:	
							
						System.out.println("----------------------------MODIFY TAGS -----------------------------------------------\n");					
						videos.get(index);
						System.out.println("Change tags of : " + videos.get(index).getTagList() + " ");
							
						if (videos.get(index).getTagList().size()>0 ) {
									
							for (String tag : videos.get(index).getTagList()) {				//GENERATE THE MENU DELETE TAG
								iCont++;
								System.out.println(iCont + ". " + tag);									
							}							
								
							System.out.println("Select tag do you want change");			//CHOOSE TAG TO MODIFY
							do {
									
								iOption=Integer.parseInt(entry.next());
								if (!(iOption >= 1 && iOption <= iCont)) {
									System.out.println("You must enter a number belows menu ( 1 - "+ iCont +" )" );
								}
						
									
							}while (!(iOption >= 1 && iOption <= iCont));
								
							
							System.out.println("Enter new tag to reemplace");
								
							sTag=entry.next();												//ENTER OPTION TAG TO MODIFY
								
							isEmpty=fieldEmpty(sTag);										//CHECK IS EMPTY 
								
							if (isEmpty ==false) { 									
								videos.get(index).getTagList().set(iOption-1, sTag);		//RE WRITE SET ELEMENT IN TAG LIST							
								}else {
										
									System.out.println("Has been generated one Error because video "+ videos.get(index).getTagList() + " hasn´t Tags");
								}
								
							iCont=0;
						}else {
							System.out.println("Tags List is Empty");
						}
					break;
						
					case 3:	
							
						System.out.println("----------------------------INSERT TAG --------------------------------------------\n");			
							
						//videos.get(index);						
						System.out.println("Do you want Insert more tags : " + videos.get(index).getTagList()  + " ?");
							
						insertTags(videos.get(index));				//CALL FUNCTION INSERT NEW TAG
						System.out.println(videos);		
					break;
					
					case 4:		
						iCont=0;
						System.out.println("----------------------------DELETE TAG --------------------------------------------\n");			
						videos.get(index);
						System.out.println("Delete tags : " + videos.get(index).getTagList()  + " ?");											
							
						if (videos.get(index).getTagList().size()>0 )
								{
								for (String tag : videos.get(index).getTagList()) {		//GENERATE THE MENU DELETE TAG
									iCont++;
									System.out.println(iCont + ". " + tag);									
								}							
									
								System.out.println("Select tag do you want eraser");
								do {													//CHOOSE TAG TO ERASER
										
									iOption=Integer.parseInt(entry.next());
									if (!(iOption >= 1 && iOption <= iCont)) {
										System.out.println("You must enter a number belows menu ( 1 - "+ iCont +" )" );
									}
										
								}while (!(iOption >= 1 && iOption <= iCont));					
																			
									videos.get(index).getTagList().remove(iOption-1);		//REMOVE ELEMENT IN TAG LIST							
									
									System.out.println("Tag has been removed");
						}else {
								System.out.println("Tags List is Empty");
						}
								
							
					break;
		
					default:
							System.out.println("BACK TO PREVIOUS MENU");					
				}			
										
			}while(option!=5);			
		
		}	
		
	}


			


	///-------------------------------------------------------------------------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------
	///--------------------------------------------	INSERT TAGS ----------------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------

	
	private static Video insertTags(Video video) {
		
		Scanner sc= new Scanner (System.in);
		boolean isEmpty=false;
		String entry="";
		String sOption="Y";
		
		System.out.println("--------------------- TAGS ------------------------------------------------------\n");		
		
		
		do {
				
			try {
					
				System.out.println("Enter new tag ");
					
				entry=sc.next();	
				
				isEmpty=fieldEmpty (entry);
					
				video.addTag(entry);	
					
				do {
						
					System.out.println("Finish ? Yes -( Y ) / NO -( N )");			
						
					sOption=  sc.next();											
					isEmpty=fieldEmpty (entry);						  
						  
					if(!sOption.equalsIgnoreCase("Y") && !sOption.equalsIgnoreCase("N") ) {									
							System.out.println("ERROR --- You must choose option ( 1 to exit or 0 to continue )");						
						}							
							
				}while(!sOption.equalsIgnoreCase("Y") && !sOption.equalsIgnoreCase("N"));							
			}
			catch(Field_Error e ) {
				System.out.println(e);				
			}
					
				
		}while (!sOption.equalsIgnoreCase("Y"));
	
		return video;
	}

		
		
		
		
		
		///-------------------------------------------------------------------------------------------------------------------------
		///-------------------------------------------------------------------------------------------------------------------------
		///--------------------------------------------	MENU -----------------------------------------------------------------------
		///-------------------------------------------------------------------------------------------------------------------------
		///-------------------------------------------------------------------------------------------------------------------------


	private static void menuClient() {
	
		System.out.println("\n---------------------  Clients -------------------------------------\n");	
		System.out.println("--------------------------------------------------------------------\n");		

		System.out.println("1. Register a new client.  \n");
		System.out.println("2. Unsubscribe Client. \n");
		System.out.println("3. View customer data. \n");
		System.out.println("0. Exit. \n");
		System.out.println("Select an Option: ( 0 - 3 )");		
		System.out.println("-------------------------------------------------------------------\n");		
	}



	private static void menuVideo() {			
	
		
		System.out.println("\n--------------------- Stock Videos ---------------------------------\n");				
		System.out.println("--------------------------------------------------------------------\n");		

		System.out.println("1. Create a new Video.  \n");
		System.out.println("2. Delete a Video. \n");
		System.out.println("3. View Videos. \n");
		System.out.println("4. Edit Video. \n");
		System.out.println("5. Back Client Menu. \n");
		System.out.println("-------------------------------------------------------------------\n");		
	}

	
	
	

	private static void menuEdit() {
		
		System.out.println("\n--------------------- Edit Video ---------------------------------\n");				
		System.out.println("--------------------------------------------------------------------\n");		

		System.out.println("1. Modify url.  \n");
		System.out.println("2. Modify Tags. \n");
		System.out.println("3. Insert new Tags. \n");
		System.out.println("4. Delete Tag. \n");
		System.out.println("5. Back Video Menu. \n");
		System.out.println("-------------------------------------------------------------------\n");		
			
		}
	
	///-------------------------------------------------------------------------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------
	///--------------------------------------------	AUXILIARS ------------------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------

	
	
	


	///-------------------------------------------------------------------------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------
	///--------------------------------------------	FIND CLIENT ----------------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------


	private static User existClient(List<User> clients) {
	
		String nickEnt;		
		String pwdEnt;	
		User client = null;
		boolean correct=false;
		boolean isEmpty=false;
		String login="login Incorrect";
			
		Scanner entry = new Scanner(System.in);

		try {
		
			do {					//WHILE FIELD EMPTY
				
			
				System.out.println("Enter your Nick \n");
				nickEnt=entry.next();
				isEmpty=fieldEmpty (nickEnt);
				
				System.out.println("Enter your Password :. \n");
				pwdEnt=entry.next();
				isEmpty=fieldEmpty (pwdEnt);
			
			}while(isEmpty==true);
			
			for (User user : clients) {			//TO VERIFY NICK AND PASSWORD
				
				correct=user.verifyPwd(nickEnt, pwdEnt);				
				if(correct==true ) { 	
					client=user;				//KEEP USER
					login="Login Correct";
				}
			}	
			
			System.out.println(login);
			
		}catch (Field_Error e) {
			
			System.out.println(e);;
		}
	
	return client;			//RETURN OBJET CLIENT
}
	
	
	
	


	///-------------------------------------------------------------------------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------
	///--------------------------------------------	FIND VIDEO -----------------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------


	private static int findVideo(List<Video> videos) {				//FIND VIDEO
		int index=-1;
		String name;
			
		Scanner sc = new Scanner(System.in);
			
		System.out.println("Enter the name of the video  \n");
	
		name = sc.next();
			
		for (Video video : videos) {
			if (video.getTitle().equals(name)) {
				index=videos.indexOf(video);				
			}			
		}
			
				
		return index;
}


	
	
	

	///-------------------------------------------------------------------------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------
	///--------------------------------------------	FIELD IS EMPTY ? -----------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	
	
				
	public static boolean fieldEmpty (String field) throws Field_Error {

		boolean empty = false;
			
		if (field.length()<=0) {
			empty =true;
			throw new Field_Error();
		}
			
		return empty;
	}
		

		
		
		
		


		///-------------------------------------------------------------------------------------------------------------------------
		///-------------------------------------------------------------------------------------------------------------------------
		///--------------------------------------------	VERIFY URL -----------------------------------------------------------------
		///-------------------------------------------------------------------------------------------------------------------------
		///-------------------------------------------------------------------------------------------------------------------------



	private static boolean verifyURL(String url) throws Url_Erronea {
			
		char letter;
		int iW=0, iDot=0;
		boolean isOk=true;
			
			
			
		if (url.length()<10) {								//CHECK LENGTH
			isOk=false;
			throw new Url_Erronea("Url must contain a minimun length of 9");		
				
		}else{
				
			for (int i = 0; i < url.length(); i++) {		//CHECKS DOTS AND wwww
					
				letter=url.charAt(i);
				if (letter=='w') {
					iW++;
				}
				if (letter=='.') {
					iDot++;
				}				
			}
		}
			
		if (iDot!=2) {
			isOk=false;			
			throw new Url_Erronea("La url must contain two dots");
		}
			
		if (iW!=3) {
			isOk=false;
			throw new Url_Erronea("La url must contain www");

		}
		return isOk;					//RETURN BOOLEN true or false
	}

		
		
		
		
}
	
			
					

