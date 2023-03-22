package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
        squares.put(Position.of(3, 3), Queen.of(Team.WHITE));
        squares.put(Position.of(3, 4), Pawn.of(Team.BLACK));
        Board board = new Board(squares, Team.WHITE);

        Position source = Position.of(3, 3);
        Position target = Position.of(3, 6);

        // expect
        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 목적지로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("폰을 움직일 때 바로 위에 상대 말이 있으면 움직일 수 없다.")
    void move_Pawn_Forward_Enemy() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        squares.put(Position.of(3, 3), Pawn.of(Team.WHITE));
        squares.put(Position.of(3, 4), Pawn.of(Team.BLACK));
        Board board = new Board(squares, Team.WHITE);
        
        Position source = Position.of(3, 3);
        Position target = Position.of(3, 4);

        // expect
        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 목적지로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("폰을 움직일 때 바로 위에 상대 말이 없으면 움직일 수 있다.")
    void move_Pawn_Forward_Success() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        squares.put(Position.of(3, 3), Pawn.of(Team.WHITE));
        Board board = new Board(squares, Team.WHITE);

        Position source = Position.of(3, 3);
        Position target = Position.of(3, 4);

        // when
        board.move(source, target);

        // expect
        assertThat(squares)
                .containsEntry(target, Pawn.of(Team.WHITE))
                .containsEntry(source, Empty.INSTANCE);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4})
    @DisplayName("폰을 움직일 때 대각선에 상대 말이 없으면 움직일 수 없다.")
    void move_Pawn_With_Diagonal_Empty(int x) {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        squares.put(Position.of(3, 3), Pawn.of(Team.WHITE));
        Board board = new Board(squares, Team.WHITE);
        
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
        Pawn sourcePiece = Pawn.of(Team.WHITE);
        squares.put(Position.of(3, 3), sourcePiece);
        squares.put(Position.of(x, 4), Pawn.of(Team.BLACK));
        Board board = new Board(squares, Team.WHITE);

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
        Board board = new Board(squares, Team.WHITE);

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
        squares.put(Position.of(3, 3), Pawn.of(Team.WHITE));
        squares.put(Position.of(3, 4), Pawn.of(Team.WHITE));
        Board board = new Board(squares, Team.WHITE);

        Position source = Position.of(3, 3);
        Position target = Position.of(3, 4);

        // expect
        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 목적지에 아군 말이 존재합니다.");
    }
    
    @Test
    @DisplayName("Knight는 이동 경로에 말이 있어도 움직일 수 있다.")
    void move_Knight_Ignore_Collision() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        Knight knight = Knight.of(Team.WHITE);
        squares.put(Position.of(3, 3), knight);
        squares.put(Position.of(2, 3), Pawn.of(Team.WHITE));
        squares.put(Position.of(2, 2), Pawn.of(Team.BLACK));
        squares.put(Position.of(3, 2), Pawn.of(Team.WHITE));
        Board board = new Board(squares, Team.WHITE);

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
        Board board = new Board(squares, Team.WHITE);

        Position source = Position.of(2, 2);
        Position target = Position.of(2, 2);

        // expect
        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 같은 위치로 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("나의 턴이 아닌데 말을 움직이면 예외가 발생한다.")
    void move_Invalid_Turn() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        Board board = new Board(squares, Team.BLACK);
        squares.put(Position.of(2, 1), Pawn.of(Team.WHITE));
        squares.put(Position.of(2, 5), Pawn.of(Team.BLACK));

        Position source = Position.of(2, 1);
        Position target = Position.of(2, 3);

        // expect
        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 팀의 턴이 아닙니다.");
    }

    @Test
    @DisplayName("턴을 바꾸면 상대방의 턴으로 넘어가야 한다.")
    void move_Next_Turn() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        Board board = new Board(squares, Team.WHITE);
        squares.put(Position.of(2, 1), Pawn.of(Team.WHITE));
        squares.put(Position.of(2, 5), Pawn.of(Team.BLACK));

        // when
        Position source = Position.of(2, 1);
        Position target = Position.of(2, 3);
        board.changeTurn();

        // expect
        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 해당 팀의 턴이 아닙니다.");
    }

    @Test
    @DisplayName("각 팀별 점수가 정확하게 계산되어야 한다.")
    void getTeamScore_Success() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        squares.put(Position.of(0, 6), Pawn.of(Team.BLACK));
        squares.put(Position.of(1, 5), Pawn.of(Team.BLACK));
        squares.put(Position.of(1, 7), Pawn.of(Team.BLACK));
        squares.put(Position.of(2, 7), Rook.of(Team.BLACK));
        squares.put(Position.of(2, 6), Pawn.of(Team.BLACK));
        squares.put(Position.of(3, 6), Bishop.of(Team.BLACK));
        squares.put(Position.of(4, 5), Queen.of(Team.BLACK));

        squares.put(Position.of(4, 0), Rook.of(Team.WHITE));
        squares.put(Position.of(5, 0), King.of(Team.WHITE));
        squares.put(Position.of(5, 1), Pawn.of(Team.WHITE));
        squares.put(Position.of(5, 2), Pawn.of(Team.WHITE));
        squares.put(Position.of(5, 3), Knight.of(Team.WHITE));
        squares.put(Position.of(6, 1), Pawn.of(Team.WHITE));
        squares.put(Position.of(6, 3), Queen.of(Team.WHITE));
        squares.put(Position.of(7, 2), Pawn.of(Team.WHITE));
        Board board = new Board(squares, Team.WHITE);

        // when
        double blackScore = board.getTeamScore(Team.BLACK);
        double whiteScore = board.getTeamScore(Team.WHITE);

        // then
        assertThat(blackScore)
                .isEqualTo(20);
        assertThat(whiteScore)
                .isEqualTo(19.5);
    }
    
    @ParameterizedTest
    @CsvSource({"1,1","2,1","3,1.5","4,2","5,2.5"})
    @DisplayName("같은 세로줄에 폰이 있으면 각 0.5점씩 계산되어야 한다.")
    void getTeamScore_Same_X_Pawn(int pawnCount, double expect) {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        for (int i = 1; i <= pawnCount; i++) {
            squares.put(Position.of(0, i), Pawn.of(Team.WHITE));
        }
        Board board = new Board(squares, Team.WHITE);
        // when
        double whiteScore = board.getTeamScore(Team.WHITE);

        // then
        assertThat(whiteScore)
                .isEqualTo(expect);
    }

    @Test
    @DisplayName("킹이 도망칠 수 없으면 체크메이트여야 한다.")
    void isCheckmate_Success() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        squares.put(Position.of(3, 7), Pawn.of(Team.BLACK));
        squares.put(Position.of(3, 6), Pawn.of(Team.BLACK));
        squares.put(Position.of(5, 7), Pawn.of(Team.BLACK));
        squares.put(Position.of(5, 6), Pawn.of(Team.BLACK));
        squares.put(Position.of(4, 7), King.of(Team.BLACK));
        squares.put(Position.of(4, 0), Queen.of(Team.WHITE));
        Board board = new Board(squares, Team.WHITE);

        // expect
        assertThat(board.isCheckmate())
                .isTrue();
    }

    @Test
    @DisplayName("킹이 적의 폰의 공격 범위에 있으면 체크여야 한다.")
    void isChecked_Pawn() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        squares.put(Position.of(3, 7), Pawn.of(Team.BLACK));
        squares.put(Position.of(5, 7), Pawn.of(Team.BLACK));
        squares.put(Position.of(5, 6), Pawn.of(Team.BLACK));
        squares.put(Position.of(4, 7), King.of(Team.BLACK));
        squares.put(Position.of(3, 6), Pawn.of(Team.WHITE));
        Board board = new Board(squares, Team.WHITE);

        // expect
        assertThat(board.isChecked())
                .isTrue();
    }

    @Test
    @DisplayName("킹이 적의 폰의 이동 범위에 있으면 체크가 아니여야 한다.")
    void isChecked_PawnMovePosition() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        squares.put(Position.of(3, 7), Pawn.of(Team.BLACK));
        squares.put(Position.of(5, 7), Pawn.of(Team.BLACK));
        squares.put(Position.of(5, 6), Pawn.of(Team.BLACK));
        squares.put(Position.of(4, 7), King.of(Team.BLACK));
        squares.put(Position.of(4, 6), Pawn.of(Team.WHITE));
        Board board = new Board(squares, Team.WHITE);

        // expect
        assertThat(board.isChecked())
                .isFalse();
    }

    @Test
    @DisplayName("킹이 적의 나이트 이동 범위에 있으면 체크여야 한다.")
    void isChecked_KnightMovePosition() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        squares.put(Position.of(3, 7), Pawn.of(Team.BLACK));
        squares.put(Position.of(3, 6), Pawn.of(Team.BLACK));
        squares.put(Position.of(5, 7), Pawn.of(Team.BLACK));
        squares.put(Position.of(5, 6), Pawn.of(Team.BLACK));
        squares.put(Position.of(4, 7), King.of(Team.BLACK));
        squares.put(Position.of(3, 5), Knight.of(Team.WHITE));
        Board board = new Board(squares, Team.WHITE);

        // expect
        assertThat(board.isChecked())
                .isTrue();
    }

    @Test
    @DisplayName("킹이 도망칠 수 있으면 체크메이트가 아니여야 한다.")
    void isCheckmate_KingCanEscape() {
        // given
        Map<Position, Piece> squares = getEmptySquares();
        squares.put(Position.of(3, 7), Pawn.of(Team.BLACK));
        squares.put(Position.of(3, 6), Pawn.of(Team.BLACK));
        squares.put(Position.of(5, 6), Pawn.of(Team.BLACK));
        squares.put(Position.of(4, 7), King.of(Team.BLACK));
        squares.put(Position.of(4, 0), Queen.of(Team.WHITE));
        Board board = new Board(squares, Team.WHITE);

        // expect
        assertThat(board.isCheckmate())
                .isFalse();
    }
}
