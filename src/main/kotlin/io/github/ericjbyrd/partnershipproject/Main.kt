package io.github.ericjbyrd.partnershipproject

import com.formdev.flatlaf.FlatDarkLaf
import net.miginfocom.swing.MigLayout
import org.slf4j.LoggerFactory
import java.awt.Font
import java.awt.Graphics
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.imageio.ImageIO
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel

class Main : JPanel(MigLayout("ins 4, fill")) {

    private val titleLabel = JLabel(TITLE, JLabel.CENTER).apply {
        font = font.deriveFont(Font.BOLD, 30F)
    }

    private val gameMenu = GameMenu()
    private val descriptionPanel = DescriptionPanel()
    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        g?.drawImage(backgroundImage,0,0, defaultWidth, defaultHeight, this)
    }

    init {
        add(titleLabel, "north")
        add(gameMenu, "grow, sg")
        add(descriptionPanel, "grow, sg")

        gameMenu.allButtons.forEach {
            it.addMouseListener(
                object : MouseAdapter() {
                    override fun mouseEntered(e: MouseEvent?) {
                        val button = e?.component as GameMenu.GameMenuButton?
                        //because component doesn't know we're working with buttons
                        //MouseEvent is null so JButton should be null as well
                        if (button != null) {
                            descriptionPanel.descriptionLabel.text = button.description
                            //left side of = the receiver
                        }
                    }

                    override fun mouseExited(e: MouseEvent?) {
                        descriptionPanel.descriptionLabel.text = ""
                    }

                }
            )
        }



    }

    companion object {
        val defaultWidth = 1000
        val defaultHeight = 600
        val backgroundImage = run {
            val imageStream = this::class.java.getResourceAsStream("/backgroundImage.jpg")
            val image = ImageIO.read(imageStream)
            imageStream?.close()
            return@run image
        }
        private val logger = LoggerFactory.getLogger(Main::class.java)
        const val TITLE = "Dark Tower"

        @JvmStatic

        fun main(args: Array<String>) {
            FlatDarkLaf.setup()
            logger.info("Starting Game: $TITLE")
            JFrame(TITLE).apply {
                contentPane = Main()
                setSize(defaultWidth, defaultHeight)
                setLocationRelativeTo(null)
                isVisible = true
                defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
            }
        }
    }
}



