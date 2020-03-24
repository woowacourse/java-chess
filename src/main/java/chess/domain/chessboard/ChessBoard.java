package chess.domain.chessboard;

import chess.domain.PiecePosition;

import java.util.Collections;
import java.util.List;

public class ChessBoard {
    private final List<PiecePosition> chessBoard;

    public ChessBoard() {
        this.chessBoard = ChessBoardFactory.create();
    }

    public int getChessBoardSize() {
        return chessBoard.size();
    }

    public List<PiecePosition> getChessBoard() {
        return Collections.unmodifiableList(chessBoard);
    }
}
