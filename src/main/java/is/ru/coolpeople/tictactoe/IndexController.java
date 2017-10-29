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

	@RequestMapping(value = "/design")
	public String design(Map<String, Object> model) {
		return "designReport";
	}

	@RequestMapping(value = "/administration")
	public String administration(Map<String, Object> model) {
		return "adminManual";
	}

	@RequestMapping(value = "/development")
	public String development(Map<String, Object> model) {
		return "devManual";
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
