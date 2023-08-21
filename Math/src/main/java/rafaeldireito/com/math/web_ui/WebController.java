package rafaeldireito.com.math.web_ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping(value = "/")
    public String index () {
        return "index";
    }

    @RequestMapping(value = "/service/{serviceId}")
    public String serviceWebUI (Model model, @PathVariable("serviceId") String serviceId) {

        switch(serviceId) {
            case "odd-vs-even":
                model.addAttribute("serviceName", "Odd VS Even");
                model.addAttribute("serviceUrl", "/api/odd-vs-even/");
                model.addAttribute("title", "Is this number even or odd?");
                model.addAttribute("buttonText", "Check If Number Is Even or Odd");
                model.addAttribute("thumbnailColor", "#0f65a6");
                break;
            case "prime":
                model.addAttribute("serviceName", "Prime");
                model.addAttribute("serviceUrl", "/api/prime/");
                model.addAttribute("title", "Is this number prime?");
                model.addAttribute("buttonText", "Check If Number Is Prime");
                model.addAttribute("thumbnailColor", "#1e4620");
                break;
            default:
                // code block
                // Some code here - return 404
        }

        return "service";
    }

}
