package chess.domain.board;

public class BoardInputForTest {
    public static final String DEFAULT_BOARD =
                    "R, N, B, Q, K, B, N, R\n" +
                    "P, P, P, P, P, P, P, P\n" +
                    "., ., ., ., ., ., ., .\n" +
                    "., ., ., ., ., ., ., .\n" +
                    "., ., ., ., ., ., ., .\n" +
                    "., ., ., ., ., ., ., .\n" +
                    "p, p, p, p, p, p, p, p\n" +
                    "r, n, b, q, k, b, n, r";

    public static final String EXAMPLE_BOARD =
                    "., N, ., Q, K, B, ., R\n" +
                    "., B, P, P, P, P, P, .\n" +
                    "., P, ., ., ., N, ., .\n" +
                    "R, ., ., ., ., ., ., P\n" +
                    "p, ., ., p, p, ., ., .\n" +
                    "., ., n, ., ., ., p, b\n" +
                    "., p, p, ., n, p, ., p\n" +
                    "r, ., b, q, k, ., ., r";

    public static final String DUPLICATED_PAWN_BOARD =
                    "., N, ., Q, K, B, ., R\n" +
                    "., B, P, P, P, P, P, .\n" +
                    "., P, ., ., ., N, ., .\n" +
                    "R, ., ., ., p, ., ., P\n" +
                    "p, ., ., ., p, ., ., .\n" +
                    "., ., n, ., p, ., p, b\n" +
                    "., p, p, ., n, ., ., p\n" +
                    "r, ., b, q, k, ., ., r";

    public static final String CHECKMATE_BOARD =
                    "., N, ., Q, ., B, ., R\n" +
                    "., B, P, P, ., P, P, .\n" +
                    "., P, ., ., ., N, ., .\n" +
                    "R, ., K, ., ., ., ., P\n" +
                    "p, ., ., p, p, ., ., .\n" +
                    "., ., n, ., ., ., p, b\n" +
                    "., p, p, ., n, p, ., p\n" +
                    "r, ., b, q, k, ., ., r";
}
