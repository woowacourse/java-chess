package chess.domain.game.board;

import chess.domain.game.Color;
import chess.domain.piece.*;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardFactory {

    public static ChessBoard initBoard() {
        Map<Position, ChessPiece> board = new HashMap<>();
        for (Color value : Color.values()) {
            List<ChessPiece> pieces = List.of(
                    new King(value),
                    new Queen(value),
                    new Pawn(value),
                    new Rook(value),
                    new Bishop(value),
                    new Knight(value));
            addPiece(pieces, board);
        }
        return new ChessBoard(board);
    }

    private static void addPiece(List<ChessPiece> pieces, Map<Position, ChessPiece> board) {
        for (ChessPiece chessPiece : pieces) {
            initByPiece(chessPiece, board);
        }
    }

    private static void initByPiece(ChessPiece chessPiece, Map<Position, ChessPiece> board) {
        if (chessPiece.isBlack()) {
            addBlackPiece(chessPiece, board);
            return;
        }
        addWhitePiece(chessPiece, board);
    }

    private static void addWhitePiece(ChessPiece chessPiece, Map<Position, ChessPiece> board) {
        for (Position position : chessPiece.getInitWhitePosition()) {
            board.put(position, chessPiece);
        }
    }

    private static void addBlackPiece(ChessPiece chessPiece, Map<Position, ChessPiece> board) {
        for (Position position : chessPiece.getInitBlackPosition()) {
            board.put(position, chessPiece);
        }
    }
}
