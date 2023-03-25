package chess.domain.game;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.NONE;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.PieceType.ROOK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ScoreTest {

    @DisplayName("점수는 음수일 수 없다")
    @Test
    void scoreNegative_throws() {
        assertThatThrownBy(() -> Score.valueOf(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("점수는 음수일 수 없습니다.");
    }

    @DisplayName("점수 객체를 생성할 수 있다")
    @ParameterizedTest
    @ValueSource(doubles = {0, 0.5, 1})
    void createScore(double score) {
        assertThat(Score.valueOf(score))
                .isInstanceOf(Score.class);
    }

    @DisplayName("점수를 계산할 수 있다")
    @Test
    void calculate() {
        Map<Position, Piece> pieces = Map.of(
                new Position("c2"), new Pawn(WHITE),
                new Position("d2"), new Pawn(WHITE),
                new Position("f2"), new Piece(WHITE, ROOK)
        );

        double score = Score.calculate(pieces, WHITE).getValue();

        assertThat(score).isEqualTo(7);
    }

    @DisplayName("같은 색깔 점수만 계산된다")
    @Test
    void calculateByColor() {
        Map<Position, Piece> pieces = Map.of(
                new Position("c2"), new Pawn(WHITE),
                new Position("d2"), new Pawn(WHITE),
                new Position("e2"), new Pawn(BLACK),
                new Position("f2"), new Piece(WHITE, ROOK)
        );

        double score = Score.calculate(pieces, WHITE).getValue();

        assertThat(score).isEqualTo(7);
    }

    @DisplayName("같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다")
    @Test
    void calculateByDuplicatedPawn() {
        Map<Position, Piece> pieces = Map.of(
                new Position("c2"), new Pawn(WHITE),
                new Position("c3"), new Pawn(WHITE),
                new Position("d3"), new Pawn(WHITE),
                new Position("e2"), new Pawn(BLACK),
                new Position("f2"), new Piece(WHITE, ROOK)
        );

        double score = Score.calculate(pieces, WHITE).getValue();

        assertThat(score).isEqualTo(7);
    }

    @DisplayName("점수를 비교하여 승리진영을 반환한다")
    @ParameterizedTest
    @CsvSource(value = {"10,5,BLACK", "5,10,WHITE"})
    void judgeWinner(double blackScore, double whiteScore, Color expected) {
        Color actual = Score.judgeWinner(Score.valueOf(blackScore), Score.valueOf(whiteScore));

        assertThat(actual).isSameAs(expected);
    }

    @DisplayName("점수가 같으면 없는색을 반환한다")
    @Test
    void judgeWinner_same_None() {
        Color winner = Score.judgeWinner(Score.valueOf(10), Score.valueOf(10));

        assertThat(winner).isSameAs(NONE);
    }
}
