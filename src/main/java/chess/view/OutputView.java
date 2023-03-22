package chess.view;

import chess.domain.Position;
import chess.domain.game.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.score.ScoreCalculator;
import chess.dto.BoardDto;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String CHESS_GAME_START_GUIDE_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String GAME_START_GUIDE_MESSAGE = "> 게임 시작 : start";
    private static final String GAME_END_GUIDE_MESSAGE = "> 게임 종료 : end";
    private static final String MOVE_COMMAND_GUIDE_MESSAGE = "게임 이동 : move source위치 target 위치 - 예. move b2 b3";
    private static final String CHECKMATE_GUIDE_MESSAGE = "King이 잡혔으므로 게임을 종료합니다";

    public static void printStartGuideMessage() {
        System.out.println(CHESS_GAME_START_GUIDE_MESSAGE);
        System.out.println(GAME_START_GUIDE_MESSAGE);
        System.out.println(GAME_END_GUIDE_MESSAGE);
        System.out.println(MOVE_COMMAND_GUIDE_MESSAGE);
    }

    //todo: System.out.print(square), System.out,println()이 아니라 System.out.println(square)아닌가용?
    public static void printBoard(BoardDto boardDto) {
        List<List<String>> board = boardDto.getDto();

        for (List<String> line : board) {
            line.stream()
                    .forEach(square -> System.out.print(square));
            System.out.println();
        }
    }

    public static void printExceptionMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public static void printScores(Map<Position, Piece> chessBoard){
        ScoreCalculator scoreCalculator = new ScoreCalculator();
        scoreCalculator.calculateScores(chessBoard);
        System.out.println("black의 점수: "+ scoreCalculator.getBlackScore());
        System.out.println("white의 점수: "+ scoreCalculator.getWhiteScore());
    }

    public static void printCheckmateGuideMessage(){
        System.out.println(CHECKMATE_GUIDE_MESSAGE);
    }
}
