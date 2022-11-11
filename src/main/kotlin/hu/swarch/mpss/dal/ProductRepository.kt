package hu.swarch.mpss.dal

import hu.swarch.mpss.entities.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long> {
}
