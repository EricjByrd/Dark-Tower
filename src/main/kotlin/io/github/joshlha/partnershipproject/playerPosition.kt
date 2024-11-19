package io.github.joshlha.partnershipproject

import javax.swing.text.html.HTML.Attribute.N

object playerPosition {
    //this can serve as coordinates AND locations for the map.
    //Idea credited to the brilliant RyiSnow!
    var x: Int = 0
    var y: Int = 0
    var d: Direction = Direction.N
    enum class Direction{
        N,S,E,W
    }
    val coordinates= "X:${x},Y:${y},D:${d}"
    val directions: List<Char> =  listOf<Char>('N','S','E','W')
    fun printPlayerCoords(){
        println("X:${x},Y:${y},D:${d}")
    }
}
