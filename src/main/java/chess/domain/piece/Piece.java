package chess.domain.piece;

import chess.domain.position.Position;
import chess.strategy.OccupiedChecker;

public interface Piece {

    void move(Position position, OccupiedChecker isOccupied);

    void kill(Piece targetPiece, OccupiedChecker isOccupied);

    boolean isPawn();

    boolean isKing();

    double score();

    boolean hasColorOf(Color color);

    boolean isAt(Position position);

    boolean isAtFileOrColumnIdxOf(int idx);

    Position position();

    String display();
}
