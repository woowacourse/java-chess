package chess.domain;

import java.util.*;
import java.util.stream.Collectors;


public class ChessGame {
    private final Map<ChessCoordinate, ChessPiece> boardState;
    private Turn turn;

    public ChessGame(AbstractStateInitiatorFactory stateInitiatorFactory, Turn turn) {
        this.boardState = stateInitiatorFactory.create();
        this.turn = turn;
    }

    public Map<ChessCoordinate, PieceType> getBoard() {
        Map<ChessCoordinate, PieceType> state = new HashMap<>();
        boardState.entrySet().forEach(entry -> state.put(entry.getKey(), entry.getValue().getType()));
        return state;
    }

    public void move(ChessCoordinate from, ChessCoordinate to) {
        ChessPiece fromPiece = boardState.get(from);

        validateTurn(fromPiece);

        Set<ChessCoordinate> movableCoordinates = fromPiece.getMovableCoordinates(this::getTeamAt, from);

        movableCoordinates.stream()
                .filter(coord -> coord.equals(to))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("이동할 수 없는 위치입니다"));

        boardState.put(to, boardState.get(from));
        boardState.put(from, EmptyCell.getInstance());

        this.turn = turn.nextTurn();
    }

    private void validateTurn(ChessPiece fromPiece) {
        if (fromPiece.getType().getTeam() != turn.getCurrent()) {
            throw new IllegalArgumentException("해당 팀의 차례가 아닙니다.");
        }
    }

    public Set<String> getMovable(ChessCoordinate from) {
        return boardState.get(from).getMovableCoordinates(this::getTeamAt, from).stream()
                .map(coord -> coord.toString()).collect(Collectors.toSet());
    }

    Team getTeamAt(ChessCoordinate coord) {
        return this.boardState.get(coord).getType().getTeam();
    }

    public Team getTurn() {
        return turn.getCurrent();
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
