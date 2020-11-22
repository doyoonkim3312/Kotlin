/*
Sealed Class
    Sealed Class allow to define restricted class hierarchies. Programmers could define a set number of classes
    all extending a base type, but those classes will be the only ones that can extend that base type.
    (EX: Loading states or results state for a network.


    Difference between Sealed class and Enum class.
    Sealed Class: With sealed class, programmer can have different properties and methods on each types.
    programmer also can use different types of classes itself within their sealed class.
 */
package kotlin_SealedClass

import java.util.*

enum class EntityType {
    HELP, EASY, MEDIUM, HARD;

    fun getFormattedName() = name.toLowerCase().capitalize()
}

object EntityFactory {
    // fun create() = Entity("id", "name")
    fun create(type: EntityType) : Entity {
        val id = UUID.randomUUID().toString()
        val name = when (type) {
            EntityType.EASY -> type.getFormattedName()
            EntityType.MEDIUM -> type.getFormattedName()
            EntityType.HARD -> type.getFormattedName()
            EntityType.HELP -> type.getFormattedName()
        }
        //return Entity(id,name)
        return when (type) {
            EntityType.EASY -> Entity.Easy(id, name)
            EntityType.MEDIUM -> Entity.Medium(id, name)
            EntityType.HARD -> Entity.Hard(id, name, 2f)
            EntityType.HELP -> Entity.Help
        }
        // Based Sealed Class type cannot be instantiate directly.
    }
}

sealed class Entity () {
    object Help : Entity() {
        val name = "Help"
    }
    /*
    Data Class
        Data Class is Kotlin's way of providing very concise, immutable data types.
        Defining class as a Data class, it means it's going to generate methods such as equals hashcode into string
        automatically for programmer.
     */
    data class Easy(val id: String, val name: String): Entity()
    data class Medium(val id: String, val name: String): Entity()
    data class Hard(val id: String, val name: String, val multiplier: Float): Entity()

}

//Extension Method(Function)
fun Entity.Medium.printInfo() {
    println("Medium class: $id")
}

//Extension Property.
val Entity.Medium.info: String
    get() = "Some Info"

/*
Extension Function and properties could be used anytime programmer want to add additional functionality to
existing class.
 */

fun main() {
    val entity = EntityFactory.create(EntityType.HELP)
    val msg = when (entity) {
        Entity.Help -> "Help class"
        is Entity.Easy -> "Easy class"
        is Entity.Medium -> "Medium class"
        is Entity.Hard -> "Hard class"
    }
    println(msg)

    val entity1 = EntityFactory.create(EntityType.EASY)
    val entity2 = EntityFactory.create(EntityType.EASY)
    if (entity1 == entity2) println("they are equal") else println("They are not equal")

    val entity3 = Entity.Medium("id", "name")
    val entity4 = entity3.copy(name = "new Name")
    entity4.printInfo()
    entity4.info
    //copy(): to copy properties from existing object.
    if (entity3 == entity4) println("They are equal") else println("They are not equal")
}