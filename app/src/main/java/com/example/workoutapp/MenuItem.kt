package com.example.workoutapp

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val id:String,
    var title: String,
    val contentDescription: String,
    val icon: ImageVector
)
