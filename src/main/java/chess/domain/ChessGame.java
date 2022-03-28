package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.dto.BoardDto;
import chess.dto.ScoreDto;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessGame {
    private static final InputView inputView = InputView.getInstance();
    private static final OutputView outputView = OutputView.getInstance();
    private static final String COMMAND_DELIMITER = " ";

    private final Board board;

    public ChessGame() {
        this.board = BoardFactory.newInstance();
    }

    public void start() {
        outputView.printBoard(BoardDto.from(board));

        String[] command = inputView.scanCommand().split(COMMAND_DELIMITER);
        ChessExecution execution = ChessExecution.from(command[0]);

        play(board, command, execution);
    }

    private void play(Board board, String[] command, ChessExecution execution) {
        while (execution != ChessExecution.END) {
            if (execution == ChessExecution.MOVE) {
                MoveResult move = board.move(command[1], command[2]);
                outputView.printBoard(BoardDto.from(board));

                if (move == MoveResult.ENDED) {
                    outputView.printGameEnded(ScoreDto.from(board.getScore()));
                    break;
                }
            }

            if (execution == ChessExecution.STATUS) {
                outputView.printScore(ScoreDto.from(board.getScore()));
            }

            command = inputView.scanCommand().split(COMMAND_DELIMITER);
            execution = ChessExecution.from(command[0]);
        }
    }
}
