package chess.domain.player;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public class Record {
    private Map<Result, Integer> record;

    public Record(final Map<Result, Integer> record) {
        this.record = record;
    }

    public Record() {
        this(Arrays.stream(Result.values())
            .collect(toMap(Function.identity(), result -> 0)));
    }

    public static Record of(int win, int lose, int draw) {
        Record record = new Record();
        record.add(Result.WIN, win);
        record.add(Result.LOSE, lose);
        record.add(Result.DRAW, draw);
        return record;
    }

    public void add(final Result result) {
        record.put(result, record.get(result) + 1);
    }

    private void add(final Result result, int times) {
        record.put(result, record.get(result) + times);
    }

    public int get(final Result result) {
        return record.get(result);
    }
}
