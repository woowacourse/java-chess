package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(BoardFactory.getInitialPieces());
    }

    @Test
    @DisplayName("선택한 Position에 말이 없으면, 예외를 발생시킨다")
    void notExistPieceInPosition() {
        Position fromPosition = Position.valueOf(Abscissa.a, Ordinate.THREE);
        Position toPosition = Position.valueOf(Abscissa.a, Ordinate.FOUR);
        assertThatThrownBy(() -> board.movePiece(fromPosition, toPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 입력한 위치에 말이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("말을 이동시키고, 이동한 위치가 보드에 존재하면 True를 반환")
    void movePieceAndCheckExistPosition() {
        Position fromPosition = Position.valueOf(Abscissa.a, Ordinate.TWO);
        Position toPosition = Position.valueOf(Abscissa.a, Ordinate.THREE);

        board.movePiece(fromPosition, toPosition);

        assertThat(board.getBoard().containsKey(toPosition)).isTrue();
    }

    @Test
    @DisplayName("말을 이동시키고, 이동하기 전 위치가 보드에 존재하지 않는지 확인")
    void movePieceAndCheckNotExistPosition() {
        Position fromPosition = Position.valueOf(Abscissa.a, Ordinate.TWO);
        Position toPosition = Position.valueOf(Abscissa.a, Ordinate.THREE);

        board.movePiece(fromPosition, toPosition);

        assertThat(board.getBoard().containsKey(fromPosition)).isFalse();
    }

    @Test
    @DisplayName("말이 움직일 수 없는 위치일 때 예외를 발생시킨다")
    void nonMovablePositionException() {
        Position fromPosition = Position.valueOf(Abscissa.a, Ordinate.TWO);
        Position toPosition = Position.valueOf(Abscissa.b, Ordinate.THREE);

        assertThatThrownBy(() -> board.movePiece(fromPosition, toPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 해당 위치는 말이 움직일 수 없습니다.");
    }

    @Test
    @DisplayName("킹이 두 개 다 있는지 체크한다")
    void checkKings() {
        assertThat(board.isAllKingExist()).isTrue();
    }

    @Test
    @DisplayName("킹이 2 개가 존재하지 않을 경우 False를 반환한다.")
    void checkKingCount() {
        Map<Position, Piece> initialPieces = new HashMap<>();
        initialPieces.put(Position.valueOf(Abscissa.a, Ordinate.ONE), new King(Color.WHITE));
        Board testBoard = new Board(initialPieces);
        assertThat(testBoard.isAllKingExist()).isFalse();
    }

    @Test
    @DisplayName("보드의 상황에 따라 점수를 잘 계산하는지 확인한다")
    void calculateScore() {
        double blackTotalScore = board.calculateScore(Color.BLACK);
        double whiteTotalScore = board.calculateScore(Color.WHITE);

        assertThat(blackTotalScore).isEqualTo(38);
        assertThat(whiteTotalScore).isEqualTo(38);
    }

    @Test
    @DisplayName("보드의 상황에 따라 점수를 잘 계산하는지 확인한다")
    void calculatePawnSameRowScore() {
        Map<Position, Piece> initialPieces = new HashMap<>();
        initialPieces.put(Position.valueOf(Abscissa.a, Ordinate.ONE), new Pawn(Color.WHITE));
        initialPieces.put(Position.valueOf(Abscissa.a, Ordinate.TWO), new Pawn(Color.WHITE));

        Board testBoard = new Board(initialPieces);

        assertThat(testBoard.calculateScore(Color.WHITE)).isEqualTo(1);
    }

    @Test
    @DisplayName("보드의 상황에 따라 점수를 잘 계산하는지 확인한다")
    void calculatePawnSameRowScore2() {
        Map<Position, Piece> initialPieces = new HashMap<>();
        initialPieces.put(Position.valueOf(Abscissa.a, Ordinate.ONE), new Pawn(Color.WHITE));
        initialPieces.put(Position.valueOf(Abscissa.a, Ordinate.TWO), new Pawn(Color.WHITE));
        initialPieces.put(Position.valueOf(Abscissa.b, Ordinate.ONE), new Pawn(Color.WHITE));
        initialPieces.put(Position.valueOf(Abscissa.b, Ordinate.TWO), new Pawn(Color.WHITE));
        initialPieces.put(Position.valueOf(Abscissa.b, Ordinate.FOUR), new Pawn(Color.WHITE));
        initialPieces.put(Position.valueOf(Abscissa.c, Ordinate.TWO), new Pawn(Color.WHITE));

        Board testBoard = new Board(initialPieces);

        assertThat(testBoard.calculateScore(Color.WHITE)).isEqualTo(3.5);
    }
}
