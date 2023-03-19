package chess.view;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.dto.controllertoview.PieceInfo;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String EMPTY_SQUARE = ".";
    private static final String NEW_LINE = System.lineSeparator();
    private static final Map<Class<? extends Piece>, String> UPPER_SIGN_BY_PIECE;
    private static final Map<File, Integer> COLUMN_BY_FILE;
    private static final Map<Rank, Integer> ROW_BY_RANK;

    static {
        UPPER_SIGN_BY_PIECE = Map.of(
                Rook.class, "R",
                Knight.class, "N",
                Bishop.class, "B",
                Queen.class, "Q",
                King.class, "K",
                Pawn.class, "P"
        );
        COLUMN_BY_FILE = Map.of(
                File.A, 0,
                File.B, 1,
                File.C, 2,
                File.D, 3,
                File.E, 4,
                File.F, 5,
                File.G, 6,
                File.H, 7
        );
        ROW_BY_RANK = Map.of(
                Rank.ONE, 7,
                Rank.TWO, 6,
                Rank.THREE, 5,
                Rank.FOUR, 4,
                Rank.FIVE, 3,
                Rank.SIX, 2,
                Rank.SEVEN, 1,
                Rank.EIGHT, 0
        );
    }

    public void printGameStartGuideMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public void printBoard(final List<PieceInfo> pieceInfos) {
        final String boardMessage = generateBoardMessage(pieceInfos);

        System.out.println(boardMessage);
    }

    private String generateBoardMessage(final List<PieceInfo> pieceInfos) {
        final StringBuilder mapBuilder = generateEmptyBoardBuilder();

        for (PieceInfo pieceInfo : pieceInfos) {
            setUpPieceInBoard(mapBuilder, pieceInfo);
        }

        return mapBuilder.toString();
    }

    private static StringBuilder generateEmptyBoardBuilder() {
        final StringBuilder mapBuilder = new StringBuilder();
        for (int index = 0; index < ROW_BY_RANK.size(); index++) {
            mapBuilder.append(EMPTY_SQUARE.repeat(COLUMN_BY_FILE.size()))
                    .append(NEW_LINE);
        }
        return mapBuilder;
    }

    private void setUpPieceInBoard(final StringBuilder mapBuilder, final PieceInfo pieceInfo) {
        final int positionIndex = calculateIndex(pieceInfo.getPositionFile(), pieceInfo.getPositionRank());
        mapBuilder.replace(positionIndex, positionIndex + 1, getPieceAccordingToSign(pieceInfo));
    }

    private int calculateIndex(final File positionFile, final Rank positionRank) {
        final int oneLineLength = COLUMN_BY_FILE.size() + NEW_LINE.length();
        return oneLineLength * ROW_BY_RANK.get(positionRank) + COLUMN_BY_FILE.get(positionFile);
    }

    private static String getPieceAccordingToSign(final PieceInfo pieceInfo) {
        final String upperPieceMessage = UPPER_SIGN_BY_PIECE.get(pieceInfo.getPieceType());
        if (pieceInfo.getColor().equals(Color.BLACK)) {
            return upperPieceMessage;
        }
        return upperPieceMessage.toLowerCase();
    }
}
