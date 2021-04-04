package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.Pieces;
import chess.domain.piece.info.Color;
import chess.domain.state.BlackTurn;
import chess.domain.state.End;
import chess.domain.state.State;
import chess.domain.state.WhiteTurn;

import java.util.List;

public class ChessGameFactory {
    public static ChessGame loadChessGameByInfo(List<Piece> pieces, String turn, boolean isFinish) {
        State state = getStateByInfo(pieces, turn, isFinish);
        return new ChessGame(state);
    }

    private static State getStateByInfo(List<Piece> pieces, String turn, boolean isFinish) {
        if (isFinish) {
            return new End(new Pieces(pieces), Color.NONE);
        }
        if (Color.valueOf(turn) == Color.BLACK) {
            return new BlackTurn(new Pieces(pieces));
        }
        return new WhiteTurn(new Pieces(pieces));
    }
}
