package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.dao.BoardDaoImpl;
import chess.dao.GameDaoImpl;
import chess.domain.position.Position;
import chess.dto.request.UpdatePiecePositionDto;
import chess.dto.response.BoardDto;
import chess.dto.response.PieceColorDto;
import chess.dto.response.ScoreResultDto;
import chess.service.ChessService;
import chess.util.BodyParser;
import chess.util.JsonMapper;
import java.util.Map;
import spark.Request;

public class WebController {

    private static final String GAME_ID = "game-id"; // TODO: 여러 게임 방 기능 구현시 제거

    private final ChessService chessService;

    public WebController() {
        this.chessService = ChessService.of(new GameDaoImpl(), new BoardDaoImpl());
    }

    public void run() {
        get("/board", (req, res) -> getBoard());
        get("/turn", (req, res) -> getTurn());
        get("/score", (req, res) -> getScore());
        get("/winner", (req, res) -> getWinner());
        post("/move", (req, res) -> movePiece(req));
        post("/initialize", (req, res) -> initialize());
    }

    private String getBoard() {
        try {
            BoardDto boardDto = chessService.getBoard(GAME_ID);
            return JsonMapper.boardDtoToJson(boardDto);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return JsonMapper.exceptionToJson(e);
        }
    }

    private String getTurn() {
        try {

            PieceColorDto pieceColorDto = chessService.getCurrentTurn(GAME_ID);
            return JsonMapper.turnToJson(pieceColorDto);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return JsonMapper.exceptionToJson(e);
        }
    }

    private String getScore() {
        try {
            ScoreResultDto scoreResultDto = chessService.getScore(GAME_ID);
            return JsonMapper.scoreResultDtoToJson(scoreResultDto);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return JsonMapper.exceptionToJson(e);
        }
    }

    private String getWinner() {
        try {
            return JsonMapper.winnerToJson(chessService.getWinColor(GAME_ID));
        } catch (IllegalStateException | IllegalArgumentException e) {
            return JsonMapper.exceptionToJson(e);
        }
    }

    private String movePiece(Request req) {
        try {
            String request = req.body();
            Map<String, String> moveRequest = BodyParser.parseToMap(request);

            Position from = Position.from(moveRequest.get("from"));
            Position to = Position.from(moveRequest.get("to"));

            chessService.movePiece(UpdatePiecePositionDto.of(GAME_ID, from, to));
        } catch (IllegalStateException e) {
            return e.getMessage();
        }

        return "success";
    }

    // TODO: Exception 으로 catch 하면 안됨
    private String initialize() {
        try {
            chessService.initializeGame(GAME_ID);
        } catch (Exception e) {
            return "fail";
        }
        return "success";
    }
}
