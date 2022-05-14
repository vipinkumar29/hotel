#!/bin/bash
unset HTTP_PROXY HTTPS_PROXY http_proxy https_proxy
for (( c=1; c<=5; c++ ))
do
   curl -X GET 'http://localhost:8085/hotels' | jq .
done
