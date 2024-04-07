package com.example.apitest

sealed class Routes(val route: String) {
    object ListScreen : Routes("ListScreen")
    object DetailScreen : Routes("DetailScreen/{mountName}") {
        fun createRoute(mountName: String) = "DetailScreen/$mountName"
    }
    object MainScreen : Routes("MainScreen")

}