package chess.controller.state;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.dto.ScoreDto;

public class Ready implements ChessGameState {

    @Override
    public ChessGameState start() {
        Board board = BoardFactory.createBoard();
        return new WhitePlaying(board);
    }

    @Override
    public ChessGameState move(String from, String to) {
        return alertReady();
    }

    @Override
    public ScoreDto status() {
        throw new IllegalStateException("게임 시작 전에는 start 또는 end만 가능합니다.");
    }

    @Override
    public ChessGameState end() {
        return new Finished();
    }

    @Override
    public Turn getTurn() {
        return Turn.START;
    }

    @Override
    public Board getBoard() {
        throw new IllegalStateException("게임 시작 전에는 start 또는 end만 가능합니다.");
    }

    @Override
    public boolean isEnded() {
        return false;
    }

    private Ready alertReady() {
        throw new IllegalStateException("게임 시작 전에는 start 또는 end만 가능합니다.");
    }
}
