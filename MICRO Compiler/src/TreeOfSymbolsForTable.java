import java.io.*;
import java.util.*;
import org.antlr.v4.runtime.*;

public class TreeOfSymbolsForTable 
{

	TreeOfSymbols global = new TreeOfSymbols("GLOBAL"); // Orphan!!! Has no parents!!!
	TreeOfSymbols currentScope;

	class TreeOfSymbols 
	{
		TreeOfSymbols parent;
		String scope; // Name refers to the entity.
		LinkedList <TreeOfSymbols> children;
		LinkedHashMap <String, typeIdentifier> mapOfHashes;
		public List <ASTTreeNode> listOfTrees = new ArrayList <ASTTreeNode> ();
		public int realCounter = 0;

		public TreeOfSymbols (String scope) 
		{
			this.scope = scope;
			parent = null;
			children = new LinkedList <TreeOfSymbols>();
			mapOfHashes = new LinkedHashMap <String, typeIdentifier>();
		}
		
		/*public void printTable() 
		{
			System.out.println("Symbol table " + scope);
			Set <String> keys = mapOfHashes.keySet();
			
			for (String key : keys) 
			{
				System.out.println(mapOfHashes.get(key));
			}
		} */
	}

		
	
	

	int blockCounter = 1;
	
	public void enterScope() 
	{
		enterScope("BLOCK " + blockCounter);
		blockCounter += 1;
	}

	public TreeOfSymbolsForTable() 
	{
		currentScope = global;
	}

	public void enterScope(String scope) 
	{
		TreeOfSymbols st = new TreeOfSymbols(scope);
		st.parent = currentScope;
		currentScope = st;
		st.parent.children.add(st);
	}
	
	public void append_var_1(ArrayList<String> names, String type) 
	{
		for (String name : names) 
		{
			append_var_2(name, type);
		}
	}

	public void append_var_2(String name, String type) 
	{
		
		if (currentScope.mapOfHashes.containsKey(name)) 
		{
			System.out.println("DECLARATION ERROR " + name);
			System.exit(0);
		}

		currentScope.mapOfHashes.put(name, new typeIdentifier(name, type));
	}

	public void exitScope() 
	{
		currentScope = currentScope.parent;
	}

	public void append_str(String name, String value) 
	{
        currentScope.mapOfHashes.put(name, new typeIdentifier(name, "STRING", value));
    }

	public void printTree() 
	{
		thePrintTree(global);
	}

	private void thePrintTree(TreeOfSymbols st) 
	{
		//st.printTable();
		//System.out.println();
	
		for (TreeOfSymbols child : st.children) 
		{
			thePrintTree(child);
		}
	}

	public void addStmt(ASTTreeNode stmtTree)
	{
		currentScope.listOfTrees.add(0, stmtTree);
	}

	public static String registerIncrementer (TreeOfSymbols currentScope)
	{
		return "$T" + (++currentScope.realCounter);
	}

	public static String registerValue (TreeOfSymbols currentScope)
	{
		return "$T" + (currentScope.realCounter);
	}

	public static String tinyRegisterValue (TreeOfSymbols currentScope)
	{
		return "r" + (currentScope.realCounter - 1);
	}

	class typeIdentifier 
	{
	String name;
	String type;
	String value;
	
	// The default.
	typeIdentifier (String name, String type)
	{
		this.name = name;
		this.type = type;
		this.value = null;
	}
	
	// Option 2.
	typeIdentifier (String name, String type, String value) 
	{
		this.name = name;
		this.type = type;
		this.value = value;
	}

	
	@Override
	public String toString() 
	{
		if (type.equals("STRING")) 
		{
			return new String("name " + name + " type " + type + " value " + value);
		} 
		else 
		{
			return new String("name " + name + " type " + type);
		}
	}
	}

}