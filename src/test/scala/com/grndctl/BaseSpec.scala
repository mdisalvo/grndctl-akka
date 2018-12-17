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

  def entityToType[T <: Object](clazz: Class[T]): T = OM.readValue(entityAs[String], clazz)

  def entityToCollectionType[T <: Object](clazz: Class[T]): Seq[T] =
    OM.readValue(
      entityAs[String],
      OM.getTypeFactory.constructCollectionType(classOf[java.util.List[T]], clazz)
    ).asInstanceOf[java.util.List[T]].asScala

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

}
