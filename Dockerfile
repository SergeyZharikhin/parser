FROM java:8

# Copy the service itself
COPY target/web-markdown-parser-1.0.war /usr/share/myservice/web-markdown-parser-1.0.war

EXPOSE "8080"

# Run the parser app
ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/myservice/web-markdown-parser-1.0.war"]
