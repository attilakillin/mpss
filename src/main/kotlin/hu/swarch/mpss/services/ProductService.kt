package hu.swarch.mpss.services

import hu.swarch.mpss.dal.PartRepository
import hu.swarch.mpss.dal.ProductRepository
import hu.swarch.mpss.dto.ProductDTO
import hu.swarch.mpss.entities.Product
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val partRepository: PartRepository,
    private val productRepository: ProductRepository
) {
    private fun dtoToEntity(productDTO: ProductDTO): Product {
        val mainPart = partRepository.findByIdOrNull(productDTO.mainPart_id) ?: throw MainPartCannotBeEmpty()
        return Product(0, productDTO.name, mainPart)
    }
    fun createProduct(productDTO: ProductDTO) {
        productRepository.save(dtoToEntity(productDTO))
    }
    fun updateProduct(productDTO: ProductDTO) {
        if (productDTO.id == null)
            throw IdCannotBeNullException()
        if (!productRepository.existsById(productDTO.id))
            throw EntityDoesntExists()
        productRepository.save(dtoToEntity(productDTO).also { it.id = productDTO.id })
    }
}