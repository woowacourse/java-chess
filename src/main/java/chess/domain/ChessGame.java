package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ChessGame {
    private GameBoardState boardState;

    public ChessGame(AbstractBoardStateFactory stateInitiatorFactory) {
        this.boardState = stateInitiatorFactory.create();
    }

    public Map<CoordinatePair, PieceType> getBoardState() {
        Map<CoordinatePair, PieceType> typeState = new HashMap<>();
        boardState.entryStream().forEach(entry -> typeState.put(entry.getKey(), entry.getValue().getType()));
        return typeState;
    }

    public void move(CoordinatePair from, CoordinatePair to) {
        Set<CoordinatePair> movableCoordinates = boardState.at(from).getMovableCoordinates(this::getTeamAt, from);

        if (movableCoordinates.contains(to)) {
            this.boardState = boardState.movePiece(from, to);
            return;
        }

        throw new IllegalArgumentException("이동할 수 없는 위치입니다: " + to);
    }

    Team getTeamAt(CoordinatePair coord) {
        return this.boardState.at(coord).getType().getTeam();
    }
}
