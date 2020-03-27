package chess.domain.piece.abstraction;

import chess.domain.board.BoardSquare;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class OneTimeMovePiece extends Piece implements Movable {

    protected OneTimeMovePiece(Color color, Type type) {
        super(color, type);
    }

    @Override
    public Set<BoardSquare> getCheatSheet(BoardSquare boardSquare, Map<BoardSquare, Piece> board) {
        return getAllCheatSheet(boardSquare).stream()
            .filter(s -> !(board.containsKey(s) && isSameColor(board.get(s))))
            .collect(Collectors.toSet());
    }
}
