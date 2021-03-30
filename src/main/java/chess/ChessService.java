package chess;

import chess.domain.ChessGame;
import chess.domain.Side;
import chess.domain.position.Position;
import chess.exception.ChessException;

public class ChessService {

    public Response move(RequestPosition requestPosition, ChessGame chessGame){
        String from = requestPosition.from();
        String to = requestPosition.to();
        try {
            chessGame.move(Position.from(from), Position.from(to));
            if(chessGame.isGameSet()) {
                Side side = chessGame.state().winner();
                return new Response("300", side.name());
            }
            return new Response("200", "Succeed");
        }catch (ChessException e){
            return new Response("400", e.getMessage());
        }
    }
}
