package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


public class ChessGame {
    private final Map<CoordinatePair, ChessPiece> boardState;

    public ChessGame(AbstractStateInitiatorFactory stateInitiatorFactory) {
        this.boardState = stateInitiatorFactory.create();
    }

    public Map<CoordinatePair, PieceType> getBoard() {
        Map<CoordinatePair, PieceType> state = new HashMap<>();
        boardState.forEach((coord, piece) -> state.put(coord, piece.getType()));
        return state;
    }

    public void move(CoordinatePair from, CoordinatePair to) {
        Set<CoordinatePair> movableCoordinates = boardState.get(from).getMovableCoordinates(this::getTeamAt, from);

        if (!movableCoordinates.contains(to)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다: " + to);
        }

        boardState.put(to, boardState.get(from));
        boardState.put(from, EmptyCell.getInstance());
    }


    Team getTeamAt(CoordinatePair coord) {
        return this.boardState.get(coord).getType().getTeam();
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
