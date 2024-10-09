fun main() {
    val cinema = Cinema()
    while (true) {
        cinema.sellTicket()
        println("Продолжить? 1 - Да, 2 - Нет")
        if (readln() == "2") break
    }
    println("Проданные билеты:")
    cinema.printSoldTickets()
}

