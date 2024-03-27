package domain.game;

import dao.GameDao;
import dao.PieceDao;
import domain.game.state.BlackTurn;
import domain.game.state.GameEnd;
import domain.game.state.GameState;
import domain.game.state.WhiteTurn;
import domain.position.Position;
import dto.PieceDto;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    public static ChessGame load(int gameId) {
        GameDao gameDao = GameDao.getInstance();
        TeamColor savedTurn = gameDao.findTurn(gameId);
        PieceDao pieceDao = PieceDao.getInstance();
        List<PieceDto> allPieces = pieceDao.findAllPieces(gameId);
        Map<Position, Piece> piecePositions = allPieces.stream()
                .collect(Collectors.toMap(
                        PieceDto::getPosition,
                        dto -> PieceFactory.create(dto.getPieceType())
                ));

        GameState state = savedTurn == TeamColor.WHITE ? WhiteTurn.getInstance() : BlackTurn.getInstance();
        return new ChessGame(state, new Board(piecePositions));
    }

    public void move(Position source, Position destination) {
        MoveResponse moveResponse = state.move(board, source, destination);

        if (shouldGameEnd(moveResponse)) {
            state = winStateOf(state);
            return;
        }
        state = nextStateOf(state);
    }

    private boolean shouldGameEnd(MoveResponse moveResponse) {
        if (!moveResponse.hasCaught()) {
            return false;
        }
        PieceType caughtPieceType = moveResponse.caughtPieceType();
        return GAME_END_WHEN_CAUGHT.contains(caughtPieceType);
    }

    private GameState winStateOf(GameState state) {
        TeamColor winner = state.currentTurn();
        return GameEnd.getInstance(winner);
    }

    private GameState nextStateOf(GameState state) {
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

    public int save() {
        return board.save(currentPlayingTeam());
    }
}
