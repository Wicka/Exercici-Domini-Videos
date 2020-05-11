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
	
		List <User> clients =new ArrayList<User>();			//LISTA DONDE GUARDAR LOS CLIENTES DADOS DE ALTA
		
		Scanner entry = new Scanner(System.in);				//VARIABLE DONDE RECOJER LA ENTRADA DE CONSOLA
		int option = 10;										//DONDE RECOJER LA SELECCION DEL USUARIO
		
		do {	
			
			menuClient();					
			
			do {
				
				
					try {
							  
						  option=  Integer.parseInt(entry.next());		
							
			            } catch (Exception e) {			            	
				           System.out.println("ERROR --- You must enter a number.");		
					    }
					
			  
				 if(option != 1 && option !=2 && option!=3 && option!=0) {							
						System.out.println("ERROR --- Please enter one next options. ( 1 / 2 / 3 / 0)");						
				 }		
					
			}while(option!=1 && option !=2 && option!=3 && option!=0  );			
				
			
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
						try {
							viewClient(clients);
						} catch (Field_Error e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (Url_Erronea e) {					
						e.printStackTrace();
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
			
			System.out.println("Enter your name :. \n");
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
	
						
		Calendar registry=new GregorianCalendar();
		year=registry.get(Calendar.YEAR);
		month=registry.get(Calendar.MONTH);
		day=registry.get(Calendar.DAY_OF_MONTH);		
		date = day + "/" +  month + "/" + year;		
						
		User client = new User(nameEnt, snameEnt, nickEnt, pwdEnt,date);		
					
			
		for (User user : clients) {
			
			if(user.equals(client)) {
				
				System.out.println("User exist");
				exist=true;
						
			}else {
							
				if(user.getNick().equals(nickEnt)) {
					System.out.println("This Nick exist in another User, Please try another Nick");
					existNick=true;
					}							
			}
		}
			
			
		if (exist!= true && existNick!=true) {
			clients.add(client);		
			System.out.println("User has been created");
		}	
		
		
		
		}catch (Field_Error e){
			System.out.println(e);		
		}
		
		return clients;	
	}


		///------------------------------------------------------------------------------------------------------------------------
		///------------------------------------------------------------------------------------------------------------------------
		///--------------------------------------------	DELETE USER ---------------------------------------------------------------
		///------------------------------------------------------------------------------------------------------------------------
		///------------------------------------------------------------------------------------------------------------------------

	
	private static List<User> deleteClient(List<User> clients) {		//BORRAR USUARIO		
		
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
			isEmpty=fieldEmpty (nickEnt);
			
			System.out.println("Enter your Password :. \n");
			pwdEnt=entry.next();
			isEmpty=fieldEmpty (pwdEnt);
			
			for (User user : clients) {	
					
				if(user.getNick().equals(nickEnt) && user.getPwd().equals(pwdEnt) ) {		
						
					System.out.println("User has been deleted");	
					position=clients.indexOf(user);
					exist=true;				
					}
				}
							
			if(exist==true) {
				
				clients.remove(position);			
			}else {
				System.out.println("Incorrect Pasword");
			}
		}catch (Field_Error e) {
			System.out.println(e);
		}
		
		return clients;			
	}	
	
	
	///---------------------------------------------------------------------------------------------------------------------------
	///---------------------------------------------------------------------------------------------------------------------------
	///--------------------------------------------	VIEW DATA USER ---------------------------------------------------------------
	///---------------------------------------------------------------------------------------------------------------------------
	///---------------------------------------------------------------------------------------------------------------------------


	
	private static void viewClient(List<User> clients) throws Url_Erronea, Field_Error {				// DETAILS USER
				
			Scanner entry = new Scanner(System.in);
			int option=0;
			int index=0;
			User client;
				
			client=existClient (clients);					//VERIFY IF CLIENT EXIST
			
			if (client!=null) {				
			
				do {
					menuVideo();
					
					do {
						
						  try {									  
								option=  Integer.parseInt(entry.nextLine());										
				            } catch (NumberFormatException excepcion) {						            	
				            	System.out.println("ERROR --- You must enter a number.");
					       }								
							if(option != 1 && option !=2 && option!=3 && option!=4 && option!=5) {									
								System.out.println("ERROR --- You must choose option ( 1 / 2 / 3 / 4 / 5 )");						
							}
							
					}while(option!=1 && option !=2 && option!=3 && option!=4  && option!=5);			
					
					
					
					switch(option){
					
						case 1:												
							System.out.println("----------------------------CREATE VIDEO-----------------------------------------------\n");					
						Video video = createVideo(client.getNick());
						client.addVideo(video);
										
						break;
						
						case 2:												
							System.out.println("----------------------------DELETE VIDEO-----------------------------------------------\n");					
							index = findVideo(client.getVideos());	
							
							try {
								client.deleteVideo(index);								
							}catch (IndexOutOfBoundsException e){
								System.out.println("Has been generated one Error because user hasn´t videos");
							}
						break;
						
						case 3:												
							System.out.println("----------------------------VIEW VIDEO LIST--------------------------------------------\n");			
							System.out.println("---------------------------- "+ client.getNick() +" ------------------------------------\n");
							ViewVideos(client.getVideos());							
						break;
						
						case 4:												
							System.out.println("----------------------------EDIT VIDEO --------------------------------------------\n");			
							System.out.println("---------------------------- "+ client.getNick() +" ------------------------------------\n");
							EditVideo(client.getVideos());							
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
		
		index=findVideo(videos);
		
		if (index < 0 ) {
			System.out.println("Video doesn't exist");
		}else {
			
		
			
			
			
			Scanner entry = new Scanner(System.in);
			String url="";
				
			
				do {
					menuEdit();
					
					do {
						
						  try {									  
								option=  Integer.parseInt(entry.nextLine());										
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
							videos.get(index);
							System.out.println("Change the url : " + videos.get(index).getUrl() + " .");
							
							url=entry.next();
						
							do {
								try {			
									
									isEmpty=fieldEmpty(url);
									
									} catch (Field_Error e2) {
										
										e2.printStackTrace();
										System.out.println("Field is empty");
									}
								
									try {
										
										isOk=verifyURL(url);
										
									} catch (Url_Erronea e1) {
										
										e1.printStackTrace();
										System.out.println("Url mistake");
									}
									
						}while(isEmpty==true && isOk ==false);
							
							videos.get(index).setUrl(url);
							
						break;
						
						case 2:	
							
							System.out.println("----------------------------MODIFY TAGS -----------------------------------------------\n");					
							videos.get(index);
							System.out.println("Change tags of : " + videos.get(index).getTagList() + " ");
							
							
								for (String tag : videos.get(index).getTagList()) {
									iCont++;
									System.out.println(iCont + ". " + tag);									
								}							
								
								System.out.println("Select tag do you want change");
								do {
									
									iOption=Integer.parseInt(entry.next());
									
								}while (!(iOption >= 1 && iOption <= iCont));
								
								iCont=0;
								
								System.out.println("Enter tag modificated");
								
								sTag=entry.next();
								isEmpty=fieldEmpty(sTag);
								
								if (isEmpty ==false) { 									
									videos.get(index).getTagList().set(iOption-1, sTag);									
									}
								System.out.println("Has been generated one Error because video "+ videos.get(index).getTagList() + " hasn´t Tags");
							
						break;
						
						case 3:	
							
							System.out.println("----------------------------INSERT TAG --------------------------------------------\n");			
							videos.get(index);
							System.out.println("Do you want Insert more tags : " + videos.get(index).getTagList()  + " ?");
							
							insertTags(videos.get(index));
							System.out.println(videos);		
								
								
								
								
								
							
						break;
						
						case 4:		
							
							System.out.println("----------------------------DELETE TAG --------------------------------------------\n");			
							videos.get(index);
							System.out.println("Delete tags : " + videos.get(index).getTagList()  + " ?");
							
							
							
							for (String tag : videos.get(index).getTagList()) {
								iCont++;
								System.out.println(iCont + ". " + tag);									
							}							
							
							System.out.println("Select tag do you want eraser");
							do {
								
								iOption=Integer.parseInt(entry.next());
								
							}while (!(iOption >= 1 && iOption <= iCont));
							
							iCont=0;
							
							System.out.println("Enter tag modificated");
							
							sTag=entry.next();
							isEmpty=fieldEmpty(sTag);
							
							if (isEmpty ==false) { 									
								videos.get(index).getTagList().remove(iCont);								
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
		
			System.out.println("Enter your Nick \n");
			nickEnt=entry.next();
			isEmpty=fieldEmpty (nickEnt);
			
			System.out.println("Enter your Password :. \n");
			pwdEnt=entry.next();
			isEmpty=fieldEmpty (pwdEnt);
			
			
			for (User user : clients) {		
				
				correct=user.verifyPwd(nickEnt, pwdEnt);				
				if(correct==true ) { 	
					client=user;
					login="Login Correct";
				}
			}	
			
			System.out.println(login);
		}catch (Field_Error e) {
			System.out.println(e);;
		}
	
	return client;
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
		int option=1;
		boolean isEmpty=false;
		boolean ok=false;
		Video video = null;
		boolean isOk=false;
		
	
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("-------------------- NEW VIDEO ---------------------------------------------\n");			
		
			try {
				

			
				do {
					
					System.out.println("Enter the url of the new video \n");			
					url = sc.next();	
					isEmpty=fieldEmpty (url);
					
					try {
						isOk= verifyURL(url);
					} catch (Url_Erronea e) {
						System.out.println(e);
						e.printStackTrace();
					}
				}while(isOk==false);
			System.out.println("Enter the title of the new video \n");		
			title = sc.next();		
			isEmpty=fieldEmpty (title);
			
			ok=true;
			}catch (Field_Error e){
				System.out.println(e);				
			}
			
			
			
			if (ok==true) {
				    video = new Video(nick, url, title);
				}else {
					video=null;
				}
			
			if (video!=null) {			
				
				video= insertTags(video);		
				
			}
			
			
		
	return video;
}



	private static boolean verifyURL(String url) throws Url_Erronea {
		
		char letter;
		int iW=0, iDot=0;
		boolean isOk=true;
		
		
		
		if (url.length()<10) {
			isOk=false;
			throw new Url_Erronea("Url must contain a minimun length of 9");		
			
		}else{
			
			for (int i = 0; i < url.length(); i++) {
				
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
		return isOk;
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

				
		public static boolean fieldEmpty (String field) throws Field_Error {

			boolean empty = false;
			
			if (field.length()<=0) {
				empty =true;
				throw new Field_Error();
			}
			
			return empty;
		}	
}
	
			
					

