package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.piece.InitPawn;
import chess.domain.piece.MovedPawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("출발 칸에 기물이 없다면 예외를 발생한다.")
    void emptySourcePositionTest() {
        // given
        Board board = new Board(Map.of());
        Position source = Position.of(File.A, Rank.ONE);
        Position destination = Position.of(File.A, Rank.TWO);
        // when, then
        assertThatThrownBy(() -> board.move(source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("출발 칸에 기물이 없습니다.");
    }

    @Test
    @DisplayName("도착 칸에 자신의 기물이 있다면 예외를 발생한다.")
    void allyPieceOnDestinationTest() {
        // given
        Map<Position, Piece> pieces = Map.of(
                Position.of(File.A, Rank.ONE), new Rook(Color.WHITE),
                Position.of(File.A, Rank.TWO), new Rook(Color.WHITE)
        );
        Board board = new Board(pieces);
        Position source = Position.of(File.A, Rank.ONE);
        Position destination = Position.of(File.A, Rank.TWO);
        // when, then
        assertThatThrownBy(() -> board.move(source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착 칸에 자신의 기물이 있습니다.");
    }

    @Test
    @DisplayName("InitPawn이 이동하면 MovedPawn으로 교체한다.")
    void replaceInitPawnTest() {
        // given
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of(File.A, Rank.TWO), new InitPawn(Color.WHITE));
        Board board = new Board(pieces);
        Position source = Position.of(File.A, Rank.TWO);
        Position destination = Position.of(File.A, Rank.THREE);
        // when
        board.move(source, destination);
        // then
        assertThat(board.pieces().get(destination)).isInstanceOf(MovedPawn.class);
    }

    @Test
    @DisplayName("Pawn이 정면으로 공격하는 경우 예외를 발생한다.")
    void pawnAttackableTest() {
        // given
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of(File.A, Rank.TWO), new InitPawn(Color.WHITE));
        pieces.put(Position.of(File.A, Rank.THREE), new MovedPawn(Color.BLACK));
        Board board = new Board(pieces);
        Position source = Position.of(File.A, Rank.TWO);
        Position destination = Position.of(File.A, Rank.THREE);
        // when, then
        assertThatThrownBy(() -> board.move(source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 경로입니다.");
    }

    @Test
    @DisplayName("경로에 기물이 있는 경우 예외를 발생한다.")
    void pieceOnRouteTest() {
        // given
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of(File.A, Rank.ONE), new Rook(Color.WHITE));
        pieces.put(Position.of(File.A, Rank.TWO), new Rook(Color.WHITE));
        Board board = new Board(pieces);
        Position source = Position.of(File.A, Rank.ONE);
        Position destination = Position.of(File.A, Rank.EIGHT);
        // when, then
        assertThatThrownBy(() -> board.move(source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("경로에 기물이 있습니다.");
    }

    @Test
    @DisplayName("이동할 수 없는 경로가 주어지면 예외를 발생한다.")
    void invalidMoveTest() {
        // given
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of(File.A, Rank.ONE), new Rook(Color.WHITE));
        Board board = new Board(pieces);
        Position source = Position.of(File.A, Rank.ONE);
        Position destination = Position.of(File.B, Rank.THREE);
        // when, then
        assertThatThrownBy(() -> board.move(source, destination))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이동할 수 없는 경로입니다.");
    }

    @Test
    @DisplayName("기물을 이동한다.")
    void moveTest() {
        // given
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of(File.A, Rank.ONE), new Rook(Color.WHITE));
        Board board = new Board(pieces);
        Position source = Position.of(File.A, Rank.ONE);
        Position destination = Position.of(File.A, Rank.TWO);
        // when
        board.move(source, destination);
        // then
        assertThat(board.pieces().containsKey(source)).isFalse();
        assertThat(board.pieces().get(destination)).isInstanceOf(Rook.class);
    }

    @Test
    @DisplayName("적 기물을 공격한다.")
    void attackTest() {
        // given
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of(File.A, Rank.ONE), new Rook(Color.WHITE));
        pieces.put(Position.of(File.A, Rank.TWO), new Rook(Color.BLACK));
        Board board = new Board(pieces);
        Position source = Position.of(File.A, Rank.ONE);
        Position destination = Position.of(File.A, Rank.TWO);
        // when
        board.move(source, destination);
        // then
        Piece actual = board.pieces().get(destination);
        Assertions.assertAll(
                () -> assertThat(board.pieces().containsKey(source)).isFalse(),
                () -> assertThat(actual).isInstanceOf(Rook.class),
                () -> assertThat(actual.hasColorOf(Color.WHITE)).isTrue()
        );
    }
}
