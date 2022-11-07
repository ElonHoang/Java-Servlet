package com.aptech.hw5.model.ulti;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Ultility {
	public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static Date convertLocalDateTimeToDateUsingInstant(LocalDateTime dateToConvert) {
		return (Date) java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}
	public static Timestamp convertLocalDatetimeToTimestamp(LocalDateTime time) {
		return Timestamp.valueOf(time);
	}
	
	public static LocalDateTime convertLocalTimestampToLocalDateTime(Timestamp time) {
		return time.toLocalDateTime();
	}
}
