grammar Micro;

@header{
	import java.lang.*;
	import java.util.*;
}

options {
  language = Java;
 }

@members {
public TreeOfSymbolsForTable theFantasticSymbolTree = new TreeOfSymbolsForTable();
public List <ASTTreeNode> listOfTrees = new ArrayList <ASTTreeNode> ();
} // Just so fantastic!

//Program 
program           : (PROGRAM id BEGIN {} pgm_body END); // Added curly braces. 

id returns [String identifier]               : IDENTIFIER { $identifier = (String)$IDENTIFIER.text;};
pgm_body          : (decl func_declarations);
decl		  	  : (string_decl decl | var_decl decl | /* */);


string_decl       : (STRING id SEQUAL str SEMICOLON) 
{ 
	theFantasticSymbolTree.append_str($id.text, $str.text); 
	TinyGenesis.stringDeclarations($id.text, $str.text);
};
str               : STRINGLITERAL;


var_decl          : (var_type id_list SEMICOLON) 
{
	theFantasticSymbolTree.append_var_1($id_list.ids, $var_type.text);
	TypeHashMap.CreateHashMap($id_list.ids, $var_type.text);
	TinyGenesis.printVariables($id_list.ids);
/*	for (int i = 0; i < $id_list.ids.size(); i = i + 2)
	{
		typeList[i] = $var_type.text;
		typeList[i + 1] = $id_list.ids.get(i);
	}*/
}; 
var_type	  	  : (FLOAT | INT);
any_type          : (var_type | VOID); 
id_list returns [ArrayList<String> ids]          : (id id_tail) { $ids = $id_tail.ids_list; $ids.add(0, $id.text); };
id_tail returns [ArrayList<String> ids_list]          
	: ',' id recurse_tail = id_tail { $ids_list = $recurse_tail.ids_list; $ids_list.add(0, $id.text); } 
	| (/* */) { $ids_list = new ArrayList<String>(); };

//Function Paramater List 
param_decl_list   : (param_decl param_decl_tail | /* */);
param_decl        : (var_type id) { theFantasticSymbolTree.append_var_2($id.text, $var_type.text); };
param_decl_tail   : (',' param_decl param_decl_tail | /* */);

//Function Declarations 
func_declarations : (func_decl func_declarations | /* */);
func_decl         : (FUNCTION any_type id)  { theFantasticSymbolTree.enterScope($id.text); } LPAREN param_decl_list RPAREN BEGIN func_body END { theFantasticSymbolTree.exitScope(); };
func_body         : (decl stmt_list); 

//Statement List 
stmt_list         : stmt stmt_list
{
	//System.out.println("Rape: STMTLIST");
	if ($stmt.stmtTree != null)
	{//	System.out.println("From g "+$stmt.stmtTree.rightChildNode.opcode);
		theFantasticSymbolTree.addStmt($stmt.stmtTree);
	}
}
| /* */;
stmt        returns [ASTTreeNode stmtTree]     : (base_stmt 
{
	$stmtTree = $base_stmt.baseStmtTree;
	//System.out.println("Rape: STMTTREE");
}
| if_stmt | while_stmt);
base_stmt  returns [ASTTreeNode baseStmtTree]      : (assign_stmt 
{
	$baseStmtTree = $assign_stmt.equalsNode;
}
| read_stmt 
{
	$baseStmtTree = $read_stmt.readNode;
}
| write_stmt 
{
	$baseStmtTree = $write_stmt.writeNode;
}
| return_stmt);

//Basic Statements 
assign_stmt  returns [ASTTreeNode equalsNode]     : (assign_expr SEMICOLON)
{
	$equalsNode = $assign_expr.equalsNode;
};

assign_expr returns [ASTTreeNode equalsNode]       : (id SEQUAL expr)
{
	$equalsNode = new ASTTreeNode();
	ASTTreeNode idNode = new ASTTreeNode();
	idNode.updateId($id.text);
	$equalsNode.updateOpcode($SEQUAL.text);
	$equalsNode.updateLeftChild(idNode);
	$equalsNode.updateRightChild($expr.node);
};
read_stmt    returns [ASTTreeNode readNode]     : (READ LPAREN id_list RPAREN SEMICOLON)
{
	$readNode = new ASTTreeNode();
	$readNode.updateOpcode("READ");
	$readNode.updateReadList($id_list.ids);
};
write_stmt    returns [ASTTreeNode writeNode]     : (WRITE LPAREN id_list RPAREN SEMICOLON)
{
	$writeNode = new ASTTreeNode();
	$writeNode.updateOpcode("WRITE");
	$writeNode.updateWriteList($id_list.ids);
};
return_stmt       : (RETURN expr SEMICOLON);

