package hu.swarch.mpss.authentication

import org.springframework.security.core.GrantedAuthority

enum class Role(private vararg val _authorities: Authority) {
    ADMINISTRATOR(Authority.MANAGE_USERS),
    PROCUREMENT(Authority.MANAGE_BASIC_PARTS, Authority.SEE_PROCUREMENT_OVERVIEW),
    PRODUCTION_MANAGER(Authority.MANAGE_COMPLEX_PARTS),
    MANAGER(Authority.MANAGE_PROD_GOALS),
    BASIC_USER;

    val authorities get() = _authorities.toList()
}

enum class Authority : GrantedAuthority {
    MANAGE_BASIC_PARTS, MANAGE_COMPLEX_PARTS, MANAGE_PROD_GOALS, SEE_PROCUREMENT_OVERVIEW, MANAGE_USERS;

    override fun getAuthority() = name
}
