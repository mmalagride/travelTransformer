package schemas

import org.apache.avro.{Schema, SchemaBuilder}

object schemas {
  val travelSchema: Schema = SchemaBuilder.record("travelAdvisorySchema").namespace("ca.gms.traveladvisories").fields()
    .requiredString("CountryShort")
    .requiredString("CountryFull")
    .requiredString("Advisory")
    .requiredString("IssuedDateTime")
    .requiredString("ReadDateTime")
    .endRecord()
  val travelSchemaTransformed: Schema = SchemaBuilder.record("travelAdvisorySchema").namespace("ca.gms.traveladvisories").fields()
    .requiredString("CountryShort")
    .requiredString("CountryFull")
    .requiredString("Advisory")
    .requiredString("IssuedDateTime")
    .requiredString("ReadDateTime")
    .requiredLong("IssuedDateUNIX")
    .requiredLong("ReadDateUNIX")
    .requiredInt("AdvisoryLevel")
    .endRecord()
}