package chess.dto.piece;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.team.Team;
import chess.entity.PieceEntity;

public class PieceDto {

    private final Team team;
    private final char pieceLetter;
    private final int x;
    private final int y;

    private PieceDto(final Team team, final char pieceLetter, int x, int y) {
        this.team = team;
        this.pieceLetter = pieceLetter;
        this.x = x;
        this.y = y;
    }

    public static PieceDto from(final Piece piece) {
        return new PieceDto(piece.getTeam(), piece.getPieceType().getValue(),
            piece.getX(), piece.getY());
    }

    public static PieceDto from(final PieceEntity pieceEntity) {
        return new PieceDto(pieceEntity.getTeam(), pieceEntity.getPieceType().getValue(),
            pieceEntity.getX(), pieceEntity.getY());
    }

    public PieceEntity toEntity(final long gameId) {
        return new PieceEntity(0L, gameId, PieceType.from(pieceLetter), team, x, y);
    }

    public Team getTeam() {
        return team;
    }

    public char getPieceLetter() {
        return pieceLetter;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
