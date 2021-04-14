package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Path;
import chess.domain.board.Team;
import chess.domain.command.Commands;
import chess.domain.dto.BoardDto;
import chess.domain.dto.CommandDto;
import chess.domain.dto.PointDto;
import chess.domain.state.Ready;
import chess.domain.state.Running;
import chess.domain.state.State;
import chess.view.OutputView;

import java.util.EnumMap;
import java.util.List;

public class ChessGame {

    private Board board;
    private State state;
    private Team turn;

    public ChessGame() {
        state = new Ready();
    }

    public ChessGame(Board board) {
        this.board = board;
        state = new Running();
        turn = Team.WHITE;
    }

    public void initBoard(Board board) {
        this.board = board;
        state = state.init();
        turn = Team.WHITE;
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

    public void makeBoardStateOf(List<CommandDto> commands) {
        for (CommandDto commandInDB : commands) {
            final Path path = new Path(new Commands(commandInDB.data()).path());
            movePiece(path);
        }
    }

    public void moveAs(Commands command) {
        final Path path = new Path(command.path());
        board.move(path, turn);
        movePiece(path);
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
            state = state.next();
        }
    }

    private EnumMap<Team, Double> calculatePoint() {
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
        if (!isEnd()) {
            return null;
        }
        return Team.opposite(turn);
    }

    private void turnOver() {
        turn = Team.opposite(turn);
    }

    public Board board() {
        return board;
    }

    public Team turn() {
        return turn;
    }

    public BoardDto boardDto() {
        return new BoardDto(board, turn);
    }

    public PointDto pointDto() {
        return new PointDto(calculatePoint());
    }
}
