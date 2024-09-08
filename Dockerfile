FROM mcr.microsoft.com/playwright:v1.38.0-jammy

RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y nano git maven openjdk-21-jdk

RUN git clone https://github.com/harshal259/playwright-automation.git
WORKDIR playwright-automation
