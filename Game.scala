package goodTetris

object Game {
    object Variables {
        val matrixOffsetX: Int     = 11
        val matrixOffsetY: Int     = 2
        var gameOff:       Boolean = false
        val frameLength:   Int     = 200
    }

    object Color {
        import java.awt.Color as JavaMegaGay
        val black  = JavaMegaGay(0x00, 0x00, 0x00) //also useless, read bottom first
        val yellow = JavaMegaGay(0xff, 0xe4, 0x24) //O piece - 1
        val cyan   = JavaMegaGay(0x00, 0xb6, 0xe3) //I pice  - 2
        val blue   = JavaMegaGay(0x00, 0x60, 0xcf) //J piece - 3
        val orange = JavaMegaGay(0xff, 0xa5, 0x00) //L piece - 4
        val red    = JavaMegaGay(0xf0, 0x14, 0x00) //Z piece - 5
        val green  = JavaMegaGay(0x00, 0xc5, 0x2e) //S piece - 6
        val purple = JavaMegaGay(0xa6, 0x18, 0xc2) //T piece - 7
        val cyan2  = JavaMegaGay(0x33, 0xaa, 0xff) //fucking useless
    }

    def getColour(colourIndex: Int): java.awt.Color = {
        colourIndex match {
            case 0 => Color.black
            case 1 => Color.yellow
            case 2 => Color.cyan
            case 3 => Color.blue
            case 4 => Color.orange
            case 5 => Color.red
            case 6 => Color.green
            case 7 => Color.purple
            case _ => Color.black
        }
    }
}

class Game() {
    import Game.*
    import Game.Variables.*
    val window = new RenderWindow()
    val matrix: Array[Array[Int]] = Array.ofDim[Int](23, 10)
    window.window.show()
    window.window.setPosition(200, 30)

    //init matrix
    for(i <- 0 until matrix.length;
        j <- 0 until matrix(0).length)
        matrix(i)(j) = 0




    def drawFalling(): Unit =
        for(i <- 0 until Tetromino.current.length;
            j <- 0 until Tetromino.current(0).length)
            if Tetromino.current(i)(j) != 0 then
                window.draw(j + Tetromino.offSet._1 + matrixOffsetX, i + Tetromino.offSet._2 + matrixOffsetY, getColour(Tetromino.current(i)(j)))

    def drawLanded(): Unit =
        //two loops bc frame of coloured blocks sticks out and get covered by window.clear() otherwise
        for(i <- 0 until matrix.length - 3;
            j <- 0 until 10)
            if matrix(i)(j) == 0 then
                window.clear(j + matrixOffsetX, i + matrixOffsetY)
        for(i <- 0 until matrix.length - 3;
            j <- 0 until 10)
            if matrix(i)(j) != 0 then
                window.draw(j + matrixOffsetX, i + matrixOffsetY, getColour(matrix(i)(j)))
            

    def initMatrix(): Unit =
            for i <- 0 until 10 do
                window.drawBorder(i + matrixOffsetX, 1, 2)
                window.drawBorder(i + matrixOffsetX, 22, 3)
            for i <- 0 until 20 do
                window.drawBorder(10, i + matrixOffsetY, 0)
                window.drawBorder(21, i + matrixOffsetY, 1)
            drawLanded()

    def gameLoop(): Unit =
        while(!gameOff){
            val t0 = System.currentTimeMillis

            drawFalling()

            val deltaTime = System.currentTimeMillis - t0
            Thread.sleep((frameLength - deltaTime.toInt) max 0)
        }
}