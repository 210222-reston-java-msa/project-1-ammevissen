package com.revature.models;

public class ErrorMsg {
	private String err;

	public ErrorMsg() {
		super();
	}
	
	public ErrorMsg(String err) {
		super();
		this.err = err;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	
	@Override
	public String toString() {
		return "ErrorMsg [err=" + err + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((err == null) ? 0 : err.hashCode());
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
		ErrorMsg other = (ErrorMsg) obj;
		if (err == null) {
			if (other.err != null)
				return false;
		} else if (!err.equals(other.err))
			return false;
		return true;
	}
	
	
}
