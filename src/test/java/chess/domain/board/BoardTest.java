package chess.domain.board;

import chess.domain.chesspiece.Blank;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Pawn;
import chess.domain.move.Coordinate;
import chess.domain.move.MovingInfo;
import chess.domain.move.Position;
import chess.factory.BoardFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BoardTest {
    private final static int CORRECTION_VALUE = 1;

    @Test
    @DisplayName("ChessBoard 생성")
    void create() {
        assertThat(BoardFactory.createBoard()).isInstanceOf(Board.class);
    }

    @Test
    @DisplayName("getBoard 테스트")
    void getBoard() {
        Board board = BoardFactory.createBoard();

        assertThat(board.getBoard()).isInstanceOf(List.class);
    }

    @Test
    @DisplayName("move 테스트")
    void move() {
        Board board = BoardFactory.createBoard();
        Position startPosition = Position.of(Coordinate.of(2), Coordinate.of(1));
        Position targetPosition = Position.of(Coordinate.of(4), Coordinate.of(1));
        MovingInfo movingInfo = new MovingInfo(startPosition, targetPosition);

        board.move(movingInfo);
        ChessPiece startChessPiece = board.getBoard().get(2 - CORRECTION_VALUE).get(1 - CORRECTION_VALUE);
        ChessPiece targetChessPiece = board.getBoard().get(4 - CORRECTION_VALUE).get(1 - CORRECTION_VALUE);
        assertThat(startChessPiece).isInstanceOf(Blank.class);
        assertThat(targetChessPiece).isInstanceOf(Pawn.class);
    }
}
