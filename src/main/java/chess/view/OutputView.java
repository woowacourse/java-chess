package chess.view;

import chess.domain.chessboard.Line;
import chess.domain.position.Column;
import chess.domain.chesspiece.Role;
import chess.domain.chesspiece.Piece;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final EnumMap<Role, String> pieceBoard = initializePiece();

    public static void printChessBoard(Map<Column, Line> chessBoard) {
        chessBoard.values()
                .forEach(line -> printOneLine(line.getLine()));
    }

    private static void printOneLine(List<Piece> line) {
        line.forEach(chessPiece -> System.out.print(pieceBoard.get(chessPiece.getRole())));
        System.out.println();
    }

    public static void printStartMessage() {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    private static EnumMap<Role, String> initializePiece() {
        EnumMap<Role, String> pieceBoard = new EnumMap<>(Role.class);
        pieceBoard.put(Role.BLACK_KING, "K");
        pieceBoard.put(Role.WHITE_KING, "k");
        pieceBoard.put(Role.BLACK_QUEEN, "Q");
        pieceBoard.put(Role.WHITE_QUEEN, "q");
        pieceBoard.put(Role.BLACK_ROOK, "R");
        pieceBoard.put(Role.WHITE_ROOK, "r");
        pieceBoard.put(Role.BLACK_BISHOP, "B");
        pieceBoard.put(Role.WHITE_BISHOP, "b");
        pieceBoard.put(Role.BLACK_KNIGHT, "N");
        pieceBoard.put(Role.WHITE_KNIGHT, "n");
        pieceBoard.put(Role.BLACK_PAWN, "P");
        pieceBoard.put(Role.WHITE_PAWN, "p");
        pieceBoard.put(Role.EMPTY, ".");
        return pieceBoard;
    }
}
