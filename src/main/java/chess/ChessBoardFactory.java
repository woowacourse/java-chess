package chess;

import chess.domain.chessPiece.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class ChessBoardFactory {
    public static Map<Position, Piece> create() {
        Map<Position, Piece> initialBoard = new HashMap<>();
        addOtherPiecesBy(initialBoard, PieceColor.WHITE, 1);
        addPawnPiecesBy(initialBoard, PieceColor.WHITE, 2);

        addPawnPiecesBy(initialBoard, PieceColor.BLACK, 7);
        addOtherPiecesBy(initialBoard, PieceColor.BLACK, 8);

        return initialBoard;
    }

    private static void addPawnPiecesBy(Map<Position, Piece> initialBoard, PieceColor pieceColor, int rank) {
        for (ChessFile chessFile : ChessFile.values()) {
            initialBoard.put(Position.of(chessFile, ChessRank.from(rank)), Piece.of(pieceColor, Pawn.getInstance()));
        }
    }

    private static void addOtherPiecesBy(Map<Position, Piece> initialBoard, PieceColor pieceColor, int rank) {
        initialBoard.put(Position.of("a" + rank), Piece.of(pieceColor, Rook.getInstance()));
        initialBoard.put(Position.of("b" + rank), Piece.of(pieceColor, Knight.getInstance()));
        initialBoard.put(Position.of("c" + rank), Piece.of(pieceColor, Bishop.getInstance()));
        initialBoard.put(Position.of("d" + rank), Piece.of(pieceColor, Queen.getInstance()));
        initialBoard.put(Position.of("e" + rank), Piece.of(pieceColor, King.getInstance()));
        initialBoard.put(Position.of("f" + rank), Piece.of(pieceColor, Bishop.getInstance()));
        initialBoard.put(Position.of("g" + rank), Piece.of(pieceColor, Knight.getInstance()));
        initialBoard.put(Position.of("h" + rank), Piece.of(pieceColor, Rook.getInstance()));
    }
}
