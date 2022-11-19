package hu.swarch.mpss.web

import hu.swarch.mpss.dal.ProductRepository
import hu.swarch.mpss.dal.ProductionGoalRepository
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
    private val productionGoalRepository: ProductionGoalRepository,
    private val productionGoalService: ProductionGoalService,
    private val productRepository: ProductRepository
) {

    @GetMapping
    fun getGoals(model: Model): String {
        model.addAttribute("goals", productionGoalRepository.findAll().map { it.toDTO() })
        model.addAttribute("products", productRepository.findAll())
        return "production_goals"
    }

    @PostMapping
    fun postBasicPart(@RequestBody data: ProductionGoalDTO, model: Model): ResponseEntity<Unit> {
        productionGoalService.createProductionGoal(data)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping
    fun putBasicPart(@RequestBody data: ProductionGoalDTO, model: Model): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @DeleteMapping
    fun deleteBasicPart(@RequestBody id: IdDTO, model: Model): ResponseEntity<Unit> {
        productionGoalRepository.deleteById(id.id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}