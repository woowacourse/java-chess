package chess.domain;

import chess.domain.board.*;
import chess.domain.piece.PieceColor;

public class Game {

    private Board board;
    private Point point;
    private GameState gameState;
    private PieceColor turnColor;

    private Game(Board board, Point point, GameState gameState, PieceColor turnColor) {
        this.board = board;
        this.point = point;
        this.gameState = gameState;
        this.turnColor = turnColor;
    }

    public static Game newGame() {
        InitializedBoard initializedBoard = new InitializedBoard();
        Board board = new Board(initializedBoard.board());
        Point point = new Point(board);

        return new Game(board, point, GameState.START, PieceColor.WHITE);
    }

    public static Game continueGame(String roomName) {
        InitializedBoardFromDb initializedBoardFromDb = new InitializedBoardFromDb();

        Board board = new Board(initializedBoardFromDb.continueBoard(roomName));
        Point point = new Point(board);
        PieceColor turnColor = initializedBoardFromDb.continueTurn(roomName);

        return new Game(board, point, GameState.START, turnColor);
    }

    public void end() {
        gameState = GameState.END;
    }

    public boolean isEnd() {
        return gameState.isEnd();
    }

    public boolean isNotStart() {
        return !gameState.isStart();
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
}
