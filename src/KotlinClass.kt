/*
Class
    (This class is used in Kotlin-VarargAndParameter file.

    *Properties in Kotlin will get getters and setters generated for them automatically.
    val properties will get getters automatically.
    var properties will get getters and setters automatically.

    Visibility modifiers
    1. public: Default Value.
    2. internal: Public within the module.
    3. private: Only available in the file which it's implemented.
    4. protected: Only be available within that class or within in any subclass.
 */

class KotlinClass(firstName : String, lastName: String, val middleName: String?, val nickName: String? = "kl") {
    //class className (parameter, parameter, ...) {}

    var codeName: String? = null
        //Overriding default setter.
        set(value) {
            field = value
            println("The new code name is $value")
        }
        //Override default getter.
        get() {
            println("The returned value is $field")
            return field
        }

    /*
    Backing field
        In kotlin, field is only used when needed as a part of a property to hold its value in memory. Field cannot be
        declared directly. However, when it's needed Kotlin provides backing field automatically.
        This BACKING FIELD can be referenced in accessor using keyword "field"
     */

    val usrFirstName: String = firstName    //has a same function of initializing using init block.
    val usrLastName: String

    //property can be declared directly in primary constructor.

    /*
    init: keyword for initializing. Init bock is a code that is run anytime an instance of this class is run.
    Class can have multiple init block. Multiple init block is processed in the order where they are defined within class.

    init blocks are always executed before the Secondary constructor.
     */
    init {
        usrLastName = lastName
    }

    /*
    Secondary constructor: Secondary constructor can provide alternative way for programmer to initiate an instance.
    In Kotlin, Secondary constructor is not strictly necessary because of the power of default parameter values.
    L> Same as the way value nickName is declared.
     */
    init {
        println("init 1")
    }
    constructor(): this("Peter", "Parker", "") {
        println("Secondary Constructor")
    }

    init {
        println("init 2")
    }

    fun returnInfo() {
        val nickNameToPrint = nickName ?: "no nickname"
        /*
        val nickNameToPrint = if(nickName != null) nickName else "no nickname"
        This line is a expanded version of the code right above.

        "Elvis Operator: Elvis Operator '?:' checks whether the value on left side is null or not. If it's not null,
        it returns value on left side. If it's null, it returns the value on right side.
         */

        println("$usrFirstName $usrLastName ($nickName): $codeName")
    }

}