# ♟ java-chess
체스 게임 구현을 위한 저장소

<br>

## 1단계 - 체스판 초기화

- [x] 게임 시작 안내문을 출력한다.
- [x] `end` 를 입력하면 종료한다.
- [x] `start` 를 입력하면 게임을 시작한다.
- [x] `start` 를 입력하지 않은 상태에서, `start`, `end`이외의 명령어를 입력하면, 예외를 발생시킨다.
- [x] 체스 게임 초기화
    - [x] 플레이어 생성
		- [x] 흑 팀 플레이어 생성
		- [x] 백 팀 플레이어 생성
    - [x] 체스판 생성
      - [x] 각 열을 File, 각 행을 Rank라고 한다.
      - [x] 체스판의 위치는 File과 Rank를 가진다.
      - [x] 체스판의 1칸은 고유한 1개의 위치를 가진다.
      - [x] 위치는 캐싱 되어있다.
      - [x] 8 * 8 의 빈 칸들로 이루어진 체스 판을 생성한다.
    - [x] 기물 생성
      - [x] 기물들을 종류와 색깔 별로 하나씩 생성한다.
      - [x] 기물들은 캐싱 되어있다.
    - [x] 기물 배치
      - [x] 배치 방식은 공식 체스 룰을 따른다.
      - [x] 흑의 기물들을 상단에, 백의 기물들을 하단에 배치한다.
      - [x] 기물들을 플레이어들에게 나눠준다.
      - [x] 기물들을 체스판에 배치한다.
  - [x] 체스 판 출력
    - [x] 체스 판 전체를 출력한다.
    - [x] 흑의 기물들은 대문자로 표기하고, 백의 기물들은 소문자로 표기한다.
    - [x] `end` 외의 명령어가 입력 될 때 마다, 체스 판을 출력한다.

<br>

## 2단계 - 말 이동

- [x] `move ${현재위치} ${도착위치}` 를 입력받는다.
  - [x] 위의 형식에 맞지 않으면, 예외를 발생시킨다.
  - [x] `${현재위치}` 와 `${도착위치}` 의 File, Rank가 유효하지 않으면, 이동할 수 없다.
- [x] `${현재위치}` 에 자신의 기물이 없으면, 예외를 발생시킨다.
  - [x] 비어있는 칸인 경우
  - [x] 적의 기물이 있는 칸인 경우
- [x] 보드에서 `${현재위치}` 에 있는 기물을 `${도착위치}` 로 이동시킨다.
- [x] 기물이 `${도착위치}` 에 이동할 수 있으면, 이동시킨다.
  - [x] 이동할 수 없으면, 예외를 발생시킨다.
  - [x] 보드의 기물 상태를 갱신한다.
  - [x] 플레이어들의 기물 상태를 갱신한다.
- [x] 말 이동 가능 여부 판단
  - [x] 말 이동 방식은 공식 체스 룰을 따른다.
  - [x] Rook, Bishop, Queen, King
    - [x] `${현재위치}` 부터  `${도착위치}` 까지의 방향(기울기)을 구한다.
    - [x] 이동 명령의 방향이, 해당 기물이 이동할 수 없는 방향이면, 예외를 발생시킨다.
    - [x] 이동 명령 방향으로 이동할 수 있으면, 한 칸씩 `${도착위치}` 까지 검사한다.
    - [x] 이동 경로 중간(`${출발위치}` 다음부터 `${도착위치}` 직전까지)에 기물이 존재하면, 이동할 수 없다.
    - [x] `${도착위치}` 에 아군의 기물이 있으면, 이동할 수 없다.
  - [x] Knight
    - [x] `${현재위치}` 부터  `${도착위치}` 까지의 방향을 구한다.
    - [x] 이동 명령의 방향이, 해당 기물이 이동할 수 없는 방향이면, 예외를 발생시킨다.
    - [x] 이동 경로 중간(`${출발위치}` 다음부터 `${도착위치}` 직전까지)에 기물이 존재해도, 이동할 수 있다.
    - [x] `${도착위치}` 에 아군 기물이 있으면, 이동할 수 없다.
  - [x] Pawn
    - [x] `${현재위치}` 부터  `${도착위치}` 까지의 방향(기울기)을 구한다.
    - [x] 이동 명령의 방향이, 해당 기물이 이동할 수 없는 방향이면, 예외를 발생시킨다.
      - [x] 흑 팀일 때 : LEFT_DOWN, DOWN, RIGHT_DOWN
      - [x] 백 팀일 때 : LEFT_UP, UP, RIGHT_UP
    - [x] 이동 명령 방향이 수직 방향 일 때
      - [x] 앞으로 한 칸 이동할 수 있다.
      - [x] 처음 위치일 때
        - [x] 두 칸 이동할 수 있다.
          - [x] 이동 경로 중간(`${출발위치}` 다음부터 `${도착위치}` 직전까지)에 기물이 존재하면, 이동할 수 없다.
      - [x] `${도착위치}` 에 기물이 있으면, 이동할 수 없다.
    - [x] 이동 명령 방향이 대각선 방향 일 때
      - [x] `${도착위치}` 에 적의 기물이 없으면, 이동할 수 없다.

