package chess.service;

import chess.controller.dao.ChessBoardDao;
import chess.controller.dao.GameDao;
import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.domain.chesspiece.Piece;
import chess.domain.game.*;
import chess.domain.position.Position;

import java.sql.SQLException;
import java.util.Map;

public class ChessService {
    ChessBoardDao chessBoardDao = new ChessBoardDao();
    GameDao gameDao = new GameDao();

    public int findRoomNumber() throws SQLException {
        return gameDao.findMaxRoomNumber();
    }

    public ResponseDto loadInitGame() throws SQLException {
        ChessBoard chessBoard = new ChessBoard(PieceFactory.create());
        ChessGame chessGame = new ChessGame(chessBoard, Player.WHITE, GameStatus.NOT_STARTED);
        ResponseDto responseDto = chessGame.start(new RequestDto(Command.START));
        int roomNumber = gameDao.findMaxRoomNumber();

        gameDao.saveInitGame(responseDto);
        chessBoardDao.saveChessBoard(responseDto.getChessBoardDto(), roomNumber);

        return responseDto;
    }

    public void endService() throws SQLException {
        int roomNumber = gameDao.findMaxRoomNumber();
        chessBoardDao.deleteChessBoard(roomNumber);
        gameDao.updateEndState(roomNumber);
    }

    public ResponseDto move(RequestDto requestDto) throws SQLException {
        int roomNumber = gameDao.findMaxRoomNumber();

        ChessBoard chessBoard = new ChessBoard(findPlayingChessBoard(roomNumber));
        ChessGame chessGame = new ChessGame(chessBoard, findTurn(roomNumber), GameStatus.RUNNING);
        ResponseDto responseDto = chessGame.move(requestDto);
        responseDto.setRoomNumber(roomNumber);

        gameDao.updateGame(responseDto);
        chessBoardDao.deleteChessBoard(roomNumber);
        chessBoardDao.saveChessBoard(responseDto.getChessBoardDto(), roomNumber);

        if(chessBoard.isGameOver()){
            responseDto.dieKing();
        }

        return responseDto;
    }

    public GameStatus findState(int roomNumber) throws SQLException {
        return gameDao.findState(roomNumber);
    }

    public Map<Position, Piece> findPlayingChessBoard(int roomNumber) throws SQLException {
        return chessBoardDao.findPlayingChessBoard(roomNumber);
    }

    public Player findTurn(int roomNumber) throws SQLException {
        return gameDao.findTurn(roomNumber);
    }

    public ResponseDto loadPlayingGame() throws SQLException {
        int roomNumber = findRoomNumber();
        ChessBoard chessBoard = new ChessBoard(findPlayingChessBoard(roomNumber));
        ChessGame chessGame = new ChessGame(chessBoard, findTurn(roomNumber), GameStatus.RUNNING);

        return chessGame.load(chessBoard);
    }
}