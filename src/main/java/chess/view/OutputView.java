package chess.view;

import chess.domain.Color;
import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import chess.dto.BoardDto;
import chess.dto.ScoreDto;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OutputView {

    private static final OutputView OUTPUT_VIEW = new OutputView();
    private static final int ONE_LINE_AMOUNT = 8;
    public static final String CHESS_GAME_START = "> 체스 게임을 시작합니다.";
    public static final String CHESS_COMMAND_START = "> 게임 시작 : start";
    public static final String CHESS_COMMAND_END = "> 게임 종료 : end";
    public static final String CHESS_COMMAND_MOVE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    public static final String SCORE_NOW_MESSAGE = "---- 현재 점수 ----";
    public static final String BLACK_MESSAGE = "BLACK : ";
    public static final String WHITE_MESSAGE = "WHITE : ";
    public static final String END_BY_KING = "KING이 잡혀 게임이 끝났습니다.";

    private OutputView() {
    }

    public static OutputView getInstance() {
        return OUTPUT_VIEW;
    }


    public void initialPrint() {
        System.out.println(CHESS_GAME_START);
        System.out.println(CHESS_COMMAND_START);
        System.out.println(CHESS_COMMAND_END);
        System.out.println(CHESS_COMMAND_MOVE);
    }

    public void printBoard(BoardDto boardDto) {
        Map<Position, Piece> board = boardDto.getBoard();

        List<Position> positions = Arrays.stream(Rank.values())
            .flatMap(getPositionWithRankAndFIle())
            .collect(Collectors.toList());

        printBoardMessage(board, positions);
    }

    private Function<Rank, Stream<? extends Position>> getPositionWithRankAndFIle() {
        return rank ->
            Arrays.stream(File.values())
                .map(file -> Position.of(rank, file));
    }

    private void printBoardMessage(Map<Position, Piece> board, List<Position> positions) {
        String fullBoard = positions.stream()
            .map(position -> board.get(position))
            .map(PieceMapper::getByPiece)
            .collect(Collectors.joining());

        for (int i = 0; i < positions.size(); i += ONE_LINE_AMOUNT) {
            System.out.println(fullBoard.substring(i, i + ONE_LINE_AMOUNT));
        }
    }

    public void printScore(ScoreDto scoreDto) {
        Map<Color, Double> score = scoreDto.getScore();

        System.out.println(SCORE_NOW_MESSAGE);
        System.out.println(BLACK_MESSAGE + score.get(Color.BLACK));
        System.out.println(WHITE_MESSAGE + score.get(Color.WHITE));
    }

    public void printGameEnded(ScoreDto scoreDto) {
        System.out.println(END_BY_KING);
        printScore(scoreDto);
    }
}
