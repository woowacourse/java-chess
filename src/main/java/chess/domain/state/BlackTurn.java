package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Team;

public class BlackTurn extends Play {

    public BlackTurn(ChessBoard chessBoard) {
        super(chessBoard, Team.BLACK);
    }

    @Override
    GameState reverseTurn() {
        return new WhiteTurn(getChessBoard());
    }
}
