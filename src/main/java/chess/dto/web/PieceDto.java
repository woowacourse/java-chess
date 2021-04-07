package chess.dto.web;

import chess.domain.board.Point;
import chess.domain.board.SquareState;
import chess.domain.board.Team;
import chess.domain.piece.Piece;

public class PieceDto {

    private static final String EMPTY_PIECE = "e";
    private static final String BLACK = "b";
    private static final String WHITE = "w";
    private static final String NONE = "n";

    private final String team;
    private final String piece;
    private final String x;
    private final String y;

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

    public Team teamForEntity() {
        if (team.equals(BLACK)) {
            return Team.BLACK;
        }
        if (team.equals(WHITE)) {
            return Team.WHITE;
        }
        return Team.NONE;
    }

    private Piece pieceForEntity() {
        if (piece.equals(EMPTY_PIECE)) {
            return Piece.EMPTY;
        }
        return Piece.pieceByName(piece);
    }

    public SquareState toSquareStateEntity() {
        Piece piece = pieceForEntity();
        Team team = teamForEntity();
        return SquareState.of(piece, team);
    }

    public Point toPointEntity() {
        return Point.of(x + y);
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
