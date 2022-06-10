import streams.StreamsBuilder
import org.slf4j.LoggerFactory

object main extends App {

  private val logger = LoggerFactory.getLogger(this.getClass)

  logger.info("starting the stream builder")
  new StreamsBuilder().start()

}
