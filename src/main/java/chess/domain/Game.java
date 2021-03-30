package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.InitializedBoard;
import chess.domain.board.Point;
import chess.domain.board.Position;
import chess.domain.piece.PieceColor;
import chess.dto.SquareDto;

import java.util.List;

public class Game {

    private Board board;
    private Point point;
    private GameState gameState;
    private PieceColor turnColor;

    public Game() {
        InitializedBoard initializedBoard = new InitializedBoard();
        this.board = new Board(initializedBoard.emptyBoard());
        this.point = new Point(board);
        this.gameState = GameState.NOT_STARTED;
        this.turnColor = PieceColor.WHITE;
    }

    public void init() {
        InitializedBoard initializedBoard = new InitializedBoard();
        this.board = new Board(initializedBoard.board());
        this.point = new Point(board);
        this.gameState = GameState.START;
        this.turnColor = PieceColor.WHITE;
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

    public void move(String source, String target) {
        Position sourcePosition = Position.from(source);
        Position targetPosition = Position.from(target);
        board.move(sourcePosition, targetPosition, turnColor);
        if (board.kingIsDead()) {
            gameState = GameState.END;
        }

        this.turnColor = turnColor.oppositeColor();
    }

    public PieceColor turnColor() {
        return turnColor;
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

    public List<SquareDto> squareDtos() {
        return board.squareDtos();
    }
}
