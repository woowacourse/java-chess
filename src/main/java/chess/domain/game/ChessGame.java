package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class ChessGame {

    private final Board board;
    private final Turn turn;
    private final GameOver gameOver;

    public ChessGame() {
        board = new Board();
        turn = new Turn();
        gameOver = new GameOver();
    }

    public ChessGame(final Board board) {
        this(board, new Turn(), new GameOver());
    }

    public ChessGame(final Board board, final Turn turn, final GameOver gameOver) {
        this.board = board;
        this.turn = turn;
        this.gameOver = gameOver;
    }

    public void changeGameOver() {
        gameOver.changeGameOver();
    }

    public boolean isGameOver() {
        return gameOver.isGameOver();
    }

    public Team nowTurn() {
        return turn.now();
    }

    public void nextTurn() {
        turn.next();
    }

    public boolean isKingDead() {
        return board.isKingDead();
    }

    public void move(final Position start, final Position end) {
        board.move(start, end, turn.now());
    }

    public Board board() {
        return board;
    }
}
