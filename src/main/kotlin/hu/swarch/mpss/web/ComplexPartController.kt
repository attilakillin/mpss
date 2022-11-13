package hu.swarch.mpss.web

import hu.swarch.mpss.dal.ComplexPartRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/complex_part")
class ComplexPartController(
    private val complexPartRepository: ComplexPartRepository
) {
    @GetMapping
    fun getComplexParts(model: Model): String {
        model.addAttribute("complex_parts", complexPartRepository.findAllComplexParts())
        return ""
    }
    @GetMapping("/{id}")
    fun getComplexPartById(@PathVariable id: Long, model: Model): String {
        val goal = complexPartRepository.findById(id)
        model.addAttribute("complex_part", goal)
        return ""
    }
}