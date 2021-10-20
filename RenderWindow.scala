package goodTetris

import javax.swing.event.DocumentEvent.EventType

class RenderWindow(
    windowSize: (Int, Int) = (1280, 900),
    blockSize:  Int        = 40 //need some extra for borders
) {
    import introprog.*

    val window = new PixelWindow(windowSize._1, windowSize._2, "TETRIS TIME", java.awt.Color(0xf1, 0xf1, 0xf1))
    window.show()

    def draw(x: Int, y: Int, colour: java.awt.Color): Unit =
        window.fill(x * blockSize - 1, y * blockSize - 1, blockSize + 2, blockSize + 2, java.awt.Color(0x1d, 0x1d, 0x1d))
        window.fill(x * blockSize + 1, y * blockSize + 1, blockSize - 2, blockSize - 2, colour)

    def clear(x: Int, y: Int): Unit =
        window.fill(x * blockSize, y * blockSize, blockSize, blockSize, java.awt.Color(0xbe, 0xbe, 0xbe))
        window.fill(x * blockSize + 1, y * blockSize + 1, blockSize - 2, blockSize - 2, java.awt.Color(0xff, 0xff, 0xff))

    def drawBorder(x: Int, y: Int, rotation: Int): Unit =
        val borderThickness = 3
        var xo  = 0 //x offset
        var yo  = 0 //y offset
        var xsi = 0 //x size
        var ysi = 0 //y size
        if rotation == 0 then      //BORDER ON LEFT
            xsi = -borderThickness
        else if rotation == 1 then //BORDER ON RIGHT
            xo  = borderThickness; xsi = -borderThickness
        else if rotation == 2 then //BORDER ON BOT
            ysi = -borderThickness
        else if rotation == 3 then //BORDER ON TOP
            yo  = borderThickness; ysi = -borderThickness

        window.fill(x * blockSize, y * blockSize, blockSize, blockSize, java.awt.Color(0x1d, 0x1d, 0x1d))
        window.fill(x * blockSize + xo, y * blockSize + yo, blockSize + xsi, blockSize + ysi, java.awt.Color(0xf1, 0xf1, 0xf1))

        //DRAW CORNERS
        window.fill(437, 77, 3, 3, java.awt.Color(0x1d, 0x1d, 0x1d))  //top left corner
        window.fill(840, 77, 3, 3, java.awt.Color(0x1d, 0x1d, 0x1d))  //top right corner
        window.fill(437, 880, 3, 3, java.awt.Color(0x1d, 0x1d, 0x1d)) //bot left corner
        window.fill(840, 880, 3, 3, java.awt.Color(0x1d, 0x1d, 0x1d)) //bot right corner

    def typeWriter(text: String, x: Int, y: Int, size: Int): Unit =
        window.drawText(text, x, y, java.awt.Color(0x67, 0x67, 0x67), size)

    def nextEvent(): RenderWindow.Event.EventType =
        import RenderWindow.Event._
        window.awaitEvent(10)
        window.lastEventType match
            case PixelWindow.Event.KeyPressed   => KeyPressed(window.lastKey)
            case PixelWindow.Event.WindowClosed => WindowClosed
            case _                              => Undefined
}

object RenderWindow {
    object Event:
        trait EventType
        case class  KeyPressed(key: String) extends EventType
        case object WindowClosed            extends EventType
        case object Undefined               extends EventType
}