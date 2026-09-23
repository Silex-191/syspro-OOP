package blackpackage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayerInitializesWithCorrectDisplayName() {
        Player player = new Player();
        String fullHandString = player.toString();
        assertTrue(fullHandString.contains("Ваши карты"), "The player's display name must be set correctly in the constructor");
    }
}