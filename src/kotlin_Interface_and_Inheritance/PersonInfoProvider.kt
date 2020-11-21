/*
Interface
Interface in Kotlin has a function exactly what 'Interface' means. It provides kind of 'Template' that can be used
under class.
Interface without parameters, methods, etc, can be used as a marker interface.
 */
package kotlin_Interface_and_Inheritance

interface PersonInfoProvider {
    val providerInfo: String
    fun returnInfo(person: Person) {
    }

    fun setNickName(person: Person, newNickName: String?) {
        println(providerInfo)
        person.nickName = newNickName
    }
}

interface SessionInfoProvider {
    fun getSessionInfo(): String
}

open class BasicInfoProvider : PersonInfoProvider, SessionInfoProvider {
    override val providerInfo: String
        get() = "Basic Info Provider"

    protected open val sessionIdPrefix = "session"

    override fun returnInfo(person: Person) {
        println("return info")
        person.returnInformation()
    }

    override fun setNickName(person: Person, newNickName: String?) {
        super.setNickName(person, newNickName)
        println("cool")
        /*
        if additional codes are needed, and if that additional codes are not declared in interface, programmer can use
        'super' keyword to add additional codes while remaining the pre-declared codes in interface.
         */
    }

    override fun getSessionInfo(): String {
        return sessionIdPrefix
    }
}

fun main() {
    val provider = BasicInfoProvider()

    val testPerson = Person("Doyoon", "Kim", "")
    testPerson.codeName = "326"

    provider.returnInfo(testPerson)
    provider.setNickName(testPerson, "kd")
    provider.returnInfo(testPerson)

    provider.getSessionInfo()
    checkType(provider)

    /*
    Object Expression
    Object Expression using 'object' keyword can extend the existing class (creating anonymous inner class rather than
    creating new named class)
     */
    val fancyProvider = object : PersonInfoProvider {
        override val providerInfo: String
            get() = "New Info Provider"

        fun getSessionInfo() = "new Session info created by anonymous inner class"
    }
    fancyProvider.returnInfo(testPerson)
    fancyProvider.getSessionInfo()
    checkType(fancyProvider)
}

//This function do type checking whether PersonInfoProvider is also an instance of a SessionInfoProvider.
fun checkType(infoProvider: PersonInfoProvider) {
    if(infoProvider is SessionInfoProvider) {
        println("is a session info provider")
        (infoProvider as SessionInfoProvider).getSessionInfo()   //'as' keyword is used to cast something to another type
        /*
        Kotlin also has a Smart Casting, that means, additional casting process manually is not required.
        (infoProvider as SessionInfoProvider).getSessionInfo() --> infoProvider.getSessionInfo()
         */
    } else {
        println("not a session info provider")
    }
}