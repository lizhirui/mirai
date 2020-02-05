package net.mamoe.mirai.api.http.route

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.routing.routing
import net.mamoe.mirai.api.http.dto.GroupDTO
import net.mamoe.mirai.api.http.dto.MemberDTO
import net.mamoe.mirai.api.http.dto.QQDTO
import net.mamoe.mirai.api.http.dto.toJson
import net.mamoe.mirai.contact.toMutableList

fun Application.infoModule() {
    routing {

        miraiGet("/friendList") {
            val ls = it.bot.qqs.toMutableList().map { qq -> QQDTO(qq) }
            call.respondJson(ls.toJson())
        }

        miraiGet("/groupList") {
            val ls = it.bot.groups.toMutableList().map { group -> GroupDTO(group) }
            call.respondJson(ls.toJson())
        }

        miraiGet("/memberList") {
            val ls = it.bot.getGroup(paramOrNull("target")).members.toMutableList().map { member -> MemberDTO(member) }
            call.respondJson(ls.toJson())
        }
    }
}