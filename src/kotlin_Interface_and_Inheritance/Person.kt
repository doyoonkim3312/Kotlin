package kotlin_Interface_and_Inheritance

class Person(var firstName: String = "John", var lastName: String = "Doe", var nickName: String? = "JD") {
    var codeName: String? = null
        set(value) {
            field = value
            println("the new code name is $value")
        }
        get() {
            println("the returned code name is $field")
            return field
        }

    fun returnInformation() {
        val codeNameToTarget = codeName ?: "No code name"
        println("$firstName $lastName ($nickName): $codeNameToTarget")
    }
}