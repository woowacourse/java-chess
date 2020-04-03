package chess;

import domain.command.exceptions.CommandTypeException;
import domain.command.exceptions.MoveCommandTokensException;
import domain.pieces.exceptions.CanNotAttackException;
import domain.pieces.exceptions.CanNotMoveException;
import domain.pieces.exceptions.CanNotReachException;
import domain.state.exceptions.StateException;
import view.Announcement;
import view.board.Board;
import domain.state.Ended;
import domain.state.State;
import domain.pieces.Pieces;
import domain.pieces.StartPieces;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import view.BoardToTable;

import java.util.HashMap;
import java.util.Map;

public class WebUIChessApplication {
    private static State state;
    private static Announcement announcement;

    public static void main(String[] args) {
        Spark.port(8080);
        Spark.staticFiles.location("/statics");

        Pieces startPieces = new Pieces(new StartPieces().getInstance());
        state = new Ended(startPieces);
        announcement = Announcement.ofFirst();

        Spark.get("/chess", (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("table", BoardToTable.of(Board.of(state.getSet()).getLists()).getBoardHtml());
            map.put("announcement", announcement.getString());
            return render(map, "/chess.html");
        });

        Spark.post("/chess", (request, response) -> {
            try {
                state = state.pushCommend(request.queryParams("commend"));
                if (state.isReported()) {
                    announcement = Announcement.ofStatus(state.getPieces());
                }
                if (state.isPlaying()) {
                    announcement = Announcement.ofPlaying();
                }
                if (state.isEnded()) {
                    announcement = Announcement.ofEnd();
                }
                response.redirect("/chess");
            } catch (CommandTypeException
                    | MoveCommandTokensException
                    | CanNotMoveException
                    | CanNotAttackException
                    | CanNotReachException
                    | StateException e) {
                announcement = Announcement.of(e.getMessage());
                response.redirect("/chess");
            }
            return "";
        });
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
