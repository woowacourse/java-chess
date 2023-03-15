package chess.view;

import chess.domain.Bishop;
import chess.domain.Color;
import chess.domain.File;
import chess.domain.King;
import chess.domain.Knight;
import chess.domain.Pawn;
import chess.domain.Piece;
import chess.domain.Position;
import chess.domain.Queen;
import chess.domain.Rank;
import chess.domain.Rook;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String EMPTY_SQUARE = ".";
    private static final Map<Class<? extends Piece>, String> UPPER_SIGN_BY_PIECE = Map.of(
            Rook.class, "R",
            Knight.class, "N",
            Bishop.class, "B",
            Queen.class, "Q",
            King.class, "K",
            Pawn.class, "P"
    );
    private static final Map<File, Integer> X_COORDINATE_BY_FILE = Map.of(
            File.A, 0,
            File.B, 1,
            File.C, 2,
            File.D, 3,
            File.E, 4,
            File.F, 5,
            File.G, 6,
            File.H, 7
    );
    private static final Map<Rank, Integer> Y_COORDINATE_BY_RANK = Map.of(
            Rank.ONE, 7,
            Rank.TWO, 6,
            Rank.THREE, 5,
            Rank.FOUR, 4,
            Rank.FIVE, 3,
            Rank.SIX, 2,
            Rank.SEVEN, 1,
            Rank.EIGHT, 0
    );

    public void printGameStartGuideMessage() {
        System.out.println("체스게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
    }

    public void printBoard(final List<Piece> pieces) {
        final StringBuilder mapBuilder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            mapBuilder.append(EMPTY_SQUARE.repeat(8));
            mapBuilder.append(System.lineSeparator());
        }

        for (Piece piece : pieces) {
            final int positionIndex = calculateIndex(piece.getPosition());
            mapBuilder.replace(positionIndex, positionIndex + 1, getPieceAccordingToSing(piece));
        }

        final String boardMessage = mapBuilder.toString();

        System.out.println(boardMessage);
    }

    private static String getPieceAccordingToSing(final Piece piece) {
        final String upperPieceMessage = UPPER_SIGN_BY_PIECE.get(piece.getClass());
        if (piece.isSameColor(Color.BLACK)) {
            return upperPieceMessage;
        }
        return upperPieceMessage.toLowerCase();
    }

    private int calculateIndex(final Position position) {
        return 9 * Y_COORDINATE_BY_RANK.get(position.getRank()) + X_COORDINATE_BY_FILE.get(position.getFile());
    }
}
