package hu.swarch.mpss.entities

import java.time.Duration
import javax.persistence.*
@Entity(name = "parts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
abstract class Part(
    @Id @GeneratedValue
    open var id: Long = 0,
    open val name: String
) {
    override fun equals(other: Any?): Boolean {
        if (other !is Part) return false
        return this.id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    abstract fun calculateSumDuration(): Duration
}
