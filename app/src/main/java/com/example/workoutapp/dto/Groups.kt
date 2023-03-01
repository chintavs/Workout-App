package com.example.workoutapp.dto

data class Groups(var groupName: String, var groupMembers: Array<User>){

    //toString override
    override fun toString(): String {
        var groupMembersStr: String = ""
        for (x in groupMembers){
            groupMembersStr += "$x\n"
        }
        return "Group Name: $groupName\nGroup Members: $groupMembersStr"
    }
}
