package chess.service;

import chess.dao.ChessBoardDAO;
import chess.domain.GameResult;
import chess.domain.board.Board;
import chess.domain.board.Cell;
import chess.domain.command.MoveCommand;

import java.util.List;

public class ChessGameService {
    private ChessBoardDAO chessBoardDAO;
    private Board board;
    private GameResult gameResult;

    public ChessGameService() {
        this.chessBoardDAO = ChessBoardDAO.getInstance();

        if (chessBoardDAO.getBoard() == null) {
            this.board = new Board();
        } else {
            this.board = chessBoardDAO.getBoard();
        }

        this.gameResult = this.board.createGameResult();
    }

    public void setNewChessGame() {
        this.board = new Board();
        this.gameResult = this.board.createGameResult();
    }

    public List<Cell> getCells() {
        return this.board.getCells();
    }

    public String getCurrentTeam() {
        return this.board.getTeam().getName();
    }

    public Double getBlackPieceScore() {
        return this.gameResult.getAliveBlackPieceScoreSum();
    }

    public Double getWhitePieceScore() {
        return this.gameResult.getAliveWhitePieceScoreSum();
    }

    public void movePiece(String source, String target) {
        this.board.move(new MoveCommand(String.format("move %s %s", source, target)));
        this.gameResult = this.board.createGameResult();
    }

    public boolean isGameOver() {
        return this.board.isGameOver();
    }

    public String getWinner() {
        return gameResult.getWinner();
    }

    public String getLoser() {
        return gameResult.getLoser();
    }

    public void endGame() {
        this.chessBoardDAO.deletePreviousBoard();
        this.chessBoardDAO.closeConnection();
        setNewChessGame();
    }

    public void proceedGame() {
        this.chessBoardDAO.deletePreviousBoard();
        this.chessBoardDAO.saveBoard(this.board.createBoardDTO());
    }
}
