package chess;

import chess.board.BoardGenerator;
import chess.manager.ChessManager;
import chess.piece.Team;
import chess.repository.InMemoryDao;
import chess.service.ChessService;
import chess.web.dto.TeamScoreDto;
import chess.web.dto.TilesDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {
    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/static");

        final ChessService chessService = new ChessService(new InMemoryDao());

        //home
        get("/home", (request, response) -> {
            Map<String, Object> model = new HashMap<>();

            ChessManager chessManager = new ChessManager(BoardGenerator.create());
            Long id = chessService.save(chessManager);

            Team currentTeam = chessManager.getCurrentTeam();
            TeamScoreDto teamScoreDto = new TeamScoreDto(chessManager);
            TilesDto tilesDto = new TilesDto(chessManager);

            model.put("id", id);
            model.put("currentTeam", currentTeam);
            model.put("teamScoreDto", teamScoreDto);
            model.put("tilesDto", tilesDto);

            return render(model, "index.html");
        });

        //start
        post("/chessboard", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ChessManager chessManager = new ChessManager(BoardGenerator.create());
            Long id = chessService.save(chessManager);
            TeamScoreDto teamScoreDto = new TeamScoreDto(chessManager);
            TilesDto tilesDto = new TilesDto(chessManager);
            System.out.println(tilesDto);

            model.put("id", id);
            model.put("teamScoreDto", teamScoreDto);


            return null;
        });

        //get id list
        get("/chessboard", (request, response) -> {

            return null;
        });

        //load chessboard
        get("/chessboard/:id", (request, response) -> {

            return null;
        });

        // move
        post("/chessboard/move", (request, response) -> {

            return null;
        });

        //surrender
        post("/surrender", (request, response) -> {

            return null;
        });

        //end
        delete("/end", (request, response) -> {

            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
