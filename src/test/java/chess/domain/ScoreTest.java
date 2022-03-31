package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.chesspiece.Bishop;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.chesspiece.King;
import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Pawn;
import chess.domain.chesspiece.Queen;
import chess.domain.chesspiece.Rook;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ScoreTest {

    @ParameterizedTest
    @DisplayName("폰이 같은 세로줄에 있는 경우의 점수를 구한다.")
    @CsvSource(value = {"BLACK:1.5", "WHITE:1"}, delimiter = ':')
    void calculateScore_pawn(final Color color, final double expected) {
        // given
        final Map<Position, ChessPiece> pieceByPosition = PieceByPosition.create()
                .add(Position.from("a1"), Pawn.from(Color.BLACK))
                .add(Position.from("a3"), Pawn.from(Color.BLACK))
                .add(Position.from("a5"), Pawn.from(Color.BLACK))
                .add(Position.from("a2"), Pawn.from(Color.WHITE))
                .toMap();

        // when
        final Score score = new Score(pieceByPosition);
        final Double actual = score.findScore(color);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("체스판에 있는 기물의 총합을 색깔별로 구한다.")
    @CsvSource(value = {"BLACK:20.5", "WHITE:0"}, delimiter = ':')
    void calculateScore_combination(final Color color, final double expected) {
        // given
        final Map<Position, ChessPiece> pieceByPosition = PieceByPosition.create()
                .add(Position.from("a1"), King.from(Color.BLACK)) // 0
                .add(Position.from("a2"), Queen.from(Color.BLACK)) // 9
                .add(Position.from("a3"), Knight.from(Color.BLACK)) // 2.5
                .add(Position.from("a4"), Rook.from(Color.BLACK)) // 5
                .add(Position.from("a5"), Bishop.from(Color.BLACK)) // 3
                .add(Position.from("a6"), Pawn.from(Color.BLACK)) // 1
                .add(Position.from("h2"), King.from(Color.WHITE)) // 0
                .toMap();

        // when
        final Score score = new Score(pieceByPosition);
        final Double actual = score.findScore(color);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static class PieceByPosition {

        private final Map<Position, ChessPiece> value;

        private PieceByPosition() {
            this.value = new HashMap<>();
        }

        static PieceByPosition create() {
            return new PieceByPosition();
        }

        PieceByPosition add(Position position, ChessPiece chessPiece) {
            value.put(position, chessPiece);
            return this;
        }

        public Map<Position, ChessPiece> toMap() {
            return value;
        }
    }
}
