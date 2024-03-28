package chess.model.score;

import chess.dto.ScoreDTO;
import chess.model.board.Board;
import chess.model.piece.*;
import chess.model.position.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreGeneratorTest {
    @ParameterizedTest
    @MethodSource("provideBoardForPawnScore")
    void 세로_줄에_같은_폰에_대해_올바르게_점수를_계산한다(Board board, ScoreDTO scoreDTO) {
        ScoreGenerator scoreGenerator = new ScoreGenerator(board);
        assertThat(scoreGenerator.calculateScore()).isEqualTo(scoreDTO);
    }

    private static Stream<Arguments> provideBoardForPawnScore() {
        return Stream.of(
                Arguments.of(
                        new Board(Map.of(
                                Position.of(1, 2), Pawn.from(Color.BLACK),
                                Position.of(1, 3), Pawn.from(Color.BLACK)),
                                Color.WHITE),
                        new ScoreDTO(1.0, 0.0)
                ),
                Arguments.of(
                        new Board(Map.of(
                                Position.of(1, 2), Pawn.from(Color.WHITE),
                                Position.of(1, 3), Pawn.from(Color.WHITE),
                                Position.of(1, 4), Pawn.from(Color.WHITE)),
                                Color.WHITE),
                        new ScoreDTO(0.0, 1.5)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideBoardWithScore")
    void 올바르게_블랙과_화이트의_점수를_계산한다(Board board, ScoreDTO scoreDTO) {
        ScoreGenerator scoreGenerator = new ScoreGenerator(board);
        assertThat(scoreGenerator.calculateScore()).isEqualTo(scoreDTO);
    }

    private static Stream<Arguments> provideBoardWithScore() {
        return Stream.of(
                Arguments.of(
                        new Board(Map.of(
                                Position.of(1, 2), Knight.from(Color.BLACK),
                                Position.of(1, 3), Queen.from(Color.WHITE)),
                                Color.WHITE),
                        new ScoreDTO(2.5, 9.0)
                ),
                Arguments.of(
                        new Board(Map.of(
                                Position.of(1, 2), Pawn.from(Color.BLACK),
                                Position.of(1, 3), King.from(Color.BLACK),
                                Position.of(1, 4), Bishop.from(Color.WHITE),
                                Position.of(3, 4), Rook.from(Color.WHITE)),
                                Color.WHITE),
                        new ScoreDTO(1.0, 8.0)
                )
        );
    }
}
