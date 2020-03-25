package chess.domain.chessBoard;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.PieceColor;
import chess.domain.chessPiece.pieceType.*;
import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class ChessBoardFactory {
    public static Map<Position, ChessPiece> create() {
        Map<Position, ChessPiece> initialBoard = new HashMap<>();
        addOtherPiecesBy(initialBoard, PieceColor.WHITE, 1);
        addPawnPiecesBy(initialBoard, PieceColor.WHITE, 2);

        addPawnPiecesBy(initialBoard, PieceColor.BLACK, 7);
        addOtherPiecesBy(initialBoard, PieceColor.BLACK, 8);

        return initialBoard;
    }

    private static void addPawnPiecesBy(Map<Position, ChessPiece> initialBoard, PieceColor pieceColor, int rank) {
        for (ChessFile chessFile : ChessFile.values()) {
            initialBoard.put(Position.of(chessFile, ChessRank.from(rank)), ChessPiece.of(pieceColor, Pawn.getInstance()));
        }
    }

    private static void addOtherPiecesBy(Map<Position, ChessPiece> initialBoard, PieceColor pieceColor, int rank) {
        initialBoard.put(Position.of("a" + rank), ChessPiece.of(pieceColor, Rook.getInstance()));
        initialBoard.put(Position.of("b" + rank), ChessPiece.of(pieceColor, Knight.getInstance()));
        initialBoard.put(Position.of("c" + rank), ChessPiece.of(pieceColor, Bishop.getInstance()));
        initialBoard.put(Position.of("d" + rank), ChessPiece.of(pieceColor, Queen.getInstance()));
        initialBoard.put(Position.of("e" + rank), ChessPiece.of(pieceColor, King.getInstance()));
        initialBoard.put(Position.of("f" + rank), ChessPiece.of(pieceColor, Bishop.getInstance()));
        initialBoard.put(Position.of("g" + rank), ChessPiece.of(pieceColor, Knight.getInstance()));
        initialBoard.put(Position.of("h" + rank), ChessPiece.of(pieceColor, Rook.getInstance()));
    }
}
