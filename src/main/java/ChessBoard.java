import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessBoard {
    
    private final List<List<String>> chessBoard;
    
    public ChessBoard() {
        chessBoard = new ArrayList<>();
        setBlack();
        for (int i = 2; i < 6; i++) {
            setBlank(i);
        }
        setWhite();
    }
    
    // 체스 보드 초기화
    private void setBlack() {
        chessBoard.add(new ArrayList<>(Arrays.asList("R", "N", "B", "Q", "K", "B", "N", "R")));
        chessBoard.add(new ArrayList<>(Arrays.asList("P", "P", "P", "P", "P", "P", "P", "P")));
    }
    
    private void setBlank(int idx) {
        List<String> blankRow = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            blankRow.add(".");
        }
        chessBoard.add(blankRow);
    }
    
    private void setWhite() {
        chessBoard.add(new ArrayList<>(Arrays.asList("p", "p", "p", "p", "p", "p", "p", "p")));
        chessBoard.add(new ArrayList<>(Arrays.asList("r", "n", "b", "q", "k", "b", "n", "r")));
    }
    
    // 체스보드 출력
    public void printLine(List<String> line) {
        for (String element : line) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
    
    public void printBoard() {
        for (List<String> line : chessBoard) {
            printLine(line);
        }
    }
    
    // 체스말 위치 변환
    public List<Integer> change(String s) {
        List<Integer> location = new ArrayList<>();
        char col = s.charAt(0);
        if (col < 'a' || col > 'h') {
            throw new IllegalArgumentException("위치 입력 오류: 열은 'a'부터 'h' 사이여야 합니다.");
        }
        int colIdx = col - 'a'; // 'a'를 0으로 변환
        location.add(colIdx);
        
        char row = s.charAt(1);
        if (row < '1' || row > '8') {
            throw new IllegalArgumentException("위치 입력 오류: 행은 '1'부터 '8' 사이여야 합니다.");
        }
        int rowIdx = row - '1'; // '1'을 0으로 변환
        location.add(rowIdx);
        
        return location;
    }
    
    // 체스말 이동
    public void movePiece(String source, String target) {
        List<Integer> s = change(source);
        List<Integer> t = change(target);
        
        String piece = chessBoard.get(7 - s.get(1)).get(s.get(0));
        
        //에러처리
        if (piece.equals(".")) {
            System.out.println("에러: 선택한 위치에 체스말이 없습니다.");
            return;
        }
        
        chessBoard.get(7 - t.get(1)).set(t.get(0), piece);
        chessBoard.get(7 - s.get(1)).set(s.get(0), ".");
    }
    
    public void move(String command) {
    	String[] mst = command.split(" ");
    	if(!mst[0].equals("move")) {
           return;
        }
    	movePiece(mst[1], mst[2]);
        printBoard();
    }
    
    public int isWhite(String p) {
    	char c = p.charAt(0);
    	if(c >= 97) {
    		return 1;
    	}
    	if(c >= 65) {
    		return -1;
    	}
    	
    	return 0;
    }
    
    public double getWhitePiece(String p, List<String> white, int[] pawnCount, int col) {
    	 switch (p) {
         case "r":
        	 white.add(0, "9");
             return 9;
         case "b":
        	 white.add(1, "3");
             return 3;
         case "n":
        	 white.add(2, "2.5");
             return 2.5;
         case "q":
        	 white.add(3, "9");
             return 9;
         case "p":
             pawnCount[col]++;
             white.add(0, Integer.toString(pawnCount[col]));
             return 1;
         default:
             return 0;
    	 }
    	
    }
    
    public double getBlackPiece(String p, List<String> black, int[] pawnCount, int col) {
   	 switch (p) {
        case "R":
       	 black.add(0, "9");
            return 9;
        case "B":
       	 black.add(1, "3");
            return 3;
        case "N":
       	 black.add(2, "2.5");
            return 2.5;
        case "Q":
       	 black.add(3, "9");
            return 9;
        case "P":
            pawnCount[col]++;
            black.add(0, Integer.toString(pawnCount[col]));
            return 1;
        default:
            return 0;
   	 }
   	
   }
    
    public double changeWhite(String p, List<String> color, int[] pawnCount, int col) {
    	if(isWhite(p) == 1) {
    		return getWhitePiece(p, color,pawnCount, col);
    	}
    	if(isWhite(p) == -1) {
    		return 0;
    	}
    	return 0;
    }
    
    public double calculateRow(List<String> row, List<String> color, int[] pawnCount, int col) {
    	int rowScore = 0;
    	for(String piece : row) {
    		rowScore += changeWhite(piece, color,pawnCount, col);
    	}
    	
    	return rowScore;
    }
   
    
   public double calculateScore(ArrayList<String> color) {
	   int[] pawnCount = new int[8];
	   int col = 0;
	   double score = 0;
	   for (List<String> row : chessBoard) {
		   score+=calculateRow(row, color,pawnCount,col);
		   col++;
	   }
	   
	   return score;
   }
}

