package chess.domain.turn;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.PromotionPiece;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.Map.Entry;

public interface GameTurn {

    GameTurn nextTurn();

    void movePiece(Position source, Position target);

    Entry<Position, Piece> promotion(PromotionPiece promotionPiece);

    Color color();

    boolean isEnd();

    Map<Position, Piece> pieces();
}
