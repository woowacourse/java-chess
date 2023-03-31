package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import chess.domain.piece.immediate.King;
import chess.domain.piece.immediate.Knight;
import chess.domain.piece.linear.Bishop;
import chess.domain.piece.linear.Queen;
import chess.domain.piece.linear.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.dto.PieceDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardFactory {

    public static Board generateBoard() {
        Map<Position, Piece> board = initBoard();
        return new Board(board);
    }

    public static Board generateBoard(List<PieceDto> pieces) {
        Map<Position, Piece> board = new HashMap<>();
        initEmpty(board);

        for (final PieceDto piece : pieces) {
            final File file = piece.getFile();
            final Rank rank = piece.getRank();
            final Type type = piece.getType();
            final Side side = piece.getSide();
            board.put(Position.of(file, rank), PieceFactory.generatePiece(type, side));
        }

        return new Board(board);
    }

    private static Map<Position, Piece> initBoard() {
        Map<Position, Piece> board = new HashMap<>();
        initEmpty(board);

        initPawns(Rank.TWO, Side.WHITE, board);
        initNonPawns(Rank.ONE, Side.WHITE, board);

        initPawns(Rank.SEVEN, Side.BLACK, board);
        initNonPawns(Rank.EIGHT, Side.BLACK, board);

        return board;
    }

    private static void initEmpty(final Map<Position, Piece> board) {
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                board.put(Position.of(file, rank), new Empty());
            }
        }
    }

    private static void initPawns(final Rank rank, final Side side, final Map<Position, Piece> board) {
        for (File file : File.values()) {
            board.put(Position.of(file, rank), new Pawn(side));
        }
    }

    private static void initNonPawns(final Rank rank, final Side side, final Map<Position, Piece> board) {
        board.put(Position.of(File.A, rank), new Rook(side));
        board.put(Position.of(File.B, rank), new Knight(side));
        board.put(Position.of(File.C, rank), new Bishop(side));
        board.put(Position.of(File.D, rank), new Queen(side));
        board.put(Position.of(File.E, rank), new King(side));
        board.put(Position.of(File.F, rank), new Bishop(side));
        board.put(Position.of(File.G, rank), new Knight(side));
        board.put(Position.of(File.H, rank), new Rook(side));
    }
}
