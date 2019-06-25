package chess.domain;

import chess.domain.boardcell.PieceType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


public class ChessGame {
    private LivingPieceGroup boardState;

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

    public Team getTeamAt(CoordinatePair coord) {
        if (boardState.at(coord) == null) {
            return Team.NEUTRAL;
        }
        return this.boardState.at(coord).getType().getTeam();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessGame chessGame = (ChessGame) o;
        return Objects.equals(boardState, chessGame.boardState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardState);
    }
}
