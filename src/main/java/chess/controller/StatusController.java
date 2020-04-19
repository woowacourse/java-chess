package chess.controller;

import chess.domain.ChessRunner;
import chess.dto.BoardScoreDTO;

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
        List<BoardScoreDTO> boardScoreDtos = chessRunner.calculateScores();
        String scores = boardScoreDtos.stream()
                .map(dto -> dto.getTeam() + DELIMITER + dto.getBoardScore())
                .collect(Collectors.joining(NEW_LINE));

        outputView.printStatus(scores);
        printBoard(chessRunner.getBoardEntities());
    }
}
