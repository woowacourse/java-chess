package chess.domain.piece.factory;

import chess.domain.piece.*;
import chess.domain.piece.state.move.MoveType;
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
    @DisplayName("#createInitializedPiece() : should return Class which implements Piece")
    @MethodSource("getCasesForCreatePiece")
    void createInitializedPiece(PieceType pieceType, Class<? extends Piece> expected) {
        Piece piece = PieceFactory.createInitializedPiece(pieceType, Team.WHITE);
        assertThat(piece).isInstanceOf(expected);
    }

    private static Stream<Arguments> getCasesForCreatePiece() {
        return Stream.of(
                Arguments.of(PieceType.INITIALIZED_PAWN, InitializedPawn.class),
                Arguments.of(PieceType.MOVED_PAWN, MovedPawn.class),
                Arguments.of(PieceType.ROOK, Rook.class),
                Arguments.of(PieceType.BISHOP, Bishop.class),
                Arguments.of(PieceType.QUEEN, Queen.class),
                Arguments.of(PieceType.KING, King.class)
        );
    }

    @Test
    @DisplayName("#createMovedPiece() : should return Class which implements Piece")
    void createMovedPice() {
        Piece piece = PieceFactory.createMovedPiece(PieceType.MOVED_PAWN, Team.WHITE, MoveType.MOVED);
        assertThat(piece).isInstanceOf(MovedPawn.class);
    }

}