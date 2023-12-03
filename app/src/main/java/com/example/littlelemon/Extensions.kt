package com.example.littlelemon

import android.content.Context


fun Context.getNullString(stringId: Int?): String {
    if (stringId == null)
        return ""
    return this.getString(stringId)
}