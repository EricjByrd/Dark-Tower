package io.github.joshlha.partnershipproject

import io.github.joshlha.partnershipproject.Main.Companion.backgroundImage
import net.miginfocom.swing.MigLayout
import java.awt.*
import java.awt.SystemColor.text
import javax.imageio.ImageIO
import javax.swing.*
import javax.swing.text.html.HTML.Attribute.N
import javax.swing.text.html.HTML.Tag.S

class MainGamePanel() : JPanel((MigLayout("debug, fill", "[]10[]", "[][][][]"))) {
    val worldview = WorldViewPanel()
    val buttons = ButtonPanel()
    //val allButtons = listOf(upButton, leftButton, rightButton, downButton)


        // Step 2; Add all the components to the layout
        init {
            isOpaque = false
            add(worldview, "span, grow, wrap")
            //add(upButton, "flowx")
            //add(leftButton,"flowx")
           // add(rightButton, "flowx")
            add(ButtonPanel().upButton, "skip 1, center, wrap")
            add(ButtonPanel().leftButton, " skip 1, sg 1")
            add(ButtonPanel().downButton, "skip 1")
            add(ButtonPanel().rightButton, "sg 1, wrap")

        }

//        exitButton.addActionListener(
//            object : ActionListener{
//                override fun actionPerformed(e: ActionEvent?) {
//                    println("Doing something")
//                }
//
//            }
//        )
            //Simplified version of the code above:

                //N = x+1
                //S = x-1
                //E = y+1
                //W = y-1


//            downButton.addActionListener {
//                //move x or y -1
//            }
//            leftButton.addActionListener {
//                //move x or y -1
//            }
//            rightButton.addActionListener {
//                //move x or y -1
//            }

        }
        // Step 3: Set up event listeners for your components



object playerCoord {
    //this can serve as coordinates AND locations for the map.
    //Idea credited to the brilliant RyiSnow!
    val x: Int = 0
    val y: Int = 0
    val d: List<Char> =  listOf<Char>('N','S','E','W')
}