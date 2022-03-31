package chess.domain.score;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.ChessScore;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreCalculatorTest {
    @Test
    @DisplayName("남아있는 말들의 점수를 계산한다.")
    void score() {
        Map<Position, Piece> pieces = Map.of(
                new Position(1, 1), Pawn.createWhite(),
                new Position(2, 2), King.createWhite(),
                new Position(3, 3), Queen.createWhite(),
                new Position(4, 4), Rook.createWhite(),
                new Position(5, 5), Bishop.createWhite(),
                new Position(6, 6), Knight.createWhite()
        );

        ChessScore chessScore = new ScoreCalculator().calculateChessScore(pieces);

        assertThat(chessScore.equals(new ChessScore(20.5, 0))).isTrue();
    }

    @Test
    @DisplayName("한 세로줄에 있는 폰들은 0.5점으로 계산한다.")
    void scorePawn() {
        Map<Position, Piece> pieces = Map.of(
                new Position(1, 1), Pawn.createWhite(),
                new Position(2, 1), Pawn.createWhite(),
                new Position(3, 2), Pawn.createWhite()
        );

        ChessScore chessScore = new ScoreCalculator().calculateChessScore(pieces);

        assertThat(chessScore.equals(new ChessScore(2.0, 0))).isTrue();
    }
}
