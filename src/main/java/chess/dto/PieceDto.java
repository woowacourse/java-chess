package chess.dto;

import chess.domain.board.Point;
import chess.domain.board.Team;
import chess.domain.piece.Piece;

public class PieceDto {

    private static final String EMPTY_PIECE = "e";
    private static final String BLACK = "b";
    private static final String WHITE = "w";
    private static final String NONE = "n";

    private String team;
    private String piece;
    private String x;
    private String y;

    public PieceDto(Point point, Team team, Piece piece) {
        this.piece = pieceNameForDto(piece);
        this.team = teamNameForDto(team);
        this.x = point.xCoordinate();
        this.y = point.yCoordinate();
    }

    private String pieceNameForDto(Piece piece) {
        if (piece == Piece.EMPTY) {
            return EMPTY_PIECE;
        }
        return piece.pieceName();
    }

    private String teamNameForDto(Team team) {
        if (team.isBlack()) {
            return BLACK;
        }
        if (team.isWhite()) {
            return WHITE;
        }
        return NONE;
    }

    public String getTeam() {
        return team;
    }

    public String getPiece() {
        return piece;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }
}
