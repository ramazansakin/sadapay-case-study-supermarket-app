#!/usr/bin/env bash

# Add script to run program here.
# Complete bin/setup so that after it is
# run, bin/run can be used to launch
# it.

# This variable contains absolute path of this `run` script
WORKING_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd )"/../solution
cd $WORKING_DIR

# This assumes the code to be in the folder <project_root>/solution, which is
# used as working directory

sh ./../bin/setup.sh

export JAVA_PROGRAM_ARGS=`echo "$@"`
# also we can run the app via mvn command
# mvn -q exec:java -Dexec.mainClass="com.test.Hello" -Dexec.args="$JAVA_PROGRAM_ARGS"
java -cp ./target/Sadapay-Supermarket-App-1.0-SNAPSHOT.jar com.rsakin.sadapay.casestudy.SuperMarketApp $JAVA_PROGRAM_ARGS

sh ./../bin/cleanup.sh
