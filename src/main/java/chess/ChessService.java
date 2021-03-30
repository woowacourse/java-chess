package chess;

import chess.domain.ChessGame;
import chess.domain.position.Position;
import chess.exception.ChessException;

public class ChessService {


    public int move(RequestPosition requestPosition, ChessGame chessGame){
        String from = requestPosition.from();
        String to = requestPosition.to();
        try {
            chessGame.move(Position.from(from), Position.from(to));
            return 200;
        }catch (ChessException e){
            System.out.println(e.getMessage());
            return 400;
        }
    }
}
