package chess.domain;

import chess.domain.chessPiece.*;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChessBoard {

    private Map<Position, ChessPiece> chessBoard;

    public ChessBoard() {
        chessBoard = new HashMap<>();
        init();
    }

    private void init() {
        for (Color value : Color.values()) {
            List<ChessPiece> pieces = List.of(
                    new King(value),
                    new Queen(value),
                    new Pawn(value),
                    new Rook(value),
                    new Bishop(value),
                    new Knight(value));

            for (ChessPiece chessPiece : pieces) {
                initByPiece(chessPiece);
            }
        }
    }

    private void initByPiece(ChessPiece chessPiece) {
        if (chessPiece.isBlack()) {
            for (Position position : chessPiece.getInitBlackPosition()) {
                chessBoard.put(position, chessPiece);
            }
            return;
        }
        for (Position position : chessPiece.getInitWhitePosition()) {
            chessBoard.put(position, chessPiece);
        }
    }

    public int countPiece() {
        return chessBoard.size();
    }

    public Optional<ChessPiece> findPiece(Position position) {
        ChessPiece piece = chessBoard.get(position);
        if (piece == null) {
            return Optional.empty();
        }

        return Optional.of(piece);
    }
}
