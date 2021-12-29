package tgd.company.educurrencyconverter.ui.main

sealed class CalculateEvent {
    data class ADD(val value: Int) : CalculateEvent()
    object CLEAR : CalculateEvent()
    object DOT : CalculateEvent()
    object DELETE : CalculateEvent()
    object RESET : CalculateEvent()
}