#!/usr/bin/env bash

kubectl apply -f grndctl.json --record

sleep 20

kubectl port-forward deployment/grndctl 8080:8080