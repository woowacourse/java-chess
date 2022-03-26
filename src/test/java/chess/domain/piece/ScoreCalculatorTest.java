package chess.domain.piece;

import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.piece.ChessmenInitializer;
import chess.domain.piece.piece.Pieces;
import chess.domain.piece.position.Position;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreCalculatorTest {

    private static final ChessmenInitializer chessmenInitializer = new ChessmenInitializer();
    private static final ScoreCalculator scoreCalculator = new ScoreCalculator();
    private Pieces chessmen;

    @BeforeEach
    void setup() {
        chessmen = chessmenInitializer.init();
    }

    @DisplayName("초기 상태의 점수 합은 38이다.")
    @Test
    void init_score() {
        List<Piece> pieces = chessmen.extractPiecesOf(WHITE);

        double actual = scoreCalculator.calculate(pieces);
        double expected = 38.0;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("퀸(9점)이 하나 없는 경우 합은 29이다.")
    @Test
    void no_queen_score() {
        Piece queen = chessmen.extractPiece(Position.of("d1"));
        chessmen.remove(queen);
        List<Piece> pieces = chessmen.extractPiecesOf(WHITE);

        double actual = scoreCalculator.calculate(pieces);
        double expected = 29.0;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("룩(5점)이 하나 없는 경우 합은 33이다.")
    @Test
    void no_rook_score() {
        Piece rook = chessmen.extractPiece(Position.of("a1"));
        chessmen.remove(rook);
        List<Piece> pieces = chessmen.extractPiecesOf(WHITE);

        double actual = scoreCalculator.calculate(pieces);
        double expected = 33.0;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("비숍(3점)이 하나 없는 경우 합은 35이다.")
    @Test
    void no_bishop_score() {
        Piece bishop = chessmen.extractPiece(Position.of("c1"));
        chessmen.remove(bishop);
        List<Piece> pieces = chessmen.extractPiecesOf(WHITE);

        double actual = scoreCalculator.calculate(pieces);
        double expected = 35.0;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("나이트(2.5점)이 하나 없는 경우 합은 35.5이다.")
    @Test
    void no_night_score() {
        Piece night = chessmen.extractPiece(Position.of("b1"));
        chessmen.remove(night);
        List<Piece> pieces = chessmen.extractPiecesOf(WHITE);

        double actual = scoreCalculator.calculate(pieces);
        double expected = 35.5;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("킹(0점)이 하나 없는 경우 합은 38이다.")
    @Test
    void no_king_score() {
        Piece king = chessmen.extractPiece(Position.of("e1"));
        chessmen.remove(king);
        List<Piece> pieces = chessmen.extractPiecesOf(WHITE);

        double actual = scoreCalculator.calculate(pieces);
        double expected = 38;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("폰(1점)이 같은 file에 하나만 존재하면 1점으로 간주되어 더해진다.")
    @Test
    void pawn_score_no_sameFilePawn() {
        List<Piece> pawnPieces = List.of(
            new Pawn(WHITE,Position.of("a1")),
            new Pawn(WHITE, Position.of("b1")));

        double actual = scoreCalculator.calculate(pawnPieces);
        double expected = 2;

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("폰(1점)이 같은 file에 2개 이상 존재하면 각각 0.5점으로 간주되어 더해진다.")
    @Test
    void pawn_score_on_sameFilePawn() {
        List<Piece> pawnPieces = List.of(
            new Pawn(WHITE,Position.of("a1")),
            new Pawn(WHITE, Position.of("a2")));

        double actual = scoreCalculator.calculate(pawnPieces);
        double expected = 1.0;

        assertThat(actual).isEqualTo(expected);
    }

}
