package blog;

import java.io.IOException;
import java.net.URL;

import com.google.gdata.client.GoogleService;
import com.google.gdata.data.Entry;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.util.ServiceException;

public class Blogger{
	
	public static void createPost(GoogleService myService) throws ServiceException, IOException {
		// Create the entry to insert
	    Entry myEntry = new Entry();
	    // POST Title
	    myEntry.setTitle(new PlainTextConstruct("tytul "));
	        
	    // Post description
	    myEntry.setContent(new PlainTextConstruct("tresc"));
		        
	    // Blogger URL
	    URL postUrl = new URL("http://pastyy.blogspot.com");
	    myService.insert(postUrl, myEntry);
	}

	public static void main(String ar[]) throws ServiceException, IOException {
	    //creating Google service required to access and update Blogger post
	    GoogleService myService = new GoogleService("blogger", "http://pastyy.blogspot.com/");
	    myService.setUserCredentials("akcz4.pr0gmail.com", "patryk144");

	    createPost(myService );
	}
}


	
