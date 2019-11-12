export ENV="productioden"
export MOODLE_USER_ID=3
export API_URL=http://localhost:1337/api/v1/project/test/case/summary/
export API_TOKEN=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZGNhZTg4ZjNiZDhlMTAwMjVjNzdmZmQiLCJpZCI6LTE1NzM1Nzg4OTUyMTgsInVzZXJuYW1lIjoiYXNkYXNkIiwidHlwZSI6ImFwaV9jbGllbnQiLCJpYXQiOjE1NzM1Nzg4OTV9.8OLpDntoX-ZDUGIrKjiQiU8g7TKEv86pss4kF0DePEU

javac -cp "VPLPlusPlus-jlib.jar" ./*.java
java -jar VPLPlusPlus-jlib.jar

