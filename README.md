# KotlinBard

KotlinBard is a kotlin dsl to generate kotlin code, built on top of kotlin-poet [KotlinPoet](https://github.com/square/kotlinpoet).

### Example
KotlinPoet's example is generated by:
```kotlin
val file = buildFile("", "HelloWorld") {
      val greeterClass = ClassName("", "Greeter")
      addClass(greeterClass) {
          primaryConstructor {
              addParameter("name", String::class)
          }
          addProperty("name", String::class) {
              initializer("name")
          }
          addFunction("greet") {
              addStatement("println(%P)", "Hello, \$name")
          }
      }
      addFunction("main") {
          addParameter("args", String::class, VARARG)
          addStatement("%T(args[0]).greet()", greeterClass)
      }
 }
```
KotlinPoet currently consists of mainly KotlinPoet extension functions.

## Current Features
`buildXXX` and `addXXX` functions for every builder:
```kotlin
val file = buildFile{
    addClass(...){...}
    addInterface(...){...}
    addTypeAlias(...){...}
}
val func = buildFunction(...){
    addParameter {...}
}
//builds an AnnotationSpec
val annotation = buildAnnotation(...){...}
//builds a TypeSpec that represents an annotation
val annotationType = buildAnnotationClass(...){...}

//Each type of Spec has its own function
val obj = buildObject(...){}
val enum = buildEnum(...){
    addEnumConstant("name"){...}
}
val intf = buildInterface(...){...}
val constructor = buildContructor{}
val getter = buildGetter{}
```
Dsls for control flow:
```kotlin
val function = buildFunction("analyzeTaco") {
    addCode { //functions are defined in the CodeBlockBuilder scope
        controlFlow("taco.let"){
            addStatement("println(it)")
        }
        If("taco.isSpicy()") {
            addStatement("println(%S)", "spicy!!")
        }.ElseIf("me.isHungry") {
            addStatement("eat(taco)")
        } Else {
            addStatement("saveForLater(taco)")
        }
        
        Do {
            addStatement("makeTaco()")
        }.While("tacos < 5")
        
        For("taco in tacos") {
            addStatement("println(%P)", "taco information: \$taco")
        }
        
        When("taco") {
            e("is SpicyTaco") then {
                addStatement("println(%S)", "Spicy!!")
            }
            Else("eat(%L)", "taco")
        }
    }
}
```
This generates the following function:
```kotlin
fun analyzeTaco() {
  taco.let {
    println(it)
  }
  if (taco.isSpicy()) {
    println("spicy!!")
  }
  else if (me.isHungry) {
    eat(taco)
  }
  else {
    saveForLater(taco)
  }
  do {
    makeTaco()
  } while (tacos < 5)
  for (taco in tacos) {
    println("""taco information: $taco""")
  }
  when (taco) {
    is SpicyTaco -> {
      println("Spicy!!")
    }
    else -> eat(taco)
  }
}
```
`get {}` and `set {}` for properties:
```kotlin
val prop = buildProperty("prop", String::class) {
    get {
        addStatement("return field")
    }
    set("value", String::class) {
        addStatement("field = value")
    }
    //or, for parameterless set
    set {
        addModifiers(KModifier.PRIVATE)
    }
}
```
Constructors and `init` blocks:
```
val type = buildClass("Foo"){
    primaryConstructor {...}
    constructor {...}
    init {...}
}
```
Builders are marked with `@CodegenDsl`:
```kotlin
val file = buildFile("","File"){
    addImport(String::class)
    addClass("Foo"){
        addImport(Int::class) //compile error
    }
}
```

Fun fact: KotlinBard uses code generation to generate many functions -- it uses a previous version of itself to generate itself!


### Usage

Add this to your gradle config:
```groovy
repositories {
    maven {
        jcenter()
    }
}

dependencies {
    implementation "io.github.enjoydambience:kotlinbard:0.1.0"
}
```
Kotlin bard is still under development; api is subject to change.

### Roadmap
- Semantically checked modifiers and types
- Shorter name extensions (?)
- Primary constructor properties
- DSLs and extensions for `XxxName`
- Other DSLs to make codegen code in similar order to output code
- Additional features to be decided

If you have feedback or suggestions, feel free to open an issue.
If you are feeling ambitious, feel free to open a pull request.
