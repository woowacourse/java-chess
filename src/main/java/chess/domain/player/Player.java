package chess.domain.player;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.player.type.TeamColor;
import chess.domain.position.Position;

public class Player {
    private final Pieces pieces = new Pieces();
    private final TeamColor teamColor;

    public Player(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    public void give(Piece piece, Position position) {
        pieces.add(piece, position);
    }

    public void removePiece(Piece piece, Position position) {
        pieces.remove(piece, position);
    }

    public double score() {
        return pieces.getScore();
    }

    public TeamColor teamColor() {
        return teamColor;
    }
}
