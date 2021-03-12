package com.revature.models;

public class ReimbursementStatus {

	private int stausId;
	private String status;
	
	public ReimbursementStatus() {
		super();
	}

	public ReimbursementStatus(int stausId, String status) {
		super();
		this.stausId = stausId;
		this.status = status;
	}

	public int getStausId() {
		return stausId;
	}

	public void setStausId(int stausId) {
		this.stausId = stausId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ReimbursementStatus [stausId=" + stausId + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + stausId;
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
		ReimbursementStatus other = (ReimbursementStatus) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (stausId != other.stausId)
			return false;
		return true;
	}



}
