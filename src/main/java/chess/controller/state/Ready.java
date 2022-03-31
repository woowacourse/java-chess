package chess.controller.state;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.dto.BoardDto;
import chess.view.OutputView;

public class Ready implements ChessGameState {
    private final OutputView outputView = OutputView.getInstance();

    @Override
    public ChessGameState start() {
        Board board = BoardFactory.createBoard();
        outputView.printBoard(BoardDto.from(board));

        return new WhitePlaying(board);
    }

    @Override
    public ChessGameState move(String from, String to) {
        return alertReady();
    }

    @Override
    public ChessGameState status() {
        return alertReady();
    }

    @Override
    public ChessGameState end() {
        return new Finished();
    }

    @Override
    public boolean isEnded() {
        return false;
    }

    private Ready alertReady() {
        throw new IllegalStateException("게임 시작 전에는 start 또는 end만 가능합니다.");
    }
}
