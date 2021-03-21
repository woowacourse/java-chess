package chess;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawn;
import chess.domain.position.Position;
import chess.util.RenderingUtils;

public class Test {

    public static void main(String[] args) {
        Board board = new Board();
        Piece piece = new WhitePawn();
//        Piece piece = new Rook(PieceColor.WHITE);
        Position position = Position.ofName("a2");
        Position position2 = Position.ofName("a4");
//        Position position = Position.ofName("h6");
//        Position position2 = Position.ofName("g6");
        board.putPiece(piece, position);
        board.move(piece, position2);
        System.out.println(RenderingUtils.renderBoard(board));
    }
}
