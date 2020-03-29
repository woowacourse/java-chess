package chess.domain.piece.implementation;

import chess.domain.board.BoardState;
import chess.domain.piece.PieceDto;
import chess.domain.piece.PieceState;
import chess.domain.piece.PieceType;
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

class PawnTest {

    private PieceState whitePawn;
    private PieceState blackPawn;
    private BoardState boardState;
    private Map<Position, PieceDto> boardDto;
    private PieceDto whitePiece = new PieceDto(PieceType.PAWN, Team.WHITE);
    private PieceDto blackPiece = new PieceDto(PieceType.PAWN, Team.BLACK);

    @BeforeEach
    void setUp() {
        whitePawn = Pawn.of(Position.of("B2"), Team.WHITE);
        blackPawn = Pawn.of(Position.of("A7"), Team.BLACK);
        boardDto = new HashMap<>();
        boardState = BoardState.of(boardDto);
    }

    @Test
    @DisplayName("Pawn은 바로 앞에 기물이 있는 경우 전진할 수 없음")
    void moveToAlly() {
        boardDto.put(Position.of("B4"), whitePiece);
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whitePawn.move(Position.of("B4"), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("직선으로 진행할 때 진행 타겟에 적군이 있는 경우 예외 발생")
    void frontMoveToEnemy() {
        boardDto.put(Position.of("B4"), blackPiece);
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whitePawn.move(Position.of("B4"), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("직선 진행 타겟에 아무것도 없는 경우 이동 가능")
    void moveToEmpty() {
        assertThat(whitePawn.move(Position.of("B4"), boardState))
                .isInstanceOf(Pawn.class);
    }

    @Test
    @DisplayName("대각선으로 진행할 때 진행 타겟에 적군이 있는 경우 이동 가능")
    void diagonalMoveToEnemy() {
        boardDto.put(Position.of("C3"), blackPiece);
        boardState = BoardState.of(boardDto);
        assertThat(whitePawn.move(Position.of("C3"), boardState))
                .isInstanceOf(Pawn.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"C4", "D5", "A4"})
    @DisplayName("진행 규칙에 어긋나는 경우 예외 발생")
    void movePolicyException(String input) {
        assertThatThrownBy(() -> whitePawn.move(Position.of(input), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException() {
        boardDto.put(Position.of("D4"), blackPiece);
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whitePawn.move(Position.of("D4"), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"B3", "B4"})
    @DisplayName("직선으로 진행할 때 진행 타겟에 적군이 있는 경우 예외 발생")
    void frontMoveToEnemy(String target) {
        boardDto.put(Position.of(target), blackPiece);
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whitePawn.move(Position.of(target), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("직선으로 2칸 진행할 때 진행 경로에 적군이 있는 경우")
    void frontMoveObstacle() {
        boardDto.put(Position.of("B3"), blackPiece);
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whitePawn.move(Position.of("B4"), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"B3", "B4"})
    @DisplayName("직선 진행 타겟에 아무것도 없는 경우 이동 가능")
    void moveToEmpty(String target) {
        assertThat(whitePawn.move(Position.of(target), boardState))
                .isInstanceOf(Pawn.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"D6", "A5"})
    @DisplayName("진행 타겟에 적군이 있지만 진행 규칙에 어긋나는 경우 예외 발생")
    void moveToEnemyException(String target) {
        boardDto.put(Position.of(target), blackPiece);
        boardState = BoardState.of(boardDto);
        assertThatThrownBy(() -> whitePawn.move(Position.of(target), boardState))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
