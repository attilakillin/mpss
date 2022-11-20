package hu.swarch.mpss.services

import hu.swarch.mpss.dal.PartRepository
import hu.swarch.mpss.dto.ComplexPartDTO
import hu.swarch.mpss.dto.toDuration
import hu.swarch.mpss.entities.ComplexPart
import hu.swarch.mpss.entities.Part
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ComplexPartService(
    private val partRepository: PartRepository
) {
    private fun dtoToEntity(dto: ComplexPartDTO): ComplexPart? {
        if (dto.name.isBlank() || dto.subparts.isEmpty()) return null
        val constructionTime = dto.constructionTime.toDuration() ?: return null

        val subparts: MutableMap<Part, Int> = mutableMapOf()
        for ((subpartId, count) in dto.subparts) {
            if (count == 0 || dto.id == subpartId) return null
            val subpart = partRepository.findByIdOrNull(subpartId) ?: return null
            if (subpart is ComplexPart && subpart.isFinalProduct) return null

            subparts[subpart] = count
        }

        return ComplexPart(
            name = dto.name,
            constructionTime = constructionTime,
            subparts = subparts,
            isFinalProduct = dto.isFinalProduct
        )
    }

    fun findAllComplexParts(): List<ComplexPart> {
        return partRepository.findAllComplexParts()
    }

    fun saveComplexPart(dto: ComplexPartDTO): ComplexPart? {
        if (dto.id != null) return null

        val part = dtoToEntity(dto) ?: return null
        partRepository.save(part)
        return part
    }

    fun updateComplexPart(dto: ComplexPartDTO): ComplexPart? {
        if (dto.id == null || !partRepository.existsById(dto.id)) return null

        val part = dtoToEntity(dto)?.also { it.id = dto.id } ?: return null
        partRepository.save(part)
        return part
    }

    fun deleteComplexPart(id: Long) {
        partRepository.deleteById(id)
    }
}
