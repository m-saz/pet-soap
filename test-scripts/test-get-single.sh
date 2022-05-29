#!/bin/sh

curl --header "content-type: text/xml" -d @request-single.xml http://localhost:8080/ws | xmllint --format -

