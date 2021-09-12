package demo.leabackend.exceptions;


public class HomeNotFoundException extends RuntimeException{
    public HomeNotFoundException(Long id) {
        super("Could not find home for user with id: " + id);
    }
}
