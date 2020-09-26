package ab.misc

import java.time._

object DateAndTime extends App {

  /** LocalDateTime */
  println(s"LocalTime.now       ${java.time.LocalTime.now}")

  /** Time Zones */
  val zoneId = java.time.ZoneId.of("Europe/Paris")
  println(s"ZoneId Paris ${zoneId}")

  /** Offset now*/
  println(s"OffsetTime.now      ${OffsetTime.now}")
  println(s"OffsetDateTime.now  ${OffsetDateTime.now}")


  /** */
  println("\n   OffsetTime")
  val offsetTime_Utc: OffsetTime = OffsetTime.of(10, 1, 0, 0, ZoneOffset.UTC)
  println(s"OffsetTime Utc   $offsetTime_Utc")
  val tenFiveOffset = OffsetTime.of(10, 1, 0, 0, ZoneOffset.ofHours(-5))
  println(s"OffsetTime -05   $tenFiveOffset")


  println("\n   ZonedDateTime")
  val zonedDateTimeUtc = ZonedDateTime.of(2017,11,11, 10, 1, 0, 0, ZoneOffset.UTC)
  println(s"ZonedDateTime Utc toInstant    ${zonedDateTimeUtc.toInstant}")
  val zonedDateTime5 = ZonedDateTime.of(2017,11,11, 5, 1, 0, 0, ZoneOffset.ofHours(-5))
  println(s"ZonedDateTime -05 toInstant    ${zonedDateTime5.toInstant}")
  assert(offsetTime_Utc != zonedDateTime5)

  println(s"ZonedDateTime -05 toLocalDateTime ${zonedDateTimeUtc.toLocalDateTime}")

  /** conversions */
  val withZoneSameInstantUtc = zonedDateTimeUtc.withZoneSameInstant(ZoneOffset.ofHours(-5))
  println(s"UTC withZoneSameInstant ${withZoneSameInstantUtc}")
  val withZoneSameLocalUtc = zonedDateTimeUtc.withZoneSameLocal(ZoneOffset.ofHours(-5))
  println(s"UTC withZoneSameInstant ${withZoneSameLocalUtc}")


  /** parse from string */
  val parsedOffsetTime = OffsetTime.parse("10:01-05:00")
  println(s"parsed OffsetTime $parsedOffsetTime")


  /** compare with time zone */
  val localTime = LocalTime.parse("10:01")
  val wawZone = ZoneId.of("Europe/Warsaw")
  val wawDateTime = ZonedDateTime.of(LocalDate.now(wawZone), localTime, wawZone)
  println(s"Warsaw ZonedDateTime $wawDateTime")
  println(s"Waw Time in UTC ${wawDateTime.toInstant}")


}
