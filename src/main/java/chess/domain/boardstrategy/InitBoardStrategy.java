package chess.domain.boardstrategy;

import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.attribute.Team;
import java.util.HashMap;
import java.util.Map;

public class InitBoardStrategy implements BoardStrategy {
    @Override
    public Map<Position, Piece> create() {
        Map<Position, Piece> squares = new HashMap<>();
        initEmptyPieces(squares);
        initNotPawnSquares(squares, Rank.ONE, Team.WHITE);
        initPawnPieces(squares, Rank.TWO, Team.WHITE);
        initPawnPieces(squares, Rank.SEVEN, Team.BLACK);
        initNotPawnSquares(squares, Rank.EIGHT, Team.BLACK);
        return squares;
    }

    private void initNotPawnSquares(Map<Position, Piece> squares, Rank rank, Team team) {
        squares.replace(new Position(Column.A, rank), new Rook(team));
        squares.replace(new Position(Column.B, rank), new Knight(team));
        squares.replace(new Position(Column.C, rank), new Bishop(team));
        squares.replace(new Position(Column.D, rank), new Queen(team));
        squares.replace(new Position(Column.E, rank), new King(team));
        squares.replace(new Position(Column.F, rank), new Bishop(team));
        squares.replace(new Position(Column.G, rank), new Knight(team));
        squares.replace(new Position(Column.H, rank), new Rook(team));
    }

    private void initEmptyPieces(Map<Position, Piece> squares) {
        for (Column column : Column.values()) {
            initRankEmpty(squares, column);
        }
    }

    private void initRankEmpty(Map<Position, Piece> squares, Column column) {
        for (Rank rank : Rank.values()) {
            squares.put(new Position(column, rank), new EmptyPiece());
        }
    }

    private void initPawnPieces(Map<Position, Piece> squares, Rank rank, Team team) {
        for (Column column : Column.values()) {
            squares.replace(new Position(column, rank), new Pawn(team));
        }
    }
}
