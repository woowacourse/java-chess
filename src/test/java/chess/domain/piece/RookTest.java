package chess.domain.piece;

import chess.domain.Board;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @DisplayName("룩이 이동할 수 있는 모든 위치를 구한다.")
    @Test
    void generatePath() {
        Board board = new Board();
        Position current = Position.of("e4");
        Piece rook = new Rook(PieceColor.WHITE);

        board.putPiece(rook, current);
        board.putPiece(new Rook(PieceColor.WHITE), Position.of("e2"));
        board.putPiece(new Bishop(PieceColor.WHITE), Position.of("b4"));
        board.putPiece(new Rook(PieceColor.BLACK), Position.of("e8"));
        Paths paths = new Paths(rook.findAllPath(current));

        for (Position position : paths.removeObstacles(board).positions()) {
            System.out.println(position.name());
        }
//        System.out.println(positions.size());
    }
}