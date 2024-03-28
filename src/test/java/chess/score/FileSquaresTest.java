package chess.score;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.board.Square;
import chess.piece.Bishop;
import chess.piece.Color;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.MovedPawn;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileSquaresTest {

    private static final List<Square> squares = List.of(
            new Square(new King(Color.WHITE)),
            new Square(new Queen(Color.WHITE)),
            new Square(new Knight(Color.WHITE)),
            new Square(new MovedPawn(Color.WHITE)),
            new Square(new Rook(Color.BLACK)),
            new Square(new Bishop(Color.BLACK)),
            new Square(new MovedPawn(Color.BLACK)),
            new Square(new MovedPawn(Color.BLACK))
    );

    @Test
    @DisplayName("주어진 기물들의 점수를 색에 따라 올바르게 계산한다.")
    void calculatePieceScoreByColorTest() {
        // given
        FileSquares fileSquares = new FileSquares(squares);
        // when
        Score whiteScore = fileSquares.calculateScore(Color.WHITE);
        Score blackScore = fileSquares.calculateScore(Color.BLACK);
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
        List<Square> pawns = List.of(
                new Square(new MovedPawn(Color.BLACK)),
                new Square(new MovedPawn(Color.BLACK)),
                new Square(new MovedPawn(Color.BLACK))
        );
        FileSquares fileSquares = new FileSquares(pawns);
        // when
        Score actual = fileSquares.calculateScore(Color.BLACK);
        // then
        assertThat(actual).isEqualTo(Score.of(1.5));
    }

    @Test
    @DisplayName("폰이 한 개만 있는 경우 올바르게 점수를 계산한다.")
    void calculateSinglePawn() {
        // given
        List<Square> pawns = List.of(
                new Square(new MovedPawn(Color.BLACK))
        );
        FileSquares fileSquares = new FileSquares(pawns);
        // when
        Score actual = fileSquares.calculateScore(Color.BLACK);
        // then
        assertThat(actual).isEqualTo(Score.of(1));
    }
}
