package hu.swarch.mpss.web

import hu.swarch.mpss.services.ProcurementService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/procurement_details")
class ProcurementDetailsController (
    private val procurementService: ProcurementService) {

    @GetMapping
    fun getProcurement(model: Model): String {
        model.addAttribute("procurements", procurementService.details())
        return "procurement_details"
    }
}