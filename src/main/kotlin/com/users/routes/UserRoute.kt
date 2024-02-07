package com.users.routes


import com.users.models.User
import com.users.models.users
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.userRouting() {
    route("/user") {

        get {
            if (users.isNotEmpty()) {
                call.respond(users)
            } else {
                call.respondText("No users found", status = HttpStatusCode.OK)
            }
        }

        get("{id?}") {
            val id =
                call.parameters["id"] ?: return@get call.respondText("missing id", status = HttpStatusCode.BadRequest)
            val user = users.find { it.id == id } ?: return@get call.respondText(
                "No user found with the id number $id",
                status = HttpStatusCode.NotFound
            )

            call.respond(user)
        }

        post {
            val user = call.receive<User>()
            users.add(user)
            call.respondText("User created successfully", status = HttpStatusCode.Created)
        }

        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (users.removeIf {
                    it.id == id
                }) {
                call.respondText("User removed successfully", status = HttpStatusCode.Accepted)
            }
            else {
                call.respondText("Not found", status = HttpStatusCode.NotFound)
            }
        }
    }
}