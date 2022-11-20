package hu.swarch.mpss.web

import hu.swarch.mpss.dal.ProductionGoalRepository
import hu.swarch.mpss.services.ProcurementService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/procurement_details")
class ProcurementDetailsController (
    private val procurementService: ProcurementService
) {

    @GetMapping
    fun getProcurements(model: Model): String {
        model.addAttribute("procurements", procurementService.details())
        return "procurement_details"
    }

    @GetMapping("/{id}")
    fun getProcurementForGoal(@PathVariable id: Long, model: Model) : String {
        val proc = procurementService.allSubParts(id)
        model.addAttribute("procurement", proc)
        return "procurement_goal"
    }
}