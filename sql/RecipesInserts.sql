
-- delete from comment where datetime > "2014/11/27";

truncate table rating;
truncate table comment;
truncate table ingredient;
delete from recipe;
truncate table offensivewords;
truncate table `user`;

INSERT INTO `user` VALUES (null, "John", "Smith", "smithj", "password"),
  						  (null, "Mary", "Byrne", "byrnem", "password"),
  						  (null, "Frankie", "Cocozza", "cocozzaf", "password"),
  						  (null, "Patrick", "Jones", "jonesp", "password");

insert into recipe (name, intro, method, imgurl) values (
    "Easy chocolate cake", 
    "Perfect for birthdays, this is a great recipe for an easy, foolproof chocolate cake.",
    "Preheat the oven to 180C/350F/Gas 4. Grease and line two 20cm/8in sandwich tins.",
    "http://www.bbcgoodfood.com/sites/bbcgoodfood.com/files/recipe_images/recipe-image-legacy-id--265553_11.jpg");
insert into recipe (name, intro, method, imgurl) values (
    "Roast root vegetables", 
    "These roasted root vegetables are a delicious and healthy alternative to roast potatoes for your Sunday lunch.", 
    "Toss all the vegetables with the olive oil and put into a large roasting tin. Top with the thyme sprigs.",
    "http://ichef.bbci.co.uk/food/ic/food_16x9_448/recipes/roastrootvegetables_8820_16x9.jpg");
insert into recipe (name, intro, method, imgurl) values (
    "Shortbread", "Try making these easy, buttery biscuits as homemade Christmas presents, with chocolate chips worked into the dough.",
    "Stir in the flour to get a smooth paste. Turn on to a work surface and gently roll out until the paste is 1cm/�in thick.",
    "http://ichef.bbci.co.uk/food/ic/food_16x9_448/recipes/shortbread_1290_16x9.jpg");
insert into recipe (name, intro, method, imgurl) values (
    "Fluffy American pancakes", 
    "These pancakes are light and fluffy and great for a weekend brunch. ", 
    "Pour the milk mixture into the flour mixture and, using a fork, beat until you have a smooth batter. Any lumps will soon disappear with a little mixing. Let the batter stand for a few minutes.",
    "http://ichef.bbci.co.uk/food/ic/food_16x9_448/recipes/fluffyamericanpancak_74828_16x9.jpg");

insert into rating (recipeid, userid, rating) values (1, 2, 4);
insert into rating (recipeid, userid, rating) values (1, 3, 5);
insert into rating (recipeid, userid, rating) values (2, 3, 1);
insert into rating (recipeid, userid, rating) values (3, 4, 1);
insert into rating (recipeid, userid, rating) values (3, 2, 4);
insert into rating (recipeid, userid, rating) values (4, 1, 4);

insert into comment (recipeid, userid, comment, datetime) values (
    1, 3, "Very tasty!", "2014-11-11 11:30:45");
insert into comment (recipeid, userid, comment, datetime) values (
    2, 1, "Made me sick.", "2014-11-03 09:30:45");
insert into comment (recipeid, userid, comment, datetime) values (
    3, 4, "Looked better than it tasted.", "2014-11-12 11:30:45");
insert into comment (recipeid, userid, comment, datetime) values (
    4, 3, "I could do better.", "2014-10-05 11:16:45");
insert into comment (recipeid, userid, comment, datetime) values (
    2, 4, "Not too shabby.", "2013-10-01 08:16:45");
insert into comment (recipeid, userid, comment, datetime) values (
    4, 3, "Huh?", "2014-11-05 11:16:45");
insert into comment (recipeid, userid, comment, datetime) values (
    4, 3, "Bland.", "2013-10-15 01:16:45");
    
insert into ingredient(recipeid, ingredient, qty, uom) values (
    1, "plain flour", 225, "g");
insert into ingredient(recipeid, ingredient, qty, uom) values (
    1, "cocoa powder", 85, "g");
insert into ingredient(recipeid, ingredient, qty, uom) values (
    1, "eggs", 2, "ea");
insert into ingredient(recipeid, ingredient, qty, uom) values (
    1, "milk", 225, "ml");
    
insert into ingredient(recipeid, ingredient, qty, uom) values (
    2, "large carrots, peeled", 2, "ea");
insert into ingredient(recipeid, ingredient, qty, uom) values (
    2, "olive oil", 25, "ml");
insert into ingredient(recipeid, ingredient, qty, uom) values (
    2, "parsnips, peeled", 2, "ea");

insert into ingredient(recipeid, ingredient, qty, uom) values (
    3, "butter", 125, "g");
insert into ingredient(recipeid, ingredient, qty, uom) values (
    3, "caster sugar", 55, "g");
insert into ingredient(recipeid, ingredient, qty, uom) values (
    3, "plain flour", 225, "g");

insert into ingredient(recipeid, ingredient, qty, uom) values (
    4, "melted butter", 2, "tbsp");
insert into ingredient(recipeid, ingredient, qty, uom) values (
    4, "plain flour", 225, "g");
insert into ingredient(recipeid, ingredient, qty, uom) values (
    4, "baking powder", 1, "tsp");
insert into ingredient(recipeid, ingredient, qty, uom) values (
    4, "milk", 130, "ml");
insert into ingredient(recipeid, ingredient, qty, uom) values (
    4, "egg", 1, "ea");

insert into offensivewords(offensiveword) values ("one");
insert into offensivewords(offensiveword) values ("two");
insert into offensivewords(offensiveword) values ("three");
insert into offensivewords(offensiveword) values ("four");
insert into offensivewords(offensiveword) values ("five");
insert into offensivewords(offensiveword) values ("six");
insert into offensivewords(offensiveword) values ("seven");
insert into offensivewords(offensiveword) values ("eight");
insert into offensivewords(offensiveword) values ("nine");
insert into offensivewords(offensiveword) values ("ten");
    