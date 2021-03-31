package chess;

import chess.domain.game.Game;
import chess.domain.position.Position;

public class ChessService {

    public Response move(Request request, Game game) {
        String from = request.from();;
        String to = request.to();
        try {
            game.move(Position.from(from), Position.from(to));
            return new Response("200", "성공", game.currentPlayer().toString());
        } catch (Exception e) {
            return new Response("400", e.getMessage(), game.currentPlayer().toString());
        }
    }
}
