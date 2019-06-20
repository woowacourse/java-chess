package chess.domain;

import chess.exception.AllyExistException;

import java.util.*;
import java.util.stream.Collectors;


public class ChessGame {
    private final Map<ChessCoordinate, ChessPiece> boardState;

    public ChessGame(List<List<ChessPiece>> initialBoardState) {
        this.boardState = initBoard(initialBoardState);
    }

    private Map<ChessCoordinate, ChessPiece> initBoard(List<List<ChessPiece>> initialBoardState) {
        Map<ChessCoordinate, ChessPiece> board = new HashMap<>();
        List<ChessYCoordinate> yAxis = ChessYCoordinate.getAscendingCoordinates(ChessYCoordinate.RANK_8);
        yAxis.add(0, ChessYCoordinate.RANK_8);
        List<ChessXCoordinate> xAxis = ChessXCoordinate.getAscendingCoordinates(ChessXCoordinate.A);
        xAxis.add(0, ChessXCoordinate.A);
        xAxis.stream()
                .forEach(x -> yAxis.forEach(y -> board.put(ChessCoordinate.valueOf(x, y).get(),
                        initialBoardState.get(y.getIndex()).get(x.getIndex()))));
        return board;
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
