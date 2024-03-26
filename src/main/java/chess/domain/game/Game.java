package chess.domain.game;

import static chess.domain.piece.Color.WHITE;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class Game {

    private final Board board;
    private final Color turn;

    private Game(Board board, Color turn) {
        this.board = board;
        this.turn = turn;
    }

    public static Game from(Board board) {
        return new Game(board, WHITE);
    }

    public Game move(Position source, Position target) {
        board.move(source, target, turn);
        return new Game(board, Color.oppose(turn));
    }

    public Board getBoard() {
        return board;
    }
}