<br>

## 3단계 - 승패 및 점수

- [x] King이 잡힌 경우
  - [x] King이 잡히는 팀이 진다.
  - [x] 잡히는 즉시, 체스 판을 출력하고, 게임을 종료한다.
  - [x] `${승리한 팀}이 이겼습니다.` 를 출력한다.
- [x] King이 잡히지 않은 상태
  - [x] `status` 명령 입력
    - [x] 각 팀의 현재 점수를 출력한다.
- [x] 점수 계산
  - [x] 각 기물의 점수는, King은 0점, Queen은 9점, Rook은 5점, Bishop은 3점, Knight는 2.5점 이다.
  - [x] Pawn의 기본 점수는 1점이다. 하지만 한 File(열)에 같은 팀의 Pawn이 있는 경우, 1점이 아닌 0.5점으로 계산한다.
  - [x] 각 팀의 점수를 따로 계산한다. (요구사항)
  - [x] Pawn을 제외한 기물들 점수 계산
    - [x] 기물들의 점수를 모두 더한다.
  - [x] Pawn 점수 계산
    - [x] 각 File(열)별로 점수를 계산해 합친다.
      - [x] 한 File(열)에 Pawn이 2개 이상이면, 각 Pawn의 점수를 0.5점으로 계산한다.
      - [x] 그 외에는 각 Pawn의 점수를 1점으로 계산한다.

<br>

## 4단계 - 웹 적용
<br>

### Step 1
  - [x] 메인 페이지
    - [x] `체스 게임 타이틀`
    - [x] `시작 버튼`
      - [x] `시작 버튼` 을 누르면, 체스 게임이 시작되고, 체스 보드가 있는 뷰를 보여준다.
  - [x] 체스 보드 페이지
      - [x] 체스 보드를 출력한다.
    - [x] 현재 차례의 팀 이름을 출력한다.
    - [x] 양 팀의 점수를 출력한다.
    - [x] 기물 이동 입력
      - [x] 움직일 기물의 `현재 위치` 를 입력하는 입력 창
      - [x] 움직일 기물의 `도착 위치` 를 입력하는 입력 창
      - [x] `기물 이동 버튼`
	    - [x] 유효하지 않은 기물 이동으로 예외가 발생한다면, 예외 메세지를 안내문으로 띄운다.
          - [x] 기물은 이동시키지 않고, 기존 상태를 유지한다.
          - [x] 현재 차례 상태도 유지한다.
        - [x] 매 번 기물이 정상적으로 이동했을 때, 출력되고 있는 각 팀의 현재 점수를 갱신한다.
        - [x] `King` 이 잡혔다면, `${이긴 팀 색깔} 팀이 이겼습니다.` 안내문을 띄운다.
          - [x] `확인` 을 누르면, 메인 페이지로 돌아간다.
            - [x] 진행중이던 체스 게임 정보는 삭제한다.
              - [x] `메인 페이지` 에서 다시 `시작 버튼` 을 누르면, 새로운 체스 게임이 시작되어야 한다.
      - [x] `종료 버튼`
        - [x] `게임을 종료 하시겠습니까?` 확인 창을 띄워 물어본다.
          - [x] `예` 를 누르면, `메인 페이지` 로 돌아간다.
            - [x] 진행중이던 체스 게임 정보는 삭제한다.
              - [x] `메인 페이지` 에서 다시 `시작 버튼` 을 누르면, 새로운 체스 게임이 시작되어야 한다.
          - [x] `아니오` 를 누르면, 하고 있던 게임을 이어서 한다.

<br>

### Step2

- [x] 체스 보드 페이지
  - [x] 홀수 번 째로 보드 한 칸을 클릭했을 때,
    - [x] 해당 칸의 위치 값을 기물 이동 시작 위치로 저장한다.
  - [x] 짝수 번 째로 보드 한 칸을 클릭했을 때,
    - [x] 해당 칸의 위치 값을 기물 이동 도착 위치로 저장한다.
    - [x] 기물 이동 시작 위치와 도착 위치로 `Controller`에게 기물 이동 요청을 보낸다.

<br>



## 5단계 - DB 적용

- [ ] 메인 페이지 View

  - [ ] 방 목록
    - [ ] 생성되어 있는 방들의 목록
      - [x] 각 방의 제목들을 나열한다.
      - [ ] 방의 제목을 클릭하면, 진행중인 상태의 체스 게임 보드 화면으로 이동한다. (링크)
    - [ ] 각 방 오른쪽에 삭제 버튼
      - [x] 삭제버튼을 누르면, `방을 삭제하시겠습니까?` 확인 창이 뜬다.
      - [ ] `확인` 을 누르면, 방을 삭제한다.
      - [x] `취소` 를 누르면, 아무 일도 발생하지 않는다.
  - [x] 방 생성
    - [x] 방 제목 입력 칸
    - [x] 방 생성 버튼

