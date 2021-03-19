package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.InitializedBoard;
import chess.domain.board.Position;
import chess.domain.piece.PieceColor;

public class Game {

    private Board board;
    private GameState gameState;
    private PieceColor turnColor;

    public Game() {
        this(new Board(InitializedBoard.emptyBoard()), GameState.NOT_STARTED);
    }

    public Game(Board board, GameState gameState) {
        this.board = board;
        this.gameState = gameState;
        this.turnColor = PieceColor.WHITE;
    }

//    public static Game set() {
//        return new Game(new Board(InitializedBoard.emptyBoard()), GameState.NOT_STARTED);
//    }

    public void init() {
        this.board = new Board(InitializedBoard.board());
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

    public void move(String source, String target) {
        Position sourcePosition = Position.from(source);
        Position targetPosition = Position.from(target);
        board.move(sourcePosition, targetPosition, turnColor);
        if (board.kingIsDead()) {
            gameState = GameState.END;
        }

        this.turnColor = turnColor.oppositeColor();
    }

    public double computeWhitePoint() {
        return board.computePoint(PieceColor.WHITE);
    }

    public double computeBlackPoint() {
        return board.computePoint(PieceColor.BLACK);
    }

    public Board getBoard() {
        return board;
    }

    public PieceColor winnerColor() {
        return board.winnerColor();
    }
}
