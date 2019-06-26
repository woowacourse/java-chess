package chess.service;

import chess.domain.board.Board;

public class ScoreService {
    public static ScoreService getInstance() {
        return ScoreServiceHandler.INSTANCE;
    }

    public void getScore(){
    }

    private static class ScoreServiceHandler {
        public static final ScoreService INSTANCE = new ScoreService();
    }
}
