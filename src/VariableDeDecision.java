
public class VariableDeDecision
{
	private float value;
	private boolean locked;
	
	public VariableDeDecision(float value, boolean locked)
	{
		this.value = value;
		this.locked = locked;
	}
	
	
	
	public boolean isLocked()
	{
		return locked;
	}
	
	
	public float getValue()
	{
		return value;
	}
	
}
