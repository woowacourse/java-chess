package chess.domain.piece;

import chess.domain.player.Player;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KnightTest {

    private PieceState whiteKnight;
    private Map<Position, PieceDto> boardDto;

    @BeforeEach
    void setUp() {
        whiteKnight = Knight.of(Position.of("b1"), Player.WHITE);
        boardDto = new HashMap<>();
    }

    @Test
    @DisplayName("진행 타켓에 우리편이 있는 경우 예외 발생")
    void moveToAlly() {
        boardDto.put(Position.of("C3"), new PieceDto(Player.WHITE));
        assertThatThrownBy(() -> whiteKnight.move(Position.of("C3"), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("아군의 말 위치로는 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("진행 타겟에 적군이 있는 경우 이동 가능")
    void moveToEnemy() {
        boardDto.put(Position.of("C3"), new PieceDto(Player.BLACK));
        assertThat(whiteKnight.move(Position.of("C3"), boardDto))
                .isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("진행 타겟에 아무것도 없는 경우 이동 가능")
    void moveToEmpty() {
        assertThat(whiteKnight.move(Position.of("C3"), boardDto))
                .isInstanceOf(Knight.class);
    }

    @Test
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException() {
        assertThatThrownBy(() -> whiteKnight.move(Position.of("C4"), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("움직일 수 없는 positon입니다.");
    }

    @Test
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException() {
        boardDto.put(Position.of("C4"), new PieceDto(Player.BLACK));
        assertThatThrownBy(() -> whiteKnight.move(Position.of("C4"), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("움직일 수 없는 positon입니다.");
    }
}
