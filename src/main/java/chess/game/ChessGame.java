package chess.game;

import static java.util.stream.Collectors.toList;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.Position;
import chess.domain.Team;
import chess.dto.SquareResponse;
import chess.game.state.GameState;
import chess.game.state.running.CheckedState;
import chess.game.state.end.EndState;
import chess.game.state.end.NoneWinState;
import chess.game.state.waiting.WaitingState;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ChessGame {
    private static final String INVALID_TURN_EXCEPTION_MESSAGE = "[ERROR] 해당 팀의 턴이 아닙니다.";
    private static final String EMPTY_PIECE_EXCEPTION_MESSAGE = "[ERROR] 빈 칸은 움직일 수 없습니다.";

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
            validate(source);
            board.move(source, target);
        });
    }

    private void validate(Position source) {
        Team pieceTeam = board.getPieceTeam(source);
        validateEmptySquare(pieceTeam);
        validateTurn(pieceTeam);
    }

    private void validateEmptySquare(Team team) {
        if (team == Team.NONE) {
            throw new IllegalArgumentException(EMPTY_PIECE_EXCEPTION_MESSAGE);
        }
    }

    private void validateTurn(Team team) {
        if (team != gameState.getTurn()) {
            throw new IllegalStateException(INVALID_TURN_EXCEPTION_MESSAGE);
        }
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
        if (board.isChecked(gameState.getTurn())) {
            this.gameState = CheckedState.createCheckedState(gameState.getTurn());
        }
    }

    public Team getWinner() {
        return gameState.getWinner();
    }

    public void checkCheckmate() {
        Team turn = gameState.getTurn();
        if (isChecked() && board.isChecked(turn)) {
            this.gameState = EndState.createWinState(turn.opposite());
        }
    }

    public boolean isChecked() {
        return gameState.isChecked();
    }

    public void save(BiConsumer<Board, GameState> saveLogic) {
        gameState.saveGame(() -> saveLogic.accept(board, gameState));
    }

    public void load(Supplier<Board> boardLoadLogic, Supplier<GameState> gameStateLoadLogic) {
        this.gameState.loadGame(() -> {
            this.board = boardLoadLogic.get();
            this.gameState = gameStateLoadLogic.get();
        });
    }
}
