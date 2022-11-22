package hu.swarch.mpss.dto

data class UserDTO (
    val id: Long,
    val username: String,
    val role: String,
    val prettyRole: String
)