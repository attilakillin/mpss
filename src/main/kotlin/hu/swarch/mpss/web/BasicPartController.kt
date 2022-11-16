package hu.swarch.mpss.web

import hu.swarch.mpss.dal.PartRepository
import hu.swarch.mpss.dto.*
import hu.swarch.mpss.entities.BasicPart
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.time.Duration

@Controller
@RequestMapping("/basic_parts")
class BasicPartController(
    private val partRepository: PartRepository
) {
    @GetMapping
    fun getBasicParts(model: Model): String {
        partRepository.save(BasicPart("Sample", 69420.0, Duration.ofHours(10)))

        model.addAttribute("parts", partRepository.findAllBasicParts().map { it.toDTO() })
        return "basic_parts"
    }

    @PostMapping
    fun postBasicPart(@RequestBody data: BasicPartDTO, model: Model): String {
        val part = data.toEntity()
        if (part != null) {
            partRepository.save(part)
            model.addAttribute("success", "saving")
        } else {
            model.addAttribute("error", "saving")
        }

        model.addAttribute("parts", partRepository.findAllBasicParts().map { it.toDTO() })
        return "basic_parts"
    }

    @PutMapping
    fun putBasicPart(@RequestBody data: BasicPartDTO, model: Model): String {
        val part = data.toEntityWithId()
        if (part != null) {
            partRepository.save(part)
            model.addAttribute("success", "edit")
        } else {
            model.addAttribute("error", "edit")
        }

        model.addAttribute("parts", partRepository.findAllBasicParts().map { it.toDTO() })
        return "basic_parts"
    }

    @DeleteMapping
    fun deleteBasicPart(@RequestBody id: IdDTO, model: Model): String {
        partRepository.deleteById(id.id)

        model.addAttribute("success", "deletion")
        model.addAttribute("parts", partRepository.findAllBasicParts().map { it.toDTO() })
        return "basic_parts"
    }
}
