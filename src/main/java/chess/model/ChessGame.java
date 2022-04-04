package chess.model;

import chess.model.board.Board;
import chess.model.board.ChessInitializer;
import chess.model.board.Square;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChessGame {
    private Board board;
    private Color turn;

    public ChessGame() {
        this.board = new Board(new ChessInitializer());
        this.turn = Color.WHITE;
    }

    public ChessGame(Board board, Color turn) {
        this.board = board;
        this.turn = turn;
    }

    public void move(Square from, Square to) {
        if (board.findPieceBySquare(from).isSameColor(turn)) {
            board.move(from, to);
            turn = turn.changeToOpposite();
            return;
        }
        throw new IllegalArgumentException(String.format("해당 기물을 움직일 권한이 없습니다. 현재 %s의 차례입니다.", turn.name()));
    }

    public Map<Color, Double> status() {
        return Color.getPlayerColors().stream()
                .collect(Collectors.toMap(Function.identity(), color -> board.calculatePoint(color)));
    }

    public boolean isRunning() {
        return board.aliveTwoKings();
    }

    public Board getBoard() {
        return board;
    }

    public Color getTurn() {
        return this.turn;
    }
}
