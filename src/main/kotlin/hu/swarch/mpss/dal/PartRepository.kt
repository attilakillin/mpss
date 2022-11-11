package hu.swarch.mpss.dal

import hu.swarch.mpss.entities.BasicPart
import hu.swarch.mpss.entities.Part
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PartRepository : JpaRepository<Part, Long> {
    @Query("from BasicPart")
    fun findAllBasicParts(): List<BasicPart>
}
