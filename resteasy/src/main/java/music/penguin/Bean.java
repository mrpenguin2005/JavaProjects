package music.penguin;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class Bean {
	
	private String name;
	private Integer age;
	
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL,
			typing = JsonSerialize.Typing.STATIC)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL,
			typing = JsonSerialize.Typing.STATIC)
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}

}
