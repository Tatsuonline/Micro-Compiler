import java.lang.*;
import java.util.*;

public class TypeHashMap
{

	static Map smokedHashMap = new HashMap();
	
	public static void CreateHashMap (ArrayList values, String type)
	{
		
		// 2nd Attempt: Access via Iterator.
		Iterator iterator = values.iterator();
		
		while(iterator.hasNext())
		{
  			String element = (String) iterator.next();
  			smokedHashMap.put(element, type);
		}

		/* First Attempt: For loop.
		for (String temp : values)
		{
			smokedHashMap.put(temp, type);
		} */
	}

}