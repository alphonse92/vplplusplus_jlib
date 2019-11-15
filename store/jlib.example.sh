export ENV="production"
export MOODLE_USER_ID=5
export API_URL=http://localhost:1337/api/v1/project/test/case/summary/
export API_TOKEN=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZGNjOTAzNWVjYWE5ODAwNGYyZjY1ZDUiLCJpZCI6LTE1NzM2ODczNDkyNjQsInVzZXJuYW1lIjoiYWRzYXNkYXNkIiwidHlwZSI6ImFwaV9jbGllbnQiLCJpYXQiOjE1NzM2ODczNDl9.UI3yTcaExB8Q_x2hBPm_2tx9OZqMFhPI3CKJUaj6rlY

javac -cp "VPLPlusPlus-jlib.jar" ./*.java
java -jar VPLPlusPlus-jlib.jar

