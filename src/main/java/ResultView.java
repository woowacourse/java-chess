import java.util.ArrayList;
import java.util.List;

public class ResultView {

	//체스보드 출력
	public void printChessBoard(ChessBoard cb) {
		cb.printBoard();
		System.out.println();
	}
	
	public void printStatus(String start) {
		if(!start.equals("status")) {
			return;
		}
		ArrayList<String> white = new ArrayList<>();;
		ArrayList<String> black = new ArrayList<>();;
		
		ChessBoard cb = new ChessBoard();
        double blackScore = cb.calculateScore(black);
        double whiteScore = cb.calculateScore(white);
        
        System.out.printf("검은색 점수: %.1f\n", blackScore);
        System.out.printf("흰색 점수: %.1f\n", whiteScore);
       
        if (blackScore > whiteScore) {
            System.out.println("검은색이 이겼습니다!");
        } else if (whiteScore > blackScore) {
            System.out.println("흰색이 이겼습니다!");
        } else {
            System.out.println("무승부입니다!");
        }
    }
}
