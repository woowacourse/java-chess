package chess.domain;

import chess.domain.position.Position;

public class ChessGame {

    private final ChessBoard chessBoard;
    private GameState state;


    private ChessGame(ChessBoard chessBoard, GameState state) {
        this.chessBoard = chessBoard;
        this.state = state;
    }

    public static ChessGame startNewGame() {
        ChessBoard chessBoard = ChessBoardFactory.create();
        return new ChessGame(chessBoard, GameState.RUNNING);
    }

    public boolean isRunning() {
        return state == GameState.RUNNING;
    }

    public void finishGame() {
        state = GameState.FINISHED;
    }

    public void executeMove(final String source, final String destination) {
        Position startPosition = Position.from(source);
        Position endPosition = Position.from(destination);
        chessBoard.move(startPosition, endPosition);
    }

    public void checkGameNotFinished() {
        if (chessBoard.isKingDead()) {
            state = GameState.FINISHED;
        }
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
