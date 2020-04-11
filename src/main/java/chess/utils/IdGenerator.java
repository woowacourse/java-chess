package chess.utils;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private static AtomicInteger roomSeq = new AtomicInteger((int) new Date().getTime() * 10);
    private static AtomicInteger moveSeq = new AtomicInteger((int) new Date().getTime() * 10);

    public static int generateRoomId() {
        return roomSeq.incrementAndGet();
    }

    public static int generateMoveId() {
        return moveSeq.incrementAndGet();
    }
}