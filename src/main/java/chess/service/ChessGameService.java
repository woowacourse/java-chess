package chess.service;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.team.BlackTeam;
import chess.domain.team.WhiteTeam;
import chess.dto.ChessGameDTO;
import chess.dto.ResponseDTO;
import chess.dto.RoomDTO;
import chess.dto.RoomsDTO;
import chess.repository.ChessRepository;
import com.google.gson.Gson;

public class ChessGameService {
    private ChessRepository chessRepository;
    private Gson gson;


    public ChessGameService(final ChessRepository chessRepository) {
        this.chessRepository = chessRepository;
        this.gson = new Gson();
    }

    public ResponseDTO createChessGame() {
        ChessGameDTO chessGameDTO = createChessGameDTO(new ChessGame(new BlackTeam(), new WhiteTeam()));
        chessRepository.createChessGame(gson.toJson(chessGameDTO));
        return new ResponseDTO(true, gson.toJson(chessGameDTO), "게임이 생성 되었습니다.");
    }

    public ResponseDTO refreshChessGame(final String roomId) {
        RoomDTO roomDTO = chessRepository.findRoomFromId(roomId);
        ChessGameDTO chessGameDTO = createChessGameDTO(new ChessGame(new BlackTeam(), new WhiteTeam()));
        chessRepository.saveChessGame(roomDTO.getGameId(), gson.toJson(chessGameDTO));
        return new ResponseDTO(true, gson.toJson(chessGameDTO), "게임이 재시작 되었습니다.");
    }

    public ResponseDTO loadChessGame(final String roomId) {
        RoomDTO roomDTO = chessRepository.findRoomFromId(roomId);
        String chessData = chessRepository.loadChessGame(roomDTO.getGameId());
        return new ResponseDTO(true, chessData, "게임을 로드 하였습니다.");
    }

    public ResponseDTO selectPiece(final String roomId, final String selected) {
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

    public ResponseDTO moveChessGame(final String roomId, final String selected, final String target) {
        Position selectedPosition = Position.of(selected);
        Position targetPosition = Position.of(target);

        RoomDTO roomDTO = chessRepository.findRoomFromId(roomId);
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

    public ResponseDTO createRoom(final String data) {
        createChessGame();
        RoomDTO roomDTO = gson.fromJson(data, RoomDTO.class);
        RoomsDTO roomsDTO = chessRepository.createRoom(roomDTO.getName(), roomDTO.getPw());
        return new ResponseDTO(true, gson.toJson(roomsDTO), "방을 생성하였습니다.");
    }

    public ResponseDTO getTotalRoom() {
        RoomsDTO roomsDTO = chessRepository.getTotalRoom();
        return new ResponseDTO(true, gson.toJson(roomsDTO), "방을 가져왔습니다.");
    }
}
