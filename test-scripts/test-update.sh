#!/bin/sh

curl --header "content-type: text/xml" -d @request-update.xml http://localhost:8080/ws | xmllint --format -

