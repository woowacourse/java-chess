package chess.domain.team.player;

import chess.domain.piece.Piece;
import chess.domain.team.Team;

import java.util.List;

public class Player {

    private final Team team;
    private final List<Piece> pieces;

    private Player(final Team team, List<Piece> pieces) {
        this.team = team;
        this.pieces = pieces;
    }

    public static Player of(final Team team, PieceGenerator pieceGenerator) {
        return new Player(team, pieceGenerator.generate());
    }

    public boolean containPiece(final Piece piece) {
        return pieces.contains(piece);
    }
}
