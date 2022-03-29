package chess.domain.game.board;

import chess.domain.game.Color;
import chess.domain.piece.*;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardFactory {

    private static Map<Position, ChessPiece> board = new HashMap<>();

    public static Map<Position, ChessPiece> initBoard() {
        for (Color value : Color.values()) {
            List<ChessPiece> pieces = List.of(
                    new King(value),
                    new Queen(value),
                    new Pawn(value),
                    new Rook(value),
                    new Bishop(value),
                    new Knight(value));
            addPiece(pieces);
        }
        return board;
    }

    private static void addPiece(List<ChessPiece> pieces) {
        for (ChessPiece chessPiece : pieces) {
            initByPiece(chessPiece);
        }
    }

    private static void initByPiece(ChessPiece chessPiece) {
        if (chessPiece.isBlack()) {
            addBlackPiece(chessPiece);
            return;
        }
        addWhitePiece(chessPiece);
    }

    private static void addWhitePiece(ChessPiece chessPiece) {
        for (Position position : chessPiece.getInitWhitePosition()) {
            board.put(position, chessPiece);
        }
    }

    private static void addBlackPiece(ChessPiece chessPiece) {
        for (Position position : chessPiece.getInitBlackPosition()) {
            board.put(position, chessPiece);
        }
    }
}
