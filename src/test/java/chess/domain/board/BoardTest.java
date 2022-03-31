package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.piece.Article;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.attribute.Color;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("체스판 중 Pawn을 제외한 기물을 생성할 수 있다.")
    void createBoardOfExcludePawn() {
        final Map<Position, Article> pawnExceptBoard = Map.of(
                Position.of("a", "1"), new Rook(Color.WHITE),
                Position.of("b", "1"), new Knight(Color.WHITE),
                Position.of("c", "1"), new Bishop(Color.WHITE),
                Position.of("d", "1"), new Queen(Color.WHITE),
                Position.of("e", "1"), new King(Color.WHITE),
                Position.of("f", "1"), new Bishop(Color.WHITE),
                Position.of("g", "1"), new Knight(Color.WHITE),
                Position.of("h", "1"), new Rook(Color.WHITE)
        );

        assertDoesNotThrow(() -> new ChessBoard(pawnExceptBoard));
    }

    @Test
    @DisplayName("체스판 중 Pawn 기물을 생성할 수 있다.")
    void createBoardOfPawn() {
        final Map<Position, Article> pawnExceptBoard = Map.of(
                Position.of("a", "2"), new Pawn(Color.WHITE),
                Position.of("b", "2"), new Pawn(Color.WHITE),
                Position.of("c", "2"), new Pawn(Color.WHITE),
                Position.of("d", "2"), new Pawn(Color.WHITE),
                Position.of("e", "2"), new Pawn(Color.WHITE),
                Position.of("f", "2"), new Pawn(Color.WHITE),
                Position.of("g", "2"), new Pawn(Color.WHITE),
                Position.of("h", "2"), new Pawn(Color.WHITE)
        );

        assertDoesNotThrow(() -> new ChessBoard(pawnExceptBoard));
    }

    @Test
    @DisplayName("체스판의 말을 이동할 수 있다.")
    void movePiece() {
        ChessBoard chessBoard = new ChessBoard(articleDefaultMap());

        chessBoard.movePiece(new Position(File.A, Rank.TWO), new Position(File.A, Rank.THREE));
        chessBoard.movePiece(new Position(File.A, Rank.ONE), new Position(File.A, Rank.TWO));

        assertAll(
                () -> assertThat(chessBoard.findByPiece(new Position(File.A, Rank.THREE))).isInstanceOf(Pawn.class),
                () -> assertThat(chessBoard.findByPiece(new Position(File.A, Rank.TWO))).isInstanceOf(Rook.class)
        );
    }

    @Test
    @DisplayName("체스판의 말이 이동할 수 없는 경우 예외가 발생한다.")
    void canNotMovePiece() {
        ChessBoard chessBoard = new ChessBoard(articleDefaultMap());

        assertThatThrownBy(() -> chessBoard.movePiece(new Position(File.A, Rank.TWO), new Position(File.B, Rank.TWO)))
                .isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    @DisplayName("각 진영의 점수를 계산할 수 있다.")
    void calculateScoreOfTeam() {
        ChessBoard chessBoard = new ChessBoard(articleDefaultMap());

        Map<Color, Double> colorsTotalScore = chessBoard.getColorsTotalScore();

        assertAll(
                () -> assertThat(colorsTotalScore.get(Color.BLACK)).isEqualTo(34),
                () -> assertThat(colorsTotalScore.get(Color.WHITE)).isEqualTo(34)
        );
    }

    private Map<Position, Article> articleDefaultMap() {
        return new HashMap<>() {{
            put(Position.of("a", "1"), new Rook(Color.WHITE));
            put(Position.of("b", "1"), new Knight(Color.WHITE));
            put(Position.of("c", "1"), new Bishop(Color.WHITE));
            put(Position.of("d", "1"), new Queen(Color.WHITE));
            put(Position.of("e", "1"), new King(Color.WHITE));
            put(Position.of("f", "1"), new Bishop(Color.WHITE));
            put(Position.of("g", "1"), new Knight(Color.WHITE));
            put(Position.of("h", "1"), new Rook(Color.WHITE));

            put(Position.of("a", "2"), new Pawn(Color.WHITE));
            put(Position.of("b", "2"), new Pawn(Color.WHITE));
            put(Position.of("c", "2"), new Pawn(Color.WHITE));
            put(Position.of("d", "2"), new Pawn(Color.WHITE));
            put(Position.of("e", "2"), new Pawn(Color.WHITE));
            put(Position.of("f", "2"), new Pawn(Color.WHITE));
            put(Position.of("g", "2"), new Pawn(Color.WHITE));
            put(Position.of("h", "2"), new Pawn(Color.WHITE));

            put(Position.of("a", "7"), new Pawn(Color.BLACK));
            put(Position.of("b", "7"), new Pawn(Color.BLACK));
            put(Position.of("c", "7"), new Pawn(Color.BLACK));
            put(Position.of("d", "7"), new Pawn(Color.BLACK));
            put(Position.of("e", "7"), new Pawn(Color.BLACK));
            put(Position.of("f", "7"), new Pawn(Color.BLACK));
            put(Position.of("g", "7"), new Pawn(Color.BLACK));
            put(Position.of("h", "7"), new Pawn(Color.BLACK));

            put(Position.of("a", "8"), new Rook(Color.BLACK));
            put(Position.of("b", "8"), new Knight(Color.BLACK));
            put(Position.of("c", "8"), new Bishop(Color.BLACK));
            put(Position.of("d", "8"), new Queen(Color.BLACK));
            put(Position.of("e", "8"), new King(Color.BLACK));
            put(Position.of("f", "8"), new Bishop(Color.BLACK));
            put(Position.of("g", "8"), new Knight(Color.BLACK));
            put(Position.of("h", "8"), new Rook(Color.BLACK));
        }};
    }
}
