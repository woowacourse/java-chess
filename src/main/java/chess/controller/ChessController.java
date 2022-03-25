package chess.controller;

import chess.domain.ChessExecution;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.dto.BoardDto;
import chess.dto.ScoreDto;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    public void start() {
        OutputView outputView = OutputView.getInstance();
        InputView inputView = InputView.getInstance();
        Board board = BoardFactory.newInstance();
        outputView.printBoard(BoardDto.from(board));

        String[] command = inputView.scanCommand().split(" ");
        ChessExecution execution = ChessExecution.from(command[0]);

        while (execution != ChessExecution.END) {
            if (execution == ChessExecution.MOVE) {
                board.move(command[1], command[2]);
                outputView.printBoard(BoardDto.from(board));
            }

            if (execution == ChessExecution.STATUS) {
                outputView.printScore(ScoreDto.from(board.getScore()));
            }

            command = inputView.scanCommand().split(" ");
            execution = ChessExecution.from(command[0]);
        }
    }
}
