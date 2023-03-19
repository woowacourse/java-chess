package chess.domain.board;

import java.util.List;

public enum InitialSymbols {
    EIGHT_AND_ONE(List.of('r', 'n', 'b', 'q', 'k', 'b', 'n', 'r')),
    SEVEN_AND_TWO(List.of('p', 'p', 'p', 'p', 'p', 'p', 'p', 'p')),
    SIX_TO_THREE(List.of('e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'));
    
    private static final int FIRST_INITIAL_ROW_OF_PIECES_EXCLUDING_PAWN = 1;
    private static final int SECOND_INITIAL_ROW_OF_PIECES_EXCLUDING_PAWN = 8;
    private static final int FIRST_INITIAL_ROW_OF_PAWN = 2;
    private static final int SECOND_INITIAL_ROW_OF_PAWN = 7;
    
    private final List<Character> symbols;
    
    InitialSymbols(List<Character> symbols) {
        this.symbols = symbols;
    }
    
    public static InitialSymbols from(int rowNum) {
        if (isOneOrEightRow(rowNum)) {
            return EIGHT_AND_ONE;
        }
    
        if (isTwoOrSevenRow(rowNum)) {
            return SEVEN_AND_TWO;
        }
        
        return SIX_TO_THREE;
    }
    
    private static boolean isTwoOrSevenRow(int rowNum) {
        return rowNum == FIRST_INITIAL_ROW_OF_PAWN || rowNum == SECOND_INITIAL_ROW_OF_PAWN;
    }
    
    private static boolean isOneOrEightRow(int rowNum) {
        return rowNum == FIRST_INITIAL_ROW_OF_PIECES_EXCLUDING_PAWN || rowNum == SECOND_INITIAL_ROW_OF_PIECES_EXCLUDING_PAWN;
    }
    
    public Character findSymbolByColumnIndex(int columnIndex) {
        return symbols.get(columnIndex);
    }
}
