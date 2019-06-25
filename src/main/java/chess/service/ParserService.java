package chess.service;

import chess.domain.board.Board;
import chess.domain.direction.core.Square;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class ParserService {
    public static final ParserService getInstance() {
        return ParserServiceHandler.INSTANCE;
    }

    public Square squareParsing(String inputSquare) {
        String[] squareArr = inputSquare.split(",");
        try {
            return Square.of(Integer.parseInt(squareArr[0]), Integer.parseInt(squareArr[1]));
        } catch (Exception e) {
            throw new IllegalArgumentException("맞지 않는 형식입니다.");
        }
    }
//
//    public Board boardParsing(String inputBoard) {
//        Gson gson = new Gson();
//        TypeToken<Map<String, Object>>(){}.getType()
//    }

    private static class ParserServiceHandler {
        static final ParserService INSTANCE = new ParserService();
    }
}
