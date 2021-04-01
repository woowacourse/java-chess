package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.RequestedBoard;
import chess.domain.board.Point;
import chess.domain.board.Position;
import chess.domain.piece.PieceColor;

public class Game {

    private Board board;
    private Point point;
    private GameState gameState;
    private PieceColor turnColor;

    public Game() {
        this.board = new Board(RequestedBoard.emptyBoard());
        this.point = new Point(board);
        this.gameState = GameState.NOT_STARTED;
        this.turnColor = PieceColor.WHITE;
    }

    public void init() {
        this.board = new Board(RequestedBoard.board());
        this.point = new Point(board);
        this.gameState = GameState.START;
    }

    public void end() {
        gameState = GameState.END;
    }

    public boolean isEnd() {
        return gameState.isEnd();
    }

    public boolean isStart() {
        return gameState.isStart();
    }

    public void move(final String source, final String target) {
        Position sourcePosition = Position.from(source);
        Position targetPosition = Position.from(target);
        board.move(sourcePosition, targetPosition, turnColor);
        if (board.kingIsDead()) {
            gameState = GameState.END;
        }
        this.turnColor = turnColor.oppositeColor();
    }

    public double computeWhitePoint() {
        return point.whitePoint();
    }

    public double computeBlackPoint() {
        return point.blackPoint();
    }

    public Board getBoard() {
        return board;
    }

    public PieceColor winnerColor() {
        return board.winnerColor();
    }

    public String turn() {
        return this.turnColor.name();
    }
}
