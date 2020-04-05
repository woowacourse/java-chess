package chess.controller;

import chess.controller.dto.BoardScoreDto;
import chess.domain.ChessRunner;

import java.util.List;
import java.util.stream.Collectors;

public class StatusController extends GameController {
    private static final String DELIMITER = ": ";
    private static final String NEW_LINE = "\n";

    public StatusController() {
        super();
    }

    @Override
    public void execute(ChessRunner chessRunner, String input) {
        List<BoardScoreDto> boardScoreDtos = chessRunner.calculateScores();
        String scores = boardScoreDtos.stream()
                .map(dto -> dto.getTeam() + DELIMITER + dto.getBoardScore())
                .collect(Collectors.joining(NEW_LINE));

        outputView.printStatus(scores);
        printBoard(chessRunner.getBoardEntities());
    }
}
