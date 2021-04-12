package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Path;
import chess.domain.board.Team;
import chess.domain.command.Commands;
import chess.domain.dto.BoardDto;
import chess.domain.dto.CommandDto;
import chess.domain.dto.GameInfoDto;
import chess.domain.dto.PointDto;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Optional;

public class ChessGame {

    private Board board;
    private State state;
//    private Optional<Team> winner = Optional.empty();
    private Team turn;

    public ChessGame() {
        state = new Ready();
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
//            winner = Optional.of(turn);
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

    public Optional<Team> winner() {
        if (!isEnd()) {
            return Optional.empty();
        }
        return Optional.of(Team.opposite(turn));
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

    public GameInfoDto gameInfo() {
        return new GameInfoDto(this);
    }
}
