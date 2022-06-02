#!/bin/sh

cd pet-soap-client
docker build --network=host -t pet-soap-client:latest .
docker run --network=host pet-soap-client


