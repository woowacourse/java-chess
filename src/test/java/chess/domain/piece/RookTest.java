package chess.domain.piece;

import chess.domain.Board;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @DisplayName("룩이 이동할 수 있는 모든 위치를 구한다.")
    @Test
    void generatePath() {
        Board board = new Board();
        Position current = new Position(Column.E, Row.FOUR);
        Piece rook = new Rook(PieceColor.WHITE);

        board.putPiece(rook, current);
        board.putPiece(new Rook(PieceColor.WHITE),new Position(Column.E, Row.TWO));
        board.putPiece(new Bishop(PieceColor.WHITE),new Position(Column.B, Row.FOUR));
        board.putPiece(new Rook(PieceColor.BLACK),new Position(Column.E, Row.EIGHT));
        Paths paths = new Paths(rook.findAllPath(current));

        for (Position position : paths.removeObstacles(board).positions()) {
            System.out.println(position.name());
        }
//        System.out.println(positions.size());
    }
}