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

        addOtherPiecesBy(chessBoard, PieceColor.WHITE, ChessRank.from(1));
        addPawnPiecesBy(chessBoard, PieceColor.WHITE, ChessRank.from(2));
        addPawnPiecesBy(chessBoard, PieceColor.BLACK, ChessRank.from(7));
        addOtherPiecesBy(chessBoard, PieceColor.BLACK, ChessRank.from(8));
        return chessBoard;
    }

    private static void addPawnPiecesBy(Map<Position, ChessPiece> chessBoard, PieceColor pieceColor, ChessRank rank) {
        for (ChessFile file : ChessFile.values()) {
            chessBoard.put(Position.of(file, rank), new Pawn(pieceColor));
        }
    }

    private static void addOtherPiecesBy(Map<Position, ChessPiece> chessBoard, PieceColor pieceColor, ChessRank rank) {
        chessBoard.put(Position.of(ChessFile.from('a'), rank), new Rook(pieceColor));
        chessBoard.put(Position.of(ChessFile.from('c'), rank), new Bishop(pieceColor));
        chessBoard.put(Position.of(ChessFile.from('b'), rank), new Knight(pieceColor));
        chessBoard.put(Position.of(ChessFile.from('d'), rank), new Queen(pieceColor));
        chessBoard.put(Position.of(ChessFile.from('e'), rank), new King(pieceColor));
        chessBoard.put(Position.of(ChessFile.from('f'), rank), new Bishop(pieceColor));
        chessBoard.put(Position.of(ChessFile.from('g'), rank), new Knight(pieceColor));
        chessBoard.put(Position.of(ChessFile.from('h'), rank), new Rook(pieceColor));
    }

}
