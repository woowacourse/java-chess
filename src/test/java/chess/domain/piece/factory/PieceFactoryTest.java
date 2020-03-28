package chess.domain.piece.factory;

import chess.domain.piece.Piece;
import chess.domain.piece.pawn.InitializedPawn;
import chess.domain.piece.pawn.RunningPawn;
import chess.domain.piece.rook.Rook;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


class PieceFactoryTest {

    @ParameterizedTest
    @DisplayName("#createPiece() : should return Class which implements Piece")
    @MethodSource("getCasesForCreatePiece")
    void createPiece(Class<? extends Piece> type) {
        Piece piece = PieceFactory.createPiece(type, Position.of(1, 1), Team.WHITE);
        assertThat(piece).isInstanceOf(type);
    }

    private static Stream<Arguments> getCasesForCreatePiece() {
        return Stream.of(
                Arguments.of(InitializedPawn.class),
                Arguments.of(RunningPawn.class),
                Arguments.of(Rook.class)
        );
    }
}