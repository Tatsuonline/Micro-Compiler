import java.io.*;
import org.antlr.v4.runtime.*;
import java.lang.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class TinyGenesis 
{

	public static List <String> theStupidVariables = new ArrayList ();
	public static String stringDecl1;
	public static String stringDecl2;
	static int registerCounter = 0;
	static int intFound = 0;
	static int fltFound = 0;
	static int onceTravelled = 0;

	public static void printVariables(List theReallyStupidVariables)
	{
		theStupidVariables = theReallyStupidVariables;
	}

	public static void stringDeclarations(String useThisInsteadOf, String thisString)
	{
		stringDecl1 = useThisInsteadOf;
		stringDecl2 = thisString;
	}

	public static void printTinyGenesis(ASTTreeNode malnourishedTree, TreeOfSymbolsForTable.TreeOfSymbols t)
	{
		/* Print the damn variables. */

		Iterator iterator = theStupidVariables.iterator();

		if (onceTravelled == 0)
		{
			while (iterator.hasNext())
			{
				String element = (String) iterator.next();
				System.out.println("var " + element);
			}
		}

		/* Print String Declarations */

		if (onceTravelled == 0)
		{
			System.out.println("str " + stringDecl1 + " " + stringDecl2);
		}

		onceTravelled++;

		/* Traverse 

		if((malnourishedTree.opcode != null) && (malnourishedTree.opcode.matches(":=")))
	    {

		   	printTinyGenesis(malnourishedTree.rightChildNode, t);
	   		printTinyGenesis(malnourishedTree.leftChildNode, t);
	
	   	}

	    /* Variable Declarations! 

	    if ((malnourishedTree.opcode == null) && (malnourishedTree.type != null) && (malnourishedTree.type.matches("INT")))
		{
			System.out.println("move " + malnourishedTree.value + " r" + registerCounter);
			intFound++;
		}
		else if ((malnourishedTree.opcode == null) && (malnourishedTree.identifier != null) && (intFound != 0))
		{
			System.out.println("move " + "r" + registerCounter + " " + malnourishedTree.identifier);
			registerCounter++;
			intFound = 0;
		}
		else if ((malnourishedTree.opcode == null) && (malnourishedTree.type != null) && (malnourishedTree.type.matches("FLOAT")))
		{
			System.out.println("move " + malnourishedTree.value + " r" + registerCounter);
			fltFound++;
		}
		else if ((malnourishedTree.opcode == null) && (malnourishedTree.identifier != null) && (fltFound != 0))
		{
			System.out.println("move " + "r" + registerCounter + " " + malnourishedTree.identifier);
			registerCounter++;
			fltFound = 0;
		}

		/* READS! 

		if ((malnourishedTree.opcode != null) && malnourishedTree.opcode.matches("READ"))
		{

			Iterator readIterator = malnourishedTree.readList.iterator();
		
			while (readIterator.hasNext())
			{
	  			String element = (String) readIterator.next();

	  			if ((TypeHashMap.smokedHashMap.get(element)).equals("INT"))
	  			{
	  				System.out.println("sys readi " + element);
	  			}
	  			else if ((TypeHashMap.smokedHashMap.get(element)).equals("FLOAT"))
	  			{
	  				System.out.println("sys readf " + element);
	  			}
	  			
			}
			
		}

		/* WRITES! 

		if ((malnourishedTree.opcode != null) && malnourishedTree.opcode.matches("WRITE"))
		{
			
			Iterator writeIterator = malnourishedTree.writeList.iterator();
		
			while (writeIterator.hasNext())
			{
	  			String element = (String) writeIterator.next();

	  			if ((TypeHashMap.smokedHashMap.get(element) != null) && (TypeHashMap.smokedHashMap.get(element)).equals("INT"))
	  			{
	  				System.out.println("sys writei " + element);
	  			}
	  			else if ((TypeHashMap.smokedHashMap.get(element) != null) && (TypeHashMap.smokedHashMap.get(element)).equals("FLOAT"))
	  			{
	  				System.out.println("sys writef " + element);
	  			}
	  			else if (element.equals("newline"))
	  			{
	  				System.out.println("sys writes " + "newline");
	  			}
	  			
			}
			
		} */
	}

}	