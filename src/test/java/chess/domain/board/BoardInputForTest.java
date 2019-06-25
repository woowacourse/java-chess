package chess.domain.board;

public class BoardInputForTest {
    public static final String DEFAULT_BOARD =
                    "RNBQKBNR\n" +
                    "PPPPPPPP\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "........\n" +
                    "pppppppp\n" +
                    "rnbqkbnr\n";

    public static final String EXAMPLE_BOARD =
                    ".N.QKB.R\n" +
                    ".BPPPPP.\n" +
                    ".P...N..\n" +
                    "R......P\n" +
                    "p..pp...\n" +
                    "..n...pb\n" +
                    ".pp.np.p\n" +
                    "r.bqk..r\n";

    public static final String DUPLICATED_PAWN_BOARD =
                    ".N.QKB.R\n" +
                    ".BPPPPP.\n" +
                    ".P...N..\n" +
                    "R...p..P\n" +
                    "p...p...\n" +
                    "..n.p.pb\n" +
                    ".pp.n..p\n" +
                    "r.bqk..r\n";

    public static final String CHECKMATE_BOARD =
            ".N.Q.B.R\n" +
                    ".BPP.PP.\n" +
                    ".P...N..\n" +
                    "R.K....P\n" +
                    "p..pp...\n" +
                    "..n...pb\n" +
                    ".pp.np.p\n" +
                    "r.bqk..r\n";
}
