package domain.chess.piece;

import static domain.Position.B3;
import static domain.Position.B5;
import static domain.Position.C2;
import static domain.Position.C6;
import static domain.Position.D4;
import static domain.Position.E2;
import static domain.Position.E6;
import static domain.Position.F3;
import static domain.Position.F5;
import static domain.chess.piece.PieceMoveResult.CATCH;
import static domain.chess.piece.PieceMoveResult.FAILURE;
import static domain.chess.piece.PieceMoveResult.SUCCESS;
import static domain.chess.piece.Team.BLACK;
import static domain.chess.piece.Team.WHITE;

import domain.Position;
import domain.chess.board.ChessBoard;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {
    public static Stream<Arguments> moveFailureParameters() {
        Set<Position> successParameters = moveSuccessParameters().map(Arguments::get)
                .map(objects -> (Position) objects[0])
                .collect(Collectors.toSet());
        return Arrays.stream(Position.values())
                .filter(position -> !successParameters.contains(position))
                .map(Arguments::of);
    }

    public static Stream<Arguments> moveSuccessParameters() {
        return Stream.of(
                Arguments.of(C6), Arguments.of(E6),
                Arguments.of(B5), Arguments.of(F5),
                Arguments.of(B3), Arguments.of(F3),
                Arguments.of(C2), Arguments.of(E2)
        );
    }

    @ParameterizedTest
    @MethodSource("moveSuccessParameters")
    @DisplayName("나이트의 이동 규칙대로 이동이 가능한지 검증")
    void moveSuccess(Position targetPosition) {
        Knight knight = new Knight(D4, WHITE);
        ChessBoard chessBoard = new ChessBoard(List.of());
        Assertions.assertThat(knight.move(targetPosition, chessBoard))
                .isEqualTo(SUCCESS);
    }

    @ParameterizedTest
    @MethodSource("moveFailureParameters")
    @DisplayName("나이트의 이동 규칙을 위반한 이동이 불가능한지 검증")
    void moveFailure(Position targetPosition) {
        Knight knight = new Knight(D4, WHITE);
        ChessBoard chessBoard = new ChessBoard(List.of());
        Assertions.assertThat(knight.move(targetPosition, chessBoard))
                .isEqualTo(FAILURE);
    }

    @ParameterizedTest
    @MethodSource("moveSuccessParameters")
    @DisplayName("나이트의 목적지에 같은 팀 말이 있는 경우 이동이 불가능한지 검증")
    void moveFailureCauseTargetIsSameTeam(Position targetPosition) {
        Knight knight = new Knight(D4, WHITE);
        ChessBoard chessBoard = new ChessBoard(List.of(new Pawn(targetPosition, WHITE)));
        Assertions.assertThat(knight.move(targetPosition, chessBoard))
                .isEqualTo(FAILURE);
    }

    @ParameterizedTest
    @MethodSource("moveSuccessParameters")
    @DisplayName("나이트의 목적지에 다른 팀 말이 있는 경우 이동이 가능한지 검증")
    void moveSuccessWhenTargetIsOtherTeam(Position targetPosition) {
        Knight knight = new Knight(D4, WHITE);
        ChessBoard chessBoard = new ChessBoard(List.of(new Pawn(targetPosition, BLACK)));
        Assertions.assertThat(knight.move(targetPosition, chessBoard))
                .isEqualTo(CATCH);
    }
}
