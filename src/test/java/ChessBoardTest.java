import chess.domain.ChessBoard;
import chess.domain.chesspieces.ChessPiece;
import chess.domain.chesspieces.Empty;
import chess.domain.chesspieces.Rook;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.annotation.Resource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {
    @DisplayName("초기 체스판 위치 확인")
    @ParameterizedTest
    @MethodSource("generatePositionAndPiece")
    void initChessBoard(Position position, ChessPiece chessPiece) {
        ChessBoard chessBoard = new ChessBoard();
        assertThat(chessBoard.getChessBoard().get(position)).isInstanceOf(chessPiece.getClass());
    }

    static Stream<Arguments> generatePositionAndPiece() {
        return Stream.of(
                Arguments.of(Positions.of(Row.A, Column.ONE), new Rook("r")),
                Arguments.of(Positions.of(Row.C, Column.FIVE), new Empty()));
    }
}
