package chess.domain.turn;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.PromotionPiece;

public interface GameTurn {

    GameTurn nextTurn();

    void movePiece(Position source, Position target);

    void promotion(PromotionPiece promotionPiece);

    Color color();

    boolean isEnd();
}
