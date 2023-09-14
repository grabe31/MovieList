import java.util.List;
import java.util.Scanner;
import controller.MovieHelper;
import model.Movie;

public class StartMovieDB {
static Scanner in = new Scanner(System.in);
static MovieHelper mh = new MovieHelper();

private static void addAnItem() {
// TODO Auto-generated method stub
System.out.print("Enter a name: ");
String name = in.nextLine();
System.out.print("Enter an director: ");
String director = in.nextLine();
Movie toAdd = new Movie(name, director);
mh.insertMovie(toAdd);
}

private static void deleteAnItem() {
// TODO Auto-generated method stub
System.out.print("Enter the movie to delete: ");
String name = in.nextLine();
System.out.print("Enter the director to delete: ");
String director = in.nextLine();
Movie toDelete = new Movie(name, director);
mh.deleteMovie(toDelete);
}

private static void editAnItem() {
// TODO Auto-generated method stub
System.out.println("How would you like to search? ");
System.out.println("1 : Search by Movie Name");
System.out.println("2 : Search by Director Name");
int searchBy = in.nextInt();
in.nextLine();

List<Movie> foundMovies;
if (searchBy == 1) {
System.out.print("Enter the Movie name: ");
String movieName = in.nextLine();
foundMovies = mh.searchForMovieByName(movieName);
} else {
System.out.print("Enter the Director: ");
String directorName = in.nextLine();
foundMovies = mh.searchForMovieByDirector(directorName);
}
if (!foundMovies.isEmpty()) {
System.out.println("Found Results.");
for (Movie l : foundMovies) {
System.out.println(l.getId() + " : " + l.toString());
}

System.out.print("Which ID to edit: ");
int idToEdit = in.nextInt();
Movie toEdit = mh.searchForMovieById(idToEdit);
System.out.println("Retrieved " + toEdit.getDirector() + " from" + toEdit.getName());
System.out.println("1 : Update Movie Name");
System.out.println("2 : Update Director Name");
int update = in.nextInt();
in.nextLine();
if (update == 1) {
System.out.print("New Movie: ");
String newName = in.nextLine();
toEdit.setName(newName);
} else if (update == 2) {
System.out.print("New Director: ");
String newDirector = in.nextLine();
toEdit.setDirector(newDirector);
}
mh.updateMovie(toEdit);
} else {
System.out.println("---- No results found");
}

}

public static void main(String[] args) {
// TODO Auto-generated method stub
runMenu();
}

public static void runMenu() {
boolean goAgain = true;
System.out.println("--- Welcome to our awesome shopping list!---");
while (goAgain) {
System.out.println("* Select an Movie:");
System.out.println("* 1 -- Add a Movie");
System.out.println("* 2 -- Edit a Movie");
System.out.println("* 3 -- Delete a Movie");
System.out.println("* 4 -- View the Movie list");
System.out.println("* 5 -- Exit the awesome program");
System.out.print("* Your selection: ");
int selection = in.nextInt();
in.nextLine();
if (selection == 1) {
addAnItem();
} else if (selection == 2) {
editAnItem();
} else if (selection == 3) {
deleteAnItem();
} else if (selection == 4) {
viewTheList();
} else {
mh.cleanUp();
System.out.println(" Goodbye! ");
goAgain = false;
}
}
}

private static void viewTheList() {
// TODO Auto-generated method stub
	List<Movie> allMovies = mh.showAllMovies();
	for(Movie singleMovie : allMovies) {
		System.out.println(singleMovie.returnMovieDetails());
	}
}
}