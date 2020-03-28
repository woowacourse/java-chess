package chess.domain.piece.implementation;

import chess.domain.board.BoardState;
import chess.domain.piece.PieceDto;
import chess.domain.piece.PieceState;
import chess.domain.player.Team;
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

class NotMovedPawnTest {

    private PieceState whiteMovedPawn;
    private BoardState boardState;
    private Map<Position, PieceDto> boardDto;

    @BeforeEach
    void setUp() {
        whiteMovedPawn = NotMovedPawn.of(Position.of("B3"), Team.WHITE);
        boardDto = new HashMap<>();
        boardState = BoardState.of(boardDto);
    }

    @Test
    @DisplayName("진행 타겟에 우리편이 있는 경우 예외 발생")
    void moveToAlly() {
        boardDto.put(Position.of("B4"), new PieceDto(Team.WHITE));
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of("B4"), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"B4", "B5"})
    @DisplayName("직선으로 진행할 때 진행 타겟에 적군이 있는 경우 예외 발생")
    void frontMoveToEnemy(String target) {
        boardDto.put(Position.of(target), new PieceDto(Team.BLACK));
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of(target), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("직선으로 2칸 진행할 때 진행 경로에 적군이 있는 경우")
    void frontMoveObstacle() {
        boardDto.put(Position.of("B4"), new PieceDto(Team.BLACK));
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of("B5"), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"B4", "B5"})
    @DisplayName("직선 진행 타겟에 아무것도 없는 경우 이동 가능")
    void moveToEmpty(String target) {
        assertThat(whiteMovedPawn.move(Position.of(target), boardState))
                .isInstanceOf(MovedPawn.class);
    }

    @Test
    @DisplayName("대각선으로 진행할 때 진행 타겟에 적군이 있는 경우 이동 가능")
    void diagonalMoveToEnemy() {
        boardDto.put(Position.of("C4"), new PieceDto(Team.BLACK));
        boardState = BoardState.of(boardDto);
        assertThat(whiteMovedPawn.move(Position.of("C4"), boardState))
                .isInstanceOf(MovedPawn.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"C4", "D5", "A4"})
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException(String input) {
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of(input), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"D6", "A5"})
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException(String target) {
        boardDto.put(Position.of(target), new PieceDto(Team.BLACK));
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whiteMovedPawn.move(Position.of(target), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
