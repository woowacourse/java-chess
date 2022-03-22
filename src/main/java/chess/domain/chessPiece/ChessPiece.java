package chess.domain.chessPiece;

import java.util.List;

public interface ChessPiece {
    List<String> getInitWhitePosition();
    List<String> getInitBlackPosition();
    String getName();
}
