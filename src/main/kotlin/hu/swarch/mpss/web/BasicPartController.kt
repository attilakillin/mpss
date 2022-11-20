package hu.swarch.mpss.web

import hu.swarch.mpss.dto.*
import hu.swarch.mpss.services.BasicPartService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/basic_parts")
class BasicPartController(
    private val basicPartService: BasicPartService
) {
    @GetMapping
    fun getBasicParts(model: Model): String {
        model.addAttribute("parts", basicPartService.findAllBasicParts())
        return "basic_parts"
    }

    @PostMapping
    fun postBasicPart(@RequestBody data: BasicPartDTO, model: Model): ResponseEntity<Unit> {
        basicPartService.saveBasicPart(data) ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping
    fun putBasicPart(@RequestBody data: BasicPartDTO, model: Model): ResponseEntity<Unit> {
        basicPartService.updateBasicPart(data) ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @DeleteMapping
    fun deleteBasicPart(@RequestBody id: IdDTO, model: Model): ResponseEntity<Unit> {
        basicPartService.deleteBasicPart(id.id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
