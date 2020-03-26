package chess.domain.piece;

import chess.domain.BoardState;
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
    private BoardState boardState;
    private Map<Position, PieceDto> boardDto;

    @BeforeEach
    void setUp() {
        whiteMovedPawn = MovedPawn.of(Position.of("B3"), Player.WHITE);
        boardDto = new HashMap<>();
        boardState = BoardState.of(boardDto);
    }

    @Test
    @DisplayName("Pawn은 바로 앞에 기물이 있는 경우 전진할 수 없음")
    void moveToAlly() {
        boardDto.put(Position.of("B4"), new PieceDto(Player.WHITE));
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of("B4"), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 장애물이 있습니다.");
    }

    @Test
    @DisplayName("직선으로 진행할 때 진행 타겟에 적군이 있는 경우 예외 발생")
    void frontMoveToEnemy() {
        boardDto.put(Position.of("B4"), new PieceDto(Player.BLACK));
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of("B4"), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 장애물이 있습니다.");
    }

    @Test
    @DisplayName("직선 진행 타겟에 아무것도 없는 경우 이동 가능")
    void moveToEmpty() {
        assertThat(whiteMovedPawn.move(Position.of("B4"), boardState))
                .isInstanceOf(MovedPawn.class);
    }

    @Test
    @DisplayName("대각선으로 진행할 때 진행 타겟에 적군이 있는 경우 이동 가능")
    void diagonalMoveToEnemy() {
        boardDto.put(Position.of("C4"), new PieceDto(Player.BLACK));
        boardState = BoardState.of(boardDto);
        assertThat(whiteMovedPawn.move(Position.of("C4"), boardState))
                .isInstanceOf(MovedPawn.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"C4", "D5", "A4"})
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException(String input) {
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of(input), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 이동 방향입니다.");
    }

    @Test
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException() {
        boardDto.put(Position.of("D4"), new PieceDto(Player.BLACK));
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of("D4"), boardState))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 이동 방향입니다.");
    }
}