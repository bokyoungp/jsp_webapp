//mysql의 경우에는 실행하지 않음
CREATE TABLE IF NOT EXISTS STUDENT (
    id    INT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(45),
    univ  VARCHAR(45),
    birth Date,
    email VARCHAR(45)
);

insert into STUDENT(name,univ,birth,email)
values ('홍길동', 'AA 대학교', '2000-01-01', 'hong@aa.com');