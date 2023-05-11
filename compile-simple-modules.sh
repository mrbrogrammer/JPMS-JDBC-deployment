#!/usr/bin/env bash
javac -d target --module-source-path src $(find src -name "*.java")
