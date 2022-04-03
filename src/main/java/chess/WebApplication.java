package chess;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.domain.board.BasicChessBoardGenerator;
import chess.domain.board.Position;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.web.MoveCommand;
import chess.web.MoveResponseDto;
import chess.web.RequestToCommand;
import com.google.gson.Gson;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        port(8089);
        staticFileLocation("/static");
        var ref = new Object() {
            State state = initializeState();
        };

        Gson gson = new Gson();

        get("/", (req, res) -> {
            Map<String, Object> model = ref.state.getBoard().toMap();
            return render(model, "index.html");
        });

        get("/initialize", (req, res) -> {
            ref.state = initializeState();
            res.redirect("/");
            return null;
        });

        post("/move", (req, res) -> {
            final MoveCommand command = RequestToCommand.toMoveCommand(req.body());
            ref.state = ref.state.movePiece(Position.of(command.getSource()), Position.of(command.getDestination()));
            boolean finished = ref.state.isFinished();

            return gson.toJson(new MoveResponseDto(command.getSource(), command.getDestination(), finished));
        });
    }

    private static State initializeState() {
        return Ready.start(BasicChessBoardGenerator.generator());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
