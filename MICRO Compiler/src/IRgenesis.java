import java.io.*;
import org.antlr.v4.runtime.*;
import java.lang.*;
import java.util.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class IRgenesis 
{

	public static int registerCounter = 0;
	
	public static returnTypeAndRegister printGenesis(ASTTreeNode malnourishedTree, TreeOfSymbolsForTable.TreeOfSymbols t)
	{
		returnTypeAndRegister right = null;
		returnTypeAndRegister left = null;
	   	//System.out.println("A wild node appeared! " + malnourishedTree.opcode + " " +malnourishedTree.value + " " + malnourishedTree.operand + " " + malnourishedTree.identifier + " " + malnourishedTree.type);
	   		
	   	if((malnourishedTree.opcode != null) && (malnourishedTree.opcode.matches(":=")))
	    {

			right = printGenesis(malnourishedTree.rightChildNode, t);
			left = printGenesis(malnourishedTree.leftChildNode, t);
		
	   		//System.out.println("SCOPETASTIC: " + t.scope);

		    /* Variable Declarations! */

		    if (malnourishedTree.opcode.matches(":="))
			{	

				if (TypeHashMap.smokedHashMap.get(malnourishedTree.leftChildNode.identifier).equals("INT"))
				{
					if (malnourishedTree.rightChildNode.value == null)
					{
						System.out.println(";STOREI " + TreeOfSymbolsForTable.registerValue(t) + " " + malnourishedTree.leftChildNode.identifier);
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", TreeOfSymbolsForTable.tinyRegisterValue(t), malnourishedTree.leftChildNode.identifier));
					}
					else
					{
						System.out.println(";STOREI " + malnourishedTree.rightChildNode.value + " " + TreeOfSymbolsForTable.registerIncrementer(t));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", malnourishedTree.rightChildNode.value, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						System.out.println(";STOREI " + TreeOfSymbolsForTable.registerValue(t) + " " + malnourishedTree.leftChildNode.identifier);
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", TreeOfSymbolsForTable.tinyRegisterValue(t), malnourishedTree.leftChildNode.identifier));
					}
				}
				else if (TypeHashMap.smokedHashMap.get(malnourishedTree.leftChildNode.identifier).equals("FLOAT"))
				{
					if (malnourishedTree.rightChildNode.value == null)
					{
						System.out.println(";STOREF " + TreeOfSymbolsForTable.registerValue(t) + " " + malnourishedTree.leftChildNode.identifier);
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", TreeOfSymbolsForTable.tinyRegisterValue(t), malnourishedTree.leftChildNode.identifier));
					}
					else
					{
						System.out.println(";STOREF " + malnourishedTree.rightChildNode.value + " " + TreeOfSymbolsForTable.registerIncrementer(t));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", malnourishedTree.rightChildNode.value, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						System.out.println(";STOREF " + TreeOfSymbolsForTable.registerValue(t) + " " + malnourishedTree.leftChildNode.identifier);
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", TreeOfSymbolsForTable.tinyRegisterValue(t), malnourishedTree.leftChildNode.identifier));
					}
				}

			}
			return null;
		}

		/* READS! */

		else if ((malnourishedTree.opcode != null) && malnourishedTree.opcode.matches("READ"))
		{
			
			Iterator iterator = malnourishedTree.readList.iterator();
		
			while (iterator.hasNext())
			{
	  			String element = (String) iterator.next();

	  			if ((TypeHashMap.smokedHashMap.get(element)).equals("INT"))
	  			{
	  				System.out.println(";READI " + element);
	  				theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("sys", "readi", element));
	  			}
	  			else if ((TypeHashMap.smokedHashMap.get(element)).equals("FLOAT"))
	  			{
	  				System.out.println(";READF " + element);
	  				theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("sys", "readr", element));
	  			}
	  			
			}
			return null;
		}

		/* WRITES! */

		else if ((malnourishedTree.opcode != null) && malnourishedTree.opcode.matches("WRITE"))
		{
			
			Iterator iterator = malnourishedTree.writeList.iterator();
		
			while (iterator.hasNext())
			{
	  			String element = (String) iterator.next();

	  			if ((TypeHashMap.smokedHashMap.get(element) != null) && (TypeHashMap.smokedHashMap.get(element)).equals("INT"))
	  			{
	  				System.out.println(";WRITEI " + element);
	  				theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("sys", "writei", element));
	  			}
	  			else if ((TypeHashMap.smokedHashMap.get(element) != null) && (TypeHashMap.smokedHashMap.get(element)).equals("FLOAT"))
	  			{
	  				System.out.println(";WRITEF " + element);
	  				theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("sys", "writer", element));
	  			}
	  			else if (element.equals("newline"))
	  			{
	  				System.out.println(";WRITES " + "newline");
	  				theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("sys", "writes", "newline"));
	  			}
	  			
			}
			return null;
		}

		/* Multiplication! Division! Addition! Subtraction! */
		
		else if ((malnourishedTree.opcode != null) && malnourishedTree.opcode.matches("\\*|\\/|\\+|\\-"))
		{
			// System.out.println("A wild node appeared! " + malnourishedTree.opcode + " " +malnourishedTree.value + " " + malnourishedTree.operand + " " + malnourishedTree.identifier + " " + malnourishedTree.type);

			right = printGenesis(malnourishedTree.rightChildNode, t);
	   		left = printGenesis(malnourishedTree.leftChildNode, t);

			if (malnourishedTree.opcode.matches("\\*"))
			{
				if (right.type.matches("INT") && left.type.matches("INT"))
				{
					System.out.println(";MULTI " + left.result + " " + right.result + " " + TreeOfSymbolsForTable.registerIncrementer(t));
					String oldRight = (String) right.result;
					String oldLeft = (String) left.result;
					String[] rightValue =  oldRight.split("[\\$T]");
					String[] leftValue = oldLeft.split("[\\$T]");
					
					if ( !oldRight.contains("$") && !oldLeft.contains("$"))
					{
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", right.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("muli", left.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}	
					else if (oldRight.contains("$") && !oldLeft.contains("$"))
					{
						int theRInt = Integer.parseInt(rightValue[2]);
						theRInt = theRInt - 1;
						String rString = "" + theRInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", "r" + rString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("muli", left.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}	
					else if (oldLeft.contains("$") && !oldRight.contains("$"))
					{
						int theLInt = Integer.parseInt(leftValue[2]);
						theLInt = theLInt - 1;
						String lString = "" + theLInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", right.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("muli", "r" + lString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}							
					else
					{
					
						int theRInt = Integer.parseInt(rightValue[2]);
						int theLInt = Integer.parseInt(leftValue[2]);
						theRInt = theRInt - 1;
						theLInt = theLInt - 1;
						String rString = "" + theRInt;
						String lString = "" + theLInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", "r" + rString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("muli", "r" + lString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						
					}
					
					return new returnTypeAndRegister(TreeOfSymbolsForTable.registerValue(t), "INT"); // MAAAAAAAAAAAAAABYE?????
				}
				else if (right.type.matches("FLOAT") || left.type.matches("FLOAT"))
				{
					System.out.println(";MULTF " + left.result + " " + right.result + " " + TreeOfSymbolsForTable.registerIncrementer(t));
					String oldRight = (String) right.result;
					String oldLeft = (String) left.result;
					String[] rightValue =  oldRight.split("[\\$T]");
					String[] leftValue = oldLeft.split("[\\$T]");
					
					if ( !oldRight.contains("$") && !oldLeft.contains("$"))
					{
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", right.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("mulr", left.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}	
					else if (oldRight.contains("$") && !oldLeft.contains("$"))
					{
						int theRInt = Integer.parseInt(rightValue[2]);
						theRInt = theRInt - 1;
						String rString = "" + theRInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", "r" + rString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("mulr", left.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}	
					else if (oldLeft.contains("$") && !oldRight.contains("$"))
					{
						int theLInt = Integer.parseInt(leftValue[2]);
						theLInt = theLInt - 1;
						String lString = "" + theLInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", right.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("mulr", "r" + lString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}							
					else
					{
					
						int theRInt = Integer.parseInt(rightValue[2]);
						int theLInt = Integer.parseInt(leftValue[2]);
						theRInt = theRInt - 1;
						theLInt = theLInt - 1;
						String rString = "" + theRInt;
						String lString = "" + theLInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", "r" + rString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("mulr", "r" + lString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						
					}

					return new returnTypeAndRegister(TreeOfSymbolsForTable.registerValue(t), "FLOAT"); // MAAAAAAAAAAAAAABYE?????
				}
				else if ((TypeHashMap.smokedHashMap.get(malnourishedTree.leftChildNode.identifier)).equals("INT") && (TypeHashMap.smokedHashMap.get(malnourishedTree.leftChildNode.identifier)).equals("INT"))
				{
					System.out.println(";MULTI " + malnourishedTree.leftChildNode.identifier + " " + malnourishedTree.rightChildNode.identifier + " " + TreeOfSymbolsForTable.registerIncrementer(t));
					theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", malnourishedTree.rightChildNode.identifier, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("muli", malnourishedTree.leftChildNode.identifier, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					return new returnTypeAndRegister(TreeOfSymbolsForTable.registerValue(t), "INT");
				}
				else
				{
					System.out.println(";MULTF " + malnourishedTree.leftChildNode.identifier + " " + malnourishedTree.rightChildNode.identifier + " " + TreeOfSymbolsForTable.registerIncrementer(t));
					theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", malnourishedTree.rightChildNode.identifier, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("mulr", malnourishedTree.leftChildNode.identifier, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					return new returnTypeAndRegister(TreeOfSymbolsForTable.registerValue(t), "FLOAT");
				}
			}
			else if (malnourishedTree.opcode.matches("\\+"))
			{
				if (right.type.matches("INT") && left.type.matches("INT"))
				{
					System.out.println(";ADDI " + left.result + " " + right.result + " " + TreeOfSymbolsForTable.registerIncrementer(t));
					String oldRight = (String) right.result;
					String oldLeft = (String) left.result;
					String[] rightValue =  oldRight.split("[\\$T]");
					String[] leftValue = oldLeft.split("[\\$T]");
					
					if ( !oldRight.contains("$") && !oldLeft.contains("$"))
					{
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", right.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("addi", left.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}	
					else if (oldRight.contains("$") && !oldLeft.contains("$"))
					{
						int theRInt = Integer.parseInt(rightValue[2]);
						theRInt = theRInt - 1;
						String rString = "" + theRInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", "r" + rString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("addi", left.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}	
					else if (oldLeft.contains("$") && !oldRight.contains("$"))
					{
						int theLInt = Integer.parseInt(leftValue[2]);
						theLInt = theLInt - 1;
						String lString = "" + theLInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", right.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("addi", "r" + lString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}							
					else
					{
					
						int theRInt = Integer.parseInt(rightValue[2]);
						int theLInt = Integer.parseInt(leftValue[2]);
						theRInt = theRInt - 1;
						theLInt = theLInt - 1;
						String rString = "" + theRInt;
						String lString = "" + theLInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", "r" + rString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("addi", "r" + lString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						
					}

					return new returnTypeAndRegister(TreeOfSymbolsForTable.registerValue(t), "INT"); // MAAAAAAAAAAAAAABYE?????
				}
				else if (right.type.matches("FLOAT") || left.type.matches("FLOAT"))
				{
					System.out.println(";ADDF " + left.result + " " + right.result + " " + TreeOfSymbolsForTable.registerIncrementer(t));
					String oldRight = (String) right.result;
					String oldLeft = (String) left.result;
					String[] rightValue =  oldRight.split("[\\$T]");
					String[] leftValue = oldLeft.split("[\\$T]");
					
					if ( !oldRight.contains("$") && !oldLeft.contains("$"))
					{
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", right.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("addr", left.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}	
					else if (oldRight.contains("$") && !oldLeft.contains("$"))
					{
						int theRInt = Integer.parseInt(rightValue[2]);
						theRInt = theRInt - 1;
						String rString = "" + theRInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", "r" + rString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("addr", left.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}	
					else if (oldLeft.contains("$") && !oldRight.contains("$"))
					{
						int theLInt = Integer.parseInt(leftValue[2]);
						theLInt = theLInt - 1;
						String lString = "" + theLInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", right.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("addr", "r" + lString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}							
					else
					{
					
						int theRInt = Integer.parseInt(rightValue[2]);
						int theLInt = Integer.parseInt(leftValue[2]);
						theRInt = theRInt - 1;
						theLInt = theLInt - 1;
						String rString = "" + theRInt;
						String lString = "" + theLInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", "r" + rString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("addr", "r" + lString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						
					}

					return new returnTypeAndRegister(TreeOfSymbolsForTable.registerValue(t), "FLOAT"); // MAAAAAAAAAAAAAABYE?????
				}
				else if ((TypeHashMap.smokedHashMap.get(malnourishedTree.leftChildNode.identifier)).equals("INT") && (TypeHashMap.smokedHashMap.get(malnourishedTree.leftChildNode.identifier)).equals("INT"))
				{
					System.out.println(";ADDI " + malnourishedTree.leftChildNode.identifier + " " + malnourishedTree.rightChildNode.identifier + " " + TreeOfSymbolsForTable.registerIncrementer(t));
					theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", malnourishedTree.rightChildNode.identifier, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("addi", malnourishedTree.leftChildNode.identifier, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					return new returnTypeAndRegister(TreeOfSymbolsForTable.registerValue(t), "INT");
				}
				else
				{
					System.out.println(";ADDF " + malnourishedTree.leftChildNode.identifier + " " + malnourishedTree.rightChildNode.identifier + " " + TreeOfSymbolsForTable.registerIncrementer(t));
					theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", malnourishedTree.rightChildNode.identifier, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("addr", malnourishedTree.leftChildNode.identifier, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					return new returnTypeAndRegister(TreeOfSymbolsForTable.registerValue(t), "FLOAT");
				}
			}
			else if (malnourishedTree.opcode.matches("\\/"))
			{
				if (right.type.matches("INT") && left.type.matches("INT"))
				{
					System.out.println(";DIVI " + left.result + " " + right.result + " " + TreeOfSymbolsForTable.registerIncrementer(t));
					String oldRight = (String) right.result;
					String oldLeft = (String) left.result;
					String[] rightValue =  oldRight.split("[\\$T]");
					String[] leftValue = oldLeft.split("[\\$T]");
					
					if ( !oldRight.contains("$") && !oldLeft.contains("$"))
					{
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", right.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("divi", left.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}	
					else if (oldRight.contains("$") && !oldLeft.contains("$"))
					{
						int theRInt = Integer.parseInt(rightValue[2]);
						theRInt = theRInt - 1;
						String rString = "" + theRInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", "r" + rString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("divi", left.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}	
					else if (oldLeft.contains("$") && !oldRight.contains("$"))
					{
						int theLInt = Integer.parseInt(leftValue[2]);
						theLInt = theLInt - 1;
						String lString = "" + theLInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", right.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("divi", "r" + lString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}							
					else
					{
					
						int theRInt = Integer.parseInt(rightValue[2]);
						int theLInt = Integer.parseInt(leftValue[2]);
						theRInt = theRInt - 1;
						theLInt = theLInt - 1;
						String rString = "" + theRInt;
						String lString = "" + theLInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", "r" + rString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("divi", "r" + lString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						
					}

					return new returnTypeAndRegister(TreeOfSymbolsForTable.registerValue(t), "INT"); // MAAAAAAAAAAAAAABYE?????
				}
				else if (right.type.matches("FLOAT") || left.type.matches("FLOAT"))
				{
					System.out.println(";DIVF " + left.result + " " + right.result + " " + TreeOfSymbolsForTable.registerIncrementer(t));
					String oldRight = (String) right.result;
					String oldLeft = (String) left.result;
					String[] rightValue =  oldRight.split("[\\$T]");
					String[] leftValue = oldLeft.split("[\\$T]");
					
					if ( !oldRight.contains("$") && !oldLeft.contains("$"))
					{
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", right.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("divr", left.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}	
					else if (oldRight.contains("$") && !oldLeft.contains("$"))
					{
						int theRInt = Integer.parseInt(rightValue[2]);
						theRInt = theRInt - 1;
						String rString = "" + theRInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", "r" + rString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("divr", left.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}	
					else if (oldLeft.contains("$") && !oldRight.contains("$"))
					{
						int theLInt = Integer.parseInt(leftValue[2]);
						theLInt = theLInt - 1;
						String lString = "" + theLInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", right.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("divr", "r" + lString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}							
					else
					{
					
						int theRInt = Integer.parseInt(rightValue[2]);
						int theLInt = Integer.parseInt(leftValue[2]);
						theRInt = theRInt - 1;
						theLInt = theLInt - 1;
						String rString = "" + theRInt;
						String lString = "" + theLInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", "r" + rString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("divr", "r" + lString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						
					}
					return new returnTypeAndRegister(TreeOfSymbolsForTable.registerValue(t), "FLOAT"); // MAAAAAAAAAAAAAABYE?????
				}
				else if ((TypeHashMap.smokedHashMap.get(malnourishedTree.leftChildNode.identifier)).equals("INT") && (TypeHashMap.smokedHashMap.get(malnourishedTree.leftChildNode.identifier)).equals("INT"))
				{
					System.out.println(";DIVI " + malnourishedTree.rightChildNode.identifier + " " + malnourishedTree.leftChildNode.identifier + " " + TreeOfSymbolsForTable.registerIncrementer(t));
					theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", malnourishedTree.rightChildNode.identifier, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("divi", malnourishedTree.leftChildNode.identifier, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					return new returnTypeAndRegister(TreeOfSymbolsForTable.registerValue(t), "INT");
				}
				else
				{
					System.out.println(";DIVF " + malnourishedTree.rightChildNode.identifier + " " + malnourishedTree.leftChildNode.identifier + " " + TreeOfSymbolsForTable.registerIncrementer(t));
					theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", malnourishedTree.rightChildNode.identifier, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("divr", malnourishedTree.leftChildNode.identifier, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					return new returnTypeAndRegister(TreeOfSymbolsForTable.registerValue(t), "FLOAT");
				}
			}
			else if (malnourishedTree.opcode.matches("\\-"))
			{
				//System.out.println("Right type: " + right.type + " " + "Left type: " + left.type + " <---------------------");

				if (right.type.matches("INT") && left.type.matches("INT"))
				{
					System.out.println(";SUBI " + left.result + " " + right.result + " " + TreeOfSymbolsForTable.registerIncrementer(t));
					String oldRight = (String) right.result;
					String oldLeft = (String) left.result;
					String[] rightValue =  oldRight.split("[\\$T]");
					String[] leftValue = oldLeft.split("[\\$T]");
					
					if ( !oldRight.contains("$") && !oldLeft.contains("$"))
					{
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", right.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("subi", left.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}	
					else if (oldRight.contains("$") && !oldLeft.contains("$"))
					{
						int theRInt = Integer.parseInt(rightValue[2]);
						theRInt = theRInt - 1;
						String rString = "" + theRInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", "r" + rString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("subi", left.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}	
					else if (oldLeft.contains("$") && !oldRight.contains("$"))
					{
						int theLInt = Integer.parseInt(leftValue[2]);
						theLInt = theLInt - 1;
						String lString = "" + theLInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", right.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("subi", "r" + lString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}							
					else
					{
					
						int theRInt = Integer.parseInt(rightValue[2]);
						int theLInt = Integer.parseInt(leftValue[2]);
						theRInt = theRInt - 1;
						theLInt = theLInt - 1;
						String rString = "" + theRInt;
						String lString = "" + theLInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", "r" + rString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("subi", "r" + lString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						
					}
					return new returnTypeAndRegister(TreeOfSymbolsForTable.registerValue(t), "INT"); // MAAAAAAAAAAAAAABYE?????
				}
				else if (right.type.matches("FLOAT") || left.type.matches("FLOAT"))
				{
					System.out.println(";SUBF " + left.result + " " + right.result + " " + TreeOfSymbolsForTable.registerIncrementer(t));
					String oldRight = (String) right.result;
					String oldLeft = (String) left.result;
					String[] rightValue =  oldRight.split("[\\$T]");
					String[] leftValue = oldLeft.split("[\\$T]");
					
					if ( !oldRight.contains("$") && !oldLeft.contains("$"))
					{
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", right.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("subr", left.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}	
					else if (oldRight.contains("$") && !oldLeft.contains("$"))
					{
						int theRInt = Integer.parseInt(rightValue[2]);
						theRInt = theRInt - 1;
						String rString = "" + theRInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", "r" + rString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("subr", left.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}	
					else if (oldLeft.contains("$") && !oldRight.contains("$"))
					{
						int theLInt = Integer.parseInt(leftValue[2]);
						theLInt = theLInt - 1;
						String lString = "" + theLInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", right.result, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("subr", "r" + lString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					}							
					else
					{
					
						int theRInt = Integer.parseInt(rightValue[2]);
						int theLInt = Integer.parseInt(leftValue[2]);
						theRInt = theRInt - 1;
						theLInt = theLInt - 1;
						String rString = "" + theRInt;
						String lString = "" + theLInt;
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", "r" + rString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("subr", "r" + lString, TreeOfSymbolsForTable.tinyRegisterValue(t)));
						
					}
					return new returnTypeAndRegister(TreeOfSymbolsForTable.registerValue(t), "FLOAT"); // MAAAAAAAAAAAAAABYE?????
				}
				else if ((TypeHashMap.smokedHashMap.get(malnourishedTree.leftChildNode.identifier)).equals("INT") && (TypeHashMap.smokedHashMap.get(malnourishedTree.leftChildNode.identifier)).equals("INT"))
				{
					System.out.println(";SUBI " + malnourishedTree.leftChildNode.identifier + " " + malnourishedTree.rightChildNode.identifier + " " + TreeOfSymbolsForTable.registerIncrementer(t));
					theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", malnourishedTree.rightChildNode.identifier, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("subi", malnourishedTree.leftChildNode.identifier, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					return new returnTypeAndRegister(TreeOfSymbolsForTable.registerValue(t), "INT");
				}
				else
				{
					System.out.println(";SUBF " + malnourishedTree.leftChildNode.identifier + " " + malnourishedTree.rightChildNode.identifier + " " + TreeOfSymbolsForTable.registerIncrementer(t));
					theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("move", malnourishedTree.rightChildNode.identifier, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					theIRNodePrinter.listOfTinyNodes.add(new theIRNode ("subr", malnourishedTree.leftChildNode.identifier, TreeOfSymbolsForTable.tinyRegisterValue(t)));
					return new returnTypeAndRegister(TreeOfSymbolsForTable.registerValue(t), "FLOAT");
				}
			}
			
			return null; // Not fucking sure at all!!!
		}

		else
		{

			String typer = "";

			if (malnourishedTree.identifier != null)
			{ 
				// System.out.println("obj:"+TypeHashMap.smokedHashMap.get(malnourishedTree.identifier));
				String temporarilyDrugFree = (String) TypeHashMap.smokedHashMap.get(malnourishedTree.identifier);
				//System.out.println("Kimchi: " + malnourishedTree.identifier + " " + temporarilyDrugFree);
				return new returnTypeAndRegister(malnourishedTree.identifier, temporarilyDrugFree);
			}

			else if (malnourishedTree.identifier == null && malnourishedTree.value.matches("\\d+"))
			{
				typer="INT";
			}

			else if (malnourishedTree.identifier == null && malnourishedTree.value.matches("\\d+\\.?\\d*"))
			{
				typer="FLOAT";
			}
			
			return new returnTypeAndRegister(malnourishedTree.value, typer);		
			
		}
		//return null;		
	}	

}
