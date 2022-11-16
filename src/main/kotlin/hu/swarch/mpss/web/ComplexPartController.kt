package hu.swarch.mpss.web

import hu.swarch.mpss.dal.PartRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/complex_parts")
class ComplexPartController(
    private val partRepository: PartRepository
) {
    @GetMapping
    fun getComplexParts(model: Model): String {
        model.addAttribute("complex_parts", partRepository.findAllComplexParts())
        return ""
    }
    @GetMapping("/{id}")
    fun getComplexPartById(@PathVariable id: Long, model: Model): String {
        val goal = partRepository.findById(id)
        model.addAttribute("complex_part", goal)
        return ""
    }
}
