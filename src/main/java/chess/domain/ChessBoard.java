package chess.domain;

import chess.domain.chessPiece.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private Map<String, ChessPiece> chessBoard;

    public ChessBoard() {
        chessBoard = new HashMap<>();
        init();
    }

    private void init() {
        List<ChessPiece> pieces = List.of(
                Rook.getInstance(),
                King.getInstance(),
                Knight.getInstance(),
                Bishop.getInstance(),
                Queen.getInstance(),
                Pawn.getInstance());

        for (ChessPiece chessPiece : pieces) {
            initByPiece(chessPiece);
        }
    }

    private void initByPiece(ChessPiece chessPiece) {
        for (String position : chessPiece.getInitPosition()) {
            chessBoard.put(position, chessPiece);
        }
    }

    public int countPiece() {
        return chessBoard.size();
    }
}
