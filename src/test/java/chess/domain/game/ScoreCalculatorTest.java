package chess.domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.chessboard.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Symbol;
import chess.domain.piece.generator.PiecesGenerator;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreCalculatorTest {

    @Test
    @DisplayName("여러 컬럼의 검은색 점수를 계산한다.")
    void calculateColumnsForBlack() {
        final Map<Position, Piece> testPieces = new HashMap<>(Map.ofEntries(
                Map.entry(Position.of("a1"), Piece.of(Color.WHITE, Symbol.KING)),
                Map.entry(Position.of("a8"), Piece.of(Color.BLACK, Symbol.KING)),
                Map.entry(Position.of("b3"), Piece.of(Color.WHITE, Symbol.PAWN)),
                Map.entry(Position.of("c4"), Piece.of(Color.WHITE, Symbol.PAWN)),
                Map.entry(Position.of("a4"), Piece.of(Color.BLACK, Symbol.PAWN)),
                Map.entry(Position.of("a7"), Piece.of(Color.BLACK, Symbol.PAWN)),
                Map.entry(Position.of("c5"), Piece.of(Color.BLACK, Symbol.PAWN)),
                Map.entry(Position.of("b8"), Piece.of(Color.BLACK, Symbol.PAWN))
        ));
        PiecesGenerator.fillEmptyPiece(testPieces);
        ChessBoard chessBoard = new ChessBoard(() -> testPieces);

        final ScoreCalculator calculator = new ScoreCalculator(chessBoard.getPieces());
        final double score = calculator.calculate(Color.BLACK);

        assertThat(score).isEqualTo(3);
    }

    @Test
    @DisplayName("여러 컬럼의 하얀색 점수를 계산한다.")
    void calculateColumnsForWhite() {
        final Map<Position, Piece> testPieces = new HashMap<>(Map.ofEntries(
                Map.entry(Position.of("a1"), Piece.of(Color.WHITE, Symbol.KING)),
                Map.entry(Position.of("a8"), Piece.of(Color.BLACK, Symbol.KING)),
                Map.entry(Position.of("b3"), Piece.of(Color.WHITE, Symbol.PAWN)),
                Map.entry(Position.of("b4"), Piece.of(Color.WHITE, Symbol.PAWN)),
                Map.entry(Position.of("a4"), Piece.of(Color.WHITE, Symbol.BISHOP)),
                Map.entry(Position.of("a7"), Piece.of(Color.WHITE, Symbol.ROOK)),
                Map.entry(Position.of("c5"), Piece.of(Color.BLACK, Symbol.PAWN)),
                Map.entry(Position.of("b8"), Piece.of(Color.BLACK, Symbol.PAWN))
        ));
        PiecesGenerator.fillEmptyPiece(testPieces);
        ChessBoard chessBoard = new ChessBoard(() -> testPieces);

        final ScoreCalculator calculator = new ScoreCalculator(chessBoard.getPieces());
        final double score = calculator.calculate(Color.WHITE);

        assertThat(score).isEqualTo(9);
    }
}
