package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.dto.MoveRequestDto;
import chess.dto.MoveResultDto;
import chess.view.JsonTransformer;
import chess.view.WebViewMapper;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    private static final Board BOARD = BoardFactory.newInstance();

    public static void main(String[] args) {
        staticFiles.location("/static");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("board", WebViewMapper.parse(BOARD.getBoard()));
            return render(model, "index.html");
        });

        get("/board", (request, response) -> WebViewMapper.parse(BOARD.getBoard())
                , new JsonTransformer());

        post("/move", (request, response) -> {
            final MoveRequestDto moveRequestDto = new Gson().fromJson(request.body(), MoveRequestDto.class);
            final boolean moveResult = BOARD.move(Position.from(moveRequestDto.getFrom()),
                    Position.from(moveRequestDto.getTo()));
            return new MoveResultDto(moveRequestDto.getPiece(),
                    WebViewMapper.parse(Position.from(moveRequestDto.getFrom())),
                    WebViewMapper.parse(Position.from(moveRequestDto.getTo())), moveResult);
        }, new JsonTransformer());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
