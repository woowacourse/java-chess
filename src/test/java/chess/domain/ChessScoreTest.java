package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessScoreTest {

    @Test
    @DisplayName("한 컬럼의 점수를 계산한다.")
    void calculateOneColumn() {
        ChessScore chessScore = new ChessScore();
        List<Piece> pieces = List.of(new Knight(Color.BLACK), new Bishop(Color.BLACK));
        double score = chessScore.calculateOneColumn(pieces);
        assertThat(score).isEqualTo(5.5);
    }

    @Test
    @DisplayName("폰이 두 개 이상 포함된 한 컬럼의 점수를 계산한다.")
    void calculateOneColumnWithPawns() {
        ChessScore chessScore = new ChessScore();
        List<Piece> pieces = List.of(new Knight(Color.BLACK), new Bishop(Color.BLACK),
                new Pawn(Color.BLACK), new Pawn(Color.BLACK), new Pawn(Color.BLACK));
        double score = chessScore.calculateOneColumn(pieces);
        assertThat(score).isEqualTo(7);
    }

    @Test
    @DisplayName("여러 컬럼의 점수를 계산한다.")
    void calculateColumns() {
        ChessScore chessScore = new ChessScore();
        List<List<Piece>> pieces = List.of(
                List.of(new Knight(Color.BLACK), new Bishop(Color.BLACK),
                        new Pawn(Color.BLACK), new Pawn(Color.BLACK), new Pawn(Color.BLACK)),
                List.of(new Pawn(Color.BLACK), new Pawn(Color.BLACK), new Queen(Color.BLACK))
        );
        double score = chessScore.calculateColumns(pieces);
        assertThat(score).isEqualTo(17);
    }
}
