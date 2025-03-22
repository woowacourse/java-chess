package chess;

import chess.piece.King;
import chess.piece.Piece;
import chess.piece.쭈;
import chess.piece.퀸비숍룩;
import chess.position.Column;
import chess.position.Offset;
import chess.position.Position;
import chess.position.Row;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static Piece[][] pieceBoard = Initializer.initializePieceBoard();

    public static Color turn = Color.WHITE;

    public static void main(String[] args) {

        while (true) {
            try {
                System.out.println("----------------------------");
                Output.printBoard();
                process();
            } catch (Exception e) {
                System.out.println("##########################");
                System.out.println(e.getMessage());
                System.out.println("재입력 ㄱㄱ");
                continue;
            }
            turn = turn.opposite();
        }
    }

    private static void process() {
        Position before = inputPosition();
        Position after = inputPosition();
        Piece selectPiece = validateMove(after, before);
        pieceBoard[before.getI()][before.getJ()] = null;
        pieceBoard[after.getI()][after.getJ()] = selectPiece;

        if (selectPiece.getClass() == 쭈.class) {
            ((쭈) selectPiece).moveCount++;
        }

        if (isCheck(findAnotherTeamKingPosition())) {
            System.out.println("체크입니다 !!!!!!!!!!!!!!!!!!!!!!!");
        }
    }

    private static boolean isCheck(final Position kingPosition) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Position startPosition = Position.of(i, j);
                try {
                    Piece piece = validateMove(startPosition, kingPosition);
                    System.out.print("piece로 인해 ");
                    return true;
                } catch (Exception ignored) {
                }
            }
        }
        return false;
    }

    private static Position findAnotherTeamKingPosition() {
        final Piece targetKing = new King(turn.opposite());
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final Piece target = pieceBoard[i][j];
                if (target != null && target.equals(targetKing)) {
                    return Position.of(i, j);
                }
            }
        }
        throw new IllegalStateException("왕이 없을 수가 없어..");
    }

    private static Piece validateMove(final Position after, final Position before) {
        Offset offset = Offset.calculate(after, before);
        if (offset.is00()) {
            throw new IllegalArgumentException("안움직였어");
        }
        Piece selectPiece = pieceBoard[before.getI()][before.getJ()];
        if (selectPiece == null) {
            throw new IllegalArgumentException("아니 없는걸 선택하면 어떡행..");
        }
        if (selectPiece.getColor() != turn) {
            throw new IllegalArgumentException("니 차례가 아니잖아 !!!");
        }

        boolean killFlag = false;
        Piece targetPiece = pieceBoard[after.getI()][after.getJ()];
        if (targetPiece != null) {
            if (targetPiece.getColor() == selectPiece.getColor()) {
                throw new IllegalArgumentException("같은 팀이 있는 곳으로 이동할 수 없어. 팀킬이라도 하게?");
            }
            killFlag = true;
        }

        if (!selectPiece.canMove(offset, killFlag)) {
            throw new IllegalArgumentException("거기로 못가는 기물임");
        }

        if (selectPiece instanceof 퀸비숍룩) {
            퀸비숍룩 piece = (퀸비숍룩) selectPiece;
            if (piece.경로상_장애물_확인(before, after)) {
                throw new IllegalArgumentException("경로상 장애물 발견 !!");
            }
        }
        return selectPiece;
    }

    public static Position inputPosition() {
        String[] inputs = scanner.nextLine().split("");
        return new Position(
                Column.from(inputs[0]), Row.from(inputs[1])
        );
    }
}
