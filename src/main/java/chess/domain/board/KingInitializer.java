package chess.domain.board;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KingInitializer implements LocationInitializer {

    private static final List<String> HORIZONTALS = Collections.singletonList("e");
    private static final List<String> VERTICALS_WHITE = Collections.singletonList("1");
    private static final List<String> VERTICALS_BLACK = Collections.singletonList("8");

    @Override
    public Map<Position, Piece> initialize() {
        final Map<Position, Piece> chessBoard = new HashMap<>();
        for (String horizontal : HORIZONTALS) {
            VERTICALS_BLACK.forEach(vertical -> chessBoard.put(new Position(horizontal, vertical), new King(Team.BLACK)));
            VERTICALS_WHITE.forEach(vertical -> chessBoard.put(new Position(horizontal, vertical), new King(Team.WHITE)));
        }
        return chessBoard;
    }
}
