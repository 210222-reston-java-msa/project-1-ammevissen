package com.revature.scratch;

public class JacksonTestModel2 {

	private String ers_color;
	private String ers_type;

	public JacksonTestModel2() {
		super();
	}

	public JacksonTestModel2(String ers_color, String ers_type) {
		super();
		this.ers_color = ers_color;
		this.ers_type = ers_type;
	}

	public String getErs_color() {
		return ers_color;
	}

	public void setErs_color(String ers_color) {
		this.ers_color = ers_color;
	}

	public String getErs_type() {
		return ers_type;
	}

	public void setErs_type(String ers_type) {
		this.ers_type = ers_type;
	}

	@Override
	public String toString() {
		return "JacksonTestModel2 [ers_color=" + ers_color + ", ers_type=" + ers_type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ers_color == null) ? 0 : ers_color.hashCode());
		result = prime * result + ((ers_type == null) ? 0 : ers_type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JacksonTestModel2 other = (JacksonTestModel2) obj;
		if (ers_color == null) {
			if (other.ers_color != null)
				return false;
		} else if (!ers_color.equals(other.ers_color))
			return false;
		if (ers_type == null) {
			if (other.ers_type != null)
				return false;
		} else if (!ers_type.equals(other.ers_type))
			return false;
		return true;
	}
	
	
}

