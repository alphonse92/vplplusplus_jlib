#Use example for vpljlib 
# You can set the -f parameter to set the folder where are the classes
# to get help java -jar VPLPlusPlus-jlib.jar --help

# Atention: you need send the moodle user id by command line (-m flag)
# Or environment variable way

# Get all output
export MOODLE_USER_ID=1
java -jar VPLPlusPlus-jlib.jar   -e "development"   -f "./"   -u "http://localhost:1337/api/v1/project/test/case/summary/"    -t "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZGM3M2Q4NmUxOTY2NGFkY2U2ZmI1ZTgiLCJpZCI6LTE1NzMzMzg1MDIzNTEsInVzZXJuYW1lIjoiYXBwIiwidHlwZSI6ImFwaV9jbGllbnQiLCJpYXQiOjE1NzMzMzg1MDJ9.Liim08kZkPPlT-v5yKW9-ywvWpCSmyBMns7i8vFbIIg"

# Get production
# java -jar VPLPlusPlus-jlib.jar   -e "production"   -f "./"   -u "http://localhost:1337/api/v1/project/test/case/summary/"    -t "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZGM3M2Q4NmUxOTY2NGFkY2U2ZmI1ZTgiLCJpZCI6LTE1NzMzMzg1MDIzNTEsInVzZXJuYW1lIjoiYXBwIiwidHlwZSI6ImFwaV9jbGllbnQiLCJpYXQiOjE1NzMzMzg1MDJ9.Liim08kZkPPlT-v5yKW9-ywvWpCSmyBMns7i8vFbIIg"  -m 1 
