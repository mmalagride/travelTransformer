package streams

import io.confluent.kafka.streams.serdes.avro.GenericAvroSerde
import setup.Arguments
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsConfig
import org.slf4j.LoggerFactory

import java.util.Properties

class PropertyBuilder {

  private val logger = LoggerFactory.getLogger(this.getClass)

  def Properties(): Properties = {
    logger.info("Configuring streams application properties")
    val props = new Properties
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, Arguments.ApplicationID)
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, Arguments.Broker)
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, Arguments.AutoOffsetResetConfig)
    props.put("schema.registry.url", Arguments.SchemaRegistryURL)
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String.getClass)
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, classOf[GenericAvroSerde])
    props
  }
}
