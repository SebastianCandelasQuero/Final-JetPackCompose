package com.example.final_jetpackcompose.Model

class City(val name: String, val timeZone: String) {
    companion object {
        fun generarCiudades(): List<City> {
            val ciudades = listOf(
                City("New_York", "America/New_York"),
                City("Los_Angeles", "America/Los_Angeles"),
                City("Chicago", "America/Chicago"),
                City("Houston", "America/Chicago"),
                City("London", "Europe/London"),
                City("Paris", "Europe/Paris"),
                City("Berlin", "Europe/Berlin"),
                City("Tokyo", "Asia/Tokyo"),
                City("Beijing", "Asia/Shanghai"),
                City("Sydney", "Australia/Sydney"),
                City("Moscow", "Europe/Moscow"),
                City("Istanbul", "Europe/Istanbul"),
                City("Rio_de_Janeiro", "America/Sao_Paulo"),
                City("Cairo", "Africa/Cairo"),
                City("Mumbai", "Asia/Kolkata"),
                City("Cape_Town", "Africa/Johannesburg"),
                City("Toronto", "America/Toronto"),
                City("Dubai", "Asia/Dubai"),
                City("Mexico_City", "America/Mexico_City"),
                City("Seoul", "Asia/Seoul"),
                City("Madrid", "Europe/Madrid"),
                City("Granada", "Europe/Madrid"),
                City("Sydney", "Australia/Sydney"),
                City("New_Delhi", "Asia/Kolkata"),
                City("Rome", "Europe/Rome")
            )
            return ciudades
        }
    }

    fun name(): String {
        return name
    }

    fun timezone(): String {
        return timeZone
    }
}


