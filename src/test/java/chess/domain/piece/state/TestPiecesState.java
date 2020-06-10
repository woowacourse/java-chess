package chess.domain.piece.state;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TestPiecesState extends PiecesState {
    private static final int BLACK_PAWN_ROW = 7;
    private static final int WHITE_PAWN_ROW = 2;

    private final Map<Position, Piece> pieces;

    private TestPiecesState(Map<Position, Piece> pieces) {
        super(pieces);
        this.pieces = pieces;
    }

    public static PiecesState initialize() {
        Map<Position, Piece> pieces = new HashMap<>();
        initializeBlackTeam(pieces);
        initializeWhiteTeam(pieces);
        return new TestPiecesState(pieces);
    }

    @Override
    public PiecesState movePiece(Position from, Position to) {
        Piece piece = pieces.get(from);
        pieces.put(to, piece);
        pieces.remove(from);
        return new PiecesState(pieces);
    }


    private static void initializeBlackTeam(Map<Position, Piece> pieces) {
        initializeEdge(pieces, Team.BLACK, Board.LINE_END);
        initializePawns(BLACK_PAWN_ROW, Team.BLACK, pieces);

    }

    private static void initializeWhiteTeam(Map<Position, Piece> pieces) {
        initializeEdge(pieces, Team.WHITE, Board.LINE_START);
        initializePawns(WHITE_PAWN_ROW, Team.WHITE, pieces);
    }

    private static void initializeEdge(Map<Position, Piece> pieces, Team team, int edgeRow) {
        for (int x = Board.LINE_START; x <= Board.LINE_END; x++) {
            Position position = Position.of(x, edgeRow);
            Piece piece = PieceFactory.createPieceWithInitialColumn(x, team);
            pieces.put(position, piece);
        }
    }

    private static void initializePawns(int row, Team team, Map<Position, Piece> pieces) {
        for (int x = Board.LINE_START; x <= Board.LINE_END; x++) {
            Position position = Position.of(x, row);
            Piece initializedPawn = PieceFactory.createInitializedPawn(team);
            pieces.put(position, initializedPawn);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TestPiecesState that = (TestPiecesState) o;
        return Objects.equals(pieces, that.pieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pieces);
    }
}
