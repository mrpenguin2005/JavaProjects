package music.penguin.domain;

import java.io.Serializable;

public interface BaseInterface<T extends Serializable> {
	
	public T getId();

}
