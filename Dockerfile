FROM maven

ENV JAVA_HOME "/usr/lib/jvm/java-8-oracle"

# Install Curl
RUN apt-get update -y && apt-get install -y curl

# Copy the service itself
COPY target/web-markdown-parser-1.0.war /usr/share/myservice/web-markdown-parser-1.0.war

# Run the parser app
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/myservice/web-markdown-parser-1.0.war"]