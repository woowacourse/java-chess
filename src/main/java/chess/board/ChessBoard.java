package chess.board;

import java.util.Map;

import chess.piece.EmptyPiece;
import chess.piece.Piece;
import chess.piece.PieceType;
import chess.piece.Team;

public class ChessBoard {

    private final Map<Position, Piece> board;

    public ChessBoard() {
        this.board = PieceFactory.createPiece();
    }

    private ChessBoard(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static ChessBoard createBoardByRule(final Map<Position, Piece> piecePosition) {
        return new ChessBoard(piecePosition);
    }

    public void movePiece(final Position from, final Position to) {
        final Piece fromPiece = board.get(from);

        fromPiece.validateMove(from, to, board);
        move(from, to);
    }

    private void move(final Position from, final Position to) {
        final Piece piece = board.get(from);
        board.put(from, new EmptyPiece());
        board.put(to, piece);
    }

    public boolean isKingKilled(final Team team) {
        return board.values()
                .stream()
                .filter(piece -> piece.isSameType(PieceType.KING))
                .noneMatch(piece -> piece.isSameTeam(team));
    }

    public GameScore getGameScore() {
        return new GameScore(board);
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
