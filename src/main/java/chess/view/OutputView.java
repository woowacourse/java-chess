package chess.view;

import chess.domain.Position;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;

public class OutputView {
    private static final String EMPTY_MARK = ".";
    private static final String STRING_FORMAT_PRINT_SCORE = "블랙팀 점수 : %.1f, 화이트 팀 점수 : %.1f";
    private static final String MESSAGE_END_GAME = "게임을 종료 합니다";

    public static void printChessBoard(ChessBoard chessBoard) {
        for (Position position : chessBoard.getPositions()) {
            seperateLine(position);
            Piece piece = chessBoard.findPieceByPosition(position);
            if (piece == null) {
                System.out.print(EMPTY_MARK);
                continue;
            }
            System.out.print(piece.pieceName());
        }
    }

    private static void seperateLine(Position position) {
        if (position.isNewLine()) {
            System.out.println();
        }
    }

    public static void calculateScore(ChessBoard chessBoard) {
        double blackTeamScore = chessBoard.calculateBlackTeamScore();
        double whiteTeamScore = chessBoard.calculateWhiteTeamScore();

        System.out.println(String.format(STRING_FORMAT_PRINT_SCORE, blackTeamScore, whiteTeamScore));
    }

    public static void printGameEndMessage() {
        System.out.println(MESSAGE_END_GAME);
    }
}
