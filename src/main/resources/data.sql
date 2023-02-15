INSERT INTO netology.FILES(name, data, active)
VALUES ('1.txt','', true);

INSERT INTO netology.FILES(name, data, active)
VALUES ('2.txt','', true);

INSERT INTO netology.FILES(name, data, active)
VALUES ('3.png','', true);

INSERT INTO netology.USERS(login, password, firstname, lastname, role, active)
VALUES ('user1','user1', 'user1', 'user1','USER', true);

INSERT INTO netology.USERS(login, password, firstname, lastname, role, active)
VALUES ('admin1','admin1', 'admin1', 'admin1','ADMIN', true);

INSERT INTO netology.TOKENS(token, active)
VALUES ('token1', true);

INSERT INTO netology.TOKENS(token, active)
VALUES ('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbnRvbiIsImV4cCI6MTY3NTYyOTc2MSwicm9sZXMiOlsiVVNFUiJdLCJmaXJzdE5hbWUiOiLQkNC90YLQvtC9In0.W7ZvX5gUt6NTDoqZPq5gEFv1BxIH3ED7-VSu4eHScE9V5M19uL7qtQd9udAFiCu3O_-tOXJFMt1SstuYOBih4Q', true);