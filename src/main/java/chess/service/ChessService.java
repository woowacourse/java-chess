package chess.service;

import chess.domain.board.Board;
import chess.domain.location.Location;
import chess.domain.piece.Piece;
import chess.domain.team.Team;
import chess.dto.chess.ChessDto;
import chess.dto.chess.MoveRequestDto;
import chess.dto.chess.MoveResponseDto;
import chess.dto.game.GamePutRequestDto;
import chess.dto.piece.PieceDto;
import chess.dto.user.UserResponseDto;
import chess.entity.Game;
import chess.entity.User;
import chess.utils.PieceConverter;
import java.util.List;
import java.util.stream.Collectors;

public class ChessService {

    private final PieceService pieceService;
    private final GameService gameService;
    private final UserService userService;

    public ChessService(final PieceService pieceService, final GameService gameService,
        final UserService userService) {

        this.pieceService = pieceService;
        this.gameService = gameService;
        this.userService = userService;
    }

    public ChessDto bringChessInfo(final long gameId) {
        final Game game = gameService.find(gameId);
        final List<PieceDto> pieceDtos = pieceService.findAll(gameId);

        final User whiteUser = userService.findById(game.getWhiteId());
        final User blackUser = userService.findById(game.getBlackId());

        return new ChessDto(
            pieceDtos,
            UserResponseDto.from(whiteUser),
            UserResponseDto.from(blackUser),
            game.getTurnValue(),
            game.isFinished()
        );
    }

    public boolean checkMovement(final long gameId, final String source,
        final String target, final Team team) {
        if (isWrongTurn(gameId, team)) {
            return false;
        }

        final Board board = makeBoard(gameId);
        return board.isMovable(Location.convert(source), Location.convert(target), team);
    }

    private boolean isWrongTurn(final long gameId, final Team team) {
        final Game game = gameService.find(gameId);
        return !team.equals(game.getTurn());
    }

    private Board makeBoard(final long gameId) {
        final List<PieceDto> pieceDtos = pieceService.findAll(gameId);
        final List<Piece> pieces = pieceDtos.stream()
            .map(PieceConverter::run)
            .collect(Collectors.toList());
        return Board.of(pieces);
    }

    public MoveResponseDto move(final long gameId, final MoveRequestDto moveRequestDto) {
        final Game game = gameService.find(gameId);
        final Team team = game.getTurn();
        final Location source = Location.convert(moveRequestDto.getSource());
        final Location target = Location.convert(moveRequestDto.getTarget());
        final Board board = makeBoard(gameId);

        removePieceIfExistent(gameId, board, target);
        updatePieceLocation(gameId, source, target);
        board.move(source, target, team);
        final boolean isFinished = !board.isKingAlive(team.reverse());
        updateGame(game, isFinished);
        updateWinLose(game, isFinished);
        return new MoveResponseDto(isFinished, getWinnerName(game, isFinished));
    }

    private void removePieceIfExistent(final long gameId, final Board board,
        final Location target) {

        if (board.isExistent(target)) {
            pieceService.deleteByLocation(gameId, target.getX(), target.getY());
        }
    }

    private void updatePieceLocation(final long gameId, final Location source,
        final Location target) {

        pieceService.updateLocation(gameId, source.getX(), source.getY(),
            target.getX(), target.getY());
    }

    private void updateGame(final Game game, final boolean isFinished) {
        final Team nextTeam = game.getTurn().reverse();
        final GamePutRequestDto gamePutRequestDto =
            new GamePutRequestDto(game.getId(), nextTeam.getValue(), isFinished);

        gameService.update(gamePutRequestDto);
    }

    private void updateWinLose(final Game game, final boolean isFinished) {
        if (!isFinished) {
            return;
        }
        final Team winTeam = game.getTurn();
        if (winTeam.isWhite()) {
            userService.updateResult(game.getWhiteId(), game.getBlackId());
            return;
        }
        userService.updateResult(game.getBlackId(), game.getWhiteId());
    }

    private String getWinnerName(final Game game, final boolean isFinished) {
        if (!isFinished) {
            return "";
        }
        final long userId = game.getTurn().isWhite() ? game.getWhiteId() : game.getBlackId();
        return userService.findById(userId).getName();
    }
}
