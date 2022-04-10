package chess.service;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

import chess.dao.GameStateDao;
import chess.dao.PieceDao;
import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.dto.PieceDto;
import chess.dto.ScoreDto;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

public class ChessGameService {

    private static final String EMPTY_GAME_STATE = "nothing";
    private static final String PLAYING_GAME_STATE = "playing";

    private static final Map<String, Function<Team, Piece>> PIECE_CREATION_STRATEGY_BY_NAME =
            Map.of("Pawn", Pawn::new, "King", King::new, "Queen", Queen::new,
                    "Rook", Rook::new, "Knight", Knight::new, "Bishop", Bishop::new);
    private static final Map<String, Team> TEAM_CREATION_STRATEGY = Map.of("WHITE", WHITE, "BLACK", BLACK);

    private final PieceDao pieceDao;
    private final GameStateDao gameStateDao;

    public ChessGameService(final PieceDao pieceDao, final GameStateDao gameStateDao) {
        this.pieceDao = pieceDao;
        this.gameStateDao = gameStateDao;
    }

    public Map<Position, Piece> getPieces() {
        final String gameState = gameStateDao.getGameState();

        if (gameState.equals(EMPTY_GAME_STATE)) {
            return new HashMap<>();
        }
        return convertToPiecesByPosition();
    }

    public Map<Position, Piece> start() {
        validatePlayingGame();
        final Board board = new Board();
        final String turn = board.getTurn()
                .name();
        gameStateDao.saveTurn(turn);
        gameStateDao.saveState(PLAYING_GAME_STATE);
        pieceDao.saveAllPieces(board.getPieces());
        return board.getPieces();
    }

    private void validatePlayingGame() {
        if (gameStateDao.hasPlayingGame()) {
            throw new IllegalStateException("이미 진행 중인 게임이 있습니다.");
        }
    }

    public Map<Position, Piece> move(final String sourcePosition, final String targetPosition) {
        checkPlayingGame();
        Board board = getSavedBoard();
        final Board movedBoard = board.movePiece(Position.from(sourcePosition), Position.from(targetPosition));

        pieceDao.saveAllPieces(movedBoard.getPieces());
        final Team turn = movedBoard.getTurn();
        gameStateDao.saveTurn(turn.name());
        return board.getPieces();
    }

    private void checkPlayingGame() {
        if (!gameStateDao.hasPlayingGame()) {
            throw new IllegalStateException("진행 중인 게임이 없습니다.");
        }
    }

    private Board getSavedBoard() {
        final String turn = gameStateDao.getTurn();
        final Team turnTeam = TEAM_CREATION_STRATEGY.get(turn);
        final Map<Position, Piece> pieces = convertToPiecesByPosition();
        return new Board(pieces, turnTeam);
    }

    private Map<Position, Piece> convertToPiecesByPosition() {
        final Map<String, PieceDto> savedPieces = pieceDao.findAllPieces();
        final Map<Position, Piece> pieces = new HashMap<>();

        for (Entry<String, PieceDto> entry : savedPieces.entrySet()) {
            final Position position = Position.from(entry.getKey());
            final PieceDto pieceDto = entry.getValue();
            final String name = pieceDto.getName();

            final Team team = TEAM_CREATION_STRATEGY.get(pieceDto.getTeam());
            final Piece piece = PIECE_CREATION_STRATEGY_BY_NAME.get(name)
                    .apply(team);
            pieces.put(position, piece);
        }
        return pieces;
    }

    public ScoreDto getScore() {
        checkPlayingGame();
        final Board board = getSavedBoard();
        return new ScoreDto(board.getTotalPoint(WHITE), board.getTotalPoint(BLACK));
    }

    public Map<Position, Piece> end() {
        final Board board = getSavedBoard();
        checkPlayingGame();
        gameStateDao.removeGameState();
        pieceDao.removeAllPieces();
        return board.getPieces();
    }
}
