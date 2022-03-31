package chess.domain.board;

import chess.domain.Team;
import chess.domain.piece.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieceBuilder implements Builder {

    public Map<Position, Piece> build() {
        Map<Position, Piece> board = new HashMap<>();
        fillBlank(board);

        List<Piece> blackSpecials = initSpecialBuilder(Team.BLACK);
        List<Piece> whiteSpecials = initSpecialBuilder(Team.WHITE);
        for (int i = 0; i < 8; i++) {
            board.put(Position.of(8, i + 1), blackSpecials.get(i));
            board.put(Position.of(7, i + 1), new Pawn(Team.BLACK));

            board.put(Position.of(1, i + 1), whiteSpecials.get(i));
            board.put(Position.of(2, i + 1), new Pawn(Team.WHITE));
        }

        return board;
    }

    private void fillBlank(Map<Position, Piece> board) {
        for (Position position : Position.getPositions()) {
            board.put(position, new Blank());
        }
    }

    private List<Piece> initSpecialBuilder(Team team) {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(new Rook(team));
        pieces.add(new Knight(team));
        pieces.add(new Bishop(team));
        pieces.add(new Queen(team));
        pieces.add(new King(team));
        pieces.add(new Bishop(team));
        pieces.add(new Knight(team));
        pieces.add(new Rook(team));
        return pieces;
    }
}
