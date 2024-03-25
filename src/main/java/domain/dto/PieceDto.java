package domain.dto;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.Type;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

public record PieceDto(
        String boardFile,
        String boardRank,
        String color,
        String type
) {
    public static PieceDto of(Position position, Piece piece) {
        return new PieceDto(
                position.file().name(),
                position.rank().number(),
                piece.color().name(),
                piece.type().name()
        );
    }

    public Position getPosition() {
        final File file = File.fromName(boardFile);
        final Rank rank = Rank.fromNumber(Integer.parseInt(boardRank));
        return new Position(file, rank);
    }

    public Piece getPiece() {
        final Color pieceColor = Color.valueOf(color);
        final Type pieceType = Type.valueOf(type);
        return pieceType.getPiece(pieceColor);
    }
}
