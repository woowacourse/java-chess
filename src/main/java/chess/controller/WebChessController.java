package chess.controller;

import chess.controller.dto.BoardScoreDto;
import chess.controller.dto.TileDto;
import chess.domain.ChessRunner;

import java.util.List;

public class WebChessController {
    private ChessRunner chessRunner;

    public List<TileDto> start() {
        chessRunner = new ChessRunner();
        return chessRunner.tileDtos();
    }

    public void move(String source, String target) {
        chessRunner.update(source, target);
    }

    public BoardScoreDto status() {
        return new BoardScoreDto(chessRunner.calculateScore());
    }
}
