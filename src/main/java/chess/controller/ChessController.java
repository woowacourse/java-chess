package chess.controller;

import chess.dao.DbChessGameDao;
import chess.domain.game.ChessGame;
import chess.domain.game.File;
import chess.domain.game.PieceMapper;
import chess.domain.game.Position;
import chess.domain.game.Rank;
import chess.domain.game.Turn;
import chess.domain.gamefactory.DefaultGameFactory;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import chess.dto.game.ChessGameLoadDto;
import chess.dto.inputView.ReadCommandDto;
import chess.dto.outputView.PrintEndMessageDto;
import chess.dto.outputView.PrintErrorMessageDto;
import chess.dto.outputView.PrintInitialMessageDto;
import chess.service.ChessGameService;
import chess.view.IOViewResolver;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static chess.controller.GameCommand.END;
import static chess.controller.GameCommand.FIRST_MOVE;
import static chess.controller.GameCommand.INIT;
import static chess.controller.GameCommand.MOVE;
import static chess.controller.GameCommand.SOURCE_INDEX;
import static chess.controller.GameCommand.START;
import static chess.controller.GameCommand.STATUS;
import static chess.controller.GameCommand.TARGET_INDEX;
import static chess.controller.GameCommand.getPosition;

public final class ChessController {

    private final Map<GameCommand, BiFunction<List<String>, ChessGame, GameCommand>> gameStatusMap;
    private final IOViewResolver ioViewResolver;
    private final ChessGameService chessGameService;

    public ChessController(final IOViewResolver ioViewResolver) {
        this.ioViewResolver = ioViewResolver;
        this.gameStatusMap = new EnumMap<>(GameCommand.class);
        this.chessGameService = new ChessGameService(new DbChessGameDao());
        initGameStatusMap();
    }

    private void initGameStatusMap() {
        gameStatusMap.put(START, this::start);
        gameStatusMap.put(FIRST_MOVE, this::move);
        gameStatusMap.put(MOVE, this::move);
        gameStatusMap.put(STATUS, this::status);
        gameStatusMap.put(END, this::end);
    }

    public void process() {
        ioViewResolver.outputViewResolve(new PrintInitialMessageDto());
        GameCommand gameCommand = INIT;
        ChessGame chessGame = null;
        while (!gameCommand.isEnd()) {
            gameCommand = play(gameCommand, chessGame);
            chessGame = updateChessGame(gameCommand, chessGame);
        }
    }

    private GameCommand play(final GameCommand gameCommand, final ChessGame chessGame) {
        try {
            final ReadCommandDto readCommandDto = ioViewResolver.inputViewResolve(ReadCommandDto.class);
            final List<String> input = readCommandDto.getResult();
            final GameCommand newGameCommand = GameCommand.from(input);
            return gameStatusMap.get(newGameCommand).apply(input, chessGame);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            ioViewResolver.outputViewResolve(new PrintErrorMessageDto(exception.getMessage()));
            return gameCommand;
        }
    }

    private ChessGame updateChessGame(final GameCommand gameCommand, final ChessGame chessGame) {
        if (gameCommand.isFirstMove()) {
            ChessGame updatedChessGame = null;
            if (chessGameService.hasHistory()) {
                final ChessGameLoadDto chessGameLoadDto = chessGameService.loadGame();
                updatedChessGame = ChessGame.of(parseBoard(chessGameLoadDto), parseTurn(chessGameLoadDto));
            }
            updatedChessGame = new DefaultGameFactory().generate();
            ioViewResolver.outputViewResolve(updatedChessGame.printBoard());
            return updatedChessGame;
        }
        return chessGame;
    }

    private GameCommand start(final List<String> input, ChessGame chessGame) {
        if (chessGame != null) {
            throw new IllegalArgumentException("체스 게임은 이미 진행되고 있습니다.");
        }

        return FIRST_MOVE;
    }

    private Map<Position, Piece> parseBoard(final ChessGameLoadDto dto) {
        final Map<Position, Piece> result = new HashMap<>();

        for (int i = 0; i < dto.getPieceTypes().size(); i++) {
            final PieceType pieceType = PieceType.valueOf(dto.getPieceTypes().get(i));
            final Team team = Team.valueOf(dto.getPieceTeams().get(i));
            final File file = File.valueOf(dto.getPieceFiles().get(i));
            final Rank rank = Rank.valueOf(dto.getPieceRanks().get(i));

            final Position position = Position.of(file, rank);
            final Piece piece = PieceMapper.get(pieceType, team);

            result.put(position, piece);
        }
        return result;
    }

    private Turn parseTurn(final ChessGameLoadDto turn) {
        final Team team = Team.valueOf(turn.getLastTurn());
        return Turn.of(team);
    }

    private GameCommand move(final List<String> input, final ChessGame chessGame) {
        if (chessGame == null) {
            throw new IllegalArgumentException("체스 게임은 아직 시작하지 않았습니다.");
        }
        final Piece deadPiece = chessGame.move(getPosition(input, SOURCE_INDEX), getPosition(input, TARGET_INDEX));
        if (deadPiece.isSamePieceTypeAs(PieceType.KING)) {
            ioViewResolver.outputViewResolve(chessGame.getWinner(deadPiece));
            return END;
        }
        ioViewResolver.outputViewResolve(chessGame.printBoard());
        return MOVE;
    }

    private GameCommand status(final List<String> strings, final ChessGame chessGame) {
        chessGameService.delete();
        ioViewResolver.outputViewResolve(chessGame.calculateScore());
        ioViewResolver.outputViewResolve(new PrintEndMessageDto());
        return END;
    }

    private GameCommand end(final List<String> input, final ChessGame chessGame) {
        ioViewResolver.outputViewResolve(new PrintEndMessageDto());
        chessGameService.save(chessGame);
        return END;
    }
}
