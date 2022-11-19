package hu.swarch.mpss.web

import hu.swarch.mpss.dal.PartRepository
import hu.swarch.mpss.entities.BasicPart
import hu.swarch.mpss.entities.ComplexPart
import hu.swarch.mpss.entities.Part
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.time.Duration

@Controller
@RequestMapping("/complex_parts")
class ComplexPartController(
    private val partRepository: PartRepository
) {
    @GetMapping
    fun getComplexParts(model: Model): String {
        val basic1 = partRepository.save(BasicPart("Component sample", 69420.0, Duration.ofHours(10)))
        val basic2 = partRepository.save(BasicPart("Other thing", 69420.0, Duration.ofHours(10)))

        val components = hashMapOf<Part, Int>(basic1 to 2, basic2 to 5)
        partRepository.save(ComplexPart("Complex thing", components, Duration.ofHours(5)))

        model.addAttribute("parts", partRepository.findAllComplexParts())
        return "complex_parts"
    }
}
