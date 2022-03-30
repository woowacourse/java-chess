package chess.controller.state;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.MoveResult;
import chess.dto.BoardDto;
import chess.dto.ScoreDto;

public class BlackPlaying extends Playing {

    public BlackPlaying(Board board) {
        this.board = board;
    }

    @Override
    public ChessGameState move(String from, String to) {
        MoveResult move = board.move(from, to, Color.BLACK);
        outputView.printBoard(BoardDto.from(board));

        return getMoveResult(move);
    }

    private ChessGameState getMoveResult(MoveResult moveResult) {
        if (moveResult == MoveResult.ENDED) {
            outputView.printGameEnded(ScoreDto.from(board.getScore()));
            return new Finished();
        }

        if (moveResult == MoveResult.SUCCESS) {
            return new WhitePlaying(board);
        }
        return new BlackPlaying(board);
    }
}
