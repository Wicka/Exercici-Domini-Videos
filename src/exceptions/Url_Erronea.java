

package exceptions;

public class Url_Erronea extends Exception{
	
	String msj="";	
	int error;	
	
	public Url_Erronea(int error) {
	
		this.msj=verify( error);
		this.error=error;
	
	}
	
	public String  verify( int error) {
		
		switch (error) {
		
		case 1:
			msj= ("Url must contain a minimun length of 9");		

		break;
		case 2:
			msj=("La url must contain two dots");
			
		break;
		case 3:
			msj=("La url must contain www");			

		break;
		default:
		break;
			
		}		
	
		return msj;
	}
	

	public String getMsj() {
		return msj;
	}

	public void setMsj(String msj) {
		this.msj = msj;
	}

	
}
