package domain.board;

import domain.piece.Piece;
import domain.piece.Team;
import domain.position.Position;
import gameinitializer.ChessGameInitializer;
import java.util.HashMap;
import java.util.Map;

public final class ChessAlignmentMock {
    private static abstract class Base implements ChessGameInitializer {

        @Override
        public Team initTeam() {
            return null;
        }
    }

    static ChessGameInitializer testStrategy(Map<Position, Piece> map) {
        return new Base() {
            @Override
            public Map<Position, Piece> initPiecePosition() {
                return new HashMap<>(map);
            }
        };
    }

    public static ChessGameInitializer testStrategyWithTeam(Map<Position, Piece> map, Team team) {
        return new Base() {
            @Override
            public Map<Position, Piece> initPiecePosition() {
                return new HashMap<>(map);
            }

            @Override
            public Team initTeam() {
                return team;
            }
        };
    }
}
