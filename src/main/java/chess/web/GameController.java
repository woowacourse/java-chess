package chess.web;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.web.dto.MoveDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class GameController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final GameService gameService = new GameService();

    public void run() {

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (request, response) ->
                objectMapper.writeValueAsString(gameService.start())
        );

        post("/move", (request, response) -> {
                    MoveDto moveDto = objectMapper.readValue(request.body(), MoveDto.class);
                    return objectMapper.writeValueAsString(gameService.move(moveDto));
                }
        );

        get("/status", (request, response) ->
                objectMapper.writeValueAsString(gameService.status()));
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
