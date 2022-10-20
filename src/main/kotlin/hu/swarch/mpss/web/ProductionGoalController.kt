package hu.swarch.mpss.web

import hu.swarch.mpss.dal.ProductionGoalRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/production_goals")
class ProductionGoalController(
    private val productionGoalRepository: ProductionGoalRepository
) {

    @GetMapping
    fun getGoals(model: Model): String {
        val goals = productionGoalRepository.findAll()

        model.addAttribute("goals", goals)

        return "production_goals"
    }
}