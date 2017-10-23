package is.ru.coolpeople.tictactoe;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by olafur on 22/10/2017.
 */

@Controller
public class IndexController implements ErrorController {

	private static final String ERROR_PATH = "/error";

	@RequestMapping(value = "/")
	public String index(Map<String, Object> model) {
		model.put("message", "test");
		return "index";
	}


	@RequestMapping(value = ERROR_PATH)
	public String error() {
		return "Error handling";
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
}