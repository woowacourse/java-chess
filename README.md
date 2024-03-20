# java-chess

## 규칙

- 레퍼런스 : [체스 나무위키](https://namu.wiki/w/%EC%B2%B4%EC%8A%A4)
    - 추가규칙 (프로모션, 캐슬링, 앙파상) 은 우선순위를 미룬다.

## 프로그램 실행 흐름

1. "start"를 입력하면 체스 게임을 시작한다.
    - 대소문자를 무시한다.
2. 기물을 이동시킬 수 있다
    - "move source위치 target위치" 를 입력하면 source 위치의 기물을 target 위치로 이동시킨다.
        - 위치 : A~H 사이의 알파벳과 1~8 사이의 숫자 조합으로 입력한다. (예시 : b2, H8...)
    - [Exception] source 위치에 본인의 기물이 존재하지 않으면 예외가 발생한다.
    - [Exception] 기물이 target 위치로 이동할 수 없으면 예외가 발생한다.
3. "end"를 입력하면 체스 게임을 종료한다.

### 게임 시작시 초기 세팅

```
RNBQKBNR
PPPPPPPP
........
........
........
........
pppppppp
rnbqkbnr
```
