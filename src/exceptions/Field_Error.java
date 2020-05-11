package exceptions;

public class Field_Error extends Exception{

	public Field_Error() {
		super ("The field must not be empty");
		
	}
	
	public Field_Error(String msj_error){
		super (msj_error);
		
	}
}
