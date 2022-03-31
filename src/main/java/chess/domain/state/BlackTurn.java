package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.board.Color;

final class BlackTurn extends Running {

    BlackTurn(Board board) {
        super(board);
    }

    @Override
    public State movePiece(Position src, Position dest) {
        board.move(src, dest, Color.BLACK);
        if (!board.hasKing(Color.WHITE)) {
            return new BlackWin();
        }
        return new WhiteTurn(board);
    }
}
