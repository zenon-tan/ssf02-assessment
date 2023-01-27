package ssf.ssfassessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import ssf.ssfassessment.model.Order;
import ssf.ssfassessment.model.Pizza;
import ssf.ssfassessment.service.PizzaService;

@Controller
@RequestMapping("/")
@SessionAttributes("pizzacart")
public class PizzaController {

    @Autowired
    PizzaService pSrc;

    @GetMapping
    public String showForm(Model model) {


        model.addAttribute("pizza", new Pizza());

        return "index";
        
    }

    @PostMapping(path = "/pizza", consumes = "application/x-www-form-urlencoded")
    public String showDetailForm(@Valid Pizza pizza, BindingResult binding, Order order, Model model, HttpSession sessions) {

        sessions.setAttribute("pizza", pizza);

        if(binding.hasErrors()) {

            model.addAttribute("pizza", pizza);
            return "index";

        }

        order = new Order();
        sessions.setAttribute("order", order);

        model.addAttribute("pizza", pizza);
        model.addAttribute("order", order);


        return "orderform";
    }

    @PostMapping(path = "/pizza/order", consumes = "application/x-www-form-urlencoded")
    public String showOrder(@Valid Order order, BindingResult binding, Model model, HttpSession sessions) {

        Pizza pizza = (Pizza) sessions.getAttribute("pizza");

        if(binding.hasErrors()) {

            model.addAttribute("order", order);
            return "orderform";

        }

        float finalcost = 0;

        float cost = pSrc.calculateCost(pizza.getPizza(), pizza.getSize(), pizza.getQuantity());

        if(order.getIsRush().equals("true")) {
            finalcost = cost + 2;
        } else if(order.getIsRush().equals("false")){
            finalcost = cost;
        }

        order.setType(pizza.getPizza());
        order.setSize(pizza.getSize());
        order.setQuantity(pizza.getQuantity());
        order.setTotalCost(finalcost);
        
        pSrc.saveJson(order);

        model.addAttribute("order", order);
        model.addAttribute("cost", cost);
        model.addAttribute("finalcost", finalcost);

        return "ordered";
    }
    
}
