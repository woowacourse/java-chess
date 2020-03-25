import chess.domain.ChessBoard;
import chess.domain.Player;
import chess.domain.chesspieces.Empty;
import chess.domain.chesspieces.Pawn;
import chess.domain.chesspieces.Rook;
import chess.domain.chesspieces.Square;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {
    @DisplayName("초기 체스판 위치 확인")
    @ParameterizedTest
    @MethodSource("generatePositionAndPiece")
    void initChessBoard(Position position, Square square) {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard.getChessBoard().get(position)).isInstanceOf(square.getClass());
    }

    static Stream<Arguments> generatePositionAndPiece() {
        return Stream.of(
                Arguments.of(Positions.of(Row.A, Column.ONE), new Rook(Player.WHITE)),
                Arguments.of(Positions.of(Row.C, Column.FIVE), new Empty()));
    }

    @DisplayName("체스 기물의 이동 확인: 정상적일 때")
    @Test
    void test2() {
        ChessBoard chessBoard = new ChessBoard();
        Position source = Positions.of("a2");
        Position target = Positions.of("a3");
        chessBoard.move(source, target);

        Assertions.assertThat(chessBoard.getChessBoard().get(source)).isInstanceOf(Empty.class);
        Assertions.assertThat(chessBoard.getChessBoard().get(target)).isInstanceOf(Pawn.class);
    }

    @Test
    void 장애물확인() {
        ChessBoard chessBoard = new ChessBoard();
        Position source = Positions.of("a1");
        Position target = Positions.of("a2");
        List<Position> routes = chessBoard.getRoutes(source, target);
        assertThat(chessBoard.validateObstacles(routes)).isFalse();
    }
}
