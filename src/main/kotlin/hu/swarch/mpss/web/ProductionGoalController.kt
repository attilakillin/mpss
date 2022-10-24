package hu.swarch.mpss.web

import hu.swarch.mpss.dal.ProductionGoalRepository
import hu.swarch.mpss.entities.ProductionGoal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.time.Instant

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

    @GetMapping("/{id}")
    fun getGoalDetail(@PathVariable id: Long, model: Model): String {
        val goal = productionGoalRepository.findById(id)

        model.addAttribute("goal", goal)

        return "goal_details"
    }

    @GetMapping("/create")
    fun getCreateGoal(model: Model): String {
        model.addAttribute("goal", ProductionGoal(0, arrayListOf(), Instant.now()))
        model.addAttribute("products_goal", null)
        return "goal_create"
    }

    @PostMapping("/create")
    fun postGoal(@ModelAttribute goal: ProductionGoal, model: Model): String {
        val goal = productionGoalRepository.save(goal)
        model.addAttribute("goal", goal)
        return "goal_create"
    }
}