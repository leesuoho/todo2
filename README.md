## 소개
To-Do Application은 사용자가 할 일을 효율적으로 관리할 수 있도록 지원하는 Spring Boot 기반의 RESTful 웹 애플리케이션입니다. 유저는 회원가입 후 로그인하여 개인화된 To-Do 리스트를 생성, 조회, 수정, 삭제할 수 있습니다.
Spring Boot의 기본적인 기능 구현과 함께 로그인 세션 및 필터를 활용한 인증 처리 방식을 포함하고 있습니다.
---
## 주요기능

### 사용자 관리

* 회원가입: 새로운 사용자를 등록합니다.

* 로그인 및 로그아웃: 이메일과 비밀번호를 사용하여 로그인하며, 세션 기반 인증을 지원합니다.

* 유저 조회: 전체 사용자 또는 특정 사용자의 정보를 조회합니다.

* 유저 정보 수정 및 삭제: 사용자 정보를 수정하거나 삭제합니다.

---

### To-Do 관리

* To-Do 생성: 새로운 To-Do를 등록합니다.

* To-Do 조회: 사용자 본인의 모든 To-Do 또는 특정 To-Do를 조회합니다.

* To-Do 수정: To-Do의 제목과 내용을 수정합니다.

* To-Do 삭제: 특정 To-Do를 삭제합니다.

---

### 인증 및 보안

로그인 필터: 특정 URL 요청에 대해 세션 인증을 확인하여 인증되지 않은 사용자 접근을 차단합니다.

---

### 주의 사항

* 로그인하지 않은 사용자는 TODO API에 접근할 수 없습니다.

* 로그인 시 세션이 생성되며, 세션이 만료되거나 로그아웃하면 다시 로그인이 필요합니다.

---

![스크린샷 2024-12-18 023816](https://github.com/user-attachments/assets/d7748f57-a07c-458d-9c91-2964e051f916)


![스크린샷 2024-12-18 024454](https://github.com/user-attachments/assets/0d22bbd3-d39e-4067-8b66-2361d8310226)


![스크린샷 2024-12-18 224407](https://github.com/user-attachments/assets/033dcd76-b3de-4f7d-91bd-ea53cec2b1ef)


