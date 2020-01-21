#!/bin/sh
set -e
export ENV="production"
export MOODLE_USER_ID=5
export API_URL=http://your-api-url/api/v1/project/test/case/summary/
export API_TOKEN=jlib client api token
export VPL_PLUS_PLUS_PROJECT_ID="project id"
rm -rf ./*.class
javac -cp "VPLPlusPlus-jlib.jar" ./*.java || export COMPILATION_ERROR=true
java -jar VPLPlusPlus-jlib.jar
