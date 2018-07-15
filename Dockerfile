FROM java:8
VOLUME /tmp
ADD target/hatespeech-detector-api.jar app.jar
ADD docker-wrapper.sh wrapper.sh
RUN bash -c 'chmod +x /wrapper.sh'
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["/bin/bash", "/wrapper.sh"]