package streams

import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.errors.MissingSourceTopicException
import org.slf4j.LoggerFactory

import java.time.Duration

class StreamsBuilder {

  private val logger = LoggerFactory.getLogger(this.getClass)

  def start(): KafkaStreams = {
    logger.info("Getting properties")
    val properties = new PropertyBuilder().Properties()
    logger.info("Getting topology")
    val topology = new TopologyBuilder().Topology()

    logger.info("Creating streams")
    val streams = new KafkaStreams(topology, properties)
    logger.info("Starting streams")
    streams.setUncaughtExceptionHandler(StreamsExceptionHandler(_, _))
    streams.start()

    sys.ShutdownHookThread {
      logger.info("Shutting down streams")
      streams.close(Duration.ofSeconds(30))
    }

    streams
  }

  def StreamsExceptionHandler(thread: Thread, throwable: Throwable): Unit = {
    throwable match {
      case exception: MissingSourceTopicException =>
        logger.error(s"${exception.getMessage}, please create missing topic or remove dangling schema")
        sys.exit(1)
    }
  }

}

