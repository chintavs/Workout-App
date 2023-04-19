package com.example.workoutapp.dto

data class Group(var groupName: String, var groupMembers: Array<User>){

    //toString override
    override fun toString(): String {
        var groupMembersStr: String = ""
        for (x in groupMembers){
            groupMembersStr += "$x\n"
        }
        //Returns:
        //Group Name: $groupName
        //Group Members: $groupMembersStr
        return "Group Name: $groupName\nGroup Members: $groupMembersStr"
    }

    fun nameToString(): String {
        return "$groupName"
    }
}
