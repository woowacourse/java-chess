package chess.view;

import chess.domain.chesspiece.Piece;
import chess.domain.chesspiece.Role;
import chess.domain.position.Position;
import java.util.EnumMap;
import java.util.Map;

public class OutputView {
    private static final EnumMap<Role, String> pieceBoard = initializePiece();

    public static void printChessBoard(Map<Position, Piece> chessBoard) {
        int count = 1;
        for (Piece piece : chessBoard.values()) {
            printPiece(piece);
            if (count % 8 == 0) {
                System.out.println();
            }
            count++;
        }
    }

    private static void printPiece(Piece piece) {
        System.out.print(pieceBoard.get(piece.getRole()));
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
