package chess.controller;

import chess.persistence.dto.ChessBoardDTO;
import chess.persistence.dto.ChessGameDTO;
import chess.persistence.dto.ChessMoveDTO;
import chess.persistence.dto.ErrorDTO;
import chess.service.BoardGeneratorService;
import chess.service.ChessGameStatusService;
import chess.service.GameGeneratorService;
import chess.service.PieceMoveService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class ChessGameController {

    public static Object currentChessBoard(Request req, Response res) {
        try {
            ChessGameDTO chessGameDTO = GameGeneratorService.getInstance().request();
            ChessBoardDTO chessBoardDTO = BoardGeneratorService.getInstance().request(chessGameDTO);
            return ChessGameStatusService.getInstance().request(chessGameDTO, chessBoardDTO);
        } catch (IllegalArgumentException e) {
            return new ErrorDTO(e.getMessage());
        }
    }

    public static Object changeChessBoard(Request req, Response res) {
        Gson gson = new Gson();
        try {
            ChessMoveDTO chessMoveDTO = gson.fromJson(req.body(), ChessMoveDTO.class);
            ChessGameDTO chessGameDTO = GameGeneratorService.getInstance().request();
            ChessBoardDTO chessBoardDTO = BoardGeneratorService.getInstance().request(chessGameDTO);
            ChessBoardDTO newChessBoardDTO = PieceMoveService.getInstance().request(chessMoveDTO, chessGameDTO, chessBoardDTO);
            return ChessGameStatusService.getInstance().request(chessGameDTO, newChessBoardDTO);
        } catch (IllegalArgumentException e) {
            return new ErrorDTO(e.getMessage());
        }
    }
}
