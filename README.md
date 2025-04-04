# 내일배움캠프 CH3 일정 관리 앱 만들기 과제 V2

## 과제 소개
+ 목표 : 간단한 일정 관리 앱을 구현하며 Servlet Filter와 Cookie/Session, JPA 익히기
+ 기간 : 25.04.02 ~ 04.04
+ 기술 스택
	
    + 백엔드 : Spring Boot (Java)
    + 데이터베이스 : MySQL
    + 데이터 액세스 : JPA
    + 빌드 도구 : Gradle
    
+ 개발 환경: IntelliJ IDEA
+ 버전 관리: Git & Github

## 파일 구조
```
src
└── main
    └── java
        └── com.example.schedulev2
            ├── common
            │   └── Const
            ├── config
            │   ├── WebConfig
            │   ├── PasswordEncoder
            │   └── GlobalExceptionHandler
            ├── controller
            │   ├── ScheduleController
            │   ├── CommentController
            │   └── UserController
            ├── dto
            │   ├── CommentDto
            │   ├── UserDto
            │   └── ScheduleDto
            ├── entity
            │   ├── BaseEntity
            │   ├── User
            │   ├── Comment
            │   └── Schedule
            ├── exception
            │   ├── ApplicationException
            │   ├── PasswordMismatchException
            │   ├── LoginRequiredException
            │   ├── UnauthorizedException
            │   ├── CannotDeleteException
            │   ├── CannotFindException
            │   └── DuplicateUserException
            ├── filter
            │   └── LoginFilter
            ├── repository
            │   ├── CommentRepository
            │   ├── ScheduleRepository
            │   └── UserRepository
            ├── service
            │   ├── CommentService
            │   ├── ScheduleService
            │   └── UserService
            └── ScheduleV2Application

```

## 주요 기능
### < 회원 >
1. 회원가입
2. 로그인 / 로그아웃
3. 회원 조회
4. 비밀번호 변경
5. 회원 탈퇴

### < 일정 >
1. 일정 생성
2. 일정 목록 조회
3. 선택 일정 조회
4. 일정 수정
5. 일정 삭제

### < 댓글 >
1. 댓글 생성
2. 일정별 댓글 목록 조회
3. 댓글 수정
4. 댓글 삭제

## API 명세서
<img width="1082" src="https://github.com/user-attachments/assets/4038c83d-9b41-4dad-89bb-af49b1a107e9"/>
<img width="1078" src="https://github.com/user-attachments/assets/1aa9336c-7b0b-474e-a316-46116fb15994"/>
<img width="1064" src="https://github.com/user-attachments/assets/09a24730-ded0-43be-b9ee-105316b75179" />

## ERD
<img width="700" src="https://github.com/user-attachments/assets/537c13e3-e288-4201-b9a0-76012523571f"/>
