package Functional_Kotlin_Practice

// Main Method.
fun main(args: Array<String>) {
    // objectExpressionBasic()
    // objectDeclarationExample()
    companionObjectExample()
}

fun objectExpressionBasic() {
    // Object is a natual singleton, which means, it is not a creating a behavioral pattern, however,
    // it means a 'function of a language itself.

    // Example below is an object without explicit type.
    val foo = object {
        val property: String = ""

        fun method(): Int {
            println("Function 'Method' called")
            return 1
        }
    }

    val boo: String = "${foo.property} and ${foo.method()}"
    println(boo)
}

// Using object without type has one restriction:
// It is allowed to use 'locally' inside of method or privately in class only.
class OuterClass {
    val internalValue = object {
        val property = ""
    }
}

fun objectWithoutTypeRestriction() {
    val foo: OuterClass = OuterClass()

    // Compile Time Error: Unresolved Reference: property.
    // println(foo.internalValue.property)
}

// Object Declaration
// Object with an explicit name is called: object declaration.
// Object is a singleton, therefore, it is not necessary to instantiate it.

interface fooable {
    fun fooableMethod(): String
}

interface foobooable {
    fun foobooableMethod(): Int
}

private class BooClass(val string: String): fooable {
    override fun fooableMethod(): String {
        return string
    }
}

// Object Decalration
// Object also extend/implement other class or interface as well.
object FooBoo: foobooable {
    fun fooBooMethod(param: fooable) {
        println(param.fooableMethod())
    }

    override fun foobooableMethod(): Int {
        return 1
    }
}

fun objectDeclarationExample() {
    val boo: BooClass = BooClass("BOO")

    // Use fooBooMethod inside of object FooBoo.
    FooBoo.fooBooMethod(boo)
}

// Companion Object:
// Object that is declared inside of class or interface can be expressed by 'companion object.'
// Companion object is like a variable with static keyword in Java class. Since the static keyword is not allowed
// in Kotlin, it is possible to consider companion object as a group of 'static' values that belong to its class.
class Cupcake(val flavor: String) {
    fun flavor(): String {
        return flavor
    }

    // Companion Object.
    // Companion object also can have its own name.
    companion object Factory {
        fun factoryInfo(): Unit {
            println("This is a companion object 'Factory'. Factory offers various value/function to generate cupcake.")
        }

        // Return Cupcake object with specific flavor.
        fun almond(): Cupcake {
            return Cupcake("Almond");
        }

        fun blueberry(): Cupcake {
            return Cupcake("Blueberry")
        }

        fun banana(): Cupcake {
            return Cupcake("Banana")
        }

        fun plain(): Cupcake {
            return Cupcake("Plain")
        }
    }
}

fun companionObjectExample() {
    val cupcakeFactory: Cupcake.Factory = Cupcake.Factory

    val almondCupcake = Cupcake.Factory.almond()
    val blueberryCupcake = Cupcake.Factory.blueberry()
    val bananaCupcake = cupcakeFactory.banana()
    val plainCupcake = cupcakeFactory.plain()

    println(almondCupcake.flavor)
    println(blueberryCupcake.flavor)
    println(bananaCupcake.flavor)
    println(plainCupcake.flavor)

}

// Type Alias
// Type Alias offers a method that declare a name of existing type name.
// It is very useful with Generic.

// This is an interface with a type parameter T.
interface Machine<T> {
    fun process(product: T): String
}

// Oven is a machine that 'bake' something that is bakeable.
interface Bakeable {
    fun bake(): String {
        return "Baking..."
    }
}

// Therefore, an Oven interface can be defined as -->
// interface Oven: Machine<Bakeable>

// In some point of view, 'Oven' is just a name that demonstrate Machine<Bakeable>.
// Here, the type alias starts to play.
typealias Oven = Machine<Bakeable>

// Now, type alias allows to use Machine<Bakeable> with just name 'Oven'.
class ElectronicOven: Oven {
    override fun process(product: Bakeable): String {
        return "${product.bake()} something."
    }
}


