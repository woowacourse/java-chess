package chess.domain.piece.factory;

import chess.domain.piece.Piece;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.InitializedPawn;
import chess.domain.piece.MovedPawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.state.move.MoveType;
import chess.domain.piece.team.Team;
import chess.domain.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


class PieceFactoryTest {

    @ParameterizedTest
    @DisplayName("#createInitializedPiece() : should return Class which implements Piece")
    @MethodSource("getCasesForCreatePiece")
    void createInitializedPiece(Class<? extends Piece> type) {
        Piece piece = PieceFactory.createInitializedPiece(type, Position.of(1, 1), Team.WHITE);
        assertThat(piece).isInstanceOf(type);
    }

    private static Stream<Arguments> getCasesForCreatePiece() {
        return Stream.of(
                Arguments.of(InitializedPawn.class),
                Arguments.of(MovedPawn.class),
                Arguments.of(Rook.class),
                Arguments.of(Bishop.class),
                Arguments.of(Queen.class),
                Arguments.of(King.class)
        );
    }

    @Test
    @DisplayName("#createMovedPiece() : should return Class which implements Piece")
    void createMovedPice() {
        Piece piece = PieceFactory.createMovedPiece(MovedPawn.class, Position.of(1, 1), Team.WHITE, MoveType.MOVED);
        assertThat(piece).isInstanceOf(MovedPawn.class);
    }

}