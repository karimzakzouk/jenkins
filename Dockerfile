FROM ubuntu:alpine

RUN apt-get update && apt-get install -y curl

CMD ["curl", "google.com"]