package chess.service;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.team.BlackTeam;
import chess.domain.team.Team;
import chess.domain.team.WhiteTeam;
import chess.dto.*;
import chess.repository.ChessRepository;
import chess.view.PieceConverter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameService {
    private ChessRepository chessRepository;
    private Gson gson;


    public ChessGameService(ChessRepository chessRepository) {
        this.chessRepository = chessRepository;
        this.gson = new Gson();
    }

    public ResponseDTO createChessGame() {
        ChessGameDTO chessGameDTO = createChessGameDTO(new ChessGame(new BlackTeam(), new WhiteTeam()));
        chessRepository.createChessGame(gson.toJson(chessGameDTO));
        return new ResponseDTO(true, gson.toJson(chessGameDTO), "게임이 생성 되었습니다.");
    }

    public ResponseDTO refreshChessGame(String roomId) {
        RoomDTO roomDTO = chessRepository.findRoomFromId(roomId);
        ChessGameDTO chessGameDTO = createChessGameDTO(new ChessGame(new BlackTeam(), new WhiteTeam()));
        chessRepository.saveChessGame(roomDTO.getGameId(), gson.toJson(chessGameDTO));
        return new ResponseDTO(true, gson.toJson(chessGameDTO), "게임이 재시작 되었습니다.");
    }

    public ResponseDTO loadChessGame(String roomId) {
        RoomDTO roomDTO = chessRepository.findRoomFromId(roomId);
        String chessData = chessRepository.loadChessGame(roomDTO.getGameId());
        return new ResponseDTO(true, chessData, "게임을 로드 하였습니다.");
    }

    public Object endChessGame(final String gameId) {
        ChessGameDTO chessGameDTO = gson.fromJson(chessRepository.loadChessGame(gameId), ChessGameDTO.class);
        ChessGame chessGame = createChessGame(chessGameDTO);
        chessGame.finish();
        ChessGameDTO movedChessGameDTO = createChessGameDTO(chessGame);
        chessRepository.saveChessGame(gameId, gson.toJson(movedChessGameDTO));
        return new ResponseDTO(true, gson.toJson(movedChessGameDTO), "게임을 종료 했습니다.");
    }

    public ResponseDTO selectPiece(String roomId, String selected) {
        Position selectedPosition = Position.of(selected);
        RoomDTO roomDTO = chessRepository.findRoomFromId(roomId);
        String chessGameData = chessRepository.loadChessGame(roomDTO.getGameId());
        ChessGameDTO chessGameDTO = gson.fromJson(chessGameData, ChessGameDTO.class);
        ChessGame chessGame = createChessGame(chessGameDTO);
        boolean havePiece = chessGame.havePieceInCurrentTurn(selectedPosition);

        if (havePiece) {
            return new ResponseDTO(true, "", "기물을 선택 하셨습니다.");
        }
        return new ResponseDTO(false, "", "잘못 선택 하셨습니다.");
    }

    public ResponseDTO moveChessGame(String roomId, String selected, String target) {
        Position selectedPosition = Position.of(selected);
        Position targetPosition = Position.of(target);

        RoomDTO roomDTO = chessRepository.findRoomFromId(roomId);
        System.out.println(roomDTO);
        String chessGameData = chessRepository.loadChessGame(roomDTO.getGameId());
        ChessGameDTO chessGameDTO = gson.fromJson(chessGameData, ChessGameDTO.class);
        ChessGame chessGame = createChessGame(chessGameDTO);
        boolean isSuccess = chessGame.move(selectedPosition, targetPosition);
        if (isSuccess) {
            ChessGameDTO movedChessGameDTO = createChessGameDTO(chessGame);
            chessRepository.saveChessGame(roomDTO.getGameId(), gson.toJson(movedChessGameDTO));
            return new ResponseDTO(true, gson.toJson(movedChessGameDTO), "움직였습니다.");
        }
        return new ResponseDTO(false, "", "움직일 수 없습니다.");
    }

    public ResponseDTO createRoom(String data) {
        createChessGame();
        RoomDTO roomDTO = gson.fromJson(data, RoomDTO.class);
        RoomsDTO roomsDTO = chessRepository.createRoom(roomDTO.getName(), roomDTO.getPw());
        return new ResponseDTO(true, gson.toJson(roomsDTO), "방을 생성하였습니다.");
    }

    private ChessGame createChessGame(ChessGameDTO chessGameDTO) {
        System.out.println("createChessGame() called.");
        System.out.println(gson.toJson(chessGameDTO));
        TeamDTO blackTeamDTO = chessGameDTO.getBlackTeam();
        PiecesDTO blackPiecesDTO = blackTeamDTO.getPiecesDto();
        List<PieceDTO> blackPieceDTOs = blackPiecesDTO.getPieceDtoList();
        Map<Position, Piece> blackPiecePositions = new HashMap<>();

        for (PieceDTO pieceDTO : blackPieceDTOs) {
            Position position = Position.of(pieceDTO.getPosition());
            Piece piece = PieceConverter.convertToPiece(pieceDTO.getPiece());
            blackPiecePositions.put(position, piece);
        }
        BlackTeam blackTeam = new BlackTeam(blackTeamDTO.getName(), blackTeamDTO.isTurn(), blackPiecePositions);

        TeamDTO whiteTeamDTO = chessGameDTO.getWhiteTeam();
        PiecesDTO whitePiecesDTO = whiteTeamDTO.getPiecesDto();
        List<PieceDTO> whitePieceDTOs = whitePiecesDTO.getPieceDtoList();
        Map<Position, Piece> whitePiecePositions = new HashMap<>();

        for (PieceDTO pieceDTO : whitePieceDTOs) {
            Position position = Position.of(pieceDTO.getPosition());
            Piece piece = PieceConverter.convertToPiece(pieceDTO.getPiece());
            whitePiecePositions.put(position, piece);
        }

        WhiteTeam whiteTeam = new WhiteTeam(whiteTeamDTO.getName(), whiteTeamDTO.isTurn(), whitePiecePositions);

        Team currentTurn = whiteTeam;
        if (blackTeam.isCurrentTurn()) {
            currentTurn = blackTeam;
        }

        blackTeam.setEnemy(whiteTeam);
        whiteTeam.setEnemy(blackTeam);

        ChessGame chessGame = new ChessGame(blackTeam, whiteTeam, currentTurn, !chessGameDTO.isRunning());
        return chessGame;
    }


    private ChessGameDTO createChessGameDTO(final ChessGame chessGame) {
        final Map<Position, String> blackPieces = convertToBlackPrintName(chessGame.getBlackTeam().getPiecePosition());
        final Map<Position, String> whitePieces = convertToWhitePrintName(chessGame.getWhiteTeam().getPiecePosition());


        List<PieceDTO> blackPieceDtos = new ArrayList<>();

        for (Map.Entry<Position, String> entry : blackPieces.entrySet()) {
            blackPieceDtos.add(new PieceDTO(entry.getKey().getKey(), entry.getValue()));
        }

        PiecesDTO blackPiecesDto = new PiecesDTO(blackPieceDtos);

        List<PieceDTO> whitePieceDtos = new ArrayList<>();

        for (Map.Entry<Position, String> entry : whitePieces.entrySet()) {
            whitePieceDtos.add(new PieceDTO(entry.getKey().getKey(), entry.getValue()));
        }

        PiecesDTO whitePiecesDto = new PiecesDTO(whitePieceDtos);

        TeamDTO whiteTeamDTO = new TeamDTO(whitePiecesDto, chessGame.getWhiteTeam().getName(),
                String.valueOf(chessGame.getWhiteTeam().calculateTotalScore()),
                chessGame.getWhiteTeam().isCurrentTurn());


        TeamDTO blackTeamDTO = new TeamDTO(blackPiecesDto, chessGame.getBlackTeam().getName(),
                String.valueOf(chessGame.getBlackTeam().calculateTotalScore()),
                chessGame.getBlackTeam().isCurrentTurn());


        ChessGameDTO chessGameDto = new ChessGameDTO(blackTeamDTO, whiteTeamDTO, !chessGame.isEnd());
        return chessGameDto;
    }

    private Map<Position, String> convertToBlackPrintName(final Map<Position, Piece> pieces) {
        final Map<Position, String> blackPrintFormat = new HashMap<>();
        for (Position position : pieces.keySet()) {
            final Piece piece = pieces.get(position);
            blackPrintFormat.put(position, PieceConverter.convertToPieceName(piece).toUpperCase());
        }
        return blackPrintFormat;
    }

    private Map<Position, String> convertToWhitePrintName(final Map<Position, Piece> pieces) {
        final Map<Position, String> whitePrintFormat = new HashMap<>();
        for (Position position : pieces.keySet()) {
            final Piece piece = pieces.get(position);
            if (piece == null) continue;
            whitePrintFormat.put(position, PieceConverter.convertToPieceName(piece).toLowerCase());
        }
        return whitePrintFormat;
    }

}
