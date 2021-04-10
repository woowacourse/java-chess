package chess.view.web;

import chess.controller.dto.*;
import chess.domain.piece.Owner;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static int SIZE_OF_ONLY_WINNER = 1;

    private OutputView() {

    }

    public static String printErrorMessage(final String message) {
        final Map<String, Object> model = new HashMap<>();
        model.put("error", new ErrorDto(message));
        return render(model, "errorPage.html");
    }

    public static String printRoomList(final List<RoomDto> roomDtos) {
        final Map<String, Object> model = new HashMap<>();
        model.put("list", roomDtos);
        return render(model, "mainPage.html");
    }

    public static String printGame(final RoomDto room, final BoardDto board, final ScoresDto scores) {
        final Map<String, Object> model = new HashMap<>();
        model.put("room", room);
        model.put("board", board);
        model.put("scores", scores);
        return render(model, "chessBoard.html");
    }

    public static String printResult(final List<Owner> winners) {
        final Map<String, Object> model = new HashMap<>();
        final WinnerDto winnerDto = new WinnerDto(decideWinnerName(winners));
        model.put("winner", winnerDto);
        return render(model, "result.html");
    }

    private static String decideWinnerName(final List<Owner> winners) {
        if (winners.size() == SIZE_OF_ONLY_WINNER) {
            final Owner winner = winners.get(0);
            return winner.name();
        }
        return "무승부";
    }

    private static String render(final Map<String, Object> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
