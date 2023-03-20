package chess.view;

import chess.domain.board.RowPieces;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
        throw new IllegalStateException("인스턴스를 생성할 수 없는 객체입니다.");
    }
    
    public static void noticeGameStart(){
        println("> 체스 게임을 시작합니다.");
        println("> 게임 시작 : start");
        println("> 게임 종료 : end");
        println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }
    
    
    public static void printChessBoard(List<RowPieces> chessBoard) {
        println(parseChessBoardToDisplay(chessBoard));
        println("abcdefgh\n");
                
    }
    
    private static String parseChessBoardToDisplay(List<RowPieces> chessBoard) {
        return chessBoard.stream()
                .map(rowPieces -> parseRowPiecesToDisplay(rowPieces) + rowPieces.row())
                .collect(Collectors.joining("\n", "", "\n"));
    }
    
    private static String parseRowPiecesToDisplay(RowPieces rowPieces) {
        return rowPieces.pieces().stream()
                .map(OutputView::parsePieceToDisplay)
                .collect(Collectors.joining("", "", " "));
    }
    
    private static String parsePieceToDisplay(Piece piece) {
        String symbol = String.valueOf(piece.symbol());
        if (piece.isSameTeam(Team.WHITE)) {
            return symbol.toUpperCase();
        }
        
        if (piece.isSameTeam(Team.BLACK)) {
            return symbol;
        }
        
        return ".";
    }
    
    public static void printErrorMessage(String message) {
        println("[ERROR]" + message + "\n");
    }
    
    public static void println(String message) {
        System.out.println(message);
    }
}
