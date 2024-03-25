package chess.domain;

import chess.domain.piece.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class ScoreTest {

    private static Stream<Arguments> calculateScoreParameters() {
        return Stream.of(
                Arguments.of(Team.WHITE, 52.5),
                Arguments.of(Team.BLACK, 43.5)
        );
    }

    @DisplayName("현재 살아있는 말에 따라 진영에 맞는 점수를 계산한다.")
    @ParameterizedTest
    @MethodSource("calculateScoreParameters")
    void calculateScoreTest(Team team, double expectedScore) {
        List<Piece> pieces = List.of(
                new Queen(new PieceInfo(Position.of("a3"), Team.WHITE)),
                new Rook(new PieceInfo(Position.of("a4"), Team.WHITE)),
                new Pawn(new PieceInfo(Position.of("a5"), Team.WHITE)),
                new Pawn(new PieceInfo(Position.of("a6"), Team.WHITE)),
                new Bishop(new PieceInfo(Position.of("b6"), Team.BLACK)),
                new Knight(new PieceInfo(Position.of("b5"), Team.BLACK)),
                new Pawn(new PieceInfo(Position.of("b4"), Team.BLACK))
        );

        Board board = new Board();
        pieces.forEach(piece -> board.placePiece(piece.getPieceInfo().getPosition(), piece));

        double actualScore = Score.calculateScore(board, team);

        Assertions.assertThat(actualScore).isEqualTo(expectedScore);
    }
}
