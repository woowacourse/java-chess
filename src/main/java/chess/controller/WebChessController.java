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
        this.chessRunner = new ChessRunner();
    }

    public MoveResultDto move(final String source, final String target) {
        try {
            this.chessRunner.update(source, target);
            String moveResult = moveResult(this.chessRunner, source, target);
            return new MoveResultDto(moveResult, "color:black;");
        } catch (IllegalArgumentException e) {
            return new MoveResultDto(e.getMessage(), "color:red;");
        }
    }

    private String moveResult(final ChessRunner chessRunner, final String source, final String target) {
        if (!chessRunner.isEndChess()) {
            return source + " -> " + target;
        }
        return chessRunner.getWinner() + " 가 이겼습니다.";
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
