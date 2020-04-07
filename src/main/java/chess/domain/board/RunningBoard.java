package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.InitializedPawn;
import chess.domain.piece.Piece;
import chess.domain.piece.factory.PieceFactory;
import chess.domain.piece.position.Position;
import chess.domain.piece.team.Team;
import chess.domain.ui.UserInterface;

import java.util.HashMap;
import java.util.Map;

public class RunningBoard extends StartedBoard {
    private static final int LINE_START_INDEX = 1;
    private static final int LINE_END_INDEX = 8;
    private static final int BLACK_PAWN_ROW = 7;
    private static final int WHITE_PAWN_ROW = 2;
    private static final int BLANK_START_INDEX = 3;
    private static final int BLANK_END_INDEX = 6;

    RunningBoard(Map<Position, Piece> pieces, UserInterface userInterface) {
        super(pieces, userInterface);
    }

    public static RunningBoard initiaize(UserInterface userInterface) {
        Map<Position, Piece> pieces = new HashMap<>();
        initializeBlackTeam(pieces);
        initializeBlanks(pieces);
        initializeWhiteTeam(pieces);
        return new RunningBoard(pieces, userInterface);
    }


    @Override
    public boolean isNotFinished() {
        return true;
    }

    @Override
    public Result concludeResult() {
        throw new IllegalStateException("게임이 끝나지 않아, 승패를 결정할 수 없습니다.");
    }

    private static void initializeBlackTeam(Map<Position, Piece> pieces) {
        initializeEdge(pieces, Team.BLACK, LINE_END_INDEX);
        initializePawns(BLACK_PAWN_ROW, Team.BLACK, pieces);

    }

    private static void initializeWhiteTeam(Map<Position, Piece> pieces) {
        initializeEdge(pieces, Team.WHITE, LINE_START_INDEX);
        initializePawns(WHITE_PAWN_ROW, Team.WHITE, pieces);
    }

    private static void initializeEdge(Map<Position, Piece> pieces, Team team, int edgeRow) {
        for (int x = LINE_START_INDEX; x <= LINE_END_INDEX; x++) {
            Position position = Position.of(x, edgeRow);
            Piece piece = PieceFactory.createInitializedPieceWithInitialColumn(x, position, team);
            pieces.put(position, piece);
        }
    }

    private static void initializePawns(int row, Team team, Map<Position, Piece> pieces) {
        for (int x = LINE_START_INDEX; x <= LINE_END_INDEX; x++) {
            Position position = Position.of(x, row);
            Piece initializedPawn = PieceFactory.createInitializedPiece(InitializedPawn.class, position, team);
            pieces.put(position, initializedPawn);
        }
    }

    private static void initializeBlanks(Map<Position, Piece> pieces) {
        for (int y = BLANK_START_INDEX; y <= BLANK_END_INDEX; y++) {
            initializeBlanks(pieces, y);
        }
    }

    private static void initializeBlanks(Map<Position, Piece> pieces, int y) {
        for (int x = LINE_START_INDEX; x <= LINE_END_INDEX; x++) {
            Position position = Position.of(x, y);
            Blank blank = Blank.of();
            pieces.put(position, blank);
        }
    }
}
