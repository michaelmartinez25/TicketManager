# testingChecker.sh

set +e


pwd

# Check if there are PMD alerts related to JUnit
# If so, FAIL
numJUnitTests=$(grep -R "JUnitTests" ./TicketManager/target/pmd.xml | wc -l)
numJUnit4Tests=$(grep -R "JUnit4Test" ./TicketManager/target/pmd.xml | wc -l)
numTestClass=$(grep -R "TestClass" ./TicketManager/target/pmd.xml | wc -l)
numAssertion=$(grep -R "Assertion" ./TicketManager/target/pmd.xml | wc -l)
numUseAssert=$(grep -R "UseAssert" ./TicketManager/target/pmd.xml | wc -l)
totalAlerts=$((numJUnitTests + numJUnit4Tests + numTestClass + numAssertion + numUseAssert))
echo "Project contains $totalAlerts JUnit-related PMD alerts"
if [ "$totalAlerts" -gt "0" ]
    then
        echo "PMD_ALERTS=true" >> $GITHUB_ENV
        exit 1
fi


# Assertion count
# Does not affect build, only FYI
numAsserts=$(grep -R "assert" ./TicketManager/src/test/ | wc -l)
echo "Project contains $numAsserts total assert statements"



# Now check coverage results
# Checks if any method is not covered
# Checks if any non-UI class does not have the specified coverage threshold

INPUT="./TicketManager/target/site/jacoco-ut/jacoco.csv"
HEADER="CLASS"
ARG=80
THRESHOLD=$(($ARG * 10))
METHODCOUNTER=0
CLASSCOUNTER=0

# Print header for output table
printf "\n\n\t%-40s %-20s %-20s\n" "CLASS" "METHOD COVERAGE" "LINE COVERAGE"

OLDIFS=$IFS

IFS=,
[ ! -f $INPUT ] && { echo "$INPUT file not found"; exit 99; }
while read group package class insMissed insCovered branchMissed branchCovered lineMissed lineCovered compMissed compCovered methodMissed methodCovered
do

        if [[ "$class" != "$HEADER" && "$package" != *"ui" && "$class" != *"Test" && "$class" != *".new Observer() {...}" ]]
            then

                # Determine method coverage
				#echo "Method missed:"
				#echo $methodMissed
				
				#echo "Method covered:"
				#echo $methodCovered
				
                methodTotal=$((methodMissed+methodCovered))
				
				#echo "Method total:"
				#echo $methodTotal
                if [ "$methodTotal" -gt "0" ]
                    then
                        methodPercent=$(( 1000 * methodCovered / methodTotal ))
                else
                        methodPercent=0
                fi
                if [ "$methodPercent" -lt "1000" ]
                    then
                        METHODCOUNTER=$(( METHODCOUNTER + methodMissed))
                fi


                # Determine line coverage
                lineTotal=$((lineMissed+lineCovered))
                if [ "$lineTotal" -gt "0" ]
                   then
                        linePercent=$(( 1000 * lineCovered / lineTotal ))
                else
                        linePercent=0
                fi

                if [ "$linePercent" -lt "$THRESHOLD" ]
                   then
                        CLASSCOUNTER=$(($CLASSCOUNTER + 1))
                fi

                methodPercentClean=$(( $methodPercent / 10 ))
                linePercentClean=$(( $linePercent / 10 ))
                printf "\t%-40s %-20d %-20d\n" "$class" "$methodPercentClean" "$linePercentClean"
        fi
done < $INPUT
IFS=$OLDIFS

#echo "TOTAL of $METHODCOUNTER methods not covered"
echo "TOTAL of $CLASSCOUNTER classes not meeting the $ARG% line coverage threshold"
#if [ "$METHODCOUNTER" -gt "0" ]
#    then
#       exit 1
#fi

if [ "$CLASSCOUNTER" -gt "0" ]
    then
        exit 1
fi
