@echo off
REM ----------------------------------------------------------------------------
REM This batch file sets up the environment and runs the project with Java 23.
REM ----------------------------------------------------------------------------

REM Go to the project directory
cd /d %~dp0

REM Run Maven using the wrapper (make sure it uses Java 23)
echo Running Maven build...
call mvnw clean install
if errorlevel 1 (
    echo Maven build failed. Exiting...
    exit /b 1
)

REM Run the Java application using the compiled .jar file
echo Running Java application...
start "" java -jar target\demo-0.0.1-SNAPSHOT.jar

REM Wait for the server to start
echo Waiting for the server to start...
:check_server
timeout /t 2 /nobreak >nul
curl -I http://localhost:8080/ >nul 2>&1
if errorlevel 1 (
    echo Server not ready, retrying...
    goto check_server
)

echo Opening Microsoft Edge...
start chrome http://localhost:8080/
if errorlevel 1 (
    echo Failed to open Microsoft Edge. Trying Chrome instead...
    start msedge http://localhost:8080/
    if errorlevel 1 (
        echo Failed to open Chrome. Please open the browser manually.
    )
)

REM Optional: Wait for user input before closing the window
pause