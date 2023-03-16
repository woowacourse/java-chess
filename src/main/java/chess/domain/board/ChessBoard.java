package chess.domain.board;

import chess.domain.camp.CampType;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.piece.PieceType.BISHOP;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.PAWN;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.ROOK;

public final class ChessBoard {
    private static final int AREA_RANK_SIZE = 2;
    private static final int FILE_SIZE = 8;
    private static final int BLANK_AREA_RANK_SIZE = 4;
    private static final PieceType[][] pieceRanks = {
            {ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK},
            {PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN}
    };

    private final Map<Position, Piece> board;

    public ChessBoard() {
        this.board = new HashMap<>();
        init();
    }

    public boolean contains(final Position possiblePosition) {
        return board.containsKey(possiblePosition);
    }

    public Piece checkPiece(final Position source) {
        if (contains(source)) {
            return board.get(source);
        }
        throw new IllegalArgumentException("체스말이 존재하는 위치를 입력해 주세요.");
    }

    public void removePiece(final Position position) {
        board.remove(position);
    }
    
    private void init() {
        int whiteAreaRank = initWhiteArea();
        initBlackArea(whiteAreaRank + BLANK_AREA_RANK_SIZE);
    }

    private int initWhiteArea() {
        for (int rank = 0; rank < AREA_RANK_SIZE; rank++) {
            makeChessRank(rank, pieceRanks[rank], CampType.WHITE);
        }
        return AREA_RANK_SIZE;
    }

    private void initBlackArea(int startRank) {
        for (int rank = AREA_RANK_SIZE - 1; rank >= 0; rank--) {
            makeChessRank(startRank++, pieceRanks[rank], CampType.BLACK);
        }
    }

    private void makeChessRank(final int rank, final PieceType[] ranks, final CampType campType) {
        for (int file = 0; file < FILE_SIZE; file++) {
            final Position position = new Position(rank, file);
            final Piece piece = new Piece(ranks[file], campType);
            board.put(position, piece);
        }
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
