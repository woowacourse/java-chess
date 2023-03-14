package coordinate;

public class Column {
    private final char column;
    
    public Column(char column) {
        validateOutOfRange(column);
        this.column = column;
    }
    
    private void validateOutOfRange(char column) {
        if (column < 'a' || column > 'h') {
            throw new IllegalArgumentException("column는 a~h까지의 문자만 가능합니다.");
        }
    }
}
