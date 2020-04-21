package chess.controller;

import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.domain.game.Command;
import chess.domain.game.GameStatus;
import chess.service.ChessService;
import spark.Request;

import java.sql.SQLException;

public class WebController {
    private static final ChessService chessService = new ChessService();

    public ResponseDto loadGame() throws SQLException {
        int roomNumber = chessService.findRoomNumber();
        GameStatus state = chessService.findState(roomNumber);

        if (state == GameStatus.FINISH) {
            return chessService.loadInitGame();
        } else if (state == GameStatus.RUNNING) {
            return chessService.loadPlayingGame();
        }

        throw new IllegalArgumentException("게임을 불러올 수 없습니다.");
    }

    public ResponseDto moveChessPiece(Request req) throws SQLException {
        RequestDto requestDto = new RequestDto(Command.MOVE, req);

        ResponseDto responseDto = chessService.move(requestDto);

        if (responseDto.isKing()) {
            chessService.endService();
        }

        return responseDto;
    }

    public void end() throws SQLException {
        chessService.endService();
    }
}