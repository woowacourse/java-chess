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
    private static final int ONE_LINE_AMOUNT = 8;
    private static final int LINE_SEPARATE_CRITERIA = 7;
    private static final String END_MESSAGE_FOR_KING_CAPTURE = "KING이 잡혀 게임이 끝났습니다.";
    private static final OutputView OUTPUT_VIEW = new OutputView();

    private OutputView() {
    }

    public static OutputView getInstance() {
        return OUTPUT_VIEW;
    }

    public void initialPrint() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(BoardDto boardDto) {
        Map<Position, Piece> board = boardDto.getBoard();
        List<Position> positions = Arrays.stream(Rank.values())
                .flatMap(getPositionStream())
                .collect(Collectors.toList());

        System.out.println(getPrintResult(board, positions));
    }

    private StringBuilder getPrintResult(Map<Position, Piece> board, List<Position> positions) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < positions.size(); i++) {
            Piece piece = board.get(positions.get(i));
            stringBuilder.append(PieceMapper.getByPiece(piece));

            if (i % ONE_LINE_AMOUNT == LINE_SEPARATE_CRITERIA) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        return stringBuilder;
    }

    private static Function<Rank, Stream<? extends Position>> getPositionStream() {
        return rank ->
                Arrays.stream(File.values())
                        .map(file -> Position.of(rank, file));
    }

    public void printScore(ScoreDto scoreDto) {
        Map<Color, Double> score = scoreDto.getScore();

        System.out.println("---- 현재 점수 ----");
        System.out.println("BLACK : " + score.get(Color.BLACK));
        System.out.println("WHITE : " + score.get(Color.WHITE));
    }

    public void printGameEnded(ScoreDto scoreDto) {
        System.out.println(END_MESSAGE_FOR_KING_CAPTURE);
        printScore(scoreDto);
    }
}
