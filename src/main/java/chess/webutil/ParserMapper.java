package chess.webutil;

import chess.domain.board.Board;
import chess.domain.piece.Team;
import chess.domain.result.GameResult;
import spark.Request;

import java.util.Arrays;
import java.util.Map;

public enum ParserMapper {
    START("새 게임", (request, board) -> {
        board.initialize();
        return ModelParser.parseBoard(board);
    }),
    END("게임 종료", (request, board) -> {
        board.clear();
        return ModelParser.parseBoard(board);
    }),
    STATUS("점수 현황", (request, board) -> {
        GameResult gameResult = new GameResult();
        Map<String, Object> model = ModelParser.parseBoard(board);
        model.put("white", gameResult.calculateScore(board, Team.WHITE));
        model.put("black", gameResult.calculateScore(board, Team.BLACK));
        return model;
    });

    private final String name;
    private final ThrowingBiFunction generateModel;

    ParserMapper(String name, ThrowingBiFunction generateModel) {
        this.name = name;
        this.generateModel = generateModel;
    }

    public static ParserMapper of(String name) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("선택 가능한 옵션이 아닙니다."));
    }

    public Map<String, Object> generateModel(Request request, Board board) {
        return this.generateModel.action(request, board);
    }

    private interface ThrowingBiFunction {
        Map<String, Object> action(Request request, Board board);
    }
}
