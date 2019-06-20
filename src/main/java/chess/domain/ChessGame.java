package chess.domain;

import java.util.*;


public class ChessGame {
    private final Map<ChessCoordinate, ChessPiece> boardState;

    public ChessGame(AbstractStateInitiatorFactory stateInitiatorFactory) {
        this.boardState = stateInitiatorFactory.create();
    }

    public Map<ChessCoordinate, PieceType> getBoard() {
        Map<ChessCoordinate, PieceType> state = new HashMap<>();
        boardState.entrySet().forEach(entry -> state.put(entry.getKey(), entry.getValue().getType()));
        return state;
    }

    public void move(ChessCoordinate from, ChessCoordinate to) {
        Set<ChessCoordinate> movableCoordinates = boardState.get(from).getMovableCoordinates(this::getTeamAt, from);

        movableCoordinates.stream()
                .filter(coord -> coord.equals(to))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("이동할 수 없는 위치입니다"));

        boardState.put(to, boardState.get(from));
        boardState.put(from, EmptyCell.getInstance());
    }


    Team getTeamAt(ChessCoordinate coord) {
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
