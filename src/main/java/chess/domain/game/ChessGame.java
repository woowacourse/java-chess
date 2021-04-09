package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Position;

public class ChessGame {

    private String roomID;
    private Board board;
    private State state;

    public ChessGame(final Board board) {
        this.board = board;
        this.state = new Ready(this);
    }

    public ChessGame(final Board board, String roomID) {
        this.board = board;
        this.state = new Ready(this);
        this.roomID = roomID;
    }

    public void changeState(State state) {
        this.state = state;
    }

    public void move(Position source, Position target) {
        state.move(source, target);
    }

    public void end() {
        state.end();
    }

    public void start() {
        state.start();
    }

    public void ready() {
        board = new Board(PieceFactory.createPieces());
        state = new Ready(this);
    }

    public boolean isReady() {
        return state instanceof Ready;
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public boolean isKingCatch() {
        return board.isKingCatch();
    }

    public Board getBoard() {
        return board;
    }

    public double getWhiteScore() {
        return board.getWhiteScore();
    }

    public double getBlackScore() {
        return board.getBlackScore();
    }

    public String getStatus() {
        return state.toString();
    }

    public String getRoomID() {
        return roomID;
    }
}
