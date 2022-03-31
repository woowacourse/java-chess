package chess.controller.state;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.MoveResult;
import chess.dto.BoardDto;
import chess.dto.ScoreDto;
import chess.view.OutputView;

public abstract class Playing implements ChessGameState {
    private final OutputView outputView = OutputView.getInstance();
    private final Board board;

    Playing(Board board) {
        this.board = board;
    }

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

    MoveResult movePiece(String from, String to, Color color) {
        MoveResult move = board.move(from, to, color);
        outputView.printBoard(BoardDto.from(board));
        return move;
    }

    ChessGameState getMoveResult(MoveResult result, MoveResult blackResult) {
        if (result == MoveResult.ENDED) {
            outputView.printGameEnded(ScoreDto.from(board.getScore()));
            return new Finished();
        }

        if (result == blackResult) {
            return new BlackPlaying(board);
        }
        return new WhitePlaying(board);
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
