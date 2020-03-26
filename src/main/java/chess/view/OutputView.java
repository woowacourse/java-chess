package chess.view;

import chess.domain.Position;
import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.TeamStrategy;
import chess.domain.chessPiece.team.WhiteTeam;
import chess.domain.chessboard.ChessBoard;

public class OutputView {
    private static final String EMPTY_MARK = ".";
    private static final String STRING_FORMAT_PRINT_SCORE = "블랙팀 점수 : %.1f, 화이트 팀 점수 : %.1f";
    private static final String MESSAGE_END_GAME = "게임을 종료 합니다";

    public static void printChessBoard(ChessBoard chessBoard) {
        for (Position position : chessBoard.getChessBoard()) {
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
        TeamStrategy blackMark = new BlackTeam();
        TeamStrategy whiteMark = new WhiteTeam();
        double blackTeamScore = chessBoard.calculateTeamScore(blackMark);
        double whiteTeamScore = chessBoard.calculateTeamScore(whiteMark);

        System.out.println(String.format(STRING_FORMAT_PRINT_SCORE, blackTeamScore, whiteTeamScore));
    }

    public static void printGameEndMessage() {
        System.out.println(MESSAGE_END_GAME);
    }
}
