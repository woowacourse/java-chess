package chess.piece;

import chess.piece.coordinate.Coordinate;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum PieceMatcher {
    KING(SymbolMatcher.KING, King::new),
    QUEEN(SymbolMatcher.QUEEN, Queen::new),
    ROOK(SymbolMatcher.ROOK, Rook::new),
    KNIGHT(SymbolMatcher.KNIGHT, Knight::new),
    BISHOP(SymbolMatcher.BISHOP, Bishop::new),
    PAWN(SymbolMatcher.PAWN, Pawn::new),
    EMPTY(SymbolMatcher.EMPTY, Empty::new);
    
    private final SymbolMatcher symbol;
    private final BiFunction<Team, Coordinate, Piece> pieceMaker;
    
    PieceMatcher(SymbolMatcher symbol, BiFunction<Team, Coordinate, Piece> pieceMaker) {
        this.symbol = symbol;
        this.pieceMaker = pieceMaker;
    }
    
    public static Piece of(SymbolMatcher symbol,Team team, Coordinate coordinate) {
        return findPieceMatcherBySymbol(symbol)
                .pieceMaker
                .apply(team, coordinate);
    }
    
    private static PieceMatcher findPieceMatcherBySymbol(SymbolMatcher symbol) {
        return Arrays.stream(values())
                .filter(pieceMatcher -> pieceMatcher.isSameSymbol(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 심볼입니다."));
    }
    
    private boolean isSameSymbol(SymbolMatcher symbol) {
        return this.symbol == symbol;
    }
}
