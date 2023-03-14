package chess;

import java.util.HashMap;
import java.util.Map;

public class Chessboard {
    private final Map<Square,Piece> board;

    public Chessboard(){
        this.board = new HashMap<>();
        for (File file : File.values()) {
            putFile(file);
        }
    }

    private void putFile(File file) {
        for(Rank rank:Rank.values()){
            board.put(new Square(file,rank),new Piece());
        }
    }

}
