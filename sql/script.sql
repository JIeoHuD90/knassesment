CREATE DATABASE quizapp;

USE quizapp;

CREATE TABLE quiz (
  quiz_id INT AUTO_INCREMENT PRIMARY KEY,
  quiz_name TEXT
);

INSERT INTO quiz (quiz_name)
VALUES ('quiz1'), ('quiz2'), ('quiz3'), ('quiz4');

CREATE TABLE topic (
  topic_id INT AUTO_INCREMENT PRIMARY KEY,
  topic_name TEXT
);

CREATE TABLE difficulty (
  difficulty_id INT AUTO_INCREMENT PRIMARY KEY,
  difficulty_level INT
);

CREATE TABLE question (
  question_id INT AUTO_INCREMENT PRIMARY KEY,
  quiz_id INT,
  topic_id INT,
  difficulty_id INT,
  content TEXT,
  FOREIGN KEY (quiz_id) REFERENCES quiz (quiz_id),c
  FOREIGN KEY (topic_id) REFERENCES topic (topic_id),
  FOREIGN KEY (difficulty_id) REFERENCES difficulty (difficulty_id)
);

CREATE TABLE response (
  question_id INT PRIMARY KEY,
  text TEXT,
  correct BOOLEAN,
  FOREIGN KEY (question_id) REFERENCES question (question_id)
);
INSERT INTO topic (topic_name)
VALUES ('topic1'), ('topic2'), ('topic3'), ('topic4');

INSERT INTO difficulty (difficulty_level)
VALUES (1), (2), (3), (4);


CREATE USER 'backend'@'192.168.0.200' IDENTIFIED BY 'stR0ngP445!_';
GRANT SELECT, INSERT, UPDATE, DELETE ON quizapp.* TO 'backend'@'192.168.0.200';
FLUSH PRIVILEGES;