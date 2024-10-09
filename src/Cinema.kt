class Cinema {
    private var soldTickets = arrayOf<Ticket>()
    private val films = arrayOf(
        Film("Film 1", 90),
        Film("Film 2", 103),
        Film("Film 3", 87)
    )

    fun sellTicket() {
        val film: Film
        while (true) {
            println("Выберите фильм:")
            films.forEach {
                println("${films.indexOf(it) + 1}: ${it.name}")
            }
            val input = readln()
            var choice = 0
            try {
                choice = input.toInt()
            } catch (e: Exception) {
                println("Недопустимое значение")
                continue
            }
            if (choice !in 1.. films.size) {
                println("Такого варианта нет")
                continue
            }
            film = films[choice - 1]
            break
        }

        var time = ""
        while (true) {
            println("Выберите время:")
            val sessions = calculateSessions(film)
            var choice = 0
            sessions.indices.forEach {
                println("${it + 1}: ${sessions[it].time}")
            }
            try {
                choice = readln().toInt()
            } catch (e: NumberFormatException) {
                println("Недопустимое значение")
                continue
            }
            if (choice !in 1.. sessions.size) {
                println("Такого варианта нет")
                continue
            }
            time = sessions[choice - 1].time
            break
        }

        var seatNumber = 0
        while (true) {
            println("Выберите место (X - место продано):")
            var freePlaces = arrayOf<Int>()
            for (i in 1..60) {
                var isSold = false
                soldTickets.forEach {
                    if (it.film == film.name && it.time == time && it.seat == i && it.isSold) isSold = true
                }
                if (!isSold) freePlaces += i
            }

            for (number in 1..60) {
                if (!freePlaces.contains(number)) {
                    print("X  ")
                    if (number == 60) println()
                    continue
                }
                if (number < 10) print("$number  ") else print("$number ")
                if (number % 10 == 0) println()
            }
            try {
                seatNumber = readln().toInt()
            } catch (e: NumberFormatException) {
                println("Недопустимое значение")
                continue
            }
            if (seatNumber !in freePlaces) {
                println("Место занято")
                continue
            }
            break
        }
        soldTickets += Ticket(film.name, seatNumber, time, true)
    }

    private fun calculateSessions(film: Film): Array<Session> {
        var sessions = arrayOf<Session>()
        var timePassed = 0
        val minutesBetweenSessions = 10
        while (840 - timePassed > film.duration) {
            sessions += Session(film, formatTime(540 + timePassed + minutesBetweenSessions))
            timePassed += film.duration
        }
        timePassed = 0
        return sessions
    }

    private fun formatTime(timeInMinutes: Int): String {
        return "${timeInMinutes / 60}:${timeInMinutes % 60}"
    }
    fun printSoldTickets() {
        soldTickets.forEach { println(it) }
    }
}