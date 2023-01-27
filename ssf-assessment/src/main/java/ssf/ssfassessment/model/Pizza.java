package ssf.ssfassessment.model;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Pizza implements Serializable {

    @NotNull(message = "You must select a pizza!")
    private String pizza;

    @NotNull(message = "You must select a Size!")
    private String size;

    @Min(value = 1, message = "Minimun order is 1")
    @Max(value = 10, message = "Maximum order is 10")
    private int quantity;

    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getPizza() {
        return pizza;
    }
    public void setPizza(String pizza) {
        this.pizza = pizza;
    }
    
}
