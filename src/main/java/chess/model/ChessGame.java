package chess.model;

import chess.console.controller.GameCommand;
import chess.model.board.Board;
import chess.model.board.BoardInitializer;
import chess.model.board.Square;
import chess.model.piece.King;
import chess.model.status.GameStatus;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChessGame {
    private Board board;
    private Color turn;
    private GameStatus status;

    public ChessGame(BoardInitializer initializer, GameStatus status) {
        this.board = new Board(initializer);
        this.turn = Color.WHITE;
        this.status = status;
    }

    public ChessGame(Board board, Color turn, GameStatus status) {
        this.board = board;
        this.turn = turn;
        this.status = status;
    }

    public void move(Square from, Square to) {
        status = status.changeStatus(GameCommand.MOVE);
        if (board.findPieceBySquare(from).isSameColor(turn)) {
            board.move(from, to);
            turn = turn.changeToOpposite();
            endWhenKingDead();
            return;
        }
        throw new IllegalArgumentException(String.format("해당 기물을 움직일 권한이 없습니다. 현재 %s의 차례입니다.", turn.name()));
    }

    private void endWhenKingDead() {
        if (!board.aliveTwoKings()) {
            end();
        }
    }

    public Map<Color, Double> getPlayersScore() {
        status.changeStatus(GameCommand.STATUS);
        return Color.getPlayerColors().stream()
                .collect(Collectors.toMap(Function.identity(), color -> board.calculatePoint(color)));
    }

    public boolean isRunning() {
        return status.isRunning();
    }

    public Board getBoard() {
        return board;
    }

    public Color getTurn() {
        return this.turn;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void end() {
        status = status.changeStatus(GameCommand.END);
    }

    public Color findWinner() {
        if (board.aliveTwoKings()) {
            return findByPoint();
        }
        return findByKing();
    }

    private Color findByKing() {
        if (board.aliveTwoKings()) {
            return Color.NOTHING;
        }
        return Color.getPlayerColors().stream()
                .filter(color -> board.has(new King(color)))
                .findFirst()
                .orElse(Color.NOTHING);
    }

    private Color findByPoint() {
        double blackPoint = board.calculatePoint(Color.BLACK);
        double whitePoint = board.calculatePoint(Color.WHITE);
        if (blackPoint < whitePoint) {
            return Color.WHITE;
        }
        if (blackPoint > whitePoint) {
            return Color.BLACK;
        }
        return Color.NOTHING;
    }
}
