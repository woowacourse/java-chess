package chess.domain;

import chess.domain.piece.Piece;

import java.util.*;

public class ChessInitialPosition {
    private static Map<ChessPieceInfo, List<Position>> positions = new HashMap<>();

    static {
        positions.put(ChessPieceInfo.BLACK_PAWN, Arrays.asList(
                Position.getPosition(1, 7),
                Position.getPosition(2, 7),
                Position.getPosition(3, 7),
                Position.getPosition(4, 7),
                Position.getPosition(5, 7),
                Position.getPosition(6, 7),
                Position.getPosition(7, 7),
                Position.getPosition(8, 7)));
        positions.put(ChessPieceInfo.WHITE_PAWN, Arrays.asList(
                Position.getPosition(1, 2),
                Position.getPosition(2, 2),
                Position.getPosition(3, 2),
                Position.getPosition(4, 2),
                Position.getPosition(5, 2),
                Position.getPosition(6, 2),
                Position.getPosition(7, 2),
                Position.getPosition(8, 2)));

        positions.put(ChessPieceInfo.BLACK_ROOK, Arrays.asList(
                Position.getPosition(1, 8),
                Position.getPosition(8, 8)));
        positions.put(ChessPieceInfo.WHITE_ROOK, Arrays.asList(
                Position.getPosition(1, 1),
                Position.getPosition(8, 1)));

        positions.put(ChessPieceInfo.BLACK_KNIGHT, Arrays.asList(
                Position.getPosition(2, 8),
                Position.getPosition(7, 8)));
        positions.put(ChessPieceInfo.WHITE_KNIGHT, Arrays.asList(
                Position.getPosition(2, 1),
                Position.getPosition(7, 1)));

        positions.put(ChessPieceInfo.BLACK_BISHOP, Arrays.asList(
                Position.getPosition(3, 8),
                Position.getPosition(6, 8)));
        positions.put(ChessPieceInfo.WHITE_BISHOP, Arrays.asList(
                Position.getPosition(3, 1),
                Position.getPosition(6, 1)));

        positions.put(ChessPieceInfo.BLACK_QUEEN, Arrays.asList(
                Position.getPosition(4, 8)));
        positions.put(ChessPieceInfo.WHITE_QUEEN, Arrays.asList(
                Position.getPosition(4, 1)));

        positions.put(ChessPieceInfo.BLACK_KING, Arrays.asList(
                Position.getPosition(5, 8)));
        positions.put(ChessPieceInfo.WHITE_KING, Arrays.asList(
                Position.getPosition(5, 1)));

    }

    public static ChessBoard generateChessBoard() {
        List<Piece> pieces = new ArrayList<>();
        for (ChessPieceInfo chessPieceInfo : positions.keySet()) {
            pieces.addAll(generatePieces(chessPieceInfo));
        }
        return new ChessBoard(pieces);
    }

    private static List<Piece> generatePieces(ChessPieceInfo chessPieceInfo) {
        List<Piece> pieces = new ArrayList<>();
        for (Position position : positions.get(chessPieceInfo)) {
            pieces.add(chessPieceInfo.generate(position));
        }
        return pieces;
    }
}
