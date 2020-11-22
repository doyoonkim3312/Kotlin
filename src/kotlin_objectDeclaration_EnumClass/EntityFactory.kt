/*
Object Declaration
    Object Declaration is an convenient way of creating thread-save singletons within Kotlin
    Object Declaration's initialization is thread-safe and done at the first access.
 */

package kotlin_objectDeclaration_EnumClass

import java.util.*

/*
Enum class
    Enum class in Kotlin have synthetic method allowing to list the defined enum constants and to get an enum constants
    by its name.
 */
enum class EntityType {
    EASY, MEDIUM, HARD;

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
        }
        return Entity(id,name)
    }
}

class Entity (val id: String, val name: String) {
    override fun toString(): String {
        return "ID: $id NAME: $name"
    }
}

fun main() {
    val entity = EntityFactory.create(EntityType.EASY)
    println(entity)

    val mediumEntity = EntityFactory.create(EntityType.MEDIUM)
    println(mediumEntity)

    val hardEntity = EntityFactory.create(EntityType.HARD)
    println(hardEntity)
}