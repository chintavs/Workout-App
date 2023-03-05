package com.example.workoutapp
//
sealed class Screen(val route: String){
    object MainScreen : Screen("main_screen")
    object GroupScreen : Screen("group_screen")
    object ProfileScreen : Screen("Profile_screen")


    fun withArgs(vararg args:String): String{
        return buildString{
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
