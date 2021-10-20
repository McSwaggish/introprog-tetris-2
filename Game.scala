package goodTetris

class Game() {
    def initMainBorderTemplate(): Unit =
        val window = new RenderWindow()
            for i <- 0 until 10 do
                window.drawBorder(i + 11, 1, 2)
                window.drawBorder(i + 11, 22, 3)
            for i <- 0 until 20 do
                window.drawBorder(10, i + 2, 0)
                window.drawBorder(21, i + 2, 1)
            
            for(i <- 0 until 10;
                j <- 0 until 20) do
                window.clear(i + 11, j + 2)
}