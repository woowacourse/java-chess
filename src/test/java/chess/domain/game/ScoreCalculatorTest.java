package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.constant.ChessPosition;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.PieceType;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class ScoreCalculatorTest {

    private final Piece WHITE_PAWN = PieceFactory.getInstance(PieceType.PAWN, Color.WHITE);
    private final Piece BLACK_PAWN = PieceFactory.getInstance(PieceType.PAWN, Color.BLACK);
    private Map<Position, Piece> initialPiecePositions;

    @BeforeEach
    void setUp() {
        initialPiecePositions = ChessPosition.initialPiecePositions();
    }

    @Test
    void 점수_계산_테스트() {
        //given
        initialPiecePositions.put(Position.of(File.A, Rank.THREE), WHITE_PAWN);
        ScoreCalculator scoreCalculator = new ScoreCalculator(initialPiecePositions);

        //when
        Map<Color, Double> result = scoreCalculator.calculateScore();

        //then
        assertThat(result)
                .containsEntry(Color.WHITE, 38.5d)
                .containsEntry(Color.BLACK, 38d);
    }
}
