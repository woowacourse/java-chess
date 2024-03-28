package domain.game;

import domain.game.state.BlackTurn;
import domain.game.state.GameEnd;
import domain.game.state.GameState;
import domain.game.state.WhiteTurn;
import domain.position.Position;
import java.util.Map;
import java.util.Set;

public class ChessGame {
    private static final Set<PieceType> GAME_END_WHEN_CAUGHT = Set.of(PieceType.WHITE_KING, PieceType.BLACK_KING);

    private GameState state;
    private final Board board;

    protected ChessGame(GameState state, Board board) {
        this.state = state;
        this.board = board;
    }

    public ChessGame() {
        this(WhiteTurn.getInstance(), BoardInitializer.init());
    }

    public static ChessGame of(TeamColor savedTurn, Map<Position, Piece> piecePositions) {
        GameState state = getGameStateOf(savedTurn);
        return new ChessGame(state, new Board(piecePositions));
    }

    private static GameState getGameStateOf(TeamColor savedTurn) {
        if (savedTurn == TeamColor.WHITE) {
            return WhiteTurn.getInstance();
        }
        return BlackTurn.getInstance();
    }

    public void move(Position source, Position destination) {
        checkMovableState();
        MoveResponse moveResponse = board.movePiece(state.currentTurn(), source, destination);
        state = changeState(moveResponse);
    }

    private void checkMovableState() {
        if (isGameEnd()) {
            throw new IllegalStateException("게임이 종료되어 더 이상 움직일 수 없습니다.");
        }
    }

    private GameState changeState(MoveResponse moveResponse) {
        if (shouldGameEnd(moveResponse)) {
            return winStateOf(state);
        }
        return nextPlayingStateOf(state);
    }

    private boolean shouldGameEnd(MoveResponse moveResponse) {
        if (!moveResponse.hasCaught()) {
            return false;
        }
        PieceType caughtPieceType = moveResponse.caughtPieceType();
        return GAME_END_WHEN_CAUGHT.contains(caughtPieceType);
    }

    private GameState winStateOf(GameState state) {
        return GameEnd.getInstance(state.currentTurn());
    }

    private GameState nextPlayingStateOf(GameState state) {
        if (state.isTurnOf(TeamColor.WHITE)) {
            return BlackTurn.getInstance();
        }
        return WhiteTurn.getInstance();
    }

    public boolean isGameEnd() {
        return !state.isContinuable();
    }

    public TeamColor getWinner() {
        return state.getWinner();
    }

    public double currentScoreOf(TeamColor teamColor) {
        return board.calculateScoreOf(teamColor);
    }

    public TeamColor currentPlayingTeam() {
        return state.currentTurn();
    }

    public Map<Position, Piece> getPositionsOfPieces() {
        return board.getPositionsOfPieces();
    }
}
