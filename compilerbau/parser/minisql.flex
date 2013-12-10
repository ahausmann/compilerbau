
package de.dhbw.blaaah.parser;

import java_cup.runtime.*;

%%

%class MiniSqlScanner
%unicode
%cup
%line
%column

%{

    public MiniSqlScanner(java.io.InputStream r, SymbolFactory sf) {
        this(r);
        this.sf = sf;
    }

    private SymbolFactory sf;
%}

%%

[Ss][Ee][Ll][Ee][Cc][Tt]    { return sf.newSymbol("SELECT", sym.SELECT); }
[Ff][Rr][Oo][Mm]            { return sf.newSymbol("FROM", sym.FROM); }
[Cc][Rr][Ee][Aa][Tt][Ee]    { return sf.newSymbol("CREATE", sym.CREATE); }
[Tt][Aa][Bb][Ll][Ee]        { return sf.newSymbol("TABLE", sym.TABLE); }
[Ii][Nn][Ss][Ee][Rr][Tt]    { return sf.newSymbol("INSERT", sym.INSERT); }
[Ii][Nn][Tt][Oo]            { return sf.newSymbol("INTO", sym.INTO); }
[Uu][Pp][Dd][Aa][Tt][Ee]    { return sf.newSymbol("UPDATE", sym.UPDATE); }
[Vv][Aa][Ll][Uu][Ee][Ss]    { return sf.newSymbol("VALUES", sym.VALUES); }
[Ss][Ee][Tt]                { return sf.newSymbol("SET", sym.SET); }
[Dd][Ee][Ll][Ee][Tt][Ee]    { return sf.newSymbol("DELETE", sym.DELETE); }
[Ww][Hh][Ee][Rr][Ee]		{ return sf.newSymbol("WHERE", sym.WHERE); }
[Dd][Rr][Oo][Pp]            { return sf.newSymbol("DROP", sym.DROP); }
[Tt][Ee][Xx][Tt]			{ return sf.newSymbol("TEXT", sym.TEXT); }
[Nn][Uu][Mm][Bb][Ee][Rr]	{ return sf.newSymbol("NUMBER", sym.NUMBER); }
";"                         { return sf.newSymbol(";", sym.SEMICOLON); }
"("                         { return sf.newSymbol("(", sym.LEFT_PARENTHESIS); }
")"                         { return sf.newSymbol(")", sym.RIGHT_PARENTHESIS); }
"*"                         { return sf.newSymbol("*", sym.STAR); }
","                         { return sf.newSymbol(",", sym.COMMA); }
"="                         { return sf.newSymbol("=", sym.EQUAL); }
[a-zA-Z][a-zA-Z0-9_]*       { return sf.newSymbol("Name", sym.NAME, yytext()); }
\"[^\"]*\"                  { return sf.newSymbol("Value", sym.VALUE, yytext()); }

[ \t\r\n\f] { /* ignore */ }

. { System.err.println("Illegal character: " + yytext()); }