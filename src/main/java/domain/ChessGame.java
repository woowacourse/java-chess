package domain;

import controller.MoveCommand;
import domain.board.ChessBoard;
import domain.board.piece.Piece;
import domain.path.location.Location;
import java.util.Map;

public final class ChessGame {

    private boolean isReady = false;
    private final ChessBoard chessBoard;

    public ChessGame(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void initialize() {
        chessBoard.initializeBoard();
    }

    public void move(MoveCommand moveCommand) {
        chessBoard.move(moveCommand.getStart(), moveCommand.getEnd());
        if (chessBoard.isOneKingExist()) {
            end();
        }
    }

    public void ready() {
        isReady = true;
    }

    public void end() {
        isReady = false;
    }

    public boolean isReady() {
        return isReady;
    }

    public Map<Location, Piece> getBoard() {
        return chessBoard.getBoard();
    }

    public double getBlackScore() {
        return chessBoard.getBlackScore();
    }

    public double getWhiteScore() {
        return chessBoard.getWhiteScore();
    }
}
