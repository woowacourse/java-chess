package chess.controller;

import chess.domain.ChessBoard;
import chess.domain.dao.ChessDao;
import chess.domain.dto.ChessBoardDto;
import chess.domain.dto.ChessRoomDto;
import chess.domain.dto.PieceDto;
import chess.domain.dto.PositionDto;
import chess.domain.piece.Piece;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.*;
import static spark.Spark.get;

public class WebChessController {
    private static ChessBoard chessBoard;
    private static ObjectMapper mapper;
    private static ChessDao chessDao;

    public WebChessController() {
        mapper = new ObjectMapper();
        chessDao = new ChessDao();
    }

    public void run() {
        staticFileLocation("static");
        chessMain();
        startChess();
        showRoute();
        movePiece();
        loadChess();
        exitChess();
    }

    private void chessMain() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private void startChess() {
        get("/start", (req, res) -> {
            chessDao.deleteChessRoomByRoomNo(1);
            chessBoard = ChessBoard.generate();
            ChessBoardDto chessBoardDto = new ChessBoardDto(chessBoard.getChessBoard());
            ChessRoomDto chessRoomDto = new ChessRoomDto(
                    chessBoardDto.getChessBoard(), Color.WHITE.name(),
                    chessBoard.sumScoreByColor(Color.BLACK), chessBoard.sumScoreByColor(Color.WHITE));
            chessDao.addChessRoom(chessRoomDto);
            chessRoomDto = chessDao.findChessRoomByRoomNo(1);
            return mapper.writeValueAsString(chessRoomDto);
        });
    }

    private void showRoute() {
        post("/route", (req, res) -> {
            Map<String, String> map = mapper.readValue(req.body(), new TypeReference<Map<String, String>>() {});
            String sourceValue = map.get("source");
            Position source = Position.of(sourceValue.charAt(0), sourceValue.charAt(1));
            Piece sourcePiece = chessBoard.findByPosition(source);
            List<PositionDto> routes = chessBoard.routes(sourcePiece, source).stream()
                    .map(position -> new PositionDto(String.valueOf(position.getX())+position.getY()))
                    .collect(Collectors.toList());
            return mapper.writeValueAsString(routes);
        });
    }

    private void movePiece() {
        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                Map<String, String> map = mapper.readValue(req.body(), new TypeReference<Map<String, String>>() {});
                Position source = Position.of(map.get("source").charAt(0), map.get("source").charAt(1));
                Position target = Position.of(map.get("target").charAt(0), map.get("target").charAt(1));
                chessBoard.movePiece(source, target);
                ChessRoomDto chessRoomDto = new ChessRoomDto(Color.from(map.get("turn")).reverse().name(), chessBoard.sumScoreByColor(Color.BLACK), chessBoard.sumScoreByColor(Color.WHITE));
                Piece nowPiece = chessBoard.findByPosition(target);
                chessDao.updateChessRoom(chessRoomDto, new PieceDto(nowPiece.getName(), nowPiece.getColor().name()), new PositionDto(map.get("source")), new PositionDto(map.get("target")));
                chessRoomDto = chessDao.findChessRoomByRoomNo(1);
                model.put("chessRoomInfo", chessRoomDto);
                model.put("isAliveAllKings", chessBoard.isAliveAllKings());
                return mapper.writeValueAsString(model);
            }catch (Exception e) {
                model.put("error", e.getMessage());
                return mapper.writeValueAsString(model);
            }
        });
    }

    private void loadChess() {
        get("/load", (req, res) -> {
            try {
                ChessRoomDto chessRoomDto = chessDao.findChessRoomByRoomNo(Integer.valueOf(req.queryParams("roomNo")));
                return mapper.writeValueAsString(chessRoomDto);
            }catch (Exception e) {
                Map<String, Object> model = new HashMap<>();
                model.put("error", e.getMessage());
                return mapper.writeValueAsString(model);
            }
        });
    }

    private void exitChess() {
        get("/exit", (req, res) -> {
            int roomNo = Integer.parseInt(req.queryParams("roomNo"));
            return chessDao.deleteChessRoomByRoomNo(roomNo);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
