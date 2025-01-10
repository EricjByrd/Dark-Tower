package io.github.ericjbyrd.partnershipproject

import io.github.ericjbyrd.partnershipproject.playerPosition.x
import io.github.ericjbyrd.partnershipproject.playerPosition.y
import java.sql.DriverManager.println

//open means, open to interpretation and does not require an initialized value on override.
//abstract means, open to interpretation but requires an initialized value on override.

abstract class Character(open val name: String, open val health: Int, open val mp: Int, open val x: Int, open val y: Int) {
    abstract fun greet()
}

open class Monster(override val name: String,
                   override val health: Int,
                   override val mp: Int,
                   override val x: Int,
                   override val y:Int,
                   open val atkPoints: Int,
                   open val defPoints: Int,
                   open val matkPoints: Int,
                   open val mdefPoints: Int) : Character(name, health, mp, x, y), normCombatant {
    override fun greet() {
        println("$name has approached!")
    }

    override fun attack(): String {
        return "${name} attacks!"
    }

    override fun magAttack(): String {
        return "${name} used spell"
    }



}

class Boss(override val name: String,
           override val health: Int,
           override val mp: Int,
           override val atkPoints: Int,
           override val defPoints: Int,
           override val matkPoints: Int,
           override val mdefPoints: Int): Monster(
    name,health,atkPoints,defPoints,matkPoints,mdefPoints, mp,matkPoints, mdefPoints), specialCombatant {
    final override fun greet() {
        println("You have encountered $name!")
    }
    override fun attack(): String {
        return ("$name attacks ferociously!")
    }
    override fun defend(): String {
        return ("$name defends!")
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

class playerCharacter(override val name: String,
                      override val health: Int,
                      override val mp: Int,
                      val atkPoints: Int,
                      val defPoints: Int,
                      val matkPoints: Int,
                      val mdefPoints: Int, ): Character(name, health, mp, x, y), normCombatant, specialCombatant {

    override fun greet(){
        println("")
    }
    override fun attack(): String {
        return("$name attacks!")
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

object monsterRepository{
    val goblin = Monster("Goblin",10,20,1,2,2,3, 2,2)
    val foulGhoul = Monster("Foul Ghoul",13,2,5,5,2,3,3,3)
    val wickedSorcerer = Monster("Wicked Sorcerer",10,20,5,5,20,30,20,15)
    val scavenger = Monster("Scavenger", 10, 10, 6,6,20,10,0,0)
    val monsterList = mutableListOf<Monster>().add(goblin)
    //possibly use index from monsterList in a function that randomly pulls an element(monster)
    // so that random monsters appear.
    //example of function -> If ((user x = 2) && (user y =2))
    //          {encounter with monster inRange of monsterList indices}
}



interface normCombatant{
    fun attack(): String
    fun magAttack(): String
}

interface specialCombatant{
    fun defend(): String
    fun magCure()
    fun specialMove()
}

