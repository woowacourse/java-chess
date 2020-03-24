package domain;

import domain.chesspiece.*;
import domain.team.Team;

import java.util.ArrayList;
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

        chess.get(0).set(0, new Rook(Team.BLACK));
        chess.get(0).set(1, new Knight(Team.BLACK));
        chess.get(0).set(2, new Bishop(Team.BLACK));
        chess.get(0).set(3, new Queen(Team.BLACK));
        chess.get(0).set(4, new King(Team.BLACK));
        chess.get(0).set(5, new Bishop(Team.BLACK));
        chess.get(0).set(6, new Knight(Team.BLACK));
        chess.get(0).set(7, new Rook(Team.BLACK));

        chess.get(7).set(0, new Rook(Team.WHITE));
        chess.get(7).set(1, new Knight(Team.WHITE));
        chess.get(7).set(2, new Bishop(Team.WHITE));
        chess.get(7).set(3, new Queen(Team.WHITE));
        chess.get(7).set(4, new King(Team.WHITE));
        chess.get(7).set(5, new Bishop(Team.WHITE));
        chess.get(7).set(6, new Knight(Team.WHITE));
        chess.get(7).set(7, new Rook(Team.WHITE));

        for (int i = 0; i < 8; i++) {
            chess.get(1).set(i, new Pawn(Team.BLACK));
            chess.get(6).set(i, new Pawn(Team.WHITE));
        }

        return chess;
    }
}
