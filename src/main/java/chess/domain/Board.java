package chess.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Board {
    private final Map<ChessCoordinate, ChessPiece> boardState;

    public Board(AbstractStateInitiatorFactory stateInitiatorFactory) {
        this.boardState = stateInitiatorFactory.create();
    }

    public Map<ChessCoordinate, PieceType> getBoardState() {
        Map<ChessCoordinate, PieceType> state = new HashMap<>();
        boardState.entrySet().forEach(entry -> state.put(entry.getKey(), entry.getValue().getType()));
        return state;
    }

    public ChessPiece getPieceByCoord(ChessCoordinate from) {
        return boardState.get(from);
    }

    public void updateBoard(ChessCoordinate from, ChessCoordinate to) {
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
        Board board = (Board) o;
        return Objects.equals(boardState, board.boardState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardState);
    }
}
