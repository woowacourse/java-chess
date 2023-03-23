package chess.domain.game.command;

import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import java.util.List;

public class BoardQuery implements Query {

    @Override
    public List<List<Piece>> execute(ChessGame chessGame) {
        return chessGame.getPieces();
    }
}
