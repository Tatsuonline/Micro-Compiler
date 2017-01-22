import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Parser;

public class EscapeFrom468 extends DefaultErrorStrategy {

@Override
public void reportError(Parser recognizer, RecognitionException e) {
throw new ParseCancellationException(e);
}

@Override
public Token recoverInline(Parser recognizer)
throws RecognitionException
{
InputMismatchException e = new InputMismatchException(recognizer);
throw new ParseCancellationException(e);
}

@Override
public void sync(Parser recognizer) { }
}
