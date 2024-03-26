package chess.view;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.HashMap;
import java.util.Map;

class PieceCharacters {

    private static final Map<Piece, Character> POOL;

    static char characterFrom(Piece piece) {
        Character c = POOL.get(piece);
        if (c == null) {
            throw new IllegalStateException("체스에 존재하지 않는 기물이 있습니다. 게임을 종료합니다.");
        }
        return c;
    }

    static {
        POOL = new HashMap<>();

        POOL.put(Piece.kingFrom(Team.WHITE), 'k');
        POOL.put(Piece.kingFrom(Team.BLACK), 'K');
        POOL.put(Piece.queenFrom(Team.WHITE), 'q');
        POOL.put(Piece.queenFrom(Team.BLACK), 'Q');
        POOL.put(Piece.bishopFrom(Team.WHITE), 'b');
        POOL.put(Piece.bishopFrom(Team.BLACK), 'B');
        POOL.put(Piece.rookFrom(Team.WHITE), 'r');
        POOL.put(Piece.rookFrom(Team.BLACK), 'R');
        POOL.put(Piece.knightFrom(Team.WHITE), 'n');
        POOL.put(Piece.knightFrom(Team.BLACK), 'N');
        POOL.put(Piece.pawnFrom(Team.WHITE), 'p');
        POOL.put(Piece.pawnFrom(Team.BLACK), 'P');
        POOL.put(Piece.empty(), '.');
    }
}
