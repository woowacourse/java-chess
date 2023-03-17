package domain.board;

import domain.piece.Piece;
import domain.position.Position;

import java.util.HashMap;
import java.util.Map;

interface ChessAlignment {
    default Map<Position, Piece> init() {
        final Map<Position, Piece> board = new HashMap<>();
        addInitialPawns(board);
        addInitialKings(board);
        addInitialBishops(board);
        addInitialKnights(board);
        addInitialQueens(board);
        addInitialRooks(board);

        return board;
    }

    void addInitialPawns(final Map<Position, Piece> board);

    void addInitialKings(final Map<Position, Piece> board);

    void addInitialQueens(final Map<Position, Piece> board);

    void addInitialBishops(final Map<Position, Piece> board);

    void addInitialKnights(final Map<Position, Piece> board);

    void addInitialRooks(final Map<Position, Piece> board);
}
