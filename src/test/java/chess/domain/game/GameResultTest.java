package chess.domain.game;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.NONE;
import static chess.domain.piece.Color.WHITE;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.ROOK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GameResultTest {

    private GameResult gameResult;

    @BeforeEach
    void setUp() {
        Map<Position, Piece> pieces = Map.of(
                new Position("c2"), new Pawn(WHITE),
                new Position("d2"), new Pawn(WHITE),
                new Position("f2"), new Piece(WHITE, ROOK),
                new Position("e2"), new Pawn(BLACK),
                new Position("a2"), new Piece(BLACK, KING)
        );
        gameResult = new GameResult(pieces);
    }

    @DisplayName("색깔별 점수를 반환한다")
    @Test
    void getScore() {
        assertThat(gameResult.getScore(WHITE)).isEqualTo(Score.valueOf(7));
        assertThat(gameResult.getScore(BLACK)).isEqualTo(Score.valueOf(1));
    }

    @DisplayName("살아있는 왕이 2개면 점수가 더 큰 진영이 승자이다")
    @Test
    void getWinner_TwoKing() {
        Map<Position, Piece> pieces = Map.of(
                new Position("c2"), new Pawn(WHITE),
                new Position("d2"), new Pawn(WHITE),
                new Position("f2"), new Piece(WHITE, ROOK),
                new Position("e2"), new Pawn(BLACK),
                new Position("a2"), new Piece(WHITE, KING),
                new Position("a1"), new Piece(BLACK, KING)
        );
        gameResult = new GameResult(pieces);

        assertThat(gameResult.getWinner()).isSameAs(WHITE);
    }

    @DisplayName("살아있는 왕이 2개인데 점수가 같으면 없는색을 반환한다")
    @Test
    void getWinner_None() {
        Map<Position, Piece> pieces = Map.of(
                new Position("c2"), new Pawn(WHITE),
                new Position("e2"), new Pawn(BLACK),
                new Position("a2"), new Piece(WHITE, KING),
                new Position("a1"), new Piece(BLACK, KING)
        );
        gameResult = new GameResult(pieces);

        assertThat(gameResult.getWinner()).isSameAs(NONE);
    }

    @DisplayName("살아있는 왕이 1개면 왕이 살아있는쪽이 승자이다")
    @Test
    void getWinner_OneKing() {
        assertThat(gameResult.getWinner()).isSameAs(BLACK);
    }

    @DisplayName("살아있는 왕이 없으면 예외가 발생한다")
    @Test
    void getWinner_NoKing_throws() {
        Map<Position, Piece> pieces = Map.of(
                new Position("c2"), new Pawn(WHITE),
                new Position("d2"), new Pawn(WHITE),
                new Position("f2"), new Piece(WHITE, ROOK),
                new Position("e2"), new Pawn(BLACK)
        );
        gameResult = new GameResult(pieces);

        assertThatThrownBy(() -> gameResult.getWinner())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("살아있는 왕이 없습니다.");
    }

    @DisplayName("각 진영의 점수를 계산할 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"WHITE,7", "BLACK,1"})
    void calculate(Color color, double score) {
        assertThat(gameResult.getScore(color)).isEqualTo(Score.valueOf(score));
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
        gameResult = new GameResult(pieces);

        assertThat(gameResult.getScore(WHITE)).isEqualTo(Score.valueOf(7));
    }
}
