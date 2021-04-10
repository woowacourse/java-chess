package chess.view.console;

import chess.controller.dto.ScoresDto;
import chess.domain.board.Board;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.piece.Symbol;

import java.util.List;

public class OutputView {
    private static final int SIZE_OF_ONLY_WINNER = 1;

    public static void printBoard(final Board board) {
        printPiece(board);
    }

    public static void printPiece(final Board board) {
        for (final Horizontal h : Horizontal.reversedValues()) {
            for (final Vertical v : Vertical.values()) {
                printSymbol(board.of(new Position(v, h)));
            }
            System.out.println();
        }
    }

    private static void printSymbol(final Piece piece) {
        System.out.print(decidePieceSymbol(piece));
    }

    private static String decidePieceSymbol(final Piece piece) {
        final Symbol symbol = piece.symbol();

        if (piece.isBlack()) {
            return symbol.asString().toUpperCase();
        }
        return symbol.asString().toLowerCase();
    }

    public static void printScore(final ScoresDto scoresDto) {
        System.out.println("black 의 점수 : " + scoresDto.getBlackScore());
        System.out.println("white 의 점수 : " + scoresDto.getWhiteScore());
    }

    public static void printWinner(final List<Owner> owner) {
        System.out.println("=== 게임 결과 ===");

        if (owner.size() == SIZE_OF_ONLY_WINNER) {
            System.out.println("승자 : " + decidePlayerSymbol(owner.get(0)));
            return;
        }

        System.out.println("무승부입니다.");
    }

    public static String decidePlayerSymbol(final Owner owner) {
        if (owner.isBlack()) {
            return "블랙";
        }
        return "화이트";
    }

    public static void printMenu() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작은 start, 종료는 end 명령을 입력하세요.");
        System.out.println("> 게임 이동 : move source target");
    }

    public static void printError(final String errorMessage) {
        System.out.println("ERROR :: " + errorMessage);
    }
}
