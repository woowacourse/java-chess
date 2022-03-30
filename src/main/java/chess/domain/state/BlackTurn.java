package chess.domain.state;

import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.Map;

public final class BlackTurn extends Playing {

    public BlackTurn(Map<Integer, Rank> ranks) {
        super(ranks);
    }

    @Override
    public boolean isBlackTurn() {
        return true;
    }

    @Override
    public BoardState move(Position start, Position target) {
        Piece selected = getPiece(start);

        if (!selected.isBlack()) {
            throw new IllegalArgumentException(IS_NOT_YOUR_TURN_EXCEPTION_MESSAGE);
        }

        return movePiece(start, target);
    }

    @Override
    public End judgeWinner() {
        return new BlackWin(ranks);
    }

    @Override
    public Playing judgeTurn() {
        return new WhiteTurn(ranks);
    }

    @Override
    public String findTurn() {
        return "흑";
    }
}
