package hu.swarch.mpss.web

import hu.swarch.mpss.dto.ComplexPartDTO
import hu.swarch.mpss.dto.IdDTO
import hu.swarch.mpss.services.ComplexPartService
import hu.swarch.mpss.services.PartService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/complex_parts")
class ComplexPartController(
    private val complexPartService: ComplexPartService,
    private val partService: PartService
) {
    @GetMapping
    fun getComplexParts(model: Model): String {
        model.addAttribute("parts", complexPartService.findAllComplexParts())
        model.addAttribute("allIntermediateParts", partService.findAllIntermediateParts())
        return "complex_parts"
    }

    @PostMapping
    fun postComplexPart(@RequestBody data: ComplexPartDTO, model: Model): ResponseEntity<Unit> {
        complexPartService.saveComplexPart(data) ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping
    fun putComplexPart(@RequestBody data: ComplexPartDTO, model: Model): ResponseEntity<Unit> {
        complexPartService.updateComplexPart(data) ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @DeleteMapping
    fun deleteComplexPart(@RequestBody id: IdDTO, model: Model): ResponseEntity<Unit> {
        complexPartService.deleteComplexPart(id.id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
