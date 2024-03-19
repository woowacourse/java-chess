package chess.model;

import chess.model.piece.Bishop;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.PieceType;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Board {

    private final Map<Position, Piece> board;

    public Board() {
        this.board = generateBoard();
    }

    private Map<Position, Piece> generateBoard() {
        Map<Position, Piece> board = new HashMap<>();
        generateWhitePieces(board);
        generateBlackPieces(board);
        generatePawns(board, 2, PieceType.WHITE_PAWN);
        generatePawns(board, 7, PieceType.BLACK_PAWN);
        return board;
    }

    private void generateWhitePieces(Map<Position, Piece> board) {
        Position leftRookPosition = new Position(1, 1);
        Position leftKnightPosition = new Position(1, 2);
        Position leftBishopPosition = new Position(1, 3);
        Position queenPosition = new Position(1, 4);
        Position kingPosition = new Position(1, 5);
        Position rightBishopPosition = new Position(1, 6);
        Position rightKnightPosition = new Position(1, 7);
        Position rightRookPosition = new Position(1, 8);

        Piece leftRook = new Rook(PieceType.WHITE_ROOK);
        Piece leftKnight = new Knight(PieceType.WHITE_KNIGHT);
        Piece leftBishop = new Bishop(PieceType.WHITE_BISHOP);
        Piece queen = new Queen(PieceType.WHITE_QUEEN);
        Piece king = new King(PieceType.WHITE_KING);
        Piece rightRook = new Rook(PieceType.WHITE_ROOK);
        Piece rightKnight = new Knight(PieceType.WHITE_KNIGHT);
        Piece rightBishop = new Bishop(PieceType.WHITE_BISHOP);

        board.put(leftRookPosition, leftRook);
        board.put(leftKnightPosition, leftKnight);
        board.put(leftBishopPosition, leftBishop);
        board.put(queenPosition, queen);
        board.put(kingPosition, king);
        board.put(rightBishopPosition, rightBishop);
        board.put(rightKnightPosition, rightKnight);
        board.put(rightRookPosition, rightRook);
    }

    private void generateBlackPieces(Map<Position, Piece> board) {
        Position leftRookPosition = new Position(8, 1);
        Position leftKnightPosition = new Position(8, 2);
        Position leftBishopPosition = new Position(8, 3);
        Position queenPosition = new Position(8, 4);
        Position kingPosition = new Position(8, 5);
        Position rightBishopPosition = new Position(8, 6);
        Position rightKnightPosition = new Position(8, 7);
        Position rightRookPosition = new Position(8, 8);

        Piece leftRook = new Rook(PieceType.BLACK_ROOK);
        Piece leftKnight = new Knight(PieceType.BLACK_KNIGHT);
        Piece leftBishop = new Bishop(PieceType.BLACK_BISHOP);
        Piece queen = new Queen(PieceType.BLACK_QUEEN);
        Piece king = new King(PieceType.BLACK_KING);
        Piece rightRook = new Rook(PieceType.BLACK_ROOK);
        Piece rightKnight = new Knight(PieceType.BLACK_KNIGHT);
        Piece rightBishop = new Bishop(PieceType.BLACK_BISHOP);

        board.put(leftRookPosition, leftRook);
        board.put(leftKnightPosition, leftKnight);
        board.put(leftBishopPosition, leftBishop);
        board.put(queenPosition, queen);
        board.put(kingPosition, king);
        board.put(rightBishopPosition, rightBishop);
        board.put(rightKnightPosition, rightKnight);
        board.put(rightRookPosition, rightRook);
    }

    private void generatePawns(Map<Position, Piece> board, int row, PieceType pieceType) {
        for (int i = 1; i <= 8; i++) {
            Position position = new Position(row, i);
            Piece piece = new Pawn(pieceType);
            board.put(position, piece);
        }
    }

    public Optional<Piece> findPiece(Position position) {
        if (board.containsKey(position)) {
            return Optional.of(board.get(position));
        }
        return Optional.empty();
    }
}
