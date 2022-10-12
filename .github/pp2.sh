for file in TS_CommandTest TS_TicketTest TS_TicketNewStateTest TS_TicketWorkingStateTest TS_TicketFeedbackStateTest TS_TicketResolvedStateTest TS_TicketClosedStateTest TS_TicketCanceledStateTest
do
    echo "Checking results for ${file}..."
    found=$(find . -name "${file}.class" -type f | wc -l)
    if [[ $found -eq 0 ]]; then
        msg="Did not find the test class ${file}.  Your project is probably not complete"
        echo $msg
        exit 127
    fi
    contents=$(find . -name "*${file}.txt" -type f -exec cat {} +)
    f=$(echo $contents | grep "Failures: 0" | wc -l)
    e=$(echo $contents | grep "Errors: 0" | wc -l)
    if [ $f -eq 0 ] || [ $e -eq 0 ]; then
        echo "Found failing tests for ${file}.  Your project doesn't satisfy the Process Points 2 deadline"
        exit 3
    fi
    echo "Success!"
    
done
