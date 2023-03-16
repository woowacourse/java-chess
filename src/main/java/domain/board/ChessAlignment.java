package domain.board;

import domain.piece.Piece;
import domain.position.Position;
import java.util.Map;

public interface ChessAlignment {
    void addInitialPawns(Map<Position, Piece> board);

    void addInitialKings(Map<Position, Piece> board);

    void addInitialQueens(Map<Position, Piece> board);

    void addInitialBishops(Map<Position, Piece> board);

    void addInitialKnights(Map<Position, Piece> board);

    void addInitialRooks(Map<Position, Piece> board);
}
