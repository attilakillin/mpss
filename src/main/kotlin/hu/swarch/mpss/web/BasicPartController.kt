package hu.swarch.mpss.web

import hu.swarch.mpss.dal.PartRepository
import hu.swarch.mpss.entities.BasicPart
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.time.Duration

@Controller
@RequestMapping("/basic_parts")
class BasicPartController(
    private val partRepository: PartRepository
) {
    @GetMapping
    fun getBasicParts(model: Model): String {
        partRepository.save(BasicPart("Sample", 69420.0, Duration.ofHours(10)))

        model.addAttribute("parts", partRepository.findAllBasicParts())
        return "basic_parts"
    }


}
