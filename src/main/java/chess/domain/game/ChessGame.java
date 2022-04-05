package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Score;
import chess.domain.board.generator.BoardGenerator;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.dto.MoveRequestDto;

public class ChessGame {

    private Board board;

    public void start(BoardGenerator boardGenerator) {
        board = boardGenerator.create();
    }

    public void move(MoveRequestDto moveRequestDto) {
        validCheck();
        board.move(Position.of(moveRequestDto.getFrom())
                , Position.of(moveRequestDto.getTo()));
    }

    public Score status() {
        validCheck();
        return board.createResult();
    }

    private void validCheck() {
        if (board != null && board.check()) {
            throw new IllegalStateException("현재 check 상황입니다.");
        }
    }

    public boolean isEnd(Command command) {
        return command.isEnd() || isCheckmate();
    }

    private boolean isCheckmate() {
        return board != null && board.checkmate();
    }

    public Board getBoard() {
        return board;
    }

    public Team getTurn() {
        return board.getTurn();
    }

    public void end() {
        board = null;
    }
}
