package com.yuminkim.plumber.core.model.specification

data class Timeout(
  private val durationInSeconds: Long
) {
  fun toSeconds(): Long {
    return durationInSeconds
  }

  companion object {
    fun from(expr: String): Timeout {
      // expr: 1h, 1m, 1s
      val duration = expr.substring(0, expr.length - 1).toLong()
      val unit = when (expr.last().lowercase()) {
        "h" -> Unit.HOURS
        "m" -> Unit.MINUTES
        "s" -> Unit.SECONDS
        else -> throw IllegalArgumentException("Invalid timeout expression: $expr")
      }
      return of(duration, unit)
    }

    fun of(duration: Long, unit: Unit): Timeout {
      return when (unit) {
        Unit.SECONDS -> Timeout(duration)
        Unit.MINUTES -> Timeout(duration * 60)
        Unit.HOURS -> Timeout(duration * 3600)
      }
    }
  }

  enum class Unit {
    SECONDS,
    MINUTES,
    HOURS
  }
}
