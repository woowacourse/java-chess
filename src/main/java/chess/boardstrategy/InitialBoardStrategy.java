package chess.boardstrategy;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Color;
import chess.domain.piece.type.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InitialBoardStrategy implements BoardStrategy {
    private final Map<Position, Piece> board = new HashMap<>();

    @Override
    public Map<Position, Piece> generate() {
        initEmptyPieces();
        initFirstRow(Rank.EIGHT, Color.BLACK);
        initSecondRow(Rank.SEVEN, Color.BLACK);
        initFirstRow(Rank.ONE, Color.WHITE);
        initSecondRow(Rank.TWO, Color.WHITE);

        return new HashMap<>(board);
    }

    /**
     * 질문 : 처음 foreach를 사용하여 필드(Map)에 Position과 기물들을 put하였습니다.
     * stream 사용 시, foreach는 동시성 보장이 안되기 때문에 연산에 사용하지 말라는 글을 읽고
     * for문 대신 stream을 사용하되 아래와 같이 수정하였습니다.
     * foreach를 사용하지 않으니 가독성이 떨어지는 점도 있는데, 차라리 for문을 사용하는 것이 나은지
     * 현재와 같이 stream을 사용해도 좋은지
     * 리뷰어님의 의견 궁금합니다.
     */
    private void initEmptyPieces() {
        Map<Position, Piece> emptyPieces = Column.getOrderedColumns().stream()
                .flatMap(column -> Rank.getOrderedRanks().stream()
                        .map(rank -> Position.of(column, rank)))
                .collect(Collectors.toMap(Function.identity(), ignored -> EmptyPiece.of()));

        board.putAll(emptyPieces);
    }

    private void initFirstRow(Rank rank, Color color) {
        List<Piece> firstRowPieces = List.of(new Rook(color), new Knight(color), new Bishop(color), new Queen(color),
                new King(color), new Bishop(color), new Knight(color), new Rook(color));

        Map<Position, Piece> firstRow = IntStream.range(0, firstRowPieces.size())
                .boxed()
                .collect(Collectors.toMap(index -> Position.of(Column.findColumnByIndex(index + 1), rank),
                        firstRowPieces::get));

        board.putAll(firstRow);
    }

    private void initSecondRow(Rank rank, Color color) {
        Map<Position, Piece> secondRow = Arrays.stream(Column.values())
                .collect(Collectors.toMap(column -> Position.of(column, rank), ignored -> new Pawn(color)));

        board.putAll(secondRow);
    }
}
