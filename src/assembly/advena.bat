@echo off
setlocal ENABLEDELAYEDEXPANSION
cd %~dp0

if defined JAVA_HOME (set JAVA="%JAVA_HOME%\bin\java.exe") else (SET JAVA=java.exe)
set JVM_OPTIONS=-XX:+UseG1GC -XX:MaxGCPauseMillis=10
set CLASSPATH=lib/*
set MAIN_CLASS=advena.DesktopLauncherKt
%JAVA% %JVM_OPTIONS% -cp %CLASSPATH% %MAIN_CLASS%

endlocal
