package chess.domain.board.search;

import chess.domain.board.position.Position;
import chess.domain.piece.jumper.King;
import chess.domain.piece.Piece;

import java.util.Map;
import java.util.stream.Collectors;

public class BoardSearch {

    private static final int ELEMENTS_UNIT_VALUE = 1;
    private static final int UPPER_LIMIT_KING_DEAD = 1;
    private static final Class<King> KING_CLASS_TYPE = King.class;

    private final Map<Class<? extends Piece>, Integer> boardSearchingMap;

    private BoardSearch(final Map<Class<? extends Piece>, Integer> boardSearchingMap) {
        this.boardSearchingMap = boardSearchingMap;
    }

    public static BoardSearch countPiecePerClassTypeFrom(final Map<Position, Piece> chessBoard) {

        return new BoardSearch(chessBoard.entrySet()
                                         .stream()
                                         .collect(Collectors.groupingBy(
                                                 it -> it.getValue().getClass(),
                                                 Collectors.summingInt(value -> ELEMENTS_UNIT_VALUE)
                                         )));
    }

    public boolean isKingDead() {
        return boardSearchingMap.get(KING_CLASS_TYPE) <= UPPER_LIMIT_KING_DEAD;
    }
}
