#!/bin/sh

DEST_DIR=../src/de/dhbw/blaaah/parser
PARSER_NAME=MiniSqlParser

cup -destdir $DEST_DIR -parser $PARSER_NAME minisql.cup

jflex -d $DEST_DIR minisql.flex