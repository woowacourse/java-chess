package chess.score;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import chess.piece.Bishop;
import chess.piece.Color;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.MovedPawn;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FilePiecesTest {

    private static final List<Piece> pieces = List.of(
            new King(Color.WHITE),
            new Queen(Color.WHITE),
            new Knight(Color.WHITE),
            new MovedPawn(Color.WHITE),

            new Rook(Color.BLACK),
            new Bishop(Color.BLACK),
            new MovedPawn(Color.BLACK),
            new MovedPawn(Color.BLACK)
    );

    @Test
    @DisplayName("주어진 기물들의 점수를 색에 따라 올바르게 계산한다.")
    void calculatePieceScoreByColorTest() {
        // given
        FilePieces filePieces = new FilePieces(pieces);
        // when
        Score whiteScore = filePieces.calculateScore(Color.WHITE);
        Score blackScore = filePieces.calculateScore(Color.BLACK);
        // then
        assertAll(
                () -> assertThat(whiteScore).isEqualTo(Score.of(12.5)),
                () -> assertThat(blackScore).isEqualTo(Score.of(9))
        );
    }
    
    @Test
    @DisplayName("폰이 여러 개 있는 경우, 점수를 조절한다.")
    void manipulatePawnScoreTest() {
        // given
        List<Piece> pawns = List.of(
                new MovedPawn(Color.BLACK),
                new MovedPawn(Color.BLACK),
                new MovedPawn(Color.BLACK)
        );
        FilePieces filePieces = new FilePieces(pawns);
        // when
        Score actual = filePieces.calculateScore(Color.BLACK);
        // then
        assertThat(actual).isEqualTo(Score.of(1.5));
    }

    @Test
    @DisplayName("폰이 한 개만 있는 경우 올바르게 점수를 계산한다.")
    void calculateSinglePawn() {
        // given
        List<Piece> pawns = List.of(
                new MovedPawn(Color.BLACK)
        );
        FilePieces filePieces = new FilePieces(pawns);
        // when
        Score actual = filePieces.calculateScore(Color.BLACK);
        // then
        assertThat(actual).isEqualTo(Score.of(1));
    }
}
