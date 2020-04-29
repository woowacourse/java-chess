package chess.domain.piece.factory;

import chess.domain.piece.*;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

//todo: refac
class PieceFactoryTest {

    @ParameterizedTest
    @DisplayName("#createPieceWithInitialColumn() : should return Class which implements Piece")
    @MethodSource("getCasesForCreatePiece")
    void createInitializedPiece(int initialColumn, Class<? extends Piece> expected) {
        //todo: refac
        Piece piece = PieceFactory.createPieceWithInitialColumn(initialColumn, Team.WHITE);
        assertThat(piece).isInstanceOf(expected);
    }

    private static Stream<Arguments> getCasesForCreatePiece() {
        return Stream.of(
                Arguments.of(1, Rook.class),
                Arguments.of(8, Rook.class),
                Arguments.of(2, Knight.class),
                Arguments.of(7, Knight.class),
                Arguments.of(3, Bishop.class),
                Arguments.of(6, Bishop.class),
                Arguments.of(4, Queen.class),
                Arguments.of(5, King.class)
        );
    }

    @Test
    @DisplayName("#createInitializedPawn() : should return InitializedPawn")
    void createInitializedPawn() {
        Piece piece = PieceFactory.createInitializedPawn(Team.WHITE);
        assertThat(piece).isInstanceOf(InitializedPawn.class);
    }

}