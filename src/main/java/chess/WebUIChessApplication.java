package chess;

import chess.domain.ChessBoard;
import chess.domain.dao.ChessDao;
import chess.domain.dto.*;
import chess.domain.piece.Piece;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static ChessBoard chessBoard;
    private static ObjectMapper mapper = new ObjectMapper();
    private static ChessDao chessDao = new ChessDao();
    public static void main(String[] args) {

        staticFileLocation("static");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            chessDao.deleteChessRoomByRoomNo(1);
            chessBoard = ChessBoard.generate();
            ChessBoardDto chessBoardDto = new ChessBoardDto(chessBoard.getChessBoard());
            ChessRoomDto chessRoomDto = new ChessRoomDto(
                    chessBoardDto.getChessBoard(), Color.WHITE.name(),
                    chessBoard.sumScoreByColor(Color.BLACK), chessBoard.sumScoreByColor(Color.WHITE));
            chessDao.addChessRoom(chessRoomDto);
            chessRoomDto = chessDao.findChessRoomByRoomNo(1);
            String jsonString = mapper.writeValueAsString(chessRoomDto);
            return jsonString;
        });

        post("/route", (req, res) -> {
            Map<String, String> map = mapper.readValue(req.body(), new TypeReference<Map<String, String>>() {});
            String sourceValue = map.get("source");
            Position source = Position.of(sourceValue.charAt(0), sourceValue.charAt(1));
            Piece sourcePiece = chessBoard.findByPosition(source);
            List<Position> routes = chessBoard.routes(sourcePiece, source);
            return mapper.writeValueAsString(routes);
        });

        post("/move", (req, res) -> {
            Map<String, String> map = mapper.readValue(req.body(), new TypeReference<Map<String, String>>() {});
            String sourceValue = map.get("source");
            String targetValue = map.get("target");
            Position source = Position.of(sourceValue.charAt(0), sourceValue.charAt(1));
            Position target = Position.of(targetValue.charAt(0), targetValue.charAt(1));
            chessBoard.movePiece(source, target);
            ChessRoomDto chessRoomDto = new ChessRoomDto(Color.from(map.get("turn")).reverse().name(),
                    chessBoard.sumScoreByColor(Color.BLACK),
                    chessBoard.sumScoreByColor(Color.WHITE));
            Piece nowPiece = chessBoard.findByPosition(target);
            chessDao.updateChessRoom(chessRoomDto, new PieceDto(nowPiece.getName(), nowPiece.getColor().name()), new PositionDto(sourceValue), new PositionDto(targetValue));
            chessRoomDto = chessDao.findChessRoomByRoomNo(1);
            return mapper.writeValueAsString(chessRoomDto);
        });

        get("/load", (req, res) -> {
            ChessRoomDto chessRoomDto = chessDao.findChessRoomByRoomNo(Integer.valueOf(req.queryParams("roomNo")));
            return mapper.writeValueAsString(chessRoomDto);
        });

        get("/exit", (req, res) -> {
            int roomNo = Integer.parseInt(req.queryParams("roomNo"));
            return chessDao.deleteChessRoomByRoomNo(roomNo);
        });

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
