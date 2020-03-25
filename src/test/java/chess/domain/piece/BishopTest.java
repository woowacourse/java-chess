package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {
    private PieceState whiteBishop;
    private Map<Position, PieceDto> boardDto;

    @BeforeEach
    void setUp() {
        whiteBishop = Bishop.of(Position.of("C4"), Player.WHITE);
        boardDto = new HashMap<>();
    }

    @ParameterizedTest
    @ValueSource(strings = {"A2", "B3", "D5", "E6", "F7", "G8"})
    @DisplayName("진행 경로에 아무것도 없는 경우 이동 가능")
    void moveToEmpty(String target) {
        assertThat(whiteBishop.move(Position.of(target), boardDto))
                .isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("진행 타겟에 우리편이 있는 경우 예외 발생")
    void moveToAlly() {
        boardDto.put(Position.of("G8"), new PieceDto(Player.WHITE));
        assertThatThrownBy(() -> whiteBishop.move(Position.of("G8"), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("아군의 말 위치로는 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("진행 경로에 우리편이 있는 경우 예외 발생")
    void allyOnPath() {
        boardDto.put(Position.of("E6"), new PieceDto(Player.WHITE));
        assertThatThrownBy(() -> whiteBishop.move(Position.of("G8"), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("움직일 수 없는 positon입니다.");
    }

    @Test
    @DisplayName("진행 타겟에 적군이 있는 경우 이동 가능")
    void moveToEnemy() {
        boardDto.put(Position.of("G8"), new PieceDto(Player.BLACK));
        assertThat(whiteBishop.move(Position.of("G8"), boardDto))
                .isInstanceOf(Bishop.class);
    }

    @Test
    @DisplayName("진행 경로에 적군이 있는 경우 예외 발생")
    void enemyOnPath() {
        boardDto.put(Position.of("E6"), new PieceDto(Player.BLACK));
        assertThatThrownBy(() -> whiteBishop.move(Position.of("G8"), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("움직일 수 없는 positon입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"A1", "B2", "D4"})
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException(String input) {
        assertThatThrownBy(() -> whiteBishop.move(Position.of(input), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("움직일 수 없는 positon입니다.");
    }

    @Test
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException() {
        boardDto.put(Position.of("A1"), new PieceDto(Player.BLACK));
        assertThatThrownBy(() -> whiteBishop.move(Position.of("A1"), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("움직일 수 없는 positon입니다.");
    }


}