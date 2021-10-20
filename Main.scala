package goodTetris

@main
def main: Unit =
    val theGame = new Game()
    theGame.initMatrix()
    theGame.gameLoop()
