package dto;

import domain.game.Piece;
import domain.game.PieceType;
import domain.game.TeamColor;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

public record PieceDto(
        int fileIndex, int rankIndex,
        String color, String type
) {
    public static PieceDto of(Position position, Piece piece) {
        return new PieceDto(
                position.columnIndex(),
                position.rowIndex(),
                piece.color().name(),
                piece.getPieceType().name()
        );
    }

    public Position getPosition() {
        return new Position(File.of(fileIndex), Rank.of(rankIndex));
    }

    public PieceType getPieceType() {
        TeamColor teamColor = TeamColor.valueOf(color);
        return PieceType.fromColorAndType(teamColor, type);
    }
}
