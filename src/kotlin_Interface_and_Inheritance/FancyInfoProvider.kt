/*
Inheritance
    Class in Kotlin is closed in default, which means class cannot be inherited from or extended.
    To extend, need to add 'open' keyword before 'class' keyword
 */

package kotlin_Interface_and_Inheritance

class FancyInfoProvider : BasicInfoProvider() {

    override val sessionIdPrefix: String
        get() = "fancy session"

    override val providerInfo: String
        get() = "Fancy Info provider"

    override fun returnInfo(person: Person) {
        super.returnInfo(person)
        println("Fancy Info!")
    }
}