import java.io.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.RecognitionException; // For some reason, this isn't included above?
import org.antlr.v4.runtime.misc.ParseCancellationException; // For some reason, this isn't included above?
import java.lang.*;
import java.util.List;
import java.util.ArrayList;
import java.util.*;

/*
1) Variables can be declared before any functions. These are "global" variables, and can be accessed from any function.
2) Variables can be declared as part of a function's parameter list. These are "local" to the function, and cannot be accessed by any other function.
3) Variables can be declared at the beginning of a function body. These are "local" to the function as well.
4) Variables can be declared at the beginning of a then block, an else block, or a repeat statement. These are "local" to the block itself. Other blocks, 
   even in the same function, cannot access these variables.
*/

public class Micro {

    public static void main (String[] args) throws Exception {

	ANTLRFileStream inputFile = new ANTLRFileStream(args[0]);
	MicroLexer lexLuthor = new MicroLexer((CharStream)inputFile); // This course is my Kryptonite.
	CommonTokenStream riverOfTokens = new CommonTokenStream(lexLuthor); // Don't let the name fool you. Tokens aren't common at all.
	MicroParser parser = new MicroParser(riverOfTokens); // Parse a river.
	parser.setErrorHandler(new EscapeFrom468()); // Need to bail!
	int Test = 0;

	try
	    {
		parser.program(); // .program is the starting point rule!	
	    }
	catch(ParseCancellationException sillyParserErrors)
	    {
		//System.out.println("Not Accepted");
		Test = Test + 1;
	    }

	if (Test == 0)
	    {
		parser.theFantasticSymbolTree.printTree(); // Print the damn tree!
	    }

	//System.out.println("FUCK COMPILERS! I FUCKING HATE THIS COURSE!");
        System.out.println(";IR code");
        for(TreeOfSymbolsForTable.TreeOfSymbols t : parser.theFantasticSymbolTree.global.children) {
            System.out.println(";LABEL " + t.scope);
            System.out.println(";LINK");
            IHate468(t);
        }
	
    }

    public static void IHate468 (TreeOfSymbolsForTable.TreeOfSymbols t)
    {
        List<ASTTreeNode> wiltingTree = t.listOfTrees;
    	int i = 0;

    	//ASTTreeNode currentNode = new ASTTreeNode();
    	for (i = 0; i < wiltingTree.size(); i++)
    	{   
            //System.out.println(wiltingTree.get(i)); 		
    		IRgenesis.printGenesis(wiltingTree.get(i), t);
    	}

        System.out.println(";RET");
        System.out.println(";tiny code");

        int j = 0;

        for (j = 0; j < wiltingTree.size(); j++)
        {         
            TinyGenesis.printTinyGenesis(wiltingTree.get(j), t);
        }

        for(theIRNode n : theIRNodePrinter.listOfTinyNodes) {
            System.out.println(n.opcode + " " + n.operand + " " + n.result);
        }

        System.out.println("sys halt");
    } 


}
