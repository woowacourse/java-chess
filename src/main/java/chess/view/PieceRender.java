package chess.view;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

import chess.domain.Team;
import chess.domain.pieces.EmptyPiece;
import chess.domain.pieces.Piece;
import chess.domain.pieces.sliding.Bishop;
import chess.domain.pieces.sliding.Queen;
import chess.domain.pieces.sliding.Rook;
import chess.domain.pieces.nonsliding.King;
import chess.domain.pieces.nonsliding.Knight;
import chess.domain.pieces.pawn.Pawn;

public final class PieceRender {

    private static final char PAWN = 'P';
    private static final char ROOK = 'R';
    private static final char KNIGHT = 'N';
    private static final char BISHOP = 'B';
    private static final char QUEEN = 'Q';
    private static final char KING = 'K';
    private static final char EMPTY = '.';

    public static char render(Piece piece) {
        if (piece instanceof EmptyPiece) {
            return EMPTY;
        }
        if (piece instanceof Pawn) {
            return checkTeam(piece.getTeam(), PAWN);
        }
        if (piece instanceof Rook) {
            return checkTeam(piece.getTeam(), ROOK);
        }
        if (piece instanceof Knight) {
            return checkTeam(piece.getTeam(), KNIGHT);
        }
        if (piece instanceof Bishop) {
            return checkTeam(piece.getTeam(), BISHOP);
        }
        if (piece instanceof Queen) {
            return checkTeam(piece.getTeam(), QUEEN);
        }
        if (piece instanceof King) {
            return checkTeam(piece.getTeam(), KING);
        }

        throw new IllegalStateException();
    }

    public static char checkTeam(Team team, char name) {
        if (team == Team.BLACK) {
            return toUpperCase(name);
        }
        return toLowerCase(name);
    }
}
