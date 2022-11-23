package hu.swarch.mpss.authentication

import org.springframework.security.core.GrantedAuthority

enum class Role(val prettyString: String, private vararg val _authorities: Authority) {
    ADMINISTRATOR("Administrator", Authority.MANAGE_USERS ),
    PROCUREMENT("Procurement", Authority.MANAGE_BASIC_PARTS, Authority.SEE_PROCUREMENT_OVERVIEW),
    PRODUCTION_MANAGER("Production manager", Authority.MANAGE_COMPLEX_PARTS),
    MANAGER("Manager", Authority.MANAGE_PROD_GOALS),
    BASIC_USER("Basic user");

    val authorities get() = _authorities.toList()
}

enum class Authority : GrantedAuthority {
    MANAGE_BASIC_PARTS, MANAGE_COMPLEX_PARTS, MANAGE_PROD_GOALS, SEE_PROCUREMENT_OVERVIEW, MANAGE_USERS;

    override fun getAuthority() = name
}
