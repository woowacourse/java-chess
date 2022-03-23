package chess.view;

import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Row;
import chess.domain.piece.Piece;
import chess.dto.BoardDto;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OutputView {

    private static final OutputView OUTPUT_VIEW = new OutputView();
    private static final int ONE_LINE_AMOUNT = 8;
    private static final int LINE_SEPARATE_CRITERIA = 7;

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

        List<Position> collect = Arrays.stream(Row.values())
            .flatMap(getPositionStream())
            .collect(Collectors.toList());

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < collect.size(); i++) {
            Piece piece = board.get(collect.get(i));
            stringBuilder.append(PieceMapper.getByPiece(piece));

            if (i % ONE_LINE_AMOUNT == LINE_SEPARATE_CRITERIA) {
                stringBuilder.append(System.lineSeparator());
            }
        }

        System.out.println(stringBuilder);
    }

    private static Function<Row, Stream<? extends Position>> getPositionStream() {
        return row ->
            Arrays.stream(Column.values())
                .map(column -> Position.of(row, column));
    }
}
