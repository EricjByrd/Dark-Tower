package io.github.joshlha.partnershipproject

import io.github.joshlha.partnershipproject.Main.Companion.backgroundImage
import net.miginfocom.swing.MigLayout
import java.awt.Graphics
import javax.imageio.ImageIO
import javax.swing.JLabel
import javax.swing.JPanel

class WorldViewPanel() : JLabel() {
    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        g?.drawImage(backgroundImage, 0, 0, 1000, 600, this)


    }

    companion object {
        val imageName: String = "/backgroundImage.jpg"
        val backgroundImage = run {
            val imageStream = this::class.java.getResourceAsStream(imageName)
            val image = ImageIO.read(imageStream)
            imageStream?.close()
            return@run image
        }
    }
}