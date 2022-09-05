FROM openjdk:8
ENV fileName=${fileName}

RUN mkdir -p /home/code
WORKDIR /home/code

RUN echo ${fileName}
CMD javac ${fileName}.java &&  java ${fileName}