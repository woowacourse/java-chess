package chess.view;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.HashMap;
import java.util.Map;

class PieceCharacters {

    private static final Map<Piece, Character> POOL = createPool();

    static char characterFrom(Piece piece) {
        return POOL.get(piece);
    }

    private static Map<Piece, Character> createPool() {
        final Map<Piece, Character> pieces = new HashMap<>();

        pieces.put(Piece.kingFrom(Team.WHITE), 'k');
        pieces.put(Piece.kingFrom(Team.BLACK), 'K');
        pieces.put(Piece.queenFrom(Team.WHITE), 'q');
        pieces.put(Piece.queenFrom(Team.BLACK), 'Q');
        pieces.put(Piece.bishopFrom(Team.WHITE), 'b');
        pieces.put(Piece.bishopFrom(Team.BLACK), 'B');
        pieces.put(Piece.rookFrom(Team.WHITE), 'r');
        pieces.put(Piece.rookFrom(Team.BLACK), 'R');
        pieces.put(Piece.knightFrom(Team.WHITE), 'n');
        pieces.put(Piece.knightFrom(Team.BLACK), 'N');
        pieces.put(Piece.pawnFrom(Team.WHITE), 'p');
        pieces.put(Piece.pawnFrom(Team.BLACK), 'P');
        pieces.put(Piece.empty(), '.');

        return pieces;
    }
}
