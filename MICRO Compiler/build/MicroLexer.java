// Generated from Micro.g4 by ANTLR 4.1

	import java.lang.*;
	import java.util.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MicroLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ADD=1, MINUS=2, MULTIPLY=3, DIVIDE=4, GTHAN=5, LTHAN=6, EQUAL=7, NEQUAL=8, 
		GTHANE=9, LTHANE=10, LPAREN=11, RPAREN=12, COMMA=13, SEMICOLON=14, SEQUAL=15, 
		IF=16, ENDIF=17, ELSE=18, WHILE=19, ENDWHILE=20, CONTINUE=21, BREAK=22, 
		READ=23, WRITE=24, RETURN=25, PROGRAM=26, BEGIN=27, END=28, FUNCTION=29, 
		FLOAT=30, INT=31, STRING=32, VOID=33, INTLITERAL=34, FLOATLITERAL=35, 
		STRINGLITERAL=36, IDENTIFIER=37, COMMENT=38, WS=39;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'+'", "'-'", "'*'", "'/'", "'<'", "'>'", "'='", "'!='", "'<='", "'>='", 
		"'('", "')'", "','", "';'", "':='", "'IF'", "'ENDIF'", "'ELSE'", "'WHILE'", 
		"'ENDWHILE'", "'CONTINUE'", "'BREAK'", "'READ'", "'WRITE'", "'RETURN'", 
		"'PROGRAM'", "'BEGIN'", "'END'", "'FUNCTION'", "'FLOAT'", "'INT'", "'STRING'", 
		"'VOID'", "INTLITERAL", "FLOATLITERAL", "STRINGLITERAL", "IDENTIFIER", 
		"COMMENT", "WS"
	};
	public static final String[] ruleNames = {
		"ADD", "MINUS", "MULTIPLY", "DIVIDE", "GTHAN", "LTHAN", "EQUAL", "NEQUAL", 
		"GTHANE", "LTHANE", "LPAREN", "RPAREN", "COMMA", "SEMICOLON", "SEQUAL", 
		"IF", "ENDIF", "ELSE", "WHILE", "ENDWHILE", "CONTINUE", "BREAK", "READ", 
		"WRITE", "RETURN", "PROGRAM", "BEGIN", "END", "FUNCTION", "FLOAT", "INT", 
		"STRING", "VOID", "INTLITERAL", "FLOATLITERAL", "STRINGLITERAL", "IDENTIFIER", 
		"COMMENT", "WS"
	};


	public TreeOfSymbolsForTable theFantasticSymbolTree = new TreeOfSymbolsForTable();
	public List <ASTTreeNode> listOfTrees = new ArrayList <ASTTreeNode> ();


	public MicroLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Micro.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 37: COMMENT_action((RuleContext)_localctx, actionIndex); break;

		case 38: WS_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: _channel = HIDDEN;  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: _channel = HIDDEN;  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2)\u011b\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\3\2\3\2\3\3\3\3\3\4"+
		"\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3!\3!\3!\3!\3!\3"+
		"!\3!\3\"\3\"\3\"\3\"\3\"\3#\6#\u00e4\n#\r#\16#\u00e5\3$\6$\u00e9\n$\r"+
		"$\16$\u00ea\3$\3$\6$\u00ef\n$\r$\16$\u00f0\3%\3%\7%\u00f5\n%\f%\16%\u00f8"+
		"\13%\3%\3%\3&\6&\u00fd\n&\r&\16&\u00fe\3\'\3\'\3\'\3\'\7\'\u0105\n\'\f"+
		"\'\16\'\u0108\13\'\3\'\5\'\u010b\n\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\6"+
		"(\u0116\n(\r(\16(\u0117\3(\3(\2)\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1"+
		"\17\t\1\21\n\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\1!"+
		"\22\1#\23\1%\24\1\'\25\1)\26\1+\27\1-\30\1/\31\1\61\32\1\63\33\1\65\34"+
		"\1\67\35\19\36\1;\37\1= \1?!\1A\"\1C#\1E$\1G%\1I&\1K\'\1M(\2O)\3\3\2\7"+
		"\3\2\62;\5\2\f\f\17\17$$\5\2\62;C\\c|\4\2\f\f\17\17\5\2\13\f\16\17\"\""+
		"\u0124\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2"+
		"/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2"+
		"\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2"+
		"G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\3Q\3\2\2\2\5S\3"+
		"\2\2\2\7U\3\2\2\2\tW\3\2\2\2\13Y\3\2\2\2\r[\3\2\2\2\17]\3\2\2\2\21_\3"+
		"\2\2\2\23b\3\2\2\2\25e\3\2\2\2\27h\3\2\2\2\31j\3\2\2\2\33l\3\2\2\2\35"+
		"n\3\2\2\2\37p\3\2\2\2!s\3\2\2\2#v\3\2\2\2%|\3\2\2\2\'\u0081\3\2\2\2)\u0087"+
		"\3\2\2\2+\u0090\3\2\2\2-\u0099\3\2\2\2/\u009f\3\2\2\2\61\u00a4\3\2\2\2"+
		"\63\u00aa\3\2\2\2\65\u00b1\3\2\2\2\67\u00b9\3\2\2\29\u00bf\3\2\2\2;\u00c3"+
		"\3\2\2\2=\u00cc\3\2\2\2?\u00d2\3\2\2\2A\u00d6\3\2\2\2C\u00dd\3\2\2\2E"+
		"\u00e3\3\2\2\2G\u00e8\3\2\2\2I\u00f2\3\2\2\2K\u00fc\3\2\2\2M\u0100\3\2"+
		"\2\2O\u0115\3\2\2\2QR\7-\2\2R\4\3\2\2\2ST\7/\2\2T\6\3\2\2\2UV\7,\2\2V"+
		"\b\3\2\2\2WX\7\61\2\2X\n\3\2\2\2YZ\7>\2\2Z\f\3\2\2\2[\\\7@\2\2\\\16\3"+
		"\2\2\2]^\7?\2\2^\20\3\2\2\2_`\7#\2\2`a\7?\2\2a\22\3\2\2\2bc\7>\2\2cd\7"+
		"?\2\2d\24\3\2\2\2ef\7@\2\2fg\7?\2\2g\26\3\2\2\2hi\7*\2\2i\30\3\2\2\2j"+
		"k\7+\2\2k\32\3\2\2\2lm\7.\2\2m\34\3\2\2\2no\7=\2\2o\36\3\2\2\2pq\7<\2"+
		"\2qr\7?\2\2r \3\2\2\2st\7K\2\2tu\7H\2\2u\"\3\2\2\2vw\7G\2\2wx\7P\2\2x"+
		"y\7F\2\2yz\7K\2\2z{\7H\2\2{$\3\2\2\2|}\7G\2\2}~\7N\2\2~\177\7U\2\2\177"+
		"\u0080\7G\2\2\u0080&\3\2\2\2\u0081\u0082\7Y\2\2\u0082\u0083\7J\2\2\u0083"+
		"\u0084\7K\2\2\u0084\u0085\7N\2\2\u0085\u0086\7G\2\2\u0086(\3\2\2\2\u0087"+
		"\u0088\7G\2\2\u0088\u0089\7P\2\2\u0089\u008a\7F\2\2\u008a\u008b\7Y\2\2"+
		"\u008b\u008c\7J\2\2\u008c\u008d\7K\2\2\u008d\u008e\7N\2\2\u008e\u008f"+
		"\7G\2\2\u008f*\3\2\2\2\u0090\u0091\7E\2\2\u0091\u0092\7Q\2\2\u0092\u0093"+
		"\7P\2\2\u0093\u0094\7V\2\2\u0094\u0095\7K\2\2\u0095\u0096\7P\2\2\u0096"+
		"\u0097\7W\2\2\u0097\u0098\7G\2\2\u0098,\3\2\2\2\u0099\u009a\7D\2\2\u009a"+
		"\u009b\7T\2\2\u009b\u009c\7G\2\2\u009c\u009d\7C\2\2\u009d\u009e\7M\2\2"+
		"\u009e.\3\2\2\2\u009f\u00a0\7T\2\2\u00a0\u00a1\7G\2\2\u00a1\u00a2\7C\2"+
		"\2\u00a2\u00a3\7F\2\2\u00a3\60\3\2\2\2\u00a4\u00a5\7Y\2\2\u00a5\u00a6"+
		"\7T\2\2\u00a6\u00a7\7K\2\2\u00a7\u00a8\7V\2\2\u00a8\u00a9\7G\2\2\u00a9"+
		"\62\3\2\2\2\u00aa\u00ab\7T\2\2\u00ab\u00ac\7G\2\2\u00ac\u00ad\7V\2\2\u00ad"+
		"\u00ae\7W\2\2\u00ae\u00af\7T\2\2\u00af\u00b0\7P\2\2\u00b0\64\3\2\2\2\u00b1"+
		"\u00b2\7R\2\2\u00b2\u00b3\7T\2\2\u00b3\u00b4\7Q\2\2\u00b4\u00b5\7I\2\2"+
		"\u00b5\u00b6\7T\2\2\u00b6\u00b7\7C\2\2\u00b7\u00b8\7O\2\2\u00b8\66\3\2"+
		"\2\2\u00b9\u00ba\7D\2\2\u00ba\u00bb\7G\2\2\u00bb\u00bc\7I\2\2\u00bc\u00bd"+
		"\7K\2\2\u00bd\u00be\7P\2\2\u00be8\3\2\2\2\u00bf\u00c0\7G\2\2\u00c0\u00c1"+
		"\7P\2\2\u00c1\u00c2\7F\2\2\u00c2:\3\2\2\2\u00c3\u00c4\7H\2\2\u00c4\u00c5"+
		"\7W\2\2\u00c5\u00c6\7P\2\2\u00c6\u00c7\7E\2\2\u00c7\u00c8\7V\2\2\u00c8"+
		"\u00c9\7K\2\2\u00c9\u00ca\7Q\2\2\u00ca\u00cb\7P\2\2\u00cb<\3\2\2\2\u00cc"+
		"\u00cd\7H\2\2\u00cd\u00ce\7N\2\2\u00ce\u00cf\7Q\2\2\u00cf\u00d0\7C\2\2"+
		"\u00d0\u00d1\7V\2\2\u00d1>\3\2\2\2\u00d2\u00d3\7K\2\2\u00d3\u00d4\7P\2"+
		"\2\u00d4\u00d5\7V\2\2\u00d5@\3\2\2\2\u00d6\u00d7\7U\2\2\u00d7\u00d8\7"+
		"V\2\2\u00d8\u00d9\7T\2\2\u00d9\u00da\7K\2\2\u00da\u00db\7P\2\2\u00db\u00dc"+
		"\7I\2\2\u00dcB\3\2\2\2\u00dd\u00de\7X\2\2\u00de\u00df\7Q\2\2\u00df\u00e0"+
		"\7K\2\2\u00e0\u00e1\7F\2\2\u00e1D\3\2\2\2\u00e2\u00e4\t\2\2\2\u00e3\u00e2"+
		"\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6"+
		"F\3\2\2\2\u00e7\u00e9\t\2\2\2\u00e8\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2"+
		"\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ee"+
		"\7\60\2\2\u00ed\u00ef\t\2\2\2\u00ee\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2"+
		"\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1H\3\2\2\2\u00f2\u00f6\7"+
		"$\2\2\u00f3\u00f5\n\3\2\2\u00f4\u00f3\3\2\2\2\u00f5\u00f8\3\2\2\2\u00f6"+
		"\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f9\3\2\2\2\u00f8\u00f6\3\2"+
		"\2\2\u00f9\u00fa\7$\2\2\u00faJ\3\2\2\2\u00fb\u00fd\t\4\2\2\u00fc\u00fb"+
		"\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff"+
		"L\3\2\2\2\u0100\u0101\7/\2\2\u0101\u0102\7/\2\2\u0102\u0106\3\2\2\2\u0103"+
		"\u0105\n\5\2\2\u0104\u0103\3\2\2\2\u0105\u0108\3\2\2\2\u0106\u0104\3\2"+
		"\2\2\u0106\u0107\3\2\2\2\u0107\u010a\3\2\2\2\u0108\u0106\3\2\2\2\u0109"+
		"\u010b\7\17\2\2\u010a\u0109\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010c\3"+
		"\2\2\2\u010c\u010d\7\f\2\2\u010d\u010e\3\2\2\2\u010e\u010f\b\'\2\2\u010f"+
		"N\3\2\2\2\u0110\u0116\t\6\2\2\u0111\u0112\7^\2\2\u0112\u0116\7u\2\2\u0113"+
		"\u0114\7\13\2\2\u0114\u0116\7\f\2\2\u0115\u0110\3\2\2\2\u0115\u0111\3"+
		"\2\2\2\u0115\u0113\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u0115\3\2\2\2\u0117"+
		"\u0118\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011a\b(\3\2\u011aP\3\2\2\2\f"+
		"\2\u00e5\u00ea\u00f0\u00f6\u00fe\u0106\u010a\u0115\u0117";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}