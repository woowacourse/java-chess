package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board() {
        this.board = new HashMap<>();
        initialize();
    }

    private void initialize() {
        initializeBlackTeam();
        initializeWhiteTeam();
    }

    private void initializeBlackTeam() {
        initializePawn(Column.RANK7, Team.BLACK);
        initializeHighValuePiece(Column.RANK8, Team.BLACK);
    }

    private void initializeWhiteTeam() {
        initializePawn(Column.RANK2, Team.WHITE);
        initializeHighValuePiece(Column.RANK1, Team.WHITE);
    }

    private void initializePawn(Column column, Team team) {
        for (Row row : Row.values()) {
            Position position = new Position(row, column);
            board.put(position, new Pawn(position, team));
        }
    }

    private void initializeHighValuePiece(Column column, Team team) {
        board.put(new Position(Row.a, column), new Rook(new Position(Row.a, column), team));
        board.put(new Position(Row.h, column), new Rook(new Position(Row.h, column), team));

        board.put(new Position(Row.b, column), new Knight(new Position(Row.b, column), team));
        board.put(new Position(Row.g, column), new Knight(new Position(Row.g, column), team));

        board.put(new Position(Row.c, column), new Bishop(new Position(Row.c, column), team));
        board.put(new Position(Row.f, column), new Bishop(new Position(Row.f, column), team));

        board.put(new Position(Row.d, column), new Queen(new Position(Row.d, column), team));
        board.put(new Position(Row.e, column), new King(new Position(Row.e, column), team));
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
