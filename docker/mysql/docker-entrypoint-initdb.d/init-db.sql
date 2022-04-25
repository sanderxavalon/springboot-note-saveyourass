CREATE DATABASE bookstore;
use bookstore;

CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `author` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

INSERT INTO `book` VALUES (1,'Pride and Prejudice','Jane Austen'),(2,'To Kill a Mockingbird','Harper Lee'),(3,'The Great Gatsby','F. Scott Fitzgerald');

GRANT ALL PRIVILEGES ON bookstore.* TO 'superuser'@'%' IDENTIFIED BY 'Admin_123456';