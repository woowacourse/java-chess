package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.player.Player;
import chess.domain.player.Players;
import chess.domain.position.Position;
import chess.domain.result.Result;
import chess.domain.state.End;
import chess.domain.state.Ready;
import chess.domain.state.Running;
import chess.domain.state.State;
import java.util.Arrays;
import java.util.Objects;

public class Game {

    private static final String INVALID_SOURCE_ERROR_MESSAGE = "움직일 수 없는 말을 선택했습니다.";

    private final Board board;
    private final Players players;

    private String name;
    private PieceColor turn;
    private State state;

    public Game() {
        board = BoardFactory.initializeBoard();
        players = Players.of(Arrays.asList(
            Player.of(PieceColor.WHITE),
            Player.of(PieceColor.BLACK)
        ));
        turn = PieceColor.WHITE;
        state = new Ready();
    }

    public Game(String name) {
        this();
        this.name = name;
        this.changeState(new Running());
    }

    public void move(Position from, Position to) {
        Piece source = board.findPieceBy(from);
        if (!players.currentPlayer(turn).isOwnerOf(source)) {
            throw new IllegalArgumentException(INVALID_SOURCE_ERROR_MESSAGE);
        }
        if (board.move(source, to)) {
            refreshState();
        }
    }

    private void refreshState() {
        if (board.kingDead()) {
            changeState(new End());
            return;
        }
        turn = turn.reversed();
    }

    public void changeState(State state) {
        this.state = state;
    }

    public boolean isRunning() {
        return state.isStarted();
    }

    public boolean isFinished() {
        return state.gameOver();
    }

    public Board getBoard() {
        return board;
    }

    public PieceColor getTurn() {
        return turn;
    }

    public Result getResult() {
        return new Result(board);
    }

    public String getWinner() {
        return getResult().findWinner();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Game game = (Game) o;
        return name == game.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
