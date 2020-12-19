## Key Changes

##Sorting Files
Most of the classes from the original files have been move into new packages based on functionality of the classes.A resources files is also created to stores all images.

##Made more specialised methods
Many methods were broken down into smaller pieces to make the code easier to work with

For example,
All initialisation of entities that was in the main method class have been moved to a new class called Objects and the class GameManager will call all other involved classes to create the game screen.

##Scene Conroller
A scene controller have been added to handle the screens in the game. Singleton Pattern was used to make sure only one class can be instantiate at a time.

##High Score
A high score have been added into the game. At the end of the game, a sceen will appear and promp the user to enter username. User is able to interact with the Scoreboard button to show a list of the high scores.
All score is saved and handle by the class ScoreHandler. the class will check and create a local file to store the score and methods like readscore() will reads the data and stores it in a array.

##Player controller
a player controller under the class PlayerHandler handles the user input and moves the player with the right image for movement.

