package com.example.navigationcompodemo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

/**
 *Created by Ankit Bajaj on 20-06-2020.
 */
@Parcelize
data class Money(val amount:BigDecimal) : Parcelable {
}