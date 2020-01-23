#!/bin/sh
set -e
export ENV="production"
export MOODLE_USER_ID=7
export API_URL=http://35.247.248.247:1337/api/v1/project/test/case/summary/
export API_TOKEN=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZTI0ZGM1YTYxNzBhMjAwMjliNzM5NzgiLCJpZCI6LTE1Nzk0NzQwMTAzMTgsInVzZXJuYW1lIjoic2FuZGJveHJ1bm5lciIsInR5cGUiOiJhcGlfY2xpZW50IiwiaWF0IjoxNTc5NDc0MDEwfQ.T8NzaNKkOm1CV1IiK6RGEx5GKuBMCKNhFveRt2CL-E0
export VPL_PLUS_PLUS_PROJECT_ID=5e29ff3eaf252c001d1ec77e
rm -rf ./*.class
javac -cp "VPLPlusPlus-jlib.jar" ./*.java || export COMPILATION_ERROR=true
java -jar VPLPlusPlus-jlib.jar
