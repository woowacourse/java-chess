package chess.board;

import java.util.List;

public enum InitialSymbols {
    NOBLE_LINE(List.of('r', 'n', 'b', 'q', 'k', 'b', 'n', 'r')),
    PAWN_LINE(List.of('p', 'p', 'p', 'p', 'p', 'p', 'p', 'p')),
    EMPTY_LINE(List.of('e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'));
    
    private static final int FIRST_INITIAL_ROW_OF_NOBLE = 1;
    private static final int SECOND_INITIAL_ROW_OF_NOBLE = 8;
    private static final int FIRST_INITIAL_ROW_OF_PAWN = 2;
    private static final int SECOND_INITIAL_ROW_OF_PAWN = 7;
    private final List<Character> symbols;
    
    InitialSymbols(List<Character> symbols) {
        this.symbols = symbols;
    }
    
    public static InitialSymbols from(int rowNum) {
        if (isOneOrEightRow(rowNum)) {
            return NOBLE_LINE;
        }
    
        if (isTwoOrSevenRow(rowNum)) {
            return PAWN_LINE;
        }
        
        return EMPTY_LINE;
    }
    
    private static boolean isTwoOrSevenRow(int rowNum) {
        return rowNum == FIRST_INITIAL_ROW_OF_PAWN || rowNum == SECOND_INITIAL_ROW_OF_PAWN;
    }
    
    private static boolean isOneOrEightRow(int rowNum) {
        return rowNum == FIRST_INITIAL_ROW_OF_NOBLE || rowNum == SECOND_INITIAL_ROW_OF_NOBLE;
    }
    
    public Character findSymbolByColumnIndex(int columnIndex) {
        return symbols.get(columnIndex);
    }
}