//Expressions 
expr  returns [ASTTreeNode node]            : (expr_prefix factor)
{
	if ($expr_prefix.exprefixNode == null)
	{
		$node = $factor.fthis;
	}
	else
	{
		$expr_prefix.exprefixNode.updateRightChild($factor.fthis);
		$node = $expr_prefix.exprefixNode;
	}
};
expr_prefix    returns [ASTTreeNode exprefixNode]   : expNode = expr_prefix factor addop
{
	if ($expNode.exprefixNode == null)
	{
		$addop.addopNode.updateLeftChild($factor.fthis);
		$exprefixNode = $addop.addopNode;
	}
	else
	{
		$expNode.exprefixNode.updateRightChild($factor.fthis);
		$addop.addopNode.updateLeftChild($expNode.exprefixNode);
		$exprefixNode = $addop.addopNode;
	}
} | /* */;
factor         returns [ASTTreeNode fthis]   : (factor_prefix postfix_expr)
{
	if ($factor_prefix.theGrandPre == null)
	{
		$fthis = $postfix_expr.postbox;
	}
	else
	{
		$factor_prefix.theGrandPre.updateRightChild($postfix_expr.postbox);
		$fthis = $factor_prefix.theGrandPre;
	}
};
factor_prefix   returns [ASTTreeNode theGrandPre]  : grandFPrefix = factor_prefix postfix_expr mulop 
{
	if ($grandFPrefix.theGrandPre == null)
	{
		$mulop.mulopNode.updateLeftChild($postfix_expr.postbox);
		$theGrandPre = $mulop.mulopNode;
	}
	else
	{
		$grandFPrefix.theGrandPre.updateRightChild($postfix_expr.postbox);
		$mulop.mulopNode.updateLeftChild($grandFPrefix.theGrandPre);
		$theGrandPre = $mulop.mulopNode;
	}
}| /* */;
postfix_expr    returns [ASTTreeNode postbox]  : primary 
{
	$postbox = $primary.optimusPrime;
	//System.out.println("Rape: POSTFIX");
} | call_expr;
call_expr         : (id LPAREN expr_list RPAREN);
expr_list         : (expr expr_list_tail | /* */);
expr_list_tail    : (',' expr expr_list_tail | /* */);
primary    returns [ASTTreeNode optimusPrime]       : LPAREN expr RPAREN 
{
	$optimusPrime = $expr.node;
}
| id 
{
	ASTTreeNode idNode = new ASTTreeNode();
	idNode.updateId($id.text);
	$optimusPrime = idNode;
}
| INTLITERAL 
{
	ASTTreeNode intNode = new ASTTreeNode();
	intNode.updateValue($INTLITERAL.text);
	intNode.updateType("INT");
	$optimusPrime = intNode;
	//System.out.println("Rape: INTLITERAL");
}
| FLOATLITERAL
{
	ASTTreeNode floatNode = new ASTTreeNode();
	floatNode.updateValue($FLOATLITERAL.text);
	floatNode.updateType("FLOAT");
	$optimusPrime = floatNode;
	//System.out.println("Rape: FLOATLITERAL");
};
addop      returns [ASTTreeNode addopNode]       : (ADD | MINUS)
{
	$addopNode = new ASTTreeNode();
	$addopNode.updateOpcode($addop.text);
	//System.out.println("Rape: ADDOP");
};
mulop      returns [ASTTreeNode mulopNode]       : (MULTIPLY | DIVIDE)
{
	$mulopNode = new ASTTreeNode();
	$mulopNode.updateOpcode($mulop.text);
	//System.out.println("Rape: MULOP");
};

//Complex Statements and Condition
if_stmt           : 'IF' {theFantasticSymbolTree.enterScope(); } LPAREN cond ')' decl stmt_list {theFantasticSymbolTree.exitScope();} else_part ENDIF;
else_part         : 'ELSE' {theFantasticSymbolTree.enterScope(); } decl stmt_list {theFantasticSymbolTree.exitScope();} | (/* */);
cond              : (expr compop expr);
compop            : (GTHAN | LTHAN | EQUAL | NEQUAL | GTHANE | LTHANE);

//ECE 573 students use this version of do_while_stmt
//do_while_stmt     : (DO decl aug_stmt_list WHILE '(' cond ')' ';');
while_stmt		  : 'WHILE' {theFantasticSymbolTree.enterScope();} LPAREN cond RPAREN decl aug_stmt_list ENDWHILE { theFantasticSymbolTree.exitScope(); };

//CONTINUE and BREAK statements. ECE 573 students only
aug_stmt_list     : (aug_stmt aug_stmt_list | /* */);
aug_stmt          : (base_stmt | aug_if_stmt | while_stmt | aug_continue SEMICOLON | aug_break SEMICOLON);
aug_break         : BREAK;
aug_continue      : CONTINUE;

//Augmented IF statements for ECE 573 students 
aug_if_stmt       : (IF LPAREN cond RPAREN decl aug_stmt_list aug_else_part ENDIF);
aug_else_part     : (ELSE decl aug_stmt_list aug_else_part | /* */);


ADD: '+';
MINUS: '-';
MULTIPLY:'*';
DIVIDE:'/';
GTHAN:'<';
LTHAN:'>';
EQUAL:'=';
NEQUAL:'!=';
GTHANE:'<=';
LTHANE:'>=';
LPAREN:'(';
RPAREN:')';
COMMA:',';
SEMICOLON:';';
SEQUAL:':=';
IF:'IF';
ENDIF:'ENDIF';
ELSE:'ELSE';
WHILE:'WHILE';
ENDWHILE: 'ENDWHILE';
CONTINUE:'CONTINUE';
BREAK:'BREAK';
READ:'READ';
WRITE:'WRITE';
RETURN:'RETURN';
PROGRAM:'PROGRAM';
BEGIN:'BEGIN';
END:'END';
FUNCTION:'FUNCTION';
FLOAT:'FLOAT';
INT:'INT';
STRING:'STRING';
VOID:'VOID';

INTLITERAL: [0-9]+;

FLOATLITERAL: ([0-9]+ '.' [0-9]+);

STRINGLITERAL: ('"' ~('\r' | '\n' | '"')* '"');

IDENTIFIER: [a-zA-Z0-9]+ ;

COMMENT:( '--' ~[\r\n]* '\r'? '\n') -> channel(HIDDEN);

WS: (' ' | '\t' | '\n' | '\r' | '\f' | '\s' |'\t\n' )+  -> channel(HIDDEN);