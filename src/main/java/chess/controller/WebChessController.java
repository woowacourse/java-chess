package chess.controller;

import chess.controller.dto.BoardDto;
import chess.controller.dto.BoardScoreDto;
import chess.domain.ChessRunner;

public class WebChessController {
    private ChessRunner chessRunner;

    public BoardDto start() {
        chessRunner = new ChessRunner();
        return new BoardDto(chessRunner.getBoardEntities());
    }

    public void move(String source, String target) {
        chessRunner.update(source, target);
    }

    public BoardScoreDto status() {
        return new BoardScoreDto(chessRunner.calculateScore());
    }
}
