package utils

import play.api.libs.json.JsLookupResult

import scala.language.implicitConversions

trait JsLookupUtils {
  implicit def changeToString(jsValue: JsLookupResult): String = {
    try {
      jsValue.get.toString.replace("\"", "")
    } catch {
      case _: Throwable => ""
    }
  }

  implicit def changeToBoolean(jsValue: JsLookupResult): Boolean = {
    try {
      jsValue.get.toString.replace("\"", "").toBoolean
    } catch {
      case _: Throwable => false
    }
  }

  implicit def changeToInt(jsValue: JsLookupResult): Int = {
    try {
      jsValue.get.toString.replace("\"", "").toInt
    } catch {
      case _: Throwable => 0
    }
  }

  implicit def changeToFloat(jsValue: JsLookupResult): Float = {
    try {
      jsValue.get.toString.replace("\"", "").toFloat
    } catch {
      case _: Throwable => 0
    }
  }

  implicit def changeToDouble(jsValue: JsLookupResult): Double = {
    try {
      jsValue.get.toString.replace("\"", "").toDouble
    } catch {
      case _: Throwable => 0
    }
  }

  implicit def changeToLong(jsValue: JsLookupResult): Long = {
    try {
      jsValue.get.toString.replace("\"", "").toLong
    } catch {
      case _: Throwable => 0
    }
  }

  implicit def changeOptStrToOptStr(jsValue: JsLookupResult): Option[String] = {
    jsValue.toOption.map(_.toString().replace("\"", ""))
  }

  implicit def changeOptDblToOptDbl(jsValue: JsLookupResult): Option[Double] = {
    jsValue.toOption.map(_.toString().replace("\"", "").toDouble)
  }

  implicit def changeToOptionInt(jsValue: JsLookupResult): Option[Int] = {
    try {
      Some(jsValue.get.toString.replace("\"", "").toInt)
    } catch {
      case _: Throwable => None
    }
  }

  implicit def changeToOptionLong(jsValue: JsLookupResult): Option[Long] = {
    try {
      Some(jsValue.get.toString.replace("\"", "").toLong)
    } catch {
      case _: Throwable => None
    }
  }

}
