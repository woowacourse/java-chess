package chess.domain.piece.factory;

import chess.domain.piece.Piece;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PieceFactoryTest {

    @ParameterizedTest
    @DisplayName("#createPieceWithInitialColumn() : should return Class which implements Piece")
    @MethodSource("getCasesForCreatePiece")
    void createInitializedPiece(int initialColumn) {
        Piece piece = PieceFactory.createPieceWithInitialColumn(initialColumn, Team.WHITE);
        assertThat(piece).isNotNull();
    }

    private static Stream<Arguments> getCasesForCreatePiece() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(8),
                Arguments.of(2),
                Arguments.of(7),
                Arguments.of(3),
                Arguments.of(6),
                Arguments.of(4),
                Arguments.of(5)
        );
    }

    @Test
    @DisplayName("#createInitializedPawn() : should return Pawn")
    void createInitializedPawn() {
        Piece piece = PieceFactory.createInitializedPawn(Team.WHITE);
        assertThat(piece).isNotNull();
    }

}