package chess.domain;

import chess.domain.piece.info.Team;
import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.state.FinishedState;
import chess.domain.state.GameState;
import chess.domain.state.LoadingState;
import chess.domain.state.ReadyState;
import chess.domain.state.RunningState;
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

    public boolean isRunningOrFinished() {
        return state.isFinished() || state.isRunning();
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

    public void enterLoad(Runnable runnable) {
        state.enterLoad(() -> {
            state = LoadingState.STATE;
            runnable.run();
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

    public void cancelLoad(Runnable runnable) {
        state.cancelLoad(() -> {
            state = ReadyState.STATE;
            runnable.run();
        });
    }

    public void displayGameStatus(Runnable runnable) {
        state.displayGameStatus(runnable);
    }

    public Map<Team, Score> makeScoreBoard() {
        Map<Team, Score> scoreBoard = new HashMap<>();
        Team.RealTeams.forEach(
            (team) -> scoreBoard.put(team, chessBoard.calculateScoreByTeam(team)));
        return scoreBoard;
    }

    public Team judgeWinner() {
        Score white = chessBoard.calculateScoreByTeam(Team.WHITE);
        Score black = chessBoard.calculateScoreByTeam(Team.BLACK);
        if (white.isMoreThan(black)) {
            return Team.WHITE;
        }
        if (black.isMoreThan(white)) {
            return Team.BLACK;
        }
        return Team.EMPTY;
    }

    public void finishGame() {
        state.finishGame(() -> {
            state = FinishedState.STATE;
        });
    }

    public void executeMove(final String source, final String destination) {
        state.movePiece(() -> {
            Position startPosition = Position.from(source);
            Position endPosition = Position.from(destination);
            chessBoard.move(startPosition, endPosition);
        });
    }

    public Team findWinner() {
        return chessBoard.findWinner();
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
