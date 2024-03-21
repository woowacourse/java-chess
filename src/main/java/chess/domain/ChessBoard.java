package chess.domain;

import chess.domain.chessPiece.*;

import java.util.*;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;
import static chess.domain.chessPiece.Role.*;

public class ChessBoard {
    private final Map<Column, Line> chessBoard;

    private ChessBoard(Map<Column, Line> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static ChessBoard initializeChessBoard() {
        Map<Column, Line> board = new LinkedHashMap<>();
        board.put(Column.valueOf("8"), new Line(List.of(new Rook(BLACK), new Knight(BLACK), new Bishop(BLACK), new Queen(BLACK), new King(BLACK), new Bishop(BLACK), new Knight(BLACK), new Rook(BLACK))));
        board.put(Column.valueOf("7"), new Line(List.of(new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK), new Pawn(BLACK))));
        board.put(Column.valueOf("6"), new Line(List.of(new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty())));
        board.put(Column.valueOf("5"), new Line(List.of(new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty())));
        board.put(Column.valueOf("4"), new Line(List.of(new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty())));
        board.put(Column.valueOf("3"), new Line(List.of(new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty(), new Empty())));
        board.put(Column.valueOf("2"), new Line(List.of(new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE), new Pawn(WHITE))));
        board.put(Column.valueOf("1"), new Line(List.of(new Rook(WHITE), new Knight(WHITE), new Bishop(WHITE), new Queen(WHITE), new King(WHITE), new Bishop(WHITE), new Knight(WHITE), new Rook(WHITE))));

        return new ChessBoard(board);
    }

    public Map<Column, Line> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    public void move(Position source, Position target) {
        Piece piece = findChessPiece(source);
        List<Position> route = piece.getRoute(source, target);

        for (Position position : route) {
            checkObstacle(position);
        }

        if(piece.isPawn() && Direction.findUpDown(source, target)) {
            checkObstacle(target);
        }
        //폰이면서, 앞으로 이동하는 경우라면
        //그냥 장애물이 뭐라도 있으면 못가

        Piece tar = findChessPiece(target);
        if(piece.isTeam(tar)){
            throw new IllegalArgumentException("x");
        }

        chessBoard.put(source.getColumn(), getUpdate(source, new Empty()));
        chessBoard.put(target.getColumn(), getUpdate(target, piece));
    }

    private void checkObstacle(Position position) {
        Piece obstacle = findChessPiece(position);
        if (obstacle.getRole() !=EMPTY){
            throw new IllegalArgumentException("이동 불가");
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
