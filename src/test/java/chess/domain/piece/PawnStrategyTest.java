package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnStrategyTest {

    @ParameterizedTest
    @DisplayName("이동 경로 찾기")
    @MethodSource("createSourceToTarget")
    void findMovePath(Position target, List<Position> expected) {
        Position source = Position.from("d2");
        Map<Position, GamePiece> board = new TreeMap<>(Board.createEmpty().getBoard());
        GamePiece gamePiece = ChessPiece.WHITE_PAWN.getGamePiece();
        board.put(source, gamePiece);
        board.put(Position.from("c3"), ChessPiece.BLACK_KING.getGamePiece());
        board.put(Position.from("e3"), ChessPiece.BLACK_KING.getGamePiece());

        assertThatCode(() -> {
            gamePiece.validatePath(board, source, target);
        }).doesNotThrowAnyException();

    }

    static Stream<Arguments> createSourceToTarget() {
        return Stream.of(
                Arguments.of(Position.from("d3"), Collections.emptyList()),
                Arguments.of(Position.from("d4"), Collections.singletonList(Position.from("d3"))),
                Arguments.of(Position.from("c3"), Collections.emptyList()),
                Arguments.of(Position.from("e3"), Collections.emptyList())
        );
    }

    @ParameterizedTest
    @DisplayName("이동할 수 없는 source, target")
    @MethodSource("createInvalidTarget")
    void invalidMovementException(Position target) {
        Map<Position, GamePiece> board = new TreeMap<>(Board.createEmpty().getBoard());
        Position source = Position.from("d5");
        GamePiece piece = ChessPiece.WHITE_PAWN.getGamePiece();

        board.put(source, piece);

        assertThatThrownBy(() -> {
            piece.validatePath(board, source, target);
        }).isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.\n이동할 수 없는 경로입니다.");
    }

    static Stream<Arguments> createInvalidTarget() {
        return Stream.of(
                Arguments.of(Position.from("c6")),
                Arguments.of(Position.from("c5")),
                Arguments.of(Position.from("c4")),
                Arguments.of(Position.from("d4")),
                Arguments.of(Position.from("e4")),
                Arguments.of(Position.from("e5")),
                Arguments.of(Position.from("e6"))
        );
    }

    // TODO: 2020/03/29 위아래 구분
}