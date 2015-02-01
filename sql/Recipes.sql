

DROP TABLE IF EXISTS comment; 
DROP TABLE IF EXISTS ingredient; 
DROP TABLE IF EXISTS rating; 
DROP TABLE IF EXISTS recipe; 
DROP TABLE IF EXISTS offensivewords;
DROP TABLE IF EXISTS `user`;

CREATE TABLE recipe (
    recipeid int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(100),
    intro varchar(1000),
    method varchar(2000),
    imgurl varchar(1000)
);

CREATE TABLE rating (
    recipeid int NOT NULL,
    userid int NOT NULL ,
    rating int NOT NULL,
    CONSTRAINT pk_rating PRIMARY KEY (recipeid, userid),
    CONSTRAINT chk_rating CHECK (rating>0 AND rating < 6),
    FOREIGN KEY (recipeid) REFERENCES recipe(recipeid) ON DELETE CASCADE,
    INDEX idxrecipeid (recipeid),
    INDEX idxuserid (userid)
);

CREATE TABLE comment (
    commentid int NOT NULL AUTO_INCREMENT PRIMARY KEY, 
    recipeid int NOT NULL,
    userid int NOT NULL,
    comment varchar(1000) NOT NULL,
    datetime DATETIME NOT NULL,
    INDEX idxrecipeid (recipeid),
    INDEX idxuserid (userid)
);

CREATE TABLE ingredient (
    recipeid int NOT NULL,
    ingredient varchar(100) NOT NULL, 
    qty decimal(7,2) NOT NULL,
    uom varchar(20) NOT NULL,
    INDEX idxrecipeid (recipeid),
    CONSTRAINT pk_ingredient PRIMARY KEY (recipeid, ingredient)
);

CREATE TABLE offensivewords (
    offensiveword varchar(30) NOT NULL,
    INDEX idxoffensiveword (offensiveword)
);


CREATE TABLE `user` (
  id int(11) NOT NULL AUTO_INCREMENT,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  username varchar(20) NOT NULL,
  password varchar(20) NOT NULL,
  PRIMARY KEY (id)
);
  




