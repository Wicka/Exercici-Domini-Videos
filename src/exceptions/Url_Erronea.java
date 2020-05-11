package exceptions;

public class Url_Erronea extends Exception{
	
	public Url_Erronea() {
		super ("The Url isn't real");
		
	}
	public Url_Erronea(String msj_Error) {
		super (msj_Error);
	}

}
