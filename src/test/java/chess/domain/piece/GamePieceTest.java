package chess.domain.piece;

import static chess.domain.piece.ChessPiece.*;
import static chess.domain.player.PlayerColor.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.exception.InvalidMovementException;

class GamePieceTest {

    @ParameterizedTest
    @DisplayName("GamePiece별 초기 위치")
    @MethodSource("createPositions")
    void getInitialPositions(GamePiece piece, List<Position> expected) {
        assertThat(piece.getOriginalPositions()).isEqualTo(expected);
    }

    static Stream<Arguments> createPositions() {
        return Stream.of(
                Arguments.of(BLACK_ROOK.getGamePiece(), Arrays.asList(Position.from("a8"), Position.from("h8"))),
                Arguments.of(WHITE_ROOK.getGamePiece(), Arrays.asList(Position.from("a1"), Position.from("h1")))
        );
    }

    @ParameterizedTest
    @DisplayName("gamepiece player가 white인지 확인")
    @MethodSource("createWhitePieces")
    void isWhite(GamePiece gamePiece, boolean expected) {
        assertThat(gamePiece.is(WHITE)).isEqualTo(expected);
    }

    static Stream<Arguments> createWhitePieces() {
        return Stream.of(
                Arguments.of(BLACK_ROOK.getGamePiece(), false),
                Arguments.of(WHITE_ROOK.getGamePiece(), true)
        );
    }

    @Test
    void samePlayerColor() {
        Position source = Position.from("d5");
        Position target = Position.from("d4");
        Map<Position, GamePiece> board = new TreeMap<>(Board.createEmpty().getBoard());
        GamePiece gamePiece = BLACK_ROOK.getGamePiece();
        board.put(source, gamePiece);
        board.put(target, BLACK_BISHOP.getGamePiece());

        assertThatThrownBy(() -> gamePiece.validateMoveTo(board, source, target))
                .isInstanceOf(InvalidMovementException.class)
                .hasMessage("이동할 수 없습니다.\n자신의 말은 잡을 수 없습니다.");
    }
}