package chess.chessBoard;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.OriginalChessSpaceGenerator;
import chess.domain.chessBoard.PieceGenerator;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @Test
    @DisplayName("이동경로에 피스가 있으면 움직일 수 없다")
    void should_not_move_when_route_has_piece() {
        Position from = new Position(File.a, Rank.ONE);
        Position to = new Position(File.a, Rank.THREE);
        ChessBoard chessBoard = new ChessBoard(new OriginalChessSpaceGenerator(new PieceGenerator()));

        assertThatThrownBy(() -> chessBoard.move(from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("루트에 피스가 있습니다.");
    }
}
