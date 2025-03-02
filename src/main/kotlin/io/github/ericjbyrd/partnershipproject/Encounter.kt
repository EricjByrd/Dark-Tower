import io.github.ericjbyrd.partnershipproject.buildLevel
import io.github.ericjbyrd.partnershipproject.playerPosition
import io.github.ericjbyrd.partnershipproject.playerPosition.d
import net.miginfocom.swing.MigLayout
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.text.StyleConstants.setIcon

class Encounter: JPanel(MigLayout("Center")) {
    val levelOne = buildLevel()
    val playerView = JLabel()
    val up = JButton("UP")
    val down = JButton("DOWN")
    val left = JButton("LEFT")
    val right = JButton("RIGHT")

    init {
        var y = 0
        var x = 0
        add(playerView)
        playerView.setIcon(ImageIcon(levelOne[x][y]?.northImagePath))
        add(up)
        add(down)
        add(left)
        add(right)
        up.addActionListener {
            if (levelOne[x][y + 1]?.northImage == null) {
                println("Ouch!")
            } else {
                y = y + 1
                playerView.setIcon(ImageIcon(levelOne[x][y]?.northImagePath))
            }

            down.addActionListener {
                if(y==0){
                    println("Ouch!")
                }
                else if (levelOne[x][y - 1]?.northImage == null){
                    println("Ouch!")
                }
                else {
                    y = y - 1
                    playerView.setIcon(ImageIcon(levelOne[x][y]?.northImagePath))
                }
            }
            left.addActionListener {
                playerView.setIcon(ImageIcon(levelOne[x][y]?.westImagePath))
            }
            right.addActionListener {
                y = y + 1
                playerView.setIcon(ImageIcon(levelOne[x][y]?.eastImagePath))
            }
        }

    }
}