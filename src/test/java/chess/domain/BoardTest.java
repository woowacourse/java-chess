package chess.domain;

import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    private Map<Position, Piece> getEmptySquares() {
        Map<Position, Piece> squares = new HashMap<>();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                squares.put(Position.of(x, y), Empty.INSTANCE);
            }
        }
        return squares;
    }

    @Test
    @DisplayName("말의 이동 경로에 말이 있으면 false가 반환된다.")
    void canMove_With_Collision() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        squares.put(Position.of(3, 3), new Queen(Team.WHITE));
        squares.put(Position.of(3, 4), new Pawn(Team.BLACK));
        Board board = new Board(squares);

        Position source = Position.of(3, 3);
        Position target = Position.of(3, 6);

        // expect
        assertThat(board.canMove(source, target)).isFalse();
    }

    @Test
    @DisplayName("폰을 움직일 때 바로 위에 상대 말이 있으면 false가 반환된다.")
    void canMove_Pawn_Forward_Enemy() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        squares.put(Position.of(3, 3), new Pawn(Team.WHITE));
        squares.put(Position.of(3, 4), new Pawn(Team.BLACK));
        Board board = new Board(squares);

        Position source = Position.of(3, 3);
        Position target = Position.of(3, 4);

        // expect
        assertThat(board.canMove(source, target)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4})
    @DisplayName("폰을 움직일 때 대각선에 상대 말이 없으면 false가 반환된다.")
    void canMove_Pawn_With_Diagonal_Empty(int x) {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        squares.put(Position.of(3, 3), new Pawn(Team.WHITE));
        Board board = new Board(squares);

        Position source = Position.of(3, 3);
        Position target = Position.of(x, 4);

        // expect
        assertThat(board.canMove(source, target)).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4})
    @DisplayName("폰을 움직일 때 대각선에 상대말이 있으면 true가 반환된다.")
    void canMove_Pawn_With_Diagonal_Enemy(int x) {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        Pawn sourcePiece = new Pawn(Team.WHITE);
        squares.put(Position.of(3, 3), sourcePiece);
        squares.put(Position.of(x, 4), new Pawn(Team.BLACK));
        Board board = new Board(squares);

        // when
        Position source = Position.of(3, 3);
        Position target = Position.of(x, 4);

        // then
        assertThat(board.canMove(source, target)).isTrue();
    }

    @Test
    @DisplayName("빈 칸을 움직이면 false가 반환된다.")
    void canMove_EmptySquare() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        Board board = new Board(squares);

        Position source = Position.of(3, 3);
        Position target = Position.of(2, 2);

        // expect
        assertThat(board.canMove(source, target)).isFalse();
    }

    @Test
    @DisplayName("아군 말의 위치로 이동하면 false가 반환된다.")
    void canMove_Same_Team_Position() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        squares.put(Position.of(3, 3), new Pawn(Team.WHITE));
        squares.put(Position.of(3, 4), new Pawn(Team.WHITE));
        Board board = new Board(squares);

        Position source = Position.of(3, 3);
        Position target = Position.of(4, 4);

        // expect
        assertThat(board.canMove(source, target)).isFalse();
    }

    @Test
    @DisplayName("Knight는 이동 경로에 말이 있어도 true가 반환된다.")
    void canMove_Knight_Ignore_Collision() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        Knight knight = new Knight(Team.WHITE);
        squares.put(Position.of(3, 3), knight);
        squares.put(Position.of(2, 3), new Pawn(Team.WHITE));
        squares.put(Position.of(2, 2), new Pawn(Team.BLACK));
        squares.put(Position.of(3, 2), new Pawn(Team.WHITE));
        Board board = new Board(squares);

        Position source = Position.of(3, 3);
        Position target = Position.of(1, 2);

        // when
        assertThat(board.canMove(source, target)).isTrue();
    }

    @Test
    @DisplayName("같은 위치로 움직이면 false가 반환된다.")
    void canMove_Duplicate_Position() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        Board board = new Board(squares);

        Position source = Position.of(2, 2);
        Position target = Position.of(2, 2);

        // expect
        assertThat(board.canMove(source, target)).isFalse();
    }

    @Test
    @DisplayName("체스 시작 점수는 38점이다.")
    void calculate_Initial_Board_Score() {
        // given
        Board board = new Board(BoardFactory.create());
        double expectedScore = 38.0;

        // when
        Double score = board.calculateScore(Team.WHITE);

        // then
        assertThat(score).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("폰이 세로 줄에 2개 이상 있는 경우 각각 0.5점으로 계산한다.")
    void calculate_Score_If_Pawn_Exist_Same_Column() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        squares.put(Position.of(1, 1), new Pawn(Team.WHITE));
        squares.put(Position.of(1, 2), new Pawn(Team.WHITE));
        squares.put(Position.of(1, 3), new Pawn(Team.WHITE));
        squares.put(Position.of(1, 4), new Pawn(Team.WHITE));
        Board board = new Board(squares);

        // expect
        assertThat(board.calculateScore(Team.WHITE)).isEqualTo(2);
    }

}
