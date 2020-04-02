package chess.domain.chessBoard;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.*;
import chess.domain.position.ChessFile;
import chess.domain.position.ChessRank;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class ChessBoardFactory {

    public static Map<Position, ChessPiece> create() {
        Map<Position, ChessPiece> chessBoard = new HashMap<>();

        addOtherPiecesBy(chessBoard, PieceColor.WHITE, ChessRank.ONE);
        addPawnPiecesBy(chessBoard, PieceColor.WHITE, ChessRank.TWO);
        addPawnPiecesBy(chessBoard, PieceColor.BLACK, ChessRank.SEVEN);
        addOtherPiecesBy(chessBoard, PieceColor.BLACK, ChessRank.EIGHT);
        return chessBoard;
    }

    private static void addPawnPiecesBy(Map<Position, ChessPiece> chessBoard, PieceColor pieceColor, ChessRank rank) {
        for (ChessFile file : ChessFile.values()) {
            chessBoard.put(Position.of(file, rank), new Pawn(pieceColor));
        }
    }

    private static void addOtherPiecesBy(Map<Position, ChessPiece> chessBoard, PieceColor pieceColor, ChessRank rank) {
        chessBoard.put(Position.of(ChessFile.A, rank), new Rook(pieceColor));
        chessBoard.put(Position.of(ChessFile.B, rank), new Knight(pieceColor));
        chessBoard.put(Position.of(ChessFile.C, rank), new Bishop(pieceColor));
        chessBoard.put(Position.of(ChessFile.D, rank), new Queen(pieceColor));
        chessBoard.put(Position.of(ChessFile.E, rank), new King(pieceColor));
        chessBoard.put(Position.of(ChessFile.F, rank), new Bishop(pieceColor));
        chessBoard.put(Position.of(ChessFile.G, rank), new Knight(pieceColor));
        chessBoard.put(Position.of(ChessFile.H, rank), new Rook(pieceColor));
    }

}
