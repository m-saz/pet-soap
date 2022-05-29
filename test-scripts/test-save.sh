#!/bin/sh

curl --header "content-type: text/xml" -d @request-save.xml http://localhost:8080/ws | xmllint --format -

