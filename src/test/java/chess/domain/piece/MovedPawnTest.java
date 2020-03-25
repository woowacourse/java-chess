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

class MovedPawnTest {

    private PieceState whiteMovedPawn;
    private Map<Position, PieceDto> boardDto;

    @BeforeEach
    void setUp() {
        whiteMovedPawn = MovedPawn.of(Position.of("B3"), Player.WHITE);
        boardDto = new HashMap<>();
    }

    @Test
    @DisplayName("진행 타겟에 우리편이 있는 경우 예외 발생")
    void moveToAlly() {
        boardDto.put(Position.of("B4"), new PieceDto(Player.WHITE));
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of("B4"), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("아군의 말 위치로는 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("직선으로 진행할 때 진행 타겟에 적군이 있는 경우 예외 발생")
    void frontMoveToEnemy() {
        boardDto.put(Position.of("B4"), new PieceDto(Player.BLACK));
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of("B4"), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("움직일 수 없는 positon입니다.");
    }

    @Test
    @DisplayName("대각선으로 진행할 때 진행 타겟에 적군이 있는 경우 이동 가능")
    void diagonalMoveToEnemy() {
        boardDto.put(Position.of("C4"), new PieceDto(Player.BLACK));
        assertThat(whiteMovedPawn.move(Position.of("C4"), boardDto))
                .isInstanceOf(MovedPawn.class);
    }

    @Test
    @DisplayName("직선 진행 타겟에 아무것도 없는 경우 이동 가능")
    void moveToEmpty() {
        assertThat(whiteMovedPawn.move(Position.of("B4"), boardDto))
                .isInstanceOf(MovedPawn.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"C4", "B5", "A4"})
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException(String input) {
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of(input), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("움직일 수 없는 positon입니다.");
    }

    @Test
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException() {
        boardDto.put(Position.of("D4"), new PieceDto(Player.BLACK));
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of("D4"), boardDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("움직일 수 없는 positon입니다.");
    }
}