package io.github.ericjbyrd.partnershipproject


import jdk.internal.org.jline.utils.Colors.s
import net.miginfocom.swing.MigLayout
import java.awt.GridLayout
import java.io.File
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel


class DungeonEditor : JPanel(MigLayout("debug")) {
    val item = testLabel()
    val path = "src/main/resources/mapSourceFiles/background.png"
    val image = ImageIO.read(File("src/main/resources/mapSourceFiles/background.png"))

    val button0 = JButton("X0Y0")
    val button1 = JButton("X0Y1")
    val button2 = JButton()
    val button3 = JButton()
    val button4 = JButton()
    val button5 = JButton()
    val button6 = JButton()
    val button7 = JButton()
    val button8 = JButton()
    val button9 = JButton()
    val button10 = JButton()
    val button11 = JButton()
    val button12 = JButton()
    val button13 = JButton()
    val coordButtons = listOf(button0,button1,button2,button3,button4,button5,button6,button7,button8,button9,
        button10,button11,button12,button13)

    init{
    item.setIcon(ImageIcon(path))
    item.graphics.drawImage(image,0,0,item)
    add(item)


    }
}




