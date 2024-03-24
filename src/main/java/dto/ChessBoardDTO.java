package dto;

import domain.File;
import domain.Rank;
import domain.Square;
import domain.piece.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record ChessBoardDTO(List<String> pieces) {
    public static ChessBoardDTO from(final Map<Square, Piece> pieceMap) {
        final List<String> pieces = new ArrayList<>();
        for (int rank = 8; rank >= 1; rank--) {
            for (int file = 1; file <= 8; file++) {
                final Square square = new Square(File.from(file), Rank.from(rank));
                final Piece piece = pieceMap.get(square);
                final String pieceString = getPieceString(piece);
                pieces.add(pieceString);
            }
        }
        return new ChessBoardDTO(pieces);
    }

    private static String getPieceString(final Piece piece) {
        if (piece == null) return ".";
        if (piece instanceof King) {
            return piece.isBlack() ? "K" : "k";
        } else if (piece instanceof Queen) {
            return piece.isBlack() ? "Q" : "q";
        } else if (piece instanceof Bishop) {
            return piece.isBlack() ? "B" : "b";
        } else if (piece instanceof Knight) {
            return piece.isBlack() ? "N" : "n";
        } else if (piece instanceof Rook) {
            return piece.isBlack() ? "R" : "r";
        } else if (piece instanceof Pawn) {
            return piece.isBlack() ? "P" : "p";
        }
        throw new IllegalStateException("존재하지 않는 피스 타입 출력을 시도했습니다.");
    }
}
