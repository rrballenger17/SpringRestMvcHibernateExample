

package hello;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(
    		@RequestParam(name="name", required=false, defaultValue="World") String name, 
    		Model model) {
    	
    	
        // look up books -> createCriteria(Book.class).list()
    	Session session = DatabaseConnection.getSession();
        final List<Book> books = session.createCriteria(Book.class).list();
        
        System.out.println("\n----\n");
        System.out.println(MessageFormat.format("Storing {0} books in the database", books.size()));
        
        String bookString = "";
        List<String> bookList = new ArrayList<String>();
        for (final Book b : books) {
            System.out.println(b.toString());
            bookString = bookString + b.toString();
            //bookString += "<br/>";
            bookList.add(b.toString());
        }
        
        
        System.out.println("\n----\n");
        session.close();
    	
        //model.addAllAttributes(bookList);
        model.addAttribute("books", bookList );
        
        model.addAttribute("name", name);
        return "greeting";
    }

}