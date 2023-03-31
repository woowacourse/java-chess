package domain;

import controller.Move;
import database.BoardDao;
import database.DatabaseConnector;
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

    public void makeBoard() {
        chessBoard.makeBoard();
    }

    public void initializeBoard() {
        chessBoard.initializeBoard();
    }

    public void move(final Move move) {
        chessBoard.move(move.getStart(), move.getEnd());
        if (chessBoard.isOneKingExist()) {
            initializeBoard();
            end();
        }
    }

    public void ready() {
        isReady = true;
    }

    public void end() {
        isReady = false;
        BoardDao boardDao = new BoardDao(new DatabaseConnector());
        boardDao.updateBoard(chessBoard.getBoard(), chessBoard.getTurn());
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
