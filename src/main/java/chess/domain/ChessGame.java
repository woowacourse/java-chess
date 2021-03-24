package chess.domain;

import chess.domain.board.*;
import chess.domain.command.Commands;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.view.OutputView;

import java.util.EnumMap;

public class ChessGame {

    private Board board;
    private State state;
    private Team winner;
    private Team turn;

    public ChessGame() {
        state = new Ready();
        turn = Team.WHITE;
    }

    public void initBoard(Board board) {
        this.board = board;
        state = state.init();
    }

    public void move(Commands command) {
        try {
            final Path path = new Path(command.path());
            board.move(path, turn);
            movePiece(path);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void movePiece(Path path) {
        if (board.containsPosition(path.target())) {
            confirmKingCaptured(path);
        }
        board.movePiece(path);
        turnOver();
    }

    private void confirmKingCaptured(Path path) {
        if (board.isKingAt(path.target())) {
            winner = turn;
            state = state.next();
        }
    }

    public EnumMap<Team, Double> calculatePoint() {
        EnumMap<Team, Double> result = new EnumMap<>(Team.class);
        calculateEachTeamPoint(result, Team.BLACK);
        calculateEachTeamPoint(result, Team.WHITE);
        return result;
    }

    private void calculateEachTeamPoint(EnumMap<Team, Double> result, Team team) {
        double totalPoint = board.calculateTotalPoint(team);
        totalPoint -= board.updatePawnPoint(team);
        result.put(team, totalPoint);
    }

    public boolean isReady() {
        return state.isReady();
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    public void endGame() {
        state = state.exit();
    }

    public boolean isRunning() {
        return !state.isExit();
    }

    public Team winner() {
        return winner;
    }

    public Board board() {
        return board;
    }

    public Team turn() {
        return turn;
    }

    private void turnOver() {
        turn = Team.turnOver(turn);
    }
}
