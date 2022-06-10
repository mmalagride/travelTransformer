package streams

import schemas.schemas._
import org.slf4j.LoggerFactory
import setup.Arguments
import org.apache.kafka.streams.{KeyValue, StreamsBuilder, Topology}
import org.apache.avro.generic.{GenericData, GenericRecord}
import java.time.Instant

class TopologyBuilder {
  private val logger = LoggerFactory.getLogger(this.getClass)

  def Topology(): Topology = {
    logger.info("Building topology for streams application")
    val builder = new StreamsBuilder
    Transformer(builder)
    builder.build()
  }

  def Transformer(builder: StreamsBuilder): Unit = {
    val rawTopic = builder.stream[String, GenericRecord](Arguments.Topic)
    val topic = rawTopic.map((key,value) => {
        val travelTransformed = new GenericData.Record(travelSchemaTransformed)
        travelTransformed.put("CountryShort", value.get("CountryShort"))
        travelTransformed.put("CountryFull", value.get("CountryFull"))
        travelTransformed.put("Advisory", value.get("Advisory"))
        var AdvisoryLevel: Integer = 0
        value.get("Advisory").toString match {
          case "Avoid all travel" => AdvisoryLevel = 1
          case "Avoid all travel (with regional advisories)" => AdvisoryLevel = 2
          case "Avoid non-essential travel" => AdvisoryLevel = 3
          case "Avoid non-essential travel (with regional advisories)" => AdvisoryLevel = 4
          case "Exercise a high degree of caution" => AdvisoryLevel = 5
          case "Exercise a high degree of caution (with regional advisories)" => AdvisoryLevel = 6
          case "Exercise normal security precautions" => AdvisoryLevel = 7
          case "Exercise normal security precautions (with regional advisories)" => AdvisoryLevel = 8
        }
        travelTransformed.put("AdvisoryLevel", AdvisoryLevel)
        travelTransformed.put("IssuedDateTime", value.get("CountryShort"))
        travelTransformed.put("IssuedDateUNIX", Instant.parse(value.get("IssuedDateTime").toString + ".00Z").getEpochSecond + 21600)
        travelTransformed.put("ReadDateTime", value.get("ReadDateTime"))
        travelTransformed.put("ReadDateUNIX", Instant.parse(value.get("ReadDateTime").toString + ".00Z").getEpochSecond + 21600)
        KeyValue.pair(key,travelTransformed)
      }
    )
    topic.to(Arguments.Topic + "-transformed")
  }
}
