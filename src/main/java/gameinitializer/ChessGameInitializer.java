package gameinitializer;

import domain.piece.Piece;
import domain.piece.Team;
import domain.position.Position;
import java.util.Map;

public interface ChessGameInitializer {
    Map<Position, Piece> initPiecePosition();

    Team initTeam();
}
