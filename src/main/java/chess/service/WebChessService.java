package chess.service;

import chess.domain.game.ChessGame;
import chess.dao.BoardDao;
import chess.dao.ChessGameDao;
import chess.domain.game.Board;
import chess.domain.game.Color;
import chess.domain.game.Command;
import chess.dto.ChessGameDto;
import chess.dto.RequestDto;
import spark.Request;

import java.sql.SQLException;

public class WebChessService {
    private final BoardDao boardDao;
    private final ChessGameDao chessGameDao;

    public WebChessService(BoardDao boardDao, ChessGameDao chessGameDao) {
        this.boardDao = boardDao;
        this.chessGameDao = chessGameDao;
    }

    public ChessGameDto loadGame(ChessGame chessGame) throws SQLException {
        ChessGameDto chessGameDto = null;

        boolean isGameExists = boardDao.isBoardExists();
        if (isGameExists) {
            Board chessBoard = Board.of(boardDao.getBoard());
            Color turn = chessGameDao.getTurn();
            chessGameDto = chessGame.load(chessBoard, turn);
            chessGameDao.updateGame(chessGameDto);
        }

        if (!isGameExists) {
            chessGameDto = chessGame.start();
            chessGameDao.saveGame(chessGameDto);
        }

        boardDao.deleteBoard();
        boardDao.saveBoard(chessGameDto.getBoardDto());
        return chessGameDto;
    }

    public ChessGameDto move(ChessGame chessGame, Request req) throws SQLException {
        RequestDto requestDto = new RequestDto(Command.MOVE, req);
        ChessGameDto chessGameDto = chessGame.move(requestDto);

        chessGameDao.updateGame(chessGameDto);
        boardDao.deleteBoard();
        boardDao.saveBoard(chessGameDto.getBoardDto());
        chessGameDao.updateGame(chessGameDto);

        return chessGameDto;
    }

    public ChessGameDto endGame(ChessGame chessGame) throws SQLException{
        ChessGameDto chessGameDto = chessGame.end();
        boardDao.deleteBoard();
        chessGameDao.deleteGame();

        return chessGameDto;
    }
}
