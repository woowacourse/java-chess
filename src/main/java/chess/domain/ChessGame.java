package chess.domain;

import chess.domain.piece.info.Team;
import chess.domain.position.Position;
import chess.domain.state.FinishedState;
import chess.domain.state.GameState;
import chess.domain.state.ReadyState;
import chess.domain.state.RunningState;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    public void startGame() {
        state.startGame(() -> {
            state = RunningState.STATE;
        });
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

    public void displayGameStatus(Runnable runnable) {
        state.displayGameStatus(runnable);
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

    public void checkGameNotFinished() {
        if (chessBoard.isKingDead()) {
            state = FinishedState.STATE;
        }
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
