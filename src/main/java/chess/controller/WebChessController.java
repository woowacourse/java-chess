package chess.controller;

import chess.controller.dto.BoardScoreDto;
import chess.controller.dto.TeamDto;
import chess.controller.dto.TileDto;
import chess.domain.ChessRunner;

import java.util.List;

public class WebChessController {
    private ChessRunner chessRunner;

    public void start() {
        chessRunner = new ChessRunner();
    }

    public void move(String source, String target) {
        chessRunner.update(source, target);
    }

    public BoardScoreDto status() {
        return new BoardScoreDto(chessRunner.calculateScore());
    }

    public TeamDto getCurrentTeam() {
        return new TeamDto(this.chessRunner.getCurrentTeam());
    }

    public List<TileDto> getTiles() {
        return this.chessRunner.tileDtos();
    }

    public BoardScoreDto getBoardScore() {
        return new BoardScoreDto(this.chessRunner.calculateScore());
    }
}
