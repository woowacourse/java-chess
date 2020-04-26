package chess.domain.piece.factory;

import chess.domain.piece.InitializedPawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PiecesState;
import chess.domain.piece.TestPiecesState;
import chess.domain.piece.policy.move.*;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

//todo: refac
class PieceTypeTest {
    @ParameterizedTest
    @DisplayName("#findByInitialColumn() : should return Class as to initialCollumn")
    @MethodSource({"getCasesForfindTypeByInitialColumn"})
    void findByInitialColumn(int initialColumn) {
//        assertThat(pieceType).isEqualTo(expected)
    }

    @ParameterizedTest
    @DisplayName("#valueOf() : should return PieceType as to Class")
    @MethodSource({"getCasesForValueOf"})
    void valueOf(Class<? extends Piece> type) {
//        assertThat(pieceType).isEqualTo(expected);
    }

    private static Stream<Arguments> getCasesForValueOf() {
        return Stream.of(
                Arguments.of(InitializedPawn.class)
        );
    }

    private static Stream<Arguments> getCasesForfindTypeByInitialColumn() {
        return Stream.of(
                Arguments.of(1, null),
                Arguments.of(2, null),
                Arguments.of(3, null),
                Arguments.of(4, null),
                Arguments.of(5, null),
                Arguments.of(6, null),
                Arguments.of(7, null),
                Arguments.of(8, null)
        );
    }

    @Test
    @DisplayName("#findByInitialColumn() : should throw IllegalArgumentException with invalid initialCollumn")
    void findTypeByInitialColumnFail() {
        int invalidInitialColumn = 10;
//        assertThatThrownBy(() -> PieceType.findByInitialColumn(invalidInitialColumn))
//                .isInstanceOf(IllegalArgumentException.class);
    }

    //todo: refac

//    @ParameterizedTest
//    @DisplayName("#canNotMove() : should return boolean as to Position 'from', 'to' and team")
//    @MethodSource({"getCasesForCanNotMove"})
//    void canNotMove(Position from, Position to, boolean expected) {
//        PiecesState piecesState = TestPiecesState.initialize();
//        boolean actual = PieceType.canNotMove(from, to, piecesState);
//        assertThat(actual).isEqualTo(expected);
//    }

    private static Stream<Arguments> getCasesForCanNotMove() {
        Team team = Team.WHITE;
        return Stream.of(
                Arguments.of(
                        Position.of(3,1),
                        Position.of(3, 1),
                        true),
                Arguments.of(
                        Position.of(3,1),
                        Position.of(1, 3),
                        true),
                Arguments.of(
                        Position.of(3,1),
                        Position.of(2, 2),
                        true),
                Arguments.of(
                        Position.of(3,1),
                        Position.of(4, 3),
                        true),
                Arguments.of(
                        Position.of(1,2),
                        Position.of(1, 5),
                        true),
                Arguments.of(
                        Position.of(1,2),
                        Position.of(2, 3),
                        true),
                Arguments.of(
                        Position.of(1,2),
                        Position.of(1, 2),
                        true),
                Arguments.of(
                        Position.of(1,2),
                        Position.of(2, 2),
                        true),
                Arguments.of(
                        Position.of(1,2),
                        Position.of(1, 1),
                        true),


                Arguments.of(
                        Position.of(5,1),
                        Position.of(5, 1),
                        true),
                Arguments.of(
                        Position.of(5,1),
                        Position.of(5, 3),
                        true),
                Arguments.of(
                        Position.of(5,1),
                        Position.of(5, 2),
                        true),


                Arguments.of(
                        Position.of(2,1),
                        Position.of(2, 1),
                        true),
                Arguments.of(
                        Position.of(2,1),
                        Position.of(3, 4),
                        true),
                Arguments.of(
                        Position.of(2,1),
                        Position.of(3, 2),
                        true),



                Arguments.of(
                        Position.of(1,2),
                        Position.of(1, 4),
                        true),
                Arguments.of(
                        Position.of(1,2),
                        Position.of(3, 4),
                        true),


                Arguments.of(
                        Position.of(4,1),
                        Position.of(4, 1),
                        true),
                Arguments.of(
                        Position.of(4,1),
                        Position.of(4, 3),
                        true),
                Arguments.of(
                        Position.of(4,1),
                        Position.of(4, 2),
                        true),
                Arguments.of(
                        Position.of(4,1),
                        Position.of(6, 2),
                        true),


                Arguments.of(
                        Position.of(1,1),
                        Position.of(1, 1),
                        true),
                Arguments.of(
                        Position.of(1,1),
                        Position.of(1, 3),
                        true),
                Arguments.of(
                        Position.of(1,1),
                        Position.of(3, 1),
                        true),
                Arguments.of(
                        Position.of(1,1),
                        Position.of(1, 2),
                        true),
                Arguments.of(
                        Position.of(1,1),
                        Position.of(2, 2),
                        true)
        );
    }
}