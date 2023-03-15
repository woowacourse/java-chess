package chess;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static chess.PieceType.BISHOP;
import static chess.PieceType.BLANK;
import static chess.PieceType.KING;
import static chess.PieceType.KNIGHT;
import static chess.PieceType.PAWN;
import static chess.PieceType.QUEEN;
import static chess.PieceType.ROOK;

public final class ChessBoard {
    private static final int AREA_RANK_SIZE = 2;
    private static final int BLANK_AREA_RANK_SIZE = 4;
    private static final PieceType[][] pieceRanks = {
            {ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK},
            {PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN}
    };
    private static final PieceType[] blankRank = {BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK};

    private final List<ChessBoardRank> files;

    public ChessBoard() {
        this.files = new ArrayList<>();
        init();
    }

    private void init() {
        int whiteAreaRank = initWhiteArea();
        int blankAreaRank = initBlankArea(whiteAreaRank);
        initBlackArea(blankAreaRank);
    }

    private int initWhiteArea() {
        for (int rank = 0; rank < AREA_RANK_SIZE; rank++) {
            final ChessBoardRank chessBoardRank = new ChessBoardRank(makeChessRank(rank, pieceRanks[rank]));
            files.add(chessBoardRank);
        }
        return AREA_RANK_SIZE;
    }

    private int initBlankArea(final int startRank) {
        for (int rank = 0; rank < BLANK_AREA_RANK_SIZE; rank++) {
            final ChessBoardRank chessBoardRank = new ChessBoardRank(makeChessRank(rank + startRank, blankRank));
            files.add(chessBoardRank);
        }
        return startRank + BLANK_AREA_RANK_SIZE;
    }

    private void initBlackArea(int startRank) {
        for (int rank = AREA_RANK_SIZE - 1; rank >= 0; rank--) {
            final ChessBoardRank chessBoardRank = new ChessBoardRank(makeChessRank(startRank++, pieceRanks[rank]));
            files.add(chessBoardRank);
        }
    }

    private Map<Position, Piece> makeChessRank(final int rank, final PieceType[] ranks) {
        final Map<Position, Piece> rankArea = new LinkedHashMap<>();
        for (int file = 0; file < 8; file++) {
            final Position position = new Position(rank, file);
            final Piece piece = new Piece(ranks[file]);
            rankArea.put(position, piece);
        }
        return rankArea;
    }

    public List<ChessBoardRank> getFiles() {
        return List.copyOf(files);
    }
}
