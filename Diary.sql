CREATE TABLE IF NOT EXISTS diary (
  Current_day date NOT NULL,
  Title varchar(80) NOT NULL,
  Entry text NOT NULL,
  PRIMARY KEY (Current_day)
);

INSERT INTO diary (current_day, title, entry) VALUES
('2018-11-20', 'Building this Diary Application', 'Was not that hard');