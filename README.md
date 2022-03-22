# java-chess

- [ ] start를 누를 경우 체스판이 초기화되며 게임이 시작된다.
- [ ] end를 누를 경우 게임이 종료된다.


- [ ] 각 기물의 시작 갯수는 제한된다
    - King, Queen : 1개
    - Rook, Bishop, Knight : 2개
    - Pawn : 8개
  
  
- [ ] 검은색 진영은 기물이 대문자로 저장된다.
- [ ] 흰색 진영은 기물이 소문자로 저장된다.


- [ ] 기물의 위치 가로는 왼쪽부터 오른쪽으로 a~h이다.
- [ ] 기물의 위치 세로는 아래부터 위로 1~8이다.


- [ ] 각 기물의 초기위치는 아래와 같다.
```java
RNBQKBNR  8
PPPPPPPP  7
........  6
........  5
........  4
........  3
pppppppp  2
rnbqkbnr  1

abcdefgh
```
- 출력 형태
- King : k
- Queen : q
- Rook : r
- Bishop  b
- Knight : n
- Pawn : p