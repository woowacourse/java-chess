package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.team.Color;

public class WhiteTurn extends Running {
    public WhiteTurn(Board board) {
        super(board);
    }

    @Override
    public State passTurn() {
        return new BlackTurn(board());
    }

    @Override
    public State end() {
        if (!isRunning()) {
            return new WhiteWin(board());
        }
        return new Draw(board());
    }

    @Override
    public void moveIfValidColor(Position source, Position target) {
        Piece sourcePiece = board().pieceOfPosition(source);
        if (sourcePiece.isBlack()) {
            throw new IllegalStateException(
                    Color.WHITE.name() + "턴엔 " + Color.WHITE.name() + "말만 이동 가능합니다."
            );
        }
        board().moveIfValidPosition(source, target);
    }
}
