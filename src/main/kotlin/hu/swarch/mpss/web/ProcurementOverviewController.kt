package hu.swarch.mpss.web

import hu.swarch.mpss.services.ProductionGoalService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/procurement_overview")
class ProcurementOverviewController (
    private val productionGoalService: ProductionGoalService
) {

    @GetMapping
    fun getProcurementOverview(model: Model): String {
        model.addAttribute("procurements", productionGoalService.getLatestProcurementDatesOfBasicParts())
        return "procurement_details"
    }
}
