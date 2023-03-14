package chess.domain;

import static chess.domain.Role.PAWN;
import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;
import static chess.domain.Role.BISHOP;
import static chess.domain.Role.KING;
import static chess.domain.Role.KNIGHT;
import static chess.domain.Role.QUEEN;
import static chess.domain.Role.ROOK;

import java.util.ArrayList;
import java.util.List;

public class BoardFactory {
    private static final Role[] CHESSMEN = {ROOK, KNIGHT, BISHOP, KING, QUEEN, BISHOP, KNIGHT, ROOK};

    private BoardFactory() {

    }

    public static List<Square> create() {
        List<Square> squares = new ArrayList<>();
        squares.addAll(generateChessmen(WHITE, 0));
        squares.addAll(generateSquare(PAWN, WHITE, 1));
        squares.addAll(generateSquare(Role.EMPTY, Team.EMPTY, 2));
        squares.addAll(generateSquare(Role.EMPTY, Team.EMPTY, 3));
        squares.addAll(generateSquare(Role.EMPTY, Team.EMPTY, 4));
        squares.addAll(generateSquare(Role.EMPTY, Team.EMPTY, 5));
        squares.addAll(generateSquare(PAWN, BLACK, 6));
        squares.addAll(generateChessmen(BLACK, 7));
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

    private static List<Square> generateSquare(Role role, Team team, int y) {
        List<Square> squares = new ArrayList<>();
        for (int x = 0; x < 8; x++) {
            Piece piece = new Piece(role, team);
            Position position = new Position(x, y);
            squares.add(new Square(piece, position));
        }
        return squares;
    }
}
