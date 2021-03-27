package chess.domain.board;

import chess.domain.piece.Piece;
import java.util.Map;

public class PieceOnBoardPosition {

    private final Map<Position, Piece> chessBoard;

    public PieceOnBoardPosition(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }


    public void pieceOnChessBoard(Position boardPosition, Piece piece) {
        chessBoard.put(boardPosition, piece);
    }

    public boolean containsKey(Position position) {
        return chessBoard.containsKey(position);
    }

    public Piece valueOf(Position position) {
        return chessBoard.get(position);
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }
}
