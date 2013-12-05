#!/bin/sh

JFLEX_JAR=../bin/JFlex.jar
CUP_JAR=../bin/java-cup-11a.jar
JAVA=java

DEST_DIR=../src/de/dhbw/blaaah/parser
PARSER_NAME=MiniSqlParser

# Java-Klassen erzeugen

rm $DEST_DIR/*

$JAVA -jar $CUP_JAR -destdir $DEST_DIR -parser $PARSER_NAME minisql.cup

$JAVA -jar $JFLEX_JAR -d $DEST_DIR minisql.flex
