package hu.swarch.mpss.web

import hu.swarch.mpss.dal.PartRepository
import hu.swarch.mpss.dal.ProductRepository
import hu.swarch.mpss.dal.ProductionGoalRepository
import hu.swarch.mpss.dto.*
import hu.swarch.mpss.services.EntityDoesntExists
import hu.swarch.mpss.services.IdCannotBeNullException
import hu.swarch.mpss.services.MainPartCannotBeEmpty
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
        try {
            productService.createProduct(data)
        } catch (e : MainPartCannotBeEmpty) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping
    fun putBasicPart(@RequestBody data: ProductDTO, model: Model): ResponseEntity<Unit> {
        try {
            productService.updateProduct(data)
        } catch (e : MainPartCannotBeEmpty) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
        catch (e : IdCannotBeNullException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
        catch (e : EntityDoesntExists) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @DeleteMapping
    fun deleteBasicPart(@RequestBody id: IdDTO, model: Model): ResponseEntity<Unit> {
        productRepository.deleteById(id.id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}