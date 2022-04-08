package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameSwitchTest {

    private GameSwitch gameSwitch;

    @BeforeEach
    void setUp() {
        gameSwitch = new GameSwitch(true);
    }

    @Test
    @DisplayName("GameSwitch 를 끈다.")
    void turnOff() {
        gameSwitch.turnOff();

        assertThat(gameSwitch.isOn()).isFalse();
    }

    @Test
    @DisplayName("GameSwitch 가 켜져있는지 확인한다.")
    void isOn() {
        assertThat(gameSwitch.isOn()).isTrue();
    }
}