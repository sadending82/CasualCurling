# CasualCurling

스마트폰게임프로그래밍 텀 프로젝트입니다.

## 게임 컨셉

캐주얼하게 즐길 수 있는 컬링 게임을 안드로이드 환경에서 자바 언어를 사용하여 개발합니다./

## 개발 범위

 * 기본적으로 세로모드로 게임을 진행하도록 합니다.

 * 하나의 휴대폰으로 두 명이 플레이하는 대결 모드, 간단한 랜덤 발사를 통한 AI 모드 총 두가지의 모드를 개발합니다.

 * 기본적으로 컬링의 룰을 따르나, 실제 컬링과 같이 10엔드를 모두 하게되면 플레이 타임이 너무 길어지므로 기본 4엔드로 시작해 엔드 수를 늘릴 수 있도록 설정합니다.

 * 터치 후 슬라이드를 통해 스톤의 힘을 지정하고, 터치를 때는 순간 스톤이 날아가도록 하는 방식을 채택합니다.

 * 하나의 엔드가 끝나면 자동으로 결과를 계산하여 점수를 더하도록 하고, 게임이 끝나면 어느 쪽이 승리 하였는지도 표시합니다.

## 예상 게임 실행 흐름
<p align="center">
  <img src="https://user-images.githubusercontent.com/34495894/229641808-03586413-cb52-4dd9-99e1-9d53bc8a4b98.PNG">
</p>

## 개발 일정

|날짜|계획|
|:---|:--------------------|
|1주차(4/4 ~ 4/11)|리소스 제작 및 수집|
|2주차(4/12 ~ 4/17)|UI 위치 및 리소스 배치할 위치 결정|
|3주차(4/18 ~ 4/24)|컬링을 위한 물리연산 코딩|
|4주차(4/25 ~ 5/1)|컬링의 실질적인 움직임 코딩|
|5주차(5/2 ~ 5/8)|1대1 대련 모드 코딩 및 완성|
|6주차(5/9 ~ 5/15)|AI모드를 위한 AI 발사 알고리즘 코딩|
|7주차(5/16 ~ 5/22)|AI 모드 코딩 및 완성|
|8주차(5/23 ~ 5/29)|시작 및 모드 선택 화면 UI 배치 및 제작|
|9주차(5/30 ~ 6/5)|디버그 및 최종 검수|
 
 
 
## 1주차

### 리소스 제작 및 수집
![image](https://user-images.githubusercontent.com/34495894/236975742-3e199214-5709-42ac-88be-8e3a9805dfde.png)
![image](https://user-images.githubusercontent.com/34495894/236975880-1854709f-be83-4f9f-8421-6f29885966fc.png)

기본적인 리소스 들의 제작은 완료가 되었음.







## 중간 발표(5/9)까지의 진행 상황

|날짜|횟수|
|:---|:---|
|1주차|리소스 제작 및 수집|
|2주차|UI 위치 및 리소스 배치할 위치 결정|
|3주차|컬링을 위한 물리연산 코딩|
|4주차|컬링의 실질적인 움직임 코딩|
|5주차|1대1 대련 모드 코딩 및 완성|
|6주차|AI모드를 위한 AI 발사 알고리즘 코딩|
|7주차|AI 모드 코딩 및 완성|
|8주차|시작 및 모드 선택 화면 UI 배치 및 제작|
|9주차|디버그 및 최종 검수|








## Git Commit 횟수
![image](https://user-images.githubusercontent.com/34495894/236976274-b7b57850-2e0d-44e5-aa15-caf238c9a621.png)

|주차|횟수|
|:---|:---|
|1주차|3|
|2주차|0|
|3주차|0|
|4주차|0|
|5주차|4|









## MainScene에 등장하는 Object

기본적으로 MainScene에 등장하는 것은 컬링 스톤과 컬링 경기장 입니다.

### Curling Stone
![image](https://user-images.githubusercontent.com/34495894/236977815-bfb4d8bf-21d6-4d0e-8c4a-b018f44de378.png)

Stone의 경우 위와 같은 형식으로 상속을 받고 있습니다. 기본적으로 교수님의 Framework를 참고하여 제작하였고, 따로 충돌처리를 위한 PhysicsObject class를 추가하였습니다.

#### PhysicsObject

PhysicsObject는 속도를 바탕으로 한 움직임 및 충돌 등 물리적인 처리를 요구하는 오브젝트를 정의하는 클래스입니다. 해당 클래스의 코드에는 속도를 바탕으로 하는 이동 및 마찰력의 계산, 충돌 시 탄성 충돌을 하도록 하는 코드 등이 포함되어 있습니다.



![image](https://user-images.githubusercontent.com/34495894/236980666-bbc53c07-e669-4c87-ac49-3319d3424237.png)

위는 Update에서 속도 만큼의 위치를 더해주고 속도에 마찰력을 설정해 마찰력 만큼 속도를 줄여주는 코드입니다.



![image](https://user-images.githubusercontent.com/34495894/236980933-e09dbe7c-e8c4-4fd8-9221-5bb06e4fed47.png)

위는 두 PhysicsObject 객체가 충돌했을 때, 해당 객체들을 탄성 충돌하도록 설정하는 코드입니다.


