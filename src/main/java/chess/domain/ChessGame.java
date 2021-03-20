package chess.domain;

import java.util.Map;

public interface ChessGame {
    void movePiece(Position currentPosition, Position targetPosition);
    GameResult gameResult();
    boolean isKingDead();
    boolean isChecked();
    boolean isCheckmate();
    Map<Position, String> nameGroupingByPosition();
    int boardSize();
    TeamColor currentColor();
}
