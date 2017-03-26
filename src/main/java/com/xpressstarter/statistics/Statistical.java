package com.xpressstarter.statistics;

public abstract class Statistical implements Comparable<Statistical>{

	protected Number number;
	
	public abstract String getName();
	public Number getValue(){
		return this.number;
	}
	public int compareTo(Statistical other) {
		double thisValue=this.number.doubleValue();
		double otherValue=other.getValue().doubleValue();
		
		if (thisValue > otherValue) return 1;
		if (thisValue < otherValue) return -1;
		if (thisValue == otherValue) return 0;
		return -1;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		Statistical other = (Statistical) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
	

}
