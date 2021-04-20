package chess.dto.piece;

import chess.domain.piece.Piece;
import chess.domain.team.Team;

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
