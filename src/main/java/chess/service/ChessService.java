package chess.service;

import chess.ConnectionFactory;
import chess.model.ChessGame;
import chess.model.Square;
import chess.model.board.BasicBoardInitializer;
import chess.model.board.Board;
import chess.model.dao.ChessDao;
import chess.model.dto.BoardInfo;
import chess.model.dto.MoveResult;
import chess.model.unit.Side;

import java.sql.Connection;
import java.sql.SQLException;

public class ChessService {
    private static ChessDao chessDao;

    static {
        Connection con = ConnectionFactory.connect();
        chessDao = new ChessDao(con);
    }

    private ChessGame loadChessGame() throws SQLException {
        return new ChessGame(chessDao.loadBoard(), chessDao.loadTurn());
    }

    public MoveResult canMove(Square source, Square target) throws SQLException {
        ChessGame chessGame = loadChessGame();
        boolean canMove = chessGame.canMove(source, target);
        if (canMove && chessGame.isKingAlive()){
            chessGame.move(source, target);
            chessDao.updateMove(source, target);
            chessDao.updateGameInfo(chessGame.createGameInfo());
            return chessGame.createSuccessMoveResult();
        }
        return chessGame.createFailureMoveResult();
    }

    public BoardInfo initializeBoard() throws SQLException {
        Board board = new Board();
        board.initialize(new BasicBoardInitializer());
        ChessGame chessGame = new ChessGame(board, Side.WHITE);
        chessDao.initializeBoard(chessGame.createBoardInfo(), chessGame.createGameInfo());
        return chessGame.createBoardInfo();
    }

    public BoardInfo createBoardInfo() throws SQLException {
        if(chessDao.checkEmpty())
            return initializeBoard();
        ChessGame chessGame = loadChessGame();
        return chessGame.createBoardInfo();
    }
}