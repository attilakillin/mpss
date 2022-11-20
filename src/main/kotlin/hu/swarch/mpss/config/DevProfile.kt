package hu.swarch.mpss.config

import hu.swarch.mpss.dal.PartRepository
import hu.swarch.mpss.dal.ProductionGoalRepository
import hu.swarch.mpss.entities.BasicPart
import hu.swarch.mpss.entities.ComplexPart
import hu.swarch.mpss.entities.ProductionGoal
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant

@Profile("dev")
@Service
class DevProfile(
    private val partRepository: PartRepository,
    private val goalRepository: ProductionGoalRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {
        // Basic parts:
        val alpha = partRepository.save(BasicPart(
            name = "Basic Alpha",
            price = 15.7,
            procurementTime = Duration.ofHours(2))
        )
        val gamma = partRepository.save(BasicPart(
            name = "Basic Gamma",
            price = 64.0,
            procurementTime = Duration.ofMinutes(45))
        )

        // Complex parts:
        val theta = partRepository.save(ComplexPart(
            name = "Complex Theta",
            subparts = mapOf(alpha to 1, gamma to 3),
            constructionTime = Duration.ofDays(1),
            isFinalProduct = false)
        )
        val omicron = partRepository.save(ComplexPart(
            name = "Final Omicron",
            subparts = mapOf(gamma to 2, theta to 1),
            constructionTime = Duration.ofHours(5).plusMinutes(30),
            isFinalProduct = true)
        )

        // Goals:
        goalRepository.save(ProductionGoal(
            products = mapOf(omicron to 3),
            deadline = Instant.now()
        ))
        goalRepository.save(ProductionGoal(
            products = mapOf(omicron to 1),
            deadline = Instant.now().plusSeconds(1234_0000)
        ))
    }
}
