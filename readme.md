#General agent programming language
The idea is to create a groovy dsl that works simliar to 2apl.
2apl uses prolog to design its code, however I thought it would
look syntasticly a lot better in a groovy dsl.

Using a groovy dsl has the added advantage of having the entire groovy
code base behind you in the DSL, and Groovy is increadibly easy to understand
Python level easy.

## Design overview:
(not completed)
So the main idea is that you have a `java/groovy/scala/any jvm langauge that
can use jars` project and you import this library to load your own defined
agent scripts.

The scripts get executed by the library. the library might do it asynchronus,
depending how you configure it.

You can pass an environment with which the scripts will interact. In asyncrhonus
mode the library will do some black magic to make all the method call's thread safe

it works by inserting an object in between the script and the environment 
which will send the call in form of an akka message rather than a direct 
call.

I'll probably also make it that you can use this library by passing a closure
rather than creating a separate script file. This should make it also work
for compiled projects.

## differences from 2apl
Rather than building my own interpreter or threading scheme, groovy dsl's will
be used and akka for multi-threading.

Gradle is used for building instead of ant. This will make it easier to use
3rd party libraries and keep those up to date.

There won't be a GUI of any kind. I think spending time on this is a mistake.
Creating a GUI is left to be done by someone else.

It will also use Scala as a main programming language. Now you might wonder
how I combine this with groovy? It turns out that groovy doesn't really
care what language the DSL classes are as long as  they're readable bytecode.

The language itself will also be more simple. I won't create separate
functionality for believe updates or add goal guards.
These functionalities can both be implemented by hand.

### Why use Groovy as the DSL language?
Groovy is extremely easy to understand, while meanwhile providing some really
cool features.

You can also import existing Java, Groovy and Scala libraries inside the script
to support the agent.

I could've also used a Scala script. But I think Scala is a bad Language for 
scripting. The learning curve to use Scala is pretty huge, this is for me
enough reason to not use it. Besides the speed bonus that Scala gets over

### Why use Scala as the library language?
Scala is awesome for multithreading. Especially the imutability.

### No prolog?
I don't know how to combine it with groovy.
