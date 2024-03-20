package chess.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static chess.domain.ChessPiece.*;
import static chess.domain.ChessPiece.BLACK_KNIGHT;

public class ChessBoard {
    private final Map<Column, List<ChessPiece>> chessBoard;

    private ChessBoard(Map<Column, List<ChessPiece>> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static ChessBoard initializeChessBoard() {
        Map<Column, List<ChessPiece>> board = new LinkedHashMap<>();
        board.put(Column.valueOf("8"), List.of(BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_QUEEN, BLACK_KING, BLACK_BISHOP, BLACK_KNIGHT, BLACK_ROOK));
        board.put(Column.valueOf("7"), List.of(BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN, BLACK_PAWN));
        board.put(Column.valueOf("6"), List.of(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE));
        board.put(Column.valueOf("5"), List.of(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE));
        board.put(Column.valueOf("4"), List.of(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE));
        board.put(Column.valueOf("3"), List.of(NONE, NONE, NONE, NONE, NONE, NONE, NONE, NONE));
        board.put(Column.valueOf("2"), List.of(WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN, WHITE_PAWN));
        board.put(Column.valueOf("1"), List.of(WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_QUEEN, WHITE_KING, WHITE_BISHOP, WHITE_KNIGHT, WHITE_ROOK));

        return new ChessBoard(board);
    }

    public Map<Column, List<ChessPiece>> getChessBoard(){
        return Collections.unmodifiableMap(chessBoard);
    }

    public void move(Position source, Position target) {
//        Column column = source.getColumn();
//        Row row = source.getRow();

        ChessPiece chessPiece = findChessPiece(source);

        if(!chessPiece.isValidMovingRule(source, target)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }

        Direction direction = Direction.findDirection(source, target);
        //임의의 값을 만들고
        //direction으로 이동을 시키면서 그 칸을 확인 해야되잖아
        Position movingPosition = direction.move(source);
        while(!movingPosition.equals(target)) {
            ChessPiece obstacle = findChessPiece(movingPosition);
            if(!obstacle.isNone()) {
                throw new IllegalArgumentException("이동할 수 없습니다.");
            }
            movingPosition = direction.move(source);
        }

        //목적지에 방해물 있는지
            //나이트,폰
        ChessPiece targetPiece = findChessPiece(movingPosition);
        if(targetPiece.isTeam(chessPiece)){
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }

        chessBoard.get(source.getColumn()).set(source.getRow().getValue(), NONE);
        chessBoard.get(target.getColumn()).set(target.getRow().getValue(), chessPiece);
    }

    private ChessPiece findChessPiece(Position source) {
        Column column = source.getColumn();
        Row row = source.getRow();
        List<ChessPiece> chessPieces = chessBoard.get(column);
        return chessPieces.get(row.getValue());
    }
}
