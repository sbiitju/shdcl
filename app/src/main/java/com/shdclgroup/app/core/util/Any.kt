package com.shdclgroup.app.core.util

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
fun Any.toJson(): String {
    return try {
        Gson().toJson(this)
    } catch (e: JsonSyntaxException) {
        ""
    }
}


inline fun <reified T> String.fromJson(): T? {  //reified keyword to allow the type T to be inferred at runtime
    return try {
        Gson().fromJson(this, T::class.java)
    } catch (e: JsonSyntaxException) {
        null
    }
}


/**

Usage:

data class Person(val name: String, val age: Int)




val person = Person("John Doe", 30)




// Serialize the object to a JSON string

val json = person.toJson()




// Deserialize the JSON string to an object

val deserializedPerson = json.fromJson<Person>()

 */