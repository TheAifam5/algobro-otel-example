package de.theaifam5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.context.Scope;

public class App {

  public static void main(String[] args) {
    final OpenTelemetry otel = GlobalOpenTelemetry.get();
    final Logger logger = LogManager.getLogger("HelloWorld");

    final Span span = otel.getTracer("HelloWorld").spanBuilder("HelloWorld").startSpan();

    while (true) {
      try (final Scope scope = span.makeCurrent()) {
        span.setAttribute("myAttribute", "myValue");
        span.addEvent("Starting my span");
        logger.error("Hello, World");
        try {
          Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        span.addEvent("Ending my span");
      } finally {
        span.end();
      }
    }
  }
}
