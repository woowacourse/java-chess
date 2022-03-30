package chess.square;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FileTest {

    @Test
    void availableLocation() {
        assertAll(
                () -> assertThat(File.D.availableLocation(-3)).isTrue(),
                () -> assertThat(File.D.availableLocation(4)).isTrue()
        );
    }

    @Test
    void unAvailableLocation() {
        assertAll(
                () -> assertThat(File.D.availableLocation(-4)).isFalse(),
                () -> assertThat(File.D.availableLocation(5)).isFalse()
        );
    }

    @Test
    void nextTo() {
        assertAll(
                () -> assertThat(File.D.nextTo(-3)).isEqualTo(File.A),
                () -> assertThat(File.D.nextTo(4)).isEqualTo(File.H)
        );
    }

    @Test
    void nextToAssert() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> File.D.nextTo(-4)),
                () -> assertThrows(IllegalArgumentException.class, () -> File.D.nextTo(5))
        );
    }
}
