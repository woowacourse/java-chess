package chess.web.utils;

import java.util.Map;

import chess.domain.game.ChessGame;
import chess.domain.game.state.position.Position;
import chess.domain.piece.Piece;

public class Request {
    public static Map<Position, Piece> move(ChessGame chessGame, String command) {
        String[] positions = command.split(",");
        return chessGame.move(getPositionFrom(positions[0]), getPositionFrom(positions[1]));
    }

    private static Position getPositionFrom(String position) {
        return RequestConverter.positionFrom(position);
    }
}