- [x] DB
  - 서버를 재시작 해도, 이전에 존재하던 모든 방들의 게임을 이어서 할 수 있어야 한다.
  - [x] 서버를 시작했을 때
    - [x] Controller, Service, ChessGame을 실행시킨다.
    - [x] Board 생성시, 모든 보드 칸들을 Empty 상태인 Cell로 초기화한다.

  - [x] 체스 게임 방을 생성하거나, 기존에 존재하는 방에 들어갔을 때
    - [x] 체스 게임 방을 새로 생성했을 때
      - [x] 모든 것들을 새로 만들어서 DB에 저장한다. (캐싱되어있는 기물들과 보드 칸 위치들 제외)
        - [x] 체스 게임
          - [x] 체스 게임 title
          - [x] 현재 차례 팀 색깔 (초기 기본값 "white")
        - [x] 백 팀, 흑 팀 플레이어
          - [x] 맨 처음 배치상태의 기물들

    - [x] ChessGame id를 통해, DB에 저장되어있는 게임 정보를 ChessGame 객체에 로드한다.
      - [x] 체스 게임
        - [x] 요청받은 체스게임을 DB에서 가져온다.
      - [x] 백 팀, 흑 팀 플레이어
        - [x] Players의 기존 Collection을 clear() 한다.
        - [x] 불러온 체스게임의 플레이어들을 DB에서 가져와 Collection에 저장한다.
      - [x] 보드의 Cell들
        - [x] Board의 모든 Cell들을 Empty로 초기화한다.
        - [x] 저장되어있는 마지막 배치 상태의 기물들을 DB에서 가져와 Board의 Cell에 저장한다.
  - [x] 보드 상태 출력 요청
    
    - [x] Board에 있는 모든 Cell들의 상태 정보를 반환한다.
    
  - [x] 기물 이동 요청
    - [x] 이동이 불가능 할  때
      - [x] 예외를 발생시키고 어떤 업데이트도 하지 않는다.
    - [x] 이동이 가능할 때
      - [x] 움직인 기물의 위치 정보를 DB와 Board(Cell)에 모두 업데이트한다.
    - [x] 상대방의 기물을 잡았을 때
      - [x] 잡힌 기물을 DB와 Board(Cell)에서 모두 삭제한다.
    - [x] ChessGame의 현재 차례 팀 색깔 정보를 DB와 ChessGame에 모두 업데이트한다.

  - [x] 게임 종료 (게임 방 삭제 버튼을 클릭한 경우 또는 King이 잡힌 경우)
    - [x] 종료할 ChessGame에 대한 모든 정보들(chess_game, player, player_piece_position)을 DB에서 모두 삭제한다.
    

    ​    
    
  - 방 목록
    - 방
      - id (Long)
      - 방 제목 (String)
      - 현재 차례 팀 색깔 (String)
      - 백 팀 플레이어 id (Long)
      - 흑 팀 플레이어 id (Long)
        - 플레이어
          - id (Long)
          - 갖고있는 기물들
            - 기물
              - id (Long)
              - 이름 (String)
              - 위치
                - id (Long)
                - File (String)
                - Rank (String)

- [ ] 테이블 구성
```sql
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema chess_game
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema chess_game
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `chess_game` DEFAULT CHARACTER SET utf8 ;
USE `chess_game` ;

-- -----------------------------------------------------
-- Table `chess_game`.`chess_room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess_game`.`chess_room` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `current_turn_team_color` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `current_turn_team_color_UNIQUE` (`current_turn_team_color` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chess_game`.`piece`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess_game`.`piece` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `color` VARCHAR(255) NOT NULL,
  `player_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `fk_piece_player1_idx` (`player_id` ASC) VISIBLE,
  CONSTRAINT `fk_piece_player1`
    FOREIGN KEY (`player_id`)
    REFERENCES `chess_game`.`player` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chess_game`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess_game`.`player` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `chess_room_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_player_chess_room1_idx` (`chess_room_id` ASC) VISIBLE,
  CONSTRAINT `fk_player_chess_room1`
    FOREIGN KEY (`chess_room_id`)
    REFERENCES `chess_game`.`chess_room` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chess_game`.`position`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chess_game`.`position` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `file_value` VARCHAR(255) NOT NULL,
  `rank_value` VARCHAR(255) NOT NULL,
  `piece_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `file_value_UNIQUE` (`file_value` ASC) VISIBLE,
  UNIQUE INDEX `rank_value_UNIQUE` (`rank_value` ASC) VISIBLE,
  INDEX `fk_position_piece_idx` (`piece_id` ASC) VISIBLE,
  CONSTRAINT `fk_position_piece`
    FOREIGN KEY (`piece_id`)
    REFERENCES `chess_game`.`piece` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
```
<br>

