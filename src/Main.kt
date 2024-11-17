import kotlin.random.Random

fun main() {
    val cities = listOf(
        "МОСКВА", "САНКТ-ПЕТЕРБУРГ", "СОЧИ", "ПЕРМЬ", "НИЖНИЙ НОВГОРОД", "ВОРОНЕЖ", "САМАРА",
        "ТЮМЕНЬ", "ПЕНЗА", "РОСТОВ-НА-ДОНУ", "УФА", "КАЗАНЬ", "КРАСНОДАР", "НОВОСИБИРСК",
        "ЕКАТЕРИНБУРГ", "ПЕРМЬ", "КРАСНОЯРСК", "ИРКУТСК", "ВЛАДИВОСТОК", "ЯРОСЛАВЛЬ", "КАЗАНЬ"
    )

    while (true) {
        println("Хотите составить план поезда или закончить работу? (составить/EXIT)")
        val input = readLine() ?: ""

        if (input == "EXIT") break

        if (input == "составить") {
            val (startCity, endCity) = generateRoute(cities)
            println("Создано направление: $startCity - $endCity")

            val passengerCount = generatePassengerCount()
            println("Продано билетов: $passengerCount")

            val train = createTrain(passengerCount)
            println("Сформирован поезд: $startCity - $endCity, состоящий из ${train.wagons.size} вагонов")

            sendTrain(train, startCity, endCity)
        }

        else {
            println("Неверный ввод. Попробуйте снова.")
        }
    }
}

fun generateRoute(cities: List<String>): Pair<String, String> { //Функция для генерации маршрута
    while (true) {
        val startCity = cities.random()
        val endCity = cities.random()
        if (startCity != endCity)
            return Pair(startCity, endCity)
        }

}
fun generatePassengerCount(): Int { //Функция для генерации количества пассажиров
    return Random.nextInt(5, 202)
}

fun createTrain(passengerCount: Int): Train { //Функция для создания поезда
    val train = Train()
    var remainingPassengers = passengerCount
    while (remainingPassengers > 0) {
        val wagonCapacity = Random.nextInt(5, 26)
        val passengersInWagon = minOf(wagonCapacity, remainingPassengers)
        train.addWagon(Wagon(wagonCapacity, passengersInWagon))
        remainingPassengers -= passengersInWagon
    }
    return train
}

fun sendTrain(train: Train, startCity: String, endCity: String) { //Функция для отправки поезда
    println("Поезд $startCity - $endCity отправлен!")

    train.wagons.forEachIndexed { index, wagon ->
        println("Вагон ${index + 1}: Вместимость - ${wagon.capacity}, Пассажиров - ${wagon.passengers}")
    }
}

class Train(var wagons: MutableList<Wagon> = mutableListOf()) {
    fun addWagon(wagon: Wagon) {
        wagons.add(wagon)
    }
}

data class Wagon(val capacity: Int, val passengers: Int)