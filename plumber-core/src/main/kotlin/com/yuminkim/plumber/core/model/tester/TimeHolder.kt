package com.yuminkim.plumber.core.model.tester

import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class TimeHolder {
  fun now(): ZonedDateTime {
    return ZonedDateTime.now()
  }
}
