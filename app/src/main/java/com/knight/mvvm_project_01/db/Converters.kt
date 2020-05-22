package com.knight.mvvm_project_01.db

import androidx.room.TypeConverter
import java.util.*


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2020/5/21 15:05
 * @descript:类型转换器
 */
class Converters {

    @TypeConverter fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter fun datestampToCalendar(value: Long): Calendar = Calendar.getInstance().apply {
        timeInMillis = value

    }
}