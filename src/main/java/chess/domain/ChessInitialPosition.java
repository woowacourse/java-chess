package chess.domain;

import chess.domain.piece.Piece;

import java.util.*;

public class ChessInitialPosition {
    private static Map<ChessPiece, List<Position>> positions = new HashMap<>();

    static {
        positions.put(ChessPiece.BLACK_PAWN, Arrays.asList(
                Position.getPosition(1, 7),
                Position.getPosition(2, 7),
                Position.getPosition(3, 7),
                Position.getPosition(4, 7),
                Position.getPosition(5, 7),
                Position.getPosition(6, 7),
                Position.getPosition(7, 7),
                Position.getPosition(8, 7)));
        positions.put(ChessPiece.WHITE_PAWN, Arrays.asList(
                Position.getPosition(1, 2),
                Position.getPosition(2, 2),
                Position.getPosition(3, 2),
                Position.getPosition(4, 2),
                Position.getPosition(5, 2),
                Position.getPosition(6, 2),
                Position.getPosition(7, 2),
                Position.getPosition(8, 2)));

        positions.put(ChessPiece.BLACK_ROOK, Arrays.asList(
                Position.getPosition(1, 8),
                Position.getPosition(8, 8)));
        positions.put(ChessPiece.WHITE_ROOK, Arrays.asList(
                Position.getPosition(1, 1),
                Position.getPosition(8, 1)));

        positions.put(ChessPiece.BLACK_KNIGHT, Arrays.asList(
                Position.getPosition(2, 8),
                Position.getPosition(7, 8)));
        positions.put(ChessPiece.WHITE_KNIGHT, Arrays.asList(
                Position.getPosition(2, 1),
                Position.getPosition(7, 1)));

        positions.put(ChessPiece.BLACK_BISHOP, Arrays.asList(
                Position.getPosition(3, 8),
                Position.getPosition(6, 8)));
        positions.put(ChessPiece.WHITE_BISHOP, Arrays.asList(
                Position.getPosition(3, 1),
                Position.getPosition(6, 1)));

        positions.put(ChessPiece.BLACK_QUEEN, Arrays.asList(
                Position.getPosition(4, 8)));
        positions.put(ChessPiece.WHITE_QUEEN, Arrays.asList(
                Position.getPosition(4, 1)));

        positions.put(ChessPiece.BLACK_KING, Arrays.asList(
                Position.getPosition(5, 8)));
        positions.put(ChessPiece.WHITE_KING, Arrays.asList(
                Position.getPosition(5, 1)));

    }

    public static ChessBoard generateChessBoard() {
        List<Piece> pieces = new ArrayList<>();
        for (ChessPiece chessPiece : positions.keySet()) {
            pieces.addAll(generatePieces(chessPiece));
        }
        return new ChessBoard(pieces);
    }

    private static List<Piece> generatePieces(ChessPiece chessPiece) {
        List<Piece> pieces = new ArrayList<>();
        for (Position position : positions.get(chessPiece)) {
            pieces.add(chessPiece.generate(position));
        }
        return pieces;
    }
}
