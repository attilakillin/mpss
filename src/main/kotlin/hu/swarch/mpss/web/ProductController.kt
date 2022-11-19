package hu.swarch.mpss.web

import hu.swarch.mpss.dal.PartRepository
import hu.swarch.mpss.dal.ProductRepository
import hu.swarch.mpss.dal.ProductionGoalRepository
import hu.swarch.mpss.dto.*
import hu.swarch.mpss.services.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/products")
class ProductController(
    private val productRepository: ProductRepository,
    private val productService: ProductService,
    private val partRepository: PartRepository
) {

    @GetMapping
    fun getProducts(model: Model): String {
        model.addAttribute("products", productRepository.findAll())
        model.addAttribute("parts", partRepository.findAll())
        return "products"
    }

    @PostMapping
    fun postBasicPart(@RequestBody data: ProductDTO, model: Model): ResponseEntity<Unit> {
        productService.createProduct(data)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping
    fun putBasicPart(@RequestBody data: ProductDTO, model: Model): ResponseEntity<Unit> {
        productService.updateProduct(data)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @DeleteMapping
    fun deleteBasicPart(@RequestBody id: IdDTO, model: Model): ResponseEntity<Unit> {
        productRepository.deleteById(id.id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}