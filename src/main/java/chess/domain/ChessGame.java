package chess.domain;

import chess.domain.piece.info.Team;
import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.state.FinishedState;
import chess.domain.state.GameState;
import chess.domain.state.ReadyState;
import chess.domain.state.RunningState;
import chess.domain.strategy.ScoreCalculator;
import chess.domain.strategy.ScoreCalculatorByPawnCount;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ChessGame {

    private final ChessBoard chessBoard;
    private GameState state;

    private ChessGame(ChessBoard chessBoard, GameState state) {
        this.chessBoard = chessBoard;
        this.state = state;
    }

    public static ChessGame createGame() {
        ChessBoard chessBoard = ChessBoardFactory.create();
        return new ChessGame(chessBoard, ReadyState.STATE);
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public void startGame(Runnable runnable) {
        state.startGame(() -> {
            runnable.run();
            state = RunningState.STATE;
        });
    }

    public void loadGame(Supplier<List<Path>> supplier) {
        state.loadGame(() -> {
            List<Path> paths = supplier.get();
            paths.forEach((path) ->
                chessBoard.move(path.getSource(), path.getDestination()));
            state = RunningState.STATE;
        });
    }

    public void displayGameStatus(Runnable runnable) {
        state.displayGameStatus(runnable);
    }

    public Map<Team, Score> makeScoreBoard(ScoreCalculator scoreCalculator) {
        Map<Team, Score> scoreBoard = new HashMap<>();
        Team.RealTeams.forEach(
            (team) -> scoreBoard.put(team, chessBoard.calculateScoreByTeam(scoreCalculator, team)));
        return scoreBoard;
    }

    public Team judgeWinner(ScoreCalculator scoreCalculator) {
        Score white = chessBoard.calculateScoreByTeam(scoreCalculator, Team.WHITE);
        Score black = chessBoard.calculateScoreByTeam(scoreCalculator, Team.BLACK);
        if (white.isMoreThan(black)) {
            return Team.WHITE;
        }
        if (black.isMoreThan(white)) {
            return Team.BLACK;
        }
        return Team.EMPTY;
    }

    public Team findTeamByTurn() {
        return chessBoard.findTeamByTurn();
    }

    public void finishGame() {
        state.finishGame(() -> {
            state = FinishedState.STATE;
        });
    }

    public void executeMove(final String sourceCommand, final String destinationCommand) {
        state.movePiece(() -> {
            Position source = Position.from(sourceCommand);
            Position destination = Position.from(destinationCommand);
            chessBoard.move(source, destination);
        });
    }

    public Team findWinner() {
        return chessBoard.findWinner();
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
