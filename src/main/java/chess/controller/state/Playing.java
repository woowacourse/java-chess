package chess.controller.state;

import chess.domain.board.Board;
import chess.dto.ScoreDto;
import chess.view.OutputView;

public abstract class Playing implements ChessGameState {
    OutputView outputView = OutputView.getInstance();
    Board board;

    @Override
    public ChessGameState start() {
        System.out.println("이미 실행중입니다");
        return this;
    }

    @Override
    public ChessGameState status() {
        outputView.printScore(ScoreDto.from(board.getScore()));
        return this;
    }

    @Override
    public ChessGameState end() {
        return new Finished();
    }

    @Override
    public boolean isEnded() {
        return false;
    }
}
