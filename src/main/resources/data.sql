DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT PRIMARY KEY,
  username VARCHAR(25) UNIQUE,
  name VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS followers;

CREATE TABLE followers (
  follower_id INT,
  being_followed_id INT,
  FOREIGN KEY (follower_id) REFERENCES users,
  FOREIGN KEY (being_followed_id) REFERENCES users
);

DROP TABLE IF EXISTS photos;

CREATE TABLE photos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  author_id INT,
  title VARCHAR(250) DEFAULT NULL,
  url VARCHAR(5000) NOT NULL,
  timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (author_id) REFERENCES users
);

DROP TABLE IF EXISTS comments;

CREATE TABLE comments (
  id INT AUTO_INCREMENT PRIMARY KEY,
  photo_id INT,
  author_id INT,
  text VARCHAR(5000) NOT NULL,
  timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (photo_id) REFERENCES photos,
  FOREIGN KEY (author_id) REFERENCES users
);




INSERT INTO users (id, username, name) VALUES
  (1, 'august', 'August'),
  (2, 'paul', 'Paul'),
  (3, 'vincent', 'Vincent'),
  (0, 'admin', 'The Admin');

INSERT INTO followers(follower_id, being_followed_id) VALUES
  (1, 2),
  (1, 3),
  (2, 1),
  (3, 1);

INSERT INTO photos (id, author_id, title, url) VALUES
  (0, 1, 'Dance At Bougival', '/img/Renoir_Dance_At_Bougival.jpg'),
  (1, 1, 'Portrait of Jeane Samary', '/img/Renoir_Jeanne_Samary.jpg'),
  (2, 1, 'Still Life: Flowers', '/img/Renoir_Still_Life_Flowers.jpg'),
  (3, 2, 'Nature Morte Aux Oiseaux Exotiques', '/img/Gauguin_Nature_morte_aux_oiseaux_exotiques.jpg'),
  (4, 2, 'Sacred Spring, Sweet Dreams', '/img/Gauguin_Sacred_Spring_Sweet_Dreams.jpg'),
  (5, 2, 'When Will You Marry.jpg', '/img/Gauguin_When_Will_You_Marry.jpg'),
  (6, 3, 'Dr Paul Gachet', '/img/Van_Gogh_Dr_Paul_Gachet.jpg'),
  (7, 3, 'Starry Night', '/img/Van_Gogh_Starry_Night.jpg'),
  (8, 3, 'Sunflowers', '/img/Van_Gogh_Sunflowers.jpg');


INSERT INTO comments (photo_id, author_id, text) VALUES
  (0, 1, 'Comment from Alice to Alice Photo 1'),
  (0, 2, 'Comment from Bob to Alice Photo 1'),
  (3, 1, 'Comment from Alice to Bob Photo 1'),
  (6, 1, '"/><img src=x onerror=alert(1)>');

