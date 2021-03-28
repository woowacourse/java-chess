package chess.view;

import chess.controller.ScoreDto;
import chess.domain.board.Board;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.piece.Owner;
import chess.domain.piece.Piece;
import chess.domain.player.Player;

import java.util.Queue;

public class OutputView {
    private static final int NUMBER_OF_WINNER = 1;

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
        System.out.print(decideSymbol(piece));
    }

    private static String decideSymbol(final Piece piece) {
        final String symbol = piece.symbol();

        if (piece.isBlack()) {
            return symbol.toUpperCase();
        }
        return symbol.toLowerCase();
    }

    public static void printScore(final ScoreDto scoreDto) {
        final Owner owner = scoreDto.owner();

        if(owner.isBlack()){
            System.out.println("블랙의 점수 : "+ scoreDto.score());
        }

        if(owner.isWhite()){
            System.out.println("화이트의 점수 : "+ scoreDto.score());
        }
    }

    public static void printWinner(final Queue<Player> winner) {
        System.out.println("=== 게임 결과 ===");

        if(winner.size() == NUMBER_OF_WINNER){
            System.out.println("승자 : " + winner.peek());
            return;
        }

        System.out.println("무승부입니다.");
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
