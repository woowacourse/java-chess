package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.team.Color;

public class BlackTurn extends Running {
    public BlackTurn(Board board) {
        super(board);
    }

    @Override
    public boolean isSameColor(Color color) {
        return Color.BLACK.equals(color);
    }

    @Override
    public State passTurn() {
        return new WhiteTurn(board());
    }

    @Override
    public State end() {
        if (isFinished()) {
            return new BlackWin(board());
        }
        return new Draw(board());
    }

    @Override
    public void moveIfValidColor(Position source, Position target) {
        Piece sourcePiece = board().pieceOfPosition(source);
        if (sourcePiece.isWhite()) {
            throw new IllegalStateException(
                    Color.BLACK.name() + "턴엔 " + Color.BLACK.name() + "말만 이동 가능합니다."
            );
        }
        board().moveIfValidPosition(source, target);
    }
}
