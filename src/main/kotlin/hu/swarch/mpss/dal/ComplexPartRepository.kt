package hu.swarch.mpss.dal

import hu.swarch.mpss.entities.ComplexPart
import hu.swarch.mpss.entities.Part
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ComplexPartRepository: JpaRepository<Part, Long> {
    @Query("from ComplexPart")
    fun findAllComplexParts(): List<ComplexPart>
}