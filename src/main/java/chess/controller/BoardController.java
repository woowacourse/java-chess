package chess.controller;

import chess.model.ChessBoard;
import chess.model.ChessPieceColor;
import chess.service.BoardDTO;
import chess.service.BoardService;
import chess.service.EngineService;
import chess.service.GameService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class BoardController {
    public static final String URL = "/board/:gameId";
    private static final BoardController INSTANCE = new BoardController();

    private BoardController() {

    }

    public static BoardController getInstance() {
        return INSTANCE;
    }

    public String get(final Request req, final Response res) {
        BoardService boardService = BoardService.getInstance();

        // 보드DTO 만들기
        BoardDTO board = boardService.getBoardDTO(req.params("gameId"));

        // 보드 가져오기
        ChessBoard chessBoard = boardService.getBoard(req.params("gameId"));

        // 턴 가져오기
        GameService gameService = GameService.getInstance();
        ChessPieceColor turn = gameService.getTurn(req.params("gameId"));

        // 가져온 보드와 턴으로 엔진 만들어서 세션에 저장
        EngineService engineService = EngineService.getInstance();
        req.session().attribute("engine", engineService.getEngine(chessBoard, turn));

        Gson gson = new Gson();
        return gson.toJson(board.getBoard());
    }
}
