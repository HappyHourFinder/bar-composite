package com.mathieuaime.hhf.composite.bar.model

import java.beans.ConstructorProperties

data class Bar @ConstructorProperties("uuid", "name", "latitude", "longitude")
constructor(var uuid: String, var name: String, var latitude: Double, var longitude: Double)