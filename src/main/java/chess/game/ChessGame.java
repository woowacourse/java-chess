package chess.game;

import static java.util.stream.Collectors.toList;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.Position;
import chess.domain.Team;
import chess.dto.SquareResponse;
import chess.game.state.EndState;
import chess.game.state.GameState;
import chess.game.state.RunningState;
import chess.game.state.WaitingState;
import java.util.List;

public class ChessGame {
    private Board board;
    private GameState gameState;

    public ChessGame() {
        this.gameState = WaitingState.STATE;
    }

    public void start(TurnStrategy turnStrategy) {
        gameState.startGame(() -> {
            this.board = new Board(BoardFactory.create(), turnStrategy.create());
            this.gameState = RunningState.STATE;
        });
    }

    public void end() {
        this.gameState = EndState.STATE;
    }

    public boolean isEnd() {
        return gameState.isEnd();
    }

    public void movePiece(Position source, Position target) {
        gameState.movePiece(() -> board.move(source, target));
    }

    public List<SquareResponse> getBoard() {
        return gameState.getBoard(() -> board.getBoard().entrySet().stream()
                .map(entry -> SquareResponse.of(entry.getKey(), entry.getValue()))
                .collect(toList()));
    }

    public double getTeamScore(Team team) {
        return gameState.getTeamScore(() -> board.getTeamScore(team));
    }

    public Team getTurn() {
        return gameState.getTurn(() -> board.getTurn());
    }
}
