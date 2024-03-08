import kotlin.test.Test

class FigureCreationTest {
    @Test
    fun testCreationFromString() {
        val str = """
o
o
oo"""

        val figure = Figure.fromString(str)
        println(figure)
    }
}