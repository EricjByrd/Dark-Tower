package io.github.joshlha.partnershipproject

import java.sql.DriverManager.println

abstract class Character(open val name: String, open val health: Int) {
    abstract fun greet()
}
open class Monster(override val name: String, override val health: Int, open val atkPoints: Int, open val defPoints: Int
,open val matkPoints: Int, open val mdefPoints: Int)
    : Character(name, health){
    override fun greet() {
        println("$name has approached!")
    }

}

class Boss(override val name: String, override val health: Int, override val atkPoints: Int, override val defPoints: Int
,override val matkPoints: Int, override val mdefPoints: Int): Monster(
    name,health,atkPoints,defPoints,matkPoints,mdefPoints), specialCombatant{
    final override fun greet() {
        println("You have encountered $name!")
    }
    override fun attack() {
        println("$name attacks!")
        //return "$name attacks ferociously!"
    }
    override fun defend(): String {
        println("$name defends!")
        return "$name defends!"
    }
    override fun magAttack(): String {
        println("$name used spell!")
        return ""
    }
    override fun magCure() {
        println("$name used //cureSpell!")
    }
    override fun specialMove() {
        println("$name uses the special move //move")
    }

}

class playerCharacter(override val name: String, override val health: Int, val atkPoints: Int, val defPoints: Int,
                      val matkPoints: Int, val mdefPoints: Int, ): Character(name, health), specialCombatant{
    override fun greet(){
        println("")
    }
    override fun attack() {
        println("$name attacks!")
    }
    override fun defend(): String {
        println("$name defends!")
        return "yes"
    }
    override fun magAttack(): String {
        println("$name used spell!")
        return "yes"
    }
    override fun magCure() {
        println("$name used //cureSpell!")
    }
    override fun specialMove() {
        println("$name uses the special move //move")
    }
}


val goblin = Monster("Goblin A", 20, 40, 40, 10, 10)
val Asteroth = Boss("Asteroth", 400, 100, 55, 20, 20)
val Eric = playerCharacter("Eric", 100, 100, 100, 100, 100)

interface normCombatant{
    fun attack()
    fun defend()
    fun magAttack()
}

interface specialCombatant{
    fun attack()
    fun defend(): String
    fun magAttack(): String
    fun magCure()
    fun specialMove()
}

