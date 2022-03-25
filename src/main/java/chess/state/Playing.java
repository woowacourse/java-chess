package chess.state;

import chess.domain.MoveResult;
import chess.domain.board.Board;
import chess.dto.BoardDto;
import chess.dto.ScoreDto;
import chess.view.OutputView;

public class Playing implements State {
    private final OutputView outputView = OutputView.getInstance();
    private final Board board;

    public Playing(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        System.out.println("이미 실행중입니다");
        return this;
    }

    @Override
    public State move(String from, String to) {
        MoveResult move = board.move(from, to);
        outputView.printBoard(BoardDto.from(board));

        if (move == MoveResult.ENDED) {
            outputView.printGameEnded(ScoreDto.from(board.getScore()));
            return new Finished();
        }

        return this;
    }

    @Override
    public State status() {
        outputView.printScore(ScoreDto.from(board.getScore()));
        return this;
    }

    @Override
    public State end() {
        return new Finished();
    }
}
