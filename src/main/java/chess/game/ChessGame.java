package chess.game;

import static java.util.stream.Collectors.toList;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.Position;
import chess.domain.Team;
import chess.dto.SquareResponse;
import chess.game.state.end.EndState;
import chess.game.state.GameState;
import chess.game.state.end.NoneWinState;
import chess.game.state.waiting.WaitingState;
import java.util.List;

public class ChessGame {
    private static final String INVALID_TURN_EXCEPTION_MESSAGE = "[ERROR] 해당 팀의 턴이 아닙니다.";

    private Board board;
    private GameState gameState;

    public ChessGame() {
        this.gameState = WaitingState.STATE;
    }

    public void start(TurnStrategy turnStrategy) {
        gameState.startGame(() -> {
            this.board = new Board(BoardFactory.create());
            this.gameState = turnStrategy.create();
        });
    }

    public void end() {
        this.gameState = NoneWinState.STATE;
    }

    public boolean isEnd() {
        return gameState.isEnd();
    }

    public void movePiece(Position source, Position target) {
        gameState.movePiece(() -> {
            validateTurn(source);
            board.move(source, target);
        });
    }

    private void validateTurn(Position source) {
        if (board.getPieceTeam(source) != gameState.getTurn()) {
            throw new IllegalStateException(INVALID_TURN_EXCEPTION_MESSAGE);
        }
    }

    public void checkCheckmate() {
        gameState.checkCheckmate(() -> {
            Team turn = gameState.getTurn();
            if (board.isChecked(turn.opposite()) && board.isCheckmate(turn.opposite())) {
                gameState = EndState.createWinState(turn);
            }
        });
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
        return gameState.getTurn();
    }

    public void changeTurn() {
        this.gameState = gameState.changeTurn();
    }

    public Team getWinner() {
        return gameState.getWinner();
    }

    public boolean hasWinner() {
        return gameState.hasWinner();
    }
}
