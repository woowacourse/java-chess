package chess.domain;

import chess.domain.piece.Piece;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> positionMap;

    public Board(Map<Position, Piece> positionMap) {
        this.positionMap = positionMap;
    }

    public boolean containsPosition(Position position) {
        return positionMap.containsKey(position);
    }

    public Piece pieceAt(Position position) {
        if (!positionMap.containsKey(position)) {
            throw new IllegalArgumentException("[ERROR] 해당 좌표에 말이 존재하지 않습니다.");
        }
        return positionMap.get(position);
    }

    public void isSameTeam(Position position, Team team) {
        if (!positionMap.containsKey(position)) {
            return;
        }

        if (positionMap.get(position).isSameTeam(team)) {
            throw new IllegalArgumentException("[ERROR] 해당 좌표에 같은 팀의 말이 존재합니다.");
        }
    }

    public void movePiece(Position source, Position target) {
        positionMap.put(target, positionMap.get(source));
        positionMap.remove(source);
    }
}
