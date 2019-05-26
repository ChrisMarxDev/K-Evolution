import kevolution.extensions.getGradientChance
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ListUtilityTest {

    @Test
    fun testGradientChance() {
        Assertions.assertEquals(1.0, 0.getGradientChance(10, 2.0))
        Assertions.assertEquals(0.25, 5.getGradientChance(10, 2.0))
        Assertions.assertEquals(0.0, 10.getGradientChance(10, 2.0))

    }
}