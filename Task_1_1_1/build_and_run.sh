#!/usr/bin/env bash
set -e

SRC_DIR="src/main/java"
BUILD_DIR="my_build"
CLASSES_DIR="$BUILD_DIR/classes"
DOCS_DIR="$BUILD_DIR/docs"
JAR_PATH="$BUILD_DIR/app.jar"
MAIN_CLASS="Main"

rm -rf "$BUILD_DIR"
mkdir -p "$CLASSES_DIR" "$DOCS_DIR"
SOURCES=$(find "$SRC_DIR" -name "*.java")
javac -encoding UTF-8 -d "$CLASSES_DIR" $SOURCES
javadoc -encoding UTF-8 -docencoding UTF-8 -charset UTF-8 -d "$DOCS_DIR" $SOURCES -quiet
jar --create --file "$JAR_PATH" --main-class "$MAIN_CLASS" -C "$CLASSES_DIR" .
java -jar "$JAR_PATH"
