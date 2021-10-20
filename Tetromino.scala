package goodTetris

object Tetromino {
    val spawnOffset: (Int, Int) = (3, 0)
    var offSet:      (Int, Int) = spawnOffset

    def O_tet(rotation: Int): Array[Array[Int]] = {
        if rotation == 0 then Array(
            Array(0, 1, 1, 0),
            Array(0, 1, 1, 0),
            Array(0, 0, 0, 0),
            Array(0, 0, 0, 0)
        )
        else Array(
            Array(0, 1, 1, 0),
            Array(0, 1, 1, 0),
            Array(0, 0, 0, 0),
            Array(0, 0, 0, 0)
        )
    }

    def current: Array[Array[Int]] =
        O_tet(0)

    


    
}