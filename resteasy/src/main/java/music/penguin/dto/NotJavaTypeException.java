package music.penguin.dto;

public class NotJavaTypeException extends RuntimeException {

	private static final long serialVersionUID = -4248610888142666505L;

	public NotJavaTypeException(String message) {
		super(message);
	}
}
