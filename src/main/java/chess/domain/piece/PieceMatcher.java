package chess.domain.piece;

import chess.domain.piece.coordinate.Coordinate;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum PieceMatcher {
    KING('k', King::new),
    QUEEN('q', Queen::new),
    ROOK('r', Rook::new),
    KNIGHT('n', Knight::new),
    BISHOP('b', Bishop::new),
    PAWN('p', Pawn::new),
    EMPTY('e', Empty::new);
    
    private final char symbol;
    private final BiFunction<Team, Coordinate, Piece> pieceMaker;
    
    PieceMatcher(char symbol, BiFunction<Team, Coordinate, Piece> pieceMaker) {
        this.symbol = symbol;
        this.pieceMaker = pieceMaker;
    }
    
    public static Piece of(char symbol,Team team, Coordinate coordinate) {
        return findPieceMatcherBySymbol(symbol)
                .pieceMaker
                .apply(team, coordinate);
    }
    
    private static PieceMatcher findPieceMatcherBySymbol(char symbol) {
        return Arrays.stream(values())
                .filter(pieceMatcher -> pieceMatcher.isSameSymbol(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 심볼입니다."));
    }
    
    private boolean isSameSymbol(char symbol) {
        return this.symbol == symbol;
    }
}
