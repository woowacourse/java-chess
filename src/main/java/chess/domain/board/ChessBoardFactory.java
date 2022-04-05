package chess.domain.board;

import java.util.HashMap;
import java.util.Map;
import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public class ChessBoardFactory {

    private ChessBoardFactory() {
    }

    public static Map<Position, Piece> initChessBoard() {
        final Map<Position, Piece> squares = new HashMap<>();

        initBlank(squares);
        initNotPawnSquares(squares, 1, PieceColor.WHITE);
        initPawnPieces(squares, 2, PieceColor.WHITE);
        initPawnPieces(squares, 7, PieceColor.BLACK);
        initNotPawnSquares(squares, 8, PieceColor.BLACK);

        return squares;
    }

    private static void initBlank(Map<Position, Piece> squares) {
        for (Position position : Position.values()) {
            squares.put(position, new Blank(position));
        }
    }

    private static void initPawnPieces(Map<Position, Piece> squares, int row, PieceColor pieceColor) {
        squares.replace(Position.valueOf("a" + row), new Pawn(pieceColor, Position.valueOf("a" + row)));
        squares.replace(Position.valueOf("b" + row), new Pawn(pieceColor, Position.valueOf("b" + row)));
        squares.replace(Position.valueOf("c" + row), new Pawn(pieceColor, Position.valueOf("c" + row)));
        squares.replace(Position.valueOf("d" + row), new Pawn(pieceColor, Position.valueOf("d" + row)));
        squares.replace(Position.valueOf("e" + row), new Pawn(pieceColor, Position.valueOf("e" + row)));
        squares.replace(Position.valueOf("f" + row), new Pawn(pieceColor, Position.valueOf("f" + row)));
        squares.replace(Position.valueOf("g" + row), new Pawn(pieceColor, Position.valueOf("g" + row)));
        squares.replace(Position.valueOf("h" + row), new Pawn(pieceColor, Position.valueOf("h" + row)));
    }

    private static void initNotPawnSquares(Map<Position, Piece> squares, int row, PieceColor pieceColor) {
        squares.replace(Position.valueOf("a" + row), new Rook(pieceColor, Position.valueOf("a" + row)));
        squares.replace(Position.valueOf("b" + row), new Knight(pieceColor, Position.valueOf("b" + row)));
        squares.replace(Position.valueOf("c" + row), new Bishop(pieceColor, Position.valueOf("c" + row)));
        squares.replace(Position.valueOf("d" + row), new Queen(pieceColor, Position.valueOf("d" + row)));
        squares.replace(Position.valueOf("e" + row), new King(pieceColor, Position.valueOf("e" + row)));
        squares.replace(Position.valueOf("f" + row), new Bishop(pieceColor, Position.valueOf("f" + row)));
        squares.replace(Position.valueOf("g" + row), new Knight(pieceColor, Position.valueOf("g" + row)));
        squares.replace(Position.valueOf("h" + row), new Rook(pieceColor, Position.valueOf("h" + row)));
    }
}
