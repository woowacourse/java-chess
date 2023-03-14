package chess.domain;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;
import static chess.domain.Type.BISHOP;
import static chess.domain.Type.KING;
import static chess.domain.Type.KNIGHT;
import static chess.domain.Type.PAWN;
import static chess.domain.Type.QUEEN;
import static chess.domain.Type.ROOK;

import java.util.ArrayList;
import java.util.List;

public class BoardFactory {
    private static final Type[] CHESSMEN = {ROOK, KNIGHT, BISHOP, KING, QUEEN, BISHOP, KNIGHT, ROOK};

    private BoardFactory() {

    }

    public static List<Square> create() {
        List<Square> squares = new ArrayList<>();
        squares.addAll(generateChessmen(WHITE, 0));
        squares.addAll(generatePawns(WHITE, 1));
        squares.addAll(generateChessmen(BLACK, 7));
        squares.addAll(generatePawns(BLACK, 6));
        return squares;
    }

    private static List<Square> generateChessmen(Team team, int y) {
        List<Square> squares = new ArrayList<>();
        for (int index = 0; index < CHESSMEN.length; index++) {
            Piece piece = new Piece(CHESSMEN[index], team);
            Position position = new Position(index, y);
            squares.add(new Square(piece, position));
        }
        return squares;
    }

    private static List<Square> generatePawns(Team team, int y) {
        List<Square> squares = new ArrayList<>();
        for (int x = 0; x < 8; x++) {
            Piece piece = new Piece(PAWN, team);
            Position position = new Position(x, y);
            squares.add(new Square(piece, position));
        }
        return squares;
    }
}
