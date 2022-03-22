package chess.domain;

import chess.domain.chessPiece.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChessBoard {

    private Map<String, String> chessBoard;

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
        for (String position : chessPiece.getInitBlackPosition()) {
            chessBoard.put(position, chessPiece.getName());
        }

        for (String position : chessPiece.getInitWhitePosition()) {
            chessBoard.put(position, chessPiece.getName().toLowerCase());
        }
    }

    public int countPiece() {
        return chessBoard.size();
    }

    public Optional<String> findPiece(String position) {

        String piece = chessBoard.get(position);
        if (piece == null) {
            return Optional.empty();
        }

        return Optional.of(piece);
    }
}
