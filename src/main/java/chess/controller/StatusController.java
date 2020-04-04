package chess.controller;

import chess.controller.dto.BoardScoreDto;
import chess.controller.dto.TeamDto;
import chess.domain.ChessRunner;

public class StatusController extends GameController {
    public StatusController() {
        super();
    }

    @Override
    public void execute(ChessRunner chessRunner, String input) {
        BoardScoreDto boardScoreDto = new BoardScoreDto(chessRunner.calculateScore());
        TeamDto teamDto = new TeamDto(chessRunner.getCurrentTeam());
        outputView.printStatus(boardScoreDto.getBoardScore(), teamDto.getTeamName());
        printBoard(chessRunner.getBoardEntities());
    }
}
