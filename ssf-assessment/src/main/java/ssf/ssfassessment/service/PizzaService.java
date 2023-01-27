package ssf.ssfassessment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.json.Json;
import jakarta.json.JsonObject;
import ssf.ssfassessment.model.Order;
import ssf.ssfassessment.repo.PizzaRepo;

@Service
public class PizzaService {

    @Autowired
    PizzaRepo pRepo;

    public float calculateCost(String pizza, String size, int quantity) {

        float totalCost = 0;
        int pizzaPrice = 0;
        float multiplierPrice = 0;


        if(pizza.equals("bella") || pizza.equals("marinara") || pizza.equals("spianatacalabrese")) {
            pizzaPrice = 30;
        } else if(pizza.equals("margherita")) {
            pizzaPrice = 22;

        } else if(pizza.equals("trioformaggio")) {
            pizzaPrice = 25;
        }

        if(size.equals("sm")) {
            multiplierPrice = 1;
        } else if(size.equals("md")) {
            multiplierPrice = 1.2f;
        } else if(size.equals("lg")) {
            multiplierPrice = 1.5f;
        }

        totalCost = multiplierPrice * pizzaPrice * quantity;

        return totalCost;
    }

    public void saveJson(Order order) {

        JsonObject jsonObj =  Json.createObjectBuilder()
        .add("orderId", "<%s, %s>".formatted(order.getOrderId(), "string"))
        .add("name", "<%s, %s, %s>".formatted(order.getName(), "string", "view 1"))
        .add("address", "<%s, %s, %s>".formatted(order.getAddress(), "string", "view 1"))
        .add("phone", "<%s, %s, %s>".formatted(order.getPhone(), "string", "view 1"))
        .add("rush", "<%s, %s, %s>".formatted(order.getIsRush(), "boolean", "view 1"))
        .add("comments", "<%s, %s, %s>".formatted(order.getComments(), "string", "view 1"))
        .add("pizza", "<%s, %s, %s>".formatted(order.getType(), "string", "view 0"))
        .add("size", "<%s, %s, %s>".formatted(order.getSize(), "string", "view 0"))
        .add("quantity", "<%s, %s, %s>".formatted(order.getQuantity(), "string", "view 0"))
        .add("total", "<%s, %s>".formatted(order.getTotalCost(), "number"))
        .build();

        System.out.println(jsonObj.toString());

        pRepo.saveToRepo(order, jsonObj);

    }
    
}
