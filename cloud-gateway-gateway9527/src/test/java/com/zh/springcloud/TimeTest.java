package com.zh.springcloud;

import org.junit.Test;

import java.time.ZonedDateTime;

public class TimeTest {

    @Test
    public void predicateTime() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
    }
}
