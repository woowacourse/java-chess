package chess.factory;

import static chess.util.PieceParser.parsePiece;

import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.pieces.Bishop;
import chess.domain.pieces.King;
import chess.domain.pieces.Knight;
import chess.domain.pieces.Name;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Place;
import chess.domain.pieces.Queen;
import chess.domain.pieces.Rook;
import chess.dto.BoardResultDto;
import chess.dto.PieceDto;
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

    public static Board createFromDto(final BoardResultDto boardResultDto) {
        Map<Position, Piece> chessBoard = new HashMap<>();
        List<PieceDto> pieces = boardResultDto.getPieces();

        pieces.stream()
                .forEach(piece -> chessBoard.put(Position.from(String.valueOf(piece.getRow()) + piece.getCol()),
                        parsePiece(piece.getPiece())));

        return new Board(chessBoard);
    }

    public static Board createFromUncompressedBoard(final Map<Position, Piece> unCompressedBoard) {
        return new Board(unCompressedBoard);
    }

    private static List<Position> makePosition() {
        List<Position> positions = Arrays.stream(Column.values())
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
            chessBoard.put(position, new Place());
        }
        if (position.isLowerPawnPositionAtFirst()) {
            chessBoard.put(position, new Pawn(new Name("p")));
        }
        if (position.isUpperPawnPositionAtFirst()) {
            chessBoard.put(position, new Pawn(new Name("P")));
        }
    }

    private static void fillUpperOtherPositionPieces(final Map<Position, Piece> chessBoard) {
        chessBoard.put(Position.of(Row.EIGHT, Column.A), new Rook(new Name("R")));
        chessBoard.put(Position.of(Row.EIGHT, Column.B), new Knight(new Name("N")));
        chessBoard.put(Position.of(Row.EIGHT, Column.C), new Bishop(new Name("B")));
        chessBoard.put(Position.of(Row.EIGHT, Column.D), new Queen(new Name("Q")));
        chessBoard.put(Position.of(Row.EIGHT, Column.E), new King(new Name("K")));
        chessBoard.put(Position.of(Row.EIGHT, Column.F), new Bishop(new Name("B")));
        chessBoard.put(Position.of(Row.EIGHT, Column.G), new Knight(new Name("N")));
        chessBoard.put(Position.of(Row.EIGHT, Column.H), new Rook(new Name("R")));
    }

    private static void fillLowerOtherPositionPieces(final Map<Position, Piece> chessBoard) {
        chessBoard.put(Position.of(Row.ONE, Column.A), new Rook(new Name("r")));
        chessBoard.put(Position.of(Row.ONE, Column.B), new Knight(new Name("n")));
        chessBoard.put(Position.of(Row.ONE, Column.C), new Bishop(new Name("b")));
        chessBoard.put(Position.of(Row.ONE, Column.D), new Queen(new Name("q")));
        chessBoard.put(Position.of(Row.ONE, Column.E), new King(new Name("k")));
        chessBoard.put(Position.of(Row.ONE, Column.F), new Bishop(new Name("b")));
        chessBoard.put(Position.of(Row.ONE, Column.G), new Knight(new Name("n")));
        chessBoard.put(Position.of(Row.ONE, Column.H), new Rook(new Name("r")));
    }
}
