package chess.view.dto;

public class ChessGameStatusDto {

    private final boolean exist;

    private ChessGameStatusDto(boolean exist) {
        this.exist = exist;
    }

    public static ChessGameStatusDto exist() {
        return new ChessGameStatusDto(true);
    }

    public static ChessGameStatusDto isNotExist() {
        return new ChessGameStatusDto(false);
    }

    public boolean isExist() {
        return exist;
    }
    
}
