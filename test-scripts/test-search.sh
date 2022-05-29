#!/bin/sh

curl --header "content-type: text/xml" -d @request-search.xml http://localhost:8080/ws | xmllint --format -

