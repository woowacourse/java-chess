package chess.model;

import static chess.model.File.*;
import static chess.model.PieceColor.*;
import static chess.model.Rank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.model.boardinitializer.BoardInitializer;
import chess.model.boardinitializer.defaultInitializer;
import chess.model.piece.EmptyPiece;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.turndecider.AlternatingTurnDecider;

public class ScoreCalculatorTest {

    @Test
    @DisplayName("초기 말들의 점수는 38.0 점이다.")
    void apply() {
        //given
        ScoreCalculator scoreCalculator = new ScoreCalculator(
            new Board(new AlternatingTurnDecider(), new defaultInitializer()).getValues(),
            new AlternatingTurnDecider());

        //when
        double actual = scoreCalculator.calculate();

        Assertions.assertThat(actual).isEqualTo(38.0);
    }

    @Test
    @DisplayName("폰이 같은 File에 두 개, 일반적 폰 1개, 다른 팀 폰 1개 있을 경우 2점으로 계산한다.")
    void when_pawns_in_same_file() {
        ScoreCalculator scoreCalculator = new ScoreCalculator(
            new Board(new AlternatingTurnDecider(), new testInitializer()).getValues(),
            new AlternatingTurnDecider());

        //then
        double actual = scoreCalculator.calculate();
        assertThat(actual).isEqualTo(2.0);
    }

    public static class testInitializer implements BoardInitializer {

        private static final Piece EMPTY_PIECE = EmptyPiece.of(EMPTY);

        @Override
        public Map<Position, Piece> apply() {
            Map<Position, Piece> result = new HashMap<>();
            putAllEmptyPieces(result);
            result.put(new Position(FOUR, A), Pawn.colorOf(WHITE));
            result.put(new Position(THREE, A), Pawn.colorOf(WHITE));
            result.put(new Position(THREE, B), Pawn.colorOf(WHITE));
            result.put(new Position(FIVE, A), Pawn.colorOf(BLACK));
            return result;
        }

        private void putAllEmptyPieces(Map<Position, Piece> result) {
            for (Rank rank : Rank.reverseValues()) {
                putEmptyPiecesInOneRank(result, rank);
            }
        }

        private void putEmptyPiecesInOneRank(Map<Position, Piece> result, Rank rank) {
            for (File file : File.values()) {
                result.put(new Position(rank, file), EMPTY_PIECE);
            }
        }
    }
}
