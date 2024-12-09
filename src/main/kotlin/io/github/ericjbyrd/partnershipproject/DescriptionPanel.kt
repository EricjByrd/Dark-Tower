package io.github.ericjbyrd.partnershipproject

import net.miginfocom.swing.MigLayout
import java.awt.Font
import javax.swing.JLabel
import javax.swing.JPanel

class DescriptionPanel : JPanel(MigLayout("fill, aligny center")) {

    val descriptionLabel = JLabel("").apply {
        font = font.deriveFont(Font.BOLD, 20F)
        horizontalAlignment = JLabel.CENTER
    }

    init {
        isOpaque = false
        add(descriptionLabel, "growx, growy")
    }
}