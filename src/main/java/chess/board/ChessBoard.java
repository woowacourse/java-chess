package chess.board;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChessBoard {
    
    private static final int MIN_ROW_NUMBER = 1;
    private static final int MAX_ROW_NUMBER = 8;
    
    private final List<RowPieces> chessBoard;
    
    public ChessBoard(List<RowPieces> chessBoard) {
        this.chessBoard = chessBoard;
    }
    
    public static ChessBoard create(){
        return new ChessBoard(initChessBoard());
    }
    
    private static List<RowPieces> initChessBoard() {
        return IntStream.rangeClosed(MIN_ROW_NUMBER, MAX_ROW_NUMBER)
                .mapToObj(RowPieces::new)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
    
    public List<RowPieces> chessBoard() {
        return chessBoard;
    }
}
