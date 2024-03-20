package util;

import domain.ChessBoard;
import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.PieceStatus;
import domain.piece.point.Point;
import dto.BoardDto;
import dto.PointDto;

import java.util.HashMap;
import java.util.Map;

public class BoardMapper {
    private BoardMapper() {
        throw new UnsupportedOperationException("생성할 수 없습니다.");
    }

    public static BoardDto toDto(ChessBoard chessBoard) {
        return new BoardDto(convertMap(chessBoard.toMap()));
    }

    private static Map<PointDto, String> convertMap(Map<Point, Piece> chessMap) {
        Map<PointDto, String> convertedMap = new HashMap<>();
        for (final var entry : chessMap.entrySet()) {
            convertedMap.put(convertPoint(entry.getKey()), convertPiece(entry.getValue()));
        }
        return convertedMap;
    }

    private static PointDto convertPoint(Point point) {
        return new PointDto(point.getRankIndex(), point.getFileIndex());
    }

    private static String convertPiece(Piece piece) {
        final var pieceName = convertStatus(piece.getStatus());
        if (piece.isEqualColor(Color.WHITE)) {
            return pieceName.toLowerCase();
        }
        return pieceName.toUpperCase();
    }

    private static String convertStatus(PieceStatus pieceStatus) {
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
