package chess.controller;

import chess.controller.dao.ChessBoardDao;
import chess.controller.dao.GameDao;
import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.domain.game.Command;
import chess.domain.game.*;
import chess.service.ChessService;
import spark.Request;

import java.sql.SQLException;

public class WebController {
    private static final ChessBoardDao chessBoardDao = new ChessBoardDao();
    private static final GameDao gameDao = new GameDao();
    private static final ChessService chessService = new ChessService();

    public ResponseDto loadGame() throws SQLException {
        int roomNumber = chessService.findRoomNumber();
        GameStatus state = gameDao.findState(roomNumber);

        if (state == GameStatus.FINISH) {
            return loadInitGame();
        } else if (state == GameStatus.RUNNING) {
            return loadPlayingGame();
        }

        throw new IllegalArgumentException("게임을 불러올 수 없습니다.");
    }

    private ResponseDto loadInitGame() throws SQLException {
        ChessBoard chessBoard = new ChessBoard(PieceFactory.create());
        ChessGame chessGame = new ChessGame(chessBoard, Player.WHITE, GameStatus.NOT_STARTED);
        ResponseDto responseDto = chessGame.start(new RequestDto(Command.START));

        chessService.loadInitGame(responseDto);

        return responseDto;
    }

    private ResponseDto loadPlayingGame() throws SQLException {
        int roomNumber = gameDao.findMaxRoomNumber();
        ChessBoard chessBoard = new ChessBoard(chessBoardDao.findPlayingChessBoard(roomNumber));
        ChessGame chessGame = new ChessGame(chessBoard, gameDao.findTurn(roomNumber), GameStatus.RUNNING);

        return chessGame.load(chessBoard);
    }

    public ResponseDto moveChessPiece(Request req) throws SQLException {
        int roomNumber = gameDao.findMaxRoomNumber();

        ChessBoard chessBoard = new ChessBoard(chessBoardDao.findPlayingChessBoard(roomNumber));
        ChessGame chessGame = new ChessGame(chessBoard, gameDao.findTurn(roomNumber), GameStatus.RUNNING);
        RequestDto requestDto = new RequestDto(Command.MOVE, req);
        ResponseDto responseDto = chessGame.move(requestDto);
        responseDto.setRoomNumber(roomNumber);

        chessService.move(responseDto);
        if (chessBoard.isGameOver()) {
            responseDto.dieKing();
            chessService.endService();
        }

        return responseDto;
    }

    public void end() throws SQLException {
        chessService.endService();
    }
}