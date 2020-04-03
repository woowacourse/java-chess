package chess;

import domain.board.Board;
import domain.state.Ended;
import domain.state.State;
import domain.pieces.Pieces;
import domain.pieces.StartPieces;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import view.InputView;
import view.OutputView;

import java.util.HashMap;
import java.util.Map;

public class WebUIChessApplication {
    private static State state;
    private static String announcement;

    public static void main(String[] args) {
        Spark.port(8080);
        Spark.staticFiles.location("/statics");

        Pieces startPieces = new Pieces(new StartPieces().getInstance());
        state = new Ended(startPieces);
        announcement = "게임을 시작하려면 start를 눌러주세요.";

        Spark.get("/chess", (request, response) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("table", BoardToTable.of(Board.of(state.getSet()).getLists()).getBoardHtml());
            map.put("announcement", announcement);
            return render(map, "/chess.html");
        });

        Spark.post("/chess", (request, response) -> {
            try {
                state = state.pushCommend(request.queryParams("commend"));
                if (state.isReported()) {
                    announcement = OutputView.getStatusAnnouncement(state.getPieces());
                }
                if (state.isPlaying()) {
                    announcement = "명령이 입력되었습니다.";
                }
                if (state.isEnded()) {
                    announcement = "게임이 종료되었습니다. 정보를 확인하려면 status, 다시 시작하려면 start를 입력해주세요.";
                }
                response.redirect("/chess");
            } catch (Exception e) {
                announcement = e.getMessage();
                response.redirect("/chess");
            }

            return "";
        });
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public static void main2(String[] args) {
        OutputView.printStart();
        Pieces startPieces = new Pieces(new StartPieces().getInstance());
        State state = new Ended(startPieces);
        while (true) {
            state = state.pushCommend(InputView.inputGameCommend());
            printIfPlaying(state);
            printIfStatus(state);
        }
    }

    private static void printIfPlaying(State state) {
        if (state.isPlaying()) {
            OutputView.printBoard(Board.of(state.getSet()));
        }
    }

    private static void printIfStatus(State state) {
        if (state.isReported()) {
            OutputView.getStatusAnnouncement(state.getPieces());
        }
    }
}
