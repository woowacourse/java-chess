package view;

import static domain.position.File.END_LETTER;
import static domain.position.File.START_LETTER;
import static domain.position.Rank.END_NUMBER;
import static domain.position.Rank.START_NUMBER;

import domain.game.ChessBoard;
import domain.piece.Piece;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

public class OutputView {
    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.%n"
            + "> 게임 시작 : start%n"
            + "> 게임 종료 : end%n"
            + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3%n";

    public void printStartMessage() {
        System.out.printf(GAME_START_MESSAGE);
    }

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }

    public void printChessBoard(final ChessBoard mover) {
        for (int rank = END_NUMBER; rank >= START_NUMBER; rank--) {
            printPieceSymbol(mover, rank);
            System.out.println();
        }
        System.out.println();
    }

    private void printPieceSymbol(final ChessBoard mover, final int rank) {
        for (char file = START_LETTER; file <= END_LETTER; file++) {
            Position position = new Position(
                    new Position(new File(file), new Rank(rank)));
            System.out.print(generateSymbol(mover, position));
        }
    }

    public String generateSymbol(final ChessBoard mover, final Position position) {
        if (mover.hasPiece(position)) {
            Piece piece = mover.findPieceByPosition(position);
            return PieceMapper.symbol(piece);
        }
        return PieceMapper.emptySymbol();
    }
}
