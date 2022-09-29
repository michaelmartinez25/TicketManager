export MISSING=false
while read p; do
    arrIN=(${p//,/ })
    sourceFile=${arrIN[0]}
    testFile=${arrIN[1]}
    echo $sourceFile
    echo $testFile
    found=$(find . -name "$sourceFile" -type f | wc -l)
    if [[ $found -eq 0 ]]; then
        find . -name "$testFile" -type f -delete
        export MISSING=true
    fi
done <.github/expected_files.txt

echo $MISSING
echo "MISSING_FILES=$(echo $MISSING)" >> $GITHUB_ENV