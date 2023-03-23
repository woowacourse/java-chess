package chess.factory;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.pieces.Bishop;
import chess.domain.pieces.King;
import chess.domain.pieces.Knight;
import chess.domain.pieces.Name;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Place;
import chess.domain.pieces.Queen;
import chess.domain.pieces.Rook;
import java.util.HashMap;
import java.util.Map;

public class BoardFactoryForTest {

    // 3단계 예시에 나온 맵
    public static Board createBoard() {
        Map<Position, Piece> board = new HashMap<>();

        board.put(Position.from("a8"), new Place());
        board.put(Position.from("a7"), new Pawn(new Name("P")));
        board.put(Position.from("a6"), new Place());
        board.put(Position.from("a5"), new Place());
        board.put(Position.from("a4"), new Place());
        board.put(Position.from("a3"), new Place());
        board.put(Position.from("a2"), new Place());
        board.put(Position.from("a1"), new Place());

        board.put(Position.from("b8"), new King(new Name("K")));
        board.put(Position.from("b7"), new Place());
        board.put(Position.from("b6"), new Pawn(new Name("P")));
        board.put(Position.from("b5"), new Place());
        board.put(Position.from("b4"), new Place());
        board.put(Position.from("b3"), new Place());
        board.put(Position.from("b2"), new Place());
        board.put(Position.from("b1"), new Place());

        board.put(Position.from("c8"), new Rook(new Name("R")));
        board.put(Position.from("c7"), new Pawn(new Name("P")));
        board.put(Position.from("c6"), new Place());
        board.put(Position.from("c5"), new Place());
        board.put(Position.from("c4"), new Place());
        board.put(Position.from("c3"), new Place());
        board.put(Position.from("c2"), new Place());
        board.put(Position.from("c1"), new Place());

        board.put(Position.from("d8"), new Place());
        board.put(Position.from("d7"), new Bishop(new Name("B")));
        board.put(Position.from("d6"), new Place());
        board.put(Position.from("d5"), new Place());
        board.put(Position.from("d4"), new Place());
        board.put(Position.from("d3"), new Place());
        board.put(Position.from("d2"), new Place());
        board.put(Position.from("d1"), new Place());

        board.put(Position.from("e8"), new Place());
        board.put(Position.from("e7"), new Place());
        board.put(Position.from("e6"), new Queen(new Name("Q")));
        board.put(Position.from("e5"), new Place());
        board.put(Position.from("e4"), new Place());
        board.put(Position.from("e3"), new Place());
        board.put(Position.from("e2"), new Place());
        board.put(Position.from("e1"), new Rook(new Name("r")));

        board.put(Position.from("f8"), new Place());
        board.put(Position.from("f7"), new Place());
        board.put(Position.from("f6"), new Place());
        board.put(Position.from("f5"), new Place());
        board.put(Position.from("f4"), new Knight(new Name("n")));
        board.put(Position.from("f3"), new Pawn(new Name("p")));
        board.put(Position.from("f2"), new Pawn(new Name("p")));
        board.put(Position.from("f1"), new King(new Name("k")));

        board.put(Position.from("g8"), new Place());
        board.put(Position.from("g7"), new Place());
        board.put(Position.from("g6"), new Place());
        board.put(Position.from("g5"), new Place());
        board.put(Position.from("g4"), new Queen(new Name("q")));
        board.put(Position.from("g3"), new Place());
        board.put(Position.from("g2"), new Pawn(new Name("p")));
        board.put(Position.from("g1"), new Place());

        board.put(Position.from("h8"), new Place());
        board.put(Position.from("h7"), new Place());
        board.put(Position.from("h6"), new Place());
        board.put(Position.from("h5"), new Place());
        board.put(Position.from("h4"), new Place());
        board.put(Position.from("h3"), new Pawn(new Name("p")));
        board.put(Position.from("h2"), new Place());
        board.put(Position.from("h1"), new Place());

        return new Board(board);
    }
}
