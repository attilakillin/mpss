package hu.swarch.mpss.services

import hu.swarch.mpss.dal.PartRepository
import hu.swarch.mpss.entities.Part
import org.springframework.stereotype.Service

@Service
class PartService(
    private val repository: PartRepository
) {
    fun findAllIntermediateParts(): List<Part> {
        val results = mutableListOf<Part>()
        results.addAll(repository.findAllBasicParts())
        results.addAll(repository.findAllNonFinalComplexParts())
        return results
    }
}
