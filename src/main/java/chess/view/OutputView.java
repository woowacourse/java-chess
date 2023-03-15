package chess.view;

import chess.board.RowPieces;
import chess.piece.Piece;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
        throw new IllegalStateException("인스턴스를 생성할 수 없는 객체입니다.");
    }
    
    public static void noticeGameStart(){
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }
    
    
    public static void printChessBoard(List<RowPieces> chessBoard) {
        System.out.println(parseChessBoardToDisplay(chessBoard));
                
    }
    
    private static String parseChessBoardToDisplay(List<RowPieces> chessBoard) {
        return chessBoard.stream()
                .map(OutputView::parseRowPiecesToDisplay)
                .collect(Collectors.joining("\n", "", "\n"));
    }
    
    private static String parseRowPiecesToDisplay(RowPieces rowPieces) {
        return rowPieces.pieces().stream()
                .map(OutputView::parsePieceToDisplay)
                .collect(Collectors.joining());
    }
    
    private static String parsePieceToDisplay(Piece piece) {
        String symbol = String.valueOf(piece.symbol());
        if (piece.isWhiteTeam()) {
            return symbol.toUpperCase();
        }
        
        if (piece.isBlackTeam()) {
            return symbol;
        }
        
        return ".";
    }
}
