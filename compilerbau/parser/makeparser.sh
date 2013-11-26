#!/bin/sh

DEST_DIR=../src/de/dhbw/blaaah/parser

cup -destdir $DEST_DIR minisql.cup

jflex -d $DEST_DIR minisql.flex