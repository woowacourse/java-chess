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
            chessBoard = ChessBoard.generate();
            ChessBoardDto chessBoardDto = new ChessBoardDto(chessBoard.getChessBoard());
            ChessRoomDto chessRoomDto = new ChessRoomDto(chessBoardDto.getChessBoard(), Color.WHITE.name(),
                    chessBoard.sumScoreByColor(Color.BLACK), chessBoard.sumScoreByColor(Color.WHITE));
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
            ChessBoardDto chessBoardDto = new ChessBoardDto(chessBoard.getChessBoard());
            ChessRoomDto chessRoomDto = new ChessRoomDto(chessBoardDto.getChessBoard(),
                    Color.from(map.get("turn")).reverse().name(),
                    chessBoard.sumScoreByColor(Color.BLACK),
                    chessBoard.sumScoreByColor(Color.WHITE));
            return mapper.writeValueAsString(chessRoomDto);
        });

        post("/save", (req, res) -> {
            Map<String, Object> map = mapper.readValue(req.body(), new TypeReference<Map<String, Object>>() {});
            List<Map> boards = (List) map.get("chessBoard");
            Map<PositionDto, PieceDto> chessBoard = new HashMap<>();
            for (Map tmp : boards) {
                chessBoard.put(new PositionDto((String) tmp.get("position")), new PieceDto((String)tmp.get("name"), (String) tmp.get("color")));
            }
            ChessRoomDto chessRoomDto = new ChessRoomDto(chessBoard,
                    (String) map.get("turn"),
                    Double.valueOf((String)map.get("blackScore")),
                    Double.valueOf((String)map.get("whiteScore")));
            return chessDao.addChessRoom(chessRoomDto);
        });

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
