package domain.game;

import domain.board.ChessBoard;
import domain.board.ChessBoardFactory;
import domain.game.state.GameState;
import domain.game.state.Prepare;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

public class ChessGame {
    private GameState state;

    public ChessGame() {
        ChessBoard chessBoard = ChessBoardFactory.createInitialChessBoard();
        this.state = new Prepare(chessBoard);
    }

    public void start() {
        this.state = state.start();
    }

    public void move(String source, String target) { // todo positions
        Position sourcePosition = getPosition(source);
        Position targetPosition = getPosition(target);
        this.state = state.move(sourcePosition, targetPosition);
    }

    private Position getPosition(String source) {
        File file = File.fromName(String.valueOf(source.charAt(0)));
        Rank rank = Rank.fromNumber(Character.getNumericValue(source.charAt(1)));
        return new Position(file, rank);
    }

    public void end() {
        this.state = state.end();
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public ChessBoard getBoard() {
        return state.chessBoard();
    }
}
