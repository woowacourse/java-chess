package chess.view;

import chess.domain.board.Team;
import chess.domain.dto.GameInfoDto;
import chess.domain.dto.HistoryDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelView {
    private ModelView() {
    }

    public static Map<String, Object> commonResponseForm(GameInfoDto gameInfoDto, String id) {
        Map<String, Object> model = new HashMap<>();
        model.put("squares", gameInfoDto.squares());
        model.put("turn", gameInfoDto.turn());
        model.put("scores", gameInfoDto.scores());
        model.put("gameId", id);
        return model;
    }

    public static Map<String, Object> commonResponseForm(GameInfoDto gameInfoDto) {
        Map<String, Object> model = new HashMap<>();
        model.put("squares", gameInfoDto.squares());
        model.put("turn", gameInfoDto.turn());
        model.put("scores", gameInfoDto.scores());
        return model;
    }

    public static Map<String, Object> startResponse(List<HistoryDto> history) {
        Map<String, Object> model = new HashMap<>();
        if (!history.isEmpty()) {
            model.put("history", history);
        }
        return model;
    }

    public static Map<String, Object> newGameResponse(GameInfoDto gameInfoDto, String id) {
        return commonResponseForm(gameInfoDto, id);
    }

    public static Map<String, Object> newGameResponse(GameInfoDto gameInfoDto) {
        return commonResponseForm(gameInfoDto);
    }

    public static Map<String, Object> moveResponse(GameInfoDto gameInfoDto, String id) {
        Map<String, Object> model = commonResponseForm(gameInfoDto, id);
        final Team winner = gameInfoDto.winner();
        if (winner != null) {
            model.put("winner", winner);
        }
        return model;
    }
}
