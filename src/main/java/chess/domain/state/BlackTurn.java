package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;

final class BlackTurn extends Running {

    public BlackTurn(Board board) {
        super(board);
    }

    @Override
    public State movePiece(Position source, Position destination) {
        board.move(source, destination, Color.BLACK);
        if (!board.hasKing(Color.WHITE)) {
            return new BlackWin(board);
        }
        return new WhiteTurn(board);
    }

    @Override
    public Color getTurn() {
        return Color.BLACK;
    }
}
