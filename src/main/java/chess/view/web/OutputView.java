package chess.view.web;

import chess.controller.dto.BoardDto;
import chess.controller.dto.RoomDto;
import chess.controller.dto.ScoresDto;
import chess.controller.dto.WinnerDto;
import chess.domain.piece.Owner;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class OutputView {

    private OutputView() {

    }

    public static String printRoomList(List<RoomDto> roomDtos) {
        Map<String, Object> model = new HashMap<>();
        model.put("list", roomDtos);
        return render(model, "mainPage.html");
    }

    public static String printGame(RoomDto roomDto, BoardDto boardDto, ScoresDto scoresDto) {
        Map<String, Object> model = new HashMap<>();
        model.put("room", roomDto);
        model.put("board", boardDto);
        model.put("scores", scoresDto);
        return render(model, "chessBoard.html");
    }

    public static Object printResult(Queue<Owner> winner) {
        Map<String, Object> model = new HashMap<>();

        WinnerDto winnerDto = new WinnerDto();
        if (winner.size() == 1) {
            winnerDto.setWinner(winner.peek().name());
        }

        if (winner.size() != 1) {
            winnerDto.setWinner("무승부");
        }
        model.put("winner", winnerDto);
        return render(model, "result.html");
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
