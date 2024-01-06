package com.shdclgroup.app.features.home.data.model

data class ProjectResponse (
    val success: Boolean,
    val data: List<Project>,
)

data class Project(
    val _id: String,
    val projectName: String,
    val projectAddress: String,
    val projectType: String,
    val projectStatus: String,
    val buildingType: String,
    val unitPerFloor: Long?,
    val storied: String?,
    val totalUnit: Long?,
    val availableUnit: Long?,
    val price: String,
    val landSize: String?,
    val aptSize: String?,
    val images: List<Any?>,
    val imageUrl: String,
    val clientName: String,
    val clientPhoneNo: String,
    val description: String,
    val handOverDate: String?,
    val numberOfBath: Long?,
    val numberOfBeds: Long?,
    val numberOfDiningRoom: Long?,
    val interiorDesigner: String?,
)
