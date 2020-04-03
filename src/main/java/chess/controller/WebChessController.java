package chess.controller;

import chess.controller.dto.BoardScoreDto;
import chess.controller.dto.MoveResultDto;
import chess.controller.dto.TeamDto;
import chess.controller.dto.TileDto;
import chess.domain.ChessRunner;

import java.util.List;

public class WebChessController {
    private ChessRunner chessRunner;

    public void start() {
        chessRunner = new ChessRunner();
    }

    public MoveResultDto move(String source, String target) {
        try {
            chessRunner.update(source, target);
            String moveResult = source + " -> " + target;
            return new MoveResultDto(moveResult);
        } catch (IllegalArgumentException e) {
            return new MoveResultDto(e.getMessage());
        }
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
