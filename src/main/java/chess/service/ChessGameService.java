package chess.service;

import chess.dao.BoardDAO;
import chess.dao.TurnDAO;
import chess.domain.GameResult;
import chess.domain.board.Board;
import chess.domain.command.MoveCommand;
import chess.dto.BoardDTO;
import chess.dto.Cell;
import chess.dto.TurnDTO;

import java.util.List;

public class ChessGameService {
    private BoardDAO boardDAO;
    private TurnDAO turnDAO;
    private Board board;
    private GameResult gameResult;

    public ChessGameService() {
        this.boardDAO = BoardDAO.getInstance();
        this.turnDAO = TurnDAO.getInstance();

        if (boardDAO.getBoard() == null || turnDAO.getTurn() == null) {
            this.board = new Board();
        } else {
            BoardDTO boardDTO = boardDAO.getBoard();
            TurnDTO turnDTO = turnDAO.getTurn();
            this.board = new Board(boardDTO.createBoard(), turnDTO.createTeam());
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
        this.boardDAO.deletePreviousBoard();
        this.turnDAO.deletePreviousTurn();
        this.boardDAO.closeConnection();
        this.turnDAO.closeConnection();
        setNewChessGame();
    }

    public void proceedGame() {
        this.boardDAO.deletePreviousBoard();
        this.turnDAO.deletePreviousTurn();
        this.boardDAO.saveBoard(BoardDTO.from(this.board));
        this.turnDAO.saveTurn(TurnDTO.from(this.board));
    }
}
