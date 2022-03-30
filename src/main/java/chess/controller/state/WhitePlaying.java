package chess.controller.state;

import chess.domain.Color;
import chess.domain.board.Board;
import chess.domain.board.MoveResult;
import chess.dto.BoardDto;
import chess.dto.ScoreDto;

public class WhitePlaying extends Playing  {
    public WhitePlaying(Board board) {
        this.board = board;
    }

    @Override
    public ChessGameState move(String from, String to) {
        MoveResult move = board.move(from, to, Color.WHITE);
        outputView.printBoard(BoardDto.from(board));

        return getMoveResult(move);
    }

    private ChessGameState getMoveResult(MoveResult moveResult) {
        if (moveResult == MoveResult.ENDED) {
            outputView.printGameEnded(ScoreDto.from(board.getScore()));
            return new Finished();
        }

        if (moveResult == MoveResult.SUCCESS) {
            return new BlackPlaying(board);
        }
        return new WhitePlaying(board);
    }
}
