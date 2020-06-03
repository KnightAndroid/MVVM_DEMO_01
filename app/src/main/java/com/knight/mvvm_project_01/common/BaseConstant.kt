package com.knight.mvvm_project_01.common


/**
 * @author created by luguian
 * @organize 车童网
 * @Date 2019/11/13 15:31
 * @descript:
 */
object BaseConstant {

    // 数据库名字
    const val TABLE_PREFS = "jetpack"
    const val SP_USER_NAME = "SP_USER_NAME"
    const val SP_USER_ID = "SP_USER_ID"

    //传递的参数
    const val ARGS_NAME = "ARGS_NAME"
    const val ARGS_EMAIL = "ARGS_EMAIL"


    //单个页面的大小
    const val SINGLE_OAGE_SIZE = 10

    // MeModel
    const val KEY_IMAGE_URI = "KEY_IMAGE_URI"

    //DetailActivity 传输的数据
    const val DETAIL_SHOE_ID = "DETAIL_SHOE_ID"


    @JvmField val NOTIFICATION_TITLE: CharSequence = "WorkRequest Starting"
    const val CHANNEL_ID = "VERBOSE_NOTIFICATION"
    const val NOTIFICATION_ID = 1


    // The name of the image manipulation work
    const val IMAGE_MANIPULATION_WORK_NAME = "image_manipulation_work"
    //Other keys
    const val OUTPUT_PATH = "blur_filter_outputs"
    const val TAG_OUTPUT = "OUTPUT"

    // Name of Notification Channel for verbose notifications of background work
    @JvmField val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence =
        "Verbose WorkManager Notifications"

    const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
        "Shows notifications whenever work starts"


    const val DELAY_TIME_MILLIS: Long = 3000


}