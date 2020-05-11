package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import exceptions.Url_Erronea;
import models.User;
import models.Video;

public class Principal {	


	public static void main(String[] args) {
	
		List <User> clients =new ArrayList<User>();			//LISTA DONDE GUARDAR LOS CLIENTES DADOS DE ALTA
		
		Scanner entry = new Scanner(System.in);				//VARIABLE DONDE RECOJER LA ENTRADA DE CONSOLA
		int option = 0;										//DONDE RECOJER LA SELECCION DEL USUARIO
	//	int salir=0;
	//	boolean exist=false;
		
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
						viewClient(clients);
					} catch (Url_Erronea e) {					
						e.printStackTrace();
					}									
				break;						
				default:
					System.out.println("GOOD BY");			//EXIT TO PROGRAM
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
	
		Scanner entry = new Scanner(System.in);		
				
		System.out.println("--------------------- USSER ACCOUNT -------------------------\n");		
		System.out.println("Enter your name :. \n");
		nameEnt=entry.nextLine();		
		System.out.println("Enter your surname : \n");
		snameEnt=entry.nextLine();		
		System.out.println("Enter your Nick \n");
		nickEnt=entry.nextLine();		
		System.out.println("Enter your Password :. \n");
		pwdEnt=entry.nextLine();		
	
						
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
				System.out.println("The User does not exist");
				exist=false;
							
				if(user.getNick().equals(nickEnt)) {
					System.out.println("but this Nick exist in another User, Please try another Nick");
					existNick=true;
					}							
			}
		}
			
			
		if (exist!= true && existNick!=true) {
			clients.add(client);		
			System.out.println("User has been created");
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
		int position=0;								
		Scanner entry = new Scanner(System.in);
						
		System.out.println("--------------------- USSER ACCOUNT -------------------------\n");		
		System.out.println("Enter your Nick \n");
		nickEnt=entry.nextLine();
		System.out.println("Enter your Password :. \n");
		pwdEnt=entry.nextLine();

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
			System.out.println("The User does not exist");
		}
		
		return clients;			
	}	
	
	
	///---------------------------------------------------------------------------------------------------------------------------
	///---------------------------------------------------------------------------------------------------------------------------
	///--------------------------------------------	VIEW DATA USER ---------------------------------------------------------------
	///---------------------------------------------------------------------------------------------------------------------------
	///---------------------------------------------------------------------------------------------------------------------------


	
	private static void viewClient(List<User> clients) throws Url_Erronea {				// DETAILS USER
				
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
							if(option != 1 && option !=2 && option!=3 && option!=0) {									
								System.out.println("ERROR --- You must choose option ( 1 / 2 / 3 / 4 )");						
							}
							
					}while(option!=1 && option !=2 && option!=3 && option!=0  && option!=4);			
					
					
					
					switch(option){
					
						case 1:												
							System.out.println("----------------------------CREATE VIDEO-----------------------------------------------\n");					
							try {
										Video video = createVideo(client.getNick());
										client.addVideo(video);							
							}catch (Url_Erronea error){
								System.out.println("Video hasn't been create, due Url was wrong ");
							}
										
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
						
						default:
							System.out.println("BACK TO PREVIOUS MENU");					
					}			
										
				}while(option!=4);						
			
			}	else {
				System.out.println("The User doesn't exist ");
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
			
		Scanner entry = new Scanner(System.in);
		
		System.out.println("Enter your Nick \n");
		nickEnt=entry.nextLine();
		System.out.println("Enter your Password :. \n");
		pwdEnt=entry.nextLine();
		
		
		for (User user : clients) {		
			
			correct=user.verifyPwd(nickEnt, pwdEnt);				
			if(correct==true ) 	client=user;
			}	
		
		if(correct==true ) {				
			System.out.println("Login Correct");	
			}else {
				System.out.println("login Incorrect");
			}
	
	return client;
}
	
	

	///-------------------------------------------------------------------------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------
	///--------------------------------------------	CREATE VIDEO ---------------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------
	///-------------------------------------------------------------------------------------------------------------------------


	

	private static Video createVideo(String nick) throws Url_Erronea {
	
		String url;
		String title;
		String entry;
		int option=1;
		
	
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("-------------------- NEW VIDEO ---------------------------------------------\n");			
		
		System.out.println("Enter the url of the new video \n");			
		url = sc.nextLine();	
		
		if (url.length()<10) {
			
			throw new Url_Erronea("La direccion debe contener una longitud minima de 9");
			
		}
		
		System.out.println("Enter the title of the new video \n");		
		title = sc.nextLine();		
		Video video = new Video(nick, url, title);			
		System.out.println("--------------------- TAGS ------------------------------------------------------\n");				
		do {
			System.out.println("Enter new tag ");
			entry=sc.nextLine();				
			video.addTag(entry);				
			do {
				System.out.println("Finish ? 1 - Yes 0 - No ");			
				
				  try {									  
						option=  Integer.parseInt(sc.next());		//KEEP THE SELECTION IN option VARIABLE										
		            } catch (NumberFormatException excepcion) {						            	
		            	System.out.println("ERROR --- You must enter a number.");
			       }								
					if(option != 0 && option !=1 ) {									
						System.out.println("ERROR --- You must choose option ( 1 to exit or 0 to continue )");						
					}
			}while(option != 0 && option !=1);		
			
		}while (option!=1);
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
				int index=0;
				String name;
				
				Scanner sc = new Scanner(System.in);
				
				System.out.println("Enter the name of the video  \n");
		
				name = sc.nextLine();
				
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
	
		System.out.println("------------------------------------------------------------------\n");		
		System.out.println("---------------------  CLIENT MENU -------------------------\n");	
		System.out.println("1. Register a new client.  \n");
		System.out.println("2. Unsubscribe Client. \n");
		System.out.println("3. View customer data. \n");
		System.out.println("0. Exit. \n");
		System.out.println("Select an Option: ( 0 - 3 )");		
		System.out.println("-------------------------------------------------------------------");		
	}



	private static void menuVideo() {			
	
		System.out.println("--------------------- STOCK VIDEOS -------------------------\n");							
		System.out.println("1. Create a new Video.  \n");
		System.out.println("2. Delete a Video. \n");
		System.out.println("3. View Videos. \n");
		System.out.println("4. Back Client Menu. \n");
		System.out.println("-------------------------------------------------------------------");		
	}

	
}
	
			
					

