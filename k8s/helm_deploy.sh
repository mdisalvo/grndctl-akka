#!/usr/bin/env bash

helm install ./grndctl \

sleep 10

kubectl port-forward $(kubectl get deployments -o=name | grep 'grndctl') 8080:8080