package hu.swarch.mpss.web

import hu.swarch.mpss.dal.PartRepository
import hu.swarch.mpss.dto.*
import hu.swarch.mpss.services.ProductionGoalService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/production_goals")
class ProductionGoalController(
    private val productionGoalService: ProductionGoalService,
    private val partRepository: PartRepository
) {

    @GetMapping
    fun getGoals(model: Model): String {
        model.addAttribute("goals", productionGoalService.findAllProductionGoals())
        model.addAttribute("products", partRepository.findAllFinalProducts())
        return "production_goals"
    }

    @PostMapping
    fun postBasicPart(@RequestBody data: ProductionGoalDTO, model: Model): ResponseEntity<Unit> {
        productionGoalService.saveProductionGoal(data) ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping
    fun putBasicPart(@RequestBody data: ProductionGoalDTO, model: Model): ResponseEntity<Unit> {
        productionGoalService.updateProductionGoal(data) ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @DeleteMapping
    fun deleteBasicPart(@RequestBody id: IdDTO, model: Model): ResponseEntity<Unit> {
        productionGoalService.deleteProductionGoal(id.id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
