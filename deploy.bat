@echo off
setlocal

REM Compila
call mvn -q clean package -DskipTests
if errorlevel 1 exit /b 1

set "WAR=target\proyect.war"
set "DEST=C:\ProgramData\Tomcat\webapps"

if not exist "%WAR%" (
  echo No se encontro el archivo %WAR%
  exit /b 1
)

copy /Y "%WAR%" "%DEST%\" >nul
if errorlevel 1 exit /b 1

echo WAR copiado a Tomcat.
endlocal

