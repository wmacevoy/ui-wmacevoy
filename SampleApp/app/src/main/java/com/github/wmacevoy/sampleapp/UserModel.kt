package com.github.wmacevoy.sampleapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


data class User(
    val id: Int = -1,
    val name: String = "Guest",
    val email: String = "unkknown"
)
class UserData {
    val username: String = "guest"
    val realName: String = "Guest User"
    val roles: Set<String> = emptySet()

}


class UserViewModel : ViewModel() {
    private val _user = MutableLiveData(UserData())
    val user : LiveData<UserData> = _user
    var username : String
        get() = _user.value!!.username
        set(value : String) {
            _user.value = _user.value?.copy(username = value)
        }
    var realName : String
        get() = _user.value!!.realName
        set(value : String) {
            _user.value = _user.value?.copy(realName = value)
        }


    var realName : String
        get() = _user.value!!.realName
    fun setUser(username: String, realName: String, roles: Set<String>) {
        _user.value = UserModel(username, realName, roles)
    }

    fun addRole(role: String) {
        _user.value = _user.value?.copy(roles = _user.value!!.roles + role)
    }

    fun removeRole(role: String) {
        _user.value = _user.value?.copy(roles = _user.value!!.roles - role)
    }
}
