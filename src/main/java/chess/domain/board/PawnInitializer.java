package chess.domain.board;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Horizontal;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PawnInitializer implements LocationInitializer {

    private static final List<String> HORIZONTALS = Horizontal.horizontalSymbols();
    private static final List<String> VERTICALS_WHITE = Collections.singletonList("2");
    private static final List<String> VERTICALS_BLACK = Collections.singletonList("7");

    @Override
    public Map<Position, Piece> initialize() {
        final Map<Position, Piece> chessBoard = new HashMap<>();
        for (final String horizontal : HORIZONTALS) {
            VERTICALS_BLACK.forEach(vertical -> chessBoard.put(new Position(horizontal, vertical), new Pawn(Team.BLACK)));
            VERTICALS_WHITE.forEach(vertical -> chessBoard.put(new Position(horizontal, vertical), new Pawn(Team.WHITE)));
        }
        return chessBoard;
    }
}
