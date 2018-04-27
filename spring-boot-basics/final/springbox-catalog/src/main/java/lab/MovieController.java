package lab;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;
import java.util.HashMap;


@RestController
public class MovieController {

	@Value("${greeting}")
	private String greeting;
	
	@RequestMapping("/")
	public String greet() {
		return greeting;
	}

}
