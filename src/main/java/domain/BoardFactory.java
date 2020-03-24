package domain;

import domain.chesspiece.Bishop;
import domain.chesspiece.Chesspiece;
import domain.chesspiece.Empty;
import domain.chesspiece.King;
import domain.chesspiece.Knight;
import domain.chesspiece.Pawn;
import domain.chesspiece.Queen;
import domain.chesspiece.Rook;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardFactory {
    public static List<List<Chesspiece>> create() {
        List<List<Chesspiece>> chess = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            List<Chesspiece> line = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                line.add(new Empty());
            }
            chess.add(line);
        }

        List<Integer> kingLine = Arrays.asList(0, 7);
        for (int i : kingLine) {
            chess.get(i).set(0, new Rook());
            chess.get(i).set(1, new Knight());
            chess.get(i).set(2, new Bishop());
            chess.get(i).set(3, new Queen());
            chess.get(i).set(4, new King());
            chess.get(i).set(5, new Bishop());
            chess.get(i).set(6, new Knight());
            chess.get(i).set(7, new Rook());
        }

        for (int i = 0; i < 8; i++) {
            chess.get(1).set(i, new Pawn());
            chess.get(6).set(i, new Pawn());
        }

        return chess;
    }
}
