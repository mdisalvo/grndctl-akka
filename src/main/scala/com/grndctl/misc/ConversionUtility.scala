package com.grndctl.misc

import com.grndctl.model.aggregates.{ConversionResult, WindComponent}
import org.apache.commons.lang3.math.Fraction.getFraction

/**
  * @author Michael Di Salvo
  * michael.vincent.disalvo@gmail.com
  */
trait ConversionUtility {

  object UnitOfMeasure extends Enumeration {
    val Celsius         = Value("C")
    val Fahrenheit      = Value("F")
    val Millibars       = Value("mb")
    val InchesOfMercury = Value("inHg")
  }

  private val ConvFactor: Double = 33.8639

  def fToC(tF: Double) = new ConversionResult(
    (tF - 32) * getFraction(5, 9).doubleValue(), UnitOfMeasure.Celsius.toString
  )

  def cToF(tC: Double) = new ConversionResult(
    tC * 1.8 + 32, UnitOfMeasure.Fahrenheit.toString
  )

  def inchesToMillibars(pressInches: Double) = new ConversionResult(
    ConvFactor * pressInches, UnitOfMeasure.Millibars.toString
  )

  def millibarsToInches(pressMillis: Double) = new ConversionResult(
    pressMillis / ConvFactor, UnitOfMeasure.InchesOfMercury.toString
  )

  def windComponents(windSpeed: Double, windDirection: Double, heading: Double) = new WindComponent(
    windSpeed, windDirection, heading
  )

}
