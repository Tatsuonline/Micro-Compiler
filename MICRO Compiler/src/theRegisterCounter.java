import java.lang.*;
import java.util.*;

public class theRegisterCounter
{
	public static int realCounter = 0;

	public static String incrementder ()
	{
		return "$T" + (realCounter++);
	}

	public static String callear ()
	{
		return "$T" + (realCounter);
	}

}