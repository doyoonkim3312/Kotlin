package Functional_Kotlin_Practice

/*
    General DSL (Domain Specific Language) Example
    Also illustrates building type-safe builder.
 */

/*
    Bicycle - XML
    <bicycle description="Fast carbon commuter">
        <bar material="ALUMINUM" type="FLAT"></bar>
        <frame material="CARBON">
            <wheel brake="DISK" material="ALUMINUM"></wheel>
        </frame>
        <fork material="CARBON">
            <wheel brake="DISK" material="ALUMINUM"></wheel>
        </fork>
    </bicycle>

    This is a quite perfect scenario for making type-safe builder. Based on XML code above, a bicycle DSL should looks:
    fun main(args: Array<String>) {
        val commuter = bicycle {
            description("Fast carbon commuter")
            bar {
                barType = FLAT
                material = ALUMINUM
            }
            frame {
                material = CARBON
                backWheel {
                    brake = DISK
                    material = ALUMINUM
                }
            }
            fork {
                material = CARBON
                frontWheel {
                    brake = DISK
                    material = ALUMINUM
                }
            }
        }
    }
 */

// 1. Element interface.
// Under this DSL, all parts of bicycle will implement/extend interface Element.
interface Element {
    fun render(builder: StringBuilder, indent: String)
}

@DslMarker
annotation class ElementMaker

@ElementMaker   // This annotation prevents internal resource from reaches external resource.
abstract class Part(private val name: String): Element {
    private val children = arrayListOf<Element>()
    protected val attributes = hashMapOf<String, String>()

    // Generic Function (T refers all types that implement Element)
    protected fun <T: Element> initElement(element: T, init: T.() -> Unit): T {
        element.init()
        children.add(element)
        return element
    }

    // Override function under interface 'Element'
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent<$name${renderAttribute()}>\n")
        children.forEach { c -> c.render(builder, indent + "\t") }
        builder.append("$indent</$name>\n")
    }

    // Private function for render function (help render belonged attributes.)
    private fun renderAttribute(): String = buildString {
        attributes.forEach { (attr, value) ->
            append(" $attr=\"$value\"")
        }
    }

    override fun toString() = buildString {
        render(this, "")
    }
}

// Enum classes.
enum class Material {
    CARBON, ALUMINUM, STEEL, TITANIUM
}

enum class BarType {
    DROP, FLAT, TT, BULLHORN
}

enum class Brake {
    RIM, DISK
}

abstract class PartWithMaterial(name: String): Part(name) {
    var material: Material
    get() = Material.valueOf(attributes["material"]!!)
    set(value) {
        attributes["material"] = value.name
    }
}

// Detailed Implementation of Each part.
class Bicycle: Part("Bicycle") {
    // Description
    fun description(description: String) {
        attributes["description"] = description
    }

    fun frame(init: Frame.() -> Unit) = initElement(Frame(), init)

    fun bar(init: Bar.() -> Unit) = initElement(Bar(), init)

    fun fork(init: Fork.() -> Unit) = initElement(Fork(), init)

}

class Frame: PartWithMaterial("Frame") {
    // Function for Back Wheel
    fun backWheel(init: Wheel.() -> Unit) = initElement(Wheel(), init)
}

class Fork: PartWithMaterial("Fork") {
    // Function for Front Wheel
    fun frontWheel(init: Wheel.() -> Unit) = initElement(Wheel(), init)
}

class Bar: PartWithMaterial("Bar") {
    // type of bar.
    var barType: BarType
    get() = BarType.valueOf(attributes["bar"]!!)
    set(value) {
        attributes["bar"] = value.name
    }
}

class Wheel: PartWithMaterial("Wheel") {
    // Brake
    var brake: Brake
    get() = Brake.valueOf(attributes["brake"]!!)
    set(value) {
        attributes["brake"] = value.name
    }
}

// Insert function for DSL
fun bicycle(init: Bicycle.() -> Unit): Bicycle {
    val cycle = Bicycle()
    cycle.init()
    return cycle
}

fun main(args: Array<String>) {
    val commuter = bicycle {
        description("Fast Commuter bicycle")
        bar {
            barType = BarType.FLAT
            material = Material.ALUMINUM
        }
        frame {
            material = Material.CARBON
            // Back Wheel
            backWheel {
                material = Material.ALUMINUM
                brake = Brake.DISK
            }
        }
        fork {
            material = Material.CARBON
            frontWheel {
                material = Material.ALUMINUM
                brake = Brake.DISK
            }
        }
    }

    println(commuter)
}






