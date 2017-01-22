import java.io.*;
import org.antlr.v4.runtime.*;
import java.lang.*;
import java.util.*;

public class ASTTreeNode
{

	String opcode = null;
	String operand = null;
	String identifier = null;
	String value = null;
	String type = null;
	List readList = null;
	List writeList = null;
	ASTTreeNode leftChildNode = null;
	ASTTreeNode rightChildNode = null;

	void updateOpcode (String newOpcode)
	{
		opcode = newOpcode;
	}

	void updateOperand (String newOperand)
	{
		operand = newOperand;
	}

	void updateId (String newId)
	{
		identifier = newId;
	}

	void updateValue (String newValue)
	{
		value = newValue;
	}

	void updateType (String newType)
	{
		type = newType;
	}

	void updateReadList (List newReadList)
	{
		readList = newReadList;
	}

	void updateWriteList (List newWriteList)
	{
		writeList = newWriteList;
	}

	void updateLeftChild (ASTTreeNode newLeftChild)
	{
		leftChildNode = newLeftChild;
	}

	void updateRightChild (ASTTreeNode newRightChild)
	{
		rightChildNode = newRightChild;
	}

}