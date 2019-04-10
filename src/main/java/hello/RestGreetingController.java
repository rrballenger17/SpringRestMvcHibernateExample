package hello;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RestController;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class RestGreetingController {

    private static final String template = "Hello, %s!";
    
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/api/greeting")
    public List<Book> greeting(@RequestParam(value="name", defaultValue="World") String name) {
        //return new Greeting(counter.incrementAndGet(),
         //                   String.format(template, name));
    	 // look up books -> createCriteria(Book.class).list()
    	Session session = DatabaseConnection.getSession();
        final List<Book> books = session.createCriteria(Book.class).list();
        
        System.out.println("\n----\n");
        System.out.println(MessageFormat.format("Storing {0} books in the database", books.size()));
        
        String bookString = "";
        List<Book> bookList = new ArrayList<Book>();
        for (final Book b : books) {
            System.out.println(b.toString());
            bookString = bookString + b.toString();
            //bookString += "<br/>";
            bookList.add(b);
        }
        
        
        System.out.println("\n----\n");
        session.close();
    	
        //model.addAllAttributes(bookList);
        return bookList;

    }
}