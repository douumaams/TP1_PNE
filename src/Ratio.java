
public class Ratio implements Comparable<Ratio>{

	private float ratio;
	private int index;
	
	
	public Ratio(float ratio, int index)
	{
		this.ratio = ratio;
		this.index = index;
	}
	
	@Override
	public int compareTo(Ratio o) {
		if(o.getRatio() == ratio)
			return 0;
		if(o.getRatio() > ratio)
			return 1;
			
		return -1;
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if(obj == null)
			return false;
		if(obj == this)
			return true;
		if(obj instanceof Ratio && ((Ratio) obj).ratio == ratio && ((Ratio) obj).index == index)
			return true;
		return false;	
	}
	
	public int getIndex() {
		return index;
	}
	
	public float getRatio() {
		return ratio;
	}
	
	
	
	
}
