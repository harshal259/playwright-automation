FROM ubuntu:latest

RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y nano git maven openjdk-21-jdk && \
    apt-get install -y libgstreamer1.0-0 libatomic1 libxslt1.1 libwoff1 libvpx9 libevent-2.1-7t64 libopus0 libgstreamer-plugins-base1.0-0 libgstreamer-gl1.0-0 libgstreamer-plugins-bad1.0-0 libwebpdemux2 libharfbuzz-icu0 libenchant-2-2 libsecret-1-0 libhyphen0 libmanette-0.2-0 libflite1 libgles2 gstreamer1.0-libav

RUN git clone https://github.com/harshal259/playwright-automation.git
WORKDIR playwright-automation
RUN mvn clean test
