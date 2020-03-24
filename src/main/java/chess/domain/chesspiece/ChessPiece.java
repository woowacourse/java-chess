package chess.domain.chesspiece;

import chess.Color;
import chess.Position;

public class ChessPiece {
    Position position;
    chess.Color Color;

    public ChessPiece(){
        this.position = new Position();
        this.Color = new Color();
    }

}
