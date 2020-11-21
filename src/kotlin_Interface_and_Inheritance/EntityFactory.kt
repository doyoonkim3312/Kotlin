/*
Companion Object
    Companion Object is an object scoped to an instance of another class.
    Companion Object has an access to private properties and methods of enclosing class.
    Companion Object can be flexible if programmer need them to. Programmers could use those to compose other types of
    behavior, store semi-static properties or method and use them to create 'FACTORY' by referencing private inner
    properties or methods of the enclosing class.
 */
package kotlin_Interface_and_Inheritance

interface IdProvider {
    fun getId(): String
}

class Entity private constructor(val id: String) {

    companion object Factory : IdProvider {

        override fun getId(): String {
            return "326"
        }
        const val id = "id"
        fun create() = Entity(getId())
    }
}

fun main() {
    val entity = Entity.Factory.create()
    //val entity = Entity.Companion.create() -> Using companion object to create the instance of a class that has 'private'
    Entity.id

}

