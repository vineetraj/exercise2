import java.math.BigDecimal

interface BookingManager {
    val version: String
    fun isSeatFree(seat: Seat): Boolean
    fun reserveSeat(seat: Seat, customerID: Long): Boolean
    fun systemStatus() = "All Systems are operational"
}

open class BasicBookingManager(authKey: String) : BookingManager {
    override val version = "1.0"
    override fun isSeatFree(seat: Seat) = true
    override fun reserveSeat(seat: Seat, customerID: Long) = false

    init {
        if (authKey != "admin") throw UnAuthUserException()
    }
}

class AdvancedBookingManager : BasicBookingManager("admin"), java.io.Closeable {
    override val version = "2.0"
    fun howManyBookings() = 10

    override fun close() {}
}

class UnAuthUserException : Throwable() //our own custom exception

fun String.toSentenceCase(): String { //extension function
    return this[0].uppercase() + this.substring(1)
}

fun main() {
    println(AdvancedBookingManager().isSeatFree(Seat(1, 1, BigDecimal.ZERO, "")))

    val greeting = "hello bro".toSentenceCase()
    println(greeting)
}
