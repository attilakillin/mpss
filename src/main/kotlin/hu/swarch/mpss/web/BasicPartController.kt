package hu.swarch.mpss.web

import hu.swarch.mpss.dal.PartRepository
import hu.swarch.mpss.dto.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/basic_parts")
class BasicPartController(
    private val partRepository: PartRepository
) {
    @GetMapping
    fun getBasicParts(model: Model): String {
        model.addAttribute("parts", partRepository.findAllBasicParts().map { it.toDTO() })
        return "basic_parts"
    }

    @PostMapping
    fun postBasicPart(@RequestBody data: BasicPartDTO, model: Model): ResponseEntity<Unit> {
        val part = data.toEntity() ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()

        partRepository.save(part)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping
    fun putBasicPart(@RequestBody data: BasicPartDTO, model: Model): ResponseEntity<Unit> {
        val part = data.toEntityWithId() ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()

        partRepository.save(part)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @DeleteMapping
    fun deleteBasicPart(@RequestBody id: IdDTO, model: Model): ResponseEntity<Unit> {
        partRepository.deleteById(id.id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
