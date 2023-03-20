package chess.factory;

import chess.domain.board.Board;
import chess.domain.board.Col;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.pieces.Bishop;
import chess.domain.pieces.King;
import chess.domain.pieces.Knight;
import chess.domain.pieces.Name;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Empty;
import chess.domain.pieces.Queen;
import chess.domain.pieces.Rook;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardFactory {

    public static Board createBoard() {
        Map<Position, Piece> chessBoard = new HashMap<>();
        List<Position> positions = makePosition();
        makeChessBoard(chessBoard, positions);
        fillLowerOtherPositionPieces(chessBoard);
        fillUpperOtherPositionPieces(chessBoard);
        return new Board(chessBoard);
    }

    private static List<Position> makePosition() {
        List<Position> positions = Arrays.stream(Col.values())
                .flatMap(col -> Arrays.stream(Row.values())
                        .map(row -> Position.of(row, col)))
                .collect(Collectors.toList());
        return positions;
    }

    private static void makeChessBoard(final Map<Position, Piece> chessBoard, final List<Position> positions) {
        for (Position position : positions) {
            fillChessBoard(chessBoard, position);
        }
    }

    private static void fillChessBoard(final Map<Position, Piece> chessBoard, final Position position) {
        if (position.isPlacePositionAtFirst()) {
            chessBoard.put(position, new Empty());
        }
        if (position.isLowerPawnPositionAtFirst()) {
            chessBoard.put(position, new Pawn(new Name("p")));
        }
        if (position.isUpperPawnPositionAtFirst()) {
            chessBoard.put(position, new Pawn(new Name("P")));
        }
    }

    private static void fillUpperOtherPositionPieces(final Map<Position, Piece> chessBoard) {
        chessBoard.put(Position.of(Row.EIGHT, Col.A), new Rook(new Name("R")));
        chessBoard.put(Position.of(Row.EIGHT, Col.B), new Knight(new Name("N")));
        chessBoard.put(Position.of(Row.EIGHT, Col.C), new Bishop(new Name("B")));
        chessBoard.put(Position.of(Row.EIGHT, Col.D), new Queen(new Name("Q")));
        chessBoard.put(Position.of(Row.EIGHT, Col.E), new King(new Name("K")));
        chessBoard.put(Position.of(Row.EIGHT, Col.F), new Bishop(new Name("B")));
        chessBoard.put(Position.of(Row.EIGHT, Col.G), new Knight(new Name("N")));
        chessBoard.put(Position.of(Row.EIGHT, Col.H), new Rook(new Name("R")));
    }

    private static void fillLowerOtherPositionPieces(final Map<Position, Piece> chessBoard) {
        chessBoard.put(Position.of(Row.ONE, Col.A), new Rook(new Name("r")));
        chessBoard.put(Position.of(Row.ONE, Col.B), new Knight(new Name("n")));
        chessBoard.put(Position.of(Row.ONE, Col.C), new Bishop(new Name("b")));
        chessBoard.put(Position.of(Row.ONE, Col.D), new Queen(new Name("q")));
        chessBoard.put(Position.of(Row.ONE, Col.E), new King(new Name("k")));
        chessBoard.put(Position.of(Row.ONE, Col.F), new Bishop(new Name("b")));
        chessBoard.put(Position.of(Row.ONE, Col.G), new Knight(new Name("n")));
        chessBoard.put(Position.of(Row.ONE, Col.H), new Rook(new Name("r")));
    }


}
