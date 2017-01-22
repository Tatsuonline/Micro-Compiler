import java.lang.*;
import java.util.*;

public class theIRNode
{
	public String opcode;
	public String operand;
	public String result;

	public theIRNode (String opcode, String operand, String result)
	{
		this.opcode = opcode;
		this.operand = operand;
		this.result = result;
	}
}