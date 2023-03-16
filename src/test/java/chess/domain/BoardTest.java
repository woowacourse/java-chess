package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Empty;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
    @DisplayName("말의 이동 경로에 말이 있으면 예외가 발생해야 한다.")
    void move_With_Collision() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        squares.put(Position.of(3, 3), new Queen(Team.WHITE));
        squares.put(Position.of(3, 4), new Pawn(Team.BLACK));
        Board board = new Board(squares);

        Position source = Position.of(3, 3);
        Position target = Position.of(3, 6);

        // expect
        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 경로에 말이 있습니다.");
    }

    @Test
    @DisplayName("폰을 움직일 때 바로 위에 상대 말이 있으면 움직일 수 없다.")
    void move_Pawn_Forward_Enemy() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        squares.put(Position.of(3, 3), new Pawn(Team.WHITE));
        squares.put(Position.of(3, 4), new Pawn(Team.BLACK));
        Board board = new Board(squares);
        
        Position source = Position.of(3, 3);
        Position target = Position.of(3, 4);

        // expect
        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 목적지로 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4})
    @DisplayName("폰을 움직일 때 대각선에 상대 말이 없으면 움직일 수 없다.")
    void move_Pawn_With_Diagonal_Empty(int x) {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        squares.put(Position.of(3, 3), new Pawn(Team.WHITE));
        Board board = new Board(squares);
        
        Position source = Position.of(3, 3);
        Position target = Position.of(x, 4);

        // expect
        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 목적지로 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4})
    @DisplayName("폰을 움직일 때 대각선에 상대말이 있으면 움직일 수 있다.")
    void move_Pawn_With_Diagonal_Enemy(int x) {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        Pawn sourcePiece = new Pawn(Team.WHITE);
        squares.put(Position.of(3, 3), sourcePiece);
        squares.put(Position.of(x, 4), new Pawn(Team.BLACK));
        Board board = new Board(squares);

        // when
        Position source = Position.of(3, 3);
        Position target = Position.of(x, 4);
        board.move(source, target);

        // then
        assertThat(squares)
                .containsEntry(target, sourcePiece)
                .containsEntry(source, Empty.INSTANCE);
    }

    @Test
    @DisplayName("빈 칸을 움직이면 예외가 발생해야 한다.")
    void move_EmptySquare() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        Board board = new Board(squares);

        Position source = Position.of(3, 3);
        Position target = Position.of(4, 4);

        // expect
        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 빈 칸은 움직일 수 없습니다.");
    }
    
    @Test
    @DisplayName("아군 말의 위치로 이동하면 예외가 발생해야 한다.")
    void move_Same_Team_Position() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        squares.put(Position.of(3, 3), new Pawn(Team.WHITE));
        squares.put(Position.of(3, 4), new Pawn(Team.WHITE));
        Board board = new Board(squares);

        Position source = Position.of(3, 3);
        Position target = Position.of(4, 4);

        // expect
        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 목적지로 이동할 수 없습니다.");
    }
    
    @Test
    @DisplayName("Knight는 이동 경로에 말이 있어도 움직일 수 있다.")
    void move_Knight_Ignore_Collision() {
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
        board.move(source, target);

        // expect
        assertThat(squares)
                .containsEntry(source, Empty.INSTANCE)
                .containsEntry(target, knight);
    }

    @Test
    @DisplayName("같은 위치로 움직이면 예외가 발생한다.")
    void move_Duplicate_Position() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        Board board = new Board(squares);

        Position source = Position.of(2, 2);
        Position target = Position.of(2, 2);

        // expect
        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 같은 위치로 움직일 수 없습니다.");
    }
}
