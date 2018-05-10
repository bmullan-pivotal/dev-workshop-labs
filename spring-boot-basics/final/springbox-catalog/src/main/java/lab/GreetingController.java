package lab;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;


@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private static final String fullTemplate = "%s, %s!";
	private static Map<Long, Greeting> mapGreetings = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @RequestMapping(value = "/addGreeting",  method = RequestMethod.POST)
    public Greeting addGreeeting(
    	@RequestParam(value="greeting", defaultValue="Hello") String greeting,
    	@RequestParam(value="name", defaultValue="World!") String name ) {
    	Greeting g = new Greeting(counter.incrementAndGet(),
    		String.format(fullTemplate,greeting,name));
    	mapGreetings.put(new Long(g.getId()),g);
    	return g;
    }

    @RequestMapping(value = "/addGreetingJson",  method = RequestMethod.POST)
    public Greeting addGreeetingJson( @RequestBody Greeting greeting ) {
        mapGreetings.put(new Long(greeting.getId()),greeting);
        return greeting;
    }

    @RequestMapping(value = "/greetings/{id}", method = RequestMethod.GET)
	public Greeting getGreetingsByID(
	  @PathVariable("id") long id) {
	    // return "Get a specific Foo with id=" + id;
	    return mapGreetings.get(new Long(id));
	}

    @RequestMapping(value = "/greetings", method = RequestMethod.GET)
    public Collection<Greeting> getGreetings() {
        return mapGreetings.values();
    }

}
