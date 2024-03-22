package util;

import domain.ChessBoard;
import domain.piece.Piece;
import domain.piece.kind.PieceStatus;
import domain.piece.attribute.point.Point;
import dto.BoardDto;
import dto.PointDto;

import java.util.HashMap;
import java.util.Map;

public class BoardMapper {
    private BoardMapper() {
        throw new UnsupportedOperationException("생성할 수 없습니다.");
    }

    public static BoardDto toDto(final ChessBoard chessBoard) {
        return new BoardDto(convertMap(chessBoard.toMap()));
    }

    private static Map<PointDto, String> convertMap(final Map<Point, Piece> chessMap) {
        final Map<PointDto, String> convertedMap = new HashMap<>();
        for (final var entry : chessMap.entrySet()) {
            convertedMap.put(convertPoint(entry.getKey()), convertPiece(entry.getValue()));
        }
        return convertedMap;
    }

    private static PointDto convertPoint(final Point point) {
        return new PointDto(point.getRankIndex(), point.getFileIndex());
    }

    private static String convertPiece(final Piece piece) {
        final var pieceName = convertStatus(piece.getStatus());
        if (piece.isWhite()) {
            return pieceName.toLowerCase();
        }
        return pieceName.toUpperCase();
    }

    private static String convertStatus(final PieceStatus pieceStatus) {
        return switch (pieceStatus) {
            case ROOK -> "R";
            case BISHOP -> "B";
            case KNIGHT -> "N";
            case QUEEN -> "Q";
            case KING -> "K";
            case PAWN -> "P";
        };
    }
}
