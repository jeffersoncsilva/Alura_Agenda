package com.alura.jeffersonapps.agenda.database.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class ConversoCalendar {

    @TypeConverter
    public Long toLong(Calendar calendar){
        return calendar.getTimeInMillis();
    }

    @TypeConverter
    public Calendar toCalendar(Long val){
        Calendar instance = Calendar.getInstance();
        if(val != null)
            instance.setTimeInMillis(val);
        return instance;
    }
}
