package hu.swarch.mpss.services

import hu.swarch.mpss.dal.PartRepository
import hu.swarch.mpss.dto.BasicPartDTO
import hu.swarch.mpss.dto.toDuration
import hu.swarch.mpss.entities.BasicPart
import org.springframework.stereotype.Service

@Service
class BasicPartService(
    private val partRepository: PartRepository
) {
    private fun dtoToEntity(dto: BasicPartDTO): BasicPart? {
        if (dto.name.isBlank()) return null

        val duration = dto.procurementTime.toDuration() ?: return null

        return BasicPart(
            name = dto.name,
            price = dto.price,
            procurementTime = duration
        )
    }

    fun findAllBasicParts(): List<BasicPart> {
        return partRepository.findAllBasicParts()
    }

    fun saveBasicPart(dto: BasicPartDTO): BasicPart? {
        if (dto.id != null) return null

        val part = dtoToEntity(dto) ?: return null
        return partRepository.save(part)
    }

    fun updateBasicPart(dto: BasicPartDTO): BasicPart? {
        if (dto.id == null || !partRepository.existsById(dto.id)) return null

        val part = dtoToEntity(dto)?.also { it.id = dto.id } ?: return null
        return partRepository.save(part)
    }

    fun deleteBasicPart(id: Long) {
        partRepository.deleteById(id)
    }
}
