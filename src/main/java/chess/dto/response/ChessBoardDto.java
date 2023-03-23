package chess.dto.response;

import java.util.List;

public class ChessBoardDto {

    private final List<String> chessBoard;

    private ChessBoardDto(List<String> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static ChessBoardDto of(List<String> chessBoard) {
        return new ChessBoardDto(chessBoard);
    }

    public List<String> getChessBoard() {
        return chessBoard;
    }
}
