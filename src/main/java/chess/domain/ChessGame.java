package chess.domain;

import chess.domain.board.Board;
import chess.domain.state.GameState;
import chess.domain.piece.Position;

public class ChessGame {
    private Long id;
    private GameState state;

    public ChessGame(GameState state) {
        this.state = state;
    }

    public ChessGame(Long id, GameState state) {
        this.id = id;
        this.state = state;
    }

    public GameState getState() {
        return state;
    }

    public Board getBoard() {
        return state.getBoard();
    }

    public String getTurn() {
        if (state.isBlackTurn()) {
            return "흑팀";
        }
        return "백팀";
    }

    public void terminate() {
        state = state.terminate();
    }

    public void move(String position1, String position2) {
        state = state.move(new Position(position1), new Position(position2));
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    public Long getId() {
        return id;
    }

    public String getWinnerName() {
        return state.findWinner().getName();
    }

    public double getWhiteScore() {
        return state.calculateWhiteScore();
    }

    public double getBlackScore() {
        return state.calculateBlackScore();
    }

    public boolean isBlackTurn() {
        return state.isBlackTurn();
    }
}
