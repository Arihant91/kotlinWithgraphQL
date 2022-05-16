package com.example.kotlinWithGraphQL

import com.expediagroup.graphql.generator.annotations.GraphQLDescription

import com.expediagroup.graphql.server.operations.Query
import org.springframework.stereotype.Component

var CONFERENCE1= Conference(name = "KotlinConf", year = 2019)

@Component
class ConferenceQuery : Query {

    var people1 : List<People> = listOf(
    Attendee(name = "Jane", ticket = TicketType.FULL),
    Speaker(name = "Guillaume", talks = listOf("GraphQL is awesome", "GraphQL-Kotlin is even better"))
    ).filter{ it.name.startsWith("")}


    fun people() : List<People> = people1

    fun conference() = CONFERENCE1


    fun schedule(): ScheduleDetails = ScheduleDetails(
            greetings = "Welcome to thie list of talks",
            talks = listOf("graphQL is awesome", "GrapQL-Kotlin is even better")
    )

    fun helloWorld(): String = "Hello World!"

}

data class Conference(@GraphQLDescription("Awesome place to learn `things` ") var name: String,
                      @Deprecated("no needed anymore")
                      val year: Int?)


interface People {
    val name: String
}

enum class TicketType {
    CONFERENCE, WORKSHOP, FULL
}

data class Attendee(
        override val name: String,
        val ticket: TicketType
) : People

data class Speaker(
        override val name: String,
         val talks: List<String>
) : People

