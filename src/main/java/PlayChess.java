
public class PlayChess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		InputView Input = new InputView();
		
		Input.startMessage();
		String start = Input.start();
		
		ChessBoard cb = new ChessBoard();
		ResultView Result = new ResultView();
		
		
		
		if(start.equals("start")) {
			Result.printChessBoard(cb);
		}
		
		while(!start.equals("end")) {
			cb.move(start);
			Result.printStatus(start);
			start = Input.start();
		}
		
	}

}
