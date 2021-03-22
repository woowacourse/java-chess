package view;

import domain.Board;
import domain.piece.Position;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class OutputView {
    public static void showBoard(Board board) {
        AtomicInteger count = new AtomicInteger(0);
        IntStream.range(0, 8)
                .boxed()
                .flatMap(row -> IntStream.range(0, 8)
                        .mapToObj(column -> Position.valueOf(row, column)))
                .forEach(position -> {
                    System.out.print(board.getPiece(position).getName());
                    if (count.incrementAndGet() % 8 == 0) {
                        System.out.println();
                    }
                });
    }
}
