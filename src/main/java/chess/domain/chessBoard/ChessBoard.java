package chess.domain.chessBoard;

import chess.domain.position.Direction;
import chess.domain.chesspiece.*;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.*;

import static chess.domain.chesspiece.Team.BLACK;
import static chess.domain.chesspiece.Team.WHITE;
import static chess.domain.chesspiece.Role.*;

public class ChessBoard {
    private final Map<Column, Line> chessBoard;

    private ChessBoard(Map<Column, Line> chessBoard) {
        this.chessBoard = chessBoard;
    }
    //TODO: 한칸 생각해보기
    public static ChessBoard initializeChessBoard() {
        Map<Column, Line> board = new LinkedHashMap<>();
        board.put(Column.valueOf("8"), new Line(List.of(new Rook(BLACK), new Knight(BLACK),
                new Bishop(BLACK), new Queen(BLACK), new King(BLACK),
                new Bishop(BLACK), new Knight(BLACK), new Rook(BLACK))));
        board.put(Column.valueOf("7"), new Line(List.of(new Pawn(BLACK), new Pawn(BLACK),
                new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK),
                new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK))));
        board.put(Column.valueOf("6"), new Line(List.of(new Empty(), new Empty(),
                new Empty(), new Empty(), new Empty(),
                new Empty(), new Empty(), new Empty())));
        board.put(Column.valueOf("5"), new Line(List.of(new Empty(), new Empty(),
                new Empty(), new Empty(), new Empty(),
                new Empty(), new Empty(), new Empty())));
        board.put(Column.valueOf("4"), new Line(List.of(new Empty(), new Empty(),
                new Empty(), new Empty(), new Empty(),
                new Empty(), new Empty(), new Empty())));
        board.put(Column.valueOf("3"), new Line(List.of(new Empty(), new Empty(),
                new Empty(), new Empty(), new Empty(),
                new Empty(), new Empty(), new Empty())));
        board.put(Column.valueOf("2"), new Line(List.of(new Pawn(WHITE), new Pawn(WHITE),
                new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE),
                new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE))));
        board.put(Column.valueOf("1"), new Line(List.of(new Rook(WHITE), new Knight(WHITE),
                new Bishop(WHITE), new Queen(WHITE), new King(WHITE),
                new Bishop(WHITE), new Knight(WHITE), new Rook(WHITE))));

        return new ChessBoard(board);
    }

    public Map<Column, Line> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    public void move(Position source, Position target) {
        Piece piece = findChessPiece(source);
        piece.getRoute(source, target)
                .forEach(this::checkObstacle);
        if (piece.isPawn()) {
            checkPawnStrategy(source, target);
        }
        checkTeam(target, piece);
        chessBoard.put(source.getColumn(), getUpdate(source, new Empty()));
        chessBoard.put(target.getColumn(), getUpdate(target, piece));
    }

    private void checkObstacle(Position position) {
        Piece obstacle = findChessPiece(position);
        if (obstacle.getRole() != EMPTY) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    private void checkPawnStrategy(Position source, Position target) {
        if (Direction.isUpDown(source, target)) {
            checkObstacle(target);
        }
        if (Direction.isDiagonal(source, target) && findChessPiece(target).isEmpty()) {
            throw new IllegalArgumentException("공격 대상이 없습니다.");
        }
    }

    private void checkTeam(Position target, Piece piece) {
        Piece targetPiece = findChessPiece(target);
        if (piece.isTeam(targetPiece)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    private Line getUpdate(Position source, Piece piece) {
        return chessBoard.get(source.getColumn()).update(source.getRow(), piece);
    }

    private Piece findChessPiece(Position source) {
        Column column = source.getColumn();
        Row row = source.getRow();
        Line chessPieces = chessBoard.get(column);
        return chessPieces.getChessPiece(row);
    }
}
