package chess.controller;

import chess.controller.dao.ChessBoardDao;
import chess.controller.dao.GameDao;
import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.domain.game.Command;
import chess.domain.game.*;

import java.sql.SQLException;

public class WebController {
    private static final ChessBoardDao chessBoardDao = new ChessBoardDao();
    private static final GameDao gameDao = new GameDao();

    public ResponseDto loadInitGame() throws SQLException {
        ChessBoard chessBoard = new ChessBoard(PieceFactory.create());
        ChessGame chessGame = new ChessGame(chessBoard, Player.WHITE, GameStatus.NOT_STARTED);
        ResponseDto responseDto = chessGame.start(new RequestDto(Command.START));
        gameDao.saveInitGame(responseDto);
        int roomNumber = gameDao.findMaxRoomNumber();
        chessBoardDao.saveChessBoard(responseDto.getChessBoardDto(), roomNumber);

        return responseDto;
    }

    public ResponseDto loadPlayingGame() throws SQLException{
        int roomNumber = gameDao.findMaxRoomNumber();
        ChessBoard chessBoard = new ChessBoard(chessBoardDao.findPlayingChessBoard(roomNumber));
        ChessGame chessGame = new ChessGame(chessBoard, gameDao.findTurn(roomNumber), GameStatus.RUNNING);
        ResponseDto responseDto = chessGame.load(chessBoard);

        return responseDto;
    }
}
