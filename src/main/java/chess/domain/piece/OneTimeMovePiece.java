package chess.domain.piece;

import chess.domain.board.BoardSquare;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class OneTimeMovePiece extends Piece {

    protected OneTimeMovePiece(Color color, Type type) {
        super(color, type);
    }

    @Override
    protected int getRepeatCount() {
        return BoardSquare.MIN_FILE_AND_RANK_COUNT;
    }

    @Override
    public Set<BoardSquare> getCheatSheet(BoardSquare boardSquare, Map<BoardSquare, Piece> board) {
        return getAllCheatSheet(boardSquare).stream()
            .filter(s -> !(board.containsKey(s) && isSameColor(board.get(s))))
            .collect(Collectors.toSet());
    }
}
