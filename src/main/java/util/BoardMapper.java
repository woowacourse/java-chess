package util;

import domain.ChessBoard;
import domain.chess.piece.Piece;
import domain.chess.piece.PieceStatus;
import domain.chess.Point;
import dto.BoardDto;
import dto.PointDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardMapper {
    private BoardMapper() {
        throw new UnsupportedOperationException("생성할 수 없습니다.");
    }

    public static BoardDto toDto(final ChessBoard chessBoard) {
        return new BoardDto(convertMap(chessBoard.getPieces()));
    }

    private static Map<PointDto, String> convertMap(final List<Piece> pieces) {
        final Map<PointDto, String> convertedMap = new HashMap<>();
        for (final Piece piece : pieces) {
            convertedMap.put(convertPoint(piece.getPoint()), convertPiece(piece));

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
