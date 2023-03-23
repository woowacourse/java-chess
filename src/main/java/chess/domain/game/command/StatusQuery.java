package chess.domain.game.command;

import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import java.util.Map;

public class StatusQuery implements Query {

    @Override
    @SuppressWarnings("unchecked")
    public Map<Color, Double> execute(ChessGame chessGame) {
        return chessGame.getStatus();
    }
}
