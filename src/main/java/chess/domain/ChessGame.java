package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardGenerator;
import chess.domain.position.Square;

public final class ChessGame {
    private static final String ERROR_MESSAGE_TURN = "[ERROR] 순서 지키시지?!";

    private Board board;
    private Color turn;

    public ChessGame(BoardGenerator boardGenerator) {
        this.board = new Board(boardGenerator);
        this.turn = GameTurn.READY;
    }

    public void move(Square source, Square target) {
        checkTurn(source);
        board.checkCanMove(source, target);
        turn = turn.switchColor();
        checkKingDie(target);
        board = board.move(source, target);
    }

    private void checkTurn(Square source) {
        if (!board.isRightTurn(source, turn)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_TURN);
        }
    }

    private void checkKingDie(Square target) {
        if (board.isTargetKing(target)) {
            turn = Color.NONE;
        }
    }

    public boolean isKingDie() {
        return turn == Color.NONE;
    }

    public Board getBoard() {
        return board;
    }
}
