package com.grndctl

import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.fasterxml.jackson.databind.ObjectMapper
import com.typesafe.scalalogging.LazyLogging
import org.scalamock.scalatest.MockFactory
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}
import scala.collection.JavaConverters._

import scala.concurrent.duration._

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
trait BaseSpec extends WordSpec
  with Matchers
  with ScalatestRouteTest
  with MockFactory
  with ScalaFutures
  with LazyLogging {

  private val timeout = 5.seconds

  val OM: ObjectMapper = new ObjectMapper

  def entityAsType[T <: Object](clazz: Class[T]): T = OM.readValue(entityAs[String], clazz)

  def entityAsSeqType[T <: Object](clazz: Class[T]): Seq[T] =
    OM.readValue(
      entityAs[String],
      OM.getTypeFactory.constructCollectionType(classOf[java.util.List[T]], clazz)
    ).asInstanceOf[java.util.List[T]].asScala

  def entityAsMapType[T <: Object](clazz: Class[T]): Map[String, Seq[T]] =
    OM.readValue(
      entityAs[String],
      OM.getTypeFactory.constructMapType(
        classOf[java.util.Map[String, java.util.List[T]]], classOf[String], classOf[java.util.List[T]]
      )
    ).asInstanceOf[java.util.Map[String, java.util.List[T]]].asScala.map(e => (e._1, e._2.asScala)).toMap

  val validMetarStr: String =
    """
      {
        "rawText": "KDEN 250353Z 21014KT 10SM FEW120 FEW220 22/15 A3026 RMK AO2 SLP169 T02220150",
        "stationId": "KDEN",
        "observationTime": "2018-07-25T03:53:00Z",
        "latitude": 39.85,
        "longitude": -104.65,
        "tempC": 22.2,
        "dewpointC": 15,
        "windDirDegrees": 210,
        "windSpeedKt": 14,
        "windGustKt": null,
        "visibilityStatuteMi": 10,
        "altimInHg": 30.259842,
        "seaLevelPressureMb": 1016.9,
        "qualityControlFlags": {
        "corrected": null,
        "auto": null,
        "autoStation": "TRUE",
        "maintenanceIndicatorOn": null,
        "noSignal": null,
        "lightningSensorOff": null,
        "freezingRainSensorOff": null,
        "presentWeatherSensorOff": null
      },
        "wxString": null,
        "skyCondition": [
      {
        "skyCover": "FEW",
        "cloudBaseFtAgl": 12000
      },
      {
        "skyCover": "FEW",
        "cloudBaseFtAgl": 22000
      }
        ],
        "flightCategory": "VFR",
        "threeHrPressureTendencyMb": null,
        "maxTC": null,
        "minTC": null,
        "maxT24HrC": null,
        "minT24HrC": null,
        "precipIn": null,
        "pcp3HrIn": null,
        "pcp6HrIn": null,
        "pcp24HrIn": null,
        "snowIn": null,
        "vertVisFt": null,
        "metarType": "METAR",
        "elevationM": 1640
      }
    """

  val validTafStr: String =
    """
      |[
      |    {
      |        "rawText": "KDEN 150551Z 1506/1612 21012KT P6SM SCT120 BKN200 FM151200 26008KT P6SM FEW120 SCT200 FM151600 34007KT P6SM FEW120 SCT200 FM152000 06011KT P6SM SCT090 BKN200 FM160000 12015G23KT P6SM VCTS SCT090CB BKN140 FM160400 19012KT P6SM SCT140 BKN200 FM160800 26008KT P6SM SCT200",
      |        "stationId": "KDEN",
      |        "issueTime": "2018-08-15T05:51:00Z",
      |        "bulletinTime": "2018-08-15T05:51:00Z",
      |        "validTimeFrom": "2018-08-15T06:00:00Z",
      |        "validTimeTo": "2018-08-16T12:00:00Z",
      |        "remarks": null,
      |        "latitude": 39.85,
      |        "longitude": -104.65,
      |        "elevationM": 1640,
      |        "forecast": [
      |            {
      |                "fcstTimeFrom": "2018-08-15T06:00:00Z",
      |                "fcstTimeTo": "2018-08-15T12:00:00Z",
      |                "changeIndicator": null,
      |                "timeBecoming": null,
      |                "probability": null,
      |                "windDirDegrees": 210,
      |                "windSpeedKt": 12,
      |                "windGustKt": null,
      |                "windShearHgtFtAgl": null,
      |                "windShearDirDegrees": null,
      |                "windShearSpeedKt": null,
      |                "visibilityStatuteMi": 6.21,
      |                "altimInHg": null,
      |                "vertVisFt": null,
      |                "wxString": null,
      |                "notDecoded": null,
      |                "skyCondition": [
      |                    {
      |                        "skyCover": "SCT",
      |                        "cloudBaseFtAgl": 12000,
      |                        "cloudType": null
      |                    },
      |                    {
      |                        "skyCover": "BKN",
      |                        "cloudBaseFtAgl": 20000,
      |                        "cloudType": null
      |                    }
      |                ],
      |                "turbulenceCondition": [],
      |                "icingCondition": [],
      |                "temperature": []
      |            },
      |            {
      |                "fcstTimeFrom": "2018-08-15T12:00:00Z",
      |                "fcstTimeTo": "2018-08-15T16:00:00Z",
      |                "changeIndicator": "FM",
      |                "timeBecoming": null,
      |                "probability": null,
      |                "windDirDegrees": 260,
      |                "windSpeedKt": 8,
      |                "windGustKt": null,
      |                "windShearHgtFtAgl": null,
      |                "windShearDirDegrees": null,
      |                "windShearSpeedKt": null,
      |                "visibilityStatuteMi": 6.21,
      |                "altimInHg": null,
      |                "vertVisFt": null,
      |                "wxString": null,
      |                "notDecoded": null,
      |                "skyCondition": [
      |                    {
      |                        "skyCover": "FEW",
      |                        "cloudBaseFtAgl": 12000,
      |                        "cloudType": null
      |                    },
      |                    {
      |                        "skyCover": "SCT",
      |                        "cloudBaseFtAgl": 20000,
      |                        "cloudType": null
      |                    }
      |                ],
      |                "turbulenceCondition": [],
      |                "icingCondition": [],
      |                "temperature": []
      |            },
      |            {
      |                "fcstTimeFrom": "2018-08-15T16:00:00Z",
      |                "fcstTimeTo": "2018-08-15T20:00:00Z",
      |                "changeIndicator": "FM",
      |                "timeBecoming": null,
      |                "probability": null,
      |                "windDirDegrees": 340,
      |                "windSpeedKt": 7,
      |                "windGustKt": null,
      |                "windShearHgtFtAgl": null,
      |                "windShearDirDegrees": null,
      |                "windShearSpeedKt": null,
      |                "visibilityStatuteMi": 6.21,
      |                "altimInHg": null,
      |                "vertVisFt": null,
      |                "wxString": null,
      |                "notDecoded": null,
      |                "skyCondition": [
      |                    {
      |                        "skyCover": "FEW",
      |                        "cloudBaseFtAgl": 12000,
      |                        "cloudType": null
      |                    },
      |                    {
      |                        "skyCover": "SCT",
      |                        "cloudBaseFtAgl": 20000,
      |                        "cloudType": null
      |                    }
      |                ],
      |                "turbulenceCondition": [],
      |                "icingCondition": [],
      |                "temperature": []
      |            },
      |            {
      |                "fcstTimeFrom": "2018-08-15T20:00:00Z",
      |                "fcstTimeTo": "2018-08-16T00:00:00Z",
      |                "changeIndicator": "FM",
      |                "timeBecoming": null,
      |                "probability": null,
      |                "windDirDegrees": 60,
      |                "windSpeedKt": 11,
      |                "windGustKt": null,
      |                "windShearHgtFtAgl": null,
      |                "windShearDirDegrees": null,
      |                "windShearSpeedKt": null,
      |                "visibilityStatuteMi": 6.21,
      |                "altimInHg": null,
      |                "vertVisFt": null,
      |                "wxString": null,
      |                "notDecoded": null,
      |                "skyCondition": [
      |                    {
      |                        "skyCover": "SCT",
      |                        "cloudBaseFtAgl": 9000,
      |                        "cloudType": null
      |                    },
      |                    {
      |                        "skyCover": "BKN",
      |                        "cloudBaseFtAgl": 20000,
      |                        "cloudType": null
      |                    }
      |                ],
      |                "turbulenceCondition": [],
      |                "icingCondition": [],
      |                "temperature": []
      |            },
      |            {
      |                "fcstTimeFrom": "2018-08-16T00:00:00Z",
      |                "fcstTimeTo": "2018-08-16T04:00:00Z",
      |                "changeIndicator": "FM",
      |                "timeBecoming": null,
      |                "probability": null,
      |                "windDirDegrees": 120,
      |                "windSpeedKt": 15,
      |                "windGustKt": 23,
      |                "windShearHgtFtAgl": null,
      |                "windShearDirDegrees": null,
      |                "windShearSpeedKt": null,
      |                "visibilityStatuteMi": 6.21,
      |                "altimInHg": null,
      |                "vertVisFt": null,
      |                "wxString": "VCTS",
      |                "notDecoded": null,
      |                "skyCondition": [
      |                    {
      |                        "skyCover": "SCT",
      |                        "cloudBaseFtAgl": 9000,
      |                        "cloudType": "CB"
      |                    },
      |                    {
      |                        "skyCover": "BKN",
      |                        "cloudBaseFtAgl": 14000,
      |                        "cloudType": null
      |                    }
      |                ],
      |                "turbulenceCondition": [],
      |                "icingCondition": [],
      |                "temperature": []
      |            },
      |            {
      |                "fcstTimeFrom": "2018-08-16T04:00:00Z",
      |                "fcstTimeTo": "2018-08-16T08:00:00Z",
      |                "changeIndicator": "FM",
      |                "timeBecoming": null,
      |                "probability": null,
      |                "windDirDegrees": 190,
      |                "windSpeedKt": 12,
      |                "windGustKt": null,
      |                "windShearHgtFtAgl": null,
      |                "windShearDirDegrees": null,
      |                "windShearSpeedKt": null,
      |                "visibilityStatuteMi": 6.21,
      |                "altimInHg": null,
      |                "vertVisFt": null,
      |                "wxString": null,
      |                "notDecoded": null,
      |                "skyCondition": [
      |                    {
      |                        "skyCover": "SCT",
      |                        "cloudBaseFtAgl": 14000,
      |                        "cloudType": null
      |                    },
      |                    {
      |                        "skyCover": "BKN",
      |                        "cloudBaseFtAgl": 20000,
      |                        "cloudType": null
      |                    }
      |                ],
      |                "turbulenceCondition": [],
      |                "icingCondition": [],
      |                "temperature": []
      |            },
      |            {
      |                "fcstTimeFrom": "2018-08-16T08:00:00Z",
      |                "fcstTimeTo": "2018-08-16T12:00:00Z",
      |                "changeIndicator": "FM",
      |                "timeBecoming": null,
      |                "probability": null,
      |                "windDirDegrees": 260,
      |                "windSpeedKt": 8,
      |                "windGustKt": null,
      |                "windShearHgtFtAgl": null,
      |                "windShearDirDegrees": null,
      |                "windShearSpeedKt": null,
      |                "visibilityStatuteMi": 6.21,
      |                "altimInHg": null,
      |                "vertVisFt": null,
      |                "wxString": null,
      |                "notDecoded": null,
      |                "skyCondition": [
      |                    {
      |                        "skyCover": "SCT",
      |                        "cloudBaseFtAgl": 20000,
      |                        "cloudType": null
      |                    }
      |                ],
      |                "turbulenceCondition": [],
      |                "icingCondition": [],
      |                "temperature": []
      |            }
      |        ]
      |    }
      |]
    """.stripMargin

  val validAirepStr: String =
    """
      |[
      |    {
      |        "receiptTime": "2018-10-15T01:27:48Z",
      |        "observationTime": "2018-10-15T01:27:00Z",
      |        "qualityControlFlags": null,
      |        "aircraftRef": "AAL96",
      |        "latitude": 51.5667,
      |        "longitude": -42.6833,
      |        "altitudeFtMsl": 38000,
      |        "skyCondition": [],
      |        "turbulenceCondition": [],
      |        "icingCondition": [],
      |        "visibilityStatuteMi": null,
      |        "wxString": null,
      |        "tempC": -40,
      |        "windDirDegrees": 230,
      |        "windSpeedKt": 65,
      |        "vertGustKt": null,
      |        "reportType": "AIREP",
      |        "rawText": "ARP AAL96 5134N04241W 0127 F380 5200N04000W 0139 5300N03000W MS40 230/65 KT N395AN DDL XXH 150127 F56A"
      |    }
      |]
    """.stripMargin

  val validAirlineStr: String =
    """
      |[
      |   {
      |       "id": "5209",
      |       "name": "United Airlines",
      |       "alias": "\\N",
      |       "iata": "UA",
      |       "icao": "UAL",
      |       "callsign": "UNITED",
      |       "country": "United States",
      |       "active": "Y"
      |   }
      |]
    """.stripMargin

  val validChartStr: String =
    """
      |{
      |    "KIAD": {
      |        "charts": {
      |            "STAR": [
      |                {
      |                    "proxy": "https://www.aircharts.org/view/0518b3e9f4fd651d362eaa4eef676f08dbecd923",
      |                    "chartname": "CAVLR THREE (RNAV)",
      |                    "id": "0518b3e9f4fd651d362eaa4eef676f08dbecd923",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100CAVLR.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/7eedeaca6e24e3765ecb468d949bb6a25b4bc2c1",
      |                    "chartname": "COATT FIVE",
      |                    "id": "7eedeaca6e24e3765ecb468d949bb6a25b4bc2c1",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100COATT.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/6ce36cfda204a127997016f8ab76466d18d16999",
      |                    "chartname": "DELRO FOUR",
      |                    "id": "6ce36cfda204a127997016f8ab76466d18d16999",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100DELRO.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/7f491ee1c226c1288b2430c89a59d4ab6007beaa",
      |                    "chartname": "DELRO FOUR, CONT.1",
      |                    "id": "7f491ee1c226c1288b2430c89a59d4ab6007beaa",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100DELRO_C.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/2f6a1ebdc19042c0519e0e383f165c114bdbba96",
      |                    "chartname": "DOCCS TWO",
      |                    "id": "2f6a1ebdc19042c0519e0e383f165c114bdbba96",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100DOCCS.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/1146b7b00a28da8d6edc20037d5342ebeb2ecf72",
      |                    "chartname": "DOCCS TWO, CONT.1",
      |                    "id": "1146b7b00a28da8d6edc20037d5342ebeb2ecf72",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100DOCCS_C.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/9a88bfb3f711e6c4e86a0f9076466871aebcc51f",
      |                    "chartname": "GIBBZ TWO (RNAV)",
      |                    "id": "9a88bfb3f711e6c4e86a0f9076466871aebcc51f",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100GIBBZ.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/9fd70f418f7229200cf9d6313514399ddb83439f",
      |                    "chartname": "GIBBZ TWO (RNAV), CONT.1",
      |                    "id": "9fd70f418f7229200cf9d6313514399ddb83439f",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100GIBBZ_C.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/084c7851647e538fb8528ca7c83f80b3fea1fd8d",
      |                    "chartname": "HYPER SEVEN (RNAV)",
      |                    "id": "084c7851647e538fb8528ca7c83f80b3fea1fd8d",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100HYPER.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/c13496c5cffae31a06f99aa84e04951852d8d4b3",
      |                    "chartname": "HYPER SEVEN (RNAV), CONT.1",
      |                    "id": "c13496c5cffae31a06f99aa84e04951852d8d4b3",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100HYPER_C.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/7ca2ee87ac66001a919b878fae310c01625b25d0",
      |                    "chartname": "HYPER SEVEN (RNAV), CONT.2",
      |                    "id": "7ca2ee87ac66001a919b878fae310c01625b25d0",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100HYPER_C2.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/989eb17745e830f60d78a06a494157bc38bd0d58",
      |                    "chartname": "LEGGO FIVE (RNAV)",
      |                    "id": "989eb17745e830f60d78a06a494157bc38bd0d58",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100LEGGO.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/b4b234149369f99dc27778e8cdaa4f8a2da128c2",
      |                    "chartname": "LEGGO FIVE (RNAV), CONT.1",
      |                    "id": "b4b234149369f99dc27778e8cdaa4f8a2da128c2",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100LEGGO_C.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/58b4a0ac84e4a576e450c637670e633ac6c2fa10",
      |                    "chartname": "MAPEL TWO (RNAV)",
      |                    "id": "58b4a0ac84e4a576e450c637670e633ac6c2fa10",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100MAPEL.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/5896dda7381643f066e8fe369201f24443f95f62",
      |                    "chartname": "MAPEL TWO (RNAV), CONT.1",
      |                    "id": "5896dda7381643f066e8fe369201f24443f95f62",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100MAPEL_C.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/2ac4d93c98b670a2ec3c6b0bcb6c35f4b665f016",
      |                    "chartname": "PRIVO ONE",
      |                    "id": "2ac4d93c98b670a2ec3c6b0bcb6c35f4b665f016",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100PRIVO.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/533b6cbc36743153f707fb0572b5e460eb26823c",
      |                    "chartname": "PRIVO ONE, CONT.1",
      |                    "id": "533b6cbc36743153f707fb0572b5e460eb26823c",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100PRIVO_C.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/dcdef5189ca1313158daaa3a33471679692baf7d",
      |                    "chartname": "SELINSGROVE FIVE",
      |                    "id": "dcdef5189ca1313158daaa3a33471679692baf7d",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100SELINSGROVE.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/80c801ecbc8592ee7cbd7612f7c6e6c80ecd2e67",
      |                    "chartname": "SELINSGROVE FIVE, CONT.1",
      |                    "id": "80c801ecbc8592ee7cbd7612f7c6e6c80ecd2e67",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100SELINSGROVE_C.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/ccc0e5e698bf0f7f1d1b54424199b9d495aeea5b",
      |                    "chartname": "WIGOL ONE (RNAV)",
      |                    "id": "ccc0e5e698bf0f7f1d1b54424199b9d495aeea5b",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100WIGOL.PDF"
      |                }
      |            ],
      |            "Approach": [
      |                {
      |                    "proxy": "https://www.aircharts.org/view/3e12c8bb9da1aa6c03f4aa5f63bbae1c67d84d5c",
      |                    "chartname": "ILS OR LOC RWY 01R",
      |                    "id": "3e12c8bb9da1aa6c03f4aa5f63bbae1c67d84d5c",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100IL1R.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/c10a48e976d7286125e00bb448c794b92bfa086e",
      |                    "chartname": "ILS OR LOC RWY 19L",
      |                    "id": "c10a48e976d7286125e00bb448c794b92bfa086e",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100IL19L.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/f0a2409078bbf52189d1ef9071d29cf9790b0e08",
      |                    "chartname": "ILS OR LOC/DME RWY 01C",
      |                    "id": "f0a2409078bbf52189d1ef9071d29cf9790b0e08",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100ILD1C.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/4aa585ed6534afc78eb5271ad97672bba79048cb",
      |                    "chartname": "ILS OR LOC/DME RWY 01L",
      |                    "id": "4aa585ed6534afc78eb5271ad97672bba79048cb",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100ILD1L.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/16aa0497a0294b65d6d41268678410aaefa902d8",
      |                    "chartname": "ILS OR LOC/DME RWY 12",
      |                    "id": "16aa0497a0294b65d6d41268678410aaefa902d8",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100ILD12.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/cfc4412c4219b22353d40e608d7d40430cf028ab",
      |                    "chartname": "ILS OR LOC/DME RWY 19C",
      |                    "id": "cfc4412c4219b22353d40e608d7d40430cf028ab",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100ILD19C.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/063d51e70c152d3db5917930a8253e19183e20e5",
      |                    "chartname": "ILS OR LOC/DME RWY 19R",
      |                    "id": "063d51e70c152d3db5917930a8253e19183e20e5",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100ILD19R.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/3052c0effd27306e54460121e970b633e88cf690",
      |                    "chartname": "ILS RWY 01C (SA CAT II)",
      |                    "id": "3052c0effd27306e54460121e970b633e88cf690",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100I1CSAC2.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/9f97aa0b0adc9ae5fedeb0334c1f363e38d76d29",
      |                    "chartname": "ILS RWY 01L (CAT II - III)",
      |                    "id": "9f97aa0b0adc9ae5fedeb0334c1f363e38d76d29",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100I1LC2_3.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/63a919a70d314f3947fa98d40cd61efb42e41951",
      |                    "chartname": "ILS RWY 01R (CAT II - III)",
      |                    "id": "63a919a70d314f3947fa98d40cd61efb42e41951",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100I1RC2_3.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/57cd0d7467494f5d635d2b4dbd32b774df3b04c1",
      |                    "chartname": "ILS RWY 19C (CAT II - III)",
      |                    "id": "57cd0d7467494f5d635d2b4dbd32b774df3b04c1",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100I19CC2_3.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/f83a411f7800827dd7fb72a7a65883123f477125",
      |                    "chartname": "ILS RWY 19L (SA CAT II)",
      |                    "id": "f83a411f7800827dd7fb72a7a65883123f477125",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100I19LSAC2.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/5c186379d67f95130a55764b10325a1730e372d2",
      |                    "chartname": "ILS RWY 19R (CAT II - III)",
      |                    "id": "5c186379d67f95130a55764b10325a1730e372d2",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100I19RC2_3.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/2095c753174ecc1264dd8dac4cc51c6992e3e7b1",
      |                    "chartname": "RNAV (GPS) RWY 01L",
      |                    "id": "2095c753174ecc1264dd8dac4cc51c6992e3e7b1",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100R1L.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/03eb7ad98173dc79fe93636f76e064b0e2014ddd",
      |                    "chartname": "RNAV (GPS) RWY 12",
      |                    "id": "03eb7ad98173dc79fe93636f76e064b0e2014ddd",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100R12.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/f679345a52e3b6b96665a96e73dde0c2395da199",
      |                    "chartname": "RNAV (GPS) RWY 19R",
      |                    "id": "f679345a52e3b6b96665a96e73dde0c2395da199",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100R19R.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/cf0cbc3e59b5e5650828fbde66cabd2a305253cd",
      |                    "chartname": "RNAV (GPS) Y RWY 01C",
      |                    "id": "cf0cbc3e59b5e5650828fbde66cabd2a305253cd",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100RY1C.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/67733770fcafcaea13f2854c575f2858df52ffb8",
      |                    "chartname": "RNAV (GPS) Y RWY 01R",
      |                    "id": "67733770fcafcaea13f2854c575f2858df52ffb8",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100RY1R.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/259e6682272df351bf58ef1db48cb73fbd2a4382",
      |                    "chartname": "RNAV (GPS) Y RWY 19C",
      |                    "id": "259e6682272df351bf58ef1db48cb73fbd2a4382",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100RY19C.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/0940088ba000ae452ec3f745aa11a729c71f2a9b",
      |                    "chartname": "RNAV (GPS) Y RWY 19L",
      |                    "id": "0940088ba000ae452ec3f745aa11a729c71f2a9b",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100RY19L.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/179322fb58dfea8b0b6ab36b9c09d28bcd82b91f",
      |                    "chartname": "RNAV (RNP) Z RWY 01C",
      |                    "id": "179322fb58dfea8b0b6ab36b9c09d28bcd82b91f",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100RRZ1C.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/a18829752418f4118c7e2ea85a6c8b7901edf420",
      |                    "chartname": "RNAV (RNP) Z RWY 01R",
      |                    "id": "a18829752418f4118c7e2ea85a6c8b7901edf420",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100RRZ1R.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/573aefbf07533a8855213d50309063623fa77b00",
      |                    "chartname": "RNAV (RNP) Z RWY 19C",
      |                    "id": "573aefbf07533a8855213d50309063623fa77b00",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100RRZ19C.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/08c5159f2e2ccf416635a1983c44674eac78e946",
      |                    "chartname": "RNAV (RNP) Z RWY 19L",
      |                    "id": "08c5159f2e2ccf416635a1983c44674eac78e946",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100RRZ19L.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/e850b8c24fcc2eabe5b8e8ccb85a5cb7a1e8c874",
      |                    "chartname": "VOR/DME RWY 12",
      |                    "id": "e850b8c24fcc2eabe5b8e8ccb85a5cb7a1e8c874",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100VD12.PDF"
      |                }
      |            ],
      |            "General": [
      |                {
      |                    "proxy": "https://www.aircharts.org/view/3e5a0799180967246ecd632cd086e5c5ef82d9bd",
      |                    "chartname": "AIRPORT DIAGRAM",
      |                    "id": "3e5a0799180967246ecd632cd086e5c5ef82d9bd",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100AD.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/fdac71a9a7b1696d6474db831ae68140ec066396",
      |                    "chartname": "ALTERNATE MINIMUMS",
      |                    "id": "fdac71a9a7b1696d6474db831ae68140ec066396",
      |                    "url": "http://155.178.201.160/d-tpp/1812/NE3ALT.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/64dd1e5bdb517b80e9285027615c1347acc9c80a",
      |                    "chartname": "TAKEOFF MINIMUMS",
      |                    "id": "64dd1e5bdb517b80e9285027615c1347acc9c80a",
      |                    "url": "http://155.178.201.160/d-tpp/1812/NE3TO.PDF"
      |                }
      |            ],
      |            "SID": [
      |                {
      |                    "proxy": "https://www.aircharts.org/view/5caa9642327dc2b9f09a868aab4c9564b47ac887",
      |                    "chartname": "BUNZZ THREE (RNAV)",
      |                    "id": "5caa9642327dc2b9f09a868aab4c9564b47ac887",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100BUNZZ.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/c7464e9dc81b14d8822269f6c628c3eb6461e735",
      |                    "chartname": "CAPITAL ONE",
      |                    "id": "c7464e9dc81b14d8822269f6c628c3eb6461e735",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100CAPITAL.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/cb1f77ce1a6b4338355fdff1739506ac33386708",
      |                    "chartname": "CAPITAL ONE, CONT.1",
      |                    "id": "cb1f77ce1a6b4338355fdff1739506ac33386708",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100CAPITAL_C.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/d268809f9bc384852302131ca076ba0968393942",
      |                    "chartname": "CLTCH TWO (RNAV)",
      |                    "id": "d268809f9bc384852302131ca076ba0968393942",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100CLTCH.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/d9b93e218a24c78266f87b14e05054c360dfd786",
      |                    "chartname": "JCOBY THREE (RNAV)",
      |                    "id": "d9b93e218a24c78266f87b14e05054c360dfd786",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100JCOBY.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/4eec27faa4cee9a3f3d5818c56f676f528a96ed9",
      |                    "chartname": "JDUBB TWO (RNAV)",
      |                    "id": "4eec27faa4cee9a3f3d5818c56f676f528a96ed9",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100JDUBB.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/b37899ff4a52aed92a7091ddeefda5fa8858f34b",
      |                    "chartname": "JERES TWO (RNAV)",
      |                    "id": "b37899ff4a52aed92a7091ddeefda5fa8858f34b",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100JERES.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/eba6e8fc7641537739097c4ac800fdc1b6c2c337",
      |                    "chartname": "MCRAY TWO (RNAV)",
      |                    "id": "eba6e8fc7641537739097c4ac800fdc1b6c2c337",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100MCRAY.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/ae022197f17347b433227f03813c360541382f8f",
      |                    "chartname": "RNLDI FOUR (RNAV)",
      |                    "id": "ae022197f17347b433227f03813c360541382f8f",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100RNLDI.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/5911a569379a0a13d9ffb7af628f4bd376a0636f",
      |                    "chartname": "SCRAM FOUR (RNAV)",
      |                    "id": "5911a569379a0a13d9ffb7af628f4bd376a0636f",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100SCRAM.PDF"
      |                },
      |                {
      |                    "proxy": "https://www.aircharts.org/view/b87d16be1fee538bae522f5265bdd7df9637a960",
      |                    "chartname": "WOOLY ONE (RNAV)",
      |                    "id": "b87d16be1fee538bae522f5265bdd7df9637a960",
      |                    "url": "http://155.178.201.160/d-tpp/1812/05100WOOLY.PDF"
      |                }
      |            ]
      |        },
      |        "info": {
      |            "elevation": 312,
      |            "latitude": "38.944533",
      |            "name": "Washington Dulles Intl",
      |            "id": "KIAD",
      |            "longitude": "-77.455811"
      |        }
      |    },
      |    "Thanks": "All information retrieved from AirCharts at http://www.aircharts.org"
      |}
    """.stripMargin

  val validNavaidSeqStr: String =
    """
      |[
      |    {
      |        "id": "85452",
      |        "filename": "Armel_VORTAC_US",
      |        "ident": "AML",
      |        "name": "Armel",
      |        "type": "VORTAC",
      |        "frequencyKhz": 113500,
      |        "latitudeDeg": 38.934600830078125,
      |        "longitudeDeg": -77.4666976928711,
      |        "elevationFt": 297,
      |        "isoCountry": "\"US\"",
      |        "dmeFrequencyKhz": 113500,
      |        "dmeChannel": "082X",
      |        "dmeLatitudeDeg": 0,
      |        "dmeLongitudeDeg": 0,
      |        "dmeElevationFt": 0,
      |        "slavedVariationDeg": -8.001,
      |        "magneticVariationDeg": -10.239,
      |        "usageType": "\"BOTH\"",
      |        "power": "MEDIUM",
      |        "associatedAirport": "KIAD"
      |    }
      |]
    """.stripMargin

  val validNavaidSeqMultiMapStr: String =
    """
      |{
      |    "HYR": [
      |        {
      |            "id": "89088",
      |            "filename": "Hayward_VOR-DME_US",
      |            "ident": "HYR",
      |            "name": "Hayward",
      |            "type": "VOR-DME",
      |            "frequencyKhz": 113400,
      |            "latitudeDeg": 46.01900100708008,
      |            "longitudeDeg": -91.44640350341797,
      |            "elevationFt": 1207,
      |            "isoCountry": "\"US\"",
      |            "dmeFrequencyKhz": 113400,
      |            "dmeChannel": "081X",
      |            "dmeLatitudeDeg": 0,
      |            "dmeLongitudeDeg": 0,
      |            "dmeElevationFt": 0,
      |            "slavedVariationDeg": 3.001,
      |            "magneticVariationDeg": -0.341,
      |            "usageType": "\"LO\"",
      |            "power": "MEDIUM",
      |            "associatedAirport": null
      |        }
      |    ]
      |}
    """.stripMargin

}
