FROM maven

ENV JAVA_HOME "/usr/lib/jvm/java-8-oracle"

# Install Curl
RUN apt-get update -y && apt-get install -y curl

# Copy Maven dependencies (not shaded into the artifact; Docker-cached)
#COPY target/lib           /usr/share/myservice/lib
# Copy the service itself
COPY target/web-markdown-parser-1.0.jar /usr/share/myservice/web-markdown-parser-1.0.jar
# Run the migrator app

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/myservice/web-markdown-parser-1.0.jar"]

