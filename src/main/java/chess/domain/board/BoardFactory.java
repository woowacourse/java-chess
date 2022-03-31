package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Blank;
import chess.domain.piece.Team;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BoardFactory {

    public static Board createChessBoard() {
        Map<Position, Piece> emptyBoard = initEmptyBoard();
        initFirstLine(Team.BLACK, Row.EIGHT, emptyBoard);
        initPawn(Team.BLACK, Row.SEVEN, emptyBoard);
        initFirstLine(Team.WHITE, Row.ONE, emptyBoard);
        initPawn(Team.WHITE, Row.TWO, emptyBoard);
        return new Board(emptyBoard);
    }

    private static Map<Position, Piece> initEmptyBoard() {
        Map<Position, Piece> emptyBoard = new TreeMap<>();
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                emptyBoard.put(Position.valueOf(column.getName() + row.getValue()), new Blank());
            }
        }
        return emptyBoard;
    }

    private static void initFirstLine(final Team team, Row row, final Map<Position, Piece> board) {
        List<Piece> pieces = List.of(
                new Rook(team),
                new Knight(team),
                new Bishop(team),
                new Queen(team),
                new King(team),
                new Bishop(team),
                new Knight(team),
                new Rook(team)
        );
        for (Column column : Column.values()) {
            board.replace(Position.valueOf(column.getName() + row.getValue()), pieces.listIterator().next());
        }
    }

    private static void initPawn(final Team team, Row row, final Map<Position, Piece> board) {
        for (Column column : Column.values()) {
            board.replace(Position.valueOf(column.getName() + row.getValue()), new Pawn(team));
        }
    }
}
