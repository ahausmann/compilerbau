
import java_cup.runtime.*;

%%

%class MiniSqlLexer
%unicode
%cup
%column

%{

    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

Whitespace = [ \t\f]

Name = [:jletter:] [:jletterdigit:]*

Integer = 0 | [1-9][0-9]* | [+-][1-9][0-9]*

%%

<YYINITIAL> {
    "SELECT"    { return symbol(sym.SELECT); }
    "FROM"      { return symbol(sym.FROM); }
    "CREATE TABLE" { return symbol(sym.CREATE_TABLE); }
    "INSERT INTO" { return symbol(sym.INSERT_INTO); }
    "UPDATE"    { return symbol(sym.UPDATE); }

    {Name}      { return symbol(sym.NAME); }
    {Whitespace} { /* ignored */ }
}