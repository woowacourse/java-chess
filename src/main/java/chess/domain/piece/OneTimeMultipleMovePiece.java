package chess.domain.piece;

import chess.domain.board.BoardSquare;
import chess.domain.board.ChessInitialSetting;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class OneTimeMultipleMovePiece extends OneTimeMovePiece {

    protected OneTimeMultipleMovePiece(Color color, Type type) {
        super(color, type);
    }

    @Override
    public Set<BoardSquare> getCheatSheet(BoardSquare boardSquare, Map<BoardSquare, Piece> board,
        Set<ChessInitialSetting> castlingElements) {
        return getAllCheatSheet(boardSquare).stream()
            .filter(s -> !(board.containsKey(s) && isSameColor(board.get(s))))
            .collect(Collectors.toSet());
    }
}
