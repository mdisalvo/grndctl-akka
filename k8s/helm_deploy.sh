#!/usr/bin/env bash

kubectl create namespace grndctl

helm install --name grndctl ./grndctl \

sleep 10

kubectl port-forward -n grndctl $(kubectl get deployments -n grndctl -o=name | grep 'grndctl') 8080:8080
