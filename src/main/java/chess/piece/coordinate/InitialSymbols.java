package chess.piece.coordinate;

import java.util.List;

public enum InitialSymbols {
    EIGHT_AND_ONE(List.of('r', 'n', 'b', 'q', 'k', 'b', 'n', 'r')),
    SEVEN_AND_TWO(List.of('p', 'p', 'p', 'p', 'p', 'p', 'p', 'p')),
    SIX_TO_THREE(List.of('e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'));
    
    private final List<Character> symbols;
    
    InitialSymbols(List<Character> symbols) {
        this.symbols = symbols;
    }
    
    public static InitialSymbols from(int rowNum) {
        if (rowNum == 8 || rowNum == 1) {
            return EIGHT_AND_ONE;
        }
    
        if (rowNum == 7 || rowNum == 2) {
            return SEVEN_AND_TWO;
        }
        
        return SIX_TO_THREE;
    }
    
    public Character findSymbolByColumnIndex(int columnIndex) {
        return symbols.get(columnIndex);
    }
}
