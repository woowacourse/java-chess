package chess.domain.piece;

import static chess.domain.piece.Fixture.E4;
import static chess.domain.piece.Fixture.KNIGHT_WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {
    @ParameterizedTest
    @CsvSource(value = {"F6,true", "G5,true", "G3,true", "F2,true",
            "D2,true", "C3,true", "C5,true", "D6,true"})
    @DisplayName("나이트는 (1,2) 또는 (2,1) 칸 만큼 이동한다")
    void knightMove(String to, boolean expected) {
        final Board board = BoardFixture.of(E4, KNIGHT_WHITE);
        final boolean move = board.move(E4, Position.from(to));
        assertThat(move).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"C6,false", "G6,false", "G2,false", "C2,false"})
    @DisplayName("나이트는 대각선으로 이동할 수 없다")
    void notMovableDiagonal(String to, boolean expected) {
        final Board board = BoardFixture.of(E4, KNIGHT_WHITE);
        final boolean move = board.move(E4, Position.from(to));
        assertThat(move).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"E6,false", "G4,false", "E2,false", "C4,false"})
    @DisplayName("나이트는 상하좌우로 이동할 수 없다")
    void notMovableVerticalOrHorizontal(String to, boolean expected) {
        final Board board = BoardFixture.of(E4, KNIGHT_WHITE);
        final boolean move = board.move(E4, Position.from(to));
        assertThat(move).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"F6,true", "G5,true", "G3,true", "F2,true",
            "D2,true", "C3,true", "C5,true", "D6,true"})
    @DisplayName("나이트는 이동 경로에 기물이 존재해도 이동한다")
    void movableThroughPieceOnTheWay(String to, boolean expected) {
        final Map<Position, Piece> testBoard = setupSurroundedMap();
        final Board board = BoardFactory.newInstance(testBoard);
        final boolean move = board.move(E4, Position.from(to));
        assertThat(move).isEqualTo(expected);
    }

    private Map<Position, Piece> setupSurroundedMap() {
        final Map<Position, Piece> testBoard = new HashMap<>();
        testBoard.put(Position.from("E5"), KNIGHT_WHITE);
        testBoard.put(Position.from("F5"), KNIGHT_WHITE);
        testBoard.put(Position.from("F4"), KNIGHT_WHITE);
        testBoard.put(Position.from("F3"), KNIGHT_WHITE);
        testBoard.put(Position.from("E3"), KNIGHT_WHITE);
        testBoard.put(Position.from("D3"), KNIGHT_WHITE);
        testBoard.put(Position.from("D4"), KNIGHT_WHITE);
        testBoard.put(Position.from("D5"), KNIGHT_WHITE);
        testBoard.put(E4, KNIGHT_WHITE);
        return testBoard;
    }

    @Test
    @DisplayName("나이트는 2.5점으로 계산된다")
    void getScore() {
        final Piece knight = KNIGHT_WHITE;
        final double score = knight.getScore();
        assertThat(score).isEqualTo(2.5);
    }
}
