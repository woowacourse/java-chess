package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.normal.King;
import chess.domain.piece.normal.Knight;
import chess.domain.piece.normal.Queen;
import chess.domain.piece.normal.Rook;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.property.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    private static final Position A1 = new Position(File.A, Rank.ONE);
    private static final Position A2 = new Position(File.A, Rank.TWO);
    private static final Position A3 = new Position(File.A, Rank.THREE);
    private static final Position A4 = new Position(File.A, Rank.FOUR);
    private static final Position A6 = new Position(File.A, Rank.SIX);
    private static final Position A7 = new Position(File.A, Rank.SEVEN);
    private static final Position E1 = new Position(File.E, Rank.ONE);
    private static final Position F1 = new Position(File.F, Rank.ONE);
    private static final Position F2 = new Position(File.F, Rank.TWO);
    private static final Position G2 = new Position(File.G, Rank.TWO);
    private static final Position F3 = new Position(File.F, Rank.THREE);
    private static final Position H3 = new Position(File.H, Rank.THREE);
    private static final Position F4 = new Position(File.F, Rank.FOUR);
    private static final Position G4 = new Position(File.G, Rank.FOUR);
    private final Board board = Board.initializeBoard();

    @Test
    @DisplayName("자신의 기물이 아닌 것을 움직일 수 없다")
    void cannotMoveOpponent() {
        assertThatThrownBy(() -> board.confirmMove(A7, A6, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("움직일 수 있는");
    }

    @Test
    @DisplayName("빈 칸을 움직일 수 없다")
    void cannotMoveEmpty() {
        assertThatThrownBy(() -> board.confirmMove(A3, A4, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비어있는");
    }

    @Test
    @DisplayName("자신의 기물을 목표로 움직일 수 없다")
    void cannotMoveToOwnPiece() {
        assertThatThrownBy(() -> board.confirmMove(A1, A2, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자신의 기물이");
    }

    @Test
    @DisplayName("유효한 움직임이 아니면 예외가 발생한다")
    void illegalMoveTest() {
        assertThatThrownBy(() -> board.confirmMove(A2, A7, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보드의 점수를 계산한다.")
    void computeScoreTest() {
        //given
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(E1, new Rook(Color.WHITE));
        pieces.put(F1, new King(Color.WHITE));
        pieces.put(F2, new Pawn(Color.WHITE));
        pieces.put(G2, new Pawn(Color.WHITE));
        pieces.put(F3, new Pawn(Color.WHITE));
        pieces.put(H3, new Pawn(Color.WHITE));
        pieces.put(F4, new Knight(Color.WHITE));
        pieces.put(G4, new Queen(Color.WHITE));
        Board board = new Board(pieces);
        //when
        double v = board.computeScore(Color.WHITE);

        //then
        assertThat(v).isEqualTo(19.5d);
    }

    @Test
    @DisplayName("폰은 세로로 여러개가 겹쳐있을 때 0.5점이다.")
    void triplePawnTest() {
        //given
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(A2, new Pawn(Color.WHITE));
        pieces.put(A3, new Pawn(Color.WHITE));
        pieces.put(A4, new Pawn(Color.WHITE));
        Board board = new Board(pieces);
        //when
        double v = board.computeScore(Color.WHITE);
        //then
        assertThat(v).isEqualTo(1.5);
    }

    @Test
    @DisplayName("킹이 남은 쪽이 승리한다.")
    void kingWinTest() {
        //given
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(A1, new King(Color.WHITE));
        pieces.put(A2, new Knight(Color.BLACK));
        Board board = new Board(pieces);
        //when
        Color color = board.computeWinner();
        //then
        assertThat(color).isEqualTo(Color.WHITE);
    }

    @Test
    @DisplayName("킹이 둘다 남았다면 NONE을 반환한다.")
    void kingNoneTest() {
        //given
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(A1, new King(Color.WHITE));
        pieces.put(A2, new King(Color.BLACK));
        Board board = new Board(pieces);
        //when
        Color color = board.computeWinner();
        //then
        assertThat(color).isEqualTo(Color.NONE);
    }
}
