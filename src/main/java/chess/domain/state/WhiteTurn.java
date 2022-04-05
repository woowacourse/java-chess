package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Team;

public class WhiteTurn extends Play {

    public WhiteTurn(ChessBoard chessBoard) {
        super(chessBoard, Team.WHITE);
    }

    @Override
    GameState reverseTurn() {
        return new BlackTurn(getChessBoard());
    }
}
