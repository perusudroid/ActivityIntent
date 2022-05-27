package com.androidsolutions.activityintent

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Employee(val id : Int,val name : String) : Parcelable
