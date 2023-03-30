package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;
import chess.domain.position.PositionFactory;
import chess.domain.position.Rank;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

final class BoardFactory {
    
    private static final List<Piece> WHITE_GENERALS = PieceFactory.createWhiteGenerals();
    private static final List<Piece> WHITE_PAWNS = PieceFactory.createWhitePawns();
    private static final List<Piece> BLACK_PAWNS = PieceFactory.createBlackPawns();
    private static final List<Piece> BLACK_GENERALS = PieceFactory.createBlackGenerals();
    
    private static final Map<Rank, Function<Position, Piece>> INITIAL_BOARD_MAP = new EnumMap<>(Rank.class);
    
    static {
        INITIAL_BOARD_MAP.put(Rank.ONE, position -> WHITE_GENERALS.get(position.getFileIndex()));
        INITIAL_BOARD_MAP.put(Rank.TWO, position -> WHITE_PAWNS.get(position.getFileIndex()));
        INITIAL_BOARD_MAP.put(Rank.THREE, position -> PieceFactory.createEmptyPiece());
        INITIAL_BOARD_MAP.put(Rank.FOUR, position -> PieceFactory.createEmptyPiece());
        INITIAL_BOARD_MAP.put(Rank.FIVE, position -> PieceFactory.createEmptyPiece());
        INITIAL_BOARD_MAP.put(Rank.SIX, position -> PieceFactory.createEmptyPiece());
        INITIAL_BOARD_MAP.put(Rank.SEVEN, position -> BLACK_PAWNS.get(position.getFileIndex()));
        INITIAL_BOARD_MAP.put(Rank.EIGHT, position -> BLACK_GENERALS.get(position.getFileIndex()));
    }
    
    private BoardFactory() {
    }
    
    public static Map<Position, Piece> create() {
        Map<Position, Piece> board = new TreeMap<>();
        for (Position position : PositionFactory.getPositions()) {
            board.put(position, INITIAL_BOARD_MAP.get(position.getRank()).apply(position));
        }
        return board;
    }
}
    

